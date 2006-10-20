/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.tests.verification.hooks;

import java.lang.reflect.Method;
import java.util.Properties;

import org.eclipse.datatools.connectivity.drivers.IDriverMgmtConstants;
import org.eclipse.tptp.test.auto.gui.internal.runner.AutoGUIVerificationHook;

/**
 * Generated code for the test suite <b>SQLToolsGUISuite</b> located at
 * <i>/org.eclipse.datatools.sqltools.test/test-resources/SQLToolsGUISuite.testsuite</i>.
 *
 * DTP SQL Dev Tools Automatic GUI Test Suite
 * @author Hui Cao
 */
public class SQLToolsGUISuite extends AutoGUIVerificationHook {

	/**
	 * Constructor for SQLToolsGUISuite.  An instance of this class is created to
	 * test a test method.
	 *
	 * @param testMethodName The test method to be tested.
	 * @param paramTypes The parameter types of the test method
	 * @param args The arguments of the test method
	 */
	public SQLToolsGUISuite(String testMethodName, Class[] paramTypes,
			Object[] args) {
		super(testMethodName, paramTypes, args);
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
	}

	/**
	* Create a Derby connection profile (driver available)
	*  
	* @throws Exception
	*/
	public void verifyPing(org.eclipse.swt.widgets.Shell arg0)
	throws Exception
	{
		 /* Make sure the title is success */
		assertTrue(arg0 != null);
		  
	}

	/**
	 * Create a Derby driver
	 *  
	 * @throws Exception
	 */
	public void verifyPreferencesDialog(org.eclipse.swt.widgets.Shell arg0)
	throws Exception
	{
	}
	
	/**
	* Create a Derby driver
	*  
	* @throws Exception
	*/
	public void verifyDriverEntry(org.eclipse.swt.widgets.Shell arg0)
	throws Exception
	{
		Messages.log("verifyDriverEntry:" + arg0.getData());
		
		//get the dialog
		Object data = arg0.getData();
		//using reflection
		Method m = data.getClass().getMethod("getPropertySet", null);
		Object pset = m.invoke(data, null);
		m = pset.getClass().getMethod("getBaseProperties", null);
		Properties props = (Properties)m.invoke(pset, null);
		props.setProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST, Messages.Derby_driver_jar_list);
		Messages.log("init jar list to:" + Messages.Derby_driver_jar_list);
		//due to the classloading problem, we can't cast explicitly here
//		EditDriverDialog dlg = (EditDriverDialog)arg0.getData();
//		pset = dlg.getPropertySet();
//		pset.getBaseProperties().setProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST, Messages.Derby_driver_jar_list);
	}

	/**
	* Create a Derby Driver ( in DSE)
	* This verification point will alter the jar list for the derby driver
	*  
	* @throws Exception
	*/
	public void verifyDriverName(org.eclipse.swt.widgets.Shell arg0)
	throws Exception
	{
		
	}
	
	/**
	 * Create a Derby Driver ( modified while selected)
	 * This verification point will alter the jar list for the derby driver
	 *  
	 * @throws Exception
	 */
	public void verifyDriver(org.eclipse.swt.widgets.Shell arg0)
	throws Exception
	{
		Object data = arg0.getData();
		Messages.log("verifyDriver:" + data);
		
		Method m = data.getClass().getMethod("getSelectedDefinition", null);
		Object pset = m.invoke(data, null);
		m = pset.getClass().getMethod("getBaseProperties", null);
		Properties props = (Properties)m.invoke(pset, null);
		props.setProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST, Messages.Derby_driver_jar_list);
		Messages.log("set jar list to:" + Messages.Derby_driver_jar_list);
	}

	/**
	* SQL File Editor
	*  
	* @throws Exception
	*/
	public void verifyDatabase(org.eclipse.swt.widgets.Shell arg0)
	throws Exception
	{
		// TODO Auto-generated method stub.  Enter your code here
	}

	/**
	* SQL File Editor
	*  
	* @throws Exception
	*/
	public void verifyOutline(org.eclipse.ui.IEditorPart arg0)
	throws Exception
	{
		// TODO Auto-generated method stub.  Enter your code here
	}

	/**
	* SQL File Editor
	*  
	* @throws Exception
	*/
	public void veryfyResultFilter(org.eclipse.ui.IViewPart arg0)
	throws Exception
	{
		// TODO Auto-generated method stub.  Enter your code here
	}

	/**
	* SQL File Editor
	*  
	* @throws Exception
	*/
	public void verifyResultSearch(org.eclipse.ui.IViewPart arg0)
	throws Exception
	{
		// TODO Auto-generated method stub.  Enter your code here
	}

	/**
	* Invoke trigger
	*  
	* @throws Exception
	*/
	public void verifyTriggered(org.eclipse.ui.IViewPart arg0)
	throws Exception
	{
		// TODO Auto-generated method stub.  Enter your code here
	}
	
}
