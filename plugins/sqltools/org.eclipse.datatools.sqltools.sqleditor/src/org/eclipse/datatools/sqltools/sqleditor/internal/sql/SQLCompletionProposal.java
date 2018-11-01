/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import org.eclipse.core.runtime.Assert;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;


/**
 * @author Li Huang
 *  
 */
public class SQLCompletionProposal implements ISQLCompletionProposal
{

    /** The string to be displayed in the completion proposal popup. */
    private String              _displayString;
    /** The replacement string. */
    private String              _replacementString;
    /** The replacement offset. */
    private int                 _replacementOffset;
    /** The replacement length. */
    private int                 _replacementLength;
    /** The cursor position after this proposal has been applied. */
    private int                 _cursorPosition;
    /** The image to be displayed in the completion proposal popup. */
    private Image               _image;
    /** The context information of this proposal. */
    private IContextInformation _contextInformation;
    /** The additional info of this proposal. */
    private String              _additionalProposalInfo;
    /**
     * The relevance of this proposal.
     * <p>
     * database column table stored procedure trigger event reserved keyword unreserved keyword variable other
     * </p>
     * large numbers will appear first
     */
    private int                 _relevance;
    public final static int     TEMPLATE           = 0;
    public final static int     OPERATOR           = 1;
    public final static int     OTHER              = 2;
    public final static int     KEYWORD            = 3;
    public final static int     UNRESERVEDKEYWORD  = 3;//TODO?
    public final static int     DATABASE           = 4;
    public final static int     TABLE              = 5;
    public final static int     STORED_PROCEDURE   = 6;
    public final static int     FUNCTION           = 6;
    public final static int     TRIGGER            = 7;
    public final static int     EVENT              = 8;
    public final static int     COLUMN             = 9;
    public final static int     VARIABLE           = 0;
    public final static int     TEMPTABLE_TEMPLATE = 11;
    private IAction             _postAction        = null;

    /**
     * Creates a new completion proposal based on the provided information. The replacement string is considered being
     * the display string too. All remaining fields are set to <code>null</code>.
     * 
     * @param replacementString the actual string to be inserted into the document
     * @param replacementOffset the offset of the text to be replaced
     * @param replacementLength the length of the text to be replaced
     * @param cursorPosition the position of the cursor following the insert relative to replacementOffset
     * @param relevance the relevance for the proposal
     */
    public SQLCompletionProposal(String replacementString, int replacementOffset, int replacementLength,
        int cursorPosition, int relevance)
    {
        this(replacementString, replacementOffset, replacementLength, cursorPosition, null, null, null, null, relevance);
    }

    /**
     * Creates a new completion proposal. All fields are initialized based on the provided information.
     * 
     * @param replacementString the actual string to be inserted into the document
     * @param replacementOffset the offset of the text to be replaced
     * @param replacementLength the length of the text to be replaced
     * @param cursorPosition the position of the cursor following the insert relative to replacementOffset
     * @param image the image to display for this proposal
     * @param displayString the string to be displayed for the proposal
     * @param contextInformation the context information associated with this proposal
     * @param additionalProposalInfo the additional information associated with this proposal
     * @param relevance the relevance for the proposal
     */
    public SQLCompletionProposal(String replacementString, int replacementOffset, int replacementLength,
        int cursorPosition, Image image, String displayString, IContextInformation contextInformation,
        String additionalProposalInfo, int relevance)
    {
        this(replacementString, replacementOffset, replacementLength, cursorPosition, image, displayString,
            contextInformation, additionalProposalInfo, relevance, null);
    }

    /**
     * Creates a new completion proposal. All fields are initialized based on the provided information.
     * 
     * @param replacementString the actual string to be inserted into the document
     * @param replacementOffset the offset of the text to be replaced
     * @param replacementLength the length of the text to be replaced
     * @param cursorPosition the position of the cursor following the insert relative to replacementOffset
     * @param image the image to display for this proposal
     * @param displayString the string to be displayed for the proposal
     * @param contextInformation the context information associated with this proposal
     * @param additionalProposalInfo the additional information associated with this proposal
     * @param relevance the relevance for the proposal
     * @param postAction the action to be performed after the completion proposal is applied
     */
    public SQLCompletionProposal(String replacementString, int replacementOffset, int replacementLength,
        int cursorPosition, Image image, String displayString, IContextInformation contextInformation,
        String additionalProposalInfo, int relevance, IAction postAction)
    {
        Assert.isNotNull(replacementString);
        Assert.isTrue(replacementOffset >= 0);
        Assert.isTrue(replacementLength >= 0);
        Assert.isTrue(cursorPosition >= 0);

        _replacementString = replacementString;
        _replacementOffset = replacementOffset;
        _replacementLength = replacementLength;
        _cursorPosition = cursorPosition;
        _image = image;
        _displayString = displayString;
        _contextInformation = contextInformation;
        _additionalProposalInfo = additionalProposalInfo;
        _relevance = relevance;
        _postAction = postAction;
    }

    /*
     * @see ICompletionProposal#apply(IDocument)
     */
    public void apply(IDocument document)
    {
        try
        {
            document.replace(_replacementOffset, _replacementLength, _replacementString);
            if (_postAction != null)
            {
                Point selection = getSelection(document);
                if (selection != null)
                {
                    SQLEditor editor = (SQLEditor)SQLEditorPlugin.getActiveEditor();
                    if (editor != null)
                    {
                        //move caret to the new position so that _postAction will get correct context information
                        editor.getSV().setSelectedRange(selection.x, selection.y);
                    }
                }
                _postAction.run();
            }
        }
        catch (BadLocationException x)
        {
            SQLEditorPlugin.getDefault().log(x);
        }
    }

    /*
     * @see ICompletionProposal#getSelection(IDocument)
     */
    public Point getSelection(IDocument document)
    {
        return new Point(_replacementOffset + _cursorPosition, 0);
    }

    /*
     * @see ICompletionProposal#getContextInformation()
     */
    public IContextInformation getContextInformation()
    {
        return _contextInformation;
    }

    /*
     * @see ICompletionProposal#getImage()
     */
    public Image getImage()
    {
        return _image;
    }

    /*
     * @see ICompletionProposal#getDisplayString()
     */
    public String getDisplayString()
    {
        if (_displayString != null)
        return _displayString;
        return _replacementString;
    }

    /*
     * @see ICompletionProposal#getAdditionalProposalInfo()
     */
    public String getAdditionalProposalInfo()
    {
        return _additionalProposalInfo;
    }

    /**
     * @return Returns the _relevance.
     */
    public int getRelevance()
    {
        return _relevance;
    }

    /**
     * @param _relevance The Relevance to set.
     */
    public void setRelevance(int relevance)
    {
        this._relevance = relevance;
    }
}
