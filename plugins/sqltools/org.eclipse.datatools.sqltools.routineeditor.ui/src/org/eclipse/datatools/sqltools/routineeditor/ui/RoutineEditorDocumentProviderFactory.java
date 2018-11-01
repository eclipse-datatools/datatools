/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IEditorInput;

/**
 * This class allows the document debuggerDocumentProvider to be extended by sql
 * debugger framework
 * 
 * @author Hui Cao
 * 
 */
public class RoutineEditorDocumentProviderFactory {

	private static final SQLRoutineDocumentProvider ROUTINE_DOCUMENT_PROVIDER = new SQLRoutineDocumentProvider();

	private static IRoutineEditorDocumentProvider debuggerDocumentProvider = null;

	/**
	 * Returns a document debuggerDocumentProvider that can be used by
	 * RoutineEditor
	 * 
	 * @param input
	 *            the editor input used to determine whether debugging is
	 *            supported
	 * @return SQLStorageDocumentProvider or the debugger plugin contributed
	 *         document debuggerDocumentProvider
	 */
	public static IRoutineEditorDocumentProvider getDocumentProvider(
			IEditorInput input) {
		// boolean supportsDebugging = false;
		// if (input instanceof ProcEditorInput)
		// {
		// ProcIdentifier procIdentifier =
		// ((ProcEditorInput)input).getProcIdentifier();
		// IControlConnection con = null;
		// try {
		// con =
		// EditorCorePlugin.getControlConnectionManager().getOrCreateControlConnection(procIdentifier.getDatabaseIdentifier());
		// supportsDebugging = con.supportsDebugging();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		//		
		// if (!supportsDebugging )
		// {
		// return ROUTINE_DOCUMENT_PROVIDER;
		// }

		if (debuggerDocumentProvider != null) {
			return debuggerDocumentProvider;
		}

		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint(
				RoutineEditorUIActivator.PLUGIN_ID, "documentprovider"); //$NON-NLS-1$ //$NON-NLS-2$
		if (extensionPoint != null) {
			IExtension[] extensions = extensionPoint.getExtensions();
			if (extensions.length > 0) {
				IConfigurationElement[] configElements = extensions[0]
						.getConfigurationElements();
				if (configElements[0].getName().equals(
						"provider")) {
					//$NON-NLS-1$
					// String id = configElements[0].getAttribute("id");
					// //$NON-NLS-1$
					String className = configElements[0].getAttribute("class");
					try {
						debuggerDocumentProvider = (IRoutineEditorDocumentProvider) configElements[0]
								.createExecutableExtension("class"); //$NON-NLS-1$
					} catch (CoreException e) {
						RoutineEditorUIActivator.getDefault().log(e);
					}

				}
			}
		}

		if (debuggerDocumentProvider == null) {
			debuggerDocumentProvider = ROUTINE_DOCUMENT_PROVIDER;
		}
		return debuggerDocumentProvider;
	}
}
