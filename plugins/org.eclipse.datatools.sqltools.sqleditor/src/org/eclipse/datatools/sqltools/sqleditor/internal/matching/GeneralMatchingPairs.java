/**
 * Created on Jul 30, 2008
 * 
 * Copyright (c) Sybase, Inc. 2004-2008. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.matching;


/**
 * The general matching rule just supports brackets.
 * 
 * @author juewu
 */
public class GeneralMatchingPairs extends AbstractMatchingPairs
{
    private static GeneralMatchingPairs INSTANCE;

    protected static String             _left_bracket         = "(";
    protected static String             _right_bracket        = ")";
    protected static String             _left_square_bracket  = "[";
    protected static String             _right_square_bracket = "]";

    public static GeneralMatchingPairs getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new GeneralMatchingPairs();
        }

        return INSTANCE;
    }

    protected GeneralMatchingPairs()
    {
        super();
        
        _pairs.put(_left_bracket, _right_bracket);
        _pairs.put(_right_bracket, _left_bracket);
        _pairs.put(_left_square_bracket, _right_square_bracket);
        _pairs.put(_right_square_bracket, _left_square_bracket);

        _leftSet.add(_left_bracket);
        _leftSet.add(_left_square_bracket);
    }
}
