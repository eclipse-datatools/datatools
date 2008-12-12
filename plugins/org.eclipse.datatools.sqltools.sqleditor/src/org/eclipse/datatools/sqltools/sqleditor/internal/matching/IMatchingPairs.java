/**
 * Created on Jul 30, 2008
 * 
 * Copyright (c) Sybase, Inc. 2004-2008. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.matching;

/**
 * The matching pattern (mapping rules) of specific language.
 * 
 * @author juewu
 */
public interface IMatchingPairs
{
    /**
     * According to the given token retrieving the matching pattern.
     * 
     * @param token is the one to be matched.
     * @return If there is no such token supported, return null. Otherwise, return matching pattern.
     */
    public String getMatchingPattern(String token);

    /**
     * Determine whether the token is supported.
     * 
     * @param token a <code>String</code> represents token.
     * @return If supported, return true. Otherwise, return false.
     */
    public boolean isSupportedToken(String token);

    /**
     * Determine whether the token is left token.
     * 
     * @param token a <code>String</code> represents token.
     * @return If the token is left token, return true. Otherwise, return false.
     */
    public boolean isLeftToken(String token);

    /**
     * Getting the related token pattern closure for a specific token. When the matching token of this given token is
     * being searching, only the tokens which match the pattern in related token pattern closure will be focused on.
     * 
     * @param token is the specific token whose related token pattern is needed.
     * @return the regular expression presents the related tokens of this specific token.
     */
    public String getMatchingPatternClosure(String token);
}
