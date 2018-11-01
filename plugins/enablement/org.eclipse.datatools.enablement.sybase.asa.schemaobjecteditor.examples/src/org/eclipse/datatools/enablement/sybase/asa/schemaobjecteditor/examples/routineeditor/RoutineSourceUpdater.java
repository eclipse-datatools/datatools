/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.enablement.sybase.IGenericDdlConstants;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlConstants;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.Token;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTRoutine;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParam;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParamDefList;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTStart;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.datatools.sqltools.sql.updater.ProceduralObjectSourceUpdater;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;

/**
 * 
 * @author Hui Cao
 * 
 */
public class RoutineSourceUpdater extends ProceduralObjectSourceUpdater implements ISybaseDdlConstants, IGenericDdlConstants
{

    public static String RECOMPILE = "RECOMPILE";

    public RoutineSourceUpdater(SybaseRoutine _object, DatabaseDefinition dbDefinition)
    {
        super(_object, dbDefinition);
    }

    protected IASTRoutine getASTRoutine(ParsingResult result)
    {
        IASTStart root = result.getRootNode();
        for (int i = 0; i < root.jjtGetNumChildren(); i++)
        {
            Node node = root.jjtGetChild(i);
            if (node instanceof IASTRoutine)
            {
                return (IASTRoutine) node;
            }
        }
        return null;
    }

    /**
     * Updates the source by adding the parameter declaration at the specified index. This index might be different
     * with the index in Routines.getParameters() list because there might be invalid entries in the list.
     * @param param
     * @param index parameter index in the source
     * @return
     */
    public boolean updateParameterAdded(SybaseParameter param, String paramDef, int index)
    {
        SybaseRoutine routine = (SybaseRoutine)_routine;
        String body = routine.getSource().getBody();
        EList params = routine.getParameters();
        int insert = -1;
        ParsingResult result = _parser.parse(body, _parserParameters);
        IASTRoutine astProc = getASTRoutine(result);
        Node astName = astProc.getNameNode();
        IASTSQLParamDefList astParamList = astProc.getParameDefList();
        if (astParamList == null)
        {
            // no parameter: () or no: firsttoken: with or as; lasttoken: name or group number
            //TODO: empty ()
            Token next = astName.getLastToken().next;
            if (next.image.equals(LEFT_PARENTHESIS) && next.next.image.equals(RIGHT_PARENTHESIS) )
            {
                insert = _parser.getEndIndex(next);
            }
            else if (next.image.equals(SEMICOLUMN))
            {
                insert = _parser.getEndIndex(next.next);//skip the group number
            }
            else
            {
                insert = _parser.getEndIndex(astName.getLastToken());
            }
            body = body.substring(0, insert) + SPACE + paramDef + body.substring(insert);
        }
        else
        {
            Token first = ((Node) astParamList).getFirstToken();
            Token last = ((Node) astParamList).getLastToken();
            
            if (index == 0)
            {// first
                // more than one: firsttoken: @xxx or (
                if (first.image.equals("("))
                {
                    insert = _parser.getEndIndex(first);
                    body = body.substring(0, insert) + SPACE + paramDef + COMMA + body.substring(insert);
                }
                else
                {
                    insert = _parser.getStartIndex(first);
                    body = body.substring(0, insert) + paramDef + COMMA + SPACE + body.substring(insert);
                }
            }
            else if (index < astParamList.getParameterCount())
            {// center
                // getchildren
                IASTSQLParam astParam = astParamList.getParameter(index);
                if (astParam != null)
                {
                    Token next = ((Node) astParam).getFirstToken();
                    insert = _parser.getStartIndex(next);
                    body = body.substring(0, insert) + paramDef + COMMA + SPACE + body.substring(insert);
                }
            }
            else
            {// last
                // lasttoken:) or output or defaultxxx, type, or type(x)
                if (last.image.equals(")"))
                {
                    //last belong to the data type
                    if (last.next.image.equals(")") || !first.image.equals("("))
                    {
                        insert = _parser.getEndIndex(last);
                    }
                    else
                    {
                        insert = _parser.getStartIndex(last);
                    }
                    body = body.substring(0, insert) + COMMA + SPACE + paramDef + body.substring(insert);
                }
                else
                {
                    // no () at all
                    insert = _parser.getEndIndex(last);
                    body = body.substring(0, insert) + COMMA + SPACE + paramDef + body.substring(insert);
                }
            }
        }
        
        if (insert >= 0)
        {
            routine.getSource().setBody(body);
            return true;
        }
        return false;
    }

    /**
     * Updates the source by modifying the parameter declaration at the specified index. This index might be different
     * with the index in Routines.getParameters() list because there might be invalid entries in the list.
     * @param param
     * @param index parameter index in the source
     * @return
     */
    public boolean updateParameterModified(SybaseParameter param, String paramDef, int index)
    {
        SybaseRoutine routine = (SybaseRoutine)_routine;
        String body = routine.getSource().getBody();
        EList params = routine.getParameters();
        ParsingResult result = _parser.parse(body, _parserParameters);
        IASTRoutine astProc = getASTRoutine(result);
        //this node is generated only when there're parameters
        IASTSQLParamDefList astParamList = astProc.getParameDefList();
        if (astParamList == null)
        {
            return updateParameterAdded(param, paramDef, index);
        }
        IASTSQLParam astParam = astParamList.getParameter(index);
        if (astParam == null)
        {
            return updateParameterAdded(param, paramDef, index);
        }

        Token pFirst = ((Node) astParam).getFirstToken();
        Token pLast = ((Node) astParam).getLastToken();
        int start = _parser.getStartIndex(pFirst);
        int end = _parser.getEndIndex(pLast);
    
        if (start >= 0 && end >= start)
        {
            body = body.substring(0, start) + paramDef + body.substring(end);
            if (_testMode)
            {
            	result = _parser.parse(body, _parserParameters);
            	if (result.getExceptions().size() > 0)
            	{
            		return false;
            	}
            }
            routine.getSource().setBody(body);
            return true;
        }
        return false;
    }

    /**
     * 
     * @param index parameter index in the source
     * @return
     */
    public boolean updateParameterDeleted(int index)
    {
        SybaseRoutine routine = (SybaseRoutine)_routine;
        String body = routine.getSource().getBody();
        int start = -1;
        int end = -1;
        ParsingResult result = _parser.parse(body, _parserParameters);
        IASTRoutine astProc = getASTRoutine(result);
        IASTSQLParamDefList astParamList = astProc.getParameDefList();
        if (astParamList == null || astParamList.getParameterCount() <= index)
        {
            return false;
        }
        Token first = ((Node) astParamList).getFirstToken();
        Token last = ((Node) astParamList).getLastToken();
        if (astParamList.getParameterCount() == 1)
        {
            // only one, needs to remove ()
            start = _parser.getStartIndex(first);
            end = _parser.getEndIndex(last);
        }
        else
        {
            IASTSQLParam astParam = astParamList.getParameter(index);
            Token pFirst = ((Node) astParam).getFirstToken();
            Token pLast = ((Node) astParam).getLastToken();
            if (index == astParamList.getParameterCount() - 1)
            {
                // last one, remove the preceding comma
                IASTSQLParam astPrevParam = astParamList.getParameter(index - 1);
                Token prev = ((Node) astPrevParam).getLastToken();
                while (!(prev.image.equals(COMMA) || prev.kind == 0))
                {
                    prev = prev.next;
                }
                start = _parser.getStartIndex(prev);
                end = _parser.getEndIndex(pLast);
            }
            else
            {
                start = _parser.getStartIndex(pFirst);
                // pLast.next is comma
                end = _parser.getEndIndex(pLast.next);
            }
        }
    
        if (start >= 0 && end >= 0)
        {
            body = body.substring(0, start) + body.substring(end);
            if (_testMode)
            {
            	result = _parser.parse(body, _parserParameters);
            	if (result.getExceptions().size() > 0)
            	{
            		return false;
            	}
            }
            routine.getSource().setBody(body);
            return true;
        }
        return false;
    }

    /**
     * Updates the source when parameter at index and index + 1 are switched.
     * 
     * @param index parameter index in the source
     */
    public boolean updateParameterRearranged(int index)
    {
        SybaseRoutine routine = (SybaseRoutine)_routine;
        String body = routine.getSource().getBody();
    
        IDocument doc = new Document(body);
        ParsingResult result = _parser.parse(body, _parserParameters);
        result.getRootNode().setDocument(doc);
        IASTRoutine astProc = getASTRoutine(result);
        IASTSQLParamDefList astParamList = astProc.getParameDefList();
        if (index + 1 >= astParamList.getParameterCount())
        {
            return false;
        }
        IASTSQLParam nextP = astParamList.getParameter(index + 1);
        IASTSQLParam prevP = astParamList.getParameter(index);
        String nextDef = ((Node) nextP).getSQLText();
        String prevDef = ((Node) prevP).getSQLText();
        Token first = ((Node) nextP).getFirstToken();
        Token last = ((Node) nextP).getLastToken();
        int start = _parser.getStartIndex(first);
        int end = _parser.getEndIndex(last);
        body = body.substring(0, start) + prevDef + body.substring(end);
    
        first = ((Node) prevP).getFirstToken();
        last = ((Node) prevP).getLastToken();
        start = _parser.getStartIndex(first);
        end = _parser.getEndIndex(last);
        body = body.substring(0, start) + nextDef + body.substring(end);
    
        routine.getSource().setBody(body);
        return true;
    }

    public String getBody()
    {
        return ((SybaseRoutine)_routine).getSource().getBody();
    }

    public void setBody(String body)
    {
        ((SybaseRoutine)_routine).getSource().setBody(body);
    }

}