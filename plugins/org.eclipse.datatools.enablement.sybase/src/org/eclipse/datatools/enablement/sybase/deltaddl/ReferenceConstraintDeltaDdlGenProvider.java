package org.eclipse.datatools.enablement.sybase.deltaddl;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration.FeatureChangeRecord;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaFactory;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Reference constraint delta ddl gen provider
 *
 * @author Idull
 */
public class ReferenceConstraintDeltaDdlGenProvider extends ConstraintDeltaDdlGenProvider
{
    protected boolean _isRecreated = false;
    
    protected void initProvider()
    {
        super.initProvider();
        _isRecreated = false;
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
        if(feature.getFeatureID() == SQLConstraintsPackage.REFERENCE_CONSTRAINT__MEMBERS)
        {
            Database database = (Database) ContainmentServiceImpl.INSTANCE.getRootElement(rc);
            DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(database);
            DDLGenerator ddlgen = def.getDDLGenerator();
            reCreateConstraint(ddlgen, rc, quoteIdentifiers, qualifyNames, fullSyntax, script);
        }
    }
    
    /**
     * Can only execute once for any type of constraint
     * @param ddlgen
     * @param rc
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @param script
     */
    protected void reCreateConstraint(DDLGenerator ddlgen, ReferenceConstraint rc, boolean quoteIdentifiers,
            boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        if(!_isRecreated)
        {
            String newName = rc.getName();
            if (isNameChanged(rc))
            {
                rc.setName(getOldName(rc));
            }

            /**
             * We need always use the oringal table when generate "DROP" statement for constraint. Otherwise it wont
             * work in editor.
             */
            String originalName = "";
            String newName1 = "";
            if (_modifyRecordMap.get(rc.eContainer()) != null)
            {
                List list = (List) _modifyRecordMap.get(rc.eContainer());
                Iterator iter = list.iterator();
                while (iter.hasNext())
                {
                    FeatureChangeRecord featureRecorder = (FeatureChangeRecord) iter.next();
                    if (featureRecorder.feature.getFeatureID() == SQLSchemaPackage.SQL_OBJECT__NAME)
                    {
                        originalName = (String) featureRecorder.oldValue;
                        newName1 = (String) featureRecorder.newValue;
                        BaseTable table = (BaseTable) rc.eContainer();
                        table.setName(originalName);
                    }
                }
            }

            // drop the old one
            String[] ddl = ddlgen.dropSQLObjects(new SQLObject[]
            {
                rc
            }, quoteIdentifiers, qualifyNames, null);

            if (newName1.length() != 0)
            {
                BaseTable table = (BaseTable) rc.eContainer();
                table.setName(newName1);
            }

            for (int i = 0; i < ddl.length; i++)
            {
                script.addAlterTableDropConstraintStatement(ddl[i]);
            }

            // create the new one
            rc.setName(newName);
            ddl = ddlgen.createSQLObjects(new SQLObject[]
            {
                rc
            }, quoteIdentifiers, qualifyNames, null);
            for (int i = 0; i < ddl.length; i++)
            {
                script.addAlterTableAddConstraintStatement(ddl[i]);
            }
            _isRecreated = true;
        }
    }

    
    protected boolean needGenerateRenamingDdl(Constraint constraint)
    {
        if(!(constraint instanceof ReferenceConstraint))
        {
            return true;
        }
        ReferenceConstraint rc = (ReferenceConstraint)constraint;
        
        List list = (List) _modifyRecordMap.get(rc);
        if (list != null)
        {
            for (int i = 0; i < list.size(); i++)
            {
                SybaseDeltaDdlGeneration.FeatureChangeRecord cr = (SybaseDeltaDdlGeneration.FeatureChangeRecord) list.get(i);
                if(cr.feature.getFeatureID() == SQLConstraintsPackage.REFERENCE_CONSTRAINT__MEMBERS)
                {
                    return false;
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
}
