/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.debugger.debug;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String DebugException_occurred;
	public static String SPModelPresentation_breakpoint;
	public static String SPModelPresentation_line;
	public static String SPModelPresentation_breakpointTextFail;
	public static String SPModelPresentation_evaluateFailMessage;
	public static String SPModelPresentation_getVariableInfoFail;
	public static String SPModelPresentation_terminatedFlag;
	public static String SPModelPresentation_disconnectedFlag;
	public static String SPModelPresentation_notRespondingFlag;
	public static String SPModelPresentation_debugTarget;
	public static String SPModelPresentation_ERROR2;
	public static String SPModelPresentation_breakpointUnknown;
	public static String SPModelPresentation_thread;
	public static String SPModelPresentation_errorGetEditorInput;
	public static String BreakpointLocationVerifierJob_notValidLocation;
	public static String BreakpointLocationVerifierJob_breakpointRemoved;
	public static String BreakpointLocationVerifierJob_breakpointSet;
	public static String BreakpointLocationVerifierJob_breakpointMovedToValid;
	public static String BreakpointLocationVerifierJob_breakpoint_verification_fail;
	public static String BreakpointLocationVerifierJob_label;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}