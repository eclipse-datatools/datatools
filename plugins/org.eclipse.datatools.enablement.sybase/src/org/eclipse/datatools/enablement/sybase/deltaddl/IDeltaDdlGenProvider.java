package org.eclipse.datatools.enablement.sybase.deltaddl;

import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;


/**
 * The interface for extension point deltagenservice to implement 
 * specified vender, version database and SQL object type modification 
 * scirpt 
 * @author linsong
 *
 */
public interface IDeltaDdlGenProvider {

	/**
	 * analyze specified sqlobject changes
	 * @param element
	 * @param changeMap
     * @param modificationRecords
	 */
	public void analyze(SQLObject element, Map changeMap, Map modificationRecords);
	
	/**
	 * generate specified sqlobject modification scripts
	 * @param element
	 * @param modifyRecordMap
	 * @param quoteIdentifiers 
	 * @param qualifyNames 
	 * @param fullSyntax 
	 * @param script 
	 * @param monitor 
	 */
	public void processAlterStatement(SQLObject element, Map modifyRecordMap,
			boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax,
			SybaseDdlScript script, IProgressMonitor monitor);
    
    /**
     * generate specified sqlobject creation scripts
     * @param element
     * @param quoteIdentifiers 
     * @param qualifyNames 
     * @param fullSyntax 
     * @param script
     * @param generator
     * @param monitor
     */
    public void processCreateStatement(SQLObject element, boolean quoteIdentifiers,
			boolean qualifyNames, boolean fullSyntax,
			SybaseDdlScript script, ISybaseDdlGenerator generator,
			IProgressMonitor monitor);
    
    /**
     * generate specified sqlobject drop scripts
     * @param element
     * @param quoteIdentifiers 
     * @param qualifyNames 
     * @param script
     * @param generator
     * @param monitor
     */
    public void processDropStatement(SQLObject element,
			boolean quoteIdentifiers, boolean qualifyNames, SybaseDdlScript script,
			ISybaseDdlGenerator generator, IProgressMonitor monitor);
}
