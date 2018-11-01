/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilder;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLSourceViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.Assert;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.RetargetAction;
import org.eclipse.ui.editors.text.TextEditorActionContributor;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.RetargetTextEditorAction;


/**
 * This class provides actions for the SQL Builder editor. 
 */
public class SQLBuilderActionBarContributor extends TextEditorActionContributor /*implements ISelectionChangedListener */ {

    public static final String CONTENT_ASSIST_ACTION_ID = "ContentAssistProposal"; //$NON-NLS-1$
    public static final String CONTENT_ASSIST_ACTION_PREFIX = "ContentAssistProposal."; //$NON-NLS-1$
    
    public static final String CONTENT_TIP_ACTION_ID = "ContentTip"; //$NON-NLS-1$
    public static final String CONTENT_TIP_ACTION_PREFIX = "ContentTip."; //$NON-NLS-1$
    
    public static final String RUN_SQL_ACTION_ID = "RunSQLAction"; //$NON-NLS-1$
//    public static final String RUN_SQL_ACTION_LABEL = "datatools.sqlbuilder.RunSQLAction.label"; //$NON-NLS-1$
//    public static final String RUN_SQL_ACTION_TOOLTIP = "datatools.sqlbuilder.RunSQLAction.tooltip"; //$NON-NLS-1$
//    public static final String RUN_SQL_ACTION_PREFIX = "datatools.sqlbuilder.RunSQLAction."; //$NON-NLS-1$
    
    protected static final String RUN_SQL_ACTION_ICON_PATH = "icons/executesql.gif"; //$NON-NLS-1$
    
    public static final String REVERT_TO_PREVIOUS_ACTION_ID = "RevertToPreviousAction"; //$NON-NLS-1$
//    public static final String REVERT_TO_PREVIOUS_ACTION_LABEL = "datatools.sqlbuilder.RevertToPreviousAction.label"; //$NON-NLS-1$
//    public static final String REVERT_TO_PREVIOUS_ACTION_PREFIX = "datatools.sqlbuilder.RevertToPreviousAction."; //$NON-NLS-1$
    
    public static final String REVERT_TO_DEFAULT_ACTION_ID = "RevertToDefaultAction"; //$NON-NLS-1$
//    public static final String REVERT_TO_DEFAULT_ACTION_LABEL = "datatools.sqlbuilder.RevertToDefaultAction.label"; //$NON-NLS-1$
//    public static final String REVERT_TO_DEFAULT_ACTION_PREFIX = "datatools.sqlbuilder.RevertToDefaultAction."; //$NON-NLS-1$

    public static final String CHANGE_STATEMENT_TYPE_ACTION_ID = "ChangeStatementType"; //$NON-NLS-1$
//  public static final String CHANGE_STATEMENT_TYPE_ACTION_LABEL = "datatools.sqlbuilder.ChangeStatementTypeAction.label"; //$NON-NLS-1$
//  public static final String CHANGE_STATEMENT_TYPE_ACTION_PREFIX = "datatools.sqlbuilder.ChangeStatementTypeAction."; //$NON-NLS-1$
    
    public static final String OMIT_CURRENT_SCHEMA_ACTION_ID = "OmitCurrentSchema"; //$NON-NLS-1$
//  public static final String OMIT_CURRENT_SCHEMA_ACTION_LABEL = "datatools.sqlbuilder.OmitCurrentSchemaAction.label"; //$NON-NLS-1$
//  public static final String OMIT_CURRENT_SCHEMA_ACTION_PREFIX = "datatools.sqlbuilder.OmitCurrentSchemaAction."; //$NON-NLS-1$
    
    protected static final String RUN_MENU_ID = "org.eclipse.ui.run";  //$NON-NLS-1$
    protected static final String RUN_MENU_EXTERNAL_TOOLS_GROUP_ID = "ExternalToolsGroup";  //$NON-NLS-1$
    protected static final String RUN_MENU_RUN_SQL_ACTION_ID = "RunMenuRunSQLAction"; //$NON-NLS-1$
    
    protected static final String SQL_MENU_ID = "datatools.sqlbuilder.SQLMenu.ID"; //$NON-NLS-1$
    protected static final String SQL_MENU_RUN_SQL_ACTION_ID = "SQLMenuRunSQLAction"; //$NON-NLS-1$
    protected static final String SQL_MENU_REVERT_TO_PREVIOUS_ACTION_ID = "SQLMenuRevertToPreviousAction"; //$NON-NLS-1$
    protected static final String SQL_MENU_REVERT_TO_DEFAULT_ACTION_ID = "SQLMenuRevertToDefaultAction"; //$NON-NLS-1$
    protected static final String SQL_MENU_CHANGE_STATEMENT_TYPE_ACTION_ID = "SQLMenuChangeStatementTypeAction"; //$NON-NLS-1$
    protected static final String SQL_MENU_OMIT_CURRENT_SCHEMA_ACTION_ID = "SQLMenuOmitCurrentSchemaAction"; //$NON-NLS-1$
    
    protected static final String TOOLBAR_RUN_SQL_ACTION_ID = "ToolBarRunSQLAction"; //$NON-NLS-1$
    
    protected RetargetTextEditorAction fEditMenuContentAssistAction;
    protected RetargetTextEditorAction fEditMenuContentTipAction;
    protected RetargetAction fRunMenuRunSQLAction;
    protected RetargetAction fSQLMenuRunSQLAction;
    protected RetargetAction fToolBarRunSQLAction;
    protected RetargetAction fSQLMenuRevertToPreviousAction;
    protected RetargetAction fSQLMenuRevertToDefaultAction;
    protected RetargetAction fSQLMenuChangeStatementTypeAction;
    protected RetargetAction fSQLMenuOmitCurrentSchemaAction;
   
    private ExecuteAction fRunSQLAction;
    private RevertToPreviousAction fRevertToPreviousAction;
    private RevertToDefaultAction fRevertToDefaultAction;
    private ChangeStatementTypeAction fChangeStatementTypeAction;
    private OmitCurrentSchemaAction fOmitCurrentSchemaAction;
    
    /** The actions registered with the editor. */  
    private Map fActions = new HashMap(10);
    
    /**
     * Constructs an instance of this class.  This is the default constructor.
     */
    public SQLBuilderActionBarContributor() {
        super();
        
        /* Create the actions objects that are added to the menus and toolbar. */
        ResourceBundle resourceBundle = SQLBuilderPlugin.getPlugin().getResourceBundle();
        
        /* RetargetTextEditorActions are meant for text actions such as cut/copy/paste */
        fEditMenuContentAssistAction = new RetargetTextEditorAction(resourceBundle, CONTENT_ASSIST_ACTION_PREFIX);
        fEditMenuContentAssistAction.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
        
        fEditMenuContentTipAction = new RetargetTextEditorAction(resourceBundle, CONTENT_TIP_ACTION_PREFIX);
        fEditMenuContentTipAction.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_CONTEXT_INFORMATION);
        
        ImageDescriptor runSQLImage = ImageDescriptor.createFromFile( org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin.getPlugin().getClass(), RUN_SQL_ACTION_ICON_PATH );
        
        /* RetargetActions are all other actions. */
        fRunMenuRunSQLAction = new RetargetAction(RUN_MENU_RUN_SQL_ACTION_ID, Messages.datatools_sqlbuilder_RunSQLAction_label );
        fRunMenuRunSQLAction.setImageDescriptor(runSQLImage);
        
        fSQLMenuRunSQLAction = new RetargetAction(SQL_MENU_RUN_SQL_ACTION_ID, Messages.datatools_sqlbuilder_RunSQLAction_label );
        fSQLMenuRunSQLAction.setImageDescriptor(runSQLImage);
        
        fToolBarRunSQLAction = new RetargetAction(TOOLBAR_RUN_SQL_ACTION_ID, Messages.datatools_sqlbuilder_RunSQLAction_label );
        fToolBarRunSQLAction.setToolTipText( Messages.datatools_sqlbuilder_RunSQLAction_tooltip);
        fToolBarRunSQLAction.setImageDescriptor(runSQLImage);

        fSQLMenuRevertToPreviousAction = new RetargetAction(SQL_MENU_REVERT_TO_PREVIOUS_ACTION_ID, Messages.datatools_sqlbuilder_RevertToPreviousAction_label );    
        fSQLMenuRevertToDefaultAction = new RetargetAction(SQL_MENU_REVERT_TO_DEFAULT_ACTION_ID, Messages.datatools_sqlbuilder_RevertToDefaultAction_label);
        fSQLMenuChangeStatementTypeAction = new RetargetAction(SQL_MENU_CHANGE_STATEMENT_TYPE_ACTION_ID, Messages.datatools_sqlbuilder_ChangeStatementTypeAction_label );    
        fSQLMenuOmitCurrentSchemaAction = new RetargetAction(SQL_MENU_OMIT_CURRENT_SCHEMA_ACTION_ID, Messages.datatools_sqlbuilder_OmitCurrentSchemaAction_label );    
        
        /* Create the "handler" actions.  (These actions get bound to the menu and toolbar
         * action objects.  These actions do the actual work.) */
        fRunSQLAction = new ExecuteAction();
        fRunSQLAction.setId( RUN_SQL_ACTION_ID );
        setAction( RUN_SQL_ACTION_ID, fRunSQLAction );
        
        fRevertToPreviousAction = new RevertToPreviousAction();
        fRevertToPreviousAction.setId( REVERT_TO_PREVIOUS_ACTION_ID );
        fRevertToPreviousAction.setShell( Display.getCurrent().getActiveShell() );
        setAction( REVERT_TO_PREVIOUS_ACTION_ID, fRevertToPreviousAction );

        fRevertToDefaultAction = new RevertToDefaultAction();
        fRevertToDefaultAction.setId( REVERT_TO_DEFAULT_ACTION_ID );
        setAction( REVERT_TO_DEFAULT_ACTION_ID, fRevertToDefaultAction );

        fChangeStatementTypeAction = new ChangeStatementTypeAction();
        fChangeStatementTypeAction.setId( CHANGE_STATEMENT_TYPE_ACTION_ID );
        fChangeStatementTypeAction.setShell( Display.getCurrent().getActiveShell() );
        setAction( CHANGE_STATEMENT_TYPE_ACTION_ID, fChangeStatementTypeAction );
        
        fOmitCurrentSchemaAction = new OmitCurrentSchemaAction();
        fOmitCurrentSchemaAction.setId( OMIT_CURRENT_SCHEMA_ACTION_ID );
        fOmitCurrentSchemaAction.setShell( Display.getCurrent().getActiveShell() );
        setAction( OMIT_CURRENT_SCHEMA_ACTION_ID, fOmitCurrentSchemaAction );
    }
    
    /**
     * Contributes items to the given menu.
     * 
     * @param menuManager the menu to which to contribute
     * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToMenu(org.eclipse.jface.action.IMenuManager)
     */
    public void contributeToMenu(IMenuManager menuManager) {
        super.contributeToMenu( menuManager );
        
        /* Add actions to the Edit menu. */
//        IMenuManager editMenu = menuManager.findMenuUsingPath(IWorkbenchActionConstants.M_EDIT);
//        if (editMenu != null) {
//            editMenu.add(new Separator()); 
//            editMenu.add(fEditMenuContentAssistAction);
//            editMenu.add(fEditMenuContentTipAction);
//        }
        
        /* Add the Run SQL action to the Run menu. If we find an "External Tools" entry
         * is already in the menu, add our action ahead of it. */
        IMenuManager runMenu = menuManager.findMenuUsingPath( RUN_MENU_ID );
        if (runMenu != null) {
            IContributionItem externalToolsGroupSeperator = runMenu.findUsingPath( RUN_MENU_EXTERNAL_TOOLS_GROUP_ID );
            if (externalToolsGroupSeperator != null) {
                runMenu.insertBefore( RUN_MENU_EXTERNAL_TOOLS_GROUP_ID, new Separator() );
                runMenu.insertBefore( RUN_MENU_EXTERNAL_TOOLS_GROUP_ID, fRunMenuRunSQLAction );
            }
            else {
                runMenu.add( new Separator() );
                runMenu.add( fRunMenuRunSQLAction );
            }
        }

        /* Create the SQL Builder (SQL) menu and add actions to it. */

        IMenuManager sqlMenu = new MenuManager( Messages._UI_MENU_SQLBUILDER , SQL_MENU_ID );
        menuManager.insertAfter( IWorkbenchActionConstants.MB_ADDITIONS, sqlMenu);
        sqlMenu.add(fSQLMenuRunSQLAction);
        sqlMenu.add(fSQLMenuRevertToPreviousAction);            
        sqlMenu.add(fSQLMenuRevertToDefaultAction);            
        sqlMenu.add(fSQLMenuChangeStatementTypeAction);            
        sqlMenu.add(fSQLMenuOmitCurrentSchemaAction);            
    }

    /**
     * Contributes items to the given toolbar.
     * 
     * @param tbm the toolbar to which to contribute
     * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToToolBar(org.eclipse.jface.action.IToolBarManager)
     */
    public void contributeToToolBar(IToolBarManager tbm) {
        super.contributeToToolBar( tbm );
        
        tbm.add(new Separator());
        tbm.add( fToolBarRunSQLAction );
    }

    /**
     * Disposes of this object.
     */
    public void dispose() {
        /* Remove retarget actions as page listeners. */
        IWorkbenchPage workbenchPage = getPage();
        if (workbenchPage != null) {
            workbenchPage.removePartListener( fRunMenuRunSQLAction );
            workbenchPage.removePartListener( fSQLMenuRunSQLAction );
            workbenchPage.removePartListener( fSQLMenuRunSQLAction );
            workbenchPage.removePartListener( fSQLMenuRevertToPreviousAction );
            workbenchPage.removePartListener( fSQLMenuRevertToDefaultAction );
            workbenchPage.removePartListener( fSQLMenuChangeStatementTypeAction );
            workbenchPage.removePartListener( fSQLMenuOmitCurrentSchemaAction );
        }
    }

    /**
     * Returns the action installed under the given action id.
     * 
     * @param actionID the ID of the action wanted
     * @return the action, or null if none
     * @see #setAction(String, IAction)
     */
    public IAction getAction(String actionID) {
        Assert.isNotNull(actionID);
        IAction action= (IAction) fActions.get(actionID);
        
//        if (action == null) {
//            action= findContributedAction(actionID);
//            if (action != null)
//                setAction(actionID, action);
//        }
        
        return action;
    }

    /**
     * Initializes the contributor.  The contributor is expected to add contributions as required to
     * the action bars and global action handlers.
     * 
     * @see org.eclipse.ui.part.EditorActionBarContributor#init(org.eclipse.ui.IActionBars)
     */
    public void init(IActionBars bars) {
        super.init(bars);
    }

    /**
     * Initializes the contributor with the given set of action bars and the given
     * workbench page.
     * 
     * @see org.eclipse.ui.IEditorActionBarContributor#init(org.eclipse.ui.IActionBars, org.eclipse.ui.IWorkbenchPage)
     */
    public void init(IActionBars bars, IWorkbenchPage page) {
        super.init(bars, page);
        bars.setGlobalActionHandler(RUN_MENU_RUN_SQL_ACTION_ID, fRunSQLAction);
        bars.setGlobalActionHandler(SQL_MENU_RUN_SQL_ACTION_ID, fRunSQLAction);
        bars.setGlobalActionHandler(TOOLBAR_RUN_SQL_ACTION_ID, fRunSQLAction);
        bars.setGlobalActionHandler(SQL_MENU_REVERT_TO_PREVIOUS_ACTION_ID, fRevertToPreviousAction );
        bars.setGlobalActionHandler(SQL_MENU_REVERT_TO_DEFAULT_ACTION_ID, fRevertToDefaultAction );
        bars.setGlobalActionHandler(SQL_MENU_CHANGE_STATEMENT_TYPE_ACTION_ID, fChangeStatementTypeAction );
        bars.setGlobalActionHandler(SQL_MENU_OMIT_CURRENT_SCHEMA_ACTION_ID, fOmitCurrentSchemaAction );
        
        page.addPartListener(fRunMenuRunSQLAction);
        page.addPartListener(fSQLMenuRunSQLAction);
        page.addPartListener(fToolBarRunSQLAction);
        page.addPartListener(fSQLMenuRevertToPreviousAction);
        page.addPartListener(fSQLMenuRevertToDefaultAction);
        page.addPartListener(fSQLMenuChangeStatementTypeAction);
        page.addPartListener(fSQLMenuOmitCurrentSchemaAction);
        
        IWorkbenchPart activePart = page.getActivePart();
        if (activePart != null) {
            fRunMenuRunSQLAction.partActivated(activePart);
            fSQLMenuRunSQLAction.partActivated(activePart);
            fToolBarRunSQLAction.partActivated(activePart);
            fSQLMenuRevertToPreviousAction.partActivated(activePart);
            fSQLMenuRevertToDefaultAction.partActivated(activePart);
            fSQLMenuChangeStatementTypeAction.partActivated(activePart);
            fSQLMenuOmitCurrentSchemaAction.partActivated(activePart);
        }
    }
    
    /**
     * Installs the given action under the given action id.
     * 
     * @param actionID the action ID to set
     * @param action the action associated with the action ID
     * @see #getAction(String)
     */
    public void setAction(String actionID, IAction action) {
        Assert.isNotNull(actionID);
        if (action == null) {
            action = (IAction) fActions.remove(actionID);
        } else {
            fActions.put(actionID, action);
        }
    }

    /**
     * Sets the active editor for the contributor to the given editor.
     * 
     * @param activeEditor the new target editor
     * @see org.eclipse.ui.part.EditorActionBarContributor#setActiveEditor(org.eclipse.ui.IEditorPart)
     */
    public void setActiveEditor(IEditorPart activeEditor) {
        super.setActiveEditor( activeEditor );
        
        if (activeEditor instanceof SQLBuilderEditor) {
            SQLBuilderEditor sqlBuilder = (SQLBuilderEditor) activeEditor;
            sqlBuilder.setActionBarContributor( this );
            
            setActiveSQLBuilder(sqlBuilder.getSQLBuilder());
            
            IActionBars bars = getActionBars();
            if (bars != null) {
                bars.updateActionBars();
            }
        }
    }

	public void setActiveSQLBuilder(SQLBuilder sqlBuilder) {
		SQLSourceViewer sourceViewer = sqlBuilder.getSourceViewer();
		if (sourceViewer != null) {
		    IAction contentAssistAction = sourceViewer.getAction(CONTENT_ASSIST_ACTION_ID);
		    fEditMenuContentAssistAction.setAction(contentAssistAction);
		    
		    IAction contentTipAction = sourceViewer.getAction(CONTENT_TIP_ACTION_ID);
		    fEditMenuContentTipAction.setAction(contentTipAction);
		}
		
		fRunSQLAction.setSQLBuilder( sqlBuilder);
		fRevertToPreviousAction.setSQLBuilder( sqlBuilder);
		fRevertToDefaultAction.setSQLBuilder( sqlBuilder);
		fChangeStatementTypeAction.setSQLBuilder( sqlBuilder );
		fOmitCurrentSchemaAction.setSQLBuilder( sqlBuilder );
		
		SQLDomainModel domainModel = sqlBuilder.getDomainModel();
		if (domainModel != null) {
		    boolean enableRevert = !domainModel.isProper();
		    fRevertToPreviousAction.setEnabled(enableRevert);
		    fRevertToDefaultAction.setEnabled(enableRevert);
		    
		    boolean enableOmitCurrentSchema;
		    if (domainModel.getDatabaseDefinition() != null){
		    	enableOmitCurrentSchema = domainModel.getDatabaseDefinition().supportsSchema();
		    }
		    else {
		    	enableOmitCurrentSchema = false;
		    }
		    fOmitCurrentSchemaAction.setEnabled(enableOmitCurrentSchema);
         }
	}
} // end class