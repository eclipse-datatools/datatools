/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper;
import org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.NumberDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.NumericalDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;

/**
 * Utilitiy methods related to asa data type
 * 
 * @author Idull
 */
public class ASADatatypeUtil
{
    public static PredefinedDataType getPredefinedRepresentation(DataType datatype)
    {
        if (datatype instanceof DistinctUserDefinedType)
        {
            DistinctUserDefinedType udt = (DistinctUserDefinedType) datatype;
            PredefinedDataType pType = udt.getPredefinedRepresentation();
            return pType;
        }
        return null;
    }

    public static boolean isNumericType(DataType datatype)
    {
        if (getPredefinedRepresentation(datatype) != null)
        {
            return isNumericType(getPredefinedRepresentation(datatype));
        }
        if ((datatype instanceof NumericalDataType) || (datatype instanceof NumberDataType))
        {
            return true;
        }
        return false;
    }

    public static boolean isStringType(DataType datatype)
    {
        if (getPredefinedRepresentation(datatype) != null)
        {
            return isStringType(getPredefinedRepresentation(datatype));
        }
        if (datatype instanceof CharacterStringDataType)
        {
            return true;
        }
        return false;
    }

    /**
     * If it has primitive type is the primitive type is time/date type, then it's a timestamp type
     * @param datatype
     * @return
     */
    public static boolean isTSType(DataType datatype)
    {
        if(datatype.getName().equalsIgnoreCase(SybaseASADefaultWrapper.TIMESTAMP_TYPE))
        {
            return true;
        }
        if (getPredefinedRepresentation(datatype) != null)
        {
            if (getPredefinedRepresentation(datatype) instanceof TimeDataType)
            {
                return true;
            }
            if (isDateType(getPredefinedRepresentation(datatype)))
            {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isTimeType(DataType datatype)
    {
        // Only if the name is "TIME" is it a time data type
        if (datatype instanceof TimeDataType)
        {
            if(datatype.getName().equalsIgnoreCase(SybaseASADefaultWrapper.TIME_TYPE))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isDateType(DataType datatype)
    {
        if (datatype instanceof DateDataType)
        {
            return true;
        }
        return false;
    }

    
    
    public static boolean isBinaryType(DataType datatype)
    {
        if (getPredefinedRepresentation(datatype) != null)
        {
            return isBinaryType(getPredefinedRepresentation(datatype));
        }
        if (datatype instanceof BinaryStringDataType)
        {
            return true;
        }
        return false;
    }
}
