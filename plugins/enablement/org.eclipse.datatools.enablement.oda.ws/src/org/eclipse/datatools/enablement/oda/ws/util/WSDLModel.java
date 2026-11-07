/*******************************************************************************
 * Copyright (c) 2026 Eclipse contributors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ws.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.datatools.connectivity.XMLUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The WSDL 1.1 information needed by the operation browser and SOAP templates.
 * XML documents are read with JAXP; external resources are loaded only through
 * explicit WSDL and XML Schema imports/includes, never through XML entities.
 */
public final class WSDLModel {
	public static final String WSDL_NS = "http://schemas.xmlsoap.org/wsdl/"; //$NON-NLS-1$
	public static final String SOAP_NS = WSDL_NS + "soap/"; //$NON-NLS-1$
	public static final String HTTP_NS = WSDL_NS + "http/"; //$NON-NLS-1$
	private static final String XSD_NS = XMLConstants.W3C_XML_SCHEMA_NS_URI;

	private WSDLModel() {
	}

	public static Definition read(String location) throws IOException {
		URI uri;
		try {
			uri = new URI(location);
			if (!uri.isAbsolute())
				uri = new File(location).toURI();
		} catch (URISyntaxException e) {
			uri = new File(location).toURI();
		}
		Definition definition = new Definition();
		Reader reader = new Reader(definition);
		reader.readWSDL(uri.normalize(), true);
		try {
			for (Binding binding : definition.bindings.values()) {
				binding.getPortType();
				for (Element element : children(binding.element, WSDL_NS, "operation")) //$NON-NLS-1$
					new BindingOperation(definition, element, binding).getOperation();
			}
			for (Operation operation : definition.operations()) {
				if (operation.getInput() != null)
					operation.getInput().getMessage();
				if (operation.getOutput() != null)
					operation.getOutput().getMessage();
			}
			for (Service service : definition.services.values())
				for (Port port : service.getPorts().values())
					port.getBinding();
		} catch (IllegalArgumentException e) {
			throw new IOException("Invalid WSDL " + location + ": " + e.getMessage(), e); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return definition;
	}

	public static List<Element> children(Element parent, String namespace, String name) {
		List<Element> result = new ArrayList<Element>();
		if (parent != null)
			for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling())
				if (node instanceof Element && namespace.equals(node.getNamespaceURI())
						&& name.equals(node.getLocalName()))
					result.add((Element) node);
		return result;
	}

	public static Element child(Element parent, String namespace, String name) {
		List<Element> elements = children(parent, namespace, name);
		return elements.isEmpty() ? null : elements.get(0);
	}

	public static QName qname(Element element, String attribute) {
		String value = element.getAttribute(attribute).trim();
		if (value.isEmpty())
			return null;
		int colon = value.indexOf(':');
		String prefix = colon < 0 ? null : value.substring(0, colon);
		String namespace = element.lookupNamespaceURI(prefix);
		if (colon >= 0 && namespace == null)
			throw new IllegalArgumentException("Undeclared QName prefix: " + value); //$NON-NLS-1$
		return new QName(namespace == null ? XMLConstants.NULL_NS_URI : namespace, value.substring(colon + 1));
	}

	private static <T> T reference(Map<QName, T> values, Element element, String attribute) {
		QName name = qname(element, attribute);
		T value = values.get(name);
		if (value == null)
			throw new IllegalArgumentException("Unresolved " + attribute + ": " + name); //$NON-NLS-1$ //$NON-NLS-2$
		return value;
	}

	private static final class Reader {
		private final Definition definition;
		private final Map<URI, Element> documents = new LinkedHashMap<URI, Element>();
		private final Set<URI> wsdls = new LinkedHashSet<URI>();
		private final Set<Element> schemas = new LinkedHashSet<Element>();

		private Reader(Definition definition) {
			this.definition = definition;
		}

		private Element document(URI uri) throws IOException {
			Element existing = documents.get(uri);
			if (existing != null)
				return existing;
			try {
				DocumentBuilderFactory factory = XMLUtil.newDocumentBuilderFactory();
				factory.setNamespaceAware(true);
				factory.setXIncludeAware(false);
				factory.setExpandEntityReferences(false);
				factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, ""); //$NON-NLS-1$
				factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, ""); //$NON-NLS-1$
				DocumentBuilder builder = factory.newDocumentBuilder();
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
				try (InputStream stream = uri.toURL().openStream()) {
					Element root = builder.parse(stream, uri.toString()).getDocumentElement();
					documents.put(uri, root);
					return root;
				}
			} catch (ParserConfigurationException | SAXException | IllegalArgumentException e) {
				throw new IOException("Cannot read XML " + uri + ": " + e.getMessage(), e); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}

		private URI resolve(Element element, String location) throws IOException {
			try {
				return new URI(element.getBaseURI()).resolve(new URI(location)).normalize();
			} catch (URISyntaxException | IllegalArgumentException e) {
				throw new IOException("Invalid import location: " + location, e); //$NON-NLS-1$
			}
		}

		private void readWSDL(URI uri, boolean primary) throws IOException {
			if (!wsdls.add(uri))
				return;
			Element root = document(uri);
			if (!WSDL_NS.equals(root.getNamespaceURI()) || !"definitions".equals(root.getLocalName())) //$NON-NLS-1$
				throw new IOException("Not a WSDL 1.1 document: " + uri); //$NON-NLS-1$
			String namespace = root.getAttribute("targetNamespace"); //$NON-NLS-1$
			if (primary)
				definition.targetNamespace = namespace;
			for (Element element : children(root, WSDL_NS, "message")) //$NON-NLS-1$
				definition.messages.put(name(namespace, element), new Message(element));
			for (Element element : children(root, WSDL_NS, "portType")) //$NON-NLS-1$
				definition.portTypes.put(name(namespace, element), new PortType(definition, element));
			for (Element element : children(root, WSDL_NS, "binding")) //$NON-NLS-1$
				definition.bindings.put(name(namespace, element), new Binding(definition, element));
			if (primary)
				for (Element element : children(root, WSDL_NS, "service")) //$NON-NLS-1$
					definition.services.put(name(namespace, element),
							new Service(definition, element, name(namespace, element)));
			for (Element types : children(root, WSDL_NS, "types")) //$NON-NLS-1$
				for (Element schema : children(types, XSD_NS, "schema")) //$NON-NLS-1$
					readSchema(schema);
			for (Element element : children(root, WSDL_NS, "import")) //$NON-NLS-1$
				if (element.hasAttribute("location")) //$NON-NLS-1$
					readWSDL(resolve(element, element.getAttribute("location")), false); //$NON-NLS-1$
		}

		private void readSchema(Element schema) throws IOException {
			if (!XSD_NS.equals(schema.getNamespaceURI()) || !"schema".equals(schema.getLocalName())) //$NON-NLS-1$
				throw new IOException("Not an XML Schema: " + schema.getBaseURI()); //$NON-NLS-1$
			if (!schemas.add(schema))
				return;
			definition.schemas.add(schema);
			for (String kind : Arrays.asList("import", "include", "redefine")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				for (Element element : children(schema, XSD_NS, kind))
					if (!element.getAttribute("schemaLocation").isEmpty()) //$NON-NLS-1$
						readSchema(document(resolve(element, element.getAttribute("schemaLocation")))); //$NON-NLS-1$
		}

		private QName name(String namespace, Element element) {
			return new QName(namespace, element.getAttribute("name")); //$NON-NLS-1$
		}
	}

	public static final class Definition {
		private String targetNamespace;
		private final Map<QName, Service> services = new LinkedHashMap<QName, Service>();
		private final Map<QName, Binding> bindings = new LinkedHashMap<QName, Binding>();
		private final Map<QName, PortType> portTypes = new LinkedHashMap<QName, PortType>();
		private final Map<QName, Message> messages = new LinkedHashMap<QName, Message>();
		private final List<Element> schemas = new ArrayList<Element>();

		public String getTargetNamespace() {
			return targetNamespace;
		}

		public Map<QName, Service> getServices() {
			return Collections.unmodifiableMap(services);
		}

		public Service getService(QName name) {
			return services.get(name);
		}

		public Element[] getSchemas() {
			return schemas.toArray(new Element[0]);
		}

		private List<Operation> operations() {
			List<Operation> result = new ArrayList<Operation>();
			for (PortType portType : portTypes.values())
				result.addAll(portType.getOperations());
			return result;
		}
	}

	public static class Component {
		final Definition definition;
		final Element element;

		private Component(Definition definition, Element element) {
			this.definition = definition;
			this.element = element;
		}

		public String getName() {
			return element.getAttribute("name"); //$NON-NLS-1$
		}

		public Element getSOAPElement(String name) {
			return child(element, SOAP_NS, name);
		}
	}

	public static final class Service extends Component {
		private final QName name;
		private final Map<String, Port> ports = new LinkedHashMap<String, Port>();

		private Service(Definition definition, Element element, QName name) {
			super(definition, element);
			this.name = name;
			for (Element port : children(element, WSDL_NS, "port")) //$NON-NLS-1$
				ports.put(port.getAttribute("name"), new Port(definition, port)); //$NON-NLS-1$
		}

		public QName getQName() {
			return name;
		}

		public Map<String, Port> getPorts() {
			return Collections.unmodifiableMap(ports);
		}

		public Port getPort(String name) {
			return ports.get(name);
		}
	}

	public static final class Port extends Component {
		private Port(Definition definition, Element element) {
			super(definition, element);
		}

		public Binding getBinding() {
			return reference(definition.bindings, element, "binding"); //$NON-NLS-1$
		}

		public Element getHTTPAddress() {
			return child(element, HTTP_NS, "address"); //$NON-NLS-1$
		}
	}

	public static final class Binding extends Component {
		private Binding(Definition definition, Element element) {
			super(definition, element);
		}

		public PortType getPortType() {
			return reference(definition.portTypes, element, "type"); //$NON-NLS-1$
		}

		public BindingOperation getBindingOperation(String name, String input, String output) {
			for (Element operation : children(element, WSDL_NS, "operation")) //$NON-NLS-1$
				if (name.equals(operation.getAttribute("name")) //$NON-NLS-1$
						&& matches(operation, "input", input) && matches(operation, "output", output)) //$NON-NLS-1$ //$NON-NLS-2$
					return new BindingOperation(definition, operation, this);
			return null;
		}
	}

	private static boolean matches(Element operation, String direction, String name) {
		Element message = child(operation, WSDL_NS, direction);
		return name == null || message != null && name.equals(message.getAttribute("name")); //$NON-NLS-1$
	}

	public static final class PortType extends Component {
		private PortType(Definition definition, Element element) {
			super(definition, element);
		}

		public List<Operation> getOperations() {
			List<Operation> result = new ArrayList<Operation>();
			for (Element operation : children(element, WSDL_NS, "operation")) //$NON-NLS-1$
				result.add(new Operation(definition, operation));
			return result;
		}
	}

	public static final class BindingOperation extends Component {
		private final Binding binding;

		private BindingOperation(Definition definition, Element element, Binding binding) {
			super(definition, element);
			this.binding = binding;
		}

		public Operation getOperation() {
			Element input = child(element, WSDL_NS, "input"); //$NON-NLS-1$
			Element output = child(element, WSDL_NS, "output"); //$NON-NLS-1$
			for (Operation operation : binding.getPortType().getOperations())
				if (getName().equals(operation.getName())
						&& matches(operation.element, "input", input == null ? null : optionalName(input)) //$NON-NLS-1$
						&& matches(operation.element, "output", output == null ? null : optionalName(output))) //$NON-NLS-1$
					return operation;
			throw new IllegalArgumentException("Unresolved operation: " + getName()); //$NON-NLS-1$
		}

		private String optionalName(Element element) {
			return element.hasAttribute("name") ? element.getAttribute("name") : null; //$NON-NLS-1$ //$NON-NLS-2$
		}

		public Element getBindingInput() {
			return child(element, WSDL_NS, "input"); //$NON-NLS-1$
		}
	}

	public static final class Operation extends Component {
		private Operation(Definition definition, Element element) {
			super(definition, element);
		}

		public Element getDocumentationElement() {
			return child(element, WSDL_NS, "documentation"); //$NON-NLS-1$
		}

		public List<String> getParameterOrdering() {
			String order = element.getAttribute("parameterOrder").trim(); //$NON-NLS-1$
			return order.isEmpty() ? null : Arrays.asList(order.split("\\s+")); //$NON-NLS-1$
		}

		public MessageReference getInput() {
			return message("input"); //$NON-NLS-1$
		}

		public MessageReference getOutput() {
			return message("output"); //$NON-NLS-1$
		}

		private MessageReference message(String direction) {
			Element message = child(element, WSDL_NS, direction);
			return message == null ? null : new MessageReference(definition, message);
		}
	}

	public static final class MessageReference extends Component {
		private MessageReference(Definition definition, Element element) {
			super(definition, element);
		}

		public Message getMessage() {
			return reference(definition.messages, element, "message"); //$NON-NLS-1$
		}
	}

	public static final class Message {
		private final Map<String, Part> parts = new LinkedHashMap<String, Part>();

		private Message(Element element) {
			for (Element part : children(element, WSDL_NS, "part")) //$NON-NLS-1$
				parts.put(part.getAttribute("name"), new Part(part)); //$NON-NLS-1$
		}

		public List<Part> getOrderedParts(List<?> order) {
			Map<String, Part> remaining = new LinkedHashMap<String, Part>(parts);
			List<Part> result = new ArrayList<Part>();
			if (order != null)
				for (Object name : order) {
					Part part = remaining.remove(name);
					if (part != null)
						result.add(part);
				}
			result.addAll(remaining.values());
			return result;
		}
	}

	public static final class Part {
		private final Element element;

		private Part(Element element) {
			this.element = element;
		}

		public String getName() {
			return element.getAttribute("name"); //$NON-NLS-1$
		}

		public QName getTypeName() {
			return qname(element, "type"); //$NON-NLS-1$
		}

		public QName getElementName() {
			return qname(element, "element"); //$NON-NLS-1$
		}
	}
}
