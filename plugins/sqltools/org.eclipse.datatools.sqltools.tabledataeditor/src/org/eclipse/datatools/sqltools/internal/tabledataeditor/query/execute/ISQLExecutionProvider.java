/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.tabledataeditor.query.execute;

import java.sql.Connection;
import java.sql.Statement;


public interface ISQLExecutionProvider 
{
  /* Returns a boolean which indicates whether this provider can be used 
   * to execute the SQL string passed in. */
  public boolean isProviderFor(String sqlStatementStr);

  /* Executes the SQL statement passed in on teh connection 
   * passed in.  The string must be a complete statement.  This 
   * method must only be called after the isProviderFor() method 
   * has returned true. Returns the JDBC statement that was 
   * created to execute the SQL statement passed in.  */
  public Statement execute(Connection con, String sqlStatementStr) throws Exception;
}
