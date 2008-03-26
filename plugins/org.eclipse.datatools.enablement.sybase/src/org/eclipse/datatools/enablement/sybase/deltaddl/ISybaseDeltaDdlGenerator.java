package org.eclipse.datatools.enablement.sybase.deltaddl;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.DeltaDDLGenerator;
//import org.eclipse.emf.ecore.sdo.EChangeSummary;

public interface ISybaseDeltaDdlGenerator extends DeltaDDLGenerator
{
    /**
     * set customized options to control the delta ddl generated scripted style
     * @param changeSummary
     * @param options
     * @param monitor
     * @return
     */
//    public String[] generateDeltaDDL(EChangeSummary changeSummary, EngineeringOption[] options, IProgressMonitor monitor);
}
