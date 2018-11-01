/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.exceptions;

import java.sql.SQLException;

import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

public class DBNotStartException extends  java.lang.Exception {
	
	private String defaultMessage = null ;
	
	public DBNotStartException(String theReason, Throwable theCause){
		super(theReason,theCause);
	}
	public DBNotStartException(Throwable theCause){
		super(null,theCause);
	}


	public String getMessage() {
	  String message = super.getMessage();
	  if(message == null){
		  if((this.getCause() != null)&&(this.getCause() instanceof  SQLException)){
			  SQLException sqlE = (SQLException)this.getCause() ;
			  if(sqlE.getSQLState() != null){
				  message = this.getDefaultMessage(sqlE.getSQLState(),sqlE.getErrorCode()) ;
			  }else{
				  message = this.getCause().getMessage() ;
			  }
		  }
	  }
	  return message ;
	}
	
	private String getDefaultMessage(String sqlState , int errorCode){
		if(defaultMessage == null){
			this.defaultMessage = ConnectivityPlugin.getDefault().getResourceString(
					"error.database.not.start",
					new Object[] {String.valueOf(errorCode), sqlState});
		}
		return this.defaultMessage;
	}
}
