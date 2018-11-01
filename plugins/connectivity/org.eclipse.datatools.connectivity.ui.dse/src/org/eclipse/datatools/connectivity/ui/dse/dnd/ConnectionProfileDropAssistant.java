/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.dse.dnd;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileProvider;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepositoryConstants;
import org.eclipse.datatools.connectivity.internal.ui.LocalRepositoryNode;
import org.eclipse.datatools.connectivity.internal.ui.refactoring.ConnectionProfileCopyProcessor;
import org.eclipse.datatools.connectivity.internal.ui.refactoring.ConnectionProfileMoveProcessor;
import org.eclipse.datatools.connectivity.ui.RefreshProfileJob;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ltk.core.refactoring.CheckConditionsOperation;
import org.eclipse.ltk.core.refactoring.PerformRefactoringOperation;
import org.eclipse.ltk.core.refactoring.participants.CopyRefactoring;
import org.eclipse.ltk.core.refactoring.participants.MoveRefactoring;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.views.navigator.LocalSelectionTransfer;

/**
 * 
 * @author tqiu
 * 
 * this class leverage CNF's built-in dnd support and simply used for DND
 * connection profiles between different repositories
 * 
 */
public class ConnectionProfileDropAssistant extends CommonDropAdapterAssistant {

	private List mElements;

	public IStatus handleDrop(CommonDropAdapter dropAdapter,
			DropTargetEvent dropTargetEvent, Object target) {
		if (LocalSelectionTransfer.getInstance().isSupportedType(
				dropAdapter.getCurrentTransfer())) {
			try {
				switch (dropAdapter.getCurrentOperation()) {
				case (DND.DROP_COPY):
					handleDropCopy(target);
					break;
				case (DND.DROP_MOVE):
					handleDropMove(target);
					break;
				}

			}
			catch (ConnectionProfileException e) {
				e.printStackTrace();
			}
			catch (CoreException e) {
				e.printStackTrace();
			}

		}

		return Status.CANCEL_STATUS;
	}

	private void handleDropMove(Object target)
			throws ConnectionProfileException, CoreException {
		// handle move cp to Local Repository
		if (target instanceof LocalRepositoryNode) {
			PerformRefactoringOperation refOperation = new PerformRefactoringOperation(
					new MoveRefactoring(new ConnectionProfileMoveProcessor(
							(IConnectionProfile[]) mElements
									.toArray(new IConnectionProfile[mElements
											.size()]), ProfileManager
									.getInstance())),
					CheckConditionsOperation.ALL_CONDITIONS);

			ResourcesPlugin.getWorkspace().run(refOperation, null);
		}
		// handle move cp to specific repository
		else if (target instanceof IConnectionProfile) {
			PerformRefactoringOperation refOperation = new PerformRefactoringOperation(
					new MoveRefactoring(new ConnectionProfileMoveProcessor(
							(IConnectionProfile[]) mElements
									.toArray(new IConnectionProfile[mElements
											.size()]), target)),
					CheckConditionsOperation.ALL_CONDITIONS);

			ResourcesPlugin.getWorkspace().run(refOperation, null);
		}
	}

	private void handleDropCopy(Object target)
			throws ConnectionProfileException, CoreException {
		// handle copy cp to Local Repository
		if (target instanceof LocalRepositoryNode) {
			PerformRefactoringOperation refOperation = new PerformRefactoringOperation(
					new CopyRefactoring(new ConnectionProfileCopyProcessor(
							(IConnectionProfile[]) mElements
									.toArray(new IConnectionProfile[mElements
											.size()]), ProfileManager
									.getInstance())),
					CheckConditionsOperation.ALL_CONDITIONS);
			ResourcesPlugin.getWorkspace().run(refOperation, null);
		}
		// handle copy cp to specific repository
		else if (target instanceof IConnectionProfile) {
			PerformRefactoringOperation refOperation = new PerformRefactoringOperation(
					new CopyRefactoring(new ConnectionProfileCopyProcessor(
							(IConnectionProfile[]) mElements
									.toArray(new IConnectionProfile[mElements
											.size()]), target)),
					CheckConditionsOperation.ALL_CONDITIONS);
			ResourcesPlugin.getWorkspace().run(refOperation, null);
		}
	}

	public IStatus validateDrop(Object target, int operation,
			TransferData transferType) {
		IStatus result = Status.OK_STATUS;

		if (LocalSelectionTransfer.getInstance().isSupportedType(transferType)) {

			initializeSelection();

			result = handleValidate(target, operation);
		}

		return result;
	}

	private IStatus handleValidate(Object target, int operation) {
		//disallow dropping of repository data and dropping of profile data from read-only repository
		for (Iterator iter = mElements.iterator(); iter.hasNext();) {
			Object data = iter.next();
			if (data instanceof ConnectionProfile) {
				IConnectionProfileRepository repo = ((ConnectionProfile) data)
						.getRepository();
				// data from non-local read-only repository
				if (operation != DND.DROP_COPY && repo != null && repo.isReadOnly()) {
					return Status.CANCEL_STATUS;
				}
			}
		}
		
		if (target instanceof LocalRepositoryNode) {
			// make sure not dropping cp to it's own repository
			for (Iterator iter = mElements.iterator(); iter.hasNext();) {
				Object data = iter.next();
				if (data instanceof IConnectionProfile) {
					IConnectionProfile profile = (IConnectionProfile) data;
					if (profile.getParentProfile() == null)
						return Status.CANCEL_STATUS;
				}
				else {
					return Status.CANCEL_STATUS;
				}
			}
		}

		else if (target instanceof IConnectionProfile) {
			IManagedConnection imc = ((IConnectionProfile) target)
					.getManagedConnection(IConnectionProfileRepositoryConstants.REPOSITORY_CONNECTION_FACTORY_ID);
			if (imc == null || !imc.isConnected()
					|| imc.getConnection() == null) {
				return Status.CANCEL_STATUS;
			}

			IConnectionProfileRepository repo = (IConnectionProfileRepository) imc
					.getConnection().getRawConnection();
			if (repo == null || repo.isReadOnly()) {
				return Status.CANCEL_STATUS;
			}

			for (Iterator iter = mElements.iterator(); iter.hasNext();) {
				Object data = iter.next();
				if (data instanceof IConnectionProfile) {
					IConnectionProfile profile = (IConnectionProfile) data;
					// make sure not dropping cp to it's containing
					// repository
					if (target.equals(profile.getParentProfile()))
						return Status.CANCEL_STATUS;

					// make sure the cp is compatible with the target
					// repository
					if (!((ConnectionProfileProvider) profile.getProvider())
							.compatibleWithRepository((IConnectionProfile) target)
							|| !repo.supportsProfileType(profile
									.getProviderId()))
						return Status.CANCEL_STATUS;

				}
				else {
					return Status.CANCEL_STATUS;
				}
			}
		}

		return Status.OK_STATUS;

	}

	protected void initializeSelection() {
		ISelection s = LocalSelectionTransfer.getInstance().getSelection();
		if (!(s instanceof IStructuredSelection))
			return;
		mElements = ((IStructuredSelection) s).toList();
	}

	protected void refreshViewer(final Object object, final boolean expand) {
		IViewPart view = PlatformUI
				.getWorkbench()
				.getActiveWorkbenchWindow()
				.getActivePage()
				.findView(
						"org.eclipse.datatools.connectivity.DataSourceExplorerNavigator"); //$NON-NLS-1$

		final CommonViewer viewer = ((CommonNavigator) view).getCommonViewer();

		if (viewer instanceof TreeViewer
				&& object instanceof IConnectionProfile) {
			RefreshProfileJob.scheduleRefreshProfileJob(
					(IConnectionProfile) object, (TreeViewer) viewer);
		}
		else {
			Runnable runner = new Runnable() {

				public void run() {
					((CommonViewer) viewer).refresh();
				}
			};
			viewer.getControl().getDisplay().syncExec(runner);
		}
	}

}
