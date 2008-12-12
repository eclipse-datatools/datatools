/**
 * Created on Jul 31, 2008
 * 
 * Copyright (c) Sybase, Inc. 2004-2008. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.matching;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.matching.AbstractPairMatcher;
import org.eclipse.datatools.sqltools.sqleditor.internal.matching.GeneralPairMatcher;
import org.eclipse.datatools.sqltools.sqleditor.internal.matching.GeneralMatchingPairs;
import org.eclipse.datatools.sqltools.sqleditor.internal.matching.IMatchingPairs;

/**
 * The class intends for generic SQL token matching.
 * 
 * @author juewu
 */
public class GenericSQLPairMatcher extends AbstractPairMatcher
{
    /** Using the general matcher to deal with general matching. */
    protected GeneralPairMatcher _generalMatcher;
    /** The variable to record the last selected token length. */
    protected int                _originalTokenLength;
    /** The variable to record the last matching token length. */
    protected int                _matchingTokenLength;
    /** The variable to record the last selected token end offset. */
    protected int                _originalTokenEndOffset = -1;

    public GenericSQLPairMatcher(IMatchingPairs matchingPairs)
    {
        super(matchingPairs);

        _generalMatcher = new GeneralPairMatcher(GeneralMatchingPairs.getInstance());

    }

    public int getMatchingTokenLength()
    {
        return _matchingTokenLength;
    }

    public int getMatchingTokenStartOffset(String token, int start, boolean forward, String text)
    {
        String originalToken = getOriginalToken(text, start);

        if (originalToken == null)
        {
            return -1;
        }

        // If is the selected token is "(", ")", "[", "]" and so on.
        if (originalToken.length() == 1)
        {
            return _generalMatcher.getMatchingTokenStartOffset(token, start, forward, text);
        }

        Matcher m = Pattern.compile(_matchingPairs.getMatchingPatternClosure(originalToken)).matcher(text);

        Stack tokenStack = new Stack();
        tokenStack.push(new int[]
        {
            _originalTokenEndOffset - _originalTokenLength, _originalTokenEndOffset
        });

        int tempStart = 0, tempEnd = 0;
        String tempToken = null;
        // matching
        if (forward)
        {
            int nextStart = _originalTokenEndOffset;

            while (m.find(nextStart) && !tokenStack.isEmpty())
            {
                tempStart = m.start();
                tempEnd = m.end();

                // If the matching token is in non-code fragment, skip it.
                if (!isCode(tempStart))
                {
                    nextStart = tempEnd;
                    continue;
                }

                tempToken = text.substring(tempStart, tempEnd);

                String currentToken = getCurrentToken(text, tempEnd, false);

                if (!(currentToken != null && currentToken.equals(tempToken)))
                {
                    nextStart = tempEnd;
                    continue;
                }

                int[] top = (int[]) tokenStack.peek();

                // If the next found token is left token, push it to stack.
                if (_matchingPairs.isLeftToken(tempToken))
                {
                    tokenStack.push(new int[]
                    {
                        tempStart, tempEnd
                    });
                }
                else
                {
                    // If the the next found token is right token and its not matched with top token in stack, it means
                    // the sql document has syntax error. Judge whether there is the situation that, the found token can
                    // be matched with bottom token in stack. If it's so, the found token will be matching token in
                    // error situation.
                    while (!Pattern.compile(_matchingPairs.getMatchingPattern(tempToken)).matcher(
                            text.substring(top[0], top[1])).matches())
                    {
                        tokenStack.pop();
                        if (tokenStack.isEmpty())
                        {
                            return -1;
                        }
                        top = (int[]) tokenStack.peek();
                    }

                    tokenStack.pop();
                }

                nextStart = tempEnd;
            }
        }
        else
        {
            // The list to store needed token, when backward matching.
            List tokenList = new ArrayList();

            // Getting all needed tokens.
            while (m.find())
            {
                tempStart = m.start();
                tempEnd = m.end();

                tempToken = text.substring(tempStart, tempEnd);

                if (tempStart >= _originalTokenEndOffset - _originalTokenLength)
                {
                    break;
                }

                String currentToken = getCurrentToken(text, tempEnd, false);

                if (isCode(tempStart) && currentToken != null && currentToken.equals(tempToken))
                {
                    tokenList.add(new int[]
                    {
                        tempStart, tempEnd
                    });
                }
            }

            Object[] tokens = tokenList.toArray();

            // Backward matching.
            for (int i = tokens.length - 1; i >= 0 && !tokenStack.isEmpty(); i--)
            {
                int[] top = (int[]) tokenStack.peek();

                tempStart = ((int[]) tokens[i])[0];

                tempEnd = ((int[]) tokens[i])[1];

                tempToken = text.substring(tempStart, tempEnd);

                if (!_matchingPairs.isLeftToken(tempToken))
                {
                    tokenStack.push(new int[]
                    {
                        tempStart, tempEnd
                    });
                }
                else
                {
                    while (!Pattern.compile(_matchingPairs.getMatchingPattern(tempToken)).matcher(
                            text.substring(top[0], top[1])).matches())
                    {
                        tokenStack.pop();
                        if (tokenStack.isEmpty())
                        {
                            return -1;
                        }
                        top = (int[]) tokenStack.peek();
                    }

                    tokenStack.pop();
                }

            }
        }

        if (tokenStack.isEmpty())
        {
            _matchingTokenLength = tempToken.length();

            String currentToken = getCurrentToken(text, tempStart, false);

            // Double check for some exception situation, for example: [being] --> [end], the matching token is x[end],
            // but the 'xend' is not valid token.
            if (currentToken != null
                    && Pattern.compile(_matchingPairs.getMatchingPattern(originalToken)).matcher(currentToken)
                            .matches())
            {
                return tempStart;
            }
        }

        return -1;
    }

    public String getOriginalToken(String text, int offset)
    {
        String token = _generalMatcher.getOriginalToken(text, offset);

        // When get original token, let the priority of bracket higher.
        if (token != null)
        {
            _originalTokenEndOffset = _generalMatcher.getOriginalTokenEndOffset(text, offset);
            _originalTokenLength = _generalMatcher.getOriginalTokenLength();
            _matchingTokenLength = _generalMatcher.getMatchingTokenLength();

            return token;
        }
        
        return getCurrentToken(text, offset, true);
    }

    public int getOriginalTokenEndOffset(String text, int offset)
    {
        return _originalTokenEndOffset;
    }

    public int getOriginalTokenLength()
    {
        return _originalTokenLength;
    }

    public void setSQLEditor(SQLEditor editor)
    {
        super.setSQLEditor(editor);

        _generalMatcher.setSQLEditor(editor);
    }

    protected boolean isDelimiter(char c)
    {
        if (c == ' ' || c == '\t' || c == '\r' || c == '\n' || c == ';' || c == '(' || c == ')' || c == '[' || c == ']'
                || c == ',')
        {
            return true;
        }

        return false;
    }

    /**
     * Getting selected token, and record token information or not according to requirement.
     * 
     * @param text is the document text.
     * @param offset is the selected position.
     * @param needRecord represents whether need to record some token information after getting it.
     * @return current selected token, if it exists. Otherwise, return null.
     */
    private String getCurrentToken(String text, int offset, boolean needRecord)
    {
        String token = null;

        int len = text.length();

        if (offset >= 0 && offset <= len)
        {

            int begin = offset - 1;
            int end = offset;

            int[] begins =
            {
                0, 0
            };
            int[] ends =
            {
                len, len
            };

            int count = 1;

            while (begin >= 0 && count >= 0)
            {
                if (isDelimiter(text.charAt(begin)))
                {
                    begins[count] = begin + 1;
                    count--;
                }

                begin--;
            }

            count = 1;

            while (end < len && count >= 0)
            {
                if (isDelimiter(text.charAt(end)))
                {
                    ends[count ^ 1] = end;
                    count--;
                }

                end++;
            }

            String token0 = text.substring(begins[0], ends[0]);
            String token1 = text.substring(begins[1], ends[1]);

            if (_matchingPairs.isSupportedToken(token0) && !_matchingPairs.isSupportedToken(token1))
            {
                token = token0;

                if (needRecord)
                    _originalTokenEndOffset = ends[0];

            }
            else if (!_matchingPairs.isSupportedToken(token0) && _matchingPairs.isSupportedToken(token1))
            {
                token = token1;

                if (needRecord)
                    _originalTokenEndOffset = ends[1];
            }
            else if (!_matchingPairs.isSupportedToken(token0) && !_matchingPairs.isSupportedToken(token1))
            {
                if (begins[1] > ends[0])
                {
                    token = null;
                }
                else
                {
                    token = text.substring(begins[1], ends[0]);

                    if (_matchingPairs.isSupportedToken(token))
                    {
                        if (needRecord)
                            _originalTokenEndOffset = ends[0];
                    }
                    else
                    {
                        token = null;
                    }
                }
            }
            else
            {
                if (token0.length() >= token1.length())
                {
                    token = token0;

                    if (needRecord)
                        _originalTokenEndOffset = ends[0];
                }
                else
                {
                    token = token1;

                    if (needRecord)
                        _originalTokenEndOffset = ends[1];
                }
            }
        }

        if (token != null && needRecord)
            _originalTokenLength = token.length();

        return token;
    }
}
