/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
	private Pattern pattern = Pattern.compile("(\\d)\\,(\\d)\\,(\\d)\\,(\\d)\\,(\\d{1,3})\\,(\\d{1,3})\\,(\\d{1,3})");

	private Boolean _boldKey = Boolean.FALSE;
	private Boolean _italicKey = Boolean.FALSE;
	private Boolean _strikethroughKey = Boolean.FALSE;
	private Boolean _underlineKey = Boolean.FALSE;
	private RGB _color = new RGB(0, 0, 0);

	public SyntaxItem(String preferenceCodes) {
		if (preferenceCodes != null && !preferenceCodes.equals("")) {
			Matcher m = pattern.matcher(preferenceCodes);
			if (m.matches()) {
				_boldKey = Boolean.valueOf(m.group(1).equals("1") ? true : false);
				_italicKey = Boolean.valueOf(m.group(2).equals("1") ? true : false);
				_strikethroughKey = Boolean.valueOf(m.group(3).equals("1") ? true : false);
				_underlineKey = Boolean.valueOf(m.group(4).equals("1") ? true : false);
				_color = new RGB(Integer.parseInt(m.group(5)), Integer.parseInt(m.group(6)), Integer.parseInt(m.group(7)));
			}
		}
	}

	public SyntaxItem(boolean bk, boolean ik, boolean sk, boolean uk, RGB rgb) {
		_boldKey = Boolean.valueOf(bk);
		_italicKey = Boolean.valueOf(ik);
		_strikethroughKey = Boolean.valueOf(sk);
		_underlineKey = Boolean.valueOf(uk);
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

	public RGB getColor() {
		return _color;
	}

	public String toString() {
		String preferenceCodes = (_boldKey.booleanValue() ? "1" : "0") + "," + (_italicKey.booleanValue() ? "1" : "0") + "," + (_strikethroughKey.booleanValue() ? "1" : "0") + ","
				+ (_underlineKey.booleanValue() ? "1" : "0") + "," + _color.red + "," + _color.green + "," + _color.blue;
		return preferenceCodes;
	}
}
