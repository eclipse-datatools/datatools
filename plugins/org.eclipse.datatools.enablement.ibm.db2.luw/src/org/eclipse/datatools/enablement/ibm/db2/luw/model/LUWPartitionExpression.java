/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWPartitionExpression.java,v 1.5 2007/10/12 23:05:35 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Partition Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#isNullsLast <em>Nulls Last</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#getKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#getColumn <em>Column</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#getPartitionElements <em>Partition Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionExpression()
 * @model
 * @generated
 */
public interface LUWPartitionExpression extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Nulls Last</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nulls Last</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nulls Last</em>' attribute.
	 * @see #setNullsLast(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionExpression_NullsLast()
	 * @model default="true"
	 * @generated
	 */
	boolean isNullsLast();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#isNullsLast <em>Nulls Last</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nulls Last</em>' attribute.
	 * @see #isNullsLast()
	 * @generated
	 */
	void setNullsLast(boolean value);

	/**
	 * Returns the value of the '<em><b>Key</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey#getPartitionExpressions <em>Partition Expressions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' container reference.
	 * @see #setKey(LUWDataPartitionKey)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionExpression_Key()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey#getPartitionExpressions
	 * @model opposite="partitionExpressions" required="true"
	 * @generated
	 */
	LUWDataPartitionKey getKey();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#getKey <em>Key</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' container reference.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(LUWDataPartitionKey value);

	/**
	 * Returns the value of the '<em><b>Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column</em>' reference.
	 * @see #setColumn(Column)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionExpression_Column()
	 * @model required="true"
	 * @generated
	 */
	Column getColumn();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#getColumn <em>Column</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column</em>' reference.
	 * @see #getColumn()
	 * @generated
	 */
	void setColumn(Column value);

	/**
	 * Returns the value of the '<em><b>Partition Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getLUWPartitionExpression <em>LUW Partition Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partition Elements</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionExpression_PartitionElements()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getLUWPartitionExpression
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement" opposite="LUWPartitionExpression" containment="true" required="true"
	 * @generated
	 */
	EList getPartitionElements();

} // LUWPartitionExpression
