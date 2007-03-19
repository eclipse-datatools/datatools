package org.eclipse.datatools.enablement.sybase.asa.deltaddl;

import org.eclipse.datatools.enablement.sybase.IGenericDdlConstants;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlConstants;
import org.eclipse.datatools.enablement.sybase.deltaddl.ReferenceConstraintDeltaDdlGenProvider;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.tables.Table;

/**
 * Delta ddl gen provider for ASA reference constraint
 *
 * @author Idull
 */
public class ASAReferenceConstraintDeltaDdlGenProvider extends ReferenceConstraintDeltaDdlGenProvider
{
    protected String generateRenameConstraintStatement(Constraint constraint, String oldname, String newname,
            boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax)
    {
        StringBuffer sb = new StringBuffer(""); //$NON-NLS-1$
        sb.append(ALTER).append(SPACE).append(TABLE).append(SPACE).append(
                (getName((Table) constraint.eContainer(), quoteIdentifiers, qualifyNames))).append(SPACE).append(
                IGenericDdlConstants.RENAME).append(SPACE).append(
                quoteIdentifiers ? getDoubleQuotedString(oldname) : oldname).append(SPACE).append(
                ISybaseDdlConstants.TO).append(SPACE).append(
                quoteIdentifiers ? getDoubleQuotedString(newname) : newname);
        return sb.toString();
    }
}
