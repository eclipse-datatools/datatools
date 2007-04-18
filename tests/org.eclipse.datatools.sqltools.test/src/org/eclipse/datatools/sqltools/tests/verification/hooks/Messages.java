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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.tests.verification.hooks.messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String driver_name;
	public static String driver_jar_list;
	public static String Log_file_location;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	public static void log(String msg)
	{
		File file = new File(Log_file_location);
		try {
			Writer writer = new FileWriter(file, true);
			writer.write(Calendar.getInstance().getTime().toString() + ":" +  msg + "\r\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}