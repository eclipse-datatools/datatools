/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils;

/**
 * @author David Cui
 *
 */
public interface ISqlDataValidatorAssistant
{
    /**
     * Validator will validate a value through the convert function offered by relative database.
     * If convert succeeds, we will get the original value or converted value.
     * If convert fails, we will throw SQL exception.
     * If the original value is a expression and needSupportExpression is true, 
     * will return the original expression, else return the converted value from the expression
     * @return boolean return the needSupportExpression
     */
    public boolean isNeedSupportExpression();
    
    /**
     * Validator will validate a value through the convert function offered by relative database.
     * If convert succeeds, we will get the original value or converted value.
     * If convert fails, we will throw SQL exception.
     * If the original value is a expression and needSupportExpression is true, 
     * will return the original expression, else return the converted value from the expression
     * @param needSupportExpression
     */
    public void setNeedSupportExpression(boolean needSupportExpression);
}
