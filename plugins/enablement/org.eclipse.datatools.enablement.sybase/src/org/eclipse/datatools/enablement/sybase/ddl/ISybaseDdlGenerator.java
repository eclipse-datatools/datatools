package org.eclipse.datatools.enablement.sybase.ddl;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

public interface ISybaseDdlGenerator extends DDLGenerator{
	/**
     * generate specified elements ddl script, which style following the specified options
	 * @param elements
	 * @param options control the generation behaviour
	 * @param progressMonitor
	 * @return
	 */
	public String[] generateDDL(SQLObject[] elements, EngineeringOption[] options, IProgressMonitor progressMonitor);
	
	/**
	 * 
	 * @param elements
	 * @param options 
	 * @param progressMonitor
	 * @return
	 */
    public String[] createSQLObjects(SQLObject[] elements, EngineeringOption[] options, IProgressMonitor progressMonitor);
    
    /**
     * 
     * @param elements
     * @param options
     * @param progressMonitor
     * @return
     */
    public String[] dropSQLObjects(SQLObject[] elements, EngineeringOption[] options, IProgressMonitor progressMonitor);
    
    public String[] createSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, IProgressMonitor progressMonitor);
    
    /**
     * Delta ddl version of <code>dropSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor)</code>. 
     * Different with <code>dropSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor)</code>, 
     * this method should not generate "use db statements", because the framework has already done this.
     * @param elements
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param progressMonitor
     * @return
     */
    public String[] dropSQLObjectsForDeltaDDL(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor);
    
    /**
     * Delta ddl version of <code>createSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor)</code>. 
     * Different with <code>createSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor)</code>, 
     * this method should not generate "use db statements", because the framework has already done this.
     * @param elements
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param progressMonitor
     * @return
     */
    public String[] createSQLObjectsForDeltaDDL(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, IProgressMonitor progressMonitor);
    
    /**
     * @param objectType
     * @return cloned additional options apply for spefied object type
     */
    public EngineeringOption[] getAdditionalOptions(int objectType);
    
    /**
     * 
     * @return cloned generation options
     */
    public EngineeringOption[] createGenerationOptions();
    
}
