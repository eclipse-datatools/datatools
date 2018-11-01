/*******************************************************************************
 * Copyright (c) 2000, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.osgi.util.NLS;

/**
 * The main methods of this class help handle ordinary and delimited SQL identifiers,
 * and can be understood according to the origin of the identifier
 * and their intended use:
 * <dl>
 * <dt><b>Identifiers from DB2 catalogs:</b>
 * <dd>
 * <ul>
 * <li>{@link #isDBIDOrdinary(String) boolean isDBIDOrdinary(id)}
 * <br>{@link #isDBIDOrdinary(String,int) boolean isDBIDOrdinary(id, platforms)}
 * <br>These methods are used when retrieving an SQL identifier from DB2,
 * where the delimiters have been dropped and lower-case characters folded to upper case.
 * <li>{@link #convertDBID(String,char) String convertDBID(id, delimiter)}
 * <br>{@link #convertDBID(String,char,int) String convertDBID(id, delimiter, platforms)}
 * <br>These methods are used when retrieving an SQL identifier from DB2,
 * where the delimiters have been dropped, so that any needed delimiters
 * are restored.
 * </ul>
 * <dt><b>Identifiers from user input:</b>
 * <dd>
 * <ul>
 * <li>For comparison with DB2 catalog values:
 * <ul>
 * <li>{@link #isUserInputOrdinary(String) boolean isUserInputOrdinary(id)}
 * <br>{@link #isUserInputOrdinary(String,int) boolean isUserInputOrdinary(id, platforms)}
 * <br>{@link #isUserInputOrdinary(String,char,int) boolean isUserInputOrdinary(id, delimiter, platforms)}
 * <br>These methods are used when processing an SQL identifier from user input,
 * where delimiters may or may not be provided.
 * <li>{@link #convertUserInput(String,char) String convertUserInput(id, delimiter)}
 * <br>{@link #convertUserInput(String,char,int) String convertUserInput(id, delimiter, platforms)}
 * <br>These methods are used to prepare an SQL identifier from user input
 * as DB2 would store it.
 * </ul>
 * <li>For composition of SQL statements, when the interface
 * automatically delimits an identifier:
 * <ul>
 * <li>{@link #isUserInputOrdinary(String) boolean isUserInputOrdinary(id)}
 * <br>{@link #isUserInputOrdinary(String,int) boolean isUserInputOrdinary(id, platforms)}
 * <br>{@link #isUserInputOrdinary(String,char,int) boolean isUserInputOrdinary(id, delimiter, platforms)}
 * <br>These methods are used when processing an SQL identifier from user input,
 * where delimiters may or may not be provided.
 * <li>{@link #convertDBID(String,char) String convertDBID(id, delimiter)}
 * <br>{@link #convertDBID(String,char,int) String convertDBID(id, delimiter, platforms)}
 * <br>These methods are used to prepare an SQL identifier from user input
 * for use in an SQL statement.
 * </ul>
 * </ul>
 * </dl>
 * <p>
 * Other methods provide supporting function.
 * <p>
 * The methods implement the following differences between platforms:
 * <ul>
 * <li>Linux, UNIX, Windows, and OS/2 (LUWO) allow characters from extended
 * character sets in ordinary identifiers; OS/390 allows DBCS if they are bracketed
 * with shift-out and shift-in characters. <b>This code currently does
 * not handle shift-out and shift-in characters.</b>
 * <li>OS/400 <i>does not</i> allow internal delimiters;
 * LUWO and OS/390 <i>do</i> allow them.
 * <li>LUWO and OS/400 ignore trailing blanks in an ordinary identifier,
 * whether it is delimited or not. <b>Need to check 390.</b>
 * <li>OS/390 lets users set the delimiter (escape character).
 * <li>There are differences in reserved words.
 * </ul>
 * <p>
 * For definitions of an ordinary SQL identifier see the online documentation:
 * <ul>
 * <li><a href="ftp://ftp.software.ibm.com/ps/products/db2/info/vr6/htm/db2s0/sqlidts.htm#hdrsqlidts">
 * LUWO ordinary SQL identifiers</a>
 * <li><a href="ftp://ftp.software.ibm.com/ps/products/db2/info/vr6/htm/db2s0/ch2char.htm#IDX5919">
 * LUWO letters</a>
 * <li><a href="http://www.unicode.org/charts/">
 * The extended Unicode character sets</a>
 * <li><a href="http://www.s390.ibm.com:80/bookmgr-cgi/bookmgr.cmd/BOOKS/DSNSQ0G2/3%2e3%2e1?SHELF=DSNSH0G3">
 * OS/390 SQL identifiers</a>
 * <li><a href="http://www.s390.ibm.com:80/bookmgr-cgi/bookmgr.cmd/BOOKS/DSNSQ0G2/3%2e1?SHELF=DSNSH0G3">
 * OS/390 letters</a>
 * <li><a href="http://publib.boulder.ibm.com/pubs/html/as400/v4r4/ic2924/info/db2/rbafzmst38.htm#HDRSQLIDTS">
 * OS/400 SQL identifiers</a>
 * <li><a href="ftp://ftp.software.ibm.com/ps/products/db2/info/vr6/htm/db2s0/db2s0476.htm">
 * ISO/ANS SQL92 Reserved Words</a>
 * <li><a href="ftp://ftp.software.ibm.com/ps/products/db2/info/vr6/htm/db2s0/db2s0474.htm">
 * Reserved words for LUWO</a>
 * <li><a href="http://www.s390.ibm.com:80/bookmgr-cgi/bookmgr.cmd/BOOKS/DSNSQ0G2/E%2e0?SHELF=DSNSH0G3#HDRKEYWD">
 * Reserved words for OS/390</a>
 * <li><a href="http://publib.boulder.ibm.com/pubs/html/as400/v4r4/ic2924/info/db2/rbafzmst156.htm#HDRRESWORD">
 * Reserved words for OS/400</a>
 * <li><a href="ftp://ftp.software.ibm.com/ps/products/db2/info/vr6/htm/db2s0/db2s0473.htm">
 * Reserved schemas</a>
 * <li><a href="http://manuals.sybase.com:80/onlinebooks/group-as/asg1200e/aserefmn/@Generic__BookTextView/29609;pt=28169#X">
 * Sybase SQL Identifiers</a>
 * </ul>
 * <p>
 * @author Thomas Sharp, sharpt@us.ibm.com
 */
public class SQLIdentifier
{
// Test main API; deliver only commented out.
//   public static void main(String[] args)
//   {
//      String r = convertAuthID(args[0], DEFAULT_DELIM, PLATFORM_INFORMIX);
//      System.out.println("SQLIdentifier.main authid: " + r);
//   }

   /** Use with NLS.bind to construct qualified SQL identifiers. See genQualifiedName. */
   public static String QUALIFIED_NAME_TEMPLATE = "{0}.{1}"; //$NON-NLS-1$

   /** Any platform. */
   public static final int PLATFORM_ANY = 0x0;
   /** LUWO: Linux, UNIX, Windows, and OS/2. */
   public static final int PLATFORM_LUWO = 0x1;
   /** OS/390, z/OS. */
   public static final int PLATFORM_390 = 0x2;
   /** OS/400, iSeries. */
   public static final int PLATFORM_400 = 0x4;
   /** LUWO, z/OS, and iSeries */
   public static final int PLATFORM_DB2 = PLATFORM_LUWO & PLATFORM_390 & PLATFORM_400;
   /** Cloudscape. */
   public static final int PLATFORM_CLOUDSCAPE = 0x8;
   /** Derby. */
   public static final int PLATFORM_DERBY = 0x8;
   /** LUWO, 390, 400, and Cloudscape */
   public static final int PLATFORM_ALL = 0xF;
   /** Informix. */
   public static final int PLATFORM_INFORMIX = 0x20;
   /** Oracle. */
   public static final int PLATFORM_ORACLE = 0x40;
   /** Sybase. */
   public static final int PLATFORM_SYBASE = 0x80;
   /** SQL Server. */
   public static final int PLATFORM_SQLSERVER = 0x100;
   /** MySQL. */
   public static final int PLATFORM_MYSQL= 0x200;
   /** Any other platform. */
   public static final int PLATFORM_OTHER = 0x10000000;

   /** Hash key for OS/390, z/OS */
   public static final String PLATFORM_390_KEY = "390_PLATFORM"; //$NON-NLS-1$
   /** Hash key for OS/400, iSeries. */
   public static final String PLATFORM_400_KEY = "400_PLATFORM"; //$NON-NLS-1$
   /** Hash key for LUWO: Linux, UNIX, Windows, and OS/2. */
   public static final String PLATFORM_LUWO_KEY = "LUWO_PLATFORM"; //$NON-NLS-1$
   /** Hash key for Cloudscape. */
   public static final String PLATFORM_CLOUDSCAPE_KEY = "CLOUDSCAPE_PLATFORM"; //$NON-NLS-1$
   /** Hash key for any other platform. */
   public static final String PLATFORM_OTHER_KEY = "OTHER_PLATFORM"; //$NON-NLS-1$

   /** For identifying DatabaseDefinitions vendor names for DB2 for zSeries. */
   public static final String DB2_ZSERIES = "zSeries"; //$NON-NLS-1$
   /** For identifying DatabaseDefinitions vendor names for DB2 for iSeries. */
   public static final String DB2_ISERIES = "iSeries"; //$NON-NLS-1$
   /** For identifying DatabaseDefinitions vendor names for DB2 for workstation. */
   public static final String DB2_LUW0 = "DB2 UDB"; // and not zSeries or iSeries;  //$NON-NLS-1$
   /** For identifying DatabaseDefinitions vendor names for IBM Cloudscape. */
   public static final String CLOUDSCAPE = "Cloudscape"; //$NON-NLS-1$
   /** For identifying DatabaseDefinitions vendor names for Derby. */
   public static final String DERBY = "Derby"; //$NON-NLS-1$
   /** For identifying DatabaseDefinitions vendor names for Informix. */
   public static final String INFORMIX = "Informix"; //$NON-NLS-1$
   /** For identifying DatabaseDefinitions vendor names for Oracle. */
   public static final String ORACLE = "Oracle"; //$NON-NLS-1$
   /** For identifying DatabaseDefinitions vendor names for Sybase. */
   public static final String SYBASE = "Sybase"; //$NON-NLS-1$
   /** For identifying DatabaseDefinitions vendor names for SQL Server. */
   public static final String SQLSERVER = "SQL Server"; //$NON-NLS-1$
   /** For identifying DatabaseDefinitions vendor names for MySQL. */
   public static final String MYSQL = "MySql"; //$NON-NLS-1$

   /** String containing the valid ordinary identifier digits. */
   protected static String ordinaryDigits;

   /** Hashtable to remember valid ordinary identifier HashSets by platform name. */
   protected static Hashtable<Object, HashSet<Character>> ordinaryFlagsSet;
   protected static String ordinaryWideDigits;
   /** Temporary list of UNO languages where we have good character sets. */
   protected static ArrayList<String> langsUNO;

   /** Set of reserved words for DB2 UDB for LUWO. */
   protected static List<String> luwoReserved;

   /** Set of reserved words for DB2 UDB for OS/390. */
   protected static List<String> os390Reserved;

   /** Set of reserved words for DB2 UDB for OS/400. */
   protected static List<String> os400Reserved;

   /** Set of reserved words for IBM Cloudscape. */
   protected static List<String> cloudscapeReserved;

   /** Array containing Characters for the valid ordinary identifier characters for other databases. */
   protected static HashSet<Character> otherOrdinaryFlags;

   /** Set of reserved schemas. */
   protected static Set<String> schemaReserved;
   /** All of the above. */
   public static final int TRIM_ALL = 0x7;
   /** Trim from the left and the right.*/
   public static final int TRIM_BOTH = 0x3;
   /** Trim inside the specified delimiter from the right. */
   public static final int TRIM_INSIDE = 0x4;

   /** Trim from the left. */
   public static final int TRIM_LEFT = 0x1;
   /** Trim from the right.*/
   public static final int TRIM_RIGHT = 0x2;

   public final static char DEFAULT_DELIM = '"';
   public final static String DEFAULT_DELIM_STRING = "\""; //$NON-NLS-1$
   public final static char DEFAULT_SQUOTE = '\'';
   public final static String EMPTY_STRING = ""; //$NON-NLS-1$
   
   /**
    * Returns the input authorization ID in SQL format.
    * Folds to upper case if the ID would be upper-cased in catalog format.
    * <p>
    * @param  id        An SQL identifier.
    * @param  conInfo   Describes the database connection.
    * @return The authorization ID converted to a SQL identifier in SQL format
    * @see #convertAuthID(String, char, int)
    */
   public static String convertAuthID(String id, ConnectionInfo conInfo)
   {
      char delimiter = getDelimiter(conInfo);
      int platform = getPlatform(conInfo);
      return convertAuthID(id, delimiter, platform);
   }
   
   /**
    * Returns the input authorization ID in SQL format.
    * Folds to upper case if the ID would be upper-cased in catalog format.
    * <p>
    * @param  id      An SQL identifier.
    * @param  dbDef   Describes the database connection.
    * @return The authorization ID converted to a SQL identifier in SQL format
    * @see #convertAuthID(String, char, int)
    */
   public static String convertAuthID(String id, DatabaseDefinition dbDef)
   {
      char delimiter = getDelimiter(dbDef);
      int platform = getPlatform(dbDef);
      return convertAuthID(id, delimiter, platform);
   }


   /**
    * Returns the input authorization ID in SQL format.
    * Folds to upper case if the ID would be upper-cased in catalog format.
    * <p>
    * @param  id        An SQL identifier.
    * @param delim      The delimiter defined as the escape character
    * on the DB2 platform or platforms.
    * @param  platforms  The sum of platform constants.
    * @return The authorization ID converted to a SQL identifier in SQL format.
    */
   public static String convertAuthID(String id, char delim, int platforms)
   {
      if ((platforms & PLATFORM_INFORMIX) == 0 && isUserInputOrdinary(id, delim, platforms))
      {
         return id.toUpperCase();
      }
      else
      {
         String sqlid = convertDBID(id, delim, platforms, false);
         boolean reserved = isReservedIdentifier(sqlid, delim, platforms);
         if (reserved)
         {
            StringBuffer sb = new StringBuffer();
            sb.insert(0, delim).append(sqlid).append(delim);
            return sb.toString();
         }
         return sqlid;
      }
   }

   /**
    * Returns the input SQL identifier as the database at
    * the given connection would store it in its catalog.
    * <p>Equivalent to convertUserInput(String id, char delimiter, int platforms).
    * <p>
    * @param  id        An SQL identifier.
    * @param  conInfo   Describes the database connection.
    * @return The converted SQL identifier.
    * @see #convertUserInput(String, char, int)
    */
   public static String toCatalogFormat(String id, ConnectionInfo conInfo)
   {
      char delimiter = getDelimiter(conInfo);
      return convertUserInput(id, delimiter, getPlatform(conInfo));
   }

   /**
    * Returns the input SQL identifier as a database for 
    * the given definition would store it in its catalog.
    * <p>Equivalent to convertUserInput(String id, char delimiter, int platforms).
    * <p>
    * @param  id        An SQL identifier.
    * @param  dbDef     Describes the database.
    * @return The converted SQL identifier.
    * @see #convertUserInput(String, char, int)
    */
   public static String toCatalogFormat(String id, DatabaseDefinition dbDef)
   {
      char delimiter = getDelimiter(dbDef);
      return convertUserInput(id, delimiter, getPlatform(dbDef));
   }

   /**
    * Returns the input SQL identifier ready to be used in an SQL statement
    * for the given connection.
    * <p>Equivalent to convertDBID(String id, char delimiter, int platforms).
    * <p>
    * @param  id        An SQL identifier.
    * @param  conInfo   Describes the database connection.
    * @return The converted SQL identifier.
    * @see #convertDBID(String, char, int)
    */
   public static String toSQLFormat(String id, ConnectionInfo conInfo)
   {
      char delimiter = getDelimiter(conInfo);
      int platform = getPlatform(conInfo);
      String sqlid = convertDBID(id, delimiter, platform, false);      
      boolean reserved;
      if (platform > PLATFORM_ALL)
         reserved = isReservedIdentifier(sqlid, conInfo);
      else
         reserved = isReservedIdentifier(sqlid, delimiter, platform);
      if (reserved)
      {
         StringBuffer sb = new StringBuffer();
         sb.insert(0, delimiter).append(sqlid).append(delimiter);
         return sb.toString();
      }
      return sqlid;
   }

   /**
    * Returns the input SQL identifier ready to be used in an SQL statement
    * for the given database definition.
    * <p>Equivalent to convertDBID(String id, char delimiter, int platforms).
    * <p>
    * @param  id        An SQL identifier.
    * @param  dbDef     Describes the database.
    * @return The converted SQL identifier.
    * @see #convertDBID(String, char, int)
    */
   public static String toSQLFormat(String id, DatabaseDefinition dbDef)
   {
      char delimiter = getDelimiter(dbDef);
      int platform = getPlatform(dbDef);
      String sqlid = convertDBID(id, delimiter, platform, false);
      boolean reserved;
      if (platform > PLATFORM_ALL)
         reserved = isReservedIdentifier(sqlid, dbDef);
      else
         reserved = isReservedIdentifier(sqlid, delimiter, platform);
      if (reserved)
      {
         StringBuffer sb = new StringBuffer();
         sb.insert(0, delimiter).append(sqlid).append(delimiter);
         return sb.toString();
      }
      return sqlid;
   }

   /**
    * Returns true if the two SQL identifiers match to the length of prefix.
    * Handles delimiters for both id and prefix.
    * <p>
    * @param  id  An SQL identifier
    * @param  prefix  The beginning of an SQL identifier
    * @param  delimiter  The delimiter defined as the escape character
    * @return  True if the two SQL identifiers match to the length of prefix.
    */
   public static boolean beginsWith(String id, String prefix, char delimiter)
   {
      String nid = trim(id,TRIM_ALL,delimiter), pref = trim(prefix,TRIM_ALL,delimiter);
      if (pref.charAt(0) == delimiter)
      {
         int plen = pref.length();
         // Remove leading delimiter
         if (pref.charAt(plen - 1) == delimiter)
            // Remove trailing delimiter, if any
            pref = pref.substring(1, plen - 2);
         else
            pref = pref.substring(1);
      }
      else
      {
         pref = pref.toUpperCase();
      }
      if (nid.charAt(0) == delimiter)
      {
         if (nid.length() + 1 < pref.length())
            return false;
         nid = nid.substring(1, pref.length() + 1);
      }
      else
      {
         if (nid.length() < pref.length())
            return false;
         nid = (nid.substring(0, pref.length())).toUpperCase();
      }
      if (pref.equals(nid))
         return true;
      else
         return false;
   }

   /**
    * Checks the passed uniqueName is in the passed Enumeration of names and returns
    * true if found, false otherwise.
    *
    * @param names Array of names
    * @param uniqueName The name to be checked.
    * @param  delimiter  The delimiter defined as the escape character
    *
    * @return True if found  false otherwise.
    */
   public static boolean checkName(Enumeration<String> e, String identifier, char delimiter)
   {
      while (e.hasMoreElements())
      {
         if (equals(e.nextElement(), identifier, delimiter))
            return true;
      }
      return false;
   }

   /**
    * Returns a new identifier concatenating the toString of the object
    * (before the closing delimiter if need be).
    * Assumes that the name is well formed, ending with a delimiter if it
    * begins with one.
    * <p>
    * @param  id  The starting identifier
    * @param  add  The object to add
    * @param  delimiter  The delimiter defined as the escape character
    * @return  The resulting identifier
    */
   public static String concatIdentifier(String id, Object add, char delimiter)
   {
      String nid = trim(id,TRIM_ALL,delimiter);
      int epos = nid.length() - 1;
      if (nid.charAt(epos) == delimiter)
      {
			StringBuffer sb = new StringBuffer();
         sb.append(nid.substring(0, epos)).append(add.toString()).append(delimiter);
			return sb.toString();
      }
      else
      {
			StringBuffer sb = new StringBuffer();
         sb.append(nid).append(add.toString());
			return sb.toString();
      }
   }

   /**
    * Returns a new identifier concatenating the number
    * (before the closing delimiter if need be).
    * Assumes that the name is well formed, ending with a delimiter if it
    * begins with one.
    * <p>
    * @param  id  The starting identifier
    * @param  add  The number to add
    * @param  delimiter  The delimiter defined as the escape character
    * @return  The resulting identifier
    */
   public static String concatIdentifier(String id, int add, char delimiter)
   {
      return concatIdentifier(id, new Integer(add), delimiter);
   }

   /** 
    * Return a unique name for adding an attribute by appending a count to the
    * passed string and also checking for the existence of the name in the vector.
    *
    * @param attrNames All attribute names in the type including the
    *        inherited attribute names.
    * @param name Name that we append count to.
    *
    * @return unique name of the attribute to be added.
    */
   public static String getUniqueVarName(Vector<String> varNames, String name) 
   {
      String uniqueName = name;
      int count = 0;
      boolean found = true;
      if (varNames != null && varNames.size() > 0) 
      {
         while (found) 
         {
            uniqueName = concatIdentifier(name, ++count, DEFAULT_DELIM);
            found = !isNameUnique(varNames, uniqueName);
         }
      } else 
      {
         uniqueName = concatIdentifier(name, ++count, DEFAULT_DELIM);
      }
      return uniqueName;
   }

   /** 
    * Checks the passed uniqueName is in the passed array names and returns
    * false if found, true otherwise.
    *
    * @param names Array of names
    * @param uniqueName The name to be checked.
    *
    * @return true if found  false otherwise.
    */
   public static boolean isNameUnique(Vector<String> names, String uniqueName)  
   {
      int nameslength = names.size();
      for (int i = 0; i < nameslength; i++) 
      {
         if (equals(names.get(i), uniqueName, DEFAULT_DELIM))
            return false;
      }
      return true;
   }
  

   /**
    * Returns the input SQL identifier ready to be used in an SQL statement
    * for DB2 UDB for LUWO.
    * If the identifier is not an ordinary identifier, the return is delimited;
    * otherwise, the return is the original identifier.
    * This method is used when retrieving an SQL identifier from DB2,
    * where the delimiters have been dropped, so that any needed delimiters
    * are restored.
    * <p>
    * (Formerly named db2String.)
    * <p>
    * This method does not guarantee that the identifier is valid; it might violate
    * other rules for identifiers, such as exceeding the maximum length.
    * <p>
    * For LUWO, OS/390, or OS/400 or a combination of platforms,
    * see {@link #convertDBID(String,char,int) convertDBID(String, char, int)}.
    * <p>
    * @param  id        An SQL identifier.
    * @param  delimiter  The delimiter defined as the escape character
    * on the DB2 platform or platforms.
    * @return  The converted SQL identifier.
    * @see #convertDBID(String, char, int)
    */
   public static String convertDBID(String id, char delimiter)
   {
      return convertDBID(id, delimiter, PLATFORM_LUWO, true);
   }

   /**
    * Returns the input SQL identifier ready to be used in an SQL statement
    * for LUWO, OS/390, or OS/400 or a combination of platforms.
    * @return convertDBID(String,char,int,true)
    */
   public static String convertDBID(String id, char delimiter, int platforms)
   {
      return convertDBID(id, delimiter, platforms, true);
   }

   /**
    * Returns the input SQL identifier ready to be used in an SQL statement
    * for LUWO, OS/390, or OS/400 or a combination of platforms.
    * If the identifier is not an ordinary identifier, the return is delimited
    * and any internal delimiters are doubled;
    * otherwise, the return is the original identifier.
    * This method is used when retrieving an SQL identifier from DB2,
    * where the delimiters have been dropped, so that any needed delimiters
    * are restored.
    * <p>
    * (Formerly named db2String.)
    * <p>
    * This method does not guarantee that the identifier is valid; it might violate
    * other rules for identifiers, such as exceeding the maximum length.
    * <p>
    * @param  id        An SQL identifier.
    * @param  delimiter  The delimiter defined as the escape character
    * on the DB2 platform or platforms.
    * @param  platforms  The sum of platform constants.
    * @param  checkReserved whether or not identifier should be checked against
    * list of reserved words when considering 'ordinaryness'.  Default true.
    * @return  The converted SQL identifier for the specified platforms.
    */
   public static String convertDBID(String id, char delimiter, int platforms,
            boolean checkReserved) //@d340004txu
   {
      // Note: much of this code is duplicated in method isDBOrdinary,
      // so one of these methods should probably call the other.
      if (id == null || id.length() == 0)
      {
         return id;
      }
      boolean ordinary = true; // Assume the id is ordinary
		StringBuffer sb = new StringBuffer();
      char ch = id.charAt(0);
      if (ch == delimiter)
      {
          sb.append(ch);  // Double it
          ordinary = false;
      }
      else if (!isOrdinaryUpperChar(ch, platforms))
      {
         ordinary = false;
      }
      sb.append(ch);
      int range = id.length();
      for (int c = 1; c < range; c++)
      {
         ch = id.charAt(c);
         if (ch == delimiter)
         {
            sb.append(ch);  // Double it
            ordinary = false;
         }
         else if (!isOrdinaryUpperChar(ch, platforms)
                  && !isOrdinaryDigit(ch)
                  && ch != '_')
         {
            ordinary = false;
         }
         sb.append(ch);
      }
      // @d322643 bgp 04Jun2004 - begin
      // Check if the identifier is the same as a reserved word.
      if (checkReserved && isReservedIdentifier(sb.toString(), delimiter, platforms))
      {   //@d340004txu
         ordinary = false;
      }
      // @d322643 bgp 04Jun2004 - end
      if (!ordinary)
      {
         sb.insert(0, delimiter).append(delimiter);
         return sb.toString();
      }
      return id;
   }

   /**
    * Returns the user-input SQL identifier as DB2 for LUWO would store it.
    * It is either converted to upper case,
    * if it is an ordinary identifier <i>ignoring the case of each letter</i>,
    * or delimited, if it cannot be an ordinary identifier.
    * If the user has begun and ended it with a delimiter,
    * then new delimiters are not added.
    * <p>
    * (Formerly named handleDB2Identifier.)
    * <p>
    * This method does not guarantee that the identifier is valid; it might violate
    * other rules for identifiers, such as exceeding the maximum length,
    * or failing to double internal delimiters.
    * <p>
    * For LUWO, OS/390, or OS/400 or a combination of platforms,
    * see {@link #convertUserInput(String,char,int) convertUserInput(String, char, int)}.
    * <p>
    * @param  id        An SQL identifier.
    * @param  delimiter  The delimiter defined as the escape character
    * on the DB2 platform or platforms.
    * @return  The converted SQL identifier.
    * @see #convertUserInput(String, char, int)
    */
   public static String convertUserInput(String id, char delimiter)
   {
      return convertUserInput(id, delimiter, PLATFORM_LUWO);
   }

   /**
    * Returns the input SQL identifier either converted to upper case,
    * if it is an ordinary identifier <i>ignoring the case of each letter</i>,
    * or delimited, if it cannot be an ordinary identifier,
    * for LUWO, OS/390, or OS/400 or a combination of platforms.
    * <p>
    * (Formerly named handleDB2Identifier.)
    * <p>
    * This method does not guarantee that the identifier is valid; it might violate
    * other rules for identifiers, such as exceeding the maximum length.
    * <p>
    * @param  id        An SQL identifier.
    * @param  conInfo   For the connection information.
    * @return  The converted SQL identifier for the specified platforms in catalog format.
    */
   public static String convertUserInput(String id, ConnectionInfo conInfo)
   {
      char delimiter = getDelimiter(conInfo);
      int platforms = getPlatform(conInfo);
      return convertUserInput(id, delimiter, platforms);
   }

   /**
    * Returns the input SQL identifier either converted to upper case,
    * if it is an ordinary identifier <i>ignoring the case of each letter</i>,
    * or delimited, if it cannot be an ordinary identifier,
    * for LUWO, OS/390, or OS/400 or a combination of platforms.
    * <p>
    * (Formerly named handleDB2Identifier.)
    * <p>
    * This method does not guarantee that the identifier is valid; it might violate
    * other rules for identifiers, such as exceeding the maximum length.
    * <p>
    * @param  id        An SQL identifier.
    * @param  dbdef     For the connection information.
    * @return  The converted SQL identifier for the specified platforms in catalog format.
    */
   public static String convertUserInput(String id, DatabaseDefinition dbDef)
   {
      char delimiter = getDelimiter(dbDef);
      int platforms = getPlatform(dbDef);
      return convertUserInput(id, delimiter, platforms);
   }
   
   /**
    * Returns the input SQL identifier either converted to upper case,
    * if it is an ordinary identifier <i>ignoring the case of each letter</i>,
    * or delimited, if it cannot be an ordinary identifier,
    * for LUWO, OS/390, or OS/400 or a combination of platforms.
    * <p>
    * (Formerly named handleDB2Identifier.)
    * <p>
    * This method does not guarantee that the identifier is valid; it might violate
    * other rules for identifiers, such as exceeding the maximum length.
    * <p>
    * @param  id        An SQL identifier.
    * @param  delimiter  The delimiter defined as the escape character
    * on the DB2 platform or platforms.
    * @param  platforms  The sum of platform constants.
    * @return  The converted SQL identifier for the specified platforms in catalog format.
    */
   public static String convertUserInput(String id, char delimiter, int platforms)
   {
      if (id == null || id.length() == 0)
      {
         return id;
      }
      else if (trim(id,TRIM_LEFT,delimiter).length() == 0)
      {
         return EMPTY_STRING;
      }
      else if (trim(id,TRIM_LEFT,delimiter).charAt(0) == delimiter)
      {
         StringBuffer sb = new StringBuffer();
         char ch;
         id = trim(id,TRIM_ALL,delimiter);
         int range = id.length() - 1;
       
         if (id.charAt(range) == delimiter)
         {
        	 range=range-1;
         }

        boolean precedingDelimiter=false;
        for (int c = 1; c <= range; c++)
        {
            ch = id.charAt(c);
            if (ch == delimiter) {
         	   if(precedingDelimiter) {                      
         		  precedingDelimiter=false;
                }
                else { 
                	precedingDelimiter=true;
         	        sb.append(ch);
                }
            } else {
                precedingDelimiter=false;
                sb.append(ch);
            }
        }

		return sb.toString();
      }
      else
      {
         return (trim(id,TRIM_BOTH,delimiter)).toUpperCase();
      }
   }

   /**
    * Parses an SQL identifier to separate a qualifier.
    * @param   docText  An SQL identifier, optionally qualified
    * @param   conInfo  Describes the database connection.
    * @param qualifierSeparator The character that separates a qualifier.
    * Usually, this is a period, but for the system naming option on
    * iSeries, this is a forward slash (/).
    * @return A String array of 2 elements, where the second element
    * will be the identifier that is qualified by the first element.
    * The first element could be qualified.
    * Any element can be null, but the array will never be null.
    * @see SmartUtil.divideIdentifier(String, char).
    */
   public static String[] getIdentifierParts(String docText, ConnectionInfo conInfo)
   {
      return getIdentifierParts(docText, conInfo, '.');
   }

   /**
    * Parses an SQL identifier to separate a qualifier.
    * @param   docText  An SQL identifier, optionally qualified
    * @param   dbDef    Describes a database.
    * @return A String array of 2 elements, where the second element
    * will be the identifier that is qualified by the first element.
    * The first element could be qualified.
    * Any element can be null, but the array will never be null.
    * @see SmartUtil.divideIdentifier(String, char).
    */
   public static String[] getIdentifierParts(String docText, DatabaseDefinition dbDef)
   {
      return getIdentifierParts(docText, dbDef, '.');
   }

   /**
    * Parses an SQL identifier to separate a qualifier.
    * @param   docText  An SQL identifier, optionally qualified
    * @param   conInfo  Describes the database connection.
    * @param qualifierSeparator The character that separates a qualifier.
    * Usually, this is a period, but for the system naming option on
    * iSeries, this is a forward slash (/).
    * @return A String array of 2 elements, where the second element
    * will be the identifier that is qualified by the first element.
    * The first element could be qualified.
    * Any element can be null, but the array will never be null.
    * @see SmartUtil.divideIdentifier(String, char, char).
    */
   public static String[] getIdentifierParts(String docText, ConnectionInfo conInfo, char qualifierSeparator)
   {
      char delim = getDelimiter(conInfo);
      return getIdentifierParts(docText, delim, qualifierSeparator);
   }

   /**
    * Parses an SQL identifier to separate a qualifier.
    * @param   docText  An SQL identifier, optionally qualified.
    * @param   dbDef    Describes the database.
    * @param qualifierSeparator The character that separates a qualifier.
    * Usually, this is a period, but for the system naming option on
    * iSeries, this is a forward slash (/).
    * @return A String array of 2 elements, where the second element
    * will be the identifier that is qualified by the first element.
    * The first element could be qualified.
    * Any element can be null, but the array will never be null.
    * @see SmartUtil.divideIdentifier(String, char, char).
    */
   public static String[] getIdentifierParts(String docText, DatabaseDefinition dbDef, char qualifierSeparator)
   {
      char delim = getDelimiter(dbDef);
      return getIdentifierParts(docText, delim, qualifierSeparator);
   }

   /**
    * Parses an SQL identifier to separate a qualifier.
    * @param   docText  An SQL identifier, optionally qualified
    * @param   delim    The delimiter for SQL identifiers.
    * @param qualifierSeparator The character that separates a qualifier.
    * Usually, this is a period, but for the system naming option on
    * iSeries, this is a forward slash (/).
    * @return A String array of 2 elements, where the second element
    * will be the identifier that is qualified by the first element.
    * The first element could be qualified.
    * Any element can be null, but the array will never be null.
    * @see SmartUtil.divideIdentifier(String, char, char).
    */
   public static String[] getIdentifierParts(String docText, char delim, char qualifierSeparator)
   {
      String[] parse = new String[2];
      if (docText == null || docText.length() == 0)
         return parse;
      int dot = getSeparator(docText, delim, qualifierSeparator);
      if (dot == -1 || dot == 0) // No separator or at the beginning; all identifier
      {
         parse[1] = docText;
         
      }
      else if (dot == docText.length() - 1) // Separator at the end; all schema
      {
         parse[0] = docText.substring(0, dot);
      }
      else
      {
         parse[0] = docText.substring(0, dot);
         parse[1] = docText.substring(dot + 1);
      }
      return parse;
   }

   /**
    * Parses an SQL identifier to separate a potential qualifier.
    * @param   docText, an SQL identifier, optionally qualified
    * @param   part, one of the following:
    * <dl compact>
    * <dt>0   <dd>Return the schema, if any
    * <dt>1   <dd>Return the unqualified identifier
    * </dl>
    * @param   conInfo   Describes the database connection.
    * @return The identifier part requested
    */
   public static String getIdentifierPart(String docText, int part, ConnectionInfo conInfo)
   {
      return getIdentifierPart(docText, part, conInfo.getDatabaseDefinition());
   }
   /**
    * Parses an SQL identifier to separate a potential qualifier.
    * @param   docText, an SQL identifier, optionally qualified
    * @param   part, one of the following:
    * <dl compact>
    * <dt>0   <dd>Return the schema, if any
    * <dt>1   <dd>Return the unqualified identifier
    * </dl>
    * @param   dbDef   Describes the database connection.
    * @return The identifier part requested
    */
   public static String getIdentifierPart(String docText, int part, DatabaseDefinition dbDef)
   {
      if (docText == null || docText.length() == 0)
      {
         return null;
      }
      int dot = getDot(docText, dbDef);
      if (part == 0)  // Return the schema, if any 
      {
         if (dot <= 0)
         {
            return null;
         }
         else
         {
            return docText.substring(0, dot);
         }
      }
      else  /* Return the identifier */
      {
         if (dot == -1)
         {
            return docText;
            // No dot; assume it's all identifier
         }
         else
         {
            if (dot == docText.length())
            {
               return null;
               // Dot at end; no identifier
            }
            else
            {
               return docText.substring(dot + 1);
            }
         }
      }
   }

   /**
    * Parses an SQL identifier to separate a potential qualifier
    * and returns the name portion only.
    * @param   docText, an SQL identifier, optionally qualified
    * @param   conInfo  Describes the database connection.
    * @return The name part
    */
   public static String getNameOnlyPart(String docText, ConnectionInfo conInfo)
   {
      return getNameOnlyPart(docText, conInfo.getDatabaseDefinition());
   }
   
   /**
    * Parses an SQL identifier to separate a potential qualifier
    * and returns the name portion only.
    * @param   docText, an SQL identifier, optionally qualified
    * @param   conInfo  Describes the database connection.
    * @return The name part
    */
   public static String getNameOnlyPart(String docText, DatabaseDefinition dbDef)
   {
      if (docText == null || docText.length() == 0)
      {
         return null;
      }
      int   dot = getDot(docText, dbDef);
      // No dot , return the whole thing
      if (dot == -1)
      {
         return docText;
      }
      // a dot was found, get the name part only
      else if ((dot > 0) && (dot != docText.length()))
      {
         return docText.substring(dot + 1);
      }
      else     // anything else, something is weird
      {
         return null;
      }
   }

   /**
    * Parses an SQL identifier to find the dot separating a qualifier.
    * Assumes that the qualifier is optional.
    * @param   docText  The text to parse
    * @param   conInfo  Describes the database connection.
    * @return  The position of any dot separating a qualifier
    * from an identifier in the input text.
    * Return -1 if the dot is not found.
    */
   public static int getDot(String docText, ConnectionInfo conInfo)
   {
		return getSeparator(docText, getDelimiter(conInfo), '.');
   }

   /**
    * Parses an SQL identifier to find the dot separating a qualifier.
    * Assumes that the qualifier is optional.
    * @param   docText  The text to parse
    * @param   dbdef    Describes the database connection.
    * @return  The position of any dot separating a qualifier
    * from an identifier in the input text.
    * Return -1 if the dot is not found.
    */
   public static int getDot(String docText, DatabaseDefinition dbdef)
   {
      return getSeparator(docText, getDelimiter(dbdef), '.');
   }
	
   /**
    * Parses an SQL identifier to find the dot separating a qualifier.
    * Assumes that the qualifier is optional.
    * @param   docText  The text to parse
    * @param   conInfo  Describes the database connection.
    * @param qualifierSeparator The character that separates a qualifier.
    * @return  The position of any dot separating a qualifier
    * from an identifier in the input text.
    * Return -1 if the dot is not found.
    */
   public static int getSeparator(String docText, ConnectionInfo conInfo, char qualifierSeparator)
   {
		return getSeparator(docText, getDelimiter(conInfo), qualifierSeparator);
   }

   /**
    * Parses an SQL identifier to separate a potential qualifier.
    * Assumes that the qualifier is optional.
    *
    * @param  docText    The text to parse
    * @param delim The delimiter character for an SQL identifier.
    * @return  The position of any dot separating schema from identifier in the field.
    */
   public static int getDot(String docText, char delim)
   {
      return getSeparator(docText, delim, '.');
   }

   /**
    * Parse an SQL identifier to separate a potential qualifier.
    * @param  docText    The text to parse
    * @param delim The delimiter character for an SQL identifier.
    * @param qualifierSeparator The character that separates a qualifier.
    * @return  The position of any dot separating schema from identifier in the field.
    */
   public static int getSeparator(String docText, char delim, char qualifierSeparator)
   {
      int docLen = docText.length();
      if (docLen == 0)
         return -1;
      // How many qualifierSeparators?
      int dot = -1;
      int dots = 0;
      int pos1 = docText.indexOf(qualifierSeparator, 0);
      // No qualifierSeparator:
      if (pos1 == -1)
      {
         return -1;
      }
      while (pos1 > -1)
      {
         dots++;
         pos1 = docText.indexOf(qualifierSeparator, pos1 + 1);
      }
      // How many delimiters?
      int delims = 0;
      // First, replace doubled delimiters (they don't count):
      ReuseStringBuffer sb = ReuseStringBuffer.getBuffer();
      sb.append(delim).append(delim);
      String ddelim = sb.toString();
      sb.setLength(0);
      sb.append(docText.charAt(0)); // Skip the first char.
      pos1 = 1;
      int pos2 = docText.indexOf(ddelim, 1);
      while (pos2 > -1)
      {
         sb.append(docText.substring(pos1, pos2));
         sb.append("--"); // hold the positions //$NON-NLS-1$
         pos1 = pos2 + 2;
         pos2 = docText.indexOf(ddelim, pos1);
      }
      if (pos1 < docLen) // add the tail
      {
         sb.append(docText.substring(pos1));
      }
      String buffed = ReuseStringBuffer.toString(sb);
      // Then, count the single delimiters:
      pos1 = 0;
      pos2 = buffed.indexOf(delim, pos1);
      while (pos2 > -1 && pos2 < buffed.length())
      {
         delims++;
         pos2 = buffed.indexOf(delim, pos2 + 1);
      }
      // Does it begin or end with a delimiter?
      //boolean beginDelim = (docText.charAt(docText.indexOf(docText.trim().charAt(0))) == DEFAULT_DELIM);
      //boolean endDelim = (docText.trim().charAt(docText.trim().length() - 1) == DEFAULT_DELIM);
      // Odd, or even number of delimiters:
      if ((delims % 2) == 0) // Even
      {
         dot = buffed.lastIndexOf(qualifierSeparator);
         pos1 = buffed.lastIndexOf(delim);
         // Return the last dot after the last delimiter
         if (dot > pos1)
         {
            return dot;
         }
         // Return the last dot after the second delimiter from the end
         pos2 = buffed.lastIndexOf(delim, pos1 - 1);
         while (dot > pos2 && dot > -1)
         {
            dot = buffed.lastIndexOf(qualifierSeparator, dot - 1);
         }
         if (dot > -1)
         {
            return dot;
         }
         return -1; //docText.lastIndexOf(qualifierSeparator);
      }
      else // Odd number of delims, which is an error
      {
         // Return the last dot after the last delimiter
         dot  = docText.lastIndexOf(qualifierSeparator);
         pos1 = docText.lastIndexOf(delim);
         if (dot > pos1)
         {
            return dot;
         }
         // or return the last dot before the first delimiter
         pos1 = docText.indexOf(delim);
         dot = docText.lastIndexOf(qualifierSeparator, pos1);
         if (dot < pos1)
         {
            return dot;
         }
         // Otherwise, return -1
         return -1;
      }
   }

   /**
    * Returns true if two SQL identifiers in SQL format are "equal"
    * for the given connection.
    * <p>
    * @param  id1       An SQL identifier in SQL format.
    * @param  id2       An SQL identifier in SQL format.
    * @param  conInfo   Describes the database connection.
    * @return True if the two SQL identifiers are "equal."
    * @see #equal(String, String, char, int)
    */
   public static boolean equals(String id1, String id2, ConnectionInfo conInfo)
   {
      char delimiter = getDelimiter(conInfo);
      int platform = getPlatform(conInfo);
      return equals(id1, id2, delimiter, platform);
   }

   /**
    * Returns true if two SQL identifiers in SQL format are "equal"
    * for the given connection.
    * <p>
    * @param  id1       An SQL identifier in SQL format.
    * @param  id2       An SQL identifier in SQL format.
    * @param  dbDef   Describes the database connection.
    * @return True if the two SQL identifiers are "equal."
    * @see #equal(String, String, char, int)
    */
   public static boolean equals(String id1, String id2, DatabaseDefinition dbDef)
   {
      char delimiter = getDelimiter(dbDef);
      int platform = getPlatform(dbDef);
      return equals(id1, id2, delimiter, platform);
   }

   /**
    * Returns true if two SQL identifiers are "equal" for DB2 UDB for LUWO.
    * Equality of SQL identifiers depends on whether they are delimited.
    * If they are not delimited, they are automatically folded to upper case by DB2.
    * This method assumes that a delimited identifier is properly formed, that is,
    * that it has a closing delimiter, and that an ordinary identifier is valid,
    * that is, that it doesn't include a delimiter.
    * <p>
    * @param  ident1  The first identifier
    * @param  ident2  The second identifier
    * @param  delimiter  The delimiter defined as the escape character
    * @return  True if the two SQL identifiers are "equal."
    */
   public static boolean equals(String ident1, String ident2, char delimiter)
   {
      return equals(ident1, ident2, delimiter, PLATFORM_LUWO);
   }

   /**
    * Returns true if two SQL identifiers in SQL format are "equal"
    * for LUWO, OS/390, or OS/400 or a combination of platforms.
    * Equality of SQL identifiers depends on whether they are delimited.
    * If they are not delimited, they are automatically folded to upper case by DB2.
    * This method assumes that a delimited identifier is properly formed, that is,
    * that it has a closing delimiter, and that an ordinary identifier is valid,
    * that is, that it doesn't include a delimiter.
    * <p>
    * @param  ident1  The first identifier
    * @param  ident2  The second identifier
    * @param  delimiter  The delimiter defined as the escape character
    * @param  platforms  The sum of platform constants.
    * @return  True if the two SQL identifiers are "equal."
    */
   public static boolean equals(String ident1, String ident2, char delimiter, int platforms)
   {
      return convertDBID(ident1, delimiter, platforms).equals(convertDBID(ident2, delimiter, platforms));
   }

   /**
    * Determines the length in the database catalog of a given Java String.
    * Catalog length depends on whether the database is in UTF-8.
    * This method assumes Unicode catalogs for z/OS V8 and LUWO V9 and later.
    */
   public static long getCatalogLength(String text, ConnectionInfo aConInfo)
   {
      long len = 0;
      int p = getPlatform(aConInfo);
      int[] v = getVersion(aConInfo);
      if (p == PLATFORM_390 && v[0] > 7)
         len = getUTF8Length(text);
      else if (p == PLATFORM_LUWO && v[0] > 8)
         len = getUTF8Length(text);
      else
         len = text.length();
      return len;
   }

   /**
    * Determines the length in the database catalog of a given Java String.
    * Catalog length depends on whether the database is in UTF-8.
    * This method assumes Unicode catalogs for z/OS V8 and LUWO V9 and later.
    */
   public static long getCatalogLength(String text, DatabaseDefinition dbDef)
   {
      long len = 0;
      int p = getPlatform(dbDef);
      int[] v = getVersion(dbDef);
      if (p == PLATFORM_390 && v[0] > 7)
         len = getUTF8Length(text);
      else if (p == PLATFORM_LUWO && v[0] > 8)
         len = getUTF8Length(text);
      else
         len = text.length();
      return len;
   }

   /**
    * Determines the length in UTF-8 of a given Java String.
    * <p>
    * Each character in a Java String is has a numeric value.
    * The following table defines the number of octets needed to
    * represent this value by its numeric range.
    * This table is derived from the description of UTF-8
    * at http://ietf.org/rfc/rfc2279.txt.
    * <dl>
    * <dt><b>UCS-4 range (in hex)</b>  <dd><b>Number of UTF-8 octets</b>
    * <dt>0000 0001-0000 007F <dd>1
    * <dt>0000 0080-0000 07FF <dd>2
    * <dt>0000 0800-0000 FFFF <dd>3
    * <dt>0001 0000-001F FFFF <dd>4
    * <dt>0020 0000-03FF FFFF <dd>5
    * <dt>0400 0000-7FFF FFFF <dd>6
    * </dl>
    * <p>
    * <b>Terminology:</b>
    * <dl>
    * <dt>UCS <dd>Universal Character Set
    * <dt>UTF <dd>UCS Transformation Format, an 8-bit encoding form
    * in which each unicode character is encoded using a
    * variable number of "octets."
    * </dl>
    * @param  text An arbitrary string or an identifier in catalog format.
    * @author Thomas Sharp
    */
   public static long getUTF8Length(String text)
   {
      long len = 0;
      if (text != null)
      {
         long n = text.length();
         char c;
         for (int i = 0; i < n; i++)
         {
            c = text.charAt(i);
            if (c < 128)           // under x0000 0080
               len += 1;
            else if (c < 2048)     // under x0000 0800
               len += 2;
            else if (c < 65536)    // under x0001 0000
               len += 3;
            else if (c < 2097152)  // under x0020 0000
               len += 4;
            else if (c < 67108864) // under x0400 0000
               len += 5;
            else                   // up to x7FFF FFFF
               len += 6;
         }
      }
      return len;
   }

   /**
    * Gets the delimiter (escape character) used to quote SQL identifiers.
    * @param conInfo A ConnectionInfo
    * @return  The delimiter returned by the JDBC implementation,
    * or a double quotation mark if we can't get the delimiter from the ConnectionInfo.
    */
   public static String getDelimiterString(ConnectionInfo conInfo)
   {
      if (conInfo != null)
      {
         String delim = conInfo.getIdentifierQuoteString();
         // indentifierQuoteString can be null in DTP
         if (delim != null) 
         {
            delim = delim.trim();
            if (delim.length() > 0)
               return delim;
         }
      }
      return DEFAULT_DELIM_STRING;
   }

   /**
    * Gets the delimiter (escape character) used to quote SQL identifiers.
    * @param conInfo A ConnectionInfo
    * @return  The delimiter returned by the JDBC implementation,
    * or a double quotation mark if we can't get the delimiter from the ConnectionInfo.
    */
   public static char getDelimiter(ConnectionInfo conInfo)
   {
      if (conInfo != null)
      {
         String delim = conInfo.getIdentifierQuoteString();
         
         // indentifierQuoteString can be null in DTP
         if (delim != null) {
        	 delim = delim.trim();
         
        	 if (delim.length() > 0)
        		 return delim.charAt(0);
         }
      }
      return DEFAULT_DELIM;
   }

   /**
    * @deprecated. Replace this with conInfo.getIdentifierQuoteString()
    * Returns the delimiter (escape character) used to quote SQL identifiers,
    * given a Connection object.
    * <p>
    * @param  connection A live Connection.
    * @return  The delimiter returned by the JDBC implementation,
    * or a double quotation mark if we can't get the delimiter from the connection.
    */
   public static char getDelimiter(Connection connection)
   {
      try
      {
         return connection.getMetaData().getIdentifierQuoteString().charAt(0);
      }
      catch (SQLException e)
      {
         return DEFAULT_DELIM;
      }
   }

   /**
    * Gets the delimiter (escape character) used to quote SQL identifiers as a String.
    * User getDelimiter(ConnectionInfo) or getDelimiter(Connection) if you can.
    * @param dbDef A DatabaseDefinition
    * @return  The delimiter defined in the definition file.
    * or a double quotation mark if we can't get the delimiter from the DatabaseDefinition.
    */
   public static String getDelimiterString(DatabaseDefinition dbDef)
   {
      if (dbDef != null )
      {
         try
         {
            String delim = dbDef.getIdentifierQuoteString();
            if (delim != null)
            {
               delim = delim.trim();
               if (delim.length() > 0 && delim.charAt(0) != '\\') // TODO: Bug in DatabaseDefinition returns backslash
                  return delim;
            }
         }
         catch (NullPointerException npe) {
            // do nothing
         }
      }
      return DEFAULT_DELIM_STRING;
   }

   /**
    * Gets the delimiter (escape character) used to quote SQL identifiers.
    * User getDelimiter(ConnectionInfo) or getDelimiter(Connection) if you can.
    * @param dbDef A DatabaseDefinition
    * @return  The delimiter defined in the definition file.
    * or a double quotation mark if we can't get the delimiter from the DatabaseDefinition.
    */
   public static char getDelimiter(DatabaseDefinition dbDef)
   {
      if (dbDef != null)
      {
         try
         {
            String delim = dbDef.getIdentifierQuoteString();
            if (delim != null)
            {
               delim = delim.trim();
               if (delim.length() > 0 && delim.charAt(0) != '\\') // TODO: Bug in DatabaseDefinition returns backslash
                  return delim.charAt(0);
            }
         }
         catch (NullPointerException npe) {
            // do nothing
         }
      }
      return DEFAULT_DELIM;
   }

   /**
    * Returns the code for the DB2 platform represented by
    * a given Connection object.
    * <p>
    * @param   conInfo The connection information object
    * @return  The platform code.
    */
   public static int getPlatform(ConnectionInfo conInfo)
   {
      if (conInfo == null)
         return PLATFORM_ANY;
      DatabaseDefinition dbDef = conInfo.getDatabaseDefinition();
      return getPlatform(dbDef);
   }

   /**
    * Returns the code for the DB2 platform represented by
    * a given DatabaseDefinition object.
    * <p>
    * @param dbDef A DatabaseDefinition.
    * @return The platform code.
    */
   public static int getPlatform(DatabaseDefinition dbDef)
   {
      if (dbDef != null)
      {
      String product = dbDef.getProduct();
      if (product != null)
      {
         if (product.indexOf(DB2_ZSERIES) > -1)
            return PLATFORM_390;
         else if (product.indexOf(DB2_ISERIES) > -1)
            return PLATFORM_400;
         else if (product.indexOf(DB2_LUW0) > -1)
            return PLATFORM_LUWO;
         else if (product.indexOf(CLOUDSCAPE) > -1)
            return PLATFORM_CLOUDSCAPE;
         else if (product.indexOf(DERBY) > -1)
             return PLATFORM_CLOUDSCAPE;
         else if (product.indexOf(INFORMIX) > -1)
             return PLATFORM_INFORMIX;
         else if (product.indexOf(SYBASE) > -1)
            return PLATFORM_SYBASE;
         else if (product.indexOf(SQLSERVER) > -1)
            return PLATFORM_SQLSERVER;
         else if (product.indexOf(MYSQL) > -1)
            return PLATFORM_MYSQL;
         else if(product.indexOf(ORACLE) > -1)
            return PLATFORM_ORACLE;
      }
      }
      return PLATFORM_OTHER;
   }

   /**
    * Returns the version, release, and modification levels,
    * as specified in the ConnectionInfo or DatabaseDefinition.
    * @param  The connection information object
    * @return
    */
   public static int[] getVersion(ConnectionInfo aConInfo)
   {
      DBVersion v = DBVersion.getSharedInstance(aConInfo);
      return new int[] {v.getVersion(), v.getRelease(), v.getMod()};
   }

   /**
    * Returns the version, release, and modification levels,
    * as specified in the ConnectionInfo or DatabaseDefinition.
    * @param  The connection information object
    * @return
    */
   public static int[] getVersion(DatabaseDefinition dbDef)
   {
      DBVersion v = DBVersion.getSharedInstance(dbDef);
      return new int[] {v.getVersion(), v.getRelease(), v.getMod()};
   }

   /**
    * Returns true if the given character is a DB2 special character.
    * A special character is any of the characters listed below:
    * <dl compact>
    * <dt><code>' '</code>   <dd>An ordinary blank character
    * <dt><code>'-'</code>   <dd>minus sign
    * <dt><code>'"'</code>   <dd>quotation mark or double-quote
    * <dt><code>'.'</code>   <dd>period
    * <dt><code>'%'</code>   <dd>percent
    * <dt><code>'/'</code>   <dd>slash
    * <dt><code>'&'</code>   <dd>ampersand
    * <dt><code>':'</code>   <dd>colon
    * <dt><code>'\''</code>   <dd>apostrophe or single quote
    * <dt><code>';'</code>   <dd>semicolon
    * <dt><code>'('</code>   <dd>left parenthesis
    * <dt><code>'<'</code>   <dd>less than
    * <dt><code>')'</code>   <dd>right parenthesis
    * <dt><code>'='</code>   <dd>equals
    * <dt><code>'*'</code>   <dd>asterisk
    * <dt><code>'>'</code>   <dd>greater than
    * <dt><code>'+'</code>   <dd>plus sign
    * <dt><code>'?'</code>   <dd>question mark
    * <dt><code>','</code>   <dd>comma
    * <dt><code>'_'</code>   <dd>underline or underscore
    * <dt><code>'|'</code>   <dd>vertical bar
    * <dt><code>'^'</code>   <dd>caret
    * <dt><code>'!'</code>   <dd>exclamation mark
    * </dl>
    * <p>
    * @param  c A character.
    * @return True if c is a DB2 special character.
    */
   public static boolean isDB2SpecialChar(char c)
   {
      switch (c)
      {
      case ' ':
      case '-':
      case '"':
      case '.':
      case '%':
      case '/':
      case '&':
      case ':':
      case '\'':
      case ';':
      case '(':
      case '<':
      case ')':
      case '=':
      case '*':
      case '>':
      case '+':
      case '?':
      case ',':
      case '_':
      case '|':
      case '^':
      case '!':
         return true;
      default:
         return false;
      }
   }

   /**
    * Determines whether not not the current language is double-byte
    * (that is, one of Chinese, Japanese, or Korean).
    * @return true when the current language is double-byte, otherwise false
    */
   protected static boolean isDBCSLanguage()
   {
      // @d330618 bgp 20May2004 - new method
      Locale locale = Locale.getDefault(); //AssistManager.getPreferredLanguage();
      String lang = locale.getLanguage();
      if (lang.equals("zh") //$NON-NLS-1$
         || lang.equals("ja") //$NON-NLS-1$
         || lang.equals("ko")) //$NON-NLS-1$
         return true;
      else
         return false;
   }

   /**
    * Returns true if the given string is an ordinary identifier
    * for DB2 UDB for LUWO; otherwise returns false.
    * <p>
    * (Formerly named isOrdinaryIdentifier.)
    * <p>
    * This method is used when retrieving an SQL identifier from DB2,
    * where the delimiters have been dropped and lower-case characters folded to upper case.
    * <p>
    * This method assumes that the delimiter is '"' and the platform is LUWO.
    * To specify the delimiter and the platform,
    * see {@link #isDBIDOrdinary(String,int) isDBIDOrdinary(String, int)}.
    * <p>
    * @param  id  A candidate ordinary identifier.
    * @return  True if the id is an ordinary identifier for LUWO.
    * @see #isDBIDOrdinary(String, int)
    * @see #isDBIDOrdinary(String, int)
    */
   public static boolean isDBIDOrdinary(String id)
   {
      return isDBIDOrdinary(id, DEFAULT_DELIM, PLATFORM_LUWO);
   }

   /**
    * Returns true if the given identifier from DB2 is an ordinary identifier
    * for LUWO, OS/390, or OS/400 or a combination of platforms; otherwise returns false.
    * For LUWO (at least), "IDENT" is ordinary, even though it is delimited.
    * <p>
    * (Formerly named isOrdinaryIdentifier.)
    * <p>
    * This method is used when retrieving an SQL identifier from DB2,
    * where the delimiters have been dropped and lower-case characters folded to upper case.
    * <p>
    * @param  id  A candidate ordinary identifier.
    * @param  platforms  The sum of platform constants.
    * @return  True if the id is an ordinary identifier for the specified platforms.
    */
   public static boolean isDBIDOrdinary(String id, int platforms)
   {
      return isDBIDOrdinary(id, DEFAULT_DELIM, platforms);
   }

   /**
    * Returns true if the given identifier from DB2 is an ordinary identifier
    * for LUWO, OS/390, or OS/400 or a combination of platforms; otherwise returns false.
    * For LUWO (at least), "IDENT" is ordinary, even though it is delimited.
    * <p>
    * (Formerly named isOrdinaryIdentifier.)
    * <p>
    * This method is used when retrieving an SQL identifier from DB2,
    * where the delimiters have been dropped and lower-case characters folded to upper case.
    * <p>
    * @param  id  A candidate ordinary identifier.
    * @param  delimiter  The delimiter defined as the escape character
    * on the DB2 platform or platforms.
    * @param  platforms  The sum of platform constants.
    * @return  True if the id is an ordinary identifier for the specified platforms.
    */
   public static boolean isDBIDOrdinary(String id, char delimiter, int platforms)
   {
      if (id == null || id.length() == 0)
      {
         return true;
      }
      // First char:
      char ch = id.charAt(0);
      if (!isOrdinaryUpperChar(ch, platforms))
      {
         return false;
      }
      // Trailing blanks are ignored in an ordinary identifier:
      String tid = trim(id, TRIM_RIGHT, delimiter);
      // Middle chars:
      int range = tid.length() - 1; // omit the last one
      if (range > -1)
      {
         for (int c = 1; c < range; c++)
         {
            ch = tid.charAt(c);
            if (!isOrdinaryUpperChar(ch, platforms)
            && !isOrdinaryDigit(ch)
            && ch != '_')
            {
               return false;
            }
         }
         // Last char:
         ch = tid.charAt(range);
         if (!isOrdinaryUpperChar(ch, platforms))
         {
            return false;
         }
         // Reserved words:
         if (isReservedIdentifier(tid, delimiter, platforms))
         {
            return false;
         }
      }
      return true;
   }

   /**
    * Determines if the specified character is an ordinary character
    * for OS/390.
    What are the shift-out and shift-in chars translated to in Unicode?
    * <p>
    * @param  ch  A char.
    * @return  True if the given char is upper case for the DB2 for OS/390.
    */
   public static boolean isOrdinary390Char(char ch)
   {
      boolean isOrdinary = false;
      if (!Locale.getDefault().getLanguage().equalsIgnoreCase("en")) //$NON-NLS-1$
      {
         isOrdinary = Character.isUnicodeIdentifierStart( ch );
      }
      else
      {
         HashSet<Character> flags = ordinaryFlagsSet.get(PLATFORM_390_KEY);
         if (flags == null)
         {
            flags = initOrdinaryFlags390();
         }
         isOrdinary = flags.contains(new Character(ch));
         // If the language of the current locale is double-byte (that is,
         // Chinese, Korean, Japanese), then consider double-byte characters
         // to be ordinary characters.
//         if (isOrdinary == false && isDBCSLanguage() == true)
//         {
//            isOrdinary = Character.isUnicodeIdentifierStart( ch );
//         }
      }
      return isOrdinary;
   }

   /**
    * Determines if the specified character is an ordinary character
    * for OS/400.
    * <p>
    * @param  ch  A char.
    * @return  True if the given char is upper case for the DB2 for OS/400.
    */
   public static boolean isOrdinary400Char(char ch)
   {
      boolean isOrdinary = false;
      if (!Locale.getDefault().getLanguage().equalsIgnoreCase("en")) //$NON-NLS-1$
      {
         isOrdinary = Character.isUnicodeIdentifierStart( ch );
      }
      else
      {
         HashSet<Character> flags = ordinaryFlagsSet.get(PLATFORM_400_KEY);
         if (flags == null)
         {
            flags = initOrdinaryFlags400();
         }
         isOrdinary = flags.contains(new Character(ch));
         // Unlike DB2 for 390 and DB2 for LUW, DB2 for 400 does not
         // (according to its SQL Ref.) support double-byte ordinary
         // identifiers.
      }
      return isOrdinary;
   }

   /**
    * Determines if the specified character is an ordinary character
    * for LUWO, OS/390, OS/400, or a combination of platforms,
    * for user input.
    * <p>
    * @param  ch  A char.
    * @param  platforms  The sum of platform constants.
    * The platforms are ignored if you have set the extra characters
    * using setExtraOrdinaryChars(String).
    * @return  True if the given char is an ordinary character
    * for the DB2 platform or platforms.
    */
   public static boolean isOrdinaryChar(char ch, int platforms)
   {
      boolean ok = false;
      if (otherOrdinaryFlags != null)
      {
         ok = isOrdinaryOtherChar(ch);
      }
      else
      {
         if ((platforms & PLATFORM_LUWO) > 0)
            ok = isOrdinaryLUWOChar(ch);
         if (!ok && ((platforms & PLATFORM_390) > 0))
            ok = isOrdinary390Char(ch);
         if (!ok && ((platforms & PLATFORM_400) > 0))
            ok = isOrdinary400Char(ch);
         if (!ok && ((platforms & PLATFORM_CLOUDSCAPE) > 0)) // Same as PLATFORM_DERBY
            ok = isOrdinaryCloudscapeChar(ch);
         // Defaults to LUWO:
         if (!ok && ((platforms & PLATFORM_INFORMIX) > 0))
            ok = isOrdinaryLUWOChar(ch);
         if (!ok && ((platforms & PLATFORM_ORACLE) > 0))
            ok = isOrdinaryLUWOChar(ch);
         if (!ok && ((platforms & PLATFORM_SYBASE) > 0))
            ok = isOrdinaryLUWOChar(ch);
         if (!ok && ((platforms & PLATFORM_SQLSERVER) > 0))
            ok = isOrdinaryLUWOChar(ch);
         if (!ok && ((platforms & PLATFORM_MYSQL) > 0))
            ok = isOrdinaryLUWOChar(ch);
         if (!ok && ((platforms & PLATFORM_OTHER) > 0))
            ok = isOrdinaryLUWOChar(ch);
      }
      return ok;
   }

   /**
    * Returns true if the given char is in the range 0..9.
    * <p>
    * @param  c  The char in question.
    * @return  True if c is in the range 0..9.
    */
   public static boolean isOrdinaryDigit(char c)
   {
      // @d330618 bgp 20May2004 - begin
      boolean isOrdinary = false;
      isOrdinary = (ordinaryDigits.indexOf(c) > -1);
      // If the language of the current locale is double-byte (that is,
      // Chinese, Korean, Japanese), then consider the "wide"
      // (double-byte) numbers to be ordinary digits too.
      if (isOrdinary == false  && isDBCSLanguage() == true)
      {
         isOrdinary = (ordinaryWideDigits.indexOf(c) > -1);
      }
      return isOrdinary;
      // @d330618 bgp 20May2004 - end
   }

   /**
    * Determines if the specified character is an ordinary character
    * for LUWO.
    * <p>
    * @param  ch  A char.
    * @return  True if the given char is upper case for DB2 for LUWO.
    */
   public static boolean isOrdinaryLUWOChar(char ch)
   {
      boolean isOrdinary = false;
      // langsUNO is part of a temporary workaround.
      // This determination should be based on the codeset of the
      // database, which we don't have. Here we assume that it corresponds
      // to the client language, and we have tested, using SQLIdentifierTester,
      // the languages in langsUNO on Windows codesets (only, so far!).
      if (langsUNO == null)
         initOrdinaryFlagsLUWO();
      String lang = Locale.getDefault().getLanguage().toLowerCase();
      if (!langsUNO.contains(lang))
      {
         isOrdinary = Character.isUnicodeIdentifierStart( ch );
      }
      else
      {
         // Instead of Character objects in HashSets (which are initialized
         // in various static init*Flags methods below), it might be more efficient
         // to use some kind of bitmap that could be produced from a codeset.
         HashSet<Character> flags = ordinaryFlagsSet.get(PLATFORM_LUWO_KEY);
         if (flags == null)
         {
            flags = initOrdinaryFlagsLUWO();
         }
         isOrdinary = flags.contains(new Character(ch));
         // If the language of the current locale is double-byte (that is,
         // Chinese, Korean, Japanese), then consider double-byte characters
         // to be ordinary characters.
//         if (!isOrdinary && isDBCSLanguage())
//         {
//            isOrdinary = Character.isUnicodeIdentifierStart( ch );
//         }
      }
      return isOrdinary;
   }

   /**
    * Determines if the specified character is an ordinary character
    * for IBM Cloudscape.
    * <p>
    * @param  ch  A char.
    * @return  True if the given char is upper case for IBM Cloudscape.
    */
   public static boolean isOrdinaryCloudscapeChar(char ch)
   {
      boolean isOrdinary = false;
      if (!Locale.getDefault().getLanguage().equalsIgnoreCase("en")) //$NON-NLS-1$
      {
         isOrdinary = Character.isUnicodeIdentifierStart( ch );
      }
      else
      {
         HashSet<Character> flags = ordinaryFlagsSet.get(PLATFORM_CLOUDSCAPE_KEY);
         if (flags == null)
         {
            flags = initOrdinaryFlagsCloudscape();
         }
         isOrdinary = flags.contains(new Character(ch));// If the language of the current locale is double-byte (that is,
         // Chinese, Korean, Japanese), then consider double-byte characters
         // to be ordinary characters.
//         if (isOrdinary == false && isDBCSLanguage() == true)
//         {
//            isOrdinary = Character.isUnicodeIdentifierStart( ch );
//         }
      }
      return isOrdinary;
   }

   /**
    * Determines if the specified character is an ordinary character
    * for a database whose ordinary characters are specified
    * using setExtraOrdinaryChars(String).
    * <p>
    * @param  ch  A char.
    * @return  True if the given char is allowed in an ordinary identifier.
    */
   public static boolean isOrdinaryOtherChar(char ch)
   {
      if (otherOrdinaryFlags == null)
         return isOrdinaryLUWOChar(ch);
      else
         return otherOrdinaryFlags.contains(new Character(ch));
   }

   /**
    * Determines if the specified character is an ordinary character
    * for LUWO, OS/390, OS/400, or a combination of platforms,
    * for an identifier in DB2.
    * <p>
    * @param  ch  A char.
    * @param  platforms  The sum of platform constants.
    * The platforms are ignored if you have set the extra characters
    * using setExtraOrdinaryChars(String).
    * @return  True if the given char is an upper case ordinary character
    * for the DB2 platform or platforms.
    */
   public static boolean isOrdinaryUpperChar(char ch, int platforms)
   {
      boolean ok = false;
      if (otherOrdinaryFlags != null)
      {
         ok = isOrdinaryOtherChar(ch);
      }
      else
      {
         if ((platforms & PLATFORM_LUWO) > 0)
            ok = isOrdinaryLUWOChar(ch);
         if (!ok && ((platforms & PLATFORM_390) > 0))
            ok = isOrdinary390Char(ch);
         if (!ok && ((platforms & PLATFORM_400) > 0))
            ok = isOrdinary400Char(ch);
         if (!ok && ((platforms & PLATFORM_CLOUDSCAPE) > 0)) // Same as PLATFORM_DERBY
            ok = isOrdinaryCloudscapeChar(ch);
         // Defaults to LUWO:
         if (!ok && ((platforms & PLATFORM_INFORMIX) > 0))
            ok = isOrdinaryLUWOChar(ch);
         if (!ok && ((platforms & PLATFORM_ORACLE) > 0))
            ok = isOrdinaryLUWOChar(ch);
         if (!ok && ((platforms & PLATFORM_SYBASE) > 0))
            ok = isOrdinaryLUWOChar(ch);
         if (!ok && ((platforms & PLATFORM_SQLSERVER) > 0))
            ok = isOrdinaryLUWOChar(ch);
         if (!ok && ((platforms & PLATFORM_MYSQL) > 0))
            ok = isOrdinaryLUWOChar(ch);
         if (!ok && ((platforms & PLATFORM_OTHER) > 0))
            ok = isOrdinaryLUWOChar(ch);
      }
      if (ok && (platforms & PLATFORM_INFORMIX) == 0)
      {
         ok = !Character.isLowerCase(ch);
      }
      return ok;
   }

   /**
    * Returns true if the given SQL identifier is a reserved word
    * for DB2 UDB for LUWO.
    * If an identifier is delimited but is exactly equal to a reserved schema
    * within the delimiters, then it is reserved.
    * <p>
    * @param  id        An SQL identifier.
    * @param  delimiter  The delimiter defined as the escape character.
    * @return True if the given identifier is reserved for LUWO.
    */
   public static boolean isReservedIdentifier(String id, char delimiter)
   {
      return isReservedIdentifier(id, delimiter, PLATFORM_LUWO);
   }

   /**
    * Returns true if the given SQL ordinary identifier is a reserved word
    * for DB2 UDB for LUWO, OS/390, OS/400, or a combination of platforms.
    * If an identifier is delimited but is exactly equal to a reserved schema
    * within the delimiters, then it is a reserved word.
    * <p>
    * @param  id        An SQL ordinary identifier.
    * @param  delimiter  The delimiter defined as the escape character
    * on the DB2 platform or platforms.
    * @param  platforms  The sum of platform constants.
    * @return True if the given identifier is reserved for the specified platforms.
    */
   public static boolean isReservedIdentifier(String id, char delimiter, int platforms)
   {
      String up = convertUserInput(id, delimiter, platforms);
      boolean ok = false;
      if ((platforms & PLATFORM_LUWO) > 0)
      {
         if (luwoReserved == null)
            luwoReserved = initReserved("DB2 UDB", "V8.2"); //$NON-NLS-1$ //$NON-NLS-2$
         ok = (luwoReserved != null) && luwoReserved.contains(up);
      }
      if (!ok && ((platforms & PLATFORM_390) > 0))
      {
         if (os390Reserved == null)
            os390Reserved = initReserved("DB2 UDB zSeries", "V8 (New-Function Mode)"); //$NON-NLS-1$ //$NON-NLS-2$
         ok = (os390Reserved != null) && os390Reserved.contains(up);
      }
      if (!ok && ((platforms & PLATFORM_400) > 0))
      {
         if (os400Reserved == null)
            os400Reserved = initReserved("DB2 UDB iSeries", "V5R4"); //$NON-NLS-1$ //$NON-NLS-2$
         ok = (os400Reserved != null) && os400Reserved.contains(up);
      }
      if (!ok && ((platforms & PLATFORM_CLOUDSCAPE) > 0))
      {
         if (cloudscapeReserved == null)
            cloudscapeReserved = initReserved("IBM Cloudscape", "10.0"); //$NON-NLS-1$ //$NON-NLS-2$
         ok = (cloudscapeReserved != null) && cloudscapeReserved.contains(up);
      }
      // Defaults to LUWO:
      if (!ok && ((platforms & PLATFORM_OTHER) > 0))
      {
         if (luwoReserved == null)
            luwoReserved = initReserved("DB2 UDB", "V8.2"); //$NON-NLS-1$ //$NON-NLS-2$
         ok = (luwoReserved != null) && luwoReserved.contains(up);
      }
      return ok;
   }

   /**
    * Returns true if the given SQL ordinary identifier is a reserved word.
    * This method should be used only for PLATFORM_OTHER.
    * For DB2 and IBM Cloudscape platforms, we have canonical Lists of keywords.
    * See isReservedIdentifier(String id, char delimiter, int platforms).
    * <p>
    * @param  id        An SQL identifier.
    * @param  conInfo   Describes the database connection.
    * @return True if the id is a keyword, according to the DatabaseDefinition.
    */
   public static boolean isReservedIdentifier(String id, ConnectionInfo conInfo)
   {
      DatabaseDefinition dbDef = conInfo.getDatabaseDefinition();
      return isReservedIdentifier(id, dbDef);
   }

   /**
    * Returns true if the given SQL ordinary identifier is a reserved word.
    * This method should be used only for PLATFORM_OTHER.
    * For DB2 and IBM Cloudscape platforms, we have canonical Lists of keywords.
    * See isReservedIdentifier(String id, char delimiter, int platforms).
    * <p>
    * @param  id        An SQL identifier.
    * @param  dbDef     Describes the database.
    * @return True if the id is a keyword, according to the DatabaseDefinition.
    */
   @SuppressWarnings("unchecked")
   public static boolean isReservedIdentifier(String id, DatabaseDefinition dbDef)
   {
      if (dbDef != null)
      {
         List<String> reslist = dbDef.getSQLKeywords();
         return reslist.contains(id);
      }
      return false;
   }

   /**
    * Returns true if the given SQL identifier is a reserved schema.
    * If an identifier is delimited but is exactly equal to a reserved schema
    * within the delimiters, then it is reserved.
    * <p>
    * @param  id        A schema
    * @param  delimiter  The delimiter defined as the escape character.
    * @return True if the given identifier is a reserved schema.
    */
   public static boolean isReservedSchema(String id, char delimiter)
   {
      return schemaReserved.contains(convertUserInput(id, delimiter));
   }

   /**
    * Returns true if the given string is an ordinary identifier
    * for DB2 UDB for LUWO <i>ignoring the case of each letter</i>;
    * otherwise returns false.
    * This method is used when processing an SQL identifier from user input,
    * where delimiters may or may not be provided.
    * <p>
    * (Formerly named isInputOrdinary.)
    * <p>
    * This method does not guarantee that the identifier is valid; it might violate
    * other rules for identifiers, such as exceeding the maximum length.
    * <p>
    * For LUWO, OS/390, or OS/400 or a combination of platforms,
    * see {@link #isUserInputOrdinary(String,int) isUserInputOrdinary(String, int)}.
    * <p>
    * @param  id  A candidate ordinary identifier.
    * @return  True if the id is an ordinary identifier for LUWO.
    * @see #isUserInputOrdinary(String, int)
    */
   public static boolean isUserInputOrdinary(String id)
   {
      return isUserInputOrdinary(id, PLATFORM_LUWO);
   }

   /**
    * Returns true if the given string as input by the user is an ordinary identifier
    * for LUWO, OS/390, or OS/400 or a combination of platforms,
    * <i>ignoring the case of each letter</i>; otherwise returns false.
    * This method is used when processing an SQL identifier from user input,
    * where delimiters may or may not be provided.
    * <p>
    * (Formerly named isInputOrdinary.)
    * <p>
    * This method does not guarantee that the identifier is valid; it might violate
    * other rules for identifiers, such as exceeding the maximum length.
    * <p>
    * @param  id  A candidate ordinary identifier.
    * @param  delimiter  The delimiter defined as the escape character
    * on the DB2 platform or platforms.
    * @param  platforms  The sum of platform constants.
    * @return  True if the id is an ordinary identifier for the specified platforms.
    */
   public static boolean isUserInputOrdinary(String id, char delimiter, int platforms)
   {
      if (id == null || id.length() == 0)
      {
         return true;
      }
      boolean delimited = false;
      int range;
      String upid = id;
      // Remove delimiters. (We don't trim first because spaces
      // before or after a delimiter would indicate that the delimiter char
      // is part of the identifier, which would then not be ordinary.
      // On the other hand, we don't verify that an internal delimiter is doubled.)
      if (upid.charAt(0) == delimiter)
      {
         delimited = true;
         upid = upid.substring(1);
         range = upid.length() - 1;
         if (upid.charAt(range) == delimiter)
         {
            upid = upid.substring(0, range);
         }
      }
      // First character can be only alphabetic:
      char ch = upid.charAt(0);
      if ((delimited && Character.toUpperCase(ch) != ch)
               || !isOrdinaryChar(ch, platforms) )
      {
         return false;
      }
      // Trailing blanks are ignored in an ordinary identifier:
      upid = trim(upid, TRIM_RIGHT, delimiter);
      // Remaining characters--alpha, digit, or underscore.
      range = upid.length();
      for (int c = 1; c < range; c++)
      {
         ch = upid.charAt(c);
         if ((delimited && Character.toUpperCase(ch) != ch)
                  || (!isOrdinaryChar(ch, platforms)
                           && !isOrdinaryDigit(ch)
                           && ch != '_'))
         {
            return false;
         }
      }
      // Reserved words:
      if (isReservedIdentifier(upid, delimiter, platforms))
      {
         return false;
      }
      return true;
   }

   /**
    * Returns true if the given string is an ordinary identifier
    * <i>ignoring the case of each letter</i>; otherwise, returns false.
    * This method is used when processing an SQL identifier from user input,
    * where delimiters may or may not be provided.
    * <p>
    * This method does not guarantee that the identifier is valid; it might violate
    * other rules for identifiers, such as exceeding the maximum length.
    * <p>
    * @param  id  A candidate ordinary identifier.
    * @param  conInfo   Describes the database connection.
    * @return  True if the id is an ordinary identifier for the ConnectionInfo.
    */
   public static boolean isUserInputOrdinary(String id, ConnectionInfo conInfo)
   {
      return isUserInputOrdinary(id, getDelimiter(conInfo), getPlatform(conInfo));
   }
   
   /**
    * Returns true if the given string is an ordinary identifier
    * for LUWO, OS/390, or OS/400 or a combination of platforms,
    * <i>ignoring the case of each letter</i>; otherwise returns false.
    * This method is used when processing an SQL identifier from user input,
    * where delimiters may or may not be provided.
    * <p>
    * (Formerly named isInputOrdinary.)
    * <p>
    * This method assumes that the delimiter is '"'.
    * To specify the delimiter,
    * see {@link #isUserInputOrdinary(String,char,int) isUserInputOrdinary(String, char, int)}.
    * <p>
    * This method does not guarantee that the identifier is valid; it might violate
    * other rules for identifiers, such as exceeding the maximum length.
    * <p>
    * @param  id  A candidate ordinary identifier.
    * @param  platforms  The sum of platform constants.
    * @return  True if the id is an ordinary identifier for the specified platforms.
    */
   public static boolean isUserInputOrdinary(String id, int platforms)
   {
      return isUserInputOrdinary(id, DEFAULT_DELIM, platforms);
   }

   /**
    * Sets the extra characters that you can use in an ordinary identifier
    * (those beyond a-z, A-Z, 0-9 and _), between 35 and 383.
    * You need this only for databases other than DB2 UDB on LUWO, 390, and 400.
    * If this method is not called, this class uses more complete tables of
    * the allowable characters than you can get from JDBC, but these are available
    * only for the three DB2 UDB platforms.
    * <p>
    * @param  connection A live Connection.
    * A null removes the otherOrdinaryFlags.
    * @see java.sql.DatabaseMetaData#getExtraNameCharacters().
    */
   public static void setExtraOrdinaryChars(Connection connection)
   {
      otherOrdinaryFlags = null;
      if (connection != null)
      {
         Object prod = null;
         try
         {
            prod = connection.getMetaData().getDatabaseProductName();
         }
         catch (SQLException e)
         {
            prod = PLATFORM_OTHER_KEY;
         }
         otherOrdinaryFlags = ordinaryFlagsSet.get(prod);
         if (otherOrdinaryFlags == null)
         {
            String extras = null;
            try
            {
               extras = connection.getMetaData().getExtraNameCharacters();
            }
            catch (SQLException e)
            {
               return; // leave the flags null, so to be unused.
            }
            // Indexes in this array are offset by 35; that is 0 == 35.
            // Characters over '\u017F' (383) long_s (indexes over 342) are ignored.
            otherOrdinaryFlags = new HashSet<Character>(59);
            ordinaryFlagsSet.put(prod, otherOrdinaryFlags);
            if (extras != null)
            {
               int elen = extras.length();
               char c;
               for (int i = 0; i < elen; i++)
               {
                  c = extras.charAt(i);
                  otherOrdinaryFlags.add(new Character(c));
               }
            }
            initCommonOrdinaryFlags(otherOrdinaryFlags);
            otherOrdinaryFlags.add(new Character('\u005F')); // (95) _
         }
      }
   }

   /**
    * Trims blanks ('\u0020'), but not other whitespace characters such as tabs,
    * from the left, the right, and the right inside a delimiter.
    * Different in behavior from java.lang.String.trim(),
    * which trims trims all ASCII control characters having codes
    * less than or equal to '\u0020'.
    * <p>
    * @param id  An SQL identifier
    * @param side  A sum of:
    * <dl>
    * <dt>TRIM_LEFT  <dd>Trim from the left.
    * <dt>TRIM_RIGHT  <dd>Trim from the right.
    * <dt>TRIM_INSIDE <dd>Trim inside the specified delimiter from the right.
    * <dt>TRIM_ALL    <dd>All of the above.
    * </dl>
    * @param  delimiter  The delimiter defined as the escape character,
    * used only if (side & TRIM_INSIDE) > 0.
    */
   public static String trim(String id, int side, char delimiter)
   {
      if (id == null || id.length() == 0)
         return id;
      int left = 0;
      int right;
      char ch;
      // Left:
      if ((side & TRIM_LEFT) > 0)
      {
         ch = id.charAt(left);
         while (ch == ' ' && left < id.length())
         {
            left++;
            if (left < id.length())
               ch = id.charAt(left);
         }
      }
      // Right:
      right = id.length() - 1;
		if (right > -1 && (side & TRIM_RIGHT) > 0)
      {
         ch = id.charAt(right);
         //System.out.println("trimming right");
			while (ch == ' ' && right > -1)
         {
            right--;
            //System.out.println("\t" + right);
				if (right > -1)
               ch = id.charAt(right);
         }
      }
      // Inside right:
		if (right > -1)   // Add  && left < id.length() ?
      {
         //System.out.println("inside starting " + right);
         ch = id.charAt(right);
         int r = -1;
         if ((side & TRIM_INSIDE) > 0 && (side & TRIM_RIGHT) == 0) // Skip trailing blanks to the delimiter
         {
            while (right > -1 && ch == ' ' && ch != delimiter)
            {
               right--;
               if (right > -1)
                  ch = id.charAt(right);
            }
            if (right > -1 && right < id.length() - 1)
               r = right + 1;
         }
         if ((side & TRIM_INSIDE) > 0 && right > 0 && ch == delimiter)
         {
            right--; // skip the delimiter
            ch = id.charAt(right);
				while (ch == ' ' && right > -1)
            {
					right--;
					if (right > -1)
						ch = id.charAt(right);
				}
				StringBuffer sb2 = new StringBuffer();
            //System.out.println("left: " + left);
            //System.out.println("right: " + right);
				if (right >= left)
            {
               sb2.append(id.substring(left, right + 1)).append(delimiter);
               if (r > -1)
                  sb2.append(id.substring(r));
            }
            else
            {
               sb2.append(id.substring(left)).append(delimiter);
            }
            return sb2.toString();
			}
			else if (right >= left)
         {
            if ((side & TRIM_INSIDE) > 0 && (side & TRIM_RIGHT) == 0)
            {
               return id;
            }
            else
            {
               return id.substring(left, right + 1);
            }
         }
         else
         {
            return id.substring(left);
         }
      }
      else
      {
         return EMPTY_STRING;
      }
   }
   
   /**
    * Prevents construction of instances of this class.
    * All methods and attributes are static; there is no reason to construct it.
    */
   private SQLIdentifier()
   {
      // Do nothing.
   }

   /** Static initialization section. */
   static
   {
      ordinaryFlagsSet = new Hashtable<Object, HashSet<Character>>(37);
      //OrdinaryChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_$#@";
      // Letters also include the alphabetics from the extended character sets.
      // Extended character sets contain additional alphabetic characters; for example, those
      // with diacritical marks ( Hex B4 is an example of a diacritical mark). The available characters
      // depend on the code page in use.

      ordinaryDigits = "0123456789"; //$NON-NLS-1$
      // Define the "wide" (multi-byte) digits used in Chinese, Japanese, Korean.
      ordinaryWideDigits = "\uFF10\uFF11\uFF12\uFF13\uFF14\uFF15\uFF16\uFF17\uFF18\uFF19"; //$NON-NLS-1$

      schemaReserved = new HashSet<String>(6);
      schemaReserved.add("SYSCAT"); //$NON-NLS-1$
      schemaReserved.add("SYSFUN"); //$NON-NLS-1$
      schemaReserved.add("SYSIBM"); //$NON-NLS-1$
      schemaReserved.add("SYSSTAT"); //$NON-NLS-1$

   }

   /**
    * Returns a List of SQL keyswords for the given vendor and version.
    * <p>
    * @param vendor A vendor name in a DatabaseDefinition.
    * @param version A version string in a DatabaseDefinition.
    * @return A List of reserved words. Might be null.
    */
   @SuppressWarnings("unchecked")
   public static List<String> initReserved(String vendor, String version)
   {
      RDBCorePlugin rdb = RDBCorePlugin.getDefault();
      if (rdb != null)
      {
         DatabaseDefinitionRegistry ddreg = rdb.getDatabaseDefinitionRegistry();
         DatabaseDefinition ddef = ddreg.getDefinition(vendor, version);
         if (ddef != null)
         {
            return (List<String>)ddef.getSQLKeywords();
         }
      }
      // Defaults to LUWO:
      return luwoReserved;
   }

   /**
    * Initializes ordinary characters for IBM Cloudscape.
    * Has a side-effect of adding the HashSet to ordinaryFlagsSet.
    * @return The HashSet
    */
   protected static HashSet<Character> initOrdinaryFlagsCloudscape()
   {
      // For IBM Cloudscape:
      HashSet<Character> f = new HashSet<Character>(349);
      ordinaryFlagsSet.put(PLATFORM_CLOUDSCAPE_KEY, f);
      //f.add(new Character('\u0023')); // (35) #
      //f.add(new Character('\u0024')); // (36) $
      //f.add(new Character('\u0040')); // (64) @
      initCommonOrdinaryFlags(f);
      f.add(new Character('\u00AA')); // (170) feminine_ordinal
      f.add(new Character('\u00B5')); // (181) micro
      f.add(new Character('\u00BA')); // (186) masculine_ordinal
      f.add(new Character('\u00C0')); // (192) A_grave
      f.add(new Character('\u00C1')); // (193) A_acute
      f.add(new Character('\u00C2')); // (194) A_circumflex
      f.add(new Character('\u00C3')); // (195) A_tilde
      f.add(new Character('\u00C4')); // (196) A_umlaut
      f.add(new Character('\u00C5')); // (197) A_ring
      f.add(new Character('\u00C6')); // (198) AE_ligature
      f.add(new Character('\u00C7')); // (199) C_cedilla
      f.add(new Character('\u00C8')); // (200) E_grave
      f.add(new Character('\u00C9')); // (201) E_acute
      f.add(new Character('\u00CA')); // (202) E_circumflex
      f.add(new Character('\u00CB')); // (203) E_umlaut
      f.add(new Character('\u00CC')); // (204) I_grave
      f.add(new Character('\u00CD')); // (205) I_acute
      f.add(new Character('\u00CE')); // (206) I_circumflex
      f.add(new Character('\u00CF')); // (207) I_umlaut
      f.add(new Character('\u00D0')); // (208) ETH_Q
      f.add(new Character('\u00D1')); // (209) N_tilde
      f.add(new Character('\u00D2')); // (210) O_grave
      f.add(new Character('\u00D3')); // (211) O_acute
      f.add(new Character('\u00D4')); // (212) O_circumflex
      f.add(new Character('\u00D5')); // (213) O_tilde
      f.add(new Character('\u00D6')); // (214) O_umlaut
      f.add(new Character('\u00D8')); // (216) O_slash
      f.add(new Character('\u00D9')); // (217) U_grave
      f.add(new Character('\u00DA')); // (218) U_acute
      f.add(new Character('\u00DB')); // (219) U_circumflex
      f.add(new Character('\u00DC')); // (220) U_umlaut
      f.add(new Character('\u00DD')); // (221) Y_acute
      f.add(new Character('\u00DE')); // (222) THORN
      f.add(new Character('\u00DF')); // (223) sharp_s
      f.add(new Character('\u00E0')); // (224) a_grave
      f.add(new Character('\u00E1')); // (225) a_acute
      f.add(new Character('\u00E2')); // (226) a_circumflex
      f.add(new Character('\u00E3')); // (227) a_tilde
      f.add(new Character('\u00E4')); // (228) a_umlaut
      f.add(new Character('\u00E5')); // (229) a_ring
      f.add(new Character('\u00E6')); // (230) ae_ligature
      f.add(new Character('\u00E7')); // (231) c_cedilla
      f.add(new Character('\u00E8')); // (232) e_grave
      f.add(new Character('\u00E9')); // (233) e_acute
      f.add(new Character('\u00EA')); // (234) e_circumflex
      f.add(new Character('\u00EB')); // (235) e_umlaut
      f.add(new Character('\u00EC')); // (236) i_grave
      f.add(new Character('\u00ED')); // (237) i_acute
      f.add(new Character('\u00EE')); // (238) i_circumflex
      f.add(new Character('\u00EF')); // (239) i_umlaut
      f.add(new Character('\u00F0')); // (240) eth
      f.add(new Character('\u00F1')); // (241) n_tilde
      f.add(new Character('\u00F2')); // (242) o_grave
      f.add(new Character('\u00F3')); // (243) o_acute
      f.add(new Character('\u00F4')); // (244) o_circumflex
      f.add(new Character('\u00F5')); // (245) o_tilde
      f.add(new Character('\u00F6')); // (246) o_umlaut
      f.add(new Character('\u00F8')); // (248) o_slash
      f.add(new Character('\u00F9')); // (249) u_grave
      f.add(new Character('\u00FA')); // (250) u_acute
      f.add(new Character('\u00FB')); // (251) u_circumflex
      f.add(new Character('\u00FC')); // (252) u_umlaut
      f.add(new Character('\u00FD')); // (253) y_acute
      f.add(new Character('\u00FE')); // (254) thorn
      f.add(new Character('\u00FF')); // (255) y_umlaut
      f.add(new Character('\u0100')); // (256) A_bar
      f.add(new Character('\u0101')); // (257) a_bar
      f.add(new Character('\u0102')); // (258) A_breve
      f.add(new Character('\u0103')); // (259) a_breve
      f.add(new Character('\u0104')); // (260) A_ogokek
      f.add(new Character('\u0105')); // (261) a_ogokek
      f.add(new Character('\u0106')); // (262) C_acute
      f.add(new Character('\u0107')); // (263) c_acute
      f.add(new Character('\u0108')); // (264) C_circumflex
      f.add(new Character('\u0109')); // (265) c_circumflex
      f.add(new Character('\u010A')); // (266) C_superdot
      f.add(new Character('\u010B')); // (267) c_superdot
      f.add(new Character('\u010C')); // (268) C_caron
      f.add(new Character('\u010D')); // (269) c_caron
      f.add(new Character('\u010E')); // (270) D_caron
      f.add(new Character('\u010F')); // (271) d_caron
      f.add(new Character('\u0110')); // (272) D_stroke
      f.add(new Character('\u0111')); // (273) d_stroke
      f.add(new Character('\u0112')); // (274) E_macron
      f.add(new Character('\u0113')); // (275) e_macron
      f.add(new Character('\u0114')); // (276) E_breve
      f.add(new Character('\u0115')); // (277) e_breve
      f.add(new Character('\u0116')); // (278) E_superdot
      f.add(new Character('\u0117')); // (279) e_superdot
      f.add(new Character('\u0118')); // (280) E_ogonek
      f.add(new Character('\u0119')); // (281) e_ogonek
      f.add(new Character('\u011A')); // (282) E_caron
      f.add(new Character('\u011B')); // (283) e_caron
      f.add(new Character('\u011C')); // (284) G_circumflex
      f.add(new Character('\u011D')); // (285) g_circumflex
      f.add(new Character('\u011E')); // (286) G_breve
      f.add(new Character('\u011F')); // (287) g_breve
      f.add(new Character('\u0120')); // (288) G_superdot
      f.add(new Character('\u0121')); // (289) g_superdot
      f.add(new Character('\u0122')); // (290) G_cedilla
      f.add(new Character('\u0123')); // (291) g_cedilla
      f.add(new Character('\u0124')); // (292) H_circumflex
      f.add(new Character('\u0125')); // (293) h_circumflex
      f.add(new Character('\u0126')); // (294) H_stroke
      f.add(new Character('\u0127')); // (295) h_stroke
      f.add(new Character('\u0128')); // (296) I_tilde
      f.add(new Character('\u0129')); // (297) i_tilde
      f.add(new Character('\u012A')); // (298) I_macron
      f.add(new Character('\u012B')); // (299) i_macron
      f.add(new Character('\u012C')); // (300) I_breve
      f.add(new Character('\u012D')); // (301) i_breve
      f.add(new Character('\u012E')); // (302) I_ogonek
      f.add(new Character('\u012F')); // (303) i_ogonek
      f.add(new Character('\u0130')); // (304) I_superdot
      f.add(new Character('\u0131')); // (305) i_nodot
      f.add(new Character('\u0132')); // (306) IJ_ligature
      f.add(new Character('\u0133')); // (307) ij_ligature
      f.add(new Character('\u0134')); // (308) J_circumflex
      f.add(new Character('\u0135')); // (309) j_circumflex
      f.add(new Character('\u0136')); // (310) K_cedilla
      f.add(new Character('\u0137')); // (311) k_cedilla
      f.add(new Character('\u0138')); // (312) kra
      f.add(new Character('\u0139')); // (313) L_acute
      f.add(new Character('\u013A')); // (314) l_acute
      f.add(new Character('\u013B')); // (315) L_cedilla
      f.add(new Character('\u013C')); // (316) l_cedilla
      f.add(new Character('\u013D')); // (317) L_caron
      f.add(new Character('\u013E')); // (318) l_caron
      f.add(new Character('\u013F')); // (319) L_middot
      f.add(new Character('\u0140')); // (320) l_middot
      f.add(new Character('\u0141')); // (321) L_stoke
      f.add(new Character('\u0142')); // (322) l_stoke
      f.add(new Character('\u0143')); // (323) N_acute
      f.add(new Character('\u0144')); // (324) n_acute
      f.add(new Character('\u0145')); // (325) N_cedilla
      f.add(new Character('\u0146')); // (326) n_cedilla
      f.add(new Character('\u0147')); // (327) N_caron
      f.add(new Character('\u0148')); // (328) n_caron
      f.add(new Character('\u0149')); // (329) n_apostrophe
      f.add(new Character('\u014A')); // (330) ENG
      f.add(new Character('\u014B')); // (331) eng
      f.add(new Character('\u014C')); // (332) O_macron
      f.add(new Character('\u014D')); // (333) o_macron
      f.add(new Character('\u014E')); // (334) O_breve
      f.add(new Character('\u014F')); // (335) o_breve
      f.add(new Character('\u0150')); // (336) O_double_acute
      f.add(new Character('\u0151')); // (337) o_double_acute
      f.add(new Character('\u0152')); // (338) OE_ligature
      f.add(new Character('\u0153')); // (339) oe_ligature
      f.add(new Character('\u0154')); // (340) R_acute
      f.add(new Character('\u0155')); // (341) r_acute
      f.add(new Character('\u0156')); // (342) R_cedilla
      f.add(new Character('\u0157')); // (343) r_cedilla
      f.add(new Character('\u0158')); // (344) R_caron
      f.add(new Character('\u0159')); // (345) r_caron
      f.add(new Character('\u015A')); // (346) S_acute
      f.add(new Character('\u015B')); // (347) s_acute
      f.add(new Character('\u015C')); // (348) S_circumflex
      f.add(new Character('\u015D')); // (349) s_circumflex
      f.add(new Character('\u015E')); // (350) S_cedilla
      f.add(new Character('\u015F')); // (351) s_cedilla
      f.add(new Character('\u0160')); // (352) S_caron
      f.add(new Character('\u0161')); // (353) s_caron
      f.add(new Character('\u0162')); // (354) T_cedilla
      f.add(new Character('\u0163')); // (355) t_cedilla
      f.add(new Character('\u0164')); // (356) T_caron
      f.add(new Character('\u0165')); // (357) t_caron
      f.add(new Character('\u0166')); // (358) T_stroke
      f.add(new Character('\u0167')); // (359) t_stroke
      f.add(new Character('\u0168')); // (360) U_tilde
      f.add(new Character('\u0169')); // (361) u_tilde
      f.add(new Character('\u016A')); // (362) U_macron
      f.add(new Character('\u016B')); // (363) u_macron
      f.add(new Character('\u016C')); // (364) U_breve
      f.add(new Character('\u016D')); // (365) u_breve
      f.add(new Character('\u016E')); // (366) U_ring
      f.add(new Character('\u016F')); // (367) u_ring
      f.add(new Character('\u0170')); // (368) U_double_acute
      f.add(new Character('\u0171')); // (369) u_double_acute
      f.add(new Character('\u0172')); // (370) U_ogonek
      f.add(new Character('\u0173')); // (371) u_ogonek
      f.add(new Character('\u0174')); // (372) W_circumflex
      f.add(new Character('\u0175')); // (373) w_circumflex
      f.add(new Character('\u0176')); // (374) Y_circumflex
      f.add(new Character('\u0177')); // (375) y_circumflex
      f.add(new Character('\u0178')); // (376) Y_diaeresis
      f.add(new Character('\u0179')); // (377) Z_acute
      f.add(new Character('\u017A')); // (378) z_acute
      f.add(new Character('\u017B')); // (379) Z_superdot
      f.add(new Character('\u017C')); // (380) z_superdot
      f.add(new Character('\u017D')); // (381) Z_caron
      f.add(new Character('\u017E')); // (382) z_caron
      f.add(new Character('\u017F')); // (383) long_s
      f.add(new Character('\u0180')); // (384) X
      f.add(new Character('\u0181')); // (385) X
      f.add(new Character('\u0182')); // (386) X
      f.add(new Character('\u0183')); // (387) X
      f.add(new Character('\u0184')); // (388) X
      f.add(new Character('\u0185')); // (389) X
      f.add(new Character('\u0186')); // (390) X
      f.add(new Character('\u0187')); // (391) X
      f.add(new Character('\u0188')); // (392) X
      f.add(new Character('\u0189')); // (393) X
      f.add(new Character('\u018A')); // (394) X
      f.add(new Character('\u018B')); // (395) X
      f.add(new Character('\u018C')); // (396) X
      f.add(new Character('\u018D')); // (397) X
      f.add(new Character('\u018E')); // (398) X
      f.add(new Character('\u018F')); // (399) X
      f.add(new Character('\u0190')); // (400) X
      f.add(new Character('\u0191')); // (401) X
      f.add(new Character('\u0192')); // (402) X
      f.add(new Character('\u0193')); // (403) X
      f.add(new Character('\u0194')); // (404) X
      f.add(new Character('\u0195')); // (405) X
      f.add(new Character('\u0196')); // (406) X
      f.add(new Character('\u0197')); // (407) X
      f.add(new Character('\u0198')); // (408) X
      f.add(new Character('\u0199')); // (409) X
      f.add(new Character('\u019A')); // (410) X
      f.add(new Character('\u019B')); // (411) X
      f.add(new Character('\u019C')); // (412) X
      f.add(new Character('\u019D')); // (413) X
      f.add(new Character('\u019E')); // (414) X
      f.add(new Character('\u019F')); // (415) X
      f.add(new Character('\u01A0')); // (416) X
      f.add(new Character('\u01A1')); // (417) X
      f.add(new Character('\u01A2')); // (418) X
      f.add(new Character('\u01A3')); // (419) X
      f.add(new Character('\u01A4')); // (420) X
      f.add(new Character('\u01A5')); // (421) X
      f.add(new Character('\u01A6')); // (422) X
      f.add(new Character('\u01A7')); // (423) X
      f.add(new Character('\u01A8')); // (424) X
      f.add(new Character('\u01A9')); // (425) X
      f.add(new Character('\u01AA')); // (426) X
      f.add(new Character('\u01AB')); // (427) X
      f.add(new Character('\u01AC')); // (428) X
      f.add(new Character('\u01AD')); // (429) X
      f.add(new Character('\u01AE')); // (430) X
      f.add(new Character('\u01AF')); // (431) X
      f.add(new Character('\u01B0')); // (432) X
      f.add(new Character('\u01B1')); // (433) X
      f.add(new Character('\u01B2')); // (434) X
      f.add(new Character('\u01B3')); // (435) X
      f.add(new Character('\u01B4')); // (436) X
      f.add(new Character('\u01B5')); // (437) X
      f.add(new Character('\u01B6')); // (438) X
      f.add(new Character('\u01B7')); // (439) X
      f.add(new Character('\u01B8')); // (440) X
      f.add(new Character('\u01B9')); // (441) X
      f.add(new Character('\u01BA')); // (442) X
      f.add(new Character('\u01BB')); // (443) X
      f.add(new Character('\u01BC')); // (444) X
      f.add(new Character('\u01BD')); // (445) X
      f.add(new Character('\u01BE')); // (446) X
      f.add(new Character('\u01BF')); // (447) X
      f.add(new Character('\u01C0')); // (448) X
      f.add(new Character('\u01C1')); // (449) X
      f.add(new Character('\u01C2')); // (450) X
      f.add(new Character('\u01C3')); // (451) X
      f.add(new Character('\u01C4')); // (452) X
      f.add(new Character('\u01C5')); // (453) X
      f.add(new Character('\u01C6')); // (454) X
      f.add(new Character('\u01C7')); // (455) X
      f.add(new Character('\u01C8')); // (456) X
      f.add(new Character('\u01C9')); // (457) X
      f.add(new Character('\u01CA')); // (458) X
      f.add(new Character('\u01CB')); // (459) X
      f.add(new Character('\u01CC')); // (460) X
      f.add(new Character('\u01CD')); // (461) X
      f.add(new Character('\u01CE')); // (462) X
      f.add(new Character('\u01CF')); // (463) X
      f.add(new Character('\u01D0')); // (464) X
      f.add(new Character('\u01D1')); // (465) X
      f.add(new Character('\u01D2')); // (466) X
      f.add(new Character('\u01D3')); // (467) X
      f.add(new Character('\u01D4')); // (468) X
      f.add(new Character('\u01D5')); // (469) X
      f.add(new Character('\u01D6')); // (470) X
      f.add(new Character('\u01D7')); // (471) X
      f.add(new Character('\u01D8')); // (472) X
      f.add(new Character('\u01D9')); // (473) X
      f.add(new Character('\u01DA')); // (474) X
      f.add(new Character('\u01DB')); // (475) X
      f.add(new Character('\u01DC')); // (476) X
      f.add(new Character('\u01DD')); // (477) X
      f.add(new Character('\u01DE')); // (478) X
      f.add(new Character('\u01DF')); // (479) X
      f.add(new Character('\u01E0')); // (480) X
      f.add(new Character('\u01E1')); // (481) X
      f.add(new Character('\u01E2')); // (482) X
      f.add(new Character('\u01E3')); // (483) X
      f.add(new Character('\u01E4')); // (484) X
      f.add(new Character('\u01E5')); // (485) X
      f.add(new Character('\u01E6')); // (486) X
      f.add(new Character('\u01E7')); // (487) X
      f.add(new Character('\u01E8')); // (488) X
      f.add(new Character('\u01E9')); // (489) X
      f.add(new Character('\u01EA')); // (490) X
      f.add(new Character('\u01EB')); // (491) X
      f.add(new Character('\u01EC')); // (492) X
      f.add(new Character('\u01ED')); // (493) X
      f.add(new Character('\u01EE')); // (494) X
      f.add(new Character('\u01EF')); // (495) X
      f.add(new Character('\u01F0')); // (496) X
      f.add(new Character('\u01F1')); // (497) X
      f.add(new Character('\u01F2')); // (498) X
      f.add(new Character('\u01F3')); // (499) X
      f.add(new Character('\u01F4')); // (500) X
      f.add(new Character('\u01F5')); // (501) X
      f.add(new Character('\u01FA')); // (506) X
      f.add(new Character('\u01FB')); // (507) X
      f.add(new Character('\u01FC')); // (508) X
      f.add(new Character('\u01FD')); // (509) X
      f.add(new Character('\u01FE')); // (510) X
      f.add(new Character('\u01FF')); // (511) X
      f.add(new Character('\u0200')); // (512) X
      f.add(new Character('\u0201')); // (513) X
      f.add(new Character('\u0202')); // (514) X
      f.add(new Character('\u0203')); // (515) X
      f.add(new Character('\u0204')); // (516) X
      f.add(new Character('\u0205')); // (517) X
      f.add(new Character('\u0206')); // (518) X
      f.add(new Character('\u0207')); // (519) X
      f.add(new Character('\u0208')); // (520) X
      f.add(new Character('\u0209')); // (521) X
      f.add(new Character('\u020A')); // (522) X
      f.add(new Character('\u020B')); // (523) X
      f.add(new Character('\u020C')); // (524) X
      f.add(new Character('\u020D')); // (525) X
      f.add(new Character('\u020E')); // (526) X
      f.add(new Character('\u020F')); // (527) X
      f.add(new Character('\u0210')); // (528) X
      f.add(new Character('\u0211')); // (529) X
      f.add(new Character('\u0212')); // (530) X
      f.add(new Character('\u0213')); // (531) X
      f.add(new Character('\u0214')); // (532) X
      f.add(new Character('\u0215')); // (533) X
      f.add(new Character('\u0216')); // (534) X
      f.add(new Character('\u0217')); // (535) X
      f.add(new Character('\u0250')); // (592) X
      f.add(new Character('\u0251')); // (593) X
      f.add(new Character('\u0252')); // (594) X
      f.add(new Character('\u0253')); // (595) X
      f.add(new Character('\u0254')); // (596) X
      f.add(new Character('\u0255')); // (597) X
      f.add(new Character('\u0256')); // (598) X
      f.add(new Character('\u0257')); // (599) X
      f.add(new Character('\u0258')); // (600) X
      f.add(new Character('\u0259')); // (601) X
      f.add(new Character('\u025A')); // (602) X
      f.add(new Character('\u025B')); // (603) X
      f.add(new Character('\u025C')); // (604) X
      f.add(new Character('\u025D')); // (605) X
      f.add(new Character('\u025E')); // (606) X
      f.add(new Character('\u025F')); // (607) X
      f.add(new Character('\u0260')); // (608) X
      f.add(new Character('\u0261')); // (609) X
      f.add(new Character('\u0262')); // (610) X
      f.add(new Character('\u0263')); // (611) X
      f.add(new Character('\u0264')); // (612) X
      f.add(new Character('\u0265')); // (613) X
      f.add(new Character('\u0266')); // (614) X
      f.add(new Character('\u0267')); // (615) X
      f.add(new Character('\u0268')); // (616) X
      f.add(new Character('\u0269')); // (617) X
      f.add(new Character('\u026A')); // (618) X
      f.add(new Character('\u026B')); // (619) X
      f.add(new Character('\u026C')); // (620) X
      f.add(new Character('\u026D')); // (621) X
      f.add(new Character('\u026E')); // (622) X
      f.add(new Character('\u026F')); // (623) X
      f.add(new Character('\u0270')); // (624) X
      f.add(new Character('\u0271')); // (625) X
      f.add(new Character('\u0272')); // (626) X
      f.add(new Character('\u0273')); // (627) X
      f.add(new Character('\u0274')); // (628) X
      f.add(new Character('\u0275')); // (629) X
      f.add(new Character('\u0276')); // (630) X
      f.add(new Character('\u0277')); // (631) X
      f.add(new Character('\u0278')); // (632) X
      f.add(new Character('\u0279')); // (633) X
      f.add(new Character('\u027A')); // (634) X
      f.add(new Character('\u027B')); // (635) X
      f.add(new Character('\u027C')); // (636) X
      f.add(new Character('\u027D')); // (637) X
      f.add(new Character('\u027E')); // (638) X
      f.add(new Character('\u027F')); // (639) X
      f.add(new Character('\u0280')); // (640) X
      f.add(new Character('\u0281')); // (641) X
      f.add(new Character('\u0282')); // (642) X
      f.add(new Character('\u0283')); // (643) X
      f.add(new Character('\u0284')); // (644) X
      f.add(new Character('\u0285')); // (645) X
      f.add(new Character('\u0286')); // (646) X
      f.add(new Character('\u0287')); // (647) X
      f.add(new Character('\u0288')); // (648) X
      f.add(new Character('\u0289')); // (649) X
      f.add(new Character('\u028A')); // (650) X
      f.add(new Character('\u028B')); // (651) X
      f.add(new Character('\u028C')); // (652) X
      f.add(new Character('\u028D')); // (653) X
      f.add(new Character('\u028E')); // (654) X
      f.add(new Character('\u028F')); // (655) X
      f.add(new Character('\u0290')); // (656) X
      f.add(new Character('\u0291')); // (657) X
      f.add(new Character('\u0292')); // (658) X
      f.add(new Character('\u0293')); // (659) X
      f.add(new Character('\u0294')); // (660) X
      f.add(new Character('\u0295')); // (661) X
      f.add(new Character('\u0296')); // (662) X
      f.add(new Character('\u0297')); // (663) X
      f.add(new Character('\u0298')); // (664) X
      f.add(new Character('\u0299')); // (665) X
      f.add(new Character('\u029A')); // (666) X
      f.add(new Character('\u029B')); // (667) X
      f.add(new Character('\u029C')); // (668) X
      f.add(new Character('\u029D')); // (669) X
      f.add(new Character('\u029E')); // (670) X
      f.add(new Character('\u029F')); // (671) X
      f.add(new Character('\u02A0')); // (672) X
      f.add(new Character('\u02A1')); // (673) X
      f.add(new Character('\u02A2')); // (674) X
      f.add(new Character('\u02A3')); // (675) X
      f.add(new Character('\u02A4')); // (676) X
      f.add(new Character('\u02A5')); // (677) X
      f.add(new Character('\u02A6')); // (678) X
      f.add(new Character('\u02A7')); // (679) X
      f.add(new Character('\u02A8')); // (680) X
      f.add(new Character('\u02B0')); // (688) X
      f.add(new Character('\u02B1')); // (689) X
      f.add(new Character('\u02B2')); // (690) X
      f.add(new Character('\u02B3')); // (691) X
      f.add(new Character('\u02B4')); // (692) X
      f.add(new Character('\u02B5')); // (693) X
      f.add(new Character('\u02B6')); // (694) X
      f.add(new Character('\u02B7')); // (695) X
      f.add(new Character('\u02B8')); // (696) X
      f.add(new Character('\u02BB')); // (699) X
      f.add(new Character('\u02BC')); // (700) X
      f.add(new Character('\u02BD')); // (701) X
      f.add(new Character('\u02BE')); // (702) X
      f.add(new Character('\u02BF')); // (703) X
      f.add(new Character('\u02C0')); // (704) X
      f.add(new Character('\u02C1')); // (705) X
      f.add(new Character('\u02D0')); // (720) X
      f.add(new Character('\u02D1')); // (721) X
      f.add(new Character('\u02E0')); // (736) X
      f.add(new Character('\u02E1')); // (737) X
      f.add(new Character('\u02E2')); // (738) X
      f.add(new Character('\u02E3')); // (739) X
      f.add(new Character('\u02E4')); // (740) X
      f.add(new Character('\u037A')); // (890) X
      f.add(new Character('\u0386')); // (902) X
      f.add(new Character('\u0388')); // (904) X
      f.add(new Character('\u0389')); // (905) X
      f.add(new Character('\u038A')); // (906) X
      f.add(new Character('\u038C')); // (908) X
      f.add(new Character('\u038E')); // (910) X
      f.add(new Character('\u038F')); // (911) X
      f.add(new Character('\u0390')); // (912) X
      f.add(new Character('\u0391')); // (913) X
      f.add(new Character('\u0392')); // (914) X
      f.add(new Character('\u0393')); // (915) X
      f.add(new Character('\u0394')); // (916) X
      f.add(new Character('\u0395')); // (917) X
      f.add(new Character('\u0396')); // (918) X
      f.add(new Character('\u0397')); // (919) X
      f.add(new Character('\u0398')); // (920) X
      f.add(new Character('\u0399')); // (921) X
      f.add(new Character('\u039A')); // (922) X
      f.add(new Character('\u039B')); // (923) X
      f.add(new Character('\u039C')); // (924) X
      f.add(new Character('\u039D')); // (925) X
      f.add(new Character('\u039E')); // (926) X
      f.add(new Character('\u039F')); // (927) X
      f.add(new Character('\u03A0')); // (928) X
      f.add(new Character('\u03A1')); // (929) X
      f.add(new Character('\u03A3')); // (931) X
      f.add(new Character('\u03A4')); // (932) X
      f.add(new Character('\u03A5')); // (933) X
      f.add(new Character('\u03A6')); // (934) X
      f.add(new Character('\u03A7')); // (935) X
      f.add(new Character('\u03A8')); // (936) X
      f.add(new Character('\u03A9')); // (937) X
      f.add(new Character('\u03AA')); // (938) X
      f.add(new Character('\u03AB')); // (939) X
      f.add(new Character('\u03AC')); // (940) X
      f.add(new Character('\u03AD')); // (941) X
      f.add(new Character('\u03AE')); // (942) X
      f.add(new Character('\u03AF')); // (943) X
      f.add(new Character('\u03B0')); // (944) X
      f.add(new Character('\u03B1')); // (945) X
      f.add(new Character('\u03B2')); // (946) X
      f.add(new Character('\u03B3')); // (947) X
      f.add(new Character('\u03B4')); // (948) X
      f.add(new Character('\u03B5')); // (949) X
      f.add(new Character('\u03B6')); // (950) X
      f.add(new Character('\u03B7')); // (951) X
      f.add(new Character('\u03B8')); // (952) X
      f.add(new Character('\u03B9')); // (953) X
      f.add(new Character('\u03BA')); // (954) X
      f.add(new Character('\u03BB')); // (955) X
      f.add(new Character('\u03BC')); // (956) X
      f.add(new Character('\u03BD')); // (957) X
      f.add(new Character('\u03BE')); // (958) X
      f.add(new Character('\u03BF')); // (959) X
      f.add(new Character('\u03C0')); // (960) X
      f.add(new Character('\u03C1')); // (961) X
      f.add(new Character('\u03C2')); // (962) X
      f.add(new Character('\u03C3')); // (963) X
      f.add(new Character('\u03C4')); // (964) X
      f.add(new Character('\u03C5')); // (965) X
      f.add(new Character('\u03C6')); // (966) X
      f.add(new Character('\u03C7')); // (967) X
      f.add(new Character('\u03C8')); // (968) X
      f.add(new Character('\u03C9')); // (969) X
      f.add(new Character('\u03CA')); // (970) X
      f.add(new Character('\u03CB')); // (971) X
      f.add(new Character('\u03CC')); // (972) X
      f.add(new Character('\u03CD')); // (973) X
      f.add(new Character('\u03CE')); // (974) X
      f.add(new Character('\u03D0')); // (976) X
      f.add(new Character('\u03D1')); // (977) X
      f.add(new Character('\u03D2')); // (978) X
      f.add(new Character('\u03D3')); // (979) X
      f.add(new Character('\u03D4')); // (980) X
      f.add(new Character('\u03D5')); // (981) X
      f.add(new Character('\u03D6')); // (982) X
      f.add(new Character('\u03DA')); // (986) X
      f.add(new Character('\u03DC')); // (988) X
      f.add(new Character('\u03DE')); // (990) X
      f.add(new Character('\u03E0')); // (992) X
      f.add(new Character('\u03E2')); // (994) X
      f.add(new Character('\u03E3')); // (995) X
      f.add(new Character('\u03E4')); // (996) X
      f.add(new Character('\u03E5')); // (997) X
      f.add(new Character('\u03E6')); // (998) X
      f.add(new Character('\u03E7')); // (999) X
      f.add(new Character('\u03E8')); // (1000) X
      f.add(new Character('\u03E9')); // (1001) X
      f.add(new Character('\u03EA')); // (1002) X
      f.add(new Character('\u03EB')); // (1003) X
      f.add(new Character('\u03EC')); // (1004) X
      f.add(new Character('\u03ED')); // (1005) X
      f.add(new Character('\u03EE')); // (1006) X
      f.add(new Character('\u03EF')); // (1007) X
      f.add(new Character('\u03F0')); // (1008) X
      f.add(new Character('\u03F1')); // (1009) X
      f.add(new Character('\u03F2')); // (1010) X
      f.add(new Character('\u03F3')); // (1011) X
      f.add(new Character('\u0401')); // (1025) X
      f.add(new Character('\u0402')); // (1026) X
      f.add(new Character('\u0403')); // (1027) X
      f.add(new Character('\u0404')); // (1028) X
      f.add(new Character('\u0405')); // (1029) X
      f.add(new Character('\u0406')); // (1030) X
      f.add(new Character('\u0407')); // (1031) X
      f.add(new Character('\u0408')); // (1032) X
      f.add(new Character('\u0409')); // (1033) X
      f.add(new Character('\u040A')); // (1034) X
      f.add(new Character('\u040B')); // (1035) X
      f.add(new Character('\u040C')); // (1036) X
      f.add(new Character('\u040E')); // (1038) X
      f.add(new Character('\u040F')); // (1039) X
      f.add(new Character('\u0410')); // (1040) X
      f.add(new Character('\u0411')); // (1041) X
      f.add(new Character('\u0412')); // (1042) X
      f.add(new Character('\u0413')); // (1043) X
      f.add(new Character('\u0414')); // (1044) X
      f.add(new Character('\u0415')); // (1045) X
      f.add(new Character('\u0416')); // (1046) X
      f.add(new Character('\u0417')); // (1047) X
      f.add(new Character('\u0418')); // (1048) X
      f.add(new Character('\u0419')); // (1049) X
      f.add(new Character('\u041A')); // (1050) X
      f.add(new Character('\u041B')); // (1051) X
      f.add(new Character('\u041C')); // (1052) X
      f.add(new Character('\u041D')); // (1053) X
      f.add(new Character('\u041E')); // (1054) X
      f.add(new Character('\u041F')); // (1055) X
      f.add(new Character('\u0420')); // (1056) X
      f.add(new Character('\u0421')); // (1057) X
      f.add(new Character('\u0422')); // (1058) X
      f.add(new Character('\u0423')); // (1059) X
      f.add(new Character('\u0424')); // (1060) X
      f.add(new Character('\u0425')); // (1061) X
      f.add(new Character('\u0426')); // (1062) X
      f.add(new Character('\u0427')); // (1063) X
      f.add(new Character('\u0428')); // (1064) X
      f.add(new Character('\u0429')); // (1065) X
      f.add(new Character('\u042A')); // (1066) X
      f.add(new Character('\u042B')); // (1067) X
      f.add(new Character('\u042C')); // (1068) X
      f.add(new Character('\u042D')); // (1069) X
      f.add(new Character('\u042E')); // (1070) X
      f.add(new Character('\u042F')); // (1071) X
      f.add(new Character('\u0430')); // (1072) X
      f.add(new Character('\u0431')); // (1073) X
      f.add(new Character('\u0432')); // (1074) X
      f.add(new Character('\u0433')); // (1075) X
      f.add(new Character('\u0434')); // (1076) X
      f.add(new Character('\u0435')); // (1077) X
      f.add(new Character('\u0436')); // (1078) X
      f.add(new Character('\u0437')); // (1079) X
      f.add(new Character('\u0438')); // (1080) X
      f.add(new Character('\u0439')); // (1081) X
      f.add(new Character('\u043A')); // (1082) X
      f.add(new Character('\u043B')); // (1083) X
      f.add(new Character('\u043C')); // (1084) X
      f.add(new Character('\u043D')); // (1085) X
      f.add(new Character('\u043E')); // (1086) X
      f.add(new Character('\u043F')); // (1087) X
      f.add(new Character('\u0440')); // (1088) X
      f.add(new Character('\u0441')); // (1089) X
      f.add(new Character('\u0442')); // (1090) X
      f.add(new Character('\u0443')); // (1091) X
      f.add(new Character('\u0444')); // (1092) X
      f.add(new Character('\u0445')); // (1093) X
      f.add(new Character('\u0446')); // (1094) X
      f.add(new Character('\u0447')); // (1095) X
      f.add(new Character('\u0448')); // (1096) X
      f.add(new Character('\u0449')); // (1097) X
      f.add(new Character('\u044A')); // (1098) X
      f.add(new Character('\u044B')); // (1099) X
      f.add(new Character('\u044C')); // (1100) X
      f.add(new Character('\u044D')); // (1101) X
      f.add(new Character('\u044E')); // (1102) X
      f.add(new Character('\u044F')); // (1103) X
      f.add(new Character('\u0451')); // (1105) X
      f.add(new Character('\u0452')); // (1106) X
      f.add(new Character('\u0453')); // (1107) X
      f.add(new Character('\u0454')); // (1108) X
      f.add(new Character('\u0455')); // (1109) X
      f.add(new Character('\u0456')); // (1110) X
      f.add(new Character('\u0457')); // (1111) X
      f.add(new Character('\u0458')); // (1112) X
      f.add(new Character('\u0459')); // (1113) X
      f.add(new Character('\u045A')); // (1114) X
      f.add(new Character('\u045B')); // (1115) X
      f.add(new Character('\u045C')); // (1116) X
      f.add(new Character('\u045E')); // (1118) X
      f.add(new Character('\u045F')); // (1119) X
      f.add(new Character('\u0460')); // (1120) X
      f.add(new Character('\u0461')); // (1121) X
      f.add(new Character('\u0462')); // (1122) X
      f.add(new Character('\u0463')); // (1123) X
      f.add(new Character('\u0464')); // (1124) X
      f.add(new Character('\u0465')); // (1125) X
      f.add(new Character('\u0466')); // (1126) X
      f.add(new Character('\u0467')); // (1127) X
      f.add(new Character('\u0468')); // (1128) X
      f.add(new Character('\u0469')); // (1129) X
      f.add(new Character('\u046A')); // (1130) X
      f.add(new Character('\u046B')); // (1131) X
      f.add(new Character('\u046C')); // (1132) X
      f.add(new Character('\u046D')); // (1133) X
      f.add(new Character('\u046E')); // (1134) X
      f.add(new Character('\u046F')); // (1135) X
      f.add(new Character('\u0470')); // (1136) X
      f.add(new Character('\u0471')); // (1137) X
      f.add(new Character('\u0472')); // (1138) X
      f.add(new Character('\u0473')); // (1139) X
      f.add(new Character('\u0474')); // (1140) X
      f.add(new Character('\u0475')); // (1141) X
      f.add(new Character('\u0476')); // (1142) X
      f.add(new Character('\u0477')); // (1143) X
      f.add(new Character('\u0478')); // (1144) X
      f.add(new Character('\u0479')); // (1145) X
      f.add(new Character('\u047A')); // (1146) X
      f.add(new Character('\u047B')); // (1147) X
      f.add(new Character('\u047C')); // (1148) X
      f.add(new Character('\u047D')); // (1149) X
      f.add(new Character('\u047E')); // (1150) X
      f.add(new Character('\u047F')); // (1151) X
      f.add(new Character('\u0480')); // (1152) X
      f.add(new Character('\u0481')); // (1153) X
      f.add(new Character('\u0490')); // (1168) X
      f.add(new Character('\u0491')); // (1169) X
      f.add(new Character('\u0492')); // (1170) X
      f.add(new Character('\u0493')); // (1171) X
      f.add(new Character('\u0494')); // (1172) X
      f.add(new Character('\u0495')); // (1173) X
      f.add(new Character('\u0496')); // (1174) X
      f.add(new Character('\u0497')); // (1175) X
      f.add(new Character('\u0498')); // (1176) X
      f.add(new Character('\u0499')); // (1177) X
      f.add(new Character('\u049A')); // (1178) X
      f.add(new Character('\u049B')); // (1179) X
      f.add(new Character('\u049C')); // (1180) X
      f.add(new Character('\u049D')); // (1181) X
      f.add(new Character('\u049E')); // (1182) X
      f.add(new Character('\u049F')); // (1183) X
      f.add(new Character('\u04A0')); // (1184) X
      f.add(new Character('\u04A1')); // (1185) X
      f.add(new Character('\u04A2')); // (1186) X
      f.add(new Character('\u04A3')); // (1187) X
      f.add(new Character('\u04A4')); // (1188) X
      f.add(new Character('\u04A5')); // (1189) X
      f.add(new Character('\u04A6')); // (1190) X
      f.add(new Character('\u04A7')); // (1191) X
      f.add(new Character('\u04A8')); // (1192) X
      f.add(new Character('\u04A9')); // (1193) X
      f.add(new Character('\u04AA')); // (1194) X
      f.add(new Character('\u04AB')); // (1195) X
      f.add(new Character('\u04AC')); // (1196) X
      f.add(new Character('\u04AD')); // (1197) X
      f.add(new Character('\u04AE')); // (1198) X
      f.add(new Character('\u04AF')); // (1199) X
      f.add(new Character('\u04B0')); // (1200) X
      f.add(new Character('\u04B1')); // (1201) X
      f.add(new Character('\u04B2')); // (1202) X
      f.add(new Character('\u04B3')); // (1203) X
      f.add(new Character('\u04B4')); // (1204) X
      f.add(new Character('\u04B5')); // (1205) X
      f.add(new Character('\u04B6')); // (1206) X
      f.add(new Character('\u04B7')); // (1207) X
      f.add(new Character('\u04B8')); // (1208) X
      f.add(new Character('\u04B9')); // (1209) X
      f.add(new Character('\u04BA')); // (1210) X
      f.add(new Character('\u04BB')); // (1211) X
      f.add(new Character('\u04BC')); // (1212) X
      f.add(new Character('\u04BD')); // (1213) X
      f.add(new Character('\u04BE')); // (1214) X
      f.add(new Character('\u04BF')); // (1215) X
      f.add(new Character('\u04C0')); // (1216) X
      f.add(new Character('\u04C1')); // (1217) X
      f.add(new Character('\u04C2')); // (1218) X
      f.add(new Character('\u04C3')); // (1219) X
      f.add(new Character('\u04C4')); // (1220) X
      f.add(new Character('\u04C7')); // (1223) X
      f.add(new Character('\u04C8')); // (1224) X
      f.add(new Character('\u04CB')); // (1227) X
      f.add(new Character('\u04CC')); // (1228) X
      f.add(new Character('\u04D0')); // (1232) X
      f.add(new Character('\u04D1')); // (1233) X
      f.add(new Character('\u04D2')); // (1234) X
      f.add(new Character('\u04D3')); // (1235) X
      f.add(new Character('\u04D4')); // (1236) X
      f.add(new Character('\u04D5')); // (1237) X
      f.add(new Character('\u04D6')); // (1238) X
      f.add(new Character('\u04D7')); // (1239) X
      f.add(new Character('\u04D8')); // (1240) X
      f.add(new Character('\u04D9')); // (1241) X
      f.add(new Character('\u04DA')); // (1242) X
      f.add(new Character('\u04DB')); // (1243) X
      f.add(new Character('\u04DC')); // (1244) X
      f.add(new Character('\u04DD')); // (1245) X
      f.add(new Character('\u04DE')); // (1246) X
      f.add(new Character('\u04DF')); // (1247) X
      f.add(new Character('\u04E0')); // (1248) X
      f.add(new Character('\u04E1')); // (1249) X
      f.add(new Character('\u04E2')); // (1250) X
      f.add(new Character('\u04E3')); // (1251) X
      f.add(new Character('\u04E4')); // (1252) X
      f.add(new Character('\u04E5')); // (1253) X
      f.add(new Character('\u04E6')); // (1254) X
      f.add(new Character('\u04E7')); // (1255) X
      f.add(new Character('\u04E8')); // (1256) X
      f.add(new Character('\u04E9')); // (1257) X
      f.add(new Character('\u04EA')); // (1258) X
      f.add(new Character('\u04EB')); // (1259) X
      f.add(new Character('\u04EE')); // (1262) X
      f.add(new Character('\u04EF')); // (1263) X
      f.add(new Character('\u04F0')); // (1264) X
      f.add(new Character('\u04F1')); // (1265) X
      f.add(new Character('\u04F2')); // (1266) X
      f.add(new Character('\u04F3')); // (1267) X
      f.add(new Character('\u04F4')); // (1268) X
      f.add(new Character('\u04F5')); // (1269) X
      //f.add(new Character('\u04F6')); // (1270) X
      //f.add(new Character('\u04F7')); // (1271) X
      f.add(new Character('\u04F8')); // (1272) X
      f.add(new Character('\u04F9')); // (1273) X
      f.add(new Character('\u0531')); // (1329) X
      f.add(new Character('\u0532')); // (1330) X
      f.add(new Character('\u0533')); // (1331) X
      f.add(new Character('\u0534')); // (1332) X
      f.add(new Character('\u0535')); // (1333) X
      f.add(new Character('\u0536')); // (1334) X
      f.add(new Character('\u0537')); // (1335) X
      f.add(new Character('\u0538')); // (1336) X
      f.add(new Character('\u0539')); // (1337) X
      f.add(new Character('\u053A')); // (1338) X
      f.add(new Character('\u053B')); // (1339) X
      f.add(new Character('\u053C')); // (1340) X
      f.add(new Character('\u053D')); // (1341) X
      f.add(new Character('\u053E')); // (1342) X
      f.add(new Character('\u053F')); // (1343) X
      f.add(new Character('\u0540')); // (1344) X
      f.add(new Character('\u0541')); // (1345) X
      f.add(new Character('\u0542')); // (1346) X
      f.add(new Character('\u0543')); // (1347) X
      f.add(new Character('\u0544')); // (1348) X
      f.add(new Character('\u0545')); // (1349) X
      f.add(new Character('\u0546')); // (1350) X
      f.add(new Character('\u0547')); // (1351) X
      f.add(new Character('\u0548')); // (1352) X
      f.add(new Character('\u0549')); // (1353) X
      f.add(new Character('\u054A')); // (1354) X
      f.add(new Character('\u054B')); // (1355) X
      f.add(new Character('\u054C')); // (1356) X
      f.add(new Character('\u054D')); // (1357) X
      f.add(new Character('\u054E')); // (1358) X
      f.add(new Character('\u054F')); // (1359) X
      f.add(new Character('\u0550')); // (1360) X
      f.add(new Character('\u0551')); // (1361) X
      f.add(new Character('\u0552')); // (1362) X
      f.add(new Character('\u0553')); // (1363) X
      f.add(new Character('\u0554')); // (1364) X
      f.add(new Character('\u0555')); // (1365) X
      f.add(new Character('\u0556')); // (1366) X
      f.add(new Character('\u0559')); // (1369) X
      f.add(new Character('\u0561')); // (1377) X
      f.add(new Character('\u0562')); // (1378) X
      f.add(new Character('\u0563')); // (1379) X
      f.add(new Character('\u0564')); // (1380) X
      f.add(new Character('\u0565')); // (1381) X
      f.add(new Character('\u0566')); // (1382) X
      f.add(new Character('\u0567')); // (1383) X
      f.add(new Character('\u0568')); // (1384) X
      f.add(new Character('\u0569')); // (1385) X
      f.add(new Character('\u056A')); // (1386) X
      f.add(new Character('\u056B')); // (1387) X
      f.add(new Character('\u056C')); // (1388) X
      f.add(new Character('\u056D')); // (1389) X
      f.add(new Character('\u056E')); // (1390) X
      f.add(new Character('\u056F')); // (1391) X
      f.add(new Character('\u0570')); // (1392) X
      f.add(new Character('\u0571')); // (1393) X
      f.add(new Character('\u0572')); // (1394) X
      f.add(new Character('\u0573')); // (1395) X
      f.add(new Character('\u0574')); // (1396) X
      f.add(new Character('\u0575')); // (1397) X
      f.add(new Character('\u0576')); // (1398) X
      f.add(new Character('\u0577')); // (1399) X
      f.add(new Character('\u0578')); // (1400) X
      f.add(new Character('\u0579')); // (1401) X
      f.add(new Character('\u057A')); // (1402) X
      f.add(new Character('\u057B')); // (1403) X
      f.add(new Character('\u057C')); // (1404) X
      f.add(new Character('\u057D')); // (1405) X
      f.add(new Character('\u057E')); // (1406) X
      f.add(new Character('\u057F')); // (1407) X
      f.add(new Character('\u0580')); // (1408) X
      f.add(new Character('\u0581')); // (1409) X
      f.add(new Character('\u0582')); // (1410) X
      f.add(new Character('\u0583')); // (1411) X
      f.add(new Character('\u0584')); // (1412) X
      f.add(new Character('\u0585')); // (1413) X
      f.add(new Character('\u0586')); // (1414) X
      f.add(new Character('\u0587')); // (1415) X
      f.add(new Character('\u05D0')); // (1488) X
      f.add(new Character('\u05D1')); // (1489) X
      f.add(new Character('\u05D2')); // (1490) X
      f.add(new Character('\u05D3')); // (1491) X
      f.add(new Character('\u05D4')); // (1492) X
      f.add(new Character('\u05D5')); // (1493) X
      f.add(new Character('\u05D6')); // (1494) X
      f.add(new Character('\u05D7')); // (1495) X
      f.add(new Character('\u05D8')); // (1496) X
      f.add(new Character('\u05D9')); // (1497) X
      f.add(new Character('\u05DA')); // (1498) X
      f.add(new Character('\u05DB')); // (1499) X
      f.add(new Character('\u05DC')); // (1500) X
      f.add(new Character('\u05DD')); // (1501) X
      f.add(new Character('\u05DE')); // (1502) X
      f.add(new Character('\u05DF')); // (1503) X
      f.add(new Character('\u05E0')); // (1504) X
      f.add(new Character('\u05E1')); // (1505) X
      f.add(new Character('\u05E2')); // (1506) X
      f.add(new Character('\u05E3')); // (1507) X
      f.add(new Character('\u05E4')); // (1508) X
      f.add(new Character('\u05E5')); // (1509) X
      f.add(new Character('\u05E6')); // (1510) X
      f.add(new Character('\u05E7')); // (1511) X
      f.add(new Character('\u05E8')); // (1512) X
      f.add(new Character('\u05E9')); // (1513) X
      f.add(new Character('\u05EA')); // (1514) X
      f.add(new Character('\u05F0')); // (1520) X
      f.add(new Character('\u05F1')); // (1521) X
      f.add(new Character('\u05F2')); // (1522) X
      f.add(new Character('\u0621')); // (1569) X
      f.add(new Character('\u0622')); // (1570) X
      f.add(new Character('\u0623')); // (1571) X
      f.add(new Character('\u0624')); // (1572) X
      f.add(new Character('\u0625')); // (1573) X
      f.add(new Character('\u0626')); // (1574) X
      f.add(new Character('\u0627')); // (1575) X
      f.add(new Character('\u0628')); // (1576) X
      f.add(new Character('\u0629')); // (1577) X
      f.add(new Character('\u062A')); // (1578) X
      f.add(new Character('\u062B')); // (1579) X
      f.add(new Character('\u062C')); // (1580) X
      f.add(new Character('\u062D')); // (1581) X
      f.add(new Character('\u062E')); // (1582) X
      f.add(new Character('\u062F')); // (1583) X
      f.add(new Character('\u0630')); // (1584) X
      f.add(new Character('\u0631')); // (1585) X
      f.add(new Character('\u0632')); // (1586) X
      f.add(new Character('\u0633')); // (1587) X
      f.add(new Character('\u0634')); // (1588) X
      f.add(new Character('\u0635')); // (1589) X
      f.add(new Character('\u0636')); // (1590) X
      f.add(new Character('\u0637')); // (1591) X
      f.add(new Character('\u0638')); // (1592) X
      f.add(new Character('\u0639')); // (1593) X
      f.add(new Character('\u063A')); // (1594) X
      f.add(new Character('\u0640')); // (1600) X
      f.add(new Character('\u0641')); // (1601) X
      f.add(new Character('\u0642')); // (1602) X
      f.add(new Character('\u0643')); // (1603) X
      f.add(new Character('\u0644')); // (1604) X
      f.add(new Character('\u0645')); // (1605) X
      f.add(new Character('\u0646')); // (1606) X
      f.add(new Character('\u0647')); // (1607) X
      f.add(new Character('\u0648')); // (1608) X
      f.add(new Character('\u0649')); // (1609) X
      f.add(new Character('\u064A')); // (1610) X
      f.add(new Character('\u0671')); // (1649) X
      f.add(new Character('\u0672')); // (1650) X
      f.add(new Character('\u0673')); // (1651) X
      f.add(new Character('\u0674')); // (1652) X
      f.add(new Character('\u0675')); // (1653) X
      f.add(new Character('\u0676')); // (1654) X
      f.add(new Character('\u0677')); // (1655) X
      f.add(new Character('\u0678')); // (1656) X
      f.add(new Character('\u0679')); // (1657) X
      f.add(new Character('\u067A')); // (1658) X
      f.add(new Character('\u067B')); // (1659) X
      f.add(new Character('\u067C')); // (1660) X
      f.add(new Character('\u067D')); // (1661) X
      f.add(new Character('\u067E')); // (1662) X
      f.add(new Character('\u067F')); // (1663) X
      f.add(new Character('\u0680')); // (1664) X
      f.add(new Character('\u0681')); // (1665) X
      f.add(new Character('\u0682')); // (1666) X
      f.add(new Character('\u0683')); // (1667) X
      f.add(new Character('\u0684')); // (1668) X
      f.add(new Character('\u0685')); // (1669) X
      f.add(new Character('\u0686')); // (1670) X
      f.add(new Character('\u0687')); // (1671) X
      f.add(new Character('\u0688')); // (1672) X
      f.add(new Character('\u0689')); // (1673) X
      f.add(new Character('\u068A')); // (1674) X
      f.add(new Character('\u068B')); // (1675) X
      f.add(new Character('\u068C')); // (1676) X
      f.add(new Character('\u068D')); // (1677) X
      f.add(new Character('\u068E')); // (1678) X
      f.add(new Character('\u068F')); // (1679) X
      f.add(new Character('\u0690')); // (1680) X
      f.add(new Character('\u0691')); // (1681) X
      f.add(new Character('\u0692')); // (1682) X
      f.add(new Character('\u0693')); // (1683) X
      f.add(new Character('\u0694')); // (1684) X
      f.add(new Character('\u0695')); // (1685) X
      f.add(new Character('\u0696')); // (1686) X
      f.add(new Character('\u0697')); // (1687) X
      f.add(new Character('\u0698')); // (1688) X
      f.add(new Character('\u0699')); // (1689) X
      f.add(new Character('\u069A')); // (1690) X
      f.add(new Character('\u069B')); // (1691) X
      f.add(new Character('\u069C')); // (1692) X
      f.add(new Character('\u069D')); // (1693) X
      f.add(new Character('\u069E')); // (1694) X
      f.add(new Character('\u069F')); // (1695) X
      f.add(new Character('\u06A0')); // (1696) X
      f.add(new Character('\u06A1')); // (1697) X
      f.add(new Character('\u06A2')); // (1698) X
      f.add(new Character('\u06A3')); // (1699) X
      f.add(new Character('\u06A4')); // (1700) X
      f.add(new Character('\u06A5')); // (1701) X
      f.add(new Character('\u06A6')); // (1702) X
      f.add(new Character('\u06A7')); // (1703) X
      f.add(new Character('\u06A8')); // (1704) X
      f.add(new Character('\u06A9')); // (1705) X
      f.add(new Character('\u06AA')); // (1706) X
      f.add(new Character('\u06AB')); // (1707) X
      f.add(new Character('\u06AC')); // (1708) X
      f.add(new Character('\u06AD')); // (1709) X
      f.add(new Character('\u06AE')); // (1710) X
      f.add(new Character('\u06AF')); // (1711) X
      f.add(new Character('\u06B0')); // (1712) X
      f.add(new Character('\u06B1')); // (1713) X
      f.add(new Character('\u06B2')); // (1714) X
      f.add(new Character('\u06B3')); // (1715) X
      f.add(new Character('\u06B4')); // (1716) X
      f.add(new Character('\u06B5')); // (1717) X
      f.add(new Character('\u06B6')); // (1718) X
      f.add(new Character('\u06B7')); // (1719) X
      f.add(new Character('\u06BA')); // (1722) X
      f.add(new Character('\u06BB')); // (1723) X
      f.add(new Character('\u06BC')); // (1724) X
      f.add(new Character('\u06BD')); // (1725) X
      f.add(new Character('\u06BE')); // (1726) X
      f.add(new Character('\u06C0')); // (1728) X
      f.add(new Character('\u06C1')); // (1729) X
      f.add(new Character('\u06C2')); // (1730) X
      f.add(new Character('\u06C3')); // (1731) X
      f.add(new Character('\u06C4')); // (1732) X
      f.add(new Character('\u06C5')); // (1733) X
      f.add(new Character('\u06C6')); // (1734) X
      f.add(new Character('\u06C7')); // (1735) X
      f.add(new Character('\u06C8')); // (1736) X
      f.add(new Character('\u06C9')); // (1737) X
      f.add(new Character('\u06CA')); // (1738) X
      f.add(new Character('\u06CB')); // (1739) X
      f.add(new Character('\u06CC')); // (1740) X
      f.add(new Character('\u06CD')); // (1741) X
      f.add(new Character('\u06CE')); // (1742) X
      f.add(new Character('\u06D0')); // (1744) X
      f.add(new Character('\u06D1')); // (1745) X
      f.add(new Character('\u06D2')); // (1746) X
      f.add(new Character('\u06D3')); // (1747) X
      f.add(new Character('\u06D5')); // (1749) X
      f.add(new Character('\u06E5')); // (1765) X
      f.add(new Character('\u06E6')); // (1766) X
      f.add(new Character('\u0905')); // (2309) X
      f.add(new Character('\u0906')); // (2310) X
      f.add(new Character('\u0907')); // (2311) X
      f.add(new Character('\u0908')); // (2312) X
      f.add(new Character('\u0909')); // (2313) X
      f.add(new Character('\u090A')); // (2314) X
      f.add(new Character('\u090B')); // (2315) X
      f.add(new Character('\u090C')); // (2316) X
      f.add(new Character('\u090D')); // (2317) X
      f.add(new Character('\u090E')); // (2318) X
      f.add(new Character('\u090F')); // (2319) X
      f.add(new Character('\u0910')); // (2320) X
      f.add(new Character('\u0911')); // (2321) X
      f.add(new Character('\u0912')); // (2322) X
      f.add(new Character('\u0913')); // (2323) X
      f.add(new Character('\u0914')); // (2324) X
      f.add(new Character('\u0915')); // (2325) X
      f.add(new Character('\u0916')); // (2326) X
      f.add(new Character('\u0917')); // (2327) X
      f.add(new Character('\u0918')); // (2328) X
      f.add(new Character('\u0919')); // (2329) X
      f.add(new Character('\u091A')); // (2330) X
      f.add(new Character('\u091B')); // (2331) X
      f.add(new Character('\u091C')); // (2332) X
      f.add(new Character('\u091D')); // (2333) X
      f.add(new Character('\u091E')); // (2334) X
      f.add(new Character('\u091F')); // (2335) X
      f.add(new Character('\u0920')); // (2336) X
      f.add(new Character('\u0921')); // (2337) X
      f.add(new Character('\u0922')); // (2338) X
      f.add(new Character('\u0923')); // (2339) X
      f.add(new Character('\u0924')); // (2340) X
      f.add(new Character('\u0925')); // (2341) X
      f.add(new Character('\u0926')); // (2342) X
      f.add(new Character('\u0927')); // (2343) X
      f.add(new Character('\u0928')); // (2344) X
      f.add(new Character('\u0929')); // (2345) X
      f.add(new Character('\u092A')); // (2346) X
      f.add(new Character('\u092B')); // (2347) X
      f.add(new Character('\u092C')); // (2348) X
      f.add(new Character('\u092D')); // (2349) X
      f.add(new Character('\u092E')); // (2350) X
      f.add(new Character('\u092F')); // (2351) X
      f.add(new Character('\u0930')); // (2352) X
      f.add(new Character('\u0931')); // (2353) X
      f.add(new Character('\u0932')); // (2354) X
      f.add(new Character('\u0933')); // (2355) X
      f.add(new Character('\u0934')); // (2356) X
      f.add(new Character('\u0935')); // (2357) X
      f.add(new Character('\u0936')); // (2358) X
      f.add(new Character('\u0937')); // (2359) X
      f.add(new Character('\u0938')); // (2360) X
      f.add(new Character('\u0939')); // (2361) X
      f.add(new Character('\u093D')); // (2365) X
      f.add(new Character('\u0958')); // (2392) X
      f.add(new Character('\u0959')); // (2393) X
      f.add(new Character('\u095A')); // (2394) X
      f.add(new Character('\u095B')); // (2395) X
      f.add(new Character('\u095C')); // (2396) X
      f.add(new Character('\u095D')); // (2397) X
      f.add(new Character('\u095E')); // (2398) X
      f.add(new Character('\u095F')); // (2399) X
      f.add(new Character('\u0960')); // (2400) X
      f.add(new Character('\u0961')); // (2401) X
      f.add(new Character('\u0985')); // (2437) X
      f.add(new Character('\u0986')); // (2438) X
      f.add(new Character('\u0987')); // (2439) X
      f.add(new Character('\u0988')); // (2440) X
      f.add(new Character('\u0989')); // (2441) X
      f.add(new Character('\u098A')); // (2442) X
      f.add(new Character('\u098B')); // (2443) X
      f.add(new Character('\u098C')); // (2444) X
      f.add(new Character('\u098F')); // (2447) X
      f.add(new Character('\u0990')); // (2448) X
      f.add(new Character('\u0993')); // (2451) X
      f.add(new Character('\u0994')); // (2452) X
      f.add(new Character('\u0995')); // (2453) X
      f.add(new Character('\u0996')); // (2454) X
      f.add(new Character('\u0997')); // (2455) X
      f.add(new Character('\u0998')); // (2456) X
      f.add(new Character('\u0999')); // (2457) X
      f.add(new Character('\u099A')); // (2458) X
      f.add(new Character('\u099B')); // (2459) X
      f.add(new Character('\u099C')); // (2460) X
      f.add(new Character('\u099D')); // (2461) X
      f.add(new Character('\u099E')); // (2462) X
      f.add(new Character('\u099F')); // (2463) X
      f.add(new Character('\u09A0')); // (2464) X
      f.add(new Character('\u09A1')); // (2465) X
      f.add(new Character('\u09A2')); // (2466) X
      f.add(new Character('\u09A3')); // (2467) X
      f.add(new Character('\u09A4')); // (2468) X
      f.add(new Character('\u09A5')); // (2469) X
      f.add(new Character('\u09A6')); // (2470) X
      f.add(new Character('\u09A7')); // (2471) X
      f.add(new Character('\u09A8')); // (2472) X
      f.add(new Character('\u09AA')); // (2474) X
      f.add(new Character('\u09AB')); // (2475) X
      f.add(new Character('\u09AC')); // (2476) X
      f.add(new Character('\u09AD')); // (2477) X
      f.add(new Character('\u09AE')); // (2478) X
      f.add(new Character('\u09AF')); // (2479) X
      f.add(new Character('\u09B0')); // (2480) X
      f.add(new Character('\u09B2')); // (2482) X
      f.add(new Character('\u09B6')); // (2486) X
      f.add(new Character('\u09B7')); // (2487) X
      f.add(new Character('\u09B8')); // (2488) X
      f.add(new Character('\u09B9')); // (2489) X
      f.add(new Character('\u09DC')); // (2524) X
      f.add(new Character('\u09DD')); // (2525) X
      f.add(new Character('\u09DF')); // (2527) X
      f.add(new Character('\u09E0')); // (2528) X
      f.add(new Character('\u09E1')); // (2529) X
      f.add(new Character('\u09F0')); // (2544) X
      f.add(new Character('\u09F1')); // (2545) X
      f.add(new Character('\u0A05')); // (2565) X
      f.add(new Character('\u0A06')); // (2566) X
      f.add(new Character('\u0A07')); // (2567) X
      f.add(new Character('\u0A08')); // (2568) X
      f.add(new Character('\u0A09')); // (2569) X
      f.add(new Character('\u0A0A')); // (2570) X
      f.add(new Character('\u0A0F')); // (2575) X
      f.add(new Character('\u0A10')); // (2576) X
      f.add(new Character('\u0A13')); // (2579) X
      f.add(new Character('\u0A14')); // (2580) X
      f.add(new Character('\u0A15')); // (2581) X
      f.add(new Character('\u0A16')); // (2582) X
      f.add(new Character('\u0A17')); // (2583) X
      f.add(new Character('\u0A18')); // (2584) X
      f.add(new Character('\u0A19')); // (2585) X
      f.add(new Character('\u0A1A')); // (2586) X
      f.add(new Character('\u0A1B')); // (2587) X
      f.add(new Character('\u0A1C')); // (2588) X
      f.add(new Character('\u0A1D')); // (2589) X
      f.add(new Character('\u0A1E')); // (2590) X
      f.add(new Character('\u0A1F')); // (2591) X
      f.add(new Character('\u0A20')); // (2592) X
      f.add(new Character('\u0A21')); // (2593) X
      f.add(new Character('\u0A22')); // (2594) X
      f.add(new Character('\u0A23')); // (2595) X
      f.add(new Character('\u0A24')); // (2596) X
      f.add(new Character('\u0A25')); // (2597) X
      f.add(new Character('\u0A26')); // (2598) X
      f.add(new Character('\u0A27')); // (2599) X
      f.add(new Character('\u0A28')); // (2600) X
      f.add(new Character('\u0A2A')); // (2602) X
      f.add(new Character('\u0A2B')); // (2603) X
      f.add(new Character('\u0A2C')); // (2604) X
      f.add(new Character('\u0A2D')); // (2605) X
      f.add(new Character('\u0A2E')); // (2606) X
      f.add(new Character('\u0A2F')); // (2607) X
      f.add(new Character('\u0A30')); // (2608) X
      f.add(new Character('\u0A32')); // (2610) X
      f.add(new Character('\u0A33')); // (2611) X
      f.add(new Character('\u0A35')); // (2613) X
      f.add(new Character('\u0A36')); // (2614) X
      f.add(new Character('\u0A38')); // (2616) X
      f.add(new Character('\u0A39')); // (2617) X
      f.add(new Character('\u0A59')); // (2649) X
      f.add(new Character('\u0A5A')); // (2650) X
      f.add(new Character('\u0A5B')); // (2651) X
      f.add(new Character('\u0A5C')); // (2652) X
      f.add(new Character('\u0A5E')); // (2654) X
      f.add(new Character('\u0A72')); // (2674) X
      f.add(new Character('\u0A73')); // (2675) X
      f.add(new Character('\u0A74')); // (2676) X
      f.add(new Character('\u0A85')); // (2693) X
      f.add(new Character('\u0A86')); // (2694) X
      f.add(new Character('\u0A87')); // (2695) X
      f.add(new Character('\u0A88')); // (2696) X
      f.add(new Character('\u0A89')); // (2697) X
      f.add(new Character('\u0A8A')); // (2698) X
      f.add(new Character('\u0A8B')); // (2699) X
      f.add(new Character('\u0A8D')); // (2701) X
      f.add(new Character('\u0A8F')); // (2703) X
      f.add(new Character('\u0A90')); // (2704) X
      f.add(new Character('\u0A91')); // (2705) X
      f.add(new Character('\u0A93')); // (2707) X
      f.add(new Character('\u0A94')); // (2708) X
      f.add(new Character('\u0A95')); // (2709) X
      f.add(new Character('\u0A96')); // (2710) X
      f.add(new Character('\u0A97')); // (2711) X
      f.add(new Character('\u0A98')); // (2712) X
      f.add(new Character('\u0A99')); // (2713) X
      f.add(new Character('\u0A9A')); // (2714) X
      f.add(new Character('\u0A9B')); // (2715) X
      f.add(new Character('\u0A9C')); // (2716) X
      f.add(new Character('\u0A9D')); // (2717) X
      f.add(new Character('\u0A9E')); // (2718) X
      f.add(new Character('\u0A9F')); // (2719) X
      f.add(new Character('\u0AA0')); // (2720) X
      f.add(new Character('\u0AA1')); // (2721) X
      f.add(new Character('\u0AA2')); // (2722) X
      f.add(new Character('\u0AA3')); // (2723) X
      f.add(new Character('\u0AA4')); // (2724) X
      f.add(new Character('\u0AA5')); // (2725) X
      f.add(new Character('\u0AA6')); // (2726) X
      f.add(new Character('\u0AA7')); // (2727) X
      f.add(new Character('\u0AA8')); // (2728) X
      f.add(new Character('\u0AAA')); // (2730) X
      f.add(new Character('\u0AAB')); // (2731) X
      f.add(new Character('\u0AAC')); // (2732) X
      f.add(new Character('\u0AAD')); // (2733) X
      f.add(new Character('\u0AAE')); // (2734) X
      f.add(new Character('\u0AAF')); // (2735) X
      f.add(new Character('\u0AB0')); // (2736) X
      f.add(new Character('\u0AB2')); // (2738) X
      f.add(new Character('\u0AB3')); // (2739) X
      f.add(new Character('\u0AB5')); // (2741) X
      f.add(new Character('\u0AB6')); // (2742) X
      f.add(new Character('\u0AB7')); // (2743) X
      f.add(new Character('\u0AB8')); // (2744) X
      f.add(new Character('\u0AB9')); // (2745) X
      f.add(new Character('\u0ABD')); // (2749) X
      f.add(new Character('\u0AE0')); // (2784) X
      f.add(new Character('\u0B05')); // (2821) X
      f.add(new Character('\u0B06')); // (2822) X
      f.add(new Character('\u0B07')); // (2823) X
      f.add(new Character('\u0B08')); // (2824) X
      f.add(new Character('\u0B09')); // (2825) X
      f.add(new Character('\u0B0A')); // (2826) X
      f.add(new Character('\u0B0B')); // (2827) X
      f.add(new Character('\u0B0C')); // (2828) X
      f.add(new Character('\u0B0F')); // (2831) X
      f.add(new Character('\u0B10')); // (2832) X
      f.add(new Character('\u0B13')); // (2835) X
      f.add(new Character('\u0B14')); // (2836) X
      f.add(new Character('\u0B15')); // (2837) X
      f.add(new Character('\u0B16')); // (2838) X
      f.add(new Character('\u0B17')); // (2839) X
      f.add(new Character('\u0B18')); // (2840) X
      f.add(new Character('\u0B19')); // (2841) X
      f.add(new Character('\u0B1A')); // (2842) X
      f.add(new Character('\u0B1B')); // (2843) X
      f.add(new Character('\u0B1C')); // (2844) X
      f.add(new Character('\u0B1D')); // (2845) X
      f.add(new Character('\u0B1E')); // (2846) X
      f.add(new Character('\u0B1F')); // (2847) X
      f.add(new Character('\u0B20')); // (2848) X
      f.add(new Character('\u0B21')); // (2849) X
      f.add(new Character('\u0B22')); // (2850) X
      f.add(new Character('\u0B23')); // (2851) X
      f.add(new Character('\u0B24')); // (2852) X
      f.add(new Character('\u0B25')); // (2853) X
      f.add(new Character('\u0B26')); // (2854) X
      f.add(new Character('\u0B27')); // (2855) X
      f.add(new Character('\u0B28')); // (2856) X
      f.add(new Character('\u0B2A')); // (2858) X
      f.add(new Character('\u0B2B')); // (2859) X
      f.add(new Character('\u0B2C')); // (2860) X
      f.add(new Character('\u0B2D')); // (2861) X
      f.add(new Character('\u0B2E')); // (2862) X
      f.add(new Character('\u0B2F')); // (2863) X
      f.add(new Character('\u0B30')); // (2864) X
      f.add(new Character('\u0B32')); // (2866) X
      f.add(new Character('\u0B33')); // (2867) X
      f.add(new Character('\u0B36')); // (2870) X
      f.add(new Character('\u0B37')); // (2871) X
      f.add(new Character('\u0B38')); // (2872) X
      f.add(new Character('\u0B39')); // (2873) X
      f.add(new Character('\u0B3D')); // (2877) X
      f.add(new Character('\u0B5C')); // (2908) X
      f.add(new Character('\u0B5D')); // (2909) X
      f.add(new Character('\u0B5F')); // (2911) X
      f.add(new Character('\u0B60')); // (2912) X
      f.add(new Character('\u0B61')); // (2913) X
      f.add(new Character('\u0B85')); // (2949) X
      f.add(new Character('\u0B86')); // (2950) X
      f.add(new Character('\u0B87')); // (2951) X
      f.add(new Character('\u0B88')); // (2952) X
      f.add(new Character('\u0B89')); // (2953) X
      f.add(new Character('\u0B8A')); // (2954) X
      f.add(new Character('\u0B8E')); // (2958) X
      f.add(new Character('\u0B8F')); // (2959) X
      f.add(new Character('\u0B90')); // (2960) X
      f.add(new Character('\u0B92')); // (2962) X
      f.add(new Character('\u0B93')); // (2963) X
      f.add(new Character('\u0B94')); // (2964) X
      f.add(new Character('\u0B95')); // (2965) X
      f.add(new Character('\u0B99')); // (2969) X
      f.add(new Character('\u0B9A')); // (2970) X
      f.add(new Character('\u0B9C')); // (2972) X
      f.add(new Character('\u0B9E')); // (2974) X
      f.add(new Character('\u0B9F')); // (2975) X
      f.add(new Character('\u0BA3')); // (2979) X
      f.add(new Character('\u0BA4')); // (2980) X
      f.add(new Character('\u0BA8')); // (2984) X
      f.add(new Character('\u0BA9')); // (2985) X
      f.add(new Character('\u0BAA')); // (2986) X
      f.add(new Character('\u0BAE')); // (2990) X
      f.add(new Character('\u0BAF')); // (2991) X
      f.add(new Character('\u0BB0')); // (2992) X
      f.add(new Character('\u0BB1')); // (2993) X
      f.add(new Character('\u0BB2')); // (2994) X
      f.add(new Character('\u0BB3')); // (2995) X
      f.add(new Character('\u0BB4')); // (2996) X
      f.add(new Character('\u0BB5')); // (2997) X
      f.add(new Character('\u0BB7')); // (2999) X
      f.add(new Character('\u0BB8')); // (3000) X
      f.add(new Character('\u0BB9')); // (3001) X
      f.add(new Character('\u0C05')); // (3077) X
      f.add(new Character('\u0C06')); // (3078) X
      f.add(new Character('\u0C07')); // (3079) X
      f.add(new Character('\u0C08')); // (3080) X
      f.add(new Character('\u0C09')); // (3081) X
      f.add(new Character('\u0C0A')); // (3082) X
      f.add(new Character('\u0C0B')); // (3083) X
      f.add(new Character('\u0C0C')); // (3084) X
      f.add(new Character('\u0C0E')); // (3086) X
      f.add(new Character('\u0C0F')); // (3087) X
      f.add(new Character('\u0C10')); // (3088) X
      f.add(new Character('\u0C12')); // (3090) X
      f.add(new Character('\u0C13')); // (3091) X
      f.add(new Character('\u0C14')); // (3092) X
      f.add(new Character('\u0C15')); // (3093) X
      f.add(new Character('\u0C16')); // (3094) X
      f.add(new Character('\u0C17')); // (3095) X
      f.add(new Character('\u0C18')); // (3096) X
      f.add(new Character('\u0C19')); // (3097) X
      f.add(new Character('\u0C1A')); // (3098) X
      f.add(new Character('\u0C1B')); // (3099) X
      f.add(new Character('\u0C1C')); // (3100) X
      f.add(new Character('\u0C1D')); // (3101) X
      f.add(new Character('\u0C1E')); // (3102) X
      f.add(new Character('\u0C1F')); // (3103) X
      f.add(new Character('\u0C20')); // (3104) X
      f.add(new Character('\u0C21')); // (3105) X
      f.add(new Character('\u0C22')); // (3106) X
      f.add(new Character('\u0C23')); // (3107) X
      f.add(new Character('\u0C24')); // (3108) X
      f.add(new Character('\u0C25')); // (3109) X
      f.add(new Character('\u0C26')); // (3110) X
      f.add(new Character('\u0C27')); // (3111) X
      f.add(new Character('\u0C28')); // (3112) X
      f.add(new Character('\u0C2A')); // (3114) X
      f.add(new Character('\u0C2B')); // (3115) X
      f.add(new Character('\u0C2C')); // (3116) X
      f.add(new Character('\u0C2D')); // (3117) X
      f.add(new Character('\u0C2E')); // (3118) X
      f.add(new Character('\u0C2F')); // (3119) X
      f.add(new Character('\u0C30')); // (3120) X
      f.add(new Character('\u0C31')); // (3121) X
      f.add(new Character('\u0C32')); // (3122) X
      f.add(new Character('\u0C33')); // (3123) X
      f.add(new Character('\u0C35')); // (3125) X
      f.add(new Character('\u0C36')); // (3126) X
      f.add(new Character('\u0C37')); // (3127) X
      f.add(new Character('\u0C38')); // (3128) X
      f.add(new Character('\u0C39')); // (3129) X
      f.add(new Character('\u0C60')); // (3168) X
      f.add(new Character('\u0C61')); // (3169) X
      f.add(new Character('\u0C85')); // (3205) X
      f.add(new Character('\u0C86')); // (3206) X
      f.add(new Character('\u0C87')); // (3207) X
      f.add(new Character('\u0C88')); // (3208) X
      f.add(new Character('\u0C89')); // (3209) X
      f.add(new Character('\u0C8A')); // (3210) X
      f.add(new Character('\u0C8B')); // (3211) X
      f.add(new Character('\u0C8C')); // (3212) X
      f.add(new Character('\u0C8E')); // (3214) X
      f.add(new Character('\u0C8F')); // (3215) X
      f.add(new Character('\u0C90')); // (3216) X
      f.add(new Character('\u0C92')); // (3218) X
      f.add(new Character('\u0C93')); // (3219) X
      f.add(new Character('\u0C94')); // (3220) X
      f.add(new Character('\u0C95')); // (3221) X
      f.add(new Character('\u0C96')); // (3222) X
      f.add(new Character('\u0C97')); // (3223) X
      f.add(new Character('\u0C98')); // (3224) X
      f.add(new Character('\u0C99')); // (3225) X
      f.add(new Character('\u0C9A')); // (3226) X
      f.add(new Character('\u0C9B')); // (3227) X
      f.add(new Character('\u0C9C')); // (3228) X
      f.add(new Character('\u0C9D')); // (3229) X
      f.add(new Character('\u0C9E')); // (3230) X
      f.add(new Character('\u0C9F')); // (3231) X
      f.add(new Character('\u0CA0')); // (3232) X
      f.add(new Character('\u0CA1')); // (3233) X
      f.add(new Character('\u0CA2')); // (3234) X
      f.add(new Character('\u0CA3')); // (3235) X
      f.add(new Character('\u0CA4')); // (3236) X
      f.add(new Character('\u0CA5')); // (3237) X
      f.add(new Character('\u0CA6')); // (3238) X
      f.add(new Character('\u0CA7')); // (3239) X
      f.add(new Character('\u0CA8')); // (3240) X
      f.add(new Character('\u0CAA')); // (3242) X
      f.add(new Character('\u0CAB')); // (3243) X
      f.add(new Character('\u0CAC')); // (3244) X
      f.add(new Character('\u0CAD')); // (3245) X
      f.add(new Character('\u0CAE')); // (3246) X
      f.add(new Character('\u0CAF')); // (3247) X
      f.add(new Character('\u0CB0')); // (3248) X
      f.add(new Character('\u0CB1')); // (3249) X
      f.add(new Character('\u0CB2')); // (3250) X
      f.add(new Character('\u0CB3')); // (3251) X
      f.add(new Character('\u0CB5')); // (3253) X
      f.add(new Character('\u0CB6')); // (3254) X
      f.add(new Character('\u0CB7')); // (3255) X
      f.add(new Character('\u0CB8')); // (3256) X
      f.add(new Character('\u0CB9')); // (3257) X
      f.add(new Character('\u0CDE')); // (3294) X
      f.add(new Character('\u0CE0')); // (3296) X
      f.add(new Character('\u0CE1')); // (3297) X
      f.add(new Character('\u0D05')); // (3333) X
      f.add(new Character('\u0D06')); // (3334) X
      f.add(new Character('\u0D07')); // (3335) X
      f.add(new Character('\u0D08')); // (3336) X
      f.add(new Character('\u0D09')); // (3337) X
      f.add(new Character('\u0D0A')); // (3338) X
      f.add(new Character('\u0D0B')); // (3339) X
      f.add(new Character('\u0D0C')); // (3340) X
      f.add(new Character('\u0D0E')); // (3342) X
      f.add(new Character('\u0D0F')); // (3343) X
      f.add(new Character('\u0D10')); // (3344) X
      f.add(new Character('\u0D12')); // (3346) X
      f.add(new Character('\u0D13')); // (3347) X
      f.add(new Character('\u0D14')); // (3348) X
      f.add(new Character('\u0D15')); // (3349) X
      f.add(new Character('\u0D16')); // (3350) X
      f.add(new Character('\u0D17')); // (3351) X
      f.add(new Character('\u0D18')); // (3352) X
      f.add(new Character('\u0D19')); // (3353) X
      f.add(new Character('\u0D1A')); // (3354) X
      f.add(new Character('\u0D1B')); // (3355) X
      f.add(new Character('\u0D1C')); // (3356) X
      f.add(new Character('\u0D1D')); // (3357) X
      f.add(new Character('\u0D1E')); // (3358) X
      f.add(new Character('\u0D1F')); // (3359) X
      f.add(new Character('\u0D20')); // (3360) X
      f.add(new Character('\u0D21')); // (3361) X
      f.add(new Character('\u0D22')); // (3362) X
      f.add(new Character('\u0D23')); // (3363) X
      f.add(new Character('\u0D24')); // (3364) X
      f.add(new Character('\u0D25')); // (3365) X
      f.add(new Character('\u0D26')); // (3366) X
      f.add(new Character('\u0D27')); // (3367) X
      f.add(new Character('\u0D28')); // (3368) X
      f.add(new Character('\u0D2A')); // (3370) X
      f.add(new Character('\u0D2B')); // (3371) X
      f.add(new Character('\u0D2C')); // (3372) X
      f.add(new Character('\u0D2D')); // (3373) X
      f.add(new Character('\u0D2E')); // (3374) X
      f.add(new Character('\u0D2F')); // (3375) X
      f.add(new Character('\u0D30')); // (3376) X
      f.add(new Character('\u0D31')); // (3377) X
      f.add(new Character('\u0D32')); // (3378) X
      f.add(new Character('\u0D33')); // (3379) X
      f.add(new Character('\u0D34')); // (3380) X
      f.add(new Character('\u0D35')); // (3381) X
      f.add(new Character('\u0D36')); // (3382) X
      f.add(new Character('\u0D37')); // (3383) X
      f.add(new Character('\u0D38')); // (3384) X
      f.add(new Character('\u0D39')); // (3385) X
      f.add(new Character('\u0D60')); // (3424) X
      f.add(new Character('\u0D61')); // (3425) X
      f.add(new Character('\u0E01')); // (3585) X
      f.add(new Character('\u0E02')); // (3586) X
      f.add(new Character('\u0E03')); // (3587) X
      f.add(new Character('\u0E04')); // (3588) X
      f.add(new Character('\u0E05')); // (3589) X
      f.add(new Character('\u0E06')); // (3590) X
      f.add(new Character('\u0E07')); // (3591) X
      f.add(new Character('\u0E08')); // (3592) X
      f.add(new Character('\u0E09')); // (3593) X
      f.add(new Character('\u0E0A')); // (3594) X
      f.add(new Character('\u0E0B')); // (3595) X
      f.add(new Character('\u0E0C')); // (3596) X
      f.add(new Character('\u0E0D')); // (3597) X
      f.add(new Character('\u0E0E')); // (3598) X
      f.add(new Character('\u0E0F')); // (3599) X
      f.add(new Character('\u0E10')); // (3600) X
      f.add(new Character('\u0E11')); // (3601) X
      f.add(new Character('\u0E12')); // (3602) X
      f.add(new Character('\u0E13')); // (3603) X
      f.add(new Character('\u0E14')); // (3604) X
      f.add(new Character('\u0E15')); // (3605) X
      f.add(new Character('\u0E16')); // (3606) X
      f.add(new Character('\u0E17')); // (3607) X
      f.add(new Character('\u0E18')); // (3608) X
      f.add(new Character('\u0E19')); // (3609) X
      f.add(new Character('\u0E1A')); // (3610) X
      f.add(new Character('\u0E1B')); // (3611) X
      f.add(new Character('\u0E1C')); // (3612) X
      f.add(new Character('\u0E1D')); // (3613) X
      f.add(new Character('\u0E1E')); // (3614) X
      f.add(new Character('\u0E1F')); // (3615) X
      f.add(new Character('\u0E20')); // (3616) X
      f.add(new Character('\u0E21')); // (3617) X
      f.add(new Character('\u0E22')); // (3618) X
      f.add(new Character('\u0E23')); // (3619) X
      f.add(new Character('\u0E24')); // (3620) X
      f.add(new Character('\u0E25')); // (3621) X
      f.add(new Character('\u0E26')); // (3622) X
      f.add(new Character('\u0E27')); // (3623) X
      f.add(new Character('\u0E28')); // (3624) X
      f.add(new Character('\u0E29')); // (3625) X
      f.add(new Character('\u0E2A')); // (3626) X
      f.add(new Character('\u0E2B')); // (3627) X
      f.add(new Character('\u0E2C')); // (3628) X
      f.add(new Character('\u0E2D')); // (3629) X
      f.add(new Character('\u0E2E')); // (3630) X
      f.add(new Character('\u0E30')); // (3632) X
      f.add(new Character('\u0E32')); // (3634) X
      f.add(new Character('\u0E33')); // (3635) X
      f.add(new Character('\u0E40')); // (3648) X
      f.add(new Character('\u0E41')); // (3649) X
      f.add(new Character('\u0E42')); // (3650) X
      f.add(new Character('\u0E43')); // (3651) X
      f.add(new Character('\u0E44')); // (3652) X
      f.add(new Character('\u0E45')); // (3653) X
      f.add(new Character('\u0E46')); // (3654) X
      f.add(new Character('\u0E81')); // (3713) X
      f.add(new Character('\u0E82')); // (3714) X
      f.add(new Character('\u0E84')); // (3716) X
      f.add(new Character('\u0E87')); // (3719) X
      f.add(new Character('\u0E88')); // (3720) X
      f.add(new Character('\u0E8A')); // (3722) X
      f.add(new Character('\u0E8D')); // (3725) X
      f.add(new Character('\u0E94')); // (3732) X
      f.add(new Character('\u0E95')); // (3733) X
      f.add(new Character('\u0E96')); // (3734) X
      f.add(new Character('\u0E97')); // (3735) X
      f.add(new Character('\u0E99')); // (3737) X
      f.add(new Character('\u0E9A')); // (3738) X
      f.add(new Character('\u0E9B')); // (3739) X
      f.add(new Character('\u0E9C')); // (3740) X
      f.add(new Character('\u0E9D')); // (3741) X
      f.add(new Character('\u0E9E')); // (3742) X
      f.add(new Character('\u0E9F')); // (3743) X
      f.add(new Character('\u0EA1')); // (3745) X
      f.add(new Character('\u0EA2')); // (3746) X
      f.add(new Character('\u0EA3')); // (3747) X
      f.add(new Character('\u0EA5')); // (3749) X
      f.add(new Character('\u0EA7')); // (3751) X
      f.add(new Character('\u0EAA')); // (3754) X
      f.add(new Character('\u0EAB')); // (3755) X
      f.add(new Character('\u0EAD')); // (3757) X
      f.add(new Character('\u0EAE')); // (3758) X
      f.add(new Character('\u0EB0')); // (3760) X
      f.add(new Character('\u0EB2')); // (3762) X
      f.add(new Character('\u0EB3')); // (3763) X
      f.add(new Character('\u0EBD')); // (3773) X
      f.add(new Character('\u0EC0')); // (3776) X
      f.add(new Character('\u0EC1')); // (3777) X
      f.add(new Character('\u0EC2')); // (3778) X
      f.add(new Character('\u0EC3')); // (3779) X
      f.add(new Character('\u0EC4')); // (3780) X
      f.add(new Character('\u0EC6')); // (3782) X
      f.add(new Character('\u0EDC')); // (3804) X
      f.add(new Character('\u0EDD')); // (3805) X
      f.add(new Character('\u0F40')); // (3904) X
      f.add(new Character('\u0F41')); // (3905) X
      f.add(new Character('\u0F42')); // (3906) X
      f.add(new Character('\u0F43')); // (3907) X
      f.add(new Character('\u0F44')); // (3908) X
      f.add(new Character('\u0F45')); // (3909) X
      f.add(new Character('\u0F46')); // (3910) X
      f.add(new Character('\u0F47')); // (3911) X
      f.add(new Character('\u0F49')); // (3913) X
      f.add(new Character('\u0F4A')); // (3914) X
      f.add(new Character('\u0F4B')); // (3915) X
      f.add(new Character('\u0F4C')); // (3916) X
      f.add(new Character('\u0F4D')); // (3917) X
      f.add(new Character('\u0F4E')); // (3918) X
      f.add(new Character('\u0F4F')); // (3919) X
      f.add(new Character('\u0F50')); // (3920) X
      f.add(new Character('\u0F51')); // (3921) X
      f.add(new Character('\u0F52')); // (3922) X
      f.add(new Character('\u0F53')); // (3923) X
      f.add(new Character('\u0F54')); // (3924) X
      f.add(new Character('\u0F55')); // (3925) X
      f.add(new Character('\u0F56')); // (3926) X
      f.add(new Character('\u0F57')); // (3927) X
      f.add(new Character('\u0F58')); // (3928) X
      f.add(new Character('\u0F59')); // (3929) X
      f.add(new Character('\u0F5A')); // (3930) X
      f.add(new Character('\u0F5B')); // (3931) X
      f.add(new Character('\u0F5C')); // (3932) X
      f.add(new Character('\u0F5D')); // (3933) X
      f.add(new Character('\u0F5E')); // (3934) X
      f.add(new Character('\u0F5F')); // (3935) X
      f.add(new Character('\u0F60')); // (3936) X
      f.add(new Character('\u0F61')); // (3937) X
      f.add(new Character('\u0F62')); // (3938) X
      f.add(new Character('\u0F63')); // (3939) X
      f.add(new Character('\u0F64')); // (3940) X
      f.add(new Character('\u0F65')); // (3941) X
      f.add(new Character('\u0F66')); // (3942) X
      f.add(new Character('\u0F67')); // (3943) X
      f.add(new Character('\u0F68')); // (3944) X
      f.add(new Character('\u0F69')); // (3945) X

      return f;
   }

   /**
    * Initializes ordinary characters for iSeries.
    * Has a side-effect of adding the HashSet to ordinaryFlagsSet.
    * @return The HashSet
    */
   protected static HashSet<Character> initOrdinaryFlags400()
   {
      // For 400:
      HashSet<Character> f = new HashSet<Character>(349);
      ordinaryFlagsSet.put(PLATFORM_400_KEY, f);
      f.add(new Character('\u0023')); // (35) #
      f.add(new Character('\u0024')); // (36) $
      f.add(new Character('\u0040')); // (64) @
      initCommonOrdinaryFlags(f);
      return f;
   }

   /**
    * Initializes ordinary characters for zSeries.
    * Has a side-effect of adding the HashSet to ordinaryFlagsSet.
    * @return The HashSet
    */
   protected static HashSet<Character> initOrdinaryFlags390()
   {
      // For 390:
      HashSet<Character> f = new HashSet<Character>(349);
      ordinaryFlagsSet.put(PLATFORM_390_KEY, f);
      f.add(new Character('\u0023')); // (35) #
      f.add(new Character('\u0024')); // (36) $
      f.add(new Character('\u0040')); // (64) @
      initCommonOrdinaryFlags(f);
      return f;
   }

   /**
    * Initializes ordinary characters for LUWO for all languages.
    * @param f The HashSet
    */
   protected static void initCommonOrdinaryFlags(HashSet<Character> f)
   {
      f.add(new Character('\u0041')); // (   65) A
      f.add(new Character('\u0042')); // (   66) B
      f.add(new Character('\u0043')); // (   67) C
      f.add(new Character('\u0044')); // (   68) D
      f.add(new Character('\u0045')); // (   69) E
      f.add(new Character('\u0046')); // (   70) F
      f.add(new Character('\u0047')); // (   71) G
      f.add(new Character('\u0048')); // (   72) H
      f.add(new Character('\u0049')); // (   73) I
      f.add(new Character('\u004A')); // (   74) J
      f.add(new Character('\u004B')); // (   75) K
      f.add(new Character('\u004C')); // (   76) L
      f.add(new Character('\u004D')); // (   77) M
      f.add(new Character('\u004E')); // (   78) N
      f.add(new Character('\u004F')); // (   79) O
      f.add(new Character('\u0050')); // (   80) P
      f.add(new Character('\u0051')); // (   81) Q
      f.add(new Character('\u0052')); // (   82) R
      f.add(new Character('\u0053')); // (   83) S
      f.add(new Character('\u0054')); // (   84) T
      f.add(new Character('\u0055')); // (   85) U
      f.add(new Character('\u0056')); // (   86) V
      f.add(new Character('\u0057')); // (   87) W
      f.add(new Character('\u0058')); // (   88) X
      f.add(new Character('\u0059')); // (   89) Y
      f.add(new Character('\u005A')); // (   90) Z
      f.add(new Character('\u0061')); // (   97) a
      f.add(new Character('\u0062')); // (   98) b
      f.add(new Character('\u0063')); // (   99) c
      f.add(new Character('\u0064')); // (  100) d
      f.add(new Character('\u0065')); // (  101) e
      f.add(new Character('\u0066')); // (  102) f
      f.add(new Character('\u0067')); // (  103) g
      f.add(new Character('\u0068')); // (  104) h
      f.add(new Character('\u0069')); // (  105) i
      f.add(new Character('\u006A')); // (  106) j
      f.add(new Character('\u006B')); // (  107) k
      f.add(new Character('\u006C')); // (  108) l
      f.add(new Character('\u006D')); // (  109) m
      f.add(new Character('\u006E')); // (  110) n
      f.add(new Character('\u006F')); // (  111) o
      f.add(new Character('\u0070')); // (  112) p
      f.add(new Character('\u0071')); // (  113) q
      f.add(new Character('\u0072')); // (  114) r
      f.add(new Character('\u0073')); // (  115) s
      f.add(new Character('\u0074')); // (  116) t
      f.add(new Character('\u0075')); // (  117) u
      f.add(new Character('\u0076')); // (  118) v
      f.add(new Character('\u0077')); // (  119) w
      f.add(new Character('\u0078')); // (  120) x
      f.add(new Character('\u0079')); // (  121) y
      f.add(new Character('\u007A')); // (  122) z
   }

   /**
    * Initializes ordinary characters for LUWO for a given language.
    * @param f The HashSet
    * @param locale The locale of the database.
    */
   protected static void initOrdinaryFlagsLUWO(HashSet<Character> f, Locale locale)
   {
      // Get the code page of the database and arrange the init methods
      // according to code page; check Linux, AIX, etc.
      // Default to anything goes?
      String lang = locale.getLanguage();
      if (lang.equalsIgnoreCase("cs")) //$NON-NLS-1$
      {
         initCSFlags(f);
         init_EU_Flags(f);
      }
      else if (lang.equalsIgnoreCase("de")) //$NON-NLS-1$
      {
         initDEFlags(f);
         init_EU_Flags(f);
      }
      else if (lang.equalsIgnoreCase("es")) //$NON-NLS-1$
      {
         initESFlags(f);
         init_EU_Flags(f);
      }
      else if (lang.equalsIgnoreCase("fr")) //$NON-NLS-1$
      {
         initFRFlags(f);
         init_EU_Flags(f);
      }
      else if (lang.equalsIgnoreCase("hu")) //$NON-NLS-1$
      {
         initHUFlags(f);
         init_EU_Flags(f);
      }
      else if (lang.equalsIgnoreCase("it")) //$NON-NLS-1$
      {
         initITFlags(f);
         init_EU_Flags(f);
      }
      else if (lang.equalsIgnoreCase("ja")) //$NON-NLS-1$
      {
         initJAFlags(f);
      }
      else if (lang.equalsIgnoreCase("ko")) //$NON-NLS-1$
      {
         initKOFlags(f);
      }
      else if (lang.equalsIgnoreCase("pl")) //$NON-NLS-1$
      {
         initPLFlags(f);
         init_EU_Flags(f);
      }
      else if (lang.equalsIgnoreCase("pt")) //$NON-NLS-1$
      {
         initPTFlags(f);
         init_EU_Flags(f);
      }
      else if (lang.equalsIgnoreCase("ru")) //$NON-NLS-1$
      {
         initRUFlags(f);
         init_EU_Flags(f);
      }
      else if (lang.equalsIgnoreCase("zh")) //$NON-NLS-1$
      {
         String country = locale.getCountry();
         if (country.equalsIgnoreCase("TW")) //$NON-NLS-1$
         {
            initTWFlags(f);
         }
         else
         {
            initZHFlags(f);
         }
      }
      else //if (lang.equalsIgnoreCase("en"))
      {
         initENFlags(f);
         init_EU_Flags(f);
      }
   }

   /**
    * Initializes ordinary characters for LUWO for the EN language.
    * @param f The HashSet
    */
   protected static void initENFlags(HashSet<Character> f)
   {
      f.add(new Character('\u00AA')); // (  170) feminine_ordinal
      f.add(new Character('\u00BA')); // (  186) masculine_ordinal
      f.add(new Character('\u00C0')); // (  192) A_grave
      f.add(new Character('\u00C1')); // (  193) A_acute
      f.add(new Character('\u00C2')); // (  194) A_circumflex
      f.add(new Character('\u00C3')); // (  195) A_tilde
      f.add(new Character('\u00C4')); // (  196) A_umlaut
      f.add(new Character('\u00C5')); // (  197) A_ring
      f.add(new Character('\u00C6')); // (  198) AE_ligature
      f.add(new Character('\u00C7')); // (  199) C_cedilla
      f.add(new Character('\u00C8')); // (  200) E_grave
      f.add(new Character('\u00C9')); // (  201) E_acute
      f.add(new Character('\u00CA')); // (  202) E_circumflex
      f.add(new Character('\u00CB')); // (  203) E_umlaut
      f.add(new Character('\u00CC')); // (  204) I_grave
      f.add(new Character('\u00CD')); // (  205) I_acute
      f.add(new Character('\u00CE')); // (  206) I_circumflex
      f.add(new Character('\u00CF')); // (  207) I_umlaut
      f.add(new Character('\u00D0')); // (  208) ETH_Q
      f.add(new Character('\u00D1')); // (  209) N_tilde
      f.add(new Character('\u00D2')); // (  210) O_grave
      f.add(new Character('\u00D3')); // (  211) O_acute
      f.add(new Character('\u00D4')); // (  212) O_circumflex
      f.add(new Character('\u00D5')); // (  213) O_tilde
      f.add(new Character('\u00D6')); // (  214) O_umlaut
      f.add(new Character('\u00D8')); // (  216) O_slash
      f.add(new Character('\u00D9')); // (  217) U_grave
      f.add(new Character('\u00DA')); // (  218) U_acute
      f.add(new Character('\u00DB')); // (  219) U_circumflex
      f.add(new Character('\u00DC')); // (  220) U_umlaut
      f.add(new Character('\u00DD')); // (  221) Y_acute
      f.add(new Character('\u00DE')); // (  222) THORN
      f.add(new Character('\u00DF')); // (  223) sharp_s
      f.add(new Character('\u00E0')); // (  224) a_grave
      f.add(new Character('\u00E1')); // (  225) a_acute
      f.add(new Character('\u00E2')); // (  226) a_circumflex
      f.add(new Character('\u00E3')); // (  227) a_tilde
      f.add(new Character('\u00E4')); // (  228) a_umlaut
      f.add(new Character('\u00E5')); // (  229) a_ring
      f.add(new Character('\u00E6')); // (  230) ae_ligature
      f.add(new Character('\u00E7')); // (  231) c_cedilla
      f.add(new Character('\u00E8')); // (  232) e_grave
      f.add(new Character('\u00E9')); // (  233) e_acute
      f.add(new Character('\u00EA')); // (  234) e_circumflex
      f.add(new Character('\u00EB')); // (  235) e_umlaut
      f.add(new Character('\u00EC')); // (  236) i_grave
      f.add(new Character('\u00ED')); // (  237) i_acute
      f.add(new Character('\u00EE')); // (  238) i_circumflex
      f.add(new Character('\u00EF')); // (  239) i_umlaut
      f.add(new Character('\u00F0')); // (  240) eth
      f.add(new Character('\u00F1')); // (  241) n_tilde
      f.add(new Character('\u00F2')); // (  242) o_grave
      f.add(new Character('\u00F3')); // (  243) o_acute
      f.add(new Character('\u00F4')); // (  244) o_circumflex
      f.add(new Character('\u00F5')); // (  245) o_tilde
      f.add(new Character('\u00F6')); // (  246) o_umlaut
      f.add(new Character('\u00F8')); // (  248) o_slash
      f.add(new Character('\u00F9')); // (  249) u_grave
      f.add(new Character('\u00FA')); // (  250) u_acute
      f.add(new Character('\u00FB')); // (  251) u_circumflex
      f.add(new Character('\u00FC')); // (  252) u_umlaut
      f.add(new Character('\u00FD')); // (  253) y_acute
      f.add(new Character('\u00FE')); // (  254) thorn
      f.add(new Character('\u00FF')); // (  255) y_umlaut
      f.add(new Character('\u0152')); // (  338) OE_ligature
      f.add(new Character('\u0153')); // (  339) oe_ligature
      f.add(new Character('\u0160')); // (  352) S_caron
      f.add(new Character('\u0161')); // (  353) s_caron
      f.add(new Character('\u0178')); // (  376) Y_diaeresis
   }

   /**
    * Initializes ordinary characters for LUWO for the ZH language.
    * @param f The HashSet
    */
   protected static void init_EU_Flags(HashSet<Character> f)
   {
      f.add(new Character('\uFF03')); // (65283) X
      f.add(new Character('\uFF04')); // (65284) X
      f.add(new Character('\uFF20')); // (65312) X
      f.add(new Character('\uFF21')); // (65313) X
      f.add(new Character('\uFF22')); // (65314) X
      f.add(new Character('\uFF23')); // (65315) X
      f.add(new Character('\uFF24')); // (65316) X
      f.add(new Character('\uFF25')); // (65317) X
      f.add(new Character('\uFF26')); // (65318) X
      f.add(new Character('\uFF27')); // (65319) X
      f.add(new Character('\uFF28')); // (65320) X
      f.add(new Character('\uFF29')); // (65321) X
      f.add(new Character('\uFF2A')); // (65322) X
      f.add(new Character('\uFF2B')); // (65323) X
      f.add(new Character('\uFF2C')); // (65324) X
      f.add(new Character('\uFF2D')); // (65325) X
      f.add(new Character('\uFF2E')); // (65326) X
      f.add(new Character('\uFF2F')); // (65327) X
      f.add(new Character('\uFF30')); // (65328) X
      f.add(new Character('\uFF31')); // (65329) X
      f.add(new Character('\uFF32')); // (65330) X
      f.add(new Character('\uFF33')); // (65331) X
      f.add(new Character('\uFF34')); // (65332) X
      f.add(new Character('\uFF35')); // (65333) X
      f.add(new Character('\uFF36')); // (65334) X
      f.add(new Character('\uFF37')); // (65335) X
      f.add(new Character('\uFF38')); // (65336) X
      f.add(new Character('\uFF39')); // (65337) X
      f.add(new Character('\uFF3A')); // (65338) X
      f.add(new Character('\uFF41')); // (65345) X
      f.add(new Character('\uFF42')); // (65346) X
      f.add(new Character('\uFF43')); // (65347) X
      f.add(new Character('\uFF44')); // (65348) X
      f.add(new Character('\uFF45')); // (65349) X
      f.add(new Character('\uFF46')); // (65350) X
      f.add(new Character('\uFF47')); // (65351) X
      f.add(new Character('\uFF48')); // (65352) X
      f.add(new Character('\uFF49')); // (65353) X
      f.add(new Character('\uFF4A')); // (65354) X
      f.add(new Character('\uFF4B')); // (65355) X
      f.add(new Character('\uFF4C')); // (65356) X
      f.add(new Character('\uFF4D')); // (65357) X
      f.add(new Character('\uFF4E')); // (65358) X
      f.add(new Character('\uFF4F')); // (65359) X
      f.add(new Character('\uFF50')); // (65360) X
      f.add(new Character('\uFF51')); // (65361) X
      f.add(new Character('\uFF52')); // (65362) X
      f.add(new Character('\uFF53')); // (65363) X
      f.add(new Character('\uFF54')); // (65364) X
      f.add(new Character('\uFF55')); // (65365) X
      f.add(new Character('\uFF56')); // (65366) X
      f.add(new Character('\uFF57')); // (65367) X
      f.add(new Character('\uFF58')); // (65368) X
      f.add(new Character('\uFF59')); // (65369) X
      f.add(new Character('\uFF5A')); // (65370) X
   }

   /**
    * Initializes ordinary characters for LUWO for the ZH language.
    * @param f The HashSet
    */
   protected static void initZHFlags(HashSet<Character> f)
   {
      f.add(new Character('\u00A4')); // (  164) currency
      f.add(new Character('\u00A7')); // (  167) SECTION
      f.add(new Character('\u00A8')); // (  168) diaeresis
      f.add(new Character('\u00B0')); // (  176) degree
      f.add(new Character('\u00B1')); // (  177) plus_minus
      f.add(new Character('\u00B7')); // (  183) middot
      f.add(new Character('\u00D7')); // (  215) times
      f.add(new Character('\u00E0')); // (  224) a_grave
      f.add(new Character('\u00E1')); // (  225) a_acute
      f.add(new Character('\u00E8')); // (  232) e_grave
      f.add(new Character('\u00E9')); // (  233) e_acute
      f.add(new Character('\u00EA')); // (  234) e_circumflex
      f.add(new Character('\u00EC')); // (  236) i_grave
      f.add(new Character('\u00ED')); // (  237) i_acute
      f.add(new Character('\u00F2')); // (  242) o_grave
      f.add(new Character('\u00F3')); // (  243) o_acute
      f.add(new Character('\u00F9')); // (  249) u_grave
      f.add(new Character('\u00FA')); // (  250) u_acute
      f.add(new Character('\u00FC')); // (  252) u_umlaut
      f.add(new Character('\u0101')); // (  257) a_bar
      f.add(new Character('\u0113')); // (  275) e_macron
      f.add(new Character('\u011B')); // (  283) e_caron
      f.add(new Character('\u012B')); // (  299) i_macron
      f.add(new Character('\u0144')); // (  324) n_acute
      f.add(new Character('\u0148')); // (  328) n_caron
      f.add(new Character('\u014D')); // (  333) o_macron
      f.add(new Character('\u016B')); // (  363) u_macron
      f.add(new Character('\u01CE')); // (  462) X
      f.add(new Character('\u01D0')); // (  464) X
      f.add(new Character('\u01D2')); // (  466) X
      f.add(new Character('\u01D4')); // (  468) X
      f.add(new Character('\u01D6')); // (  470) X
      f.add(new Character('\u01D8')); // (  472) X
      f.add(new Character('\u01DA')); // (  474) X
      f.add(new Character('\u01DC')); // (  476) X
      f.add(new Character('\u01F9')); // (  505) X
      f.add(new Character('\u0251')); // (  593) X
      f.add(new Character('\u0261')); // (  609) X
      f.add(new Character('\u02C7')); // (  711) X
      f.add(new Character('\u02C9')); // (  713) X
      f.add(new Character('\u02CA')); // (  714) X
      f.add(new Character('\u02CB')); // (  715) X
      f.add(new Character('\u02D9')); // (  729) X
      f.add(new Character('\u0391')); // (  913) X
      f.add(new Character('\u0392')); // (  914) X
      f.add(new Character('\u0393')); // (  915) X
      f.add(new Character('\u0394')); // (  916) X
      f.add(new Character('\u0395')); // (  917) X
      f.add(new Character('\u0396')); // (  918) X
      f.add(new Character('\u0397')); // (  919) X
      f.add(new Character('\u0398')); // (  920) X
      f.add(new Character('\u0399')); // (  921) X
      f.add(new Character('\u039A')); // (  922) X
      f.add(new Character('\u039B')); // (  923) X
      f.add(new Character('\u039C')); // (  924) X
      f.add(new Character('\u039D')); // (  925) X
      f.add(new Character('\u039E')); // (  926) X
      f.add(new Character('\u039F')); // (  927) X
      f.add(new Character('\u03A0')); // (  928) X
      f.add(new Character('\u03A1')); // (  929) X
      f.add(new Character('\u03A3')); // (  931) X
      f.add(new Character('\u03A4')); // (  932) X
      f.add(new Character('\u03A5')); // (  933) X
      f.add(new Character('\u03A6')); // (  934) X
      f.add(new Character('\u03A7')); // (  935) X
      f.add(new Character('\u03A8')); // (  936) X
      f.add(new Character('\u03A9')); // (  937) X
      f.add(new Character('\u03B1')); // (  945) X
      f.add(new Character('\u03B2')); // (  946) X
      f.add(new Character('\u03B3')); // (  947) X
      f.add(new Character('\u03B4')); // (  948) X
      f.add(new Character('\u03B5')); // (  949) X
      f.add(new Character('\u03B6')); // (  950) X
      f.add(new Character('\u03B7')); // (  951) X
      f.add(new Character('\u03B8')); // (  952) X
      f.add(new Character('\u03B9')); // (  953) X
      f.add(new Character('\u03BA')); // (  954) X
      f.add(new Character('\u03BB')); // (  955) X
      f.add(new Character('\u03BC')); // (  956) X
      f.add(new Character('\u03BD')); // (  957) X
      f.add(new Character('\u03BE')); // (  958) X
      f.add(new Character('\u03BF')); // (  959) X
      f.add(new Character('\u03C0')); // (  960) X
      f.add(new Character('\u03C1')); // (  961) X
      f.add(new Character('\u03C3')); // (  963) X
      f.add(new Character('\u03C4')); // (  964) X
      f.add(new Character('\u03C5')); // (  965) X
      f.add(new Character('\u03C6')); // (  966) X
      f.add(new Character('\u03C7')); // (  967) X
      f.add(new Character('\u03C8')); // (  968) X
      f.add(new Character('\u03C9')); // (  969) X
      f.add(new Character('\u0401')); // ( 1025) X
      f.add(new Character('\u0410')); // ( 1040) X
      f.add(new Character('\u0411')); // ( 1041) X
      f.add(new Character('\u0412')); // ( 1042) X
      f.add(new Character('\u0413')); // ( 1043) X
      f.add(new Character('\u0414')); // ( 1044) X
      f.add(new Character('\u0415')); // ( 1045) X
      f.add(new Character('\u0416')); // ( 1046) X
      f.add(new Character('\u0417')); // ( 1047) X
      f.add(new Character('\u0418')); // ( 1048) X
      f.add(new Character('\u0419')); // ( 1049) X
      f.add(new Character('\u041A')); // ( 1050) X
      f.add(new Character('\u041B')); // ( 1051) X
      f.add(new Character('\u041C')); // ( 1052) X
      f.add(new Character('\u041D')); // ( 1053) X
      f.add(new Character('\u041E')); // ( 1054) X
      f.add(new Character('\u041F')); // ( 1055) X
      f.add(new Character('\u0420')); // ( 1056) X
      f.add(new Character('\u0421')); // ( 1057) X
      f.add(new Character('\u0422')); // ( 1058) X
      f.add(new Character('\u0423')); // ( 1059) X
      f.add(new Character('\u0424')); // ( 1060) X
      f.add(new Character('\u0425')); // ( 1061) X
      f.add(new Character('\u0426')); // ( 1062) X
      f.add(new Character('\u0427')); // ( 1063) X
      f.add(new Character('\u0428')); // ( 1064) X
      f.add(new Character('\u0429')); // ( 1065) X
      f.add(new Character('\u042A')); // ( 1066) X
      f.add(new Character('\u042B')); // ( 1067) X
      f.add(new Character('\u042C')); // ( 1068) X
      f.add(new Character('\u042D')); // ( 1069) X
      f.add(new Character('\u042E')); // ( 1070) X
      f.add(new Character('\u042F')); // ( 1071) X
      f.add(new Character('\u0430')); // ( 1072) X
      f.add(new Character('\u0431')); // ( 1073) X
      f.add(new Character('\u0432')); // ( 1074) X
      f.add(new Character('\u0433')); // ( 1075) X
      f.add(new Character('\u0434')); // ( 1076) X
      f.add(new Character('\u0435')); // ( 1077) X
      f.add(new Character('\u0436')); // ( 1078) X
      f.add(new Character('\u0437')); // ( 1079) X
      f.add(new Character('\u0438')); // ( 1080) X
      f.add(new Character('\u0439')); // ( 1081) X
      f.add(new Character('\u043A')); // ( 1082) X
      f.add(new Character('\u043B')); // ( 1083) X
      f.add(new Character('\u043C')); // ( 1084) X
      f.add(new Character('\u043D')); // ( 1085) X
      f.add(new Character('\u043E')); // ( 1086) X
      f.add(new Character('\u043F')); // ( 1087) X
      f.add(new Character('\u0440')); // ( 1088) X
      f.add(new Character('\u0441')); // ( 1089) X
      f.add(new Character('\u0442')); // ( 1090) X
      f.add(new Character('\u0443')); // ( 1091) X
      f.add(new Character('\u0444')); // ( 1092) X
      f.add(new Character('\u0445')); // ( 1093) X
      f.add(new Character('\u0446')); // ( 1094) X
      f.add(new Character('\u0447')); // ( 1095) X
      f.add(new Character('\u0448')); // ( 1096) X
      f.add(new Character('\u0449')); // ( 1097) X
      f.add(new Character('\u044A')); // ( 1098) X
      f.add(new Character('\u044B')); // ( 1099) X
      f.add(new Character('\u044C')); // ( 1100) X
      f.add(new Character('\u044D')); // ( 1101) X
      f.add(new Character('\u044E')); // ( 1102) X
      f.add(new Character('\u044F')); // ( 1103) X
      f.add(new Character('\u0451')); // ( 1105) X
      f.add(new Character('\u2010')); // ( 8208) X
      f.add(new Character('\u2013')); // ( 8211) X
      f.add(new Character('\u2014')); // ( 8212) X
      f.add(new Character('\u2015')); // ( 8213) X
      f.add(new Character('\u2016')); // ( 8214) X
      f.add(new Character('\u2018')); // ( 8216) X
      f.add(new Character('\u2019')); // ( 8217) X
      f.add(new Character('\u201C')); // ( 8220) X
      f.add(new Character('\u201D')); // ( 8221) X
      f.add(new Character('\u2025')); // ( 8229) X
      f.add(new Character('\u2026')); // ( 8230) X
      f.add(new Character('\u2030')); // ( 8240) X
      f.add(new Character('\u2032')); // ( 8242) X
      f.add(new Character('\u2033')); // ( 8243) X
      f.add(new Character('\u2035')); // ( 8245) X
      f.add(new Character('\u203B')); // ( 8251) X
      f.add(new Character('\u20AC')); // ( 8364) X
      f.add(new Character('\u2103')); // ( 8451) X
      f.add(new Character('\u2105')); // ( 8453) X
      f.add(new Character('\u2109')); // ( 8457) X
      f.add(new Character('\u2116')); // ( 8470) X
      f.add(new Character('\u2121')); // ( 8481) X
      f.add(new Character('\u2160')); // ( 8544) X
      f.add(new Character('\u2161')); // ( 8545) X
      f.add(new Character('\u2162')); // ( 8546) X
      f.add(new Character('\u2163')); // ( 8547) X
      f.add(new Character('\u2164')); // ( 8548) X
      f.add(new Character('\u2165')); // ( 8549) X
      f.add(new Character('\u2166')); // ( 8550) X
      f.add(new Character('\u2167')); // ( 8551) X
      f.add(new Character('\u2168')); // ( 8552) X
      f.add(new Character('\u2169')); // ( 8553) X
      f.add(new Character('\u216A')); // ( 8554) X
      f.add(new Character('\u216B')); // ( 8555) X
      f.add(new Character('\u2170')); // ( 8560) X
      f.add(new Character('\u2171')); // ( 8561) X
      f.add(new Character('\u2172')); // ( 8562) X
      f.add(new Character('\u2173')); // ( 8563) X
      f.add(new Character('\u2174')); // ( 8564) X
      f.add(new Character('\u2175')); // ( 8565) X
      f.add(new Character('\u2176')); // ( 8566) X
      f.add(new Character('\u2177')); // ( 8567) X
      f.add(new Character('\u2178')); // ( 8568) X
      f.add(new Character('\u2179')); // ( 8569) X
      f.add(new Character('\u2190')); // ( 8592) X
      f.add(new Character('\u2191')); // ( 8593) X
      f.add(new Character('\u2192')); // ( 8594) X
      f.add(new Character('\u2193')); // ( 8595) X
      f.add(new Character('\u2196')); // ( 8598) X
      f.add(new Character('\u2197')); // ( 8599) X
      f.add(new Character('\u2198')); // ( 8600) X
      f.add(new Character('\u2199')); // ( 8601) X
      f.add(new Character('\u2208')); // ( 8712) X
      f.add(new Character('\u220F')); // ( 8719) X
      f.add(new Character('\u2211')); // ( 8721) X
      f.add(new Character('\u2215')); // ( 8725) X
      f.add(new Character('\u221A')); // ( 8730) X
      f.add(new Character('\u221D')); // ( 8733) X
      f.add(new Character('\u221E')); // ( 8734) X
      f.add(new Character('\u221F')); // ( 8735) X
      f.add(new Character('\u2220')); // ( 8736) X
      f.add(new Character('\u2223')); // ( 8739) X
      f.add(new Character('\u2225')); // ( 8741) X
      f.add(new Character('\u2227')); // ( 8743) X
      f.add(new Character('\u2228')); // ( 8744) X
      f.add(new Character('\u2229')); // ( 8745) X
      f.add(new Character('\u222A')); // ( 8746) X
      f.add(new Character('\u222B')); // ( 8747) X
      f.add(new Character('\u222E')); // ( 8750) X
      f.add(new Character('\u2234')); // ( 8756) X
      f.add(new Character('\u2235')); // ( 8757) X
      f.add(new Character('\u2236')); // ( 8758) X
      f.add(new Character('\u2237')); // ( 8759) X
      f.add(new Character('\u223D')); // ( 8765) X
      f.add(new Character('\u2248')); // ( 8776) X
      f.add(new Character('\u224C')); // ( 8780) X
      f.add(new Character('\u2252')); // ( 8786) X
      f.add(new Character('\u2260')); // ( 8800) X
      f.add(new Character('\u2261')); // ( 8801) X
      f.add(new Character('\u2264')); // ( 8804) X
      f.add(new Character('\u2265')); // ( 8805) X
      f.add(new Character('\u2266')); // ( 8806) X
      f.add(new Character('\u2267')); // ( 8807) X
      f.add(new Character('\u226E')); // ( 8814) X
      f.add(new Character('\u226F')); // ( 8815) X
      f.add(new Character('\u2295')); // ( 8853) X
      f.add(new Character('\u2299')); // ( 8857) X
      f.add(new Character('\u22A5')); // ( 8869) X
      f.add(new Character('\u22BF')); // ( 8895) X
      f.add(new Character('\u2312')); // ( 8978) X
      f.add(new Character('\u2460')); // ( 9312) X
      f.add(new Character('\u2461')); // ( 9313) X
      f.add(new Character('\u2462')); // ( 9314) X
      f.add(new Character('\u2463')); // ( 9315) X
      f.add(new Character('\u2464')); // ( 9316) X
      f.add(new Character('\u2465')); // ( 9317) X
      f.add(new Character('\u2466')); // ( 9318) X
      f.add(new Character('\u2467')); // ( 9319) X
      f.add(new Character('\u2468')); // ( 9320) X
      f.add(new Character('\u2469')); // ( 9321) X
      f.add(new Character('\u2474')); // ( 9332) X
      f.add(new Character('\u2475')); // ( 9333) X
      f.add(new Character('\u2476')); // ( 9334) X
      f.add(new Character('\u2477')); // ( 9335) X
      f.add(new Character('\u2478')); // ( 9336) X
      f.add(new Character('\u2479')); // ( 9337) X
      f.add(new Character('\u247A')); // ( 9338) X
      f.add(new Character('\u247B')); // ( 9339) X
      f.add(new Character('\u247C')); // ( 9340) X
      f.add(new Character('\u247D')); // ( 9341) X
      f.add(new Character('\u247E')); // ( 9342) X
      f.add(new Character('\u247F')); // ( 9343) X
      f.add(new Character('\u2480')); // ( 9344) X
      f.add(new Character('\u2481')); // ( 9345) X
      f.add(new Character('\u2482')); // ( 9346) X
      f.add(new Character('\u2483')); // ( 9347) X
      f.add(new Character('\u2484')); // ( 9348) X
      f.add(new Character('\u2485')); // ( 9349) X
      f.add(new Character('\u2486')); // ( 9350) X
      f.add(new Character('\u2487')); // ( 9351) X
      f.add(new Character('\u2488')); // ( 9352) X
      f.add(new Character('\u2489')); // ( 9353) X
      f.add(new Character('\u248A')); // ( 9354) X
      f.add(new Character('\u248B')); // ( 9355) X
      f.add(new Character('\u248C')); // ( 9356) X
      f.add(new Character('\u248D')); // ( 9357) X
      f.add(new Character('\u248E')); // ( 9358) X
      f.add(new Character('\u248F')); // ( 9359) X
      f.add(new Character('\u2490')); // ( 9360) X
      f.add(new Character('\u2491')); // ( 9361) X
      f.add(new Character('\u2492')); // ( 9362) X
      f.add(new Character('\u2493')); // ( 9363) X
      f.add(new Character('\u2494')); // ( 9364) X
      f.add(new Character('\u2495')); // ( 9365) X
      f.add(new Character('\u2496')); // ( 9366) X
      f.add(new Character('\u2497')); // ( 9367) X
      f.add(new Character('\u2498')); // ( 9368) X
      f.add(new Character('\u2499')); // ( 9369) X
      f.add(new Character('\u249A')); // ( 9370) X
      f.add(new Character('\u249B')); // ( 9371) X
      f.add(new Character('\u2500')); // ( 9472) X
      f.add(new Character('\u2501')); // ( 9473) X
      f.add(new Character('\u2502')); // ( 9474) X
      f.add(new Character('\u2503')); // ( 9475) X
      f.add(new Character('\u2504')); // ( 9476) X
      f.add(new Character('\u2505')); // ( 9477) X
      f.add(new Character('\u2506')); // ( 9478) X
      f.add(new Character('\u2507')); // ( 9479) X
      f.add(new Character('\u2508')); // ( 9480) X
      f.add(new Character('\u2509')); // ( 9481) X
      f.add(new Character('\u250A')); // ( 9482) X
      f.add(new Character('\u250B')); // ( 9483) X
      f.add(new Character('\u250C')); // ( 9484) X
      f.add(new Character('\u250D')); // ( 9485) X
      f.add(new Character('\u250E')); // ( 9486) X
      f.add(new Character('\u250F')); // ( 9487) X
      f.add(new Character('\u2510')); // ( 9488) X
      f.add(new Character('\u2511')); // ( 9489) X
      f.add(new Character('\u2512')); // ( 9490) X
      f.add(new Character('\u2513')); // ( 9491) X
      f.add(new Character('\u2514')); // ( 9492) X
      f.add(new Character('\u2515')); // ( 9493) X
      f.add(new Character('\u2516')); // ( 9494) X
      f.add(new Character('\u2517')); // ( 9495) X
      f.add(new Character('\u2518')); // ( 9496) X
      f.add(new Character('\u2519')); // ( 9497) X
      f.add(new Character('\u251A')); // ( 9498) X
      f.add(new Character('\u251B')); // ( 9499) X
      f.add(new Character('\u251C')); // ( 9500) X
      f.add(new Character('\u251D')); // ( 9501) X
      f.add(new Character('\u251E')); // ( 9502) X
      f.add(new Character('\u251F')); // ( 9503) X
      f.add(new Character('\u2520')); // ( 9504) X
      f.add(new Character('\u2521')); // ( 9505) X
      f.add(new Character('\u2522')); // ( 9506) X
      f.add(new Character('\u2523')); // ( 9507) X
      f.add(new Character('\u2524')); // ( 9508) X
      f.add(new Character('\u2525')); // ( 9509) X
      f.add(new Character('\u2526')); // ( 9510) X
      f.add(new Character('\u2527')); // ( 9511) X
      f.add(new Character('\u2528')); // ( 9512) X
      f.add(new Character('\u2529')); // ( 9513) X
      f.add(new Character('\u252A')); // ( 9514) X
      f.add(new Character('\u252B')); // ( 9515) X
      f.add(new Character('\u252C')); // ( 9516) X
      f.add(new Character('\u252D')); // ( 9517) X
      f.add(new Character('\u252E')); // ( 9518) X
      f.add(new Character('\u252F')); // ( 9519) X
      f.add(new Character('\u2530')); // ( 9520) X
      f.add(new Character('\u2531')); // ( 9521) X
      f.add(new Character('\u2532')); // ( 9522) X
      f.add(new Character('\u2533')); // ( 9523) X
      f.add(new Character('\u2534')); // ( 9524) X
      f.add(new Character('\u2535')); // ( 9525) X
      f.add(new Character('\u2536')); // ( 9526) X
      f.add(new Character('\u2537')); // ( 9527) X
      f.add(new Character('\u2538')); // ( 9528) X
      f.add(new Character('\u2539')); // ( 9529) X
      f.add(new Character('\u253A')); // ( 9530) X
      f.add(new Character('\u253B')); // ( 9531) X
      f.add(new Character('\u253C')); // ( 9532) X
      f.add(new Character('\u253D')); // ( 9533) X
      f.add(new Character('\u253E')); // ( 9534) X
      f.add(new Character('\u253F')); // ( 9535) X
      f.add(new Character('\u2540')); // ( 9536) X
      f.add(new Character('\u2541')); // ( 9537) X
      f.add(new Character('\u2542')); // ( 9538) X
      f.add(new Character('\u2543')); // ( 9539) X
      f.add(new Character('\u2544')); // ( 9540) X
      f.add(new Character('\u2545')); // ( 9541) X
      f.add(new Character('\u2546')); // ( 9542) X
      f.add(new Character('\u2547')); // ( 9543) X
      f.add(new Character('\u2548')); // ( 9544) X
      f.add(new Character('\u2549')); // ( 9545) X
      f.add(new Character('\u254A')); // ( 9546) X
      f.add(new Character('\u254B')); // ( 9547) X
      f.add(new Character('\u2550')); // ( 9552) X
      f.add(new Character('\u2551')); // ( 9553) X
      f.add(new Character('\u2552')); // ( 9554) X
      f.add(new Character('\u2553')); // ( 9555) X
      f.add(new Character('\u2554')); // ( 9556) X
      f.add(new Character('\u2555')); // ( 9557) X
      f.add(new Character('\u2556')); // ( 9558) X
      f.add(new Character('\u2557')); // ( 9559) X
      f.add(new Character('\u2558')); // ( 9560) X
      f.add(new Character('\u2559')); // ( 9561) X
      f.add(new Character('\u255A')); // ( 9562) X
      f.add(new Character('\u255B')); // ( 9563) X
      f.add(new Character('\u255C')); // ( 9564) X
      f.add(new Character('\u255D')); // ( 9565) X
      f.add(new Character('\u255E')); // ( 9566) X
      f.add(new Character('\u255F')); // ( 9567) X
      f.add(new Character('\u2560')); // ( 9568) X
      f.add(new Character('\u2561')); // ( 9569) X
      f.add(new Character('\u2562')); // ( 9570) X
      f.add(new Character('\u2563')); // ( 9571) X
      f.add(new Character('\u2564')); // ( 9572) X
      f.add(new Character('\u2565')); // ( 9573) X
      f.add(new Character('\u2566')); // ( 9574) X
      f.add(new Character('\u2567')); // ( 9575) X
      f.add(new Character('\u2568')); // ( 9576) X
      f.add(new Character('\u2569')); // ( 9577) X
      f.add(new Character('\u256A')); // ( 9578) X
      f.add(new Character('\u256B')); // ( 9579) X
      f.add(new Character('\u256C')); // ( 9580) X
      f.add(new Character('\u256D')); // ( 9581) X
      f.add(new Character('\u256E')); // ( 9582) X
      f.add(new Character('\u256F')); // ( 9583) X
      f.add(new Character('\u2570')); // ( 9584) X
      f.add(new Character('\u2571')); // ( 9585) X
      f.add(new Character('\u2572')); // ( 9586) X
      f.add(new Character('\u2573')); // ( 9587) X
      f.add(new Character('\u2581')); // ( 9601) X
      f.add(new Character('\u2582')); // ( 9602) X
      f.add(new Character('\u2583')); // ( 9603) X
      f.add(new Character('\u2584')); // ( 9604) X
      f.add(new Character('\u2585')); // ( 9605) X
      f.add(new Character('\u2586')); // ( 9606) X
      f.add(new Character('\u2587')); // ( 9607) X
      f.add(new Character('\u2588')); // ( 9608) X
      f.add(new Character('\u2589')); // ( 9609) X
      f.add(new Character('\u258A')); // ( 9610) X
      f.add(new Character('\u258B')); // ( 9611) X
      f.add(new Character('\u258C')); // ( 9612) X
      f.add(new Character('\u258D')); // ( 9613) X
      f.add(new Character('\u258E')); // ( 9614) X
      f.add(new Character('\u258F')); // ( 9615) X
      f.add(new Character('\u2593')); // ( 9619) X
      f.add(new Character('\u2594')); // ( 9620) X
      f.add(new Character('\u2595')); // ( 9621) X
      f.add(new Character('\u25A0')); // ( 9632) X
      f.add(new Character('\u25A1')); // ( 9633) X
      f.add(new Character('\u25B2')); // ( 9650) X
      f.add(new Character('\u25B3')); // ( 9651) X
      f.add(new Character('\u25BC')); // ( 9660) X
      f.add(new Character('\u25BD')); // ( 9661) X
      f.add(new Character('\u25C6')); // ( 9670) X
      f.add(new Character('\u25C7')); // ( 9671) X
      f.add(new Character('\u25CB')); // ( 9675) X
      f.add(new Character('\u25CE')); // ( 9678) X
      f.add(new Character('\u25CF')); // ( 9679) X
      f.add(new Character('\u25E2')); // ( 9698) X
      f.add(new Character('\u25E3')); // ( 9699) X
      f.add(new Character('\u25E4')); // ( 9700) X
      f.add(new Character('\u25E5')); // ( 9701) X
      f.add(new Character('\u2605')); // ( 9733) X
      f.add(new Character('\u2606')); // ( 9734) X
      f.add(new Character('\u2609')); // ( 9737) X
      f.add(new Character('\u2640')); // ( 9792) X
      f.add(new Character('\u2642')); // ( 9794) X
      f.add(new Character('\u2E81')); // (11905) X
      f.add(new Character('\u2E84')); // (11908) X
      f.add(new Character('\u2E88')); // (11912) X
      f.add(new Character('\u2E8B')); // (11915) X
      f.add(new Character('\u2E8C')); // (11916) X
      f.add(new Character('\u2E97')); // (11927) X
      f.add(new Character('\u2EA7')); // (11943) X
      f.add(new Character('\u2EAA')); // (11946) X
      f.add(new Character('\u2EAE')); // (11950) X
      f.add(new Character('\u2EB3')); // (11955) X
      f.add(new Character('\u2EB6')); // (11958) X
      f.add(new Character('\u2EB7')); // (11959) X
      f.add(new Character('\u2EBB')); // (11963) X
      f.add(new Character('\u2ECA')); // (11978) X
      f.add(new Character('\u2FF0')); // (12272) X
      f.add(new Character('\u2FF1')); // (12273) X
      f.add(new Character('\u2FF2')); // (12274) X
      f.add(new Character('\u2FF3')); // (12275) X
      f.add(new Character('\u2FF4')); // (12276) X
      f.add(new Character('\u2FF5')); // (12277) X
      f.add(new Character('\u2FF6')); // (12278) X
      f.add(new Character('\u2FF7')); // (12279) X
      f.add(new Character('\u2FF8')); // (12280) X
      f.add(new Character('\u2FF9')); // (12281) X
      f.add(new Character('\u2FFA')); // (12282) X
      f.add(new Character('\u2FFB')); // (12283) X
      f.add(new Character('\u3001')); // (12289) X
      f.add(new Character('\u3002')); // (12290) X
      f.add(new Character('\u3003')); // (12291) X
      f.add(new Character('\u3005')); // (12293) X
      f.add(new Character('\u3006')); // (12294) X
      f.add(new Character('\u3007')); // (12295) X
      f.add(new Character('\u3008')); // (12296) X
      f.add(new Character('\u3009')); // (12297) X
      f.add(new Character('\u300A')); // (12298) X
      f.add(new Character('\u300B')); // (12299) X
      f.add(new Character('\u300C')); // (12300) X
      f.add(new Character('\u300D')); // (12301) X
      f.add(new Character('\u300E')); // (12302) X
      f.add(new Character('\u300F')); // (12303) X
      f.add(new Character('\u3010')); // (12304) X
      f.add(new Character('\u3011')); // (12305) X
      f.add(new Character('\u3012')); // (12306) X
      f.add(new Character('\u3013')); // (12307) X
      f.add(new Character('\u3014')); // (12308) X
      f.add(new Character('\u3015')); // (12309) X
      f.add(new Character('\u3016')); // (12310) X
      f.add(new Character('\u3017')); // (12311) X
      f.add(new Character('\u301D')); // (12317) X
      f.add(new Character('\u301E')); // (12318) X
      f.add(new Character('\u3021')); // (12321) X
      f.add(new Character('\u3022')); // (12322) X
      f.add(new Character('\u3023')); // (12323) X
      f.add(new Character('\u3024')); // (12324) X
      f.add(new Character('\u3025')); // (12325) X
      f.add(new Character('\u3026')); // (12326) X
      f.add(new Character('\u3027')); // (12327) X
      f.add(new Character('\u3028')); // (12328) X
      f.add(new Character('\u3029')); // (12329) X
      f.add(new Character('\u303E')); // (12350) X
      f.add(new Character('\u3041')); // (12353) X
      f.add(new Character('\u3042')); // (12354) X
      f.add(new Character('\u3043')); // (12355) X
      f.add(new Character('\u3044')); // (12356) X
      f.add(new Character('\u3045')); // (12357) X
      f.add(new Character('\u3046')); // (12358) X
      f.add(new Character('\u3047')); // (12359) X
      f.add(new Character('\u3048')); // (12360) X
      f.add(new Character('\u3049')); // (12361) X
      f.add(new Character('\u304A')); // (12362) X
      f.add(new Character('\u304B')); // (12363) X
      f.add(new Character('\u304C')); // (12364) X
      f.add(new Character('\u304D')); // (12365) X
      f.add(new Character('\u304E')); // (12366) X
      f.add(new Character('\u304F')); // (12367) X
      f.add(new Character('\u3050')); // (12368) X
      f.add(new Character('\u3051')); // (12369) X
      f.add(new Character('\u3052')); // (12370) X
      f.add(new Character('\u3053')); // (12371) X
      f.add(new Character('\u3054')); // (12372) X
      f.add(new Character('\u3055')); // (12373) X
      f.add(new Character('\u3056')); // (12374) X
      f.add(new Character('\u3057')); // (12375) X
      f.add(new Character('\u3058')); // (12376) X
      f.add(new Character('\u3059')); // (12377) X
      f.add(new Character('\u305A')); // (12378) X
      f.add(new Character('\u305B')); // (12379) X
      f.add(new Character('\u305C')); // (12380) X
      f.add(new Character('\u305D')); // (12381) X
      f.add(new Character('\u305E')); // (12382) X
      f.add(new Character('\u305F')); // (12383) X
      f.add(new Character('\u3060')); // (12384) X
      f.add(new Character('\u3061')); // (12385) X
      f.add(new Character('\u3062')); // (12386) X
      f.add(new Character('\u3063')); // (12387) X
      f.add(new Character('\u3064')); // (12388) X
      f.add(new Character('\u3065')); // (12389) X
      f.add(new Character('\u3066')); // (12390) X
      f.add(new Character('\u3067')); // (12391) X
      f.add(new Character('\u3068')); // (12392) X
      f.add(new Character('\u3069')); // (12393) X
      f.add(new Character('\u306A')); // (12394) X
      f.add(new Character('\u306B')); // (12395) X
      f.add(new Character('\u306C')); // (12396) X
      f.add(new Character('\u306D')); // (12397) X
      f.add(new Character('\u306E')); // (12398) X
      f.add(new Character('\u306F')); // (12399) X
      f.add(new Character('\u3070')); // (12400) X
      f.add(new Character('\u3071')); // (12401) X
      f.add(new Character('\u3072')); // (12402) X
      f.add(new Character('\u3073')); // (12403) X
      f.add(new Character('\u3074')); // (12404) X
      f.add(new Character('\u3075')); // (12405) X
      f.add(new Character('\u3076')); // (12406) X
      f.add(new Character('\u3077')); // (12407) X
      f.add(new Character('\u3078')); // (12408) X
      f.add(new Character('\u3079')); // (12409) X
      f.add(new Character('\u307A')); // (12410) X
      f.add(new Character('\u307B')); // (12411) X
      f.add(new Character('\u307C')); // (12412) X
      f.add(new Character('\u307D')); // (12413) X
      f.add(new Character('\u307E')); // (12414) X
      f.add(new Character('\u307F')); // (12415) X
      f.add(new Character('\u3080')); // (12416) X
      f.add(new Character('\u3081')); // (12417) X
      f.add(new Character('\u3082')); // (12418) X
      f.add(new Character('\u3083')); // (12419) X
      f.add(new Character('\u3084')); // (12420) X
      f.add(new Character('\u3085')); // (12421) X
      f.add(new Character('\u3086')); // (12422) X
      f.add(new Character('\u3087')); // (12423) X
      f.add(new Character('\u3088')); // (12424) X
      f.add(new Character('\u3089')); // (12425) X
      f.add(new Character('\u308A')); // (12426) X
      f.add(new Character('\u308B')); // (12427) X
      f.add(new Character('\u308C')); // (12428) X
      f.add(new Character('\u308D')); // (12429) X
      f.add(new Character('\u308E')); // (12430) X
      f.add(new Character('\u308F')); // (12431) X
      f.add(new Character('\u3090')); // (12432) X
      f.add(new Character('\u3091')); // (12433) X
      f.add(new Character('\u3092')); // (12434) X
      f.add(new Character('\u3093')); // (12435) X
      f.add(new Character('\u309B')); // (12443) X
      f.add(new Character('\u309C')); // (12444) X
      f.add(new Character('\u309D')); // (12445) X
      f.add(new Character('\u309E')); // (12446) X
      f.add(new Character('\u30A1')); // (12449) X
      f.add(new Character('\u30A2')); // (12450) X
      f.add(new Character('\u30A3')); // (12451) X
      f.add(new Character('\u30A4')); // (12452) X
      f.add(new Character('\u30A5')); // (12453) X
      f.add(new Character('\u30A6')); // (12454) X
      f.add(new Character('\u30A7')); // (12455) X
      f.add(new Character('\u30A8')); // (12456) X
      f.add(new Character('\u30A9')); // (12457) X
      f.add(new Character('\u30AA')); // (12458) X
      f.add(new Character('\u30AB')); // (12459) X
      f.add(new Character('\u30AC')); // (12460) X
      f.add(new Character('\u30AD')); // (12461) X
      f.add(new Character('\u30AE')); // (12462) X
      f.add(new Character('\u30AF')); // (12463) X
      f.add(new Character('\u30B0')); // (12464) X
      f.add(new Character('\u30B1')); // (12465) X
      f.add(new Character('\u30B2')); // (12466) X
      f.add(new Character('\u30B3')); // (12467) X
      f.add(new Character('\u30B4')); // (12468) X
      f.add(new Character('\u30B5')); // (12469) X
      f.add(new Character('\u30B6')); // (12470) X
      f.add(new Character('\u30B7')); // (12471) X
      f.add(new Character('\u30B8')); // (12472) X
      f.add(new Character('\u30B9')); // (12473) X
      f.add(new Character('\u30BA')); // (12474) X
      f.add(new Character('\u30BB')); // (12475) X
      f.add(new Character('\u30BC')); // (12476) X
      f.add(new Character('\u30BD')); // (12477) X
      f.add(new Character('\u30BE')); // (12478) X
      f.add(new Character('\u30BF')); // (12479) X
      f.add(new Character('\u30C0')); // (12480) X
      f.add(new Character('\u30C1')); // (12481) X
      f.add(new Character('\u30C2')); // (12482) X
      f.add(new Character('\u30C3')); // (12483) X
      f.add(new Character('\u30C4')); // (12484) X
      f.add(new Character('\u30C5')); // (12485) X
      f.add(new Character('\u30C6')); // (12486) X
      f.add(new Character('\u30C7')); // (12487) X
      f.add(new Character('\u30C8')); // (12488) X
      f.add(new Character('\u30C9')); // (12489) X
      f.add(new Character('\u30CA')); // (12490) X
      f.add(new Character('\u30CB')); // (12491) X
      f.add(new Character('\u30CC')); // (12492) X
      f.add(new Character('\u30CD')); // (12493) X
      f.add(new Character('\u30CE')); // (12494) X
      f.add(new Character('\u30CF')); // (12495) X
      f.add(new Character('\u30D0')); // (12496) X
      f.add(new Character('\u30D1')); // (12497) X
      f.add(new Character('\u30D2')); // (12498) X
      f.add(new Character('\u30D3')); // (12499) X
      f.add(new Character('\u30D4')); // (12500) X
      f.add(new Character('\u30D5')); // (12501) X
      f.add(new Character('\u30D6')); // (12502) X
      f.add(new Character('\u30D7')); // (12503) X
      f.add(new Character('\u30D8')); // (12504) X
      f.add(new Character('\u30D9')); // (12505) X
      f.add(new Character('\u30DA')); // (12506) X
      f.add(new Character('\u30DB')); // (12507) X
      f.add(new Character('\u30DC')); // (12508) X
      f.add(new Character('\u30DD')); // (12509) X
      f.add(new Character('\u30DE')); // (12510) X
      f.add(new Character('\u30DF')); // (12511) X
      f.add(new Character('\u30E0')); // (12512) X
      f.add(new Character('\u30E1')); // (12513) X
      f.add(new Character('\u30E2')); // (12514) X
      f.add(new Character('\u30E3')); // (12515) X
      f.add(new Character('\u30E4')); // (12516) X
      f.add(new Character('\u30E5')); // (12517) X
      f.add(new Character('\u30E6')); // (12518) X
      f.add(new Character('\u30E7')); // (12519) X
      f.add(new Character('\u30E8')); // (12520) X
      f.add(new Character('\u30E9')); // (12521) X
      f.add(new Character('\u30EA')); // (12522) X
      f.add(new Character('\u30EB')); // (12523) X
      f.add(new Character('\u30EC')); // (12524) X
      f.add(new Character('\u30ED')); // (12525) X
      f.add(new Character('\u30EE')); // (12526) X
      f.add(new Character('\u30EF')); // (12527) X
      f.add(new Character('\u30F0')); // (12528) X
      f.add(new Character('\u30F1')); // (12529) X
      f.add(new Character('\u30F2')); // (12530) X
      f.add(new Character('\u30F3')); // (12531) X
      f.add(new Character('\u30F4')); // (12532) X
      f.add(new Character('\u30F5')); // (12533) X
      f.add(new Character('\u30F6')); // (12534) X
      f.add(new Character('\u30FC')); // (12540) X
      f.add(new Character('\u30FD')); // (12541) X
      f.add(new Character('\u30FE')); // (12542) X
      f.add(new Character('\u3105')); // (12549) X
      f.add(new Character('\u3106')); // (12550) X
      f.add(new Character('\u3107')); // (12551) X
      f.add(new Character('\u3108')); // (12552) X
      f.add(new Character('\u3109')); // (12553) X
      f.add(new Character('\u310A')); // (12554) X
      f.add(new Character('\u310B')); // (12555) X
      f.add(new Character('\u310C')); // (12556) X
      f.add(new Character('\u310D')); // (12557) X
      f.add(new Character('\u310E')); // (12558) X
      f.add(new Character('\u310F')); // (12559) X
      f.add(new Character('\u3110')); // (12560) X
      f.add(new Character('\u3111')); // (12561) X
      f.add(new Character('\u3112')); // (12562) X
      f.add(new Character('\u3113')); // (12563) X
      f.add(new Character('\u3114')); // (12564) X
      f.add(new Character('\u3115')); // (12565) X
      f.add(new Character('\u3116')); // (12566) X
      f.add(new Character('\u3117')); // (12567) X
      f.add(new Character('\u3118')); // (12568) X
      f.add(new Character('\u3119')); // (12569) X
      f.add(new Character('\u311A')); // (12570) X
      f.add(new Character('\u311B')); // (12571) X
      f.add(new Character('\u311C')); // (12572) X
      f.add(new Character('\u311D')); // (12573) X
      f.add(new Character('\u311E')); // (12574) X
      f.add(new Character('\u311F')); // (12575) X
      f.add(new Character('\u3120')); // (12576) X
      f.add(new Character('\u3121')); // (12577) X
      f.add(new Character('\u3122')); // (12578) X
      f.add(new Character('\u3123')); // (12579) X
      f.add(new Character('\u3124')); // (12580) X
      f.add(new Character('\u3125')); // (12581) X
      f.add(new Character('\u3126')); // (12582) X
      f.add(new Character('\u3127')); // (12583) X
      f.add(new Character('\u3128')); // (12584) X
      f.add(new Character('\u3129')); // (12585) X
      f.add(new Character('\u3220')); // (12832) X
      f.add(new Character('\u3221')); // (12833) X
      f.add(new Character('\u3222')); // (12834) X
      f.add(new Character('\u3223')); // (12835) X
      f.add(new Character('\u3224')); // (12836) X
      f.add(new Character('\u3225')); // (12837) X
      f.add(new Character('\u3226')); // (12838) X
      f.add(new Character('\u3227')); // (12839) X
      f.add(new Character('\u3228')); // (12840) X
      f.add(new Character('\u3229')); // (12841) X
      f.add(new Character('\u3231')); // (12849) X
      f.add(new Character('\u32A3')); // (12963) X
      f.add(new Character('\u338E')); // (13198) X
      f.add(new Character('\u338F')); // (13199) X
      f.add(new Character('\u339C')); // (13212) X
      f.add(new Character('\u339D')); // (13213) X
      f.add(new Character('\u339E')); // (13214) X
      f.add(new Character('\u33A1')); // (13217) X
      f.add(new Character('\u33C4')); // (13252) X
      f.add(new Character('\u33CE')); // (13262) X
      f.add(new Character('\u33D1')); // (13265) X
      f.add(new Character('\u33D2')); // (13266) X
      f.add(new Character('\u33D5')); // (13269) X
      f.add(new Character('\u3447')); // (13383) X
      f.add(new Character('\u3473')); // (13427) X
      f.add(new Character('\u359E')); // (13726) X
      f.add(new Character('\u360E')); // (13838) X
      f.add(new Character('\u361A')); // (13850) X
      f.add(new Character('\u3918')); // (14616) X
      f.add(new Character('\u396E')); // (14702) X
      f.add(new Character('\u39CF')); // (14799) X
      f.add(new Character('\u39D0')); // (14800) X
      f.add(new Character('\u39DF')); // (14815) X
      f.add(new Character('\u3A73')); // (14963) X
      f.add(new Character('\u3B4E')); // (15182) X
      f.add(new Character('\u3C6E')); // (15470) X
      f.add(new Character('\u3CE0')); // (15584) X
      f.add(new Character('\u4056')); // (16470) X
      f.add(new Character('\u415F')); // (16735) X
      f.add(new Character('\u4337')); // (17207) X
      f.add(new Character('\u43AC')); // (17324) X
      f.add(new Character('\u43B1')); // (17329) X
      f.add(new Character('\u43DD')); // (17373) X
      f.add(new Character('\u44D6')); // (17622) X
      f.add(new Character('\u464C')); // (17996) X
      f.add(new Character('\u4661')); // (18017) X
      f.add(new Character('\u4723')); // (18211) X
      f.add(new Character('\u4729')); // (18217) X
      f.add(new Character('\u477C')); // (18300) X
      f.add(new Character('\u478D')); // (18317) X
      f.add(new Character('\u4947')); // (18759) X
      f.add(new Character('\u497A')); // (18810) X
      f.add(new Character('\u497D')); // (18813) X
      f.add(new Character('\u4982')); // (18818) X
      f.add(new Character('\u4983')); // (18819) X
      f.add(new Character('\u4985')); // (18821) X
      f.add(new Character('\u4986')); // (18822) X
      f.add(new Character('\u499B')); // (18843) X
      f.add(new Character('\u499F')); // (18847) X
      f.add(new Character('\u49B6')); // (18870) X
      f.add(new Character('\u49B7')); // (18871) X
      f.add(new Character('\u4C77')); // (19575) X
      f.add(new Character('\u4C9F')); // (19615) X
      f.add(new Character('\u4CA0')); // (19616) X
      f.add(new Character('\u4CA1')); // (19617) X
      f.add(new Character('\u4CA2')); // (19618) X
      f.add(new Character('\u4CA3')); // (19619) X
      f.add(new Character('\u4D13')); // (19731) X
      f.add(new Character('\u4D14')); // (19732) X
      f.add(new Character('\u4D15')); // (19733) X
      f.add(new Character('\u4D16')); // (19734) X
      f.add(new Character('\u4D17')); // (19735) X
      f.add(new Character('\u4D18')); // (19736) X
      f.add(new Character('\u4D19')); // (19737) X
      f.add(new Character('\u4DAE')); // (19886) X
      f.add(new Character('\u4E00')); // (19968) X
      f.add(new Character('\u4E01')); // (19969) X
      f.add(new Character('\u4E02')); // (19970) X
      f.add(new Character('\u4E03')); // (19971) X
      f.add(new Character('\u4E04')); // (19972) X
      f.add(new Character('\u4E05')); // (19973) X
      f.add(new Character('\u4E06')); // (19974) X
      f.add(new Character('\u4E07')); // (19975) X
      f.add(new Character('\u4E08')); // (19976) X
      f.add(new Character('\u4E09')); // (19977) X
   }

   /**
    * Initializes ordinary characters for LUWO for the zh_TW language.
    * @param f The HashSet
    */
   protected static void initTWFlags(HashSet<Character> f)
   {
      f.add(new Character('\u00A7')); // ( 167) SECTION
      f.add(new Character('\u00A8')); // ( 168) diaeresis
      f.add(new Character('\u00AF')); // ( 175) macron
      f.add(new Character('\u00B0')); // ( 176) degree
      f.add(new Character('\u00B1')); // ( 177) plus_minus
      f.add(new Character('\u00B4')); // ( 180) acute
      f.add(new Character('\u00B6')); // ( 182) Pilcrow
      f.add(new Character('\u00B7')); // ( 183) middot
      f.add(new Character('\u00D7')); // ( 215) times
      f.add(new Character('\u0100')); // ( 256) A_bar
      f.add(new Character('\u0101')); // ( 257) a_bar
      f.add(new Character('\u0102')); // ( 258) A_breve
      f.add(new Character('\u0103')); // ( 259) a_breve
      f.add(new Character('\u0104')); // ( 260) A_ogokek
      f.add(new Character('\u0105')); // ( 261) a_ogokek
      f.add(new Character('\u0106')); // ( 262) C_acute
      f.add(new Character('\u0107')); // ( 263) c_acute
      f.add(new Character('\u0108')); // ( 264) C_circumflex
      f.add(new Character('\u0109')); // ( 265) c_circumflex
      f.add(new Character('\u010A')); // ( 266) C_superdot
      f.add(new Character('\u010B')); // ( 267) c_superdot
      f.add(new Character('\u010C')); // ( 268) C_caron
      f.add(new Character('\u010D')); // ( 269) c_caron
      f.add(new Character('\u010E')); // ( 270) D_caron
      f.add(new Character('\u010F')); // ( 271) d_caron
      f.add(new Character('\u0110')); // ( 272) D_stroke
      f.add(new Character('\u0111')); // ( 273) d_stroke
      f.add(new Character('\u0112')); // ( 274) E_macron
      f.add(new Character('\u0113')); // ( 275) e_macron
      f.add(new Character('\u0114')); // ( 276) E_breve
      f.add(new Character('\u0115')); // ( 277) e_breve
      f.add(new Character('\u0116')); // ( 278) E_superdot
      f.add(new Character('\u0117')); // ( 279) e_superdot
      f.add(new Character('\u0118')); // ( 280) E_ogonek
      f.add(new Character('\u0119')); // ( 281) e_ogonek
      f.add(new Character('\u011A')); // ( 282) E_caron
      f.add(new Character('\u011B')); // ( 283) e_caron
      f.add(new Character('\u011C')); // ( 284) G_circumflex
      f.add(new Character('\u011D')); // ( 285) g_circumflex
      f.add(new Character('\u011E')); // ( 286) G_breve
      f.add(new Character('\u011F')); // ( 287) g_breve
      f.add(new Character('\u0120')); // ( 288) G_superdot
      f.add(new Character('\u0121')); // ( 289) g_superdot
      f.add(new Character('\u0122')); // ( 290) G_cedilla
      f.add(new Character('\u0123')); // ( 291) g_cedilla
      f.add(new Character('\u0124')); // ( 292) H_circumflex
      f.add(new Character('\u0125')); // ( 293) h_circumflex
      f.add(new Character('\u0126')); // ( 294) H_stroke
      f.add(new Character('\u0127')); // ( 295) h_stroke
      f.add(new Character('\u0128')); // ( 296) I_tilde
      f.add(new Character('\u0129')); // ( 297) i_tilde
      f.add(new Character('\u012A')); // ( 298) I_macron
      f.add(new Character('\u012B')); // ( 299) i_macron
      f.add(new Character('\u012C')); // ( 300) I_breve
      f.add(new Character('\u012D')); // ( 301) i_breve
      f.add(new Character('\u012E')); // ( 302) I_ogonek
      f.add(new Character('\u012F')); // ( 303) i_ogonek
      f.add(new Character('\u0130')); // ( 304) I_superdot
      f.add(new Character('\u0131')); // ( 305) i_nodot
      f.add(new Character('\u0132')); // ( 306) IJ_ligature
      f.add(new Character('\u0133')); // ( 307) ij_ligature
      f.add(new Character('\u0134')); // ( 308) J_circumflex
      f.add(new Character('\u0135')); // ( 309) j_circumflex
      f.add(new Character('\u0136')); // ( 310) K_cedilla
      f.add(new Character('\u0137')); // ( 311) k_cedilla
      f.add(new Character('\u0138')); // ( 312) kra
      f.add(new Character('\u0139')); // ( 313) L_acute
      f.add(new Character('\u013A')); // ( 314) l_acute
      f.add(new Character('\u013B')); // ( 315) L_cedilla
      f.add(new Character('\u013C')); // ( 316) l_cedilla
      f.add(new Character('\u013D')); // ( 317) L_caron
      f.add(new Character('\u013E')); // ( 318) l_caron
      f.add(new Character('\u013F')); // ( 319) L_middot
      f.add(new Character('\u0140')); // ( 320) l_middot
      f.add(new Character('\u0141')); // ( 321) L_stoke
      f.add(new Character('\u0142')); // ( 322) l_stoke
      f.add(new Character('\u0143')); // ( 323) N_acute
      f.add(new Character('\u0144')); // ( 324) n_acute
      f.add(new Character('\u0145')); // ( 325) N_cedilla
      f.add(new Character('\u0146')); // ( 326) n_cedilla
      f.add(new Character('\u0147')); // ( 327) N_caron
      f.add(new Character('\u0148')); // ( 328) n_caron
      f.add(new Character('\u0149')); // ( 329) n_apostrophe
      f.add(new Character('\u014A')); // ( 330) ENG
      f.add(new Character('\u014B')); // ( 331) eng
      f.add(new Character('\u014C')); // ( 332) O_macron
      f.add(new Character('\u014D')); // ( 333) o_macron
      f.add(new Character('\u014E')); // ( 334) O_breve
      f.add(new Character('\u014F')); // ( 335) o_breve
      f.add(new Character('\u0150')); // ( 336) O_double_acute
      f.add(new Character('\u0151')); // ( 337) o_double_acute
      f.add(new Character('\u0152')); // ( 338) OE_ligature
      f.add(new Character('\u0153')); // ( 339) oe_ligature
      f.add(new Character('\u0154')); // ( 340) R_acute
      f.add(new Character('\u0155')); // ( 341) r_acute
      f.add(new Character('\u0156')); // ( 342) R_cedilla
      f.add(new Character('\u0157')); // ( 343) r_cedilla
      f.add(new Character('\u0158')); // ( 344) R_caron
      f.add(new Character('\u0159')); // ( 345) r_caron
      f.add(new Character('\u015A')); // ( 346) S_acute
      f.add(new Character('\u015B')); // ( 347) s_acute
      f.add(new Character('\u015C')); // ( 348) S_circumflex
      f.add(new Character('\u015D')); // ( 349) s_circumflex
      f.add(new Character('\u015E')); // ( 350) S_cedilla
      f.add(new Character('\u015F')); // ( 351) s_cedilla
      f.add(new Character('\u0160')); // ( 352) S_caron
      f.add(new Character('\u0161')); // ( 353) s_caron
      f.add(new Character('\u0162')); // ( 354) T_cedilla
      f.add(new Character('\u0163')); // ( 355) t_cedilla
      f.add(new Character('\u0164')); // ( 356) T_caron
      f.add(new Character('\u0165')); // ( 357) t_caron
      f.add(new Character('\u0166')); // ( 358) T_stroke
      f.add(new Character('\u0167')); // ( 359) t_stroke
      f.add(new Character('\u0168')); // ( 360) U_tilde
      f.add(new Character('\u0169')); // ( 361) u_tilde
      f.add(new Character('\u016A')); // ( 362) U_macron
      f.add(new Character('\u016B')); // ( 363) u_macron
      f.add(new Character('\u016C')); // ( 364) U_breve
      f.add(new Character('\u016D')); // ( 365) u_breve
      f.add(new Character('\u016E')); // ( 366) U_ring
      f.add(new Character('\u016F')); // ( 367) u_ring
      f.add(new Character('\u0170')); // ( 368) U_double_acute
      f.add(new Character('\u0171')); // ( 369) u_double_acute
      f.add(new Character('\u0172')); // ( 370) U_ogonek
      f.add(new Character('\u0173')); // ( 371) u_ogonek
      f.add(new Character('\u0174')); // ( 372) W_circumflex
      f.add(new Character('\u0175')); // ( 373) w_circumflex
      f.add(new Character('\u0176')); // ( 374) Y_circumflex
      f.add(new Character('\u0177')); // ( 375) y_circumflex
      f.add(new Character('\u0178')); // ( 376) Y_diaeresis
      f.add(new Character('\u0179')); // ( 377) Z_acute
      f.add(new Character('\u017A')); // ( 378) z_acute
      f.add(new Character('\u017B')); // ( 379) Z_superdot
      f.add(new Character('\u017C')); // ( 380) z_superdot
      f.add(new Character('\u017D')); // ( 381) Z_caron
      f.add(new Character('\u017E')); // ( 382) z_caron
      f.add(new Character('\u017F')); // ( 383) long_s
      f.add(new Character('\u0180')); // ( 384) X
      f.add(new Character('\u0181')); // ( 385) X
      f.add(new Character('\u0182')); // ( 386) X
      f.add(new Character('\u0183')); // ( 387) X
      f.add(new Character('\u0184')); // ( 388) X
      f.add(new Character('\u0185')); // ( 389) X
      f.add(new Character('\u0186')); // ( 390) X
      f.add(new Character('\u0187')); // ( 391) X
      f.add(new Character('\u0188')); // ( 392) X
      f.add(new Character('\u0189')); // ( 393) X
      f.add(new Character('\u018A')); // ( 394) X
      f.add(new Character('\u018B')); // ( 395) X
      f.add(new Character('\u018C')); // ( 396) X
      f.add(new Character('\u018D')); // ( 397) X
      f.add(new Character('\u018E')); // ( 398) X
      f.add(new Character('\u018F')); // ( 399) X
      f.add(new Character('\u0190')); // ( 400) X
      f.add(new Character('\u0191')); // ( 401) X
      f.add(new Character('\u0192')); // ( 402) X
      f.add(new Character('\u0193')); // ( 403) X
      f.add(new Character('\u0194')); // ( 404) X
      f.add(new Character('\u0195')); // ( 405) X
      f.add(new Character('\u0196')); // ( 406) X
      f.add(new Character('\u0197')); // ( 407) X
      f.add(new Character('\u0198')); // ( 408) X
      f.add(new Character('\u0199')); // ( 409) X
      f.add(new Character('\u019A')); // ( 410) X
      f.add(new Character('\u019B')); // ( 411) X
      f.add(new Character('\u019C')); // ( 412) X
      f.add(new Character('\u019D')); // ( 413) X
      f.add(new Character('\u019E')); // ( 414) X
      f.add(new Character('\u019F')); // ( 415) X
      f.add(new Character('\u01A0')); // ( 416) X
      f.add(new Character('\u01A1')); // ( 417) X
      f.add(new Character('\u01A2')); // ( 418) X
      f.add(new Character('\u01A3')); // ( 419) X
      f.add(new Character('\u01A4')); // ( 420) X
      f.add(new Character('\u01A5')); // ( 421) X
      f.add(new Character('\u01A6')); // ( 422) X
      f.add(new Character('\u01A7')); // ( 423) X
      f.add(new Character('\u01A8')); // ( 424) X
      f.add(new Character('\u01A9')); // ( 425) X
      f.add(new Character('\u01AA')); // ( 426) X
      f.add(new Character('\u01AB')); // ( 427) X
      f.add(new Character('\u01AC')); // ( 428) X
      f.add(new Character('\u01AD')); // ( 429) X
      f.add(new Character('\u01AE')); // ( 430) X
      f.add(new Character('\u01AF')); // ( 431) X
      f.add(new Character('\u01B0')); // ( 432) X
      f.add(new Character('\u01B1')); // ( 433) X
      f.add(new Character('\u01B2')); // ( 434) X
      f.add(new Character('\u01B3')); // ( 435) X
      f.add(new Character('\u01B4')); // ( 436) X
      f.add(new Character('\u01B5')); // ( 437) X
      f.add(new Character('\u01B6')); // ( 438) X
      f.add(new Character('\u01B7')); // ( 439) X
      f.add(new Character('\u01B8')); // ( 440) X
      f.add(new Character('\u01B9')); // ( 441) X
      f.add(new Character('\u01BA')); // ( 442) X
      f.add(new Character('\u01BB')); // ( 443) X
      f.add(new Character('\u01BC')); // ( 444) X
      f.add(new Character('\u01BD')); // ( 445) X
      f.add(new Character('\u01BE')); // ( 446) X
      f.add(new Character('\u01BF')); // ( 447) X
      f.add(new Character('\u01C0')); // ( 448) X
      f.add(new Character('\u01C1')); // ( 449) X
      f.add(new Character('\u01C2')); // ( 450) X
      f.add(new Character('\u01C3')); // ( 451) X
      f.add(new Character('\u01C4')); // ( 452) X
      f.add(new Character('\u01C5')); // ( 453) X
      f.add(new Character('\u01C6')); // ( 454) X
      f.add(new Character('\u01C7')); // ( 455) X
      f.add(new Character('\u01C8')); // ( 456) X
      f.add(new Character('\u01C9')); // ( 457) X
      f.add(new Character('\u01CA')); // ( 458) X
      f.add(new Character('\u01CB')); // ( 459) X
      f.add(new Character('\u01CC')); // ( 460) X
      f.add(new Character('\u01CD')); // ( 461) X
      f.add(new Character('\u01CE')); // ( 462) X
      f.add(new Character('\u01CF')); // ( 463) X
      f.add(new Character('\u01D0')); // ( 464) X
      f.add(new Character('\u01D1')); // ( 465) X
      f.add(new Character('\u01D2')); // ( 466) X
      f.add(new Character('\u01D3')); // ( 467) X
      f.add(new Character('\u01D4')); // ( 468) X
      f.add(new Character('\u01D5')); // ( 469) X
      f.add(new Character('\u01D6')); // ( 470) X
      f.add(new Character('\u01D7')); // ( 471) X
      f.add(new Character('\u01D8')); // ( 472) X
      f.add(new Character('\u01D9')); // ( 473) X
      f.add(new Character('\u01DA')); // ( 474) X
      f.add(new Character('\u01DB')); // ( 475) X
      f.add(new Character('\u01DC')); // ( 476) X
      f.add(new Character('\u01DD')); // ( 477) X
      f.add(new Character('\u01DE')); // ( 478) X
      f.add(new Character('\u01DF')); // ( 479) X
      f.add(new Character('\u01E0')); // ( 480) X
      f.add(new Character('\u01E1')); // ( 481) X
      f.add(new Character('\u01E2')); // ( 482) X
      f.add(new Character('\u01E3')); // ( 483) X
      f.add(new Character('\u01E4')); // ( 484) X
      f.add(new Character('\u01E5')); // ( 485) X
      f.add(new Character('\u01E6')); // ( 486) X
      f.add(new Character('\u01E7')); // ( 487) X
      f.add(new Character('\u01E8')); // ( 488) X
      f.add(new Character('\u01E9')); // ( 489) X
      f.add(new Character('\u01EA')); // ( 490) X
      f.add(new Character('\u01EB')); // ( 491) X
      f.add(new Character('\u01EC')); // ( 492) X
      f.add(new Character('\u01ED')); // ( 493) X
      f.add(new Character('\u01EE')); // ( 494) X
      f.add(new Character('\u01EF')); // ( 495) X
      f.add(new Character('\u01F0')); // ( 496) X
      f.add(new Character('\u01F1')); // ( 497) X
      f.add(new Character('\u01F2')); // ( 498) X
      f.add(new Character('\u01F3')); // ( 499) X
      f.add(new Character('\u01F4')); // ( 500) X
      f.add(new Character('\u01F5')); // ( 501) X
      f.add(new Character('\u01F6')); // ( 502) X
      f.add(new Character('\u01F7')); // ( 503) X
      f.add(new Character('\u01F8')); // ( 504) X
      f.add(new Character('\u01F9')); // ( 505) X
      f.add(new Character('\u01FA')); // ( 506) X
      f.add(new Character('\u01FB')); // ( 507) X
      f.add(new Character('\u01FC')); // ( 508) X
      f.add(new Character('\u01FD')); // ( 509) X
      f.add(new Character('\u01FE')); // ( 510) X
      f.add(new Character('\u01FF')); // ( 511) X
      f.add(new Character('\u0200')); // ( 512) X
      f.add(new Character('\u0201')); // ( 513) X
      f.add(new Character('\u0202')); // ( 514) X
      f.add(new Character('\u0203')); // ( 515) X
      f.add(new Character('\u0204')); // ( 516) X
      f.add(new Character('\u0205')); // ( 517) X
      f.add(new Character('\u0206')); // ( 518) X
      f.add(new Character('\u0207')); // ( 519) X
      f.add(new Character('\u0208')); // ( 520) X
      f.add(new Character('\u0209')); // ( 521) X
      f.add(new Character('\u020A')); // ( 522) X
      f.add(new Character('\u020B')); // ( 523) X
      f.add(new Character('\u020C')); // ( 524) X
      f.add(new Character('\u020D')); // ( 525) X
      f.add(new Character('\u020E')); // ( 526) X
      f.add(new Character('\u020F')); // ( 527) X
      f.add(new Character('\u0210')); // ( 528) X
      f.add(new Character('\u0211')); // ( 529) X
      f.add(new Character('\u0212')); // ( 530) X
      f.add(new Character('\u0213')); // ( 531) X
      f.add(new Character('\u0214')); // ( 532) X
      f.add(new Character('\u0215')); // ( 533) X
      f.add(new Character('\u0216')); // ( 534) X
      f.add(new Character('\u0217')); // ( 535) X
      f.add(new Character('\u0218')); // ( 536) X
      f.add(new Character('\u0219')); // ( 537) X
      f.add(new Character('\u021A')); // ( 538) X
      f.add(new Character('\u021B')); // ( 539) X
      f.add(new Character('\u021C')); // ( 540) X
      f.add(new Character('\u021D')); // ( 541) X
      f.add(new Character('\u021E')); // ( 542) X
      f.add(new Character('\u021F')); // ( 543) X
      f.add(new Character('\u0220')); // ( 544) X
      f.add(new Character('\u0221')); // ( 545) X
      f.add(new Character('\u0222')); // ( 546) X
      f.add(new Character('\u0223')); // ( 547) X
      f.add(new Character('\u0224')); // ( 548) X
      f.add(new Character('\u0225')); // ( 549) X
      f.add(new Character('\u0226')); // ( 550) X
      f.add(new Character('\u0227')); // ( 551) X
      f.add(new Character('\u0228')); // ( 552) X
      f.add(new Character('\u0229')); // ( 553) X
      f.add(new Character('\u022A')); // ( 554) X
      f.add(new Character('\u022B')); // ( 555) X
      f.add(new Character('\u022C')); // ( 556) X
      f.add(new Character('\u022D')); // ( 557) X
      f.add(new Character('\u022E')); // ( 558) X
      f.add(new Character('\u022F')); // ( 559) X
      f.add(new Character('\u0230')); // ( 560) X
      f.add(new Character('\u0231')); // ( 561) X
      f.add(new Character('\u0232')); // ( 562) X
      f.add(new Character('\u0233')); // ( 563) X
      f.add(new Character('\u0234')); // ( 564) X
      f.add(new Character('\u0235')); // ( 565) X
      f.add(new Character('\u0236')); // ( 566) X
      f.add(new Character('\u0237')); // ( 567) X
      f.add(new Character('\u0238')); // ( 568) X
      f.add(new Character('\u0239')); // ( 569) X
      f.add(new Character('\u023A')); // ( 570) X
      f.add(new Character('\u023B')); // ( 571) X
      f.add(new Character('\u023C')); // ( 572) X
      f.add(new Character('\u023D')); // ( 573) X
      f.add(new Character('\u023E')); // ( 574) X
      f.add(new Character('\u023F')); // ( 575) X
      f.add(new Character('\u0240')); // ( 576) X
      f.add(new Character('\u0241')); // ( 577) X
      f.add(new Character('\u0242')); // ( 578) X
      f.add(new Character('\u0243')); // ( 579) X
      f.add(new Character('\u0244')); // ( 580) X
      f.add(new Character('\u0245')); // ( 581) X
      f.add(new Character('\u0246')); // ( 582) X
      f.add(new Character('\u0247')); // ( 583) X
      f.add(new Character('\u0248')); // ( 584) X
      f.add(new Character('\u0249')); // ( 585) X
      f.add(new Character('\u024A')); // ( 586) X
      f.add(new Character('\u024B')); // ( 587) X
      f.add(new Character('\u024C')); // ( 588) X
      f.add(new Character('\u024D')); // ( 589) X
      f.add(new Character('\u024E')); // ( 590) X
      f.add(new Character('\u024F')); // ( 591) X
      f.add(new Character('\u0250')); // ( 592) X
      f.add(new Character('\u0251')); // ( 593) X
      f.add(new Character('\u0252')); // ( 594) X
      f.add(new Character('\u0253')); // ( 595) X
      f.add(new Character('\u0254')); // ( 596) X
      f.add(new Character('\u0255')); // ( 597) X
      f.add(new Character('\u0256')); // ( 598) X
      f.add(new Character('\u0257')); // ( 599) X
      f.add(new Character('\u0258')); // ( 600) X
      f.add(new Character('\u0259')); // ( 601) X
      f.add(new Character('\u025A')); // ( 602) X
      f.add(new Character('\u025B')); // ( 603) X
      f.add(new Character('\u025C')); // ( 604) X
      f.add(new Character('\u025D')); // ( 605) X
      f.add(new Character('\u025E')); // ( 606) X
      f.add(new Character('\u025F')); // ( 607) X
      f.add(new Character('\u0260')); // ( 608) X
      f.add(new Character('\u0261')); // ( 609) X
      f.add(new Character('\u0262')); // ( 610) X
      f.add(new Character('\u0263')); // ( 611) X
      f.add(new Character('\u0264')); // ( 612) X
      f.add(new Character('\u0265')); // ( 613) X
      f.add(new Character('\u0266')); // ( 614) X
      f.add(new Character('\u0267')); // ( 615) X
      f.add(new Character('\u0268')); // ( 616) X
      f.add(new Character('\u0269')); // ( 617) X
      f.add(new Character('\u026A')); // ( 618) X
      f.add(new Character('\u026B')); // ( 619) X
      f.add(new Character('\u026C')); // ( 620) X
      f.add(new Character('\u026D')); // ( 621) X
      f.add(new Character('\u026E')); // ( 622) X
      f.add(new Character('\u026F')); // ( 623) X
      f.add(new Character('\u0270')); // ( 624) X
      f.add(new Character('\u0271')); // ( 625) X
      f.add(new Character('\u0272')); // ( 626) X
      f.add(new Character('\u0273')); // ( 627) X
      f.add(new Character('\u0274')); // ( 628) X
      f.add(new Character('\u0275')); // ( 629) X
      f.add(new Character('\u0276')); // ( 630) X
      f.add(new Character('\u0277')); // ( 631) X
      f.add(new Character('\u0278')); // ( 632) X
      f.add(new Character('\u0279')); // ( 633) X
      f.add(new Character('\u027A')); // ( 634) X
      f.add(new Character('\u027B')); // ( 635) X
      f.add(new Character('\u027C')); // ( 636) X
      f.add(new Character('\u027D')); // ( 637) X
      f.add(new Character('\u027E')); // ( 638) X
      f.add(new Character('\u027F')); // ( 639) X
      f.add(new Character('\u0280')); // ( 640) X
      f.add(new Character('\u0281')); // ( 641) X
      f.add(new Character('\u0282')); // ( 642) X
      f.add(new Character('\u0283')); // ( 643) X
      f.add(new Character('\u0284')); // ( 644) X
      f.add(new Character('\u0285')); // ( 645) X
      f.add(new Character('\u0286')); // ( 646) X
      f.add(new Character('\u0287')); // ( 647) X
      f.add(new Character('\u0288')); // ( 648) X
      f.add(new Character('\u0289')); // ( 649) X
      f.add(new Character('\u028A')); // ( 650) X
      f.add(new Character('\u028B')); // ( 651) X
      f.add(new Character('\u028C')); // ( 652) X
      f.add(new Character('\u028D')); // ( 653) X
      f.add(new Character('\u028E')); // ( 654) X
      f.add(new Character('\u028F')); // ( 655) X
      f.add(new Character('\u0290')); // ( 656) X
      f.add(new Character('\u0291')); // ( 657) X
      f.add(new Character('\u0292')); // ( 658) X
      f.add(new Character('\u0293')); // ( 659) X
      f.add(new Character('\u0294')); // ( 660) X
      f.add(new Character('\u0295')); // ( 661) X
      f.add(new Character('\u0296')); // ( 662) X
      f.add(new Character('\u0297')); // ( 663) X
      f.add(new Character('\u0298')); // ( 664) X
      f.add(new Character('\u0299')); // ( 665) X
      f.add(new Character('\u029A')); // ( 666) X
      f.add(new Character('\u029B')); // ( 667) X
      f.add(new Character('\u029C')); // ( 668) X
      f.add(new Character('\u029D')); // ( 669) X
      f.add(new Character('\u029E')); // ( 670) X
      f.add(new Character('\u029F')); // ( 671) X
      f.add(new Character('\u02A0')); // ( 672) X
      f.add(new Character('\u02A1')); // ( 673) X
      f.add(new Character('\u02A2')); // ( 674) X
      f.add(new Character('\u02A3')); // ( 675) X
      f.add(new Character('\u02A4')); // ( 676) X
      f.add(new Character('\u02A5')); // ( 677) X
      f.add(new Character('\u02A6')); // ( 678) X
      f.add(new Character('\u02A7')); // ( 679) X
      f.add(new Character('\u02A8')); // ( 680) X
      f.add(new Character('\u02A9')); // ( 681) X
      f.add(new Character('\u02AA')); // ( 682) X
      f.add(new Character('\u02AB')); // ( 683) X
      f.add(new Character('\u02AC')); // ( 684) X
      f.add(new Character('\u02AD')); // ( 685) X
      f.add(new Character('\u02AE')); // ( 686) X
      f.add(new Character('\u02AF')); // ( 687) X
      f.add(new Character('\u02B0')); // ( 688) X
      f.add(new Character('\u02B1')); // ( 689) X
      f.add(new Character('\u02B2')); // ( 690) X
      f.add(new Character('\u02B3')); // ( 691) X
      f.add(new Character('\u02B4')); // ( 692) X
      f.add(new Character('\u02B5')); // ( 693) X
      f.add(new Character('\u02B6')); // ( 694) X
      f.add(new Character('\u02B7')); // ( 695) X
      f.add(new Character('\u02B8')); // ( 696) X
      f.add(new Character('\u02B9')); // ( 697) X
      f.add(new Character('\u02BA')); // ( 698) X
      f.add(new Character('\u02BB')); // ( 699) X
      f.add(new Character('\u02BC')); // ( 700) X
      f.add(new Character('\u02BD')); // ( 701) X
      f.add(new Character('\u02BE')); // ( 702) X
      f.add(new Character('\u02BF')); // ( 703) X
      f.add(new Character('\u02C0')); // ( 704) X
      f.add(new Character('\u02C1')); // ( 705) X
      f.add(new Character('\u02C2')); // ( 706) X
      f.add(new Character('\u02C3')); // ( 707) X
      f.add(new Character('\u02C4')); // ( 708) X
      f.add(new Character('\u02C5')); // ( 709) X
      f.add(new Character('\u02C6')); // ( 710) X
      f.add(new Character('\u02C7')); // ( 711) X
      f.add(new Character('\u02C8')); // ( 712) X
      f.add(new Character('\u02C9')); // ( 713) X
      f.add(new Character('\u02CA')); // ( 714) X
      f.add(new Character('\u02CB')); // ( 715) X
      f.add(new Character('\u02CC')); // ( 716) X
      f.add(new Character('\u02CD')); // ( 717) X
      f.add(new Character('\u02CE')); // ( 718) X
      f.add(new Character('\u02CF')); // ( 719) X
      f.add(new Character('\u02D0')); // ( 720) X
      f.add(new Character('\u02D1')); // ( 721) X
      f.add(new Character('\u02D2')); // ( 722) X
      f.add(new Character('\u02D3')); // ( 723) X
      f.add(new Character('\u02D4')); // ( 724) X
      f.add(new Character('\u02D5')); // ( 725) X
      f.add(new Character('\u02D6')); // ( 726) X
      f.add(new Character('\u02D7')); // ( 727) X
      f.add(new Character('\u02D8')); // ( 728) X
      f.add(new Character('\u02D9')); // ( 729) X
      f.add(new Character('\u02DA')); // ( 730) X
      f.add(new Character('\u02DB')); // ( 731) X
      f.add(new Character('\u02DC')); // ( 732) X
      f.add(new Character('\u02DD')); // ( 733) X
      f.add(new Character('\u02DE')); // ( 734) X
      f.add(new Character('\u02DF')); // ( 735) X
      f.add(new Character('\u02E0')); // ( 736) X
      f.add(new Character('\u02E1')); // ( 737) X
      f.add(new Character('\u02E2')); // ( 738) X
      f.add(new Character('\u02E3')); // ( 739) X
      f.add(new Character('\u02E4')); // ( 740) X
      f.add(new Character('\u02E5')); // ( 741) X
      f.add(new Character('\u02E6')); // ( 742) X
      f.add(new Character('\u02E7')); // ( 743) X
      f.add(new Character('\u02E8')); // ( 744) X
      f.add(new Character('\u02E9')); // ( 745) X
      f.add(new Character('\u02EA')); // ( 746) X
      f.add(new Character('\u02EB')); // ( 747) X
      f.add(new Character('\u02EC')); // ( 748) X
      f.add(new Character('\u02ED')); // ( 749) X
      f.add(new Character('\u02EE')); // ( 750) X
      f.add(new Character('\u02EF')); // ( 751) X
      f.add(new Character('\u02F0')); // ( 752) X
      f.add(new Character('\u02F1')); // ( 753) X
      f.add(new Character('\u02F2')); // ( 754) X
      f.add(new Character('\u02F3')); // ( 755) X
      f.add(new Character('\u02F4')); // ( 756) X
      f.add(new Character('\u02F5')); // ( 757) X
      f.add(new Character('\u02F6')); // ( 758) X
      f.add(new Character('\u02F7')); // ( 759) X
      f.add(new Character('\u02F8')); // ( 760) X
      f.add(new Character('\u02F9')); // ( 761) X
      f.add(new Character('\u02FA')); // ( 762) X
      f.add(new Character('\u02FB')); // ( 763) X
      f.add(new Character('\u02FC')); // ( 764) X
      f.add(new Character('\u02FD')); // ( 765) X
      f.add(new Character('\u02FE')); // ( 766) X
      f.add(new Character('\u02FF')); // ( 767) X
      f.add(new Character('\u0300')); // ( 768) X
      f.add(new Character('\u0301')); // ( 769) X
      f.add(new Character('\u0302')); // ( 770) X
      f.add(new Character('\u0303')); // ( 771) X
      f.add(new Character('\u0304')); // ( 772) X
      f.add(new Character('\u0305')); // ( 773) X
      f.add(new Character('\u0306')); // ( 774) X
      f.add(new Character('\u0307')); // ( 775) X
      f.add(new Character('\u0308')); // ( 776) X
      f.add(new Character('\u0309')); // ( 777) X
      f.add(new Character('\u030A')); // ( 778) X
      f.add(new Character('\u030B')); // ( 779) X
      f.add(new Character('\u030C')); // ( 780) X
      f.add(new Character('\u030D')); // ( 781) X
      f.add(new Character('\u030E')); // ( 782) X
      f.add(new Character('\u030F')); // ( 783) X
      f.add(new Character('\u0310')); // ( 784) X
      f.add(new Character('\u0311')); // ( 785) X
      f.add(new Character('\u0312')); // ( 786) X
      f.add(new Character('\u0313')); // ( 787) X
      f.add(new Character('\u0314')); // ( 788) X
      f.add(new Character('\u0315')); // ( 789) X
      f.add(new Character('\u0316')); // ( 790) X
      f.add(new Character('\u0317')); // ( 791) X
      f.add(new Character('\u0318')); // ( 792) X
      f.add(new Character('\u0319')); // ( 793) X
      f.add(new Character('\u031A')); // ( 794) X
      f.add(new Character('\u031B')); // ( 795) X
      f.add(new Character('\u031C')); // ( 796) X
      f.add(new Character('\u031D')); // ( 797) X
      f.add(new Character('\u031E')); // ( 798) X
      f.add(new Character('\u031F')); // ( 799) X
      f.add(new Character('\u0320')); // ( 800) X
      f.add(new Character('\u0321')); // ( 801) X
      f.add(new Character('\u0322')); // ( 802) X
      f.add(new Character('\u0323')); // ( 803) X
      f.add(new Character('\u0324')); // ( 804) X
      f.add(new Character('\u0325')); // ( 805) X
      f.add(new Character('\u0326')); // ( 806) X
      f.add(new Character('\u0327')); // ( 807) X
      f.add(new Character('\u0328')); // ( 808) X
      f.add(new Character('\u0329')); // ( 809) X
      f.add(new Character('\u032A')); // ( 810) X
      f.add(new Character('\u032B')); // ( 811) X
      f.add(new Character('\u032C')); // ( 812) X
      f.add(new Character('\u032D')); // ( 813) X
      f.add(new Character('\u032E')); // ( 814) X
      f.add(new Character('\u032F')); // ( 815) X
      f.add(new Character('\u0330')); // ( 816) X
      f.add(new Character('\u0331')); // ( 817) X
      f.add(new Character('\u0332')); // ( 818) X
      f.add(new Character('\u0333')); // ( 819) X
      f.add(new Character('\u0334')); // ( 820) X
      f.add(new Character('\u0335')); // ( 821) X
      f.add(new Character('\u0336')); // ( 822) X
      f.add(new Character('\u0337')); // ( 823) X
      f.add(new Character('\u0338')); // ( 824) X
      f.add(new Character('\u0339')); // ( 825) X
      f.add(new Character('\u033A')); // ( 826) X
      f.add(new Character('\u033B')); // ( 827) X
      f.add(new Character('\u033C')); // ( 828) X
      f.add(new Character('\u033D')); // ( 829) X
      f.add(new Character('\u033E')); // ( 830) X
      f.add(new Character('\u033F')); // ( 831) X
      f.add(new Character('\u0340')); // ( 832) X
      f.add(new Character('\u0341')); // ( 833) X
      f.add(new Character('\u0342')); // ( 834) X
      f.add(new Character('\u0343')); // ( 835) X
      f.add(new Character('\u0344')); // ( 836) X
      f.add(new Character('\u0345')); // ( 837) X
      f.add(new Character('\u0346')); // ( 838) X
      f.add(new Character('\u0347')); // ( 839) X
      f.add(new Character('\u0348')); // ( 840) X
      f.add(new Character('\u0349')); // ( 841) X
      f.add(new Character('\u034A')); // ( 842) X
      f.add(new Character('\u034B')); // ( 843) X
      f.add(new Character('\u034C')); // ( 844) X
      f.add(new Character('\u034D')); // ( 845) X
      f.add(new Character('\u034E')); // ( 846) X
      f.add(new Character('\u034F')); // ( 847) X
      f.add(new Character('\u0350')); // ( 848) X
      f.add(new Character('\u0351')); // ( 849) X
      f.add(new Character('\u0352')); // ( 850) X
      f.add(new Character('\u0353')); // ( 851) X
      f.add(new Character('\u0354')); // ( 852) X
      f.add(new Character('\u0355')); // ( 853) X
      f.add(new Character('\u0356')); // ( 854) X
      f.add(new Character('\u0357')); // ( 855) X
      f.add(new Character('\u0358')); // ( 856) X
      f.add(new Character('\u0359')); // ( 857) X
      f.add(new Character('\u035A')); // ( 858) X
      f.add(new Character('\u035B')); // ( 859) X
      f.add(new Character('\u035C')); // ( 860) X
      f.add(new Character('\u035D')); // ( 861) X
      f.add(new Character('\u035E')); // ( 862) X
      f.add(new Character('\u035F')); // ( 863) X
      f.add(new Character('\u0360')); // ( 864) X
      f.add(new Character('\u0361')); // ( 865) X
      f.add(new Character('\u0362')); // ( 866) X
      f.add(new Character('\u0363')); // ( 867) X
      f.add(new Character('\u0364')); // ( 868) X
      f.add(new Character('\u0365')); // ( 869) X
      f.add(new Character('\u0366')); // ( 870) X
      f.add(new Character('\u0367')); // ( 871) X
      f.add(new Character('\u0368')); // ( 872) X
      f.add(new Character('\u0369')); // ( 873) X
      f.add(new Character('\u036A')); // ( 874) X
      f.add(new Character('\u036B')); // ( 875) X
      f.add(new Character('\u036C')); // ( 876) X
      f.add(new Character('\u036D')); // ( 877) X
      f.add(new Character('\u036E')); // ( 878) X
      f.add(new Character('\u036F')); // ( 879) X
      f.add(new Character('\u0370')); // ( 880) X
      f.add(new Character('\u0371')); // ( 881) X
      f.add(new Character('\u0372')); // ( 882) X
      f.add(new Character('\u0373')); // ( 883) X
      f.add(new Character('\u0374')); // ( 884) X
      f.add(new Character('\u0375')); // ( 885) X
      f.add(new Character('\u0376')); // ( 886) X
      f.add(new Character('\u0377')); // ( 887) X
      f.add(new Character('\u0378')); // ( 888) X
      f.add(new Character('\u0379')); // ( 889) X
      f.add(new Character('\u037A')); // ( 890) X
      f.add(new Character('\u037B')); // ( 891) X
      f.add(new Character('\u037C')); // ( 892) X
      f.add(new Character('\u037D')); // ( 893) X
      f.add(new Character('\u037E')); // ( 894) X
      f.add(new Character('\u037F')); // ( 895) X
      f.add(new Character('\u0380')); // ( 896) X
      f.add(new Character('\u0381')); // ( 897) X
      f.add(new Character('\u0382')); // ( 898) X
      f.add(new Character('\u0383')); // ( 899) X
      f.add(new Character('\u0384')); // ( 900) X
      f.add(new Character('\u0385')); // ( 901) X
      f.add(new Character('\u0386')); // ( 902) X
      f.add(new Character('\u0387')); // ( 903) X
      f.add(new Character('\u0388')); // ( 904) X
      f.add(new Character('\u0389')); // ( 905) X
      f.add(new Character('\u038A')); // ( 906) X
      f.add(new Character('\u038B')); // ( 907) X
      f.add(new Character('\u038C')); // ( 908) X
      f.add(new Character('\u038D')); // ( 909) X
      f.add(new Character('\u038E')); // ( 910) X
      f.add(new Character('\u038F')); // ( 911) X
      f.add(new Character('\u0390')); // ( 912) X
      f.add(new Character('\u0391')); // ( 913) X
      f.add(new Character('\u0392')); // ( 914) X
      f.add(new Character('\u0393')); // ( 915) X
      f.add(new Character('\u0394')); // ( 916) X
      f.add(new Character('\u0395')); // ( 917) X
      f.add(new Character('\u0396')); // ( 918) X
      f.add(new Character('\u0397')); // ( 919) X
      f.add(new Character('\u0398')); // ( 920) X
      f.add(new Character('\u0399')); // ( 921) X
      f.add(new Character('\u039A')); // ( 922) X
      f.add(new Character('\u039B')); // ( 923) X
      f.add(new Character('\u039C')); // ( 924) X
      f.add(new Character('\u039D')); // ( 925) X
      f.add(new Character('\u039E')); // ( 926) X
      f.add(new Character('\u039F')); // ( 927) X
      f.add(new Character('\u03A0')); // ( 928) X
      f.add(new Character('\u03A1')); // ( 929) X
      f.add(new Character('\u03A2')); // ( 930) X
      f.add(new Character('\u03A3')); // ( 931) X
      f.add(new Character('\u03A4')); // ( 932) X
      f.add(new Character('\u03A5')); // ( 933) X
      f.add(new Character('\u03A6')); // ( 934) X
      f.add(new Character('\u03A7')); // ( 935) X
      f.add(new Character('\u03A8')); // ( 936) X
      f.add(new Character('\u03A9')); // ( 937) X
      f.add(new Character('\u03AA')); // ( 938) X
      f.add(new Character('\u03AB')); // ( 939) X
      f.add(new Character('\u03AC')); // ( 940) X
      f.add(new Character('\u03AD')); // ( 941) X
      f.add(new Character('\u03AE')); // ( 942) X
      f.add(new Character('\u03AF')); // ( 943) X
      f.add(new Character('\u03B0')); // ( 944) X
      f.add(new Character('\u03B1')); // ( 945) X
      f.add(new Character('\u03B2')); // ( 946) X
      f.add(new Character('\u03B3')); // ( 947) X
      f.add(new Character('\u03B4')); // ( 948) X
      f.add(new Character('\u03B5')); // ( 949) X
      f.add(new Character('\u03B6')); // ( 950) X
      f.add(new Character('\u03B7')); // ( 951) X
      f.add(new Character('\u03B8')); // ( 952) X
      f.add(new Character('\u03B9')); // ( 953) X
      f.add(new Character('\u03BA')); // ( 954) X
      f.add(new Character('\u03BB')); // ( 955) X
      f.add(new Character('\u03BC')); // ( 956) X
      f.add(new Character('\u03BD')); // ( 957) X
      f.add(new Character('\u03BE')); // ( 958) X
      f.add(new Character('\u03BF')); // ( 959) X
      f.add(new Character('\u03C0')); // ( 960) X
      f.add(new Character('\u03C1')); // ( 961) X
      f.add(new Character('\u03C2')); // ( 962) X
      f.add(new Character('\u03C3')); // ( 963) X
      f.add(new Character('\u03C4')); // ( 964) X
      f.add(new Character('\u03C5')); // ( 965) X
      f.add(new Character('\u03C6')); // ( 966) X
      f.add(new Character('\u03C7')); // ( 967) X
      f.add(new Character('\u03C8')); // ( 968) X
      f.add(new Character('\u03C9')); // ( 969) X
      f.add(new Character('\u03CA')); // ( 970) X
      f.add(new Character('\u03CB')); // ( 971) X
      f.add(new Character('\u03CC')); // ( 972) X
      f.add(new Character('\u03CD')); // ( 973) X
      f.add(new Character('\u03CE')); // ( 974) X
      f.add(new Character('\u03CF')); // ( 975) X
      f.add(new Character('\u03D0')); // ( 976) X
      f.add(new Character('\u03D1')); // ( 977) X
      f.add(new Character('\u03D2')); // ( 978) X
      f.add(new Character('\u03D3')); // ( 979) X
      f.add(new Character('\u03D4')); // ( 980) X
      f.add(new Character('\u03D5')); // ( 981) X
      f.add(new Character('\u03D6')); // ( 982) X
      f.add(new Character('\u03D7')); // ( 983) X
      f.add(new Character('\u03D8')); // ( 984) X
      f.add(new Character('\u03D9')); // ( 985) X
      f.add(new Character('\u03DA')); // ( 986) X
      f.add(new Character('\u03DB')); // ( 987) X
      f.add(new Character('\u03DC')); // ( 988) X
      f.add(new Character('\u03DD')); // ( 989) X
      f.add(new Character('\u03DE')); // ( 990) X
      f.add(new Character('\u03DF')); // ( 991) X
      f.add(new Character('\u03E0')); // ( 992) X
      f.add(new Character('\u03E1')); // ( 993) X
      f.add(new Character('\u03E2')); // ( 994) X
      f.add(new Character('\u03E3')); // ( 995) X
      f.add(new Character('\u03E4')); // ( 996) X
      f.add(new Character('\u03E5')); // ( 997) X
      f.add(new Character('\u03E6')); // ( 998) X
      f.add(new Character('\u03E7')); // ( 999) X
      f.add(new Character('\u03E8')); // (1000) X
      f.add(new Character('\u03E9')); // (1001) X
      f.add(new Character('\u03EA')); // (1002) X
      f.add(new Character('\u03EB')); // (1003) X
      f.add(new Character('\u03EC')); // (1004) X
      f.add(new Character('\u03ED')); // (1005) X
      f.add(new Character('\u03EE')); // (1006) X
      f.add(new Character('\u03EF')); // (1007) X
      f.add(new Character('\u03F0')); // (1008) X
      f.add(new Character('\u03F1')); // (1009) X
      f.add(new Character('\u03F2')); // (1010) X
      f.add(new Character('\u03F3')); // (1011) X
      f.add(new Character('\u03F4')); // (1012) X
      f.add(new Character('\u03F5')); // (1013) X
      f.add(new Character('\u03F6')); // (1014) X
      f.add(new Character('\u03F7')); // (1015) X
      f.add(new Character('\u03F8')); // (1016) X
      f.add(new Character('\u03F9')); // (1017) X
      f.add(new Character('\u03FA')); // (1018) X
      f.add(new Character('\u03FB')); // (1019) X
      f.add(new Character('\u03FC')); // (1020) X
      f.add(new Character('\u03FD')); // (1021) X
      f.add(new Character('\u03FE')); // (1022) X
      f.add(new Character('\u03FF')); // (1023) X
      f.add(new Character('\u0400')); // (1024) X
      f.add(new Character('\u0401')); // (1025) X
      f.add(new Character('\u0402')); // (1026) X
      f.add(new Character('\u0403')); // (1027) X
      f.add(new Character('\u0404')); // (1028) X
      f.add(new Character('\u0405')); // (1029) X
      f.add(new Character('\u0406')); // (1030) X
      f.add(new Character('\u0407')); // (1031) X
      f.add(new Character('\u0408')); // (1032) X
      f.add(new Character('\u0409')); // (1033) X
      f.add(new Character('\u040A')); // (1034) X
      f.add(new Character('\u040B')); // (1035) X
      f.add(new Character('\u040C')); // (1036) X
      f.add(new Character('\u040D')); // (1037) X
      f.add(new Character('\u040E')); // (1038) X
      f.add(new Character('\u040F')); // (1039) X
      f.add(new Character('\u0410')); // (1040) X
      f.add(new Character('\u0411')); // (1041) X
      f.add(new Character('\u0412')); // (1042) X
      f.add(new Character('\u0413')); // (1043) X
      f.add(new Character('\u0414')); // (1044) X
      f.add(new Character('\u0415')); // (1045) X
      f.add(new Character('\u0416')); // (1046) X
      f.add(new Character('\u0417')); // (1047) X
      f.add(new Character('\u0418')); // (1048) X
      f.add(new Character('\u0419')); // (1049) X
      f.add(new Character('\u041A')); // (1050) X
      f.add(new Character('\u041B')); // (1051) X
      f.add(new Character('\u041C')); // (1052) X
      f.add(new Character('\u041D')); // (1053) X
      f.add(new Character('\u041E')); // (1054) X
      f.add(new Character('\u041F')); // (1055) X
      f.add(new Character('\u0420')); // (1056) X
      f.add(new Character('\u0421')); // (1057) X
      f.add(new Character('\u0422')); // (1058) X
      f.add(new Character('\u0423')); // (1059) X
      f.add(new Character('\u0424')); // (1060) X
      f.add(new Character('\u0425')); // (1061) X
      f.add(new Character('\u0426')); // (1062) X
      f.add(new Character('\u0427')); // (1063) X
      f.add(new Character('\u0428')); // (1064) X
      f.add(new Character('\u0429')); // (1065) X
      f.add(new Character('\u042A')); // (1066) X
      f.add(new Character('\u042B')); // (1067) X
      f.add(new Character('\u042C')); // (1068) X
      f.add(new Character('\u042D')); // (1069) X
      f.add(new Character('\u042E')); // (1070) X
      f.add(new Character('\u042F')); // (1071) X
      f.add(new Character('\u0430')); // (1072) X
      f.add(new Character('\u0431')); // (1073) X
      f.add(new Character('\u0432')); // (1074) X
      f.add(new Character('\u0433')); // (1075) X
      f.add(new Character('\u0434')); // (1076) X
      f.add(new Character('\u0435')); // (1077) X
      f.add(new Character('\u0436')); // (1078) X
      f.add(new Character('\u0437')); // (1079) X
      f.add(new Character('\u0438')); // (1080) X
      f.add(new Character('\u0439')); // (1081) X
      f.add(new Character('\u043A')); // (1082) X
      f.add(new Character('\u043B')); // (1083) X
      f.add(new Character('\u043C')); // (1084) X
      f.add(new Character('\u043D')); // (1085) X
      f.add(new Character('\u043E')); // (1086) X
      f.add(new Character('\u043F')); // (1087) X
      f.add(new Character('\u0440')); // (1088) X
      f.add(new Character('\u0441')); // (1089) X
      f.add(new Character('\u0442')); // (1090) X
      f.add(new Character('\u0443')); // (1091) X
      f.add(new Character('\u0444')); // (1092) X
      f.add(new Character('\u0445')); // (1093) X
      f.add(new Character('\u0446')); // (1094) X
      f.add(new Character('\u0447')); // (1095) X
      f.add(new Character('\u0448')); // (1096) X
      f.add(new Character('\u0449')); // (1097) X
      f.add(new Character('\u044A')); // (1098) X
      f.add(new Character('\u044B')); // (1099) X
      f.add(new Character('\u044C')); // (1100) X
      f.add(new Character('\u044D')); // (1101) X
      f.add(new Character('\u044E')); // (1102) X
      f.add(new Character('\u044F')); // (1103) X
      f.add(new Character('\u0450')); // (1104) X
      f.add(new Character('\u0451')); // (1105) X
      f.add(new Character('\u0452')); // (1106) X
      f.add(new Character('\u0453')); // (1107) X
      f.add(new Character('\u0454')); // (1108) X
      f.add(new Character('\u0455')); // (1109) X
      f.add(new Character('\u0456')); // (1110) X
      f.add(new Character('\u0457')); // (1111) X
      f.add(new Character('\u0458')); // (1112) X
      f.add(new Character('\u0459')); // (1113) X
      f.add(new Character('\u045A')); // (1114) X
      f.add(new Character('\u045B')); // (1115) X
      f.add(new Character('\u045C')); // (1116) X
      f.add(new Character('\u045D')); // (1117) X
      f.add(new Character('\u045E')); // (1118) X
      f.add(new Character('\u045F')); // (1119) X
      f.add(new Character('\u0460')); // (1120) X
      f.add(new Character('\u0461')); // (1121) X
      f.add(new Character('\u0462')); // (1122) X
      f.add(new Character('\u0463')); // (1123) X
      f.add(new Character('\u0464')); // (1124) X
      f.add(new Character('\u0465')); // (1125) X
      f.add(new Character('\u0466')); // (1126) X
      f.add(new Character('\u0467')); // (1127) X
      f.add(new Character('\u0468')); // (1128) X
      f.add(new Character('\u0469')); // (1129) X
      f.add(new Character('\u046A')); // (1130) X
      f.add(new Character('\u046B')); // (1131) X
      f.add(new Character('\u046C')); // (1132) X
      f.add(new Character('\u046D')); // (1133) X
      f.add(new Character('\u046E')); // (1134) X
      f.add(new Character('\u046F')); // (1135) X
      f.add(new Character('\u0470')); // (1136) X
      f.add(new Character('\u0471')); // (1137) X
      f.add(new Character('\u0472')); // (1138) X
      f.add(new Character('\u0473')); // (1139) X
      f.add(new Character('\u0474')); // (1140) X
      f.add(new Character('\u0475')); // (1141) X
      f.add(new Character('\u0476')); // (1142) X
      f.add(new Character('\u0477')); // (1143) X
      f.add(new Character('\u0478')); // (1144) X
      f.add(new Character('\u0479')); // (1145) X
      f.add(new Character('\u047A')); // (1146) X
      f.add(new Character('\u047B')); // (1147) X
      f.add(new Character('\u047C')); // (1148) X
      f.add(new Character('\u047D')); // (1149) X
      f.add(new Character('\u047E')); // (1150) X
      f.add(new Character('\u047F')); // (1151) X
      f.add(new Character('\u0480')); // (1152) X
      f.add(new Character('\u0481')); // (1153) X
      f.add(new Character('\u0482')); // (1154) X
      f.add(new Character('\u0483')); // (1155) X
      f.add(new Character('\u0484')); // (1156) X
      f.add(new Character('\u0485')); // (1157) X
      f.add(new Character('\u0486')); // (1158) X
      f.add(new Character('\u0487')); // (1159) X
      f.add(new Character('\u0488')); // (1160) X
      f.add(new Character('\u0489')); // (1161) X
      f.add(new Character('\u048A')); // (1162) X
      f.add(new Character('\u048B')); // (1163) X
      f.add(new Character('\u048C')); // (1164) X
      f.add(new Character('\u048D')); // (1165) X
      f.add(new Character('\u048E')); // (1166) X
      f.add(new Character('\u048F')); // (1167) X
      f.add(new Character('\u0490')); // (1168) X
      f.add(new Character('\u0491')); // (1169) X
      f.add(new Character('\u0492')); // (1170) X
      f.add(new Character('\u0493')); // (1171) X
      f.add(new Character('\u0494')); // (1172) X
      f.add(new Character('\u0495')); // (1173) X
      f.add(new Character('\u0496')); // (1174) X
      f.add(new Character('\u0497')); // (1175) X
      f.add(new Character('\u0498')); // (1176) X
      f.add(new Character('\u0499')); // (1177) X
      f.add(new Character('\u049A')); // (1178) X
      f.add(new Character('\u049B')); // (1179) X
      f.add(new Character('\u049C')); // (1180) X
      f.add(new Character('\u049D')); // (1181) X
      f.add(new Character('\u049E')); // (1182) X
      f.add(new Character('\u049F')); // (1183) X
      f.add(new Character('\u04A0')); // (1184) X
      f.add(new Character('\u04A1')); // (1185) X
      f.add(new Character('\u04A2')); // (1186) X
      f.add(new Character('\u04A3')); // (1187) X
      f.add(new Character('\u04A4')); // (1188) X
      f.add(new Character('\u04A5')); // (1189) X
      f.add(new Character('\u04A6')); // (1190) X
      f.add(new Character('\u04A7')); // (1191) X
      f.add(new Character('\u04A8')); // (1192) X
      f.add(new Character('\u04A9')); // (1193) X
      f.add(new Character('\u04AA')); // (1194) X
      f.add(new Character('\u04AB')); // (1195) X
      f.add(new Character('\u04AC')); // (1196) X
      f.add(new Character('\u04AD')); // (1197) X
      f.add(new Character('\u04AE')); // (1198) X
      f.add(new Character('\u04AF')); // (1199) X
      f.add(new Character('\u04B0')); // (1200) X
      f.add(new Character('\u04B1')); // (1201) X
      f.add(new Character('\u04B2')); // (1202) X
      f.add(new Character('\u04B3')); // (1203) X
      f.add(new Character('\u04B4')); // (1204) X
      f.add(new Character('\u04B5')); // (1205) X
      f.add(new Character('\u04B6')); // (1206) X
      f.add(new Character('\u04B7')); // (1207) X
      f.add(new Character('\u04B8')); // (1208) X
      f.add(new Character('\u04B9')); // (1209) X
      f.add(new Character('\u04BA')); // (1210) X
      f.add(new Character('\u04BB')); // (1211) X
      f.add(new Character('\u04BC')); // (1212) X
      f.add(new Character('\u04BD')); // (1213) X
      f.add(new Character('\u04BE')); // (1214) X
      f.add(new Character('\u04BF')); // (1215) X
      f.add(new Character('\u04C0')); // (1216) X
      f.add(new Character('\u04C1')); // (1217) X
      f.add(new Character('\u04C2')); // (1218) X
      f.add(new Character('\u04C3')); // (1219) X
      f.add(new Character('\u04C4')); // (1220) X
      f.add(new Character('\u04C5')); // (1221) X
      f.add(new Character('\u04C6')); // (1222) X
      f.add(new Character('\u04C7')); // (1223) X
      f.add(new Character('\u04C8')); // (1224) X
      f.add(new Character('\u04C9')); // (1225) X
      f.add(new Character('\u04CA')); // (1226) X
      f.add(new Character('\u04CB')); // (1227) X
      f.add(new Character('\u04CC')); // (1228) X
      f.add(new Character('\u04CD')); // (1229) X
      f.add(new Character('\u04CE')); // (1230) X
      f.add(new Character('\u04CF')); // (1231) X
      f.add(new Character('\u04D0')); // (1232) X
      f.add(new Character('\u04D1')); // (1233) X
      f.add(new Character('\u04D2')); // (1234) X
      f.add(new Character('\u04D3')); // (1235) X
      f.add(new Character('\u04D4')); // (1236) X
      f.add(new Character('\u04D5')); // (1237) X
      f.add(new Character('\u04D6')); // (1238) X
      f.add(new Character('\u04D7')); // (1239) X
      f.add(new Character('\u04D8')); // (1240) X
      f.add(new Character('\u04D9')); // (1241) X
      f.add(new Character('\u04DA')); // (1242) X
      f.add(new Character('\u04DB')); // (1243) X
      f.add(new Character('\u04DC')); // (1244) X
      f.add(new Character('\u04DD')); // (1245) X
      f.add(new Character('\u04DE')); // (1246) X
      f.add(new Character('\u04DF')); // (1247) X
      f.add(new Character('\u04E0')); // (1248) X
      f.add(new Character('\u04E1')); // (1249) X
      f.add(new Character('\u04E2')); // (1250) X
      f.add(new Character('\u04E3')); // (1251) X
      f.add(new Character('\u04E4')); // (1252) X
      f.add(new Character('\u04E5')); // (1253) X
      f.add(new Character('\u04E6')); // (1254) X
      f.add(new Character('\u04E7')); // (1255) X
      f.add(new Character('\u04E8')); // (1256) X
      f.add(new Character('\u04E9')); // (1257) X
      f.add(new Character('\u04EA')); // (1258) X
      f.add(new Character('\u04EB')); // (1259) X
      f.add(new Character('\u04EC')); // (1260) X
      f.add(new Character('\u04ED')); // (1261) X
      f.add(new Character('\u04EE')); // (1262) X
      f.add(new Character('\u04EF')); // (1263) X
      f.add(new Character('\u04F0')); // (1264) X
      f.add(new Character('\u04F1')); // (1265) X
      f.add(new Character('\u04F2')); // (1266) X
      f.add(new Character('\u04F3')); // (1267) X
      f.add(new Character('\u04F4')); // (1268) X
      f.add(new Character('\u04F5')); // (1269) X
      f.add(new Character('\u04F6')); // (1270) X
      f.add(new Character('\u04F7')); // (1271) X
      f.add(new Character('\u04F8')); // (1272) X
      f.add(new Character('\u04F9')); // (1273) X
      f.add(new Character('\u04FA')); // (1274) X
      f.add(new Character('\u04FB')); // (1275) X
      f.add(new Character('\u04FC')); // (1276) X
      f.add(new Character('\u04FD')); // (1277) X
      f.add(new Character('\u04FE')); // (1278) X
      f.add(new Character('\u04FF')); // (1279) X
      f.add(new Character('\u0500')); // (1280) X
      f.add(new Character('\u0501')); // (1281) X
      f.add(new Character('\u0502')); // (1282) X
      f.add(new Character('\u0503')); // (1283) X
      f.add(new Character('\u0504')); // (1284) X
      f.add(new Character('\u0505')); // (1285) X
      f.add(new Character('\u0506')); // (1286) X
      f.add(new Character('\u0507')); // (1287) X
      f.add(new Character('\u0508')); // (1288) X
      f.add(new Character('\u0509')); // (1289) X
      f.add(new Character('\u050A')); // (1290) X
      f.add(new Character('\u050B')); // (1291) X
      f.add(new Character('\u050C')); // (1292) X
      f.add(new Character('\u050D')); // (1293) X
      f.add(new Character('\u050E')); // (1294) X
      f.add(new Character('\u050F')); // (1295) X
      f.add(new Character('\u0510')); // (1296) X
      f.add(new Character('\u0511')); // (1297) X
      f.add(new Character('\u0512')); // (1298) X
      f.add(new Character('\u0513')); // (1299) X
      f.add(new Character('\u0514')); // (1300) X
      f.add(new Character('\u0515')); // (1301) X
      f.add(new Character('\u0516')); // (1302) X
      f.add(new Character('\u0517')); // (1303) X
      f.add(new Character('\u0518')); // (1304) X
      f.add(new Character('\u0519')); // (1305) X
      f.add(new Character('\u051A')); // (1306) X
      f.add(new Character('\u051B')); // (1307) X
      f.add(new Character('\u051C')); // (1308) X
      f.add(new Character('\u051D')); // (1309) X
      f.add(new Character('\u051E')); // (1310) X
      f.add(new Character('\u051F')); // (1311) X
      f.add(new Character('\u0520')); // (1312) X
      f.add(new Character('\u0521')); // (1313) X
      f.add(new Character('\u0522')); // (1314) X
      f.add(new Character('\u0523')); // (1315) X
      f.add(new Character('\u0524')); // (1316) X
      f.add(new Character('\u0525')); // (1317) X
      f.add(new Character('\u0526')); // (1318) X
      f.add(new Character('\u0527')); // (1319) X
      f.add(new Character('\u0528')); // (1320) X
      f.add(new Character('\u0529')); // (1321) X
      f.add(new Character('\u052A')); // (1322) X
      f.add(new Character('\u052B')); // (1323) X
      f.add(new Character('\u052C')); // (1324) X
      f.add(new Character('\u052D')); // (1325) X
      f.add(new Character('\u052E')); // (1326) X
      f.add(new Character('\u052F')); // (1327) X
      f.add(new Character('\u0530')); // (1328) X
      f.add(new Character('\u0531')); // (1329) X
      f.add(new Character('\u0532')); // (1330) X
      f.add(new Character('\u0533')); // (1331) X
      f.add(new Character('\u0534')); // (1332) X
      f.add(new Character('\u0535')); // (1333) X
      f.add(new Character('\u0536')); // (1334) X
      f.add(new Character('\u0537')); // (1335) X
      f.add(new Character('\u0538')); // (1336) X
      f.add(new Character('\u0539')); // (1337) X
      f.add(new Character('\u053A')); // (1338) X
      f.add(new Character('\u053B')); // (1339) X
      f.add(new Character('\u053C')); // (1340) X
      f.add(new Character('\u053D')); // (1341) X
      f.add(new Character('\u053E')); // (1342) X
      f.add(new Character('\u053F')); // (1343) X
      f.add(new Character('\u0540')); // (1344) X
      f.add(new Character('\u0541')); // (1345) X
      f.add(new Character('\u0542')); // (1346) X
      f.add(new Character('\u0543')); // (1347) X
      f.add(new Character('\u0544')); // (1348) X
      f.add(new Character('\u0545')); // (1349) X
      f.add(new Character('\u0546')); // (1350) X
      f.add(new Character('\u0547')); // (1351) X
      f.add(new Character('\u0548')); // (1352) X
      f.add(new Character('\u0549')); // (1353) X
      f.add(new Character('\u054A')); // (1354) X
      f.add(new Character('\u054B')); // (1355) X
      f.add(new Character('\u054C')); // (1356) X
      f.add(new Character('\u054D')); // (1357) X
      f.add(new Character('\u054E')); // (1358) X
      f.add(new Character('\u054F')); // (1359) X
      f.add(new Character('\u0550')); // (1360) X
      f.add(new Character('\u0551')); // (1361) X
      f.add(new Character('\u0552')); // (1362) X
      f.add(new Character('\u0553')); // (1363) X
      f.add(new Character('\u0554')); // (1364) X
      f.add(new Character('\u0555')); // (1365) X
      f.add(new Character('\u0556')); // (1366) X
      f.add(new Character('\u0557')); // (1367) X
      f.add(new Character('\u0558')); // (1368) X
      f.add(new Character('\u0559')); // (1369) X
      f.add(new Character('\u055A')); // (1370) X
      f.add(new Character('\u055B')); // (1371) X
      f.add(new Character('\u055C')); // (1372) X
      f.add(new Character('\u055D')); // (1373) X
      f.add(new Character('\u055E')); // (1374) X
      f.add(new Character('\u055F')); // (1375) X
      f.add(new Character('\u0560')); // (1376) X
      f.add(new Character('\u0561')); // (1377) X
      f.add(new Character('\u0562')); // (1378) X
      f.add(new Character('\u0563')); // (1379) X
      f.add(new Character('\u0564')); // (1380) X
      f.add(new Character('\u0565')); // (1381) X
      f.add(new Character('\u0566')); // (1382) X
      f.add(new Character('\u0567')); // (1383) X
      f.add(new Character('\u0568')); // (1384) X
      f.add(new Character('\u0569')); // (1385) X
      f.add(new Character('\u056A')); // (1386) X
      f.add(new Character('\u056B')); // (1387) X
      f.add(new Character('\u056C')); // (1388) X
      f.add(new Character('\u056D')); // (1389) X
      f.add(new Character('\u056E')); // (1390) X
      f.add(new Character('\u056F')); // (1391) X
      f.add(new Character('\u0570')); // (1392) X
      f.add(new Character('\u0571')); // (1393) X
      f.add(new Character('\u0572')); // (1394) X
      f.add(new Character('\u0573')); // (1395) X
      f.add(new Character('\u0574')); // (1396) X
      f.add(new Character('\u0575')); // (1397) X
      f.add(new Character('\u0576')); // (1398) X
      f.add(new Character('\u0577')); // (1399) X
      f.add(new Character('\u0578')); // (1400) X
      f.add(new Character('\u0579')); // (1401) X
      f.add(new Character('\u057A')); // (1402) X
      f.add(new Character('\u057B')); // (1403) X
      f.add(new Character('\u057C')); // (1404) X
      f.add(new Character('\u057D')); // (1405) X
      f.add(new Character('\u057E')); // (1406) X
      f.add(new Character('\u057F')); // (1407) X
      f.add(new Character('\u0580')); // (1408) X
      f.add(new Character('\u0581')); // (1409) X
      f.add(new Character('\u0582')); // (1410) X
      f.add(new Character('\u0583')); // (1411) X
      f.add(new Character('\u0584')); // (1412) X
      f.add(new Character('\u0585')); // (1413) X
      f.add(new Character('\u0586')); // (1414) X
      f.add(new Character('\u0587')); // (1415) X
   }

   /**
    * Initializes ordinary characters for LUWO for the RU language.
    * @param f The HashSet
    */
   protected static void initRUFlags(HashSet<Character> f)
   {
      f.add(new Character('\u00B5')); // (  181) micro
      f.add(new Character('\u0401')); // ( 1025) X
      f.add(new Character('\u0402')); // ( 1026) X
      f.add(new Character('\u0403')); // ( 1027) X
      f.add(new Character('\u0404')); // ( 1028) X
      f.add(new Character('\u0405')); // ( 1029) X
      f.add(new Character('\u0406')); // ( 1030) X
      f.add(new Character('\u0407')); // ( 1031) X
      f.add(new Character('\u0408')); // ( 1032) X
      f.add(new Character('\u0409')); // ( 1033) X
      f.add(new Character('\u040A')); // ( 1034) X
      f.add(new Character('\u040B')); // ( 1035) X
      f.add(new Character('\u040C')); // ( 1036) X
      f.add(new Character('\u040E')); // ( 1038) X
      f.add(new Character('\u040F')); // ( 1039) X
      f.add(new Character('\u0410')); // ( 1040) X
      f.add(new Character('\u0411')); // ( 1041) X
      f.add(new Character('\u0412')); // ( 1042) X
      f.add(new Character('\u0413')); // ( 1043) X
      f.add(new Character('\u0414')); // ( 1044) X
      f.add(new Character('\u0415')); // ( 1045) X
      f.add(new Character('\u0416')); // ( 1046) X
      f.add(new Character('\u0417')); // ( 1047) X
      f.add(new Character('\u0418')); // ( 1048) X
      f.add(new Character('\u0419')); // ( 1049) X
      f.add(new Character('\u041A')); // ( 1050) X
      f.add(new Character('\u041B')); // ( 1051) X
      f.add(new Character('\u041C')); // ( 1052) X
      f.add(new Character('\u041D')); // ( 1053) X
      f.add(new Character('\u041E')); // ( 1054) X
      f.add(new Character('\u041F')); // ( 1055) X
      f.add(new Character('\u0420')); // ( 1056) X
      f.add(new Character('\u0421')); // ( 1057) X
      f.add(new Character('\u0422')); // ( 1058) X
      f.add(new Character('\u0423')); // ( 1059) X
      f.add(new Character('\u0424')); // ( 1060) X
      f.add(new Character('\u0425')); // ( 1061) X
      f.add(new Character('\u0426')); // ( 1062) X
      f.add(new Character('\u0427')); // ( 1063) X
      f.add(new Character('\u0428')); // ( 1064) X
      f.add(new Character('\u0429')); // ( 1065) X
      f.add(new Character('\u042A')); // ( 1066) X
      f.add(new Character('\u042B')); // ( 1067) X
      f.add(new Character('\u042C')); // ( 1068) X
      f.add(new Character('\u042D')); // ( 1069) X
      f.add(new Character('\u042E')); // ( 1070) X
      f.add(new Character('\u042F')); // ( 1071) X
      f.add(new Character('\u0430')); // ( 1072) X
      f.add(new Character('\u0431')); // ( 1073) X
      f.add(new Character('\u0432')); // ( 1074) X
      f.add(new Character('\u0433')); // ( 1075) X
      f.add(new Character('\u0434')); // ( 1076) X
      f.add(new Character('\u0435')); // ( 1077) X
      f.add(new Character('\u0436')); // ( 1078) X
      f.add(new Character('\u0437')); // ( 1079) X
      f.add(new Character('\u0438')); // ( 1080) X
      f.add(new Character('\u0439')); // ( 1081) X
      f.add(new Character('\u043A')); // ( 1082) X
      f.add(new Character('\u043B')); // ( 1083) X
      f.add(new Character('\u043C')); // ( 1084) X
      f.add(new Character('\u043D')); // ( 1085) X
      f.add(new Character('\u043E')); // ( 1086) X
      f.add(new Character('\u043F')); // ( 1087) X
      f.add(new Character('\u0440')); // ( 1088) X
      f.add(new Character('\u0441')); // ( 1089) X
      f.add(new Character('\u0442')); // ( 1090) X
      f.add(new Character('\u0443')); // ( 1091) X
      f.add(new Character('\u0444')); // ( 1092) X
      f.add(new Character('\u0445')); // ( 1093) X
      f.add(new Character('\u0446')); // ( 1094) X
      f.add(new Character('\u0447')); // ( 1095) X
      f.add(new Character('\u0448')); // ( 1096) X
      f.add(new Character('\u0449')); // ( 1097) X
      f.add(new Character('\u044A')); // ( 1098) X
      f.add(new Character('\u044B')); // ( 1099) X
      f.add(new Character('\u044C')); // ( 1100) X
      f.add(new Character('\u044D')); // ( 1101) X
      f.add(new Character('\u044E')); // ( 1102) X
      f.add(new Character('\u044F')); // ( 1103) X
      f.add(new Character('\u0451')); // ( 1105) X
      f.add(new Character('\u0452')); // ( 1106) X
      f.add(new Character('\u0453')); // ( 1107) X
      f.add(new Character('\u0454')); // ( 1108) X
      f.add(new Character('\u0455')); // ( 1109) X
      f.add(new Character('\u0456')); // ( 1110) X
      f.add(new Character('\u0457')); // ( 1111) X
      f.add(new Character('\u0458')); // ( 1112) X
      f.add(new Character('\u0459')); // ( 1113) X
      f.add(new Character('\u045A')); // ( 1114) X
      f.add(new Character('\u045B')); // ( 1115) X
      f.add(new Character('\u045C')); // ( 1116) X
      f.add(new Character('\u045E')); // ( 1118) X
      f.add(new Character('\u045F')); // ( 1119) X
      f.add(new Character('\u0490')); // ( 1168) X
      f.add(new Character('\u0491')); // ( 1169) X
   }

   /**
    * Initializes ordinary characters for LUWO for the PT language.
    * @param f The HashSet
    */
   protected static void initPTFlags(HashSet<Character> f)
   {
      f.add(new Character('\u00AA')); // (  170) feminine_ordinal
      f.add(new Character('\u00BA')); // (  186) masculine_ordinal
      f.add(new Character('\u00C0')); // (  192) A_grave
      f.add(new Character('\u00C1')); // (  193) A_acute
      f.add(new Character('\u00C2')); // (  194) A_circumflex
      f.add(new Character('\u00C3')); // (  195) A_tilde
      f.add(new Character('\u00C4')); // (  196) A_umlaut
      f.add(new Character('\u00C5')); // (  197) A_ring
      f.add(new Character('\u00C6')); // (  198) AE_ligature
      f.add(new Character('\u00C7')); // (  199) C_cedilla
      f.add(new Character('\u00C8')); // (  200) E_grave
      f.add(new Character('\u00C9')); // (  201) E_acute
      f.add(new Character('\u00CA')); // (  202) E_circumflex
      f.add(new Character('\u00CB')); // (  203) E_umlaut
      f.add(new Character('\u00CC')); // (  204) I_grave
      f.add(new Character('\u00CD')); // (  205) I_acute
      f.add(new Character('\u00CE')); // (  206) I_circumflex
      f.add(new Character('\u00CF')); // (  207) I_umlaut
      f.add(new Character('\u00D0')); // (  208) ETH_Q
      f.add(new Character('\u00D1')); // (  209) N_tilde
      f.add(new Character('\u00D2')); // (  210) O_grave
      f.add(new Character('\u00D3')); // (  211) O_acute
      f.add(new Character('\u00D4')); // (  212) O_circumflex
      f.add(new Character('\u00D5')); // (  213) O_tilde
      f.add(new Character('\u00D6')); // (  214) O_umlaut
      f.add(new Character('\u00D8')); // (  216) O_slash
      f.add(new Character('\u00D9')); // (  217) U_grave
      f.add(new Character('\u00DA')); // (  218) U_acute
      f.add(new Character('\u00DB')); // (  219) U_circumflex
      f.add(new Character('\u00DC')); // (  220) U_umlaut
      f.add(new Character('\u00DD')); // (  221) Y_acute
      f.add(new Character('\u00DE')); // (  222) THORN
      f.add(new Character('\u00DF')); // (  223) sharp_s
      f.add(new Character('\u00E0')); // (  224) a_grave
      f.add(new Character('\u00E1')); // (  225) a_acute
      f.add(new Character('\u00E2')); // (  226) a_circumflex
      f.add(new Character('\u00E3')); // (  227) a_tilde
      f.add(new Character('\u00E4')); // (  228) a_umlaut
      f.add(new Character('\u00E5')); // (  229) a_ring
      f.add(new Character('\u00E6')); // (  230) ae_ligature
      f.add(new Character('\u00E7')); // (  231) c_cedilla
      f.add(new Character('\u00E8')); // (  232) e_grave
      f.add(new Character('\u00E9')); // (  233) e_acute
      f.add(new Character('\u00EA')); // (  234) e_circumflex
      f.add(new Character('\u00EB')); // (  235) e_umlaut
      f.add(new Character('\u00EC')); // (  236) i_grave
      f.add(new Character('\u00ED')); // (  237) i_acute
      f.add(new Character('\u00EE')); // (  238) i_circumflex
      f.add(new Character('\u00EF')); // (  239) i_umlaut
      f.add(new Character('\u00F0')); // (  240) eth
      f.add(new Character('\u00F1')); // (  241) n_tilde
      f.add(new Character('\u00F2')); // (  242) o_grave
      f.add(new Character('\u00F3')); // (  243) o_acute
      f.add(new Character('\u00F4')); // (  244) o_circumflex
      f.add(new Character('\u00F5')); // (  245) o_tilde
      f.add(new Character('\u00F6')); // (  246) o_umlaut
      f.add(new Character('\u00F8')); // (  248) o_slash
      f.add(new Character('\u00F9')); // (  249) u_grave
      f.add(new Character('\u00FA')); // (  250) u_acute
      f.add(new Character('\u00FB')); // (  251) u_circumflex
      f.add(new Character('\u00FC')); // (  252) u_umlaut
      f.add(new Character('\u00FD')); // (  253) y_acute
      f.add(new Character('\u00FE')); // (  254) thorn
      f.add(new Character('\u00FF')); // (  255) y_umlaut
      f.add(new Character('\u0152')); // (  338) OE_ligature
      f.add(new Character('\u0153')); // (  339) oe_ligature
      f.add(new Character('\u0160')); // (  352) S_caron
      f.add(new Character('\u0161')); // (  353) s_caron
      f.add(new Character('\u0178')); // (  376) Y_diaeresis
   }

   /**
    * Initializes ordinary characters for LUWO for the PL language.
    * @param f The HashSet
    */
   protected static void initPLFlags(HashSet<Character> f)
   {
      f.add(new Character('\u00B5')); // (  181) micro
      f.add(new Character('\u00C1')); // (  193) A_acute
      f.add(new Character('\u00C2')); // (  194) A_circumflex
      f.add(new Character('\u00C4')); // (  196) A_umlaut
      f.add(new Character('\u00C7')); // (  199) C_cedilla
      f.add(new Character('\u00C9')); // (  201) E_acute
      f.add(new Character('\u00CB')); // (  203) E_umlaut
      f.add(new Character('\u00CD')); // (  205) I_acute
      f.add(new Character('\u00CE')); // (  206) I_circumflex
      f.add(new Character('\u00D3')); // (  211) O_acute
      f.add(new Character('\u00D4')); // (  212) O_circumflex
      f.add(new Character('\u00D6')); // (  214) O_umlaut
      f.add(new Character('\u00DA')); // (  218) U_acute
      f.add(new Character('\u00DC')); // (  220) U_umlaut
      f.add(new Character('\u00DD')); // (  221) Y_acute
      f.add(new Character('\u00DF')); // (  223) sharp_s
      f.add(new Character('\u00E1')); // (  225) a_acute
      f.add(new Character('\u00E2')); // (  226) a_circumflex
      f.add(new Character('\u00E4')); // (  228) a_umlaut
      f.add(new Character('\u00E7')); // (  231) c_cedilla
      f.add(new Character('\u00E9')); // (  233) e_acute
      f.add(new Character('\u00EB')); // (  235) e_umlaut
      f.add(new Character('\u00ED')); // (  237) i_acute
      f.add(new Character('\u00EE')); // (  238) i_circumflex
      f.add(new Character('\u00F3')); // (  243) o_acute
      f.add(new Character('\u00F4')); // (  244) o_circumflex
      f.add(new Character('\u00F6')); // (  246) o_umlaut
      f.add(new Character('\u00FA')); // (  250) u_acute
      f.add(new Character('\u00FC')); // (  252) u_umlaut
      f.add(new Character('\u00FD')); // (  253) y_acute
      f.add(new Character('\u0102')); // (  258) A_breve
      f.add(new Character('\u0103')); // (  259) a_breve
      f.add(new Character('\u0104')); // (  260) A_ogokek
      f.add(new Character('\u0105')); // (  261) a_ogokek
      f.add(new Character('\u0106')); // (  262) C_acute
      f.add(new Character('\u0107')); // (  263) c_acute
      f.add(new Character('\u010C')); // (  268) C_caron
      f.add(new Character('\u010D')); // (  269) c_caron
      f.add(new Character('\u010E')); // (  270) D_caron
      f.add(new Character('\u010F')); // (  271) d_caron
      f.add(new Character('\u0110')); // (  272) D_stroke
      f.add(new Character('\u0111')); // (  273) d_stroke
      f.add(new Character('\u0118')); // (  280) E_ogonek
      f.add(new Character('\u0119')); // (  281) e_ogonek
      f.add(new Character('\u011A')); // (  282) E_caron
      f.add(new Character('\u011B')); // (  283) e_caron
      f.add(new Character('\u0139')); // (  313) L_acute
      f.add(new Character('\u013A')); // (  314) l_acute
      f.add(new Character('\u013D')); // (  317) L_caron
      f.add(new Character('\u013E')); // (  318) l_caron
      f.add(new Character('\u0141')); // (  321) L_stoke
      f.add(new Character('\u0142')); // (  322) l_stoke
      f.add(new Character('\u0143')); // (  323) N_acute
      f.add(new Character('\u0144')); // (  324) n_acute
      f.add(new Character('\u0147')); // (  327) N_caron
      f.add(new Character('\u0148')); // (  328) n_caron
      f.add(new Character('\u0150')); // (  336) O_double_acute
      f.add(new Character('\u0151')); // (  337) o_double_acute
      f.add(new Character('\u0154')); // (  340) R_acute
      f.add(new Character('\u0155')); // (  341) r_acute
      f.add(new Character('\u0158')); // (  344) R_caron
      f.add(new Character('\u0159')); // (  345) r_caron
      f.add(new Character('\u015A')); // (  346) S_acute
      f.add(new Character('\u015B')); // (  347) s_acute
      f.add(new Character('\u015E')); // (  350) S_cedilla
      f.add(new Character('\u015F')); // (  351) s_cedilla
      f.add(new Character('\u0160')); // (  352) S_caron
      f.add(new Character('\u0161')); // (  353) s_caron
      f.add(new Character('\u0162')); // (  354) T_cedilla
      f.add(new Character('\u0163')); // (  355) t_cedilla
      f.add(new Character('\u0164')); // (  356) T_caron
      f.add(new Character('\u0165')); // (  357) t_caron
      f.add(new Character('\u016E')); // (  366) U_ring
      f.add(new Character('\u016F')); // (  367) u_ring
      f.add(new Character('\u0170')); // (  368) U_double_acute
      f.add(new Character('\u0171')); // (  369) u_double_acute
      f.add(new Character('\u0179')); // (  377) Z_acute
      f.add(new Character('\u017A')); // (  378) z_acute
      f.add(new Character('\u017B')); // (  379) Z_superdot
      f.add(new Character('\u017C')); // (  380) z_superdot
      f.add(new Character('\u017D')); // (  381) Z_caron
      f.add(new Character('\u017E')); // (  382) z_caron
   }

   /**
    * Initializes ordinary characters for LUWO for the KO language.
    * @param f The HashSet
    */
   protected static void initKOFlags(HashSet<Character> f)
   {
      f.add(new Character('\u00A1')); // ( 161) inverted_exclamation
      f.add(new Character('\u00A4')); // ( 164) currency
      f.add(new Character('\u00A7')); // ( 167) SECTION
      f.add(new Character('\u00A8')); // ( 168) diaeresis
      f.add(new Character('\u00AA')); // ( 170) feminine_ordinal
      f.add(new Character('\u00AD')); // ( 173) soft_hyphen
      f.add(new Character('\u00AE')); // ( 174) REGISTERED
      f.add(new Character('\u00B0')); // ( 176) degree
      f.add(new Character('\u00B1')); // ( 177) plus_minus
      f.add(new Character('\u00B2')); // ( 178) super2
      f.add(new Character('\u00B3')); // ( 179) super3
      f.add(new Character('\u00B4')); // ( 180) acute
      f.add(new Character('\u00B6')); // ( 182) Pilcrow
      f.add(new Character('\u00B7')); // ( 183) middot
      f.add(new Character('\u00B8')); // ( 184) cedilla
      f.add(new Character('\u00B9')); // ( 185) super1
      f.add(new Character('\u00BA')); // ( 186) masculine_ordinal
      f.add(new Character('\u00BC')); // ( 188) one_quarter
      f.add(new Character('\u00BD')); // ( 189) one_half
      f.add(new Character('\u00BE')); // ( 190) three_quarters
      f.add(new Character('\u00BF')); // ( 191) inverted_question_mark
      f.add(new Character('\u00C6')); // ( 198) AE_ligature
      f.add(new Character('\u00D0')); // ( 208) ETH_Q
      f.add(new Character('\u00D7')); // ( 215) times
      f.add(new Character('\u00D8')); // ( 216) O_slash
      f.add(new Character('\u00DE')); // ( 222) THORN
      f.add(new Character('\u00DF')); // ( 223) sharp_s
      f.add(new Character('\u00E6')); // ( 230) ae_ligature
      f.add(new Character('\u00F0')); // ( 240) eth
      f.add(new Character('\u00F8')); // ( 248) o_slash
      f.add(new Character('\u00FE')); // ( 254) thorn
      f.add(new Character('\u0100')); // ( 256) A_bar
      f.add(new Character('\u0101')); // ( 257) a_bar
      f.add(new Character('\u0102')); // ( 258) A_breve
      f.add(new Character('\u0103')); // ( 259) a_breve
      f.add(new Character('\u0104')); // ( 260) A_ogokek
      f.add(new Character('\u0105')); // ( 261) a_ogokek
      f.add(new Character('\u0106')); // ( 262) C_acute
      f.add(new Character('\u0107')); // ( 263) c_acute
      f.add(new Character('\u0108')); // ( 264) C_circumflex
      f.add(new Character('\u0109')); // ( 265) c_circumflex
      f.add(new Character('\u010A')); // ( 266) C_superdot
      f.add(new Character('\u010B')); // ( 267) c_superdot
      f.add(new Character('\u010C')); // ( 268) C_caron
      f.add(new Character('\u010D')); // ( 269) c_caron
      f.add(new Character('\u010E')); // ( 270) D_caron
      f.add(new Character('\u010F')); // ( 271) d_caron
      f.add(new Character('\u0110')); // ( 272) D_stroke
      f.add(new Character('\u0111')); // ( 273) d_stroke
      f.add(new Character('\u0112')); // ( 274) E_macron
      f.add(new Character('\u0113')); // ( 275) e_macron
      f.add(new Character('\u0114')); // ( 276) E_breve
      f.add(new Character('\u0115')); // ( 277) e_breve
      f.add(new Character('\u0116')); // ( 278) E_superdot
      f.add(new Character('\u0117')); // ( 279) e_superdot
      f.add(new Character('\u0118')); // ( 280) E_ogonek
      f.add(new Character('\u0119')); // ( 281) e_ogonek
      f.add(new Character('\u011A')); // ( 282) E_caron
      f.add(new Character('\u011B')); // ( 283) e_caron
      f.add(new Character('\u011C')); // ( 284) G_circumflex
      f.add(new Character('\u011D')); // ( 285) g_circumflex
      f.add(new Character('\u011E')); // ( 286) G_breve
      f.add(new Character('\u011F')); // ( 287) g_breve
      f.add(new Character('\u0120')); // ( 288) G_superdot
      f.add(new Character('\u0121')); // ( 289) g_superdot
      f.add(new Character('\u0122')); // ( 290) G_cedilla
      f.add(new Character('\u0123')); // ( 291) g_cedilla
      f.add(new Character('\u0124')); // ( 292) H_circumflex
      f.add(new Character('\u0125')); // ( 293) h_circumflex
      f.add(new Character('\u0126')); // ( 294) H_stroke
      f.add(new Character('\u0127')); // ( 295) h_stroke
      f.add(new Character('\u0128')); // ( 296) I_tilde
      f.add(new Character('\u0129')); // ( 297) i_tilde
      f.add(new Character('\u012A')); // ( 298) I_macron
      f.add(new Character('\u012B')); // ( 299) i_macron
      f.add(new Character('\u012C')); // ( 300) I_breve
      f.add(new Character('\u012D')); // ( 301) i_breve
      f.add(new Character('\u012E')); // ( 302) I_ogonek
      f.add(new Character('\u012F')); // ( 303) i_ogonek
      f.add(new Character('\u0130')); // ( 304) I_superdot
      f.add(new Character('\u0131')); // ( 305) i_nodot
      f.add(new Character('\u0132')); // ( 306) IJ_ligature
      f.add(new Character('\u0133')); // ( 307) ij_ligature
      f.add(new Character('\u0134')); // ( 308) J_circumflex
      f.add(new Character('\u0135')); // ( 309) j_circumflex
      f.add(new Character('\u0136')); // ( 310) K_cedilla
      f.add(new Character('\u0137')); // ( 311) k_cedilla
      f.add(new Character('\u0138')); // ( 312) kra
      f.add(new Character('\u0139')); // ( 313) L_acute
      f.add(new Character('\u013A')); // ( 314) l_acute
      f.add(new Character('\u013B')); // ( 315) L_cedilla
      f.add(new Character('\u013C')); // ( 316) l_cedilla
      f.add(new Character('\u013D')); // ( 317) L_caron
      f.add(new Character('\u013E')); // ( 318) l_caron
      f.add(new Character('\u013F')); // ( 319) L_middot
      f.add(new Character('\u0140')); // ( 320) l_middot
      f.add(new Character('\u0141')); // ( 321) L_stoke
      f.add(new Character('\u0142')); // ( 322) l_stoke
      f.add(new Character('\u0143')); // ( 323) N_acute
      f.add(new Character('\u0144')); // ( 324) n_acute
      f.add(new Character('\u0145')); // ( 325) N_cedilla
      f.add(new Character('\u0146')); // ( 326) n_cedilla
      f.add(new Character('\u0147')); // ( 327) N_caron
      f.add(new Character('\u0148')); // ( 328) n_caron
      f.add(new Character('\u0149')); // ( 329) n_apostrophe
      f.add(new Character('\u014A')); // ( 330) ENG
      f.add(new Character('\u014B')); // ( 331) eng
      f.add(new Character('\u014C')); // ( 332) O_macron
      f.add(new Character('\u014D')); // ( 333) o_macron
      f.add(new Character('\u014E')); // ( 334) O_breve
      f.add(new Character('\u014F')); // ( 335) o_breve
      f.add(new Character('\u0150')); // ( 336) O_double_acute
      f.add(new Character('\u0151')); // ( 337) o_double_acute
      f.add(new Character('\u0152')); // ( 338) OE_ligature
      f.add(new Character('\u0153')); // ( 339) oe_ligature
      f.add(new Character('\u0154')); // ( 340) R_acute
      f.add(new Character('\u0155')); // ( 341) r_acute
      f.add(new Character('\u0156')); // ( 342) R_cedilla
      f.add(new Character('\u0157')); // ( 343) r_cedilla
      f.add(new Character('\u0158')); // ( 344) R_caron
      f.add(new Character('\u0159')); // ( 345) r_caron
      f.add(new Character('\u015A')); // ( 346) S_acute
      f.add(new Character('\u015B')); // ( 347) s_acute
      f.add(new Character('\u015C')); // ( 348) S_circumflex
      f.add(new Character('\u015D')); // ( 349) s_circumflex
      f.add(new Character('\u015E')); // ( 350) S_cedilla
      f.add(new Character('\u015F')); // ( 351) s_cedilla
      f.add(new Character('\u0160')); // ( 352) S_caron
      f.add(new Character('\u0161')); // ( 353) s_caron
      f.add(new Character('\u0162')); // ( 354) T_cedilla
      f.add(new Character('\u0163')); // ( 355) t_cedilla
      f.add(new Character('\u0164')); // ( 356) T_caron
      f.add(new Character('\u0165')); // ( 357) t_caron
      f.add(new Character('\u0166')); // ( 358) T_stroke
      f.add(new Character('\u0167')); // ( 359) t_stroke
      f.add(new Character('\u0168')); // ( 360) U_tilde
      f.add(new Character('\u0169')); // ( 361) u_tilde
      f.add(new Character('\u016A')); // ( 362) U_macron
      f.add(new Character('\u016B')); // ( 363) u_macron
      f.add(new Character('\u016C')); // ( 364) U_breve
      f.add(new Character('\u016D')); // ( 365) u_breve
      f.add(new Character('\u016E')); // ( 366) U_ring
      f.add(new Character('\u016F')); // ( 367) u_ring
      f.add(new Character('\u0170')); // ( 368) U_double_acute
      f.add(new Character('\u0171')); // ( 369) u_double_acute
      f.add(new Character('\u0172')); // ( 370) U_ogonek
      f.add(new Character('\u0173')); // ( 371) u_ogonek
      f.add(new Character('\u0174')); // ( 372) W_circumflex
      f.add(new Character('\u0175')); // ( 373) w_circumflex
      f.add(new Character('\u0176')); // ( 374) Y_circumflex
      f.add(new Character('\u0177')); // ( 375) y_circumflex
      f.add(new Character('\u0178')); // ( 376) Y_diaeresis
      f.add(new Character('\u0179')); // ( 377) Z_acute
      f.add(new Character('\u017A')); // ( 378) z_acute
      f.add(new Character('\u017B')); // ( 379) Z_superdot
      f.add(new Character('\u017C')); // ( 380) z_superdot
      f.add(new Character('\u017D')); // ( 381) Z_caron
      f.add(new Character('\u017E')); // ( 382) z_caron
      f.add(new Character('\u017F')); // ( 383) long_s
      f.add(new Character('\u0180')); // ( 384) X
      f.add(new Character('\u0181')); // ( 385) X
      f.add(new Character('\u0182')); // ( 386) X
      f.add(new Character('\u0183')); // ( 387) X
      f.add(new Character('\u0184')); // ( 388) X
      f.add(new Character('\u0185')); // ( 389) X
      f.add(new Character('\u0186')); // ( 390) X
      f.add(new Character('\u0187')); // ( 391) X
      f.add(new Character('\u0188')); // ( 392) X
      f.add(new Character('\u0189')); // ( 393) X
      f.add(new Character('\u018A')); // ( 394) X
      f.add(new Character('\u018B')); // ( 395) X
      f.add(new Character('\u018C')); // ( 396) X
      f.add(new Character('\u018D')); // ( 397) X
      f.add(new Character('\u018E')); // ( 398) X
      f.add(new Character('\u018F')); // ( 399) X
      f.add(new Character('\u0190')); // ( 400) X
      f.add(new Character('\u0191')); // ( 401) X
      f.add(new Character('\u0192')); // ( 402) X
      f.add(new Character('\u0193')); // ( 403) X
      f.add(new Character('\u0194')); // ( 404) X
      f.add(new Character('\u0195')); // ( 405) X
      f.add(new Character('\u0196')); // ( 406) X
      f.add(new Character('\u0197')); // ( 407) X
      f.add(new Character('\u0198')); // ( 408) X
      f.add(new Character('\u0199')); // ( 409) X
      f.add(new Character('\u019A')); // ( 410) X
      f.add(new Character('\u019B')); // ( 411) X
      f.add(new Character('\u019C')); // ( 412) X
      f.add(new Character('\u019D')); // ( 413) X
      f.add(new Character('\u019E')); // ( 414) X
      f.add(new Character('\u019F')); // ( 415) X
      f.add(new Character('\u01A0')); // ( 416) X
      f.add(new Character('\u01A1')); // ( 417) X
      f.add(new Character('\u01A2')); // ( 418) X
      f.add(new Character('\u01A3')); // ( 419) X
      f.add(new Character('\u01A4')); // ( 420) X
      f.add(new Character('\u01A5')); // ( 421) X
      f.add(new Character('\u01A6')); // ( 422) X
      f.add(new Character('\u01A7')); // ( 423) X
      f.add(new Character('\u01A8')); // ( 424) X
      f.add(new Character('\u01A9')); // ( 425) X
      f.add(new Character('\u01AA')); // ( 426) X
      f.add(new Character('\u01AB')); // ( 427) X
      f.add(new Character('\u01AC')); // ( 428) X
      f.add(new Character('\u01AD')); // ( 429) X
      f.add(new Character('\u01AE')); // ( 430) X
      f.add(new Character('\u01AF')); // ( 431) X
      f.add(new Character('\u01B0')); // ( 432) X
      f.add(new Character('\u01B1')); // ( 433) X
      f.add(new Character('\u01B2')); // ( 434) X
      f.add(new Character('\u01B3')); // ( 435) X
      f.add(new Character('\u01B4')); // ( 436) X
      f.add(new Character('\u01B5')); // ( 437) X
      f.add(new Character('\u01B6')); // ( 438) X
      f.add(new Character('\u01B7')); // ( 439) X
      f.add(new Character('\u01B8')); // ( 440) X
      f.add(new Character('\u01B9')); // ( 441) X
      f.add(new Character('\u01BA')); // ( 442) X
      f.add(new Character('\u01BB')); // ( 443) X
      f.add(new Character('\u01BC')); // ( 444) X
      f.add(new Character('\u01BD')); // ( 445) X
      f.add(new Character('\u01BE')); // ( 446) X
      f.add(new Character('\u01BF')); // ( 447) X
      f.add(new Character('\u01C0')); // ( 448) X
      f.add(new Character('\u01C1')); // ( 449) X
      f.add(new Character('\u01C2')); // ( 450) X
      f.add(new Character('\u01C3')); // ( 451) X
      f.add(new Character('\u01C4')); // ( 452) X
      f.add(new Character('\u01C5')); // ( 453) X
      f.add(new Character('\u01C6')); // ( 454) X
      f.add(new Character('\u01C7')); // ( 455) X
      f.add(new Character('\u01C8')); // ( 456) X
      f.add(new Character('\u01C9')); // ( 457) X
      f.add(new Character('\u01CA')); // ( 458) X
      f.add(new Character('\u01CB')); // ( 459) X
      f.add(new Character('\u01CC')); // ( 460) X
      f.add(new Character('\u01CD')); // ( 461) X
      f.add(new Character('\u01CE')); // ( 462) X
      f.add(new Character('\u01CF')); // ( 463) X
      f.add(new Character('\u01D0')); // ( 464) X
      f.add(new Character('\u01D1')); // ( 465) X
      f.add(new Character('\u01D2')); // ( 466) X
      f.add(new Character('\u01D3')); // ( 467) X
      f.add(new Character('\u01D4')); // ( 468) X
      f.add(new Character('\u01D5')); // ( 469) X
      f.add(new Character('\u01D6')); // ( 470) X
      f.add(new Character('\u01D7')); // ( 471) X
      f.add(new Character('\u01D8')); // ( 472) X
      f.add(new Character('\u01D9')); // ( 473) X
      f.add(new Character('\u01DA')); // ( 474) X
      f.add(new Character('\u01DB')); // ( 475) X
      f.add(new Character('\u01DC')); // ( 476) X
      f.add(new Character('\u01DD')); // ( 477) X
      f.add(new Character('\u01DE')); // ( 478) X
      f.add(new Character('\u01DF')); // ( 479) X
      f.add(new Character('\u01E0')); // ( 480) X
      f.add(new Character('\u01E1')); // ( 481) X
      f.add(new Character('\u01E2')); // ( 482) X
      f.add(new Character('\u01E3')); // ( 483) X
      f.add(new Character('\u01E4')); // ( 484) X
      f.add(new Character('\u01E5')); // ( 485) X
      f.add(new Character('\u01E6')); // ( 486) X
      f.add(new Character('\u01E7')); // ( 487) X
      f.add(new Character('\u01E8')); // ( 488) X
      f.add(new Character('\u01E9')); // ( 489) X
      f.add(new Character('\u01EA')); // ( 490) X
      f.add(new Character('\u01EB')); // ( 491) X
      f.add(new Character('\u01EC')); // ( 492) X
      f.add(new Character('\u01ED')); // ( 493) X
      f.add(new Character('\u01EE')); // ( 494) X
      f.add(new Character('\u01EF')); // ( 495) X
      f.add(new Character('\u01F0')); // ( 496) X
      f.add(new Character('\u01F1')); // ( 497) X
      f.add(new Character('\u01F2')); // ( 498) X
      f.add(new Character('\u01F3')); // ( 499) X
      f.add(new Character('\u01F4')); // ( 500) X
      f.add(new Character('\u01F5')); // ( 501) X
      f.add(new Character('\u01F6')); // ( 502) X
      f.add(new Character('\u01F7')); // ( 503) X
      f.add(new Character('\u01F8')); // ( 504) X
      f.add(new Character('\u01F9')); // ( 505) X
      f.add(new Character('\u01FA')); // ( 506) X
      f.add(new Character('\u01FB')); // ( 507) X
      f.add(new Character('\u01FC')); // ( 508) X
      f.add(new Character('\u01FD')); // ( 509) X
      f.add(new Character('\u01FE')); // ( 510) X
      f.add(new Character('\u01FF')); // ( 511) X
      f.add(new Character('\u0200')); // ( 512) X
      f.add(new Character('\u0201')); // ( 513) X
      f.add(new Character('\u0202')); // ( 514) X
      f.add(new Character('\u0203')); // ( 515) X
      f.add(new Character('\u0204')); // ( 516) X
      f.add(new Character('\u0205')); // ( 517) X
      f.add(new Character('\u0206')); // ( 518) X
      f.add(new Character('\u0207')); // ( 519) X
      f.add(new Character('\u0208')); // ( 520) X
      f.add(new Character('\u0209')); // ( 521) X
      f.add(new Character('\u020A')); // ( 522) X
      f.add(new Character('\u020B')); // ( 523) X
      f.add(new Character('\u020C')); // ( 524) X
      f.add(new Character('\u020D')); // ( 525) X
      f.add(new Character('\u020E')); // ( 526) X
      f.add(new Character('\u020F')); // ( 527) X
      f.add(new Character('\u0210')); // ( 528) X
      f.add(new Character('\u0211')); // ( 529) X
      f.add(new Character('\u0212')); // ( 530) X
      f.add(new Character('\u0213')); // ( 531) X
      f.add(new Character('\u0214')); // ( 532) X
      f.add(new Character('\u0215')); // ( 533) X
      f.add(new Character('\u0216')); // ( 534) X
      f.add(new Character('\u0217')); // ( 535) X
      f.add(new Character('\u0218')); // ( 536) X
      f.add(new Character('\u0219')); // ( 537) X
      f.add(new Character('\u021A')); // ( 538) X
      f.add(new Character('\u021B')); // ( 539) X
      f.add(new Character('\u021C')); // ( 540) X
      f.add(new Character('\u021D')); // ( 541) X
      f.add(new Character('\u021E')); // ( 542) X
      f.add(new Character('\u021F')); // ( 543) X
      f.add(new Character('\u0220')); // ( 544) X
      f.add(new Character('\u0221')); // ( 545) X
      f.add(new Character('\u0222')); // ( 546) X
      f.add(new Character('\u0223')); // ( 547) X
      f.add(new Character('\u0224')); // ( 548) X
      f.add(new Character('\u0225')); // ( 549) X
      f.add(new Character('\u0226')); // ( 550) X
      f.add(new Character('\u0227')); // ( 551) X
      f.add(new Character('\u0228')); // ( 552) X
      f.add(new Character('\u0229')); // ( 553) X
      f.add(new Character('\u022A')); // ( 554) X
      f.add(new Character('\u022B')); // ( 555) X
      f.add(new Character('\u022C')); // ( 556) X
      f.add(new Character('\u022D')); // ( 557) X
      f.add(new Character('\u022E')); // ( 558) X
      f.add(new Character('\u022F')); // ( 559) X
      f.add(new Character('\u0230')); // ( 560) X
      f.add(new Character('\u0231')); // ( 561) X
      f.add(new Character('\u0232')); // ( 562) X
      f.add(new Character('\u0233')); // ( 563) X
      f.add(new Character('\u0234')); // ( 564) X
      f.add(new Character('\u0235')); // ( 565) X
      f.add(new Character('\u0236')); // ( 566) X
      f.add(new Character('\u0237')); // ( 567) X
      f.add(new Character('\u0238')); // ( 568) X
      f.add(new Character('\u0239')); // ( 569) X
      f.add(new Character('\u023A')); // ( 570) X
      f.add(new Character('\u023B')); // ( 571) X
      f.add(new Character('\u023C')); // ( 572) X
      f.add(new Character('\u023D')); // ( 573) X
      f.add(new Character('\u023E')); // ( 574) X
      f.add(new Character('\u023F')); // ( 575) X
      f.add(new Character('\u0240')); // ( 576) X
      f.add(new Character('\u0241')); // ( 577) X
      f.add(new Character('\u0242')); // ( 578) X
      f.add(new Character('\u0243')); // ( 579) X
      f.add(new Character('\u0244')); // ( 580) X
      f.add(new Character('\u0245')); // ( 581) X
      f.add(new Character('\u0246')); // ( 582) X
      f.add(new Character('\u0247')); // ( 583) X
      f.add(new Character('\u0248')); // ( 584) X
      f.add(new Character('\u0249')); // ( 585) X
      f.add(new Character('\u024A')); // ( 586) X
      f.add(new Character('\u024B')); // ( 587) X
      f.add(new Character('\u024C')); // ( 588) X
      f.add(new Character('\u024D')); // ( 589) X
      f.add(new Character('\u024E')); // ( 590) X
      f.add(new Character('\u024F')); // ( 591) X
      f.add(new Character('\u0250')); // ( 592) X
      f.add(new Character('\u0251')); // ( 593) X
      f.add(new Character('\u0252')); // ( 594) X
      f.add(new Character('\u0253')); // ( 595) X
      f.add(new Character('\u0254')); // ( 596) X
      f.add(new Character('\u0255')); // ( 597) X
      f.add(new Character('\u0256')); // ( 598) X
      f.add(new Character('\u0257')); // ( 599) X
      f.add(new Character('\u0258')); // ( 600) X
      f.add(new Character('\u0259')); // ( 601) X
      f.add(new Character('\u025A')); // ( 602) X
      f.add(new Character('\u025B')); // ( 603) X
      f.add(new Character('\u025C')); // ( 604) X
      f.add(new Character('\u025D')); // ( 605) X
      f.add(new Character('\u025E')); // ( 606) X
      f.add(new Character('\u025F')); // ( 607) X
      f.add(new Character('\u0260')); // ( 608) X
      f.add(new Character('\u0261')); // ( 609) X
      f.add(new Character('\u0262')); // ( 610) X
      f.add(new Character('\u0263')); // ( 611) X
      f.add(new Character('\u0264')); // ( 612) X
      f.add(new Character('\u0265')); // ( 613) X
      f.add(new Character('\u0266')); // ( 614) X
      f.add(new Character('\u0267')); // ( 615) X
      f.add(new Character('\u0268')); // ( 616) X
      f.add(new Character('\u0269')); // ( 617) X
      f.add(new Character('\u026A')); // ( 618) X
      f.add(new Character('\u026B')); // ( 619) X
      f.add(new Character('\u026C')); // ( 620) X
      f.add(new Character('\u026D')); // ( 621) X
      f.add(new Character('\u026E')); // ( 622) X
      f.add(new Character('\u026F')); // ( 623) X
      f.add(new Character('\u0270')); // ( 624) X
      f.add(new Character('\u0271')); // ( 625) X
      f.add(new Character('\u0272')); // ( 626) X
      f.add(new Character('\u0273')); // ( 627) X
      f.add(new Character('\u0274')); // ( 628) X
      f.add(new Character('\u0275')); // ( 629) X
      f.add(new Character('\u0276')); // ( 630) X
      f.add(new Character('\u0277')); // ( 631) X
      f.add(new Character('\u0278')); // ( 632) X
      f.add(new Character('\u0279')); // ( 633) X
      f.add(new Character('\u027A')); // ( 634) X
      f.add(new Character('\u027B')); // ( 635) X
      f.add(new Character('\u027C')); // ( 636) X
      f.add(new Character('\u027D')); // ( 637) X
      f.add(new Character('\u027E')); // ( 638) X
      f.add(new Character('\u027F')); // ( 639) X
      f.add(new Character('\u0280')); // ( 640) X
      f.add(new Character('\u0281')); // ( 641) X
      f.add(new Character('\u0282')); // ( 642) X
      f.add(new Character('\u0283')); // ( 643) X
      f.add(new Character('\u0284')); // ( 644) X
      f.add(new Character('\u0285')); // ( 645) X
      f.add(new Character('\u0286')); // ( 646) X
      f.add(new Character('\u0287')); // ( 647) X
      f.add(new Character('\u0288')); // ( 648) X
      f.add(new Character('\u0289')); // ( 649) X
      f.add(new Character('\u028A')); // ( 650) X
      f.add(new Character('\u028B')); // ( 651) X
      f.add(new Character('\u028C')); // ( 652) X
      f.add(new Character('\u028D')); // ( 653) X
      f.add(new Character('\u028E')); // ( 654) X
      f.add(new Character('\u028F')); // ( 655) X
      f.add(new Character('\u0290')); // ( 656) X
      f.add(new Character('\u0291')); // ( 657) X
      f.add(new Character('\u0292')); // ( 658) X
      f.add(new Character('\u0293')); // ( 659) X
      f.add(new Character('\u0294')); // ( 660) X
      f.add(new Character('\u0295')); // ( 661) X
      f.add(new Character('\u0296')); // ( 662) X
      f.add(new Character('\u0297')); // ( 663) X
      f.add(new Character('\u0298')); // ( 664) X
      f.add(new Character('\u0299')); // ( 665) X
      f.add(new Character('\u029A')); // ( 666) X
      f.add(new Character('\u029B')); // ( 667) X
      f.add(new Character('\u029C')); // ( 668) X
      f.add(new Character('\u029D')); // ( 669) X
      f.add(new Character('\u029E')); // ( 670) X
      f.add(new Character('\u029F')); // ( 671) X
      f.add(new Character('\u02A0')); // ( 672) X
      f.add(new Character('\u02A1')); // ( 673) X
      f.add(new Character('\u02A2')); // ( 674) X
      f.add(new Character('\u02A3')); // ( 675) X
      f.add(new Character('\u02A4')); // ( 676) X
      f.add(new Character('\u02A5')); // ( 677) X
      f.add(new Character('\u02A6')); // ( 678) X
      f.add(new Character('\u02A7')); // ( 679) X
      f.add(new Character('\u02A8')); // ( 680) X
      f.add(new Character('\u02A9')); // ( 681) X
      f.add(new Character('\u02AA')); // ( 682) X
      f.add(new Character('\u02AB')); // ( 683) X
      f.add(new Character('\u02AC')); // ( 684) X
      f.add(new Character('\u02AD')); // ( 685) X
      f.add(new Character('\u02AE')); // ( 686) X
      f.add(new Character('\u02AF')); // ( 687) X
      f.add(new Character('\u02B0')); // ( 688) X
      f.add(new Character('\u02B1')); // ( 689) X
      f.add(new Character('\u02B2')); // ( 690) X
      f.add(new Character('\u02B3')); // ( 691) X
      f.add(new Character('\u02B4')); // ( 692) X
      f.add(new Character('\u02B5')); // ( 693) X
      f.add(new Character('\u02B6')); // ( 694) X
      f.add(new Character('\u02B7')); // ( 695) X
      f.add(new Character('\u02B8')); // ( 696) X
      f.add(new Character('\u02B9')); // ( 697) X
      f.add(new Character('\u02BA')); // ( 698) X
      f.add(new Character('\u02BB')); // ( 699) X
      f.add(new Character('\u02BC')); // ( 700) X
      f.add(new Character('\u02BD')); // ( 701) X
      f.add(new Character('\u02BE')); // ( 702) X
      f.add(new Character('\u02BF')); // ( 703) X
      f.add(new Character('\u02C0')); // ( 704) X
      f.add(new Character('\u02C1')); // ( 705) X
      f.add(new Character('\u02C2')); // ( 706) X
      f.add(new Character('\u02C3')); // ( 707) X
      f.add(new Character('\u02C4')); // ( 708) X
      f.add(new Character('\u02C5')); // ( 709) X
      f.add(new Character('\u02C6')); // ( 710) X
      f.add(new Character('\u02C7')); // ( 711) X
      f.add(new Character('\u02C8')); // ( 712) X
      f.add(new Character('\u02C9')); // ( 713) X
      f.add(new Character('\u02CA')); // ( 714) X
      f.add(new Character('\u02CB')); // ( 715) X
      f.add(new Character('\u02CC')); // ( 716) X
      f.add(new Character('\u02CD')); // ( 717) X
      f.add(new Character('\u02CE')); // ( 718) X
      f.add(new Character('\u02CF')); // ( 719) X
      f.add(new Character('\u02D0')); // ( 720) X
      f.add(new Character('\u02D1')); // ( 721) X
      f.add(new Character('\u02D2')); // ( 722) X
      f.add(new Character('\u02D3')); // ( 723) X
      f.add(new Character('\u02D4')); // ( 724) X
      f.add(new Character('\u02D5')); // ( 725) X
      f.add(new Character('\u02D6')); // ( 726) X
      f.add(new Character('\u02D7')); // ( 727) X
      f.add(new Character('\u02D8')); // ( 728) X
      f.add(new Character('\u02D9')); // ( 729) X
      f.add(new Character('\u02DA')); // ( 730) X
      f.add(new Character('\u02DB')); // ( 731) X
      f.add(new Character('\u02DC')); // ( 732) X
      f.add(new Character('\u02DD')); // ( 733) X
      f.add(new Character('\u02DE')); // ( 734) X
      f.add(new Character('\u02DF')); // ( 735) X
      f.add(new Character('\u02E0')); // ( 736) X
      f.add(new Character('\u02E1')); // ( 737) X
      f.add(new Character('\u02E2')); // ( 738) X
      f.add(new Character('\u02E3')); // ( 739) X
      f.add(new Character('\u02E4')); // ( 740) X
      f.add(new Character('\u02E5')); // ( 741) X
      f.add(new Character('\u02E6')); // ( 742) X
      f.add(new Character('\u02E7')); // ( 743) X
      f.add(new Character('\u02E8')); // ( 744) X
      f.add(new Character('\u02E9')); // ( 745) X
      f.add(new Character('\u02EA')); // ( 746) X
      f.add(new Character('\u02EB')); // ( 747) X
      f.add(new Character('\u02EC')); // ( 748) X
      f.add(new Character('\u02ED')); // ( 749) X
      f.add(new Character('\u02EE')); // ( 750) X
      f.add(new Character('\u02EF')); // ( 751) X
      f.add(new Character('\u02F0')); // ( 752) X
      f.add(new Character('\u02F1')); // ( 753) X
      f.add(new Character('\u02F2')); // ( 754) X
      f.add(new Character('\u02F3')); // ( 755) X
      f.add(new Character('\u02F4')); // ( 756) X
      f.add(new Character('\u02F5')); // ( 757) X
      f.add(new Character('\u02F6')); // ( 758) X
      f.add(new Character('\u02F7')); // ( 759) X
      f.add(new Character('\u02F8')); // ( 760) X
      f.add(new Character('\u02F9')); // ( 761) X
      f.add(new Character('\u02FA')); // ( 762) X
      f.add(new Character('\u02FB')); // ( 763) X
      f.add(new Character('\u02FC')); // ( 764) X
      f.add(new Character('\u02FD')); // ( 765) X
      f.add(new Character('\u02FE')); // ( 766) X
      f.add(new Character('\u02FF')); // ( 767) X
      f.add(new Character('\u0300')); // ( 768) X
      f.add(new Character('\u0301')); // ( 769) X
      f.add(new Character('\u0302')); // ( 770) X
      f.add(new Character('\u0303')); // ( 771) X
      f.add(new Character('\u0304')); // ( 772) X
      f.add(new Character('\u0305')); // ( 773) X
      f.add(new Character('\u0306')); // ( 774) X
      f.add(new Character('\u0307')); // ( 775) X
      f.add(new Character('\u0308')); // ( 776) X
      f.add(new Character('\u0309')); // ( 777) X
      f.add(new Character('\u030A')); // ( 778) X
      f.add(new Character('\u030B')); // ( 779) X
      f.add(new Character('\u030C')); // ( 780) X
      f.add(new Character('\u030D')); // ( 781) X
      f.add(new Character('\u030E')); // ( 782) X
      f.add(new Character('\u030F')); // ( 783) X
      f.add(new Character('\u0310')); // ( 784) X
      f.add(new Character('\u0311')); // ( 785) X
      f.add(new Character('\u0312')); // ( 786) X
      f.add(new Character('\u0313')); // ( 787) X
      f.add(new Character('\u0314')); // ( 788) X
      f.add(new Character('\u0315')); // ( 789) X
      f.add(new Character('\u0316')); // ( 790) X
      f.add(new Character('\u0317')); // ( 791) X
      f.add(new Character('\u0318')); // ( 792) X
      f.add(new Character('\u0319')); // ( 793) X
      f.add(new Character('\u031A')); // ( 794) X
      f.add(new Character('\u031B')); // ( 795) X
      f.add(new Character('\u031C')); // ( 796) X
      f.add(new Character('\u031D')); // ( 797) X
      f.add(new Character('\u031E')); // ( 798) X
      f.add(new Character('\u031F')); // ( 799) X
      f.add(new Character('\u0320')); // ( 800) X
      f.add(new Character('\u0321')); // ( 801) X
      f.add(new Character('\u0322')); // ( 802) X
      f.add(new Character('\u0323')); // ( 803) X
      f.add(new Character('\u0324')); // ( 804) X
      f.add(new Character('\u0325')); // ( 805) X
      f.add(new Character('\u0326')); // ( 806) X
      f.add(new Character('\u0327')); // ( 807) X
      f.add(new Character('\u0328')); // ( 808) X
      f.add(new Character('\u0329')); // ( 809) X
      f.add(new Character('\u032A')); // ( 810) X
      f.add(new Character('\u032B')); // ( 811) X
      f.add(new Character('\u032C')); // ( 812) X
      f.add(new Character('\u032D')); // ( 813) X
      f.add(new Character('\u032E')); // ( 814) X
      f.add(new Character('\u032F')); // ( 815) X
      f.add(new Character('\u0330')); // ( 816) X
      f.add(new Character('\u0331')); // ( 817) X
      f.add(new Character('\u0332')); // ( 818) X
      f.add(new Character('\u0333')); // ( 819) X
      f.add(new Character('\u0334')); // ( 820) X
      f.add(new Character('\u0335')); // ( 821) X
      f.add(new Character('\u0336')); // ( 822) X
      f.add(new Character('\u0337')); // ( 823) X
      f.add(new Character('\u0338')); // ( 824) X
      f.add(new Character('\u0339')); // ( 825) X
      f.add(new Character('\u033A')); // ( 826) X
      f.add(new Character('\u033B')); // ( 827) X
      f.add(new Character('\u033C')); // ( 828) X
      f.add(new Character('\u033D')); // ( 829) X
      f.add(new Character('\u033E')); // ( 830) X
      f.add(new Character('\u033F')); // ( 831) X
      f.add(new Character('\u0340')); // ( 832) X
      f.add(new Character('\u0341')); // ( 833) X
      f.add(new Character('\u0342')); // ( 834) X
      f.add(new Character('\u0343')); // ( 835) X
      f.add(new Character('\u0344')); // ( 836) X
      f.add(new Character('\u0345')); // ( 837) X
      f.add(new Character('\u0346')); // ( 838) X
      f.add(new Character('\u0347')); // ( 839) X
      f.add(new Character('\u0348')); // ( 840) X
      f.add(new Character('\u0349')); // ( 841) X
      f.add(new Character('\u034A')); // ( 842) X
      f.add(new Character('\u034B')); // ( 843) X
      f.add(new Character('\u034C')); // ( 844) X
      f.add(new Character('\u034D')); // ( 845) X
      f.add(new Character('\u034E')); // ( 846) X
      f.add(new Character('\u034F')); // ( 847) X
      f.add(new Character('\u0350')); // ( 848) X
      f.add(new Character('\u0351')); // ( 849) X
      f.add(new Character('\u0352')); // ( 850) X
      f.add(new Character('\u0353')); // ( 851) X
      f.add(new Character('\u0354')); // ( 852) X
      f.add(new Character('\u0355')); // ( 853) X
      f.add(new Character('\u0356')); // ( 854) X
      f.add(new Character('\u0357')); // ( 855) X
      f.add(new Character('\u0358')); // ( 856) X
      f.add(new Character('\u0359')); // ( 857) X
      f.add(new Character('\u035A')); // ( 858) X
      f.add(new Character('\u035B')); // ( 859) X
      f.add(new Character('\u035C')); // ( 860) X
      f.add(new Character('\u035D')); // ( 861) X
      f.add(new Character('\u035E')); // ( 862) X
      f.add(new Character('\u035F')); // ( 863) X
      f.add(new Character('\u0360')); // ( 864) X
      f.add(new Character('\u0361')); // ( 865) X
      f.add(new Character('\u0362')); // ( 866) X
      f.add(new Character('\u0363')); // ( 867) X
      f.add(new Character('\u0364')); // ( 868) X
      f.add(new Character('\u0365')); // ( 869) X
      f.add(new Character('\u0366')); // ( 870) X
      f.add(new Character('\u0367')); // ( 871) X
      f.add(new Character('\u0368')); // ( 872) X
      f.add(new Character('\u0369')); // ( 873) X
      f.add(new Character('\u036A')); // ( 874) X
      f.add(new Character('\u036B')); // ( 875) X
      f.add(new Character('\u036C')); // ( 876) X
      f.add(new Character('\u036D')); // ( 877) X
      f.add(new Character('\u036E')); // ( 878) X
      f.add(new Character('\u036F')); // ( 879) X
      f.add(new Character('\u0370')); // ( 880) X
      f.add(new Character('\u0371')); // ( 881) X
      f.add(new Character('\u0372')); // ( 882) X
      f.add(new Character('\u0373')); // ( 883) X
      f.add(new Character('\u0374')); // ( 884) X
      f.add(new Character('\u0375')); // ( 885) X
      f.add(new Character('\u0376')); // ( 886) X
      f.add(new Character('\u0377')); // ( 887) X
      f.add(new Character('\u0378')); // ( 888) X
      f.add(new Character('\u0379')); // ( 889) X
      f.add(new Character('\u037A')); // ( 890) X
      f.add(new Character('\u037B')); // ( 891) X
      f.add(new Character('\u037C')); // ( 892) X
      f.add(new Character('\u037D')); // ( 893) X
      f.add(new Character('\u037E')); // ( 894) X
      f.add(new Character('\u037F')); // ( 895) X
      f.add(new Character('\u0380')); // ( 896) X
      f.add(new Character('\u0381')); // ( 897) X
      f.add(new Character('\u0382')); // ( 898) X
      f.add(new Character('\u0383')); // ( 899) X
      f.add(new Character('\u0384')); // ( 900) X
      f.add(new Character('\u0385')); // ( 901) X
      f.add(new Character('\u0386')); // ( 902) X
      f.add(new Character('\u0387')); // ( 903) X
      f.add(new Character('\u0388')); // ( 904) X
      f.add(new Character('\u0389')); // ( 905) X
      f.add(new Character('\u038A')); // ( 906) X
      f.add(new Character('\u038B')); // ( 907) X
      f.add(new Character('\u038C')); // ( 908) X
      f.add(new Character('\u038D')); // ( 909) X
      f.add(new Character('\u038E')); // ( 910) X
      f.add(new Character('\u038F')); // ( 911) X
      f.add(new Character('\u0390')); // ( 912) X
      f.add(new Character('\u0391')); // ( 913) X
      f.add(new Character('\u0392')); // ( 914) X
      f.add(new Character('\u0393')); // ( 915) X
      f.add(new Character('\u0394')); // ( 916) X
      f.add(new Character('\u0395')); // ( 917) X
      f.add(new Character('\u0396')); // ( 918) X
      f.add(new Character('\u0397')); // ( 919) X
      f.add(new Character('\u0398')); // ( 920) X
      f.add(new Character('\u0399')); // ( 921) X
      f.add(new Character('\u039A')); // ( 922) X
      f.add(new Character('\u039B')); // ( 923) X
      f.add(new Character('\u039C')); // ( 924) X
      f.add(new Character('\u039D')); // ( 925) X
      f.add(new Character('\u039E')); // ( 926) X
      f.add(new Character('\u039F')); // ( 927) X
      f.add(new Character('\u03A0')); // ( 928) X
      f.add(new Character('\u03A1')); // ( 929) X
      f.add(new Character('\u03A2')); // ( 930) X
      f.add(new Character('\u03A3')); // ( 931) X
      f.add(new Character('\u03A4')); // ( 932) X
      f.add(new Character('\u03A5')); // ( 933) X
      f.add(new Character('\u03A6')); // ( 934) X
      f.add(new Character('\u03A7')); // ( 935) X
      f.add(new Character('\u03A8')); // ( 936) X
      f.add(new Character('\u03A9')); // ( 937) X
      f.add(new Character('\u03AA')); // ( 938) X
      f.add(new Character('\u03AB')); // ( 939) X
      f.add(new Character('\u03AC')); // ( 940) X
      f.add(new Character('\u03AD')); // ( 941) X
      f.add(new Character('\u03AE')); // ( 942) X
      f.add(new Character('\u03AF')); // ( 943) X
      f.add(new Character('\u03B0')); // ( 944) X
      f.add(new Character('\u03B1')); // ( 945) X
      f.add(new Character('\u03B2')); // ( 946) X
      f.add(new Character('\u03B3')); // ( 947) X
      f.add(new Character('\u03B4')); // ( 948) X
      f.add(new Character('\u03B5')); // ( 949) X
      f.add(new Character('\u03B6')); // ( 950) X
      f.add(new Character('\u03B7')); // ( 951) X
      f.add(new Character('\u03B8')); // ( 952) X
      f.add(new Character('\u03B9')); // ( 953) X
      f.add(new Character('\u03BA')); // ( 954) X
      f.add(new Character('\u03BB')); // ( 955) X
      f.add(new Character('\u03BC')); // ( 956) X
      f.add(new Character('\u03BD')); // ( 957) X
      f.add(new Character('\u03BE')); // ( 958) X
      f.add(new Character('\u03BF')); // ( 959) X
      f.add(new Character('\u03C0')); // ( 960) X
      f.add(new Character('\u03C1')); // ( 961) X
      f.add(new Character('\u03C2')); // ( 962) X
      f.add(new Character('\u03C3')); // ( 963) X
      f.add(new Character('\u03C4')); // ( 964) X
      f.add(new Character('\u03C5')); // ( 965) X
      f.add(new Character('\u03C6')); // ( 966) X
      f.add(new Character('\u03C7')); // ( 967) X
      f.add(new Character('\u03C8')); // ( 968) X
      f.add(new Character('\u03C9')); // ( 969) X
      f.add(new Character('\u03CA')); // ( 970) X
      f.add(new Character('\u03CB')); // ( 971) X
      f.add(new Character('\u03CC')); // ( 972) X
      f.add(new Character('\u03CD')); // ( 973) X
      f.add(new Character('\u03CE')); // ( 974) X
      f.add(new Character('\u03CF')); // ( 975) X
      f.add(new Character('\u03D0')); // ( 976) X
      f.add(new Character('\u03D1')); // ( 977) X
      f.add(new Character('\u03D2')); // ( 978) X
      f.add(new Character('\u03D3')); // ( 979) X
      f.add(new Character('\u03D4')); // ( 980) X
      f.add(new Character('\u03D5')); // ( 981) X
      f.add(new Character('\u03D6')); // ( 982) X
      f.add(new Character('\u03D7')); // ( 983) X
      f.add(new Character('\u03D8')); // ( 984) X
      f.add(new Character('\u03D9')); // ( 985) X
      f.add(new Character('\u03DA')); // ( 986) X
      f.add(new Character('\u03DB')); // ( 987) X
      f.add(new Character('\u03DC')); // ( 988) X
      f.add(new Character('\u03DD')); // ( 989) X
      f.add(new Character('\u03DE')); // ( 990) X
      f.add(new Character('\u03DF')); // ( 991) X
      f.add(new Character('\u03E0')); // ( 992) X
      f.add(new Character('\u03E1')); // ( 993) X
      f.add(new Character('\u03E2')); // ( 994) X
      f.add(new Character('\u03E3')); // ( 995) X
      f.add(new Character('\u03E4')); // ( 996) X
      f.add(new Character('\u03E5')); // ( 997) X
      f.add(new Character('\u03E6')); // ( 998) X
      f.add(new Character('\u03E7')); // ( 999) X
      f.add(new Character('\u03E8')); // (1000) X
      f.add(new Character('\u03E9')); // (1001) X
      f.add(new Character('\u03EA')); // (1002) X
      f.add(new Character('\u03EB')); // (1003) X
      f.add(new Character('\u03EC')); // (1004) X
      f.add(new Character('\u03ED')); // (1005) X
      f.add(new Character('\u03EE')); // (1006) X
      f.add(new Character('\u03EF')); // (1007) X
      f.add(new Character('\u03F0')); // (1008) X
      f.add(new Character('\u03F1')); // (1009) X
      f.add(new Character('\u03F2')); // (1010) X
      f.add(new Character('\u03F3')); // (1011) X
      f.add(new Character('\u03F4')); // (1012) X
      f.add(new Character('\u03F5')); // (1013) X
      f.add(new Character('\u03F6')); // (1014) X
      f.add(new Character('\u03F7')); // (1015) X
      f.add(new Character('\u03F8')); // (1016) X
      f.add(new Character('\u03F9')); // (1017) X
      f.add(new Character('\u03FA')); // (1018) X
      f.add(new Character('\u03FB')); // (1019) X
      f.add(new Character('\u03FC')); // (1020) X
      f.add(new Character('\u03FD')); // (1021) X
      f.add(new Character('\u03FE')); // (1022) X
      f.add(new Character('\u03FF')); // (1023) X
      f.add(new Character('\u0400')); // (1024) X
      f.add(new Character('\u0401')); // (1025) X
      f.add(new Character('\u0402')); // (1026) X
      f.add(new Character('\u0403')); // (1027) X
      f.add(new Character('\u0404')); // (1028) X
      f.add(new Character('\u0405')); // (1029) X
      f.add(new Character('\u0406')); // (1030) X
      f.add(new Character('\u0407')); // (1031) X
      f.add(new Character('\u0408')); // (1032) X
      f.add(new Character('\u0409')); // (1033) X
      f.add(new Character('\u040A')); // (1034) X
      f.add(new Character('\u040B')); // (1035) X
      f.add(new Character('\u040C')); // (1036) X
      f.add(new Character('\u040D')); // (1037) X
      f.add(new Character('\u040E')); // (1038) X
      f.add(new Character('\u040F')); // (1039) X
      f.add(new Character('\u0410')); // (1040) X
      f.add(new Character('\u0411')); // (1041) X
      f.add(new Character('\u0412')); // (1042) X
      f.add(new Character('\u0413')); // (1043) X
      f.add(new Character('\u0414')); // (1044) X
      f.add(new Character('\u0415')); // (1045) X
      f.add(new Character('\u0416')); // (1046) X
      f.add(new Character('\u0417')); // (1047) X
      f.add(new Character('\u0418')); // (1048) X
      f.add(new Character('\u0419')); // (1049) X
      f.add(new Character('\u041A')); // (1050) X
      f.add(new Character('\u041B')); // (1051) X
      f.add(new Character('\u041C')); // (1052) X
      f.add(new Character('\u041D')); // (1053) X
      f.add(new Character('\u041E')); // (1054) X
      f.add(new Character('\u041F')); // (1055) X
      f.add(new Character('\u0420')); // (1056) X
      f.add(new Character('\u0421')); // (1057) X
      f.add(new Character('\u0422')); // (1058) X
      f.add(new Character('\u0423')); // (1059) X
      f.add(new Character('\u0424')); // (1060) X
      f.add(new Character('\u0425')); // (1061) X
      f.add(new Character('\u0426')); // (1062) X
      f.add(new Character('\u0427')); // (1063) X
      f.add(new Character('\u0428')); // (1064) X
      f.add(new Character('\u0429')); // (1065) X
      f.add(new Character('\u042A')); // (1066) X
      f.add(new Character('\u042B')); // (1067) X
      f.add(new Character('\u042C')); // (1068) X
      f.add(new Character('\u042D')); // (1069) X
      f.add(new Character('\u042E')); // (1070) X
      f.add(new Character('\u042F')); // (1071) X
      f.add(new Character('\u0430')); // (1072) X
      f.add(new Character('\u0431')); // (1073) X
      f.add(new Character('\u0432')); // (1074) X
      f.add(new Character('\u0433')); // (1075) X
      f.add(new Character('\u0434')); // (1076) X
      f.add(new Character('\u0435')); // (1077) X
      f.add(new Character('\u0436')); // (1078) X
      f.add(new Character('\u0437')); // (1079) X
      f.add(new Character('\u0438')); // (1080) X
      f.add(new Character('\u0439')); // (1081) X
      f.add(new Character('\u043A')); // (1082) X
      f.add(new Character('\u043B')); // (1083) X
      f.add(new Character('\u043C')); // (1084) X
      f.add(new Character('\u043D')); // (1085) X
      f.add(new Character('\u043E')); // (1086) X
      f.add(new Character('\u043F')); // (1087) X
      f.add(new Character('\u0440')); // (1088) X
      f.add(new Character('\u0441')); // (1089) X
      f.add(new Character('\u0442')); // (1090) X
      f.add(new Character('\u0443')); // (1091) X
      f.add(new Character('\u0444')); // (1092) X
      f.add(new Character('\u0445')); // (1093) X
      f.add(new Character('\u0446')); // (1094) X
      f.add(new Character('\u0447')); // (1095) X
      f.add(new Character('\u0448')); // (1096) X
      f.add(new Character('\u0449')); // (1097) X
      f.add(new Character('\u044A')); // (1098) X
      f.add(new Character('\u044B')); // (1099) X
      f.add(new Character('\u044C')); // (1100) X
      f.add(new Character('\u044D')); // (1101) X
      f.add(new Character('\u044E')); // (1102) X
      f.add(new Character('\u044F')); // (1103) X
      f.add(new Character('\u0450')); // (1104) X
      f.add(new Character('\u0451')); // (1105) X
      f.add(new Character('\u0452')); // (1106) X
      f.add(new Character('\u0453')); // (1107) X
      f.add(new Character('\u0454')); // (1108) X
      f.add(new Character('\u0455')); // (1109) X
      f.add(new Character('\u0456')); // (1110) X
      f.add(new Character('\u0457')); // (1111) X
      f.add(new Character('\u0458')); // (1112) X
      f.add(new Character('\u0459')); // (1113) X
      f.add(new Character('\u045A')); // (1114) X
      f.add(new Character('\u045B')); // (1115) X
      f.add(new Character('\u045C')); // (1116) X
      f.add(new Character('\u045D')); // (1117) X
      f.add(new Character('\u045E')); // (1118) X
      f.add(new Character('\u045F')); // (1119) X
      f.add(new Character('\u0460')); // (1120) X
      f.add(new Character('\u0461')); // (1121) X
      f.add(new Character('\u0462')); // (1122) X
      f.add(new Character('\u0463')); // (1123) X
      f.add(new Character('\u0464')); // (1124) X
      f.add(new Character('\u0465')); // (1125) X
      f.add(new Character('\u0466')); // (1126) X
      f.add(new Character('\u0467')); // (1127) X
      f.add(new Character('\u0468')); // (1128) X
      f.add(new Character('\u0469')); // (1129) X
      f.add(new Character('\u046A')); // (1130) X
      f.add(new Character('\u046B')); // (1131) X
      f.add(new Character('\u046C')); // (1132) X
      f.add(new Character('\u046D')); // (1133) X
      f.add(new Character('\u046E')); // (1134) X
      f.add(new Character('\u046F')); // (1135) X
      f.add(new Character('\u0470')); // (1136) X
      f.add(new Character('\u0471')); // (1137) X
      f.add(new Character('\u0472')); // (1138) X
      f.add(new Character('\u0473')); // (1139) X
      f.add(new Character('\u0474')); // (1140) X
      f.add(new Character('\u0475')); // (1141) X
      f.add(new Character('\u0476')); // (1142) X
      f.add(new Character('\u0477')); // (1143) X
      f.add(new Character('\u0478')); // (1144) X
      f.add(new Character('\u0479')); // (1145) X
      f.add(new Character('\u047A')); // (1146) X
      f.add(new Character('\u047B')); // (1147) X
      f.add(new Character('\u047C')); // (1148) X
      f.add(new Character('\u047D')); // (1149) X
      f.add(new Character('\u047E')); // (1150) X
      f.add(new Character('\u047F')); // (1151) X
      f.add(new Character('\u0480')); // (1152) X
      f.add(new Character('\u0481')); // (1153) X
      f.add(new Character('\u0482')); // (1154) X
      f.add(new Character('\u0483')); // (1155) X
      f.add(new Character('\u0484')); // (1156) X
      f.add(new Character('\u0485')); // (1157) X
      f.add(new Character('\u0486')); // (1158) X
      f.add(new Character('\u0487')); // (1159) X
      f.add(new Character('\u0488')); // (1160) X
      f.add(new Character('\u0489')); // (1161) X
      f.add(new Character('\u048A')); // (1162) X
      f.add(new Character('\u048B')); // (1163) X
      f.add(new Character('\u048C')); // (1164) X
      f.add(new Character('\u048D')); // (1165) X
      f.add(new Character('\u048E')); // (1166) X
      f.add(new Character('\u048F')); // (1167) X
      f.add(new Character('\u0490')); // (1168) X
      f.add(new Character('\u0491')); // (1169) X
      f.add(new Character('\u0492')); // (1170) X
      f.add(new Character('\u0493')); // (1171) X
      f.add(new Character('\u0494')); // (1172) X
      f.add(new Character('\u0495')); // (1173) X
      f.add(new Character('\u0496')); // (1174) X
      f.add(new Character('\u0497')); // (1175) X
      f.add(new Character('\u0498')); // (1176) X
      f.add(new Character('\u0499')); // (1177) X
      f.add(new Character('\u049A')); // (1178) X
      f.add(new Character('\u049B')); // (1179) X
      f.add(new Character('\u049C')); // (1180) X
      f.add(new Character('\u049D')); // (1181) X
      f.add(new Character('\u049E')); // (1182) X
      f.add(new Character('\u049F')); // (1183) X
      f.add(new Character('\u04A0')); // (1184) X
      f.add(new Character('\u04A1')); // (1185) X
      f.add(new Character('\u04A2')); // (1186) X
      f.add(new Character('\u04A3')); // (1187) X
      f.add(new Character('\u04A4')); // (1188) X
      f.add(new Character('\u04A5')); // (1189) X
      f.add(new Character('\u04A6')); // (1190) X
      f.add(new Character('\u04A7')); // (1191) X
      f.add(new Character('\u04A8')); // (1192) X
      f.add(new Character('\u04A9')); // (1193) X
      f.add(new Character('\u04AA')); // (1194) X
      f.add(new Character('\u04AB')); // (1195) X
      f.add(new Character('\u04AC')); // (1196) X
      f.add(new Character('\u04AD')); // (1197) X
      f.add(new Character('\u04AE')); // (1198) X
      f.add(new Character('\u04AF')); // (1199) X
      f.add(new Character('\u04B0')); // (1200) X
      f.add(new Character('\u04B1')); // (1201) X
      f.add(new Character('\u04B2')); // (1202) X
      f.add(new Character('\u04B3')); // (1203) X
      f.add(new Character('\u04B4')); // (1204) X
      f.add(new Character('\u04B5')); // (1205) X
      f.add(new Character('\u04B6')); // (1206) X
      f.add(new Character('\u04B7')); // (1207) X
      f.add(new Character('\u04B8')); // (1208) X
      f.add(new Character('\u04B9')); // (1209) X
      f.add(new Character('\u04BA')); // (1210) X
      f.add(new Character('\u04BB')); // (1211) X
      f.add(new Character('\u04BC')); // (1212) X
      f.add(new Character('\u04BD')); // (1213) X
      f.add(new Character('\u04BE')); // (1214) X
      f.add(new Character('\u04BF')); // (1215) X
      f.add(new Character('\u04C0')); // (1216) X
      f.add(new Character('\u04C1')); // (1217) X
      f.add(new Character('\u04C2')); // (1218) X
      f.add(new Character('\u04C3')); // (1219) X
      f.add(new Character('\u04C4')); // (1220) X
      f.add(new Character('\u04C5')); // (1221) X
      f.add(new Character('\u04C6')); // (1222) X
      f.add(new Character('\u04C7')); // (1223) X
      f.add(new Character('\u04C8')); // (1224) X
      f.add(new Character('\u04C9')); // (1225) X
      f.add(new Character('\u04CA')); // (1226) X
      f.add(new Character('\u04CB')); // (1227) X
      f.add(new Character('\u04CC')); // (1228) X
      f.add(new Character('\u04CD')); // (1229) X
      f.add(new Character('\u04CE')); // (1230) X
      f.add(new Character('\u04CF')); // (1231) X
      f.add(new Character('\u04D0')); // (1232) X
      f.add(new Character('\u04D1')); // (1233) X
      f.add(new Character('\u04D2')); // (1234) X
      f.add(new Character('\u04D3')); // (1235) X
      f.add(new Character('\u04D4')); // (1236) X
      f.add(new Character('\u04D5')); // (1237) X
      f.add(new Character('\u04D6')); // (1238) X
      f.add(new Character('\u04D7')); // (1239) X
      f.add(new Character('\u04D8')); // (1240) X
      f.add(new Character('\u04D9')); // (1241) X
      f.add(new Character('\u04DA')); // (1242) X
      f.add(new Character('\u04DB')); // (1243) X
      f.add(new Character('\u04DC')); // (1244) X
      f.add(new Character('\u04DD')); // (1245) X
      f.add(new Character('\u04DE')); // (1246) X
      f.add(new Character('\u04DF')); // (1247) X
      f.add(new Character('\u04E0')); // (1248) X
      f.add(new Character('\u04E1')); // (1249) X
      f.add(new Character('\u04E2')); // (1250) X
      f.add(new Character('\u04E3')); // (1251) X
      f.add(new Character('\u04E4')); // (1252) X
      f.add(new Character('\u04E5')); // (1253) X
      f.add(new Character('\u04E6')); // (1254) X
      f.add(new Character('\u04E7')); // (1255) X
      f.add(new Character('\u04E8')); // (1256) X
      f.add(new Character('\u04E9')); // (1257) X
      f.add(new Character('\u04EA')); // (1258) X
      f.add(new Character('\u04EB')); // (1259) X
      f.add(new Character('\u04EC')); // (1260) X
      f.add(new Character('\u04ED')); // (1261) X
      f.add(new Character('\u04EE')); // (1262) X
      f.add(new Character('\u04EF')); // (1263) X
      f.add(new Character('\u04F0')); // (1264) X
      f.add(new Character('\u04F1')); // (1265) X
      f.add(new Character('\u04F2')); // (1266) X
      f.add(new Character('\u04F3')); // (1267) X
      f.add(new Character('\u04F4')); // (1268) X
      f.add(new Character('\u04F5')); // (1269) X
      f.add(new Character('\u04F6')); // (1270) X
      f.add(new Character('\u04F7')); // (1271) X
      f.add(new Character('\u04F8')); // (1272) X
      f.add(new Character('\u04F9')); // (1273) X
      f.add(new Character('\u04FA')); // (1274) X
      f.add(new Character('\u04FB')); // (1275) X
      f.add(new Character('\u04FC')); // (1276) X
      f.add(new Character('\u04FD')); // (1277) X
      f.add(new Character('\u04FE')); // (1278) X
      f.add(new Character('\u04FF')); // (1279) X
      f.add(new Character('\u0500')); // (1280) X
      f.add(new Character('\u0501')); // (1281) X
      f.add(new Character('\u0502')); // (1282) X
      f.add(new Character('\u0503')); // (1283) X
      f.add(new Character('\u0504')); // (1284) X
      f.add(new Character('\u0505')); // (1285) X
      f.add(new Character('\u0506')); // (1286) X
      f.add(new Character('\u0507')); // (1287) X
      f.add(new Character('\u0508')); // (1288) X
      f.add(new Character('\u0509')); // (1289) X
      f.add(new Character('\u050A')); // (1290) X
      f.add(new Character('\u050B')); // (1291) X
      f.add(new Character('\u050C')); // (1292) X
      f.add(new Character('\u050D')); // (1293) X
      f.add(new Character('\u050E')); // (1294) X
      f.add(new Character('\u050F')); // (1295) X
      f.add(new Character('\u0510')); // (1296) X
      f.add(new Character('\u0511')); // (1297) X
      f.add(new Character('\u0512')); // (1298) X
      f.add(new Character('\u0513')); // (1299) X
      f.add(new Character('\u0514')); // (1300) X
      f.add(new Character('\u0515')); // (1301) X
      f.add(new Character('\u0516')); // (1302) X
      f.add(new Character('\u0517')); // (1303) X
      f.add(new Character('\u0518')); // (1304) X
      f.add(new Character('\u0519')); // (1305) X
      f.add(new Character('\u051A')); // (1306) X
      f.add(new Character('\u051B')); // (1307) X
      f.add(new Character('\u051C')); // (1308) X
      f.add(new Character('\u051D')); // (1309) X
      f.add(new Character('\u051E')); // (1310) X
      f.add(new Character('\u051F')); // (1311) X
      f.add(new Character('\u0520')); // (1312) X
      f.add(new Character('\u0521')); // (1313) X
      f.add(new Character('\u0522')); // (1314) X
      f.add(new Character('\u0523')); // (1315) X
      f.add(new Character('\u0524')); // (1316) X
      f.add(new Character('\u0525')); // (1317) X
      f.add(new Character('\u0526')); // (1318) X
      f.add(new Character('\u0527')); // (1319) X
      f.add(new Character('\u0528')); // (1320) X
      f.add(new Character('\u0529')); // (1321) X
      f.add(new Character('\u052A')); // (1322) X
      f.add(new Character('\u052B')); // (1323) X
      f.add(new Character('\u052C')); // (1324) X
      f.add(new Character('\u052D')); // (1325) X
      f.add(new Character('\u052E')); // (1326) X
      f.add(new Character('\u052F')); // (1327) X
      f.add(new Character('\u0530')); // (1328) X
      f.add(new Character('\u0531')); // (1329) X
      f.add(new Character('\u0532')); // (1330) X
      f.add(new Character('\u0533')); // (1331) X
      f.add(new Character('\u0534')); // (1332) X
      f.add(new Character('\u0535')); // (1333) X
   }

   /**
    * Initializes ordinary characters for LUWO for the JA language.
    * @param f The HashSet
    */
   protected static void initJAFlags(HashSet<Character> f)
   {
      f.add(new Character('\u00A6')); // ( 166) BROKEN_BAR
      f.add(new Character('\u00A7')); // ( 167) SECTION
      f.add(new Character('\u00A8')); // ( 168) diaeresis
      f.add(new Character('\u00B0')); // ( 176) degree
      f.add(new Character('\u00B1')); // ( 177) plus_minus
      f.add(new Character('\u00B4')); // ( 180) acute
      f.add(new Character('\u00B6')); // ( 182) Pilcrow
      f.add(new Character('\u00D7')); // ( 215) times
      f.add(new Character('\u0100')); // ( 256) A_bar
      f.add(new Character('\u0101')); // ( 257) a_bar
      f.add(new Character('\u0102')); // ( 258) A_breve
      f.add(new Character('\u0103')); // ( 259) a_breve
      f.add(new Character('\u0104')); // ( 260) A_ogokek
      f.add(new Character('\u0105')); // ( 261) a_ogokek
      f.add(new Character('\u0106')); // ( 262) C_acute
      f.add(new Character('\u0107')); // ( 263) c_acute
      f.add(new Character('\u0108')); // ( 264) C_circumflex
      f.add(new Character('\u0109')); // ( 265) c_circumflex
      f.add(new Character('\u010A')); // ( 266) C_superdot
      f.add(new Character('\u010B')); // ( 267) c_superdot
      f.add(new Character('\u010C')); // ( 268) C_caron
      f.add(new Character('\u010D')); // ( 269) c_caron
      f.add(new Character('\u010E')); // ( 270) D_caron
      f.add(new Character('\u010F')); // ( 271) d_caron
      f.add(new Character('\u0110')); // ( 272) D_stroke
      f.add(new Character('\u0111')); // ( 273) d_stroke
      f.add(new Character('\u0112')); // ( 274) E_macron
      f.add(new Character('\u0113')); // ( 275) e_macron
      f.add(new Character('\u0114')); // ( 276) E_breve
      f.add(new Character('\u0115')); // ( 277) e_breve
      f.add(new Character('\u0116')); // ( 278) E_superdot
      f.add(new Character('\u0117')); // ( 279) e_superdot
      f.add(new Character('\u0118')); // ( 280) E_ogonek
      f.add(new Character('\u0119')); // ( 281) e_ogonek
      f.add(new Character('\u011A')); // ( 282) E_caron
      f.add(new Character('\u011B')); // ( 283) e_caron
      f.add(new Character('\u011C')); // ( 284) G_circumflex
      f.add(new Character('\u011D')); // ( 285) g_circumflex
      f.add(new Character('\u011E')); // ( 286) G_breve
      f.add(new Character('\u011F')); // ( 287) g_breve
      f.add(new Character('\u0120')); // ( 288) G_superdot
      f.add(new Character('\u0121')); // ( 289) g_superdot
      f.add(new Character('\u0122')); // ( 290) G_cedilla
      f.add(new Character('\u0123')); // ( 291) g_cedilla
      f.add(new Character('\u0124')); // ( 292) H_circumflex
      f.add(new Character('\u0125')); // ( 293) h_circumflex
      f.add(new Character('\u0126')); // ( 294) H_stroke
      f.add(new Character('\u0127')); // ( 295) h_stroke
      f.add(new Character('\u0128')); // ( 296) I_tilde
      f.add(new Character('\u0129')); // ( 297) i_tilde
      f.add(new Character('\u012A')); // ( 298) I_macron
      f.add(new Character('\u012B')); // ( 299) i_macron
      f.add(new Character('\u012C')); // ( 300) I_breve
      f.add(new Character('\u012D')); // ( 301) i_breve
      f.add(new Character('\u012E')); // ( 302) I_ogonek
      f.add(new Character('\u012F')); // ( 303) i_ogonek
      f.add(new Character('\u0130')); // ( 304) I_superdot
      f.add(new Character('\u0131')); // ( 305) i_nodot
      f.add(new Character('\u0132')); // ( 306) IJ_ligature
      f.add(new Character('\u0133')); // ( 307) ij_ligature
      f.add(new Character('\u0134')); // ( 308) J_circumflex
      f.add(new Character('\u0135')); // ( 309) j_circumflex
      f.add(new Character('\u0136')); // ( 310) K_cedilla
      f.add(new Character('\u0137')); // ( 311) k_cedilla
      f.add(new Character('\u0138')); // ( 312) kra
      f.add(new Character('\u0139')); // ( 313) L_acute
      f.add(new Character('\u013A')); // ( 314) l_acute
      f.add(new Character('\u013B')); // ( 315) L_cedilla
      f.add(new Character('\u013C')); // ( 316) l_cedilla
      f.add(new Character('\u013D')); // ( 317) L_caron
      f.add(new Character('\u013E')); // ( 318) l_caron
      f.add(new Character('\u013F')); // ( 319) L_middot
      f.add(new Character('\u0140')); // ( 320) l_middot
      f.add(new Character('\u0141')); // ( 321) L_stoke
      f.add(new Character('\u0142')); // ( 322) l_stoke
      f.add(new Character('\u0143')); // ( 323) N_acute
      f.add(new Character('\u0144')); // ( 324) n_acute
      f.add(new Character('\u0145')); // ( 325) N_cedilla
      f.add(new Character('\u0146')); // ( 326) n_cedilla
      f.add(new Character('\u0147')); // ( 327) N_caron
      f.add(new Character('\u0148')); // ( 328) n_caron
      f.add(new Character('\u0149')); // ( 329) n_apostrophe
      f.add(new Character('\u014A')); // ( 330) ENG
      f.add(new Character('\u014B')); // ( 331) eng
      f.add(new Character('\u014C')); // ( 332) O_macron
      f.add(new Character('\u014D')); // ( 333) o_macron
      f.add(new Character('\u014E')); // ( 334) O_breve
      f.add(new Character('\u014F')); // ( 335) o_breve
      f.add(new Character('\u0150')); // ( 336) O_double_acute
      f.add(new Character('\u0151')); // ( 337) o_double_acute
      f.add(new Character('\u0152')); // ( 338) OE_ligature
      f.add(new Character('\u0153')); // ( 339) oe_ligature
      f.add(new Character('\u0154')); // ( 340) R_acute
      f.add(new Character('\u0155')); // ( 341) r_acute
      f.add(new Character('\u0156')); // ( 342) R_cedilla
      f.add(new Character('\u0157')); // ( 343) r_cedilla
      f.add(new Character('\u0158')); // ( 344) R_caron
      f.add(new Character('\u0159')); // ( 345) r_caron
      f.add(new Character('\u015A')); // ( 346) S_acute
      f.add(new Character('\u015B')); // ( 347) s_acute
      f.add(new Character('\u015C')); // ( 348) S_circumflex
      f.add(new Character('\u015D')); // ( 349) s_circumflex
      f.add(new Character('\u015E')); // ( 350) S_cedilla
      f.add(new Character('\u015F')); // ( 351) s_cedilla
      f.add(new Character('\u0160')); // ( 352) S_caron
      f.add(new Character('\u0161')); // ( 353) s_caron
      f.add(new Character('\u0162')); // ( 354) T_cedilla
      f.add(new Character('\u0163')); // ( 355) t_cedilla
      f.add(new Character('\u0164')); // ( 356) T_caron
      f.add(new Character('\u0165')); // ( 357) t_caron
      f.add(new Character('\u0166')); // ( 358) T_stroke
      f.add(new Character('\u0167')); // ( 359) t_stroke
      f.add(new Character('\u0168')); // ( 360) U_tilde
      f.add(new Character('\u0169')); // ( 361) u_tilde
      f.add(new Character('\u016A')); // ( 362) U_macron
      f.add(new Character('\u016B')); // ( 363) u_macron
      f.add(new Character('\u016C')); // ( 364) U_breve
      f.add(new Character('\u016D')); // ( 365) u_breve
      f.add(new Character('\u016E')); // ( 366) U_ring
      f.add(new Character('\u016F')); // ( 367) u_ring
      f.add(new Character('\u0170')); // ( 368) U_double_acute
      f.add(new Character('\u0171')); // ( 369) u_double_acute
      f.add(new Character('\u0172')); // ( 370) U_ogonek
      f.add(new Character('\u0173')); // ( 371) u_ogonek
      f.add(new Character('\u0174')); // ( 372) W_circumflex
      f.add(new Character('\u0175')); // ( 373) w_circumflex
      f.add(new Character('\u0176')); // ( 374) Y_circumflex
      f.add(new Character('\u0177')); // ( 375) y_circumflex
      f.add(new Character('\u0178')); // ( 376) Y_diaeresis
      f.add(new Character('\u0179')); // ( 377) Z_acute
      f.add(new Character('\u017A')); // ( 378) z_acute
      f.add(new Character('\u017B')); // ( 379) Z_superdot
      f.add(new Character('\u017C')); // ( 380) z_superdot
      f.add(new Character('\u017D')); // ( 381) Z_caron
      f.add(new Character('\u017E')); // ( 382) z_caron
      f.add(new Character('\u017F')); // ( 383) long_s
      f.add(new Character('\u0180')); // ( 384) X
      f.add(new Character('\u0181')); // ( 385) X
      f.add(new Character('\u0182')); // ( 386) X
      f.add(new Character('\u0183')); // ( 387) X
      f.add(new Character('\u0184')); // ( 388) X
      f.add(new Character('\u0185')); // ( 389) X
      f.add(new Character('\u0186')); // ( 390) X
      f.add(new Character('\u0187')); // ( 391) X
      f.add(new Character('\u0188')); // ( 392) X
      f.add(new Character('\u0189')); // ( 393) X
      f.add(new Character('\u018A')); // ( 394) X
      f.add(new Character('\u018B')); // ( 395) X
      f.add(new Character('\u018C')); // ( 396) X
      f.add(new Character('\u018D')); // ( 397) X
      f.add(new Character('\u018E')); // ( 398) X
      f.add(new Character('\u018F')); // ( 399) X
      f.add(new Character('\u0190')); // ( 400) X
      f.add(new Character('\u0191')); // ( 401) X
      f.add(new Character('\u0192')); // ( 402) X
      f.add(new Character('\u0193')); // ( 403) X
      f.add(new Character('\u0194')); // ( 404) X
      f.add(new Character('\u0195')); // ( 405) X
      f.add(new Character('\u0196')); // ( 406) X
      f.add(new Character('\u0197')); // ( 407) X
      f.add(new Character('\u0198')); // ( 408) X
      f.add(new Character('\u0199')); // ( 409) X
      f.add(new Character('\u019A')); // ( 410) X
      f.add(new Character('\u019B')); // ( 411) X
      f.add(new Character('\u019C')); // ( 412) X
      f.add(new Character('\u019D')); // ( 413) X
      f.add(new Character('\u019E')); // ( 414) X
      f.add(new Character('\u019F')); // ( 415) X
      f.add(new Character('\u01A0')); // ( 416) X
      f.add(new Character('\u01A1')); // ( 417) X
      f.add(new Character('\u01A2')); // ( 418) X
      f.add(new Character('\u01A3')); // ( 419) X
      f.add(new Character('\u01A4')); // ( 420) X
      f.add(new Character('\u01A5')); // ( 421) X
      f.add(new Character('\u01A6')); // ( 422) X
      f.add(new Character('\u01A7')); // ( 423) X
      f.add(new Character('\u01A8')); // ( 424) X
      f.add(new Character('\u01A9')); // ( 425) X
      f.add(new Character('\u01AA')); // ( 426) X
      f.add(new Character('\u01AB')); // ( 427) X
      f.add(new Character('\u01AC')); // ( 428) X
      f.add(new Character('\u01AD')); // ( 429) X
      f.add(new Character('\u01AE')); // ( 430) X
      f.add(new Character('\u01AF')); // ( 431) X
      f.add(new Character('\u01B0')); // ( 432) X
      f.add(new Character('\u01B1')); // ( 433) X
      f.add(new Character('\u01B2')); // ( 434) X
      f.add(new Character('\u01B3')); // ( 435) X
      f.add(new Character('\u01B4')); // ( 436) X
      f.add(new Character('\u01B5')); // ( 437) X
      f.add(new Character('\u01B6')); // ( 438) X
      f.add(new Character('\u01B7')); // ( 439) X
      f.add(new Character('\u01B8')); // ( 440) X
      f.add(new Character('\u01B9')); // ( 441) X
      f.add(new Character('\u01BA')); // ( 442) X
      f.add(new Character('\u01BB')); // ( 443) X
      f.add(new Character('\u01BC')); // ( 444) X
      f.add(new Character('\u01BD')); // ( 445) X
      f.add(new Character('\u01BE')); // ( 446) X
      f.add(new Character('\u01BF')); // ( 447) X
      f.add(new Character('\u01C0')); // ( 448) X
      f.add(new Character('\u01C1')); // ( 449) X
      f.add(new Character('\u01C2')); // ( 450) X
      f.add(new Character('\u01C3')); // ( 451) X
      f.add(new Character('\u01C4')); // ( 452) X
      f.add(new Character('\u01C5')); // ( 453) X
      f.add(new Character('\u01C6')); // ( 454) X
      f.add(new Character('\u01C7')); // ( 455) X
      f.add(new Character('\u01C8')); // ( 456) X
      f.add(new Character('\u01C9')); // ( 457) X
      f.add(new Character('\u01CA')); // ( 458) X
      f.add(new Character('\u01CB')); // ( 459) X
      f.add(new Character('\u01CC')); // ( 460) X
      f.add(new Character('\u01CD')); // ( 461) X
      f.add(new Character('\u01CE')); // ( 462) X
      f.add(new Character('\u01CF')); // ( 463) X
      f.add(new Character('\u01D0')); // ( 464) X
      f.add(new Character('\u01D1')); // ( 465) X
      f.add(new Character('\u01D2')); // ( 466) X
      f.add(new Character('\u01D3')); // ( 467) X
      f.add(new Character('\u01D4')); // ( 468) X
      f.add(new Character('\u01D5')); // ( 469) X
      f.add(new Character('\u01D6')); // ( 470) X
      f.add(new Character('\u01D7')); // ( 471) X
      f.add(new Character('\u01D8')); // ( 472) X
      f.add(new Character('\u01D9')); // ( 473) X
      f.add(new Character('\u01DA')); // ( 474) X
      f.add(new Character('\u01DB')); // ( 475) X
      f.add(new Character('\u01DC')); // ( 476) X
      f.add(new Character('\u01DD')); // ( 477) X
      f.add(new Character('\u01DE')); // ( 478) X
      f.add(new Character('\u01DF')); // ( 479) X
      f.add(new Character('\u01E0')); // ( 480) X
      f.add(new Character('\u01E1')); // ( 481) X
      f.add(new Character('\u01E2')); // ( 482) X
      f.add(new Character('\u01E3')); // ( 483) X
      f.add(new Character('\u01E4')); // ( 484) X
      f.add(new Character('\u01E5')); // ( 485) X
      f.add(new Character('\u01E6')); // ( 486) X
      f.add(new Character('\u01E7')); // ( 487) X
      f.add(new Character('\u01E8')); // ( 488) X
      f.add(new Character('\u01E9')); // ( 489) X
      f.add(new Character('\u01EA')); // ( 490) X
      f.add(new Character('\u01EB')); // ( 491) X
      f.add(new Character('\u01EC')); // ( 492) X
      f.add(new Character('\u01ED')); // ( 493) X
      f.add(new Character('\u01EE')); // ( 494) X
      f.add(new Character('\u01EF')); // ( 495) X
      f.add(new Character('\u01F0')); // ( 496) X
      f.add(new Character('\u01F1')); // ( 497) X
      f.add(new Character('\u01F2')); // ( 498) X
      f.add(new Character('\u01F3')); // ( 499) X
      f.add(new Character('\u01F4')); // ( 500) X
      f.add(new Character('\u01F5')); // ( 501) X
      f.add(new Character('\u01F6')); // ( 502) X
      f.add(new Character('\u01F7')); // ( 503) X
      f.add(new Character('\u01F8')); // ( 504) X
      f.add(new Character('\u01F9')); // ( 505) X
      f.add(new Character('\u01FA')); // ( 506) X
      f.add(new Character('\u01FB')); // ( 507) X
      f.add(new Character('\u01FC')); // ( 508) X
      f.add(new Character('\u01FD')); // ( 509) X
      f.add(new Character('\u01FE')); // ( 510) X
      f.add(new Character('\u01FF')); // ( 511) X
      f.add(new Character('\u0200')); // ( 512) X
      f.add(new Character('\u0201')); // ( 513) X
      f.add(new Character('\u0202')); // ( 514) X
      f.add(new Character('\u0203')); // ( 515) X
      f.add(new Character('\u0204')); // ( 516) X
      f.add(new Character('\u0205')); // ( 517) X
      f.add(new Character('\u0206')); // ( 518) X
      f.add(new Character('\u0207')); // ( 519) X
      f.add(new Character('\u0208')); // ( 520) X
      f.add(new Character('\u0209')); // ( 521) X
      f.add(new Character('\u020A')); // ( 522) X
      f.add(new Character('\u020B')); // ( 523) X
      f.add(new Character('\u020C')); // ( 524) X
      f.add(new Character('\u020D')); // ( 525) X
      f.add(new Character('\u020E')); // ( 526) X
      f.add(new Character('\u020F')); // ( 527) X
      f.add(new Character('\u0210')); // ( 528) X
      f.add(new Character('\u0211')); // ( 529) X
      f.add(new Character('\u0212')); // ( 530) X
      f.add(new Character('\u0213')); // ( 531) X
      f.add(new Character('\u0214')); // ( 532) X
      f.add(new Character('\u0215')); // ( 533) X
      f.add(new Character('\u0216')); // ( 534) X
      f.add(new Character('\u0217')); // ( 535) X
      f.add(new Character('\u0218')); // ( 536) X
      f.add(new Character('\u0219')); // ( 537) X
      f.add(new Character('\u021A')); // ( 538) X
      f.add(new Character('\u021B')); // ( 539) X
      f.add(new Character('\u021C')); // ( 540) X
      f.add(new Character('\u021D')); // ( 541) X
      f.add(new Character('\u021E')); // ( 542) X
      f.add(new Character('\u021F')); // ( 543) X
      f.add(new Character('\u0220')); // ( 544) X
      f.add(new Character('\u0221')); // ( 545) X
      f.add(new Character('\u0222')); // ( 546) X
      f.add(new Character('\u0223')); // ( 547) X
      f.add(new Character('\u0224')); // ( 548) X
      f.add(new Character('\u0225')); // ( 549) X
      f.add(new Character('\u0226')); // ( 550) X
      f.add(new Character('\u0227')); // ( 551) X
      f.add(new Character('\u0228')); // ( 552) X
      f.add(new Character('\u0229')); // ( 553) X
      f.add(new Character('\u022A')); // ( 554) X
      f.add(new Character('\u022B')); // ( 555) X
      f.add(new Character('\u022C')); // ( 556) X
      f.add(new Character('\u022D')); // ( 557) X
      f.add(new Character('\u022E')); // ( 558) X
      f.add(new Character('\u022F')); // ( 559) X
      f.add(new Character('\u0230')); // ( 560) X
      f.add(new Character('\u0231')); // ( 561) X
      f.add(new Character('\u0232')); // ( 562) X
      f.add(new Character('\u0233')); // ( 563) X
      f.add(new Character('\u0234')); // ( 564) X
      f.add(new Character('\u0235')); // ( 565) X
      f.add(new Character('\u0236')); // ( 566) X
      f.add(new Character('\u0237')); // ( 567) X
      f.add(new Character('\u0238')); // ( 568) X
      f.add(new Character('\u0239')); // ( 569) X
      f.add(new Character('\u023A')); // ( 570) X
      f.add(new Character('\u023B')); // ( 571) X
      f.add(new Character('\u023C')); // ( 572) X
      f.add(new Character('\u023D')); // ( 573) X
      f.add(new Character('\u023E')); // ( 574) X
      f.add(new Character('\u023F')); // ( 575) X
      f.add(new Character('\u0240')); // ( 576) X
      f.add(new Character('\u0241')); // ( 577) X
      f.add(new Character('\u0242')); // ( 578) X
      f.add(new Character('\u0243')); // ( 579) X
      f.add(new Character('\u0244')); // ( 580) X
      f.add(new Character('\u0245')); // ( 581) X
      f.add(new Character('\u0246')); // ( 582) X
      f.add(new Character('\u0247')); // ( 583) X
      f.add(new Character('\u0248')); // ( 584) X
      f.add(new Character('\u0249')); // ( 585) X
      f.add(new Character('\u024A')); // ( 586) X
      f.add(new Character('\u024B')); // ( 587) X
      f.add(new Character('\u024C')); // ( 588) X
      f.add(new Character('\u024D')); // ( 589) X
      f.add(new Character('\u024E')); // ( 590) X
      f.add(new Character('\u024F')); // ( 591) X
      f.add(new Character('\u0250')); // ( 592) X
      f.add(new Character('\u0251')); // ( 593) X
      f.add(new Character('\u0252')); // ( 594) X
      f.add(new Character('\u0253')); // ( 595) X
      f.add(new Character('\u0254')); // ( 596) X
      f.add(new Character('\u0255')); // ( 597) X
      f.add(new Character('\u0256')); // ( 598) X
      f.add(new Character('\u0257')); // ( 599) X
      f.add(new Character('\u0258')); // ( 600) X
      f.add(new Character('\u0259')); // ( 601) X
      f.add(new Character('\u025A')); // ( 602) X
      f.add(new Character('\u025B')); // ( 603) X
      f.add(new Character('\u025C')); // ( 604) X
      f.add(new Character('\u025D')); // ( 605) X
      f.add(new Character('\u025E')); // ( 606) X
      f.add(new Character('\u025F')); // ( 607) X
      f.add(new Character('\u0260')); // ( 608) X
      f.add(new Character('\u0261')); // ( 609) X
      f.add(new Character('\u0262')); // ( 610) X
      f.add(new Character('\u0263')); // ( 611) X
      f.add(new Character('\u0264')); // ( 612) X
      f.add(new Character('\u0265')); // ( 613) X
      f.add(new Character('\u0266')); // ( 614) X
      f.add(new Character('\u0267')); // ( 615) X
      f.add(new Character('\u0268')); // ( 616) X
      f.add(new Character('\u0269')); // ( 617) X
      f.add(new Character('\u026A')); // ( 618) X
      f.add(new Character('\u026B')); // ( 619) X
      f.add(new Character('\u026C')); // ( 620) X
      f.add(new Character('\u026D')); // ( 621) X
      f.add(new Character('\u026E')); // ( 622) X
      f.add(new Character('\u026F')); // ( 623) X
      f.add(new Character('\u0270')); // ( 624) X
      f.add(new Character('\u0271')); // ( 625) X
      f.add(new Character('\u0272')); // ( 626) X
      f.add(new Character('\u0273')); // ( 627) X
      f.add(new Character('\u0274')); // ( 628) X
      f.add(new Character('\u0275')); // ( 629) X
      f.add(new Character('\u0276')); // ( 630) X
      f.add(new Character('\u0277')); // ( 631) X
      f.add(new Character('\u0278')); // ( 632) X
      f.add(new Character('\u0279')); // ( 633) X
      f.add(new Character('\u027A')); // ( 634) X
      f.add(new Character('\u027B')); // ( 635) X
      f.add(new Character('\u027C')); // ( 636) X
      f.add(new Character('\u027D')); // ( 637) X
      f.add(new Character('\u027E')); // ( 638) X
      f.add(new Character('\u027F')); // ( 639) X
      f.add(new Character('\u0280')); // ( 640) X
      f.add(new Character('\u0281')); // ( 641) X
      f.add(new Character('\u0282')); // ( 642) X
      f.add(new Character('\u0283')); // ( 643) X
      f.add(new Character('\u0284')); // ( 644) X
      f.add(new Character('\u0285')); // ( 645) X
      f.add(new Character('\u0286')); // ( 646) X
      f.add(new Character('\u0287')); // ( 647) X
      f.add(new Character('\u0288')); // ( 648) X
      f.add(new Character('\u0289')); // ( 649) X
      f.add(new Character('\u028A')); // ( 650) X
      f.add(new Character('\u028B')); // ( 651) X
      f.add(new Character('\u028C')); // ( 652) X
      f.add(new Character('\u028D')); // ( 653) X
      f.add(new Character('\u028E')); // ( 654) X
      f.add(new Character('\u028F')); // ( 655) X
      f.add(new Character('\u0290')); // ( 656) X
      f.add(new Character('\u0291')); // ( 657) X
      f.add(new Character('\u0292')); // ( 658) X
      f.add(new Character('\u0293')); // ( 659) X
      f.add(new Character('\u0294')); // ( 660) X
      f.add(new Character('\u0295')); // ( 661) X
      f.add(new Character('\u0296')); // ( 662) X
      f.add(new Character('\u0297')); // ( 663) X
      f.add(new Character('\u0298')); // ( 664) X
      f.add(new Character('\u0299')); // ( 665) X
      f.add(new Character('\u029A')); // ( 666) X
      f.add(new Character('\u029B')); // ( 667) X
      f.add(new Character('\u029C')); // ( 668) X
      f.add(new Character('\u029D')); // ( 669) X
      f.add(new Character('\u029E')); // ( 670) X
      f.add(new Character('\u029F')); // ( 671) X
      f.add(new Character('\u02A0')); // ( 672) X
      f.add(new Character('\u02A1')); // ( 673) X
      f.add(new Character('\u02A2')); // ( 674) X
      f.add(new Character('\u02A3')); // ( 675) X
      f.add(new Character('\u02A4')); // ( 676) X
      f.add(new Character('\u02A5')); // ( 677) X
      f.add(new Character('\u02A6')); // ( 678) X
      f.add(new Character('\u02A7')); // ( 679) X
      f.add(new Character('\u02A8')); // ( 680) X
      f.add(new Character('\u02A9')); // ( 681) X
      f.add(new Character('\u02AA')); // ( 682) X
      f.add(new Character('\u02AB')); // ( 683) X
      f.add(new Character('\u02AC')); // ( 684) X
      f.add(new Character('\u02AD')); // ( 685) X
      f.add(new Character('\u02AE')); // ( 686) X
      f.add(new Character('\u02AF')); // ( 687) X
      f.add(new Character('\u02B0')); // ( 688) X
      f.add(new Character('\u02B1')); // ( 689) X
      f.add(new Character('\u02B2')); // ( 690) X
      f.add(new Character('\u02B3')); // ( 691) X
      f.add(new Character('\u02B4')); // ( 692) X
      f.add(new Character('\u02B5')); // ( 693) X
      f.add(new Character('\u02B6')); // ( 694) X
      f.add(new Character('\u02B7')); // ( 695) X
      f.add(new Character('\u02B8')); // ( 696) X
      f.add(new Character('\u02B9')); // ( 697) X
      f.add(new Character('\u02BA')); // ( 698) X
      f.add(new Character('\u02BB')); // ( 699) X
      f.add(new Character('\u02BC')); // ( 700) X
      f.add(new Character('\u02BD')); // ( 701) X
      f.add(new Character('\u02BE')); // ( 702) X
      f.add(new Character('\u02BF')); // ( 703) X
      f.add(new Character('\u02C0')); // ( 704) X
      f.add(new Character('\u02C1')); // ( 705) X
      f.add(new Character('\u02C2')); // ( 706) X
      f.add(new Character('\u02C3')); // ( 707) X
      f.add(new Character('\u02C4')); // ( 708) X
      f.add(new Character('\u02C5')); // ( 709) X
      f.add(new Character('\u02C6')); // ( 710) X
      f.add(new Character('\u02C7')); // ( 711) X
      f.add(new Character('\u02C8')); // ( 712) X
      f.add(new Character('\u02C9')); // ( 713) X
      f.add(new Character('\u02CA')); // ( 714) X
      f.add(new Character('\u02CB')); // ( 715) X
      f.add(new Character('\u02CC')); // ( 716) X
      f.add(new Character('\u02CD')); // ( 717) X
      f.add(new Character('\u02CE')); // ( 718) X
      f.add(new Character('\u02CF')); // ( 719) X
      f.add(new Character('\u02D0')); // ( 720) X
      f.add(new Character('\u02D1')); // ( 721) X
      f.add(new Character('\u02D2')); // ( 722) X
      f.add(new Character('\u02D3')); // ( 723) X
      f.add(new Character('\u02D4')); // ( 724) X
      f.add(new Character('\u02D5')); // ( 725) X
      f.add(new Character('\u02D6')); // ( 726) X
      f.add(new Character('\u02D7')); // ( 727) X
      f.add(new Character('\u02D8')); // ( 728) X
      f.add(new Character('\u02D9')); // ( 729) X
      f.add(new Character('\u02DA')); // ( 730) X
      f.add(new Character('\u02DB')); // ( 731) X
      f.add(new Character('\u02DC')); // ( 732) X
      f.add(new Character('\u02DD')); // ( 733) X
      f.add(new Character('\u02DE')); // ( 734) X
      f.add(new Character('\u02DF')); // ( 735) X
      f.add(new Character('\u02E0')); // ( 736) X
      f.add(new Character('\u02E1')); // ( 737) X
      f.add(new Character('\u02E2')); // ( 738) X
      f.add(new Character('\u02E3')); // ( 739) X
      f.add(new Character('\u02E4')); // ( 740) X
      f.add(new Character('\u02E5')); // ( 741) X
      f.add(new Character('\u02E6')); // ( 742) X
      f.add(new Character('\u02E7')); // ( 743) X
      f.add(new Character('\u02E8')); // ( 744) X
      f.add(new Character('\u02E9')); // ( 745) X
      f.add(new Character('\u02EA')); // ( 746) X
      f.add(new Character('\u02EB')); // ( 747) X
      f.add(new Character('\u02EC')); // ( 748) X
      f.add(new Character('\u02ED')); // ( 749) X
      f.add(new Character('\u02EE')); // ( 750) X
      f.add(new Character('\u02EF')); // ( 751) X
      f.add(new Character('\u02F0')); // ( 752) X
      f.add(new Character('\u02F1')); // ( 753) X
      f.add(new Character('\u02F2')); // ( 754) X
      f.add(new Character('\u02F3')); // ( 755) X
      f.add(new Character('\u02F4')); // ( 756) X
      f.add(new Character('\u02F5')); // ( 757) X
      f.add(new Character('\u02F6')); // ( 758) X
      f.add(new Character('\u02F7')); // ( 759) X
      f.add(new Character('\u02F8')); // ( 760) X
      f.add(new Character('\u02F9')); // ( 761) X
      f.add(new Character('\u02FA')); // ( 762) X
      f.add(new Character('\u02FB')); // ( 763) X
      f.add(new Character('\u02FC')); // ( 764) X
      f.add(new Character('\u02FD')); // ( 765) X
      f.add(new Character('\u02FE')); // ( 766) X
      f.add(new Character('\u02FF')); // ( 767) X
      f.add(new Character('\u0300')); // ( 768) X
      f.add(new Character('\u0301')); // ( 769) X
      f.add(new Character('\u0302')); // ( 770) X
      f.add(new Character('\u0303')); // ( 771) X
      f.add(new Character('\u0304')); // ( 772) X
      f.add(new Character('\u0305')); // ( 773) X
      f.add(new Character('\u0306')); // ( 774) X
      f.add(new Character('\u0307')); // ( 775) X
      f.add(new Character('\u0308')); // ( 776) X
      f.add(new Character('\u0309')); // ( 777) X
      f.add(new Character('\u030A')); // ( 778) X
      f.add(new Character('\u030B')); // ( 779) X
      f.add(new Character('\u030C')); // ( 780) X
      f.add(new Character('\u030D')); // ( 781) X
      f.add(new Character('\u030E')); // ( 782) X
      f.add(new Character('\u030F')); // ( 783) X
      f.add(new Character('\u0310')); // ( 784) X
      f.add(new Character('\u0311')); // ( 785) X
      f.add(new Character('\u0312')); // ( 786) X
      f.add(new Character('\u0313')); // ( 787) X
      f.add(new Character('\u0314')); // ( 788) X
      f.add(new Character('\u0315')); // ( 789) X
      f.add(new Character('\u0316')); // ( 790) X
      f.add(new Character('\u0317')); // ( 791) X
      f.add(new Character('\u0318')); // ( 792) X
      f.add(new Character('\u0319')); // ( 793) X
      f.add(new Character('\u031A')); // ( 794) X
      f.add(new Character('\u031B')); // ( 795) X
      f.add(new Character('\u031C')); // ( 796) X
      f.add(new Character('\u031D')); // ( 797) X
      f.add(new Character('\u031E')); // ( 798) X
      f.add(new Character('\u031F')); // ( 799) X
      f.add(new Character('\u0320')); // ( 800) X
      f.add(new Character('\u0321')); // ( 801) X
      f.add(new Character('\u0322')); // ( 802) X
      f.add(new Character('\u0323')); // ( 803) X
      f.add(new Character('\u0324')); // ( 804) X
      f.add(new Character('\u0325')); // ( 805) X
      f.add(new Character('\u0326')); // ( 806) X
      f.add(new Character('\u0327')); // ( 807) X
      f.add(new Character('\u0328')); // ( 808) X
      f.add(new Character('\u0329')); // ( 809) X
      f.add(new Character('\u032A')); // ( 810) X
      f.add(new Character('\u032B')); // ( 811) X
      f.add(new Character('\u032C')); // ( 812) X
      f.add(new Character('\u032D')); // ( 813) X
      f.add(new Character('\u032E')); // ( 814) X
      f.add(new Character('\u032F')); // ( 815) X
      f.add(new Character('\u0330')); // ( 816) X
      f.add(new Character('\u0331')); // ( 817) X
      f.add(new Character('\u0332')); // ( 818) X
      f.add(new Character('\u0333')); // ( 819) X
      f.add(new Character('\u0334')); // ( 820) X
      f.add(new Character('\u0335')); // ( 821) X
      f.add(new Character('\u0336')); // ( 822) X
      f.add(new Character('\u0337')); // ( 823) X
      f.add(new Character('\u0338')); // ( 824) X
      f.add(new Character('\u0339')); // ( 825) X
      f.add(new Character('\u033A')); // ( 826) X
      f.add(new Character('\u033B')); // ( 827) X
      f.add(new Character('\u033C')); // ( 828) X
      f.add(new Character('\u033D')); // ( 829) X
      f.add(new Character('\u033E')); // ( 830) X
      f.add(new Character('\u033F')); // ( 831) X
      f.add(new Character('\u0340')); // ( 832) X
      f.add(new Character('\u0341')); // ( 833) X
      f.add(new Character('\u0342')); // ( 834) X
      f.add(new Character('\u0343')); // ( 835) X
      f.add(new Character('\u0344')); // ( 836) X
      f.add(new Character('\u0345')); // ( 837) X
      f.add(new Character('\u0346')); // ( 838) X
      f.add(new Character('\u0347')); // ( 839) X
      f.add(new Character('\u0348')); // ( 840) X
      f.add(new Character('\u0349')); // ( 841) X
      f.add(new Character('\u034A')); // ( 842) X
      f.add(new Character('\u034B')); // ( 843) X
      f.add(new Character('\u034C')); // ( 844) X
      f.add(new Character('\u034D')); // ( 845) X
      f.add(new Character('\u034E')); // ( 846) X
      f.add(new Character('\u034F')); // ( 847) X
      f.add(new Character('\u0350')); // ( 848) X
      f.add(new Character('\u0351')); // ( 849) X
      f.add(new Character('\u0352')); // ( 850) X
      f.add(new Character('\u0353')); // ( 851) X
      f.add(new Character('\u0354')); // ( 852) X
      f.add(new Character('\u0355')); // ( 853) X
      f.add(new Character('\u0356')); // ( 854) X
      f.add(new Character('\u0357')); // ( 855) X
      f.add(new Character('\u0358')); // ( 856) X
      f.add(new Character('\u0359')); // ( 857) X
      f.add(new Character('\u035A')); // ( 858) X
      f.add(new Character('\u035B')); // ( 859) X
      f.add(new Character('\u035C')); // ( 860) X
      f.add(new Character('\u035D')); // ( 861) X
      f.add(new Character('\u035E')); // ( 862) X
      f.add(new Character('\u035F')); // ( 863) X
      f.add(new Character('\u0360')); // ( 864) X
      f.add(new Character('\u0361')); // ( 865) X
      f.add(new Character('\u0362')); // ( 866) X
      f.add(new Character('\u0363')); // ( 867) X
      f.add(new Character('\u0364')); // ( 868) X
      f.add(new Character('\u0365')); // ( 869) X
      f.add(new Character('\u0366')); // ( 870) X
      f.add(new Character('\u0367')); // ( 871) X
      f.add(new Character('\u0368')); // ( 872) X
      f.add(new Character('\u0369')); // ( 873) X
      f.add(new Character('\u036A')); // ( 874) X
      f.add(new Character('\u036B')); // ( 875) X
      f.add(new Character('\u036C')); // ( 876) X
      f.add(new Character('\u036D')); // ( 877) X
      f.add(new Character('\u036E')); // ( 878) X
      f.add(new Character('\u036F')); // ( 879) X
      f.add(new Character('\u0370')); // ( 880) X
      f.add(new Character('\u0371')); // ( 881) X
      f.add(new Character('\u0372')); // ( 882) X
      f.add(new Character('\u0373')); // ( 883) X
      f.add(new Character('\u0374')); // ( 884) X
      f.add(new Character('\u0375')); // ( 885) X
      f.add(new Character('\u0376')); // ( 886) X
      f.add(new Character('\u0377')); // ( 887) X
      f.add(new Character('\u0378')); // ( 888) X
      f.add(new Character('\u0379')); // ( 889) X
      f.add(new Character('\u037A')); // ( 890) X
      f.add(new Character('\u037B')); // ( 891) X
      f.add(new Character('\u037C')); // ( 892) X
      f.add(new Character('\u037D')); // ( 893) X
      f.add(new Character('\u037E')); // ( 894) X
      f.add(new Character('\u037F')); // ( 895) X
      f.add(new Character('\u0380')); // ( 896) X
      f.add(new Character('\u0381')); // ( 897) X
      f.add(new Character('\u0382')); // ( 898) X
      f.add(new Character('\u0383')); // ( 899) X
      f.add(new Character('\u0384')); // ( 900) X
      f.add(new Character('\u0385')); // ( 901) X
      f.add(new Character('\u0386')); // ( 902) X
      f.add(new Character('\u0387')); // ( 903) X
      f.add(new Character('\u0388')); // ( 904) X
      f.add(new Character('\u0389')); // ( 905) X
      f.add(new Character('\u038A')); // ( 906) X
      f.add(new Character('\u038B')); // ( 907) X
      f.add(new Character('\u038C')); // ( 908) X
      f.add(new Character('\u038D')); // ( 909) X
      f.add(new Character('\u038E')); // ( 910) X
      f.add(new Character('\u038F')); // ( 911) X
      f.add(new Character('\u0390')); // ( 912) X
      f.add(new Character('\u0391')); // ( 913) X
      f.add(new Character('\u0392')); // ( 914) X
      f.add(new Character('\u0393')); // ( 915) X
      f.add(new Character('\u0394')); // ( 916) X
      f.add(new Character('\u0395')); // ( 917) X
      f.add(new Character('\u0396')); // ( 918) X
      f.add(new Character('\u0397')); // ( 919) X
      f.add(new Character('\u0398')); // ( 920) X
      f.add(new Character('\u0399')); // ( 921) X
      f.add(new Character('\u039A')); // ( 922) X
      f.add(new Character('\u039B')); // ( 923) X
      f.add(new Character('\u039C')); // ( 924) X
      f.add(new Character('\u039D')); // ( 925) X
      f.add(new Character('\u039E')); // ( 926) X
      f.add(new Character('\u039F')); // ( 927) X
      f.add(new Character('\u03A0')); // ( 928) X
      f.add(new Character('\u03A1')); // ( 929) X
      f.add(new Character('\u03A2')); // ( 930) X
      f.add(new Character('\u03A3')); // ( 931) X
      f.add(new Character('\u03A4')); // ( 932) X
      f.add(new Character('\u03A5')); // ( 933) X
      f.add(new Character('\u03A6')); // ( 934) X
      f.add(new Character('\u03A7')); // ( 935) X
      f.add(new Character('\u03A8')); // ( 936) X
      f.add(new Character('\u03A9')); // ( 937) X
      f.add(new Character('\u03AA')); // ( 938) X
      f.add(new Character('\u03AB')); // ( 939) X
      f.add(new Character('\u03AC')); // ( 940) X
      f.add(new Character('\u03AD')); // ( 941) X
      f.add(new Character('\u03AE')); // ( 942) X
      f.add(new Character('\u03AF')); // ( 943) X
      f.add(new Character('\u03B0')); // ( 944) X
      f.add(new Character('\u03B1')); // ( 945) X
      f.add(new Character('\u03B2')); // ( 946) X
      f.add(new Character('\u03B3')); // ( 947) X
      f.add(new Character('\u03B4')); // ( 948) X
      f.add(new Character('\u03B5')); // ( 949) X
      f.add(new Character('\u03B6')); // ( 950) X
      f.add(new Character('\u03B7')); // ( 951) X
      f.add(new Character('\u03B8')); // ( 952) X
      f.add(new Character('\u03B9')); // ( 953) X
      f.add(new Character('\u03BA')); // ( 954) X
      f.add(new Character('\u03BB')); // ( 955) X
      f.add(new Character('\u03BC')); // ( 956) X
      f.add(new Character('\u03BD')); // ( 957) X
      f.add(new Character('\u03BE')); // ( 958) X
      f.add(new Character('\u03BF')); // ( 959) X
      f.add(new Character('\u03C0')); // ( 960) X
      f.add(new Character('\u03C1')); // ( 961) X
      f.add(new Character('\u03C2')); // ( 962) X
      f.add(new Character('\u03C3')); // ( 963) X
      f.add(new Character('\u03C4')); // ( 964) X
      f.add(new Character('\u03C5')); // ( 965) X
      f.add(new Character('\u03C6')); // ( 966) X
      f.add(new Character('\u03C7')); // ( 967) X
      f.add(new Character('\u03C8')); // ( 968) X
      f.add(new Character('\u03C9')); // ( 969) X
      f.add(new Character('\u03CA')); // ( 970) X
      f.add(new Character('\u03CB')); // ( 971) X
      f.add(new Character('\u03CC')); // ( 972) X
      f.add(new Character('\u03CD')); // ( 973) X
      f.add(new Character('\u03CE')); // ( 974) X
      f.add(new Character('\u03CF')); // ( 975) X
      f.add(new Character('\u03D0')); // ( 976) X
      f.add(new Character('\u03D1')); // ( 977) X
      f.add(new Character('\u03D2')); // ( 978) X
      f.add(new Character('\u03D3')); // ( 979) X
      f.add(new Character('\u03D4')); // ( 980) X
      f.add(new Character('\u03D5')); // ( 981) X
      f.add(new Character('\u03D6')); // ( 982) X
      f.add(new Character('\u03D7')); // ( 983) X
      f.add(new Character('\u03D8')); // ( 984) X
      f.add(new Character('\u03D9')); // ( 985) X
      f.add(new Character('\u03DA')); // ( 986) X
      f.add(new Character('\u03DB')); // ( 987) X
      f.add(new Character('\u03DC')); // ( 988) X
      f.add(new Character('\u03DD')); // ( 989) X
      f.add(new Character('\u03DE')); // ( 990) X
      f.add(new Character('\u03DF')); // ( 991) X
      f.add(new Character('\u03E0')); // ( 992) X
      f.add(new Character('\u03E1')); // ( 993) X
      f.add(new Character('\u03E2')); // ( 994) X
      f.add(new Character('\u03E3')); // ( 995) X
      f.add(new Character('\u03E4')); // ( 996) X
      f.add(new Character('\u03E5')); // ( 997) X
      f.add(new Character('\u03E6')); // ( 998) X
      f.add(new Character('\u03E7')); // ( 999) X
      f.add(new Character('\u03E8')); // (1000) X
      f.add(new Character('\u03E9')); // (1001) X
      f.add(new Character('\u03EA')); // (1002) X
      f.add(new Character('\u03EB')); // (1003) X
      f.add(new Character('\u03EC')); // (1004) X
      f.add(new Character('\u03ED')); // (1005) X
      f.add(new Character('\u03EE')); // (1006) X
      f.add(new Character('\u03EF')); // (1007) X
      f.add(new Character('\u03F0')); // (1008) X
      f.add(new Character('\u03F1')); // (1009) X
      f.add(new Character('\u03F2')); // (1010) X
      f.add(new Character('\u03F3')); // (1011) X
      f.add(new Character('\u03F4')); // (1012) X
      f.add(new Character('\u03F5')); // (1013) X
      f.add(new Character('\u03F6')); // (1014) X
      f.add(new Character('\u03F7')); // (1015) X
      f.add(new Character('\u03F8')); // (1016) X
      f.add(new Character('\u03F9')); // (1017) X
      f.add(new Character('\u03FA')); // (1018) X
      f.add(new Character('\u03FB')); // (1019) X
      f.add(new Character('\u03FC')); // (1020) X
      f.add(new Character('\u03FD')); // (1021) X
      f.add(new Character('\u03FE')); // (1022) X
      f.add(new Character('\u03FF')); // (1023) X
      f.add(new Character('\u0400')); // (1024) X
      f.add(new Character('\u0401')); // (1025) X
      f.add(new Character('\u0402')); // (1026) X
      f.add(new Character('\u0403')); // (1027) X
      f.add(new Character('\u0404')); // (1028) X
      f.add(new Character('\u0405')); // (1029) X
      f.add(new Character('\u0406')); // (1030) X
      f.add(new Character('\u0407')); // (1031) X
      f.add(new Character('\u0408')); // (1032) X
      f.add(new Character('\u0409')); // (1033) X
      f.add(new Character('\u040A')); // (1034) X
      f.add(new Character('\u040B')); // (1035) X
      f.add(new Character('\u040C')); // (1036) X
      f.add(new Character('\u040D')); // (1037) X
      f.add(new Character('\u040E')); // (1038) X
      f.add(new Character('\u040F')); // (1039) X
      f.add(new Character('\u0410')); // (1040) X
      f.add(new Character('\u0411')); // (1041) X
      f.add(new Character('\u0412')); // (1042) X
      f.add(new Character('\u0413')); // (1043) X
      f.add(new Character('\u0414')); // (1044) X
      f.add(new Character('\u0415')); // (1045) X
      f.add(new Character('\u0416')); // (1046) X
      f.add(new Character('\u0417')); // (1047) X
      f.add(new Character('\u0418')); // (1048) X
      f.add(new Character('\u0419')); // (1049) X
      f.add(new Character('\u041A')); // (1050) X
      f.add(new Character('\u041B')); // (1051) X
      f.add(new Character('\u041C')); // (1052) X
      f.add(new Character('\u041D')); // (1053) X
      f.add(new Character('\u041E')); // (1054) X
      f.add(new Character('\u041F')); // (1055) X
      f.add(new Character('\u0420')); // (1056) X
      f.add(new Character('\u0421')); // (1057) X
      f.add(new Character('\u0422')); // (1058) X
      f.add(new Character('\u0423')); // (1059) X
      f.add(new Character('\u0424')); // (1060) X
      f.add(new Character('\u0425')); // (1061) X
      f.add(new Character('\u0426')); // (1062) X
      f.add(new Character('\u0427')); // (1063) X
      f.add(new Character('\u0428')); // (1064) X
      f.add(new Character('\u0429')); // (1065) X
      f.add(new Character('\u042A')); // (1066) X
      f.add(new Character('\u042B')); // (1067) X
      f.add(new Character('\u042C')); // (1068) X
      f.add(new Character('\u042D')); // (1069) X
      f.add(new Character('\u042E')); // (1070) X
      f.add(new Character('\u042F')); // (1071) X
      f.add(new Character('\u0430')); // (1072) X
      f.add(new Character('\u0431')); // (1073) X
      f.add(new Character('\u0432')); // (1074) X
      f.add(new Character('\u0433')); // (1075) X
      f.add(new Character('\u0434')); // (1076) X
      f.add(new Character('\u0435')); // (1077) X
      f.add(new Character('\u0436')); // (1078) X
      f.add(new Character('\u0437')); // (1079) X
      f.add(new Character('\u0438')); // (1080) X
      f.add(new Character('\u0439')); // (1081) X
      f.add(new Character('\u043A')); // (1082) X
      f.add(new Character('\u043B')); // (1083) X
      f.add(new Character('\u043C')); // (1084) X
      f.add(new Character('\u043D')); // (1085) X
      f.add(new Character('\u043E')); // (1086) X
      f.add(new Character('\u043F')); // (1087) X
      f.add(new Character('\u0440')); // (1088) X
      f.add(new Character('\u0441')); // (1089) X
      f.add(new Character('\u0442')); // (1090) X
      f.add(new Character('\u0443')); // (1091) X
      f.add(new Character('\u0444')); // (1092) X
      f.add(new Character('\u0445')); // (1093) X
      f.add(new Character('\u0446')); // (1094) X
      f.add(new Character('\u0447')); // (1095) X
      f.add(new Character('\u0448')); // (1096) X
      f.add(new Character('\u0449')); // (1097) X
      f.add(new Character('\u044A')); // (1098) X
      f.add(new Character('\u044B')); // (1099) X
      f.add(new Character('\u044C')); // (1100) X
      f.add(new Character('\u044D')); // (1101) X
      f.add(new Character('\u044E')); // (1102) X
      f.add(new Character('\u044F')); // (1103) X
      f.add(new Character('\u0450')); // (1104) X
      f.add(new Character('\u0451')); // (1105) X
      f.add(new Character('\u0452')); // (1106) X
      f.add(new Character('\u0453')); // (1107) X
      f.add(new Character('\u0454')); // (1108) X
      f.add(new Character('\u0455')); // (1109) X
      f.add(new Character('\u0456')); // (1110) X
      f.add(new Character('\u0457')); // (1111) X
      f.add(new Character('\u0458')); // (1112) X
      f.add(new Character('\u0459')); // (1113) X
      f.add(new Character('\u045A')); // (1114) X
      f.add(new Character('\u045B')); // (1115) X
      f.add(new Character('\u045C')); // (1116) X
      f.add(new Character('\u045D')); // (1117) X
      f.add(new Character('\u045E')); // (1118) X
      f.add(new Character('\u045F')); // (1119) X
      f.add(new Character('\u0460')); // (1120) X
      f.add(new Character('\u0461')); // (1121) X
      f.add(new Character('\u0462')); // (1122) X
      f.add(new Character('\u0463')); // (1123) X
      f.add(new Character('\u0464')); // (1124) X
      f.add(new Character('\u0465')); // (1125) X
      f.add(new Character('\u0466')); // (1126) X
      f.add(new Character('\u0467')); // (1127) X
      f.add(new Character('\u0468')); // (1128) X
   }

   /**
    * Initializes ordinary characters for LUWO for the IT language.
    * @param f The HashSet
    */
   private static void initITFlags(HashSet<Character> f)
   {
      f.add(new Character('\u00AA')); // (  170) feminine_ordinal
      f.add(new Character('\u00BA')); // (  186) masculine_ordinal
      f.add(new Character('\u00C0')); // (  192) A_grave
      f.add(new Character('\u00C1')); // (  193) A_acute
      f.add(new Character('\u00C2')); // (  194) A_circumflex
      f.add(new Character('\u00C3')); // (  195) A_tilde
      f.add(new Character('\u00C4')); // (  196) A_umlaut
      f.add(new Character('\u00C5')); // (  197) A_ring
      f.add(new Character('\u00C6')); // (  198) AE_ligature
      f.add(new Character('\u00C7')); // (  199) C_cedilla
      f.add(new Character('\u00C8')); // (  200) E_grave
      f.add(new Character('\u00C9')); // (  201) E_acute
      f.add(new Character('\u00CA')); // (  202) E_circumflex
      f.add(new Character('\u00CB')); // (  203) E_umlaut
      f.add(new Character('\u00CC')); // (  204) I_grave
      f.add(new Character('\u00CD')); // (  205) I_acute
      f.add(new Character('\u00CE')); // (  206) I_circumflex
      f.add(new Character('\u00CF')); // (  207) I_umlaut
      f.add(new Character('\u00D0')); // (  208) ETH_Q
      f.add(new Character('\u00D1')); // (  209) N_tilde
      f.add(new Character('\u00D2')); // (  210) O_grave
      f.add(new Character('\u00D3')); // (  211) O_acute
      f.add(new Character('\u00D4')); // (  212) O_circumflex
      f.add(new Character('\u00D5')); // (  213) O_tilde
      f.add(new Character('\u00D6')); // (  214) O_umlaut
      f.add(new Character('\u00D8')); // (  216) O_slash
      f.add(new Character('\u00D9')); // (  217) U_grave
      f.add(new Character('\u00DA')); // (  218) U_acute
      f.add(new Character('\u00DB')); // (  219) U_circumflex
      f.add(new Character('\u00DC')); // (  220) U_umlaut
      f.add(new Character('\u00DD')); // (  221) Y_acute
      f.add(new Character('\u00DE')); // (  222) THORN
      f.add(new Character('\u00DF')); // (  223) sharp_s
      f.add(new Character('\u00E0')); // (  224) a_grave
      f.add(new Character('\u00E1')); // (  225) a_acute
      f.add(new Character('\u00E2')); // (  226) a_circumflex
      f.add(new Character('\u00E3')); // (  227) a_tilde
      f.add(new Character('\u00E4')); // (  228) a_umlaut
      f.add(new Character('\u00E5')); // (  229) a_ring
      f.add(new Character('\u00E6')); // (  230) ae_ligature
      f.add(new Character('\u00E7')); // (  231) c_cedilla
      f.add(new Character('\u00E8')); // (  232) e_grave
      f.add(new Character('\u00E9')); // (  233) e_acute
      f.add(new Character('\u00EA')); // (  234) e_circumflex
      f.add(new Character('\u00EB')); // (  235) e_umlaut
      f.add(new Character('\u00EC')); // (  236) i_grave
      f.add(new Character('\u00ED')); // (  237) i_acute
      f.add(new Character('\u00EE')); // (  238) i_circumflex
      f.add(new Character('\u00EF')); // (  239) i_umlaut
      f.add(new Character('\u00F0')); // (  240) eth
      f.add(new Character('\u00F1')); // (  241) n_tilde
      f.add(new Character('\u00F2')); // (  242) o_grave
      f.add(new Character('\u00F3')); // (  243) o_acute
      f.add(new Character('\u00F4')); // (  244) o_circumflex
      f.add(new Character('\u00F5')); // (  245) o_tilde
      f.add(new Character('\u00F6')); // (  246) o_umlaut
      f.add(new Character('\u00F8')); // (  248) o_slash
      f.add(new Character('\u00F9')); // (  249) u_grave
      f.add(new Character('\u00FA')); // (  250) u_acute
      f.add(new Character('\u00FB')); // (  251) u_circumflex
      f.add(new Character('\u00FC')); // (  252) u_umlaut
      f.add(new Character('\u00FD')); // (  253) y_acute
      f.add(new Character('\u00FE')); // (  254) thorn
      f.add(new Character('\u00FF')); // (  255) y_umlaut
      f.add(new Character('\u0152')); // (  338) OE_ligature
      f.add(new Character('\u0153')); // (  339) oe_ligature
      f.add(new Character('\u0160')); // (  352) S_caron
      f.add(new Character('\u0161')); // (  353) s_caron
      f.add(new Character('\u0178')); // (  376) Y_diaeresis
   }

   /**
    * Initializes ordinary characters for LUWO for the HU language.
    * @param f The HashSet
    */
   protected static void initHUFlags(HashSet<Character> f)
   {
      f.add(new Character('\u00B5')); // (  181) micro
      f.add(new Character('\u00C1')); // (  193) A_acute
      f.add(new Character('\u00C2')); // (  194) A_circumflex
      f.add(new Character('\u00C4')); // (  196) A_umlaut
      f.add(new Character('\u00C7')); // (  199) C_cedilla
      f.add(new Character('\u00C9')); // (  201) E_acute
      f.add(new Character('\u00CB')); // (  203) E_umlaut
      f.add(new Character('\u00CD')); // (  205) I_acute
      f.add(new Character('\u00CE')); // (  206) I_circumflex
      f.add(new Character('\u00D3')); // (  211) O_acute
      f.add(new Character('\u00D4')); // (  212) O_circumflex
      f.add(new Character('\u00D6')); // (  214) O_umlaut
      f.add(new Character('\u00DA')); // (  218) U_acute
      f.add(new Character('\u00DC')); // (  220) U_umlaut
      f.add(new Character('\u00DD')); // (  221) Y_acute
      f.add(new Character('\u00DF')); // (  223) sharp_s
      f.add(new Character('\u00E1')); // (  225) a_acute
      f.add(new Character('\u00E2')); // (  226) a_circumflex
      f.add(new Character('\u00E4')); // (  228) a_umlaut
      f.add(new Character('\u00E7')); // (  231) c_cedilla
      f.add(new Character('\u00E9')); // (  233) e_acute
      f.add(new Character('\u00EB')); // (  235) e_umlaut
      f.add(new Character('\u00ED')); // (  237) i_acute
      f.add(new Character('\u00EE')); // (  238) i_circumflex
      f.add(new Character('\u00F3')); // (  243) o_acute
      f.add(new Character('\u00F4')); // (  244) o_circumflex
      f.add(new Character('\u00F6')); // (  246) o_umlaut
      f.add(new Character('\u00FA')); // (  250) u_acute
      f.add(new Character('\u00FC')); // (  252) u_umlaut
      f.add(new Character('\u00FD')); // (  253) y_acute
      f.add(new Character('\u0102')); // (  258) A_breve
      f.add(new Character('\u0103')); // (  259) a_breve
      f.add(new Character('\u0104')); // (  260) A_ogokek
      f.add(new Character('\u0105')); // (  261) a_ogokek
      f.add(new Character('\u0106')); // (  262) C_acute
      f.add(new Character('\u0107')); // (  263) c_acute
      f.add(new Character('\u010C')); // (  268) C_caron
      f.add(new Character('\u010D')); // (  269) c_caron
      f.add(new Character('\u010E')); // (  270) D_caron
      f.add(new Character('\u010F')); // (  271) d_caron
      f.add(new Character('\u0110')); // (  272) D_stroke
      f.add(new Character('\u0111')); // (  273) d_stroke
      f.add(new Character('\u0118')); // (  280) E_ogonek
      f.add(new Character('\u0119')); // (  281) e_ogonek
      f.add(new Character('\u011A')); // (  282) E_caron
      f.add(new Character('\u011B')); // (  283) e_caron
      f.add(new Character('\u0139')); // (  313) L_acute
      f.add(new Character('\u013A')); // (  314) l_acute
      f.add(new Character('\u013D')); // (  317) L_caron
      f.add(new Character('\u013E')); // (  318) l_caron
      f.add(new Character('\u0141')); // (  321) L_stoke
      f.add(new Character('\u0142')); // (  322) l_stoke
      f.add(new Character('\u0143')); // (  323) N_acute
      f.add(new Character('\u0144')); // (  324) n_acute
      f.add(new Character('\u0147')); // (  327) N_caron
      f.add(new Character('\u0148')); // (  328) n_caron
      f.add(new Character('\u0150')); // (  336) O_double_acute
      f.add(new Character('\u0151')); // (  337) o_double_acute
      f.add(new Character('\u0154')); // (  340) R_acute
      f.add(new Character('\u0155')); // (  341) r_acute
      f.add(new Character('\u0158')); // (  344) R_caron
      f.add(new Character('\u0159')); // (  345) r_caron
      f.add(new Character('\u015A')); // (  346) S_acute
      f.add(new Character('\u015B')); // (  347) s_acute
      f.add(new Character('\u015E')); // (  350) S_cedilla
      f.add(new Character('\u015F')); // (  351) s_cedilla
      f.add(new Character('\u0160')); // (  352) S_caron
      f.add(new Character('\u0161')); // (  353) s_caron
      f.add(new Character('\u0162')); // (  354) T_cedilla
      f.add(new Character('\u0163')); // (  355) t_cedilla
      f.add(new Character('\u0164')); // (  356) T_caron
      f.add(new Character('\u0165')); // (  357) t_caron
      f.add(new Character('\u016E')); // (  366) U_ring
      f.add(new Character('\u016F')); // (  367) u_ring
      f.add(new Character('\u0170')); // (  368) U_double_acute
      f.add(new Character('\u0171')); // (  369) u_double_acute
      f.add(new Character('\u0179')); // (  377) Z_acute
      f.add(new Character('\u017A')); // (  378) z_acute
      f.add(new Character('\u017B')); // (  379) Z_superdot
      f.add(new Character('\u017C')); // (  380) z_superdot
      f.add(new Character('\u017D')); // (  381) Z_caron
      f.add(new Character('\u017E')); // (  382) z_caron
   }

   /**
    * Initializes ordinary characters for LUWO for the FR language.
    * @param f The HashSet
    */
   protected static void initFRFlags(HashSet<Character> f)
   {
      f.add(new Character('\u00AA')); // (  170) feminine_ordinal
      f.add(new Character('\u00BA')); // (  186) masculine_ordinal
      f.add(new Character('\u00C0')); // (  192) A_grave
      f.add(new Character('\u00C1')); // (  193) A_acute
      f.add(new Character('\u00C2')); // (  194) A_circumflex
      f.add(new Character('\u00C3')); // (  195) A_tilde
      f.add(new Character('\u00C4')); // (  196) A_umlaut
      f.add(new Character('\u00C5')); // (  197) A_ring
      f.add(new Character('\u00C6')); // (  198) AE_ligature
      f.add(new Character('\u00C7')); // (  199) C_cedilla
      f.add(new Character('\u00C8')); // (  200) E_grave
      f.add(new Character('\u00C9')); // (  201) E_acute
      f.add(new Character('\u00CA')); // (  202) E_circumflex
      f.add(new Character('\u00CB')); // (  203) E_umlaut
      f.add(new Character('\u00CC')); // (  204) I_grave
      f.add(new Character('\u00CD')); // (  205) I_acute
      f.add(new Character('\u00CE')); // (  206) I_circumflex
      f.add(new Character('\u00CF')); // (  207) I_umlaut
      f.add(new Character('\u00D0')); // (  208) ETH_Q
      f.add(new Character('\u00D1')); // (  209) N_tilde
      f.add(new Character('\u00D2')); // (  210) O_grave
      f.add(new Character('\u00D3')); // (  211) O_acute
      f.add(new Character('\u00D4')); // (  212) O_circumflex
      f.add(new Character('\u00D5')); // (  213) O_tilde
      f.add(new Character('\u00D6')); // (  214) O_umlaut
      f.add(new Character('\u00D8')); // (  216) O_slash
      f.add(new Character('\u00D9')); // (  217) U_grave
      f.add(new Character('\u00DA')); // (  218) U_acute
      f.add(new Character('\u00DB')); // (  219) U_circumflex
      f.add(new Character('\u00DC')); // (  220) U_umlaut
      f.add(new Character('\u00DD')); // (  221) Y_acute
      f.add(new Character('\u00DE')); // (  222) THORN
      f.add(new Character('\u00DF')); // (  223) sharp_s
      f.add(new Character('\u00E0')); // (  224) a_grave
      f.add(new Character('\u00E1')); // (  225) a_acute
      f.add(new Character('\u00E2')); // (  226) a_circumflex
      f.add(new Character('\u00E3')); // (  227) a_tilde
      f.add(new Character('\u00E4')); // (  228) a_umlaut
      f.add(new Character('\u00E5')); // (  229) a_ring
      f.add(new Character('\u00E6')); // (  230) ae_ligature
      f.add(new Character('\u00E7')); // (  231) c_cedilla
      f.add(new Character('\u00E8')); // (  232) e_grave
      f.add(new Character('\u00E9')); // (  233) e_acute
      f.add(new Character('\u00EA')); // (  234) e_circumflex
      f.add(new Character('\u00EB')); // (  235) e_umlaut
      f.add(new Character('\u00EC')); // (  236) i_grave
      f.add(new Character('\u00ED')); // (  237) i_acute
      f.add(new Character('\u00EE')); // (  238) i_circumflex
      f.add(new Character('\u00EF')); // (  239) i_umlaut
      f.add(new Character('\u00F0')); // (  240) eth
      f.add(new Character('\u00F1')); // (  241) n_tilde
      f.add(new Character('\u00F2')); // (  242) o_grave
      f.add(new Character('\u00F3')); // (  243) o_acute
      f.add(new Character('\u00F4')); // (  244) o_circumflex
      f.add(new Character('\u00F5')); // (  245) o_tilde
      f.add(new Character('\u00F6')); // (  246) o_umlaut
      f.add(new Character('\u00F8')); // (  248) o_slash
      f.add(new Character('\u00F9')); // (  249) u_grave
      f.add(new Character('\u00FA')); // (  250) u_acute
      f.add(new Character('\u00FB')); // (  251) u_circumflex
      f.add(new Character('\u00FC')); // (  252) u_umlaut
      f.add(new Character('\u00FD')); // (  253) y_acute
      f.add(new Character('\u00FE')); // (  254) thorn
      f.add(new Character('\u00FF')); // (  255) y_umlaut
      f.add(new Character('\u0152')); // (  338) OE_ligature
      f.add(new Character('\u0153')); // (  339) oe_ligature
      f.add(new Character('\u0160')); // (  352) S_caron
      f.add(new Character('\u0161')); // (  353) s_caron
      f.add(new Character('\u0178')); // (  376) Y_diaeresis
   }

   /**
    * Initializes ordinary characters for LUWO for the ES language.
    * @param f The HashSet
    */
   protected static void initESFlags(HashSet<Character> f)
   {
      f.add(new Character('\u00AA')); // (  170) feminine_ordinal
      f.add(new Character('\u00BA')); // (  186) masculine_ordinal
      f.add(new Character('\u00C0')); // (  192) A_grave
      f.add(new Character('\u00C1')); // (  193) A_acute
      f.add(new Character('\u00C2')); // (  194) A_circumflex
      f.add(new Character('\u00C3')); // (  195) A_tilde
      f.add(new Character('\u00C4')); // (  196) A_umlaut
      f.add(new Character('\u00C5')); // (  197) A_ring
      f.add(new Character('\u00C6')); // (  198) AE_ligature
      f.add(new Character('\u00C7')); // (  199) C_cedilla
      f.add(new Character('\u00C8')); // (  200) E_grave
      f.add(new Character('\u00C9')); // (  201) E_acute
      f.add(new Character('\u00CA')); // (  202) E_circumflex
      f.add(new Character('\u00CB')); // (  203) E_umlaut
      f.add(new Character('\u00CC')); // (  204) I_grave
      f.add(new Character('\u00CD')); // (  205) I_acute
      f.add(new Character('\u00CE')); // (  206) I_circumflex
      f.add(new Character('\u00CF')); // (  207) I_umlaut
      f.add(new Character('\u00D0')); // (  208) ETH_Q
      f.add(new Character('\u00D1')); // (  209) N_tilde
      f.add(new Character('\u00D2')); // (  210) O_grave
      f.add(new Character('\u00D3')); // (  211) O_acute
      f.add(new Character('\u00D4')); // (  212) O_circumflex
      f.add(new Character('\u00D5')); // (  213) O_tilde
      f.add(new Character('\u00D6')); // (  214) O_umlaut
      f.add(new Character('\u00D8')); // (  216) O_slash
      f.add(new Character('\u00D9')); // (  217) U_grave
      f.add(new Character('\u00DA')); // (  218) U_acute
      f.add(new Character('\u00DB')); // (  219) U_circumflex
      f.add(new Character('\u00DC')); // (  220) U_umlaut
      f.add(new Character('\u00DD')); // (  221) Y_acute
      f.add(new Character('\u00DE')); // (  222) THORN
      f.add(new Character('\u00DF')); // (  223) sharp_s
      f.add(new Character('\u00E0')); // (  224) a_grave
      f.add(new Character('\u00E1')); // (  225) a_acute
      f.add(new Character('\u00E2')); // (  226) a_circumflex
      f.add(new Character('\u00E3')); // (  227) a_tilde
      f.add(new Character('\u00E4')); // (  228) a_umlaut
      f.add(new Character('\u00E5')); // (  229) a_ring
      f.add(new Character('\u00E6')); // (  230) ae_ligature
      f.add(new Character('\u00E7')); // (  231) c_cedilla
      f.add(new Character('\u00E8')); // (  232) e_grave
      f.add(new Character('\u00E9')); // (  233) e_acute
      f.add(new Character('\u00EA')); // (  234) e_circumflex
      f.add(new Character('\u00EB')); // (  235) e_umlaut
      f.add(new Character('\u00EC')); // (  236) i_grave
      f.add(new Character('\u00ED')); // (  237) i_acute
      f.add(new Character('\u00EE')); // (  238) i_circumflex
      f.add(new Character('\u00EF')); // (  239) i_umlaut
      f.add(new Character('\u00F0')); // (  240) eth
      f.add(new Character('\u00F1')); // (  241) n_tilde
      f.add(new Character('\u00F2')); // (  242) o_grave
      f.add(new Character('\u00F3')); // (  243) o_acute
      f.add(new Character('\u00F4')); // (  244) o_circumflex
      f.add(new Character('\u00F5')); // (  245) o_tilde
      f.add(new Character('\u00F6')); // (  246) o_umlaut
      f.add(new Character('\u00F8')); // (  248) o_slash
      f.add(new Character('\u00F9')); // (  249) u_grave
      f.add(new Character('\u00FA')); // (  250) u_acute
      f.add(new Character('\u00FB')); // (  251) u_circumflex
      f.add(new Character('\u00FC')); // (  252) u_umlaut
      f.add(new Character('\u00FD')); // (  253) y_acute
      f.add(new Character('\u00FE')); // (  254) thorn
      f.add(new Character('\u00FF')); // (  255) y_umlaut
      f.add(new Character('\u0152')); // (  338) OE_ligature
      f.add(new Character('\u0153')); // (  339) oe_ligature
      f.add(new Character('\u0160')); // (  352) S_caron
      f.add(new Character('\u0161')); // (  353) s_caron
      f.add(new Character('\u0178')); // (  376) Y_diaeresis
   }

   /**
    * Initializes ordinary characters for LUWO for the DE language.
    * @param f The HashSet
    */
   protected static void initDEFlags(HashSet<Character> f)
   {
      f.add(new Character('\u00AA')); // (  170) feminine_ordinal
      f.add(new Character('\u00BA')); // (  186) masculine_ordinal
      f.add(new Character('\u00C0')); // (  192) A_grave
      f.add(new Character('\u00C1')); // (  193) A_acute
      f.add(new Character('\u00C2')); // (  194) A_circumflex
      f.add(new Character('\u00C3')); // (  195) A_tilde
      f.add(new Character('\u00C4')); // (  196) A_umlaut
      f.add(new Character('\u00C5')); // (  197) A_ring
      f.add(new Character('\u00C6')); // (  198) AE_ligature
      f.add(new Character('\u00C7')); // (  199) C_cedilla
      f.add(new Character('\u00C8')); // (  200) E_grave
      f.add(new Character('\u00C9')); // (  201) E_acute
      f.add(new Character('\u00CA')); // (  202) E_circumflex
      f.add(new Character('\u00CB')); // (  203) E_umlaut
      f.add(new Character('\u00CC')); // (  204) I_grave
      f.add(new Character('\u00CD')); // (  205) I_acute
      f.add(new Character('\u00CE')); // (  206) I_circumflex
      f.add(new Character('\u00CF')); // (  207) I_umlaut
      f.add(new Character('\u00D0')); // (  208) ETH_Q
      f.add(new Character('\u00D1')); // (  209) N_tilde
      f.add(new Character('\u00D2')); // (  210) O_grave
      f.add(new Character('\u00D3')); // (  211) O_acute
      f.add(new Character('\u00D4')); // (  212) O_circumflex
      f.add(new Character('\u00D5')); // (  213) O_tilde
      f.add(new Character('\u00D6')); // (  214) O_umlaut
      f.add(new Character('\u00D8')); // (  216) O_slash
      f.add(new Character('\u00D9')); // (  217) U_grave
      f.add(new Character('\u00DA')); // (  218) U_acute
      f.add(new Character('\u00DB')); // (  219) U_circumflex
      f.add(new Character('\u00DC')); // (  220) U_umlaut
      f.add(new Character('\u00DD')); // (  221) Y_acute
      f.add(new Character('\u00DE')); // (  222) THORN
      f.add(new Character('\u00DF')); // (  223) sharp_s
      f.add(new Character('\u00E0')); // (  224) a_grave
      f.add(new Character('\u00E1')); // (  225) a_acute
      f.add(new Character('\u00E2')); // (  226) a_circumflex
      f.add(new Character('\u00E3')); // (  227) a_tilde
      f.add(new Character('\u00E4')); // (  228) a_umlaut
      f.add(new Character('\u00E5')); // (  229) a_ring
      f.add(new Character('\u00E6')); // (  230) ae_ligature
      f.add(new Character('\u00E7')); // (  231) c_cedilla
      f.add(new Character('\u00E8')); // (  232) e_grave
      f.add(new Character('\u00E9')); // (  233) e_acute
      f.add(new Character('\u00EA')); // (  234) e_circumflex
      f.add(new Character('\u00EB')); // (  235) e_umlaut
      f.add(new Character('\u00EC')); // (  236) i_grave
      f.add(new Character('\u00ED')); // (  237) i_acute
      f.add(new Character('\u00EE')); // (  238) i_circumflex
      f.add(new Character('\u00EF')); // (  239) i_umlaut
      f.add(new Character('\u00F0')); // (  240) eth
      f.add(new Character('\u00F1')); // (  241) n_tilde
      f.add(new Character('\u00F2')); // (  242) o_grave
      f.add(new Character('\u00F3')); // (  243) o_acute
      f.add(new Character('\u00F4')); // (  244) o_circumflex
      f.add(new Character('\u00F5')); // (  245) o_tilde
      f.add(new Character('\u00F6')); // (  246) o_umlaut
      f.add(new Character('\u00F8')); // (  248) o_slash
      f.add(new Character('\u00F9')); // (  249) u_grave
      f.add(new Character('\u00FA')); // (  250) u_acute
      f.add(new Character('\u00FB')); // (  251) u_circumflex
      f.add(new Character('\u00FC')); // (  252) u_umlaut
      f.add(new Character('\u00FD')); // (  253) y_acute
      f.add(new Character('\u00FE')); // (  254) thorn
      f.add(new Character('\u00FF')); // (  255) y_umlaut
      f.add(new Character('\u0152')); // (  338) OE_ligature
      f.add(new Character('\u0153')); // (  339) oe_ligature
      f.add(new Character('\u0160')); // (  352) S_caron
      f.add(new Character('\u0161')); // (  353) s_caron
      f.add(new Character('\u0178')); // (  376) Y_diaeresis
   }


   /**
    * Initializes ordinary characters for LUWO for the CS language.
    * @param f The HashSet
    */
   protected static void initCSFlags(HashSet<Character> f)
   {
      f.add(new Character('\u05D0')); // ( 1488) X
      f.add(new Character('\u05D1')); // ( 1489) X
      f.add(new Character('\u05D2')); // ( 1490) X
      f.add(new Character('\u05D3')); // ( 1491) X
      f.add(new Character('\u05D4')); // ( 1492) X
      f.add(new Character('\u05D5')); // ( 1493) X
      f.add(new Character('\u05D6')); // ( 1494) X
      f.add(new Character('\u05D7')); // ( 1495) X
      f.add(new Character('\u05D8')); // ( 1496) X
      f.add(new Character('\u05D9')); // ( 1497) X
      f.add(new Character('\u05DA')); // ( 1498) X
      f.add(new Character('\u05DB')); // ( 1499) X
      f.add(new Character('\u05DC')); // ( 1500) X
      f.add(new Character('\u05DD')); // ( 1501) X
      f.add(new Character('\u05DE')); // ( 1502) X
      f.add(new Character('\u05DF')); // ( 1503) X
      f.add(new Character('\u05E0')); // ( 1504) X
      f.add(new Character('\u05E1')); // ( 1505) X
      f.add(new Character('\u05E2')); // ( 1506) X
      f.add(new Character('\u05E3')); // ( 1507) X
      f.add(new Character('\u05E4')); // ( 1508) X
      f.add(new Character('\u05E5')); // ( 1509) X
      f.add(new Character('\u05E6')); // ( 1510) X
      f.add(new Character('\u05E7')); // ( 1511) X
      f.add(new Character('\u05E8')); // ( 1512) X
      f.add(new Character('\u05E9')); // ( 1513) X
      f.add(new Character('\u05EA')); // ( 1514) X
      f.add(new Character('\u05F0')); // ( 1520) X
      f.add(new Character('\u05F1')); // ( 1521) X
      f.add(new Character('\u05F2')); // ( 1522) X
   }

   /**
    * Initializes ordinary characters for LUWO.
    * Has a side-effect of adding the HashSet to ordinaryFlagsSet.
    * @return The HashSet
    */
   protected static HashSet<Character> initOrdinaryFlagsLUWO()
   {
      langsUNO = new ArrayList<String>(); // Part of workaround til we get codeset info
      langsUNO.add("en"); //$NON-NLS-1$
      langsUNO.add("cs"); //$NON-NLS-1$
      langsUNO.add("de"); //$NON-NLS-1$
      langsUNO.add("es"); //$NON-NLS-1$
      langsUNO.add("fr"); //$NON-NLS-1$
      langsUNO.add("hu"); //$NON-NLS-1$
      langsUNO.add("it"); //$NON-NLS-1$
      langsUNO.add("ja"); //$NON-NLS-1$
      langsUNO.add("ko"); //$NON-NLS-1$
      langsUNO.add("pl"); //$NON-NLS-1$
      langsUNO.add("pt"); //$NON-NLS-1$
      langsUNO.add("ru"); //$NON-NLS-1$
      langsUNO.add("zh"); //$NON-NLS-1$
      HashSet<Character> f = new HashSet<Character>(349);
      ordinaryFlagsSet.put(PLATFORM_LUWO_KEY, f);
      // First add characters for all LUWO locales:
      f.add(new Character('\u0023')); // (   35) #
      f.add(new Character('\u0024')); // (   36) $
      f.add(new Character('\u0040')); // (   64) @
      initCommonOrdinaryFlags(f);
      // Then for the locale:
      Locale locale = Locale.getDefault();
      initOrdinaryFlagsLUWO(f, locale);
      return f;
   }

   /** 
    * Formats {0}.{1}.
    * @param schema The schema name, in catalog format.
    * @param name The routine name, in catalog format.
    * @param conInfo The ConnnectionInfo.
    * @return The qualified name in SQL format.
    */
   public static String getQualifiedName(String schema, String name, ConnectionInfo conInfo)
   {
      String sqlName = toSQLFormat(name, conInfo);
      if (schema != null && schema.length() > 0)
      {
         String sqlSchema = toSQLFormat(schema, conInfo);
         return NLS.bind(QUALIFIED_NAME_TEMPLATE, sqlSchema, sqlName);
      }
      else
      {
         return sqlName;
      }
   }

   /** 
    * Formats {0}.{1}.
    * @param schema The schema name, in catalog format.
    * @param name The routine name, in catalog format.
    * @param dbDef Defines the database.
    * @return The qualified name in SQL format.
    */
   public static String getQualifiedName(String schema, String name, DatabaseDefinition dbDef)
   {
      String sqlName = toSQLFormat(name, dbDef);
      if (schema != null && schema.length() > 0)
      {
         String sqlSchema = toSQLFormat(schema, dbDef);
         return NLS.bind(QUALIFIED_NAME_TEMPLATE, sqlSchema, sqlName);
      }
      else
      {
         return sqlName;
      }
   }

   /** 
    * Formats {0}.{1}.{2}....
    * @param names An array of the unqualified names, in catalog format.
    * @param conInfo The ConnnectionInfo.
    * @return The qualified name in SQL format.
    */
   public static String getQualifiedName(String[] names, ConnectionInfo conInfo)
   {
      String sqlName1 = null;
      int count = names.length;
      ArrayList<String> nns = new ArrayList<String>(count);
      int n = 0;
      while (n < count)
      {
         if (names[n] != null && names[n].length() > 0)
            nns.add(names[n]);
         n++;
      }
      count = nns.size();
      if (count > 0)
      {
         sqlName1 = toSQLFormat(nns.get(0), conInfo);
         if (count > 1)
         {
            String sqlName2;
            n = 1;
            while (n < count)
            {
               sqlName2 = toSQLFormat(nns.get(n), conInfo);
               sqlName1 = NLS.bind(QUALIFIED_NAME_TEMPLATE, sqlName1, sqlName2);
               n++;
            }
         }
      }
      return sqlName1;
   }

   /** 
    * Formats {0}.{1}.{2}....
    * @param names An array of the unqualified names, in catalog format.
    * @param dbDef Defines the database.
    * @return The qualified name in SQL format.
    */
   public static String getQualifiedName(String[] names, DatabaseDefinition dbDef)
   {
      String sqlName1 = null;
      int count = names.length;
      ArrayList<String> nns = new ArrayList<String>(count);
      int n = 0;
      while (n < count)
      {
         if (names[n] != null && names[n].length() > 0)
            nns.add(names[n]);
         n++;
      }
      count = nns.size();
      if (count > 0)
      {
         sqlName1 = toSQLFormat(nns.get(0), dbDef);
         if (count > 1)
         {
            String sqlName2;
            n = 1;
            while (n < count)
            {
               sqlName2 = toSQLFormat(nns.get(n), dbDef);
               sqlName1 = NLS.bind(QUALIFIED_NAME_TEMPLATE, sqlName1, sqlName2);
               n++;
            }
         }
      }
      return sqlName1;
   }

}
