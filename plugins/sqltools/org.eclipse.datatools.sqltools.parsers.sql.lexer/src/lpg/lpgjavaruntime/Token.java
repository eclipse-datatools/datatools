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

public class Token extends AbstractToken implements IToken
{
    public Token() {}
    public Token(int startOffset, int endOffset, int kind)
    {
        super(null, startOffset, endOffset, kind);
    }
    public Token(PrsStream prsStream, int startOffset, int endOffset, int kind)
    {
        super(prsStream, startOffset, endOffset, kind);
    }

    //
    // Return an iterator for the adjuncts that follow token i.
    //
    public IToken[] getFollowingAdjuncts()
    {
        return getPrsStream().getFollowingAdjuncts(getTokenIndex());
    }

    public IToken[] getPrecedingAdjuncts()
    {
        return getPrsStream().getPrecedingAdjuncts(getTokenIndex());
    }

}
