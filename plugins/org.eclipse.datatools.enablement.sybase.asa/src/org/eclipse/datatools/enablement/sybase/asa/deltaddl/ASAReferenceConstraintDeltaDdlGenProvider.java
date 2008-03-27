package org.eclipse.datatools.enablement.sybase.asa.deltaddl;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.enablement.sybase.IGenericDdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlConstants;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.ReferenceConstraintDeltaDdlGenProvider;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Delta ddl gen provider for ASA reference constraint
 *
 * @author Idull
 */
public class ASAReferenceConstraintDeltaDdlGenProvider extends ReferenceConstraintDeltaDdlGenProvider
{
    
    protected String[] generateRenameConstraintStatement(Constraint constraint, String oldname, String newname,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        StringBuffer sb1 = new StringBuffer("");
        sb1.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                (getName((Table) constraint.eContainer(), quoteIdentifiers, qualifyNames))).append(SPACE).append(
                IGenericDdlConstants.RENAME).append(SPACE).append(CONSTRAINT).append(SPACE).append(
                quoteIdentifiers ? getDoubleQuotedString(oldname) : oldname).append(SPACE).append(
                ISybaseDdlConstants.TO).append(SPACE).append(
                quoteIdentifiers ? getDoubleQuotedString(newname) : newname);
        return new String[]{sb1.toString()};
    }
    
    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        super.getModificationResult(e, feature, oldValue, newValue, quoteIdentifiers, qualifyNames, fullSyntax, script);
        if(!(e instanceof ReferenceConstraint))
        {
            return;
        }
        ReferenceConstraint rc = (ReferenceConstraint)e;
        
        // the referenced members are changed. Need to drop the old one and create a new one.
        if (feature.getFeatureID() == SQLConstraintsPackage.REFERENCE_CONSTRAINT__MEMBERS
                || feature == SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseUniqueConstraint_Clustered())
        {
            Database database = (Database) ContainmentServiceImpl.INSTANCE.getRootElement(rc);
            DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(database);
            DDLGenerator ddlgen = def.getDDLGenerator();
            reCreateConstraint(ddlgen, rc, quoteIdentifiers, qualifyNames, fullSyntax, script);
        }
    }
}
