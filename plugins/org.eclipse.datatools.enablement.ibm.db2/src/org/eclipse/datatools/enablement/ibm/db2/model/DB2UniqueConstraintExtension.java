/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.schema.ObjectExtension;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Unique Constraint Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2UniqueConstraintExtension#isBusPeriodWithoutOverlap <em>Bus Period Without Overlap</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2UniqueConstraintExtension()
 * @model
 * @generated
 */
public interface DB2UniqueConstraintExtension extends SQLObject, ObjectExtension {
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
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2UniqueConstraintExtension_BusPeriodWithoutOverlap()
	 * @model default="False"
	 * @generated
	 */
	boolean isBusPeriodWithoutOverlap();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2UniqueConstraintExtension#isBusPeriodWithoutOverlap <em>Bus Period Without Overlap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus Period Without Overlap</em>' attribute.
	 * @see #isBusPeriodWithoutOverlap()
	 * @generated
	 */
	void setBusPeriodWithoutOverlap(boolean value);

} // DB2UniqueConstraintExtension
