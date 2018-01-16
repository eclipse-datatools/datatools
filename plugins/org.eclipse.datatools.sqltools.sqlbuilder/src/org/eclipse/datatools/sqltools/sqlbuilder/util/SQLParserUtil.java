package org.eclipse.datatools.sqltools.sqlbuilder.util;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.parsers.sql.postparse.PostParseProcessorConfiguration;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParseResult;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManagerProvider;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLDialectInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;

/**
 * This is a utility class for the SQLBuilderEditorInput class. It allows you
 * to parse a SQL statement created with one connection profile and give you
 * back the parse tree for the statement connected to a different  connection profile.
 * 
 * It parses the statement according to the specified dialect but uses the post-parse-processing
 * which hooks the SQM to the database tables for the current connectionInfo.
 * 
 * @author jeremyl
 *
 */
public class SQLParserUtil {

	/**
	 * This method parses the editorinput's SQL statement according
	 * to the dialect specified in the editorinput's SQLDialectInfo, but
	 * using the PostParseProcessorConfiguration associated with the domainModel
	 * passed.
	 * 
	 * Note that the parsing source code is based on SQLDomainModel.parse(String). 
	 * 
	 * @param editorInput
	 * @return
	 */
	public static QueryStatement parseForDifferentDialect(
			SQLBuilderEditorInput editorInput, SQLDomainModel domainModel) {
		QueryStatement sqlStatement = null;
		String strSQL = editorInput.getSQL();
        strSQL = strSQL.trim();
        
        try {
            sqlStatement = SQLParserUtil.parse(strSQL, editorInput, domainModel);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
		
		
		return sqlStatement;
	}

	
    /**
     * Parses string SQL statement passed and returns the QueryStatement object.
     * If parse fails it returns null.
     * @param sqlStr the String sql statement
     * @return the generated Query object
     * @throws SQLParserException if the parse was not successful
     * @throws SQLParserInternalException
     */
	protected static QueryStatement parse(String sqlStr, SQLBuilderEditorInput editorInput, SQLDomainModel domainModel) throws SQLParserException, SQLParserInternalException {
        if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(new Object[] { sqlStr });
        }

        QueryStatement parsedStatement = null;
        SQLQueryParserManager parserManager = SQLParserUtil.getParserManager(editorInput, domainModel);
        if (parserManager != null) {
            SQLQueryParseResult parseResult = parserManager.parseQuery(sqlStr);
            if (parseResult != null) {
                parsedStatement = parseResult.getQueryStatement();
            }
        }

        return (QueryStatement) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(parsedStatement);
    }

    /**
     * Gets an instance of the parser manager to use with this statement.
     * @return the parser manager object
     */
    protected static SQLQueryParserManager getParserManager(SQLBuilderEditorInput editorInput, SQLDomainModel domainModel) {
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(null);

		ISQLDialectInfo dialectInfo = editorInput.getSQLStatementInfo().getSQLDialectInfo();
        SQLQueryParserManager parserManager = null;
        String dbProduct = dialectInfo.getProduct();
        String dbVersion = dialectInfo.getVersion();
        
        // Create an instance of the ParserManager
        parserManager = SQLQueryParserManagerProvider.getInstance().getParserManager(dbProduct, dbVersion);
        
        // Use SQLQuerySourceFormat for the editorInput's dialect
        SQLQuerySourceFormat srcFormat = SQLParserUtil.getSqlSourceFormat(editorInput.getSQLStatementInfo().getSQLDialectInfo());
        parserManager.setSourceFormat(srcFormat);

        // THIS IS THE IMPORTANT PART - USE PostParseProcessorConfiguration
        // FOR THE EDITORINPUT'S OWN CONNECTIONINFO, NOT FOR THE DIFFERENT DIALECT
        PostParseProcessorConfiguration p3config = getPostParseProcessorConfiguration(domainModel);
        parserManager.configPostParseProcessors(p3config);

        return (SQLQueryParserManager) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(parserManager);
    }

    /**
     * Gets the current SQLQuerySourceFormat object.  The SQLQuerySourceFormat object contains
     * parameters that indicate how the SQL for the query model should be generated and parsed.  
     * @return the SQLQuerySourceFormat object 
     */
    protected static SQLQuerySourceFormat getSqlSourceFormat(ISQLDialectInfo sqlDialectInfo) {
    	SQLQuerySourceFormat sqlSourceFormat = SQLQuerySourceFormat.copyDefaultFormat();

        // Configure the source format object with the omit schema option, with
    	// the schemaName to be omitted taken from the dialectInfo
    	String omitSchema = sqlDialectInfo.getOmitSchema();
        sqlSourceFormat.setOmitSchema(omitSchema);
        
        // Configure the source format object with the IdentifierQuoteString option
        DatabaseDefinition dbDef = SQLParserUtil.getDatabaseDefinition(sqlDialectInfo);
        if (dbDef != null && dbDef.getIdentifierQuoteString() != null && dbDef.getIdentifierQuoteString().length() == 1){
        	sqlSourceFormat.setDelimitedIdentifierQuote(dbDef.getIdentifierQuoteString().charAt(0));
        }
        return (SQLQuerySourceFormat) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(sqlSourceFormat);
    }


	private static DatabaseDefinition getDatabaseDefinition(
			ISQLDialectInfo dialectInfo) {
    	DatabaseDefinition dbDef = 
    		 RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(dialectInfo.getProduct(), dialectInfo.getVersion());
		return dbDef;
	}

    /**
     * Gets the configuration object to configure list of PostParseProcessor
     * objects for the parser. PostParseProcessor objects are used by the parser
     * after a query model is generated to by the parser to modify or verify the
     * generated model before returning it to the caller of the parser. By
     * default, the list contains a table reference resolver and a data type
     * resolver.
     * 
     * @return the configuration object for PostParseProcessor objects
     */
    protected static PostParseProcessorConfiguration getPostParseProcessorConfiguration(SQLDomainModel domainModel) {
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(null);

        Database db = domainModel.getDatabase();
        String currentSchemaName = domainModel.getCurrentSchema();
        PostParseProcessorConfiguration p3config = new PostParseProcessorConfiguration(db, currentSchemaName);

        return (PostParseProcessorConfiguration) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(p3config);
    }


	public static String generateSQL(QueryStatement sqlStatement,
			SQLBuilderEditorInput editorInput) {
		return sqlStatement.getSQL();
	}

}
