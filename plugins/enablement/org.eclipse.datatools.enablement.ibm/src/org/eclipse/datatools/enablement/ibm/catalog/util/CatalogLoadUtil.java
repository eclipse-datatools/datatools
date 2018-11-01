/*******************************************************************************
 * Copyright (c) 2003, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.catalog.util;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ibm.IBMPluginActivator;
import org.eclipse.datatools.enablement.ibm.util.ModelHelper;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class CatalogLoadUtil
{
	public static final String EMPTY = ""; //$NON-NLS-1$

	private static final String CONTEXT_GET_USERNAME = "getUserName"; //$NON-NLS-1$

	public static void load(EObject obj, IProgressMonitor monitor, int task)
	{
		loadInternal(obj, monitor, task, true);

		Iterator it = obj.eClass().getEAllStructuralFeatures().iterator();
		while(it.hasNext() ) {
			EStructuralFeature feature = (EStructuralFeature) it.next();

			if (isRequired(feature))
				obj.eGet(feature);
		}				
	}

	public static void loadWithoutAttributes(EObject obj, IProgressMonitor monitor, int task)
	{
		loadInternal(obj, monitor, task, false);

		Iterator it = obj.eClass().getEAllReferences().iterator();
		while(it.hasNext() && !monitor.isCanceled()) {
			EStructuralFeature feature = (EStructuralFeature) it.next();

			if(isRequired(feature))
				obj.eGet(feature);
		}				
	}

	public static double loadInternal( EObject object, IProgressMonitor monitor, double task, boolean includingAttributes)
	{
		ContainmentService containmentService = RDBCorePlugin.getDefault().getContainmentService();
		Collection c = containmentService.getContainedElements(object);
		Connection connection = object instanceof ICatalogObject ? ((ICatalogObject)object).getConnection() : null;
        
		double acc = 0;

		if(c.size() != 0) {
			double delta = task/c.size();

			Iterator it = c.iterator();
			while(it.hasNext() && !monitor.isCanceled()) {
				EObject child = (EObject) it.next();

				if(containmentService.isDisplayableElement(child)) {
					if(child instanceof ENamedElement) {
						String name = ((ENamedElement) child).getName();

						if(name != null) {
//							String type = IDataToolsUIServiceManager.INSTANCE.getLabelService(child).getDisplayType();
							String type = child.eClass().getName();
							name = "<" + type + "> " + name;  //$NON-NLS-1$//$NON-NLS-2$
//bgp							monitor.subTask(Messages.LOADING_SUBTASK + "  " + name); //$NON-NLS-1$
						}
					}
				}

				acc += loadInternal(child, monitor, delta, includingAttributes);

/*
				try
                {
                    if (monitor.isCanceled() || connection != null && connection.isClosed())
                    {
                        monitor.setCanceled(true);
                        return 0.0;
                    }
                }
                catch (SQLException e)
                {
                    return 0.0;
                }
				if(acc >= 1.0) {
					monitor.worked((int) acc);			
					acc = acc - (int) acc;
				}
*/
			}
		}
		else {
			acc = task;
		}

		if ( includingAttributes ) {
			Iterator it = object.eClass().getEAllStructuralFeatures().iterator();
			while ( it.hasNext()) {
				EStructuralFeature feature = (EStructuralFeature) it.next();

				if(isRequired(feature))
					object.eGet(feature);
			}
		}
		else {
			Iterator it = object.eClass().getEAllReferences().iterator();
			while ( it.hasNext()) {
				EStructuralFeature feature = (EStructuralFeature) it.next();

				if(isRequired(feature))
					object.eGet(feature);
			}
		}
		return acc;
	}
	
	public static void generateImplicitPK(Database database)
	{
		for (Iterator iterS = database.getSchemas().iterator();iterS.hasNext();){
			Schema schema = (Schema) iterS.next();

			for (Iterator iterT = schema.getTables().iterator();iterT.hasNext();) {
				Table table = (Table) iterT.next();

				if (table instanceof BaseTable) {
					generateImplicitPK((BaseTable)table);
				}
			}
		}
	}
	
	private static boolean isRequired(EStructuralFeature feature) {
		//Hard-coded Exceptions
		//Nickname ---> RemoteDataSet
		if ( feature.getEContainingClass().getName().equals("LUWNickname")) { //$NON-NLS-1$
			if ( feature.getName().equals("remoteDataSet")) //$NON-NLS-1$
				return false;
		}
		
		if (feature.isDerived() || feature.isTransient())
			return false;

		return true;
	}
	
	private static void generateImplicitPK(BaseTable table){
		PrimaryKey pk = table.getPrimaryKey();
		if ( pk != null)
			return;
		
		Iterator iter = table.getIndex().iterator();
		while ( iter.hasNext()) {
			Index index = (Index) iter.next();

			if (index.isUnique()) {
				generateImplictPK(table,index);

				break;
			}
		}
	}
	
    private static void generateImplictPK(BaseTable table,Index index){
    	Schema schema = table.getSchema();
		Database database = ModelHelper.getDatabase(schema);
		DatabaseDefinition databaseDefinition = IBMPluginActivator.getInstance().getDatabaseDefinitionRegistry().getDefinition(database);
		DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

		PrimaryKey pk = (PrimaryKey)factory.create(SQLConstraintsPackage.eINSTANCE.getPrimaryKey());
		pk.setEnforced(false);

		EList pkMember = pk.getMembers();
		for ( Iterator iter = index.getMembers().iterator(); iter.hasNext();){
			Column column = ((IndexMember) iter.next()).getColumn();
			if ( column == null) {
				return;
			}
			pkMember.add(column);
		}
		
//bgp		String name = ImplicitRelationshipHandler.INSTANCE.createUniqueConstraintName(table.getConstraints(), PreferenceUtil.getExpandedPKString(table));
//bgp		pk.setName(name);

		table.getConstraints().add(pk);
    }
	
	public static String getFilterString(String predicate, String parameter){
		String filter = EMPTY;
		String pattern = "\\{"; //$NON-NLS-1$

		int occurrences = predicate.length() - predicate.toLowerCase().replaceAll(pattern.toLowerCase(), EMPTY).length();

		if (occurrences > 0) {
			Object[] params = new Object[ occurrences ];

			for (int i = 0; i<params.length; i++) {
				params[i] = parameter;
			}

			filter = MessageFormat.format( predicate.replaceAll( "'", "''" ), params); //$NON-NLS-1$ //$NON-NLS-2$
		}
		else {
			filter = parameter + " " + predicate; //$NON-NLS-1$
		}
		
		return "(" + filter + ")"; //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	public static String parsePredicateFromReqList( //
			String reqList, String schemaCol, String nameCol, //
			@SuppressWarnings ("unused") String filterVal)
	{	
		// Create a set of required objects from the various lists, remove duplicates
		Map<String,Set<String>> objectMap = new HashMap<String,Set<String>>();

		StringTokenizer listTokenizer = new StringTokenizer(reqList, "||"); //$NON-NLS-1$

		while (listTokenizer.hasMoreTokens()) {
			String fromList = listTokenizer.nextToken();

			fromList = removeTag(fromList);

			StringTokenizer objTokenizer = new StringTokenizer(fromList, ","); //$NON-NLS-1$

			while (objTokenizer.hasMoreTokens()) {
				addObjectToMap(objTokenizer.nextToken(), objectMap);
			}
		}
		
		// Return early if no objects are required
		if (objectMap.size() == 0) {
			return EMPTY;
		}
		
		// Format set of objects into an IN-list predicate
		StringBuilder predicate = new StringBuilder();
		Set<String> schemas = objectMap.keySet();

		for (String schema : schemas) {
			predicate.append( "(" ); //$NON-NLS-1$
			predicate.append( schemaCol + "='" + schema + "' AND " + nameCol + " IN (" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			for (String name : objectMap.get(schema)) {
				predicate.append( "'" + name + "'," ); //$NON-NLS-1$ //$NON-NLS-2$
			}

			predicate.setLength(predicate.length()-1); // Remove trailing comma
			predicate.append(")) OR "); //$NON-NLS-1$
		}

		if (predicate.length() == 0) {
			return EMPTY;
		}

		predicate.setLength(predicate.length()-4); // Remove trailing comma

		return predicate.toString();
	}

	private static void addObjectToMap(String obj, Map<String,Set<String>> objectMap) {
		String[] elms = obj.split("\\."); //$NON-NLS-1$
		if (elms.length != 2) {
			return;
		}
		
		String schema = elms[0];
		String name = elms[1];

		Set<String> objectSet = objectMap.get(schema);

		if (objectSet == null) {
			objectSet = new HashSet<String>();
			objectMap.put(schema, objectSet);
		}

		objectSet.add(name);
	}

	private static String removeTag(String fromList) {
		return fromList.substring( fromList.indexOf( ']' ) + 1 );
	}

	public static void safeClose( ResultSet rs )
	{
		try
		{
			if ( rs != null )
			{
				rs.close();
			}
		}
		catch (SQLException e)
		{
			// eat it
		}
	}

	public static void safeClose( Statement stmt, ResultSet rs )
	{
		safeClose( rs );

		try
		{
			if ( stmt != null )
			{
				stmt.close();
			}
		}
		catch (SQLException e)
		{
			// eat it
		}
	}

//<bgp	/**
//	 * Gets the user name in the connection or from CURRENT USER if the
//	 * connection user name is null.
//	 */
//	public static String getUserName( Connection connection, Database database )
//	{
//		String userName = null;
//
//		try
//		{
//			if ( connection != null && connection.getMetaData() != null )
//			{
//				userName = connection.getMetaData().getUserName();
//			}
//		}
//		catch (Exception e)
//		{
//			userName = null;
//
//			DB2PluginActivator.log( e );
//		}
//
//		if ( userName == null || userName.trim().length() == 0 )
//		{
//			try
//			{
//				String query = "select CURRENT USER as USERNAME from sysibm.sysdummy1"; //$NON-NLS-1$
//
//				ResultSet r = new PersistentResultSet( database, CONTEXT_GET_USERNAME, connection, query );
//
//				try
//				{
//					if ( r.next() )
//					{
//						userName = r.getString( "USERNAME" ); //$NON-NLS-1$
//					}
//				}
//				catch (Exception e)
//				{
//					// Eat it
//				}
//				finally
//				{
//					safeClose( r );
//				}
//			}
//			catch (Exception e)
//			{
//				DB2PluginActivator.log( e );
//			}
//		}
//
//		return userName;
//bgp>	}

	public static String safeGetString( ResultSet r, String name ) //
			throws SQLException
	{
		String s = r.getString( name );
		
		return (s != null)
				? s
				: EMPTY;
	}

	public static String safeGetTrimmedString( ResultSet r, String name ) //
			throws SQLException
	{
		String s = r.getString( name );
		
		return (s != null)
				? s.trim()
				: EMPTY;
	}

	public static void safeClose( LineNumberReader lnr )
	{
		if ( lnr != null )
		{
			try
			{
				lnr.close();
			}
			catch (IOException e)
			{
				// eat it
			}
		}
	}

	public static void safeClose( PrintWriter pw )
	{
		if ( pw != null )
		{
			pw.close();
		}
	}
}
