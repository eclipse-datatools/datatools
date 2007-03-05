package org.eclipse.datatools.enablement.sybase.asa.deltaddl;

import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlConstants;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Delta ddl gen provider for ASA foreign key.
 * 
 * @author Idull
 */
public class ASAForeignKeyDeltaDdlGenProvider extends ASAReferenceConstraintDeltaDdlGenProvider
{
    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        if (!(e instanceof SybaseASAForeignKey))
        {
            return;
        }
        SybaseASAForeignKey fk = (SybaseASAForeignKey) e;

        super.getModificationResult(e, feature, oldValue, newValue, quoteIdentifiers, qualifyNames, fullSyntax, script);
        if (feature.getFeatureID() == SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__CHECK_ON_COMMIT
                || feature.getFeatureID() == SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__NULLABLE
                || feature.getFeatureID() == SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__ON_DELETE
                || feature.getFeatureID() == SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__ON_UPDATE)
        {
            Database database = (Database) ContainmentServiceImpl.INSTANCE.getRootElement(fk);
            DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(database);
            DDLGenerator ddlgen = def.getDDLGenerator();
            reCreateConstraint(ddlgen, fk, quoteIdentifiers, qualifyNames, fullSyntax, script);
        }

        if (feature.getFeatureID() == SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__DESCRIPTION
                && fk.getDescription() != null && fk.getDescription().length() != 0)
        {
            StringBuffer sb = new StringBuffer("");
            sb.append("COMMENT").append(SPACE).append(ON).append(SPACE).append(FOREIGN_KEY).append(SPACE).append(
                    getName((Table) fk.eContainer(), quoteIdentifiers, qualifyNames) + "."
                            + getName(fk, quoteIdentifiers)).append(SPACE).append(ISybaseDdlConstants.IS).append(SPACE)
                    .append(getSingleQuotedString(fk.getDescription()));
            script.addAlterOtherStatements(sb.toString());
        }
    }
    protected boolean needGenerateRenamingDdl(Constraint constraint)
    {
        if (!(constraint instanceof ReferenceConstraint))
        {
            return true;
        }
        ReferenceConstraint rc = (ReferenceConstraint) constraint;

        List list = (List) _modifyRecordMap.get(rc);
        if (list != null)
        {
            for (int i = 0; i < list.size(); i++)
            {
                SybaseDeltaDdlGeneration.FeatureChangeRecord cr = (SybaseDeltaDdlGeneration.FeatureChangeRecord) list.get(i);
                if (cr.feature.getFeatureID() == SQLConstraintsPackage.REFERENCE_CONSTRAINT__MEMBERS
                        || cr.feature.getFeatureID() == SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__CHECK_ON_COMMIT
                        || cr.feature.getFeatureID() == SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__NULLABLE
                        || cr.feature.getFeatureID() == SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__ON_DELETE
                        || cr.feature.getFeatureID() == SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__ON_UPDATE)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
