/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.parser;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.datatools.sqltools.sql.parser.ast.IASTDeployable;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParam;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParamDefList;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLStatement;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTStart;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.datatools.sqltools.sql.parser.ast.SimpleNode;
import org.eclipse.jface.text.IDocument;

/**
 * Used to cache the parsing result of a sql text.
 * TODO: move the findXXX and getXXX methods to Visitor.
 * 
 * @author Hui Cao
 *  
 */
public abstract class ParsingResult
{

    protected IASTStart _rootNode;
    protected ArrayList _exceptions;

    /**
     *  
     */
    public ParsingResult(Node rootNode, ArrayList exceptions)
    {
        this._rootNode = (IASTStart) rootNode;
        this._exceptions = exceptions;
    }

    /**
     * Accumulates all the exceptions during the parsing process
     * 
     * @return Collections of ParseExceptions
     */
    public ArrayList getExceptions()
    {
        return _exceptions;
    }

    public IASTStart getRootNode()
    {
        return _rootNode;
    }

    /**
     * Returns all the cursor names that can be used at the position indicated by offset.
     * @param document
     * @param offset
     * @param rootNode
     * @return all the cursor names that can be used at the position indicated by offset
     */
    public ArrayList getCursorNames(IDocument document, int offset)
    {
        ArrayList cursors = new ArrayList();
        SimpleNode context = (SimpleNode) findContainingDeployable(document, offset, _rootNode);
        if (context != null)
        {
            cursors = findCursorNames(document, offset, context);
        }
        return cursors;
    }

    /**
     * Gets all the variable definitions indexed by their names that can be used at the position indicated by offset
     * 
     * @param document
     * @param offset
     * @return key: String; value: IASTSQLParam
     */
    public HashMap getVariables(IDocument document, int offset)
    {
        HashMap map = new HashMap();
        SimpleNode context = (SimpleNode) findContainingDeployable(document, offset, _rootNode);
        if (context != null)
        {
            map = findVariables(document, offset, context);
        }
        return map;
    }

    /**
     * Gets all the parameter definitions indexed by their names in the surrounding stored procedure
     * 
     * @param document
     * @param offset
     * @return key: String; value: IASTSQLParam
     */
    public HashMap getParameters(IDocument document, int offset)
    {
        HashMap map = new HashMap();
        SimpleNode context = (SimpleNode) findContainingDeployable(document, offset, _rootNode);
        //only SP can have parameters
        if (context != null && context instanceof IASTDeployable)
        {
            map = findParameters(document, offset, (IASTDeployable) context);
        }
        return map;
    }

    /**
     * Finds the immediate containing compound statement, or return null
     * 
     * @param document
     * @param offset
     * @return
     */
    public Node findParentCompound(IDocument document, int offset, Node rootNode)
    {
        SimpleNode node = (SimpleNode) findNode(document, offset, rootNode, true);
        if (node == null)
        {
            return null;
        }
        while (!(node instanceof IASTStart || node instanceof IASTDeployable || (node instanceof IASTSQLStatement && "BEGIN"
        .equalsIgnoreCase(node.getFirstToken().image))))
        {
            node = (SimpleNode) node.jjtGetParent();
        }

        return node;
    }

    /**
     * Finds the node that offset belongs to, or return null if none is found.
     * 
     * @param document
     * @param offset
     * @param rootNode the outmost node to search for
     * @param inclusive whether to include the trailing spaces
     * @return
     */
    public static Node findNode(IDocument document, int offset, Node rootNode, boolean inclusive)
    {
        if (!contains(document, offset, rootNode, inclusive)) 
        {
            return null; 
        }

        //now we are sure the root contains offset
        SimpleNode root = (SimpleNode) rootNode;
        for (int i = root.jjtGetNumChildren() - 1; i >= 0; i--)
        {
            SimpleNode node = (SimpleNode) root.jjtGetChild(i);
            if (contains(document, offset, node, inclusive))
            {
                return findNode(document, offset, node, inclusive);
            }
        }
        return root;
    }

    private static boolean contains(IDocument document, int offset, Node node, boolean inclusive)
    {
        SimpleNode simpleNode = (SimpleNode) node;
        if (offset > document.getLength()) 
        {
            return false; 
        }

        boolean in = simpleNode.getStartOffset(document) < offset ;
        if (inclusive)
        {
            in = in && simpleNode.getGreatestEndOffset(document) >= offset;
        }
        else
        {
            in = in && simpleNode.getEndOffset(document) >= offset;
        }
        return in;

    }

    /**
     * Finds the containing SP/event/function/trigger statement, or _rootNode if there're standalone statements before
     * offset, else return null
     * 
     * @param document
     * @param offset
     * @param start TODO
     * @return
     */
    public Node findContainingDeployable(IDocument document, int offset, IASTStart start)
    {
        SimpleNode root = (SimpleNode) start;
        if (root.jjtGetNumChildren() <= 0 || root.getStartOffset(document) >= offset) 
        {
            return null; 
        }
        IASTDeployable deployable = null;
        for (int i = root.jjtGetNumChildren() - 1; i >= 0; i--)
        {
            SimpleNode node = (SimpleNode) root.jjtGetChild(i);
            if (root.jjtGetChild(i) instanceof IASTDeployable)
            {
                if (node.getStartOffset(document) < offset)
                {
                    deployable = (IASTDeployable) node;
                    break;
                }
            }
            else
            {
                if (node.getStartOffset(document) <= offset)
                {
                    break;
                }
                else
                {
                    continue;
                }

            }
        }
        if (deployable == null)
        {
            if (!(root.jjtGetChild(0) instanceof IASTDeployable))
            {
                //template methods should loop through all the stand alone statements before any of the IASTDeployable
                // statements
                return root;
            }
        }
        else
        {
            return deployable;
        }
        return null;
    }

    /**
     * Subclass should override this template method instead of getCursorNames, based on specific SQL syntax.
     * getCursorNames will provide the right context node for this method.
     * 
     * @param document
     * @param offset
     * @param node the
     * @return
     */
    protected abstract ArrayList findCursorNames(IDocument document, int offset, SimpleNode node);

    /**
     * Subclass should override this template method instead of getVariables, based on specific SQL syntax. getVariables
     * will provide the right context node for this method. Subclass must be careful as to whether variables defined in
     * a compound statement can be referenced by statements following that compound statement. (e.g. ASE can while ASA
     * not)
     * 
     * @param document
     * @param offset
     * @param node the
     * @return
     */
    protected abstract HashMap findVariables(IDocument document, int offset, SimpleNode node);

    /**
     * Subclass should override this template method instead of getParameters, based on specific SQL syntax.
     * getParameters will provide the right context node for this method. This default implementation assumes that
     * parameter declare statement is represented by IASTSQLParamDefList and IASTSQLParam nodes.
     * 
     * @param document
     * @param offset
     * @param node the
     * @return
     */
    protected HashMap findParameters(IDocument document, int offset, IASTDeployable node)
    {
        HashMap map = new HashMap();
        if (node.jjtGetNumChildren() > 0)
        {
            for (int i = 0; i < node.jjtGetNumChildren(); i++)
            {
                SimpleNode child = (SimpleNode) node.jjtGetChild(i);
                if (child instanceof IASTSQLParamDefList)
                {
                    for (int j = 0; j < child.jjtGetNumChildren(); j++)
                    {
                        Node param = child.jjtGetChild(j);
                        if (param instanceof IASTSQLParam)
                        {
                            map.put(((IASTSQLParam)param).getName(), param);
                        }
                    }
                    break;
                }
                continue;
            }
        }
        return map;
    }

    /**
     * Subclass should override this template method , based on specific SQL syntax. findCusross
     * will provide the right context node for this method. Subclass must be careful as to whether variables defined in
     * a compound statement can be referenced by statements following that compound statement. (e.g. ASE can while ASA
     * not)
     * 
     * @param document
     * @param offset
     * @param node the
     * @return
     */
    protected abstract HashMap findCursors(IDocument document, int offset, SimpleNode node);

    /**
     * Gets all the parameter definitions indexed by their names in the surrounding stored procedure
     * 
     * @param document
     * @param offset
     * @return key: String; value: IASTSQLParam
     */
    public HashMap getCursors(IDocument document, int offset)
    {
        HashMap map = new HashMap();
        SimpleNode context = (SimpleNode) findContainingDeployable(document, offset, _rootNode);
        // only SP can have parameters
        if (context != null && context instanceof IASTDeployable)
        {
            map = findCursors(document, offset, context);
        }
        return map;
    }

    /**
     * Checks whether the token list represents a valiad database object.
     * 
     * @param tokenLists
     * @return
     */
    public abstract boolean isValidObjectsFormats(ArrayList tokenLists);

    /**
     * Checks whether the token list is variable or parameter.
     * 
     * @param tokenLists
     * @return
     */
    public abstract boolean isVarOrPara(ArrayList tokenLists);

    /**
     * Gets prefix name of result.
     * 
     * @param tokenLists
     * @param node
     * @return
     */
    public abstract ArrayList getPrefix(ArrayList tokenLists, Node node);

    /**
     * Gets non-sharable temp tables referenced in sp/trigger which have not been defined
     * explicitly in sp/trigger,not in annotation.
     * @param document
     * @param offset
     * @return
     */
    public abstract ArrayList getUnSharableTempTables(IDocument document, int offset);
}
