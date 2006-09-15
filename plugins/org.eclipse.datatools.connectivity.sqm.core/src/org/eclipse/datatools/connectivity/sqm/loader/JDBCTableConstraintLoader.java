/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.loader;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.jdbc.JDBCForeignKey;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.jdbc.JDBCPrimaryKey;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.jdbc.JDBCUniqueConstraint;
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

/**
 * Base loader implementation for loading a database's catalog objects. This
 * class may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @author rcernich
 * 
 * Created on Aug 28, 2006
 */
public class JDBCTableConstraintLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_COLUMN_NAME = "COLUMN_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_KEY_SEQ = "KEY_SEQ"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_PK_NAME = "PK_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_PKCOLUMN_NAME = "PKCOLUMN_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_PKTABLE_CAT = "PKTABLE_CAT"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_PKTABLE_SCHEM = "PKTABLE_SCHEM"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_PKTABLE_NAME = "PKTABLE_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_FKCOLUMN_NAME = "FKCOLUMN_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_UPDATE_RULE = "UPDATE_RULE"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_DELETE_RULE = "DELETE_RULE"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_FK_NAME = "FK_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getIndexes()
	 */
	public static final String COLUMN_DEFERRABILITY = "DEFERRABILITY"; //$NON-NLS-1$

	/**
	 * @param catalogObject the Database object upon which this loader operates.
	 */
	public JDBCTableConstraintLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	public JDBCTableConstraintLoader(
										ICatalogObject catalogObject,
										IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		assert (catalogObject instanceof Table);
	}

	public PrimaryKey loadPrimaryKey() throws SQLException {
		ResultSet rs = null;
		try {
			Map columns = new TreeMap();
			PrimaryKey pk = null;
			for (rs = createPrimaryKeyResultSet(); rs.next();) {
				if (pk == null) {
					pk = createPrimaryKey();
					pk.setName(rs.getString(COLUMN_PK_NAME));
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

	public Collection loadUniqueConstraints(PrimaryKey pk) throws SQLException {
		ResultSet rs = null;
		try {
			Map constraints = new HashMap();
			Map constraintColumns = new HashMap();
			for (rs = createUniqueConstraintResultSet(); rs.next();) {
				String ucName = rs.getString(COLUMN_PK_NAME);
				if (ucName.equals(pk.getName())) {
					// Already seen this guy
					continue;
				}
				else if (!constraints.containsKey(ucName)) {
					// create the next UC
					UniqueConstraint uc = createUniqueConstraint();
					uc.setName(ucName);
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
			return constraints.values();
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
		}
	}

	public Collection loadForeignKeys() throws SQLException {
		ResultSet rs = null;
		try {
			Map constraints = new HashMap();
			Map constraintColumns = new HashMap();
			for (rs = createForeignKeyResultSet(); rs.next();) {
				String fkName = rs.getString(COLUMN_FK_NAME);
				if (!constraints.containsKey(fkName)) {
					// create the next FK
					ForeignKey fk = createForeignKey();
					fk.setName(fkName);
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
					fk.setUniqueConstraint(findUniqueConstraint(rs
							.getString(COLUMN_PKTABLE_CAT), rs
							.getString(COLUMN_PKTABLE_SCHEM), rs
							.getString(COLUMN_PKTABLE_NAME), rs
							.getString(COLUMN_PK_NAME)));

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
			return constraints.values();
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
		}
	}

	public void clearConstraints(EList constraintContainer, List remove) {
		constraintContainer.removeAll(remove);
	}

	protected ResultSet createPrimaryKeyResultSet() throws SQLException {
		Table table = getTable();
		Schema schema = table.getSchema();
		return getCatalogObject().getConnection().getMetaData().getPrimaryKeys(
				schema.getCatalog().getName(), schema.getName(),
				table.getName());
	}

	protected ResultSet createUniqueConstraintResultSet() throws SQLException {
		Table table = getTable();
		Schema schema = table.getSchema();
		return getCatalogObject().getConnection().getMetaData()
				.getExportedKeys(schema.getCatalog().getName(),
						schema.getName(), table.getName());
	}

	protected ResultSet createForeignKeyResultSet() throws SQLException {
		Table table = getTable();
		Schema schema = table.getSchema();
		return getCatalogObject().getConnection().getMetaData()
				.getImportedKeys(schema.getCatalog().getName(),
						schema.getName(), table.getName());
	}

	protected void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
		}
	}

	protected PrimaryKey createPrimaryKey() {
		return new JDBCPrimaryKey();
	}

	protected UniqueConstraint createUniqueConstraint() {
		return new JDBCUniqueConstraint();
	}

	protected ForeignKey createForeignKey() {
		return new JDBCForeignKey();
	}

	protected Table getTable() {
		return (Table) getCatalogObject();
	}

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
