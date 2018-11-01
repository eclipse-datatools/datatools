/**
 *************************************************************************
 * Copyright (c) 2010 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: ResultSubset.java,v 1.1 2010/10/15 05:41:37 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Result Subset</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines a subset of columns in a data set design's result set.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ResultSubset#getDataSetDesign <em>Data Set Design</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ResultSubset#getResultSetName <em>Result Set Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ResultSubset#getColumnIdentifiers <em>Column Identifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSubset()
 * @model extendedMetaData="name='ResultSubset' kind='elementOnly'"
 * @generated
 * @since 3.3.2
 */
public interface ResultSubset extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Data Set Design</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A nested instance of dataSetDesign to use for retrieving the referenced data set column.  If not specified, the same data set design that contains this design element would apply by default.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Data Set Design</em>' containment reference.
     * @see #setDataSetDesign(DataSetDesign)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSubset_DataSetDesign()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='dataSetDesign' namespace='##targetNamespace'"
     * @generated
     */
    DataSetDesign getDataSetDesign();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResultSubset#getDataSetDesign <em>Data Set Design</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Set Design</em>' containment reference.
     * @see #getDataSetDesign()
     * @generated
     */
    void setDataSetDesign( DataSetDesign value );

    /**
     * Returns the value of the '<em><b>Result Set Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Identifies the result set of the data set design if more than one result sets are available, and can be identified by name.  If no results set is explicitly specified here, the primary result set would apply by default.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Result Set Name</em>' attribute.
     * @see #setResultSetName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSubset_ResultSetName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='resultSetName' namespace='##targetNamespace'"
     * @generated
     */
    String getResultSetName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResultSubset#getResultSetName <em>Result Set Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Result Set Name</em>' attribute.
     * @see #getResultSetName()
     * @generated
     */
    void setResultSetName( String value );

    /**
     * Returns the value of the '<em><b>Column Identifiers</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Uniquely identifies one or more columns in the specified result set.  Multiple columns are combined in the specified sequence, such as for a compounded attribute.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Column Identifiers</em>' containment reference.
     * @see #setColumnIdentifiers(DataElementIdentifiers)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSubset_ColumnIdentifiers()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='columnIdentifiers' namespace='##targetNamespace'"
     * @generated
     */
    DataElementIdentifiers getColumnIdentifiers();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResultSubset#getColumnIdentifiers <em>Column Identifiers</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Column Identifiers</em>' containment reference.
     * @see #getColumnIdentifiers()
     * @generated
     */
    void setColumnIdentifiers( DataElementIdentifiers value );

    /**
     * Appends the specified column, identified by name only, to the end
     * of the '<em><b>Column Identifiers</b></em>' containment reference list.
     * @param columnName the name of column identifier to be appended
     * @generated NOT
     */
    void addColumnIdentifier( String columnName );

    /**
     * Appends the specified column, identified by name and position, to the end
     * of the '<em><b>Column Identifiers</b></em>' containment reference list.
     * @param columnName the name of column identifier to be appended;
     *          the name may be empty if the column can only be identified by position
     * @param columnPosition the 1-based position of the column within a result set
     * @generated NOT
     */
    void addColumnIdentifier( String columnName, int columnPosition );

    /**
     * Appends the specified column identifier to the end
     * of the '<em><b>Column Identifiers</b></em>' containment reference list.
     * @param columnIdentifier the column identifier to be appended
     * @generated NOT
     */
    void addColumnIdentifier( DataElementIdentifier columnIdentifier );

} // ResultSubset
