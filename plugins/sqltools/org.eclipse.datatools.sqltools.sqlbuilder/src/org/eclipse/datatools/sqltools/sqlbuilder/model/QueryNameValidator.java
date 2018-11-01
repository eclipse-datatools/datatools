/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.model;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.util.TString;
import org.eclipse.jface.dialogs.IInputValidator;


public class QueryNameValidator implements IInputValidator {

    List currentNamesList = null;

    public QueryNameValidator(List names) {
        currentNamesList = names;
    }

    public String containsInvalidChar(String text) {
        String invalidChars[] = { "?", "\"", "<", ">", "\\", "|", ":", "*", "#", "/" };

        for (int i = 0; i < invalidChars.length; i++) {
            if (text.indexOf(invalidChars[i]) != -1) {
                return invalidChars[i];
            }
        }
        return "";
    }

    public String isValid(String newText) {

        String returnMessage = null;
        if (newText.equals("") || (newText.indexOf(" ") != -1)) {
            returnMessage = Messages._ERROR_INVALID_STATEMENT_NAME;
        }
        String invalidChar = containsInvalidChar(newText);
        if (!invalidChar.equals("")) {
            String msg1 = Messages._ERROR_INVALID_CHAR_IN_STATEMENT_NAME;
            returnMessage = TString.change(msg1, "%1", invalidChar);
        }
        if (newText.length() > 160) {
            returnMessage = Messages._ERROR_STATEMENT_NAME_TOO_LONG;
        }
        if (currentNamesList != null) {
            Iterator itr = currentNamesList.iterator();
            while (itr.hasNext()) {
                String name = (String) itr.next();
                if ((newText.toUpperCase()).equals(name.toUpperCase())) {
                    String msg2 = Messages._ERROR_STATEMENT_NAME_EXISTS;
                    returnMessage = TString.change(msg2, "%1", newText);
                }
            }
        }
        return returnMessage;
    }

}