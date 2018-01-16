package org.eclipse.datatools.enablement.sybase.asa.deltaddl;

import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.enablement.sybase.IGenericDdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlConstants;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
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
        if (!(e instanceof SybaseASABaseForeignKey))
        {
            return;
        }
        SybaseASABaseForeignKey fk = (SybaseASABaseForeignKey) e;

        super.getModificationResult(e, feature, oldValue, newValue, quoteIdentifiers, qualifyNames, fullSyntax, script);
        if (feature.getFeatureID() == SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__CHECK_ON_COMMIT
                || feature.getFeatureID() == SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__NULLABLE
                || feature.getFeatureID() == SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__ON_DELETE
                || feature.getFeatureID() == SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__ON_UPDATE
                || feature.getFeatureID() == SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__CLUSTERED)
        {
            Database database = (Database) ContainmentServiceImpl.INSTANCE.getRootElement(fk);
            DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(database);
            DDLGenerator ddlgen = def.getDDLGenerator();
            reCreateConstraint(ddlgen, fk, quoteIdentifiers, qualifyNames, fullSyntax, script);
        }

        if (feature.getFeatureID() == SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__DESCRIPTION)
        {
            StringBuffer sb = new StringBuffer("");
            String description = fk.getDescription();
            if(description!=null && description.length()>0)
            {
            	description = SQLUtil.quote(description, DOUBLE_QUOTE);;
            }
            else
            {
            	description = SINGLE_QUOTE+SINGLE_QUOTE;
            }
            sb.append("COMMENT").append(SPACE).append(ON).append(SPACE).append(FOREIGN_KEY).append(SPACE).append(
                    getName((Table) fk.eContainer(), quoteIdentifiers, qualifyNames) + "."
                            + getName(fk, quoteIdentifiers)).append(SPACE).append(ISybaseDdlConstants.IS).append(SPACE)
                    .append(description);
            script.addCommentOnStatements(sb.toString());
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

        StringBuffer sb2 = new StringBuffer();
        sb2.append(NEWLINE).append(ALTER).append(SPACE).append(FOREIGN_KEY).append(SPACE).append(
                quoteIdentifiers ? getDoubleQuotedString(oldname) : oldname).append(SPACE).append(ON).append(SPACE)
                .append(getName((Table) constraint.eContainer(), quoteIdentifiers, qualifyNames)).append(SPACE).append(
                        IGenericDdlConstants.RENAME).append(SPACE).append(ISybaseDdlConstants.TO).append(SPACE).append(
                        quoteIdentifiers ? getDoubleQuotedString(newname) : newname);
        return new String[]{sb1.toString(),sb2.toString()};
    }
    
    
}
