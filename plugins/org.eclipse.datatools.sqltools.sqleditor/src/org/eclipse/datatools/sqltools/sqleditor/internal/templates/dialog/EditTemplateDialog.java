/**
 * Created on 2005-4-28
 * 
 * Copyright (c) Sybase, Inc. 2004 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.templates.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.IUpdate;


/**
 * Dialog to edit a template. Clients will usually instantiate, but may also may extend. This is just a copy of
 * org.eclipse.ui.texteditor.templates.EditTemplateDialog to make it visible.
 * 
 * @author Hui Cao
 *  
 */
public class EditTemplateDialog extends StatusDialog implements IContextProvider
{

    private static class TextViewerAction extends Action implements IUpdate
    {

        private int                  _fOperationCode = -1;
        private ITextOperationTarget _fOperationTarget;

        /**
         * Creates a new action.
         * 
         * @param viewer the viewer
         * @param operationCode the opcode
         */
        public TextViewerAction(ITextViewer viewer, int operationCode)
        {
            _fOperationCode = operationCode;
            _fOperationTarget = viewer.getTextOperationTarget();
            update();
        }

        /**
         * Updates the enabled state of the action. Fires a property change if the enabled state changes.
         * 
         * @see Action#firePropertyChange(String, Object, Object)
         */
        public void update()
        {

            boolean wasEnabled = isEnabled();
            boolean isEnabled = (_fOperationTarget != null && _fOperationTarget.canDoOperation(_fOperationCode));
            setEnabled(isEnabled);

            if (wasEnabled != isEnabled)
            {
                firePropertyChange(ENABLED, wasEnabled ? Boolean.TRUE : Boolean.FALSE, isEnabled ? Boolean.TRUE
                    : Boolean.FALSE);
            }
        }

        /**
         * @see Action#run()
         */
        public void run()
        {
            if (_fOperationCode != -1 && _fOperationTarget != null)
            {
                _fOperationTarget.doOperation(_fOperationCode);
            }
        }
    }

    private final Template                  _fTemplate;

    private Text                            _fNameText;
    private Text                            _fDescriptionText;
    private Combo                           _fContextCombo;
    private SourceViewer                    _fPatternEditor;
    private Button                          _fInsertVariableButton;
    private boolean                         _fIsNameModifiable;

    private StatusInfo                      _fValidationStatus;
    private boolean                         _fSuppressError     = true;                           // #4354
    private Map                             _fGlobalActions     = new HashMap(10);
    private List                            _fSelectionActions  = new ArrayList(3);
    private String[][]                      _fContextTypes;

    private ContextTypeRegistry             _fContextTypeRegistry;

    private final TemplateVariableProcessor _fTemplateProcessor = new TemplateVariableProcessor();

    private ContextProviderDelegate contextProviderDelegate = new ContextProviderDelegate(SQLEditorPlugin.getDefault().getBundle().getSymbolicName());
    
    public IContext getContext(Object target) {
        return contextProviderDelegate.getContext(target);
    }
    
    public int getContextChangeMask() {
        return contextProviderDelegate.getContextChangeMask();
    }
    
    public String getSearchExpression(Object target) {
        return contextProviderDelegate.getSearchExpression(target);
    }
    
    /**
     * Creates a new dialog.
     * 
     * @param parent the shell parent of the dialog
     * @param template the template to edit
     * @param edit whether this is a new template or an existing being edited
     * @param isNameModifiable whether the name of the template may be modified
     * @param registry the context type registry to use
     */
    public EditTemplateDialog(Shell parent, Template template, boolean edit, boolean isNameModifiable,
        ContextTypeRegistry registry)
    {
        super(parent);

        setShellStyle(getShellStyle() | SWT.MAX | SWT.RESIZE);

        String title = edit ? TextEditorTemplateMessages.EditTemplateDialog_title_edit
            : TextEditorTemplateMessages.EditTemplateDialog_title_new; 
        setTitle(title);

        _fTemplate = template;
        _fIsNameModifiable = isNameModifiable;

        List contexts = new ArrayList();
        for (Iterator it = registry.contextTypes(); it.hasNext();)
        {
            TemplateContextType type = (TemplateContextType) it.next();
            contexts.add(new String[]
            {
                type.getId(), type.getName()
            }
            );
        }
        _fContextTypes = (String[][]) contexts.toArray(new String[contexts.size()][]);

        _fValidationStatus = new StatusInfo();

        _fContextTypeRegistry = registry;

        TemplateContextType type = _fContextTypeRegistry.getContextType(template.getContextTypeId());
        _fTemplateProcessor.setContextType(type);
    }

    /*
     * @see org.eclipse.ui.texteditor.templates.StatusDialog#create()
     */
    public void create()
    {
        super.create();
        // update initial ok button to be disabled for new templates
        boolean valid = _fNameText == null || _fNameText.getText().trim().length() != 0;
        if (!valid)
        {
            StatusInfo status = new StatusInfo();
            status.setError(TextEditorTemplateMessages.EditTemplateDialog_error_noname); 
            updateButtonsEnableState(status);
        }
    }

    /*
     * @see Dialog#createDialogArea(Composite)
     */
    protected Control createDialogArea(Composite ancestor)
    {
        getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp(ancestor.getShell(), HelpUtil.getContextId(IHelpContextIds.EDIT_TEMPLATE_DIALOG, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));
        
        Composite parent = new Composite(ancestor, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        parent.setLayout(layout);
        parent.setLayoutData(new GridData(GridData.FILL_BOTH));

        ModifyListener listener = new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                doTextWidgetChanged(e.widget);
            }
        }
        ;

        if (_fIsNameModifiable)
        {
            createLabel(parent, TextEditorTemplateMessages.EditTemplateDialog_name); 

            Composite composite = new Composite(parent, SWT.NONE);
            composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            layout = new GridLayout();
            layout.numColumns = 3;
            layout.marginWidth = 0;
            layout.marginHeight = 0;
            composite.setLayout(layout);

            _fNameText = createText(composite);
            _fNameText.addModifyListener(listener);
            _fNameText.addFocusListener(new FocusListener()
            {

                public void focusGained(FocusEvent e)
                {
                }

                public void focusLost(FocusEvent e)
                {
                    if (_fSuppressError)
                    {
                        _fSuppressError = false;
                        updateButtons();
                    }
                }
            }
            );

            createLabel(composite, TextEditorTemplateMessages.EditTemplateDialog_context); 
            _fContextCombo = new Combo(composite, SWT.READ_ONLY);

            for (int i = 0; i < _fContextTypes.length; i++)
            {
                _fContextCombo.add(_fContextTypes[i][1]);
            }

            _fContextCombo.addModifyListener(listener);
        }

        createLabel(parent, TextEditorTemplateMessages.EditTemplateDialog_description); 

        int descFlags = _fIsNameModifiable ? SWT.BORDER : SWT.BORDER | SWT.READ_ONLY;
        _fDescriptionText = new Text(parent, descFlags);
        _fDescriptionText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        _fDescriptionText.addModifyListener(listener);

        Label patternLabel = createLabel(parent, TextEditorTemplateMessages.EditTemplateDialog_pattern); 
        patternLabel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
        _fPatternEditor = createEditor(parent);

        Label filler = new Label(parent, SWT.NONE);
        filler.setLayoutData(new GridData());

        Composite composite = new Composite(parent, SWT.NONE);
        layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData());

        _fInsertVariableButton = new Button(composite, SWT.NONE);
        _fInsertVariableButton.setLayoutData(getButtonGridData(_fInsertVariableButton));
        _fInsertVariableButton.setText(TextEditorTemplateMessages.EditTemplateDialog_insert_variable); 
        _fInsertVariableButton.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {
                _fPatternEditor.getTextWidget().setFocus();
                _fPatternEditor.doOperation(ISourceViewer.CONTENTASSIST_PROPOSALS);
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
            }
        }
        );

        _fDescriptionText.setText(_fTemplate.getDescription());
        if (_fIsNameModifiable)
        {
            _fNameText.setText(_fTemplate.getName());
            _fNameText.addModifyListener(listener);
            _fContextCombo.select(getIndex(_fTemplate.getContextTypeId()));
        }
        else
        {
            _fPatternEditor.getControl().setFocus();
        }
        initializeActions();

        applyDialogFont(parent);
        return composite;
    }

    private void doTextWidgetChanged(Widget w)
    {
        if (w == _fNameText)
        {
            _fSuppressError = false;
            String name = _fNameText.getText();
            _fTemplate.setName(name);
            updateButtons();
        }
        else if (w == _fContextCombo)
        {
            String name = _fContextCombo.getText();
            String contextId = getContextId(name);
            _fTemplate.setContextTypeId(contextId);
            _fTemplateProcessor.setContextType(_fContextTypeRegistry.getContextType(contextId));
        }
        else if (w == _fDescriptionText)
        {
            String desc = _fDescriptionText.getText();
            _fTemplate.setDescription(desc);
        }
    }

    private String getContextId(String name)
    {
        if (name == null)
        {
            return name;
        }

        for (int i = 0; i < _fContextTypes.length; i++)
        {
            if (name.equals(_fContextTypes[i][1]))
            {
                return _fContextTypes[i][0];
            }
        }
        return name;
    }

    private void doSourceChanged(IDocument document)
    {
        String text = document.get();
        _fTemplate.setPattern(text);
        _fValidationStatus.setOK();
        TemplateContextType contextType = _fContextTypeRegistry.getContextType(_fTemplate.getContextTypeId());
        if (contextType != null)
        {
            try
            {
                contextType.validate(text);
            }
            catch (TemplateException e)
            {
                _fValidationStatus.setError(e.getLocalizedMessage());
            }
        }

        updateUndoAction();
        updateButtons();
    }

    private static GridData getButtonGridData(Button button)
    {
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        // TODO get some button hints.
        //		data.heightHint= SWTUtil.getButtonHeightHint(button);

        return data;
    }

    private static Label createLabel(Composite parent, String name)
    {
        Label label = new Label(parent, SWT.NULL);
        label.setText(name);
        label.setLayoutData(new GridData());

        return label;
    }

    private static Text createText(Composite parent)
    {
        Text text = new Text(parent, SWT.BORDER);
        text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        return text;
    }

    private SourceViewer createEditor(Composite parent)
    {
        SourceViewer viewer = createViewer(parent);

        IDocument document = new Document(_fTemplate.getPattern());
        viewer.setEditable(true);
        viewer.setDocument(document);

        int nLines = document.getNumberOfLines();
        if (nLines < 5)
        {
            nLines = 5;
        }
        else if (nLines > 12)
        {
            nLines = 12;
        }

        Control control = viewer.getControl();
        GridData data = new GridData(GridData.FILL_BOTH);
        data.widthHint = convertWidthInCharsToPixels(80);
        data.heightHint = convertHeightInCharsToPixels(nLines);
        control.setLayoutData(data);

        viewer.addTextListener(new ITextListener()
        {
            public void textChanged(TextEvent event)
            {
                if (event.getDocumentEvent() != null)
                doSourceChanged(event.getDocumentEvent().getDocument());
            }
        }
        );

        viewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            public void selectionChanged(SelectionChangedEvent event)
            {
                updateSelectionDependentActions();
            }
        }
        );

        viewer.prependVerifyKeyListener(new VerifyKeyListener()
        {
            public void verifyKey(VerifyEvent event)
            {
                handleVerifyKeyPressed(event);
            }
        }
        );

        return viewer;
    }

    /**
     * Creates the viewer to be used to display the pattern. Subclasses may override.
     * 
     * @param parent the parent composite of the viewer
     * @return a configured <code>SourceViewer</code>
     */
    protected SourceViewer createViewer(Composite parent)
    {
        SourceViewer viewer = new SourceViewer(parent, null, null, false, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        SourceViewerConfiguration configuration = new SourceViewerConfiguration()
        {
            public IContentAssistant getContentAssistant(ISourceViewer sourceViewer)
            {

                ContentAssistant assistant = new ContentAssistant();
                assistant.enableAutoActivation(true);
                assistant.enableAutoInsert(true);
                assistant.setContentAssistProcessor(_fTemplateProcessor, IDocument.DEFAULT_CONTENT_TYPE);
                return assistant;
            }
        }
        ;
        viewer.configure(configuration);
        return viewer;
    }

    private void handleVerifyKeyPressed(VerifyEvent event)
    {
        if (!event.doit)
        {
            return;
        }

        if (event.stateMask != SWT.MOD1)
        {
            return;
        }

        switch (event.character)
        {
            case ' ':
                _fPatternEditor.doOperation(ISourceViewer.CONTENTASSIST_PROPOSALS);
                event.doit = false;
                break;

                // CTRL-Z
            case 'z' - 'a' + 1:
                _fPatternEditor.doOperation(ITextOperationTarget.UNDO);
                event.doit = false;
                break;
        }
    }

    private void initializeActions()
    {
        TextViewerAction action = new TextViewerAction(_fPatternEditor, ITextOperationTarget.UNDO);
        action.setText(TextEditorTemplateMessages.EditTemplateDialog_undo); 
        _fGlobalActions.put(ITextEditorActionConstants.UNDO, action);

        action = new TextViewerAction(_fPatternEditor, ITextOperationTarget.CUT);
        action.setText(TextEditorTemplateMessages.EditTemplateDialog_cut); 
        _fGlobalActions.put(ITextEditorActionConstants.CUT, action);

        action = new TextViewerAction(_fPatternEditor, ITextOperationTarget.COPY);
        action.setText(TextEditorTemplateMessages.EditTemplateDialog_copy); 
        _fGlobalActions.put(ITextEditorActionConstants.COPY, action);

        action = new TextViewerAction(_fPatternEditor, ITextOperationTarget.PASTE);
        action.setText(TextEditorTemplateMessages.EditTemplateDialog_paste); 
        _fGlobalActions.put(ITextEditorActionConstants.PASTE, action);

        action = new TextViewerAction(_fPatternEditor, ITextOperationTarget.SELECT_ALL);
        action.setText(TextEditorTemplateMessages.EditTemplateDialog_select_all); 
        _fGlobalActions.put(ITextEditorActionConstants.SELECT_ALL, action);

        action = new TextViewerAction(_fPatternEditor, ISourceViewer.CONTENTASSIST_PROPOSALS);
        action.setText(TextEditorTemplateMessages.EditTemplateDialog_content_assist); 
        _fGlobalActions.put("ContentAssistProposal", action); //$NON-NLS-1$

        _fSelectionActions.add(ITextEditorActionConstants.CUT);
        _fSelectionActions.add(ITextEditorActionConstants.COPY);
        _fSelectionActions.add(ITextEditorActionConstants.PASTE);

        // create context menu
        MenuManager manager = new MenuManager(null, null);
        manager.setRemoveAllWhenShown(true);
        manager.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager mgr)
            {
                fillContextMenu(mgr);
            }
        }
        );

        StyledText text = _fPatternEditor.getTextWidget();
        Menu menu = manager.createContextMenu(text);
        text.setMenu(menu);
    }

    private void fillContextMenu(IMenuManager menu)
    {
        menu.add(new GroupMarker(ITextEditorActionConstants.GROUP_UNDO));
        menu.appendToGroup(ITextEditorActionConstants.GROUP_UNDO, (IAction) _fGlobalActions
            .get(ITextEditorActionConstants.UNDO));

        menu.add(new Separator(ITextEditorActionConstants.GROUP_EDIT));
        menu.appendToGroup(ITextEditorActionConstants.GROUP_EDIT, (IAction) _fGlobalActions
            .get(ITextEditorActionConstants.CUT));
        menu.appendToGroup(ITextEditorActionConstants.GROUP_EDIT, (IAction) _fGlobalActions
            .get(ITextEditorActionConstants.COPY));
        menu.appendToGroup(ITextEditorActionConstants.GROUP_EDIT, (IAction) _fGlobalActions
            .get(ITextEditorActionConstants.PASTE));
        menu.appendToGroup(ITextEditorActionConstants.GROUP_EDIT, (IAction) _fGlobalActions
            .get(ITextEditorActionConstants.SELECT_ALL));

        menu.add(new Separator("templates")); //$NON-NLS-1$
        menu.appendToGroup("templates", (IAction) _fGlobalActions.get("ContentAssistProposal")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private void updateSelectionDependentActions()
    {
        Iterator iterator = _fSelectionActions.iterator();
        while (iterator.hasNext())
        updateAction((String) iterator.next());
    }

    private void updateUndoAction()
    {
        IAction action = (IAction) _fGlobalActions.get(ITextEditorActionConstants.UNDO);
        if (action instanceof IUpdate)
        {
            ((IUpdate) action).update();
        }
    }

    private void updateAction(String actionId)
    {
        IAction action = (IAction) _fGlobalActions.get(actionId);
        if (action instanceof IUpdate)
        {
            ((IUpdate) action).update();
        }
    }

    private int getIndex(String contextid)
    {

        if (contextid == null)
        {
            return -1;
        }

        for (int i = 0; i < _fContextTypes.length; i++)
        {
            if (contextid.equals(_fContextTypes[i][0]))
            {
                return i;
            }
        }
        return -1;
    }

    private void updateButtons()
    {
        StatusInfo status;

        boolean valid = _fNameText == null || _fNameText.getText().trim().length() != 0;
        if (!valid)
        {
            status = new StatusInfo();
            if (!_fSuppressError)
            {
                status.setError(TextEditorTemplateMessages.EditTemplateDialog_error_noname); 
            }
        }
        else
        {
            status = _fValidationStatus;
        }
        updateStatus(status);
    }

}
