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

package org.eclipse.datatools.enablement.oda.xml.util.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.xerces.impl.xs.XSAttributeGroupDecl;
import org.apache.xerces.impl.xs.XSAttributeUseImpl;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.impl.xs.XSModelGroupImpl;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.xs.XSAttributeDeclaration;
import org.apache.xerces.xs.XSObjectList;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.impl.DataTypes;

/**
 * The instance of this class is used as tree node of the xml schema tree that
 * is passed to gui.
 */
public class ATreeNode
{

	//
	public static final int ELEMENT_TYPE = 1;
	public static final int ATTRIBUTE_TYPE = 2;
	public static final int OTHER_TYPE = 0;

	// The value of certain tree node.
	private Object value;

	// The children list of certain tree node.
	private ArrayList children;

	// The parent of tree node.
	private ATreeNode parent;

	// The type of the tree node, may either attribute or element.
	private int type;

	// The data type is the complex type that defined in an xsd file.
	private String dataType;

	private static HashMap xmlTypeToDataType = new HashMap( );

	static
	{
		try
		{
			xmlTypeToDataType.put( "string", //$NON-NLS-1$
					DataTypes.getTypeString( DataTypes.STRING ) );
			xmlTypeToDataType.put( "byte",   //$NON-NLS-1$
					DataTypes.getTypeString( DataTypes.INT ) );
			xmlTypeToDataType.put( "decimal",    //$NON-NLS-1$    
					DataTypes.getTypeString( DataTypes.BIGDECIMAL ) );
			xmlTypeToDataType.put( "long",    //$NON-NLS-1$    
					DataTypes.getTypeString( DataTypes.BIGDECIMAL ) );
			xmlTypeToDataType.put( "double",   //$NON-NLS-1$
					DataTypes.getTypeString( DataTypes.DOUBLE ) );
			xmlTypeToDataType.put( "float",   //$NON-NLS-1$
					DataTypes.getTypeString( DataTypes.DOUBLE ) );
			xmlTypeToDataType.put( "int",    //$NON-NLS-1$
					DataTypes.getTypeString( DataTypes.INT ) );
			xmlTypeToDataType.put( "integer",    //$NON-NLS-1$
					DataTypes.getTypeString( DataTypes.INT ) );
			xmlTypeToDataType.put( "negativeInteger",    //$NON-NLS-1$
					DataTypes.getTypeString( DataTypes.INT ) );
			xmlTypeToDataType.put( "nonNegativeInteger", //$NON-NLS-1$
					DataTypes.getTypeString( DataTypes.INT ) );
			xmlTypeToDataType.put( "nonPositiveInteger", //$NON-NLS-1$
					DataTypes.getTypeString( DataTypes.INT ) );
			xmlTypeToDataType.put( "positiveInteger",    //$NON-NLS-1$
					DataTypes.getTypeString( DataTypes.INT ) );
			xmlTypeToDataType.put( "short",  //$NON-NLS-1$
					DataTypes.getTypeString( DataTypes.INT ) );
			xmlTypeToDataType.put( "date",   //$NON-NLS-1$
					DataTypes.getTypeString( DataTypes.DATE ) );
			xmlTypeToDataType.put( "dateTime",   //$NON-NLS-1$
					DataTypes.getTypeString( DataTypes.TIMESTAMP ) );
			xmlTypeToDataType.put( "time",   //$NON-NLS-1$       
					DataTypes.getTypeString( DataTypes.TIME ) );
			xmlTypeToDataType.put( "boolean",    //$NON-NLS-1$
					DataTypes.getTypeString( DataTypes.BOOLEAN ) );
		}
		catch ( OdaException e )
		{
			// Should not arrive here
		}
	}

	private static String getDataType( String type ) throws OdaException
	{
		Object result = xmlTypeToDataType.get( type );
		if ( result == null )
			return type;
		else
			return result.toString( );

	}

	private XSElementDecl element;
	private XSAttributeDeclaration attr;

	/**
	 * 
	 * 
	 */
	protected ATreeNode( )
	{
		this.children = new ArrayList();
	}

	/**
	 * 
	 * @param element
	 * @throws OdaException
	 */
	public ATreeNode( XSElementDecl element ) throws OdaException
	{
		this();
		this.element = element;
		this.value = element.getName( );
		this.type = ELEMENT_TYPE;

		if ( element.getTypeDefinition( ).getName( ) != null )
			this.dataType = getDataType( element.getTypeDefinition( ).getName( ) );
		else
			this.dataType = getDataType( element.getName( ) );

	}

	private ATreeNode( XSAttributeDeclaration attrDeclaration )
			throws OdaException
	{
		this();
		this.attr = attrDeclaration;
		this.value = this.attr.getName( );
		this.type = ATTRIBUTE_TYPE;
		this.dataType = getDataType( attrDeclaration.getTypeDefinition( )
				.getName( ) );
	}

	/**
	 * Return the value of tree node.
	 * 
	 * @return
	 */
	public Object getValue( )
	{
		return value;
	}

	/**
	 * Return the children of tree node.
	 * 
	 * @return
	 * @throws OdaException
	 */
	public Object[] getChildren( ) throws OdaException
	{
		if ( this.children.size( ) != 0 )
			return this.children.toArray( );
		List result = new ArrayList( );
		if ( this.attr != null )
			return result.toArray( );

		if ( this.element != null )
		{
			if ( this.element.getTypeDefinition( ) instanceof XSComplexTypeDecl )
			{
				XSModelGroupImpl group = null;

				if ( ( (XSComplexTypeDecl) this.element.getTypeDefinition( ) ).getParticle( ) != null )
				{
					group = (XSModelGroupImpl) ( (XSComplexTypeDecl) this.element.getTypeDefinition( ) ).getParticle( )
							.getTerm( );

					populateModelGroup( result, group );
				}
				if ( ( (XSComplexTypeDecl) this.element.getTypeDefinition( ) ).getAttrGrp( ) != null )
				{
					XSAttributeGroupDecl gp1 = ( (XSComplexTypeDecl) this.element.getTypeDefinition( ) ).getAttrGrp( );
					XSObjectList list = gp1.getAttributeUses( );

					for ( int i = 0; i < list.getLength( ); i++ )
					{
						XSAttributeUseImpl impl = (XSAttributeUseImpl) list.item( i );
						ATreeNode node = new ATreeNode( impl.getAttrDeclaration( ) );
						addNodeToChild( result, node );
					}
				}
			}
		}
		for ( int i = 0; i < result.size(); i++ ) 
		{
			( ( ATreeNode ) result.get( i ) ).setParent( this );
		}
		return result.toArray( );
	}

	/**
	 * 
	 * @param result
	 * @param group
	 * @throws OdaException
	 */
	private void populateModelGroup( List result, XSModelGroupImpl group )
			throws OdaException
	{
		for ( int i = 0; i < group.getParticles( ).getLength( ); i++ )
		{
			ATreeNode node = null;
			Object o = ( (XSParticleDecl) group.getParticles( ).item( i ) ).getTerm( );
			if ( o instanceof XSModelGroupImpl )
			{
				populateModelGroup( result, (XSModelGroupImpl) o );

			}
			else if ( o instanceof XSElementDecl )
			{
				node = new ATreeNode( (XSElementDecl) o );
				addNodeToChild( result, node );
			}
		}
	}

	/**
	 * 
	 * @param result
	 * @param node
	 */
	private void addNodeToChild( List result, ATreeNode node )
	{
		for ( int i = 0; i < result.size( ); i++ )
		{
			if ( ( (ATreeNode) result.get( i ) ).getValue( )
					.equals( node.getValue( ) ) )
				return;
		}
		result.add( node );
	}

	/**
	 * Return the parent of tree node.
	 * 
	 * @return
	 */
	public ATreeNode getParent( )
	{
		return parent;
	}

	/**
	 * Set the value to be held by this tree node.
	 * 
	 * @param value
	 */
	public void setValue( Object value )
	{
		this.value = value;
	}

	/**
	 * Add a child to the tree node.
	 * 
	 * @param child
	 */
	public void addChild( Object child )
	{
		this.children.add( child );
		( (ATreeNode) child ).parent = this;
	}

	/**
	 * Set the parent of the tree node.
	 * 
	 * @param parent
	 */
	public void setParent( ATreeNode parent )
	{
		this.parent = parent;
		parent.addChild( this );
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	public String toString( )
	{
		return value.toString( );
	}

	/**
	 * Return the type of tree node.
	 * 
	 * @return
	 */
	public int getType( )
	{
		return type;
	}

	/**
	 * Set the type of tree node ( either attribute or element)
	 * 
	 * @param type
	 */
	public void setType( int type )
	{
		this.type = type;
	}

	/**
	 * Return the data type of tree node. The data type is the complex type that
	 * defined in an xsd file.
	 * 
	 * @return
	 * @throws OdaException
	 */
	public String getDataType( ) throws OdaException
	{
		return dataType;
	}

	/**
	 * Set the data type of tree node ( either attribute or element)
	 * 
	 * @param type
	 * @throws OdaException
	 */
	public void setDataType( String type ) throws OdaException
	{
		this.dataType = getDataType( type );
	}
}
