/*******************************************************************************
 * Copyright (c) 2006, 2013 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: 
 *      rcernich - initial API and implementation
 *      Actuate Corporation - re-factoring to expose method to sub-classes (BZ 348160)
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.loader;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCSchema;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

import com.ibm.icu.text.MessageFormat;

/**
 * Base loader implementation for loading a database's schema objects. This
 * class may be specialized as necessary to meet a particular vendor's needs.
 */
public class JDBCSchemaLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the schema's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getSchemas()
	 */
	public static final String COLUMN_TABLE_SCHEM = "TABLE_SCHEM"; //$NON-NLS-1$

	/**
	 * The column name containing the schema's catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getSchemas()
	 */
	public static final String COLUMN_TABLE_CATALOG = "TABLE_CATALOG"; //$NON-NLS-1$

	private Set mSupportedColumns;

	/**
	 * This constructs the loader using a SchemaFilterProvider filter.
	 * 
	 * @param catalogObject the Catalog object upon which this loader operates.
	 */
	public JDBCSchemaLoader(ICatalogObject catalogObject) {
		this(catalogObject, new SchemaFilterProvider());
	}

	/**
	 * @param catalogObject the Catalog object upon which this loader operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "schema" objects being loaded
	 */
	public JDBCSchemaLoader(ICatalogObject catalogObject,
							IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		if (catalogObject != null)
			assert (catalogObject instanceof Catalog);
	}

	/**
	 * @return a collection of Schema objects
	 * 
	 * @throws SQLException if an error occurred during loading.
	 * 
	 * @deprecated see {@link #loadSchemas(List, Collection)}
	 */
	public List loadSchemas() throws SQLException {
		List retVal = new ArrayList();
		loadSchemas(retVal, Collections.EMPTY_SET);
		return retVal;
	}

	/**
	 * Loads the "schema" objects from the database. This method uses the result
	 * set from createResultSet() to load the "schema" objects from the server.
	 * This method first checks the name of the "schema" to determine whether or
	 * not it should be filtered. If it is not filtered, it checks to see if an
	 * object with that name was loaded previously. If it finds an existing
	 * object, it refreshes that object and adds it to the containment list. If
	 * the named object does not exist, the result set is passed to
	 * processRow(). Schema objects are created using the factory method,
	 * createSchema() and initialized through the initialize() method.
	 * 
	 * This method should only be overridden as a last resort when the desired
	 * behavior cannot be acheived by overriding createResultSet(),
	 * closeResultSet(), processRow(), createSchema() and initialize().
	 * 
	 * @param containmentList the containment list held by parent
	 * @param existingSchemas the catalog objects which were previously loaded
	 * @throws SQLException if an error occurred during loading.
	 */
	public void loadSchemas(List containmentList, Collection existingSchemas)
			throws SQLException {
		ResultSet rs = null;
		try {
			initActiveFilter();
			rs = createResultSet();
			if (mSupportedColumns == null) {
				mSupportedColumns = new TreeSet();
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int colNum = 1, colCount = rsmd.getColumnCount(); colNum <= colCount; ++colNum) {
					mSupportedColumns.add(rsmd.getColumnName(colNum));
				}
			}
			while (rs.next()) {
				if (!isSchemaInCatalog(rs)) {
					continue;
				}
				String schemaName = rs.getString(COLUMN_TABLE_SCHEM);
				if (schemaName == null || isFiltered(schemaName)) {
					continue;
				}
				Schema schema = (Schema) getAndRemoveSQLObject(existingSchemas,
						schemaName);
				if (schema == null) {
					schema = processRow(rs);
					if (schema != null) {
						containmentList.add(schema);
					}
				}
				else {
					containmentList.add(schema);
					if (schema instanceof ICatalogObject) {
						((ICatalogObject) schema).refresh();
					}
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
	 * Removes the specified schema from the model.
	 * 
	 * @param schemas the schemas to be removed from the model.
	 */
	public void clearSchemas(List schemas) {
		schemas.clear();
	}

	/**
	 * Creates a result set to be used by the loading logic. The default version
	 * uses of the JDBC DatabaseMetaData.getSchemas() to create the result set.
	 * This method may be overridden to use a vendor specific query. However,
	 * the default logic requires the columns named by the "COLUMN_*" fields.
	 * Keep this in mind if you plan to reuse the default logic (e.g.
	 * initialize())
	 * 
	 * @return a result containing the information used to initialize Schema
	 *         objects
	 * 
	 * @throws SQLException if an error occurs
	 */
	protected ResultSet createResultSet() throws SQLException {
		try {
			return getCatalogObject().getConnection().getMetaData()
					.getSchemas();
		}
		catch (RuntimeException e) {
			SQLException error = new SQLException(MessageFormat.format(
					Messages.Error_Unsupported_DatabaseMetaData_Method,
					new Object[] { "java.sql.DatabaseMetaData.getSchemas()"})); //$NON-NLS-1$
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
	 * Processes a single row in the result set. By default, this method invokes
	 * createSchema() followed by initialize(), finally returning the newly
	 * created, initialized Schema object.
	 * 
	 * @param rs the result set
	 * @return a new Schema object
	 * @throws SQLException if anything goes wrong
	 */
	protected Schema processRow(ResultSet rs) throws SQLException {
		Schema schema = createSchema();
		initialize(schema, rs);
		return schema;
	}

	/**
	 * Returns true if the meta-data in the result set represents a schema
	 * contained by the catalog being loaded. This is a by product of the JDBC
	 * DatabaseMetaData.getSchemas() call not supporting filtering by catalog.
	 * 
	 * If you override createResultSet() to provide a result set specific to the
	 * catalog being populated, you should override this method as well to
	 * simply return true (just to save some processing).
	 * 
	 * @param rs the result set
	 * @return true if the meta-data represents a schema in the catalog
	 * @throws SQLException if anything goes wrong
	 */
	protected boolean isSchemaInCatalog(ResultSet rs) throws SQLException {
	    boolean caseSensitive = isCaseSensitive();
	    
		if (mSupportedColumns.contains(COLUMN_TABLE_CATALOG)) {
			Catalog catalog = getCatalog();
			String catalogName = rs.getString(COLUMN_TABLE_CATALOG);
			if (catalogName != null) {
			    if (caseSensitive) {
			        return catalog.getName().equalsIgnoreCase (catalogName.toLowerCase ());
			    } else {
			        return catalog.getName().equals(catalogName);
			    }
			}
		}

		// NULL/No catalog found. Some databases only return the schema column.
		return isCurrentCatalog( caseSensitive );
	}
	
	private boolean isCaseSensitive() throws SQLException {
        DatabaseMetaData databaseMetaData = getCatalogObject().getConnection().getMetaData ();
	    return databaseMetaData.supportsMixedCaseIdentifiers () || databaseMetaData.supportsMixedCaseQuotedIdentifiers ();
	}

    /* 
     * Check to see if the current catalog matches this catalog or
     * if the current catalog does not exist and this is the catalog
     * for objects without a catalog.
	 */
    protected boolean isCurrentCatalog() throws SQLException {
        return isCurrentCatalog( null );
    }

	private boolean isCurrentCatalog( Boolean isCaseSensitive ) throws SQLException {
	    if( isCaseSensitive == null )
	        isCaseSensitive = isCaseSensitive();
		
		return (isCaseSensitive && getCatalog().getName().equals(getCatalogObject().getConnection().getCatalog()) 
				|| !isCaseSensitive && getCatalog().getName ().equalsIgnoreCase (getCatalogObject().getConnection().getCatalog()))
				|| (getCatalog().getName().length() == 0 && getCatalogObject()
						.getConnection().getCatalog() == null);
	}

	/**
	 * Returns a new Schema object. By default, this method returns a new
	 * JDBCSchema.
	 * 
	 * @return a new Schema object.
	 */
	protected Schema createSchema() {
		return new JDBCSchema();
	}

	/**
	 * Used to initialize a newly created Schema object. By default, this method
	 * initializes the name of the Schema. This method may be overridden to
	 * initialize any vendor specific properties.
	 * 
	 * @param schema a newly created Schema object
	 * @param rs the result set containing the information
	 * @throws SQLException if anything goes wrong
	 */
	protected void initialize(Schema schema, ResultSet rs) throws SQLException {
		schema.setName(rs.getString(COLUMN_TABLE_SCHEM));
	}

	/**
	 * Utility method.
	 * 
	 * @return returns the catalog object being operated upon as a Catalog (i.e.
	 *         (Catalog) getCatalogObject()).
	 */
	protected Catalog getCatalog() {
		return (Catalog) getCatalogObject();
	}

}
