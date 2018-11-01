/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal;

import java.util.Stack;

import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLPartitionScanner;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.link.ILinkedModeListener;
import org.eclipse.jface.text.link.LinkedModeModel;
import org.eclipse.jface.text.link.LinkedModeUI;
import org.eclipse.jface.text.link.LinkedPosition;
import org.eclipse.jface.text.link.LinkedPositionGroup;
import org.eclipse.jface.text.link.LinkedModeUI.ExitFlags;
import org.eclipse.jface.text.link.LinkedModeUI.IExitPolicy;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.texteditor.link.EditorLinkedModeUI;

/**
 * 
 * @author Li Huang
 * 
 */
public class SymbolInserter implements VerifyKeyListener, ILinkedModeListener
{

    private boolean          _closeSingleQuotes = true;
    private boolean          _closeDoubleQuotes = true;
    private boolean          _closeBrackets     = true;

    private final String     CATEGORY           = toString();
    private IPositionUpdater _updater           = new ExclusivePositionUpdater(CATEGORY);
    private Stack            _bracketLevelStack = new Stack();
	private SQLEditor _sqlEditor;

    public SymbolInserter(SQLEditor editor)
    {
    	this._sqlEditor = editor;
    }
    
    public void setCloseSingleQuotesEnabled(boolean enabled)
    {
        _closeSingleQuotes = enabled;
    }

    public void setCloseDoubleQuotesEnabled(boolean enabled)
    {
        _closeDoubleQuotes = enabled;
    }

    public void setCloseBracketsEnabled(boolean enabled)
    {
        _closeBrackets = enabled;
    }

    private boolean hasIdentifierToTheRight(IDocument document, int offset)
    {
        try
        {
            int end = offset;
            IRegion endLine = document.getLineInformationOfOffset(end);
            int maxEnd = endLine.getOffset() + endLine.getLength();
            while (end != maxEnd && Character.isWhitespace(document.getChar(end)))
            ++end;

            return end != maxEnd && Character.isJavaIdentifierPart(document.getChar(end));

        }
        catch (BadLocationException e)
        {
            // be conservative
//            _log.debug(EditorMessages.error_badLocationException, e);
            return true;
        }
    }

    private boolean hasIdentifierToTheLeft(IDocument document, int offset)
    {
        try
        {
            int start = offset;
            IRegion startLine = document.getLineInformationOfOffset(start);
            int minStart = startLine.getOffset();
            return start != minStart && Character.isJavaIdentifierPart(document.getChar(start - 1));

        }
        catch (BadLocationException e)
        {
//            _log.debug(EditorMessages.error_badLocationException, e);
            return true;
        }
    }

    private boolean hasCharacterToTheRight(IDocument document, int offset, char character)
    {
        try
        {
            int end = offset;
            IRegion endLine = document.getLineInformationOfOffset(end);
            int maxEnd = endLine.getOffset() + endLine.getLength();
            while (end != maxEnd && Character.isWhitespace(document.getChar(end)))
            {
                ++end;
            }

            return end != maxEnd && document.getChar(end) == character;

        }
        catch (BadLocationException e)
        {
//            _log.debug(EditorMessages.error_badLocationException, e);
            // be conservative
            return true;
        }
    }

    /*
     * @see org.eclipse.swt.custom.VerifyKeyListener#verifyKey(org.eclipse.swt.events.VerifyEvent)
     */
    public void verifyKey(VerifyEvent event)
    {

        if (!event.doit)
        {
            return;
        }

		ISourceViewer sourceviewer = _sqlEditor.getSV();
		IDocument document = sourceviewer.getDocument();

        final Point selection = sourceviewer.getSelectedRange();
        final int offset = selection.x;
        final int length = selection.y;

        switch (event.character)
        {
            case '(':
                if (!_closeBrackets)
                {
                    return;
                }
                if (hasCharacterToTheRight(document, offset + length, '('))
                {
                    return;
                }

                // fall through

            case '\'':
                if (event.character == '\'')
                {
                    if (!_closeSingleQuotes)
                    {
                        return;
                    }
                    if (hasIdentifierToTheLeft(document, offset)
                    || hasIdentifierToTheRight(document, offset + length))
                    {
                        return;
                    }
                }

                // fall through

            case '"':
                if (event.character == '"')
                {
                    if (!_closeDoubleQuotes)
                    {
                        return;
                    }
                    if (hasIdentifierToTheLeft(document, offset)
                    || hasIdentifierToTheRight(document, offset + length))
                    {
                        return;
                    }
                }

                try
                {
                    ITypedRegion partition = TextUtilities.getPartition(document, SQLPartitionScanner.SQL_PARTITIONING,
                        offset, true);
                    if (!IDocument.DEFAULT_CONTENT_TYPE.equals(partition.getType())
                    && partition.getOffset() != offset)
                    {
                        return;
                    }

                    if (!_sqlEditor.validateEditorInputState())
                    {
                        return;
                    }

                    final char character = event.character;
                    final char closingCharacter = getPeerCharacter(character);
                    final StringBuffer buffer = new StringBuffer();
                    buffer.append(character);
                    buffer.append(closingCharacter);

                    document.replace(offset, length, buffer.toString());

                    SymbolLevel level = new SymbolLevel();
                    _bracketLevelStack.push(level);

                    LinkedPositionGroup group = new LinkedPositionGroup();
                    group.addPosition(new LinkedPosition(document, offset + 1, 0, LinkedPositionGroup.NO_STOP));

                    LinkedModeModel model = new LinkedModeModel();
                    model.addLinkingListener(this);
                    model.addGroup(group);
                    model.forceInstall();

                    level._offset = offset;
                    level._length = 2;

                    // set up position tracking for our magic peers
                    if (_bracketLevelStack.size() == 1)
                    {
                        document.addPositionCategory(CATEGORY);
                        document.addPositionUpdater(_updater);
                    }
                    level._firstPosition = new Position(offset, 1);
                    level._secondPosition = new Position(offset + 1, 1);
                    document.addPosition(CATEGORY, level._firstPosition);
                    document.addPosition(CATEGORY, level._secondPosition);

                    level._uI = new EditorLinkedModeUI(model, sourceviewer);
                    level._uI.setSimpleMode(true);
                    level._uI.setExitPolicy(new ExitPolicy(closingCharacter, getEscapeCharacter(closingCharacter), _bracketLevelStack));
                    level._uI.setExitPosition(sourceviewer, offset + 2, 0, Integer.MAX_VALUE);
                    level._uI.setCyclingMode(LinkedModeUI.CYCLE_NEVER);
                    level._uI.enter();

                    IRegion newSelection = level._uI.getSelectedRegion();
                    sourceviewer.setSelectedRange(newSelection.getOffset(), newSelection.getLength());

                    event.doit = false;

                }
                catch (BadLocationException e)
                {
//                    _log.error(EditorMessages.error_badLocationException, e);
                }
                catch (BadPositionCategoryException e)
                {
//                    _log.error(EditorMessages.error_badLocationException, e);
                }
                break;
        }
    }

    /*
     * @see org.eclipse.jface.text.link.ILinkedModeListener#left(org.eclipse.jface.text.link.LinkedModeModel, int)
     */
    public void left(LinkedModeModel environment, int flags)
    {

        final SymbolLevel level = (SymbolLevel) _bracketLevelStack.pop();

        if (flags != ILinkedModeListener.EXTERNAL_MODIFICATION)
        {
            return;
        }

        // remove brackets
        final IDocument document = _sqlEditor.getSV().getDocument();
        if (document instanceof IDocumentExtension)
        {
            IDocumentExtension extension = (IDocumentExtension) document;
            extension.registerPostNotificationReplace(null, new IDocumentExtension.IReplace()
            {

                public void perform(IDocument d, IDocumentListener owner)
                {
                    if ((level._firstPosition.isDeleted || level._firstPosition.length == 0)
                    && !level._secondPosition.isDeleted
                        && level._secondPosition.offset == level._firstPosition.offset)
                    {
                        try
                        {
                            document.replace(level._secondPosition.offset, level._secondPosition.length, null);
                        }
                        catch (BadLocationException e)
                        {
                        }
                    }

                    if (_bracketLevelStack.size() == 0)
                    {
                        document.removePositionUpdater(_updater);
                        try
                        {
                            document.removePositionCategory(CATEGORY);
                        }
                        catch (BadPositionCategoryException e)
                        {
                        }
                    }
                }

            }
            );
        }

    }

    /*
     * @see org.eclipse.jface.text.link.ILinkedModeListener#suspend(org.eclipse.jface.text.link.LinkedModeModel)
     */
    public void suspend(LinkedModeModel environment)
    {
    }

    /*
     * @see org.eclipse.jface.text.link.ILinkedModeListener#resume(org.eclipse.jface.text.link.LinkedModeModel, int)
     */
    public void resume(LinkedModeModel environment, int flags)
    {
    }
    
    private static class SymbolLevel
    {
        int          _offset;
        int          _length;
        LinkedModeUI _uI;
        Position     _firstPosition;
        Position     _secondPosition;
    }
    
    private class ExitPolicy implements IExitPolicy
    {

        final char  _exitCharacter;
        final char  _escapeCharacter;
        final Stack _stack;
        final int   _size;

        public ExitPolicy(char exitCharacter, char escapeCharacter, Stack stack)
        {
            _exitCharacter = exitCharacter;
            _escapeCharacter = escapeCharacter;
            _stack = stack;
            _size = _stack.size();
        }

        /*
         * @see org.eclipse.jdt.internal.ui.text.link.LinkedPositionUI.ExitPolicy#doExit(org.eclipse.jdt.internal.ui.text.link.LinkedPositionManager,
         *      org.eclipse.swt.events.VerifyEvent, int, int)
         */
        public ExitFlags doExit(LinkedModeModel model, VerifyEvent event, int offset, int length)
        {

            if (event.character == _exitCharacter)
            {

                if (_size == _stack.size() && !isMasked(offset))
                {
                    SymbolLevel level = (SymbolLevel) _stack.peek();
                    if (level._firstPosition.offset > offset || level._secondPosition.offset < offset)
                    {
                        return null;
                    }
                    if (level._secondPosition.offset == offset && length == 0)
                    {
                        // don't enter the character if if its the closing peer
                        return new ExitFlags(ILinkedModeListener.UPDATE_CARET, false);
                    }
                }
            }
            return null;
        }

        private boolean isMasked(int offset)
        {
            IDocument document = _sqlEditor.getSV().getDocument();
            try
            {
                return _escapeCharacter == document.getChar(offset - 1);
            }
            catch (BadLocationException e)
            {
//                _log.debug(EditorMessages.error_badLocationException, e);
            }
            return false;
        }
    }

    public static char getEscapeCharacter(char character)
    {
        switch (character)
        {
            case '"':
            case '\'':
                return '\\';
            default:
                return 0;
        }
    }


    public static char getPeerCharacter(char character)
    {
        switch (character)
        {
            case '(':
                return ')';

            case ')':
                return '(';

            case '"':
                return character;

            case '\'':
                return character;

            default:
                throw new IllegalArgumentException();
        }
    }

}
