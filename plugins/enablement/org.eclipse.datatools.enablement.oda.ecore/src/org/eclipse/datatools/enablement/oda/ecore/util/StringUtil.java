/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2007-2008 SolutionsIQ, Inc.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *
 * </copyright>
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.util;

public class StringUtil {

	public static String join(final String[] strings, final String joint) {
		if (strings == null) {
			return null;
		}
		if (strings.length == 0) {
			return "";
		}
		final StringBuilder builder = new StringBuilder(strings[0]);
		for (int i = 1; i < strings.length; i++) {
			builder.append(joint);
			builder.append(strings[i]);
		}
		return builder.toString();
	}

	public static int countChars(final char c, final CharSequence s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (c == s.charAt(i)) {
				count++;
			}
		}
		return count;
	}
}
