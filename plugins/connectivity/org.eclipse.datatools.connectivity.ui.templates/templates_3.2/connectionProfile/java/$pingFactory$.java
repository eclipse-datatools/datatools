/*
 *************************************************************************
 * Copyright (c) 2007 <<Your Company Name here>>
 *  
 *************************************************************************
 */
package $packageName$;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionFactory;
import org.eclipse.datatools.connectivity.IConnectionProfile;

/**
 * Auto-generated implementation of a custom ping connection factory
 * that provides a custom IConnection instance. 
 * 
 * <code>org.eclipse.datatools.connectivity.IConnectionFactory</code> for
 * creating <code>org.eclipse.datatools.connectivity.IConnection</code> connections.
 * <br>
 * Implementers are expected to change this exemplary implementation 
 * as appropriate. 
 */
public class $pingFactory$ implements IConnectionFactory {

	/**
	 * 
	 */
	public $pingFactory$() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactory#createConnection(org.eclipse.datatools.connectivity.IConnectionProfile)
	 */
	public IConnection createConnection(IConnectionProfile profile) {
		// Must implement an IConnection class or base it on an existing 
		// connection class such as JDBCConnection 
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactory#createConnection(org.eclipse.datatools.connectivity.IConnectionProfile,
	 *      java.lang.String, java.lang.String)
	 */
	public IConnection createConnection(IConnectionProfile profile, String uid,
			String pwd) {
		return createConnection(profile);
	}

}
