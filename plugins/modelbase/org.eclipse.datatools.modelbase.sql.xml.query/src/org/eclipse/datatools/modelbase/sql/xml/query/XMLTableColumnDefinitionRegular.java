/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLTableColumnDefinitionRegular.java,v 1.2 2005/12/22 22:21:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Table Column Definition Regular</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getPassingOption <em>Passing Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getTableColumnPattern <em>Table Column Pattern</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getColumnDefinitionDefault <em>Column Definition Default</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLTableColumnDefinitionRegular()
 * @model
 * @generated
 */
public interface XMLTableColumnDefinitionRegular extends XMLTableColumnDefinitionItem{
	/**
     * Returns the value of the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Type</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Type</em>' containment reference.
     * @see #setDataType(DataType)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLTableColumnDefinitionRegular_DataType()
     * @model containment="true"
     * @generated
     */
    DataType getDataType();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getDataType <em>Data Type</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Type</em>' containment reference.
     * @see #getDataType()
     * @generated
     */
    void setDataType(DataType value);

	/**
     * Returns the value of the '<em><b>Passing Option</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Passing Option</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Passing Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType
     * @see #setPassingOption(XMLPassingType)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLTableColumnDefinitionRegular_PassingOption()
     * @model
     * @generated
     */
    XMLPassingType getPassingOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getPassingOption <em>Passing Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Passing Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType
     * @see #getPassingOption()
     * @generated
     */
    void setPassingOption(XMLPassingType value);

	/**
     * Returns the value of the '<em><b>Table Column Pattern</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Column Pattern</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Column Pattern</em>' attribute.
     * @see #setTableColumnPattern(String)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLTableColumnDefinitionRegular_TableColumnPattern()
     * @model
     * @generated
     */
    String getTableColumnPattern();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getTableColumnPattern <em>Table Column Pattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Column Pattern</em>' attribute.
     * @see #getTableColumnPattern()
     * @generated
     */
    void setTableColumnPattern(String value);

	/**
     * Returns the value of the '<em><b>Column Definition Default</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault#getColumnDefinitionRegular <em>Column Definition Regular</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Column Definition Default</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Column Definition Default</em>' containment reference.
     * @see #setColumnDefinitionDefault(XMLTableColumnDefinitionDefault)
     * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage#getXMLTableColumnDefinitionRegular_ColumnDefinitionDefault()
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault#getColumnDefinitionRegular
     * @model opposite="columnDefinitionRegular" containment="true"
     * @generated
     */
    XMLTableColumnDefinitionDefault getColumnDefinitionDefault();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getColumnDefinitionDefault <em>Column Definition Default</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Column Definition Default</em>' containment reference.
     * @see #getColumnDefinitionDefault()
     * @generated
     */
    void setColumnDefinitionDefault(XMLTableColumnDefinitionDefault value);

} // XMLTableColumnDefinitionRegular
