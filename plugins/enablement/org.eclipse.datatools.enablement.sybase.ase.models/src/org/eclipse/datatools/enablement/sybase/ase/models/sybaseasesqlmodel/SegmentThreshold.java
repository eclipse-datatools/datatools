/**
 * <copyright>
 * </copyright>
 *
 * $Id: SegmentThreshold.java,v 1.7 2007/07/06 08:40:16 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Segment Threshold</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold#getProcedureName <em>Procedure Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold#getFreeSpace <em>Free Space</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSegmentThreshold()
 * @model
 * @generated
 */
public interface SegmentThreshold extends SQLObject {
	/**
     * Returns the value of the '<em><b>Procedure Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Procedure Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Procedure Name</em>' attribute.
     * @see #setProcedureName(String)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSegmentThreshold_ProcedureName()
     * @model
     * @generated
     */
	String getProcedureName();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold#getProcedureName <em>Procedure Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Procedure Name</em>' attribute.
     * @see #getProcedureName()
     * @generated
     */
	void setProcedureName(String value);

	/**
     * Returns the value of the '<em><b>Free Space</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Free Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Free Space</em>' attribute.
     * @see #setFreeSpace(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSegmentThreshold_FreeSpace()
     * @model default="0" required="true"
     * @generated
     */
	int getFreeSpace();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold#getFreeSpace <em>Free Space</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Free Space</em>' attribute.
     * @see #getFreeSpace()
     * @generated
     */
	void setFreeSpace(int value);

} // SegmentThreshold
