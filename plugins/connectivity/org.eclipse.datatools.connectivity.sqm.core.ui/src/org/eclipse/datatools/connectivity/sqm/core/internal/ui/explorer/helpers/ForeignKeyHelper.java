/*******************************************************************************
 * Copyright (c) 2001, 2004, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.helpers;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.services.IColumnHelperService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.services.IForeignKeyHelperService;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EAnnotation;

/**
 * @author ljulien
 */
public class ForeignKeyHelper implements IForeignKeyHelperService
{
	private IColumnHelperService columnHelper = IDataToolsUIServiceManager.INSTANCE.getColumnHelperService();
	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.internal.ui.ui.services.IForeignKeyHelper#isIdentifyingConstraint(org.eclipse.sql.constraints.ForeignKey)
	 */
	public boolean isIdentifyingConstraint(ForeignKey constraint)
	{
        EAnnotation eAnnotation = constraint.getEAnnotation(RDBCorePlugin.FK_MODELING_RELATIONSHIP);
        if (eAnnotation != null)
        {
            return new Boolean ((String)eAnnotation.getDetails().get(RDBCorePlugin.FK_IS_IDENTIFYING_RELATIONSHIP)).booleanValue();
        }
        return true;
	}
	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.internal.ui.ui.services.IForeignKeyHelper#isNonIdentifyingConstraint(org.eclipse.sql.constraints.ForeignKey)
	 */
	public boolean isNonIdentifyingConstraint(ForeignKey constraint)
	{
		return !isIdentifyingConstraint(constraint);
	}

	public Table getTarget (ForeignKey fk)
    {
        UniqueConstraint constraint = fk.getUniqueConstraint();
        if (constraint != null)
        {
            return constraint.getBaseTable();
        }
        else if (fk.getUniqueIndex() != null)
        {
            return fk.getUniqueIndex().getTable();
        }
        else
        {
            return fk.getReferencedTable();
        }
    }
}
