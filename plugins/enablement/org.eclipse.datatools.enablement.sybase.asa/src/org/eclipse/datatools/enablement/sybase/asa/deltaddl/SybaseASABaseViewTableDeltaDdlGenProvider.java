package org.eclipse.datatools.enablement.sybase.asa.deltaddl;

import org.eclipse.datatools.enablement.sybase.asa.ISybaseASADdlConstants;
import org.eclipse.datatools.enablement.sybase.asa.ddl.SybaseASADdlBuilder;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseViewTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.AbstractDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.IDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASABaseViewTableDeltaDdlGenProvider extends AbstractDeltaDdlGenProvider implements
        IDeltaDdlGenProvider, ISybaseASADdlConstants
{
    
    protected void addCreateStatement(SybaseDdlScript script, String statement)
    {

    }

 
    protected void addDropStatement(SybaseDdlScript script, String statement)
    {

    }

   
    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        if (!(e instanceof SybaseASABaseViewTable))
        {
            return;
        }
        if (feature.getFeatureID() == SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__DESCRIPTION)
        {
            SybaseASADdlBuilder builder = (SybaseASADdlBuilder) SybaseASADdlBuilder.getInstance();
            String description = null;
            if (e.getDescription() == null || e.getDescription().trim().length() == 0)
            {
                description = NULL;
            }
            else
            {
                description = SQLUtil.quote(e.getDescription(), SINGLE_QUOTE);
            }
            String objectName = builder.getName((SybaseASABaseViewTable) e, quoteIdentifiers, qualifyNames);
            StringBuffer comment = new StringBuffer("");
            comment.append(COMMENT).append(SPACE).append(ON).append(SPACE).append(VIEW).append(SPACE)
                    .append(objectName);
            comment.append(SPACE).append(IS).append(SPACE).append(description);
            script.addAlterOtherStatements(comment.toString());
        }
    }
}
