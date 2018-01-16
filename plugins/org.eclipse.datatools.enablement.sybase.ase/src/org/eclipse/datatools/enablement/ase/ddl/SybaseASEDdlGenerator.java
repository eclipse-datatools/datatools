package org.eclipse.datatools.enablement.ase.ddl;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.enablement.ase.ISybaseASEDdlConstants;
import org.eclipse.datatools.enablement.ase.SybaseASESQLUtil;
import org.eclipse.datatools.enablement.sybase.IGenericDdlConstants;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDatabaseObjecType;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlGenerationOptions;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.emf.ecore.EObject;

public final class SybaseASEDdlGenerator extends SybaseDdlGenerator implements DDLGenerator, IExecutableExtension,
        ISybaseASEDdlConstants, IGenericDdlConstants
{

    public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
            throws CoreException
    {
        this.product = config.getAttribute("product"); //$NON-NLS-1$
        this.version = config.getAttribute("version"); //$NON-NLS-1$
    }

    
    
    // add use db before non-catalog object
    protected void createStatements(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax, SybaseDdlScript script, IProgressMonitor progressMonitor,
            int task) 
    {
        if (sybaseDdlBuilder == null)
        {
            sybaseDdlBuilder = (SybaseDdlBuilder) createBuilder();
        }
        for (int i = 0; i < elements.length; i++)
        {
            createStatement(elements[i], quoteIdentifiers, qualifyNames, fullSyntax, script, progressMonitor,
                    task);
        }
    }
    
    
    protected void dropStatements(SQLObject[] elements, boolean quoteIdentifiers,
            boolean qualifyNames, SybaseDdlScript script, IProgressMonitor progressMonitor, int task)
    {
        if (this.sybaseDdlBuilder == null)
        {
            this.sybaseDdlBuilder = (SybaseDdlBuilder) createBuilder();
        }
        
        for (int i = 0; i < elements.length; i++)
        {
            dropStatement(elements[i], quoteIdentifiers, qualifyNames, script, progressMonitor, task);
        }
    }

    public void createStatement(SQLObject element, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax,
            SybaseDdlScript script, IProgressMonitor progressMonitor, int task)
    {
        if(element instanceof SybaseASEColumnCheckConstraint)
        {
            //do nothing
        }
        else if (element instanceof SybaseASESegment)
        {
            String[] statements = ((SybaseASEDdlBuilder) sybaseDdlBuilder).createSegment((SybaseASESegment) element,
                    quoteIdentifiers, qualifyNames, fullSyntax);
            for (int j = 0; j < statements.length; j++)
            {
                script.addCreateDatabaseObjectStatements(statements[j]);
            }
        }
        else if (element instanceof SybaseASEDefault)
        {
            String[] statements = ((SybaseASEDdlBuilder) sybaseDdlBuilder).createDefault((SybaseASEDefault) element,
                    quoteIdentifiers, qualifyNames, fullSyntax);
            for (int j = 0; j < statements.length; j++)
            {
                script.addCreateDefaultStatements(statements[j]);
            }
        }
        else if (element instanceof SybaseASERule)
        {
            String[] statements = ((SybaseASEDdlBuilder) sybaseDdlBuilder).createRule((SybaseASERule) element,
                    quoteIdentifiers, qualifyNames, fullSyntax);
            for (int j = 0; j < statements.length; j++)
            {
                script.addCreateRuleStatements(statements[j]);
            }
        }
        else
        {
            super.createStatement(element, quoteIdentifiers, qualifyNames, fullSyntax, script, progressMonitor, task);
        }
    }

    public void dropStatement(SQLObject element, boolean quoteIdentifiers, boolean qualifyNames,
            SybaseDdlScript script, IProgressMonitor progressMonitor, int task)
    {
        super.dropStatement(element, quoteIdentifiers, qualifyNames, script, progressMonitor, task);
        if (element instanceof SybaseASESegment)
        {
            script.addDropDatabaseObjectStatements(((SybaseASEDdlBuilder) sybaseDdlBuilder).dropSegment(
                    (SybaseASESegment) element, quoteIdentifiers, qualifyNames));
        }
        else if (element instanceof SybaseASEDefault)
        {
            script.addDropDefaultStatements(((SybaseASEDdlBuilder) sybaseDdlBuilder).dropDefault(
                    (SybaseASEDefault) element, quoteIdentifiers, qualifyNames));
        }
        else if (element instanceof SybaseASERule)
        {
            script.addDropRuleStatements(((SybaseASEDdlBuilder) sybaseDdlBuilder).dropRule((SybaseASERule) element,
                    quoteIdentifiers, qualifyNames));
        }
    }

    private String product;
    private String version;

    protected SybaseDdlBuilder createBuilder()
    {
        sybaseDdlBuilder = SybaseASEDdlBuilder.getInstance();
        return sybaseDdlBuilder;
    }

    public byte[] getAdditionalOptionIndices(int objectType) {
        if(objectType == SybaseDatabaseObjecType.DATABASE)
        {
            return new byte[]{
                SybaseDdlGenerationOptions.GENERATE_STORED_PROCEDURES,
                SybaseDdlGenerationOptions.GENERATE_DEFAULTS,
                SybaseDdlGenerationOptions.GENERATE_RULES,
                SybaseDdlGenerationOptions.GENERATE_TRIGGERS,
                SybaseDdlGenerationOptions.GENERATE_TABLES,
                SybaseDdlGenerationOptions.GENERATE_PROXY_TABLES,
                SybaseDdlGenerationOptions.GENERATE_VIEWS,
                SybaseDdlGenerationOptions.GENERATE_INDICES,
                SybaseDdlGenerationOptions.GENERATE_USER_DEFINED_TYPES,
                SybaseDdlGenerationOptions.GENERATE_USERS_GROUPS,
                SybaseDdlGenerationOptions.GENERATE_SEGMENTS,
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
                SybaseDdlGenerationOptions.GENERATE_RULES,
                SybaseDdlGenerationOptions.GENERATE_DEFAULTS,
            };
        }
        else
        {
            return new byte[]{};
        }
    }

    public byte[] getGenerationOptionIndices(int objectType)
    {
        if (objectType == SybaseDatabaseObjecType.TABLE || objectType == SybaseDatabaseObjecType.PROCEDURE
                || objectType == SybaseDatabaseObjecType.USER_DEFINED_FUNCTION
                || objectType == SybaseDatabaseObjecType.VIEW || objectType == SybaseDatabaseObjecType.DATABASE
                || objectType == SybaseDatabaseObjecType.PROXY_TABLE)
        {
            return new byte[]{
                SybaseDdlGenerationOptions.GENERATE_CREATE_STATEMENTS,
                SybaseDdlGenerationOptions.GENERATE_DROP_STATEMENTS,
                SybaseDdlGenerationOptions.GENERATE_FULLY_QUALIFIED_NAME,
                SybaseDdlGenerationOptions.GENERATE_QUOTED_IDENTIFIER,
//                SybaseDdlGenerationOptions.GENERATE_FULL_SYNTAX,
                SybaseDdlGenerationOptions.GENERATE_PRIVILEGE,
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
            };
        }
    }
    
    public byte[] getExclusiveAdditionalOptionIndices(int objectType)
    {
        if(objectType == SybaseDatabaseObjecType.DATABASE)
        {
            return new byte[]{
                SybaseDdlGenerationOptions.GENERATE_DEFAULTS,
                SybaseDdlGenerationOptions.GENERATE_RULES,
                SybaseDdlGenerationOptions.GENERATE_SEGMENTS,
            };
        }
        else if(objectType == SybaseDatabaseObjecType.TABLE || objectType == SybaseDatabaseObjecType.PROXY_TABLE)
        {
            return new byte[]{
                SybaseDdlGenerationOptions.GENERATE_TRIGGERS,
                SybaseDdlGenerationOptions.GENERATE_RULES,
                SybaseDdlGenerationOptions.GENERATE_DEFAULTS,
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
		SybaseDdlScript script = new SybaseDdlScript();
        dropStatements(elements, quoteIdentifiers, qualifyNames, script, progressMonitor, 100);
        return script.getStatements();
	}



	public String[] createSQLObjectsForDeltaDDL(SQLObject[] elements,boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax,
			IProgressMonitor progressMonitor) 
	{
        SybaseDdlScript script = new SybaseDdlScript();
        createStatements(elements, quoteIdentifiers, qualifyNames, fullSyntax, script, progressMonitor, 100);
        return script.getStatements();
	}
	
	/**
	 * Override the one in super class to add use db statements.
	 */
    public String[] createSQLObjects(SQLObject[] elements, EngineeringOption[] options,
            IProgressMonitor progressMonitor)
    {
        SybaseDdlScript script = new SybaseDdlScript();
        createStatements(elements, options, script, progressMonitor, 100);
        if(generateUseDatabase(options))
        {
            for (int i = 0; i < elements.length; i++)
            {
                if(!(elements[i] instanceof Catalog))
                {
                    Catalog catalog = SybaseASESQLUtil.getContainedCatalog((EObject)elements[i]);
                    if(catalog != null)
                    {
                        String useDbStr = SybaseASESQLUtil.getUseDbStatement(catalog,(DatabaseIdentifier)getParameter());
                        script.addUseDatabaseCreateStatements(useDbStr);
                        break;
                    }
                }
            }
        }
        return script.getStatements(); 
    }
    
    public String[] createSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax, IProgressMonitor progressMonitor)
    {
        SybaseDdlScript script = new SybaseDdlScript();
        createStatements(elements, quoteIdentifiers, qualifyNames, fullSyntax, script, progressMonitor, 100);
        for (int i = 0; i < elements.length; i++)
        {
            if(!(elements[i] instanceof Catalog))
            {
                Catalog catalog = SybaseASESQLUtil.getContainedCatalog((EObject)elements[i]);
                if(catalog != null)
                {
                    String useDbStr = SybaseASESQLUtil.getUseDbStatement(catalog,(DatabaseIdentifier)getParameter());
                    script.addUseDatabaseCreateStatements(useDbStr);
                    break;
                }
            }
        }
        return script.getStatements();
    }
    
    public String[] dropSQLObjects(SQLObject[] elements, EngineeringOption[] options, IProgressMonitor progressMonitor)
    {
        SybaseDdlScript script = new SybaseDdlScript();
        dropStatements(elements, options, script, progressMonitor, 100);
        if(generateUseDatabase(options))
        {
            for (int i = 0; i < elements.length; i++)
            {
                if(!(elements[i] instanceof Catalog))
                {
                    Catalog catalog = SybaseASESQLUtil.getContainedCatalog((EObject)elements[i]);
                    if(catalog != null)
                    {
                        String useDbStr = SybaseASESQLUtil.getUseDbStatement(catalog,(DatabaseIdentifier)getParameter());
                        script.addUseDatabaseCreateStatements(useDbStr);
                        break;
                    }
                }
            }
        }
        return script.getStatements();
    }
    
    public String[] dropSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames,
            IProgressMonitor progressMonitor)
    {
        SybaseDdlScript script = new SybaseDdlScript();
        dropStatements(elements, quoteIdentifiers, qualifyNames, script, progressMonitor, 100);
        for (int i = 0; i < elements.length; i++)
        {
            if(!(elements[i] instanceof Catalog))
            {
                {
                    Catalog catalog = SybaseASESQLUtil.getContainedCatalog((EObject)elements[i]);
                    if(catalog != null)
                    {
                        String useDbStr = SybaseASESQLUtil.getUseDbStatement(catalog,(DatabaseIdentifier)getParameter());
                        script.addUseDatabaseDropStatements(useDbStr);
                        break;
                    }
                }
            }
        }
        return script.getStatements();
    }
}
