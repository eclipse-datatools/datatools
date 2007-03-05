package org.eclipse.datatools.enablement.sybase.asa.deltaddl;

import org.eclipse.datatools.enablement.sybase.asa.ddl.SybaseASADdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.deltaddl.PrivilegeDeltaDdlGenProvider;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;

/**
 * Privilege delta ddl generator provider for ASA
 * 
 * @author Idull
 */
public class ASAPrivilegeDeltaDdlGenProvider extends PrivilegeDeltaDdlGenProvider
{
    protected String[] generateGrantGOStatement(Privilege p, boolean quoteIdentifiers, boolean qualifyNames,
            boolean fullSyntax)
    {
        SybaseDdlBuilder ddlBuilder = (SybaseDdlBuilder)SybaseASADdlBuilder.getInstance();
        return ddlBuilder.grantPrivilege(p, quoteIdentifiers, qualifyNames, fullSyntax);
    }
}
