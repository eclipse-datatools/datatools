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

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.eclipse.datatools.enablement.ibm.util.CommonOptions;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public interface  WrapperConfigData {
   /**
   * Returns the XML file name for this wrapper
   *
   * @return String of XML file names
   */
    public String getXMLFileName ();


   /**
   * Returns a DOM root node for navigating the DOM tree. The wrapperid idetifies
   * the XML file the DOM was build from.
   *
   * @return a DOM root node
   */
    public Node getRootNode ();

   /**
   * Returns an InputSource binary stream with the XML file.
   * This can be passed directly into the parser.
   *
   * @return InputSource bianry stream
   */
    public InputSource getBinaryXMLFile ();


   /**
   * Returns an InputSource binary stream with the DTD file.
   * This can be passed directly into the parser.
   *
   * @return InputSource bianry stream
   */
    public InputSource getBinaryDTDFile ();

 //====================SERVER==========================

   /**
   *  returns string "windows" | "aix" | "solaris" | "hpux" | "linux" | "unix"
   *
   * @return server platform type
   */
    public String getServerPlatform ();

   /**
   *  get a list of server type names
   *
   * @return Enumeration of server type names
   */
    public Enumeration getServerTypes()  throws Exception;

   /**
   * Get the server options in a hashtable. The hash key is the option name.
   * If there are no options an empty hashtable is returned.
   * <p>Hashtable key is a String - option name
   * <p>Hashtable value is a GUIOption - option
   * @return hashtable of GUIOption objects
   */
    public Hashtable getServerOptions()  throws Exception;

   /**
   * Get the server type versions
   *
   * @parm serverType the name of the type of server, returned by  getServerTypes
   * @return Vector of version numbers as Strings
   */
    public Vector getServerVersionsByType(String serverType)  throws Exception;

  /**
   * Get the server kinds name and description. The Name is the hash key ,
   * the description is hash value.
   * <p>Hashtable key is a String - name
   * <p>Hashtable value is a String - description
   * @return Hashtable key is server kind name , object is description
   */
    public  Hashtable getServerKinds()  throws Exception;

  /**
   *  Get the wrapper library name for the server's platform
   * @return String
   */
    public  String getLibname()  throws Exception;

 //====================WRAPPER===================


   /**
   * Get the Wrapper options in a hashtable. The hash key is the option name.
   * If there are no options an empty hashtable is returned.
   * <p>Hashtable key is a String - option name
   * <p>Hashtable value is a GUIOption - option
   * @return hashtable of GUIOption objects
   */
    public Hashtable getWrapperOptions()  throws Exception;

   /**
   *  Get the wrapper id
   * @return String
   */
    public  String getWrapperID()  throws Exception;

    /**
   *  Get the wrapper description
   * @return String
   */
    public  String getWrapperDescription()  throws Exception;

    /**
   *  Test if sample contents is supported for this wrapper.
   * @return returns true if supported
   */
    public  boolean isSampleContentsAvailable()  throws Exception;


    /**
   * Tests if userid is required for the wrapper.
   * @return true if required
   */
    public boolean isServerUseridRequired()  throws Exception;


 //====================DISCOVER===================
   /**
   * Get the discover options in a hashtable. The hash key is the option name.
   * If there are no options an empty hashtable is returned.
   * <p>Hashtable key is a String - option name
   * <p>Hashtable value is a GUIOption - option
   * @return hashtable of GUIOption objects
   */
    public Hashtable getDiscoverOptions()  throws Exception;

   /**
   * Get the discover options in a vector in the same order as in the XML file.
   * If there are no options an empty vector is returned.
   * @return vector of GUIOption objects
   */
    public Vector getDiscoverOptionsVector()  throws Exception;

   /**
   * Get the discover server options in a hashtable. The hash key is the option name.
   * If there are no options an empty hashtable is returned.
   * <p>Hashtable key is a String - option name
   * <p>Hashtable value is a GUIOption - option
   * @return hashtable of GUIOption objects
   */
    public Hashtable getDiscoverServerOptions()  throws Exception;

   /**
   * Get the discover server options in a vector in the same order as in the XML file.
   * If there are no options an empty vector is returned.
   * @return vector of GUIOption objects
   */
    public Vector getDiscoverServerOptionsVector()  throws Exception;

   /**
   * Get the discover parameters in a hashtable. The hash key is the parm name.
   *
   * <p>Hashtable key is a String - parm name
   * <p>Hashtable value is a String - parm value
   * @return hashtable of parm values
   */
    public Hashtable getDiscoverParms()  throws Exception;

   /**
   * Get the discover java class name for this wrapper.
   *
   * @return class name
   */
    public String getDiscoverClassName()  throws Exception;

   /**
   * Get the CommonOption class.
   *
   * @return CommonOptions  The commonOptions class or null if none defined.
   */
    public CommonOptions getCommonOptions()  throws Exception;

}

