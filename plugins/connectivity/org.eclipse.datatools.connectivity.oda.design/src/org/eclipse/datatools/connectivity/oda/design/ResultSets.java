/**
 *************************************************************************
 * Copyright (c) 2005, 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: ResultSets.java,v 1.3 2007/04/11 02:59:53 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A collection of result sets' definition and metadata. If the metadata can be derived, i.e. can be obtained by an ODA driver in each design session, an ODA host designer is not required to include the derived metadata in the next design session request.  An ODA designer may ignore such metadata in a Request.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ResultSets#getResultSetDefinitions <em>Result Set Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ResultSets#isDerivedMetaData <em>Derived Meta Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSets()
 * @model extendedMetaData="name='ResultSets' kind='elementOnly'"
 * @generated
 */
public interface ResultSets extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Finds and returns a named result set definition that
     * matches the specified name.
     * This only matches against those result sets that 
     * have a name defined.  
     * A result set name is case-sensitive.
     * @param resultSetName
     * @return  the matching result set definition; or null
     *          if no match is found
     * @generated NOT
     */
    ResultSetDefinition findResultSetDefinition( String resultSetName );

    /**
     * Inserts the specified result set definition 
     * at the specified position in 
     * the '<em><b>Result Set Definitions</b></em>' containment reference list.
     * Shifts the element currently at that position 
     * (if any) and any subsequent elements to the right 
     * (adds one to their indices). 
     * @param index the 0-based index at which the specified element is to be inserted
     * @param resultSetDefn result set definition to be inserted
     * @generated NOT
     */
    void addResultSetDefinition( int index, ResultSetDefinition resultSetDefn );

    /**
     * Appends the specified result set definition to the end
     * of the '<em><b>Result Set Definitions</b></em>' containment reference list.
     * @param resultSetDefn result set definition to be inserted
     * @generated NOT
     */
    void addResultSetDefinition( ResultSetDefinition resultSetDefn );

    /**
     * Returns the value of the '<em><b>Result Set Definitions</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Result Set Definitions</em>' containment reference list.
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSets_ResultSetDefinitions()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='resultSetDefinitions' namespace='##targetNamespace'"
     * @generated
     */
    EList<ResultSetDefinition> getResultSetDefinitions();

    /**
     * Returns the value of the '<em><b>Derived Meta Data</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * If the metadata can be derived, i.e. can be obtained by an ODA driver in each design session, an ODA host designer is not required to include the derived metadata in the next design session request.  An ODA designer may ignore such metadata in a Request.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Derived Meta Data</em>' attribute.
     * @see #isSetDerivedMetaData()
     * @see #unsetDerivedMetaData()
     * @see #setDerivedMetaData(boolean)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSets_DerivedMetaData()
     * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='derivedMetaData'"
     * @generated
     */
    boolean isDerivedMetaData();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResultSets#isDerivedMetaData <em>Derived Meta Data</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Derived Meta Data</em>' attribute.
     * @see #isSetDerivedMetaData()
     * @see #unsetDerivedMetaData()
     * @see #isDerivedMetaData()
     * @generated
     */
    void setDerivedMetaData( boolean value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResultSets#isDerivedMetaData <em>Derived Meta Data</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetDerivedMetaData()
     * @see #isDerivedMetaData()
     * @see #setDerivedMetaData(boolean)
     * @generated
     */
    void unsetDerivedMetaData();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResultSets#isDerivedMetaData <em>Derived Meta Data</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Derived Meta Data</em>' attribute is set.
     * @see #unsetDerivedMetaData()
     * @see #isDerivedMetaData()
     * @see #setDerivedMetaData(boolean)
     * @generated
     */
    boolean isSetDerivedMetaData();

} // ResultSets
