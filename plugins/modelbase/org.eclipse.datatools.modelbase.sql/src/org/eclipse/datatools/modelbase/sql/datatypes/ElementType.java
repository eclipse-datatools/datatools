/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.datatypes;

import org.eclipse.datatools.modelbase.sql.schema.TypedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.ElementType#getCollectionDataType <em>Collection Data Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getElementType()
 * @model
 * @generated
 */
public interface ElementType extends TypedElement {
	/**
	 * Returns the value of the '<em><b>Collection Data Type</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.datatypes.CollectionDataType#getElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collection Data Type</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collection Data Type</em>' container reference.
	 * @see #setCollectionDataType(CollectionDataType)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getElementType_CollectionDataType()
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CollectionDataType#getElementType
	 * @model opposite="elementType"
	 * @generated
	 */
	CollectionDataType getCollectionDataType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.ElementType#getCollectionDataType <em>Collection Data Type</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collection Data Type</em>' container reference.
	 * @see #getCollectionDataType()
	 * @generated
	 */
	void setCollectionDataType(CollectionDataType value);

} // ElementType
