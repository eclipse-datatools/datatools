/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileRule;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.progress.UIJob;

/**
 * This class can be used by UI components to refresh a connection profile
 * within a TreeViewer.  This job synchronizes with other profile jobs and
 * ensures that refresh(profile) is invoked only once per tree viewer per connection
 * profile.
 * 
 * @author rcernich
 * 
 * Created on May 24, 2005
 */
public class RefreshProfileJob extends UIJob {

	private IConnectionProfile mProfile;

	private TreeViewer mViewer;

	private final static Map sProfileToJobsMap = new HashMap();

	private static IJobChangeListener sJobListener = new JobChangeAdapter() {

		public void running(IJobChangeEvent event) {
			RefreshProfileJob job = (RefreshProfileJob) event.getJob();
			synchronized (sProfileToJobsMap) {
				// This job has started, so remove it from our list. This will
				// allow new jobs to be scheduled for this profile/viewer
				// combination.
				List jobs = (List) sProfileToJobsMap.get(job.mProfile);
				jobs.remove(job);
				if (jobs.isEmpty()) {
					sProfileToJobsMap.remove(job.mProfile);
				}
			}
		}
	};

	/**
	 * @param profile the connection profile to refresh
	 * @param viewer the viewer containing the profile
	 */
	public static void scheduleRefreshProfileJob(IConnectionProfile profile,
			TreeViewer viewer) {
		RefreshProfileJob newJob;
		List jobs;
		synchronized (sProfileToJobsMap) {
			// See if we've already scheduled a job for this profile/viewer
			// combination.
			if (sProfileToJobsMap.containsKey(profile)) {
				jobs = (List) sProfileToJobsMap.get(profile);
				for (Iterator it = jobs.iterator(); it.hasNext();) {
					RefreshProfileJob job = (RefreshProfileJob) it.next();
					if (job.mViewer == viewer) {
						return;
					}
				}
			}
			else {
				jobs = new ArrayList();
				sProfileToJobsMap.put(profile, jobs);
			}

			// If we got here, we need to schedule a new job.
			newJob = new RefreshProfileJob(profile, viewer);
			jobs.add(newJob);
		}
		newJob.addJobChangeListener(sJobListener);
		newJob.schedule();
	}

	/**
	 * @param name
	 */
	private RefreshProfileJob(IConnectionProfile profile, TreeViewer viewer) {
		super(ConnectivityUIPlugin.getDefault().getResourceString(
				"actions.connect.uijob")); //$NON-NLS-1$
		setRule(new ProfileRule(profile));
		setSystem(false);
		setUser(true);
		if (!viewer.getTree().isDisposed()) {
			setDisplay(viewer.getTree().getDisplay());
		}
		mProfile = profile;
		mViewer = viewer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.progress.UIJob#runInUIThread(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public IStatus runInUIThread(IProgressMonitor monitor) {
		monitor.beginTask(getName(), 1);
		if (mViewer != null && !mViewer.getTree().isDisposed()) {
			// BZ 166522: we should be able to refresh the object,
			// but due to a bug in the platform, we currently have
			// to refresh the entire viewer
			// Turns out this is only a problem when adding or
			// removing a profile.  It needs to be documented that
			// this class only works to refresh an existing profile,
			// not added or removed profiles.  For those cases,
			// the parent category should be refreshed.
			mViewer.refresh(mProfile); 
			mViewer.setExpandedState(mProfile, true);
		}
		monitor.worked(1);
		return Status.OK_STATUS;
	}

	public boolean belongsTo(Object family) {
		if (family instanceof IConnectionProfile) {
			return mProfile.equals(family);
		}
		else if (family instanceof TreeViewer) {
			return family == mViewer;
		}
		return super.belongsTo(family);
	}
}