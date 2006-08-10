package org.eclipse.datatools.connectivity.internal.ui.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class DataToolsMainPage extends PreferencePage implements
		IWorkbenchPreferencePage {

	public DataToolsMainPage() {
		super();
		noDefaultAndApplyButton();
	}

	public DataToolsMainPage(String title) {
		super(title);
	}

	public DataToolsMainPage(String title, ImageDescriptor image) {
		super(title, image);
	}

	protected Control createContents(Composite parent) {
		return null;
	}

	public void init(IWorkbench workbench) {
	}

}
