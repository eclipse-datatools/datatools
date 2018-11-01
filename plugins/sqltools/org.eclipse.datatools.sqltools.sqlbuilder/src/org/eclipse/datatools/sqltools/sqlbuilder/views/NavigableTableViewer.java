/*******************************************************************************
 * Copyright © 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.views;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Table;

public class NavigableTableViewer extends TableViewer
{
  public static final String copyright = "(c) Copyright IBM Corporation 2000, 2002."; //$NON-NLS-1$

   TableNavigator navigator;

   public NavigableTableViewer(Table parent)
   {
      super(parent);
      navigator = new TableNavigator(getTable(), this);
   }

   //override setCellEditors to put in call to moveAboveCellEditors for TableNavigator
   public void setCellEditors(CellEditor[] editors)
   {
     super.setCellEditors(editors);
     navigator.moveCellEditorsAbove(editors);

   }

   //override refresh so that TableNavigator is refreshed for all model changes
   public void refresh()
   {
   	if( !this.getTable().isDisposed() )
   	{
      	super.refresh();
      	navigator.refresh();
   	}
   }

/**
 * @return Returns the navigator.
 */
public TableNavigator getNavigator() {
	return navigator;
}
}
