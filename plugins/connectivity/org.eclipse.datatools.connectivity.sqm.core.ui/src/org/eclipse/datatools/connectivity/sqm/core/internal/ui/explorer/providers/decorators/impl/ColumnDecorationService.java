/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.impl;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.IColumnDecorationService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.icons.ImageDescription;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.preferences.ColumnDecoratorUtil;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.jface.viewers.IDecoration;


/**
 * @author ljulien
 */
public class ColumnDecorationService extends AbstractDecorationService implements IColumnDecorationService
{
    private void decorate (Column column, IDecoration decoration)
	{
	    boolean pk = column.isPartOfPrimaryKey();
	    boolean fk = column.isPartOfForeignKey();
	    boolean nullable = column.isNullable();
	    String dataType = IDataToolsUIServiceManager.INSTANCE.getColumnHelperService().getDataType(column);
	    
		if (fk && pk)
		{
			decoration.addSuffix(ColumnDecoratorUtil.getPKFKColumnDecoration(dataType));
			decoration.addOverlay(ImageDescription.getPKFKDecorationDescriptor());
		}
		else if (fk && nullable)
		{
		    decoration.addSuffix(ColumnDecoratorUtil.getFKNullableColumnDecoration(dataType));
			decoration.addOverlay(ImageDescription.getFKDecorationDescriptor());
		}
		else if (fk)
		{
		    decoration.addSuffix(ColumnDecoratorUtil.getFKColumnDecoration(dataType));
			decoration.addOverlay(ImageDescription.getFKDecorationDescriptor());
		}
		else if (pk)
		{
		    decoration.addSuffix(ColumnDecoratorUtil.getPKColumnDecoration(dataType));
			decoration.addOverlay(ImageDescription.getPKDecorationDescriptor());
		}
		else if (nullable)
		{
		    decoration.addSuffix(ColumnDecoratorUtil.getNullableColumnDecoration(dataType));
		}
		else
		{
		    decoration.addSuffix(ColumnDecoratorUtil.getColumnDecoration(dataType));
		}
 	}
	
	/**
	 * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object, org.eclipse.jface.viewers.IDecoration)
	 */
	public void decorate(Object column, IDecoration decoration)
	{
	    if (column instanceof Column)
	    {
	        decorate ((Column)column, decoration);
	    }
	}
}
