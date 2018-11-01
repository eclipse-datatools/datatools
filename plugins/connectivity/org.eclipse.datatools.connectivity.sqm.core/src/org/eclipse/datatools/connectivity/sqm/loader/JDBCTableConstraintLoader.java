/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.loader;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCForeignKey;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCPrimaryKey;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCUniqueConstraint;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;

import com.ibm.icu.text.MessageFormat;

/**
 * Base loader implementation for loading a table's constraint objects (e.g. PK,
 * FK, etc.). This class may be specialized as necessary to meet a particular
 * vendor's needs.
 * 
 * @since 1.0
 */
public class JDBCTableConstraintLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the column's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getExportedKeys()
	 */
	public static final String COLUMN_COLUMN_NAME = "COLUMN_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the column's key sequence.
	 * 
	 * @see java.sql.DatabaseMetaData.getExportedKeys()
	 */
	public static final String COLUMN_KEY_SEQ = "KEY_SEQ"; //$NON-NLS-1$

	/**
	 * The column name containing the primary key's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getExportedKeys()
	 */
	public static final String COLUMN_PK_NAME = "PK_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing a primary key's column name.
	 * 
	 * @see java.sql.DatabaseMetaData.getExportedKeys()
	 */
	public static final String COLUMN_PKCOLUMN_NAME = "PKCOLUMN_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the primary key's catalog.
	 * 
	 * @see java.sql.DatabaseMetaData.getImportedKeys()
	 */
	public static final String COLUMN_PKTABLE_CAT = "PKTABLE_CAT"; //$NON-NLS-1$

	/**
	 * The column name containing the primary key's schema.
	 * 
	 * @see java.sql.DatabaseMetaData.getImportedKeys()
	 */
	public static final String COLUMN_PKTABLE_SCHEM = "PKTABLE_SCHEM"; //$NON-NLS-1$

	/**
	 * The column name containing the primary key's table.
	 * 
	 * @see java.sql.DatabaseMetaData.getImportedKeys()
	 */
	public static final String COLUMN_PKTABLE_NAME = "PKTABLE_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the foreign key's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getImportedKeys()
	 */
	public static final String COLUMN_FKCOLUMN_NAME = "FKCOLUMN_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the constraint's update rule.
	 * 
	 * @see java.sql.DatabaseMetaData.getImportedKeys()
	 */
	public static final String COLUMN_UPDATE_RULE = "UPDATE_RULE"; //$NON-NLS-1$

	/**
	 * The column name containing the constraint's delete rule.
	 * 
	 * @see java.sql.DatabaseMetaData.getImportedKeys()
	 */
	public static final String COLUMN_DELETE_RULE = "DELETE_RULE"; //$NON-NLS-1$

	/**
	 * The column name containing the foreign key's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getImportedKeys()
	 */
	public static final String COLUMN_FK_NAME = "FK_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the constraint's deferrability.
	 * 
	 * @see java.sql.DatabaseMetaData.getImportedKeys()
	 */
	public static final String COLUMN_DEFERRABILITY = "DEFERRABILITY"; //$NON-NLS-1$

	/**
	 * This constructs the loader using no filter.
	 * 
	 * @param catalogObject the Table object upon which this loader operates.
	 */
	public JDBCTableConstraintLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	/**
	 * @param catalogObject the Catalog object upon which this loader operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "constraint" objects being loaded
	 */
	public JDBCTableConstraintLoader(
										ICatalogObject catalogObject,
										IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		if (catalogObject != null)
			assert (catalogObject instanceof Table);
	}

	/**
	 * @return the table's primary key
	 * 
	 * @throws SQLException if an error occurred during loading.
	 * 
	 * @deprecated see {@link #loadPrimaryKey(PrimaryKey)}
	 */
	public PrimaryKey loadPrimaryKey() throws SQLException {
		return loadPrimaryKey(null);
	}

	/**
	 * Loads the "primary key" object from the database. This method uses the
	 * result set from createPrimaryKeyResultSet() to load the "primary key"
	 * object from the server..
	 * 
	 * @param existingPK the existing primary key, if one exists.
	 * @return the table's primary key
	 * 
	 * @throws SQLException if an error occurred during loading.
	 */
	public PrimaryKey loadPrimaryKey(PrimaryKey existingPK) throws SQLException {
		ResultSet rs = null;
		try {
			Map columns = new TreeMap();
			PrimaryKey pk = null;
			for (rs = createPrimaryKeyResultSet(); rs.next();) {
				if (pk == null) {
					String pkName = rs.getString(COLUMN_PK_NAME);
					if (pkName == null) {
						return null;
					}
					if (existingPK != null
							&& pkName.equals(existingPK.getName())) {
						pk = existingPK;
						pk.getMembers().clear();
						if (existingPK instanceof ICatalogObject) {
							((ICatalogObject) pk).refresh();
						}
					}
					else {
						pk = createPrimaryKey();
						pk.setName(pkName);
					}
				}
				columns.put(new Integer(rs.getShort(COLUMN_KEY_SEQ)),
						findColumn(rs.getString(COLUMN_COLUMN_NAME)));
			}
			for (Iterator it = columns.values().iterator(); it.hasNext();) {
				pk.getMembers().add(it.next());
			}
			return pk;
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
		}
	}

	/**
	 * @param pk the table's primary key. Used to prevent duplicating the PK
	 *        constraint.
	 * @return a collection of UniqueConstraint objects
	 * 
	 * @throws SQLException if an error occurred during loading.
	 * 
	 * @deprecated see
	 *             {@link #loadUniqueConstraints(PrimaryKey, List, Collection)}
	 */
	public Collection loadUniqueConstraints(PrimaryKey pk) throws SQLException {
		List retVal = new ArrayList();
		loadUniqueConstraints(pk, retVal, Collections.EMPTY_SET);
		return retVal;
	}

	/**
	 * Loads the "unique constraint" objects from the database. This method uses
	 * the result set from createUniqueConstraintResultSet() to load the "unique
	 * constraint" objects from the server.
	 * 
	 * @param pk the table's primary key. Used to prevent duplicating the PK
	 *        constraint.
	 * @param containmentList the containment list held by parent
	 * @param existingUCs the catalog objects which were previously loaded
	 * 
	 * @throws SQLException if an error occurred during loading.
	 */
	public void loadUniqueConstraints(PrimaryKey pk, List containmentList,
			Collection existingUCs) throws SQLException {
		ResultSet rs = null;
		if (pk != null) {
			// Remove this guy from the list.
			existingUCs.remove(pk);
		}
		try {
			Map constraints = new HashMap();
			Map constraintColumns = new HashMap();
			for (rs = createUniqueConstraintResultSet(); rs.next();) {
				String ucName = rs.getString(COLUMN_PK_NAME);
				if (ucName.equals(pk == null ? null : pk.getName())) {
					// Already seen this guy
					continue;
				}
				else if (!constraints.containsKey(ucName)) {
					UniqueConstraint uc = (UniqueConstraint) getAndRemoveSQLObject(
							existingUCs, ucName);
					if (uc == null) {
						// create the next UC
						uc = createUniqueConstraint();
						uc.setName(ucName);
					}
					else {
						uc.getMembers().clear();
						if (uc instanceof ICatalogObject) {
							((ICatalogObject) uc).refresh();
						}
					}
					containmentList.add(uc);
					constraints.put(ucName, uc);
					constraintColumns.put(ucName, new TreeMap());
				}
				((Map) constraintColumns.get(ucName)).put(new Integer(rs
						.getShort(COLUMN_KEY_SEQ)), findColumn(rs
						.getString(COLUMN_PKCOLUMN_NAME)));
			}
			for (Iterator it = constraints.entrySet().iterator(); it.hasNext();) {
				Map.Entry entry = (Map.Entry) it.next();
				UniqueConstraint uc = (UniqueConstraint) entry.getValue();
				for (Iterator colIt = ((Map) constraintColumns
						.get(uc.getName())).values().iterator(); colIt
						.hasNext();) {
					uc.getMembers().add(colIt.next());
				}
			}
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
		}
	}

	/**
	 * @return a collection of ForeignKey objects
	 * 
	 * @throws SQLException if an error occurred during loading.
	 * 
	 * @deprecated see {@link #loadForeignKeys(List, Collection)}
	 */
	public Collection loadForeignKeys() throws SQLException {
		List retVal = new ArrayList();
		loadForeignKeys(retVal, Collections.EMPTY_SET);
		return retVal;
	}

	/**
	 * Loads the "foreign key" objects from the database. This method uses the
	 * result set from createUniqueConstraintResultSet() to load the "foreign
	 * key" objects from the server.
	 * 
	 * @param containmentList the containment list held by parent
	 * @param existingFKs the catalog objects which were previously loaded
	 * @throws SQLException if an error occurred during loading.
	 */
	public void loadForeignKeys(List containmentList, Collection existingFKs)
			throws SQLException {
		ResultSet rs = null;
		try {
			Map constraints = new HashMap();
			Map constraintColumns = new HashMap();
			for (rs = createForeignKeyResultSet(); rs.next();) {
				String fkName = rs.getString(COLUMN_FK_NAME);

				if (!constraints.containsKey(fkName)) {
					ForeignKey fk = (ForeignKey) getAndRemoveSQLObject(
							existingFKs, fkName);
					if (fk == null) {
						// create the next FK
						fk = createForeignKey();
						fk.setName(fkName);
					}
					else {
						fk.getMembers().clear();
						if (fk instanceof ICatalogObject) {
							((ICatalogObject) fk).refresh();
						}
					}
					switch (rs.getShort(COLUMN_UPDATE_RULE)) {
					case DatabaseMetaData.importedKeyCascade:
						fk.setOnUpdate(ReferentialActionType.CASCADE_LITERAL);
						break;
					case DatabaseMetaData.importedKeyRestrict:
						fk.setOnUpdate(ReferentialActionType.RESTRICT_LITERAL);
						break;
					case DatabaseMetaData.importedKeySetDefault:
						fk
								.setOnUpdate(ReferentialActionType.SET_DEFAULT_LITERAL);
						break;
					case DatabaseMetaData.importedKeySetNull:
						fk.setOnUpdate(ReferentialActionType.SET_NULL_LITERAL);
						break;
					case DatabaseMetaData.importedKeyNoAction:
					default:
						fk.setOnUpdate(ReferentialActionType.NO_ACTION_LITERAL);
						break;
					}
					switch (rs.getShort(COLUMN_DELETE_RULE)) {
					case DatabaseMetaData.importedKeyCascade:
						fk.setOnDelete(ReferentialActionType.CASCADE_LITERAL);
						break;
					case DatabaseMetaData.importedKeyRestrict:
						fk.setOnDelete(ReferentialActionType.RESTRICT_LITERAL);
						break;
					case DatabaseMetaData.importedKeySetDefault:
						fk
								.setOnDelete(ReferentialActionType.SET_DEFAULT_LITERAL);
						break;
					case DatabaseMetaData.importedKeySetNull:
						fk.setOnDelete(ReferentialActionType.SET_NULL_LITERAL);
						break;
					case DatabaseMetaData.importedKeyNoAction:
					default:
						fk.setOnDelete(ReferentialActionType.NO_ACTION_LITERAL);
						break;
					}
					switch (rs.getShort(COLUMN_DEFERRABILITY)) {
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
					UniqueConstraint uk = findUniqueConstraint(rs
							.getString(COLUMN_PKTABLE_CAT), rs
							.getString(COLUMN_PKTABLE_SCHEM), rs
							.getString(COLUMN_PKTABLE_NAME), rs
							.getString(COLUMN_PK_NAME));
					if (uk == null) continue;
					fk.setUniqueConstraint(uk);

					containmentList.add(fk);
					constraints.put(fkName, fk);
					constraintColumns.put(fkName, new TreeMap());

				}
				((Map) constraintColumns.get(fkName)).put(new Integer(rs
						.getShort(COLUMN_KEY_SEQ)), findColumn(rs
						.getString(COLUMN_FKCOLUMN_NAME)));
			}
			for (Iterator it = constraints.entrySet().iterator(); it.hasNext();) {
				Map.Entry entry = (Map.Entry) it.next();
				ForeignKey fk = (ForeignKey) entry.getValue();
				for (Iterator colIt = ((Map) constraintColumns
						.get(fk.getName())).values().iterator(); colIt
						.hasNext();) {
					fk.getMembers().add(colIt.next());
				}
				initReferenceAnnotation(fk);
			}
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
		}
	}

	/**
	 * Removes the specified constraints from the model.
	 * 
	 * @param constraintContainer the constraints container
	 * @param remove the constraints to remove.
	 */
	public void clearConstraints(EList constraintContainer, List remove) {
		constraintContainer.removeAll(remove);
	}

	/**
	 * Creates a result set to be used by the primary key loading logic. The
	 * default version uses of the JDBC DatabaseMetaData.getPrimaryKeys() to
	 * create the result set. This method may be overridden to use a vendor
	 * specific query. However, the default logic requires the columns named by
	 * the "COLUMN_*" fields. Keep this in mind if you plan to reuse the default
	 * logic (e.g. loadPrimaryKey()).
	 * 
	 * @return a result containing the information used to initialize PrimaryKey
	 * 
	 * @throws SQLException if an error occurs
	 */
	protected ResultSet createPrimaryKeyResultSet() throws SQLException {
		try {
			Table table = getTable();
			Schema schema = table.getSchema();
			return getCatalogObject().getConnection().getMetaData().getPrimaryKeys(
					schema.getCatalog().getName(), schema.getName(),
					table.getName());
		}
		catch (RuntimeException e) {
			SQLException error = new SQLException(
					MessageFormat
							.format(
									Messages.Error_Unsupported_DatabaseMetaData_Method,
									new Object[] { "java.sql.DatabaseMetaData.getPrimaryKeys()"})); //$NON-NLS-1$
			error.initCause(e);
			throw error;
		}
	}

	/**
	 * Creates a result set to be used by the unique constraint loading logic.
	 * The default version uses of the JDBC DatabaseMetaData.getExportedKeys()
	 * to create the result set. This method may be overridden to use a vendor
	 * specific query. However, the default logic requires the columns named by
	 * the "COLUMN_*" fields. Keep this in mind if you plan to reuse the default
	 * logic (e.g. loadUniqueConstraints()).
	 * 
	 * @return a result containing the information used to initialize
	 *         UniqueConstraint objects
	 * 
	 * @throws SQLException if an error occurs
	 */
	protected ResultSet createUniqueConstraintResultSet() throws SQLException {
		try {
			Table table = getTable();
			Schema schema = table.getSchema();
			return getCatalogObject().getConnection().getMetaData()
					.getExportedKeys(schema.getCatalog().getName(),
							schema.getName(), table.getName());
		}
		catch (RuntimeException e) {
			SQLException error = new SQLException(
					MessageFormat
							.format(
									Messages.Error_Unsupported_DatabaseMetaData_Method,
									new Object[] { "java.sql.DatabaseMetaData.getExportedKeys()"})); //$NON-NLS-1$
			error.initCause(e);
			throw error;
		}
	}

	/**
	 * Creates a result set to be used by the unique constraint loading logic.
	 * The default version uses of the JDBC DatabaseMetaData.getExportedKeys()
	 * to create the result set. This method may be overridden to use a vendor
	 * specific query. However, the default logic requires the columns named by
	 * the "COLUMN_*" fields. Keep this in mind if you plan to reuse the default
	 * logic (e.g. loadForeignKeys()).
	 * 
	 * @return a result containing the information used to initialize ForeignKey
	 *         objects
	 * 
	 * @throws SQLException if an error occurs
	 */
	protected ResultSet createForeignKeyResultSet() throws SQLException {
		try {
			Table table = getTable();
			Schema schema = table.getSchema();
			return getCatalogObject().getConnection().getMetaData()
					.getImportedKeys(schema.getCatalog().getName(),
							schema.getName(), table.getName());
		}
		catch (RuntimeException e) {
			SQLException error = new SQLException(
					MessageFormat
							.format(
									Messages.Error_Unsupported_DatabaseMetaData_Method,
									new Object[] { "java.sql.DatabaseMetaData.getImportedKeys()"})); //$NON-NLS-1$
			error.initCause(e);
			throw error;
		}
	}

	/**
	 * Closes the result set used for catalog object loading. This method is
	 * implemented as rs.close(). However, if you used a Statement object to
	 * create the result set, this is where you would close that Statement.
	 * 
	 * @param rs the result set to close. This will be the result set created by
	 *        createResultSet().
	 */
	protected void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
		}
	}

	/**
	 * Returns a new PrimaryKey object. By default, this method returns a new
	 * JDBCPrimaryKey.
	 * 
	 * @return a new PrimaryKey object.
	 */
	protected PrimaryKey createPrimaryKey() {
		return new JDBCPrimaryKey();
	}

	/**
	 * Returns a new UniqueConstraint object. By default, this method returns a
	 * new JDBCUniqueConstraint.
	 * 
	 * @return a new UniqueConstraint object.
	 */
	protected UniqueConstraint createUniqueConstraint() {
		return new JDBCUniqueConstraint();
	}

	/**
	 * Returns a new ForeignKey object. By default, this method returns a new
	 * JDBCForeignKey.
	 * 
	 * @return a new ForeignKey object.
	 */
	protected ForeignKey createForeignKey() {
		return new JDBCForeignKey();
	}

	/**
	 * Utility method.
	 * 
	 * @return returns the catalog object being operated upon as a Table (i.e.
	 *         (Table) getCatalogObject()).
	 */
	protected Table getTable() {
		return (Table) getCatalogObject();
	}

	/**
	 * Initializes the reference annotations for the foreign key.
	 * 
	 * @param fk the foreign key to initialize
	 */
	protected void initReferenceAnnotation(ForeignKey fk) {
		EAnnotation eAnnotation = fk
				.addEAnnotation(RDBCorePlugin.FK_MODELING_RELATIONSHIP);
		fk.addEAnnotationDetail(eAnnotation,
				RDBCorePlugin.FK_IS_IDENTIFYING_RELATIONSHIP, new Boolean(
						foreignKeyIsIdentifyingRelationship(fk)).toString());

		fk.addEAnnotationDetail(eAnnotation,
				RDBCorePlugin.FK_CHILD_MULTIPLICITY, RDBCorePlugin.MANY);
		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_CHILD_ROLE_NAME,
				new String());
		fk.addEAnnotationDetail(eAnnotation,
				RDBCorePlugin.FK_PARENT_MULTIPLICITY,
				(fk.getMembers().size() > 0) ? RDBCorePlugin.ZERO_TO_ONE
						: RDBCorePlugin.ONE);
		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_PARENT_ROLE_NAME,
				new String());
	}

	/**
	 * @param fk the foreign key
	 * @return true if one of the foreign key's columns is part of a table's
	 *         primary key
	 */
	protected boolean foreignKeyIsIdentifyingRelationship(ForeignKey fk) {
		boolean isIdentifying = true;

		for (Iterator it = fk.getMembers().iterator(); it.hasNext();) {
			Column column = (Column) it.next();
			if (!column.isPartOfPrimaryKey()) {
				isIdentifying = false;
				break;
			}
		}

		return isIdentifying;
	}

	/**
	 * Returns the column object with the specified columnName.
	 * 
	 * @param columnName the name of the column to find.
	 * 
	 * @return the Column; null if the named column does not exist.
	 */
	protected Column findColumn(String columnName) {
		if (columnName == null) {
			return null;
		}

		for (Iterator it = getTable().getColumns().iterator(); it.hasNext();) {
			Column column = (Column) it.next();
			if (columnName.equals(column.getName())) {
				return column;
			}
		}
		return null;
	}

	/**
	 * Returns the unique constraint for the catalog.schema.table.keyName.
	 * 
	 * @param catalogName the catalog name
	 * @param schemaName the schema name
	 * @param tableName the table name
	 * @param keyName the key name
	 * 
	 * @return the UniqueConstraint; null if it does not exist.
	 */
	protected UniqueConstraint findUniqueConstraint(String catalogName,
			String schemaName, String tableName, String keyName) {
		if (keyName == null) {
			return null;
		}
		Table table = findTable(catalogName, schemaName, tableName);
		if (table == null || !(table instanceof BaseTable)) {
			return null;
		}
		for (Iterator it = ((BaseTable) table).getUniqueConstraints()
				.iterator(); it.hasNext();) {
			UniqueConstraint uc = (UniqueConstraint) it.next();
			if (keyName.equals(uc.getName())) {
				return uc;
			}
		}
		return null;
	}

	/**
	 * Returns the table for the catalog.schema.table.
	 * 
	 * @param catalogName the containing catalog's name
	 * @param schemaName the containing schema's name
	 * @param tableName the table's name
	 * 
	 * @return the Table; null if it does not exit.
	 */
	protected Table findTable(String catalogName, String schemaName,
			String tableName) {
		if (tableName == null) {
			return null;
		}
		if (catalogName == null) {
			catalogName = getTable().getSchema().getCatalog().getName();
			try {
				if (getCatalogObject().getConnection().getMetaData()
						.supportsCatalogsInTableDefinitions()) {
					catalogName = new String();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (schemaName == null) {
			schemaName = getTable().getSchema().getName();
			try {
				if (getCatalogObject().getConnection().getMetaData()
						.supportsSchemasInTableDefinitions()) {
					schemaName = new String();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		Database db = getCatalogObject().getCatalogDatabase();
		for (Iterator catIt = db.getCatalogs().iterator(); catIt.hasNext();) {
			Catalog catalog = (Catalog) catIt.next();
			if (catalogName.equals(catalog.getName())) {
				for (Iterator schemIt = catalog.getSchemas().iterator(); schemIt
						.hasNext();) {
					Schema schema = (Schema) schemIt.next();
					if (schemaName.equals(schema.getName())) {
						for (Iterator tableIt = schema.getTables().iterator(); tableIt
								.hasNext();) {
							Table table = (Table) tableIt.next();
							if (tableName.equals(table.getName())) {
								// found it
								return table;
							}
						}
					}
				}
			}
		}
		return null;
	}

}
