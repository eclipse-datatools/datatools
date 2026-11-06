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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.util.ISaxParserConsumer;
import org.eclipse.datatools.enablement.oda.xml.util.IXMLSource;
import org.eclipse.datatools.enablement.oda.xml.util.ResourceLocatorUtil;
import org.eclipse.datatools.enablement.oda.xml.util.SaxParser;
import org.eclipse.datatools.enablement.oda.xml.util.XMLPath;
import org.eclipse.datatools.enablement.oda.xml.util.XMLSourceFromPath;
import org.w3c.dom.Element;

/**
 * This class is used to offer GUI a utility to get an tree from certain xml/xsd
 * file.
 */
public class SchemaPopulationUtil {
	/**
	 * 
	 * @return
	 * @throws OdaException
	 */
	public static Map getPrefixMapping(String xmlFileName, String xmlEncoding, Object resourceIdentifiers)
			throws OdaException {
		IXMLSource xmlSource = new XMLSourceFromPath(xmlFileName, xmlEncoding, resourceIdentifiers);
		SaxParser sp = new SaxParser(xmlSource, new ISaxParserConsumer() {
			public void endElement(XMLPath path) {
			}

			public void finish() {
			}

			public void manipulateData(XMLPath path, String value) {

			}

			public void startElement(XMLPath path) {

			}

		}, false);

		Thread spThread = new Thread(sp);
		spThread.start();
		try {
			spThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (sp == null || sp.exceptionOccurred())
			return new HashMap();
		return sp.getPrefixMapping();
	}

	/**
	 * Get the schema tree's root node
	 * 
	 * @param xsdFileName
	 * @param xmlFileName
	 * @param xmlEncoding
	 * @param numberOfElementsAccessiable
	 * @return
	 * @throws OdaException
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	public static ATreeNode getSchemaTree(String xsdFileName, String xmlFileName, String xmlEncoding,
			int numberOfElementsAccessiable, Object resourceIdentifiers)
			throws OdaException, MalformedURLException, URISyntaxException {
		if (xsdFileName != null && xsdFileName.trim().length() > 0) {
			if (xmlFileName != null && xmlFileName.trim().length() > 0)
				return XSDFileSchemaTreePopulator.getSchemaTree(xsdFileName, xmlFileName, xmlEncoding,
						resourceIdentifiers);
			else
				return XSDFileSchemaTreePopulator.getSchemaTree(xsdFileName, xmlEncoding, resourceIdentifiers);
		} else
			return new XMLFileSchemaTreePopulator(numberOfElementsAccessiable).getSchemaTree(xmlFileName, xmlEncoding,
					resourceIdentifiers);
	}

	/**
	 * Get the schema tree's root node
	 * 
	 * @param xsdFileName
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	public static ATreeNode getSchemaTree(String xsdFileName, String xmlFileName, int numberOfElementsAccessiable,
			Object resourceIdentifiers) throws OdaException, MalformedURLException, URISyntaxException {
		return getSchemaTree(xsdFileName, xmlFileName, null, numberOfElementsAccessiable, resourceIdentifiers);
	}

}

/**
 * This class is used to populate an XML schema tree from an xml file.
 * 
 */
final class XMLFileSchemaTreePopulator implements ISaxParserConsumer {

	private static final String FORWARD_SLASH = "/"; //$NON-NLS-1$
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$
	private static final String ROOT_LITERAL = "ROOT"; //$NON-NLS-1$

	private int rowCount;
	private ATreeNode root;
	private SaxParser sp;

	private int numberOfElementsAccessiable;
	Thread spThread;

	/**
	 * 
	 * @param numberOfElementsAccessiable
	 */
	XMLFileSchemaTreePopulator(int numberOfElementsAccessiable) {
		this.rowCount = 0;
		this.root = new ATreeNode();
		this.root.setValue(ROOT_LITERAL);
		this.numberOfElementsAccessiable = numberOfElementsAccessiable == 0 ? Integer.MAX_VALUE
				: numberOfElementsAccessiable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.util.ISaxParserConsumer#
	 * manipulateData(java.lang.String, java.lang.String)
	 */
	public void manipulateData(XMLPath path, String value) {
		if (isAttribute(path.getPathString()))
			try {
				this.insertNode(path.getPathString().replaceAll("\\Q[\\E\\d+\\Q]\\E", EMPTY_STRING).trim()); //$NON-NLS-1$
			} catch (OdaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void startElement(XMLPath path) {
		String treamedPath = path.getPathString().replaceAll("\\Q[\\E\\d+\\Q]\\E", EMPTY_STRING).trim(); //$NON-NLS-1$
		try {
			this.insertNode(treamedPath);
		} catch (OdaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rowCount++;

		// Only parser the first 10000 elements
		if (rowCount >= numberOfElementsAccessiable) {
			assert sp != null;
			sp.stopParsing();
		}

	}

	public void endElement(XMLPath path) {
		String treamedPath = path.getPathString().replaceAll("\\Q[\\E\\d+\\Q]\\E", EMPTY_STRING).trim(); //$NON-NLS-1$
		try {
			this.insertNode(treamedPath);
		} catch (OdaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Only parser the first 10000 elements
		if (rowCount >= numberOfElementsAccessiable) {
			assert sp != null;
			sp.stopParsing();
		}

	}

	/**
	 * Exam whether given path specified an attribute
	 * 
	 * @param path
	 * @return
	 */
	private boolean isAttribute(String path) {
		return path.matches(".*\\Q@\\E.*"); //$NON-NLS-1$
	}

	/**
	 * Return the root node of a schema tree.
	 * 
	 * @param xmlFileName
	 * @param xmlEncoding
	 * @return
	 */
	public ATreeNode getSchemaTree(String xmlFileName, String xmlEncoding, Object resourceIdentifiers) {
		try {

			IXMLSource xmlSource = new XMLSourceFromPath(xmlFileName, xmlEncoding, resourceIdentifiers);
			sp = new SaxParser(xmlSource, this, false);

			spThread = new Thread(sp);
			spThread.start();
			try {
				spThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (OdaException e1) {
			e1.printStackTrace();
		}

		if (sp == null || sp.exceptionOccurred())
			return null;

		return root;
	}

	/**
	 * Return the root node of a schema tree.
	 * 
	 * @param xmlFileName
	 * @param includeAttribute
	 * @return
	 */
	public ATreeNode getSchemaTree(String xmlFileName, Object resourceIdentifiers) {
		return getSchemaTree(xmlFileName, null, resourceIdentifiers);
	}

	/**
	 * Insert a node specified by the path.
	 * 
	 * @param treatedPath
	 * @throws OdaException
	 */
	private void insertNode(String treatedPath) throws OdaException {
		boolean isAttribute = isAttribute(treatedPath);

		// Remove the leading "/" then split the path.
		String[] path = treatedPath.replaceFirst(FORWARD_SLASH, EMPTY_STRING).split(FORWARD_SLASH);

		// If the path specified an attribute then re-build the path array so
		// that it can divid element and
		// its attribute to two array items.
		if (isAttribute) {
			String[] temp = path[path.length - 1].split("\\Q@\\E"); //$NON-NLS-1$

			assert temp.length == 2;
			path[path.length - 1] = temp[1];

		}

		// The parentNode
		ATreeNode parentNode = root;

		// Iterate each path array element, find or create its countpart node
		// instance.
		for (int i = 0; i < path.length; i++) {
			// This variable hosts the node instance that matches the given path
			// array item value.
			ATreeNode matchedNode = null;

			for (int j = 0; j < parentNode.getChildren().length; j++) {
				if (((ATreeNode) parentNode.getChildren()[j]).getValue().equals(path[i])) {
					matchedNode = (ATreeNode) parentNode.getChildren()[j];
					break;
				}
			}
			if (matchedNode != null) {
				parentNode = matchedNode;
			} else {
				matchedNode = new ATreeNode();

				if ((i == path.length - 1) && isAttribute) {
					matchedNode.setType(ATreeNode.ATTRIBUTE_TYPE);
				} else {
					matchedNode.setType(ATreeNode.ELEMENT_TYPE);
				}

				matchedNode.setValue(path[i]);
				matchedNode.setParent(parentNode);
				parentNode = matchedNode;
			}
		}
	}

	public void finish() {
	}
}

/**
 * This class is used to populate an XML schema tree from an xml file.
 * 
 */
final class XSDFileSchemaTreePopulator {
	private static final String ROOT_LITERAL = "ROOT"; //$NON-NLS-1$

	/**
	 * Return the root node of a schema tree.
	 * 
	 * @param schemafileName
	 * @param xmlFileName
	 * @param xmlEncoding
	 * @return
	 * @throws OdaException
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	public static ATreeNode getSchemaTree(String schemafileName, String xmlFileName, String xmlEncoding,
			Object resourceIdentifiers) throws OdaException, MalformedURLException, URISyntaxException {
		ATreeNode xmlRoot = new XMLFileSchemaTreePopulator(2).getSchemaTree(xmlFileName, xmlEncoding,
				resourceIdentifiers);

		XsdSchema schema = loadSchema(schemafileName, xmlEncoding, resourceIdentifiers);
		List<Element> elements = schema.getElements();

		ATreeNode xsdRoot = new ATreeNode();
		xsdRoot.setValue(ROOT_LITERAL);
		for (Element element : elements) {
			ATreeNode node = new ATreeNode(schema, element, ATreeNode.ELEMENT_TYPE);

			xsdRoot.addChild(node);

			// Only the element whose name is same to the tag name of the
			// xml root,as well as its sub elements will be populated in the
			// tree, if this no element that matches the xml root elememt,
			// then
			// the tree is populated based on the xsd file structure
			if (xmlRoot != null && xmlRoot.getChildren().length > 0
					&& node.getValue().equals(((ATreeNode) xmlRoot.getChildren()[0]).getValue())) {
				xsdRoot = new ATreeNode();
				xsdRoot.setValue(ROOT_LITERAL);
				xsdRoot.addChild(node);
				break;
			}

		}
		return xsdRoot;
	}

	/**
	 * @param schemafileName
	 * @param xmlEncoding
	 * @return
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 * @throws OdaException
	 */
	private static XsdSchema loadSchema(String schemafileName, String xmlEncoding, Object resourceIdentifiers)
			throws MalformedURLException, URISyntaxException, OdaException {
		URI uri = ResourceLocatorUtil.resolvePath(resourceIdentifiers, schemafileName);
		if (uri == null)
			throw new OdaException(Messages.getString("ui.xsdFileNotFound") + schemafileName); //$NON-NLS-1$
		return XsdSchema.load(uri, xmlEncoding);
	}

	/**
	 * get schema tree node from <code>xsdFileName</code> according to encoding
	 * <code>xmlEncoding</code>.
	 * 
	 * @param xsdFileName
	 * @param xmlEncoding
	 * @return
	 * @throws OdaException
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	public static ATreeNode getSchemaTree(String xsdFileName, String xmlEncoding, Object resourceIdentifiers)
			throws OdaException, MalformedURLException, URISyntaxException {

		XsdSchema schema = loadSchema(xsdFileName, xmlEncoding, resourceIdentifiers);

		ATreeNode root = new ATreeNode();
		root.setValue(ROOT_LITERAL);
		for (Element element : schema.getElements()) {
			ATreeNode node = new ATreeNode(schema, element, ATreeNode.ELEMENT_TYPE);
			node.setDataType(element.getAttribute("name")); //$NON-NLS-1$
			root.addChild(node);
		}

		return root;

	}

	/**
	 * Return the root node of a schema tree.
	 * 
	 * @param xsdFileName
	 * @param incAttr
	 * @return
	 * @throws OdaException
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	public static ATreeNode getSchemaTree(String xsdFileName, Object resourceIdentifiers)
			throws OdaException, MalformedURLException, URISyntaxException {
		return getSchemaTree(xsdFileName, null, resourceIdentifiers);
	}
}