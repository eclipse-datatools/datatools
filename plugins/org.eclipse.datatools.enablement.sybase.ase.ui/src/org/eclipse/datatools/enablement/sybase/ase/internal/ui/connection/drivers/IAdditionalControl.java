/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ase.internal.ui.connection.drivers;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public interface IAdditionalControl {
	
	public void setAgentPluginID(String agentPluginID);
	
	public void createControllerContents(Composite parent);
	
	public void assignServerText(Text host, Text port);
	
}
