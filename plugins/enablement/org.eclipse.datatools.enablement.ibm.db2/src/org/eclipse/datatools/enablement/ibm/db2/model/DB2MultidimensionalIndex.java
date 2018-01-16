/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Multidimensional Index</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MultidimensionalIndex#getDimensionIndexes <em>Dimension Indexes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2MultidimensionalIndex()
 * @model
 * @generated
 */
public interface DB2MultidimensionalIndex extends Index {
	/**
	 * Returns the value of the '<em><b>Dimension Indexes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Index}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#getDB2MultidimensionalIndex <em>DB2 Multidimensional Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dimension Indexes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dimension Indexes</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2MultidimensionalIndex_DimensionIndexes()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#getDB2MultidimensionalIndex
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.model.DB2Index" opposite="DB2MultidimensionalIndex" required="true"
	 * @generated
	 */
	EList getDimensionIndexes();

} // DB2MultidimensionalIndex
