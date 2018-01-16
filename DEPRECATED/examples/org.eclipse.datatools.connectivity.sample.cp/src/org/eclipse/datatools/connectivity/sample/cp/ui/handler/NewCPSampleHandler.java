package org.eclipse.datatools.connectivity.sample.cp.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class NewCPSampleHandler extends AbstractHandler {

	public NewCPSampleHandler() {
		super();
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveShell(event);
		MessageDialog.openInformation(shell, 
				"Overridden by Sample CP", 
				"New profile handler was overridden by the Sample CP");
		return null;
	}

}
