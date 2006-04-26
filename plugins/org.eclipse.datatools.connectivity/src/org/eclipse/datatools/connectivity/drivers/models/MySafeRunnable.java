package org.eclipse.datatools.connectivity.drivers.models;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

public class MySafeRunnable implements ISafeRunnable {

	private TemplateDescriptor[] mInstances = null;
	private CategoryDescriptor[] mCatInstances = null;
	private IConfigurationElement mElement = null;
	
	public MySafeRunnable ( CategoryDescriptor[] instance, IConfigurationElement element ) {
		this.mCatInstances = instance;
		this.mElement = element;
	}
	
	public MySafeRunnable ( TemplateDescriptor[] instance, IConfigurationElement element ) {
		this.mInstances = instance;
		this.mElement = element;
	}

	public void handleException(Throwable exception) {
		ConnectivityPlugin.getDefault().log(exception);
	}

	public void run() throws Exception {
		if (this.mInstances != null)
			this.mInstances[0] = new TemplateDescriptor(this.mElement);
		else if (this.mCatInstances != null)
			this.mCatInstances[0] = new CategoryDescriptor(this.mElement);
	}

}
