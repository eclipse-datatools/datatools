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
    
    public String[] createSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, IProgressMonitor progressMonitor);
    
    /**
     * @param elements
     * @return cloned options apply for spefied elements
     */
    public EngineeringOption[] createSelectedOptions(SQLObject[] elements);
    
    /**
     * 
     * @return cloned generation options
     */
    public EngineeringOption[] createGenerationOptions();
    
}
