/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import org.eclipse.jface.action.Action;

import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelFactoryImpl;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;


public class AddSubSelectAction extends Action
{
  SQLDomainModel domainModel;
  QueryCombined queryCombined;

  public AddSubSelectAction(SQLDomainModel model)
  {
    super(Messages._UI_ACTION_ADD_SELECT);
    domainModel = model;
  }

  public void setElement(Object container)
  {
  	queryCombined = (QueryCombined)container;
  }

 
  public void run()      
  {
    //String titleText = Messages._UI_RSC_ACTION_ADD_SELECT");
    QuerySelect select = SQLQueryModelFactoryImpl.eINSTANCE.createQuerySelect();
    SelectHelper.setChildForQueryCombined(queryCombined,select);
  }
    
  
}
