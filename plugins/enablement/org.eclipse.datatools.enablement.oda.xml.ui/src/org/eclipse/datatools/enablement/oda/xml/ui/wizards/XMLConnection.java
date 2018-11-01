/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import org.eclipse.datatools.enablement.oda.xml.impl.Connection;

/**
 * encapsulation of all information about query.
 */


public class XMLConnection
{
   private static final String EMPTY_STRING = "";   //$NON-NLS-1$
    //conncetion instantce
	private Connection conn;
	//query info
	private String relationInfo;
	//xml file inof
	private String fileInfo;
	//query max row
	private int maxRow = 0;
	
	/**
	 * get xml connection
	 * @return
	 */
	Connection getConnection()
	{
		if( conn== null )
		{
			conn = new Connection();
		}
		return conn;	
	}
	
	/**
	 * set relation information
	 * @param info
	 */
	void setRelationInformation(String info)
	{
		relationInfo = info;
	}
	
	/**
	 * get relation information
	 * @return
	 */
	String getRelationInformation( )
	{
		return relationInfo == null ? EMPTY_STRING : relationInfo; 
	}
	
	/**
	 * set file list information
	 * @param info
	 */
	void setFileListInformation(String info)
	{
		fileInfo = info;
	}
	
	/**
	 * get file list information
	 * @return
	 */
	String getFileListInformation( )
	{
		return fileInfo;
	}
	
	/**
	 * set max row number
	 * @param maxRowNum
	 */
	void setMaxRowNumber( int maxRowNum )
	{
	    this.maxRow = maxRowNum;
	}
	
	/**
	 * get max row number
	 * @return
	 */
	int getMaxRowNumber()
	{
		return this.maxRow;
	}
	
}
