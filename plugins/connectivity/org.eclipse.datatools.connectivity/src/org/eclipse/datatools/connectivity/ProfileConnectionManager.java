/*******************************************************************************
 * Copyright (c) 2004-2011 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf, rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

/**
 * NON-API
 * 
 * A simple reference count based connection pool. This class manages the life
 * cycle of connections by handling <code>ConnectEvent</code>s through a
 * registered <code>IConnectListener</code>. Connections are only managed for
 * those profiles and connection factories registered with the manager by a
 * client (i.e. if no client has registered with the connection manager, no
 * connections are made).
 * 
 * @author brianf, rcernich
 */
public class ProfileConnectionManager {

	private static ProfileConnectionManager mConnectionManager = null;

	private HashMap mManagedProfileKeyToConnections;
	private HashMap mManagedProfileKeyToClientSet;
	private HashMap mProfileToListenerSet;
	private HashMap mFactoryIDToListener;
	private MyProfileListener listener = null;

	public ProfileConnectionManager() {
		// empty
		mManagedProfileKeyToConnections = new HashMap();
		mManagedProfileKeyToClientSet = new HashMap();
		mProfileToListenerSet = new HashMap();
		mFactoryIDToListener = new HashMap();
		listener = new MyProfileListener();
		ProfileManager.getInstance().addProfileListener(listener);
	}

	public static ProfileConnectionManager getProfileConnectionManagerInstance() {
		if (mConnectionManager == null)
			mConnectionManager = new ProfileConnectionManager();
		return mConnectionManager;
	}

	/**
	 * If the client is an instance of IConnectListener, it will be notified of
	 * changes to the connect status of the profile. The manager will notify
	 * each client after it has processed the event itself (i.e. the client will
	 * be notified after the connection has been opened or closed). For the
	 * okToClose event, each client is notified until one client returns false.
	 * At that point, no other clients are notified of the attempt to close.
	 * 
	 * @param profile
	 * @param factoryID
	 * @param client
	 */
	public void manageProfileConnection(IConnectionProfile profile,
			String factoryID, Object client) {
		ManagedProfileKey key = new ManagedProfileKey(profile, factoryID);
		if (!mManagedProfileKeyToConnections.containsKey(key)) {
			IConnection con = createConnection(profile, factoryID);

			mManagedProfileKeyToConnections.put(key, con);
			mManagedProfileKeyToClientSet.put(key, new HashSet());

			IConnectListener listener = (IConnectListener) mFactoryIDToListener
					.get(factoryID);
			if (listener == null) {
				listener = new ProfileConnectListener(factoryID);
				mFactoryIDToListener.put(factoryID, listener);
			}

			Set listeners = (Set) mProfileToListenerSet.get(profile);
			if (listeners == null) {
				listeners = new HashSet();
				mProfileToListenerSet.put(profile, listeners);
			}
			listeners.add(listener);
			profile.addConnectListener(listener);
		}
		((Set) mManagedProfileKeyToClientSet.get(key)).add(client);
	}

	public void unmanageProfileConnection(IConnectionProfile profile,
			String factoryID, Object client) {
		ManagedProfileKey key = new ManagedProfileKey(profile, factoryID);
		if (mManagedProfileKeyToConnections.containsKey(key)) {
			Set clientSet = (Set) mManagedProfileKeyToClientSet.get(key);
			clientSet.remove(client);
			if (clientSet.size() == 0) {
				IConnection con = (IConnection) mManagedProfileKeyToConnections
						.get(key);
				if (con != null) {
					try {
						con.close();
					}
					catch (Throwable e) {
					}
				}
				mManagedProfileKeyToConnections.remove(key);
				mManagedProfileKeyToClientSet.remove(key);

				IConnectListener listener = (IConnectListener) mFactoryIDToListener
						.get(factoryID);
				profile.removeConnectListener(listener);
				((Set) mProfileToListenerSet.get(profile)).remove(listener);
			}
		}
	}

	public IConnection getConnection(IConnectionProfile profile,
			String factoryID) {
		ManagedProfileKey key = new ManagedProfileKey(profile, factoryID);
		if (mManagedProfileKeyToConnections.containsKey(key)) {
			return (IConnection) mManagedProfileKeyToConnections.get(key);
		}
		return null;
	}

	private class MyProfileListener implements IProfileListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.datatools.connectivity.IProfileListener#profileAdded(org.eclipse.datatools.connectivity.IConnectionProfile)
		 */
		public void profileAdded(IConnectionProfile profile) {
			// do nothing here
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.datatools.connectivity.IProfileListener#profileDeleted(org.eclipse.datatools.connectivity.IConnectionProfile)
		 */
		public void profileDeleted(IConnectionProfile profile) {
			if (!mProfileToListenerSet.containsKey(profile)) {
				return;
			}
			for (Iterator it = ((Set) mProfileToListenerSet.get(profile))
					.iterator(); it.hasNext();) {
				ProfileConnectListener listener = (ProfileConnectListener) it
						.next();
				ManagedProfileKey key = new ManagedProfileKey(profile, listener
						.getFactoryID());

				profile.removeConnectListener(listener);

				if (mManagedProfileKeyToConnections.containsKey(profile)) {
					IConnection con = (IConnection) mManagedProfileKeyToConnections
							.get(key);
					if (con != null) {
						try {
							con.close();
						}
						catch (Throwable e) {
						}
					}
				}
				mManagedProfileKeyToConnections.remove(key);
				mManagedProfileKeyToClientSet.remove(key);
			}
			mProfileToListenerSet.remove(profile);

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.datatools.connectivity.IProfileListener#profileChanged(org.eclipse.datatools.connectivity.IConnectionProfile)
		 */
		public void profileChanged(IConnectionProfile profile) {
			if (mManagedProfileKeyToConnections.containsKey(profile)) {
				// RJC: Do we need to reconnect here?
			}
		}

	}

	/**
	 * @param icp
	 * @return
	 */
	private IConnection createConnection(IConnectionProfile icp,
			String factoryID) {
		IConnection con = null;
		if (icp.getConnectionState() == IConnectionProfile.CONNECTED_STATE) {
			con = icp.createConnection(factoryID);
		}
		return con;
	}

	private class ProfileConnectListener extends ConnectAdapter {

		private String mFactoryID;

		public ProfileConnectListener(String factoryID) {
			mFactoryID = factoryID;
		}

		public String getFactoryID() {
			return mFactoryID;
		}

		public boolean okToClose(ConnectEvent event) {
			ManagedProfileKey key = new ManagedProfileKey(event
					.getConnectionProfile(), mFactoryID);
			if (mManagedProfileKeyToClientSet.containsKey(key)) {
				for (Iterator it = ((Set) mManagedProfileKeyToClientSet
						.get(key)).iterator(); it.hasNext();) {
					Object obj = it.next();
					if (obj instanceof IConnectListener) {
						if (!((IConnectListener) obj).okToClose(event)) {
							return false;
						}
					}
				}
			}
			return true;
		}

		public void closeConnection(ConnectEvent event) throws CoreException {
			ManagedProfileKey key = new ManagedProfileKey(event
					.getConnectionProfile(), mFactoryID);
			if (mManagedProfileKeyToConnections.containsKey(key)) {
				// Notify any clients that the connection will be closed
				for (Iterator it = ((Set) mManagedProfileKeyToClientSet
						.get(key)).iterator(); it.hasNext();) {
					Object obj = it.next();
					if (obj instanceof IConnectListener) {
						try {
							((IConnectListener) obj).closeConnection(event);
						}
						catch (CoreException e) {
							// Ignore this
						}
					}
				}

				IConnection con = (IConnection) mManagedProfileKeyToConnections
						.get(key);
				if (con != null) {
					try {
						con.close();
					}
					catch (Throwable e) {
					}
					mManagedProfileKeyToConnections.put(key, null);
				}
			}
		}

		public void openConnection(ConnectEvent event) throws CoreException {
			ManagedProfileKey key = new ManagedProfileKey(event
					.getConnectionProfile(), mFactoryID);
			if (mManagedProfileKeyToConnections.containsKey(key)) {
				IConnection con = (IConnection) mManagedProfileKeyToConnections
						.get(key);
				if (con == null || con.getConnectException() != null) {
					con = event.getConnectionProfile().createConnection(
							mFactoryID);
					if (con.getConnectException() == null) {
						mManagedProfileKeyToConnections.put(key, con);
					}
					else {
						throw new CoreException(new Status(IStatus.ERROR,
								ConnectivityPlugin.getSymbolicName(),
								ConnectivityPlugin.INTERNAL_ERROR,
								ConnectivityPlugin.getDefault()
										.getResourceString(
												"error.connect", //$NON-NLS-1$
												new String[] { event
														.getConnectionProfile()
														.getName()}), con
										.getConnectException()));
					}
				}

				// Notify any clients that the connection has been closed
				for (Iterator it = ((Set) mManagedProfileKeyToClientSet
						.get(key)).iterator(); it.hasNext();) {
					Object obj = it.next();
					if (obj instanceof IConnectListener) {
						try {
							((IConnectListener) obj).openConnection(event);
						}
						catch (CoreException e) {
							// Ignore this
						}
					}
				}
			}
		}
	}

	private static class ManagedProfileKey {

		private IConnectionProfile mProfile;
		private String mFactoryID;

		public ManagedProfileKey(IConnectionProfile profile, String factoryID) {
			mProfile = profile;
			mFactoryID = factoryID;
		}

		public IConnectionProfile getConnectionProfile() {
			return mProfile;
		}

		public String getFactoryID() {
			return mFactoryID;
		}

		public boolean equals(Object object) {
			if (object instanceof ManagedProfileKey) {
				ManagedProfileKey other = (ManagedProfileKey) object;
				return mProfile.equals(other.mProfile)
						&& mFactoryID.equals(other.mFactoryID);
			}
			return false;
		}

		public int hashCode() {
			// not the best, but usable
			return mProfile.hashCode();
		}
	}
}