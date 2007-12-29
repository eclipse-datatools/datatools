
package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

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

	private static Image sourceFileImage, xmlElementImage, columnImage;
	private static String SOURCE_FILE_ICON = "xPathChoosePage.FileNameIcon";
	private static String XML_ELEMENT_ICON = "xPathChoosePage.XMLElementIcon";
	private static String COLUMN_ICON = "xPathChoosePage.ColumnIcon";

	static
	{
		try
		{
			ImageRegistry reg = JFaceResources.getImageRegistry( );
			reg.put( SOURCE_FILE_ICON,
					ImageDescriptor.createFromFile( UiPlugin.class,
							"icons/XML_file.gif" ) );//$NON-NLS-1$
			reg.put( XML_ELEMENT_ICON,
					ImageDescriptor.createFromFile( UiPlugin.class,
							"icons/XML_element.gif" ) );//$NON-NLS-1$
			reg.put( COLUMN_ICON,
					ImageDescriptor.createFromFile( UiPlugin.class,
							"icons/XML_column.gif" ) );//$NON-NLS-1$
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
