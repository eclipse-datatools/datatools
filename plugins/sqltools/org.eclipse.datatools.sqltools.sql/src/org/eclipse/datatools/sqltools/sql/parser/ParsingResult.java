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
package org.eclipse.datatools.sqltools.sql.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.sqltools.sql.parser.ast.IASTDeployable;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParam;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParamDefList;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLStatement;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTStart;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.datatools.sqltools.sql.parser.ast.SimpleNode;
import org.eclipse.datatools.sqltools.sql.reference.internal.Table;
import org.eclipse.datatools.sqltools.sql.util.ConsistentOrderHashMap;
import org.eclipse.datatools.sqltools.sql.util.SQLUtil;
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
    protected int fScope;

    /**
	 * Expected token list can be retrieved from ParseException, while expected
	 * unreserved keywords should be retrieved from this list, which is
	 * populated by the parser. 
	 */
    protected List              _expectedUnreservedKeywords  = new ArrayList();
    /**
     * _currentTableNames contains all table objects for content assist.
     */
    private List             _currentTableNames = new ArrayList();
	private HashMap fEntries = new HashMap();
    
    /**
     *  
     */
    public ParsingResult()
    {
    }
    
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
    /**
     * Accumulates all the exceptions during the parsing process
     * 
     * @return Collections of ParseExceptions
     */
    public void setExceptions(ArrayList exceptions)
    {
    	_exceptions = exceptions;
    }
    
    public void setRootNode(Node rootNode)
    {
    	_rootNode = (IASTStart) rootNode;
    }
    
    
    /**
     * Returns the expected unreserved keywords, used in content assist.
     * @return String list
     */
    public List getExpectedUnreservedKeywords()
    {
    	return Collections.unmodifiableList(_expectedUnreservedKeywords);
    }
    
    
    /**
     * Adds an unreserved keyword if it doesn't exist in the list.
     * @return String list
     */
    public void addExpectedUnreservedKeywords(String unReservedKeyword)
    {
    	if (_expectedUnreservedKeywords == null)
    	{
    		_expectedUnreservedKeywords = new ArrayList();
    	}
    	if(!_expectedUnreservedKeywords.contains(unReservedKeyword))
    	{
    		_expectedUnreservedKeywords.add(unReservedKeyword);
    		
    	}
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
        HashMap map = new ConsistentOrderHashMap();
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
        HashMap map = new ConsistentOrderHashMap();
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
	 * Gets the current scope at the position where content assist is invoked.
	 * This is used by content assist processor to determine what database meta
	 * info should be retrieved.
	 * 
	 * @see SQLParserConstants
	 * @return scope constants defined in <code>SQLParserConstants</code>
	 */
    public int getScope()
    {
        return fScope;
    }


	/**
	 * Sets the current scope
	 */
    public void setScope(int scope)
    {
        fScope = scope;
    }
    
    /**
     * Return the current table list.
     * @return List the current table list.
     */
    public List getCurrentTables()
    {
        return _currentTableNames;
    }

    /**
     * Add a new table object into the current table list.
     * The new table object will not be added when isContentAssist is true for avoiding to be done repeatedly. 
     * @param simpleNode the node's text which is the table name.
     * @param token the token's image which is the alias name of table.
     * @param isContentAssist true for content assist.
     * TODO revisit the necessity of isContentAssist
     * 
     */
    public void addCurrentTables(Node simpleNode, Token token, boolean isContentAssist)
    {
        if (!isContentAssist)
        {
            return;
        }
        if (simpleNode instanceof SimpleNode)
        {
            String tableName = ((SimpleNode) simpleNode).getText();
            String aliasName = null;
            if (token != null && token.image != null)
            {
                aliasName = token.image;
            }
            addCurrentTables(tableName, aliasName, isContentAssist);
        }
    }

    /**
     * Add a new table object into the current table list.
     * The new table object will not be added when isContentAssist is true for avoiding to be done repeatedly. 
     * @param tableName the table name.
     * @param aliasName the alias name of the table.
     * @param isContentAssist true for content assist.
     */
    public void addCurrentTables(String tableName, String aliasName, boolean isContentAssist)
    {
        if (!isContentAssist)
        {
            return;
        }


        String[] objects = SQLUtil.splitDotStr(tableName);
        int length = objects.length;
        String dbname = null;
        String owner = null;
        String name = null;
        switch (length)
        {
            case 1:
                name = trim(objects[0]);
                break;
            case 2:
                owner = trim(objects[0]);
                name = trim(objects[1]);
                break;
            case 3:
                dbname= trim(objects[0]);
                owner = trim(objects[1]);
                name = trim(objects[2]);
            default:
                break;
        }

        //Remove duplicate table.
        if (_currentTableNames != null)
        {
            for (int i = 0; i < _currentTableNames.size(); i++)
            {
                Table table1 = (Table) _currentTableNames.get(i);
                if (owner != null && owner.equals(table1.getOwner()))
                {
                    if (name != null && name.equals(table1.getName()))
                    {
                        table1.setAliasName(aliasName);
                        return;
                    }
                }
                else if (owner == null)
                {
                    if (name != null && name.equals(table1.getName()))
                    {
                        table1.setAliasName(aliasName);
                        return;
                    }
                }

            }
        }


        Table table = new Table();

        if (name != null)
        {
            table.setName(name);
        }
        if (owner != null)
        {
            table.setOwner(owner);
        }
        table.setAliasName(aliasName);
        if (!_currentTableNames.contains(table))
        {
            _currentTableNames.add(table);
        }

    }

    private String trim(String str)
    {
        if (str != null)
        {
            return str.trim();
        }
        return null;
    }
    /**
     * Clear the content of the current table list.
     * If isContentAssist is true, just return.
     * @param isContentAssist true for content assist.
     */
    public void clearCurrentTableNames(boolean isContentAssist)
    {
        if (!isContentAssist)
        {
            return;
        }
        if (_currentTableNames != null && !_currentTableNames.isEmpty())
        {
            _currentTableNames.clear();
        }
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

    /**
	 * This methoe used to find out all referenced tables in sp/trigger.
	 * So far, this method can only support DML statements as "select", 
	 * "insert","update","delete","create table","declare cursor" and "truncate table"
	 */
    protected void findReferencedTables(SimpleNode node, ArrayList refTables) 
    {
        if (node instanceof IASTSQLStatement)
        {
            int nodeType = ((IASTSQLStatement) node).getType(); 
            switch(nodeType)
            {
                //for "create" statement
                case SQLParserConstants.TYPE_SQL_CREATE_TABLE:
                    {
                        Token token = node.getFirstToken();
                        if ("create".equalsIgnoreCase(token.image))
                        {
                            token = token.next;
                            if ("table".equalsIgnoreCase(token.image))
                            {
                                token = token.next;
                                String tableName = token.image;
                                while(token.next.image=="."||token.image==".")
                                {
                                    token = token.next;      
                                    tableName = tableName+token.image;
                                }
                                if (!refTables.contains(tableName))
                                {
                                    refTables.add(tableName);
                                }
                            }
                        }
                        break;
                    }
                    //for "truncate" statement
                case SQLParserConstants.TYPE_SQL_OTHERS:
                    {
                        Token token = node.getFirstToken();
                        if ("truncate".equalsIgnoreCase(token.image))
                        {
                            token = token.next;
                            if ("table".equalsIgnoreCase(token.image))
                            {
                                token = token.next;
                                String tableName = token.image;
                                while(token.next.image=="."||token.image==".")
                                {
                                    token = token.next;      
                                    tableName = tableName+token.image;
                                }
                                if (!refTables.contains(tableName))
                                {
                                    refTables.add(tableName);
                                }
                            }
                        }
                        break;
                    }
                    //for other DML statement
                default:
                    {
                        Collection params = ((IASTSQLStatement)node).getObjectIdentifiers();
                        if (params != null && params.size() > 0)
                        {
                            for (Iterator iter = params.iterator(); iter.hasNext();)
                            {
                                String name = (String) iter.next();
                                if (!refTables.contains(name))
                                {
                                    refTables.add(name);
                                }
                            }
                        }
                    }
            }
        }
        for (int i = 0; i < node.jjtGetNumChildren(); i++)
        {
            findReferencedTables((SimpleNode) node.jjtGetChild(i), refTables);
        }
    }

    public ArrayList getReferencedTables() 
    {
        ArrayList tables = new ArrayList();
        SimpleNode context = (SimpleNode) _rootNode;
        if (context != null)
        {
            findReferencedTables(context, tables);
        }
        return tables;
    }
    
    public void addEntry(Object t, String type) 
    {
        List objects = (ArrayList) fEntries.get(type);
        if (objects == null)
        {
            objects = new ArrayList();
        }
        if (!objects.contains(t))
        {
        	objects.add(t);
        }
        fEntries .put(type,objects);
    }
    /**
     * 
     * @param type defined in SQLParserConstants
     * @return
     */
    public String[] getEntries(String type)
    {
        String[] items = null;
        List objects = (ArrayList) fEntries.get(type);
        
        if (objects == null)
        {
            return new String[0];
        }
        
        items = new String[objects.size()];
        for(int i=0;i<objects.size();i++)
        {
            Object current = objects.get(i);
            if (current instanceof Token)
            {
                items[i] = ((Token) current).image;
                if (items[i].startsWith("@"))
                {
                    items[i] = items[i].substring(1);//remove the @ from the names
                }
            }
            else
            {
                items[i] = (String)current;//tables,columns are saved as strings.
            }
        }

        return items != null ?items:new String[0];
    }

}
