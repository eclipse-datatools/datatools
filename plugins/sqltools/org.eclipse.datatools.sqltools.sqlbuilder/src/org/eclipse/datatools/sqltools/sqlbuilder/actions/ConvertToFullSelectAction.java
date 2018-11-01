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
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;


/**
 * Add a With table to the With Statement
 */
public class ConvertToFullSelectAction extends Action
{
  SQLQueryObject sourceObject;
  SQLDomainModel domainModel;
  

  public ConvertToFullSelectAction(SQLDomainModel domainModel)
  {
    super(Messages._UI_ACTION_CONVERT_TO_FULL_SELECT);
    this.domainModel = domainModel;
  }

  public void setElement(Object obj)
  {
    if (obj instanceof SQLQueryObject) {
    	sourceObject = ((SQLQueryObject)obj); 
    }
  }

  public void run()
  {
    convertToFullSelect();
  }

  /**
   * Converts the given QuerySelectStatement or QuerySelect to FullSelect
   */
  private void convertToFullSelect()
  {
  	if (sourceObject instanceof QuerySelectStatement) {
  		QuerySelectStatement selectStmt = (QuerySelectStatement)sourceObject ;
  		QuerySelect qSelect = SelectHelper.getQuerySelect((QuerySelectStatement)sourceObject) ;
  		if (qSelect != null) {
  	  	  	QueryCombined qCombined = StatementHelper.createQueryCombined() ;
  	  	  	QuerySelect tempQSelect = StatementHelper.createQuerySelect() ;
  			qCombined.setLeftQuery(qSelect) ;
  			qCombined.setRightQuery(tempQSelect) ;
  			selectStmt.getQueryExpr().setQuery(qCombined) ;
  		}
  	} else if (sourceObject instanceof QuerySelect && sourceObject.eContainer() instanceof WithTableSpecification) {
  	        QuerySelect sourceQSelect = (QuerySelect)sourceObject ;
  	        WithTableSpecification withTable = (WithTableSpecification)sourceObject.eContainer() ;
            QueryCombined qCombined = StatementHelper.createQueryCombined() ;
            QuerySelect tempQSelect = StatementHelper.createQuerySelect() ;
            qCombined.setLeftQuery(sourceQSelect) ;
            qCombined.setRightQuery(tempQSelect) ;
            withTable.setWithTableQueryExpr(qCombined) ;
    } else if (sourceObject instanceof QuerySelect) { //Queryselect passed only in the case of Fullselect  
  		QuerySelect sourceQSelect = (QuerySelect)sourceObject ;
  		QueryCombined parentQCombined = null ;
	  	QueryCombined qCombined = StatementHelper.createQueryCombined() ;
  	  	QuerySelect tempQSelect = StatementHelper.createQuerySelect() ;
  		
  	  	parentQCombined = sourceQSelect.getCombinedLeft() ;
  	  	//if parentQCombined is null we can assume that QSelect is on the right side of Query Combined.
  		if (parentQCombined == null) {
  			parentQCombined = sourceQSelect.getCombinedRight() ;
  			if (parentQCombined != null) {
  	  			qCombined.setLeftQuery(sourceQSelect) ;
  	  			qCombined.setRightQuery(tempQSelect) ;
  	  			parentQCombined.setRightQuery(qCombined) ;
  			}
  		}else {
	  			qCombined.setLeftQuery(sourceQSelect) ;
  	  			qCombined.setRightQuery(tempQSelect) ;
  	  			parentQCombined.setLeftQuery(qCombined) ;
  		}
  	}
  	
  	SelectHelper.refresh(sourceObject);
  }

  
}
