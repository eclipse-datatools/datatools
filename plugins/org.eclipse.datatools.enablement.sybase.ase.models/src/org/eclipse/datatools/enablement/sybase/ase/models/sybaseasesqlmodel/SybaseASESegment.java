/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASESegment.java,v 1.1 2008/03/27 07:41:13 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Segment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment#getCatalog <em>Catalog</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment#getDeviceNames <em>Device Names</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment#getThresholds <em>Thresholds</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASESegment()
 * @model
 * @generated
 */
public interface SybaseASESegment extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Catalog</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getSegments <em>Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Catalog</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Catalog</em>' container reference.
	 * @see #setCatalog(SybaseASECatalog)
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASESegment_Catalog()
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getSegments
	 * @model opposite="segments" transient="false"
	 * @generated
	 */
	SybaseASECatalog getCatalog();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment#getCatalog <em>Catalog</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Catalog</em>' container reference.
	 * @see #getCatalog()
	 * @generated
	 */
	void setCatalog(SybaseASECatalog value);

	/**
	 * Returns the value of the '<em><b>Device Names</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Device Names</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Device Names</em>' attribute list.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASESegment_DeviceNames()
	 * @model required="true"
	 * @generated
	 */
	EList getDeviceNames();

	/**
	 * Returns the value of the '<em><b>Thresholds</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Thresholds</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Thresholds</em>' reference list.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASESegment_Thresholds()
	 * @model type="org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold"
	 * @generated
	 */
	EList getThresholds();

} // SybaseASESegment
