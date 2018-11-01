/*******************************************************************************
 * Copyright (c) 2005 Exadel Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Exadel Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SQLUtility {
	
    //TODO: add parameters to this method since profile name is not enough
	public static ISQLEditorConnectionInfo getConnectionInfo(String profileName) {
        ISQLEditorConnectionInfo connection = null;        
        connection = new SQLEditorConnectionInfo(null, profileName, null);
        return connection;
    }
	
	
	public static String getOutputSQLPageXML(String inStatement, Map map) {
        // tau 27.06.04 06.07.04

		org.w3c.dom.Document doc = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        DOMImplementation impl = builder.getDOMImplementation();
        doc = impl.createDocument(null, "SQLPage", null);
        
        Element root = doc.getDocumentElement();
		
		for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
			Object element = it.next();
			if (element instanceof Map.Entry) {
				String key = (String) ((Map.Entry) element).getKey();
				String value = (String) ((Map.Entry) element).getValue();
		        Attr attr = doc.createAttribute(key);
		        attr.setValue(value);
		        root.setAttributeNode(attr);				
			} 
		} 

		/*
        String userid = "";
        String driver = "";
        String url = "";
        String password = "";
        
        if (rdbConnection != null) {
            userid = rdbConnection.getUserName();
            driver = rdbConnection.getDriverClassName();
            url = rdbConnection.getURL();
            password = rdbConnection.getPassword();
        }
        
        Attr attr = doc.createAttribute("userid");
        attr.setValue(userid);
        root.setAttributeNode(attr);
        
        attr = doc.createAttribute("driver");
        attr.setValue(driver);
        root.setAttributeNode(attr);
        
        attr = doc.createAttribute("url");
        attr.setValue(url);
        root.setAttributeNode(attr);
        
        attr = doc.createAttribute("password");
        attr.setValue(password);
        root.setAttributeNode(attr);
        
        */
        
        Element item = doc.createElement("Statement");       // Create element        

        if (inStatement == null) inStatement = "";
        item.appendChild( doc.createCDATASection(inStatement) );        
        root.appendChild( item );
        
        StringWriter stringOut = new StringWriter();        //Writer will be a String

        
        //       Serialisation through Tranform. add tau 06.04.07
        DOMSource domSource = new DOMSource(doc);
        
        StreamResult streamResult = new StreamResult(stringOut);
        
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = null;
        
        try {
            serializer = tf.newTransformer();
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //serializer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
        serializer.setOutputProperty(OutputKeys.INDENT,"yes");
        
        try {
            serializer.transform(domSource, streamResult);
        } catch (TransformerException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        } 
    
        return stringOut.toString();
    }
	
	public static NodeSQLPage getInputSQLPageXML(File fileXMLin) {
		org.w3c.dom.Document xmlDocument = null;
		NodeSQLPage nodeSQLPage = null;
		
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
                
			xmlDocument = builder.parse(fileXMLin);
		
            Element xmlElement = xmlDocument.getDocumentElement();
		
			if (!xmlElement.getNodeName().equals("SQLPage")){
				return null;
			}

			NodeSQLPage new_nodeSQLPage = new NodeSQLPage();
			new_nodeSQLPage.setNameConnection(xmlElement.getAttribute("nameConnection"));
                
            NodeList nodeList = xmlElement.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE &&
                    node.getNodeName().equals("Statement")) {
                    Element statementElement = (Element) node;
                    NodeList statementNodeList = statementElement.getChildNodes();
                    for (int j = 0; j < statementNodeList.getLength(); j++) {
                        Node nodeSQL = statementNodeList.item(j);
                        if (nodeSQL.getNodeType() == Node.CDATA_SECTION_NODE) {
							new_nodeSQLPage.setStatementSQL(nodeSQL.getNodeValue());
                            break;
                        }
                    }                       
                    break;
                }
            }
			
			nodeSQLPage = new_nodeSQLPage;
                
        } catch (FactoryConfigurationError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            //e.printStackTrace(); tau 05.07.04
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		return nodeSQLPage;
		
	}

}
