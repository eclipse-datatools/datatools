/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

 /**
  *
  */
  public class ParseXMLFile implements EntityResolver, ErrorHandler {

    private  Node   rootNode = null;
    private  InputSource binDTDFile = null;
    private  InputSource binXMLFile = null;

    public ParseXMLFile (InputSource DTDFile, InputSource XMLFile, String rootNodeName ) throws SAXException, IOException, ParserConfigurationException {
        this.binDTDFile = DTDFile;
        this.binXMLFile = XMLFile;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setEntityResolver(this);
        builder.setErrorHandler( this);
        Document document = builder.parse(this.binXMLFile);
        rootNode = document.getDocumentElement();
        if (rootNodeName != null && !(rootNodeName.equals(rootNode.getNodeName())) ){      
           SAXException e = new SAXException("Not our XML file. Root is " + rootNode.getNodeName() + " but should be " + rootNodeName );
          // ServerdiscoveryPlugin.getDefault().trace("SAX Exception: "+e.toString());  //$NON-NLS-1$
         	
           throw e;
        }
  }

   /**
   * Returns a DOM root node for navigating the DOM tree. The wrapperid idetifies
   * the XML file the DOM was build from.
   *
   * @return a DOM root node
   */
    public Node getRootNode () {
      return rootNode;
    }

   /**
   * Set the DOM root node for navigating the DOM tree.
   *
   * @parm a DOM root node
   */
    public void setRootNode (Node n) {
      rootNode = n;
    }


   /**
   * Returns an InputSource binary stream with the XML file.
   * This can be passed directly into the parser.
   *
   * @return InputSource bianry stream
   */
    public InputSource getBinaryXMLFile () {
      return binXMLFile;
    }


   /**
   * Returns an InputSource binary stream with the DTD file.
   * This can be passed directly into the parser.
   *
   * @return InputSource bianry stream
   */
    public InputSource getBinaryDTDFile () {
      return binDTDFile;
    }
    
   /**
   * Sets the InputSource binary stream for the XML file.
   *
   * @parm InputSource bianry stream
   */
    public void setBinaryXMLFile (InputSource is) {
      binXMLFile = is;
    }


   /**
   * Sets the InputSource binary stream for the DTD file.
   *
   * @parm InputSource bianry stream
   */
    public void  setBinaryDTDFile (InputSource is) {
      binDTDFile = is;
    }


 /**
  * Find the specified attribute in the specified node and return its value.
  * If the attribute is not found, the default value is returned.
  *
  * @param  node  The node that contains the attribute.
  * @param  key  The attribute name.
  * @param  defV  The default value to return if the attribute is not found.
  * @return a String that contains the attribute value if the attribute was
  *     found or the default value if the attribute was not found.
  */
  public String findAttribute( Node node, String key, String defV ) {
    NamedNodeMap nnm = node.getAttributes();
    if( nnm == null ) {
      return defV;
    }
    for( int i = 0; i < nnm.getLength(); i++ ) {
      Node attr = nnm.item( i );
      if( key.equals( attr.getNodeName() ) ) {
        return attr.getNodeValue().trim();
      }
    }
    return defV;
  }

    //===========================ENTITY RESOLVER===========================
    /**
    * Use DTD binary InputSource for validation instead of trying to find file.
    *
    * @return binary DTD input source
    */
    public InputSource resolveEntity(java.lang.String publicId, java.lang.String systemId)
      throws SAXException, java.io.IOException {
       return binDTDFile;
    }

    //=================================ERROR HANDLER=============================
    public void error( SAXParseException e ) throws SAXException {
      //write parse error info to trace
   	
      String msg = new String("An XML Parser exception occured. Exception Text: "+e.getMessage());
      //String msg = MessageFormat.format( WTStringPool.get(WTStringPool.DJ_WCF_PARSE_ERROR), new Object[]{e.getMessage()} );
      
      SAXException err = new SAXException( msg); 
      //dump info to standard out
   // ServerdiscoveryPlugin.getDefault().trace("parse error. line number:"+e.getLineNumber()+" .column number:"+e.getColumnNumber()+". message:"+e.getMessage());  //$NON-NLS-1$
  	
      //dump stack to trace
      ByteArrayOutputStream baos =  new ByteArrayOutputStream();
      PrintStream errOut = new PrintStream(baos, true);
      e.printStackTrace(errOut);
//      System.out.println("error: "+new String(baos.toByteArray()));
     // ServerdiscoveryPlugin.getDefault().trace("parse error: "+new String(baos.toByteArray())+err);  //$NON-NLS-1$
      throw err;
    }

    public void fatalError( SAXParseException e ) throws SAXException {
      //write fatal parse error info to trace
  
        String msg = new String("An XML Parser exception occured. Exception Text: "+e.getMessage());
        SAXException err = new SAXException( msg);  
//dump info to standard out
   // ServerdiscoveryPlugin.getDefault().trace("fatal parse error. line number:"+e.getLineNumber()+" .column number:"+e.getColumnNumber()+". message:"+e.getMessage());  //$NON-NLS-1$
    	
      //dump stack to trace
      ByteArrayOutputStream baos =  new ByteArrayOutputStream();
      PrintStream errOut = new PrintStream(baos, true);
      e.printStackTrace(errOut);

     // ServerdiscoveryPlugin.getDefault().trace("fatal error: "+new String(baos.toByteArray())+err);  //$NON-NLS-1$
      
      throw err;
    }

    public void warning( SAXParseException e ) throws SAXException {
 	//ServerdiscoveryPlugin.getDefault().trace("warning parse error. line number:"+e.getLineNumber()+" .column number:"+e.getColumnNumber()+". message:"+e.getMessage());  //$NON-NLS-1$
    	
        //do not throw this warning exception, ignore it
    }
 }

