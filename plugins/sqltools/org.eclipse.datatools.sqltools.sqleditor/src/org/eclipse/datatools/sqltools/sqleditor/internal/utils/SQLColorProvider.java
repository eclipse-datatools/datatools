/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.preferences.SyntaxItem;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * This class defines colors used in the SQL editor.
 */
public class SQLColorProvider {

    public static final RGB SQL_COMMENT_COLOR              = new RGB(64, 128, 128);
    public static final RGB SQL_MULTILINE_COMMENT_COLOR    = new RGB(64, 128, 128);
    public static final RGB SQL_QUOTED_LITERAL_COLOR       = new RGB(0, 0, 255);
    public static final RGB SQL_KEYWORD_COLOR              = new RGB(127, 0, 85);
    public static final RGB SQL_TYPE_COLOR				   = new RGB(64, 0, 200);
    public static final RGB SQL_IDENTIFIER_COLOR           = new RGB( 0, 0, 128 );  // dark blue 
    public static final RGB SQL_DELIMITED_IDENTIFIER_COLOR = new RGB( 0, 128, 0 );  // dark green
    public static final RGB SQL_DEFAULT_COLOR              = new RGB( 0, 0, 0 );    // black
    
    // Define colors that can be used when the display is in "high contrast" mode.
    // (High contrast is a Windows feature that helps vision impaired people.) 
    public static final RGB SQL_HC_COMMENT_COLOR              = new RGB( 255, 0, 0 );     // bright red
    public static final RGB SQL_HC_MULTILINE_COMMENT_COLOR    = new RGB( 0, 0, 255 );     // bright blue
    public static final RGB SQL_HC_QUOTED_LITERAL_COLOR       = new RGB( 0, 255, 0 );     // bright green
    public static final RGB SQL_HC_KEYWORD_COLOR              = new RGB( 255, 255, 0 );   // yellow
    public static final RGB SQL_HC_TYPE_COLOR				  = new RGB(64, 0, 200);
    public static final RGB SQL_HC_IDENTIFIER_COLOR           = new RGB( 0, 0, 255 );     // bright blue
    public static final RGB SQL_HC_DELIMITED_IDENTIFIER_COLOR = new RGB( 255, 0, 0 ) ;    // bright red
    public static final RGB SQL_HC_DEFAULT_COLOR              = new RGB( 255, 255, 255 ); // bright white
    
    // Define names for displaying.
    public static final String SQL_COMMENT 					= Messages.SyntaxColoring_Comment;
	public static final String SQL_MULTILINE_COMMENT 		= Messages.SyntaxColoring_MultilineComment;
	public static final String SQL_QUOTED_LITERAL 			= Messages.SyntaxColoring_QuotedLiteral;
	public static final String SQL_DELIMITED_IDENTIFIER 	= Messages.SyntaxColoring_DelimitedIdentifier;
	public static final String SQL_KEYWORD 					= Messages.SyntaxColoring_Keyword;
	public static final String SQL_TYPE 					= Messages.SyntaxColoring_Type;
	public static final String SQL_IDENTIFIER 				= Messages.SyntaxColoring_Identifier;
	public static final String SQL_DEFAULT 					= Messages.SyntaxColoring_Default;
    
    protected Map fColorTable = new HashMap(10);

    /**
	 * Release all of the color resources held onto by the receiver.
	 */
    public void dispose() {
        Iterator e = fColorTable.values().iterator();
        while (e.hasNext())
            ((Color) e.next()).dispose();
    }
    
    /**
     * Gets a Color object for the given RGB value.
     * 
     * @param rgb the RGB value for which the Color object is needed
     * @return the Color object corresponding to the RGB value
     */
    public Color getColor( RGB rgb ) {
        Color color = (Color) fColorTable.get( rgb );
        // If this is first time the color has been requested, create the
        // color and put it in our Map associated with its RGB value.
        if (color == null) {
            color = new Color( Display.getCurrent(), rgb );
            fColorTable.put( rgb, color );
        }
        return color;
    }
    
    /**
     * Creates a IToken object for the given syntax value.
     * 
     * @param syntax the value representing a syntax
     * @return the IToken object corresponding to the value saved in preference store
     */
    public IToken createToken(String syntax) {
    	
    	TextAttribute ta = createTextAttribute(syntax);
    	if (ta != null) {
    		return new Token(ta);
    	}

    	return null;
    }
    
    /**
     * Creates a TextAttribute object for the given syntax value.
     * 
     * @param syntax the value representing a syntax
     * @return the TextAttribute object corresponding to the value saved in preference store
     */
    public TextAttribute createTextAttribute(String syntax) {
		IPreferenceStore store = SQLEditorPlugin.getDefault().getPreferenceStore();

		if (!store.getString(syntax).equals("")) {
			SyntaxItem si = new SyntaxItem(store.getString(syntax));
			if (si != null) {
				int style = 0;
				if (si.isBold()) {
					style = style | SWT.BOLD;
				}
				if (si.isItalic()) {
					style = style | SWT.ITALIC;
				}
				if (si.isStrikethrough()) {
					style = style | TextAttribute.STRIKETHROUGH;
				}
				if (si.isUnderline()) {
					style = style | TextAttribute.UNDERLINE;
				}
				return new TextAttribute(getColor(si.getColor()), null, style);
			}
		}

		if (syntax.equals(SQL_COMMENT)) {
			return new TextAttribute(getColor(SQL_COMMENT_COLOR));
		} else if (syntax.equals(SQL_MULTILINE_COMMENT)) {
			return new TextAttribute(getColor(SQL_MULTILINE_COMMENT_COLOR));
		} else if (syntax.equals(SQL_QUOTED_LITERAL)) {
			return new TextAttribute(getColor(SQL_QUOTED_LITERAL_COLOR));
		} else if (syntax.equals(SQL_KEYWORD)) {
			return new TextAttribute(getColor(SQL_KEYWORD_COLOR), null, SWT.BOLD);
		} else if (syntax.equals(SQL_TYPE)) {
			return new TextAttribute(getColor(SQL_TYPE_COLOR), null, SWT.BOLD);
		} else if (syntax.equals(SQL_IDENTIFIER)) {
			return new TextAttribute(getColor(SQL_IDENTIFIER_COLOR));
		} else if (syntax.equals(SQL_DELIMITED_IDENTIFIER)) {
			return new TextAttribute(getColor(SQL_DELIMITED_IDENTIFIER_COLOR));
		} else {
			return new TextAttribute(getColor(SQL_DEFAULT_COLOR));
		}
    }
} // end class
