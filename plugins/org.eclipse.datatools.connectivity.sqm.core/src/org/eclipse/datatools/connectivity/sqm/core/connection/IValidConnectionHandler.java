package org.eclipse.datatools.connectivity.sqm.core.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.IConnectionProfile;

/**
 * Implementers ensure that the connection
 * is valid by performing a validation action
 * as defined for the data server and re-establishing
 * a connection if necessary
 *
 */
public interface IValidConnectionHandler
{
	/**
	 * Performs the validation and ensure that the underlying
	 * JDBC connection is good
	 * 
	 * It is highly recommended that implementors issue a very lightweight
	 * check, such as JDBC 4.0 isValid()
	 */
	public Connection ensureConnectivity(IConnectionProfile profile) throws SQLException;
}
