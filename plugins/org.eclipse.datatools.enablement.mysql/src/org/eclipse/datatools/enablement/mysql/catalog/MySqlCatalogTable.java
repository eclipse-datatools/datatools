 /*******************************************************************************
  * Copyright (c) 2005 Versant Corporation and others.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License v1.0
  * which accompanies this distribution, and is available at
  * http://www.eclipse.org/legal/epl-v10.html
  * 
  * Contributors:
  *     Versant Corporation - initial API and implementation
  *******************************************************************************/
package org.eclipse.datatools.enablement.mysql.catalog;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.mysql.MysqlPlugin;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.constraints.*;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.schema.*;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.impl.PersistentTableImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This class is the Schema implementation, its purpose is to load columns,
 * constraint, index and primaryKeys
 */
public class MySqlCatalogTable extends PersistentTableImpl implements
		ICatalogObject {

	private static final long serialVersionUID = 3761127145711088689L;

	private boolean columnsLoaded = false;
	private boolean constraintLoaded = false;
	private String tableType;
	private boolean isAutoInc;
	private boolean indexLoaded;
	private boolean pkLoaded;

	public void refresh() {
		this.columnsLoaded = false;
		this.constraintLoaded = false;
		this.pkLoaded = false;
		this.indexLoaded = false;
		super.getColumns().clear();
		super.getConstraints().clear();
		super.getIndex().clear();
		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		return ((MySqlCatalogDatabase) database).getConnection();
	}

	public Database getCatalogDatabase() {
		return this.getSchema().getDatabase();
	}

	public PrimaryKey getPrimaryKey() {
        if (!this.pkLoaded)
                this.loadPrimaryKey();
        return doGetPrimaryKey();
	}
	
	private PrimaryKey doGetPrimaryKey() {
	        Iterator allConstraints = super.getConstraints().iterator();
	        while( allConstraints.hasNext() ) {
	             Constraint currentConstraint = (Constraint)allConstraints.next();
	             if (currentConstraint instanceof PrimaryKey) {
	                return (PrimaryKey)currentConstraint;
	             }
	        }
	        return null;
	}


	public EList getColumns() {
		if (!this.columnsLoaded)
			this.loadColumns();
		return this.columns;
	}

	public EList getConstraints() {
		if (!this.constraintLoaded)
			this.loadConstraints();
		return this.constraints;
	}

	public EList getIndex() {
		if (!this.indexLoaded)
			this.loadIndexes();
		return this.index;
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if (id == SQLTablesPackage.PERSISTENT_TABLE__COLUMNS) {
			this.getColumns();
		} else if (id == SQLTablesPackage.PERSISTENT_TABLE__CONSTRAINTS) {
			this.getConstraints();
		} else if (id == SQLTablesPackage.PERSISTENT_TABLE__INDEX) {
			this.getIndex();
		} else if (id == SQLTablesPackage.PERSISTENT_TABLE__TRIGGERS) {
			this.getTriggers();
		}

		return super.eIsSet(eFeature);
	}

	private synchronized void loadColumns() {
		if (this.columnsLoaded)
			return;
		EList columnList = super.getColumns();

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);

		try {
			DatabaseMetaData metaData = this.getConnection().getMetaData();
			ResultSet r = metaData.getColumns(null, null, this.getName(), null);
			while (r.next()) {
				Column column = new MySqlCatalogColumn();

				final String columnName = r.getString(4);
				column.setName(columnName);

				final String remarks = r.getString(12);
				column.setDescription(remarks);

				String defaultValue = r.getString(13);
				column.setDefaultValue(defaultValue);

				String typeName = r.getString(6);

				final DatabaseDefinition databaseDefinition = this
						.getDatabaseDefinition();
				PredefinedDataTypeDefinition typeDefinition = databaseDefinition
						.getPredefinedDataTypeDefinition(typeName);
				if (typeDefinition != null) {
					PredefinedDataType type = databaseDefinition
							.getPredefinedDataType(typeDefinition);
					if (typeDefinition.isLengthSupported()) {
						EStructuralFeature feature = type.eClass()
								.getEStructuralFeature("length"); //$NON-NLS-1$
						type.eSet(feature, new Integer(r.getInt(7)));
					} else if (typeDefinition.isPrecisionSupported()) {
						EStructuralFeature feature = type.eClass()
								.getEStructuralFeature("precision"); //$NON-NLS-1$
						type.eSet(feature, new Integer(r.getInt(10)));
					}

					if (typeDefinition.isScaleSupported()) {
						EStructuralFeature feature = type.eClass()
								.getEStructuralFeature("scale"); //$NON-NLS-1$
						type.eSet(feature, new Integer(r.getInt(9)));
					}
					column.setContainedType(type);
				}

				final String nulls = r.getString(18);
				if (nulls.equals("YES")) { //$NON-NLS-1$
					column.setNullable(true);
				} else {
					column.setNullable(false);
				}

				columnList.add(column);
			}
			if (isAutoInc) {
				loadIdentity(this.getConnection());
			}
			this.columnsLoaded = true;
			r.close();
		} catch (Exception e) {
            MysqlPlugin.getDefault().getLog().log(new Status(
                    IStatus.ERROR, MysqlPlugin.ID, 0,
                    "Could not load the Columns for table " //$NON-NLS-1$
                    + this.getName(), e));
		}

		this.eSetDeliver(deliver);
	}

	private synchronized void loadIdentity(Connection connection) {
		try {
			Statement s = connection.createStatement();
			final String query = "SHOW COLUMNS FROM " + this.getName(); //$NON-NLS-1$
			ResultSet r = s.executeQuery(query);
			while (r.next()) {
				String extra = r.getString("Extra"); //$NON-NLS-1$
				if (extra != null && extra.equalsIgnoreCase("auto_increment")) { //$NON-NLS-1$
					Column column = getColumn(r.getString("Field")); //$NON-NLS-1$
					final Database database = this.getSchema().getDatabase();
					final DatabaseDefinition databaseDefinition = RDBCorePlugin
							.getDefault().getDatabaseDefinitionRegistry()
							.getDefinition(database);
					final DataModelElementFactory factory = databaseDefinition
							.getDataModelElementFactory();
					IdentitySpecifier identitySpecifier = (IdentitySpecifier) factory
							.create(SQLSchemaPackage.eINSTANCE
									.getIdentitySpecifier());
					column.setIdentitySpecifier(identitySpecifier);
				}
			}
			r.close();
			s.close();
		} catch (Exception e) {
            MysqlPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
                    MysqlPlugin.ID, 0,
                    "Could not load the identity type of coulumns for table " //$NON-NLS-1$
                    + this.getName(), e));
		}

	}

	private synchronized void loadPrimaryKey() {
		if (this.pkLoaded)
			return;
		Connection connection = this.getConnection();
		if (connection == null)
			return;

		this.pkLoaded = true;
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);

		try {
			DatabaseMetaData metaData = connection.getMetaData();
			this.loadPrimaryKey(metaData);
		} catch (Exception e) {
            MysqlPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
                    MysqlPlugin.ID, 0,
                    "Could not get the DatabaseMetaData from connection", e)); //$NON-NLS-1$
		}
		this.eSetDeliver(deliver);
	}

	private synchronized void loadConstraints() {
		if (this.constraintLoaded)
			return;
		Connection connection = this.getConnection();
		if (connection == null)
			return;

		this.constraintLoaded = true;
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);

		try {
			DatabaseMetaData metaData = connection.getMetaData();

			if (!this.pkLoaded) {
				this.loadPrimaryKey(metaData);
				this.pkLoaded = true;
			}

			this.loadForeignKey(metaData);

		} catch (Exception e) {
            MysqlPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
                    MysqlPlugin.ID, 0,
                    "Could not get the DatabaseMetaData from connection", e)); //$NON-NLS-1$
		}
		this.eSetDeliver(deliver);
	}

	private synchronized MySqlCatalogPrimaryKey loadPrimaryKey(
			DatabaseMetaData metaData) {
		super.getConstraints();
		MySqlCatalogPrimaryKey key = null;
		try {
			ResultSet r = metaData.getPrimaryKeys(null, null, this.getName());

			// DataModelElementFactory factory =
			// RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getCatalogDatabase()).getDataModelElementFactory();

			MySqlCatalogTable.KeyColumnCollection columns =
                    new MySqlCatalogTable.KeyColumnCollection(this);
			while (r.next()) {

				if (key == null) {
					String name = r.getString(6);
					key = new MySqlCatalogPrimaryKey();
					key.setName(name);
					super.getConstraints().add(key);
				}
				String columnName = r.getString(4);
				int seq = r.getInt(5);
				columns.add(seq, columnName);
			}

			Iterator it = columns.iterator();
			while (it.hasNext()) {
				Column column = (Column) it.next();
				key.getMembers().add(column);
			}
			r.close();
		} catch (Exception e) {
            MysqlPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
                    MysqlPlugin.ID, 0,
                    "Could not load the primary keys for table " //$NON-NLS-1$
                    + this.getName(), e));
		}

		return key;
	}

    /**
     * Load the ForeignKey for this table.
     */
	private synchronized void loadForeignKey(DatabaseMetaData metaData) {
		try {
			ResultSet r = metaData.getImportedKeys(null, null, this.getName());

			// DataModelElementFactory factory =
			// RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getCatalogDatabase()).getDataModelElementFactory();

			MySqlCatalogForeignKey fk = null;
			MySqlCatalogTable fkTable = null;
			String fkTableName = ""; //$NON-NLS-1$
			String fkName = ""; //$NON-NLS-1$
			while (r.next()) {

				final String fkTable_Name = r.getString("PKTABLE_NAME"); //$NON-NLS-1$
				final String fk_Name = r.getString("FK_NAME"); //$NON-NLS-1$
				if (!fkTableName.equals(fkTable_Name) || !fkName.equals(fk_Name)) {
					// build a new FK on a break in either the table name or the foreign key name
					fkTable = this.getTable(fkTable_Name);

					if (fkTable == null || fk_Name == null)
						continue;
					fkTableName = fkTable_Name;
					fkName = fk_Name;
					fk = new MySqlCatalogForeignKey();
					fk.setName(fk_Name);
					if (fkTable.getPrimaryKey() != null) {
						fk.setUniqueConstraint(fkTable.getPrimaryKey());
					} else {
						Index index = fkTable.findIndexWithColumnName(r
								.getString("PKCOLUMN_NAME")); //$NON-NLS-1$
						if (index != null) {
							fk.setUniqueIndex(index);
						}
					}
					short updateRule = r.getShort("UPDATE_RULE"); //$NON-NLS-1$
					switch (updateRule) {
					case DatabaseMetaData.importedKeyCascade:
						fk.setOnUpdate(ReferentialActionType.CASCADE_LITERAL);
						break;
					case DatabaseMetaData.importedKeyRestrict:
						fk.setOnUpdate(ReferentialActionType.RESTRICT_LITERAL);
						break;
					case DatabaseMetaData.importedKeySetDefault:
						fk.setOnUpdate(ReferentialActionType.SET_DEFAULT_LITERAL);
						break;
					case DatabaseMetaData.importedKeySetNull:
						fk.setOnUpdate(ReferentialActionType.SET_NULL_LITERAL);
						break;
					case DatabaseMetaData.importedKeyNoAction:
                        fk.setOnUpdate(ReferentialActionType.NO_ACTION_LITERAL);
                        break;
                    default:
						fk.setOnUpdate(ReferentialActionType.CASCADE_LITERAL);
						break;
					}
					short deleteRule = r.getShort("DELETE_RULE"); //$NON-NLS-1$
					switch (deleteRule) {
					case DatabaseMetaData.importedKeyCascade:
						fk.setOnDelete(ReferentialActionType.CASCADE_LITERAL);
						break;
					case DatabaseMetaData.importedKeyRestrict:
						fk.setOnDelete(ReferentialActionType.RESTRICT_LITERAL);
						break;
					case DatabaseMetaData.importedKeySetDefault:
						fk.setOnDelete(ReferentialActionType.SET_DEFAULT_LITERAL);
						break;
					case DatabaseMetaData.importedKeySetNull:
						fk.setOnDelete(ReferentialActionType.SET_NULL_LITERAL);
						break;
					case DatabaseMetaData.importedKeyNoAction:
                        fk.setOnDelete(ReferentialActionType.NO_ACTION_LITERAL);
                        break;
                    default:
						fk.setOnDelete(ReferentialActionType.CASCADE_LITERAL);
						break;
					}
					short deferrability = r.getShort("DEFERRABILITY"); //$NON-NLS-1$
					switch (deferrability) {
					case DatabaseMetaData.importedKeyInitiallyDeferred:
						fk.setDeferrable(true);
						fk.setInitiallyDeferred(true);
						break;
					case DatabaseMetaData.importedKeyInitiallyImmediate:
						fk.setDeferrable(true);
						fk.setInitiallyDeferred(false);
						break;
					case DatabaseMetaData.importedKeyNotDeferrable:
					default:
						fk.setDeferrable(false);
						break;
					}
					super.getConstraints().add(fk);
				}

				String columnName = r.getString("FKCOLUMN_NAME"); //$NON-NLS-1$
				Column column = getColumn(columnName);
				fk.getMembers().add(column);
			}
			r.close();
		} catch (Exception e) {
            MysqlPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
                    MysqlPlugin.ID, 0,
                    "Could not load the foreign keys for table " //$NON-NLS-1$
                    + this.getName(), e));
		}
	}

	private Index findIndexWithColumnName(String colName) {
		EList eList = this.getIndex();
		for (Iterator it = eList.iterator(); it.hasNext();) {
			MySqlCatalogIndex index = (MySqlCatalogIndex) it.next();
			EList list = index.getMembers();
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				IndexMember member = (IndexMember) iter.next();
				if (member.getColumn().getName().equals(colName)) {
					return index;
				}
			}
		}
		return null;
	}

    /**
     * load the index's for MySql.
     * MySql show Primary Key as index's, so we make check that
     * it does not pick up the the Primary Key.
      */
	private synchronized void loadIndexes() {
		if (this.indexLoaded)
			return;
		Connection connection = this.getConnection();
		if (connection == null)
			return;

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		EList indexList = super.getIndex();
		try {
			DatabaseMetaData metaData = connection.getMetaData();
			DataModelElementFactory factory = this.getDatabaseDefinition()
					.getDataModelElementFactory();
			ResultSet r = metaData.getIndexInfo(null, null, this.getName(),
					false, false);
			Index index = null;
			String indexName = ""; //$NON-NLS-1$
			PrimaryKey pk = this.getPrimaryKey();
			while (r.next()) {
				final String indName = r.getString(6);
				if (pk == null || !indName.equalsIgnoreCase(pk.getName())) {
					if (!indName.equals(indexName)) {
						indexName = indName;
						index = new MySqlCatalogIndex();
						index.setName(indName);
						index.setSchema(this.getSchema());

						final boolean isUnqiue = !r.getBoolean(4);
						index.setUnique(isUnqiue);

						final short type = r.getShort(7);
						if (type == DatabaseMetaData.tableIndexClustered) {
							index.setClustered(true);
						}

						indexList.add(index);
					}

					final String column_name = r.getString(9);
					if (column_name != null) {
						IndexMember member = (IndexMember) factory
								.create(SQLConstraintsPackage.eINSTANCE
										.getIndexMember());
						member.setColumn(MySqlCatalogTable.getColumn(this,
								column_name));
						final String order = r.getString(10);
						if (order.equals("A")) //$NON-NLS-1$
							member.setIncrementType(IncrementType.ASC_LITERAL);
						else if (order.equals("D")) //$NON-NLS-1$
							member.setIncrementType(IncrementType.DESC_LITERAL);

						index.getMembers().add(member);
					}
				}

			}
			this.indexLoaded = true;
			r.close();
		} catch (Exception e) {
            MysqlPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
                    MysqlPlugin.ID, 0,
                    "Could not load the indexes for table " //$NON-NLS-1$
                    + this.getName(), e));
		}

		this.eSetDeliver(deliver);
	}

//	private UniqueConstraint getRefrencedUniqueConstraint(String pkSchema,
//			String pkTable, String pkName) {
//		BaseTable table = (BaseTable) this.getTable(pkTable);
//		Iterator it = table.getConstraints().iterator();
//		while (it.hasNext()) {
//			Constraint constraint = (Constraint) it.next();
//			if (constraint instanceof UniqueConstraint
//					&& constraint.getName().equals(pkName)) {
//				return (UniqueConstraint) constraint;
//			}
//		}
//		return null;
//	}

	private DatabaseDefinition getDatabaseDefinition() {
		Database d = this.getSchema().getDatabase();
		return RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry()
				.getDefinition(d);
	}

	static Column getColumn(Table table, String columnName) {
		Iterator it = table.getColumns().iterator();
		while (it.hasNext()) {
			Column c = (Column) it.next();
			if (c.getName().equals(columnName))
				return c;
		}
		return null;
	}

	private Column getColumn(String columnName) {
		Iterator it = super.getColumns().iterator();
		while (it.hasNext()) {
			Column c = (Column) it.next();
			if (c.getName().equals(columnName))
				return c;
		}
		return null;
	}

	private MySqlCatalogTable getTable(String tableName) {
		Schema schema = this.getSchema();
		Iterator it = schema.getTables().iterator();

		while (it.hasNext()) {
			MySqlCatalogTable table = (MySqlCatalogTable) it.next();
			if (table.getName().equals(tableName))
				return table;
		}
		return null;
	}

	private static class KeyColumnCollection {

		private Map keyMap = new TreeMap();

		private BaseTable table;

		public KeyColumnCollection(BaseTable table) {
			this.table = table;
		}

		public void add(int seq, String name) {
			Column column = this.getColumn(name);
			String key = "k" + seq; //$NON-NLS-1$
			this.keyMap.put(key, column);
		}

		public Iterator iterator() {
			return keyMap.values().iterator();
		}

		private Column getColumn(String name) {
			Iterator it = this.table.getColumns().iterator();
			while (it.hasNext()) {
				Column column = (Column) it.next();
				if (column.getName().equals(name))
					return column;
			}
			return null;
		}

	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public boolean isAutoInc() {
		return isAutoInc;
	}

	public void setAutoInc(boolean autoInc) {
		isAutoInc = autoInc;
	}

}
