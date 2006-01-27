/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.services;

import java.util.Locale;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.ContainmentService;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;

import com.ibm.icu.text.Collator;

/**
 * @author ljulien
 */
public class ExplorerSorterProvider implements IExplorerSorterService
{
	private Collator collator = Collator.getInstance(Locale.getDefault());
	private ContainmentService containment = RDBCorePlugin.getDefault().getContainmentService();

	private boolean isValid (Object element1, Object element2)
	{
	    if (element1 instanceof SQLObject && element2 instanceof SQLObject)
	    {
	        return !(containment.getContainer((SQLObject)element1) instanceof Table) 
	        		&& !(containment.getContainer((SQLObject)element2) instanceof Table);
	    }
	    return true;
	}
	
    private String getName (Object object)
    {
        if (object instanceof SQLObject)
        {
            return ((SQLObject)object).getName();
        }
        else if (object instanceof IVirtualNode)
        {
            return ((IVirtualNode)object).getDisplayName();
        }
        else if (object instanceof IAdaptable)
        {
            Object resource = ((IAdaptable)object).getAdapter(IResource.class);
            if (resource != null)
            {
                return ((IResource)resource).getName();
            }
        }
        return null;
    }
	
    public int compare (Object element1, Object element2)
    {
        if (isValid (element1, element2))
        {
            String string1 = getName (element1);
            String string2 = getName (element2);
            return string1 != null && string2 != null ? collator.getCollationKey(string1).compareTo(collator.getCollationKey(string2)) : -1;
        }
        return -1;
    }
}
