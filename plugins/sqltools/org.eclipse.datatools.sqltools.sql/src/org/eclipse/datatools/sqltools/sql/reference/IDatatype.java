/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sql.reference;

/**
 * Represents a data type object.
 * @author Dafan Yang
 *  
 */
public interface IDatatype
{
    /**
     * To test if this data type is user-defined datatype or not
     * 
     */
    public boolean isUDT();
    public void setUDT(boolean isUDT);

    /**
     * Gets the base type of this data type,if this type is UDT,then return it's base type otherwise(it is system
     * type),return itself
     * 
     */
    public IDatatype getBaseType();
    public void setBaseType(IDatatype baseType);

    /**
     * Gets the default value of this data type
     * 
     */
    public Object getDefaultValue();
    public void setDefaultValue(Object defaultValue);

    /**
     * Gets the physical lenght of this type
     * 
     */
    public int getLength();
    public void setLength(int length);

    /**
     * To test if this data type can have null value
     * 
     */
    public boolean allowNull();
    public void setAllowNull(boolean allowNull);

    /**
     * Gets the precision of this type, if precision is -1, then no precision defined
     */
    public int getPrecision();
    public void setPrecision(int precision);

    /**
     * Gets the scale of this type, if precision is -1, then no scale defined
     */
    public int getScale();
    public void setScale(int scale);


}
