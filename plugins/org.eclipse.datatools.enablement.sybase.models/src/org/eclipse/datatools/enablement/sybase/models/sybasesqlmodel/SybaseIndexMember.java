/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel;

import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase Index Member</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseIndexMember#getColumnExpression <em>Column Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage#getSybaseIndexMember()
 * @model
 * @generated
 */
public interface SybaseIndexMember extends IndexMember {
	/**
	 * Returns the value of the '<em><b>Column Expression</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column Expression</em>' attribute.
	 * @see #setColumnExpression(String)
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage#getSybaseIndexMember_ColumnExpression()
	 * @model default=""
	 * @generated
	 */
	String getColumnExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseIndexMember#getColumnExpression <em>Column Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column Expression</em>' attribute.
	 * @see #getColumnExpression()
	 * @generated
	 */
	void setColumnExpression(String value);

} // SybaseIndexMember
