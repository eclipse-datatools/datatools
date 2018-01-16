package org.eclipse.datatools.enablement.sybase.asa.catalog;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SystemDefinedDefaultType;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.Domain;
import org.eclipse.datatools.modelbase.sql.datatypes.NumberDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.NumericalDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpressionDefault;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsFactory;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogUtils {

	  private static final String PROCEDURE_CAT   = "PROCEDURE_CAT"; //$NON-NLS-1$
	  private static final String PROCEDURE_SCHEM = "PROCEDURE_SCHEM"; //$NON-NLS-1$
	  private static final String PROCEDURE_NAME  = "PROCEDURE_NAME"; //$NON-NLS-1$
	  private static final String COLUMN_NAME     = "COLUMN_NAME"; //$NON-NLS-1$
	  private static final String COLUMN_TYPE     = "COLUMN_TYPE"; //$NON-NLS-1$
	  private static final String DATA_TYPE       = "DATA_TYPE"; // from java.sql.Types //$NON-NLS-1$
	  private static final String TYPE_NAME       = "TYPE_NAME"; //$NON-NLS-1$
	  private static final String PRECISION       = "PRECISION"; //$NON-NLS-1$
	  private static final String LENGTH          = "LENGTH"; //$NON-NLS-1$
	  private static final String SCALE           = "SCALE"; //$NON-NLS-1$
	  private static final String RADIX           = "RADIX"; //$NON-NLS-1$
	  private static final String NULLABLE        = "NULLABLE"; //$NON-NLS-1$
	  private static final String REMARKS         = "REMARKS"; //$NON-NLS-1$
	  private static final String[] _colPropNames = {PROCEDURE_CAT,PROCEDURE_SCHEM,PROCEDURE_NAME,COLUMN_NAME,COLUMN_TYPE,DATA_TYPE,TYPE_NAME,PRECISION,LENGTH,SCALE,RADIX,NULLABLE,REMARKS};

	  protected static final int RESULT_SET_STRING = 1;

	  protected static ArrayList         _colMetaData    = new ArrayList();

	  /////////////////////////////////////////////////
	  // For the given catalog (DB) get all its' supported JDBC types
	  public static List getDBDatatypes(String ctg, Connection cnxn)throws SQLException
	  {
	    List ret = null;
	    if( cnxn != null && !cnxn.isClosed() )
	    {
	    	cnxn.setCatalog(ctg);
	      DatabaseMetaData dbmd = cnxn.getMetaData();
	      ret = new ArrayList();
	      ResultSet datatypes = dbmd.getTypeInfo();
	      //int cc = schms.getMetaData().getColumnCount();
	      while( datatypes.next() )
	      {
	        String s = datatypes.getString(1);
	        if( s != null )
	        {
	          ret.add(s);
	        }
	      }
	    }

	    return ret;
	  }

	  /**
		 * Return a ResultSet object representing the stored procedures on the
		 * database visible to this connection
		 * 
		 * @return java.sql.ResultSet - the stored procedures
		 */
	public static ResultSet getStoredProcs(String ctg_nm, String sch_nm,
			String proc_nm, Connection cnxn) throws SQLException {
		if (cnxn == null || cnxn.isClosed())
			return null;

		DatabaseMetaData dbmd = null;
		// String escapeStr = null;
		ResultSet rsProcedures = null;

		// get the database metadata from the connection
		dbmd = cnxn.getMetaData();

		// Get the escape string so we can use wild card expressions
		// escapeStr = dbmd.getSearchStringEscape();
		// _ctlgSep = dbmd.getCatalogSeparator();
		// if( _ctlgSep == null || _ctlgSep.trim().length() == 0 )
		// {
		// _ctlgSep = WSMFConstants_JDBC.WSMF_JDBC_DFLT_CTG_SEPARATOR;
		// SybLogger.warn(WSMFJDBCResourceKey.wsmfJDBCdefaultingCatalogSeparator);
		// }
		// _qtdStringDelim = dbmd.getIdentifierQuoteString();

		rsProcedures = dbmd.getProcedures(ctg_nm, sch_nm, proc_nm);

		return rsProcedures;
	}

	// ////////////////////////////////////////////////////////////////////////////
	public static List addOperationNamesToCatalog(ResultSet storedProcs)
			throws SQLException {
		ArrayList operNames = new ArrayList();
		int added = 0;

		while (storedProcs.next()) {
			// Some stored proc names have a ';n' suffix, where n is the version
			// number.
			String ctg = storedProcs.getString(1);
			String sch = storedProcs.getString(2);
			String sp = storedProcs.getString(3);
			int idx = sp.indexOf(';');
			if (idx > 0) {
				sp = sp.substring(0, idx);
			}
			String s = ""; //$NON-NLS-1$
			if (ctg != null && ctg.length() > 0) {
				s = ctg + "."; //$NON-NLS-1$
			}

			if (sch != null && sch.length() > 0) {
				s += sch + "."; //$NON-NLS-1$
			}

			s += sp;
			operNames.add(s);
			added++;
		}

		return operNames;
	}

	/**
	 * Return a ResultSet object representing the stored procedures on the
	 * database visible to this connection
	 * 
	 * @return java.sql.ResultSet - the stored procedures
	 */
	public static ResultSet getTables(String ctg_nm, String sch_nm,
			String table_nm, Connection cnxn) throws SQLException {
		if (cnxn == null || cnxn.isClosed())
			return null;

		DatabaseMetaData dbmd = null;

		ResultSet rsTables = null;

		// get the database metadata from the connection
		dbmd = cnxn.getMetaData();

		rsTables = dbmd.getTables(ctg_nm, sch_nm, null, null);

		return rsTables;
	}

	/*
	 * ////////////////////////////////////////////////////////////////////////////
	 */
	public static boolean getSPMetaData(Connection cxn, String catalog,
			String schema, String spName) {
		// According to the ResultSet java docs, for portability I need to
		// extract
		// my information from the resutlset only once and from left to right.
		// also,
		// if any of the values are null, they will need to be set to an empty
		// string and according to the javadoc's 1, 2, and 13 may be null. In
		// testing, it
		// seems that oracle is retuning null for 4 as well
		boolean fret = true;
		String tmp = null;
		// HashMap name_map = new HashMap();
		int parm_idx = 0;
		try {
			java.sql.ResultSet sp_cols = cxn.getMetaData().getProcedureColumns(
					catalog, schema, spName, null);
			while (sp_cols.next()) {
				Properties props = new Properties();
				for (int idx = 0; idx < _colPropNames.length; idx++) {
					// this will always return the data as a string. the last
					// parameter is obsolete
					tmp = getResultSetData(sp_cols, idx + 1, RESULT_SET_STRING);
					props.setProperty(_colPropNames[idx], tmp);
				}

				_colMetaData.add(props);
				parm_idx++;
			}
		} catch (SQLException sqlx) {
			String ex_msg = ""; //$NON-NLS-1$

			while (sqlx != null) {
				ex_msg += "\n" + sqlx.getLocalizedMessage(); //$NON-NLS-1$
				sqlx = sqlx.getNextException();
			}
			System.out.println(ex_msg);
			fret = false;
		}
		return fret;
	}

	protected static String getResultSetData(ResultSet rslt_set, int col_index,
			int rslt_set_col_type) throws java.sql.SQLException {
		String ret = ""; //$NON-NLS-1$

		ret = rslt_set.getString(col_index);
		if (ret == null) {
			ret = ""; //$NON-NLS-1$
		}
		return ret;
	}

	public static synchronized String retrieveRoutineObjectCode(
			Connection conn, String schemaName, String routineName) throws SQLException{
		String code = "";
		
		PreparedStatement stmt=null;
		ResultSet rs= null;
	
		long id = 0;
		try {
			String sql = "SELECT P.proc_id FROM SYS.SYSPROCEDURE P JOIN SYS.SYSUSERPERMS U ON U.user_id = P.creator AND U.user_name=? AND P.proc_name=?"; //$NON-NLS-1$
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, schemaName);
			stmt.setString(2, routineName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getLong(1);
			}
		} finally {
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
		try {
			//TODO what about source field? 
			String sql = "select proc_defn from sysprocedure where proc_id=?"; //$NON-NLS-1$
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				code = rs.getString(1);
			}
		} finally {
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
		return code;

	}
	
	public static synchronized String retrieveTriggerObjectCode(
			Connection conn, String tableSchemaName, String tableName, String schemaName, String routineName) throws SQLException {
		String code = "";
		
		PreparedStatement stmt=null;
		ResultSet rs= null;
		
		int trigger_id = 0;
		long table_id = 0;
		try {
            String sql = "SELECT T.trigger_id, T.table_id FROM "
                + "SYS.SYSTRIGGER T JOIN SYS.SYSTABLE A ON T.table_id = A.table_id "
                + "JOIN SYS.SYSUSERPERMS U on A.creator = U.user_id " + "WHERE T.trigger_name = ? AND "
                + "A.table_name = ? AND " + "U.user_name = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, routineName);
            stmt.setString(2, tableName);
            stmt.setString(3, schemaName);

            ResultSet set = stmt.executeQuery();
            if (set.next())
            {
                trigger_id = set.getInt(1);
                table_id = set.getLong(2);
            }
			
		} finally {
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
		try {
			//TODO what about source field? 
			String sql = "SELECT trigger_defn FROM SYS.SYSTRIGGER WHERE trigger_id=? AND table_id=?"; //$NON-NLS-1$
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, trigger_id);
			stmt.setLong(2, table_id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				code = rs.getString(1);
			}
		} finally {
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
		return code;
		
	}

	static public void cleanupJDBCResouce(ResultSet rs, Statement stmt) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
	}

	public static Object findElement(Collection collection, String name){
		Object object = null;
		Iterator it = collection.iterator();
		while(it.hasNext()){
			SQLObject sqlObject = (SQLObject) it.next();
			if (sqlObject.getName().equals(name)){
				object = sqlObject;
				break;
			}
		}
		return object;
	}
	
	public static Object findElement(Collection collection, String name,EClass metaclass){
		Object object = null;
		Iterator it = collection.iterator();
		while(it.hasNext()){
			SQLObject sqlObject = (SQLObject) it.next();
			if (sqlObject.getName().equals(name) && metaclass.isSuperTypeOf(sqlObject.eClass())){
				object = sqlObject;
				break;
			}
		}
		return object;
	}
	
	static public List getSpecifiedColumns(String colListStr, Collection columnList)
	{
		List result = new ArrayList();
		List columnNameList = parseColumnList(colListStr);
        for(int i = 0; i<columnNameList.size(); i++)
        {
        	String colName = (String)columnNameList.get(i);
        	Column column = (Column)SybaseASACatalogUtils.findElement(columnList, colName);
        	if(column != null)
        	{
        		result.add(column);
        	}
        }
        return result;
	}
	
	public static List parseColumnList(String columnListStr)
	{
		List results = new ArrayList();
		
		int index = -1;
		while((index = columnListStr.indexOf(ASASQLs.COLUMN_NAME_DELIMITER)) != -1) 
		{
			String columnName = columnListStr.substring(0, index);
			results.add(columnName);
			columnListStr = columnListStr.substring(index + ASASQLs.COLUMN_NAME_DELIMITER.length());
		}
		
		results.add(columnListStr);
		
		return results;
	} 
	
	static public char getCharValue(String value)
	{
		char c;
        if(value != null && value.length() > 0)
            c = value.charAt(0);
        else
            c = '\0';
        return c;
	}
	
	public static PredefinedDataType getASAPredefinedType(int width,
			int scale, String typeName,
			final DatabaseDefinition databaseDefinition) throws SQLException {
		PredefinedDataTypeDefinition typeDefinition = databaseDefinition
				.getPredefinedDataTypeDefinition(typeName);

		PredefinedDataType type = databaseDefinition
				.getPredefinedDataType(typeDefinition);

        if(typeDefinition == null)
        {
            return type;
        }
		if (typeDefinition.isLengthSupported()) {
			EStructuralFeature feature = type.eClass().getEStructuralFeature(
					"length"); //$NON-NLS-1$
			type.eSet(feature, new Integer(width));
		}
		if (typeDefinition.isScaleSupported()) {
			EStructuralFeature feature = type.eClass().getEStructuralFeature(
					"scale"); //$NON-NLS-1$
			type.eSet(feature, new Integer(scale));
		}
		if (typeDefinition.isPrecisionSupported()) {
			EStructuralFeature feature = type.eClass().getEStructuralFeature(
					"precision"); //$NON-NLS-1$
			type.eSet(feature, new Integer(width));
		}
		return type;
	}
	
	public static boolean isSystemDefault(String defaultValue)
	{
		SystemDefinedDefaultType sddt = SystemDefinedDefaultType.get(defaultValue.toUpperCase()); 
		if(sddt != null)
			return true;
		
		Pattern p = Pattern.compile("global autoincrement\\(\\d+\\)");
		Matcher m = p.matcher(defaultValue);
		return m.matches();
	}
	
	static public Domain getSpecifiedUserDefinedDatatype(Database database, String typeName)
	{
		Domain type = null;
		List schemaList = database.getSchemas();
		for(int i = 0; i<schemaList.size(); i++)
		{
			Schema schema = (Schema)schemaList.get(i);
			type = (Domain)findElement(schema.getUserDefinedTypes(), typeName);
			if(type != null)
				break;
		}
		return type;
	}
	
	static public void parseView (ViewTable view,String viewText){
    	String body = viewText;
    	Pattern pattern = Pattern.compile(".*[\\s]+?AS[\\s]+?([(\\s]*SELECT.*)",Pattern.CASE_INSENSITIVE|Pattern.DOTALL); //$NON-NLS-1$
    	Matcher matcher = pattern.matcher(viewText);
    	if (matcher.matches()) {
    		body = matcher.group(1);
    		pattern = Pattern.compile("(.*)[ \t]+?WITH[ \t]+?.*",Pattern.CASE_INSENSITIVE|Pattern.DOTALL); //$NON-NLS-1$
    		matcher = pattern.matcher(body);
    		if (matcher.matches()) {
    			body = matcher.group(1).trim();
    		}
    	}

    	QueryExpression queryExpression = SQLExpressionsFactory.eINSTANCE.createQueryExpressionDefault();
		((QueryExpressionDefault)queryExpression).setSQL(body);
		view.setQueryExpression(queryExpression);
    }
	
	
	/**
	 * Parse the order
	 * 
	 * @param orderListStr
	 * @return
	 */
	public static  List parseOrderList(String orderListStr)
	{
		List results = new ArrayList();
		
		int index = -1;
		while((index = orderListStr.indexOf(ASASQLs.COLUMN_ORDER_DELIMITER)) != -1) 
		{
			String columnName = orderListStr.substring(0, index);
			results.add(columnName);
			orderListStr = orderListStr.substring(index + ASASQLs.COLUMN_ORDER_DELIMITER.length());
		}
		
		results.add(orderListStr);
		
		return results;
	}
    
    public static PredefinedDataType getPredefinedRepresentation(DataType datatype)
    {
        if (datatype instanceof DistinctUserDefinedType)
        {
            DistinctUserDefinedType udt = (DistinctUserDefinedType) datatype;
            PredefinedDataType pType = udt.getPredefinedRepresentation();
            return pType;
        }
        return null;
    }

    public static boolean isNumericType(DataType datatype)
    {
        if (getPredefinedRepresentation(datatype) != null)
        {
            return isNumericType(getPredefinedRepresentation(datatype));
        }
        if ((datatype instanceof NumericalDataType) || (datatype instanceof NumberDataType))
        {
            return true;
        }
        return false;
    }

    public static boolean isStringType(DataType datatype)
    {
        if (getPredefinedRepresentation(datatype) != null)
        {
            return isStringType(getPredefinedRepresentation(datatype));
        }
        if (datatype instanceof CharacterStringDataType)
        {
            return true;
        }
        return false;
    }

    public static boolean isTimeType(DataType datatype)
    {
        if (getPredefinedRepresentation(datatype) != null)
        {
            return isTimeType(getPredefinedRepresentation(datatype));
        }
        if (datatype instanceof TimeDataType)
        {
            return true;
        }
        return false;
    }

    public static boolean isDateType(DataType datatype)
    {
        if (getPredefinedRepresentation(datatype) != null)
        {
            return isDateType(getPredefinedRepresentation(datatype));
        }
        if (datatype instanceof DateDataType)
        {
            return true;
        }
        return false;
    }

    public static boolean isBinaryType(DataType datatype)
    {
        if (getPredefinedRepresentation(datatype) != null)
        {
            return isBinaryType(getPredefinedRepresentation(datatype));
        }
        if (datatype instanceof BinaryStringDataType)
        {
            return true;
        }
        return false;
    }
}
