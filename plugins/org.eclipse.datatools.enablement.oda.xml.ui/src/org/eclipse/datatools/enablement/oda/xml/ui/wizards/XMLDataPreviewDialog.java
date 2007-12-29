
package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * This dialog displays the sample XML data for the purpose of preview
 * 
 */
public class XMLDataPreviewDialog extends TrayDialog
{

	private ResultSetTableViewer previewViewer;

	/**
	 * The constructor of XMLDataPreviewDialog
	 * 
	 * @param shell
	 */
	protected XMLDataPreviewDialog( Shell shell )
	{
		super( shell );
	}

	/**
	 * Create table viewer composite
	 * 
	 * @param parent
	 */
	public void createTableViewer( Composite parent )
	{
		previewViewer = new ResultSetTableViewer( parent, true, true, true );
		previewViewer.getViewer( ).setHeaderVisible( true );
		previewViewer.getControl( )
				.setLayoutData( new GridData( GridData.FILL_BOTH ) );
	}

	/**
	 * Create dialog area composite
	 * 
	 * @param parent
	 */
	protected Control createDialogArea( Composite parent )
	{
		Composite control = (Composite) super.createDialogArea( parent );
		createTableViewer( control );
		return control;

	}

	/**
	 * Create the button bar of the dialog, which only contains one "close"
	 * button
	 * 
	 * @param parent
	 */
	protected void createButtonsForButtonBar( Composite parent )
	{
		createButton( parent,
				IDialogConstants.CLOSE_ID,
				IDialogConstants.CLOSE_LABEL,
				true );

	}

	/**
	 * Configure the shell
	 * 
	 * @param parent
	 */
	protected void configureShell( Shell shell )
	{
		super.configureShell( shell );
		shell.setText( Messages.getString( "XMLDataPreviewDialog.title" ) );
	}

	/**
	 * Add listener to the "close" button
	 * 
	 * @param parent
	 */
	protected void buttonPressed( int buttonId )
	{
		if ( buttonId == IDialogConstants.CLOSE_ID )
		{
			getShell( ).close( );
		}
		else
			super.buttonPressed( buttonId );
	}

}
