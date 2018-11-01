/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ase.internal.ui.connection.drivers;

import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;

public class AdditionalControlManager {

	private static AdditionalControlManager sInstance = new AdditionalControlManager();
	
	public static final String EXTENSION_ID = "org.eclipse.datatools.enablement.sybase.ase.additionalcontroller"; //$NON-NLS-1$
	
	public static final String EXT_ELEM_UAF_CONTROLLER = "additionalcontroller"; //$NON-NLS-1$
	
	public static final String ATTR_ID = "id"; //$NON-NLS-1$

	public static final String ATTR_CLASS = "class"; //$NON-NLS-1$
	
	private IConfigurationElement mElement;
	
	private String mId;

	private IAdditionalControl mAdditionalControl;
	
	public static AdditionalControlManager getInstance() {
		return sInstance;
	}
	
	private void processExtensions() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint exp = registry.getExtensionPoint(EXTENSION_ID);
		IExtension[] exts = exp.getExtensions();
		for (Iterator xit = Arrays.asList(exts).iterator(); xit.hasNext();) {
			IExtension ext = (IExtension) xit.next();
			IConfigurationElement[] elems = ext.getConfigurationElements();
			for (Iterator eit = Arrays.asList(elems).iterator(); eit.hasNext();) {
				IConfigurationElement elem = (IConfigurationElement) eit.next();
				String elemName = elem.getName();
				if (EXT_ELEM_UAF_CONTROLLER.equals(elemName)) {
					mElement = elem;
					mId = elem.getAttribute(ATTR_ID);
					break;
				}
			}
		}
		
		initClass();
	}
	
	private void initClass() {
		ISafeRunnable code = new ISafeRunnable() {

			public void run() throws Exception {
				if (mElement != null) {
					if (mElement.createExecutableExtension(ATTR_CLASS) instanceof IAdditionalControl)
						mAdditionalControl = (IAdditionalControl) mElement
								.createExecutableExtension(ATTR_CLASS);
				}
			}

			public void handleException(Throwable exception) {
				JDBCASEPlugin.getDefault().log(exception);
			}

		};
		SafeRunner.run(code);
	}

	public String getId() {
		if (mId == null)
		{
			processExtensions();
		}
		return mId;
	}
	
	public IAdditionalControl getAdditionalControl() {
		if (mAdditionalControl == null)
		{
			processExtensions();
		}
		return mAdditionalControl;
	}

	
}
