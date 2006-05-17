/*
 *************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.nls;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS 
{
	private static final String BUNDLE_NAME = "org.eclipse.datatools.connectivity.oda.consumer.nls.messages"; //$NON-NLS-1$

	private Messages() 
	{}

	static 
	{
		// initialize resource bundle
		NLS.initializeMessages( BUNDLE_NAME, Messages.class );
	}

	public static String helper_cannotGetNamedResultsAfterExecuteQuery;
	public static String helper_cannotSetParamBeforePrepare;
	public static String helper_cannotGetParamBeforeExecute;
	public static String helper_cannotGetParamMdBeforePrepare;
	public static String helper_cannotSetSortSpecBeforePrepare;
	public static String helper_maxConcurrentConnectionsReached;
	public static String helper_connectionFailed;
	public static String helper_cannotConstructConnectionFactory;
	public static String helper_inadequatePermissionsForCompatibility;
	public static String helper_connectionIsInactive;
	public static String helper_maxConcurrentStatementsReached;
	public static String helper_prepareFailed;
	public static String helper_cannotGetResultSetMdBeforePrepare;
	public static String helper_cannotExecuteBeforePrepare;
    public static String helper_missingDriverInfo;
    public static String helper_extension_mustImplementInterface;

}
