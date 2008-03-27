package org.eclipse.datatools.enablement.sybase.asa.deltaddl;

import org.eclipse.datatools.enablement.sybase.IGenericDdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlConstants;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Delta ddl gen provider for ASA column check constraint
 *
 * @author Idull
 */
public class ASAColumnCheckConstraintDeltaDdlGenProvider extends ASATableCheckConstraintDeltaDdlGenProvider
{
    
    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        super.getModificationResult(e, feature, oldValue, newValue, quoteIdentifiers, qualifyNames, fullSyntax, script);
        if (!(e instanceof SybaseASABaseColumnCheckConstraint))
        {
            return;
        }
        SybaseASABaseColumnCheckConstraint columnCk = (SybaseASABaseColumnCheckConstraint) e;

        if (feature.getFeatureID() == SQLExpressionsPackage.SEARCH_CONDITION_DEFAULT__SQL)
        {
            StringBuffer sb = new StringBuffer("");
            sb.append(IGenericDdlConstants.ALTER).append(IGenericDdlConstants.SPACE).append(IGenericDdlConstants.TABLE).append(IGenericDdlConstants.SPACE).append(
                    getName((Table) columnCk.eContainer(), quoteIdentifiers, qualifyNames)).append(IGenericDdlConstants.SPACE).append(
                    IGenericDdlConstants.MODIFY).append(SPACE).append(
                    quoteIdentifiers ? getDoubleQuotedString(columnCk.getColumn().getName()) : columnCk.getColumn()
                            .getName()).append(SPACE).append(CONSTRAINT).append(SPACE).append(
                    quoteIdentifiers ? getDoubleQuotedString(columnCk.getName()) : columnCk.getName()).append(SPACE)
                    .append(CHECK).append(LEFT_PARENTHESIS).append(columnCk.getSearchCondition().getSQL()).append(
                            RIGHT_PARENTHESIS);
            script.addAlterTableAlterConstraintStatements(sb.toString());
        }
    }
}
