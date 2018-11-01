/**
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
 *
 * $Id: SortKey.java,v 1.4 2010/03/17 00:34:13 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sort Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A dynamic sort key specified in a SortCriteria.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getColumnIdentifier <em>Column Identifier</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getColumnName <em>Column Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getColumnPosition <em>Column Position</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getSortDirection <em>Sort Direction</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getNullValueOrdering <em>Null Value Ordering</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.SortKey#isOptional <em>Optional</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getSortKey()
 * @since 3.3 (DTP 1.8)
 * @model extendedMetaData="name='SortKey' kind='elementOnly'"
 * @generated
 */
public interface SortKey extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009, 2010 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Column Identifier</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Uniquely identifies a result set column by name and/or position.  It must reference one of the columns associated with the same ResultSetDefinition.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Column Identifier</em>' containment reference.
     * @see #setColumnIdentifier(DataElementIdentifier)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getSortKey_ColumnIdentifier()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='columnIdentifier' namespace='##targetNamespace'"
     * @generated
     * @since 3.3.2
     */
    DataElementIdentifier getColumnIdentifier();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getColumnIdentifier <em>Column Identifier</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Column Identifier</em>' containment reference.
     * @see #getColumnIdentifier()
     * @generated
     * @since 3.3.2
     */
    void setColumnIdentifier( DataElementIdentifier value );

    /**
     * Returns the value of the '<em><b>Column Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The unique name of a result set column. It must reference one of the columns associated with the same ResultSetDefinition.  If a column can only be identified by position, this name may be empty.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Column Name</em>' attribute.
     * @see #setColumnName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getSortKey_ColumnName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='columnName' namespace='##targetNamespace'"
     * @generated
     */
    String getColumnName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getColumnName <em>Column Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Column Name</em>' attribute.
     * @see #getColumnName()
     * @generated
     */
    void setColumnName( String value );

    /**
     * Returns the value of the '<em><b>Column Position</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The 1-based index position (left-to-right order) of a result set column. 
     * <!-- end-model-doc -->
     * @return the value of the '<em>Column Position</em>' attribute.
     * @see #isSetColumnPosition()
     * @see #unsetColumnPosition()
     * @see #setColumnPosition(int)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getSortKey_ColumnPosition()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.UnsignedShort"
     *        extendedMetaData="kind='element' name='columnPosition' namespace='##targetNamespace'"
     * @generated
     */
    int getColumnPosition();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getColumnPosition <em>Column Position</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Column Position</em>' attribute.
     * @see #isSetColumnPosition()
     * @see #unsetColumnPosition()
     * @see #getColumnPosition()
     * @generated
     */
    void setColumnPosition( int value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getColumnPosition <em>Column Position</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetColumnPosition()
     * @see #getColumnPosition()
     * @see #setColumnPosition(int)
     * @generated
     */
    void unsetColumnPosition();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getColumnPosition <em>Column Position</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Column Position</em>' attribute is set.
     * @see #unsetColumnPosition()
     * @see #getColumnPosition()
     * @see #setColumnPosition(int)
     * @generated
     */
    boolean isSetColumnPosition();

    /**
     * Returns the value of the '<em><b>Sort Direction</b></em>' attribute.
     * The default value is <code>"Ascending"</code>.
     * The literals are from the enumeration {@link org.eclipse.datatools.connectivity.oda.design.SortDirectionType}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The sort direction of this result set column.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Sort Direction</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.SortDirectionType
     * @see #isSetSortDirection()
     * @see #unsetSortDirection()
     * @see #setSortDirection(SortDirectionType)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getSortKey_SortDirection()
     * @model default="Ascending" unsettable="true"
     *        extendedMetaData="kind='element' name='sortDirection' namespace='##targetNamespace'"
     * @generated
     */
    SortDirectionType getSortDirection();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getSortDirection <em>Sort Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Sort Direction</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.SortDirectionType
     * @see #isSetSortDirection()
     * @see #unsetSortDirection()
     * @see #getSortDirection()
     * @generated
     */
    void setSortDirection( SortDirectionType value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getSortDirection <em>Sort Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetSortDirection()
     * @see #getSortDirection()
     * @see #setSortDirection(SortDirectionType)
     * @generated
     */
    void unsetSortDirection();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getSortDirection <em>Sort Direction</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Sort Direction</em>' attribute is set.
     * @see #unsetSortDirection()
     * @see #getSortDirection()
     * @see #setSortDirection(SortDirectionType)
     * @generated
     */
    boolean isSetSortDirection();

    /**
     * Returns the value of the '<em><b>Null Value Ordering</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.connectivity.oda.design.NullOrderingType}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The ordering of null vs. non-null values in the sort order.  Default value is "Unknown", i.e. not specified.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Null Value Ordering</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.NullOrderingType
     * @see #isSetNullValueOrdering()
     * @see #unsetNullValueOrdering()
     * @see #setNullValueOrdering(NullOrderingType)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getSortKey_NullValueOrdering()
     * @model unsettable="true"
     *        extendedMetaData="kind='element' name='nullValueOrdering' namespace='##targetNamespace'"
     * @generated
     */
    NullOrderingType getNullValueOrdering();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getNullValueOrdering <em>Null Value Ordering</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Null Value Ordering</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.NullOrderingType
     * @see #isSetNullValueOrdering()
     * @see #unsetNullValueOrdering()
     * @see #getNullValueOrdering()
     * @generated
     */
    void setNullValueOrdering( NullOrderingType value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getNullValueOrdering <em>Null Value Ordering</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetNullValueOrdering()
     * @see #getNullValueOrdering()
     * @see #setNullValueOrdering(NullOrderingType)
     * @generated
     */
    void unsetNullValueOrdering();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.SortKey#getNullValueOrdering <em>Null Value Ordering</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Null Value Ordering</em>' attribute is set.
     * @see #unsetNullValueOrdering()
     * @see #getNullValueOrdering()
     * @see #setNullValueOrdering(NullOrderingType)
     * @generated
     */
    boolean isSetNullValueOrdering();

    /**
     * Returns the value of the '<em><b>Optional</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Indicates whether this sort key can be excluded at runtime.  Default value is false.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Optional</em>' attribute.
     * @see #isSetOptional()
     * @see #unsetOptional()
     * @see #setOptional(boolean)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getSortKey_Optional()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='optional' namespace='##targetNamespace'"
     * @generated
     */
    boolean isOptional();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.SortKey#isOptional <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Optional</em>' attribute.
     * @see #isSetOptional()
     * @see #unsetOptional()
     * @see #isOptional()
     * @generated
     */
    void setOptional( boolean value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.SortKey#isOptional <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetOptional()
     * @see #isOptional()
     * @see #setOptional(boolean)
     * @generated
     */
    void unsetOptional();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.SortKey#isOptional <em>Optional</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Optional</em>' attribute is set.
     * @see #unsetOptional()
     * @see #isOptional()
     * @see #setOptional(boolean)
     * @generated
     */
    boolean isSetOptional();

} // SortKey
