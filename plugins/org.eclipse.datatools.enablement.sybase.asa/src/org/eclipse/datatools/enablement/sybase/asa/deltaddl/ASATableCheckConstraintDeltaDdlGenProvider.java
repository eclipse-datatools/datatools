package org.eclipse.datatools.enablement.sybase.asa.deltaddl;

import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.enablement.sybase.IGenericDdlConstants;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlConstants;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.ConstraintDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Delta ddl gen provider for ASA table check constraint.
 *
 * @author Idull
 */
public class ASATableCheckConstraintDeltaDdlGenProvider extends ConstraintDeltaDdlGenProvider
{
    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        super.getModificationResult(e, feature, oldValue, newValue, quoteIdentifiers, qualifyNames, fullSyntax, script);
        if(!(e instanceof CheckConstraint))
        {
            return;
        }
        CheckConstraint rc = (CheckConstraint)e;
        
        // The search condition is changed
        if(feature.getFeatureID() == SQLExpressionsPackage.SEARCH_CONDITION_DEFAULT__SQL)
        {
            String tableName = rc.getBaseTable().getName();
            String constraintName = rc.getName();
            if(quoteIdentifiers)
            {
                tableName = SQLUtil.quote(tableName,DOUBLE_QUOTE);
                constraintName = SQLUtil.quote(constraintName,DOUBLE_QUOTE);
            }
            StringBuffer statement = new StringBuffer(128);
            statement.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                    getName(rc.getBaseTable(), quoteIdentifiers, qualifyNames)).append(SPACE).append(ALTER).append(
                    SPACE).append(CONSTRAINT).append(SPACE).append(constraintName).append(SPACE).append(CHECK).append(
                    LEFT_PARENTHESIS).append(rc.getSearchCondition().getSQL()).append(RIGHT_PARENTHESIS);
            script.addAlterTableAlterConstraintStatements(statement.toString());
        }
    }
    
    protected void reCreateConstraint(DDLGenerator ddlgen, CheckConstraint rc, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        String newName = rc.getName();
        if(isNameChanged(rc))
        {
            rc.setName(getOldName(rc));
        }
        
        // drop the old one
        String[] ddl = ddlgen.dropSQLObjects(new SQLObject[]
        {
            rc
        }, quoteIdentifiers, qualifyNames, null);
        for (int i = 0; i < ddl.length; i++)
        {
            script.addAlterTableDropConstraintStatement(ddl[i]);
        }
        
        // create the new one
        rc.setName(newName);
        ddl = ddlgen.createSQLObjects(new SQLObject[]{rc}, quoteIdentifiers, qualifyNames, null);
        for (int i = 0; i < ddl.length; i++)
        {
            script.addAlterTableAddConstraintStatement(ddl[i]);
        }
    }

    
    protected boolean needGenerateRenamingDdl(Constraint constraint)
    {
        if(!(constraint instanceof CheckConstraint))
        {
            return true;
        }
        CheckConstraint rc = (CheckConstraint)constraint;
        
        List list = (List) _modifyRecordMap.get(rc);
        if (list != null)
        {
            for (int i = 0; i < list.size(); i++)
            {
                SybaseDeltaDdlGeneration.FeatureChangeRecord cr = (SybaseDeltaDdlGeneration.FeatureChangeRecord) list.get(i);
                if(cr.feature.getFeatureID() == SQLExpressionsPackage.SEARCH_CONDITION_DEFAULT__SQL)
                {
                    return true;
                }
            }
        }
        return true;
    }
    
    protected boolean isNameChanged(SQLObject obj)
    {
        List list = (List) _modifyRecordMap.get(obj);
        if (list != null)
        {
            for (int i = 0; i < list.size(); i++)
            {
                SybaseDeltaDdlGeneration.FeatureChangeRecord cr = (SybaseDeltaDdlGeneration.FeatureChangeRecord) list.get(i);
                if(cr.feature.getFeatureID() == SQLSchemaPackage.SQL_OBJECT__NAME)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected String getOldName(SQLObject obj)
    {
        List list = (List) _modifyRecordMap.get(obj);
        if (list != null)
        {
            for (int i = 0; i < list.size(); i++)
            {
                SybaseDeltaDdlGeneration.FeatureChangeRecord cr = (SybaseDeltaDdlGeneration.FeatureChangeRecord) list.get(i);
                if(cr.feature.getFeatureID() == SQLSchemaPackage.SQL_OBJECT__NAME)
                {
                    return (String)cr.oldValue;
                }
            }
        }
        return obj.getName();
    }
    
    
    protected String[] generateRenameConstraintStatement(Constraint constraint, String oldname, String newname,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        StringBuffer sb = new StringBuffer("");
        sb.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                (getName((Table) constraint.eContainer(), quoteIdentifiers, qualifyNames))).append(SPACE).append(
                IGenericDdlConstants.RENAME).append(SPACE).append(CONSTRAINT).append(SPACE).append(SPACE).append(
                quoteIdentifiers ? getDoubleQuotedString(oldname) : oldname).append(SPACE).append(
                ISybaseDdlConstants.TO).append(SPACE).append(
                quoteIdentifiers ? getDoubleQuotedString(newname) : newname);
        return new String[]{sb.toString()};
    }
}
