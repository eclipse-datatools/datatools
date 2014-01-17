/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.constraints.impl.IndexImpl;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Index;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2MultidimensionalIndex;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Multidimensional Index</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MultidimensionalIndexImpl#getDimensionIndexes <em>Dimension Indexes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2MultidimensionalIndexImpl extends IndexImpl implements DB2MultidimensionalIndex {
	/**
	 * The cached value of the '{@link #getDimensionIndexes() <em>Dimension Indexes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDimensionIndexes()
	 * @generated
	 * @ordered
	 */
	protected EList dimensionIndexes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2MultidimensionalIndexImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_MULTIDIMENSIONAL_INDEX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDimensionIndexes() {
		if (dimensionIndexes == null) {
			dimensionIndexes = new EObjectWithInverseResolvingEList(DB2Index.class, this, DB2ModelPackage.DB2_MULTIDIMENSIONAL_INDEX__DIMENSION_INDEXES, DB2ModelPackage.DB2_INDEX__DB2_MULTIDIMENSIONAL_INDEX);
		}
		return dimensionIndexes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_MULTIDIMENSIONAL_INDEX__DIMENSION_INDEXES:
				return ((InternalEList)getDimensionIndexes()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_MULTIDIMENSIONAL_INDEX__DIMENSION_INDEXES:
				return ((InternalEList)getDimensionIndexes()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DB2ModelPackage.DB2_MULTIDIMENSIONAL_INDEX__DIMENSION_INDEXES:
				return getDimensionIndexes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DB2ModelPackage.DB2_MULTIDIMENSIONAL_INDEX__DIMENSION_INDEXES:
				getDimensionIndexes().clear();
				getDimensionIndexes().addAll((Collection)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case DB2ModelPackage.DB2_MULTIDIMENSIONAL_INDEX__DIMENSION_INDEXES:
				getDimensionIndexes().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DB2ModelPackage.DB2_MULTIDIMENSIONAL_INDEX__DIMENSION_INDEXES:
				return dimensionIndexes != null && !dimensionIndexes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DB2MultidimensionalIndexImpl
