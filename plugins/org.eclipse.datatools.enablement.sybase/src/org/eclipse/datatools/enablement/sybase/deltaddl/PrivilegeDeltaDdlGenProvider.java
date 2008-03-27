package org.eclipse.datatools.enablement.sybase.deltaddl;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Delta ddl provider for privilege.
 * 
 * @author Idull
 */
public class PrivilegeDeltaDdlGenProvider extends AbstractDeltaDdlGenProvider
{
    public void processCreateStatement(SQLObject element, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax, SybaseDdlScript script, ISybaseDdlGenerator generator, IProgressMonitor monitor)
    {
        if (!(element instanceof Privilege))
        {
            return;
        }
        Privilege p = (Privilege) element;
        SybaseDdlBuilder ddlBuilder = ((SybaseDdlGenerator) generator).getSybaseDdlBuilder();
        String[] statement = ddlBuilder.grantPrivilege(p, quoteIdentifiers, qualifyNames, fullSyntax);

        for(int i = 0; i<statement.length; i++)
        {
            addCreateStatement(script, statement[i]);
        }
    }

    
    public void processDropStatement(SQLObject element, boolean quoteIdentifiers, boolean qualifyNames,
            SybaseDdlScript script, DDLGenerator generator, IProgressMonitor monitor)
    {
        if (!(element instanceof Privilege))
        {
            return;
        }
        Privilege p = (Privilege) element;
        SybaseDdlBuilder ddlBuilder = ((SybaseDdlGenerator) generator).getSybaseDdlBuilder();
        try
        {
            String statement = ddlBuilder.revokePrivilege(p, quoteIdentifiers, qualifyNames);
            if (statement == null && statement.trim().length() == 0)
            {
                return;
            }
            addDropStatement(script, statement);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            /**
             * On the safe side, we catch the exception from ddl layer
             */
        }
    }

    
    protected void addCreateStatement(SybaseDdlScript script, String statement)
    {
        script.addGrantPrivilegeStatement(statement);
    }

    
    protected void addDropStatement(SybaseDdlScript script, String statement)
    {
        script.addRevokePrivilegeStatement(statement);
    }

    
    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        if (!(e instanceof Privilege))
        {
            return;
        }
        Privilege p = (Privilege) e;
        if (feature.getFeatureID() == SQLAccessControlPackage.PRIVILEGE__GRANTABLE)
        {
            Assert.isTrue(oldValue instanceof Boolean);
            Assert.isTrue(newValue instanceof Boolean);

            // revoke grant option
            if (((Boolean) oldValue).booleanValue())
            {
                script.addRevokePrivilegeStatement(generateRevokeGOStatement(p, quoteIdentifiers, qualifyNames,
                        fullSyntax));
            }
            // grant grant option
            else if (!((Boolean) oldValue).booleanValue())
            {
                String[] grantScripts = generateGrantGOStatement(p, quoteIdentifiers, qualifyNames, fullSyntax);
                for(int i = 0; i<grantScripts.length; i++)
                    script.addGrantPrivilegeStatement(grantScripts[i]);
            }
        }
    }

    /**
     * Generates the statement to grant the grant option.
     * 
     * @param p
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @return
     */
    protected String[] generateGrantGOStatement(Privilege p, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        return new String[]{""};
    }

    /**
     * Generates the statement to revoke the grant option. Subclass needs to override this method if it support revoking
     * grant option.
     * 
     * @param p
     * @param quoteIdentifiers
     * @param qualifyNames
     * @param fullSyntax
     * @return
     */
    protected String generateRevokeGOStatement(Privilege p, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        return "";
    }
}
