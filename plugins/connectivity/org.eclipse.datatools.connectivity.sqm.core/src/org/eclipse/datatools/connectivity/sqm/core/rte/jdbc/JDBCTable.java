/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.rte.jdbc;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.core.util.CatalogLoaderOverrideManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableColumnLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableConstraintLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableIndexLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableSuperTableLoader;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.IncrementType;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.impl.PersistentTableImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;


public class JDBCTable extends PersistentTableImpl implements ICatalogObject {

	private static final long serialVersionUID = 1122783015230966825L;


	public Connection getConnection() {
		Database db = this.getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}
	
	public Database getCatalogDatabase() {
		return this.getSchema().getCatalog().getDatabase();		
	}
	
	public void refresh() {
		synchronized (columnsLoaded) {
			if (columnsLoaded.booleanValue()) {
				columnsLoaded = Boolean.FALSE;
			}
		}
		synchronized (ucsLoaded) {
			if (ucsLoaded.booleanValue()) {
				pkLoaded = Boolean.FALSE;
				ucsLoaded = Boolean.FALSE;
			}
		}
		synchronized (fksLoaded) {
			if (fksLoaded.booleanValue()) {
				fksLoaded = Boolean.FALSE;
			}
		}
		synchronized (indexesLoaded) {
			if (indexesLoaded.booleanValue()) {
				indexesLoaded = Boolean.FALSE;
			}
		}
		synchronized (supertableLoaded) {
			if (supertableLoaded.booleanValue()) {
				supertableLoaded = Boolean.FALSE;
				setSupertable(null);
			}
		}

		RefreshManager.getInstance().referesh(this);
	}

	public EList getColumns(){
		synchronized (columnsLoaded) {
			if (!columnsLoaded.booleanValue())
				loadColumns();
		}
		return super.getColumns();
	}

	protected JDBCTableColumnLoader createColumnLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());
	
		JDBCBaseLoader loader =
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, 
					SQLTablesPackage.eINSTANCE.getColumn().getInstanceClassName());
		
		if (loader != null) {
			JDBCTableColumnLoader tableColumnLoader = (JDBCTableColumnLoader) loader;
			tableColumnLoader.setCatalogObject(this);
			return tableColumnLoader;
		}
		return new JDBCTableColumnLoader(this);
	}

	protected final JDBCTableColumnLoader getColumnLoader() {
		if (columnLoaderRef == null || columnLoaderRef.get() == null) {
			columnLoaderRef = new SoftReference(createColumnLoader());
		}
		return (JDBCTableColumnLoader) columnLoaderRef.get();
	}

	private void loadColumns() {
		boolean deliver = eDeliver();
		try {
			List container = super.getColumns();
			List existingColumns = new ArrayList(container);

			eSetDeliver(false);

			container.clear();

			getColumnLoader().loadColumns(container, existingColumns);

			getColumnLoader().clearColumns(existingColumns);

			columnsLoaded = Boolean.TRUE;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			eSetDeliver(deliver);
		}
	}

	public PrimaryKey getPrimaryKey() {
		synchronized (ucsLoaded) {
			if (!pkLoaded.booleanValue())
				loadUniqueConstraints();
		}
		return internalGetPrimaryKey(super.getConstraints());
	}

	public List getUniqueConstraints() {
		synchronized (ucsLoaded) {
			if (!ucsLoaded.booleanValue())
				loadUniqueConstraints();
		}
		return internalGetUniqueConstraints(super.getConstraints());
	}

	public List getForeignKeys() {
		synchronized (fksLoaded) {
			if (!fksLoaded.booleanValue())
				loadForeignKeys();
		}
		return internalGetForeignKeys(super.getConstraints());
	}

	public EList getConstraints() {
		synchronized (ucsLoaded) {
			if (!ucsLoaded.booleanValue())
				loadUniqueConstraints();
		}
		synchronized (fksLoaded) {
			if (!fksLoaded.booleanValue())
				loadForeignKeys();
		}
		return super.getConstraints();
	}

	protected JDBCTableConstraintLoader createConstraintLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());
	
		JDBCBaseLoader loader =
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, 
					SQLConstraintsPackage.eINSTANCE.getTableConstraint().getInstanceClassName());
		
		if (loader != null) {
			JDBCTableConstraintLoader tableConstraintLoader = (JDBCTableConstraintLoader) loader;
			tableConstraintLoader.setCatalogObject(this);
			return tableConstraintLoader;
		}
		return new JDBCTableConstraintLoader(this);
	}

	protected final JDBCTableConstraintLoader getConstraintLoader() {
		if (constraintLoaderRef == null || constraintLoaderRef.get() == null) {
			constraintLoaderRef = new SoftReference(createConstraintLoader());
		}
		return (JDBCTableConstraintLoader) constraintLoaderRef.get();
	}

	private void loadUniqueConstraints() {
		boolean deliver = eDeliver();
		try {
			List container = super.getConstraints();
			PrimaryKey existingPK = internalGetPrimaryKey(container);
			PrimaryKey pk = getConstraintLoader().loadPrimaryKey(existingPK);
			if (pk != null) {
				if (existingPK == null) {
					container.add(0, pk);
				}
				else if (!pk.equals(existingPK)) {
					container.set(container.indexOf(existingPK), pk);
				}
				// else PK didn't change
			}
			else if (existingPK != null) {
				container.remove(existingPK);
			}
			pkLoaded = Boolean.TRUE;

			List existingUCs = internalGetUniqueConstraints(container);
			if (pk != null) {
				existingUCs.remove(pk);
			}
			container.removeAll(existingUCs);
			getConstraintLoader().loadUniqueConstraints(getPrimaryKey(),
					container, existingUCs);
			ucsLoaded = Boolean.TRUE;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			eSetDeliver(deliver);
		}
	}
	
	private void loadForeignKeys() {
		boolean deliver = eDeliver();
		try {
			List container = super.getConstraints();
			List existingFKs = internalGetForeignKeys(container);
			container.removeAll(existingFKs);
			getConstraintLoader().loadForeignKeys(container, existingFKs);
			fksLoaded = Boolean.TRUE;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			eSetDeliver(deliver);
		}
	}
	
	public EList getIndex() {
		synchronized (indexesLoaded) {
			if (!indexesLoaded.booleanValue())
				loadIndexes();
		}
		return super.getIndex();
	}

	protected JDBCTableIndexLoader createIndexLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());
	
		JDBCBaseLoader loader =
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, 
					SQLConstraintsPackage.eINSTANCE.getIndex().getInstanceClassName());
		
		if (loader != null) {
			JDBCTableIndexLoader tableIndexLoader = (JDBCTableIndexLoader) loader;
			tableIndexLoader.setCatalogObject(this);
			return tableIndexLoader;
		}
		return new JDBCTableIndexLoader(this);
	}

	protected final JDBCTableIndexLoader getIndexLoader() {
		if (indexLoaderRef == null || indexLoaderRef.get() == null) {
			indexLoaderRef = new SoftReference(createIndexLoader());
		}
		return (JDBCTableIndexLoader) indexLoaderRef.get();
	}

	private void loadIndexes() {
		boolean deliver = eDeliver();
		try {
			List container = super.getIndex();
			List existingIndexes = new ArrayList(container);
			
			eSetDeliver(false);

			container.clear();

			getIndexLoader().loadIndexes(container, existingIndexes);

			getIndexLoader().clearIndexes(existingIndexes);

			indexesLoaded = Boolean.TRUE;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			eSetDeliver(deliver);
		}
	}
	
	public Table getSupertable() {
		synchronized (supertableLoaded) {
			if (!supertableLoaded.booleanValue())
				loadSupertable();
		}
		return super.getSupertable();
	}

	protected JDBCTableSuperTableLoader createSupertableLoader() {
		DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().
			getDefinition(this.getCatalogDatabase());
	
		JDBCBaseLoader loader =
			CatalogLoaderOverrideManager.INSTANCE.getLoaderForDatabase(databaseDefinition, 
					SQLTablesPackage.eINSTANCE.getTable().getInstanceClassName());
		
		if ((loader != null) && (loader instanceof JDBCTableSuperTableLoader)) { 
			JDBCTableSuperTableLoader tableSuperTableLoader = (JDBCTableSuperTableLoader) loader;
			tableSuperTableLoader.setCatalogObject(this);
			return tableSuperTableLoader;
		}
		return new JDBCTableSuperTableLoader(this);
	}

	protected final JDBCTableSuperTableLoader getSupertableLoader() {
		if (supertableLoaderRef == null || supertableLoaderRef.get() == null) {
			supertableLoaderRef = new SoftReference(createSupertableLoader());
		}
		return (JDBCTableSuperTableLoader) supertableLoaderRef.get();
	}

	protected void loadSupertable() { 
		try {
			setSupertable(getSupertableLoader().loadSuperTable());
			supertableLoaded = Boolean.TRUE;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private PrimaryKey internalGetPrimaryKey(Collection constraints) {
		for( Iterator it = constraints.iterator(); it.hasNext(); ) {
			Constraint currentConstraint = (Constraint)it.next();
			if (currentConstraint instanceof PrimaryKey) {
				return (PrimaryKey)currentConstraint;
			}
		}
		return null;
	}
	
	private List internalGetUniqueConstraints(Collection constraints) {
		Vector uniqueConstraints = new Vector();
		for( Iterator it = constraints.iterator(); it.hasNext(); ) {
			Constraint currentConstraint = (Constraint)it.next();
			if (currentConstraint instanceof UniqueConstraint) {
				uniqueConstraints.add(currentConstraint);
			}
		}
		return uniqueConstraints;
	}
	
	private List internalGetForeignKeys(Collection constraints) {
		Vector uniqueConstraints = new Vector();
		for( Iterator it = constraints.iterator(); it.hasNext(); ) {
			Constraint currentConstraint = (Constraint)it.next();
			if (currentConstraint instanceof ForeignKey) {
				uniqueConstraints.add(currentConstraint);
			}
		}
		return uniqueConstraints;
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case SQLTablesPackage.PERSISTENT_TABLE__COLUMNS:
			getColumns();
			break;
		case SQLTablesPackage.PERSISTENT_TABLE__CONSTRAINTS:
			getConstraints();
			break;
		case SQLTablesPackage.PERSISTENT_TABLE__INDEX:
			getIndex();
			break;
		case SQLTablesPackage.PERSISTENT_TABLE__SUPERTABLE:
			getSupertable();
		}
		return super.eIsSet(eFeature);
	}
	
	private Boolean columnsLoaded = Boolean.FALSE;
	private SoftReference columnLoaderRef;
	private Boolean pkLoaded = Boolean.FALSE;
	private Boolean ucsLoaded = Boolean.FALSE;
	private Boolean fksLoaded = Boolean.FALSE;
	private SoftReference constraintLoaderRef;
	private Boolean indexesLoaded = Boolean.FALSE;
	private SoftReference indexLoaderRef;
	protected Boolean supertableLoaded = Boolean.FALSE; 
	private SoftReference supertableLoaderRef;
	private Boolean subTablesLoaded = Boolean.FALSE;

	
	/**
	 * @deprecated
	 */
	public static void loadColumns(Connection connection, EList columnList, Table table) throws SQLException {
		final Schema schema = table.getSchema();
		final Database database = schema.getDatabase();
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

		Object[] list = columnList.toArray();
		columnList.clear();
		
		try {
			DatabaseMetaData metaData = connection.getMetaData();
			ResultSet r = metaData.getColumns(null, schema.getName(), table.getName(), null);
			while(r.next()) {
				final String columnName = r.getString(4);

				Object element = JDBCTable.findElement(list, columnName,SQLTablesPackage.eINSTANCE.getColumn());
				
				Column column;
				if (element != null){
					column = (Column) element;
					((ICatalogObject)element).refresh();
				} else {
					column = new JDBCColumn();
					column.setName(columnName);
				}	
				
				final String remarks = r.getString(12);
				column.setDescription(remarks);

				String defaultValue = r.getString(13);
				column.setDefaultValue(defaultValue);

				String typeName = r.getString(6);
				
				PredefinedDataTypeDefinition typeDefinition = databaseDefinition.getPredefinedDataTypeDefinition(typeName);
				if(typeDefinition != null) {
					PredefinedDataType type = databaseDefinition.getPredefinedDataType(typeDefinition);
					if(typeDefinition.isLengthSupported()) {
						EStructuralFeature feature = type.eClass().getEStructuralFeature("length");  //$NON-NLS-1$
						type.eSet(feature, new Integer(r.getInt(7)));
					}
					else if(typeDefinition.isPrecisionSupported()) {
						EStructuralFeature feature = type.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
						type.eSet(feature, new Integer(r.getInt(10)));
					}
					
					if(typeDefinition.isScaleSupported()) {
						EStructuralFeature feature = type.eClass().getEStructuralFeature("scale"); //$NON-NLS-1$
						type.eSet(feature, new Integer(r.getInt(9)));
					}
					column.setContainedType(type);
				}
				else {
					System.out.println("Unresolved datatype: " + typeName); //$NON-NLS-1$
					Iterator it = databaseDefinition.getPredefinedDataTypes();
					while (it.hasNext()) {
						PredefinedDataTypeDefinition datatype = (PredefinedDataTypeDefinition)it.next();
						PredefinedDataType type = databaseDefinition.getPredefinedDataType(datatype);
						column.setContainedType(type);
						break;
					}
				}

				final String nulls = r.getString(18);
				if(nulls.equals("YES")) column.setNullable(true);  //$NON-NLS-1$
				else column.setNullable(false);

				columnList.add(column);
			}
			r.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @deprecated
	 */
	public static void loadIndexes(Connection connection, EList indexList, Table table) throws SQLException {
		try {
			final Schema schema = table.getSchema();
			final Catalog catalog = schema.getCatalog();
			final Database database = catalog.getDatabase();
			final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
			final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();
			
			DatabaseMetaData metaData = connection.getMetaData();
			ResultSet r = metaData.getIndexInfo(null,schema.getName(),table.getName(),false,false);
			Index index = null;
			String indexName=""; //$NON-NLS-1$
			while(r.next()) {
				final String indName = r.getString(6);
				if (indName == null) continue;
				if (!indName.equals(indexName)){
					indexName = indName;
					index = new JDBCIndex();
					index.setName(indName);
	
					final String indSchema = r.getString(2);
					index.setSchema(JDBCTable.getSchema(table,indSchema));

					final boolean isUnqiue = !r.getBoolean(4);
					index.setUnique(isUnqiue);

					final short type = r.getShort(7);
					if (type == DatabaseMetaData.tableIndexClustered) {
						index.setClustered(true);
					}
					
					indexList.add(index);
				}
				final String column_name= r.getString(9);
				if (column_name !=null){
					IndexMember member = (IndexMember) factory.create(SQLConstraintsPackage.eINSTANCE.getIndexMember()); 
					member.setColumn(JDBCTable.getColumn(table,column_name));
					final String order = r.getString(10);
					if (order != null) {
						if(order.equals("A"))  //$NON-NLS-1$
							member.setIncrementType(IncrementType.ASC_LITERAL);
						else if(order.equals("D"))  //$NON-NLS-1$
							member.setIncrementType(IncrementType.DESC_LITERAL);
					}
					index.getMembers().add(member);
				}
				
			}
			r.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Object findElement(Object[] list, String name,EClass metaclass){
		Object object = null;
		for (int i = 0; i < list.length; i++){
			SQLObject sqlObject = (SQLObject) list[i];
			if (sqlObject.getName().equals(name) && sqlObject.eClass() == metaclass){
				object = list[i];
				break;
			}
		}
		return object;
	}
	
	private static Schema getSchema(Table table, String schemaName) {
		Schema s = table.getSchema();
		if(s.getName().equals(schemaName)) return s;
		Database d = s.getDatabase();
		Iterator it = d.getSchemas().iterator();
		while(it.hasNext()) {
			s = (Schema) it.next();
			if(s.getName().equals(schemaName)) return s;
		}
		return null;
	}

	private static Column getColumn(Table table,String columnName) {
		Iterator it = table.getColumns().iterator();
		while(it.hasNext()) {
			Column c = (Column) it.next();
			if(c.getName().equals(columnName)) return c;
		}
		return null;
	}
	
}
