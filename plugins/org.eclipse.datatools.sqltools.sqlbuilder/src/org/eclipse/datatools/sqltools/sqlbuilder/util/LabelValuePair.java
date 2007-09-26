/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.util;

import org.eclipse.jface.util.Assert;

public class LabelValuePair {

    public String fLabel;
    public Object fValue;

    /**
     * Creates a new name/value item
     */
    public LabelValuePair(String label, Object value) {
        Assert.isTrue(label != null);
        fLabel = label;
        fValue = value;
    }
}
