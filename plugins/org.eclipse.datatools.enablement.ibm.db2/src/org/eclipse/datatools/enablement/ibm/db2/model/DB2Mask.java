/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Mask</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getCorrelationName <em>Correlation Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getCaseExpression <em>Case Expression</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#isEnable <em>Enable</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectTable <em>Subject Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectMQT <em>Subject MQT</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectColumn <em>Subject Column</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Mask()
 * @model
 * @generated
 */
public interface DB2Mask extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Correlation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Correlation Name</em>' attribute.
	 * @see #setCorrelationName(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Mask_CorrelationName()
	 * @model
	 * @generated
	 */
	String getCorrelationName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getCorrelationName <em>Correlation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Correlation Name</em>' attribute.
	 * @see #getCorrelationName()
	 * @generated
	 */
	void setCorrelationName(String value);

	/**
	 * Returns the value of the '<em><b>Case Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Case Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Case Expression</em>' containment reference.
	 * @see #setCaseExpression(QueryExpression)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Mask_CaseExpression()
	 * @model containment="true"
	 * @generated
	 */
	QueryExpression getCaseExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getCaseExpression <em>Case Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Case Expression</em>' containment reference.
	 * @see #getCaseExpression()
	 * @generated
	 */
	void setCaseExpression(QueryExpression value);

	/**
	 * Returns the value of the '<em><b>Enable</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enable</em>' attribute.
	 * @see #setEnable(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Mask_Enable()
	 * @model default="true"
	 * @generated
	 */
	boolean isEnable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#isEnable <em>Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enable</em>' attribute.
	 * @see #isEnable()
	 * @generated
	 */
	void setEnable(boolean value);

	/**
	 * Returns the value of the '<em><b>Schema</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getMasks <em>Masks</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema</em>' reference.
	 * @see #setSchema(DB2Schema)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Mask_Schema()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getMasks
	 * @model opposite="masks" required="true"
	 * @generated
	 */
	DB2Schema getSchema();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSchema <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' reference.
	 * @see #getSchema()
	 * @generated
	 */
	void setSchema(DB2Schema value);

	/**
	 * Returns the value of the '<em><b>Subject Table</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getMasks <em>Masks</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject Table</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject Table</em>' reference.
	 * @see #setSubjectTable(DB2Table)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Mask_SubjectTable()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getMasks
	 * @model opposite="masks" required="true"
	 * @generated
	 */
	DB2Table getSubjectTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectTable <em>Subject Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subject Table</em>' reference.
	 * @see #getSubjectTable()
	 * @generated
	 */
	void setSubjectTable(DB2Table value);

	/**
	 * Returns the value of the '<em><b>Subject MQT</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#getMasks <em>Masks</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject MQT</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject MQT</em>' reference.
	 * @see #setSubjectMQT(DB2MaterializedQueryTable)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Mask_SubjectMQT()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#getMasks
	 * @model opposite="masks" required="true"
	 * @generated
	 */
	DB2MaterializedQueryTable getSubjectMQT();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectMQT <em>Subject MQT</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subject MQT</em>' reference.
	 * @see #getSubjectMQT()
	 * @generated
	 */
	void setSubjectMQT(DB2MaterializedQueryTable value);

	/**
	 * Returns the value of the '<em><b>Subject Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject Column</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject Column</em>' reference.
	 * @see #setSubjectColumn(DB2Column)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Mask_SubjectColumn()
	 * @model required="true"
	 * @generated
	 */
	DB2Column getSubjectColumn();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectColumn <em>Subject Column</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subject Column</em>' reference.
	 * @see #getSubjectColumn()
	 * @generated
	 */
	void setSubjectColumn(DB2Column value);

} // DB2Mask
