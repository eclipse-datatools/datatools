/*
 * Created on Feb 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.datatools.modelbase.sql.query.util;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;

/**
 * A <code>SQLSourceWriter</code> provides a generic <code>getSQL()</code>
 * method to generate the SQL source of a given <code>queryObject</code>.
 * {@link org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceWriter} provides the
 * base implementation of this interface, implementing the <code>getSQL()</code>
 * method for the Package <code>com.ibm.db.models.sql.query</code>.
 * 
 * @author ckadner
 */
public interface SQLSourceWriter
{
    public String getSQL(SQLQueryObject queryObject);
}