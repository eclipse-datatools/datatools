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
package org.eclipse.datatools.enablement.ibm.db2.luw.catalog;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument;
import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaImpl;

/**
 * @author debbani
 */
public class LUWCatalogXmlSchema extends DB2XMLSchemaImpl implements ICatalogObject {

	private boolean dependencyLoaded = false;
	private boolean xmlSchemaDocLoaded = false;
	
	public static final String XSD_EXTENSION = ".xsd"; //$NON-NLS-1$
   /**
    * @see org.eclipse.wst.rdb.internal.core.rte.ICatalogObject#refresh()
    */
   public void refresh() {
   	
   	  // set to unloaded status to force reload next time
   	  if(this.xmlSchemaDocLoaded){
   	  	 this.xmlSchemaDocs.clear();
   	  	 this.xmlSchemaDocLoaded = false;
   	  }
      
      if (this.dependencyLoaded){
         this.dependencies.clear();
         this.dependencyLoaded = false;
      }
      RefreshManager.getInstance().referesh(this);
   }

   /**
    * @see org.eclipse.wst.rdb.internal.core.rte.ICatalogObject#getConnection()
    */
   public Connection getConnection() {
      Database database = this.getCatalogDatabase();
      return ((LUWCatalogDatabase) database).getConnection();
   }

   /**
    * @see org.eclipse.wst.rdb.internal.core.rte.ICatalogObject#getCatalogDatabase()
    */
   public Database getCatalogDatabase() {
      return this.getSchema().getDatabase();		
   }

	public EList getXmlSchemaDocs() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getXmlSchemaDocs();
		} else {
			if(!this.xmlSchemaDocLoaded) this.loadXmlSchemaDocs();
			return this.xmlSchemaDocs;
		}
	}	
	
	private synchronized void loadXmlSchemaDocs() 
	{
		   if(this.xmlSchemaDocLoaded) return;
		   EList xmlSchemaDocList = super.getXmlSchemaDocs();
		   
		   boolean deliver = this.eDeliver();
		   this.eSetDeliver(false);
		   Connection connection = this.getConnection();
		   try {
		      
		      LUWCatalogXmlSchema.loadXMLSchemaDocs(connection,xmlSchemaDocList, this);
		   }
		   catch (Exception e) {
		   }
		   this.xmlSchemaDocLoaded = true;
		   this.eSetDeliver(deliver);
	}		
	
	public static void loadXMLSchemaDocs(Connection connection, EList xmlSchemaDocList, DB2XMLSchema xmlSchema) throws SQLException {
		Object[] oldList = xmlSchemaDocList.toArray();
		xmlSchemaDocList.clear();

		ConnectionInfo connectionInfo = DatabaseConnectionRegistry.getInstance().getConnectionForDatabase(xmlSchema.getSchema().getDatabase());
		ConnectionFilter xmlSchemaDocFilter = connectionInfo.getFilter(xmlSchema.getName()+"::"+ConnectionFilter.XML_SCHEMA_DOC_FILTER); //$NON-NLS-1$
		if (xmlSchemaDocFilter == null) {	//default jar filter
			xmlSchemaDocFilter = connectionInfo.getFilter(ConnectionFilter.XML_SCHEMA_DOC_FILTER);
		}
		String filterStr = ""; //$NON-NLS-1$
		if (xmlSchemaDocFilter != null) {
		   filterStr += "SCHEMALOCATION " + xmlSchemaDocFilter.getPredicate();		    //$NON-NLS-1$
		   filterStr =  " AND (" + filterStr; //$NON-NLS-1$
		   filterStr += ") "; //$NON-NLS-1$
		} 
		
		String query = "SELECT H.HTYPE, C.SCHEMALOCATION, C.TARGETNAMESPACE, C.COMPONENTID, C.STATUS, C.COMPONENT"  //$NON-NLS-1$
					+ " FROM SYSCAT.XSROBJECTCOMPONENTS C, SYSCAT.XSROBJECTHIERARCHIES H" //$NON-NLS-1$
					+ " WHERE C.OBJECTNAME='" + xmlSchema.getName() + "' AND "  //$NON-NLS-1$ //$NON-NLS-2$
					+ " C.OBJECTSCHEMA= '" + xmlSchema.getSchema().getName() + "' AND " //$NON-NLS-1$ //$NON-NLS-2$
					+ " C.COMPONENTID = H.COMPONENTID"; //$NON-NLS-1$
		
		if (xmlSchemaDocFilter != null) {
			query += filterStr;
		}
		
		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);
		
		try {
			while(r.next()) {
				DB2XMLSchemaDocument xmlSchemaDoc = null;
				
				final String schemaLocation = r.getString("SCHEMALOCATION");				 //$NON-NLS-1$
				
				EClass metaclass = DB2ModelPackage.eINSTANCE.getDB2XMLSchemaDocument();
				
				if (metaclass != null)
				{
					String docName = null;
					
					if (schemaLocation != null && !schemaLocation.trim().equals(""))
					{
						if (schemaLocation.toLowerCase().endsWith(XSD_EXTENSION)) {
							// if the schemaLocation ends in .xsd, throw the .xsd extension away from the display name
							docName = schemaLocation.substring(schemaLocation.lastIndexOf("/") + 1, schemaLocation.lastIndexOf('.')); //$NON-NLS-1$
						}
						else {
							// only take the last part of the name that doesn't contain slashes, retaining any other kind of extension
							docName = schemaLocation.substring(schemaLocation.lastIndexOf("/") + 1); //$NON-NLS-1$;
						}
					}					
					else
					{
						// since we don't have a non-blank schema location, just use the component ID for display						
						final long componentId = r.getLong("COMPONENTID"); //$NON-NLS-1$
						docName = String.valueOf(componentId); // doc name is without file extension  
					}

						
					Object element = LUWCatalogXmlSchema.findElement(oldList,docName,metaclass);
					if (element != null) {
					    // we already have the model object in the schema
						xmlSchemaDoc = (DB2XMLSchemaDocument) element;
						((ICatalogObject)xmlSchema).refresh();
					} else {
					    // create the model object
						xmlSchemaDoc = new LUWCatalogXmlSchemaDocument();
						xmlSchemaDoc.setName(docName);
						xmlSchemaDoc.setSchemaLocation(schemaLocation);
					}
					// populate the model object
					final String targetNamespace = r.getString("TARGETNAMESPACE");				 //$NON-NLS-1$
					xmlSchemaDoc.setTargetNamespace(targetNamespace);
					
					final String htype = r.getString("HTYPE"); //$NON-NLS-1$
					if (htype.equals("P")) //$NON-NLS-1$
						xmlSchemaDoc.setPrimary(true);
					}
					xmlSchemaDocList.add(xmlSchemaDoc);  
					
					// get the xsd blob
					Blob xsd = r.getBlob("COMPONENT");  //$NON-NLS-1$
					if (xsd != null){
						
						InputStream xsdStream = xsd.getBinaryStream();
						
                        // construct path and filename for cached file storage
                        // the path is unique for connection,db,dbscheman and xmlschema
                        String xmlschemaPath = xmlSchema.getName();
                        String path = LUWCatalogSchema.getXSDSourceCacheRoot(connectionInfo, xmlSchema.getSchema()) + xmlschemaPath;
						// create directories and file
						File dir = new File(path);
						dir.mkdirs();
						File xsdFile = new File(dir,xmlSchemaDoc.getName() + XSD_EXTENSION);
						
					    // copy file to system path (buffered)
						FileOutputStream fos = new FileOutputStream(xsdFile);
						BufferedOutputStream bos = new BufferedOutputStream(fos);
						BufferedInputStream bis = new BufferedInputStream(xsdStream);
						byte b[];
						b = new byte[bis.available()];
						bis.read(b);
						bos.write(b);
						bis.close();
						bos.close();
						
						// everything's fine, so set filename to xsd model object
						xmlSchemaDoc.setFileName(path + File.separator + xmlSchemaDoc.getName() + XSD_EXTENSION);
					}
				}
		}catch (Exception e) {
			// nothing happening here
		}finally{
			r.close();
			s.close();
		}
    }		

	private static Object findElement(Object[] list, String name,EClass metaclass){
		Object object = null;
		for (int i = 0; i < list.length; i++){
			SQLObject sqlObject = (SQLObject) list[i];
			if (sqlObject.getName().equals(name) && sqlObject.eClass() == metaclass && sqlObject instanceof ICatalogObject){
				object = list[i];
				break;
			}
		}
		return object;
	}	
}
