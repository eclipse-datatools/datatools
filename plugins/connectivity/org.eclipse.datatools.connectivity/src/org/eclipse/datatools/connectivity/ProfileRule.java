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
package org.eclipse.datatools.connectivity;

import org.eclipse.core.runtime.jobs.ISchedulingRule;

/**
 * Can be used to synchronize a <code>Job</code> with other <code>Job</code>s
 * affecting a specific profile.
 * 
 * @author rcernich
 * 
 * Created on May 24, 2005
 */
public class ProfileRule implements ISchedulingRule {

	private IConnectionProfile mProfile;

	/**
	 * @param profile the profile to synchronize against
	 */
	public ProfileRule(IConnectionProfile profile) {
		super();
		if (profile == null) {
			throw new IllegalArgumentException("profile == null"); //$NON-NLS-1$
		}
		mProfile = profile;
	}

	public boolean isConflicting(ISchedulingRule rule) {
		return rule instanceof ProfileRule
				&& ((ProfileRule) rule).mProfile.equals(mProfile);
	}

	public boolean contains(ISchedulingRule rule) {
		return isConflicting(rule);
	}
}