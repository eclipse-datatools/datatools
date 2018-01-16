/*
 *************************************************************************
 * Copyright (c) 2007 <<Your Company Name here>>
 *  
 *************************************************************************
 */
package $packageName$;

import java.util.Properties;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard;

/**
 * Auto-generated implementation of a custom new profile wizard. 
 *
 * See the NewConnectionProfileWizard class in the 
 * org.eclipse.datatools.connectivity.db.generic.ui plug-in
 * as an example.
 * 
 * Implementers are expected to change this exemplary implementation 
 * as appropriate. 
 */
public class $newWizardClass$
		extends NewConnectionProfileWizard {

	public $newWizardClass$() {
	}

    /**
     * @see org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard#addCustomPages()
     */
	public void addCustomPages() {
		/*
		 * Add custom wizard pages here
		 */
	}

	/** 
     * @see org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard#getProfileProperties()
	 */
	public Properties getProfileProperties() {
		Properties props = new Properties();
		/*
		 * Set properties here
		 */
		return props;
	}
}