/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ase.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.ase.catalog.SybaseASECatalogTrigger;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUser;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.datatools.sqltools.sql.util.SQLUtil;
import org.eclipse.emf.common.util.EList;

/**
 * @author Hui Cao
 * 
 */
public class ASECatalogUtil {
	
    public static final String LOGSEGMENT = "logsegment";  //$NON-NLS-1$

	public static synchronized String retrieveProceduralObjectCode(
			Connection connection, ProcIdentifier proc) {
		String dbname = proc.getDatabaseName();

		boolean defaultDatabase = (dbname == null || dbname.length() == 0);
		// Database, Table, view, procedural object name and column names can
		// use delimited identifiers, others can't
		if (!defaultDatabase) {
			// TODO: handle quoted identifier correctly
			// dbname = SQLUtil.quoteWhenNecessary(dbname,
			// proc.getDatabaseIdentifier());
		}

		String syscomments = defaultDatabase ? "syscomments" //$NON-NLS-1$
				: (dbname + "..syscomments"); //$NON-NLS-1$
		String sysobjects = defaultDatabase ? "sysobjects" //$NON-NLS-1$
				: (dbname + "..sysobjects"); //$NON-NLS-1$
		String query1 = "select id from " + sysobjects + " where " + sysobjects //$NON-NLS-1$ //$NON-NLS-2$
				+ ".name=? and user_name(uid)=? "; //$NON-NLS-1$
		ResultSet rs1 = null;
		ResultSet rs = null;
		StringBuffer buffer = new StringBuffer();
		try {
			PreparedStatement getSourceStmt1 = null;
			String procName = proc.getProcName();

			// this is a special case, seems ASE can't handle this case well,
			// so we have to use 'like' here.
			// 1> create proc "t " as print 't '
			// 2> go
			// 1> select name+':' from " a b"..sysobjects where " a
			// b"..sysobjects.name='t '
			// 2> go
			// (0 rows affected)
			int id = 0;

			if (procName.charAt(0) == ' '
					|| procName.charAt(procName.length() - 1) == ' ') {
				String procLike = SQLUtil.quote("%" + procName.trim() + "%", //$NON-NLS-1$ //$NON-NLS-2$
						'\'');

				query1 = "select id, name from " + sysobjects + " where " //$NON-NLS-1$ //$NON-NLS-2$
						+ sysobjects + ".name like " + procLike //$NON-NLS-1$
						+ " and user_name(uid)=? "; //$NON-NLS-1$
				getSourceStmt1 = connection.prepareStatement(query1);
				getSourceStmt1.setString(1, procLike);
				getSourceStmt1.setString(1, proc.getOwnerName());
				rs1 = getSourceStmt1.executeQuery();
				String name = ""; //$NON-NLS-1$
				while (rs1.next()) {
					id = rs1.getInt(1);
					name = rs1.getString(2);
					if (name.equals(procName)) {
						break;
					}
				}
			} else {
				getSourceStmt1 = connection.prepareStatement(query1);
				getSourceStmt1.setString(1, procName);
				getSourceStmt1.setString(2, proc.getOwnerName());
				rs1 = getSourceStmt1.executeQuery();
				while (rs1.next()) {
					id = rs1.getInt(1);
				}
			}

			String query = "select text from " + syscomments + " where " //$NON-NLS-1$ //$NON-NLS-2$
					+ syscomments + ".id=? and " + syscomments + ".number=?"; //$NON-NLS-1$ //$NON-NLS-2$
			PreparedStatement getSourceStmt2 = connection
					.prepareStatement(query);
			getSourceStmt2.setInt(1, id);

			int number;
			if (proc.getType() == ProcIdentifier.TYPE_SP) {
				number = proc.getNumber();
				if (number <= 0) {
					number = 1; // for stored procedure, group number must >=1
				}
			} else
				number = 0;

			getSourceStmt2.setInt(2, number);

			rs = getSourceStmt2.executeQuery();
			while (rs.next()) {
				buffer.append(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs1 != null) {
					rs1.close();
				}
			} catch (Exception e) {
			}
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
			}
		}
		return buffer.toString();
	}

	/**
	 * Returns all triggers for the given table, and add them into the list.
	 * 
	 * @param list
	 * @throws SQLException
	 */
	public static synchronized void getAllTriggers(Connection connection,
			Table subjectTable, List list) {
		ResultSet rs = null;
		ResultSet rs1 = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		try {
            String query1 = "select id from sysobjects where name=?"; //$NON-NLS-1$
            stmt1 = connection.prepareStatement(query1);
            stmt1.setString(1, subjectTable.getName());
	        rs1 = stmt1.executeQuery();
	
	        int id =0;
	        if (rs1.next())
	        {
	            id = rs1.getInt(1);
	        }
	        else
	        {
	        	System.out.println(subjectTable.getName() + " does not exist!"); //$NON-NLS-1$
	        	return;
	        }
	        
			String query = "select O.name, user_name(O.uid) from sysobjects O where O.type='TR' and (O.deltrig=? or O.instrig=? or O.updtrig=?)"; //$NON-NLS-1$
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.setInt(2, id);
			stmt.setInt(3, id);
			rs = stmt.executeQuery();

			// XXX: dbname?
			while (rs.next()) {
				String name = rs.getString(1);
				String owner = rs.getString(2);

				if (name != null) {
					name = name.trim();
				} else {
					name = ""; //$NON-NLS-1$
				}
				if (owner != null) {
					owner = owner.trim();
				} else {
					owner = ""; //$NON-NLS-1$
				}

				SybaseASECatalogTrigger trigger = new SybaseASECatalogTrigger();
				trigger.setName(name);
				list.add(trigger);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs1 != null) {
					rs1.close();
				}
			} catch (Exception e) {
			}
			try {
				if (stmt1 != null) {
					stmt1.close();
				}
			} catch (Exception e) {
			}
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
    /**
     * Checks whether the segment is logsement or not
     * 
     * @param segment Segment Model
     * @return if is log segment return true, else false
     */
    public static boolean isLogSegement(SybaseASESegment segment)
    {
        if (segment != null && LOGSEGMENT.equals(segment.getName()))
        {
            return true;
        }
        return false;
    }
    
    public static List getSchemaWithoutRole(SybaseASECatalog catalog)
    {
    	EList authors = catalog.getAuthorizationIds();
        List owners = new ArrayList();
        for(int i=0;i<authors.size();i++)
        {
            if(authors.get(i) instanceof SybaseASEUser)
            {
                owners.add(authors.get(i));
            }
        }
        List schemas = new ArrayList();
        for(int j=0;j<owners.size();j++)
        {
            schemas.addAll(((SybaseASEUser)owners.get(j)).getOwnedSchema());
        }
        return schemas;
    }
    

    /**
     * Find the rule from the database
     * 
     * @param database
     * @param defaultName
     * @param refresh
     * @return
     */
    public static SybaseASERule findRule(Database database, String databaseName, String ownerName, String ruleName,
            boolean refresh, boolean caseSensitive)
    {

        if (database == null || ruleName == null)
        {
            return null;
        }
        Schema schema = ModelUtil.findSchema(database, databaseName, ownerName);
        if (schema instanceof SybaseASESchema)
        {
            if (schema instanceof ICatalogObject && refresh)
            {
                ((ICatalogObject) schema).refresh();
            }
            for (int j = 0; j < ((SybaseASESchema) schema).getRules().size(); j++)
            {
                if (((SybaseASESchema) schema).getRules().get(j) instanceof SybaseASERule
                        && ModelUtil.equals(((SybaseASERule) ((SybaseASESchema) schema).getRules().get(j)).getName(),
                                ruleName, caseSensitive))
                {
                    return (SybaseASERule) ((SybaseASESchema) schema).getRules().get(j);
                }
            }
        }
        return null;
    }

    /**
     * Find the default from the database
     * 
     * @param database
     * @param defaultName
     * @param refresh
     * @return
     */
    public static SybaseASEDefault findDefault(Database database, String databaseName, String ownerName,
            String defaultName, boolean refresh, boolean caseSensitive)
    {

        if (database == null || defaultName == null)
        {
            return null;
        }
        Schema schema = ModelUtil.findSchema(database, databaseName, ownerName);
        if (schema instanceof SybaseASESchema)
        {
            if (schema instanceof ICatalogObject && refresh)
            {
                ((ICatalogObject) schema).refresh();
            }
            for (int j = 0; j < ((SybaseASESchema) schema).getDefaults().size(); j++)
            {
                if (((SybaseASESchema) schema).getDefaults().get(j) instanceof SybaseASEDefault
                        && ModelUtil.equals(((SybaseASEDefault) ((SybaseASESchema) schema).getDefaults().get(j))
                                .getName(), defaultName, caseSensitive))
                {
                    return (SybaseASEDefault) ((SybaseASESchema) schema).getDefaults().get(j);
                }
            }
        }
        return null;
    }


}
