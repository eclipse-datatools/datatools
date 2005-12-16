/*******************************************************************************
 * IBM CONFIDENTIAL -- OCO SOURCE MATERIALS
 *
 * COPYRIGHT:
 *     (C) COPYRIGHT IBM CORPORATION 2002
 *
 * The source code for this program is not published or otherwise divested of
 * its trade secrets, irrespective of what has been deposited with the U.S.
 * Copyright Office.
 *
 * Source File Name: %W%
 * Version: %I%, %G%
 *
 * Descriptive Name: Database helper
 *
 * Function:  Provides a set of utility methods for dealing with
 *            Database objects and associated objects such as
 *            Table and Column.
 *
 * Change Activity:
 *
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.query.helper;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.helper.SchemaHelper;
import org.eclipse.datatools.modelbase.sql.tables.Table;

/**
 * This class provides a set of utility methods for dealing with RDBDatabase
 * objects and associated objects such as RDBTable and RDBColumn.
 */
public class DatabaseHelper {

  private static final String myPackageName = "com.ibm.db2.tools.sqlassist2.sqlmodel.helpers";
  private static final String myClassName = "DatabaseHelper";

  
  
  /** DO NOT USE ANYMORE IN CONNECTION WITH com.ibm.db.parsers.sql.query PARSER!
   * Resolves the table references in the From clause of the given query so that
   * each has the correct RDBTable attached to it.
   * 
   * @param aSQLStmt
   *            a SQL statement which's table refernces are to be resolved
   * @param aDatabase
   *            a Database to use to get RDBTables
   * @param aDefaultSchemaName
   *            the name of the Schema the tables will be looked up for,
   *            if the tables are not already qualified 
   * 
   * @deprecated table reference resolving is now done by post parse processer,
   * use {@link com.ibm.db.parsers.sql.query.postparse.TableReferenceResolver}
   * with {@link com.ibm.db.parsers.sql.query.SQLQueryParserManager}
   */
  public static void resolveTableReferenceRDBTables(QueryStatement aSQLStmt,
                                                    Database aDatabase,
                                                    String aDefaultSchemaName)
  {
      List tableRefList = StatementHelper.getTablesForStatement(aSQLStmt);
      resolveTableReferenceRDBTables(tableRefList, aDatabase, aDefaultSchemaName);
  }

  /** DO NOT USE ANYMORE IN CONNECTION WITH com.ibm.db.parsers.sql.query PARSER!
   * Resolves the table references in the From clause of the given query so that
   * each has the correct RDBTable attached to it.
   * 
   * @param aTableRefList
   *            a list of table references to be resolved
   * @param aDatabase
   *            an Database to use to get RDBTables
   * @param aDefaultSchemaName
   *            the name of the Schema the tables will be looked up for, if the tables 
   *            are not already qualified 
   * 
   * @deprecated table reference resolving is now done by post parse processer,
   * use {@link com.ibm.db.parsers.sql.query.postparse.TableReferenceResolver}
   * with {@link com.ibm.db.parsers.sql.query.SQLQueryParserManager}
   */
  public static void resolveTableReferenceRDBTables(List aTableRefList,
                                                    Database aDatabase,
                                                    String aDefaultSchemaName )
  {

      TableReference tblRef;
      TableInDatabase table = null;

      for (Iterator tableIt = aTableRefList.iterator(); tableIt.hasNext();)
      {
          tblRef = (TableReference) tableIt.next();

          if (tblRef instanceof TableInDatabase)
          {
              table = (TableInDatabase) tblRef;
              resolveTableReferenceRDBTable(table, aDatabase, aDefaultSchemaName);
          }
      }
  }

  /**
   * Resolves the table references in the From clause of the given query so that
   * each has the correct RDBTable attached to it.
   * 
   * @param aTableInDB
   *            a table references to be resolved
   * @param aDatabase
   *            an Database to use to get RDBTables
   * @param aDefaultSchemaName
   *            the name of the Schema the tables will be looked up for,
   *             if the tables are not already qualified 
   */
  public static void resolveTableReferenceRDBTable( TableInDatabase aTableInDB,
                                                    Database aDatabase,
                                                    String aDefaultSchemaName )
  {

      Table rdbTable = null;
      Schema rdbSchema = null;
      String rdbSchemaName = null;
      String searchTableName = null;
      
      searchTableName = aTableInDB.getName();
      Table tempRdbTbl = aTableInDB.getDatabaseTable();
      Schema tempRdbSch = null;

      // If the table doesn't have a schema associated with it,
      // use the current user id of the connection. ..defaultSchema to
      // pass in
      if (tempRdbTbl != null)
      {
          tempRdbSch = tempRdbTbl.getSchema();
          if (tempRdbSch != null && tempRdbSch.getName() != null && tempRdbSch.getName().length() > 0)
          {
              rdbSchemaName = tempRdbSch.getName();
          }
          else 
          {
              rdbSchemaName = aDefaultSchemaName;
          }
      }

      // Get the RDBTable object associated with the RDBDatabase that
      // has the table name we're looking for.
      //
      // * * * * * TODO: QMP * * * * *?
      // ____________do we?______________
      // Note: we've set up the RDBDatabase so that tables
      // and views
      //       are both treated as tables. If this changes, we will
      //       need to call "findAbstractTable" here instead of
      //       "findTable".
      rdbSchema = org.eclipse.datatools.modelbase.sql.schema.helper.DatabaseHelper.findSchema(aDatabase, rdbSchemaName);
      
      if (rdbSchema != null) 
      {
          rdbTable = SchemaHelper.findTable(rdbSchema, searchTableName); 
      }
                                                                       // hemant

      // Associate the RDBTable we found with our query table.
      if (rdbTable != null)
      {
          aTableInDB.setDatabaseTable(rdbTable);
          // Populate the column list ttached to the TableExpression object in the query model
          TableHelper.populateTableExpressionColumns(aTableInDB,rdbTable);
      }
      
  }
  
  


  
/**
 * @param decimal
 * @param string
 * @param string2
 * @param string3
 * @param adb
 * @return
 * @deprecated not even implemented! use {@link ValueExpressionHelper#getPredefinedDataTypeForSimpleValue(String)}
 */
public static DataType getDataType(int decimal, String string, String string2, String string3, Database adb)
{
    throw new UnsupportedOperationException(DatabaseHelper.class.getName()+"#getDataType() not implemented!");
}


/*
  *//**
   * Gets a RDBMemberType object equivalent to the given SQL datatype
   * information.
   * @param aType a JDBC datatype
   * @param aTypeName the printable name of the type
   * @param aLength the length of the type (e.g. string length or numeric length)
   * @param aScale The scale of a numeric type
   * @param aDB an RDBDatabase containing datatype information
   * @param aDBVersion an object with database version information
   * @return The member type
   *//*
  public static DataType getRDBMemberType(
       int aType,
       String aTypeName,
       String aLength,
       String aScale,
       Database aDB,
       DBVersion aDBVersion
    ) {

    // This code was adapted from DC's ModelUtil class.
    // We only deal with predefined types at this time, so that's what
    // we'll create.
    RDBPredefinedType newPDType = null;
    int type = aType;
    String typeName = aTypeName;

//    System.out.println("type before:     *" +type + "*");
//    System.out.println("typeName before: *" +typeName + "*");

    // Handle some special cases where the DB2 UDB v8.1 JDBC type and typename do not
    // match the RDBSchema SQL Primitives.
    if (typeName.equals("CHAR FOR BIT DATA")) {
        typeName = "CHARACTER () FOR BIT DATA";
    }
    else if (typeName.equals("VARCHAR FOR BIT DATA")) {
        typeName = "VARCHAR () FOR BIT DATA";
    }
    else if ( (typeName.endsWith("() FOR SBCS DATA"))
       || (typeName.endsWith("() FOR DBCS DATA")) ) {
        int indexOfParens = typeName.lastIndexOf("()");
        typeName = typeName.substring(0,indexOfParens-1);
    }
    else if (  (typeName.endsWith("FOR SBCS DATA"))
       || (typeName.endsWith("FOR DBCS DATA")) ) {
        int indexOfFOR = typeName.lastIndexOf("FOR");
        typeName = typeName.substring(0,indexOfFOR-1);
    }
    //Remove "() FOR BIT DATA" at end of typeName
    //except in v8.1.  In v8.1, only change CHAR to CHARACTER.
    else if (typeName.endsWith("() FOR BIT DATA")) {
        if (aDBVersion.isDB2_LUWO()) {
          if (typeName.equals("CHAR () FOR BIT DATA")) {
            typeName = "CHARACTER () FOR BIT DATA";
          }
        }
        else {
          int indexOfParens = typeName.lastIndexOf("()");
          typeName = typeName.substring(0,indexOfParens-1);
        }
    }
    //Remove "FOR BIT DATA" at the end of typeName
    //unless it's v8.1 which means typeName "LONG VARCHAR FOR BIT DATA".
    else if ( (typeName.endsWith("FOR BIT DATA")) && (!aDBVersion.isDB2_LUWO()) ) {
        int indexOfFOR = typeName.lastIndexOf("FOR");
        typeName = typeName.substring(0,indexOfFOR-1);
    }
    if (typeName.equals("LONGVARCHAR")) {
        typeName = "LONG VARCHAR";
    }
    else if (typeName.equals("LONGVARGRAPHIC")) {
        typeName = "LONG VARGRAPHIC";
    }
    else if ( (typeName.equals("ROWID")) && (type == 1111) ) {
      type = -2;
    }
    typeName = typeName.trim();

//    System.out.println("type after:     *" +type + "*");
//    System.out.println("typeName after: *" +typeName + "*");

    // Get a list of datatype objects that correspond to the JDBC information
    // that we have.
    // List datatypeList = aDB.getDataTypeSet().findByJDBCTypeAndRenderedString( aType, aTypeName );
    // Note: the "rendered string" attribute on the SQLPrimitives doesn't
    //       correspond directly to the JDBC type name.  There should be a
    //       "findByJDBCTypeAndName" method on SQLPrimitives, but there isn't
    //       one, so I've put the equivalent code here.
    if (CommonTrace.isTrace()) {
      CommonTrace.write( trace, "searching for type with JDBC type: " + type + "  and JDBC type name: " + typeName );
    }

//    System.out.println("searching for type with JDBC type: " + type + "  and JDBC type name: " + typeName);

    SQLPrimitives datatypeSet = aDB.getDataTypeSet();
    EList datatypeList = new BasicEList();
    EList allTypesList = datatypeSet.getTypes();
    Iterator allTypesListIter = allTypesList.iterator();
    List lJdbc = datatypeSet.findByJDBCType( type );
    List lTypeName = datatypeSet.findByName( typeName );
    if (lTypeName != null && lTypeName.size() == 0) {
      if (CommonTrace.isTrace()) {
        CommonTrace.write( trace, "findByName returned nothing, trying findByRenderedString" );
//        System.out.println("couldn't find by name...trying by rendered string...");
      }
      lTypeName = datatypeSet.findByRenderedString(typeName);
    }

    if( lJdbc != null ) {
      if (CommonTrace.isTrace()) {
        for (Iterator lJdbcIter = lJdbc.iterator(); lJdbcIter.hasNext(); ) {
          Object obj = lJdbcIter.next();
          CommonTrace.write( trace, "item from findByJDBCType list: " + obj );
        }
        if (lTypeName != null) {
          for (Iterator lTypeNameIter = lTypeName.iterator(); lTypeNameIter.hasNext(); ) {
            Object obj = lTypeNameIter.next();
            CommonTrace.write( trace, "item from findByName/RenderedString list: " + obj );
//            System.out.println("RDBPredefinedType - Rendered String    = *" +((RDBPredefinedType)obj).getRenderedString());
//            System.out.println("RDBPredefinedType - Type Enum          = *" +((RDBPredefinedType)obj).getJdbcEnumType().getValue());
          }
        }
      }

      for( Iterator iterJdbc = lJdbc.iterator(); iterJdbc.hasNext(); ) {
        Object obj = iterJdbc.next();

        if( lTypeName.contains( obj ) ) {
          if (CommonTrace.isTrace()) {
            CommonTrace.write( trace, "found match in lists: " + obj );
          }
//          System.out.println("found a match =)");
          datatypeList.add( obj );
        }
//        System.out.println("~~~~~~~~~~~");
      }
    }

    if( datatypeList.size() == 0 ) {
      // Special case for when the jdbc type code is Types.DOUBLE (8),
      // and the db2 name is FLOAT.
      //
      // For FLOAT, the values 1 through 24 indicate single precision
      // and the values 25 through 53 indicate double precision.  The
      // default is double precision (DOUBLE).  Single precision can
      // also be represented by REAL.
      if( type == java.sql.Types.DOUBLE && typeName.equals("FLOAT") ) {
        // only one FLOAT in the list.
        datatypeList = datatypeSet.findByRenderedString(typeName);
      }
    }

    // Get the first list item.
    for (Iterator iter = datatypeList.iterator(); iter.hasNext(); ) {
      RDBPredefinedType pdType = (RDBPredefinedType)iter.next();
      newPDType = (RDBPredefinedType) pdType.getCopy();
      break;
    }

    // Some datatypes have some additional attributes, such as length,
    // so set them now.
    if (newPDType != null) {
      if (CommonTrace.isTrace()) {
        CommonTrace.write( trace, "found type: " + newPDType );
      }

      int datatype = newPDType.getTypeEnum().getValue();
      switch( datatype) {
        case SQLDefinedType.CHARACTER:
        case SQLDefinedType.CHARACTERVARYING:
          ((SQLCharacterStringType) newPDType).setLength( aLength );
          break;

        case SQLDefinedType.CHARACTERLARGEOBJECT:
          ((SQLCharacterLargeObject) newPDType).setLength( aLength );
          ((SQLCharacterLargeObject) newPDType).setMultiplier( "Bytes" );
          break;

        case SQLDefinedType.NATIONALCHARACTER:
        case SQLDefinedType.NATIONALCHARACTERVARYING:
          ((SQLNationalCharacterStringType) newPDType).setLength( aLength );
          break;

        case SQLDefinedType.NATIONALCHARACTERLARGEOBJECT:
          ((SQLNationalCharacterLargeObject) newPDType).setLength( aLength );
          break;

        case SQLDefinedType.NUMERIC:
        case SQLDefinedType.DECIMAL:
          ((SQLNumeric) newPDType).setPrecision( aLength );
          ((SQLNumeric) newPDType).setScale( aScale );
          break;

        case SQLDefinedType.FLOAT:
          ((SQLFloat) newPDType).setPrecision( aLength );
          break;

        case SQLDefinedType.TIME:
          ((SQLTime) newPDType).setPrecision( aLength );
          break;

        case SQLDefinedType.TIMESTAMP:
          ((SQLTimestamp) newPDType).setPrecision( aLength );
          break;

        case SQLDefinedType.BINARYLARGEOBJECT:
          ((SQLBinaryLargeObject) newPDType).setLength( aLength );
          break;

        case SQLDefinedType.BIT:
        case SQLDefinedType.BITVARYING:
          ((SQLBitString) newPDType).setLength( aLength );
          break;
      }
    }
    // If we didn't find the correct datatype, we'll punt and use a default datatype
    // of CHARACTER(10).  It's a kludge, but it avoids exceptions further down the line.
    else {
      if (CommonTrace.isTrace()) {
        CommonTrace.write( trace, "Couldn't resolve type, using default instead" );
      }

      type = java.sql.Types.CHAR;
      typeName = "CHARACTER";
      allTypesListIter = allTypesList.iterator();
      while (allTypesListIter.hasNext()) {
        Object o = allTypesListIter.next();
        if (o instanceof RDBPredefinedType) {
          RDBPredefinedType t = (RDBPredefinedType) o;
          Integer jdbcEnumType = t.getJdbcEnumType();
          if (jdbcEnumType.intValue() == type && t.getName().equalsIgnoreCase( typeName )) datatypeList.add( o );
        }
      }

      // Get the first list item.
      for (Iterator iter = datatypeList.iterator(); iter.hasNext(); ) {
        RDBPredefinedType pdType = (RDBPredefinedType)iter.next();
        newPDType = (RDBPredefinedType) pdType.getCopy();
        break;
      }

      // Set the length.
      if (newPDType != null) {
        ((SQLCharacterStringType) newPDType).setLength( "10" );
      }
    }

    return (RDBMemberType) CommonTrace.exit( trace, newPDType );
  }

  *//**
   * Gets the string representation of the given datatype in the form
   * it would appear in DDL, such as VARCHAR(10)  or DECIMAL(6,2).
   * @param aDataType to render as a string
   * @return
   *//*
  public static String getRDBMemberTypeAsString( RDBMemberType aDataType ) {
    // @d301485 bgp 09Feb2004 - new method
    String typeStr = "";

    if (aDataType != null) {
      if (aDataType instanceof RDBPredefinedType) {
        // Get the basic datatype name.
        RDBPredefinedType predefinedType = (RDBPredefinedType) aDataType;
        typeStr = predefinedType.getRenderedString();
        // Some types have a length (or precision and optional scale)
        // associated with them.  Get the length (and scale).
        // (Precision is treated as length.)
        String typeLen = null;
        String typeScale = null;
        if (predefinedType instanceof SQLCharacterStringType) {
          typeLen = ((SQLCharacterStringType) predefinedType).getLength();
        }
        else if (predefinedType instanceof SQLNationalCharacterStringType) {
          typeLen = ((SQLNationalCharacterStringType) predefinedType).getLength();
        }
        else if (predefinedType instanceof SQLBinaryLargeObject) {
          typeLen = ((SQLBinaryLargeObject) predefinedType).getLength();
        }
        else if (predefinedType instanceof SQLBitString) {
          typeLen = ((SQLBitString) predefinedType).getLength();
        }
        else if (predefinedType instanceof SQLFloat) {
          typeLen = ((SQLFloat) predefinedType).getPrecision();
        }
        else if (predefinedType instanceof SQLNumeric) {
          typeLen = ((SQLNumeric) predefinedType).getPrecision();
          if (((SQLNumeric) predefinedType).hasScale() == true) {
            typeScale = ((SQLNumeric) predefinedType).getScale();
          }
        }

        // Add the length information to the type string.
        if (typeLen != null) {
          typeStr = typeStr + "(" + typeLen;
          if (typeScale != null) {
            typeStr = typeStr + "," + typeScale;
          }
          typeStr = typeStr + ")";
        }
      }
      // If it's not a predefined type, it is probably a user-defined
      // type.  Try to get it to generate a reasonable representation
      // of itself.
      else {
        if (aDataType.hasExternalName()) {
          typeStr = aDataType.getExternalName();
        }
        else {
          typeStr = aDataType.toString();
        }
      }
    }

    return typeStr;
  }

*/

} // end class DatabaseHelper
