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

public class ErrorToken extends Token
{
    private IToken firstToken,
                   lastToken,
                   errorToken;

    public ErrorToken(IToken firstToken, IToken lastToken, IToken errorToken, int startOffset, int endOffset, int kind)
    {
        super(firstToken.getPrsStream(), startOffset, endOffset, kind);

        this.firstToken = firstToken;
        this.lastToken = lastToken;
        this.errorToken = errorToken;
    }

    /**
     * @deprecated replaced by {@link #getFirstRealToken()}
     *
     */
    public IToken getFirstToken() { return getFirstRealToken(); }
    public IToken getFirstRealToken()
    {
        return firstToken;
    }

    /**
     * @deprecated replaced by {@link #getLastRealToken()}
     *
     */
    public IToken getLastToken() { return getLastRealToken(); }
    public IToken getLastRealToken()
    {
        return lastToken;
    }
    
    public IToken getErrorToken()
    {
        return errorToken;
    }
    
    public IToken[] getPrecedingAdjuncts() { return firstToken.getPrecedingAdjuncts(); }
    public IToken[] getFollowingAdjuncts() { return lastToken.getFollowingAdjuncts(); }
}

