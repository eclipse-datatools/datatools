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

import java.math.BigInteger;

import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.DecimalFormatSymbols;
import com.ibm.icu.text.NumberFormat;

public class BigIntegerFormat
{
	protected NumberFormat nf;
	protected static BigIntegerFormat instance = null;
	
	protected BigIntegerFormat()
	{
		nf = NumberFormat.getIntegerInstance();
	}
	
	public static BigIntegerFormat getInstance()
	{
		if (instance==null)
			instance = new BigIntegerFormat();
		
		return instance;
	}
	
	public String format(BigInteger bi)
	{
		String s = bi.toString();
		
		if (nf instanceof DecimalFormat) {
			DecimalFormatSymbols symbols = ((DecimalFormat)nf).getDecimalFormatSymbols();
			s = s.replace('-', symbols.getMinusSign());
		}
		
		return s;
	}
	
	public BigInteger parse(String s)
	{		
		if (s == null ) {
			return null;
		}
		if (nf instanceof DecimalFormat) {
			DecimalFormatSymbols symbols = ((DecimalFormat)nf).getDecimalFormatSymbols();
			s = s.replace(symbols.getMinusSign(), '-');
		}
		
		return new BigInteger(s);
	}
}
