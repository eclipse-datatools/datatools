package org.eclipse.datatools.enablement.sybase.deltaddl;

import java.util.Map;

import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * While no DeltaDdlGenProvider extension found for specified EClass type, DeltaDdlGenServiceImpl will  
 * call GenericDeltaDdlGenProvider as default.   
 * @author  linsong
 */
public class GenericDeltaDdlGenProvider extends AbstractDeltaDdlGenProvider implements IDeltaDdlGenProvider {
	
	public static final GenericDeltaDdlGenProvider INSTANCE = new GenericDeltaDdlGenProvider();
	
	private GenericDeltaDdlGenProvider(){};

	public void analyze(SQLObject element, Map changeMap) {
		int flag = ((Integer) changeMap.get(element)).intValue();
		if((flag & (SybaseDeltaDdlGeneration.MODIFIED)) != 0) 
		{
			changeMap.put(element, new Integer(SybaseDeltaDdlGeneration.DROP|SybaseDeltaDdlGeneration.CREATE));
		}
	}

    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript newParam)
    {
    }

	protected void addCreateStatement(SybaseDdlScript script, String statement) {
		script.addCreateOtherStatements(statement);
	}

	protected void addDropStatement(SybaseDdlScript script, String statement) {
		script.addDropOtherStatements(statement);
	}

}
