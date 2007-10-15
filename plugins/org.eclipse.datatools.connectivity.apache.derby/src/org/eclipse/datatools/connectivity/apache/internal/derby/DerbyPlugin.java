/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.apache.internal.derby;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.datatools.connectivity.apache.internal.derby.connection.DerbyShutdownService;
import org.osgi.framework.BundleContext;

public class DerbyPlugin extends Plugin
{
	private static DerbyPlugin plugin;
	
	protected DerbyShutdownService shutdownService;	

	/**
	 * The constructor.
	 */
	public DerbyPlugin()
	{
		super();
		plugin = this;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);		
		shutdownService = new DerbyShutdownService();
		File dir = getStateLocation().append("sample").toFile(); //$NON-NLS-1$
		if(!dir.exists()) {
			String location = getBundle().getLocation();
			location = location.substring(location.indexOf('@')+1);
			File f = new File(location);
			f = new File(f, "sample"); //$NON-NLS-1$
			try {
				copyDir(f, dir);
			}
			catch (Exception e) {
			}
		}
	}
	
    public void stop(BundleContext context) throws Exception {
        shutdownService.dispose();
        super.stop(context);
    }
	
	/**
	 * Returns the shared instance.
	 */
	public static DerbyPlugin getDefault()
	{
		return plugin;
	}

	private void copyFile(File source, File dest) throws IOException {
	     FileChannel in = null, out = null;
	     try {          
	          in = new FileInputStream(source).getChannel();
	          out = new FileOutputStream(dest).getChannel();

	          long size = in.size();
	          MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, 0, size);

	          out.write(buf);

	     } finally {
	          if (in != null) in.close();
	          if (out != null) out.close();
	     }
	}
	
	private void copyDir(File source, File dest) throws IOException {
		dest.mkdir();
		File[] files = source.listFiles();
		for(int i=0; i<files.length; ++i) {
			File file = files[i];
			File target = new File(dest, file.getName());
			if(file.isDirectory()) {
				copyDir(file, target);
			}
			else {
				copyFile(file, target);
			}
		}
	}	
}
