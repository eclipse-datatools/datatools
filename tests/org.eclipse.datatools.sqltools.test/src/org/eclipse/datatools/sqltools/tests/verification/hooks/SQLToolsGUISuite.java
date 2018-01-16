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

import java.util.Properties;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.services.SQLService;
import org.eclipse.datatools.sqltools.sqleditor.result.GroupSQLResultRunnable;
import org.eclipse.tptp.test.auto.gui.internal.runner.AutoGUIVerificationHook;

/**
 * Generated code for the test suite <b>SQLToolsGUISuite</b> located at
 * <i>/org.eclipse.datatools.sqltools.test/test-resources/SQLToolsGUISuite.testsuite</i>.
 * 
 * DTP SQL Dev Tools Automatic GUI Test Suite
 * 
 * @author Hui Cao
 */
public class SQLToolsGUISuite extends AutoGUIVerificationHook {


	/**
	 * Constructor for SQLToolsGUISuite. An instance of this class is created to
	 * test a test method.
	 * 
	 * @param testMethodName
	 *            The test method to be tested.
	 * @param paramTypes
	 *            The parameter types of the test method
	 * @param args
	 *            The arguments of the test method
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
	 * Create Driver and Profile
	 * 
	 * @throws Exception
	 */
	public void createDerbyDriverProfile(org.eclipse.ui.IViewPart arg0)
			throws Exception {
	}

	public void setUpDatabase(org.eclipse.ui.IViewPart arg0) {
		try {
			Properties props = new Properties();
			SQLToolsTestUtil.loadProps(SQLToolsTestUtil.class
					.getResource("props.properties"), props);
			// construct properties for profile
			SQLToolsTestUtil.createProfile(props);
			Messages.log("derby profile created");
			String sql = SQLToolsTestUtil.readFile(this.getClass().getResource(
					"setup.scripts"));

			String profileName = SQLToolsTestUtil.getProperties(props,
					"Profile");
			String databaseName = SQLToolsTestUtil.getProperties(props,
					"databasename");
			SQLDevToolsConfiguration f = SQLToolsFacade
					.getConfigurationByProfileName(profileName);

			String[] groups = new String[] { sql };
			SQLService sqlService = f.getSQLService();
			if (sqlService != null) {
				groups = sqlService.splitSQL(sql);
			}

			GroupSQLResultRunnable _job = new GroupSQLResultRunnable(null,
					groups, null, null, new DatabaseIdentifier(profileName,
							databaseName), false, null, "Prepare",
					"SQLToolsGUISuite");
			_job.setUser(true);
			_job.schedule();
			_job.join();

		} catch (Exception e) {
			Messages.log("derby profile creation failed" + e.getMessage());
			e.printStackTrace();
		}

	}

	public void tearDownDatabase(org.eclipse.ui.IViewPart arg0) {
		try {
			Properties props = new Properties();
			SQLToolsTestUtil.loadProps(SQLToolsTestUtil.class
					.getResource("props.properties"), props);
			// construct properties for profile
			String profileName = SQLToolsTestUtil.getProperties(props,
			"Profile");
			String databaseName = SQLToolsTestUtil.getProperties(props,
			"databasename");
			String sql = SQLToolsTestUtil.readFile(this.getClass().getResource(
					"teardown.scripts"));

			SQLDevToolsConfiguration f = SQLToolsFacade
					.getConfigurationByProfileName(profileName);

			String[] groups = new String[] { sql };
			SQLService sqlService = f.getSQLService();
			if (sqlService != null) {
				groups = sqlService.splitSQL(sql);
			}

			GroupSQLResultRunnable _job = new GroupSQLResultRunnable(null,
					groups, null, null, new DatabaseIdentifier(profileName,
							databaseName), false, null, "Tear Down",
					"SQLToolsGUISuite");
			_job.setUser(true);
			_job.schedule();
			_job.join();
			
			SQLToolsTestUtil.deleteProfile(profileName);
			Messages.log("derby profile deleted");


		} catch (Exception e) {
			Messages.log("derby profile deletion failed" + e.getMessage());
			e.printStackTrace();
		}
	
	}

}
