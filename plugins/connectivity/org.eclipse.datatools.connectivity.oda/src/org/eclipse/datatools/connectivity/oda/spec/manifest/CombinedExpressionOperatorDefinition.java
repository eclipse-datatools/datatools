/*
 *************************************************************************
 * Copyright (c) 2009, 2010 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.spec.manifest;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.CombinedValueExpression;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.CombinedValueExpressionOperator;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.InternalValueExprFactory;

/**
 * Represents the definition of a value expression combined operator type, as specified in an extension of the
 * <i>org.eclipse.datatools.connectivity.oda.dynamicResultSet</i> extension point.
 * @since 3.2.2 (DTP 1.7.2)
 */
public class CombinedExpressionOperatorDefinition
{
    // element and attribute tags defined in the extension point schema definition
    static final String SUPPORTED_ELEMENT_NAME = "supportedOdaCombinedOperator"; //$NON-NLS-1$
    static final String CUSTOM_ELEMENT_NAME = "combinedOperatorType"; //$NON-NLS-1$
    protected static final String ATTR_ID = "id"; //$NON-NLS-1$
    protected static final String ATTR_LITERAL = "literal"; //$NON-NLS-1$
    protected static final String ATTR_CLASS = "class"; //$NON-NLS-1$

    private String m_id;
    private CombinedValueExpressionOperator m_customInstance;

    CombinedExpressionOperatorDefinition( IConfigurationElement exprElement ) throws OdaException
    {
        init( exprElement );
    }

    private CombinedExpressionOperatorDefinition() {}
    
    private void init( IConfigurationElement opElement ) throws OdaException
    {
        ResultExtensionUtil.validateConfigurationElement( opElement );
        
        m_id = getIdAttributeValue( opElement );;
        
        m_customInstance = createCustomOperator( opElement );
    }
    
    /**
     * For internal use only.
     */
    public static String getIdAttributeValue( IConfigurationElement opElement ) throws OdaException
    {
        return ResultExtensionUtil.getRequiredAttributeValue( opElement, ATTR_ID, SUPPORTED_ELEMENT_NAME );
    }

    /**
     * For internal use only.
     */
    public static String getCustomLiteralAttributeValue( IConfigurationElement opElement ) throws OdaException
    {
        // may return null;
        // the literal attribute only exists in custom combinedOperatorType element
        return opElement.getAttribute( ATTR_LITERAL );
    }
    
    protected CombinedValueExpressionOperator createCustomOperator( IConfigurationElement opElement )
        throws OdaException
    {
        String className = opElement.getAttribute( ATTR_CLASS );
        if( className == null || className.length() == 0 )
            return null;

        // create an instance of the specified class attribute
        try
        {
            Object clazz = opElement.createExecutableExtension( ATTR_CLASS );
            if( clazz instanceof CombinedValueExpressionOperator )
            {
                CombinedValueExpressionOperator customInstance = (CombinedValueExpressionOperator) clazz;
                return customInstance;
            }
            else
                throw new OdaException( Messages.bind( Messages.querySpec_INVALID_CLASS_TYPE_ATTRIBUTE, 
                        new Object[]{ className, ATTR_CLASS, CombinedValueExpressionOperator.class.getName()} ));
        }
        catch( CoreException ex )
        {
            throw new OdaException( ex );
        }
    }
    
    public String getId()
    {
        return m_id;
    }
    
    public String getDisplayName()
    {
        return getId();
    }
    
    public boolean isBuiltInOperator()
    {
        return true;
    }
    
    /**
     * Returns an instance of ValueExpressionCombinedOperator for use in an ODA {@link CombinedValueExpression},
     * based on the class attribute specified in this definition.
     * @return  an instance of {@link CombinedValueExpressionOperator} or its subclass
     */
    public CombinedValueExpressionOperator getOperator()
    {
        if( m_customInstance != null )
            return m_customInstance;
        return CombinedValueExpressionOperator.get( m_id );
    }

    static CustomCombinedOperatorDefinition newCustomDefinition( IConfigurationElement opElement ) 
        throws OdaException
    {
        return (new CombinedExpressionOperatorDefinition()).new CustomCombinedOperatorDefinition( opElement );
    }
    
    public class CustomCombinedOperatorDefinition extends CombinedExpressionOperatorDefinition
    {
        private static final String ATTR_NAME = "displayName"; //$NON-NLS-1$

        private String m_name;
        
        private CustomCombinedOperatorDefinition( IConfigurationElement opElement )
            throws OdaException
        {
            super( opElement );            
            m_name = opElement.getAttribute( ATTR_NAME );
        }
        
        protected CombinedValueExpressionOperator createCustomOperator( IConfigurationElement opElement )
            throws OdaException
        {
            // create an instance of custom class, if specified
            CombinedValueExpressionOperator customOp = super.createCustomOperator( opElement );

            // if no custom class specified, use the base class
            if( customOp == null )
            {
                // literal attribute is required in custom combinedOperatorType element
                String literal = ResultExtensionUtil.getRequiredAttributeValue( opElement, ATTR_LITERAL, 
                        CUSTOM_ELEMENT_NAME );
                customOp = InternalValueExprFactory.createCombinedOperator( getId(), literal );
            }
            return customOp;
        }
        
        @Override
        public String getDisplayName()
        {
            if( m_name != null && m_name.trim().length() > 0 )
                return m_name;
            return m_id;    // default to the operator id if no name is specified.
        }

        @Override
        public boolean isBuiltInOperator()
        {
            return false;
        }        
    }
    
}
