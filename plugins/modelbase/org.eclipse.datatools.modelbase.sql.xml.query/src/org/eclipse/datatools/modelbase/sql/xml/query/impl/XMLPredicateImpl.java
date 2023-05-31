/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLPredicateImpl.java,v 1.5 2007/02/08 17:04:21 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import org.eclipse.datatools.modelbase.sql.query.impl.PredicateImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicate;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Predicate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class XMLPredicateImpl extends PredicateImpl implements XMLPredicate {
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLPredicateImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_PREDICATE;
    }

} //XMLPredicateImpl
