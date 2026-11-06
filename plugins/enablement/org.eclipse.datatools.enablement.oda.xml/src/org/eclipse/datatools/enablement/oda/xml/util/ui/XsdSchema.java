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

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.SchemaFactory;

import org.eclipse.datatools.connectivity.XMLUtil;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.i18n.Messages;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * DOM-backed declarations for the schema selection tree. JAXP validates schemas
 * but does not expose a schema component model, so declarations are indexed by
 * expanded name and traversed lazily by {@link ATreeNode}.
 */
final class XsdSchema {
	private static final String XSD = XMLConstants.W3C_XML_SCHEMA_NS_URI;
	private final Map<String, Map<QName, Element>> components = new HashMap<>();
	private final Map<Document, String> namespaces = new IdentityHashMap<>();
	private final Map<Element, Element> redefined = new IdentityHashMap<>();
	private final Set<String> loaded = new HashSet<>();

	static XsdSchema load(URI uri, String encoding) throws OdaException {
		Document document = read(uri, encoding);
		try {
			SchemaFactory.newInstance(XSD).newSchema(new DOMSource(document, uri.toString()));
		} catch (SAXException e) {
			throw invalidSchema(e);
		}
		return new XsdSchema(document);
	}

	XsdSchema(Document document) throws OdaException {
		index(document, ""); //$NON-NLS-1$
	}

	private static Document read(URI uri, String encoding) throws OdaException {
		try (InputStream stream = uri.toURL().openStream()) {
			DocumentBuilder builder = XMLUtil.newDocumentBuilder(true);
			builder.setErrorHandler(new DefaultHandler() {
				@Override
				public void error(SAXParseException e) throws SAXException {
					throw e;
				}

				@Override
				public void fatalError(SAXParseException e) throws SAXException {
					throw e;
				}
			});
			InputSource source = new InputSource(stream);
			source.setSystemId(uri.toString());
			if (encoding != null && encoding.length() > 0) {
				source.setEncoding(encoding);
			}
			return builder.parse(source);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			throw invalidSchema(e);
		}
	}

	private void index(Document document, String includedNamespace) throws OdaException {
		Element root = document.getDocumentElement();
		if (!is(root, "schema")) //$NON-NLS-1$
			throw new OdaException(Messages.getString("ui.invalidXSDFile")); //$NON-NLS-1$
		String namespace = root.getAttribute("targetNamespace"); //$NON-NLS-1$
		if (namespace.length() == 0)
			namespace = includedNamespace;
		if (!loaded.add(document.getDocumentURI() + "|" + namespace)) //$NON-NLS-1$
			return;
		namespaces.put(document, namespace);

		for (Element child : children(root)) {
			if (child.hasAttribute("name")) //$NON-NLS-1$
				componentMap(child.getLocalName()).put(new QName(namespace, child.getAttribute("name")), child); //$NON-NLS-1$
		}
		for (Element child : children(root)) {
			if (is(child, "include") || is(child, "import") || is(child, "redefine")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			{
				String location = child.getAttribute("schemaLocation"); //$NON-NLS-1$
				if (location.length() == 0)
					continue;
				try {
					URI base = child.getBaseURI() == null ? new URI("") : new URI(child.getBaseURI()); //$NON-NLS-1$
					URI uri = base.resolve(new URI(location)).normalize();
					String inherited = is(child, "import") ? "" : namespace; //$NON-NLS-1$ //$NON-NLS-2$
					// Chameleon includes are loaded separately for each adopting namespace.
					if (!loaded.contains(uri.toString() + "|" + inherited)) //$NON-NLS-1$
						index(read(uri, null), inherited);
					if (is(child, "redefine")) { //$NON-NLS-1$
						for (Element replacement : children(child)) {
							if (!replacement.hasAttribute("name")) //$NON-NLS-1$
								continue;
							Map<QName, Element> map = componentMap(replacement.getLocalName());
							QName key = new QName(namespace, replacement.getAttribute("name")); //$NON-NLS-1$
							Element original = map.put(key, replacement);
							if (original != null)
								redefined.put(replacement, original);
						}
					}
				} catch (java.net.URISyntaxException | IllegalArgumentException e) {
					throw invalidSchema(e);
				}
			}
		}
	}

	List<Element> getElements() {
		return new ArrayList<>(componentMap("element").values()); //$NON-NLS-1$
	}

	private Map<QName, Element> componentMap(String kind) {
		Map<QName, Element> map = components.get(kind);
		if (map == null) {
			map = new LinkedHashMap<>();
			components.put(kind, map);
		}
		return map;
	}

	private QName name(Element context, String value) {
		int colon = value.indexOf(':');
		String prefix = colon < 0 ? null : value.substring(0, colon);
		String namespace = context.lookupNamespaceURI(prefix);
		// Unqualified references in a chameleon include acquire its namespace.
		if (namespace == null && prefix == null
				&& !context.getOwnerDocument().getDocumentElement().hasAttribute("targetNamespace")) //$NON-NLS-1$
			namespace = namespaces.get(context.getOwnerDocument());
		return new QName(namespace == null ? "" : namespace, //$NON-NLS-1$
				colon < 0 ? value : value.substring(colon + 1));
	}

	private Element lookup(String kind, Element context, String value) throws OdaException {
		Element result = find(kind, context, value);
		if (result == null)
			throw new OdaException(Messages.getString("ui.unresolvedXSDComponent") + " " + kind + ": " + value); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	private Element find(String kind, Element context, String value) {
		QName key = name(context, value);
		QName underlyingKey = name(context, value.replaceAll("_fn3dktizrknc9pi$", ""));
		for (Node ancestor = context; ancestor instanceof Element; ancestor = ancestor.getParentNode()) {
			Element original = redefined.get(ancestor);
			if (original != null && kind.equals(original.getLocalName()) && underlyingKey
					.equals(new QName(namespaces.get(original.getOwnerDocument()), original.getAttribute("name")))) //$NON-NLS-1$
				return original;
		}
		return componentMap(kind).get(key);
	}

	Element resolveDeclaration(Element declaration) throws OdaException {
		return declaration.hasAttribute("ref") //$NON-NLS-1$
				? lookup(declaration.getLocalName(), declaration, declaration.getAttribute("ref")) //$NON-NLS-1$
				: declaration;
	}

	private Element type(Element declaration) throws OdaException {
		for (Element child : children(declaration)) {
			if (is(child, "complexType") || is(child, "simpleType")) //$NON-NLS-1$ //$NON-NLS-2$
				return child;
		}
		if (!declaration.hasAttribute("type")) //$NON-NLS-1$
			return declaration.hasAttribute("substitutionGroup") //$NON-NLS-1$
					? type(lookup("element", declaration, declaration.getAttribute("substitutionGroup"))) //$NON-NLS-1$ //$NON-NLS-2$
					: null;
		String value = declaration.getAttribute("type"); //$NON-NLS-1$
		if (XSD.equals(name(declaration, value).getNamespaceURI()))
			return null;
		Element result = find("complexType", declaration, value); //$NON-NLS-1$
		return result == null ? lookup("simpleType", declaration, value) : result; //$NON-NLS-1$
	}

	String getTypeName(Element declaration) throws OdaException {
		if (declaration.hasAttribute("type")) //$NON-NLS-1$
			return name(declaration, declaration.getAttribute("type")).getLocalPart(); //$NON-NLS-1$
		if (declaration.hasAttribute("substitutionGroup")) //$NON-NLS-1$
			return getTypeName(lookup("element", declaration, declaration.getAttribute("substitutionGroup"))); //$NON-NLS-1$ //$NON-NLS-2$
		Element type = type(declaration);
		if (type != null)
			return is(declaration, "attribute") ? null : declaration.getAttribute("name"); //$NON-NLS-1$ //$NON-NLS-2$
		return is(declaration, "attribute") ? "anySimpleType" : "anyType"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	List<ATreeNode> getChildren(Element declaration) throws OdaException {
		Map<String, Element> elements = new LinkedHashMap<>();
		Map<QName, Element> attributes = new LinkedHashMap<>();
		Element type = type(declaration);
		if (type != null && is(type, "complexType")) //$NON-NLS-1$
			collectType(type, elements, attributes, new HashSet<Element>());

		List<ATreeNode> result = new ArrayList<>();
		Set<String> names = new HashSet<>();
		for (Element element : elements.values()) {
			ATreeNode node = new ATreeNode(this, element, ATreeNode.ELEMENT_TYPE);
			if (names.add(node.getValue().toString()))
				result.add(node);
		}
		for (Element attribute : attributes.values()) {
			ATreeNode node = new ATreeNode(this, attribute, ATreeNode.ATTRIBUTE_TYPE);
			if (names.add(node.getValue().toString()))
				result.add(node);
		}
		return result;
	}

	private void collectType(Element type, Map<String, Element> elements, Map<QName, Element> attributes,
			Set<Element> visiting) throws OdaException {
		if (!visiting.add(type))
			throw new OdaException(Messages.getString("ui.circularXSDComponent")); //$NON-NLS-1$
		Element content = type;
		for (Element child : children(type)) {
			if (is(child, "complexContent") || is(child, "simpleContent")) //$NON-NLS-1$ //$NON-NLS-2$
			{
				for (Element derivation : children(child)) {
					if (is(derivation, "extension") || is(derivation, "restriction")) //$NON-NLS-1$ //$NON-NLS-2$
					{
						content = derivation;
						String baseName = derivation.getAttribute("base"); //$NON-NLS-1$
						Element base = find("complexType", derivation, baseName); //$NON-NLS-1$
						if (base != null) {
							Map<String, Element> inherited = is(derivation, "extension") //$NON-NLS-1$
									? elements
									: new LinkedHashMap<String, Element>();
							collectType(base, inherited, attributes, visiting);
						}
					}
				}
			}
		}
		collectParticles(content, elements, new HashSet<Element>());
		collectAttributes(content, attributes, new HashSet<Element>());
		visiting.remove(type);
	}

	private void collectParticles(Element content, Map<String, Element> result, Set<Element> visiting)
			throws OdaException {
		if (!visiting.add(content))
			throw new OdaException(Messages.getString("ui.circularXSDComponent")); //$NON-NLS-1$
		for (Element child : children(content)) {
			if ("0".equals(child.getAttribute("maxOccurs"))) //$NON-NLS-1$ //$NON-NLS-2$
				continue;
			if (is(child, "element")) //$NON-NLS-1$
			{
				Element declaration = resolveDeclaration(child);
				String name = declaration.getAttribute("name"); //$NON-NLS-1$
				if (!result.containsKey(name))
					result.put(name, declaration);
			} else if (is(child, "group")) //$NON-NLS-1$
			{
				collectParticles(resolveDeclaration(child), result, visiting);
			} else if (is(child, "sequence") || is(child, "choice") || is(child, "all")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			{
				collectParticles(child, result, visiting);
			}
		}
		visiting.remove(content);
	}

	private void collectAttributes(Element content, Map<QName, Element> result, Set<Element> visiting)
			throws OdaException {
		if (!visiting.add(content))
			throw new OdaException(Messages.getString("ui.circularXSDComponent")); //$NON-NLS-1$
		for (Element child : children(content)) {
			if (is(child, "attribute")) //$NON-NLS-1$
			{
				Element declaration = resolveDeclaration(child);
				String namespace = ""; //$NON-NLS-1$
				if (child.hasAttribute("ref") || "qualified".equals(child.getAttribute("form")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						|| (!child.hasAttribute("form") && "qualified".equals(child.getOwnerDocument() //$NON-NLS-1$ //$NON-NLS-2$
								.getDocumentElement().getAttribute("attributeFormDefault")))) //$NON-NLS-1$
					namespace = namespaces.get(declaration.getOwnerDocument());
				QName key = new QName(namespace, declaration.getAttribute("name")); //$NON-NLS-1$
				if ("prohibited".equals(child.getAttribute("use"))) //$NON-NLS-1$ //$NON-NLS-2$
					result.remove(key);
				else
					result.put(key, declaration);
			} else if (is(child, "attributeGroup")) //$NON-NLS-1$
			{
				collectAttributes(resolveDeclaration(child), result, visiting);
			}
		}
		visiting.remove(content);
	}

	private static boolean is(Element element, String name) {
		return XSD.equals(element.getNamespaceURI()) && name.equals(element.getLocalName());
	}

	private static List<Element> children(Element parent) {
		List<Element> result = new ArrayList<>();
		for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
			if (child instanceof Element && XSD.equals(child.getNamespaceURI()))
				result.add((Element) child);
		}
		return result;
	}

	private static OdaException invalidSchema(Exception cause) {
		OdaException exception = new OdaException(
				Messages.getString("ui.invalidXSDFile") + " " + cause.getLocalizedMessage()); //$NON-NLS-1$ //$NON-NLS-2$
		exception.initCause(cause);
		return exception;
	}
}