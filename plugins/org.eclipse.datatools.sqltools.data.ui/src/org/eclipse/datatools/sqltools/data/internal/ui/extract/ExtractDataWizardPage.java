package org.eclipse.datatools.sqltools.data.internal.ui.extract;

import org.eclipse.datatools.sqltools.data.internal.ui.FileFormatWizardPage;

public class ExtractDataWizardPage extends FileFormatWizardPage {

    public ExtractDataWizardPage(String pageName)
    {
        super(pageName, true);
    }
	
	public String getHelpID() {
		return "org.eclipse.wst.rdb.data.ui.infopop.extract"; //$NON-NLS-1$
	}

}
