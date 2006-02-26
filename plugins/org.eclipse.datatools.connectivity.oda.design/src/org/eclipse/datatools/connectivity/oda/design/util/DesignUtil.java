/**
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: DesignUtil.java,v 1.2 2006/02/22 04:34:12 lchan Exp $
 */

package org.eclipse.datatools.connectivity.oda.design.util;

import java.util.Iterator;
import java.util.Map.Entry;

import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.Properties;
import org.eclipse.datatools.connectivity.oda.design.Property;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;

/**
 * An utility class to handle the ODA Design API objects.
 */
public class DesignUtil
{
    
    // class has static methods only; not intended to instantiate
    private DesignUtil()
    {
    }
    
    /**
     * Validates the given EMF object is valid and has all required
     * elements. 
     * @param eObject   an EMF object
     * @throws IllegalStateException    if the given object is not valid,
     *                  and includes error and warning messages in exception
     */
    public static void validateObject( EObject eObject )
        throws IllegalStateException
    {
        Diagnostic diagnostic = diagnoseObject( eObject );
        if( diagnostic == null )
            return;     // no error or warning
        
        String errMsg = getErrorOrWarningMessages( diagnostic );

        // TODO - migrate to throw DiagnosticException when migrate to EMF 2.2
        throw new IllegalStateException( errMsg );
    }

    /**
     * Diagnoses the given EMF object, and returns the diagnostic result
     * if any error or warning is found. 
     * @param eObject   an EMF object
     * @return  the diagnostic result, or null if no error or 
     *          warning is found
     */
    public static Diagnostic diagnoseObject( EObject eObject )
    {
        Diagnostic diagnostic = Diagnostician.INSTANCE.validate( eObject );
        if( diagnostic.getSeverity() == Diagnostic.ERROR
                || diagnostic.getSeverity() == Diagnostic.WARNING )
            return diagnostic;
        else
            return null;
    }
    
    /**
     * Concatenates and returns all error and warning messages 
     * from given diagnostic.
     * @param diagnostic
     * @return
     */
    private static String getErrorOrWarningMessages( Diagnostic diagnostic )
    {
        if( diagnostic == null )
            return null;

        String errMsg = diagnostic.getMessage();
        for( Iterator i = diagnostic.getChildren().iterator(); i.hasNext();)
        {
            Diagnostic childDiagnostic = (Diagnostic) i.next();
            switch( childDiagnostic.getSeverity() )
            {
                case Diagnostic.ERROR:
                case Diagnostic.WARNING:
                    errMsg += "\n" + childDiagnostic.getMessage(); //$NON-NLS-1$
            }
        }

        return errMsg;
    }
    
    /**
     * Converts the public and private properties defined in an 
     * ODA data source design definition, and returns in a combined
     * java.util.Properties collection.
     * @param dataSourceDesign
     * @return
     */
    public static java.util.Properties convertDataSourceProperties( DataSourceDesign dataSourceDesign )
    {
        java.util.Properties publicProps = convertDesignProperties( 
                dataSourceDesign.getPublicProperties() );
        java.util.Properties propCollection = convertDesignProperties( 
                dataSourceDesign.getPrivateProperties() );

        // merge public and private properties, with public values
        // taking precedence if same property key is used
        propCollection.putAll( publicProps );
        return propCollection;        
    }

    /**
     * Converts the given design properties defined in an 
     * ODA data source or data set design definition, to
     * a java.util.Properties collection.
     * @param designProps   the ODA design properties to convert from
     * @return              converted properties in a java.util.Properties collection
     */
    public static java.util.Properties convertDesignProperties( Properties designProps )
    {
        java.util.Properties utilProps = new java.util.Properties();
        if( designProps == null || designProps.getProperties() == null )
            return utilProps;  // return an empty collection
        
        Iterator itr = designProps.getProperties().iterator();
        while( itr.hasNext() )
        {
            Property designProp = (Property) itr.next();

            // util collection does not allow null name or value;
            // excludes the property in such case, which would allow
            // consumer to get a null value 
            // when a property name is not found in collection
            if( designProp.getNameValue() == null ||
                designProp.getNameValue().getName() == null ||
                designProp.getNameValue().getValue() == null )
                continue;   // skip property
            
            utilProps.setProperty( designProp.getNameValue().getName(),
                                   designProp.getNameValue().getValue() );
        }
        return utilProps;
    }
    
    /**
     * Converts the given java.util.Properties collection 
     * to ODA design properties that can be applied in an 
     * ODA data source or data set design definition.
     * @param utilProps the java.util.Properties collection to convert from
     * @return          converted properties in a design properties collection
     */
    public static Properties convertToDesignProperties( 
                                java.util.Properties utilProps )
    {
        Properties designProps = 
            DesignFactory.eINSTANCE.createProperties();
        if( utilProps == null || utilProps.size() == 0 )
            return designProps;  // return an empty collection
        
        Iterator iter = utilProps.entrySet().iterator();
        while( iter.hasNext() )
        {
            Entry utilProp = (Entry) iter.next();
            designProps.setProperty( (String) utilProp.getKey(), 
                                    (String) utilProp.getValue() );
        }

        return designProps;   
    }
    
}
