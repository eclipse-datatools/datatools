package org.eclipse.datatools.connectivity.internal.ui.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
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
		Composite comp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		comp.setLayout(layout);

		Label descLabel = new Label(comp, SWT.NONE);
		descLabel.setText(Messages.Connectivity_description);

		return comp;
	}

	public void init(IWorkbench workbench) {
	}

}
