/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    linsong - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ase.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEFuncBasedIndexMember;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelFactory;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.constraints.IncrementType;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalogUtils {

	private static final String PROCEDURE_CAT = "PROCEDURE_CAT"; //$NON-NLS-1$

	private static final String PROCEDURE_SCHEM = "PROCEDURE_SCHEM"; //$NON-NLS-1$

	private static final String PROCEDURE_NAME = "PROCEDURE_NAME"; //$NON-NLS-1$

	private static final String COLUMN_NAME = "COLUMN_NAME"; //$NON-NLS-1$

	private static final String COLUMN_TYPE = "COLUMN_TYPE"; //$NON-NLS-1$

	private static final String DATA_TYPE = "DATA_TYPE"; // from java.sql.Types //$NON-NLS-1$

	private static final String TYPE_NAME = "TYPE_NAME"; //$NON-NLS-1$

	private static final String PRECISION = "PRECISION"; //$NON-NLS-1$

	private static final String LENGTH = "LENGTH"; //$NON-NLS-1$

	private static final String SCALE = "SCALE"; //$NON-NLS-1$

	private static final String RADIX = "RADIX"; //$NON-NLS-1$

	private static final String NULLABLE = "NULLABLE"; //$NON-NLS-1$

	private static final String REMARKS = "REMARKS"; //$NON-NLS-1$

	protected static final int RESULT_SET_STRING = 1;

	static final int PERMISSION_ATTR_GRANTOR = 1;

	static final int PERMISSION_ATTR_GRANTEE = 2;

	static final int PERMISSION_ATTR_TYPE = 3;

	static final int PERMISSION_ATTR_ACTION = 4;

	static final int PERMISSION_ATTR_OWNER = 5;

	static final int PERMISSION_ATTR_OBJECT = 6;

	static final int PERMISSION_ATTR_OBJTYPE = 7;

	static final int PERMISSION_ATTR_COLUMN = 8;

	static final int PERMISSION_REFERENCE_TYPE = 151;// references

	static final int PERMISSION_SELECT_TYPE = 193;// = select

	static final int PERMISSION_INSERT_TYPE = 195; // = insert

	static final int PERMISSION_DELETE_TYPE = 196; // = delete

	static final int PERMISSION_UPDATE_TYPE = 197;// = update

	static final int PERMISSION_EXECUTE_TYPE = 224;// = execute
    
    static final int GRANT_WITH_GRANT_TYPE = 0;
    
    static final int GRANT_TYPE = 1;
    
    static final int REVOKE_TYPE = 2;
	
	private static final int CONST_UNICHARSIZE = 2; // @@unicharsize hardcoded
													// to constant 2 as per
													// unchar specs

	protected static ArrayList _colMetaData = new ArrayList();

	// ///////////////////////////////////////////////
	// For the given catalog (DB) get all its' supported JDBC types
	public static List getDBDatatypes(String ctg, Connection conn)
			throws SQLException {
		List ret = new ArrayList();
		
		PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(ctg);
            stmt = conn.prepareStatement(ASESQLs.SYSTEM_DEFINED_DATATPYE_QUERY);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                String udtName = rs.getString(1);
                if (udtName != null) {
					ret.add(udtName);
				}
            }
        }
        catch (Exception e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
		
		return ret;
	}

	/**
	 * Return a ResultSet object representing the stored procedures on the
	 * database visible to this connection
	 * 
	 * @return java.sql.ResultSet - the stored procedures
	 */
	public static ResultSet getStoredProcs(String sch_nm, 
			Connection cnxn) throws SQLException {

		PreparedStatement stmt = cnxn
				.prepareStatement(ASESQLs.STORED_PROCEDURE_QUERY);
		stmt.setString(1, sch_nm);
//		stmt.setString(2, proc_nm);

		return stmt.executeQuery();
	}

	protected static int getConfigureOption(String configParameter,
			Connection connection) {
		int result = 0;

		PreparedStatement stmt = null;
		ResultSet rSet = null;
		try {
			String pattern = "'{' call sp_configure ''{0}'' '}'";
			String sql = MessageFormat.format(pattern,
					new String[] { configParameter });
			stmt = connection.prepareStatement(sql);
			boolean isResultSet = stmt.execute();
			int updateCount = -1;
			do {
				if (isResultSet) {
					rSet = stmt.getResultSet();

					while (rSet.next()) {
						try {
							result = rSet.getInt("Run Value");
							break;
						} catch (SQLException e) {
							JDBCASEPlugin.getDefault().log(e);
						}
					}
				}
				isResultSet = stmt.getMoreResults();
				updateCount = stmt.getUpdateCount();
			} while ((isResultSet != false) || (updateCount != -1));
		} catch (Exception e) {
			//JDBCASEPlugin.getDefault().log(e);
		} finally {
			try {
				if (rSet != null)
					rSet.close();

				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				JDBCASEPlugin.getDefault().log(e);
			}

		}

		return result;
	}

	protected static List getPrivileges(AuthorizationIdentifier auth,
			SybaseASECatalog catalog) {
		List result = new ArrayList();
		List authIds = catalog.getAuthorizationIds();
        
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = ((ICatalogObject) auth).getConnection();
		String oldCatalog = null;
		try {
			oldCatalog = conn.getCatalog();
			conn.setCatalog(catalog.getName());
			stmt = conn.prepareStatement(ASESQLs.QUERY_AUTHID_PRIVILEGES);
			stmt.setString(1, auth.getName());
			rs = stmt.executeQuery();
			while (rs.next()) {
				SQLObject permissionObj = null;

				String grantee = rs
						.getString(PERMISSION_ATTR_OBJECT);
				if ((grantee != null)) {
					SybasePrivilege privilege = (SybasePrivilege) SybasesqlmodelFactory.eINSTANCE.createSybasePrivilege();
					String owner = rs.getString(PERMISSION_ATTR_OWNER);
					String columnName = rs.getString(PERMISSION_ATTR_COLUMN);
					boolean isColumn = columnName != null;
					Schema schema = (Schema) ASEUtil.getSQLObject(catalog
							.getSchemas(), owner);
					String objType = rs.getString(PERMISSION_ATTR_OBJTYPE)
							.trim();
					if (objType.equals("U") || objType.equals("S")
							|| objType.equals("V")) {
						permissionObj = ASEUtil.getSQLObject(
								schema.getTables(), grantee);
					} else if (objType.equals("P") || objType.equals("F")) {
						permissionObj = ASEUtil.getSQLObject(schema
								.getRoutines(), grantee);
					} else {
						continue;
					}

					if (isColumn) // here permissionObj must not null now
					{
						EList columns = ((Table) permissionObj).getColumns();
						permissionObj = ASEUtil.getSQLObject(columns,
								columnName);
					}
					privilege.setObject(permissionObj);

					String sAction = null;
					int iAction = rs.getInt(PERMISSION_ATTR_ACTION);
					switch (iAction) {
					case PERMISSION_DELETE_TYPE:
						sAction = ASEUtil.PERMISSION_DELETE_ACTION;
						break;
					case PERMISSION_INSERT_TYPE:
						sAction = ASEUtil.PERMISSION_INSERT_ACTION;
						break;
					case PERMISSION_REFERENCE_TYPE:
						sAction = ASEUtil.PERMISSION_REFERENCE_ACTION;
						break;
					case PERMISSION_SELECT_TYPE:
						sAction = ASEUtil.PERMISSION_SELECT_ACTION;
						break;
					case PERMISSION_UPDATE_TYPE:
						sAction = ASEUtil.PERMISSION_UPDATE_ACTION;
						break;
					case PERMISSION_EXECUTE_TYPE:
					    sAction = ASEUtil.PERMISSION_EXECUTE_ACTION;
					    break;
					default:
                        //TODO support other types of permissions
                        sAction = ASEUtil.PERMISSION_OTHER_ACTION;
						continue;
					}
					privilege.setAction(sAction);
					int iType = rs.getInt(PERMISSION_ATTR_TYPE);
                    switch(iType)
                    {
                        case GRANT_WITH_GRANT_TYPE:
                            privilege.setGrantable(true);
                            break;
                        case REVOKE_TYPE:
                            privilege.setRevoked(true);
                            break;
                    }
                    
					// find grantor
					String grantorName = rs.getString(PERMISSION_ATTR_GRANTOR);
					AuthorizationIdentifier grantor = (AuthorizationIdentifier) ASEUtil
							.getSQLObject(authIds, grantorName);
					privilege.setGrantor(grantor);
					privilege.setGrantee(auth);

					result.add(privilege);
				}
			}
		} catch (SQLException e) {
			JDBCASEPlugin.getDefault().log(e);
		} finally {
			SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
		}

		return result;
	}

	protected static List getPrivileges(SybaseAuthorizedObject object,
	        SybaseASECatalog catalog) {
	    List result = new ArrayList();
	    
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    Connection conn = ((ICatalogObject) catalog).getConnection();
	    String oldCatalog = null;
	    try {
	        oldCatalog = conn.getCatalog();
	        conn.setCatalog(catalog.getName());
	        
	        String ownerName = getSchemaName(object);
	        if(ownerName != null && ownerName.trim().length()>0)
	        {
	        	stmt = conn.prepareStatement(ASESQLs.QUERY_OBJECT_PRIVILEGES_FILTER_SCHEMA);
	        	stmt.setString(1, object.getName());
	        	stmt.setString(2, ownerName);
	        }
	        else
	        {
	        	stmt = conn.prepareStatement(ASESQLs.QUERY_OBJECT_PRIVILEGES);
	        	stmt.setString(1, object.getName());
	        }
	        
	        rs = stmt.executeQuery();
            EList authIds = catalog.getAuthorizationIds();

	        while (rs.next()) {
	            SQLObject permissionObj = object;

                String granteeName = rs
	            .getString(PERMISSION_ATTR_GRANTEE);
	            if ((granteeName != null)) {
	                SybasePrivilege privilege = (SybasePrivilege) SybasesqlmodelFactory.eINSTANCE.createSybasePrivilege();
	                //use the cloned authorization identifier to avoid loading all privileges for it
                    AuthorizationIdentifier grantee = (AuthorizationIdentifier) ASEUtil
                    .getSQLObject(authIds, granteeName);
                    //465458: ignore the grantee if it's an invalid user in this catalog
                    if (grantee == null)
                    {
                    	continue;
                    }
                    privilege.setGrantee(grantee);

                    String columnName = rs.getString(PERMISSION_ATTR_COLUMN);
	                boolean isColumn = columnName != null;
	                if (isColumn) 
	                {
	                    EList columns = ((Table) permissionObj).getColumns();
	                    permissionObj = ASEUtil.getSQLObject(columns,
	                            columnName);
	                    privilege.getActionObjects().add(permissionObj);
	                }
	                //the relationship will be established when these privileges are added into the privilege list
//	                privilege.setObject(permissionObj);
	                
	                String sAction = null;
	                int iAction = rs.getInt(PERMISSION_ATTR_ACTION);
	                switch (iAction) {
	                    case PERMISSION_DELETE_TYPE:
	                        sAction = ASEUtil.PERMISSION_DELETE_ACTION;
	                        break;
	                    case PERMISSION_INSERT_TYPE:
	                        sAction = ASEUtil.PERMISSION_INSERT_ACTION;
	                        break;
	                    case PERMISSION_REFERENCE_TYPE:
	                        sAction = ASEUtil.PERMISSION_REFERENCE_ACTION;
	                        break;
	                    case PERMISSION_SELECT_TYPE:
	                        sAction = ASEUtil.PERMISSION_SELECT_ACTION;
	                        break;
	                    case PERMISSION_UPDATE_TYPE:
	                        sAction = ASEUtil.PERMISSION_UPDATE_ACTION;
	                        break;
	                    case PERMISSION_EXECUTE_TYPE:
	                        sAction = ASEUtil.PERMISSION_EXECUTE_ACTION;
	                        break;
	                    default:
	                        //TODO support other types of permissions
	                        sAction = ASEUtil.PERMISSION_OTHER_ACTION;
	                    continue;
	                }
	                privilege.setAction(sAction);
	                int iType = rs.getInt(PERMISSION_ATTR_TYPE);
	                switch(iType)
	                {
	                    case GRANT_WITH_GRANT_TYPE:
	                        privilege.setGrantable(true);
	                        break;
	                    case REVOKE_TYPE:
	                        privilege.setRevoked(true);
	                        break;
	                }
	                
	                // find grantor
	                String grantorName = rs.getString(PERMISSION_ATTR_GRANTOR);
	                AuthorizationIdentifier authId = (AuthorizationIdentifier) ASEUtil
	                .getSQLObject(authIds, grantorName);
	                privilege.setGrantor(authId);
	                
	                result.add(privilege);
	            }
	        }
	    } catch (SQLException e) {
	        JDBCASEPlugin.getDefault().log(e);
	    } finally {
	        SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
	    }
	    
	    return result;
	}
	
    public static String getSchemaName(EObject obj) {
    	if (obj instanceof Trigger)
    	{
    		return ((Trigger)obj).getSchema().getName();
    	}
        EObject container = ContainmentServiceImpl.INSTANCE.getContainer(obj);
        while (container != null)
        {
            obj = container;
            if (obj instanceof Schema)
            {
                return ((Schema)obj).getName();
            }
            container = ContainmentServiceImpl.INSTANCE.getContainer(obj);
        }
        return null;
    }

	/**
	 * 
	 * @param obj
	 * @param conn
	 * @return
	 * @throws SQLException
	 * @throws SQLException
	 */
	protected static String getCompiledObjectText(SQLObject obj,
			Connection conn, String catalogName) {
		if (conn == null)
			return null;

		StringBuffer strRes = new StringBuffer();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String oldCatalog = null;
		try {
			oldCatalog = conn.getCatalog();
			conn.setCatalog(catalogName);
			String coName = obj.getName();
			int groupNumb = -1;
			if (obj instanceof SybaseASECatalogProcedure) {
				groupNumb = ((SybaseASECatalogProcedure) obj).getGroupNumber();
			}

			String sql = "{ call sp_helptext ? }";
			if (groupNumb >= 1) {
				sql = "{ call sp_helptext ?,? }";
			}
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, getSchemaName(obj) + "." + coName);
			if (groupNumb >= 1) {
				stmt.setInt(2, groupNumb);
			}

			boolean updateCount = stmt.execute();

			while (!updateCount) {
				updateCount = stmt.getMoreResults();
			}
			updateCount = stmt.getMoreResults();
			// Skip all the update counts
			while (!updateCount) {
				updateCount = stmt.getMoreResults();
			}
			rs = stmt.getResultSet();

			while (rs.next()) {
				strRes.append(rs.getString("text"));
			}
		} catch (SQLException e) {
			JDBCASEPlugin.getDefault().log(e);
		} finally {
			SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
		}

		return strRes.toString().trim();
	}

	/**
	 * 
	 * @param typeName
	 * @param dbName
	 * @param conn
	 * @return
	 * @throws SQLException
	 * @author sfyu
	 */
	public static String getUDTOwner(String typeName, String dbName,
			Connection conn) throws SQLException {

		if (typeName != null && !typeName.trim().equals("")) {
			Statement stat = null;
			ResultSet rs = null;
			String ownerName = null;
			try {
				String query = "SELECT U.name owner_name,T.name udt_name, PHY.name udt_sys_name, T.length udt_length,"
						+ "T.prec udt_precision, T.scale udt_scale, T.allownulls udt_allow_nulls, T.ident udt_is_identity,"
						+ "(SELECT DO.name + '.' + object_name(T.tdefault,db_id('"
						+ dbName
						+ "')) "
						+ "FROM "
						+ dbName
						+ ".dbo.sysusers DO "
						+ "WHERE DO.uid=(SELECT uid from "
						+ dbName
						+ ".dbo.sysobjects where id = T.tdefault)) "
						+ "udt_default_name, "
						+ "(SELECT RO.name + '.' + object_name((case when T.domain<>0 then T.domain else T.accessrule end),db_id('pubs2')) "
						+ "FROM "
						+ dbName
						+ ".dbo.sysusers RO "
						+ "WHERE RO.uid = (SELECT uid from dbo.sysobjects where id  = T.domain OR id = T.accessrule)) "
						+ "udt_rule_name "
						+ "FROM  "
						+ dbName
						+ ".dbo.systypes T, "
						+ dbName
						+ ".dbo.sysusers U, "
						+ dbName
						+ ".dbo.systypes PHY "
						+ "WHERE T.uid = U.uid AND PHY.usertype =(SELECT min(usertype) FROM "
						+ dbName
						+ ".dbo.systypes WHERE type = T.type ) "
						+ "AND T.type = PHY.type AND T.usertype >= 100 AND T.name like '"
						+ typeName + "'";
				stat = conn.createStatement();
				rs = stat.executeQuery(query.toString());
				if (rs.next()) {
					ownerName = rs.getString("owner_name");
				}
			} catch (SQLException se) {
				throw se;
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (stat != null) {
					stat.close();
				}
			}
			return ownerName;
		}
		return null;
	}

	// protected static ResultSet getIndexColumn(SybaseASECatalogTable
	// parentTable, int indexId, int keyNumb,
	// Connection conn) throws SQLException
	// {
	// if (conn == null || conn.isClosed())
	// return null;
	//
	// String fullTableName = ASEUtil.getFullQuatifiedName(parentTable);
	//
	// PreparedStatement stmt = conn.prepareStatement(ASESQLs.INDEX_COL_QUERY);
	// stmt.setString(1, fullTableName);
	// stmt.setInt(2, indexId);
	// stmt.setInt(3, keyNumb);
	// stmt.setString(4, fullTableName);
	// stmt.setInt(5, indexId);
	// stmt.setInt(6, keyNumb);
	//
	// return stmt.executeQuery();
	// }

	protected static IndexMember getIndexMember(DatabaseDefinition dbDef,
			SybaseASECatalogIndex index, String colName, String order,
			Connection conn) {
		IndexMember member = null;
		IncrementType incType = IncrementType.get(order);

		boolean isFunctionalIndexCol = false;
		int funcIndexColId = -1;

		DataModelElementFactory factory = dbDef.getDataModelElementFactory();
		String catalogName = index.getTable().getSchema().getCatalog().getName();
		String version = dbDef.getVersion();
//		index.getTable().getSchema().getCatalog().getDatabase().getVersion();
		if (version.compareTo(ASEUtil.VERSION_15) >= 0) {
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String oldCatalog = null;
			try {
				oldCatalog = conn.getCatalog();
				conn.setCatalog(catalogName);
				stmt = conn.prepareStatement(ASESQLs.INDEX_COL_STATUS3_QUERY);
				stmt.setString(1, colName);
				stmt.setString(2, ASEUtil
						.getFullQuatifiedName(index.getTable()));
				rs = stmt.executeQuery();
				while (rs.next()) {
					int status3 = rs.getInt(1);
					if ((status3 & SybaseASECatalogIndex.COL_STATUS3_FUNC_EXPRESSION) > 0) {
						isFunctionalIndexCol = true;
						funcIndexColId = rs.getInt(2);
					}
				}
			} catch (SQLException e) {
				JDBCASEPlugin.getDefault().log(e);
			} finally {
				SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
			}
		}

		if (!isFunctionalIndexCol) {
			member = (IndexMember) factory
					.create(SQLConstraintsPackage.eINSTANCE.getIndexMember());
		} else {
			String funcIndexColExpr = getFunctionedIndexExpression(
					funcIndexColId, catalogName, conn);
			member = (SybaseASEFuncBasedIndexMember) factory
					.create(SybaseasesqlmodelPackage.eINSTANCE
							.getSybaseASEFuncBasedIndexMember());
			((SybaseASEFuncBasedIndexMember) member)
					.setColumnExpression(funcIndexColExpr);
		}
		EList columnList = index.getTable().getColumns();
		for (int i = 0; i < columnList.size(); i++) {
			Column col = (Column) columnList.get(i);
			if (col.getName().equals(colName)) {
				member.setColumn(col);
				break;
			}
		}
		member.setIncrementType(incType);
		return member;
	}

	private static String getFunctionedIndexExpression(int colId, String catalog,
			Connection conn) {
		StringBuffer text = new StringBuffer(256);
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String oldCatalog = null;
		try {
			oldCatalog = conn.getCatalog();
			conn.setCatalog(catalog);
			stmt = conn.prepareStatement(ASESQLs.FUNC_INDEX_COL_QUERY);
			stmt.setInt(1, colId);
			rs = stmt.executeQuery();
			int count = 0;

			while (rs.next()) {
				count++;
				String expression = rs.getString(1);
				if (!expression.equals("")) {
					expression = expression.trim();

					// expression is of the form AS (expression) MATERIALIZED/
					// NON MATERIALIZED
					// remove 'AS' word from expression
					if (count == 1) { // remove the AS keyword only the first
										// time
						expression = expression.substring(
								expression.indexOf("AS") + 2).trim();
					}

					String tmpExpression = expression.toUpperCase();

					// remove the MATERIALZED/ NOT MATERIALIZED words
					// from the expression
					int pos = tmpExpression.lastIndexOf("MATERIALIZED");
					if (pos != -1) {
						expression = expression.substring(0, pos).trim();
						// remove the 'NOT' word from the expression
						pos = tmpExpression.lastIndexOf("NOT");
						if (pos != -1) {
							expression = expression.substring(0, pos).trim();
						}
					}
				}
				text.append(expression);
			}
		} catch (SQLException e) {
			JDBCASEPlugin.getDefault().log(e);
		} finally {
			SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
		}
		return text.toString();
	}

	protected static String getIndexCache(SybaseASECatalogIndex index,
			int indexId, Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String cacheName = null;
		String oldCatalog = null;
		try {
			oldCatalog = conn.getCatalog();
			conn
					.setCatalog(index.getTable().getSchema().getCatalog()
							.getName());
			stmt = conn.prepareStatement(ASESQLs.CACHE_QUERY);
			stmt.setInt(1, indexId);
			stmt.setString(2, ASEUtil.getFullQuatifiedName(index.getTable()));
			rs = stmt.executeQuery();
			while (rs.next()) {
				cacheName = rs.getString(1);
			}
		} catch (SQLException e) {
			JDBCASEPlugin.getDefault().log(e);
		} finally {
			SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
		}

		return cacheName;
	}

	protected static PredefinedDataType getASEPredefinedType(int length,
			int precision, int scale, int iCharSize, String typeName,
			final DatabaseDefinition databaseDefinition) throws SQLException {
		PredefinedDataTypeDefinition typeDefinition = databaseDefinition
				.getPredefinedDataTypeDefinition(typeName);

		PrimitiveType primitiveType = typeDefinition.getPrimitiveType();
		PredefinedDataType type = databaseDefinition
				.getPredefinedDataType(typeDefinition);
		// we should do special process with
		// float/nchar/nvarchar/unichar/univarchar type
		if (primitiveType.getValue() == PrimitiveType.FLOAT) {
			// if it is float type, we set precision as length * 2
			EStructuralFeature feature = type.eClass().getEStructuralFeature(
					"precision"); //$NON-NLS-1$
			type.eSet(feature, new Integer(length * 2));

		} else if (primitiveType.getValue() == PrimitiveType.NATIONAL_CHARACTER
				|| primitiveType.getValue() == PrimitiveType.NATIONAL_CHARACTER_VARYING) {
			iCharSize = (iCharSize == 0) ? 1 : iCharSize;
			EStructuralFeature feature = type.eClass().getEStructuralFeature(
					"length"); //$NON-NLS-1$
			type.eSet(feature, new Integer(length / iCharSize));
		} else if (typeName.equals("unichar") || typeName.equals("univarchar")) {
			EStructuralFeature feature = type.eClass().getEStructuralFeature(
					"length"); //$NON-NLS-1$
			type.eSet(feature, new Integer(length / CONST_UNICHARSIZE));
		} else {
			if (typeDefinition.isLengthSupported()) {
				EStructuralFeature feature = type.eClass()
						.getEStructuralFeature("length"); //$NON-NLS-1$
				type.eSet(feature, new Integer(length));
			} else if (typeDefinition.isPrecisionSupported()) {
				EStructuralFeature feature = type.eClass()
						.getEStructuralFeature("precision"); //$NON-NLS-1$
				type.eSet(feature, new Integer(precision));
			}
		}

		if (typeDefinition.isScaleSupported()) {
			EStructuralFeature feature = type.eClass().getEStructuralFeature(
					"scale"); //$NON-NLS-1$
			type.eSet(feature, new Integer(scale));
		}
		return type;
	}

	static public void cleanupJDBCResouce(ResultSet rs, Statement stmt,
			String oldCatalog, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			JDBCASEPlugin.getDefault().log(e);
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			JDBCASEPlugin.getDefault().log(e);
		}
		try {
			if (oldCatalog != null)
				conn.setCatalog(oldCatalog);
		} catch (SQLException e) {
			JDBCASEPlugin.getDefault().log(e);
		}
	}
	
	//TODO:not take care quoted identifier
	static public String getBoundDefaultValue(String body)
	{
		String result = "";
		Pattern pattern = Pattern
				.compile(
						"\\s*create\\s+default\\s+.*\\s+AS\\s+(.*\\S)\\s*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$
		Matcher matcher = pattern.matcher(body);
		if (matcher.matches()) {
			result = matcher.group(1);
		}
		return result;
	}
    
    static public DataType getSpecifiedUserDefinedDatatype(Catalog catalog, String typeName)
    {
        DataType type = null;
        List schemaList = catalog.getSchemas();
        for(int i = 0; i<schemaList.size(); i++)
        {
            Schema schema = (Schema)schemaList.get(i);
            type = (DataType)ASEUtil.getSQLObject(schema.getUserDefinedTypes(), typeName);
            if(type != null)
                break;
        }
        return type;
    }

}
