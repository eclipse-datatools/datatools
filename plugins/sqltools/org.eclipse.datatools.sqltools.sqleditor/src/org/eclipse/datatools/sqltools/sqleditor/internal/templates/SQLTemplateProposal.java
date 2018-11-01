/***********************************************************************************************************************
 * Copyright (c) 2007 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.templates;

import java.util.HashMap;

import org.eclipse.datatools.sqltools.editor.template.SQLTemplate;
import org.eclipse.datatools.sqltools.editor.template.TemplateConstant;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.ISQLCompletionProposal;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.Assert;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension2;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension3;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.link.ILinkedModeListener;
import org.eclipse.jface.text.link.InclusivePositionUpdater;
import org.eclipse.jface.text.link.LinkedModeModel;
import org.eclipse.jface.text.link.LinkedModeUI;
import org.eclipse.jface.text.link.LinkedPosition;
import org.eclipse.jface.text.link.LinkedPositionGroup;
import org.eclipse.jface.text.link.ProposalPosition;
import org.eclipse.jface.text.templates.DocumentTemplateContext;
import org.eclipse.jface.text.templates.GlobalTemplateVariables;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateBuffer;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;

/**
 * This class is refactored from TemplateProposal to support intelligence template.
 * 
 * @author lihuang
 */
public class SQLTemplateProposal implements ICompletionProposal, ICompletionProposalExtension,
        ICompletionProposalExtension2, ICompletionProposalExtension3, ISQLCompletionProposal
{

    public final static String         KEY_SQLEDITOR    = "SQLEDITOR";
    private Template                   _template;
    private final TemplateContext      _context;
    private final Image                _image;
    private final IRegion              _region;
    private int                        _relevance;

    private IRegion                    _selectedRegion;
    private String                     _displayString;
    private InclusivePositionUpdater   _updater;
    private IInformationControlCreator _informationControlCreator;

    private SQLEditor                  _editor;

    private boolean                    inserted         = false;

    private int                        _startOffset;
    private int                        _endOffset;

    private IPreferenceStore           _preferenceStore = SQLEditorPlugin.getDefault().getPreferenceStore();
    
    private int count = 0;
    
    /**
     * Creates a SQL template proposal with a template and its context.
     * 
     * @param template the template
     * @param context the context in which the template was requested.
     * @param region the region this proposal is applied to
     * @param image the icon of the proposal.
     */
    public SQLTemplateProposal(SQLEditor editor, Template template, TemplateContext context, IRegion region, Image image)
    {
        this(editor, template, context, region, image, 0);
    }

    /**
     * Creates a template proposal with a template and its context.
     * 
     * @param template the template
     * @param context the context in which the template was requested.
     * @param image the icon of the proposal.
     * @param region the region this proposal is applied to
     * @param relevance the relevance of the proposal
     */
    public SQLTemplateProposal(SQLEditor editor, Template template, TemplateContext context, IRegion region,
            Image image, int relevance)
    {
        Assert.isNotNull(template);
        Assert.isNotNull(context);
        Assert.isNotNull(region);

        _template = template;
        _context = context;
        _image = image;
        _region = region;

        _displayString = null;

        _relevance = relevance;

        _editor = editor;
    }

    /**
     * Sets the information control creator for this completion proposal.
     * 
     * @param informationControlCreator the information control creator
     * @since 3.1
     */
    public final void setInformationControlCreator(IInformationControlCreator informationControlCreator)
    {
        _informationControlCreator = informationControlCreator;
    }

    /**
     * Returns the template of this proposal.
     * 
     * @return the template of this proposal
     * @since 3.1
     */
    protected final Template getTemplate()
    {
        return _template;
    }

    /**
     * Returns the context in which the template was requested.
     * 
     * @return the context in which the template was requested
     * @since 3.1
     */
    protected final TemplateContext getContext()
    {
        return _context;
    }

    /*
     * @see ICompletionProposal#apply(IDocument)
     */
    public final void apply(IDocument document)
    {
        // not called anymore
    }

    /**
     * Inserts the template offered by this proposal into the viewer's document and sets up a <code>LinkedModeUI</code>
     * on the viewer to edit any of the template's unresolved variables.
     * 
     * @param viewer {@inheritDoc}
     * @param trigger {@inheritDoc}
     * @param stateMask {@inheritDoc}
     * @param offset {@inheritDoc}
     */
    public void apply(ITextViewer viewer, char trigger, int stateMask, int offset)
    {

        // template is inserted into editor.
        if (inserted == true)
        {
            if (!_editor.isConnected())
            {
                inserted = false;
                return;
            }

            try
            {
                TemplateBuffer templateBuffer = null;
                TemplateVariable[] variables = null;

                try
                {
                    templateBuffer = _context.evaluate(_template);
                    variables = templateBuffer.getVariables();
                }
                catch (BadLocationException e)
                {
                    SQLEditorPlugin.getDefault().log(e);
                }

                HashMap hashMap = new HashMap();
                String word = "";
                int offset1 = ((TextSelection) viewer.getSelectionProvider().getSelection()).getOffset();
                if (variables != null)
                {
                    for (int i = 0; i < variables.length; i++)
                    {

                        String text = viewer.getDocument().get();

                        word = findWord(text, offset1);
                        hashMap.put(variables[i].getName(), word);
                    }
                }

                hashMap.put(KEY_SQLEDITOR, _editor);

                SQLDevToolsUIConfiguration configuration = SQLToolsUIFacade.getConfigurationByProfileName(_editor
                        .getDatabaseIdentifier().getProfileName());
                SQLTemplate template = configuration.getTemplateService().getIntelligenceTemplate(
                        ((SQLTemplate) _template).getId(), hashMap, _editor.getDatabaseIdentifier());
                _preferenceStore.setValue(TemplateConstant.INTELLIGENT_TEMPLATE, template.getClass().getName());
                if (template.getPattern() != null)
                {
                    insertTemplate(viewer, 0, _startOffset, offset1, template);
                }

            }
            catch (TemplateException e)
            {
                _selectedRegion = _region;
                SQLEditorPlugin.getDefault().log(e);
            }
            catch (Exception e)
            {
                SQLEditorPlugin.getDefault().log(e);
            }
            finally
            {
                inserted = false;
                return;
            }
        }

        int start = getReplaceOffset();
        int end = Math.max(getReplaceEndOffset(), offset);

        insertTemplate(viewer, offset, start, end, _template);

        inserted = true;

    }

    private void insertTemplate(ITextViewer viewer, int offset, int start, int end, Template template)
    {
        IDocument document = viewer.getDocument();
        try
        {
            _context.setReadOnly(false);
            TemplateBuffer templateBuffer;
            try
            {
                templateBuffer = _context.evaluate(template);
            }
            catch (TemplateException e)
            {
                _selectedRegion = _region;
                SQLEditorPlugin.getDefault().log(e);
                return;
            }

            // insert template string
            String templateString = templateBuffer.getString();

            int documentlength = end - start;
            if (end - start > document.getLength() - start)
            {
                documentlength = document.getLength() - start;
            }
            document.replace(start, documentlength, templateString);
            _startOffset = start;
            _endOffset = start + templateString.length();

            // translate positions
            LinkedModeModel model = new LinkedModeModel();
            TemplateVariable[] variables = templateBuffer.getVariables();
            boolean hasPositions = false;
            for (int i = 0; i != variables.length; i++)
            {
                TemplateVariable variable = variables[i];

                if (variable.isUnambiguous())
                {
                    continue;
                }

                LinkedPositionGroup group = new LinkedPositionGroup();

                int[] offsets = variable.getOffsets();
                int length = variable.getLength();

                String[] values = variable.getValues();
                ICompletionProposal[] proposals = new ICompletionProposal[values.length];
                for (int j = 0; j < values.length; j++)
                {
                    ensurePositionCategoryInstalled(document, model);
                    Position pos = new Position(offsets[0] + start, length);
                    document.addPosition(getCategory(), pos);

                    proposals[j] = new PositionBasedCompletionProposal(values[j], pos, length);
                }

                for (int j = 0; j != offsets.length; j++)
                    if (j == 0 && proposals.length > 1)
                    {
                        group.addPosition(new ProposalPosition(document, offsets[j] + start, length, proposals));
                    }
                    else
                    {
                        group.addPosition(new LinkedPosition(document, offsets[j] + start, length));
                    }

                model.addGroup(group);
                hasPositions = true;
            }

            if (hasPositions)
            {
                model.forceInstall();
                LinkedModeUI ui = new LinkedModeUI(model, viewer);
                ui.setExitPosition(viewer, getCaretOffset(templateBuffer) + start, 0, Integer.MAX_VALUE);
                ui.setDoContextInfo(true);
                ui.enter();

                _selectedRegion = ui.getSelectedRegion();
            }
            else
            {
                ensurePositionCategoryRemoved(document);
                _selectedRegion = new Region(getCaretOffset(templateBuffer) + start, 0);
            }

        }
        catch (BadLocationException e)
        {
            openErrorDialog(viewer.getTextWidget().getShell(), e);
            SQLEditorPlugin.getDefault().log(e);
            ensurePositionCategoryRemoved(document);
            _selectedRegion = _region;
        }
        catch (BadPositionCategoryException e)
        {
            openErrorDialog(viewer.getTextWidget().getShell(), e);
            SQLEditorPlugin.getDefault().log(e);
            _selectedRegion = _region;
        }
    }

    private void ensurePositionCategoryInstalled(final IDocument document, LinkedModeModel model)
    {
        if (!document.containsPositionCategory(getCategory()))
        {
            document.addPositionCategory(getCategory());
            _updater = new InclusivePositionUpdater(getCategory());
            document.addPositionUpdater(_updater);

            model.addLinkingListener(new ILinkedModeListener()
            {

                /*
                 * @see org.eclipse.jface.text.link.ILinkedModeListener#left(org.eclipse.jface.text.link.LinkedModeModel,
                 *      int)
                 */
                public void left(LinkedModeModel environment, int flags)
                {
                    ensurePositionCategoryRemoved(document);

                    if (_template instanceof SQLTemplate)
                    {
                        String id = ((SQLTemplate) _template).getId();
                        if (id.startsWith(TemplateConstant.INTELLIGENT_TEMPLATE) && inserted == true)
                        {
                            apply((ITextViewer) _editor.getSV(), ' ', 0, 0);
                        }
                        count++;
                        if (count == 2)
                        {
                            _preferenceStore.setValue(TemplateConstant.INTELLIGENT_TEMPLATE, "");
                        }
                    }

                }

                public void suspend(LinkedModeModel environment)
                {
                }

                public void resume(LinkedModeModel environment, int flags)
                {
                }
            });
        }
    }

    private void ensurePositionCategoryRemoved(IDocument document)
    {
        if (document.containsPositionCategory(getCategory()))
        {
            try
            {
                document.removePositionCategory(getCategory());
            }
            catch (BadPositionCategoryException e)
            {
                // ignore
            }
            document.removePositionUpdater(_updater);
        }
    }

    private String getCategory()
    {
        return "TemplateProposalCategory_" + toString(); //$NON-NLS-1$
    }

    private int getCaretOffset(TemplateBuffer buffer)
    {

        TemplateVariable[] variables = buffer.getVariables();
        for (int i = 0; i != variables.length; i++)
        {
            TemplateVariable variable = variables[i];
            if (variable.getType().equals(GlobalTemplateVariables.Cursor.NAME))
            {
                return variable.getOffsets()[0];
            }
        }

        return buffer.getString().length();
    }

    /**
     * Returns the offset of the range in the document that will be replaced by applying this template.
     * 
     * @return the offset of the range in the document that will be replaced by applying this template
     * @since 3.1
     */
    protected final int getReplaceOffset()
    {
        int start;
        if (_context instanceof DocumentTemplateContext)
        {
            DocumentTemplateContext docContext = (DocumentTemplateContext) _context;
            start = docContext.getStart();
        }
        else
        {
            start = _region.getOffset();
        }
        return start;
    }

    /**
     * Returns the end offset of the range in the document that will be replaced by applying this template.
     * 
     * @return the end offset of the range in the document that will be replaced by applying this template
     * @since 3.1
     */
    protected final int getReplaceEndOffset()
    {
        int end;
        if (_context instanceof DocumentTemplateContext)
        {
            DocumentTemplateContext docContext = (DocumentTemplateContext) _context;
            end = docContext.getEnd();
        }
        else
        {
            end = _region.getOffset() + _region.getLength();
        }
        return end;
    }

    /*
     * @see ICompletionProposal#getSelection(IDocument)
     */
    public Point getSelection(IDocument document)
    {
        return new Point(_selectedRegion.getOffset(), _selectedRegion.getLength());
    }

    /*
     * @see ICompletionProposal#getAdditionalProposalInfo()
     */
    public String getAdditionalProposalInfo()
    {
        try
        {
            _context.setReadOnly(true);
            TemplateBuffer templateBuffer;
            try
            {
                templateBuffer = _context.evaluate(_template);
            }
            catch (TemplateException e)
            {
                return null;
            }
            
            if ( (_template instanceof SQLTemplate) && ((SQLTemplate) _template).getId().startsWith(TemplateConstant.INTELLIGENT_TEMPLATE))
            {
                String str = null;
                str = "<B><CENTER>" + _template.getPattern() + "</B> </CENTER><BR>";
                str = str
                        + Messages.SQLTemplateProposal_proposalInfo;
                str = str +   "<B>" + ((SQLTemplate)_template).getProposalPopupDescription() + "</B>";

                return str;
            }
            else
            {
                return templateBuffer.getString();
            }

        }
        catch (BadLocationException e)
        {
            return null;
        }
    }

    /*
     * @see ICompletionProposal#getDisplayString()
     */
    public String getDisplayString()
    {
        if (_displayString == null)
        {
            String[] arguments = new String[]
            {
                _template.getName(), _template.getDescription()
            };
            _displayString = Messages.format(Messages.TemplateProposal_displayString, arguments);//$NON-NLS-1$
        }
        return _displayString;
    }

    /*
     * @see ICompletionProposal#getImage()
     */
    public Image getImage()
    {
        return _image;
    }

    /*
     * @see ICompletionProposal#getContextInformation()
     */
    public IContextInformation getContextInformation()
    {
        return null;
    }

    private void openErrorDialog(Shell shell, Exception e)
    {
        MessageDialog.openError(shell, Messages.TemplateProposal_errorDialog_title, e.getMessage()); //$NON-NLS-1$
    }

    /**
     * Returns the relevance.
     * 
     * @return the relevance
     */
    public int getRelevance()
    {
        return _relevance;
    }

    /*
     * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension3#getInformationControlCreator()
     */
    public IInformationControlCreator getInformationControlCreator()
    {
        return _informationControlCreator;
    }

    /*
     * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension2#selected(org.eclipse.jface.text.ITextViewer,
     *      boolean)
     */
    public void selected(ITextViewer viewer, boolean smartToggle)
    {
    }

    /*
     * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension2#unselected(org.eclipse.jface.text.ITextViewer)
     */
    public void unselected(ITextViewer viewer)
    {
    }

    /*
     * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension2#validate(org.eclipse.jface.text.IDocument,
     *      int, org.eclipse.jface.text.DocumentEvent)
     */
    public boolean validate(IDocument document, int offset, DocumentEvent event)
    {
        try
        {
            int replaceOffset = getReplaceOffset();
            if (offset >= replaceOffset)
            {
                String content = document.get(replaceOffset, offset - replaceOffset);
                return _template.getName().toLowerCase().startsWith(content.toLowerCase());
            }
        }
        catch (BadLocationException e)
        {
            // concurrent modification - ignore
        }
        return false;
    }

    /*
     * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension3#getPrefixCompletionText(org.eclipse.jface.text.IDocument,
     *      int)
     */
    public CharSequence getPrefixCompletionText(IDocument document, int completionOffset)
    {
        return _template.getName();
    }

    /*
     * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension3#getPrefixCompletionStart(org.eclipse.jface.text.IDocument,
     *      int)
     */
    public int getPrefixCompletionStart(IDocument document, int completionOffset)
    {
        return getReplaceOffset();
    }

    /*
     * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension#apply(org.eclipse.jface.text.IDocument,
     *      char, int)
     */
    public void apply(IDocument document, char trigger, int offset)
    {
        // not called any longer
    }

    /*
     * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension#isValidFor(org.eclipse.jface.text.IDocument,
     *      int)
     */
    public boolean isValidFor(IDocument document, int offset)
    {
        // not called any longer
        return false;
    }

    /*
     * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension#getTriggerCharacters()
     */
    public char[] getTriggerCharacters()
    {
        // no triggers
        return new char[0];
    }

    /*
     * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension#getContextInformationPosition()
     */
    public int getContextInformationPosition()
    {
        return _region.getOffset();
    }

    /**
     * Find the qualified object identifier.
     * 
     * @param text
     * @param offset
     * @return
     */
    private String findWord(String text, int offset)
    {
        // find word from variable's offset until white space is encountered.
        int end = offset;
        for (int i = end - 1; i > 0; i--)
        {
            if (text.charAt(i) == ' ' || text.charAt(i) == '\n' || text.charAt(i) == '\t')
            {
                break;
            }
            end--;
        }

        return text.substring(end, offset);
    }
}
