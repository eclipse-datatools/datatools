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

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.impl.DataTypes;
import org.w3c.dom.Element;

/**
 * The instance of this class is used as tree node of the xml schema tree that
 * is passed to gui.
 */
public class ATreeNode {

	//
	public static final int ELEMENT_TYPE = 1;
	public static final int ATTRIBUTE_TYPE = 2;
	public static final int OTHER_TYPE = 0;

	// The value of certain tree node.
	private Object value;

	// The children list of certain tree node.
	private ArrayList<Object> children;

	// The parent of tree node.
	private ATreeNode parent;

	// The type of the tree node, may either attribute or element.
	private int type;

	// The data type is the complex type that defined in an xsd file.
	private String dataType;

	private static HashMap<String, String> xmlTypeToDataType = new HashMap<>();

	static {
		try {
			xmlTypeToDataType.put("string", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.STRING));
			xmlTypeToDataType.put("byte", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.INT));
			xmlTypeToDataType.put("decimal", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.BIGDECIMAL));
			xmlTypeToDataType.put("long", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.BIGDECIMAL));
			xmlTypeToDataType.put("double", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.DOUBLE));
			xmlTypeToDataType.put("float", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.DOUBLE));
			xmlTypeToDataType.put("int", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.INT));
			xmlTypeToDataType.put("integer", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.INT));
			xmlTypeToDataType.put("negativeInteger", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.INT));
			xmlTypeToDataType.put("nonNegativeInteger", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.INT));
			xmlTypeToDataType.put("nonPositiveInteger", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.INT));
			xmlTypeToDataType.put("positiveInteger", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.INT));
			xmlTypeToDataType.put("short", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.INT));
			xmlTypeToDataType.put("date", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.DATE));
			xmlTypeToDataType.put("dateTime", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.TIMESTAMP));
			xmlTypeToDataType.put("time", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.TIME));
			xmlTypeToDataType.put("boolean", //$NON-NLS-1$
					DataTypes.getTypeString(DataTypes.BOOLEAN));
		} catch (OdaException e) {
			// Should not arrive here
		}
	}

	private static String getDataType(String type) throws OdaException {
		Object result = xmlTypeToDataType.get(type);
		if (result == null)
			return type;
		else
			return result.toString();

	}

	private Element declaration;
	private XsdSchema schema;
	private boolean childrenPopulated;

	/**
	 * 
	 * 
	 */
	protected ATreeNode() {
		this.children = new ArrayList<>();
	}

	/**
	 * 
	 * @param element an XML Schema element declaration in a namespace-aware DOM
	 * @throws OdaException
	 */
	public ATreeNode(Element element) throws OdaException {
		this(new XsdSchema(element.getOwnerDocument()), element, ELEMENT_TYPE);
	}

	ATreeNode(XsdSchema schema, Element declaration, int type) throws OdaException {
		this();
		this.schema = schema;
		this.declaration = schema.resolveDeclaration(declaration);
		this.value = this.declaration.getAttribute("name"); //$NON-NLS-1$
		this.type = type;
		this.dataType = getDataType(schema.getTypeName(this.declaration));
	}

	/**
	 * Return the value of tree node.
	 * 
	 * @return
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Return the children of tree node.
	 * 
	 * @return
	 * @throws OdaException
	 */
	public Object[] getChildren() throws OdaException {
		if (children.size() != 0 || childrenPopulated)
			return children.toArray();
		if (declaration != null && type == ELEMENT_TYPE) {
			for (ATreeNode node : schema.getChildren(declaration)) {
				node.setParent(this);
			}
		}
		childrenPopulated = true;
		return children.toArray();
	}

	/**
	 * Return the parent of tree node.
	 * 
	 * @return
	 */
	public ATreeNode getParent() {
		return parent;
	}

	/**
	 * Set the value to be held by this tree node.
	 * 
	 * @param value
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Add a child to the tree node.
	 * 
	 * @param child
	 */
	public void addChild(Object child) {
		this.children.add(child);
		((ATreeNode) child).parent = this;
	}

	/**
	 * Set the parent of the tree node.
	 * 
	 * @param parent
	 */
	public void setParent(ATreeNode parent) {
		this.parent = parent;
		parent.addChild(this);
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return value.toString();
	}

	/**
	 * Return the type of tree node.
	 * 
	 * @return
	 */
	public int getType() {
		return type;
	}

	/**
	 * Set the type of tree node ( either attribute or element)
	 * 
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Return the data type of tree node. The data type is the complex type that
	 * defined in an xsd file.
	 * 
	 * @return
	 * @throws OdaException
	 */
	public String getDataType() throws OdaException {
		return dataType;
	}

	/**
	 * Set the data type of tree node ( either attribute or element)
	 * 
	 * @param type
	 * @throws OdaException
	 */
	public void setDataType(String type) throws OdaException {
		this.dataType = getDataType(type);
	}
}
