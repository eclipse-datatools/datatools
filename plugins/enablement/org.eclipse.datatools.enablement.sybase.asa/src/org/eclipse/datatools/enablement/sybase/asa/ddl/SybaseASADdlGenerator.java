package org.eclipse.datatools.enablement.sybase.asa.ddl;


import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDatabaseObjecType;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlGenerationOptions;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

public class SybaseASADdlGenerator extends SybaseDdlGenerator implements DDLGenerator ,IExecutableExtension {
	
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		this.product = config.getAttribute("product"); //$NON-NLS-1$
		this.version = config.getAttribute("version"); //$NON-NLS-1$
	}
	
    public void createStatement(SQLObject element, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax, SybaseDdlScript script, IProgressMonitor progressMonitor,
            int task)
    {
        super.createStatement(element, quoteIdentifiers, qualifyNames, fullSyntax, script, progressMonitor,
                task);
            if(element instanceof SybaseASABaseDBSpace)
            {
                String[] statements = ((SybaseASADdlBuilder)sybaseDdlBuilder).createDBSpace((SybaseASABaseDBSpace)element, quoteIdentifiers, qualifyNames, fullSyntax);
                for(int j = 0; j<statements.length; j++)
                {
                    script.addCreateDatabaseObjectStatements(statements[j]);
                }
            }
            
//            if(o instanceof AuthorizationIdentifier)
//            {
//                if(!this.generatePrivilegeStatement())
//                {
//                    continue;
//                }
//                AuthorizationIdentifier authid = (AuthorizationIdentifier)o;
//
//                // role, group and user are all contained in the authorizations list
//                EList pList = authid.getReceivedPrivilege();
//                Iterator iter = pList.iterator();
//                while(iter.hasNext())
//                {
//                    Privilege p = (Privilege)iter.next();
//                    SQLObject obj = p.getObject();
//                    
//                    // Check if the SQL object of privilege is among the given objects
//                    boolean foundObj = false;
//                    Iterator elementIter = allElements.iterator();
//                    while(elementIter.hasNext())
//                    {
//                        Object e = elementIter.next();
//                        if(e == obj)
//                        {
//                            foundObj = true;
//                            break;
//                        }
//                    }
//                    if(!foundObj)
//                    {
//                        // Dont generate grant statement if the object is not in the list
//                        continue;
//                    }
//                    String stat = builder.grantPrivilege(p, quoteIdentifiers, qualifyNames);
//                    if(stat != null && !stat.trim().equals(""))
//                    {
//                        script.addGrantPrivilegeStatement(stat);
//                    }
//                }
//            }
    }
    
    
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlGenerator#dropStatement(org.eclipse.datatools.modelbase.sql.schema.SQLObject, org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption[], boolean, boolean, org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript, org.eclipse.core.runtime.IProgressMonitor, int)
     */
    public void dropStatement(SQLObject element, boolean quoteIdentifiers, boolean qualifyNames, SybaseDdlScript script, IProgressMonitor progressMonitor, int task)
    {
        super.dropStatement(element, quoteIdentifiers, qualifyNames, script, progressMonitor, task);
        if (element instanceof SybaseASABaseDBSpace)
        {
            String statement = ((SybaseASADdlBuilder)sybaseDdlBuilder).dropDBSpace((SybaseASABaseDBSpace)element, quoteIdentifiers, qualifyNames);
            if (statement != null)
                script.addDropTableStatement(statement);
        }
    }

    private String product;
    private String version;

	protected SybaseDdlBuilder createBuilder() {
        sybaseDdlBuilder = SybaseASADdlBuilder.getInstance();
        return sybaseDdlBuilder;
	}

	public byte[] getAdditionalOptionIndices(int objectType) {
		if(objectType == SybaseDatabaseObjecType.DATABASE)
        {
		    return new byte[]{
                SybaseDdlGenerationOptions.GENERATE_STORED_PROCEDURES,
                SybaseDdlGenerationOptions.GENERATE_USER_FUNCTION,
                SybaseDdlGenerationOptions.GENERATE_TRIGGERS,
                SybaseDdlGenerationOptions.GENERATE_EVENTS,
		        SybaseDdlGenerationOptions.GENERATE_TABLES,
                SybaseDdlGenerationOptions.GENERATE_PROXY_TABLES,
                SybaseDdlGenerationOptions.GENERATE_VIEWS,
                SybaseDdlGenerationOptions.GENERATE_INDICES,
                SybaseDdlGenerationOptions.GENERATE_USER_DEFINED_TYPES,
                SybaseDdlGenerationOptions.GENERATE_USERS_GROUPS,
                SybaseDdlGenerationOptions.GENERATE_DBSPACE,
            };
        }
        else if(objectType == SybaseDatabaseObjecType.TABLE || objectType == SybaseDatabaseObjecType.PROXY_TABLE)
        {
            return new byte[]{
                SybaseDdlGenerationOptions.GENERATE_PRIMARY_KEYS,
                SybaseDdlGenerationOptions.GENERATE_UNIQUE_CONSTRAINTS,
                SybaseDdlGenerationOptions.GENERATE_FOREIGN_KEYS,
                SybaseDdlGenerationOptions.GENERATE_CHECK_CONSTRAINTS,
                SybaseDdlGenerationOptions.GENERATE_INDICES,
                SybaseDdlGenerationOptions.GENERATE_TRIGGERS,
                SybaseDdlGenerationOptions.GENERATE_USER_DEFINED_TYPES,
            };
        }
        else
        {
            return new byte[]{};
        }
	}

    public byte[] getGenerationOptionIndices(int objectType)
    {
        if (objectType == SybaseDatabaseObjecType.TABLE || objectType == SybaseDatabaseObjecType.USER_DEFINED_FUNCTION
                || objectType == SybaseDatabaseObjecType.PROCEDURE || objectType == SybaseDatabaseObjecType.VIEW
                || objectType == SybaseDatabaseObjecType.DATABASE || objectType == SybaseDatabaseObjecType.PROXY_TABLE)
        {
            return new byte[]{
                SybaseDdlGenerationOptions.GENERATE_CREATE_STATEMENTS,
                SybaseDdlGenerationOptions.GENERATE_DROP_STATEMENTS,
                SybaseDdlGenerationOptions.GENERATE_FULLY_QUALIFIED_NAME,
                SybaseDdlGenerationOptions.GENERATE_QUOTED_IDENTIFIER,
//                SybaseDdlGenerationOptions.GENERATE_FULL_SYNTAX,
                SybaseDdlGenerationOptions.GENERATE_PRIVILEGE,
                //TODO: currently we always generate comments 
//                SybaseDdlGenerationOptions.GENERATE_COMMENTS,
            };
        }
        else
        {
            return new byte[]{
                SybaseDdlGenerationOptions.GENERATE_CREATE_STATEMENTS,
                SybaseDdlGenerationOptions.GENERATE_DROP_STATEMENTS,
                SybaseDdlGenerationOptions.GENERATE_FULLY_QUALIFIED_NAME,
                SybaseDdlGenerationOptions.GENERATE_QUOTED_IDENTIFIER,
//                SybaseDdlGenerationOptions.GENERATE_FULL_SYNTAX,
                //TODO: currently we always generate comments
//                SybaseDdlGenerationOptions.GENERATE_COMMENTS,
            };
        }
    }
    
    public byte[] getExclusiveAdditionalOptionIndices(int objectType)
    {
        if(objectType == SybaseDatabaseObjecType.DATABASE)
        {
            return new byte[]{
                SybaseDdlGenerationOptions.GENERATE_USER_FUNCTION,
                SybaseDdlGenerationOptions.GENERATE_EVENTS,
                SybaseDdlGenerationOptions.GENERATE_DBSPACE,
            };
        }
        else if(objectType == SybaseDatabaseObjecType.TABLE || objectType == SybaseDatabaseObjecType.PROXY_TABLE)
        {
            return new byte[]{
                SybaseDdlGenerationOptions.GENERATE_TRIGGERS,
            };
        }
        else
        {
            return new byte[]{};
        }
    }

	public String[] dropSQLObjectsForDeltaDDL(SQLObject[] elements,boolean quoteIdentifiers, boolean qualifyNames,
			IProgressMonitor progressMonitor) 
	{
		return super.dropSQLObjects(elements, quoteIdentifiers, qualifyNames, progressMonitor);
	}

	public String[] createSQLObjectsForDeltaDDL(SQLObject[] elements,boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax,
			IProgressMonitor progressMonitor) 
	{
		return super.createSQLObjects(elements, quoteIdentifiers, qualifyNames, progressMonitor);
	}
}

