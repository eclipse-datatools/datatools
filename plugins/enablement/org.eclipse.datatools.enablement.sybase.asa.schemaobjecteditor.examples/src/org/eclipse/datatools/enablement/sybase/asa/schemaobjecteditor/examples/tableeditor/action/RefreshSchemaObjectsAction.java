/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.action;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.commonui.ListSchemaObjectsSection;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.Images;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.custom.BusyIndicator;



/**
 * Refresh the schema objects
 * 
 * @author Idull
 */
public class RefreshSchemaObjectsAction extends Action
{
    protected ListSchemaObjectsSection _sectionToBeRefreshed;
    protected ICatalogObject           _parentObject;

    public RefreshSchemaObjectsAction(ICatalogObject parentObj)
    {
        super();
        setImageDescriptor(Images.DESC_REFRESH);
        _parentObject = parentObj;
    }

    public void run()
    {
        super.run();
        BusyIndicator.showWhile(ExamplePlugin.getDisplay(), new Runnable()
        {
            public void run()
            {
                if (_parentObject != null && _sectionToBeRefreshed != null)
                {
                    _parentObject.refresh();
                    _sectionToBeRefreshed.refresh();
                }
            }
        });
    }

    public void setSectionToBeRefreshed(ListSchemaObjectsSection toBeRefreshed)
    {
        _sectionToBeRefreshed = toBeRefreshed;
    }
}
