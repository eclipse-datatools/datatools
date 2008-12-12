/**
 * Created on Aug 1, 2008
 * 
 * Copyright (c) Sybase, Inc. 2004-2008. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.matching;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The abstract class for matching token pairs.
 * 
 * @author juewu
 */
public abstract class AbstractMatchingPairs implements IMatchingPairs
{
    protected Map _pairs      = new HashMap();
    protected Set _leftSet    = new HashSet();
    protected Map _closureMap = new HashMap();

    public String getMatchingPattern(String token)
    {
        if (!_pairs.containsKey(token))
        {
            return null;
        }

        return _pairs.get(token).toString();
    }

    public boolean isSupportedToken(String token)
    {
        if (_pairs.containsKey(token))
        {
            return true;
        }

        return false;
    }

    public boolean isLeftToken(String token)
    {
        return _leftSet.contains(token);
    }

    public String getMatchingPatternClosure(String token)
    {
        return null;
    }
}
