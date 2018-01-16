/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel;

import org.eclipse.datatools.modelbase.sql.routines.Parameter;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This class is introduced to work around the problem mentioned in BZ129326. When the desired attributes are added in the base model, this class can be removed.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter#isNullable <em>Nullable</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter#getJDBCParameterType <em>JDBC Parameter Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage#getSybaseParameter()
 * @model
 * @generated
 */
public interface SybaseParameter extends Parameter {
	/**
	 * Returns the value of the '<em><b>Nullable</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nullable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nullable</em>' attribute.
	 * @see #setNullable(boolean)
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage#getSybaseParameter_Nullable()
	 * @model default="true"
	 * @generated
	 */
	boolean isNullable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter#isNullable <em>Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nullable</em>' attribute.
	 * @see #isNullable()
	 * @generated
	 */
	void setNullable(boolean value);

	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value</em>' attribute.
	 * @see #setDefaultValue(String)
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage#getSybaseParameter_DefaultValue()
	 * @model
	 * @generated
	 */
	String getDefaultValue();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter#getDefaultValue <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' attribute.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(String value);

	/**
	 * Returns the value of the '<em><b>JDBC Parameter Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>JDBC Parameter Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>JDBC Parameter Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType
	 * @see #setJDBCParameterType(JDBCParameterType)
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage#getSybaseParameter_JDBCParameterType()
	 * @model
	 * @generated
	 */
	JDBCParameterType getJDBCParameterType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter#getJDBCParameterType <em>JDBC Parameter Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>JDBC Parameter Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType
	 * @see #getJDBCParameterType()
	 * @generated
	 */
	void setJDBCParameterType(JDBCParameterType value);

} // SybaseParameter
