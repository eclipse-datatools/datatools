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
package org.eclipse.datatools.sqltools.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.DBHelper;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.IDatabaseSetting;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.IDatabaseSetting.NotSupportedSettingException;
import org.eclipse.datatools.sqltools.sql.identifier.IIdentifierValidator;
import org.eclipse.datatools.sqltools.sql.identifier.ValidatorMessage;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.datatools.sqltools.sql.util.SQLUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SQLDevToolsUtil {

    private static Pattern ID_PATTERN = Pattern.compile("((\\Q[\\E([^\"]|(\"\"))+\\Q]\\E|[^\\s\"\\Q.\\E]+|\"([^\"]|(\"\"))+\")\\Q.\\E?)");
    private static Pattern STRING_PATTERN = Pattern.compile("(([^\\s']+)|('([^']|(''))+'))");

	/**
	 * Converts the <code>Routine</code> or <code>Trigger</code> object into
	 * <code>ProcIdentifier</code>.
	 * 
	 * @param routine
	 *            the routine object could be a stored procedure, function,
	 *            event, or trigger
	 * @return
	 */
	public static ProcIdentifier getProcIdentifier( SQLObject routine) {
		EObject db = ContainmentServiceImpl.INSTANCE.getRootElement(routine);
		if (db instanceof Database) {
            String dbName = ModelUtil.getDatabaseName(routine);
		    return getProcIdentifier(getDatabaseIdentifier((Database)db, dbName), routine);
		}
        return null;
	}
	
	/**
	 * Converts the <code>Routine</code> or <code>Trigger</code> object into
	 * <code>ProcIdentifier</code>.
	 * 
	 * @param databaseIdentifier
	 *            database identifier used to create the
	 *            <code>ProcIdentifier</code>
	 * @param routine
	 *            the routine object could be a stored procedure, function,
	 *            event, or trigger
	 * @return
	 */
	public static ProcIdentifier getProcIdentifier(
			DatabaseIdentifier databaseIdentifier, SQLObject routine) {

		SQLDevToolsConfiguration config = SQLToolsFacade
				.getConfigurationByProfileName(databaseIdentifier
						.getProfileName());
		DBHelper h = null;
		if (config != null) {
			h = config.getDBHelper();
		} else {
			h = new DBHelper();
		}

		ProcIdentifier proc = null;
		if (routine instanceof Routine) {
			Schema schema = ((Routine) routine).getSchema();
			/*
			 * This method gets ProcIdentifiers that support overloaded routines
			 * whose specificName distinguishes routines with the same name.
			 * See BZ 171718.
			 */
			if (routine instanceof Procedure) {
				proc = h.getProcIdentifier(databaseIdentifier, routine
						.getName(), ((Procedure) routine).getSpecificName(),
						ProcIdentifier.TYPE_SP, null, schema.getName());
			} else if (routine instanceof Function) {
				proc = h.getProcIdentifier(databaseIdentifier, routine
						.getName(), ((Function) routine).getSpecificName(),
						ProcIdentifier.TYPE_UDF, null, schema.getName());
			}
		} else if (routine instanceof Trigger) {
			Schema schema = ((Trigger) routine).getSchema();
			Table table = ((Trigger) routine).getSubjectTable();
			proc = h.getProcIdentifier(databaseIdentifier, routine.getName(),
					ProcIdentifier.TYPE_TRIGGER, table.getName(), schema
							.getName(), table.getSchema().getName());
		} else if (routine instanceof Event) {
			String creator = "";
			EStructuralFeature creatorFeature = routine.eClass()
			.getEStructuralFeature("eventCreator");
			if (creatorFeature != null)
			{
				Object obj = routine.eGet(creatorFeature);
                if (obj instanceof Schema)
                {
                    creator = ((Schema)obj).getName();
                }
			}
			
			proc = h.getProcIdentifier(databaseIdentifier, routine.getName(),
					ProcIdentifier.TYPE_EVENT, null, creator);
		} else {
			EStructuralFeature schemaFeature = routine.eClass().getEStructuralFeature("schema");
			EStructuralFeature eventIdFeature = routine.eClass().getEStructuralFeature("eventId");
			if (schemaFeature != null && eventIdFeature != null)
			{
				proc = h.getProcIdentifier(databaseIdentifier, routine.getName(),
						ProcIdentifier.TYPE_EVENT, null, ((Schema)routine.eGet(schemaFeature)).getName());
				//TODO deprecated for backward compatibility 
			}

		}

		return proc;
	}

	/**
	 * Returns the type of the procedural object.
	 * 
	 * @see ProcIdentifier
	 * @param routine
	 * @return
	 */
	public static int getProcType(SQLObject routine) {
		if (routine instanceof Trigger) {
			return ProcIdentifier.TYPE_TRIGGER;
		} else if (routine instanceof Procedure) {
			return ProcIdentifier.TYPE_SP;
		} else if (routine instanceof Function) {
			return ProcIdentifier.TYPE_UDF;
		}
		// TODO add Event support
		 else if (routine instanceof Event || isEventType(routine) != null)
		 {
			 return ProcIdentifier.TYPE_EVENT;
		 }
		return ProcIdentifier.TYPE_SQL;
	}

	public static EStructuralFeature isEventType(SQLObject routine) {
		return routine.eClass().getEStructuralFeature("eventId");
	}

    /**
     * Returns DatabaseIdentifier for databases that doesn't support catalog
     * @deprecated use #getDatabaseIdentifier(database, String) instead
     * @param database
     * @return
     */
	public static DatabaseIdentifier getDatabaseIdentifier(Database database) {
		ConnectionInfo connInfo = DatabaseConnectionRegistry.getInstance()
				.getConnectionForDatabase(database);
		if (connInfo instanceof ConnectionInfoImpl) {
			IConnectionProfile profile = ((ConnectionInfoImpl) connInfo)
					.getConnectionProfile();
			return new DatabaseIdentifier(profile.getName(), database.getName());
		}

		return null;
	}

    /**
     * Returns DatabaseIdentifier for the database
     * <p>
     * Caution: the database object must be the one created by DSE for this method to find the connection info
     * </p>
     * @param database 
     * @param catalogName if null, database.getName() will be used instead
     * @return
     */
	public static DatabaseIdentifier getDatabaseIdentifier(Database database, String catalogName) {
	    ConnectionInfo connInfo = DatabaseConnectionRegistry.getInstance()
	    .getConnectionForDatabase(database);
	    if (connInfo instanceof ConnectionInfoImpl) {
            String dbName = catalogName;
            if (dbName == null)
            {
                dbName = database.getName();
            }
	        IConnectionProfile profile = ((ConnectionInfoImpl) connInfo)
	        .getConnectionProfile();
	        return new DatabaseIdentifier(profile.getName(), dbName);
	    }
	    
	    return null;
	}
	
	/**
	 * Returns DatabaseIdentifier for the 
	 * @param database
	 * @param catalogName if null, database.getName() will be used instead
	 * @return
	 */
	public static DatabaseIdentifier getDatabaseIdentifier(SQLObject obj) {
        Object db = ContainmentServiceImpl.INSTANCE.getRootElement(obj);
        if (db instanceof Database)
        {
            String dbName = ModelUtil.getDatabaseName(obj);
            return getDatabaseIdentifier((Database)db, dbName);
        }
        return null;
	}
	
	
    /**
     * Returns the quoted identifier setting for the database
     * @param dbid the database identifier
     */
    public static boolean getQuotedIdentifier(DatabaseIdentifier dbid)
    {
        return isQuotedIdentifierOn(dbid);
    }
    
    /**
     * Get Quoted identifier if ON return true else return false
     * 
     * @param databaseIdentifier Database Identifier 
     * @return Quoted identifier if ON return true else return false
     */
    public static boolean isQuotedIdentifierOn(DatabaseIdentifier databaseIdentifier)
    {
        if (databaseIdentifier == null)
        {
            return false;
        }
        
        boolean quotedIdentifier = false;
        Object quotedIdentifierCfg = null;
        try
        {
        	SQLDevToolsConfiguration conf = SQLToolsFacade.getConfiguration(null, databaseIdentifier); 
            if (conf == null || conf.getDatabaseSetting(databaseIdentifier) == null)
            {
                return false;
            }
            quotedIdentifierCfg = conf.getDatabaseSetting(databaseIdentifier)
                .getConnectionConfigProperty(IDatabaseSetting.C_QUOTED_IDENTIFIER);
        }
        catch (NotSupportedSettingException e)
        {
        }
        if (quotedIdentifierCfg instanceof Boolean)
        {
            quotedIdentifier = ((Boolean) quotedIdentifierCfg).booleanValue();
        }
        return quotedIdentifier;
    }

    /**
     * Quotes the string when it contains space or single quote and is not properly quoted.
     * @param objstr
     * @return
     */
    public static String quoteStringWhenNecessary(String objstr)
    {
        String r = null;
        Matcher m = STRING_PATTERN.matcher(objstr);
        while (m.find())
        {
            r = m.group();
            break;
        }
        if (r != null && r.equals(objstr))
        {
            return r;
        }
        return SQLUtil.quote(objstr, "'");

    }

    /**
     * This is a convenience method of
     * <code>quoteWhenNecessary(String content, DatabaseIdentifier _databaseIdentifier, String
     * quote)</code>
     * 
     * @param content original identifier
     * @return
     */
    public static String quoteWhenNecessary(String content, DatabaseIdentifier databaseIdentifier)
    {
        return quoteWhenNecessary(content, databaseIdentifier, "\"");
    }

    /**
     * Checks the validity and quoted identifier setting and surrounds content with proper quotation mark if necessary.
     * 
     * @param id Identifier
     * @param databaseIdentifier database Identfier
     * @param quote quotes, maybe single quote or double quote
     * @param IdentifierType identfierType,see IIdentfierValidator API
     * @return quoted String
     */
    public static String quoteWhenNecessary(String id, DatabaseIdentifier databaseIdentifier, String quote, int identiferType)
    {
        if (id == null || id.equals("") || databaseIdentifier == null)
        {
            return id;
        }
        SQLDevToolsConfiguration conf = SQLToolsFacade.getConfigurationByProfileName(databaseIdentifier.getProfileName());
        IIdentifierValidator validator = conf.getSQLService().getIdentifierValidator();
        if (validator != null)
        {
            ValidatorMessage msg = validator.isValid(id, identiferType, databaseIdentifier);
            if (msg == null || !ValidatorMessage.hasError(msg, ValidatorMessage.ERROR))
            {
                return id;
            }
        }

        boolean quoted_id = false;
        //initiate connection with quoted_identifier option
        IDatabaseSetting dbSetting = conf.getDatabaseSetting(
            databaseIdentifier);
        if (dbSetting != null)
        {
            try
            {
                quoted_id = ((Boolean) dbSetting
                    .getConnectionConfigProperty(IDatabaseSetting.C_QUOTED_IDENTIFIER)).booleanValue();
            }
            catch (Exception e)
            {
                // can't get setting, assume it's false
            }
        }

        if (quoted_id)
        {
            return SQLUtil.quote(id, quote);
        }
        return id;

    }

    /**
     * Checks the validity and quoted identifier setting and surrounds content with proper quotation mark if necessary.
     * 
     * @param id original identifier
     * @return
     */
    public static String quoteWhenNecessary(String id, DatabaseIdentifier databaseIdentifier, String quote)
    {
        return quoteWhenNecessary(id, databaseIdentifier, quote,IIdentifierValidator.IDENTIFIER_TYPE_UNKNOW);
    }

    /**
     * Checks the validity of the unquoted identifier and unquotes content if necessary. 
     * 
     * @param id original identifier
     * @return
     */
    public static String unquoteWhenNecessary(String id, DatabaseIdentifier databaseIdentifier)
    {
        if (id == null || id.equals("") || databaseIdentifier == null)
        {
            return id;
        }
        String newId = SQLUtil.unquote(id);
        SQLDevToolsConfiguration conf = SQLToolsFacade.getConfigurationByProfileName(databaseIdentifier.getProfileName());
        IIdentifierValidator validator = conf.getSQLService().getIdentifierValidator();
        if (validator != null)
        {
            ValidatorMessage msg = validator.isValid(newId, IIdentifierValidator.IDENTIFIER_TYPE_UNKNOW, databaseIdentifier);
            if (msg == null || !ValidatorMessage.hasError(msg, ValidatorMessage.ERROR))
            {
                return newId;
            }
        }
        return id;

    }


}
