package org.eclipse.datatools.enablement.sybase.deltaddl;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Constraint delta ddl gen provider
 *
 * @author Idull
 */
public class ConstraintDeltaDdlGenProvider extends AbstractDeltaDdlGenProvider
{
    protected Map _modifyRecordMap;

    public void processAlterStatement(SQLObject element, Map modifyRecordMap, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script, IProgressMonitor monitor)
    {
        _modifyRecordMap = modifyRecordMap;
        List list = (List) modifyRecordMap.get(element);
        if (list != null)
        {
            for (int i = 0; i < list.size(); i++)
            {
                SybaseDeltaDdlGeneration.FeatureChangeRecord cr = (SybaseDeltaDdlGeneration.FeatureChangeRecord) list.get(i);
                getModificationResult(element, cr.feature, cr.oldValue, cr.newValue, quoteIdentifiers, qualifyNames, fullSyntax, script);
            }
        }
    }
    protected void addCreateStatement(SybaseDdlScript script, String statement)
    {
        script.addAlterTableAddConstraintStatement(statement);
    }
    protected void addDropStatement(SybaseDdlScript script, String statement)
    {
        script.addAlterTableDropConstraintStatement(statement);
    }
    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        if (!(e instanceof Constraint))
        {
            return;
        }
        Constraint c = (Constraint) e;

        // name changed
        if (feature.getFeatureID() == SQLConstraintsPackage.CONSTRAINT__NAME && needGenerateRenamingDdl(c))
        {
            script.addAlterTableRenameConstraintStatement(generateRenameConstraintStatement(c, (String) oldValue, (String) newValue,
                    quoteIdentifiers, qualifyNames, fullSyntax));
        }
    }

    protected String generateRenameConstraintStatement(Constraint constraint, String oldname, String newname,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        return ""; //$NON-NLS-1$
    }
    
    protected boolean needGenerateRenamingDdl(Constraint constraint)
    {
        return true;
    }
}
