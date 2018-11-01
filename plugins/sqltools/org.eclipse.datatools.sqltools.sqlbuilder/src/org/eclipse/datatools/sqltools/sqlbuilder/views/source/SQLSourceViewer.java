/*******************************************************************************
 * Copyright © 2000, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.views.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParseErrorInfo;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.parsers.sql.query.postparse.TableReferenceResolver;
import org.eclipse.datatools.sqltools.sqlbuilder.IContentChangeListener;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilder;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.SQLBuilderActionBarContributor;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.jface.util.Assert;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BidiSegmentEvent;
import org.eclipse.swt.custom.BidiSegmentListener;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.texteditor.DefaultRangeIndicator;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.IUpdate;


public class SQLSourceViewer extends ContentViewer implements ISelectionChangedListener, IMenuListener, IPropertyChangeListener, DisposeListener {

    protected SQLEditorDocumentProvider documentProvider;
    protected SourceViewer sourceViewer;
    protected SQLSourceViewerConfiguration configuration;
    protected IDocument document;
    protected SQLDomainModel sqlDomainModel;
    protected Object currentSelection;

    public Map actions = new HashMap(10);
    SourceViewerAction contentAssistAction;
    SourceViewerAction contentTipAction;
    protected boolean textChanged = false;
    private boolean isParseRequired = false;
    protected IContentChangeListener qListener = null;
    private ITextListener textChangeListener;
    protected SQLBuilder sqlbuilder;

    private String lastKnownProperSource;
    private boolean handleImproper;
    private String currentSQLStr = ""; //$NON-NLS-1$
    private String fileSQLStr = ""; //$NON-NLS-1$
    private boolean fileSQLStrChanged = false;
    
    public SQLSourceViewer(SQLDomainModel sqlDomainModel, Composite parent) {
        this.sqlDomainModel = sqlDomainModel;
        this.sqlbuilder = null;

        initSourceViewer(parent);
        initDragAndDrop();
        initTextWidget();
        initContextMenu();
        setLastKnownProperSource();
        handleImproper = false;
    }

    public SQLSourceViewer(SQLDomainModel sqlDomainModel, Composite parent, boolean handleImproper) {
        this(sqlDomainModel, parent);
        this.handleImproper = handleImproper;
    }    

    private void initSourceViewer(Composite parent) {
        configuration = new SQLSourceViewerConfiguration();

        documentProvider = new SQLEditorDocumentProvider();
        SQLSourceEditingEnvironment.connect();

        int styles = SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI | SWT.BORDER;
        sourceViewer = new SourceViewer(parent, new VerticalRuler(12), styles);
        sourceViewer.setRangeIndicator(new DefaultRangeIndicator());
        sourceViewer.configure(configuration);
        
        // override the default BiDi ordering 
        // RATLC01113834 
        sourceViewer.getTextWidget().addBidiSegmentListener(new BidiSegmentListener() {
            public void lineGetSegments(BidiSegmentEvent evt) {
                evt.segments = getSegments(evt.lineOffset, evt.lineText);
            }
        });
        
        textChangeListener = new ITextListener() {

            public void textChanged(TextEvent event) {
                setTextChanged(true);
                sqlDomainModel.setDirty(true);
                if (qListener != null) {
                    qListener.notifyContentChange();
                }
            }
        };
        sourceViewer.addTextListener(textChangeListener);
        sourceViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                Iterator actionsIterator = actions.values().iterator();
                while (actionsIterator.hasNext()) {
                    Object object = actionsIterator.next();
                    if (object instanceof TextViewerOperationAction) {
                        ((TextViewerOperationAction) object).update();
                    }
                }
            }
        });
    }
    
    /**
     * Searches a string and returns the segments containing qoutes '"'
     * @param offset the line offset
     * @param text the string text to search
     * @return an array of segments
     */
    protected int[] getSegments(int offset, String text)
    {
        if (text == null || text.equals(""))
        {
            return null;
        }
        boolean found = true;
        int searchIndex = 0;
        java.util.List segmentList = new ArrayList();
        segmentList.add(new Integer(0));
        while (found)
        {
            int index = text.indexOf('"', searchIndex);
            if (index == -1)
            {
                found = false;
            }
            if (index > 0)
            {
                segmentList.add(new Integer(index));                
            }
            searchIndex = index + 1;
        }        
        int temp[] = new int[segmentList.size()];
        for (int i=0;i<segmentList.size();i++)
        {
            temp[i] = ((Integer)segmentList.get(i)).intValue();            
        }
        return temp;        
    }

    private void initTextWidget() {

        setEditable(true);
        StyledText styledText = sourceViewer.getTextWidget();
        styledText.setFont(JFaceResources.getFontRegistry().get(JFaceResources.TEXT_FONT));
        JFaceResources.getFontRegistry().addListener(this);
        sourceViewer.getControl().addDisposeListener(this);

        styledText.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                // can only edit if root statement...
                if (isRootStatement()) {
                    if (e.stateMask == SWT.CTRL && e.character == ' ' && contentAssistAction != null) {
                        contentAssistAction.run();
                    }
                    if (e.stateMask == (SWT.SHIFT | SWT.CTRL) && e.character == ' ' && contentTipAction != null) {
                        contentTipAction.run();
                    }
                }
                setParseRequired(true);
            }
           

        });

        styledText.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent e) {
                //if contentassist is running, the ignore the focusLost event
                if (contentAssistAction.isActive()) {
                    return;
                }
                
                // [wsdbu00055322] bgp 03May2006, [RATLC01118321] bgp 18Aug2006
                // Ignore the focus lost event if the content assist is active.
                if (configuration.getIsContentAssistActive() == true) {
                    return;
                }
                                
                if (isTextChanged() == true)
                    reparse();

                // A call to validateEdit will cause a loss of focus, in that case, we
                // don't want to reparse

                // is context menu showing? if so - leave menu options active & do not reparse
                // on Linux extra focusLost gets called on right click
                boolean menuShowing = getControl().getMenu().isVisible();
                boolean sqlBuilderNotInValidate = (sqlbuilder != null) && (sqlbuilder.inValidateEditCall() == false);

                if (!menuShowing && (sqlbuilder == null || sqlBuilderNotInValidate)) {
                    reparse();
                    Iterator aIterator = actions.values().iterator();
                    while (aIterator.hasNext()) {
                        Object object = aIterator.next();
                        ((Action) object).setEnabled(false);
                    }
                }
            }

            public void focusGained(FocusEvent e) {
                Iterator aIterator = actions.values().iterator();

                while (aIterator.hasNext()) {
                    Object object = aIterator.next();
                    if (object instanceof TextViewerOperationAction) {
                        TextViewerOperationAction textAction = (TextViewerOperationAction) object;
                        textAction.update();
                    }
                    else if (object instanceof SourceViewerAction) {
                        SourceViewerAction sourceAction = (SourceViewerAction) object;
                        sourceAction.setEnabled(true);
                    }
                }
                contentAssistAction.setAsActive(false);
            }
        });

    }

    //menu setup
    private void initContextMenu() {
        createActions();
        MenuManager contextMenu = new MenuManager("#PopUp"); //$NON-NLS-1$
        contextMenu.add(new Separator("additions")); //$NON-NLS-1$
        contextMenu.addMenuListener(this);
        Menu menu = contextMenu.createContextMenu(getControl());
        getControl().setMenu(menu);

    }

    //
    // Add drag and drop support for receiving rdbtables
    //
    private void initDragAndDrop() {
        int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK;
        Transfer[] types = new Transfer[] { LocalTransfer.getInstance() };
        DropTarget target = new DropTarget(getControl(), operations);
        target.setTransfer(types);

        target.addDropListener(new org.eclipse.datatools.sqltools.sqlbuilder.views.RDBTableDropListener(this, sqlDomainModel));
    }

    public void setSQLBuilder(SQLBuilder sqlbuilder) {
        this.sqlbuilder = sqlbuilder;
    }

    public void propertyChange(PropertyChangeEvent event) {
        if (event.getProperty() == JFaceResources.TEXT_FONT && sourceViewer != null) {
            sourceViewer.getTextWidget().setFont(JFaceResources.getFontRegistry().get(JFaceResources.TEXT_FONT));
        }
    }

    // pass in the editor using the source viewer
    // detect changes in source
    public void setContentChangeListener(IContentChangeListener qListener) {
        this.qListener = qListener;
    }

    public void initDBContext() {
        if (sqlDomainModel != null) {
            // [wsdbu00055322] bgp 28Apr2006
            ConnectionContext connContext = new ConnectionContext();
            ISQLEditorConnectionInfo connInfo = sqlDomainModel.getConnectionInfo();
            // [RATLC01120919] bgp 07July2006 
            if (connInfo != null) {
                SQLBuilderDBProposalsService dbProposalsService = new SQLBuilderDBProposalsService( connInfo ); // [RATLC01118321] bgp 22Sep2006, RATLC01136221 bgp 10Jan2007
                connContext.setDBProposalsService(dbProposalsService);
            }
            connContext.setDomainModel(sqlDomainModel);
            configuration.addDBContext(connContext);
        }
    }

    public void inputChanged(Object input, Object oldInput) {
        sourceViewer.removeTextListener(textChangeListener);
        if (oldInput != null) {
            documentProvider.disconnect(oldInput);
        }
        try {
            documentProvider.connect(input);
            IAnnotationModel model = documentProvider.getAnnotationModel(input);
            document = documentProvider.getDocument(input);
            sourceViewer.setDocument(document, model);

            if (currentSQLStr.length() > 0 && !currentSQLStr.equals(sqlDomainModel.getSQLStatement().getSQL())) {
                setTextChanged(true);
                sqlDomainModel.setDirty(true);
                if (sqlDomainModel.isUnmatchedSource()) {
                    currentSQLStr = sqlDomainModel.getInitialSource();                    
                    document.set(currentSQLStr);
                    sqlDomainModel.setUnmatchedSource(false);  
                    if (!StatementHelper.isTemplateSQL(currentSQLStr)) {
                        sqlbuilder.updateProperStatement(false);
                    }
                }
                else {
                    currentSQLStr = sqlDomainModel.getSQLStatement().getSQL();
                    fileSQLStrChanged = true;
                }
                
            }
            else if (currentSQLStr.length() == 0) {
                if (sqlDomainModel.isUnmatchedSource()) {
                    currentSQLStr = sqlDomainModel.getInitialSource();                    
                    document.set(currentSQLStr);
                    sqlDomainModel.setUnmatchedSource(false);  
                    if (!StatementHelper.isTemplateSQL(currentSQLStr)) {
                        sqlbuilder.updateProperStatement(false);
                    }
                }
                else {
                    currentSQLStr = sqlDomainModel.getSQLStatement().getSQL();
                    fileSQLStrChanged = true;
                }
            }

            if (fileSQLStr.length() > 0 && !fileSQLStrChanged) {
                if (!StatementHelper.getSQLSourceUnformatted(fileSQLStr).trim().equals(StatementHelper.getSQLSourceUnformatted(currentSQLStr).trim())) {
                    document.set(fileSQLStr);
                    sqlbuilder.updateProperStatement(false);
                }
            }
            //
            // Only allow the user to edit from the root as that is the place when reparse is allowed
            //
            if (input == sqlDomainModel.getSQLStatement()) {
                setEditable(true);
                //to keep display the invalid user input
                if (sqlDomainModel.getImproperStatement() != null && sqlDomainModel.getImproperStatement().length() > 0) {
                    document.set(sqlDomainModel.getImproperStatement());
                }
            }
            else {
                setEditable(false);
            }
        }
        catch (Exception e) {
        }
        sourceViewer.addTextListener(textChangeListener);
    }

    protected void handleDispose(DisposeEvent event) {
        SQLSourceEditingEnvironment.connect();
        super.handleDispose(event);
    }

    protected void setEditable(boolean isEditable) {
        sourceViewer.setEditable(isEditable);
        if (!isEditable) {
            sourceViewer.getTextWidget().setBackground(sourceViewer.getControl().getBackground());
        }
        else {
            sourceViewer.getTextWidget().setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

        }
    }

    protected boolean isEditable() {
        return sourceViewer.isEditable();
    }

    public Control getControl() {
        return sourceViewer.getTextWidget();
    }

    public void refresh() {
        if (isReparseRunning() != true) {
            setInput(getInput());
        }
    }

    public void setSelection(ISelection selection, boolean reveal) {
    }

    public ISelection getSelection() {
        return null;
    }

    public void refreshSource() {
        sourceViewer.removeTextListener(textChangeListener);
        currentSelection = getInput();
        String str = documentProvider.inputToStringHelper(currentSelection);
        if (document != null) {
            document.set(str);
        }
        sourceViewer.addTextListener(textChangeListener);
    }

    public void refreshSource(String inputStr) {
        //sourceViewer.removeTextListener(textChangeListener);
        document.set(inputStr);
        //sourceViewer.addTextListener(textChangeListener);
    }

    public void revertToLastKnownProperSource() {
        document.set(getLastKnownProperSource());
        forceReparse();
    }

    public void revertToDefaultSource() {
        //sqlDomainModel.clearStatementToTemplate();
        String s = sqlDomainModel.getTemplateSQLForCurrentStatement();
        document.set(s);
        sqlDomainModel.setImproperStatement(null);
        forceReparse();
    }

    public void revertToInitialSource() {
        sourceViewer.removeTextListener(textChangeListener);
        document.set(sqlDomainModel.getInitialSource());
        sqlDomainModel.setImproperStatement(null);
        forceReparse();
        sourceViewer.addTextListener(textChangeListener);
    }

    public String getText() {
        return document.get();
    }

    // implement ISelectionChangedListener
    public void selectionChanged(SelectionChangedEvent event) {
    }

    public void setAction(String actionID, IAction action) {
        Assert.isNotNull(actionID);
        if (action == null) {
            actions.remove(actionID);
        }
        else {
            actions.put(actionID, action);
        }
    }

    public SQLDomainModel getDomainModel() {
        return sqlDomainModel;
    }

    public IAction getAction(String actionID) {
        Assert.isNotNull(actionID);
        return (IAction) actions.get(actionID);
    }

    protected void updateAction(String actionID) {
        Assert.isNotNull(actionID);
        IAction action = (IAction) actions.get(actionID);
        if (action != null && action instanceof TextViewerOperationAction) {
            ((TextViewerOperationAction) action).update();
        }
    }

    protected void addAction(IMenuManager menu, String actionID) {
        IAction action = getAction(actionID);
        if (action != null) {
            menu.add(action);
            if (action instanceof TextViewerOperationAction) {
                ((TextViewerOperationAction) action).update();
            }
            else if (action instanceof IUpdate) {
                ((IUpdate) action).update();
            }
        }
    }

    protected void createActions() {
        ResourceBundle resourceBundle = SQLBuilderPlugin.getPlugin().getResourceBundle();

        setAction("Cut", new TextViewerOperationAction(sourceViewer, ITextOperationTarget.CUT)); //$NON-NLS-1$
        setAction("Copy", new TextViewerOperationAction(sourceViewer, ITextOperationTarget.COPY)); //$NON-NLS-1$
        setAction("Paste", new TextViewerOperationAction(sourceViewer, ITextOperationTarget.PASTE)); //$NON-NLS-1$

        contentAssistAction = new SourceViewerAction(resourceBundle, "ContentAssistProposal_", sourceViewer, ISourceViewer.CONTENTASSIST_PROPOSALS); //$NON-NLS-1$
        contentAssistAction.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
        contentAssistAction.setAccelerator(SWT.CTRL | ' ');
        setAction("ContentAssistProposal", contentAssistAction); //$NON-NLS-1$

        contentTipAction = new SourceViewerAction(resourceBundle, "ContentTip_", sourceViewer, ISourceViewer.CONTENTASSIST_CONTEXT_INFORMATION); //$NON-NLS-1$
        contentTipAction.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_CONTEXT_INFORMATION);
        contentTipAction.setAccelerator(SWT.CTRL | SWT.SHIFT | ' ');
        setAction("ContentTip", contentTipAction); //$NON-NLS-1$
    }
    
    public boolean isRootStatement() {
        return getInput() == sqlDomainModel.getSQLStatement();
    }

    public void menuAboutToShow(IMenuManager menu) {
        SQLBuilderActionBarContributor contributor = sqlbuilder.getActionBarContributor();
        if (contributor != null) {
            menu.removeAll();
            addAction(menu, "Reparse"); //$NON-NLS-1$
            menu.add(new Separator("Text Operations")); //$NON-NLS-1$
            
            addAction(menu, "Cut"); //$NON-NLS-1$
            addAction(menu, "Copy"); //$NON-NLS-1$
            addAction(menu, "Paste"); //$NON-NLS-1$
            menu.add(new Separator("Content Assist Options")); //$NON-NLS-1$
            addAction(menu, "ContentAssistProposal"); //$NON-NLS-1$
            addAction(menu, "ContentTip"); //$NON-NLS-1$
            
            if (handleImproper) {
                //            revertAction.setSQLSourceViewer(this);
                //            defaultAction.setSQLSourceViewer(this);
                menu.add(new Separator());
                IAction revertToPreviousAction = contributor.getAction( SQLBuilderActionBarContributor.REVERT_TO_PREVIOUS_ACTION_ID );
                if (revertToPreviousAction != null) {
                    menu.add(revertToPreviousAction);
                }
                
                IAction revertToDefaultAction = contributor.getAction( SQLBuilderActionBarContributor.REVERT_TO_DEFAULT_ACTION_ID );
                if (revertToDefaultAction != null) {
                    menu.add(revertToDefaultAction);                    
                }
                
                boolean proper = sqlDomainModel.isProper();
                revertToPreviousAction.setEnabled(!proper);
                revertToDefaultAction.setEnabled(!proper);
            }

            IAction changeStatementTypeAction = contributor.getAction( SQLBuilderActionBarContributor.CHANGE_STATEMENT_TYPE_ACTION_ID );
            menu.add(changeStatementTypeAction);
            changeStatementTypeAction.setEnabled(true);
            
            menu.add( new Separator("Omit Current Schema")); //$NON-NLS-1$
            IAction omitCurrentSchemaAction = contributor.getAction( SQLBuilderActionBarContributor.OMIT_CURRENT_SCHEMA_ACTION_ID );
            menu.add(omitCurrentSchemaAction);
            omitCurrentSchemaAction.setEnabled(true);
            
            menu.add( new Separator("Run SQL")); //$NON-NLS-1$
            IAction runSQLAction = contributor.getAction( SQLBuilderActionBarContributor.RUN_SQL_ACTION_ID );
            if (runSQLAction != null) {
                menu.add( runSQLAction );
            }
            
            
            // Only enable re-parse on the root statement object, not the sub-selects
            if (isRootStatement()) {
                contentAssistAction.setEnabled(true);
                contentTipAction.setEnabled(true);
            }
            else {
                contentAssistAction.setEnabled(false);
                contentTipAction.setEnabled(false);
            }
        }
    }

    protected boolean reparseRunning = false;

    protected boolean isReparseRunning() {
        return reparseRunning;
    }

    public boolean reparse() {
        boolean isParseSuccess = false;
        ArrayList errorList = new ArrayList();

        if (handleImproper && getInput() instanceof QueryStatement) {
            // Model manipulation through UI results isTextChanged() == true, but there is no need to 
            // parse in this case.A parse occurs only if the the text is manually changed in the source area 
            if (isTextChanged() == true && isParseRequired) {
                String currentSQL = this.getText();
                reparseRunning = true;
                QueryStatement previousStmt = sqlDomainModel.getSQLStatement();
                String name = previousStmt.getName();
                QueryStatement parsedStmt = null;
                try {
                    parsedStmt = sqlDomainModel.parse(currentSQL, errorList);
                }
                catch (SQLParserException e) {
                }
                catch (SQLParserInternalException e) {
                }
                // Check for post parsed processing errors.If there are errors, 
                // disable Graph and Design views. The parse may be good in this case so 
                // this will proceed as isParseSuccess is true

                if (errorList.size() > 0) {
                    SQLParseErrorInfo errorInfo = (SQLParseErrorInfo) errorList.get(0);
                    if (sqlbuilder != null) {
                        sqlbuilder.getDesignViewer().setEnabled(isParseSuccess);
                        sqlbuilder.getGraphViewer().setEnabled(isParseSuccess);

                        //SQLBuilderPlugin plugin = SQLBuilderPlugin.getPlugin();
                        String parseErrMsg = getParseErrorMessageString(errorInfo);
                        handleImproperStatement(parsedStmt.getSQL());
                        
                        MessageDialog.openWarning(Display.getCurrent().getActiveShell(), Messages._UI_VALIDATE_FAILED_TITLE, parseErrMsg ); 
                    
                    }
                }
                
                // Statement parsed OK.
                if (parsedStmt != null && errorList.size()==0) {
                    
                    // Make sure the new statement type is supported by the SQB.
                    int previousStmtType = StatementHelper.getStatementType(previousStmt);
                    int newStmtType = StatementHelper.getStatementType(parsedStmt);
                    if (sqlDomainModel.getIsStatementTypeSupported(newStmtType) == true) {
                        // Determine if the statement type has changed.
                        if (newStmtType != previousStmtType) {
                            // When the statement type has changed, reset the query model
                            // to the template statement of the new type.  This will cause
                            // the UI to reconfigure for the new statement type.
                            if (sqlbuilder != null) {
                                sqlbuilder.changeStatementType(newStmtType); 
                            }
                        }

                        // Indicate that the parse succeeded and replace the current
                        // statement in the domain model with the new parsed statement. 
                        isParseSuccess = true;
                        parsedStmt.setName(name);
                        sqlDomainModel.replaceStatementContents(parsedStmt);
                        refreshSource();
                    }
                }
                // ignore the parse fail for template SQL 
                else if (StatementHelper.isTemplateSQL(currentSQL)) {
                    //source editing might have built up the model, hence clear it 
                    sqlDomainModel.clearStatementToTemplate();
                    isParseSuccess = true;
                }
                if (errorList.size() == 0) {
                    handleParseStatusChange(isParseSuccess, sqlDomainModel.getSQLStatement(), currentSQL);
                }
                reparseRunning = false;
                setTextChanged(false);
            }
        }
        else {
            noUnsupportedReparse();
        }
        setParseRequired(false);
        return isParseSuccess;
    }
    
    /**
     * Determines the appropriate error message string to be displayed for this error
     * @param errorInfo the SQLParseErrorInfo object containing the error information
     */
    private String getParseErrorMessageString(SQLParseErrorInfo errorInfo) {
        if (errorInfo != null) {
            //SQLBuilderPlugin plugin = SQLBuilderPlugin.getPlugin();
            String errorCode = errorInfo.getErrorCode();
            if (TableReferenceResolver.ERROR_CODE_TABLE_UNRESOLVED.equalsIgnoreCase(errorCode)) {
                // table not found
                String tableInError = errorInfo.getErrorSourceText();
                return NLS.bind(Messages._UI_GRAPH_NO_TABLE_FOUND, tableInError);
            }
            else if (TableReferenceResolver.ERROR_CODE_COLUMN_UNRESOLVED.equalsIgnoreCase(errorCode)
                    || TableReferenceResolver.ERROR_CODE_NONEXISTENT_COLUMN.equalsIgnoreCase(errorCode)) {
                // column not found
                String columnInError = errorInfo.getErrorSourceText();
                return NLS.bind(Messages._UI_GRAPH_NO_COLUMN_FOUND, columnInError); 
            }
        }
        return null;
    }
    
    public void noUnsupportedReparse() {
        if (isTextChanged() == true) {
            reparseRunning = true;
            refreshSource();
            reparseRunning = false;
            setTextChanged(false);
        }
    }

    public boolean forceReparse() {
        setTextChanged(true);
        setParseRequired(true);
        return reparse();
    }

    private void handleParseStatusChange(boolean success, QueryStatement currentSQLStatement, String statement) {
        if (!success) {
            // To clear out all the 'fields' of the SQLStatement, create a new default statement            
            // If we are working on a sub statement, use the string from the SQLDomainModel

            if ((currentSQLStatement instanceof QuerySelectStatement || currentSQLStatement instanceof QueryInsertStatement)
                    && !sqlbuilder.isContentOutlineRootSelected()) {
                String genString = ""; //$NON-NLS-1$
                genString = currentSQLStatement.getSQL();
                handleImproperStatement(genString);

            }
            else {
                handleImproperStatement(statement);
            }

            sqlbuilder.updateProperStatement(success);

        }
        else {
            sqlbuilder.updateProperStatement(success);
            sqlDomainModel.setImproperStatement(null);
            // Store the proper source in case the user wishes to revert back to this source
            lastKnownProperSource = statement;
        }
    }

    private void handleImproperStatement(String statement) {
        sqlDomainModel.setImproperStatement(statement);
    }

    public boolean getTextChanged() {
        return textChanged;
    }

    public boolean isTextChanged() {
        return getTextChanged();
    }

    public void setTextChanged(boolean changed) {
        textChanged = changed;
    }
    
    public void setParseRequired(boolean required) {
        isParseRequired = required;
    }
    public boolean getParseRequired() {
        return isParseRequired;
    }
    
    
    /**
     * Let the source view knows that the text is dirty from action such as
     * dropping a table in the Graph view.
     * FYI: the Textlistener should have detected the change but did not.  
     * This is a work-around solution
     * @param dirty whether or not the text is dirty
     */
    public void setTextDirty(boolean dirty) {
        if (dirty) {
            setTextChanged(true);
            sqlDomainModel.setDirty(true);
            if (qListener != null) {
                qListener.notifyContentChange();
            }
        }
    }

    /**
     * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(DisposeEvent)
     */
    public void widgetDisposed(DisposeEvent arg0) {
        JFaceResources.getFontRegistry().removeListener(this);
    }

    public String getLastKnownProperSource() {
        // always return the source from the model instance in sqlDomainModel
        // becuase it could have been modified through UI or though a parse
        QueryStatement stmt = sqlDomainModel.getSQLStatement();
        if(stmt != null){
            lastKnownProperSource = stmt.getSQL();
        }
        else {
            lastKnownProperSource = "";
        }
        return lastKnownProperSource;
    }

    private void setLastKnownProperSource() {
        QueryStatement stmt = sqlDomainModel.getSQLStatement();
        if (sqlDomainModel.isProper() && stmt != null) {
            lastKnownProperSource = stmt.getSQL();
        }
        else {
            lastKnownProperSource = ""; // SQLBuilderEditor.getDefaultStatement(stmt); //$NON-NLS-1$
        }

    }

    /**
     * @return Returns the fileSQLStr.
     */
    public String getFileSQLStr() {
        return fileSQLStr;
    }

    /**
     * @param fileSQLStr The fileSQLStr to set.
     */
    public void setFileSQLStr(String fileSQLStr) {
        this.fileSQLStr = fileSQLStr;
    }
    
    public IDocumentProvider getDocumentProvider() {
        return documentProvider;
    }
}
