/*
 * Copyright (c) 2005. IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 */

package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.editor.contentassist.ISQLDBProposalsService;
import org.eclipse.datatools.sqltools.editor.contentassist.SQLDBProposalsRequest;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.datatools.sqltools.sql.reference.ITable;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.common.util.EList;

/**
 * Provides database identifier proposal services for the editor, to support
 * code completion. These are services to construct and return a list of
 * proposals of database objects (tables, columns) based on a given database
 * connection.
 * 
 * This class implements the <code>ISQLDBProposalsService</code> interface.
 * 
 * @author Hetty Dougherty
 * @author bgp
 */
public class SQLDBProposalsService implements ISQLDBProposalsService {

	private ISQLEditorConnectionInfo fConnInfo;

	private List fDBProposalList;

	private SQLDBProposalsRequest fRequest;

	/**
	 * Constructs an instance of this class with the given
	 * <code>ISQLEditorConnectionInfo</code> object.
	 * 
	 * @param connInfo
	 *            the <code>ISQLEditorConnectionInfo</code> object to use
	 */
	public SQLDBProposalsService(ISQLEditorConnectionInfo connInfo) {
		super();

		fConnInfo = connInfo;
		fDBProposalList = new ArrayList();
		fRequest = null;
	}

	//
	// /**
	// * Returns a value indicating whether tables only, columns only, or both
	// * tables and columns are needed for the proposal list by examining the
	// * given list of tokens indicating the start of the expression for which
	// * DB proposals are wanted. The returned value contains the constants
	// * PROPOSAL_TYPE_TABLES and PROPOSAL_TYPE_COLUMNS either alone or "ORed"
	// * together, or PROPOSAL_TYPE_INVALID.
	// *
	// * @param tokenList list of tokens indicating the start of the expression
	// for
	// * which the user wants DB proposals
	// * @return
	// */
	// protected int determineProposalType( SQLDBProposalsRequest request ) {
	// int proposalType = request.getScope();
	// //try to determine a scope
	// if (proposalType == SQLParserConstants.SCOPE_DEFAULT || proposalType ==
	// SQLParserConstants.SCOPE_COLUMNS)
	// {
	// if (request.get) {
	// if (this.fConnInfo.getDefaultSchemaName() == null) {
	// proposalType = SQLParserConstants.SCOPE_TABLES;
	// } else {
	// proposalType = SQLParserConstants.SCOPE_TABLES |
	// SQLParserConstants.SCOPE_COLUMNS;
	// }
	// } else if (tokenList.size() == 2) {
	// proposalType = SQLParserConstants.SCOPE_COLUMNS;
	// }
	// }else if ( proposalType == SQLParserConstants.SCOPE_TRIGGERS)
	// {
	// if (tokenList.size() == 1) {
	// if (this.fConnInfo.getDefaultSchemaName() == null) {
	// proposalType = SQLParserConstants.SCOPE_TABLES;
	// } else {
	// proposalType = SQLParserConstants.SCOPE_TABLES |
	// SQLParserConstants.SCOPE_TRIGGERS;
	// }
	// }
	// }
	//        
	// return proposalType;
	// }

	/**
	 * Gets the <code>ISQLEditorConnectionInfo</code> used to provide content
	 * assist. Implements <code>ISQLDBProposalsService</code> interface.
	 * 
	 * @return the current <code>ISQLEditorConnectionInfo</code> object
	 * @see ISQLDBProposalsService#getConnectionInfo()
	 */
	public ISQLEditorConnectionInfo getConnectionInfo() {
		return fConnInfo;
	}

	/**
	 * Gets the list of <code>SQLDBProposal</code> objects for the content
	 * assist proposals. Implements <code>ISQLDBProposalsService</code>
	 * interface.
	 * 
	 * @return the list of proposals
	 * @see ISQLDBProposalsService#getDBProposals()
	 */
	public List getDBProposals() {
		return fDBProposalList;
	}

	/**
	 * Gets a <code>Schema</code> object with the given name from the given
	 * the database.
	 * 
	 * @param database
	 *            the database to search for the named schema
	 * @param schemaName
	 *            the name of the schema to find
	 * @return the <code>Schema</code> object with the given name
	 */
	protected Schema getSchema(Database database, String catalogName, String schemaName) {
		EList schemaList = ModelUtil.getSchemas(database, catalogName);
		Schema schema = null;
		for (int i = 0; i < schemaList.size(); i++) {
			Schema thisSchema = (Schema) schemaList.get(i);
			if (thisSchema.getName().equalsIgnoreCase(schemaName)) {
				schema = thisSchema;
				break;
			}
		}
		return schema;
	}

	/**
	 * Gets a <code>Schema</code> object with the given name from the given
	 * the database.
	 * 
	 * @param database
	 *            the database to search for the named schema
	 * @param schemaName
	 *            the name of the schema to find
	 * @return the <code>Schema</code> object with the given name
	 */
	protected void loadSchemas(Database database, String catalogName, boolean clear) {
		if (clear) {
			fDBProposalList.clear();
		}
		EList schemaList = ModelUtil.getSchemas(database,catalogName);

		for (int i = 0; i < schemaList.size(); i++) {
			Schema thisSchema = (Schema) schemaList.get(i);
			fDBProposalList.add(new SQLDBProposal(thisSchema));
		}
	}

    /**
     * Gets a <code>Schema</code> object with the given name from the given
     * the database.
     * 
     * @param database
     *            the database to search for the named schema
     * @param schemaName
     *            the name of the schema to find
     * @return the <code>Schema</code> object with the given name
     */
    protected void loadCatalogs(Database database, boolean clear) {
        if (clear) {
            fDBProposalList.clear();
        }
        EList catalogsList = database.getCatalogs();

        for (int i = 0; i < catalogsList.size(); i++) {
            Catalog thisCatalog = (Catalog) catalogsList.get(i);
            fDBProposalList.add(new SQLDBProposal(thisCatalog));
        }
    }
    
	/**
	 * Gets the content assist request for database meta objects.
	 * 
	 * @return the request
	 */
	public SQLDBProposalsRequest getSQLDBProposalRequest() {
		return fRequest;
	}

	/**
	 * Creates and stores a list of <code>SQLDBProposal</code> objects for
	 * each column in the given table. Retrieve the list using getDBProposals().
	 * The current list of proposals, if any, is cleared.
	 * 
	 * @param table
	 *            the <code>Table</code> object for which columns are needed
	 */
	protected void loadColumns(Table table) {
		loadColumns(table, true, table.getName());
	}

	/**
	 * Creates and stores a list of <code>SQLDBProposal</code> objects for
	 * each column in the given table. Retrieve the list using getDBProposals().
	 * 
	 * @param table
	 *            the <code>Table</code> object for which columns are needed
	 * @param clear
	 *            when true, clear the existing list of proposals before loading
	 */
	protected void loadColumns(Table table, boolean clear, String alias) {
		if (table != null) {
			if (clear) {
				fDBProposalList.clear();
			}

			EList columns = table.getColumns();
			for (int j = 0; j < columns.size(); j++) {
				Column column = (Column) columns.get(j);
				fDBProposalList.add(new SQLDBProposal(column, alias));
			}
		}
	}

	/**
	 * Creates and stores a list of <code>SQLDBProposal</code> objects for the
	 * available schemas, tables, and columns of the currently connected
	 * database. Retrieve the list using getDBProposals(). The current list of
	 * proposals, if any, is cleared.
	 * 
	 * @param tokenList
	 *            list of strings indicating the start of the expression for
	 *            which the user wants DB proposals
	 */
	protected void loadDBProposals(SQLDBProposalsRequest request) {
		fDBProposalList.clear();
		Database database = this.fConnInfo.getDatabase();
		String fImpliedSchemaName = this.fConnInfo.getDefaultSchemaName();
		if (database != null) {
			int proposalsType = request.getScope();

            if ((proposalsType & SQLParserConstants.SCOPE_CATALOGS) == SQLParserConstants.SCOPE_CATALOGS) {
                loadCatalogs(database, false);
                return;
            }
			if ((proposalsType & SQLParserConstants.SCOPE_SCHEMAS) == SQLParserConstants.SCOPE_SCHEMAS) {
				loadSchemas(database, request.getDatabase(), false);
				return;
			}
			if ((proposalsType & SQLParserConstants.SCOPE_EVENTS) == SQLParserConstants.SCOPE_EVENTS) {
				loadEvents(database, false);
				return;
			}
            Schema schema = getSchema(database, fConnInfo.getDatabaseName(), request.getSchema());
			if (request.getDatabase() != null)
            {
                schema = getSchema(database, request.getDatabase(), request.getSchema());
            }
            
			if ((proposalsType & SQLParserConstants.SCOPE_TABLES) == SQLParserConstants.SCOPE_TABLES) {
				loadTables(schema, false);
				createAliasTableProposals(request, false);
			}
			if ((proposalsType & SQLParserConstants.SCOPE_FUNCTIONS) == SQLParserConstants.SCOPE_FUNCTIONS) {
				loadFunctions(schema, false);
			}
			if ((proposalsType & SQLParserConstants.SCOPE_STORED_PROCEDURES) == SQLParserConstants.SCOPE_STORED_PROCEDURES) {
				loadProcedures(schema, false);
			}
			if ((proposalsType & SQLParserConstants.SCOPE_TRIGGERS) == SQLParserConstants.SCOPE_TRIGGERS) {
				loadTriggers(schema, false);
			}
            if ((proposalsType & SQLParserConstants.SCOPE_VIEWS) == SQLParserConstants.SCOPE_VIEWS) {
                loadViews(schema, false);
            }
            if ((proposalsType & SQLParserConstants.SCOPE_INDEXES) == SQLParserConstants.SCOPE_INDEXES) {
                loadIndexes(schema, false);
            }
            if ((proposalsType & SQLParserConstants.SCOPE_SEGMENT) == SQLParserConstants.SCOPE_SEGMENT) {
                loadSegments(schema, false);
            }
            
			if ((proposalsType & SQLParserConstants.SCOPE_COLUMNS) == SQLParserConstants.SCOPE_COLUMNS 
                 || (proposalsType & SQLParserConstants.SCOPE_WITHOUT_TABLE) == SQLParserConstants.SCOPE_WITHOUT_TABLE) {
				String realTableName = request.getRealTable();
				// get table prefix first, then get context tables

				if (realTableName != null && realTableName.trim().length() > 0) {
					Table table = null;
                    if (schema == null)
                    {
                        return;
                    }
					EList tables = schema.getTables();
					for (int i = 0; i < tables.size(); i++) {
						table = (Table) tables.get(i);
						if (table.getName().equals(realTableName)) {
							loadColumns(table, false, request.getTable());
							break;
						}
					}
				} else {
					List ctxTables = request.getContextTables();
					if (!ctxTables.isEmpty()) {
						for (Iterator iter = ctxTables.iterator(); iter
								.hasNext();) {
							org.eclipse.datatools.sqltools.sql.reference.ITable ref = (org.eclipse.datatools.sqltools.sql.reference.ITable) iter
									.next();
							Schema ctxSchema = schema;
							if (ref.getOwner() != null)
                            {
                                ctxSchema = getSchema(database, fConnInfo.getDatabaseName(), ref.getOwner());
                                if (ctxSchema == null)
                                {
                                    continue;
                                }

                                EList tables = ctxSchema.getTables();
                                for (int i = 0; i < tables.size(); i++)
                                {
                                    Table table = (Table) tables.get(i);
                                    if (table.getName().equals(ref.getName()))
                                    {
                                        loadColumns(table, false, ref.getAliasName());
                                        break;
                                    }
                                }
                            }
                            else
                            {
                                DatabaseIdentifier databaseIdentifier = new DatabaseIdentifier(fConnInfo
                                        .getConnectionProfileName(), fConnInfo.getDatabaseName());
                                // find table with default schema in current catalog
                                Table table = ModelUtil.findTableObject(databaseIdentifier,
                                        fConnInfo.getDatabaseName(), ctxSchema.getName(), ref.getName(), false, true,
                                        false);
                                if (table == null)
                                {
                                    // without table owner, find table in all schemas
                                    table = ModelUtil.findTableInAllSchemas(databaseIdentifier, fConnInfo
                                            .getDatabaseName(), ref.getName(), true, false);
                                }
                                if (table != null)
                                {
                                    loadColumns(table, false, ref.getAliasName());
                                }
                            }

						}

					}
				}
			}
		}
	}

	/**
	 * Creates and stores a list of <code>SQLDBProposal</code> objects for
	 * each table associated with the given schema. Retrieve the list using
	 * getDBProposals(). The current list of proposals, if any, is cleared.
	 * 
	 * @param schema
	 *            the <code>Schema</code> object for which tables are needed
	 */
	protected void loadTables(Schema schema) {
		loadTables(schema, true);
	}

	/**
	 * Creates and stores a list of <code>SQLDBProposal</code> objects for
	 * each table associated with the given schema. Retrieve the list using
	 * getDBProposals().
	 * 
	 * @param schema
	 *            the <code>Schema</code> object for which tables are needed
	 * @param clear
	 *            when true, clear the existing list of proposals before loading
	 */
	protected void loadTables(Schema schema, boolean clear) {
		if (schema != null) {
			if (clear) {
				fDBProposalList.clear();
			}
			EList tables = schema.getTables();

			for (int i = 0; i < tables.size(); i++) {
				Table table = (Table) tables.get(i);
				fDBProposalList.add(new SQLDBProposal(table));
			}
		}
	}

	/**
	 * Creates and stores a list of <code>SQLDBProposal</code> objects for
	 * each alias table associated with the current statement. Retrieve the list using
	 * getDBProposals().
	 * 
	 * @param clear
	 *            when true, clear the existing list of proposals before loading
	 */
	protected void createAliasTableProposals(SQLDBProposalsRequest request, boolean clear) {
			if (clear) {
				fDBProposalList.clear();
			}
			List tables = request.getContextTables();
			
			for (int i = 0; i < tables.size(); i++) {
				ITable table = (ITable) tables.get(i);
				fDBProposalList.add(new SQLDBProposal(table.getAliasName()));
			}
	}
	
	/**
	 * Creates and stores a list of <code>SQLDBProposal</code> objects for
	 * each stored procedure associated with the given schema. Retrieve the list
	 * using getDBProposals().
	 * 
	 * @param schema
	 *            the <code>Schema</code> object for which stored procedures
	 *            are needed
	 * @param clear
	 *            when true, clear the existing list of proposals before loading
	 */
	protected void loadProcedures(Schema schema, boolean clear) {
		if (schema != null) {
			if (clear) {
				fDBProposalList.clear();
			}
			EList procedures = schema.getProcedures();

			for (int i = 0; i < procedures.size(); i++) {
				Procedure proc = (Procedure) procedures.get(i);
				fDBProposalList.add(new SQLDBProposal(proc));
			}
		}
	}

	/**
	 * Creates and stores a list of <code>SQLDBProposal</code> objects for
	 * each user defined functions associated with the given schema. Retrieve
	 * the list using getDBProposals().
	 * 
	 * @param schema
	 *            the <code>Schema</code> object for which user defined
	 *            functions are needed
	 * @param clear
	 *            when true, clear the existing list of proposals before loading
	 */
	protected void loadFunctions(Schema schema, boolean clear) {
		if (schema != null) {
			if (clear) {
				fDBProposalList.clear();
			}
			EList funcs = schema.getUDFs();

			for (int i = 0; i < funcs.size(); i++) {
				Function func = (Function) funcs.get(i);
				fDBProposalList.add(new SQLDBProposal(func));
			}
		}
	}

	/**
	 * Creates and stores a list of <code>SQLDBProposal</code> objects for
	 * each triggers associated with the given schema. Retrieve the list using
	 * getDBProposals().
	 * 
	 * @param schema
	 *            the <code>Schema</code> object for which triggers are needed
	 * @param clear
	 *            when true, clear the existing list of proposals before loading
	 */
	protected void loadTriggers(Schema schema, boolean clear) {
		if (schema != null) {
			if (clear) {
				fDBProposalList.clear();
			}
			EList triggers = schema.getTriggers();

			for (int i = 0; i < triggers.size(); i++) {
                Trigger trigger = (Trigger) triggers.get(i);
				fDBProposalList.add(new SQLDBProposal(trigger));
			}
		}
	}

	/**
	 * Creates and stores a list of <code>SQLDBProposal</code> objects for
	 * each events associated with the given schema. Retrieve the list using
	 * getDBProposals().
	 * 
	 * @param database
	 *            the <code>Schema</code> object for which events are needed
	 * @param clear
	 *            when true, clear the existing list of proposals before loading
	 */
	protected void loadEvents(Database database, boolean clear) {
		if (database != null) {
			if (clear) {
				fDBProposalList.clear();
			}
			EList events = database.getEvents();

			for (int i = 0; i < events.size(); i++) {
				Event event = (Event) events.get(i);
				fDBProposalList.add(new SQLDBProposal(event));
			}
		}
	}

	/**
	 * Populates the list database of database object proposals (schemas,
	 * tables, columns) using given list of tokens (DB identifiers) indicating
	 * the start of the expression for which the user wants DB proposals. For
	 * example, if the user provides the list (MYSCHEMA, TABLE1), the list of
	 * proposals will be the columns of table MYSCHEMA.TABLE1. Retrieve the list
	 * using getDBProposals(). Implements <code>ISQLDBProposalsService</code>
	 * interface.
	 * 
	 * @param tokenList
	 *            list of strings indicating the start of the expression for
	 *            which the user wants DB proposals
	 * @return true if database objects were loaded, for example as a result of
	 *         reestablishing a connection
	 * @see ISQLDBProposalsService#populate()
	 */
	public boolean populate(final SQLDBProposalsRequest request) {
		boolean loaded = false;

		if (request != null) {
			/* Connect or reconnect to DB Server if necessary. */
			ISQLEditorConnectionInfo connInfo = getConnectionInfo();
			boolean connected = false;
			if (connInfo != null && connInfo.getConnectionProfile() != null) {
				connected = connInfo.getConnectionProfile().isConnected();
			}
			if (connected == true) {
				loaded = true;

				/* Get the proposals from the connected database. */
                loadDBProposals(request);
            }
        }

		return loaded;
	}

	/**
     * Creates and stores a list of <code>SQLDBProposal</code> objects for
     * each views associated with the given schema. Retrieve the list using
     * getDBProposals().
     * 
     * @param schema
     *            the <code>Schema</code> object for which views are needed
     * @param clear
     *            when true, clear the existing list of proposals before loading
     */
    protected void loadViews(Schema schema, boolean clear) {
        if (schema != null) {
            if (clear) {
                fDBProposalList.clear();
            }
            EList tables = schema.getTables();

            for (int i = 0; i < tables.size(); i++) {
                Table table = (Table) tables.get(i);
                if (table instanceof ViewTable) {
                    fDBProposalList.add(new SQLDBProposal(table));
                }
            }
        }
    }
    
    /**
     * Creates and stores a list of <code>SQLDBProposal</code> objects for
     * each index associated with the given schema. Retrieve the list using
     * getDBProposals().
     * 
     * @param schema
     *            the <code>Schema</code> object for which indexes are needed
     * @param clear
     *            when true, clear the existing list of proposals before loading
     */
    protected void loadIndexes(Schema schema, boolean clear) {
        if (schema != null) {
            if (clear) {
                fDBProposalList.clear();
            }
            EList indexes = schema.getIndices();

            for (int i = 0; i < indexes.size(); i++) {
                Index index = (Index) indexes.get(i);
                fDBProposalList.add(new SQLDBProposal(index));
            }
        }
    }
    
    /**
     * Creates and stores a list of <code>SQLDBProposal</code> objects for
     * each segment associated with the given schema. Retrieve the list using
     * getDBProposals().
     * 
     * @param schema
     *            the <code>Schema</code> object for which segments are needed
     * @param clear
     *            when true, clear the existing list of proposals before loading
     */
    protected void loadSegments(Schema schema, boolean clear) {
        // It will be implemented in derived classes.
    }
    
	public ISQLEditorConnectionInfo getSQLEditorConnectionInfo() {
		return fConnInfo;
	}

    public void setSQLEditorConnectionInfo(ISQLEditorConnectionInfo connectionInfo) {
        fConnInfo = connectionInfo;
    }
}