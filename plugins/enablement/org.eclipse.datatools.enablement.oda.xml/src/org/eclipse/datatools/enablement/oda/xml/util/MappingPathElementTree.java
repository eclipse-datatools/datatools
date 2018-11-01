/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
	public static final String DOUBLE_SLASH_REPLACEMENT = "<>";    //$NON-NLS-1$
	public static final String FORWARD_SLASH = "/";                //$NON-NLS-1$
	public static final String DOUBLE_SLASH = "//";                //$NON-NLS-1$
    public static final String ASTERISK = "*";     //$NON-NLS-1$
    public static final String ATTR_MARKER = "@";  //$NON-NLS-1$

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
			else if ( relativePath.startsWith( "../" ) || relativePath.equals( ".." ))//$NON-NLS-1$ //$NON-NLS-2$ 
			// A nested column, unaccessible down from the table path
			{
				int doubleDotCount = 0;
				String[] splits = relativePath.split( FORWARD_SLASH );
				for (int j = 0; j < splits.length; j++)
				{
					if (splits[j].equals( ".." ))//$NON-NLS-1$
					{
						doubleDotCount++;
					}
				}
				
				Set ancestors = getPossibleAncestors( lastTreeNodeForTablePath,
						doubleDotCount );
				
				// remove all the prefix ../..
				String path = relativePath.replaceFirst( "\\Q..\\E(\\Q/..\\E)*", //$NON-NLS-1$
						"" );
				indexNestedColumnMap.put( Integer.valueOf( i ),
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
	 * whether this xml path matches the table mapping path
	 * 
	 * @param path
	 */
	public boolean matchesTablePath( XMLPath path )
	{
		Set nodes = getPossibleEndNodes( path );
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
	 * @param path
	 * @return the possible tree nodes which are corresponding with <code>path</code>
	 */
	private Set getPossibleEndNodes( XMLPath path )
	{
		IXMLPathNode[] absolutePath = path.getPath( );
		
		Set result = new HashSet( );
		Iterator downables = root.getMatchedDownables( absolutePath[0] ).iterator( );
		while ( downables.hasNext( ) )
		{
			TreeNode downable = (TreeNode) downables.next( );
			result.addAll( getPossibleEndNodes( absolutePath, 0, downable ) );
		}
		return result;
	}
	

	


	/**
	 * traverse down from fromNode according to <code>absolutePath</code>  
	 * @param absolutePath: a sequence of xml path nodes encountered during xml file parsing
	 *        fromIndex: the index of beginning path element 
	 *        fromNode:  the beginning node 
	 * @return all the destination nodes which is corresponding with <code>relativePath</code>
	 */
	private Set getPossibleEndNodes( IXMLPathNode[] relativePath, int fromIndex, TreeNode fromNode )
	{
		assert relativePath != null && fromIndex > -1 && fromNode != null;
		if ( fromIndex >= relativePath.length)
		{
			return Collections.EMPTY_SET;
		}

		if ( fromNode.matches( relativePath[fromIndex] ) )
		{
			//reaches the last path element
			if ( fromIndex == relativePath.length - 1 )
			{
				Set result = new HashSet( );
				result.add( fromNode );

				if ( fromNode instanceof AnyNumberElementPlaceholderNode )
				{
					// AnyElementPlaceholderNode may represent nothing
					Set downables = fromNode.getMatchedDownables( relativePath[fromIndex] );
					result.addAll( downables );
				}

				TreeNode node = fromNode.getAnyNumberElementChild( );
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
				if ( fromNode instanceof AnyNumberElementPlaceholderNode )
				{
					Set result = new HashSet( );

					// fromNode may represent 0 or 1, 2, .... (absolutePath.length - fromIndex - 1) elements
					for ( int i = 0; i <= relativePath.length - fromIndex - 1; i++ )
					{
						IXMLPathNode firstElement = relativePath[fromIndex + i];
						Set downables = fromNode.getMatchedDownables( firstElement );
						Iterator itr = downables.iterator( );
						while (itr.hasNext( ))
						{
							TreeNode downable = (TreeNode) itr.next( );
							result.addAll( getPossibleEndNodes( relativePath, fromIndex + i,
									downable ) );
						}
					}

					// fromNode represents absolutePath.length - fromIndex elements
					if ( fromNode.matches( relativePath[relativePath.length - 1] ) )
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
					Set downables = fromNode.getMatchedDownables( relativePath[fromIndex + 1] );
					Iterator itr = downables.iterator( );
					while ( itr.hasNext( ) )
					{
						TreeNode downable = (TreeNode) itr.next( );
						result.addAll( getPossibleEndNodes( relativePath, fromIndex + 1,
								downable ) );
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
	 * return all the possible matched column indexes for a XML path.
	 * ignore the absolute table path
	 * @param path:
	 * @return
	 */
	public int[] getMatchedColumnIndexs( XMLPath path )
	{
		Set nodes = getPossibleEndNodes( path );
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
	 * <code>column</code> and is reachable down from <code>row</code>
	 * 
	 * @param column:
	 * @param row:
	 * @return
	 */
	public int[] getMatchedButNotNestedColumnIndexs( XMLPath column,
			XMLPath row )
	{
		assert column != null && row != null;
		
		IXMLPathNode[] columnPath = column.getPath( );
		IXMLPathNode[] rowPath = row.getPath( );
		
		// columnPath should be accessible down form rowPath
		if ( !column.getPathString( ).startsWith( row.getPathString( ) ) )
		{
			return new int[0];
		}
		
		Set columnEndNodes = new HashSet( );
		if ( columnPath.length == rowPath.length ) // columnPath.equals(rowPath )
		{
			columnEndNodes.add( lastTreeNodeForTablePath );
			TreeNode node = lastTreeNodeForTablePath.getAnyNumberElementChild( );
			if (node != null)
			{
				columnEndNodes.add( node );
			}
		}
		else
		{
			Iterator downables = lastTreeNodeForTablePath.getMatchedDownables( columnPath[rowPath.length] ).iterator( );
			while ( downables.hasNext( ) )
			{
				TreeNode downable = (TreeNode) downables.next( );
				columnEndNodes.addAll( getPossibleEndNodes( columnPath, rowPath.length, downable ) );
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
		return indexNestedColumnMap.containsKey( Integer.valueOf( index ) );
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
	 * whether column is row's nested column, which index is
	 * <code>index</code>
	 * 
	 * @param index
	 * @param row:
	 * @param column: 
	 * @return
	 */
	public boolean isValidNestedColumn( int index, XMLPath row,
			XMLPath column )
	{
		NestedColumn nc = (NestedColumn) ( indexNestedColumnMap.get( Integer.valueOf( index ) ) );
		if ( nc == null )
		{
			return false;
		}
		IXMLPathNode[] rowPath = row.getPath( );
		IXMLPathNode[] columnPath = column.getPath( );

		if ( rowPath.length == 0 )
		{
			// tablePath is just "/"
			return false;
		}

		int doubleDotCount = nc.getDoubleDotCount( );
		if ( rowPath.length < doubleDotCount )
		{
			return false;
		}
		if ( columnPath.length < rowPath.length - doubleDotCount )
		{
			return false;
		}

		for ( int i=0; i<rowPath.length-doubleDotCount; i++)
		{
			if ( ! rowPath[i].equals( columnPath[i] ))
			{
				return false;
			}
		}
		
		//just up, no down
		if (columnPath.length == rowPath.length-doubleDotCount )
		{
			return nc.getPurePath( ).equals( "" );
		}
		
		
		
		String pureMappingPath = nc.getPurePath( );
		if (pureMappingPath.equals( "" ))
		{
			//mapping just up, no down
			return false;
		}
		
		IXMLPathNode[] nodes = new IXMLPathNode[columnPath.length - ( rowPath.length - doubleDotCount )];
		System.arraycopy( columnPath, rowPath.length - doubleDotCount, nodes, 0, nodes.length );
		
		XMLPath path = new XMLPath( nodes);
		
		//compare the down part of mapping path with the down part of real path
		MappingPathElementTree tree = new MappingPathElementTree( pureMappingPath, new String[0]);
		return tree.matchesTablePath( path );
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
			else if ( node instanceof AnyNumberElementPlaceholderNode )
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
				if ( node.getParent( ) instanceof AnyNumberElementPlaceholderNode )
				{
					result.add( node.getParent( ).getParent( ) );
				}
				return result;
			}
		}
		else
		{
			if ( node instanceof AnyNumberElementPlaceholderNode )
			{
				Set result = new HashSet( );
				for ( int i=0; i<upLevel; i++)
				{
					//node itself represents a sequence of elements, whose count is i 
					result.addAll( getPossibleAncestors( node.getParent( ), upLevel - i ) );
				}
				
				//node itself represents a sequence of elements whose count is upLevel
				result.add(  node.getParent( ) );
				
				//node itself reprents a sequence of elements whose count is greater than upLevel
				result.add( node );
				
				return result;
			} 
			else
			{
				//just recurse
				return getPossibleAncestors( node.getParent( ), upLevel - 1 );
			}
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
		tablePath = tablePath.replaceAll( DOUBLE_SLASH, FORWARD_SLASH+ DOUBLE_SLASH_REPLACEMENT + FORWARD_SLASH );

		String[] splits = tablePath.split( FORWARD_SLASH );

		root = new ElementNode( FORWARD_SLASH );

		assert splits.length > 1;
		
		// Attention: splits of "/A/B" be [][A][B]
		// So, the first empty string should be ignored
		TreeNode currentNode = root.addChild( splits[1] );
		
		for ( int i = 2; i < splits.length; i++ )
		{
			currentNode = ( (ChildrenAllowedTreeNode) currentNode ).addChild( splits[i] );
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
		String dummy = relativePath.replaceAll( DOUBLE_SLASH, FORWARD_SLASH + DOUBLE_SLASH_REPLACEMENT + FORWARD_SLASH );

		String[] splits = dummy.split( FORWARD_SLASH );

		TreeNode currentNode = fromNode;

		int j = (splits.length > 0  // splits of "/" is String[0]
				&& splits[0].equals( "" )) ? 1 : 0; //splits of "/A/B" be [][A][B], splits of "A/B" be [A][B]
		for ( ; j < splits.length; j++ )
		{
			currentNode = ( (ChildrenAllowedTreeNode) currentNode ).addChild( splits[j] );
		}
		currentNode.addColumnIndex( columnIndex );
	}
	
	/**
	 * Whether tablePath is a valid table mapping path
	 * @param tablePath: table mapping path
	 * @return
	 */
	public static boolean isValidTableMappingPath(String tablePath)
	{
		return (tablePath != null
				&& tablePath.startsWith( FORWARD_SLASH ) //tablePath must start with "/"
				&& !tablePath.equals( FORWARD_SLASH )	   //tablePath can not be "/"
				&& tablePath.indexOf( "///" ) == -1	//$NON-NLS-1$
				&& !containParentAxisAfterAnyElement(tablePath)
				//if contain attribute path element, that must be the last element in the path
				&& !tablePath.matches( ".*\\Q/@\\E.*\\Q/\\E.+" )); //$NON-NLS-1$
	}
	
	/**
	 * Whether columnPath is a valid column mapping path
	 * @param columnPath: a column mapping path
	 * @return
	 */
	private static boolean isValidColumnMappingPath(String columnPath)
	{
		return  columnPath != null
				&& columnPath.indexOf( "///" ) == -1	//$NON-NLS-1$
				&& !containParentAxisAfterAnyElement(columnPath)
				//if contain attribute path element, that must be the last element in the path
				&& !columnPath.matches( ".*\\Q/@\\E.*\\Q/\\E.+" );//$NON-NLS-1$
	}
	
	/**
	 * Whether mapping path contains "//.."
	 * Currently do not support this kind of mapping path
	 * @param mappingPath
	 * @return
	 */
	private static boolean containParentAxisAfterAnyElement(String mappingPath)
	{
		return 	mappingPath.matches( ".*\\Q//../\\E.*" )	//$NON-NLS-1$
				|| mappingPath.matches( ".*\\Q//..\\E$" );	//$NON-NLS-1$
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

	private ChildrenAllowedTreeNode parent;

	
	public TreeNode( String pathElementName )
	{
		assert pathElementName != null;
		this.pathElemntName = pathElementName;
	}

	public void addColumnIndex( int index )
	{
		assert index >= 0;
		columnIndexes.add( Integer.valueOf( index ) );
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
	 * @param node: one of nodes encountered during XML file parsing
	 * @return
	 */
	abstract boolean matches( IXMLPathNode node );


	/**
	 * Whether this node contain at least one child
	 * @return
	 */
	protected boolean hasChild( )
	{
		return false;
	}

	/**
	 * @param nodeName
	 * @return
	 */
	protected TreeNode getAnyNumberElementChild()
	{
		return null;
	}
	
	/**
	 * @param xn: one of xml nodes encountered during xml file parsing
	 * @return all the nodes that are down from this node and matche <code>xn</code>
	 */
	protected Set getMatchedDownables(IXMLPathNode xn)
	{
		return Collections.EMPTY_SET;
	}
}

abstract class ChildrenAllowedTreeNode extends TreeNode
{
    private AnyNumberElementPlaceholderNode anyNumberElementChild = null;
	
	private OneElementPlaceholderNode oneElementChild = null;
	
	//<attrName, AttrNode> map
	private Map attrChildren = new HashMap();
	
	//<pureElementName, Set<ElementNode>> map, pureElementName: the result of removing prediction part
	private Map elementChildren = new HashMap();
	
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
		
		if ( pathElement.equals( MappingPathElementTree.DOUBLE_SLASH_REPLACEMENT ) )
		{
			if (anyNumberElementChild == null)
			{
				anyNumberElementChild = new AnyNumberElementPlaceholderNode( );
				anyNumberElementChild.setParent( this );
			}
			hasChild = true;
			return anyNumberElementChild;
		}
		else if (pathElement.equals( MappingPathElementTree.ASTERISK ))
		{
			if (oneElementChild == null)
			{
				oneElementChild = new OneElementPlaceholderNode();
				oneElementChild.setParent( this );
			}
			hasChild = true;
			return oneElementChild;
		}
		else if ( pathElement.startsWith( MappingPathElementTree.ATTR_MARKER ) )
		{
			String attrName = pathElement.substring( 1 ); //remove the "@" flag
			TreeNode existNode = (TreeNode)attrChildren.get( attrName );
			if (existNode == null)
			{
				existNode = new AttrNode( attrName );
				existNode.setParent( this );
				attrChildren.put( attrName, existNode );
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
					break;
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

	protected TreeNode getAnyNumberElementChild( )
	{
		return anyNumberElementChild;
	}

	/**
	 * @param xn: one of xml nodes encountered during xml file parsing
	 * @return all the nodes that are down from this node and matche <code>xn</code>
	 */
	public Set getMatchedDownables( IXMLPathNode xpn )
	{
		assert xpn != null;
		Set result = new HashSet();
		if ( xpn instanceof XMLAttr ) //an attribute
		{
			if (anyNumberElementChild != null )
			{
				result.addAll( anyNumberElementChild.getMatchedDownables( xpn ));
			}
			TreeNode node = (TreeNode)attrChildren.get( xpn.getName( ) );
			if (node != null)
			{
				result.add( node );
			}
		}
		else // an element
		{  
			if (anyNumberElementChild != null )
			{
				result.add( anyNumberElementChild );
				result.addAll( anyNumberElementChild.getMatchedDownables( xpn ) );
			}
			if (oneElementChild != null )
			{
				result.add( oneElementChild );
			}
			Set elementNodes = (Set)elementChildren.get( xpn.getName( ) );
			if (elementNodes != null)
			{
				Iterator itr = elementNodes.iterator( );
				while (itr.hasNext( ))
				{
					TreeNode elementNode = (TreeNode)itr.next( );
					if (elementNode.matches( xpn ))
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

	boolean matches( IXMLPathNode xpn )
	{
		if ( xpn instanceof XMLAttr )
		{
			return this.getPathElemntName( ).equals( xpn.getName( ) );
		}
		return false;
	}
}

/**
 * represent a xml element in the mapping path
 * 
 */
class ElementNode extends ChildrenAllowedTreeNode
{
	private boolean isWithIndexPrediction; 
	
	ElementNode( String elementName )
	{
		super( elementName );
		isWithIndexPrediction = this.getPathElemntName( ).matches( ".*\\Q[\\E\\d+\\Q]\\E$" );
	}


	boolean matches( IXMLPathNode xpn )
	{
		assert xpn != null;
		if ( xpn instanceof XMLElement )
		{
			XMLElement xe = (XMLElement)xpn;
		
			
			if ( isWithIndexPrediction )
			{
				// has index prediction
				return getPathElemntName( ).equals( xe.getName( ) + "[" + xe.getIndex( ) + "]" );
			}
			else
			{
				return this.getPathElemntName( ).equals( xe.getName( ) );
			}
		}
		return false;
	}
}

/**
 * represent a double slash , i.e. "//" in the mapping path
 * 
 */
class AnyNumberElementPlaceholderNode extends ChildrenAllowedTreeNode
{
	// node name makes no sense for AnyElementPlaceholderNode
	AnyNumberElementPlaceholderNode( )
	{
		super( "" );
	}


	boolean matches( IXMLPathNode xpn )
	{
		// matches any xml element, but not attribute
		return !(xpn instanceof XMLAttr);
	}

	protected TreeNode addChild( String pathElement )
	{
		assert pathElement != null;
		if (pathElement.equals( MappingPathElementTree.DOUBLE_SLASH_REPLACEMENT ))
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
 * represent a "*" path element in the mapping path
 * 
 */
class OneElementPlaceholderNode extends ChildrenAllowedTreeNode
{
	OneElementPlaceholderNode( )
	{
		super("");
	}


	boolean matches( IXMLPathNode xpn )
	{
		// matches any xml element, but not attribute
		return !(xpn instanceof XMLAttr);
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
