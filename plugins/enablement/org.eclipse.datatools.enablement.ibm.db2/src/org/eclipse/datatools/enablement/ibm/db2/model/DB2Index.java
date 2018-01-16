/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.constraints.Index;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Index</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#getIndexType <em>Index Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#isBusPeriodWithoutOverlap <em>Bus Period Without Overlap</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#isEncodedVector <em>Encoded Vector</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#getDB2MultidimensionalIndex <em>DB2 Multidimensional Index</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Index()
 * @model
 * @generated
 */
public interface DB2Index extends Index {
	/**
	 * Returns the value of the '<em><b>Index Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType
	 * @see #setIndexType(DB2IndexType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Index_IndexType()
	 * @model
	 * @generated
	 */
	DB2IndexType getIndexType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#getIndexType <em>Index Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType
	 * @see #getIndexType()
	 * @generated
	 */
	void setIndexType(DB2IndexType value);

	/**
	 * Returns the value of the '<em><b>Bus Period Without Overlap</b></em>' attribute.
	 * The default value is <code>"False"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bus Period Without Overlap</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bus Period Without Overlap</em>' attribute.
	 * @see #setBusPeriodWithoutOverlap(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Index_BusPeriodWithoutOverlap()
	 * @model default="False"
	 * @generated
	 */
	boolean isBusPeriodWithoutOverlap();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#isBusPeriodWithoutOverlap <em>Bus Period Without Overlap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus Period Without Overlap</em>' attribute.
	 * @see #isBusPeriodWithoutOverlap()
	 * @generated
	 */
	void setBusPeriodWithoutOverlap(boolean value);

	/**
	 * Returns the value of the '<em><b>Encoded Vector</b></em>' attribute.
	 * The default value is <code>"False"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Encoded Vector</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Encoded Vector</em>' attribute.
	 * @see #setEncodedVector(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Index_EncodedVector()
	 * @model default="False"
	 * @generated
	 */
	boolean isEncodedVector();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#isEncodedVector <em>Encoded Vector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Encoded Vector</em>' attribute.
	 * @see #isEncodedVector()
	 * @generated
	 */
	void setEncodedVector(boolean value);

	/**
	 * Returns the value of the '<em><b>DB2 Multidimensional Index</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MultidimensionalIndex#getDimensionIndexes <em>Dimension Indexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>DB2 Multidimensional Index</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>DB2 Multidimensional Index</em>' reference.
	 * @see #setDB2MultidimensionalIndex(DB2MultidimensionalIndex)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Index_DB2MultidimensionalIndex()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2MultidimensionalIndex#getDimensionIndexes
	 * @model opposite="dimensionIndexes" required="true"
	 * @generated
	 */
	DB2MultidimensionalIndex getDB2MultidimensionalIndex();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#getDB2MultidimensionalIndex <em>DB2 Multidimensional Index</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>DB2 Multidimensional Index</em>' reference.
	 * @see #getDB2MultidimensionalIndex()
	 * @generated
	 */
	void setDB2MultidimensionalIndex(DB2MultidimensionalIndex value);

} // DB2Index
