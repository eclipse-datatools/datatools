/*******************************************************************************
 * Copyright (c) 2009, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw;

import org.eclipse.datatools.connectivity.drivers.IDriverValuesProvider;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.enablement.ibm.util.ClientUtil;

public class DB2LUWJDBC4DriverValuesProvider implements IDriverValuesProvider{
	private TemplateDescriptor  templateDescriptor = null;
	
	public String createDefaultValue(String key) {
		String newKeyValue = null;
		if (key.equals(VALUE_JARLIST)) { 
			newKeyValue = locateDB2DriverJars();
		}		
		return newKeyValue;
	}

	public TemplateDescriptor getDriverTemplate() {
		return templateDescriptor;
	}

	public void setDriverTemplate(TemplateDescriptor templateDescriptor) {
		this.templateDescriptor = templateDescriptor;
	}
	
	private String locateDB2DriverJars(){
		String jarList = null;
		String path = ClientUtil.getDB2UniversalDriverJDBC4ClientJarsPath();
		if ((path != null) && (path.length() > 0)){
			jarList = path;
		}
		return jarList;
	}
}
