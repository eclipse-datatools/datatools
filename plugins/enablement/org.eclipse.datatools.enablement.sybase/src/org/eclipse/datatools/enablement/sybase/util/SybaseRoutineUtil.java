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
package org.eclipse.datatools.enablement.sybase.util;

import java.util.Iterator;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.parser.QuickSQLParser;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.emf.common.util.EList;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SybaseRoutineUtil
{
    public static void parseParameterDefaultValues(Routine routine, EList params)
    {
        if (routine == null || params == null)
        {
            return;
        }
        
        String body = null;
        if (routine.getSource() != null)
        {
            // To fix CR487693-1
            // Since "\t" is considered as 4 white spaces by parser, but it is only a character in body string.
            // When pick out default value using the offset returned by parser, the result will be incorrect.
            // Now replace "\t" with a white space to avoid the differences.
            body = routine.getSource().getBody().replaceAll("\t", " ");
        }
        if (body != null)
        {
            QuickSQLParser parser = QuickSQLParser.getInstance();
            //Function parameter doesn't support default value
//            boolean match = parser.match(body, QuickSQLParser.CREATE_PROC_HEADER_PATTERN);
            String[][] paramDefaults = parser.getParameters(body);
            if (paramDefaults != null)
            {
                for (int i = 0; i < paramDefaults.length; i++)
                {
                    String name = paramDefaults[i][0];
                    String defValue = paramDefaults[i][1];
                    for (Iterator iter = params.iterator(); iter.hasNext();)
                    {
                        Parameter param = (Parameter) iter.next();
                        if (param instanceof SybaseParameter)
                        {
                            if (param.getName().equals(name)){
                                boolean deliver = param.eDeliver();
                                param.eSetDeliver(false);
                                ((SybaseParameter)param).setDefaultValue(defValue);
                                param.eSetDeliver(deliver);
                                break;
                            }
                        }
                    }
                }
            }
        }

    }
}
