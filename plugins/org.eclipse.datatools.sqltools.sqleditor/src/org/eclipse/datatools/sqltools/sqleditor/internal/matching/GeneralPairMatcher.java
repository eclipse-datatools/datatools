/**
 * Created on Jul 28, 2008
 * 
 * Copyright (c) Sybase, Inc. 2004-2008. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.matching;

/**
 * Class for matching the tokens, for example the bracket, "<b>(</b>" and "<b>)</b>" ,"<b>[</b>" and "<b>]</b>"
 * in SQL Editor.
 * 
 * @author juewu
 */
public class GeneralPairMatcher extends AbstractPairMatcher
{
    public GeneralPairMatcher(IMatchingPairs matchingPairs)
    {
        super(matchingPairs);
    }

    public String getOriginalToken(String text, int offset)
    {
        if (offset == 0)
        {
            return null;
        }

        String token = text.substring(offset - 1, offset);

        if (_matchingPairs.isSupportedToken(token))
        {
            return token;
        }

        return null;
    }

    public int getOriginalTokenLength()
    {
        return 1;
    }

    public int getMatchingTokenLength()
    {
        return 1;
    }

    public int getOriginalTokenEndOffset(String text, int offset)
    {
        return offset;
    }

    public int getMatchingTokenStartOffset(String token, int start, boolean forward, String text)
    {
        // Token number counter in stack.
        int stackItemNum = 1;

        // Next start matching position.
        int nextStart = forward ? start : start - 1;
        int tOcc = start;
        int mOcc = start;

        String mToken = _matchingPairs.getMatchingPattern(token);

        if (forward)
        {
            while (stackItemNum != 0 && nextStart < text.length())
            {
                tOcc = text.indexOf(token, nextStart);
                mOcc = text.indexOf(mToken, nextStart);

                if (mOcc == -1)
                {
                    return -1;
                }

                if (tOcc == -1)
                {
                    stackItemNum--;

                    nextStart = mOcc + 1;
                    continue;
                }

                if (tOcc < mOcc)
                {
                    stackItemNum++;

                    nextStart = tOcc + 1;
                }
                else
                {
                    stackItemNum--;

                    nextStart = mOcc + 1;
                }
            }
        }
        else
        {
            while (stackItemNum != 0 && nextStart >= 0)
            {
                tOcc = text.lastIndexOf(token, nextStart - 1);
                mOcc = text.lastIndexOf(mToken, nextStart - 1);

                if (mOcc == -1)
                {
                    return -1;
                }

                if (tOcc == -1)
                {
                    stackItemNum--;

                    nextStart = mOcc;
                    continue;
                }

                if (tOcc > mOcc)
                {
                    stackItemNum++;

                    nextStart = tOcc;
                }
                else
                {
                    stackItemNum--;

                    nextStart = mOcc;
                }
            }
        }

        if (stackItemNum == 0)
        {
            // Adjust return position to be left position of the matching token.
            return forward ? nextStart - 1 : nextStart;
        }

        return start;
    }
}
