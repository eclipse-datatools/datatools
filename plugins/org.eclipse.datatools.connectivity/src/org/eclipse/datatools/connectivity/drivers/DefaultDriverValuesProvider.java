package org.eclipse.datatools.connectivity.drivers;

import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;

public class DefaultDriverValuesProvider implements IDriverValuesProvider {

	private TemplateDescriptor mTemplate = null;
	
	public String createDefaultValue(String key) {
		return null;
	}

	public void setDriverTemplate(TemplateDescriptor template) {
		this.mTemplate = template;
	}

	public TemplateDescriptor getDriverTemplate() {
		return this.mTemplate;
	}

}
