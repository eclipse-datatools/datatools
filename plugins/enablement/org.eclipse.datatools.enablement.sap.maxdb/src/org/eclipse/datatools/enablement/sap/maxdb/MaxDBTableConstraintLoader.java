/*******************************************************************************
 * Copyright (c) 2008 SAP AG
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Dimiter Dimitrov; Wolfgang Auer - initial API and implementation
 *******************************************************************************/ 

package org.eclipse.datatools.enablement.sap.maxdb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableConstraintLoader;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.tables.Table;

//this class is a workaround for the DTP bug 193957 and 197806
public class MaxDBTableConstraintLoader extends JDBCTableConstraintLoader{

	public MaxDBTableConstraintLoader() {
		super(null);
	}
	/**
	 * This constructs the loader using no filter.
	 *
	 * @param catalogObject the Table object upon which this loader operates.
	 */
	public MaxDBTableConstraintLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	/**
	 * @param catalogObject the Catalog object upon which this loader operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "constraint" objects being loaded
	 */
	public MaxDBTableConstraintLoader(ICatalogObject catalogObject,
									  IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		assert(catalogObject instanceof Table);
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
	// This method is a workaround for the DTP bug 189079
	// To be removed when "all" MaxDB releases support PK Name
	// http://www.sapdb.org/webpts?wptsdetail=yes&ErrorType=1&ErrorID=1149312
	public PrimaryKey loadPrimaryKey(PrimaryKey existingPK) throws SQLException {
		ResultSet rs = null;
		try {
			Map columns = new TreeMap();
			PrimaryKey pk = null;
			for (rs = createPrimaryKeyResultSet(); rs.next();) {
				if (pk == null) {
					String pkName = rs.getString(COLUMN_PK_NAME);
					if (pkName == null) {
						pkName = rs.getString("TABLE_NAME")+"_PK"; //$NON-NLS-1$  //$NON-NLS-2$
					}
					if (existingPK != null
							&& 
						pkName.equals(existingPK.getName())) 
					{
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
	public void loadUniqueConstraints(PrimaryKey pk, List containmentList,Collection existingUCs) throws SQLException {
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
				if (ucName == null) {
					ucName = rs.getString(COLUMN_PKTABLE_NAME)+"_PK";  //$NON-NLS-1$  //$NON-NLS-2$
				}
				
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

}
