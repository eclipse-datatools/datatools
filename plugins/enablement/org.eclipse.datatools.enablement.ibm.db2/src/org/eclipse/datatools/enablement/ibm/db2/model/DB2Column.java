/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2Column.java,v 1.6 2008/04/28 20:54:22 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.tables.Column;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#getGenerationType <em>Generation Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isRowChangeTimestamp <em>Row Change Timestamp</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isRowBegin <em>Row Begin</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isRowEnd <em>Row End</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isTransStartID <em>Trans Start ID</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#getBeginPeriod <em>Begin Period</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#getEndPeriod <em>End Period</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Column()
 * @model
 * @generated
 */
public interface DB2Column extends Column {
	/**
	 * Returns the value of the '<em><b>Generation Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.model.GenerateType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generation Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generation Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.GenerateType
	 * @see #setGenerationType(GenerateType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Column_GenerationType()
	 * @model
	 * @generated
	 */
	GenerateType getGenerationType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#getGenerationType <em>Generation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generation Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.GenerateType
	 * @see #getGenerationType()
	 * @generated
	 */
	void setGenerationType(GenerateType value);

	/**
	 * Returns the value of the '<em><b>Row Change Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Row Change Timestamp</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row Change Timestamp</em>' attribute.
	 * @see #setRowChangeTimestamp(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Column_RowChangeTimestamp()
	 * @model
	 * @generated
	 */
	boolean isRowChangeTimestamp();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isRowChangeTimestamp <em>Row Change Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Row Change Timestamp</em>' attribute.
	 * @see #isRowChangeTimestamp()
	 * @generated
	 */
	void setRowChangeTimestamp(boolean value);

	/**
	 * Returns the value of the '<em><b>Row Begin</b></em>' attribute.
	 * The default value is <code>"False"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Row Begin</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row Begin</em>' attribute.
	 * @see #setRowBegin(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Column_RowBegin()
	 * @model default="False"
	 * @generated
	 */
	boolean isRowBegin();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isRowBegin <em>Row Begin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Row Begin</em>' attribute.
	 * @see #isRowBegin()
	 * @generated
	 */
	void setRowBegin(boolean value);

	/**
	 * Returns the value of the '<em><b>Row End</b></em>' attribute.
	 * The default value is <code>"False"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Row End</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row End</em>' attribute.
	 * @see #setRowEnd(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Column_RowEnd()
	 * @model default="False"
	 * @generated
	 */
	boolean isRowEnd();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isRowEnd <em>Row End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Row End</em>' attribute.
	 * @see #isRowEnd()
	 * @generated
	 */
	void setRowEnd(boolean value);

	/**
	 * Returns the value of the '<em><b>Trans Start ID</b></em>' attribute.
	 * The default value is <code>"False"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trans Start ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trans Start ID</em>' attribute.
	 * @see #setTransStartID(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Column_TransStartID()
	 * @model default="False"
	 * @generated
	 */
	boolean isTransStartID();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isTransStartID <em>Trans Start ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trans Start ID</em>' attribute.
	 * @see #isTransStartID()
	 * @generated
	 */
	void setTransStartID(boolean value);

	/**
	 * Returns the value of the '<em><b>Begin Period</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Period#getBeginColumn <em>Begin Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Begin Period</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Begin Period</em>' reference.
	 * @see #setBeginPeriod(DB2Period)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Column_BeginPeriod()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Period#getBeginColumn
	 * @model opposite="beginColumn"
	 * @generated
	 */
	DB2Period getBeginPeriod();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#getBeginPeriod <em>Begin Period</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Begin Period</em>' reference.
	 * @see #getBeginPeriod()
	 * @generated
	 */
	void setBeginPeriod(DB2Period value);

	/**
	 * Returns the value of the '<em><b>End Period</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Period#getEndColumn <em>End Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Period</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Period</em>' reference.
	 * @see #setEndPeriod(DB2Period)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Column_EndPeriod()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Period#getEndColumn
	 * @model opposite="endColumn"
	 * @generated
	 */
	DB2Period getEndPeriod();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#getEndPeriod <em>End Period</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Period</em>' reference.
	 * @see #getEndPeriod()
	 * @generated
	 */
	void setEndPeriod(DB2Period value);
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	boolean isGenerated();

} // DB2Column
