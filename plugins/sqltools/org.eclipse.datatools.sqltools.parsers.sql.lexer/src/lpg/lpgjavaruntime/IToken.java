/*******************************************************************************
 * Copyright (c) 2004, 2023 Eclipse contributors and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package lpg.lpgjavaruntime;

public interface IToken
{
    public static final char EOF = '\uffff';

    public int getKind();
    public void setKind(int kind);

    public int getStartOffset();
    public void setStartOffset(int startOffset);

    public int getEndOffset();
    public void setEndOffset(int endOffset);

    public int getTokenIndex();
    public void setTokenIndex(int i);
    
    public int getAdjunctIndex();
    public void setAdjunctIndex(int i);
    
    public IToken[] getPrecedingAdjuncts();
    public IToken[] getFollowingAdjuncts();

    public PrsStream getPrsStream();
    public int getLine();
    public int getColumn();
    public int getEndLine();
    public int getEndColumn();

    /**
     * @deprecated replaced by toString()
     */
    public abstract String getValue(char[] inputChars);

    public abstract String toString();
}
