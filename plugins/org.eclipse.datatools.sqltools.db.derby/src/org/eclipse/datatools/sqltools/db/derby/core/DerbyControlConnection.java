/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.db.derby.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.IControlConnectionManager;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.dbitem.IDBItem;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.editor.contentassist.ContentAssistQueryRequest;
import org.eclipse.datatools.sqltools.editor.contentassist.model.DBObject;
import org.eclipse.datatools.sqltools.editor.contentassist.model.IDatatype;
import org.eclipse.datatools.sqltools.internal.core.AbstractControlConnection;

/**
 * @author Hui Cao
 *
 */
public class DerbyControlConnection extends AbstractControlConnection  implements IControlConnection
{

    //  the following two field are used together to cache last prepared statement of a db
    private PreparedStatement _getSourceStmt   = null;
    private String            _lastDbName      = null;


    /**
     * @param sd
     * @throws SQLException
     * @throws NoSuchProfileException
     */
    public DerbyControlConnection(IControlConnectionManager manager, DatabaseIdentifier databaseIdentifier) 
    {
        super(manager, databaseIdentifier);
    }


	protected IDBItem createDBItem(ProcIdentifier proc) {
		return null;
	}


	protected IDatatype getUserDataType(String typeName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	public void profileRenamed(String profileName) {
		// TODO Auto-generated method stub
		
	}


	public ProcIdentifier[] getAllProcs() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	public DBObject[] getContentAssistInfo(ContentAssistQueryRequest request) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	public void createRoutine(String sql) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void registerInternalConn(String connid) {
		// TODO Auto-generated method stub
		
	}


	public void unregisterInternalConn(String connid) {
		// TODO Auto-generated method stub
		
	}

}
