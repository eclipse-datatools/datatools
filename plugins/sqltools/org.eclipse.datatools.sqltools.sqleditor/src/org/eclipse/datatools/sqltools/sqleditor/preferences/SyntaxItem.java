/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase
 ******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.preferences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.graphics.RGB;

/**
 * A syntax item object
 * 
 * @author renj
 */
public class SyntaxItem {
	private Pattern pattern = Pattern.compile("(\\d)\\,(\\d)\\,(\\d)\\,(\\d)\\,(\\d)\\,(\\d{1,3})\\,(\\d{1,3})\\,(\\d{1,3})");

	private Boolean _boldKey = Boolean.FALSE;
	private Boolean _italicKey = Boolean.FALSE;
	private Boolean _strikethroughKey = Boolean.FALSE;
	private Boolean _underlineKey = Boolean.FALSE;
	private Boolean _systemDefault = Boolean.FALSE;
	private RGB _color = new RGB(0, 0, 0);

	public SyntaxItem(String preferenceCodes) {
		if (preferenceCodes != null && !preferenceCodes.equals("")) {
			Matcher m = pattern.matcher(preferenceCodes);
			if (m.matches()) {
				_boldKey = Boolean.valueOf(m.group(1).equals("1") ? true : false);
				_italicKey = Boolean.valueOf(m.group(2).equals("1") ? true : false);
				_strikethroughKey = Boolean.valueOf(m.group(3).equals("1") ? true : false);
				_underlineKey = Boolean.valueOf(m.group(4).equals("1") ? true : false);
				_systemDefault = Boolean.valueOf(m.group(5).equals("1") ? true : false);
				_color = new RGB(Integer.parseInt(m.group(6)), Integer.parseInt(m.group(7)), Integer.parseInt(m.group(8)));
			}
		}
	}

	public SyntaxItem(boolean bk, boolean ik, boolean sk, boolean uk, boolean sys, RGB rgb) {
		_boldKey = Boolean.valueOf(bk);
		_italicKey = Boolean.valueOf(ik);
		_strikethroughKey = Boolean.valueOf(sk);
		_underlineKey = Boolean.valueOf(uk);
		_systemDefault = Boolean.valueOf(sys);
		_color = rgb;
	}

	public void setBoldKey(boolean bk) {
		_boldKey = Boolean.valueOf(bk);
	}

	public void setItalicKey(boolean ik) {
		_italicKey = Boolean.valueOf(ik);
	}

	public void setStrikethroughKey(boolean sk) {
		_strikethroughKey = Boolean.valueOf(sk);
	}

	public void setUnderlineKey(boolean uk) {
		_underlineKey = Boolean.valueOf(uk);
	}

	public void setSystemDefault(boolean sys) {
		_systemDefault = Boolean.valueOf(sys);
	}

	public void setColor(RGB rgb) {
		_color = rgb;
	}

	public boolean isBold() {
		return _boldKey.booleanValue();
	}

	public boolean isItalic() {
		return _italicKey.booleanValue();
	}

	public boolean isStrikethrough() {
		return _strikethroughKey.booleanValue();
	}

	public boolean isUnderline() {
		return _underlineKey.booleanValue();
	}

	public boolean isSystemDefault() {
		return _systemDefault.booleanValue();
	}

	public RGB getColor() {
		return _color;
	}

	public String toString() {
		String preferenceCodes = (_boldKey.booleanValue() ? "1" : "0") + "," + (_italicKey.booleanValue() ? "1" : "0") + "," + (_strikethroughKey.booleanValue() ? "1" : "0") + ","
				+ (_underlineKey.booleanValue() ? "1" : "0") + "," + (_systemDefault.booleanValue() ? "1" : "0") + "," + _color.red + "," + _color.green + "," + _color.blue;
		return preferenceCodes;
	}
}
