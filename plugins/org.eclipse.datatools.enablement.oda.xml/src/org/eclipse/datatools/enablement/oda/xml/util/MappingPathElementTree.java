/*******************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * A tree constructed by table mapping path and its column mapping paths.
 * One path element is corresponding with a <code>TreeNode</code>.
 * This tree is mainly used to accelerate the following operations:
 * 1. Whether a path generated during xml file parsing matches table mapping path, 
 * 	  see matchesTablePath method
 * 2. What columns a path generated during xml file parsing can match
 *    see getMatchedColumnIndex method
 * 3. For a table path "tp", given a not nested column path "cp", what columns cp can match
 *    see getMatchedButNotNestedColumnIndexes method  
 * 4. For a table path "tp", given a nested column path "cp", Whether cp is a valid nested column for tp
 *    see isValidNestedColumn method
 * 
 * Notes: A nested column, generally contains "..", means this column cannot reachable just down from table path
 * 
 */
public class MappingPathElementTree
{
	private static final String DOUBLE_SLASH_REPLACEMENT = "<>";

	// the TreeNode corresponding with the first path element in the mapping
	// path of the table
	private TreeNode firstTreeNodeForTablePath;

	// the TreeNode corresponding with the last path element in the mapping path
	// of the table
	private TreeNode lastTreeNodeForTablePath;

	// corresponding with the root of xml "/"
	private ChildrenAllowedTreeNode root;

	//<index, NestedColumn> map, save the nested columns info
	private Map indexNestedColumnMap = new HashMap( );

	/**
	 * @param tablePath:
	 *            the mapping path of the table, the result of filter condition removing and needless parent axis removing
	 * @param relativeColumnPaths:
	 *            the relative column paths, the result of filter condition removing and needless parent axis removing
	 */
	public MappingPathElementTree( String tablePath,
			String[] relativeColumnPaths )
	{
		//the validation of tablePath should be checked outside
		assert isValidTableMappingPath(tablePath);
		
		initFromTablePath( tablePath );

		for ( int i = 0; i < relativeColumnPaths.length; i++ )
		{
			String relativePath = relativeColumnPaths[i];
			
			if (!isValidColumnMappingPath(relativePath))
			{
				continue;
			}
			
			if ( relativePath.equals( "" ) ) // column path is the same with table path
			{
				lastTreeNodeForTablePath.addColumnIndex( i );
			}
			else if ( relativePath.startsWith( "../" ) || relativePath.equals( ".." )) 
			// A nested column, unaccessible down from the table path
			{
				int doubleDotCount = 0;
				String[] splits = relativePath.split( "/" );
				for (int j = 0; j < splits.length; j++)
				{
					if (splits[j].equals( ".." ))
					{
						doubleDotCount++;
					}
				}
				
				Set ancestors = getPossibleAncestors( lastTreeNodeForTablePath,
						doubleDotCount );
				
				// remove all the prefix ../..
				String path = relativePath.replaceFirst( "\\Q..\\E(\\Q/..\\E)*",
						"" );
				indexNestedColumnMap.put( new Integer( i ),
						new NestedColumn( doubleDotCount, path ) );

				Iterator itr = ancestors.iterator( );
				while ( itr.hasNext( ) )
				{
					ChildrenAllowedTreeNode ancestor = (ChildrenAllowedTreeNode) itr.next( );
					addColumnPath( ancestor, path, i );
				}
			}
			else
			{
				//lastTreeNodeForTablePath can be AttrNode
				//For example, tableMapPath: /BOOK/@color; a columnMapPath: ../Author 
				if ( lastTreeNodeForTablePath instanceof ChildrenAllowedTreeNode )
				{
					addColumnPath( (ChildrenAllowedTreeNode) lastTreeNodeForTablePath,
							relativePath,
							i );
				}
			}
		}
	}

	/**
	 * whether this absolutePath matches the table mapping path
	 * 
	 * @param absolutePath:
	 *            the path generated during xml file parsing
	 */
	public boolean matchesTablePath( String absolutePath )
	{
		Set nodes = getPossibleEndNodes( absolutePath );
		Iterator itr = nodes.iterator( );
		while ( itr.hasNext( ) )
		{
			TreeNode node = (TreeNode) itr.next( );
			if ( node == lastTreeNodeForTablePath )
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param absolutePath:
	 *            the path generated during xml file parsing
	 * @return the possible tree nodes which are corresponding with the last
	 *         path element of absolutePath
	 */
	private Set getPossibleEndNodes( String absolutePath )
	{
		String[] splits = absolutePath.split( "/" );
		Set result = new HashSet( );
		if ( splits.length == 0 )
		{
			result.add( root );
		}
		else
		{
			// Attention: splits of "/A/B" be [][A][B]
			// So, the first empty string should be ignored
			Iterator children = root.getMatchedChildren( splits[1] ).iterator( );
			while ( children.hasNext( ) )
			{
				TreeNode child = (TreeNode) children.next( );
				result.addAll( getPossibleEndNodes( splits, 1, child ) );
			}
		}
		return result;
	}


	/**
	 * traverse down from fromNode according to pathElements  
	 * @param pathElements: a sequence of path elements generated during xml file parsing
	 *        fromIndex: the index of beginning path element 
	 *        fromNode:  the beginning node 
	 * @return all the destination nodes which is corresponding with last element of pathElements
	 */
	private Set getPossibleEndNodes( String[] pathElements, int fromIndex, TreeNode fromNode )
	{
		assert pathElements != null && fromIndex > -1 && fromNode != null;
		if ( fromIndex >= pathElements.length)
		{
			return Collections.EMPTY_SET;
		}

		if ( fromNode.matches( pathElements[fromIndex] ) )
		{
			//reaches the last path element
			if ( fromIndex == pathElements.length - 1 )
			{
				Set result = new HashSet( );
				result.add( fromNode );

				if ( fromNode instanceof AnyElementPlaceholderNode )
				{
					// AnyElementPlaceholderNode may represent nothing
					Set children = fromNode.getMatchedChildren( pathElements[fromIndex] );
					result.addAll( children );
				}

				TreeNode node = fromNode.getChildByName( AnyElementPlaceholderNode.ANY_ELEMENT_HOLDER );
				if (node != null)
				{
					//fromNode contains a AnyElementPlaceholderNode child
					result.add( node );
				}

				return result;
			}
			else
			// pathElements.length > 1
			{
				if ( fromNode instanceof AnyElementPlaceholderNode )
				{
					Set result = new HashSet( );

					// fromNode may represent 0 or 1, 2, .... (pathElements.length - fromIndex - 1) elements
					for ( int i = 0; i <= pathElements.length - fromIndex - 1; i++ )
					{
						String firstElement = pathElements[fromIndex + i];
						Set children = fromNode.getMatchedChildren( firstElement );
						Iterator itr = children.iterator( );
						while (itr.hasNext( ))
						{
							TreeNode child = (TreeNode) itr.next( );
							result.addAll( getPossibleEndNodes( pathElements, fromIndex + i,
									child ) );
						}
					}

					// fromNode represents pathElements.length - fromIndex elements
					if ( fromNode.matches( pathElements[pathElements.length - 1] ) )
					{
						result.add( fromNode );
					}
					return result;
				}
				else if ( !fromNode.hasChild( ) )
				{
					// fromNode has no child but there are still path elements
					// left
					return Collections.EMPTY_SET;
				}
				else
				{
					// begin to compare next level
					Set result = new HashSet( );
					Set children = fromNode.getMatchedChildren( pathElements[fromIndex + 1] );
					Iterator itr = children.iterator( );
					while ( itr.hasNext( ) )
					{
						TreeNode child = (TreeNode) itr.next( );
						result.addAll( getPossibleEndNodes( pathElements, fromIndex + 1,
								child ) );
					}
					return result;
				}
			}
		}
		else
		{
			// first level mismatch, needless to continue
			return Collections.EMPTY_SET;
		}
	}
	
	/**
	 * return all the possible matched column indexes for columnAbsolutePath.
	 * ignore the absolute table path
	 * @param columnAbsolutePath:
	 *            the path generated during xml file parsing
	 * @return
	 */
	public int[] getMatchedColumnIndexs( String columnAbsolutePath )
	{
		Set nodes = getPossibleEndNodes( columnAbsolutePath );
		return getAllColumnIndexes(nodes);
	}
	
	/**
	 * 
	 * @param nodes a set of TreeNode
	 * @return all the column indexes
	 */
	private int[] getAllColumnIndexes(Set nodes)
	{
		Set indexes = new HashSet( );
		Iterator itr = nodes.iterator( );
		while ( itr.hasNext( ) )
		{
			TreeNode node = (TreeNode) itr.next( );
			indexes.addAll( node.getColumnIndexes( ) );
		}

		int[] result = new int[indexes.size( )];
		itr = indexes.iterator( );
		int i = 0;
		while ( itr.hasNext( ) )
		{
			int index = ( (Integer) itr.next( ) ).intValue( );
			result[i++] = index;
		}
		return result;		
	}

	/**
	 * return all the indexes of matched columns which path is
	 * columnAbsolutePath and is reachable down from tableAbsolutPath
	 * 
	 * @param columnAbsolutePath:
	 *            the path generated during xml file parsing
	 * @param tableAbsolutPath:
	 *            the path generated during xml file parsing and matches table mapping path
	 * @return
	 */
	public int[] getMatchedButNotNestedColumnIndexs( String columnAbsolutePath,
			String tableAbsolutePath )
	{
		//return new int[]{2};
		assert columnAbsolutePath != null && tableAbsolutePath != null;

		// columnAbsolutePath should be accessible down form tableAbsolutePath
		if ( !columnAbsolutePath.startsWith( tableAbsolutePath ) )
		{
			return new int[0];
		}

		String pureRelativePath = "";
		if ( !columnAbsolutePath.equals( tableAbsolutePath ) )
		{
			pureRelativePath = columnAbsolutePath.substring( tableAbsolutePath.length( ) );
		}

		String[] splits = pureRelativePath.split( "/" );
		Set columnEndNodes = new HashSet( );
		if ( splits.length == 1 ) // columnAbsolutePath.equals(tableAbsolutePath )
		{
			columnEndNodes.add( lastTreeNodeForTablePath );
			TreeNode node = lastTreeNodeForTablePath.getChildByName(AnyElementPlaceholderNode.ANY_ELEMENT_HOLDER);
			if (node != null)
			{
				columnEndNodes.add( node );
			}
		}
		else
		{
			// Attention: splits of "/A/B" be [][A][B]
			// So, the first empty string should be ignored
			Iterator children = lastTreeNodeForTablePath.getMatchedChildren( splits[1] ).iterator( );
			while ( children.hasNext( ) )
			{
				TreeNode child = (TreeNode) children.next( );
				columnEndNodes.addAll( getPossibleEndNodes( splits, 1, child ) );
			}
		}
		return getAllColumnIndexes(columnEndNodes);
	}

	/**
	 * whether the column is a nested column
	 * 
	 * @param index
	 * @return
	 */
	public boolean isNestedColumn( int index )
	{
		return indexNestedColumnMap.containsKey( new Integer( index ) );
	}
	
	/** 
	 * @return
	 */
	public int[] getNestedColumnIndexes()
	{
		Set indexes = indexNestedColumnMap.keySet( );
		int[] result = new int[indexes.size( )];
		Iterator itr = indexes.iterator( );
		int i = 0;
		while (itr.hasNext( ))
		{
			result[i++] = ((Integer)itr.next( )).intValue( );
		}
		return result;
	}

	/**
	 * whether columnPath is table's nested column, which index is
	 * <code>index</code>
	 * 
	 * @param index
	 * @param tablePath: generated during xml file parsing and matches table mapping path
	 * @param columnPath: generated during xml file parsing
	 * @return
	 */
	public boolean isValidNestedColumn( int index, String tablePath,
			String columnPath )
	{
		NestedColumn nc = (NestedColumn) ( indexNestedColumnMap.get( new Integer( index ) ) );
		if ( nc == null )
		{
			return false;
		}
		String[] tableSplits = tablePath.split( "/" );
		String[] columnSplits = columnPath.split( "/" );

		if ( tableSplits.length == 0 )
		{
			// tablePath is just "/"
			return false;
		}

		int doubleDotCount = nc.getDoubleDotCount( );
		// Attention: splits of "/A/B" be [][A][B]
		if ( tableSplits.length - 1 < doubleDotCount )
		{
			return false;
		}
		if ( columnSplits.length < tableSplits.length - doubleDotCount )
		{
			return false;
		}

		StringBuffer ancestorPath = new StringBuffer( "" );
		for ( int i = 1; i < tableSplits.length - doubleDotCount; i++ )
		{
			if ( !tableSplits[i].equals( columnSplits[i] ) )
			{
				return false;
			}
			ancestorPath.append( "/" ).append( tableSplits[i] );
		}
		
		//just up, no down
		if (columnPath.equals( ancestorPath.toString( ) ))
		{
			return nc.getPurePath( ).equals( "" );
		}
		
		String relativePath = columnPath.substring( ancestorPath.length( ) );
		
		String pureMappingPath = nc.getPurePath( );
		if (pureMappingPath.equals( "" ))
		{
			//mapping just up, no down
			return false;
		}
		
		//compare the down part of mapping path with the down part of real path
		MappingPathElementTree tree = new MappingPathElementTree( pureMappingPath, new String[0]);
		return tree.matchesTablePath( relativePath );
	}

	/**
	 * @param node
	 * @param upLevel:
	 *            the parent level is 1, the parent of parent is 2...
	 * @return the possible ancestor, which level is upLevel.
	 */
	private Set getPossibleAncestors( TreeNode node, int upLevel )
	{
		if ( node == null || upLevel < 1 )
		{
			return Collections.EMPTY_SET;
		}
		if ( upLevel == 1 )
		{
			if ( node == root )
			{
				return Collections.EMPTY_SET;
			}
			else if ( node instanceof AnyElementPlaceholderNode )
			{
				Set result = new HashSet( );

				// node may represent more than 2 path elements
				result.add( node );

				// node may represent just one path element
				result.add( node.getParent( ) );

				// node may represent none path element
				if ( node.getParent( ).getParent( ) != null )
				{
					result.add( node.getParent( ).getParent( ) );
				}

				return result;
			}
			else
			{
				Set result = new HashSet( );
				result.add( node.getParent( ) );
				if ( node.getParent( ) instanceof AnyElementPlaceholderNode )
				{
					result.add( node.getParent( ).getParent( ) );
				}
				return result;
			}
		}
		else
		{
			//just recurse
			return getPossibleAncestors( node.getParent( ), upLevel - 1 );
		}
	}

	/**
	 * Construct the tree according to the mapping path of the table
	 * 
	 * @param tablePath
	 * @return
	 */
	private void initFromTablePath( String tablePath )
	{
		// used to differentiate "//" and "/"
		tablePath = tablePath.replaceAll( "//", "/"+ DOUBLE_SLASH_REPLACEMENT + "/" );

		String[] splits = tablePath.split( "/" );

		root = new ElementNode( "/" );

		if ( splits.length == 0 )
		{
			//tablePath can not be "/", so never go here
			assert false;
		}
		// Attention: splits of "/A/B" be [][A][B]
		// So, the first empty string should be ignored
		else if ( !isAnyElementMapping(splits[1]))
		{
			// table path is such as /A/....
			firstTreeNodeForTablePath = root.addChild( splits[1] );
		}
		else
		{
			// table path is a relative path which begins with "//"
			firstTreeNodeForTablePath = root.addChild( AnyElementPlaceholderNode.ANY_ELEMENT_HOLDER );
		}

		TreeNode currentNode = firstTreeNodeForTablePath;
		
		for ( int i = 2; i < splits.length; i++ )
		{
			//A double slash or a wildcard in the path
			if ( isAnyElementMapping(splits[i]) ) 
			{
				currentNode = ( (ChildrenAllowedTreeNode) currentNode ).addChild( AnyElementPlaceholderNode.ANY_ELEMENT_HOLDER );
			}
			else
			// xml element in the path
			{
				currentNode = ( (ChildrenAllowedTreeNode) currentNode ).addChild( splits[i] );
			}
		}
		lastTreeNodeForTablePath = currentNode;
	}

	/**
	 * add column mapping path to the tree
	 * @param fromNode
	 * @param relativePath
	 * @param columnIndex
	 */
	private void addColumnPath( ChildrenAllowedTreeNode fromNode,
			String relativePath, int columnIndex )
	{
		// used to differentiate "//" and "/"
		String dummy = relativePath.replaceAll( "//", "/" + DOUBLE_SLASH_REPLACEMENT + "/" );

		String[] splits = dummy.split( "/" );

		TreeNode currentNode = fromNode;

		// Attention: splits of "/A/B" be [][A][B], splits of "A/B" be [A][B]
		int j = splits[0].equals( "" ) ? 1 : 0;
		for ( ; j < splits.length; j++ )
		{
			//double slash or wildcard
			if ( isAnyElementMapping(splits[j]) ) 
			{
				currentNode = ( (ChildrenAllowedTreeNode) currentNode ).addChild( AnyElementPlaceholderNode.ANY_ELEMENT_HOLDER );
			}
			else
			{
				currentNode = ( (ChildrenAllowedTreeNode) currentNode ).addChild( splits[j] );
			}
		}
		currentNode.addColumnIndex( columnIndex );
	}
	
	/**
	 * Whether a path element in the mapping path represent any element
	 */
	private static boolean isAnyElementMapping(String pathElement)
	{
		return pathElement.equals( "*" ) || pathElement.equals( DOUBLE_SLASH_REPLACEMENT );
	}
	
	/**
	 * Whether tablePath is a valid table mapping path
	 * @param tablePath: table mapping path
	 * @return
	 */
	public static boolean isValidTableMappingPath(String tablePath)
	{
		return (tablePath != null
				&& (tablePath.startsWith( "/" ) 
						|| tablePath.startsWith( "*/" )
						|| tablePath.equals( "*" )) //tablePath must start with "/" or "*/" or just be "*"
				&& !tablePath.equals( "/" )	   //tablePath can not be "/"
				&& tablePath.indexOf( "///" ) == -1
				&& !containParentAxisAfterAnyElement(tablePath)
				//if contain attribute path element, that must be the last element in the path
				&& !tablePath.matches( ".*\\Q/@\\E.*\\Q/\\E.+" ));
	}
	
	/**
	 * Whether columnPath is a valid column mapping path
	 * @param columnPath: a column mapping path
	 * @return
	 */
	private static boolean isValidColumnMappingPath(String columnPath)
	{
		return  columnPath != null
				&& columnPath.indexOf( "///" ) == -1
				&& !containParentAxisAfterAnyElement(columnPath)
				//if contain attribute path element, that must be the last element in the path
				&& !columnPath.matches( ".*\\Q/@\\E.*\\Q/\\E.+" );
	}
	
	/**
	 * Whether mapping path contains "//.." or "wildcard/.."
	 * Currently do not support this kind of mapping path
	 * @param mappingPath
	 * @return
	 */
	private static boolean containParentAxisAfterAnyElement(String mappingPath)
	{
		return 	mappingPath.matches( ".*\\Q//../\\E.*" )
				|| mappingPath.matches( ".*\\Q//..\\E$" )
				|| mappingPath.matches( ".*\\Q/*/../\\E.*" )
				|| mappingPath.matches( ".*\\Q/*/..\\E$" )
				|| mappingPath.matches( "^\\Q*/../\\E.*" )
				|| mappingPath.matches( "^\\Q*/..\\E$" );
	}
}

/**
 * A TreeNode represents a path element in the mapping path
 */
abstract class TreeNode
{
	// the name of path element in the mapping path
	private String pathElemntName;

	// saves the indexes of column which mapping path ends at this node
	private Set columnIndexes = new HashSet( );

	ChildrenAllowedTreeNode parent;

	public TreeNode( String pathElementName )
	{
		assert pathElementName != null;
		this.pathElemntName = pathElementName;
	}

	public void addColumnIndex( int index )
	{
		assert index >= 0;
		columnIndexes.add( new Integer( index ) );
	}

	protected Set getColumnIndexes( )
	{
		return columnIndexes;
	}

	public ChildrenAllowedTreeNode getParent( )
	{
		return parent;
	}

	protected void setParent( ChildrenAllowedTreeNode parent )
	{
		this.parent = parent;
	}

	protected String getPathElemntName( )
	{
		return pathElemntName;
	}

	/**
	 * Whether the content of this node match the specified pathElement
	 * 
	 * @param pathElement: one of pathElement in the absolute path generated during xml file parsing
	 * @return
	 */
	abstract boolean matches( String pathElement );


	/**
	 * Whether this node contain at least one child
	 * @return
	 */
	protected boolean hasChild( )
	{
		return false;
	}

	/**
	 * find child by node name
	 * @param nodeName
	 * @return
	 */
	protected TreeNode getChildByName(String nodeName)
	{
		return null;
	}
	
	/**
	 * @param pathElement: one of pathElement in the absolute path generated during xml file parsing
	 * @return all the children which matches pathElement
	 */
	protected Set getMatchedChildren(String pathElement)
	{
		return Collections.EMPTY_SET;
	}
}

abstract class ChildrenAllowedTreeNode extends TreeNode
{
	AnyElementPlaceholderNode anyElementChild = null;
	
	//<attrName, AttrNode> map
	Map attrChildren = new HashMap();
	
	//<pureElementName, Set<ElementNode>> map, pureElementName: the result of removing prediction part
	Map elementChildren = new HashMap();
	
	private boolean hasChild = false;


	public ChildrenAllowedTreeNode( String pathElementName )
	{
		super( pathElementName );
	}
	
	/**
	 * @param pathElement: a path element in the mapping path
	 * @return if that child already exists, return that child; else return the new added child
	 */
	protected TreeNode addChild( String pathElement )
	{
		assert pathElement != null;
		
		if ( pathElement.equals( AnyElementPlaceholderNode.ANY_ELEMENT_HOLDER ) )
		{
			if (anyElementChild == null)
			{
				anyElementChild = new AnyElementPlaceholderNode( );
				anyElementChild.setParent( this );
			}
			hasChild = true;
			return anyElementChild;
		}
		else if ( pathElement.startsWith( "@" ) )
		{
			TreeNode existNode = (TreeNode)attrChildren.get( pathElement );
			if (existNode == null)
			{
				existNode = new AttrNode( pathElement );
				existNode.setParent( this );
				attrChildren.put( pathElement, existNode );
			}
			hasChild = true;
			return existNode;
		}
		else
		{
			TreeNode existNode = null;
			String pureElement = pathElement.replaceAll( "\\Q[\\E\\d+\\Q]\\E$", "" );
			Set elementNodes = (Set)elementChildren.get( pureElement );
			if (elementNodes == null)
			{
				elementNodes = new HashSet();
				elementChildren.put( pureElement, elementNodes );
			}
			Iterator itr = elementNodes.iterator( );
			while (itr.hasNext( ))
			{
				TreeNode node = (TreeNode)itr.next( );
				if (node.getPathElemntName( ).equals( pathElement ))
				{
					existNode = node; 
				}
			}
			if (existNode == null)
			{
				existNode = new ElementNode(pathElement);
				existNode.setParent( this );
				elementNodes.add( existNode );
			}
			hasChild = true;
			return existNode;		
		}
	}

	
	protected boolean hasChild( )
	{
		return hasChild;
	}

	protected TreeNode getChildByName(String nodeName)
	{
		assert nodeName != null;
		if (nodeName.equals( AnyElementPlaceholderNode.ANY_ELEMENT_HOLDER ))
		{
			return anyElementChild;
		}
		else if (nodeName.startsWith( "@"))
		{
			TreeNode node = (TreeNode)attrChildren.get( nodeName );
			return node;
		}
		else
		{
			TreeNode node = null;
			String pureElement = nodeName.replaceAll( "\\Q[\\E\\d+\\Q]\\E$", "" );
			Set elementNodes = (Set)elementChildren.get( pureElement );
			if (elementNodes != null)
			{
				Iterator itr = elementNodes.iterator( );
				while (itr.hasNext( ))
				{
					TreeNode elementNode = (TreeNode)itr.next( );
					if (elementNode.getPathElemntName( ).equals( nodeName ))
					{
						node = elementNode;
					}
				}
			}
			return node;
		}
	}
	
	/**
	 * @param pathElement: one of pathElement in the absolute path generated during xml file parsing
	 * @return all the children which matches pathElement
	 */
	public Set getMatchedChildren(String pathElement)
	{
		assert pathElement != null;
		Set result = new HashSet();
		if (anyElementChild != null && anyElementChild.matches( pathElement ))
		{
			result.add( anyElementChild );
		}
		if (pathElement.startsWith( "@"))
		{
			TreeNode node = (TreeNode)attrChildren.get( pathElement );
			if (node != null)
			{
				result.add( node );
			}
		}
		else
		{
			String pureElement = pathElement.replaceAll( "\\Q[\\E\\d+\\Q]\\E$", "" );
			Set elementNodes = (Set)elementChildren.get( pureElement );
			if (elementNodes != null)
			{
				Iterator itr = elementNodes.iterator( );
				while (itr.hasNext( ))
				{
					TreeNode elementNode = (TreeNode)itr.next( );
					if (elementNode.matches( pathElement ))
					{
						result.add( elementNode );
					}
				}
			}
		}
		return result;
	}
}

/**
 * represent a xml attribute in the mapping path
 */
class AttrNode extends TreeNode
{

	AttrNode( String attrName )
	{
		super( attrName );
	}

	/**
	 * Whether the content of this node match the specified pathElement
	 * 
	 * @param pathElement: one of pathElement in the absolute path generated during xml file parsing
	 * @return
	 */
	boolean matches( String pathElement )
	{
		return this.getPathElemntName( ).equals( pathElement );
	}
}

/**
 * represent a xml element in the mapping path
 * 
 */
class ElementNode extends ChildrenAllowedTreeNode
{

	ElementNode( String elementName )
	{
		super( elementName );
	}

	/**
	 * Whether the content of this node match the specified pathElement
	 * 
	 * @param pathElement: one of pathElement in the absolute path generated during xml file parsing
	 * @return
	 */
	boolean matches( String pathElement )
	{
		assert pathElement != null;
		// has index prediction
		if ( this.getPathElemntName( ).matches( ".*\\Q[\\E\\d+\\Q]\\E$" ) )
		{
			return getPathElemntName( ).equals( pathElement );
		}
		else
		{
			return this.getPathElemntName( )
					.equals( pathElement.replaceAll( "\\Q[\\E\\d+\\Q]\\E$", "" ) );
		}
	}
}

/**
 * represent a double slash , i.e. "//" or a wildcard in the mapping path
 * 
 */
class AnyElementPlaceholderNode extends ChildrenAllowedTreeNode
{

	public static final String ANY_ELEMENT_HOLDER = "";

	// node name makes no sense for AnyElementPlaceholderNode
	AnyElementPlaceholderNode( )
	{
		super( ANY_ELEMENT_HOLDER );
	}

	/**
	 * Whether the content of this node match the specified pathElement
	 * 
	 * @param pathElement: one of pathElement in the absolute path generated during xml file parsing
	 * @return
	 */
	boolean matches( String pathElement )
	{
		// matches any xml element, but not attribute
		return !pathElement.startsWith( "@" );
	}

	protected TreeNode addChild( String pathElement )
	{
		assert pathElement != null;
		if (pathElement.equals( AnyElementPlaceholderNode.ANY_ELEMENT_HOLDER ))
		{
			//two AnyElementPlaceholderNode nodes can be merged into one 
			//AnyElementPlaceHolderNode never contain AnyElementPlaceHolderNode child
			return this;
		}
		else
		{
			return super.addChild( pathElement );
		}
	}

	
}

/**
 * Save a nested column's double dot count and the result of removing all ../.. prefix from it's mapping path
 *
 */
class NestedColumn
{
	private int doubleDotCount;
	
	//path without parent axis
	private String purePath;

	public NestedColumn( int doubleDotCount, String purePath)
	{
		assert doubleDotCount >= 1 && purePath != null;
		this.doubleDotCount = doubleDotCount;
		this.purePath = purePath;
	}

	protected int getDoubleDotCount( )
	{
		return doubleDotCount;
	}

	
	protected String getPurePath( )
	{
		return purePath;
	}
}
