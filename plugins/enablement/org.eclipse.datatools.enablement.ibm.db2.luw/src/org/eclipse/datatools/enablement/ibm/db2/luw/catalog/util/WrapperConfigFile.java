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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.util.CommonOptions;
import org.eclipse.datatools.enablement.ibm.util.ConfigMessage;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
 
public class WrapperConfigFile implements WrapperConfigData {
    public static Vector instances = new Vector();     // Keep track of all objects to track storage use

    private static final String rootNodeName = "wrapper";    // The root node of our files
    private  Node   rootNode = null;
    private  String XMLFileName = null;
    private  ParseXMLFile parser = null;
    private  Hashtable textBundle = null;
    private  boolean hasPropertiesFile = false;

    //wrapper
    private boolean wrapperParsed = false;
    private  String wid = null;
    private  String libname = null;
    private boolean sample_contents = false;
    private boolean userid_required = false;
    private String wrapperDescription = "";
    private Hashtable wrapperoptionnameToOption = null;
    private Vector vecWrapperoptionnameToOption = null;  
    private Hashtable wrapperLibraryToPlatform = null;   
    private Hashtable optionnameToKey = null;          

    //server
    private boolean serverParsed = false;
    private String serverPlatform = null;
    private boolean fUnixServer = false;
    private boolean allServerTypesVersionsSameDataTypes = true;  
    private Hashtable serverkindnameToDescription = null;
    private Hashtable serveroptionnameToOption = null;
    private Vector vecServeroptionnameToOption = null;    
    private Hashtable servertypenameToVersionlist = null;  //contains vectors of versions
    private Hashtable servertypenameandversionToDatatype = null;  //contains vectors of datatypes  

    //discover
    private boolean discoverParsed = false;
    private  String discoverClassName = "";
    private Hashtable discoveroptionnameToOption = null;
    private Hashtable discoverServerOptions = null;
    private Vector  vDiscoverOptions = null;
    private Vector  vDiscoverServerOptions = null;
    private Hashtable discoverparmnameToParmvalue = null;
    private Vector  vDiscoverParms = null;
//    private Vector  vGenericFunctions = null;

    //column
//    private boolean columnParsed = false;
//    private Vector vColumnDatatypes = null;
//    private Hashtable columnoptionnameToOption = null;

    //user
    private boolean userParsed = false;
    private Hashtable useroptionnameToOption = null;
    private String usersMappingType = "optional";


    //common options
    private boolean commonOptionsParsed = false;
    private CommonOptions commonOptions = null;

    //generic functions
//    private boolean genericFunctionsParsed = false;
//    private Vector genericFunctions = null;

    //enumeration values
    public static final String SERVER_PLATFORM_AIX =     "aix";
    public static final String SERVER_PLATFORM_WINDOWS = "windows";
    public static final String SERVER_PLATFORM_SOLARIS = "solaris" ;
    public static final String SERVER_PLATFORM_LINUX =   "linux";
    public static final String SERVER_PLATFORM_UNIX =    "unix";
    public static final String SERVER_PLATFORM_HPUX =    "hpux";


    public static final String OPTION_PLATFORM_AIX =     "aix";
    public static final String OPTION_PLATFORM_WINDOWS = "windows";
    public static final String OPTION_PLATFORM_SOLARIS = "solaris" ;
    public static final String OPTION_PLATFORM_LINUX =   "linux";
    public static final String OPTION_PLATFORM_HPUX =    "hpux";
    public static final String OPTION_PLATFORM_ALLUNIX = "all_unix";
    public static final String OPTION_PLATFORM_ALL =     "all";

    public static final String NICKNAME_DDLSYNTAX_WITHCOL =    "with_columns";
    public static final String NICKNAME_DDLSYNTAX_WITHOUTCOL = "without_columns";
    public static final String NICKNAME_DDLSYNTAX_EITHER =     "either ";

    public static final String ATTRIBUTE_VALUE_YES =     "Y";
    public static final String ATTRIBUTE_VALUE_NO  =     "N";

    public static final String USER_MAPPING_NOTSUPPORT  =   "not_supported";
    public static final String USER_MAPPING_OPTIONAL  =     "optional";
    public static final String USER_MAPPING_REQUIRED  =     "required";

    public WrapperConfigFile() {
      //TODO! >> Farnaz 
      //CommonUtils.removeFreedReferences(instances);      // Remove any references to dialogs that have been freed  
      instances.addElement( new WeakReference(this) );   // Keep track of the dialog via a WeakReference           
    }

   /**
   *  Connects to a database on the db server and runs the
   *  stored procedure to retrieve the xml file as a blob.
   *  The server platform is also retrieved.
   *
   * @param  wrapperID
   * @param db2_udb.Database the database object for connecting
   */
    public void initFile (String wrapperID, LUWDatabase aDatabase) throws Exception {  //Farnaz
      String spTrace = null;
      Thread.dumpStack();
      wid = wrapperID;
      try{
        //Context context = new Context();  //Farnaz
		ConnectionInfo conInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(aDatabase);

      	Connection conn = conInfo.getSharedConnection(); //Farnaz
      	//Connection conn = aDatabase.getConnection(context);  //Farnaz
        CallableStatement callStmt = conn.prepareCall( "CALL GET_WRAP_CFG_C(?,?,?,?,?)" ); 
        callStmt.setString(1, wrapperID);
        String clientLocale = Locale.getDefault().toString();
        if(clientLocale.length() > 5){
          //shorten to 5 character representation
          clientLocale = clientLocale.substring(0,5);
        } else if(clientLocale.length() < 5){
          //if locale is other varriant use English
          clientLocale = "en_US";
        }
        callStmt.setString(2, clientLocale);
        callStmt.registerOutParameter(3, Types.VARCHAR);
        callStmt.registerOutParameter(4, Types.VARCHAR);
        callStmt.registerOutParameter(5, Types.INTEGER);
        callStmt.execute();
        int returncode =  callStmt.getInt(5);
        if(returncode != 0){
          //error returned from SP
          String msg = new String ("The GET_WRAP_CFG_C stored procedure returned an error. Return  code "+new String(String.valueOf(returncode))+", wrapper id: "+wrapperID+", locale: "+clientLocale);
            
          //String msg = MessageFormat.format( WTStringPool.get(WTStringPool.DJ_WCF_SP_ERROR), new Object[]{new String(String.valueOf(returncode)), wrapperID, clientLocale} );
          String sp_msg = getSPMessage(returncode);
          Exception err = new Exception( msg +" "+ sp_msg);
     //     ServerdiscoveryPlugin.getDefault().trace("SP Msg!=0:"+err.toString());  //$NON-NLS-1$

          throw err;
        }
        serverPlatform =  callStmt.getString(3);
        spTrace =  callStmt.getString(4);
//        System.out.println(spTrace);   //DEBUG
//        System.out.println("initFile: stored procedure trace: "+spTrace);
     //   ServerdiscoveryPlugin.getDefault().trace("Stored procedure trace:"+spTrace);  //$NON-NLS-1$

        ResultSet rs = callStmt.getResultSet();
        
        int count = 1;
        InputSource binDTDFile = null;
        InputSource binXMLFile = null;
        while( rs.next() ) {
          if(count == 1){
            ByteArrayInputStream  bais = new ByteArrayInputStream(rs.getBytes(2));
            binDTDFile = new InputSource(bais);
            count++;
          } else {
            XMLFileName = rs.getString(1);
            //XML file BLOB
            Blob blob = rs.getBlob(2);
            byte[] b1 =  blob.getBytes(1,(int)blob.length());   
            ByteArrayInputStream  bais = new ByteArrayInputStream(b1, 0, b1.length);
            binXMLFile = new InputSource(bais);
            //properties file BLOB 
            Blob propertiesFile = rs.getBlob(3);
            if(propertiesFile != null){
              byte[] b2 =  propertiesFile.getBytes(1,(int)propertiesFile.length());  
              ByteArrayInputStream  prop_bais = new ByteArrayInputStream(b2, 0, b2.length);
              InputStreamReader prop_isr = new InputStreamReader(prop_bais, "UTF-8");
              textBundle = buildResources(prop_isr);
              hasPropertiesFile = true;
            }
          }
        }

        //parse XML file and save root node
        this.parser = new ParseXMLFile(binDTDFile, binXMLFile, rootNodeName);
        this.rootNode = parser.getRootNode();

        rs.close();
        callStmt.close();
        //conn.close();
       } catch (Exception e){
       	// ServerdiscoveryPlugin.getDefault().trace("initFile Exception:"+e.toString());  //$NON-NLS-1$

         throw e;
       }
    }


   /**
   *  Connects to a database on the db server and runs the
   *  stored procedure to retrieve all the xml files as blobs.
   *  The server platform is also retrieved.
   *
   * @param db2_udb.Database the database object for connecting
   * @return Hashtable of WrapperConfigFile objects
   */
    public static Hashtable initAllFiles (LUWDatabase aDatabase) throws Exception { //Farnaz
      Hashtable h = new Hashtable();
      String spTrace = null;
      try{ 
      	
      	CallableStatement callStmt = null;
      	String clientLocale = null;

		ConnectionInfo conInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(aDatabase);
        Connection conn = conInfo.getSharedConnection(); //Farnaz

      	try {
        callStmt = conn.prepareCall( "CALL GET_WRAP_CFG_C(?,?,?,?,?)");
      	} 
      	catch (SQLException e)
		{
      		e.printStackTrace();
		}
      	callStmt.setString(1, "");
        clientLocale = Locale.getDefault().toString();
        if(clientLocale.length() > 5){
          //shorten to 5 character representation
          clientLocale = clientLocale.substring(0,5);
        } else if(clientLocale.length() < 5){
          //if locale is other varriant use English
          clientLocale = "en_US";
        }
        callStmt.setString(2, clientLocale);
        callStmt.registerOutParameter(3, Types.VARCHAR);
        callStmt.registerOutParameter(4, Types.VARCHAR);
        callStmt.registerOutParameter(5, Types.INTEGER);
        callStmt.execute();
      	
        int returncode =  callStmt.getInt(5);
        if(returncode != 0){
          //error returned from SP
          String msg = new String ("The GET_WRAP_CFG_C stored procedure returned an error. Return  code "+new String(String.valueOf(returncode))+", locale: "+clientLocale);
            
          //String msg = MessageFormat.format( WTStringPool.get(WTStringPool.DJ_WCF_SP_ERROR_ALL), new Object[]{new String(String.valueOf(returncode)), clientLocale} );
          String sp_msg = getSPMessage(returncode);
          Exception err = new Exception( msg + " "+ sp_msg);
        //  ServerdiscoveryPlugin.getDefault().trace("SP Message = "+err.toString());  //$NON-NLS-1$

          throw err;
        }
        String tempServerPlatform = callStmt.getString(3);
        spTrace =  callStmt.getString(4);
        ResultSet rs = callStmt.getResultSet();
       int count = 1;
//        byte[] bDTD = null;

        InputSource binDTDFile = null;
        InputSource binXMLFile = null;
        ByteArrayInputStream  baisDTD = null;
        while( rs.next() ) {
          if(count == 1){
            baisDTD = new ByteArrayInputStream(rs.getBytes(2));
            binDTDFile = new InputSource(baisDTD);
            count++;
          } else {
            WrapperConfigFile wcf = new WrapperConfigFile();
            wcf.setServerPlatform(tempServerPlatform);
            String filename = rs.getString(1);
            wcf.setXMLFileName(filename);
            wcf.setWrapperID(filename.substring(0, filename.length()-4));  //remove ".xml"

            //xml file BLOB
            Blob blob = rs.getBlob(2);
            byte[] b1 =  blob.getBytes(1,(int)blob.length());    
            ByteArrayInputStream  bais = new ByteArrayInputStream(b1, 0, b1.length);
            binXMLFile =  new InputSource(bais);

            //properties file BLOB
            Blob propertiesFile = rs.getBlob(3);
            if(propertiesFile != null ){
              byte[] b2 =  propertiesFile.getBytes(1,(int)propertiesFile.length());   
              ByteArrayInputStream  prop_bais = new ByteArrayInputStream(b2, 0, b2.length);
              InputStreamReader prop_isr = new InputStreamReader(prop_bais, "UTF-8");
              Hashtable t = wcf.buildResources(prop_isr);
              wcf.setTextBundle(t);
              wcf.setHasTextBundle(true);
            }
            try{
              //parse XML file and save root node
              wcf.setXMLParser( new ParseXMLFile(binDTDFile, binXMLFile, rootNodeName));
              wcf.setRootNode( wcf.getXMLParser().getRootNode());
              //add to hash table
              h.put( wcf.getWrapperID() , wcf);
            }catch(SAXException parseError){   
          	parseError.printStackTrace();
            }
          }
        }
        rs.close();
        callStmt.close();
        //conn.close();
       } catch (Exception e){
         throw e;
       }
      return h;
    }

   /**
   * Passes a XML file, a dtd file, and a peoperties file to parser.
   * @param  XMLFile
   * @param  DTDFile
   * @param  propertiesFile
   */
   public void initWithXMLFile(File XMLFile, File DTDFile, File propertiesFile)throws Exception
   {
          InputSource binDTDFile = null;
          InputSource binXMLFile = null;

          FileInputStream   fis_xml = new FileInputStream(XMLFile);
          InputStreamReader isr_xml = new InputStreamReader (fis_xml, "UTF-8");

          BufferedReader xmlReader = new BufferedReader(isr_xml);
         // binXMLFile = new InputSource(isr_xml);  // xml file input
          binXMLFile = new InputSource(xmlReader);  // xml file input

          FileInputStream   fis_dtd = new FileInputStream(DTDFile);
          InputStreamReader isr_dtd = new InputStreamReader (fis_dtd); //always english

          BufferedReader dtdReader = new BufferedReader(isr_dtd);

        //  binDTDFile = new InputSource(isr_dtd);
          binDTDFile = new InputSource(dtdReader);

           //parse XML
           this.parser = new ParseXMLFile(binDTDFile, binXMLFile, rootNodeName);
           this.rootNode = parser.getRootNode();

           //get strings from properties file
           if(propertiesFile != null){
              FileInputStream   fis_prop = new FileInputStream(propertiesFile );
              InputStreamReader isr_prop = new InputStreamReader (fis_prop, "UTF-8");
              textBundle = buildResources(isr_prop);
              hasPropertiesFile = true;
           }
    } 

   /**
   * Returns the XML file name for this wrapper
   *
   * @return String of XML file names
   */
    public String getXMLFileName () {
       return XMLFileName;
    }

   /**
   * Returns the XML file name for this wrapper
   *
   * @parm String of XML file names
   */
    public void setXMLFileName (String f) {
      XMLFileName = f;
    }

   /**
   * Returns the ParseXMLFile object
   *
   * @return ParseXMLFile
   */
    public ParseXMLFile getXMLParser () {
       return parser;
    }

   /**
   * sets the ParseXMLFile object
   *
   * @parm String of XML file names
   */
    public void setXMLParser (ParseXMLFile p) {
      parser = p;
    }

   /**
   * Returns the PropertyResourceBundle object
   *
   * @return PropertyResourceBundle
   */
    public boolean hasTextBundle () {
      return hasPropertiesFile;
    }
   /**
   * Returns the Hashtable object
   *
   * @return Hashtable
   */
    public Hashtable getTextBundle () {   
       return textBundle;
    }

   /**
   * sets the Hashtable object
   *
   * @parm Hashtable
   */
    public void setTextBundle (Hashtable p) {
      textBundle = p;
    }


   /**
   * sets the text bundle flag
   *
   * @parm boolean
   */
    public void setHasTextBundle (boolean t) {
      hasPropertiesFile = t;
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
      return parser.getBinaryXMLFile();
    }


   /**
   * Returns an InputSource binary stream with the DTD file.
   * This can be passed directly into the parser.
   *
   * @return InputSource bianry stream
   */
    public InputSource getBinaryDTDFile () {
      return parser.getBinaryDTDFile();
    }

 //====================SERVER==========================

   /**
   *  returns string "windows" | "aix" | "solaris" | "hpux" | "linux" | "unix"
   *
   * @return server platform type
   */
    public String getServerPlatform () {
       return serverPlatform;
    }

   /**
   *  set server platform to: string "windows" | "aix" | "solaris" | "hpux" | "linux" | "unix"
   *
   * @parm server platform type
   */
    public void setServerPlatform (String sp) {
       serverPlatform = sp;
    }

   /**
   *  get a list of server type names
   *
   * @return Enumeration of server type names
   */
    public Enumeration getServerTypes()  throws Exception{
      if(!serverParsed){
         getServer();
       }
       return servertypenameToVersionlist.keys();
    }

   /**
   * Get the server options in a hashtable. The hash key is the option name.
   * If there are no options an empty hashtable is returned.
   * <p>Hashtable key is a String - option name
   * <p>Hashtable value is a GUIOption - option
   * @return hashtable of GUIOption objects
   */
    public Hashtable getServerOptions()  throws Exception{
       if(!serverParsed){
         getServer();
       }
       return serveroptionnameToOption;
    }

   /**
   * Get the server options in a vector.
   * If there are no options an empty vector is returned.
   * @return vector of GUIOption objects
   */
    public Vector getServerOptionsVector()  throws Exception{
       if(!serverParsed){
         getServer();
       }
       return vecServeroptionnameToOption;
    }

   /**
   * Get the server type versions
   *
   * @parm serverType the name of the type of server, returned by  getServerTypes
   * @return Vector of version numbers as Strings
   */
    public Vector getServerVersionsByType(String serverType)  throws Exception{
       if(!serverParsed){
         getServer();
       }
       Vector v = (Vector)servertypenameToVersionlist.get(serverType);
       return v;
    }


  /**
   * Get the server kinds name and description. The Name is the hash key ,
   * the description is hash value.
   * <p>Hashtable key is a String - name
   * <p>Hashtable value is a String - description
   * @return Hashtable key is server kind name , object is description
   */
    public  Hashtable getServerKinds()  throws Exception{
       if(!serverParsed){
         getServer();
       }
       return serverkindnameToDescription;
    }

  /**
   *  Get the wrapper library name for the server's platform
   * @return String
   */
    public  String getLibname()  throws Exception{
      if(!serverParsed){
         getServer();
      }
       return libname;
    }

   /**
   *  Tests if all server types/versions support same data types
   *
   * @return true if all server types/versions support same data types
   */
    public boolean isAllServerTypesVersionsSameDataTypes()  throws Exception{
      if(!serverParsed){
         getServer();
       }
       return allServerTypesVersionsSameDataTypes;
    } 

 //====================WRAPPER===================


   /**
   * Get the Wrapper options in a hashtable. The hash key is the option name.
   * If there are no options an empty hashtable is returned.
   * <p>Hashtable key is a String - option name
   * <p>Hashtable value is a GUIOption - option
   * @return hashtable of GUIOption objects
   */
    public Hashtable getWrapperOptions()  throws Exception{
       if(!wrapperParsed){
         getWrapper();
       }
       return wrapperoptionnameToOption;
    }

   /**
   * Get the Wrapper options in a vector.
   * <p>Hashtable key is a String - option name
   * <p>Hashtable value is a GUIOption - option
   * @return hashtable of GUIOption objects
   */
    public Vector getWrapperOptionsVector()  throws Exception{
       if(!wrapperParsed){
         getWrapper();
       }
       return vecWrapperoptionnameToOption;
    }

   /**
   * Get all supported paltforms' libraries in a hashtable. The hash key is the platform.
   * <p>Hashtable key is a String - platform
   * <p>Hashtable value is a library name
   * @return hashtable of all supported platforms' libraries
   */
    public Hashtable getLibraries()  throws Exception{
       if(!wrapperParsed){
         getWrapper();
       }
       return wrapperLibraryToPlatform;
    } 

   /**
   *  Get the wrapper id
   * @return String
   */
    public  String getWrapperID()  throws Exception{
      if(!wrapperParsed){         
         getWrapper();
      }
       return wid;
    }

   /**
   *  Set the wrapper id
   * @return String
   */
    public void  setWrapperID(String w)  throws Exception{
       wid = w;
    }

    /**
   *  Get the wrapper description
   * @return String
   */
    public  String getWrapperDescription()  throws Exception{
      if(!wrapperParsed){
         getWrapper();
      }
       return wrapperDescription;
    }

    /**
   *  Test if sample contents is supported for this wrapper.
   * @return true if supported
   */
    public  boolean isSampleContentsAvailable()  throws Exception{
      if(!wrapperParsed){
         getWrapper();
      }
      return sample_contents;
    }

     /**
   * Tests if userid is required for the wrapper.
   * @return true if required
   */
    public boolean isServerUseridRequired()  throws Exception{
      if(!wrapperParsed){
         getWrapper();
      }
       return userid_required;
    }

    /**
   * Tests if userid is required for the server type.
   * @deprecated This has been replaced with {@link #isServerUseridRequired()}
   * @parm serverType the name of the type of server, returned by  getServerTypes
   * @return true if required
   */
    public boolean isServerUseridRequired(String serverType)  throws Exception{
       //DEPRICATED
       if(!wrapperParsed){
         getWrapper();
      }
       return userid_required;
    }

  
   /**
   * Get all original resource keys in a hashtable. The hash key is an option name.
   * <p>Hashtable key is a String - option name
   * <p>Hashtable value is a String - original key
   * @return hashtable of all original resource keys
   */
    public Hashtable getOriginalKeyTable() throws Exception {
       if(!wrapperParsed){
         getWrapper();
       }
       if(!serverParsed){
         getServer();
       }
      return optionnameToKey;
    } 

 //====================DISCOVER===================
   /**
   * Get the discover options in a hashtable. The hash key is the option name.
   * If there are no options an empty hashtable is returned.
   * <p>Hashtable key is a String - option name
   * <p>Hashtable value is a GUIOption - option
   * @return hashtable of GUIOption objects
   */
    public Hashtable getDiscoverOptions()  throws Exception{
       if(!discoverParsed){
         getDiscover();
       }
       return discoveroptionnameToOption;
    }

   /**
   * Get the discover server options in a hashtable. The hash key is the option name.
   * If there are no options an empty hashtable is returned.
   * <p>Hashtable key is a String - option name
   * <p>Hashtable value is a GUIOption - option
   * @return hashtable of GUIOption objects
   */
    public Hashtable getDiscoverServerOptions()  throws Exception{
       if(!discoverParsed){
         getDiscover();
       }
       return discoverServerOptions;
    }

    /**
   * Get the discover options in a vector in the same order as in the XML file.
   * If there are no options an empty vector is returned.
   * <p>Hashtable key is a String - option name
   * <p>Hashtable value is a GUIOption - option
   * @return vector of GUIOption objects
   */
    public Vector getDiscoverOptionsVector()  throws Exception{
       if(!discoverParsed){
         getDiscover();
       }
       return vDiscoverOptions;
    }

    /**
   * Get the discover server options in a vector in the same order as in the XML file.
   * If there are no options an empty vector is returned.
   * @return vector of GUIOption objects
   */
    public Vector getDiscoverServerOptionsVector()  throws Exception{
       if(!discoverParsed){
         getDiscover();
       }
       return vDiscoverServerOptions;
    }

   /**
   * Get the discover parameters in a hashtable. The hash key is the parm name.
   *
   * <p>Hashtable key is a String - parm name
   * <p>Hashtable value is a String - parm value
   * @return hashtable of parm values
   */
    public Hashtable getDiscoverParms()  throws Exception{

       if(!discoverParsed){
         getDiscover();
       }
       return discoverparmnameToParmvalue;
    }

   
   /**
   * Get the discover parameters in a vector in the same order as in the XML file.
   * If there are no parameters an empty vector is returned.
   * @return vector of parameters
   */
    public Vector getDiscoverParmsVector()  throws Exception{
       if(!discoverParsed){
         getDiscover();
       }
       return vDiscoverParms;
    }

   /**
   * Get the discover java class name for this wrapper.
   *
   * @return class name
   */
    public String getDiscoverClassName()  throws Exception{
       if(!discoverParsed){
         getDiscover();
       }
       return discoverClassName;
    }

 //====================USER===================

   /**
   * get a Hashtable of user Options.  The option name is the hash key.
   * If there are no options an empty hashtable is returned.
   * <p>Hashtable key is a String - option name
   * <p>Hashtable value is a GUIOption - option
   * @return hashtable of GUIOption objects
   */
    public Hashtable getUserOptions()  throws Exception{
       if(useroptionnameToOption == null){
         getUser();
       }
       return useroptionnameToOption;
    }

   /**
   * Get the user mapping type. Returns static string
   *  USER_MAPPING_NOTSUPPORT, USER_MAPPING_OPTIONAL, USER_MAPPING_REQUIRED
   * @return string for user mapping type
   */

    public String getUsersMappingType()  throws Exception{
       if(!userParsed){
         getUser();
       }
       return usersMappingType;
    }

 //===============================Private methods======================================
    /**
   * parse wrapper data
   */
    private void getWrapper()  throws Exception{
      // if wid is not set from initFile(), then get it 
      if ( wid == null )
           wid = findAttribute( rootNode, "wrapper_id", "" ); 
      wrapperLibraryToPlatform = new Hashtable();             
      optionnameToKey = new Hashtable();                      
      //set sample contents
      String valueSampleConents = findAttribute( rootNode, "sample_contents", "" );
      if(valueSampleConents.equals(ATTRIBUTE_VALUE_YES)){
         sample_contents = true;
      }
      //set userid required
      String valueUseridRequired = findAttribute( rootNode, "userid_required", "" );
      if(valueUseridRequired.equals(ATTRIBUTE_VALUE_YES)){
         userid_required = true;
      }

      //check if server is a UNIX variant
      if( serverPlatform != null ) 
      {
        if(serverPlatform.equals(SERVER_PLATFORM_AIX) ||
           serverPlatform.equals(SERVER_PLATFORM_SOLARIS) ||
           serverPlatform.equals(SERVER_PLATFORM_UNIX) ||
           serverPlatform.equals(SERVER_PLATFORM_LINUX) ||
           serverPlatform.equals(SERVER_PLATFORM_HPUX) ){
           fUnixServer = true;       
        }
      } // end if( serverPlatform != null )

      Node child = rootNode.getFirstChild();
      while( child != null ) {
        if( "description".equals( child.getNodeName() ) ) {
           Node tNode = child.getFirstChild();
           if( tNode != null ) {
             //set wrapper  description
             wrapperDescription = tNode.getNodeValue().trim();
             if (optionnameToKey!=null)
                 optionnameToKey.put("WRAPPER_DESC",wrapperDescription); 
             //check if wrapper has text bundle and load description from properties file
             if(hasTextBundle() && wrapperDescription.length() > 0){
               String textDescription = getResource(wrapperDescription);
               if(textDescription.length() > 0){
                  wrapperDescription = textDescription;
               }
             }

           }
        } else if( "wrapper_options".equals( child.getNodeName() ) ) {
           //set wrapper options
           wrapperoptionnameToOption = getOptions(child);
           vecWrapperoptionnameToOption = getOptionsVector(child);
        } else if( "libraries".equals( child.getNodeName() ) ) {
           //set library  name
          if( serverPlatform != null ) 
          {
           if(serverPlatform.equals(SERVER_PLATFORM_AIX)){
             libname = findAttribute( child, SERVER_PLATFORM_AIX, "" );
           } else if(serverPlatform.equals(SERVER_PLATFORM_WINDOWS)){
             libname = findAttribute( child, SERVER_PLATFORM_WINDOWS, "" );
           } else if(serverPlatform.equals(SERVER_PLATFORM_SOLARIS)){
             libname = findAttribute( child, SERVER_PLATFORM_SOLARIS, "" );
           } else if(serverPlatform.equals(SERVER_PLATFORM_LINUX)){
             libname = findAttribute( child, SERVER_PLATFORM_LINUX, "" );
           } else if(serverPlatform.equals(SERVER_PLATFORM_HPUX)){
             libname = findAttribute( child, SERVER_PLATFORM_HPUX, "" );
           } else if(serverPlatform.equals(SERVER_PLATFORM_UNIX)){
             libname = findAttribute( child, SERVER_PLATFORM_UNIX, "" );
           } else {
             libname = ""; //empty if platform not supported
           }
          } // end if( serverPlatform != null )

          
          // build hashtable for all supported libraries/platforms
          libname = findAttribute( child, SERVER_PLATFORM_WINDOWS, "" );
          if ( libname.length()>0 )
            wrapperLibraryToPlatform.put(SERVER_PLATFORM_WINDOWS, libname);
          libname = findAttribute( child, SERVER_PLATFORM_AIX, "" );
          if ( libname.length()>0 )
              wrapperLibraryToPlatform.put(SERVER_PLATFORM_AIX, libname);
          libname = findAttribute( child, SERVER_PLATFORM_SOLARIS, "" );
          if ( libname.length()>0 )
              wrapperLibraryToPlatform.put(SERVER_PLATFORM_SOLARIS, libname);
          libname = findAttribute( child, SERVER_PLATFORM_HPUX, "" );
          if ( libname.length()>0 )
              wrapperLibraryToPlatform.put(SERVER_PLATFORM_HPUX, libname);
          libname = findAttribute( child, SERVER_PLATFORM_UNIX, "" );
          if ( libname.length()>0 )
              wrapperLibraryToPlatform.put(SERVER_PLATFORM_UNIX, libname);
          libname = findAttribute( child, SERVER_PLATFORM_LINUX, "" );
          if ( libname.length()>0 )
              wrapperLibraryToPlatform.put(SERVER_PLATFORM_LINUX, libname);
         

//           if (libname.length() == 0){
             //error no library name in the XML file
//             String msg = MessageFormat.format( DJStringPool.get(DJStringPool.DJ_WCF_LIBNAME_MISSING), new Object[]{wid, serverPlatform} );
//             Exception err = new Exception( msg);
//             CommonTrace.exit(trace);
//             throw err;
//           }
        }
        child = child.getNextSibling();
      }
      if(wrapperoptionnameToOption == null){
        wrapperoptionnameToOption = new Hashtable(); //in case there are no options
      }
      if(vecWrapperoptionnameToOption == null){      
        vecWrapperoptionnameToOption = new Vector(); //in case there are no options
      }

      wrapperParsed = true;
    }


   /**
   * parse server and serverkind data
   */
    private void getServer()  throws Exception{
      //check if server is a UNIX variant
      if ( serverPlatform!=null )  
      {
        if(serverPlatform.equals(SERVER_PLATFORM_AIX) ||
           serverPlatform.equals(SERVER_PLATFORM_SOLARIS) ||
           serverPlatform.equals(SERVER_PLATFORM_UNIX) ||
           serverPlatform.equals(SERVER_PLATFORM_LINUX) ||
           serverPlatform.equals(SERVER_PLATFORM_HPUX) ){
           fUnixServer = true;
        }
      }

      //create non-option hashtables
      serverkindnameToDescription = new Hashtable();
      servertypenameToVersionlist = new Hashtable();
      servertypenameandversionToDatatype = new Hashtable();

      Node child = rootNode.getFirstChild();
      while( child != null ) {
        if( "server_kind".equals( child.getNodeName() ) ) {
          String name = findAttribute( child, "display_name", "" );
          if (optionnameToKey!=null)
              optionnameToKey.put("SERVER_DISPLAYNAME", name);  

          //check if wrapper has text bundle and load display_name from properties file
          if(hasTextBundle() && name.length() > 0){
             String textDisplayName = getResource(name);
             if(textDisplayName.length() > 0){
                name = textDisplayName;
             }
          }

          Node descNode = child.getFirstChild();
          String desc = "";
          while( descNode != null ) {
            if( "description".equals( descNode.getNodeName() ) ) {    //only one of these nodes
              Node tNode = descNode.getFirstChild();
              if( tNode != null ) {
               desc = tNode.getNodeValue().trim();
                //check if wrapper has text bundle and load description from properties file
               if(hasTextBundle() && desc.length() > 0){
                  String textDescription = getResource(desc);
                  if(textDescription.length() > 0){
                    desc = textDescription;
                  }
                }
              }
            }
            descNode = descNode.getNextSibling();
          }
          if(desc == null){
            serverkindnameToDescription.put(name, "");
          } else {
          //set server kinds and descriptions
            serverkindnameToDescription.put(name, desc);
          }
        } else if("libraries".equals( child.getNodeName() )){
           //set library  name
          if (serverPlatform!=null)  
          {
           if(serverPlatform.equals(SERVER_PLATFORM_AIX)){
             libname = findAttribute( child, SERVER_PLATFORM_AIX, "" );
           } else if(serverPlatform.equals(SERVER_PLATFORM_WINDOWS)){
             libname = findAttribute( child, SERVER_PLATFORM_WINDOWS, "" );
           } else if(serverPlatform.equals(SERVER_PLATFORM_SOLARIS)){
             libname = findAttribute( child, SERVER_PLATFORM_SOLARIS, "" );
           } else if(serverPlatform.equals(SERVER_PLATFORM_LINUX)){
             libname = findAttribute( child, SERVER_PLATFORM_LINUX, "" );
           } else if(serverPlatform.equals(SERVER_PLATFORM_HPUX)){
             libname = findAttribute( child, SERVER_PLATFORM_HPUX, "" );
           } else if(serverPlatform.equals(SERVER_PLATFORM_UNIX)){
             libname = findAttribute( child, SERVER_PLATFORM_UNIX, "" );
           } else {
             libname = ""; //empty means not supported on this platform
           }
          }
        } else if("servers".equals( child.getNodeName() )){
          Node typesNode = child.getFirstChild();
          while( typesNode != null ) {
            if( "types".equals( typesNode.getNodeName() ) ) {
              Node typeNode = typesNode.getFirstChild();
              while( typeNode != null ) {
                if( "type".equals( typeNode.getNodeName() ) ) {
                  Vector vValues = new Vector();   //new vector for each type
                  String typeName = findAttribute( typeNode, "name", "" );
                  Node versionNode = typeNode.getFirstChild();
                  while( versionNode != null ) {
                    if( "version".equals( versionNode.getNodeName() ) ) {
                      String versionValue = findAttribute( versionNode, "version_number", "" );
                      if(versionValue.length() > 0){
                         vValues.add( versionValue );
                      }
                      //get server + version specific data types
                      Node datatypeNode = versionNode.getFirstChild(); 
                      while( datatypeNode != null ) {
                        if( "column_datatypes".equals( datatypeNode.getNodeName() ) ) {
                           allServerTypesVersionsSameDataTypes = false;  
                           Vector vVersionDataTypes = getDatatypes(datatypeNode);
                           //add data type if version has a value
                           if(versionValue.length() > 0){
                             //hash table key is servertype + version
                             servertypenameandversionToDatatype.put((typeName + versionValue), vVersionDataTypes);
                           }
                        }
                        datatypeNode = datatypeNode.getNextSibling();
                      }
                    }
                    //get server specific data types
                    if( "column_datatypes".equals( versionNode.getNodeName() ) ) {  
                        allServerTypesVersionsSameDataTypes = false;  
                        Vector vServerDataTypes = getDatatypes(versionNode);
                        //hash table key is servertype
                        servertypenameandversionToDatatype.put(typeName, vServerDataTypes);
                    }
                    versionNode = versionNode.getNextSibling();
                  }
                  servertypenameToVersionlist.put(typeName , vValues);
                }
                typeNode = typeNode.getNextSibling();
              }
            } else if( "server_options".equals( typesNode.getNodeName() ) ){
               //set server options
               serveroptionnameToOption = getOptions(typesNode);
               vecServeroptionnameToOption = getOptionsVector(typesNode);  
            }
            typesNode = typesNode.getNextSibling();
          }
        }
        child = child.getNextSibling();
      }
      if(serveroptionnameToOption == null){
        serveroptionnameToOption = new Hashtable(); //in case there are no options
      }
      if(vecServeroptionnameToOption == null){    
        vecServeroptionnameToOption = new Vector(); //in case there are no options
      }

      serverParsed = true;
//      if (libname.length() == 0){
        //error no library name in the XML file
        //if this happens it is an install error
        //the XML file should not be installed on a server platform
        //that does not support the wrapper
//        String msg = MessageFormat.format( DJStringPool.get(DJStringPool.DJ_WCF_LIBNAME_MISSING), new Object[]{wid, serverPlatform} );
//        Exception err = new Exception( msg);
//        CommonTrace.exit(trace);
//        throw err;
//      }
    }

   /**
   * parse the discover data from the xml file.
   *
   */
    private void getDiscover()  throws Exception{
      discoverparmnameToParmvalue = new Hashtable();
      vDiscoverOptions = new Vector();
      vDiscoverParms = new Vector(); 
      vDiscoverServerOptions = new Vector();
//      vGenericFunctions = new Vector();
      Node child = rootNode.getFirstChild();
      while( child != null ) {
        if( "discover".equals( child.getNodeName() ) ) {
          //get class name
          discoverClassName = findAttribute( child, "class_name","" );
          
          //in the xml config file we have this location: com.ibm.db2.tools.wrapperTools.extensions.RelationalDiscovery
          //the class in eclipse is in tis location:com.ibm.datatools.metadata.admintooling.discovery.RelationalDiscovery
          //so, have to hack!
          //ODBC Class for server discovery is the same as relational discovery. So, replace that with the RelationalDiscovery
          //If we ever decide to support nickname discovery for ODBC we should migrate OdbcDiscovery class to this plugin
          //The same issue for Teradata
          int location = discoverClassName.lastIndexOf(".");
          String discoveryClass = discoverClassName.substring(location);
          if(discoveryClass.equalsIgnoreCase(".OdbcDiscovery") || discoveryClass.equalsIgnoreCase(".TeradataDiscovery")) {
          	discoveryClass = new String(".RelationalDiscovery");
          }
          discoverClassName = new String("com.ibm.datatools.db2.luw.serverdiscovery"+discoveryClass);
        //  ServerdiscoveryPlugin.getDefault().trace("Discovery Class Name="+discoverClassName);  //$NON-NLS-1$

		  Node propertyNode = child.getFirstChild();
          while( propertyNode != null ) {
            if( "property".equals( propertyNode.getNodeName() ) ) {
               String propName =  findAttribute( propertyNode, "name","" );
               Node tNode = propertyNode.getFirstChild();
               if( tNode != null ) {
                 //set parms, parm value is a String
                 discoverparmnameToParmvalue.put(propName, tNode.getNodeValue().trim());
                 Object[] aParm = new Object[2];         
                 aParm[0] = propName;                    
                 aParm[1] = tNode.getNodeValue().trim(); 
                 vDiscoverParms.add(aParm);            
               }
            } else if( "generic_gui".equals( propertyNode.getNodeName() ) ) {
              //get options
              discoveroptionnameToOption = getOptions(propertyNode);
            } else if( "serverObject_gui".equals( propertyNode.getNodeName() ) ) {
              //get options
              discoverServerOptions = getOptions(propertyNode);
            }
            propertyNode = propertyNode.getNextSibling();
          }
        }
        child = child.getNextSibling();
      }
      //if discover object is not in XML file then make sure there is an empty hashtable
      if(discoveroptionnameToOption == null){
         discoveroptionnameToOption = new Hashtable();
      }
      if(discoverServerOptions == null){
         discoverServerOptions = new Hashtable();
      }
      discoverParsed = true;
    }


   /**
   * parse user options
   */
    private void getUser()  throws Exception{
      Node child = rootNode.getFirstChild();
      while( child != null ) {
        if( "users".equals( child.getNodeName() ) ) {
          usersMappingType = findAttribute( child, "user_mapping","" );
          Node userOpNode = child.getFirstChild();
          while( userOpNode != null ) {
            if( "user_options".equals( userOpNode.getNodeName() ) ) {
              //set options
              useroptionnameToOption = getOptions(userOpNode);
            }
            userOpNode = userOpNode.getNextSibling();
          }
        }
        child = child.getNextSibling();
      }
      if(useroptionnameToOption == null){
        useroptionnameToOption = new Hashtable(); //in case there are no options
      }
      userParsed = true;
    }


   /**
   *
   *
   * @param  fileName
   */
//    private void setFileName( String name)  throws Exception {
//       XMLFileName = name;
//    }

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
  private String findAttribute( Node node, String key, String defV ) {
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

 /**
  * Get a hashtable of GUIOption objects. If there are no options an empty hashtable
  * is returned.
  * @param  node  The parent node that contains the options element
  * @return Hashtable with GuiOption objects
  *
  */
  private Hashtable getOptions( Node node) throws Exception {
    Hashtable h = new Hashtable();
    try {
      Node option = node.getFirstChild();
      while( option != null ) {
        if( "option".equals( option.getNodeName() ) ) {
           String optPlatform = findAttribute( option, "platform", "" );

           //if wrong platform skip this option
           if(optPlatform.equals(OPTION_PLATFORM_ALL) ||
              optPlatform.equals(serverPlatform) ||
              (optPlatform.equals(OPTION_PLATFORM_ALLUNIX) && fUnixServer )){

             String optName = findAttribute( option, "name", "" );
             String optDescription = findAttribute( option, "description", "" );
             String optRequired = findAttribute( option, "required", "" );
             boolean fRequired =false;
             if(optRequired.equals(ATTRIBUTE_VALUE_YES)){
                fRequired = true;
             }
             String optEditable = findAttribute( option, "editable", "" );
             boolean fEdit =false;
             if(optEditable.equals(ATTRIBUTE_VALUE_YES)){
                fEdit = true;
             }
             String optDefault = findAttribute( option, "default", "" );
             String optHints = findAttribute( option, "hints", "" );
             String optAlter = findAttribute( option, "alter", "" );
             boolean fAlter =false;
             if(optAlter.equals(ATTRIBUTE_VALUE_YES)){
                fAlter = true;
             }
             Vector vValues = new Vector();
             //get the value object
             Node values = option.getFirstChild();
             boolean fMultivalue =false;
             String optDelimiter = "";
             while( values != null) {
               if( "values".equals( values.getNodeName() ) ) {
                 //get multivalue
                 String optMultivalue = findAttribute( values, "multivalue", "" );
                 if(optMultivalue.equals(ATTRIBUTE_VALUE_YES)){
                   fMultivalue = true;
                 }
                 //get delimiter
                 optDelimiter = findAttribute( option, "delimiter", "" );
                 //get the settings
                 Node setting = values.getFirstChild();
                 while( setting != null) {
                   if( "setting".equals( setting.getNodeName() ) ) {
                     Node settingValue = setting.getFirstChild();
                     if(settingValue != null){
                       String optSetting = settingValue.getNodeValue().trim();
                       vValues.add( optSetting );
                     }
                   }
                   setting = setting.getNextSibling();
                 }
               }
               values = values.getNextSibling();
             }

             if ( optHints.length() > 0 )       
             {
                if (optionnameToKey!=null)
                    optionnameToKey.put(optName+"HINT", optHints);
             }
             if ( optDescription.length() > 0 )  
             {
                if (optionnameToKey!=null)
                    optionnameToKey.put(optName+"DESC", optDescription);
             }

             //check for description and hints from the properties file
             if(hasTextBundle()){                                    
               if(optHints.length() > 0){
                 String textHints =  getResource(optHints);
                 if(textHints.length() > 0){
                    optHints = textHints;
                 }
               }
               if(optDescription.length() > 0){
                 String textDescription = getResource(optDescription);
                 if(textDescription.length() > 0){
                    optDescription = textDescription;
                 }
               }
             }
             //if the option has no values then the values vector is empty
             GUIOption op = new GUIOption(optName, vValues, optDefault, fEdit,
                                          fRequired, fMultivalue, fAlter,
                                          optDelimiter, optPlatform, optHints,
                                          optDescription);
             if(!h.containsKey(optName)){ 
               h.put(optName, op);
             } else {
//             	System.out.println("getOptions: Duplicate option '" + optName +"' not added to option hash table.");
             //	ServerdiscoveryPlugin.getDefault().trace("Duplicate option '"+optName +"' not added to option hash table.");  //$NON-NLS-1$

             }
             //if this is discovery options then also add to vector
             if("generic_gui".equals( node.getNodeName()) ){
                vDiscoverOptions.add(op);
             }
             if("serverObject_gui".equals( node.getNodeName()) ){
                vDiscoverServerOptions.add(op);
             }
           }
         }
         option = option.getNextSibling();
      }
    }catch( Exception e ){
      throw e;
    }
    return h;
  }

 /**
  * Get a vector of GUIOption objects. If there are no options an empty vector
  * is returned.
  * @param  node  The parent node that contains the options element
  * @return Vector with GuiOption objects
  *
  */
  private Vector getOptionsVector( Node node) throws Exception {
    Vector h = new Vector();
    try {
      Node option = node.getFirstChild();
      while( option != null ) {
        if( "option".equals( option.getNodeName() ) ) { // get all options
             String optPlatform = findAttribute( option, "platform", "" );
             String optName = findAttribute( option, "name", "" );
             String optDescription = findAttribute( option, "description", "" );
             String optRequired = findAttribute( option, "required", "" );
             boolean fRequired =false;
             if(optRequired.equals(ATTRIBUTE_VALUE_YES)){
                fRequired = true;
             }
             String optEditable = findAttribute( option, "editable", "" );
             boolean fEdit =false;
             if(optEditable.equals(ATTRIBUTE_VALUE_YES)){
                fEdit = true;
             }
             String optDefault = findAttribute( option, "default", "" );
             String optHints = findAttribute( option, "hints", "" );
             String optAlter = findAttribute( option, "alter", "" );
             boolean fAlter =false;
             if(optAlter.equals(ATTRIBUTE_VALUE_YES)){
                fAlter = true;
             }
             Vector vValues = new Vector();
             //get the value object
             Node values = option.getFirstChild();
             boolean fMultivalue =false;
             String optDelimiter = "";
             while( values != null) {
               if( "values".equals( values.getNodeName() ) ) {
                 //get multivalue
                 String optMultivalue = findAttribute( values, "multivalue", "" );
                 if(optMultivalue.equals(ATTRIBUTE_VALUE_YES)){
                   fMultivalue = true;
                 }
                 //get delimiter
                 optDelimiter = findAttribute( option, "delimiter", "" );
                 //get the settings
                 Node setting = values.getFirstChild();
                 while( setting != null) {
                   if( "setting".equals( setting.getNodeName() ) ) {
                     Node settingValue = setting.getFirstChild();
                     if(settingValue != null){
                       String optSetting = settingValue.getNodeValue().trim();
                       vValues.add( optSetting );
                     }
                   }
                   setting = setting.getNextSibling();
                 }
               }
               values = values.getNextSibling();
             }

             if ( optHints.length() > 0 )        
             {
                if (optionnameToKey!=null)
                    optionnameToKey.put(optName+"HINT", optHints);
             }
             if ( optDescription.length() > 0 )  
             {
                if (optionnameToKey!=null)
                    optionnameToKey.put(optName+"DESC", optDescription);
             }

             //check for description and hints from the properties file
             if(hasTextBundle()){                                                   
             	if(optHints.length() > 0){
                 String textHints =  getResource(optHints);
                 if(textHints.length() > 0){
                    optHints = textHints;
                 }
               }
               if(optDescription.length() > 0){
                 String textDescription = getResource(optDescription);
                 if(textDescription.length() > 0){
                    optDescription = textDescription;
                 }
               }
             }
             //if the option has no values then the values vector is empty
             GUIOption op = new GUIOption(optName, vValues, optDefault, fEdit,
                                          fRequired, fMultivalue, fAlter,
                                          optDelimiter, optPlatform, optHints,
                                          optDescription);
             h.add(op);
             //if this is discovery options then also add to vector
             if("generic_gui".equals( node.getNodeName()) ){
                vDiscoverOptions.add(op);
             }
             if("serverObject_gui".equals( node.getNodeName()) ){
                vDiscoverServerOptions.add(op);
             }
         } // end if ( "option".equals( option.getNodeName() ) )
         option = option.getNextSibling();
      } // end while
    }catch( Exception e ){
      throw e;
    }
    return h;
  }

/**
  * Get the datatype values under the column_datatypes node.
  * @param  node  The parent node that contains the datatype element
  * @return Vector with String objects with datatype values
  *
  */
  private Vector getDatatypes( Node node) throws Exception {  
    Vector v = new Vector();
    try {
       Node datatypeNode = node.getFirstChild();
       while( datatypeNode != null ) {
         if( "datatype".equals( datatypeNode.getNodeName() ) ) {
           Node tNode = datatypeNode.getFirstChild();
           if( tNode != null ) {
             //set datatype
             v.add(tNode.getNodeValue().trim());
           }
         }
         datatypeNode = datatypeNode.getNextSibling();
       }
    }catch( Exception e ){
      throw e;
    }
    return v;
  }

 /**
  * Get a hashtable of GUIOption objects. If there are no options an empty hashtable
  * is returned.
  * @param  node  The parent node that contains the options element
  * @return Hashtable with GuiOption objects
  *
  */
  /*static*/ private Hashtable  buildResources(InputStreamReader prop_isr) throws Exception {  
    Hashtable h = new Hashtable();
    BufferedReader br =  new BufferedReader( prop_isr);
    boolean fContinue = false;
    String key  = "";     
    String value = "";
    String line = br.readLine();
    while(line != null){
       if(line.length()>0){
          line = line.trim(); //remove leading and trailing blanks 
          if(!line.startsWith("#")){  //skip the comment lines that start with #
            if(!fContinue){
              int index = line.indexOf("=");
              if(index != -1){
                 key = new String(line.substring(0, index));
                 key = key.trim(); 
                 value = new String(line.substring(index+1));
                 value = value.trim(); 
              }
            } else {
              value += line;
            }
            if(value.endsWith("\\")){ //continuation character escaped
               fContinue = true;
               int index = value.lastIndexOf("\\");
               value = value.substring(0, index);
            } else {
               if(key.length() > 0){
                 if(!h.containsKey(key)){ 
                   h.put(key, value);
                 } else {
//                 	System.out.println("buildResources: Duplicate key '" + key +"' not added to property hash table." );
                 //	ServerdiscoveryPlugin.getDefault().trace("Duplicate key '" + key +"' not added to property hash table.");  //$NON-NLS-1$

                 }
               } else{ 
//               	System.out.println("buildResources: Invalid properties file. No Key was found for strings." );
               //	ServerdiscoveryPlugin.getDefault().trace("Invalid properties file. No Key was found for strings.");  //$NON-NLS-1$

               }
               fContinue = false;
            }
          }
       }
       line = br.readLine();  //get the next line
    }
    return h;
  }

  private String getResource(String key) {  
    if(textBundle.containsKey(key)){  
      String value = (String)textBundle.get(key);
      return value;
    }else{
      // System.out.println("Key '"+ key + "' not found in text bundle." );
      return "";
    }
  }

  static private String getSPMessage(int rc) {
    String sp_msg = "";
    if( rc == 40 ){
        sp_msg = new String("The DB2 install path is invalid.");
    }else if(rc == 41){
    	sp_msg = new String("The wrapper XML file path is invalid.");
    }else if(rc == 42){
    	sp_msg = new String("The wrapper XML files do not exist or cannot be read. Make sure the files have public read permission.");
    }else if(rc == 43){
    	sp_msg = new String("Cannot find the default English locale directory.");
    }else if(rc == 44){
    	sp_msg = new String("No XML files were found in directory");
    }else if(rc == 45){
    	sp_msg = new String("A memory error occurred while executing the stored procedure GET_WRAP_CFG_C.");
    }else if(rc == 46){
    	sp_msg = new String("Cannot open or read wrapper.dtd file.");
    }else if(rc == 47){
    	sp_msg = new String("No XML files were found in directory.");
    }else if(rc == 48){
    	sp_msg = new String("No properties files were found in directory.");
    }else if(rc == 49){
    	sp_msg = new String("The wrapper properties files cannot be read. Make sure the files have public read permission.");
    } else {
      return "";
    }
    return sp_msg;
 }

   /**
   * Get the CommonOption class.
   *
   * @return CommonOptions  The CommonOptions class .
   */
    public CommonOptions getCommonOptions()  throws Exception {
       if(!commonOptionsParsed){
         parseCommonOptions();
       }
       return commonOptions;
    }

   /**
   * parse the common_options element from the xml file.
   *
   */
    private void parseCommonOptions()  throws Exception{
      Vector messages = new Vector();
      Vector configVars = new Vector();

      Node child = rootNode.getFirstChild();
      while( child != null ) {                                           // Loop through to find the common_options element
        if( "common_options".equals( child.getNodeName() ) ) {           // Find it?
          Node node2 = child.getFirstChild();                            // ..yes
          while( node2 != null ) {
            if( "config_variable".equals( node2.getNodeName() ) ) {      // Is the sub element a config_variable element?
               String type =  findAttribute( node2, "type","" );         // ..yes, get the type attribute
               String location =  findAttribute( node2, "location","" ); // and location attribute too
               Hashtable ht = getOptions(node2);                         // Get the option element (should only be 1)
//               GUIOption guiOption = null;
               if (ht != null && ht.size() == 1 && type != null && location != null) { // If everything looks valid
//                 Enumeration _enum = ht.elements();                                     // ..get the one option element
//                 guiOption = (GUIOption) _enum.nextElement();
                 //configVars.add( new ConfigVariable(type, location, guiOption) );  //Farnaz      // ..and put everything in the list
               } else {
//               	System.out.println("parseCommonOptions: Invalid data in config_variable element.  Type " + type + " location " + location + " hashtable " + ht);
              // 	ServerdiscoveryPlugin.getDefault().trace("Invalid data in config_variable element.  Type " + type + " location " + location + " hashtable " + ht);  //$NON-NLS-1$

               }
            } else if( "message".equals( node2.getNodeName() ) ) {        // Is it the message element?
               String platform =  findAttribute( node2, "platform","" );  // ..yes, get platform attribute
               String msgText = null;
               Node msgTextNode = node2.getFirstChild();                  // The message text is in a child node
               if (msgTextNode != null) {
                 msgText = msgTextNode.getNodeValue();
               };
               if (msgText != null && platform != null) {                 // If everything looks valid
                 messages.add( new ConfigMessage(msgText, platform) ) ;   // ..then save it in the list
               } else {
//               	System.out.println("parseCommonOptions: Invalid data in <message> element.  Platform " + platform + " msgText " + msgText);
              // 	ServerdiscoveryPlugin.getDefault().trace("Invalid data in <message> element.  Platform " + platform + " msgText " + msgText);  //$NON-NLS-1$
               };
            }
            node2 = node2.getNextSibling();
          }
        }
        child = child.getNextSibling();
      }

      commonOptions = new CommonOptions(messages, configVars);
      commonOptionsParsed = true;
    }

 }  //end class


