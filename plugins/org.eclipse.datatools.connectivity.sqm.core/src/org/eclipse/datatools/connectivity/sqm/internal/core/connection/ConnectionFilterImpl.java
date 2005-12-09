/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.connection;

import java.util.Vector;

public class ConnectionFilterImpl implements ConnectionFilter {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3689626986939299641L;

    private String predicate;

    public ConnectionFilterImpl() {

    }

    public ConnectionFilterImpl(String predicate) {
        this.predicate = predicate;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }

    public String getPredicate() {
        return this.predicate;
    }

    public boolean isValid(String predicate) {
        return predicate != null && predicate.length() > 0;
    }

    public String getPattern() {
        String pattern = ""; //$NON-NLS-1$
        if (predicate != null) {
            try {
                if (predicate.indexOf("(") < 0) { //$NON-NLS-1$
                    pattern = predicate.substring(predicate.indexOf("'"), //$NON-NLS-1$
                            predicate.length());
                } else {
                    pattern = predicate.substring(predicate.indexOf("("), //$NON-NLS-1$
                            predicate.length());
                }
            } catch (Exception e) {
                // Bury the exception - if the predicate is an unexpected string
                // we want to return an empty string
            }
        }
        return pattern;
    }

    public String getOperator() {
        String operator = ""; //$NON-NLS-1$
        if (predicate != null) {
            try {
                if (predicate.indexOf("(") < 0) { //$NON-NLS-1$
                    operator = predicate.substring(0, predicate.indexOf("'")); //$NON-NLS-1$
                } else {
                    operator = predicate.substring(0, predicate.indexOf("(")); //$NON-NLS-1$
                }
            } catch (Exception e) {
                // Bury the exception - if the predicate is an unexpected string
                // we want to return an empty string
            }
        }
        return operator;
    }

    public String[] getPatternElements() {
        String[] elements = new String[] {};
        Vector patternVector = new Vector();
        String pattern = this.getPattern();
        String[] patterns = pattern.split("'"); //$NON-NLS-1$
        for (int i = 0; i < patterns.length; i++) {
            if (!patterns[i].equals(", ") && !patterns[i].equals("(") //$NON-NLS-1$ //$NON-NLS-2$
                    && !patterns[i].equals(")")) { //$NON-NLS-1$
                patternVector.add(patterns[i]);
            }
        }
        elements = new String[patternVector.size()];
        patternVector.copyInto(elements);
        return elements;
    }
    
    public boolean isOperatorInclusive(){
        boolean isInclusive = false;
        if (getOperator().equals("IN")){ //$NON-NLS-1$
            isInclusive = true;
        }
        return isInclusive;
    }
    
    public boolean isOperatorExclusive(){
        boolean isExclusive = false;
        if (getOperator().equals("NOT IN")){ //$NON-NLS-1$
            isExclusive = true;
        }
        return isExclusive;
    }
}