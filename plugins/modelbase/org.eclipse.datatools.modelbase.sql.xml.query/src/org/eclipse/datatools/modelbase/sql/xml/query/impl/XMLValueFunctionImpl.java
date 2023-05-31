/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionImpl.java,v 1.5 2007/02/08 17:04:21 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionFunctionImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunction;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Value Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class XMLValueFunctionImpl extends ValueExpressionFunctionImpl implements XMLValueFunction {
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLValueFunctionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_FUNCTION;
    }

} //XMLValueFunctionImpl
