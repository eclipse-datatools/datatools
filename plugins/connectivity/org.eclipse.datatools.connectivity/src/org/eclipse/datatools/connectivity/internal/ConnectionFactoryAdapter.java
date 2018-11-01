/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import java.util.HashMap;
import java.util.Map;

public class ConnectionFactoryAdapter {

	private ConnectionFactoryAdapterProvider mDefaultFactoryAdapter;
	private Map mFactoryAdapterOverrides;
	private final String mId;
	private final String mFactoryId;

	public ConnectionFactoryAdapter(String id, String factoryId) {
		super();
		mId = id;
		mFactoryId = factoryId;
		mFactoryAdapterOverrides = new HashMap();
	}

	public String getId() {
		return mId;
	}

	public String getFactoryId() {
		return mFactoryId;
	}

	public ConnectionFactoryAdapterProvider getDefault() {
		return mDefaultFactoryAdapter;
	}

	public ConnectionFactoryAdapterProvider getOverride(String profileId) {
		if (mFactoryAdapterOverrides.containsKey(profileId)) {
			return (ConnectionFactoryAdapterProvider) mFactoryAdapterOverrides
					.get(profileId);
		}
		return null;
	}

	public void addAdapter(ConnectionFactoryAdapterProvider adapter) {
		if (adapter.isDefault()) {
			if (mDefaultFactoryAdapter == null) {
				mDefaultFactoryAdapter = adapter;
			}
			else {
				if (ConnectionProfileManager.DEBUG_CONNECTION_PROFILE_EXTENSION) {
					System.err
							.println(ConnectivityPlugin
									.getDefault()
									.getResourceString(
											"trace.error.multipleDefaultConnectionAdapters", //$NON-NLS-1$
											new Object[] { mId,
													mFactoryId}));
				}
			}
		}
		else {
			if (mFactoryAdapterOverrides.containsKey(adapter.getProfileOverride())) {
				if (ConnectionProfileManager.DEBUG_CONNECTION_PROFILE_EXTENSION) {
					System.err
							.println(ConnectivityPlugin
									.getDefault()
									.getResourceString(
											"trace.error.multipleConnectionAdapterOverrides", //$NON-NLS-1$
											new Object[] {
													mId,
													mFactoryId,
													adapter
															.getProfileOverride()}));
				}
			}
			else {
				mFactoryAdapterOverrides.put(adapter.getProfileOverride(), adapter);
			}
		}
	}

}
