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

package org.eclipse.datatools.sqltools.core;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;

/**
 * The validator to validate a predefined data type
 * 
 * @author Dafan Yang
 */
public class PredefinedDataTypeValidator
{
    /**
     * Validates whether the given predefined data type is a valid one.
     * @param type a predefined data type
     * @param dbdef the databse definition
     * @return <code>null</code> if the data type is valid, otherwise returns the error message
     */
    public static String isValidPredefinedType(PredefinedDataType type, DatabaseDefinition dbdef)
    {
        if (dbdef == null)
        {
            return Messages.PredefinedDataTypeValidator_no_db_definition;
        }
        PredefinedDataTypeDefinition typeDef = getPredefinedDataTypeDefinition(type, dbdef);
        if (typeDef == null)
        {
            return Messages.PredefinedDataTypeValidator_no_datatype_definition;
        }

        if (typeDef.isLengthSupported())
        {
            EStructuralFeature f = type.eClass().getEStructuralFeature(DataTypeProvider.LENGTH_FEATURE);
            Integer length = (Integer) type.eGet(f);
            if (length != null)
            {
                if (length.intValue() < 0)
                {
                    return NLS.bind(Messages.PredefinedDataTypeValidator_length_shouldbe_positive, type.getName());
                }
                if (length.intValue() > typeDef.getMaximumLength())
                {
                    return NLS.bind(Messages.PredefinedDataTypeValidator_length_exceed_max, new Object[]
                    {
                        type.getName(), new Integer(typeDef.getMaximumLength())
                    });
                }
            }
        }

        if (typeDef.isPrecisionSupported())
        {
            EStructuralFeature f = type.eClass().getEStructuralFeature(DataTypeProvider.PRECISION_FEATURE);
            Integer precision = (Integer) type.eGet(f);
            if (precision != null)
            {
                if (precision.intValue() < 0)
                {
                    return NLS.bind(Messages.PredefinedDataTypeValidator_precision_positive, type.getName());
                }
            }
            if (precision.intValue() > typeDef.getMaximumPrecision())
            {
                return NLS.bind(Messages.PredefinedDataTypeValidator_precision_exceed_max, new Object[]
                {
                    type.getName(), new Integer(typeDef.getMaximumPrecision())
                });
            }
        }

        if (typeDef.isScaleSupported())
        {
            EStructuralFeature f = type.eClass().getEStructuralFeature(DataTypeProvider.SCALE_FEATURE);
            Integer scale = (Integer) type.eGet(f);
            if (scale != null)
            {
                if (scale.intValue() < typeDef.getMinimumScale())
                {
                    return NLS.bind(Messages.PredefinedDataTypeValidator_scale_below_min, new Object[]
                    {
                        type.getName(), new Integer(typeDef.getMinimumScale())
                    });
                }
                if (scale.intValue() > typeDef.getMaximumScale())
                {
                    return NLS.bind(Messages.PredefinedDataTypeValidator_precision_exceed_max, new Object[]
                    {
                        type.getName(), new Integer(typeDef.getMaximumScale())
                    });
                }
            }
        }

        if (typeDef.isPrecisionSupported() && typeDef.isScaleSupported())
        {
            EStructuralFeature f = type.eClass().getEStructuralFeature(DataTypeProvider.PRECISION_FEATURE);
            Integer precision = (Integer) type.eGet(f);

            EStructuralFeature sf = type.eClass().getEStructuralFeature(DataTypeProvider.SCALE_FEATURE);
            Integer scale = (Integer) type.eGet(sf);

            if (precision != null && scale != null && precision.intValue() < scale.intValue())
            {
                return NLS.bind(Messages.PredefinedDataTypeValidator_precision_lessthan_scale, type
                        .getName());
            }
        }

        return null;
    }

    /**
     * Returns the predefined data type definition given a predefined data type
     * 
     * @param dataType
     * @return
     */
    private static PredefinedDataTypeDefinition getPredefinedDataTypeDefinition(PredefinedDataType dataType,
            DatabaseDefinition dbdef)
    {
        if (dataType == null || dataType.getName() == null)
        {
            return null;
        }
        Iterator i = dbdef.getPredefinedDataTypes();
        while (i.hasNext())
        {
            PredefinedDataTypeDefinition def = (PredefinedDataTypeDefinition)i.next();
            if (def != null && def.getName() != null)
            {
                if (matchName(def.getName(), dataType.getName()))
                {
                    return def;
                }
            }
        }
        return null;
    }

    private static boolean matchName(List names, String name)
    {
        if (names == null || names.size() == 0 || name == null)
        {
            return false;
        }
        Iterator i = names.iterator();
        while (i.hasNext())
        {
            String n = (String)i.next();
            if (n == null)
            {
                continue;
            }
            if (n.equalsIgnoreCase(name))
            {
                return true;
            }
        }
        return false;
    }
}
