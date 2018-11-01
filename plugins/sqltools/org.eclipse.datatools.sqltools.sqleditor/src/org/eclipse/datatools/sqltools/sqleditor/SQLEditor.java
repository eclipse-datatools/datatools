/***********************************************************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others. All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.common.ui.dialog.SaveAsDialog;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.services.ActionService;
import org.eclipse.datatools.sqltools.core.services.SQLEditorService;
import org.eclipse.datatools.sqltools.core.services.SQLEditorUIService;
import org.eclipse.datatools.sqltools.editor.contentassist.ISQLDBProposalsService;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.plan.IPlanOption;
import org.eclipse.datatools.sqltools.sql.parser.ParserParameters;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.datatools.sqltools.sql.parser.ast.SimpleNode;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorContributorExtensionRegistry;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorDocumentSetupParticipant;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.datatools.sqltools.sqleditor.internal.SymbolInserter;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.AddTemplateAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.DMLDialogSelectionSQLAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.ExecuteCurrentSQLAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.ExecuteSQLAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.ExecuteSQLAsOneStatementAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.ExecuteSelectionSQLAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.GotoMatchingTokenAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.SQLConnectAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.ToggleCommentAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.editor.SQLEditorContentOutlinePage;
import org.eclipse.datatools.sqltools.sqleditor.internal.editor.SQLOutlinePage;
import org.eclipse.datatools.sqltools.sqleditor.internal.editor.SQLSourceViewerConfiguration;
import org.eclipse.datatools.sqltools.sqleditor.internal.editor.SQLUpdater;
import org.eclipse.datatools.sqltools.sqleditor.internal.matching.AbstractPairMatcher;
import org.eclipse.datatools.sqltools.sqleditor.internal.matching.GenericSQLMatchingPairs;
import org.eclipse.datatools.sqltools.sqleditor.internal.matching.GenericSQLPairMatcher;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.ISQLPartitions;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLDBProposalsService;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLPartitionScanner;
import org.eclipse.datatools.sqltools.sqleditor.internal.utils.SQLColorProvider;
import org.eclipse.datatools.sqltools.sqleditor.preferences.ContentAssistPreference;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.AbstractInformationControlManager;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.text.ITextViewerExtension4;
import org.eclipse.jface.text.ITextViewerExtension5;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.IInformationProviderExtension;
import org.eclipse.jface.text.information.IInformationProviderExtension2;
import org.eclipse.jface.text.information.InformationPresenter;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationHoverExtension;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.eclipse.jface.text.source.ILineRange;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.ISourceViewerExtension2;
import org.eclipse.jface.text.source.ISourceViewerExtension3;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IPostSelectionProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.editors.text.FileDocumentProvider;
import org.eclipse.ui.editors.text.ILocationProvider;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.DefaultRangeIndicator;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.IUpdate;
import org.eclipse.ui.texteditor.ResourceAction;
import org.eclipse.ui.texteditor.TextEditorAction;
import org.eclipse.ui.texteditor.TextOperationAction;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * This class is responsible for configuring the SQL editor.
 */
public class SQLEditor extends TextEditor implements IPropertyChangeListener
{

    /**
     * Internal implementation class for a change listener.
     */
    protected abstract class AbstractSelectionChangedListener implements ISelectionChangedListener
    {

        /**
         * Installs this selection changed listener with the given selection provider. If the selection provider is a
         * post selection provider, post selection changed events are the preferred choice, otherwise normal selection
         * changed events are requested.
         * 
         * @param selectionProvider
         */
        public void install(ISelectionProvider selectionProvider)
        {
            if (selectionProvider == null)
            {
                return;
            }

            if (selectionProvider instanceof IPostSelectionProvider)
            {
                IPostSelectionProvider provider = (IPostSelectionProvider) selectionProvider;
                provider.addPostSelectionChangedListener(this);
            }
            else
            {
                selectionProvider.addSelectionChangedListener(this);
            }
        }

        /**
         * Removes this selection changed listener from the given selection provider.
         * 
         * @param selectionProvider the selection provider
         */
        public void uninstall(ISelectionProvider selectionProvider)
        {
            if (selectionProvider == null)
            {
                return;
            }

            if (selectionProvider instanceof IPostSelectionProvider)
            {
                IPostSelectionProvider provider = (IPostSelectionProvider) selectionProvider;
                provider.removePostSelectionChangedListener(this);
            }
            else
            {
                selectionProvider.removeSelectionChangedListener(this);
            }
        }
    }

    /**
     * Information provider used to present focusable information shells.
     * 
     */
    private static final class InformationProvider implements IInformationProvider, IInformationProviderExtension,
            IInformationProviderExtension2
    {

        private IRegion                    fHoverRegion;
        private Object                     fHoverInfo;
        private IInformationControlCreator fControlCreator;

        InformationProvider(IRegion hoverRegion, Object hoverInfo, IInformationControlCreator controlCreator)
        {
            fHoverRegion = hoverRegion;
            fHoverInfo = hoverInfo;
            fControlCreator = controlCreator;
        }

        /*
         * @see org.eclipse.jface.text.information.IInformationProvider#getSubject(org.eclipse.jface.text.ITextViewer,
         *      int)
         */
        public IRegion getSubject(ITextViewer textViewer, int invocationOffset)
        {
            return fHoverRegion;
        }

        /*
         * @see org.eclipse.jface.text.information.IInformationProvider#getInformation(org.eclipse.jface.text.ITextViewer,
         *      org.eclipse.jface.text.IRegion)
         */
        public String getInformation(ITextViewer textViewer, IRegion subject)
        {
            return fHoverInfo.toString();
        }

        /*
         * @see org.eclipse.jface.text.information.IInformationProviderExtension#getInformation2(org.eclipse.jface.text.ITextViewer,
         *      org.eclipse.jface.text.IRegion)
         * @since 3.2
         */
        public Object getInformation2(ITextViewer textViewer, IRegion subject)
        {
            return fHoverInfo;
        }

        /*
         * @see org.eclipse.jface.text.information.IInformationProviderExtension2#getInformationPresenterControlCreator()
         */
        public IInformationControlCreator getInformationPresenterControlCreator()
        {
            return fControlCreator;
        }
    }

    /**
     * This action behaves in two different ways: If there is no current text hover, the SQL information is displayed
     * using information presenter. If there is a current text hover, it is converted into a information presenter in
     * order to make it sticky.
     * 
     * @see AbstractTextEditor#InformationDispatchAction. Copied out to support Eclipse 3.2
     * 
     */
    private final class InformationDispatchAction extends TextEditorAction
    {

        /** The wrapped text operation action. */
        private final TextOperationAction fTextOperationAction;

        /**
         * Creates a dispatch action.
         * 
         * @param resourceBundle the resource bundle
         * @param prefix the prefix
         * @param textOperationAction the text operation action
         */
        public InformationDispatchAction(ResourceBundle resourceBundle, String prefix,
                final TextOperationAction textOperationAction)
        {
            super(resourceBundle, prefix, SQLEditor.this);
            if (textOperationAction == null)
                throw new IllegalArgumentException();
            fTextOperationAction = textOperationAction;
        }

        /*
         * @see org.eclipse.jface.action.IAction#run()
         */
        public void run()
        {

            ISourceViewer sourceViewer = getSourceViewer();
            if (sourceViewer == null)
            {
                if (fTextOperationAction.isEnabled())
                    fTextOperationAction.run();
                return;
            }

            if (sourceViewer instanceof ITextViewerExtension4)
            {
                ITextViewerExtension4 extension4 = (ITextViewerExtension4) sourceViewer;
                if (extension4.moveFocusToWidgetToken())
                    return;
            }

            if (sourceViewer instanceof ITextViewerExtension2)
            {
                // does a text hover exist?
                ITextHover textHover = ((ITextViewerExtension2) sourceViewer).getCurrentTextHover();
                if (textHover != null && makeTextHoverFocusable(sourceViewer, textHover))
                    return;
            }

            if (sourceViewer instanceof ISourceViewerExtension3)
            {
                // does an annotation hover exist?
                IAnnotationHover annotationHover = ((ISourceViewerExtension3) sourceViewer).getCurrentAnnotationHover();
                if (annotationHover != null && makeAnnotationHoverFocusable(sourceViewer, annotationHover))
                    return;
            }
            //            
            // otherwise, just run the action
            if (fTextOperationAction.isEnabled())
                fTextOperationAction.run();
        }

        /**
         * Tries to make a text hover focusable (or "sticky").
         * 
         * @param sourceViewer the source viewer to display the hover over
         * @param textHover the hover to make focusable
         * @return <code>true</code> if successful, <code>false</code> otherwise
         */
        private boolean makeTextHoverFocusable(ISourceViewer sourceViewer, ITextHover textHover)
        {
            Point hoverEventLocation = ((ITextViewerExtension2) sourceViewer).getHoverEventLocation();
            int offset = computeOffsetAtLocation(sourceViewer, hoverEventLocation.x, hoverEventLocation.y);
            if (offset == -1)
                return false;

            try
            {
                IRegion hoverRegion = textHover.getHoverRegion(sourceViewer, offset);
                if (hoverRegion == null)
                    return false;

                String hoverInfo = textHover.getHoverInfo(sourceViewer, hoverRegion);

                IInformationControlCreator controlCreator = null;
                if (textHover instanceof IInformationProviderExtension2)
                    controlCreator = ((IInformationProviderExtension2) textHover)
                            .getInformationPresenterControlCreator();

                IInformationProvider informationProvider = new InformationProvider(hoverRegion, hoverInfo,
                        controlCreator);

                _informationPresenter.setOffset(offset);
                _informationPresenter.setAnchor(AbstractInformationControlManager.ANCHOR_BOTTOM);
                _informationPresenter.setMargins(6, 6); // default values from AbstractInformationControlManager
                String contentType = TextUtilities.getContentType(sourceViewer.getDocument(),
                        getSourceViewerConfiguration().getConfiguredDocumentPartitioning(getSourceViewer()), offset,
                        true);
                _informationPresenter.setInformationProvider(informationProvider, contentType);
                _informationPresenter.showInformation();

                return true;

            }
            catch (BadLocationException e)
            {
                return false;
            }
        }

        /**
         * Tries to make an annotation hover focusable (or "sticky").
         * 
         * @param sourceViewer the source viewer to display the hover over
         * @param annotationHover the hover to make focusable
         * @return <code>true</code> if successful, <code>false</code> otherwise
         */
        private boolean makeAnnotationHoverFocusable(ISourceViewer sourceViewer, IAnnotationHover annotationHover)
        {
            IVerticalRulerInfo info = getVerticalRuler();
            int line = info.getLineOfLastMouseButtonActivity();
            if (line == -1)
                return false;

            try
            {

                // compute the hover information
                Object hoverInfo;
                if (annotationHover instanceof IAnnotationHoverExtension)
                {
                    IAnnotationHoverExtension extension = (IAnnotationHoverExtension) annotationHover;
                    ILineRange hoverLineRange = extension.getHoverLineRange(sourceViewer, line);
                    if (hoverLineRange == null)
                        return false;
                    final int maxVisibleLines = Integer.MAX_VALUE; // allow any number of lines being displayed, as we
                                                                    // support scrolling
                    hoverInfo = extension.getHoverInfo(sourceViewer, hoverLineRange, maxVisibleLines);
                }
                else
                {
                    hoverInfo = annotationHover.getHoverInfo(sourceViewer, line);
                }

                // hover region: the beginning of the concerned line to place the control right over the line
                IDocument document = sourceViewer.getDocument();
                int offset = document.getLineOffset(line);
                String contentType = TextUtilities.getContentType(document, getSourceViewerConfiguration()
                        .getConfiguredDocumentPartitioning(getSourceViewer()), offset, true);

                IInformationControlCreator controlCreator = null;
                if (annotationHover instanceof IInformationProviderExtension2)
                    controlCreator = ((IInformationProviderExtension2) annotationHover)
                            .getInformationPresenterControlCreator();
                else if (annotationHover instanceof IAnnotationHoverExtension)
                    controlCreator = ((IAnnotationHoverExtension) annotationHover).getHoverControlCreator();

                IInformationProvider informationProvider = new InformationProvider(new Region(offset, 0), hoverInfo,
                        controlCreator);

                _informationPresenter.setOffset(offset);
                _informationPresenter.setAnchor(AbstractInformationControlManager.ANCHOR_RIGHT);
                _informationPresenter.setMargins(4, 0);
                _informationPresenter.setInformationProvider(informationProvider, contentType);
                _informationPresenter.showInformation();

                return true;

            }
            catch (BadLocationException e)
            {
                return false;
            }
        }

        // modified version from TextViewer
        private int computeOffsetAtLocation(ITextViewer textViewer, int x, int y)
        {

            StyledText styledText = textViewer.getTextWidget();
            IDocument document = textViewer.getDocument();

            if (document == null)
                return -1;

            try
            {
                int widgetOffset = styledText.getOffsetAtLocation(new Point(x, y));
                Point p = styledText.getLocationAtOffset(widgetOffset);
                if (p.x > x)
                    widgetOffset--;

                if (textViewer instanceof ITextViewerExtension5)
                {
                    ITextViewerExtension5 extension = (ITextViewerExtension5) textViewer;
                    return extension.widgetOffset2ModelOffset(widgetOffset);
                }
                IRegion visibleRegion = textViewer.getVisibleRegion();
                return widgetOffset + visibleRegion.getOffset();
            }
            catch (IllegalArgumentException e)
            {
                return -1;
            }

        }
    }

    /**
     * Updates the selection in the editor's widget with the selection of the outline page.
     */
    protected class OutlineSelectionChangedListener extends AbstractSelectionChangedListener
    {
        public void selectionChanged(SelectionChangedEvent event)
        {
            ISelection selection = event.getSelection();
            Object element = ((IStructuredSelection) selection).getFirstElement();
            SimpleNode node = element instanceof SimpleNode ? (SimpleNode) element : null;

            setSelection(node);
        }
    }

    public class AdaptedSourceViewer extends ProjectionViewer
    {

        /**
         * @param parent
         * @param verticalRuler
         * @param overviewRuler
         * @param showAnnotationsOverview
         * @param styles
         */
        public AdaptedSourceViewer(Composite parent, IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
                boolean showAnnotationsOverview, int styles)
        {
            super(parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles);
        }

        public IContentAssistant getContentAssistant()
        {
            return fContentAssistant;
        }
    }

    class propertyChangeListener implements IPropertyChangeListener
    {

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
         */
        public void propertyChange(PropertyChangeEvent event)
        {
            AdaptedSourceViewer asv = (AdaptedSourceViewer) getSourceViewer();
            if (asv != null)
            {
                String p = event.getProperty();
                ContentAssistant contentAssistant = (ContentAssistant) asv.getContentAssistant();

                if (contentAssistant instanceof ContentAssistant)
                {
                    ContentAssistPreference.changeConfiguration((ContentAssistant) contentAssistant,
                            getPreferenceStore(), event);
                }
                SQLEditor.this.handlePreferenceStoreChanged(event);
            }

        }

    }

    private class EditorSelectionChangedListener extends AbstractSelectionChangedListener
    {

        public void selectionChanged(SelectionChangedEvent event)
        {
            ISourceViewer sourceViewer = getSourceViewer();
            IDocument document = sourceViewer.getDocument();
            IRegion selection = getSignedSelection(sourceViewer);

            IRegion region = _pairMatcher.match(document, selection.getOffset());

            // Indicating the range between a pair of matching tokens, if they exist.
            if (region != null)
            {
                sourceViewer.setRangeIndication(region.getOffset(), region.getLength(), false);
                return;
            }

            // If no matching tokens, indicating current line.
            sourceViewer.setRangeIndication(selection.getOffset(), 0, false);
        }
    }

    public static final String                 PLUGIN_NAME                       = "org.eclipse.datatools.sqltools.sqleditor"; //$NON-NLS-1$
    public static final String                 HELP_CONTEXT_ID                   = PLUGIN_NAME + ".sqleditorhelp";            //$NON-NLS-1$

    /** The selection changed listener */
    protected AbstractSelectionChangedListener _fOutlineSelectionChangedListener = new OutlineSelectionChangedListener();
    /** The editor selection changed listener */
    private EditorSelectionChangedListener     _fEditorSelectionChangedListener  = null;
    /** The content outline page providing the outline view for the editor content. */
    private SQLOutlinePage                     _fOutlinePage                     = null;
    /** The object updating outline view and syntax validation. */
    protected SQLUpdater                       _fSQLUpdater                      = null;
    /** The projection (code folding) support object. */
    private ProjectionSupport                  fProjectionSupport;
    /** The document setup participant object, which is used partition the edit text. */
    private SQLEditorDocumentSetupParticipant  fDocSetupParticipant;
    /** The content assist proposal service for database objects. */
    private ISQLDBProposalsService             fDBProposalsService;
    /** The result value generated by SQLParser */
    protected ParsingResult                    _parsingResult                    = null;
    private SymbolInserter                     _symbolInserter                   = null;
    private ArrayList                          _profileListeners                 = new ArrayList();
    private InformationPresenter               _informationPresenter;
    private IEditorPart                        _parentEditor                     = null;
    /** The token matcher. */
    private ICharacterPairMatcher              _pairMatcher                      = null;
    /** Matching token painter. */
    private MatchingPairPainter                _painter                          = null;
    /** Tracking the cursor position */
    private int                                _previousCursorLine               = -1;

    /**
     * Constructs an instance of this class. This is the default constructor.
     */
    public SQLEditor()
    {
        super();
        _symbolInserter = new SymbolInserter(this);
    }

    public void init(IEditorSite site, IEditorInput input) throws PartInitException
    {
        super.init(site, input);
        fireConnectionProfileAttached();

        // Set corresponding token matcher, according to database type.
        SQLDevToolsUIConfiguration sqlDevToolsConfig = SQLToolsUIFacade.getConfiguration(getDBType(),
                getDatabaseIdentifier());
        _pairMatcher = sqlDevToolsConfig.getSQLEditorUIService().getSQLPairMatcher();

        // If the specific database token matcher is not supported, set default generic sql matcher.
        if (_pairMatcher == null)
        {
            _pairMatcher = new GenericSQLPairMatcher(GenericSQLMatchingPairs.getInstance());
        }

        ((AbstractPairMatcher) _pairMatcher).setSQLEditor(this);
    }

    /**
     * Creates and installs the editor actions.
     * 
     * @see org.eclipse.ui.texteditor.AbstractTextEditor#createActions()
     */
    protected void createActions()
    {
        super.createActions();
        ResourceBundle bundle = getResourceBundle();
        IActionBars bars = ((IEditorSite) getSite()).getActionBars();

        IAction a = new TextOperationAction(bundle,
                "ContentAssistProposal.", this, ISourceViewer.CONTENTASSIST_PROPOSALS); //$NON-NLS-1$
        a.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
        setAction("ContentAssistProposal", a); //$NON-NLS-1$

        a = new TextOperationAction(bundle, "ContentAssistTip.", this, ISourceViewer.CONTENTASSIST_CONTEXT_INFORMATION); //$NON-NLS-1$
        a.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_CONTEXT_INFORMATION);
        setAction("ContentAssistTip", a); //$NON-NLS-1$

        a = new TextOperationAction(bundle, "ContentFormat.", this, ISourceViewer.FORMAT); //$NON-NLS-1$
        setAction("ContentFormat", a); //$NON-NLS-1$

        a = new TextOperationAction(bundle, "ShowSQLInfo.", this, ISourceViewer.INFORMATION, true); //$NON-NLS-1$
        a = new InformationDispatchAction(bundle, "ShowSQLInfo.", (TextOperationAction) a); //$NON-NLS-1$
        a.setActionDefinitionId(ISQLEditorActionConstants.SHOW_INFORMATION_ACTION_ID);
        setAction(ISQLEditorActionConstants.SHOW_INFORMATION_ACTION_ID, a);

        // ToggleCommentAction
        a = new ToggleCommentAction(bundle, "SQLEditor.action.toggle.commect.", this); //$NON-NLS-1$
        setAction(ISQLEditorActionConstants.TOGGLE_COMMENT, a); //$NON-NLS-1$
        configureToggleCommentAction(a);
        markAsStateDependentAction(ISQLEditorActionConstants.TOGGLE_COMMENT, true);
        bars.setGlobalActionHandler(ISQLEditorActionConstants.TOGGLE_COMMENT,
                getAction(ISQLEditorActionConstants.TOGGLE_COMMENT));

        // GotoMatchingTokenAction
        setAction(ISQLEditorActionConstants.GOTO_MATCHING_TOKEN_ACTION_ID, new GotoMatchingTokenAction(this));
        markAsStateDependentAction(ISQLEditorActionConstants.GOTO_MATCHING_TOKEN_ACTION_ID, true);
        bars.setGlobalActionHandler(ISQLEditorActionConstants.GOTO_MATCHING_TOKEN_ACTION_ID,
                getAction(ISQLEditorActionConstants.GOTO_MATCHING_TOKEN_ACTION_ID));

        setAction(ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID, new ExecuteSQLAction(this));
        bars.setGlobalActionHandler(ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID,
                getAction(ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID));

        setAction(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID, new ExecuteSelectionSQLAction(this));
        markAsSelectionDependentAction(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID, true);
        bars.setGlobalActionHandler(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID,
                getAction(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID));

        setAction(ISQLEditorActionConstants.EXECUTE_SQL_AS_ONE_STATEMENT_ACTION_ID, new ExecuteSQLAsOneStatementAction(this));
        markAsSelectionDependentAction(ISQLEditorActionConstants.EXECUTE_SQL_AS_ONE_STATEMENT_ACTION_ID, true);
        bars.setGlobalActionHandler(ISQLEditorActionConstants.EXECUTE_SQL_AS_ONE_STATEMENT_ACTION_ID,
                getAction(ISQLEditorActionConstants.EXECUTE_SQL_AS_ONE_STATEMENT_ACTION_ID));
        
        setAction(ISQLEditorActionConstants.EXECUTE_CURRENT_SQL_ACTION_ID, new ExecuteCurrentSQLAction(this));
        markAsSelectionDependentAction(ISQLEditorActionConstants.EXECUTE_CURRENT_SQL_ACTION_ID, true);
        bars.setGlobalActionHandler(ISQLEditorActionConstants.EXECUTE_CURRENT_SQL_ACTION_ID, 
                getAction(ISQLEditorActionConstants.EXECUTE_CURRENT_SQL_ACTION_ID));
        
        setAction(ISQLEditorActionConstants.DMLDIALOG_SELECTION_SQL_ACTION_ID, new DMLDialogSelectionSQLAction(this));
        markAsSelectionDependentAction(ISQLEditorActionConstants.DMLDIALOG_SELECTION_SQL_ACTION_ID, true);
        bars.setGlobalActionHandler(ISQLEditorActionConstants.DMLDIALOG_SELECTION_SQL_ACTION_ID,
                getAction(ISQLEditorActionConstants.DMLDIALOG_SELECTION_SQL_ACTION_ID));

        setAction(ISQLEditorActionConstants.SAVE_AS_TEMPLATE_ACTION_ID, new AddTemplateAction(getResourceBundle(),
                "AddTemplateAction.", this)); //$NON-NLS-1$
        markAsSelectionDependentAction(ISQLEditorActionConstants.SAVE_AS_TEMPLATE_ACTION_ID, true);
        bars.setGlobalActionHandler(ISQLEditorActionConstants.SAVE_AS_TEMPLATE_ACTION_ID,
                getAction(ISQLEditorActionConstants.SAVE_AS_TEMPLATE_ACTION_ID));

        final Shell shell;
        if (getSourceViewer() != null)
            shell = getSourceViewer().getTextWidget().getShell();
        else
            shell = null;
        a = new ResourceAction(bundle, "Editor.ContextPreferencesAction.") { //$NON-NLS-1$
            public void run()
            {
                String[] preferencePages = collectContextMenuPreferencePages();
                if (preferencePages.length > 0 && (shell == null || !shell.isDisposed()))
                    PreferencesUtil.createPreferenceDialogOn(shell, preferencePages[0], preferencePages, null).open();
            }
        };
        a.setId(ITextEditorActionConstants.CONTEXT_PREFERENCES);
        setAction(ITextEditorActionConstants.CONTEXT_PREFERENCES, a);

    }

    /**
     * Creates and returns the content outline page for the SQL editor.
     * 
     * @return the new content outline page
     */
    protected SQLEditorContentOutlinePage createContentOutlinePage()
    {
        SQLEditorContentOutlinePage outlinePage = new SQLEditorContentOutlinePage(getDocumentProvider(), this);

        return outlinePage;
    }

    /**
     * Creates the SWT controls for the editor.
     * 
     * @see org.eclipse.ui.texteditor.AbstractTextEditor#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent)
    {
        setupDocumentPartitioner();

        super.createPartControl(parent);
        setProjectionSupport(createProjectionSupport());

        // Register the editor selection change listener.
        _fEditorSelectionChangedListener = new EditorSelectionChangedListener();
        _fEditorSelectionChangedListener.install(getSelectionProvider());

        /*
         * Now that we have enabled source folding, make sure everything is expanded.
         */
        ProjectionViewer viewer = (ProjectionViewer) getSourceViewer();
        viewer.doOperation(ProjectionViewer.TOGGLE);

        ((TextViewer) this.getSourceViewer()).setDocumentPartitioning(ISQLPartitions.SQL_PARTITIONING);

        getSite().setSelectionProvider((SourceViewer) getSourceViewer());
        hookContextMenu();

        // only when there's control, need we update update outline and annotation
        installSQLUpdater();

        SQLEditorPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(new propertyChangeListener());

        IInformationControlCreator informationControlCreator = new IInformationControlCreator()
        {
            public IInformationControl createInformationControl(Shell shell)
            {
                boolean cutDown = false;
                int style = cutDown ? SWT.NONE : (SWT.V_SCROLL | SWT.H_SCROLL);
                return new DefaultInformationControl(shell, SWT.RESIZE, style, null);
            }
        };

        _informationPresenter = new InformationPresenter(informationControlCreator);
        _informationPresenter.setSizeConstraints(60, 10, true, true);
        _informationPresenter.install(getSourceViewer());

        // TODO install EditorSelectionChangedListener to update the Java outline page selection and this editor's range
        // indicator.

        // symbol inserter
        IPreferenceStore preferenceStore = SQLEditorPlugin.getDefault().getPreferenceStore();
        boolean closeSingleQuotes = preferenceStore.getBoolean(PreferenceConstants.SQLEDITOR_CLOSE_SINGLE_QUOTES);
        boolean closeDoubleQuotes = preferenceStore.getBoolean(PreferenceConstants.SQLEDITOR_CLOSE_DOUBLE_QUOTES);
        boolean closeBrackets = preferenceStore.getBoolean(PreferenceConstants.SQLEDITOR_CLOSE_BRACKETS);

        _symbolInserter.setCloseSingleQuotesEnabled(closeSingleQuotes);
        _symbolInserter.setCloseDoubleQuotesEnabled(closeDoubleQuotes);
        _symbolInserter.setCloseBracketsEnabled(closeBrackets);

        ISourceViewer sourceViewer = getSourceViewer();
        if (sourceViewer instanceof ITextViewerExtension)
            ((ITextViewerExtension) sourceViewer).prependVerifyKeyListener(_symbolInserter);

        /* Set a help context ID to enable F1 help. */
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, HELP_CONTEXT_ID);
    }

    public void updatePartControl(IEditorInput input)
    {
        super.updatePartControl(input);
        refreshActionStatus();
    }

    private void hookContextMenu()
    {
        StyledText styledText = getSourceViewer().getTextWidget();
        MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
        menuMgr.setRemoveAllWhenShown(true);

        menuMgr.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager manager)
            {
                // editorContextMenuAboutToShow(manager);// Display what comes by default
                SQLEditor.this.fillContextMenu(manager);
            }
        });

        Menu menu = menuMgr.createContextMenu(styledText);
        styledText.setMenu(menu);
        getSite().registerContextMenu(EditorConstants.EDITOR_ID, menuMgr, getSelectionProvider());
    }

    /**
     * Creates, configures, and returns a <code>ProjectionSupport</code> object for this editor.
     * 
     * @return the <code>ProjectSupport</code> object to use with this editor
     */
    protected ProjectionSupport createProjectionSupport()
    {
        ProjectionViewer viewer = (ProjectionViewer) getSourceViewer();
        ProjectionSupport projSupport = new ProjectionSupport(viewer, getAnnotationAccess(), getSharedColors());
        projSupport.addSummarizableAnnotationType("org.eclipse.ui.workbench.texteditor.error"); //$NON-NLS-1$
        projSupport.addSummarizableAnnotationType("org.eclipse.ui.workbench.texteditor.warning"); //$NON-NLS-1$
        projSupport.install();

        return projSupport;
    }

    /**
     * Creates the source viewer to be used by this editor.
     * 
     * @see org.eclipse.ui.texteditor.AbstractTextEditor#createSourceViewer(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.jface.text.source.IVerticalRuler, int)
     */
    protected ISourceViewer createSourceViewer(Composite parent, IVerticalRuler ruler, int styles)
    {
        fAnnotationAccess = createAnnotationAccess();
        fOverviewRuler = createOverviewRuler(getSharedColors());

        ISourceViewer viewer = doCreateSourceViewer(parent, ruler, styles);
        // ensure decoration support has been created and configured.
        getSourceViewerDecorationSupport(viewer);
        // ensure decoration support has been created and configured.
        getSourceViewerDecorationSupport(viewer);

        // Instantiate and register the matching token painter to source viewer and its text widget.
        _painter = new MatchingPairPainter(viewer, _pairMatcher);
        viewer.getTextWidget().addPaintListener(_painter);
        _painter.setColor(new Color(viewer.getTextWidget().getDisplay(), 192, 192, 192));
        ((AdaptedSourceViewer) viewer).addPainter(_painter);

        return viewer;
    }

    protected AdaptedSourceViewer doCreateSourceViewer(Composite parent, IVerticalRuler ruler, int styles)
    {
        return new AdaptedSourceViewer(parent, ruler, getOverviewRuler(), isOverviewRulerVisible(), styles);
    }

    /**
     * Creates the source viewer configuation to be used by this editor.
     * 
     * @return the new source viewer configuration object
     */
    protected SQLSourceViewerConfiguration createSourceViewerConfiguration()
    {
        SQLSourceViewerConfiguration config = SQLEditorContributorExtensionRegistry.getInstance()
                .getSQLSourceViewerConfiguration();
        if (config == null)
        {
            config = new SQLSourceViewerConfiguration(this);
        }
        else
        {
            config.setSQLEditor(this);
        }
        ISQLDBProposalsService proposalsService = getDBProposalsService();
        if (proposalsService != null)
        {
            config.setDBProposalsService(proposalsService);
        }

        return config;
    }

    /**
     * Dispose of resources held by this editor.
     * 
     * @see IWorkbenchPart#dispose()
     */
    public void dispose()
    {
        SQLOutlinePage outlinePage = getOutlinePage();
        if (outlinePage != null)
        {
            outlinePage.setInput(null);
        }

        // Remove the editor change listener.
        if (_fEditorSelectionChangedListener != null)
        {
            _fEditorSelectionChangedListener.uninstall(getSelectionProvider());
            _fEditorSelectionChangedListener = null;
        }

        // Remove and the matching token painter.
        if (_painter != null)
        {
            ((AdaptedSourceViewer) getSV()).removePainter(_painter);
            _painter.deactivate(true);
            _painter.dispose();
            _painter = null;
        }

        stopSQLUpdater();
        super.dispose();

        ISourceViewer sourceViewer = getSourceViewer();
        if (sourceViewer instanceof ITextViewerExtension)
            ((ITextViewerExtension) sourceViewer).removeVerifyKeyListener(_symbolInserter);

    }

    /**
     * Gets the multipage editor to which this sql editor belongs
     * 
     * @param editor
     */
    public IEditorPart getParentEditor()
    {
        return _parentEditor;
    }

    /**
     * Sets the multipage editor to which this sql editor belongs
     * 
     * @param editor
     */
    public void setParentEditor(IEditorPart editor)
    {
        _parentEditor = editor;
    }

    /**
     * Abandons all modifications applied to this text editor's input element's textual presentation since the last save
     * operation.
     * 
     * @see ITextEditor#doRevertToSaved()
     */
    public void doRevertToSaved()
    {
        super.doRevertToSaved();
        runUpdater();
    }

    /**
     * Saves the content of this editor.
     * 
     * @param progressMonitor the progress monitor for communicating result state or <code>null</code>
     * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void doSave(IProgressMonitor monitor)
    {
        super.doSave(monitor);
        runUpdater();
    }

    /**
     * Saves the contents of this editor to another object.
     * 
     * @see org.eclipse.ui.ISaveablePart#doSaveAs()
     */
    public void doSaveAs()
    {
        ISQLEditorConnectionInfo connInfo = getConnectionInfo();
        super.doSaveAs();
        // now the input should be IFileEditorInput if saveas succeeded
        setConnectionInfo(connInfo);
        setupDocumentPartitioner();
        runUpdater();
    }

    /**
     * Sets the input of the outline page after this class has set input.
     * 
     * @param input the new input for the editor
     * @see org.eclipse.ui.editors.text.TextEditor#doSetInput(org.eclipse.ui.IEditorInput)
     */
    public void doSetInput(IEditorInput input) throws CoreException
    {
    	//bz246180 :Lost line focus after saving
        saveCursorPosition();
        
        if (_fSQLUpdater != null)
        {
            _fSQLUpdater.removeMarkers();
            getSourceViewer().getDocument().removeDocumentListener(_fSQLUpdater);
        }

        IDocumentProvider docProvider = this.getDocumentProvider();
        // 209486: Changes in SQL Editor are not saved
        if (input instanceof FileEditorInput && (docProvider == null || !(docProvider instanceof FileDocumentProvider)))
        {
            docProvider = new FileDocumentProvider();
            setDocumentProvider(docProvider);
        }

        super.doSetInput(input);

        /*
         * Make sure the document partitioner is set up. The document setup participant sets up document partitioning,
         * which is used for text colorizing and other text features.
         */
        if (docProvider != null)
        {
            IDocument doc = docProvider.getDocument(input);
            if (doc != null)
            {
                SQLEditorDocumentSetupParticipant docSetupParticipant = getDocumentSetupParticipant();
                docSetupParticipant.setup(doc);
            }
        }

        if (input instanceof ISQLEditorInput)
        {
            ISQLEditorInput sqlEditorInput = (ISQLEditorInput) input;
            SQLDevToolsConfiguration toolsConfig = SQLToolsFacade
                    .getConfigurationByVendorIdentifier(getConnectionInfo().getDatabaseVendorDefinitionId());
            SQLEditorService editorService = toolsConfig.getSQLEditorService();
            fDBProposalsService = editorService.getSQLDBProposalsService(getConnectionInfo());
            if (fDBProposalsService == null)
            {
                fDBProposalsService = new SQLDBProposalsService(sqlEditorInput.getConnectionInfo());
            }
            SourceViewerConfiguration config = getSourceViewerConfiguration();
            if (config != null && config instanceof SQLSourceViewerConfiguration)
            {
                SQLSourceViewerConfiguration sqlConfig = (SQLSourceViewerConfiguration) config;
                sqlConfig.setDBProposalsService(fDBProposalsService);
            }
            setupDocumentPartitioner();
            IDocument document = getDocumentProvider().getDocument(input);
            if (_fSQLUpdater != null)
            {
                document.addDocumentListener(_fSQLUpdater);
                _fSQLUpdater.run();
            }
        }

        /*
         * Show the connection status in the status area at the bottom of the workbench window.
         */
        refreshConnectionStatus();
        
        //bz246180 :Lost line focus after saving
        tryKeepCursor();
    }
    
    /**
     * Save cursor line info.
     * BZ246180 :SOE:Lost line focus after saving
     * 
     * @author sul
     */
    private void saveCursorPosition()
    {
        Point preOffset = null;
        _previousCursorLine = -1;
        if (getSourceViewer() != null)
        {
            IDocument doc = getSourceViewer().getDocument();
            try 
            {
                preOffset = getSourceViewer().getSelectedRange();
                _previousCursorLine = doc.getLineOfOffset(preOffset.x);
            } 
            catch(BadLocationException bad)
            {
                preOffset = null;
                _previousCursorLine = -1;
            }
        }
    }

    /**
     * Try to restore the cursor to end of the preCursonLine.
     * BZ246180 :SOE:Lost line focus after saving
     * 
     * @author sul
     */
    private void tryKeepCursor()
    {
        if (_previousCursorLine >= 0)
        {
            ISourceViewer viewer = getSourceViewer();
            IDocument newDoc = viewer.getDocument();

            try
            {
                int newTotalLine = newDoc.getLineOfOffset(newDoc.getLength() - 1);

                if(_previousCursorLine >= newTotalLine)
                {
                    //locate cursor to the end of document
                    viewer.setRangeIndication(newDoc.getLength(), 0, true);
                }
                else
                {
                    //just locate cursor to the end of preCursorLine.
                    IRegion newLine = newDoc.getLineInformation(_previousCursorLine);
                    viewer.setRangeIndication(newLine.getOffset() + newLine.getLength(), 0, true);
                }
            }
            catch (BadLocationException bad)
            {
                //if exception is thrown out, set the cursor to the start
                viewer.setRangeIndication(0, 0, true);
            }
            
        }
    }

    /**
     * Sets up this editor's context menu before it is made visible.
     * 
     * @see org.eclipse.ui.texteditor.AbstractTextEditor#editorContextMenuAboutToShow(org.eclipse.jface.action.IMenuManager)
     */
    protected void editorContextMenuAboutToShow(IMenuManager menu)
    {
        super.editorContextMenuAboutToShow(menu);
        fillContextMenu(menu);
    }

    /**
     * Fills the context menu with sql editor specific actions. This is different with editorContextMenuAboutToShow in
     * that it does not show the inherited actions.
     * 
     * @param menu
     */
    protected void fillContextMenu(IMenuManager menu)
    {
        menu.add(new Separator(ITextEditorActionConstants.GROUP_UNDO));
        menu.add(new Separator(ISQLEditorActionConstants.GROUP_OPEN));
        menu.add(new Separator(ITextEditorActionConstants.GROUP_COPY));
        menu.add(new Separator(ISQLEditorActionConstants.GROUP_SQLEDITOR_SOURCE));
        menu.add(new Separator(ISQLEditorActionConstants.GROUP_SQLEDITOR_EXECUTE));
        menu.add(new Separator(ISQLEditorActionConstants.GROUP_SQLEDITOR_WIZARD));
        menu.add(new Separator(ISQLEditorActionConstants.GROUP_SQLEDITOR_SAVE));
        menu.add(new Separator(ISQLEditorActionConstants.GROUP_SQLEDITOR_ADDITION));
        menu.add(new Separator(ITextEditorActionConstants.MB_ADDITIONS));

        if (!isEditorInputReadOnly())
        {
            addAction(menu, ITextEditorActionConstants.GROUP_UNDO, ITextEditorActionConstants.UNDO);
            if (getEditorInput() instanceof IFileEditorInput || getEditorInput() instanceof ILocationProvider)
            {
                addAction(menu, ITextEditorActionConstants.GROUP_UNDO, ITextEditorActionConstants.REVERT_TO_SAVED);
            }
            addAction(menu, ITextEditorActionConstants.GROUP_COPY, ITextEditorActionConstants.CUT);
            addAction(menu, ITextEditorActionConstants.GROUP_COPY, ITextEditorActionConstants.COPY);
            addAction(menu, ITextEditorActionConstants.GROUP_COPY, ITextEditorActionConstants.PASTE);
        }
        else
        {
            addAction(menu, ITextEditorActionConstants.GROUP_COPY, ITextEditorActionConstants.COPY);
        }
        menu.appendToGroup(ISQLEditorActionConstants.GROUP_SQLEDITOR_SOURCE,
                getAction(ISQLEditorActionConstants.TOGGLE_COMMENT));
        // menu.appendToGroup(ISQLEditorActionConstants.GROUP_SQLEDITOR_SOURCE,
        // getAction(ISQLEditorActionConstants.GOTO_MATCHING_TOKEN_ACTION_ID));

        // menu.add( new Separator() );
        // addAction( menu, "ContentAssistProposal" ); //$NON-NLS-1$
        // addAction( menu, "ContentAssistTip" ); //$NON-NLS-1$
        // addAction( menu, "ContentFormat" ); //$NON-NLS-1$

        menu.add(new Separator());

        // 2006-7-20
        SQLDevToolsUIConfiguration config = SQLToolsUIFacade.getConfigurationByVendorIdentifier(getConnectionInfo()
                .getDatabaseVendorDefinitionId());
        ActionService actionService = config.getActionService();
        SQLEditorUIService editorService = config.getSQLEditorUIService();
        // Execute SQL
        if (actionService.supportsAction(ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID))
        {
            addAction(menu, ISQLEditorActionConstants.GROUP_SQLEDITOR_EXECUTE,
                    ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID);
        }
        if (actionService.supportsAction(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID))
        {
            addAction(menu, ISQLEditorActionConstants.GROUP_SQLEDITOR_EXECUTE,
                    ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID);
        }
        if (actionService.supportsAction(ISQLEditorActionConstants.EXECUTE_SQL_AS_ONE_STATEMENT_ACTION_ID))
        {
            addAction(menu, ISQLEditorActionConstants.GROUP_SQLEDITOR_EXECUTE, 
                    ISQLEditorActionConstants.EXECUTE_SQL_AS_ONE_STATEMENT_ACTION_ID);
        }
        if (actionService.supportsAction(ISQLEditorActionConstants.EXECUTE_CURRENT_SQL_ACTION_ID))
        {
            addAction(menu, ISQLEditorActionConstants.GROUP_SQLEDITOR_EXECUTE,
                    ISQLEditorActionConstants.EXECUTE_CURRENT_SQL_ACTION_ID);
        }

        // Explain SQL
        IPlanOption planOption = config.getPlanService().getPlanOption();
        if (planOption != null && (actionService.supportsAction(ISQLEditorActionConstants.EXPLAIN_SQL_ACTION_ID)))
        {
            addAction(menu, ISQLEditorActionConstants.GROUP_SQLEDITOR_EXECUTE,
                    ISQLEditorActionConstants.EXPLAIN_SQL_ACTION_ID);
        }

        addAction(menu, ISQLEditorActionConstants.GROUP_SQLEDITOR_SAVE,
                ISQLEditorActionConstants.SAVE_AS_TEMPLATE_ACTION_ID);

        // Edit SQL in DMLdialog
        if (actionService.supportsAction(ISQLEditorActionConstants.DMLDIALOG_SELECTION_SQL_ACTION_ID))
        {
            addAction(menu, ISQLEditorActionConstants.GROUP_SQLEDITOR_ADDITION,
                    ISQLEditorActionConstants.DMLDIALOG_SELECTION_SQL_ACTION_ID);
        }

        Collection extensions = SQLEditorPlugin.getSQLEditorActionContributorExtension();
        if (extensions != null)
        {
            for (Iterator iter = extensions.iterator(); iter.hasNext();)
            {
                ISQLEditorActionContributorExtension ext = (ISQLEditorActionContributorExtension) iter.next();
                ext.contributeToContextMenu(menu);
            }
        }

        // database specific actions go here
        HashMap dbActions = editorService.getAdditionalActions();
        if (dbActions != null && !dbActions.isEmpty())
        {
            MenuManager dbSubMenuMgr = new MenuManager(config.getDatabaseVendorDefinitionId().toString(),
                    ISQLEditorActionConstants.GROUP_SQLEDITOR_DB_SUBMENU); //$NON-NLS-1$
            IActionBars bars = ((IEditorSite) getSite()).getActionBars();
            for (Iterator iter = dbActions.keySet().iterator(); iter.hasNext();)
            {
                String key = (String) iter.next();
                Object value = dbActions.get(key);
                if (value instanceof Collection)
                {
                    for (Iterator iterator = ((Collection) value).iterator(); iterator.hasNext();)
                    {
                        addContributedMenus(key, iterator.next(), menu, dbSubMenuMgr);
                    }
                }
                else
                {
                    addContributedMenus(key, value, menu, dbSubMenuMgr);
                }
            }
            dbSubMenuMgr.add(new Separator(ITextEditorActionConstants.MB_ADDITIONS));
            menu.appendToGroup(ISQLEditorActionConstants.GROUP_SQLEDITOR_ADDITION, dbSubMenuMgr);
        }

        IAction preferencesAction = getAction(ITextEditorActionConstants.CONTEXT_PREFERENCES);
        menu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, new Separator(
                ITextEditorActionConstants.GROUP_SETTINGS));
        menu.appendToGroup(ITextEditorActionConstants.GROUP_SETTINGS, preferencesAction);

    }

    private void addContributedMenus(String key, Object dba, IMenuManager manager, MenuManager dbSubMenuMgr)
    {
        if (dba instanceof IEditorActionDelegate)
        {
            ((IEditorActionDelegate) dba).setActiveEditor(null, this);
        }
        else if (dba instanceof TextEditorAction)
        {
            ((TextEditorAction) dba).setEditor(this);
        }
        // only one level for now
        if (dba instanceof IMenuManager)
        {
            IContributionItem[] items = ((IMenuManager) dba).getItems();
            for (int i = 0; i < items.length; i++)
            {
                if (items[i] instanceof IEditorActionDelegate)
                {
                    ((IEditorActionDelegate) items[i]).setActiveEditor(null, this);
                }
                else if (items[i] instanceof TextEditorAction)
                {
                    ((TextEditorAction) items[i]).setEditor(this);
                }
            }
        }
        if (key != null && !key.equals("")) //$NON-NLS-1$
        {
            String subMgrId = key;
            String subGroupId = key;
            if (key.indexOf("/") > 0) //$NON-NLS-1$
            {
                subMgrId = key.substring(0, key.indexOf("/")); //$NON-NLS-1$
                subGroupId = key.substring(key.indexOf("/") + 1); //$NON-NLS-1$
            }
            if (manager.find(subMgrId) != null)
            {
                IMenuManager subMgr = manager;
                if (manager.find(subMgrId) instanceof IMenuManager)
                {
                    subMgr = (IMenuManager) manager.find(subMgrId);
                }
                if (subMgr.find(subGroupId) != null)
                {
                    if (dba instanceof IAction)
                    {
                        subMgr.appendToGroup(subGroupId, (IAction) dba);
                    }
                    else if (dba instanceof IContributionItem)
                    {
                        subMgr.appendToGroup(subGroupId, (IContributionItem) dba);
                    }
                }
            }
            // otherwise ignore
        }
        else
        {
            if (dba instanceof IAction)
            {
                dbSubMenuMgr.add((IAction) dba);
            }
            else if (dba instanceof IContributionItem)
            {
                dbSubMenuMgr.add((IContributionItem) dba);
            }
        }
    }

    /**
     * Gets an adapter for the given class. Returns the SQL content outline page if the get request is for an outline
     * page. Otherwise returns a projection adapter if one hasn't already been created.
     * 
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     * @see org.eclipse.jface.text.source.projection.ProjectionSupport#getAdapter(org.eclipse.jface.text.source.ISourceViewer,
     *      java.lang.Class)
     */
    public Object getAdapter(Class classForWhichAdapterNeeded)
    {
        Object adapter = null;

        /* Get and return the content outline page, if that's what's requested. */
        if (IContentOutlinePage.class.equals(classForWhichAdapterNeeded))
        {
            if (_fOutlinePage == null)
            {
                _fOutlinePage = new SQLOutlinePage(this);
                _fOutlineSelectionChangedListener.install(_fOutlinePage);
                installSQLUpdater();
                _fSQLUpdater.setOutlinePage(_fOutlinePage);
            }
            adapter = _fOutlinePage;
        }
        /*
         * This allows consumers to get an instance of SQLEditor via the active editor in a consistent way.
         */
        else if (SQLEditor.class.equals(classForWhichAdapterNeeded))
        {
            return this;
        }
        /*
         * Delegate getting the adapter to the projection support object, if there is one. Projection refers to the
         * ability to visibly collapse and expand sections of the document.
         */
        else if (adapter == null)
        {
            ProjectionSupport projSupport = getProjectionSupport();
            if (projSupport != null)
            {
                adapter = projSupport.getAdapter(getSourceViewer(), classForWhichAdapterNeeded);
            }
        }

        /* If we still don't have an adapter let the superclass handle it. */
        if (adapter == null)
        {
            adapter = super.getAdapter(classForWhichAdapterNeeded);
        }

        return adapter;
    }

    /**
     * Gets the connection info object of the editor input of this editor.
     * 
     * @return the current connection info object if the editor has one, otherwise null
     */
    public ISQLEditorConnectionInfo getConnectionInfo()
    {
        if (getEditorInput() instanceof ISQLEditorInput)
        {
            return ((ISQLEditorInput) getEditorInput()).getConnectionInfo();
        }
        return SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO;
    }

    /**
     * Sets the connection info object of the editor input of this editor. Also updates connection related features such
     * as actions, status line, syntax highlighting, and syntax validation, etc.
     * 
     * @param connInfo the new connection info object
     */
    public void setConnectionInfo(ISQLEditorConnectionInfo connInfo)
    {
        if (getEditorInput() instanceof ISQLEditorInput)
        {
            ISQLEditorConnectionInfo oldConnInfo = getConnectionInfo();
            ((ISQLEditorInput) getEditorInput()).setConnectionInfo(connInfo);
            fDBProposalsService.setSQLEditorConnectionInfo(connInfo);

            if (connInfo != null
                    && !connInfo.getDatabaseVendorDefinitionId().equals(oldConnInfo.getDatabaseVendorDefinitionId()))
            {
                // when the database definition is different, reset the SourceViewer for correct syntax highlight
                ((ISourceViewerExtension2) getSourceViewer()).unconfigure();
                SQLSourceViewerConfiguration configuration = createSourceViewerConfiguration();
                setSourceViewerConfiguration(configuration);
                getSourceViewer().configure(configuration);
                // we have to do this since only configuring the source viewer doesn't work. PresentationReconciler
                // won't get notified of the document changed event.
                setInput(getEditorInput());
                // do it twice because setInput might regenerate an editor input
                ((ISQLEditorInput) getEditorInput()).setConnectionInfo(connInfo);
            }

            SQLEditorPlugin.getDisplay().asyncExec(new Runnable()
            {
                public void run()
                {
                    refreshActionStatus();
                    refreshConnectionStatus();
                    _fSQLUpdater.run();
                    fireConnectionProfileAttached();
                }
            });

        }
    }

    /**
     * Requests a connection. Does not guarantee the connection is established. The default implementation does nothing.
     * 
     */
    public void requestConnection()
    {
        // Do nothing by default
    }

    /**
     * Gets the <code>Database</code> object associated with this input.
     * 
     * @return the <code>Database</code> object associated with this input
     */
    public Database getDatabase()
    {
        if (getEditorInput() instanceof ISQLEditorInput)
        {
            return ((ISQLEditorInput) getEditorInput()).getConnectionInfo().getDatabase();
        }
        return null;
    }

    /**
     * Gets the <code>DBProposalsService</code> object that provides content assist services for this editor.
     * 
     * @return the current <code>DBProposalsService</code> object
     */
    public ISQLDBProposalsService getDBProposalsService()
    {
        return fDBProposalsService;
    }

    /**
     * Gets the default schema name to use with the connection or database associated with this input.
     * 
     * @return the default schema name to use with this input, or null if none
     */
    public String getDefaultSchemaName()
    {
        if (getEditorInput() instanceof ISQLEditorInput)
        {
            return ((ISQLEditorInput) getEditorInput()).getConnectionInfo().getDefaultSchemaName();
        }
        return null;
    }

    /**
     * Gets the document setup participant object associated with this editor. The setup participant sets the
     * partitioning type for the document.
     * 
     * @return the current document setup participant
     */
    public SQLEditorDocumentSetupParticipant getDocumentSetupParticipant()
    {
        if (fDocSetupParticipant == null)
        {
            fDocSetupParticipant = new SQLEditorDocumentSetupParticipant();
        }
        return fDocSetupParticipant;
    }

    /**
     * Gets the outline page associated with this editor, if there is one.
     * 
     * @return the outline page associated with this editor
     */
    protected SQLOutlinePage getOutlinePage()
    {
        return _fOutlinePage;
    }

    /**
     * Gets the <code>ProjectionSupport</code> object associated with this editor.
     * 
     * @return the current <code>ProjectionSupport</code> object
     */
    protected ProjectionSupport getProjectionSupport()
    {
        return fProjectionSupport;
    }

    /**
     * Gets the resource bundle associated with this editor.
     * 
     * @return the resource bundle associated with this editor.
     */
    public ResourceBundle getResourceBundle()
    {
        return SQLEditorResources.getResourceBundle();
    }

    /**
     * Gets the color provider for colorizing SQL source code.
     * 
     * @return the SQL color provider
     */
    public SQLColorProvider getSQLColorProvider()
    {
        return SQLEditorPlugin.getDefault().getSQLColorProvider();
    }

    /**
     * Initializes the editor.
     * 
     * @see org.eclipse.ui.editors.text.TextEditor#initializeEditor()
     */
    protected void initializeEditor()
    {
        super.initializeEditor();
        setDocumentProvider(new SQLStorageDocumentProvider());
        setSourceViewerConfiguration(createSourceViewerConfiguration());
        setRulerContextMenuId("#SQLEditorRulerContext"); //$NON-NLS-1$
        setRangeIndicator(new DefaultRangeIndicator());
        // Sets the SQL editor's help context id.
        // 212003 caution: parameter can't be null
        setHelpContextId(IHelpContextIds.SQLEDITOR);

    }

    /**
     * Handles notifications to the object that a property has changed.
     * 
     * @param event the property change event object describing which property changed and how
     */
    public void propertyChange(PropertyChangeEvent event)
    {
        if (event.getProperty().equals(SQLConnectAction.CONNECTION))
        {
            // TODO refresh db proposal service
            refreshConnectionStatus();
        }
    }

    protected void performSaveAs(IProgressMonitor progressMonitor)
    {
        // Using the SaveAsDialog defined in common.ui which provide "Create Project" button
        Shell shell = getSite().getShell();
        final IEditorInput input = getEditorInput();

        IDocumentProvider provider = getDocumentProvider();
        final IEditorInput newInput;

        {
            SaveAsDialog dialog = new SaveAsDialog(shell, false);

            IFile original = (input instanceof IFileEditorInput) ? ((IFileEditorInput) input).getFile() : null;
            if (original != null)
            {
                dialog.setOriginalFile(original);
            }

            dialog.create();

            if (provider.isDeleted(input) && original != null)
            {
                String message = NLS.bind(SQLEditorResources.SQLEditor_file_deleted_or_not_accessible, original
                        .getName());
                dialog.setErrorMessage(null);
                dialog.setMessage(message, IMessageProvider.WARNING);
            }

            if (dialog.open() == Window.CANCEL)
            {
                if (progressMonitor != null)
                {
                    progressMonitor.setCanceled(true);
                }
                return;
            }

            IPath filePath = dialog.getResult();
            if (filePath == null)
            {
                if (progressMonitor != null)
                {
                    progressMonitor.setCanceled(true);
                }
                return;
            }

            IWorkspace workspace = ResourcesPlugin.getWorkspace();
            IFile file = workspace.getRoot().getFile(filePath);
            newInput = new FileEditorInput(file);
        }

        if (provider == null)
        {
            // editor has programmatically been closed while the dialog was open
            return;
        }

        boolean success = false;
        try
        {

            provider.aboutToChange(newInput);
            provider.saveDocument(progressMonitor, newInput, provider.getDocument(input), true);
            success = true;

        }
        catch (CoreException x)
        {
            final IStatus status = x.getStatus();
            if (status == null || status.getSeverity() != IStatus.CANCEL)
            {
                String title = SQLEditorResources.SQLEditor_problem_save_as;
                String msg = NLS.bind(SQLEditorResources.SQLEditor_could_not_save_as, x.getMessage());
                MessageDialog.openError(shell, title, msg);
            }
        }
        finally
        {
            provider.changed(newInput);
            if (success)
            {
                setInput(newInput);
            }
        }

        if (progressMonitor != null)
        {
            progressMonitor.setCanceled(!success);
        }

    }

    /**
     * Sets the document setup participant object associated with this editor to the given object. The setup participant
     * sets the partitioning type for the document.
     * 
     * @return the current document setup participant
     */
    public void setDocumentSetupParticipant(SQLEditorDocumentSetupParticipant docSetupParticipant)
    {
        fDocSetupParticipant = docSetupParticipant;
    }

    /**
     * Asks this part to take focus within the workbench.
     * 
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    public void setFocus()
    {
        super.setFocus();

        /*
         * Make sure the connection status message is up to date. It doesn't automatically get updated if the user
         * switches editor instances.
         */
        refreshConnectionStatus();
    }

    /**
     * Sets the <code>ProjectionSupport</code> object associated with this editor.
     * 
     * @param projSupport the <code>ProjectionSupport</code> object to use
     */
    protected void setProjectionSupport(ProjectionSupport projSupport)
    {
        fProjectionSupport = projSupport;
    }

    /**
     * Forces the updater to run to update the outline page (if there is one) and syntax markers to reflect the latest
     * changes in the editor.
     */
    protected void runUpdater()
    {
        if (_fSQLUpdater != null)
        {
            _fSQLUpdater.run();
        }
    }

    /**
     * Prompts the user to select an existing connection or create a new one. Returns a
     * <code>ISQLEditorConnectionInfo</code> object for the chosen connection.
     * 
     * @return the <code>ISQLEditorConnectionInfo</code> object for the selected connection or null if none selected
     */
    public ISQLEditorConnectionInfo requestConnectionFromUser()
    {
        // TODO: call "SQL connection" dialog
        return null;
    }

    /**
     * Makes the source viewer public.
     * 
     * @return The source viewer used to create this editor
     */
    public ISourceViewer getSV()
    {
        return getSourceViewer();
    }

    // ///////////////////Outline and Updater//////////////////////////////////////
    /**
     * Informs the editor that its outliner has been closed.
     */
    public void outlinePageClosed()
    {
        if (_fOutlinePage != null)
        {
            _fOutlineSelectionChangedListener.uninstall(_fOutlinePage);
            _fOutlinePage = null;
        }
    }

    private void stopSQLUpdater()
    {
        try
        {
            _fSQLUpdater.removeMarkers();
            _fSQLUpdater.setOutlinePage(null);
            if (getSourceViewer().getDocument() != null)
            {
                getSourceViewer().getDocument().removeDocumentListener(_fSQLUpdater);
            }
            IPreferenceStore preferenceStore = SQLEditorPlugin.getDefault().getPreferenceStore();
            if (preferenceStore != null)
            {
                preferenceStore.removePropertyChangeListener(_fSQLUpdater);
            }
            getSite().getShell().getDisplay().timerExec(-1, _fSQLUpdater);
        }
        catch (RuntimeException e)
        {
            // do nothing
        }
    }

    private void installSQLUpdater()
    {

        try
        {
            if (_fSQLUpdater == null)
            {
                _fSQLUpdater = new SQLUpdater(this);

                getSourceViewer().getDocument().addDocumentListener(_fSQLUpdater);
                IPreferenceStore preferenceStore = SQLEditorPlugin.getDefault().getPreferenceStore();
                if (preferenceStore != null)
                {
                    preferenceStore.addPropertyChangeListener(_fSQLUpdater);
                }
                _fSQLUpdater.run();
            }
        }
        catch (Throwable e)
        {
            // Might caught LookaheadSuccess
            SQLEditorPlugin.getDefault().log(SQLEditorResources.SQLEditor_error_while_trying_to_install_sql_updater, e);
        }
    }

    /**
     * Selects the given node in the editor.
     * 
     * @param node the selected node which contains the text range information
     */
    protected void setSelection(SimpleNode node)
    {
        ISourceViewer viewer = getSourceViewer();
        if (viewer == null)
        {
            return;
        }
        StyledText widget = viewer.getTextWidget();
        widget.setRedraw(false);
        if (node != null)
        {
            int offset = node.getStartOffset(viewer.getDocument());
            int length = node.getEndOffset(viewer.getDocument()) - offset;

            try
            {
                setHighlightRange(offset, length, true);
            }
            catch (Exception e)
            {
                // this can occur as result of a parse error
            }
            viewer.revealRange(offset, length);
            viewer.setSelectedRange(offset, length);
        }
        else
        {
            resetHighlightRange();
            viewer.setSelectedRange(widget.getCaretOffset(), 0);
        }
        widget.setRedraw(true);
    }

    /**
     * Returns the parsing result no matter the "Enable syntax validation" is on or off. If this option is on, we will
     * always keep the parsing result up-to-date, if it is off, we will check if the parsing result is kept sync with
     * the editor content, if not, we will parse it to get the synchronized parsing result
     * 
     * @return the recent parsing result since the last modification, may only be null when parser can't be found
     */
    public ParsingResult getParsingResult()
    {
        /*
         * If the "Enable syntax validation" is turned off and current editor is needed to be parsed again to keep the
         * parsing result in sync with the editor content, then we will parse it
         */
        if (_fSQLUpdater.needToParse())
        {
            ISQLEditorConnectionInfo connInfo = getConnectionInfo();
            SQLDevToolsConfiguration conf = SQLToolsFacade.getConfiguration(getDatabaseIdentifier(), connInfo
                    .getDatabaseVendorDefinitionId());
            SQLParser parser = conf.getSQLService().getSQLParser();
            if (parser == null)
            {
                return null;
            }

            String content = getSourceViewer().getDocument().get();
            // Add '\n' to avoid parser to throw exception when last line is single line comment.
            content = content + "\n"; //$NON-NLS-1$

            boolean useDelimiter = getSQLType() == SQLParserConstants.TYPE_SQL_ROOT;
            _parsingResult = parser.parse(content, new ParserParameters(useDelimiter, getSQLType()));
            _parsingResult.getRootNode().setDocument(getSV().getDocument());
            _fSQLUpdater.setNeedToParse(false);
        }
        return _parsingResult;
    }

    /**
     * @param node the new parsing result
     */
    public void setParsingResult(ParsingResult result)
    {
        _parsingResult = result;
    }

    // /////////////////////Document Provider////////////////////////////////////////
    /**
     * Sets up the document partitioner. Since we support multiple dialects, each time when the database vendor
     * definition for this SQL editor is changed, this method should be called to update the document partitioner.
     */
    protected void setupDocumentPartitioner()
    {
        IDocument document = getDocumentProvider().getDocument(getEditorInput());
        DatabaseVendorDefinitionId vendorId = getConnectionInfo().getDatabaseVendorDefinitionId();

        SQLDevToolsConfiguration factory = SQLToolsFacade.getConfigurationByVendorIdentifier(vendorId);
        SQLPartitionScanner _sqlPartitionSanner = new SQLPartitionScanner(factory.getSQLService().getSQLSyntax());
        if (document instanceof IDocumentExtension3)
        {
            IDocumentExtension3 extension3 = (IDocumentExtension3) document;

            IDocumentPartitioner partitioner = new FastPartitioner(_sqlPartitionSanner, new String[]
            {
                SQLPartitionScanner.SQL_COMMENT, SQLPartitionScanner.SQL_MULTILINE_COMMENT,
                SQLPartitionScanner.SQL_STRING, SQLPartitionScanner.SQL_DOUBLE_QUOTES_IDENTIFIER
            });
            partitioner.connect(document);
            extension3.setDocumentPartitioner(SQLPartitionScanner.SQL_PARTITIONING, partitioner);

        }
    }

    // /////////////////////Action and Status line status/////////////////////////////////////
    /**
     * Refreshes the actions
     */
    protected void refreshActionStatus()
    {
        List actionList = new ArrayList();
        IAction action = getAction(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID);
        if (action != null)
        {
            actionList.add(action);
        }
        action = getAction(ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID);
        if (action != null)
        {
            actionList.add(action);
        }
        action = getAction(ISQLEditorActionConstants.EXECUTE_SQL_AS_ONE_STATEMENT_ACTION_ID);
        if (action != null)
        {
            actionList.add(action);
        }
        action = getAction(ISQLEditorActionConstants.EXECUTE_CURRENT_SQL_ACTION_ID);
        if (action != null)
        {
            actionList.add(action);
        }
        // TODO notify ISQLEditorActionContributorExtension
        Collection extensions = SQLEditorPlugin.getSQLEditorActionContributorExtension();
        if (extensions != null)
        {
            for (Iterator iter = extensions.iterator(); iter.hasNext();)
            {
                ISQLEditorActionContributorExtension ext = (ISQLEditorActionContributorExtension) iter.next();
                ext.updateAction();
            }
        }

        Iterator iterator = actionList.iterator();
        while (iterator.hasNext())
        {
            IUpdate textEditorAction = (IUpdate) iterator.next();
            if (textEditorAction != null)
            {
                textEditorAction.update();
            }
        }

    }

    /**
     * Refreshes the status area indicating the connection state.
     */
    public void refreshConnectionStatus()
    {
        IEditorSite editorSite = getEditorSite();
        if (editorSite != null)
        {
            IActionBars actionBars = editorSite.getActionBars();
            if (actionBars != null)
            {
                IStatusLineManager statusLineMgr = actionBars.getStatusLineManager();
                ISQLEditorConnectionInfo connInfo = getConnectionInfo();
                if (connInfo != null)
                {
                    statusLineMgr.setErrorMessage(null);
                    String connStatus = connInfo.getName();
                    statusLineMgr.setMessage(connStatus);
                }
                else
                {
                    String connStatus = SQLEditorResources.SQLEditor_connection_status_noConnection;
                    statusLineMgr.setErrorMessage(connStatus);
                }
                actionBars.updateActionBars();
            }
        }
    }

    // ////////////////////Utility methods///////////////////////////////////
    /**
     * Returns the complete text in this editor.
     * 
     * @return the complete text.
     */
    public String getText()
    {
        IDocument document = getDocumentProvider().getDocument(getEditorInput());
        return document.get();
    }

    /**
     * Returns the selected text.
     */
    public String getSelectedText()
    {
        String sql = null;
        if (getSelectionProvider() == null)
        {
            return null;
        }
        // get the selection
        ITextSelection selection = (ITextSelection) getSelectionProvider().getSelection();
        if (!selection.isEmpty() && selection.getText() != null && !selection.getText().equals("")) //$NON-NLS-1$
        {
            sql = selection.getText();
        }
        return sql;
    }

    /**
     * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#initializeKeyBindingScopes()
     */
    protected void initializeKeyBindingScopes()
    {
        setKeyBindingScopes(new String[]
        {
            "org.eclipse.datatools.sqltools.SQLEditorScope" //$NON-NLS-1$
        }); //$NON-NLS-1$
    }

    /*
     * @see org.eclipse.ui.part.WorkbenchPart#getOrientation()
     * @since 3.1
     */
    public int getOrientation()
    {
        return SWT.LEFT_TO_RIGHT; // SQL editors are always left to right by default
    }

    /**
     * 
     * @return the selected text or the whole text when there's no selection
     */
    public String getTargetText()
    {
        String sql = getSelectedText();
        if (sql == null)
        {
            // get the document
            IDocument document = getDocumentProvider().getDocument(getEditorInput());
            sql = document.get();
        }
        return sql;
    }

    /**
     * Configures the toggle comment action
     * 
     */
    private void configureToggleCommentAction(IAction action)
    {
        if (action instanceof ToggleCommentAction)
        {
            ISourceViewer sourceViewer = getSourceViewer();
            SourceViewerConfiguration configuration = getSourceViewerConfiguration();
            ((ToggleCommentAction) action).configure(sourceViewer, configuration);
        }
    }

    //
    protected void handlePreferenceStoreChanged(PropertyChangeEvent event)
    {
        try
        {
            if (getSourceViewer() != null)
            {

                String p = event.getProperty();
                IPreferenceStore preferenceStore = SQLEditorPlugin.getDefault().getPreferenceStore();
                if (PreferenceConstants.SQLEDITOR_CLOSE_SINGLE_QUOTES.equals(p))
                {
                    _symbolInserter.setCloseSingleQuotesEnabled(preferenceStore.getBoolean(p));
                    return;
                }
                if (PreferenceConstants.SQLEDITOR_CLOSE_DOUBLE_QUOTES.equals(p))
                {
                    _symbolInserter.setCloseDoubleQuotesEnabled(preferenceStore.getBoolean(p));
                    return;
                }
                if (PreferenceConstants.SQLEDITOR_CLOSE_BRACKETS.equals(p))
                {
                    _symbolInserter.setCloseBracketsEnabled(preferenceStore.getBoolean(p));
                    return;
                }
                // when user enbale "syntax validation", we update/recreate the parsing result for SQLEditor
                if (PreferenceConstants.SYNTAX_VALIDATION.equals(p))
                {
                    if (_fSQLUpdater != null)
                    {
                        _fSQLUpdater.run();
                    }
                    return;
                }
            }

        }
        finally
        {
            super.handlePreferenceStoreChanged(event);
        }
    }

    /**
     * Returns the outmost sql statement type that's allowed in this editor. The default sqlType is
     * SQLParserConstants.TYPE_SQL_ROOT, which means any sql statements can occur.
     * 
     * @return
     */
    public int getSQLType()
    {
        return SQLParserConstants.TYPE_SQL_ROOT;
    }

    public void addConnectionProfileAttachListener(IConnectionProfileAttachListener listener)
    {
        if (!_profileListeners.contains(listener))
        {
            _profileListeners.add(listener);
        }
    }

    public void removeConnectionProfileAttachListener(IConnectionProfileAttachListener listener)
    {
        _profileListeners.remove(listener);
    }

    public void fireConnectionProfileAttached()
    {
        for (Iterator iter = _profileListeners.iterator(); iter.hasNext();)
        {
            IConnectionProfileAttachListener l = (IConnectionProfileAttachListener) iter.next();
            l.connectionProfileAttached(this);
        }
    }

    /**
     * Returns the DatabaseIdentifier associated with the connection info.
     * 
     * @return might be null
     */
    public DatabaseIdentifier getDatabaseIdentifier()
    {
        ISQLEditorConnectionInfo connInfo = getConnectionInfo();
        if (connInfo != null && connInfo.getConnectionProfileName() != null)
        {
            return new DatabaseIdentifier(connInfo.getConnectionProfileName(), connInfo.getDatabaseName());
        }
        return null;
    }

    public String getDBType()
    {
        return getConnectionInfo().getDatabaseVendorDefinitionId().toString();
    }

    /**
     * Insert the SQL text into editor
     * 
     * @param sqlText
     */
    public void insert(String sqlText)
    {
        // get the document
        IDocument document = getDocumentProvider().getDocument(getEditorInput());

        // get the selection
        ITextSelection selection = (ITextSelection) getSelectionProvider().getSelection();
        int start = 0;
        int length = 0;

        // get the offset of the selection
        if (!selection.isEmpty())
        {
            start = selection.getOffset();
            length = selection.getLength();
            if (length < 0)
            {
                length = -length;
                start -= length;
            }
        }

        // replace/insert
        try
        {
            document.replace(start, length, sqlText);
        }
        catch (BadLocationException e)
        {
            IEditorSite editorSite = getEditorSite();
            if (editorSite != null)
            {
                IActionBars actionBars = editorSite.getActionBars();
                if (actionBars != null)
                {
                    IStatusLineManager statusLineMgr = actionBars.getStatusLineManager();
                    statusLineMgr.setErrorMessage(e.getLocalizedMessage());
                }
            }
            SQLEditorPlugin.getDefault().log(e);
        }
    }

    /**
     * Utility methods to "getConnectionInfo().isConnected()"
     * 
     * @return
     */
    public boolean isConnected()
    {
        return getConnectionInfo().isConnected();
    }

    protected String[] collectContextMenuPreferencePages()
    {
        String[] parent = super.collectContextMenuPreferencePages();
        String[] prefs = new String[parent.length + 1];
        System.arraycopy(parent, 0, prefs, 1, parent.length);
        prefs[0] = "org.eclipse.datatools.sqltools.sqleditor.preferences.SQLEditor";
        return prefs;
    }

    /**
     * Going to matching token.
     */
    public void gotoMatchingToken()
    {
        ISourceViewer sourceViewer = getSourceViewer();
        IDocument document = sourceViewer.getDocument();
        IRegion selection = getSignedSelection(sourceViewer);

        IRegion region = _pairMatcher.match(document, selection.getOffset());

        if (region == null)
        {
            return;
        }

        int offset = selection.getOffset();

        if (_pairMatcher.getAnchor() == ICharacterPairMatcher.RIGHT)
        {
            offset = region.getOffset() + ((AbstractPairMatcher) _pairMatcher).getMatchingTokenLength();
        }
        else if (_pairMatcher.getAnchor() == ICharacterPairMatcher.LEFT)
        {
            offset = region.getOffset() + region.getLength();
        }

        sourceViewer.setSelectedRange(offset, 0);
        sourceViewer.revealRange(offset, 0);
    }

    /**
     * @see org.eclipse.jdt.internal.ui.javaeditor.JavaEditor#getSignedSelection(ISourceViewer sourceViewer)
     */
    protected IRegion getSignedSelection(ISourceViewer sourceViewer)
    {
        StyledText text = sourceViewer.getTextWidget();
        Point selection = text.getSelectionRange();

        if (text.getCaretOffset() == selection.x)
        {
            selection.x = selection.x + selection.y;
            selection.y = -selection.y;
        }

        selection.x = widgetOffset2ModelOffset(sourceViewer, selection.x);

        return new Region(selection.x, selection.y);
    }

    protected ICharacterPairMatcher getPairMatcher()
    {
        return _pairMatcher;
    }

    protected void setPairMatcher(ICharacterPairMatcher matcher)
    {
        if (matcher instanceof AbstractPairMatcher)
        {
            AbstractPairMatcher pairMatcher = (AbstractPairMatcher) matcher;

            pairMatcher.setSQLEditor(this);

            _pairMatcher = pairMatcher;

            _painter.setFPairMatcher(pairMatcher);
        }
    }

} // end class
