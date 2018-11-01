/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.net.URL;

import org.eclipse.datatools.enablement.oda.xml.ui.UiPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;

/**
 * This is a utility class that serves for the Tree Node.
 * 
 */
public class TreeNodeDataUtil
{

	private static final String ICONS_XML_COLUMN_GIF_CONSTANT = "icons/XML_column.gif"; //$NON-NLS-1$
    private static final String XML_ELEMENT_GIF_LOCATION = "icons/XML_element.gif";     //$NON-NLS-1$
    private static final String XML_FILE_GIF_LOCATION = "icons/XML_file.gif";           //$NON-NLS-1$
    private static final String FORWARD_SLASH = "/";                                    //$NON-NLS-1$

    private static Image sourceFileImage, xmlElementImage, columnImage;
	private static String SOURCE_FILE_ICON = "xPathChoosePage.FileNameIcon";            //$NON-NLS-1$
	private static String XML_ELEMENT_ICON = "xPathChoosePage.XMLElementIcon";          //$NON-NLS-1$
	private static String COLUMN_ICON = "xPathChoosePage.ColumnIcon";                   //$NON-NLS-1$

	static
	{
		try
		{
			ImageRegistry reg = JFaceResources.getImageRegistry( );
			reg.put( SOURCE_FILE_ICON,
					ImageDescriptor.createFromURL( new URL(UiPlugin.getDefault().getBundle().getEntry(FORWARD_SLASH),
							XML_FILE_GIF_LOCATION )) );           
			reg.put( XML_ELEMENT_ICON,
					ImageDescriptor.createFromURL( new URL(UiPlugin.getDefault().getBundle().getEntry(FORWARD_SLASH),
							XML_ELEMENT_GIF_LOCATION )) );        
			reg.put( COLUMN_ICON,
					ImageDescriptor.createFromURL( new URL(UiPlugin.getDefault().getBundle().getEntry(FORWARD_SLASH),
							ICONS_XML_COLUMN_GIF_CONSTANT )) );         
			sourceFileImage = JFaceResources.getImage( SOURCE_FILE_ICON );
			xmlElementImage = JFaceResources.getImage( XML_ELEMENT_ICON );
			columnImage = JFaceResources.getImage( COLUMN_ICON );
		}
		catch ( Exception e )
		{

		}
	}

	/**
	 * Return source file icon image
	 * 
	 * @return Image
	 */
	public static Image getSourceFileImage( )
	{
		return sourceFileImage;
	}

	/**
	 * Return XML element icon image
	 * 
	 * @return Image
	 */
	public static Image getXmlElementImage( )
	{
		return xmlElementImage;
	}

	/**
	 * Return column icon image
	 * 
	 * @return Image
	 */
	public static Image getColumnImage( )
	{
		return columnImage;
	}

}
