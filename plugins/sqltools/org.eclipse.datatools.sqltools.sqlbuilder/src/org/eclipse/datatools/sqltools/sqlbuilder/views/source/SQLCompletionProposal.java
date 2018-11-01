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
package org.eclipse.datatools.sqltools.sqlbuilder.views.source;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.util.Assert;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

public class SQLCompletionProposal implements ICompletionProposal, ICompletionProposalExtension {

    // RATLC01136221 bgp 14Jan2007 - begin
    public final static int PROPOSAL_TYPE_UNSPECIFIED = 0;
    public final static int PROPOSAL_TYPE_SYNTAX = 1;
    public final static int PROPOSAL_TYPE_SCHEMA = 2;
    public final static int PROPOSAL_TYPE_TABLE = 3;
    public final static int PROPOSAL_TYPE_COLUMN = 4;
    // RATLC01136221 bgp 14Jan2007 - end
    
    private String fDisplayString;
    private String fReplacementString;
    private int fReplacementOffset;
    private int fDocumentOffset = -1;
    private int fReplacementLength;
    private int fCursorPosition;
    private Image fImage;
    private IContextInformation fContextInformation;
    private int fContextInformationPosition;
    private char[] fTriggerCharacters;
    private String fAdditionalProposalInfo;
    private int fProposalType;  // RATLC01136221 bgp 14Jan2007

    /**
     * Creates a new completion proposal. All fields are initialized based on
     * the provided information.
     * 
     * @param replacementString
     *            the actual string to be inserted into the document
     * @param replacementOffset
     *            the offset of the text to be replaced
     * @param documentOffset
     *            the offset of the text to be replaced
     * @param replacementLength
     *            the length of the text to be replaced
     * @param image
     *            the image to display for this proposal
     * @param displayString
     *            the string to be displayed for the proposal If set to
     *            <code>null</code>, the replacement string will be taken as
     *            display string.
     */
    public SQLCompletionProposal(String replacementString, int replacementOffset, int documentOffset, int replacementLength, Image image, String displayString,
            IContextInformation contextInformation) {
        Assert.isNotNull(replacementString);
        Assert.isTrue(replacementOffset >= 0);
        Assert.isTrue(documentOffset >= 0);
        Assert.isTrue(replacementLength >= 0);

        fReplacementString = replacementString;
        fReplacementOffset = replacementOffset;
        fDocumentOffset = documentOffset;
        fReplacementLength = replacementLength;
        fImage = image;
        fDisplayString = displayString != null ? displayString : replacementString;

        fCursorPosition = replacementString.length();

        fContextInformation = contextInformation;
        //		fContextInformationPosition= -1;
        fTriggerCharacters = null;
        fAdditionalProposalInfo = null;
        fProposalType = PROPOSAL_TYPE_UNSPECIFIED;
    }

    /**
     * Creates a new completion proposal. All fields are initialized based on
     * the provided information.
     * 
     * @param replacementString
     *            the actual string to be inserted into the document
     * @param replacementOffset
     *            the offset of the text to be replaced
     * @param replacementLength
     *            the length of the text to be replaced
     * @param cursorPosition
     *            the position of the cursor following the insert relative to
     *            replacementOffset
     * @param image
     *            the image to display for this proposal
     * @param displayString
     *            the string to be displayed for the proposal
     * @param contentInformation
     *            the context information associated with this proposal
     * @param additionalProposalInfo
     *            the additional information associated with this proposal
     */
    public SQLCompletionProposal(String replacementString, int replacementOffset, int replacementLength, int cursorPosition, Image image, String displayString,
            IContextInformation contextInformation, String additionalProposalInfo) {
        Assert.isNotNull(replacementString);
        Assert.isTrue(replacementOffset >= 0);
        Assert.isTrue(replacementLength >= 0);
        Assert.isTrue(cursorPosition >= 0);

        fReplacementString = replacementString;
        fReplacementOffset = replacementOffset;
        fDocumentOffset = fReplacementOffset;
        fReplacementLength = replacementLength;
        fCursorPosition = cursorPosition;
        fImage = image;
        fDisplayString = displayString;
        fContextInformation = contextInformation;
        fAdditionalProposalInfo = additionalProposalInfo;
        fProposalType = PROPOSAL_TYPE_UNSPECIFIED;
    }

    /**
     * Sets the context information.
     * 
     * @param contentInformation
     *            The context information associated with this proposal
     */
    public void setContextInformation(IContextInformation contextInformation) {
        fContextInformation = contextInformation;
        fContextInformationPosition = (fContextInformation != null ? fCursorPosition : -1);
    }

    /**
     * Sets the trigger characters.
     * 
     * @param triggerCharacters
     *            The set of characters which can trigger the application of
     *            this completion proposal
     */
    public void setTriggerCharacters(char[] triggerCharacters) {
        fTriggerCharacters = triggerCharacters;
    }

    /**
     * Sets the cursor position relative to the insertion offset. By default
     * this is the length of the completion string (Cursor positioned after the
     * completion)
     * 
     * @param cursorPosition
     *            The cursorPosition to set
     */
    public void setCursorPosition(int cursorPosition) {
        Assert.isTrue(cursorPosition >= 0);
        fCursorPosition = cursorPosition;
        fContextInformationPosition = (fContextInformation != null ? fCursorPosition : -1);
    }

    /*
     * @see ICompletionProposalExtension#apply(IDocument, char, int)
     */
    public void apply(IDocument document, char trigger, int offset) {
        try {
            // patch replacement length
            int delta = offset - (fDocumentOffset + fReplacementLength);
            if (delta > 0)
                fReplacementLength += delta;

            if (trigger == (char) 0) {
                replace(document, fDocumentOffset, fReplacementLength, fReplacementString);
            }
            else {
                StringBuffer buffer = new StringBuffer(fReplacementString);

                // fix for PR #5533. Assumes that no eating takes place.
                if ((fReplacementLength < buffer.length() && buffer.charAt(fReplacementLength) != trigger)) {
                    buffer.insert(fCursorPosition, trigger);
                    ++fCursorPosition;
                }

                replace(document, fDocumentOffset, fReplacementLength, buffer.toString());
            }

            int oldLen = document.getLength();
            fDocumentOffset += document.getLength() - oldLen;
        }
        catch (BadLocationException x) {
            // ignore
        }
    }

    // #6410 - File unchanged but dirtied by code assist
    private void replace(IDocument document, int offset, int length, String string) throws BadLocationException {
        if (!document.get(offset, length).equals(string))
            document.replace(offset, length, string);
    }

    /*
     * @see ICompletionProposal#apply
     */
    public void apply(IDocument document) {
        //		apply(document, (char) 0, fReplacementOffset + fReplacementLength);
        apply(document, (char) 0, fDocumentOffset + fReplacementLength);
    }

    /*
     * @see ICompletionProposal#getSelection
     */
    public Point getSelection(IDocument document) {
        //		return new Point(fReplacementOffset + fCursorPosition, 0);
        return new Point(fDocumentOffset + fCursorPosition, 0);
    }

    /*
     * @see ICompletionProposal#getContextInformation()
     */
    public IContextInformation getContextInformation() {
        return fContextInformation;
    }

    /*
     * @see ICompletionProposal#getImage()
     */
    public Image getImage() {
        return fImage;
    }

    /*
     * @see ICompletionProposal#getDisplayString()
     */
    public String getDisplayString() {
        return fDisplayString;
    }

    /*
     * @see ICompletionProposal#getAdditionalProposalInfo()
     */
    public String getAdditionalProposalInfo() {
        return fAdditionalProposalInfo;
    }

    /*
     * @see ICompletionProposalExtension#getTriggerCharacters()
     */
    public char[] getTriggerCharacters() {
        return fTriggerCharacters;
    }

    /*
     * @see ICompletionProposalExtension#getContextInformationPosition()
     */
    public int getContextInformationPosition() {
        return fReplacementOffset + fContextInformationPosition;
    }

    /**
     * Gets the replacement offset.
     * 
     * @return Returns a int
     */
    public int getReplacementOffset() {
        return fReplacementOffset;
    }

    /**
     * Sets the replacement offset.
     * 
     * @param replacementOffset
     *            The replacement offset to set
     */
    public void setReplacementOffset(int replacementOffset) {
        Assert.isTrue(replacementOffset >= 0);
        fReplacementOffset = replacementOffset;
    }

    /**
     * Gets the replacement length.
     * 
     * @return Returns a int
     */
    public int getReplacementLength() {
        return fReplacementLength;
    }

    /**
     * Sets the replacement length.
     * 
     * @param replacementLength
     *            The replacementLength to set
     */
    public void setReplacementLength(int replacementLength) {
        Assert.isTrue(replacementLength >= 0);
        fReplacementLength = replacementLength;
    }

    /**
     * Gets the replacement string.
     * 
     * @return Returns a String
     */
    public String getReplacementString() {
        return fReplacementString;
    }

    /**
     * Sets the replacement string.
     * 
     * @param replacementString
     *            The replacement string to set
     */
    public void setReplacementString(String replacementString) {
        fReplacementString = replacementString;
    }

    /**
     * Sets the image.
     * 
     * @param image
     *            The image to set
     */
    public void setImage(Image image) {
        fImage = image;
    }

    /*
     * @see ICompletionProposalExtension#isValidFor(IDocument, int)
     */
    public boolean isValidFor(IDocument document, int offset) {
        if (offset < fReplacementOffset)
            return false;

        int replacementLength = fReplacementString == null ? 0 : fReplacementString.length();
        if (offset >= fReplacementOffset + replacementLength)
            return false;

        try {
            int length = offset - fReplacementOffset;
            String start = document.get(fReplacementOffset, length);
            return fReplacementString.substring(0, length).equalsIgnoreCase(start);
        }
        catch (BadLocationException x) {
        }

        return false;
    }

    // RATLC01136221 bgp 14Jan2007 - new method
    /**
     * Gets the proposal type of this proposal.  See the proposal type constants
     * defined in this class.
     * @return the proposal type
     */
    public int getProposalType() {
        return fProposalType;
    }

    // RATLC01136221 bgp 14Jan2007 - new method
    /**
     * Sets the proposal type for this proposal to the given type.  See the proposal 
     * type constants defined in this class.
     * @param proposalType the proposal type to set
     */
    public void setProposalType(int proposalType) {
        fProposalType = proposalType;
    }
}