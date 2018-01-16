package org.eclipse.datatools.enablement.ase.deltaddl;

import org.eclipse.core.runtime.Assert;
import org.eclipse.datatools.enablement.ase.ISybaseASEDdlConstants;
import org.eclipse.datatools.enablement.ase.ddl.SybaseASEDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.PrivilegeDeltaDdlGenProvider;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Privilege delta ddl provider for ASE
 * 
 * @author Idull
 */
public class ASEPrivilegeDeltaDdlGenProvider extends PrivilegeDeltaDdlGenProvider implements ISybaseASEDdlConstants
{
    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        super.getModificationResult(e, feature, oldValue, newValue, quoteIdentifiers, qualifyNames, fullSyntax, script);
        Privilege p = (Privilege) e;
        if (feature.getFeatureID() == SybaseasesqlmodelPackage.SYBASE_ASE_PRIVILEGE__REVOKED)
        {
            Assert.isTrue(oldValue instanceof Boolean);
            Assert.isTrue(newValue instanceof Boolean);

            //TODO grant revoke inherited privileges
            if (((Boolean) oldValue).booleanValue()&&(!p.isGrantable()))
            {
                SybaseASEDdlBuilder builder = (SybaseASEDdlBuilder) SybaseASEDdlBuilder.getInstance();
                String[] statement = builder.grantPrivilege(p, quoteIdentifiers, qualifyNames, fullSyntax);
                for(int i = 0; i<statement.length; i++)
                script.addGrantPrivilegeStatement(statement[i]);
            }
            
        }
    }

    
    protected String[] generateGrantGOStatement(Privilege p, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        SybaseDdlBuilder ddlBuilder = (SybaseDdlBuilder)SybaseASEDdlBuilder.getInstance();
        return ddlBuilder.grantPrivilege(p, quoteIdentifiers, qualifyNames, fullSyntax);
    }

    
    protected String generateRevokeGOStatement(Privilege p, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        StringBuffer sb = new StringBuffer("");
        sb.append(REVOKE).append(SPACE).append(GO_FOR);

        SybaseDdlBuilder ddlBuilder = (SybaseDdlBuilder)SybaseASEDdlBuilder.getInstance();
        String revokeStat = ddlBuilder.revokePrivilege(p, quoteIdentifiers, qualifyNames);
        revokeStat = revokeStat.substring(REVOKE.length());
        sb.append(revokeStat);
        return sb.toString();
    }

}
