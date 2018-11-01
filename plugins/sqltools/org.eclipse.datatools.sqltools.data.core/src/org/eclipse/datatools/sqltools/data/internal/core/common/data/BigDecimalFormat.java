/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.core.common.data;

import java.math.BigDecimal;

import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.DecimalFormatSymbols;
import com.ibm.icu.text.NumberFormat;

public class BigDecimalFormat
{
	protected NumberFormat nf;
	protected static BigDecimalFormat instance = null;
	
	protected BigDecimalFormat()
	{
		nf = NumberFormat.getNumberInstance();
	}
	
	public static BigDecimalFormat getInstance()
	{
		if (instance==null)
			instance = new BigDecimalFormat();
		
		return instance;
	}
	
	public String format(BigDecimal bd)
	{
		String s = bd.toString();
		
		if (nf instanceof DecimalFormat) {
			DecimalFormatSymbols symbols = ((DecimalFormat)nf).getDecimalFormatSymbols();
			s = s.replace('.', symbols.getDecimalSeparator());
			s = s.replace('-', symbols.getMinusSign());
		}
		
		return s;
	}
	
	public BigDecimal parse(String s)
	{
		if (nf instanceof DecimalFormat) {
			DecimalFormatSymbols symbols = ((DecimalFormat)nf).getDecimalFormatSymbols();
			s = s.replace(symbols.getDecimalSeparator(), '.');
			s = s.replace(symbols.getMinusSign(), '-');
		}
		
		return new BigDecimal(s);
	}
}
