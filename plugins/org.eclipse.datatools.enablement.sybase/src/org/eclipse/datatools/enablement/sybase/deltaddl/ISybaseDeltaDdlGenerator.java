package org.eclipse.datatools.enablement.sybase.deltaddl;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.DeltaDDLGenerator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;

public interface ISybaseDeltaDdlGenerator extends DeltaDDLGenerator
{
    /**
     * set customized options to control the delta ddl generated scripted style
     * @param rootObject TODO
     * @param changeDescription
     * @param options
     * @param monitor
     * @return
     */
    public String[] generateDeltaDDL(EObject rootObject, ChangeDescription changeDescription, EngineeringOption[] options, IProgressMonitor monitor);
}
