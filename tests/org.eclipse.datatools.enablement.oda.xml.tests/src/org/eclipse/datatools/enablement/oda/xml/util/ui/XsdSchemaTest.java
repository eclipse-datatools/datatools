/*******************************************************************************
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.util.ui;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.util.ISaxParserConsumer;
import org.eclipse.datatools.enablement.oda.xml.util.IXMLSource;
import org.eclipse.datatools.enablement.oda.xml.util.SaxParser;
import org.eclipse.datatools.enablement.oda.xml.util.XMLPath;
import org.w3c.dom.Element;

import junit.framework.TestCase;

public class XsdSchemaTest extends TestCase {
	private static final String SCHEMA = "<xs:schema xmlns:xs='http://www.w3.org/2001/XMLSchema'>";
	private final List<Path> files = new ArrayList<>();
	private Path directory;

	@Override
	protected void setUp() throws Exception {
		directory = Files.createTempDirectory("oda-xsd-test-");
	}

	@Override
	protected void tearDown() throws Exception {
		for (Path file : files)
			Files.deleteIfExists(file);
		Files.delete(directory);
	}

	private Path write(String name, String content) throws Exception {
		Path file = directory.resolve(name);
		files.add(file);
		Files.write(file, content.getBytes(StandardCharsets.UTF_8));
		return file;
	}

	private ATreeNode tree(String declarations) throws Exception {
		Path schema = write("schema.xsd", SCHEMA + declarations + "</xs:schema>");
		return SchemaPopulationUtil.getSchemaTree(schema.toString(), null, 0, null);
	}

	private ATreeNode child(ATreeNode parent, String name) throws OdaException {
		for (Object child : parent.getChildren()) {
			ATreeNode node = (ATreeNode) child;
			if (name.equals(node.getValue())) {
				assertSame(parent, node.getParent());
				return node;
			}
		}
		fail("Missing child " + name + " of " + parent);
		return null;
	}

	public void testGroupsAttributesAndLazyRecursion() throws Exception {
		ATreeNode root = tree("<xs:attribute name='code' type='xs:int'/>"
				+ "<xs:attributeGroup name='attributes'><xs:attribute ref='code'/></xs:attributeGroup>"
				+ "<xs:group name='items'><xs:sequence>" + "<xs:element name='value' type='xs:string'/>"
				+ "<xs:element name='value' type='xs:string'/>"
				+ "<xs:choice><xs:element name='next' type='Node' minOccurs='0'/></xs:choice>"
				+ "</xs:sequence></xs:group>" + "<xs:complexType name='Node'><xs:group ref='items'/>"
				+ "<xs:attributeGroup ref='attributes'/></xs:complexType>" + "<xs:element name='root' type='Node'/>");
		ATreeNode node = child(root, "root");
		assertEquals(3, node.getChildren().length);
		assertEquals("STRING", child(node, "value").getDataType());
		assertEquals(ATreeNode.ATTRIBUTE_TYPE, child(node, "code").getType());
		assertEquals("INT", child(node, "code").getDataType());
		assertSame(node.getChildren()[0], node.getChildren()[0]);
		for (int i = 0; i < 20; i++) {
			node = child(node, "next");
			assertEquals("Node", node.getDataType());
		}
	}

	public void testExtensionRestrictionAndSimpleContent() throws Exception {
		ATreeNode root = tree("<xs:complexType name='Base'><xs:sequence>"
				+ "<xs:element name='first' type='xs:date'/></xs:sequence>"
				+ "<xs:attribute name='keep' type='xs:boolean'/>"
				+ "<xs:attribute name='remove' type='xs:string'/></xs:complexType>"
				+ "<xs:complexType name='Extended'><xs:complexContent><xs:extension base='Base'>"
				+ "<xs:sequence><xs:element name='second' type='xs:decimal'/></xs:sequence>"
				+ "<xs:attribute name='extra' type='xs:double'/>"
				+ "</xs:extension></xs:complexContent></xs:complexType>"
				+ "<xs:complexType name='Restricted'><xs:complexContent><xs:restriction base='Base'>"
				+ "<xs:sequence><xs:element name='first' type='xs:date'/></xs:sequence>"
				+ "<xs:attribute name='remove' use='prohibited'/>"
				+ "</xs:restriction></xs:complexContent></xs:complexType>"
				+ "<xs:complexType name='Text'><xs:simpleContent><xs:extension base='xs:string'>"
				+ "<xs:attribute name='language' type='xs:string'/>"
				+ "</xs:extension></xs:simpleContent></xs:complexType>"
				+ "<xs:element name='extended' type='Extended'/>" + "<xs:element name='restricted' type='Restricted'/>"
				+ "<xs:element name='text' type='Text'/>");
		ATreeNode extended = child(root, "extended");
		assertEquals(5, extended.getChildren().length);
		assertEquals("first", ((ATreeNode) extended.getChildren()[0]).getValue());
		assertEquals("DATE", child(extended, "first").getDataType());
		assertEquals("BIGDECIMAL", child(extended, "second").getDataType());
		assertEquals("BOOLEAN", child(extended, "keep").getDataType());
		ATreeNode restricted = child(root, "restricted");
		assertEquals(2, restricted.getChildren().length);
		child(restricted, "keep");
		assertEquals(1, child(root, "text").getChildren().length);
		child(child(root, "text"), "language");
	}

	public void testIncludesImportsAndQualifiedReferences() throws Exception {
		write("included.xsd",
				SCHEMA + "<xs:complexType name='Node'><xs:sequence>" + "<xs:element name='included' type='xs:string'/>"
						+ "<xs:element name='recursive' type='Node' minOccurs='0'/>"
						+ "</xs:sequence></xs:complexType></xs:schema>");
		write("imported.xsd",
				"<xs:schema xmlns:xs='http://www.w3.org/2001/XMLSchema' targetNamespace='urn:other'>"
						+ "<xs:complexType name='Node'><xs:sequence>"
						+ "<xs:element name='imported' type='xs:int'/></xs:sequence></xs:complexType>"
						+ "<xs:attribute name='id' type='xs:int'/></xs:schema>");
		Path schema = write("main.xsd", "<xs:schema xmlns:xs='http://www.w3.org/2001/XMLSchema'"
				+ " xmlns:m='urn:main' xmlns:o='urn:other' targetNamespace='urn:main'>"
				+ "<xs:include schemaLocation='included.xsd'/>"
				+ "<xs:import namespace='urn:other' schemaLocation='imported.xsd'/>"
				+ "<xs:element name='root'><xs:complexType><xs:all>" + "<xs:element name='local' type='m:Node'/>"
				+ "<xs:element name='foreign' type='o:Node'/></xs:all>"
				+ "<xs:attribute ref='o:id'/></xs:complexType></xs:element></xs:schema>");
		ATreeNode node = child(SchemaPopulationUtil.getSchemaTree(schema.toString(), null, 0, null), "root");
		ATreeNode local = child(node, "local");
		child(local, "included");
		child(child(local, "recursive"), "included");
		assertEquals("INT", child(child(node, "foreign"), "imported").getDataType());
		assertEquals(ATreeNode.ATTRIBUTE_TYPE, child(node, "id").getType());
	}

	public void testRedefinition() throws Exception {
		write("base.xsd",
				SCHEMA + "<xs:complexType name='Base'><xs:sequence><xs:element name='original' type='xs:int'/>"
						+ "</xs:sequence></xs:complexType>"
						+ "<xs:group name='Group'><xs:sequence><xs:element name='one' type='xs:int'/>"
						+ "</xs:sequence></xs:group>"
						+ "<xs:attributeGroup name='Attributes'><xs:attribute name='first' type='xs:int'/>"
						+ "</xs:attributeGroup></xs:schema>");
		ATreeNode root = tree("<xs:redefine schemaLocation='base.xsd'>"
				+ "<xs:complexType name='Base'><xs:complexContent><xs:extension base='Base'>"
				+ "<xs:group ref='Group'/><xs:attributeGroup ref='Attributes'/>"
				+ "</xs:extension></xs:complexContent></xs:complexType>"
				+ "<xs:group name='Group'><xs:sequence><xs:group ref='Group'/>"
				+ "<xs:element name='two' type='xs:int'/></xs:sequence></xs:group>"
				+ "<xs:attributeGroup name='Attributes'><xs:attributeGroup ref='Attributes'/>"
				+ "<xs:attribute name='second' type='xs:int'/></xs:attributeGroup></xs:redefine>"
				+ "<xs:element name='root' type='Base'/>");
		ATreeNode node = child(root, "root");
		assertEquals(5, node.getChildren().length);
		child(node, "original");
		child(node, "one");
		child(node, "two");
		child(node, "first");
		child(node, "second");
	}

	public void testExplicitEncodingAndRelativeInclude() throws Exception {
		write("included.xsd", SCHEMA + "<xs:complexType name='Text'><xs:attribute name='code' type='xs:int'/>"
				+ "</xs:complexType></xs:schema>");
		Path schema = directory.resolve("encoded.xsd");
		files.add(schema);
		Files.write(schema,
				(SCHEMA + "<xs:include schemaLocation='included.xsd'/>"
						+ "<xs:element name='caf\u00e9' type='Text'/></xs:schema>")
						.getBytes(StandardCharsets.ISO_8859_1));
		ATreeNode root = SchemaPopulationUtil.getSchemaTree(schema.toString(), null, "ISO-8859-1", 0, null);
		child(child(root, "caf\u00e9"), "code");
	}

	public void testXmlRootSelectionAndEarlyStop() throws Exception {
		Path schema = write("schema.xsd",
				SCHEMA + "<xs:element name='unused' type='xs:int'/>"
						+ "<xs:element name='root'><xs:complexType><xs:sequence>"
						+ "<xs:element name='value' type='xs:string'/></xs:sequence>"
						+ "</xs:complexType></xs:element></xs:schema>");
		Path xml = write("document.xml", "<root><value>text</value></root>");
		ATreeNode root = SchemaPopulationUtil.getSchemaTree(schema.toString(), xml.toString(), 0, null);
		assertEquals(1, root.getChildren().length);
		child(child(root, "root"), "value");
	}

	public void testDomConstructorAndEmptyChildren() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		String xml = SCHEMA + "<xs:element name='root'><xs:complexType><xs:sequence>"
				+ "<xs:element name='leaf' type='xs:string'/></xs:sequence></xs:complexType>"
				+ "</xs:element></xs:schema>";
		Element declaration = (Element) factory.newDocumentBuilder()
				.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)))
				.getElementsByTagNameNS("http://www.w3.org/2001/XMLSchema", "element").item(0);
		ATreeNode root = new ATreeNode(declaration);
		assertEquals("root", root.getDataType());
		ATreeNode leaf = child(root, "leaf");
		assertEquals(0, leaf.getChildren().length);
		assertEquals(0, leaf.getChildren().length);
	}

	public void testInvalidSchemaReportsCause() throws Exception {
		Path schema = write("schema.xsd", SCHEMA + "<xs:element name='broken'></xs:schema>");
		try {
			SchemaPopulationUtil.getSchemaTree(schema.toString(), null, 0, null);
			fail("Expected invalid schema error");
		} catch (OdaException e) {
			assertNotNull(e.getCause());
		}
	}

	public void testMissingIncludeReportsError() throws Exception {
		Path schema = write("schema.xsd", SCHEMA + "<xs:include schemaLocation='missing.xsd'/></xs:schema>");
		try {
			SchemaPopulationUtil.getSchemaTree(schema.toString(), null, 0, null);
			fail("Expected missing include error");
		} catch (OdaException e) {
			assertNotNull(e.getCause());
		}
	}

	public void testSaxNamespaceMappingAndText() throws Exception {
		final List<String> values = new ArrayList<>();
		SaxParser parser = new SaxParser(new IXMLSource() {
			public java.io.InputStream openInputStream() {
				return new ByteArrayInputStream(
						"<p:root xmlns:p='urn:test'><p:value>text</p:value></p:root>".getBytes(StandardCharsets.UTF_8));
			}

			public String getEncoding() {
				return null;
			}

			public void release() {
			}
		}, new ISaxParserConsumer() {
			public void startElement(XMLPath path) {
			}

			public void endElement(XMLPath path) {
			}

			public void finish() {
			}

			public void manipulateData(XMLPath path, String value) {
				values.add(value);
			}
		}, false);
		parser.run();
		assertFalse(parser.exceptionOccurred());
		assertEquals("urn:test", parser.getPrefixMapping().get("p"));
		assertTrue(values.contains("text"));
	}
}