/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf & mdow - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.ftp.internal;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPListParseEngine;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author mdow and brianf
 */
public class FtpContentProvider implements ITreeContentProvider,
		IAdaptable {

	/**
	 * Constructor
	 */
	public FtpContentProvider() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parent) {
		try {
			if (parent instanceof FTPClientObject) {
				FTPClient ftpClient = ((FTPClientObject) parent).getFtpClient();
				if (ftpClient.isConnected()) {
					FTPListParseEngine engine = ftpClient.initiateListParsing();
					FTPFile[] files = engine.getFiles();
					return FTPFileObject.convert(parent, ((FTPClientObject) parent).getProfile(), files);
				}
			} else if (parent instanceof FTPFileObject) {
				FTPFile ftpFile = ((FTPFileObject) parent).getFTPFile();
				FTPClient ftpClient = getFTPClient(parent);
				if (ftpFile.isDirectory() && ftpClient.isConnected()) {
					FTPListParseEngine engine = ftpClient
							.initiateListParsing(getDirectory((FTPFileObject) parent));
					FTPFile[] files = engine.getFiles();
					return FTPFileObject.convert(parent, ((FTPFileObject) parent).getProfile(), files);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			FTPClient ftpClient = getFTPClient(parent);
			try {
				if (ftpClient != null)
					ftpClient.disconnect();
			} catch (Exception ex) {
			}
		}
		return new Object[0];
	}

	private String getDirectory(FTPFileObject file) {
		StringBuffer sb = new StringBuffer();
		FTPFileObject fileobj = file;
		Object obj;
		FTPFile ftpFile;
		while (fileobj != null) {
			ftpFile = fileobj.getFTPFile();
			sb.insert(0, ftpFile.getName());
			sb.insert(0, "/");
			obj = getParent(fileobj);
			if (obj instanceof FTPFileObject) {
			    fileobj = (FTPFileObject) obj;
			} else {
			    fileobj = null;
			}
		}
		return sb.toString();
	}

	private FTPClient getFTPClient(Object element) {
		Object obj = element;
		while (obj != null && !(obj instanceof FTPClientObject)) {
			obj = ((FTPFileObject) obj).getParent();
		}
		if (obj != null)
			return ((FTPClientObject) obj).getFtpClient();
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object element) {
		Object parent = null;
		if (element instanceof FTPFileObject) {
			parent = ((FTPFileObject) element).getParent();
		}
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object element) {
	    if (element instanceof FTPClientObject) {
	        return true;
	    } else if (element instanceof FTPFileObject) {
			FTPFile ftpFile = ((FTPFileObject) element).getFTPFile();
			if (ftpFile.isDirectory()) {
			    return true;
			} else {
			    return false;
			}
	    } else {
	        return false;
	    }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		// Release any listeners. Other cleanup.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// Register listeners on newInput, remove listeners on oldInput, other
		// initialization.
	}

	/**
	 * @param oldInput
	 * @param newInput
	 */
	public void init(Object oldInput, Object newInput) {
		// Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}
}