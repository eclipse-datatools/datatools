/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.enablement.sybase.asa.ISybaseASADdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.ddl.SybaseASADdlBuilder;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.Token;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParamDefList;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.emf.common.util.EList;


/**
 * 
 * @author Hui Cao
 * 
 */
public class ASARoutineSourceUpdater extends RoutineSourceUpdater implements ISybaseASADdlConstants
{
    protected String _cachedParameters = null;
    
    public ASARoutineSourceUpdater(SybaseRoutine _object, DatabaseDefinition dbDefinition)
    {
        super(_object, dbDefinition);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Returns the on exception resume and deterministic characteristics
     * @return
     */
    protected String getCharacteristic()
    {
        return SybaseASADdlBuilder.getInstance().getCharacteristics((SybaseRoutine)_routine);
    }
    
    /**
     * Switches among user defined, sqlcode and sqlstate.
     * @param paramType
     */
    public void updateParameterType(ParameterType oldType, ParameterType newType)
    {
        if (oldType.equals(newType))
        {
            return;
        }
        SybaseRoutine routine = (SybaseRoutine)_routine;
        String body = routine.getSource().getBody();
        EList params = routine.getParameters();
        int start = -1;
        int end = -1;
        ParsingResult result = _parser.parse(body, _parserParameters);
        org.eclipse.datatools.sqltools.sql.parser.ast.IASTRoutine astProc = getASTRoutine(result);
        Node astName = astProc.getNameNode();
        IASTSQLParamDefList astParamList = astProc.getParameDefList();
        boolean hasParenthesis = false;
        if (astParamList == null)
        {
            Token next = astName.getLastToken().next;
            if (next.image.equals(LEFT_PARENTHESIS) && next.next.image.equals(RIGHT_PARENTHESIS) )
            {
                start = _parser.getEndIndex(next);
                end = start;
                hasParenthesis = true;
            }
            else
            {
                start = _parser.getStartIndex(next);//preceeding space
                end = start;
                hasParenthesis = false;
            }
        }
        else
        {
            Token first = ((Node) astParamList).getFirstToken();
            Token last = ((Node) astParamList).getLastToken();
            start = _parser.getStartIndex(first);
            end = _parser.getEndIndex(last);
            if (last.next.image.equals(RIGHT_PARENTHESIS))
            {
                hasParenthesis = true;
            }
        }
        
        if (newType.getValue() == ParameterType.VARIABLE)
        {
            //check the cache
            if (_cachedParameters != null)
            {
                if (hasParenthesis)
                {
                    body = body.substring(0, start) + _cachedParameters + body.substring(end);
                }
                else
                {
                    body = body.substring(0, start) + LEFT_PARENTHESIS + _cachedParameters + RIGHT_PARENTHESIS + NEWLINE + body.substring(end);
                }
            }
            else
            {
                body = body.substring(0, start) + body.substring(end);//remove parameters
            }
        }
        else 
        {
            if (oldType.getValue() == ParameterType.VARIABLE)
            {
                //keep the cache
                _cachedParameters = body.substring(start, end);
            }
            if (hasParenthesis)
            {
                body = body.substring(0, start) + newType.getLiteral() + body.substring(end);
            }
            else
            {
                body = body.substring(0, start) + LEFT_PARENTHESIS + newType.getLiteral() + RIGHT_PARENTHESIS + SPACE + body.substring(end);
            }
            
        }
        setBody(body);
    }
    
    public void init()
    {
        _cachedParameters = null;
    }

}
