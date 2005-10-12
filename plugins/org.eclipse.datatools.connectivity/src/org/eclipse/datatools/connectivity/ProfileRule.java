/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

import org.eclipse.core.runtime.jobs.ISchedulingRule;

/**
 * @author rcernich
 * 
 * Created on May 24, 2005
 */
public class ProfileRule implements ISchedulingRule {

	private IConnectionProfile mProfile;

	public ProfileRule(IConnectionProfile profile) {
		super();
		if (profile == null) {
			throw new IllegalArgumentException("profile == null");
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