/**
 * Created on Aug 22, 2008
 * 
 * Copyright (c) Sybase, Inc. 2004-2008. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.matching;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The class intend for generic SQL language matching token pairs.
 * 
 * @author juewu
 */
public class GenericSQLMatchingPairs extends AbstractMatchingPairs
{
    private static GenericSQLMatchingPairs INSTANCE;

    protected String                       _lc_begin             = "begin";
    protected String                       _uc_begin             = "BEGIN";
    protected String                       _lc_end               = "end";
    protected String                       _uc_end               = "END";

    protected String                       _beginMatchingPattern = "end|END";
    protected String                       _endMatchingPattern   = "begin|BEGIN";

    protected volatile boolean             _closureInitial       = false;

    public static GenericSQLMatchingPairs getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new GenericSQLMatchingPairs();
        }

        return INSTANCE;
    }

    protected GenericSQLMatchingPairs()
    {
        _pairs.put(_lc_begin, _beginMatchingPattern);
        _pairs.put(_uc_begin, _beginMatchingPattern);
        _pairs.put(_lc_end, _endMatchingPattern);
        _pairs.put(_uc_end, _endMatchingPattern);

        _leftSet.add(_lc_begin);
        _leftSet.add(_uc_begin);

        buildClosure();
    }

    public String getMatchingPatternClosure(String token)
    {
        if (_closureMap.containsKey(token))
        {
            return _closureMap.get(token).toString();
        }

        return null;
    }

    public boolean isLeftToken(String token)
    {
        if (_pairs.containsKey(token))
        {
            return super.isLeftToken(token);
        }
        else if (GeneralMatchingPairs.getInstance().isLeftToken(token))
        {
            return GeneralMatchingPairs.getInstance().isLeftToken(token);
        }

        return false;
    }

    public boolean isSupportedToken(String token)
    {
        if (_pairs.containsKey(token))
        {
            return true;
        }
        else if (GeneralMatchingPairs.getInstance().isSupportedToken(token))
        {
            return true;
        }

        return false;
    }

    /**
     * A recursive method to add a token and its related token closure into set.
     * 
     * @param closureSet is the related token closure set.
     * @param token is the token <code>String</code>.
     */
    private void addToClosure(Set closureSet, String token)
    {
        if (!closureSet.contains(token))
        {
            closureSet.add(token);

            String[] mts = _pairs.get(token).toString().split("\\|");

            for (int i = 0; i < mts.length; i++)
            {
                addToClosure(closureSet, mts[i]);
            }
        }
    }

    /**
     * Build related token closure for each token.
     */
    protected void buildClosure()
    {
        Map closureMap = new HashMap();

        Object[] keys = _pairs.keySet().toArray();

        // Set up the map which records every key and its related token set.
        for (int i = 0; i < keys.length; i++)
        {
            Set set = new HashSet();

            addToClosure(set, keys[i].toString());

            closureMap.put(keys[i], set);
        }

        for (int i = 0; i < keys.length; i++)
        {
            Object[] tks = ((Set) closureMap.get(keys[i])).toArray();

            StringBuffer sb = new StringBuffer();

            Arrays.sort(tks, new Comparator()
            {

                public int compare(Object o1, Object o2)
                {
                    if (o1.toString().length() == o2.toString().length())
                    {
                        return 0;
                    }

                    return o1.toString().length() < o2.toString().length() ? 1 : -1;
                }

            });

            for (int j = 0; j < tks.length; j++)
            {
                sb.append(tks[j].toString() + "|");
            }

            String s = sb.substring(0, sb.length() - 1);

            closureMap.put(keys[i], s);
        }

        _closureMap = closureMap;
    }
}
