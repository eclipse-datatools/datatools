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
package org.eclipse.datatools.enablement.ibm.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
//bgp import org.eclipse.datatools.enablement.ibm.db2.util.DataModelResource;
import org.eclipse.datatools.enablement.ibm.util.ModelVersion;
import org.eclipse.datatools.modelbase.sql.constraints.IncrementType;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.xmi.XMLResource;

public class ModelHelper {

	public static final float DB2LUW_V97 = 9.7f;
	public static final float DB2LUW_V10_1 = 10.1f;
	public static final float DB2LUW_V10_5 = 10.5f;
	
	public static Database getDatabase(Schema schema) {
		if (schema != null) {
			if (schema.getCatalog() != null) {
				return schema.getCatalog().getDatabase();
			}
			else if (schema.getDatabase() != null) {
				return schema.getDatabase();
			}
		}
		
		return null;
	}
	
	public static List<Schema> getSchemas (Database database)
	{
		if (database == null) throw new IllegalArgumentException();
		List <Schema> schemaList = new ArrayList<Schema> ();

		schemaList.addAll(database.getSchemas());
		for (Object o : database.getCatalogs())
		{
			Catalog c = (Catalog) o;
			List <Schema> schemas = c.getSchemas();
			if (!schemas.isEmpty())
			{
				schemaList.addAll(schemas);
			}
		}
		return schemaList;
	}
	
	public static float getVersionAsFloat(String versionString)
	{
		float version = 8.0f;
		
		try {
			version = Float.parseFloat(versionString.substring(1));
		}
		catch ( NumberFormatException e ) {
		}
		return version;
	}
	
	public static float getZseriesVersionAsFloat(String versionString)
	{
		float version = 8.0f;
		
		try 
		{
			if ( versionString.length() >= 3 )
			{
				version = Float.parseFloat(versionString.substring(1,3).trim());
			}
			else
			{
				version = Float.parseFloat(versionString.substring(1,2).trim());
			}
		}
		catch ( NumberFormatException e ) {
		}
		return version;
	}
	
	public static boolean isSupportPL_SQL(DatabaseDefinition dbDef){
		String product = dbDef.getProduct();
		if ("DB2 UDB".equals(product) && getVersionAsFloat(dbDef.getVersion()) >= DB2LUW_V97) { 
			return true;
		}
		return false;
	}
	
	public static boolean isSupportSecure(DatabaseDefinition dbDef){
		String product = dbDef.getProduct();
		if ("DB2 UDB".equals(product) && getVersionAsFloat(dbDef.getVersion()) >= DB2LUW_V10_1) { 
			return true;
		}
		return false;
	}

	public static boolean isSupportModule(DatabaseDefinition dbDef){
		String product = dbDef.getProduct();
		if ("DB2 UDB".equals(product) && getVersionAsFloat(dbDef.getVersion()) >= DB2LUW_V97) { 
			return true;
		}
		return false;
	}
	
	public static boolean isSupportGlobalVariable(DatabaseDefinition dbDef){
		String product = dbDef.getProduct();
		if ("DB2 UDB".equals(product) && getVersionAsFloat(dbDef.getVersion()) >= DB2LUW_V97) { 
			return true;
		}
		return false;
	}

	public static boolean isSupportArrayDataType(DatabaseDefinition dbDef){
		String product = dbDef.getProduct();
		if ("DB2 UDB".equals(product) && getVersionAsFloat(dbDef.getVersion()) >= DB2LUW_V97) { 
			return true;
		} else if ("Oracle".equals(product)) {
			return true;
		}
		return false;
	}

	public static boolean isSupportRowDataType(DatabaseDefinition dbDef){
		String product = dbDef.getProduct();
		if ("DB2 UDB".equals(product) && getVersionAsFloat(dbDef.getVersion()) >= DB2LUW_V97) { 
			return true;
		} 
		return false;
	}
	
	public static boolean isSupportRandomIndex(DatabaseDefinition dbDef) {

		String product = dbDef.getProduct();
		if ("DB2 UDB".equals(product)
				&& getVersionAsFloat(dbDef.getVersion()) >= DB2LUW_V10_5) {
			return true;
		}
		if ("DB2 UDB zSeries".equals(product)) {
			String version = dbDef.getVersion();
			if (version.compareTo("V9 (New-Function Mode)") == 0 //
					|| getZseriesVersionAsFloat(version) > 9) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isSupportColumnStore(DatabaseDefinition dbDef){
		String product = dbDef.getProduct();
		if ("DB2 UDB".equals(product)
				&& getVersionAsFloat(dbDef.getVersion()) >= DB2LUW_V10_5) {
			return true;
		}
		return false;
	}
	
	public static boolean isSupportInlineFunction(DatabaseDefinition dbDef){
		String product = dbDef.getProduct();
		if ("DB2 UDB zSeries".equals(product) //
				&& getZseriesVersionAsFloat(dbDef.getVersion()) >= 10 )
		{
			 return true;
		} 
		return false;
	}
	
	/**
	 * Gets the model version of the resource
	 * @param modelResource the model resource
	 * @return the model version of the resource, or UNKNOWN if not a 
	 * DataModelResource or version not known
	 */
	public static ModelVersion getModelVersion(XMLResource modelResource)
	{
		ModelVersion version = ModelVersion.UNKNOWN;
//bgp		if (modelResource instanceof DataModelResource)
//		{
//		    version = ((DataModelResource)modelResource).getVersion();
//bgp		}
		return version;
	}
	
	/**
	 * Determines whether or not the model resource version is the same
	 * as the current version
	 * @param resource the resource to check
	 * @return true if the version is the current version, false if not
	 */
	public static boolean isCurrentModelVersion(XMLResource resource)
	{
		boolean current = false;
		ModelVersion version = ModelHelper.getModelVersion(resource);
		if (ModelVersion.CURRENT_VERSION.equals(version))
		{
			current = true;
		}
		return current;
	}

	public static boolean isSupportPartitionedIndex(DatabaseDefinition dbDef){
		String product = dbDef.getProduct();
		if ("DB2 UDB".equals(product) && getVersionAsFloat(dbDef.getVersion()) >= DB2LUW_V97) { 
			return true;
		}
		return false;
	}
	
	public static Column getColumn(Table table, String colname)
	 {
		 EList<Column> columns = table.getColumns();
		 for ( Column currentCol: columns )
		 {
			 if ( currentCol.getName().equals(colname) )
			 {
				 return currentCol;
	    	}
		 }
		 return null;   
	 }	   
	
	public static boolean isEqual(IndexMember member1, IndexMember member2) {
		if (member1.getColumn() != null &&
				member2.getColumn() != null) {
			Column column1 = member1.getColumn();
			IncrementType incType1 = member1.getIncrementType();
			Column column2 = member2.getColumn();
			IncrementType incType2 = member2.getIncrementType();
			if (column1 == column2 && incType1.getValue() == incType2.getValue()) {
				return true;
			}
		}
		else if (member1.getExpression() != null &&
				member2.getExpression() != null) {
			String sql1 = member1.getExpression().getSql();
			IncrementType incType1 = member1.getIncrementType();
			String sql2 = member2.getExpression().getSql();
			IncrementType incType2 = member2.getIncrementType();
			if (sql1 != null &&
					sql1.equals(sql2) &&
					incType1.getValue() == incType2.getValue()) {
				return true;
			}
		}
		return false;
	}
}
