/*******************************************************************************
 * Copyright (c) 2001, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.impl;

import java.text.MessageFormat;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.jface.viewers.IDecoration;


/**
 * @author ljulien
 */
public class ForeignKeyDecorationService extends AbstractDecorationService
{
	private static final String FOREIGN_KEY_NAME = " (-> {0})"; //$NON-NLS-1$
	private static final String INACTIVE_RELATIONSHIP = ResourceLoader.getResourceLoader().queryString("DATATOOLS.PROJECT.UI.INACTIVE_RELATIONSHIP"); //$NON-NLS-1$
	
	/**
	 * @return The formated Decorated Name for the Foreign key
	 */
	private String getForeignKeyDecoration(ForeignKey foreignKey)
	{
		String parentTableName = null;
		SQLObject uc = foreignKey.getUniqueConstraint();
		if(uc != null) 
		{
	         Table tbl = ((UniqueConstraint)uc).getBaseTable();
	         if(tbl != null) {
	             parentTableName = tbl.getName();
	         }
	         else {
	             parentTableName = "";
	         }
		}
		else if ((uc = foreignKey.getUniqueIndex()) != null) 
		{
			parentTableName = ((Index)uc).getTable().getName();
		}
		else 
		{
		    parentTableName = INACTIVE_RELATIONSHIP;	   
		}
		return MessageFormat.format(FOREIGN_KEY_NAME, new String[] {parentTableName});
	}

	/**
	 * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object, org.eclipse.jface.viewers.IDecoration)
	 */
	public void decorate(Object element, IDecoration decoration)
	{
		if (element instanceof ForeignKey)
		{
			decoration.addSuffix(getForeignKeyDecoration((ForeignKey)element));
		}
	}
}
