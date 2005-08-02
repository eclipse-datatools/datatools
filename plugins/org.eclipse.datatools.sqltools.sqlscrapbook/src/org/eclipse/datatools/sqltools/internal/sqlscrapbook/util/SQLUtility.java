/*******************************************************************************
 * Copyright (c) 2005 Exadel Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

import org.eclipse.datatools.connectivity.core.internal.ui.explorer.virtual.IConnectionNode;
import org.eclipse.datatools.connectivity.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.server.internal.ui.explorer.content.ServerExplorerConfiguration;
import org.eclipse.datatools.connectivity.server.internal.ui.services.IServerExplorerContentService;
import org.eclipse.datatools.connectivity.server.internal.ui.services.IServicesManager;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SQLUtility {
	
	public static ConnectionInfo getConnectionInfo(String nameConnection) {
        IServerExplorerContentService service = IServicesManager.INSTANCE.getServerExplorerContentService();
				
        // Service is not yet initialized
        // TODO reconnect at later time
        if (service == null) {
            return null;
        }
		
        Object[] servers = service.getKnownServerNode().getChildrenArray();
        ConnectionInfo connection = null;        
        
        for (int i = 0; i < servers.length; i++) {
            IConnectionNode node = (IConnectionNode) servers[i];
            ConnectionInfo info = node.getConnectionInfo();
            
            if (info.getName().equals(nameConnection)) {
                connection = node.getConnectionInfo();
				if (!node.isConnected()){
					//service.reconnectServer(new IConnectionNode[] { node });
					//service.connectServer(node);
					//node.setConnected(true);
			        new ServerExplorerConfiguration().restoreConnectionInfo(node, node.getConnectionInfo());
					
				}
				break;
            }

        }
        
        if (connection == null) {
            // tau - get connectionWizard? - No. 20.03.2005 tau
        }
        return connection;
    }
	
    // 01.07.04 tau - add 16.03.2005
    public static ConnectionInfo getConnectionInfo(String userid, String url, String driver, String password) {
        IServerExplorerContentService service = IServicesManager.INSTANCE.getServerExplorerContentService();
        // Service is not yet initialized
        // TODO reconnect at later time
        if (service == null) {
            return null;
        }
        Object[] servers = service.getKnownServerNode().getChildrenArray();
        ConnectionInfo connection = null;        
        
        for (int i = 0; i < servers.length; i++) {
            IConnectionNode node = (IConnectionNode) servers[i];
            ConnectionInfo info = node.getConnectionInfo();
            
            if (info.getUserName().equals(userid) &&
                    info.getURL().equals(url)       &&
                    info.getDriverClassName().equals(driver)) {
                
                connection = node.getConnectionInfo(); 
                break;
            }
        }
        
        if (connection == null) {
            // tau - get connectionWizard? - No. 20.03.2005 tau
        }
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
		
		Iterator itMap = map.entrySet().iterator();
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
		
			String sQLPage = xmlElement.getNodeName(); // add tau 16.03.2005
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
