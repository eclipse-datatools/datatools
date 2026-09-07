package org.eclipse.datatools.connectivity;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.DOMImplementation;
import org.xml.sax.SAXException;

/**
 * @since 1.16
 */
public final class XMLUtil {
	private XMLUtil() {
	}

	/**
	 * Instantiate SAX parser and disable XML vectors.
	 *
	 * @return a new SAX parser
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public static SAXParser createSAXParser(boolean namespaceAware) throws ParserConfigurationException, SAXException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(namespaceAware);

		// Disable XML External Entity to avoid hack
		factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true); //$NON-NLS-1$
		factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false); //$NON-NLS-1$
		factory.setFeature("http://xml.org/sax/features/external-general-entities", false); //$NON-NLS-1$
		factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false); //$NON-NLS-1$
		return factory.newSAXParser();
	}

	/**
	 * Instantiate a new DocumentBuilderFactory and disable XML vectors.
	 *
	 * @return a new document builder factory
	 *
	 * @throws ParserConfigurationException
	 */
	public static DocumentBuilderFactory newDocumentBuilderFactory() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true); //$NON-NLS-1$
		factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false); //$NON-NLS-1$
		factory.setFeature("http://xml.org/sax/features/external-general-entities", false); //$NON-NLS-1$
		factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false); //$NON-NLS-1$
		return factory;
	}

	/**
	 * Instantiate a new DocumentBuilder with disabled XML vectors
	 *
	 * @return a new document builder.
	 *
	 * @throws ParserConfigurationException
	 */
	public static DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
		return newDocumentBuilderFactory().newDocumentBuilder();
	}

	/**
	 * Instantiate a new DocumentBuilder with disabled XML attack vector and with
	 * the given namespace awareness.
	 *
	 * @param namespaceAware the namespace awareness.
	 *
	 * @return a new document builder with the given namespace awareness.
	 *
	 * @throws ParserConfigurationException
	 */
	public static DocumentBuilder newDocumentBuilder(boolean namespaceAware) throws ParserConfigurationException {
		DocumentBuilderFactory factory = newDocumentBuilderFactory();
		factory.setNamespaceAware(namespaceAware);
		return factory.newDocumentBuilder();
	}

	/**
	 * Instantiate a new {@link DOMImplementation}.
	 *
	 * @return a new DOM implementation.
	 *
	 * @throws ParserConfigurationException
	 */
	public static DOMImplementation newDOMImplementation() throws ParserConfigurationException {
		return newDocumentBuilder().getDOMImplementation();
	}
}