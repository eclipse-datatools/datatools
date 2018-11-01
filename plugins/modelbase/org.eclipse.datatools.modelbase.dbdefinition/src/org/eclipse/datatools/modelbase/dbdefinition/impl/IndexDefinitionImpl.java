/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.PercentFreeTerminology;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Index Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.IndexDefinitionImpl#getPercentFreeTerminology <em>Percent Free Terminology</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.IndexDefinitionImpl#isPercentFreeChangeable <em>Percent Free Changeable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.IndexDefinitionImpl#isClusteringSupported <em>Clustering Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.IndexDefinitionImpl#isClusterChangeable <em>Cluster Changeable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.IndexDefinitionImpl#isFillFactorSupported <em>Fill Factor Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.IndexDefinitionImpl#isIncludedColumnsSupported <em>Included Columns Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.IndexDefinitionImpl#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IndexDefinitionImpl extends EObjectImpl implements IndexDefinition {
	/**
	 * The default value of the '{@link #getPercentFreeTerminology() <em>Percent Free Terminology</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPercentFreeTerminology()
	 * @generated
	 * @ordered
	 */
	protected static final PercentFreeTerminology PERCENT_FREE_TERMINOLOGY_EDEFAULT = PercentFreeTerminology.PERCENT_FREE_LITERAL;

	/**
	 * The cached value of the '{@link #getPercentFreeTerminology() <em>Percent Free Terminology</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPercentFreeTerminology()
	 * @generated
	 * @ordered
	 */
	protected PercentFreeTerminology percentFreeTerminology = PERCENT_FREE_TERMINOLOGY_EDEFAULT;

	/**
	 * The default value of the '{@link #isPercentFreeChangeable() <em>Percent Free Changeable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPercentFreeChangeable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PERCENT_FREE_CHANGEABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPercentFreeChangeable() <em>Percent Free Changeable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPercentFreeChangeable()
	 * @generated
	 * @ordered
	 */
	protected boolean percentFreeChangeable = PERCENT_FREE_CHANGEABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isClusteringSupported() <em>Clustering Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClusteringSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CLUSTERING_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isClusteringSupported() <em>Clustering Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClusteringSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean clusteringSupported = CLUSTERING_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isClusterChangeable() <em>Cluster Changeable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClusterChangeable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CLUSTER_CHANGEABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isClusterChangeable() <em>Cluster Changeable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClusterChangeable()
	 * @generated
	 * @ordered
	 */
	protected boolean clusterChangeable = CLUSTER_CHANGEABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isFillFactorSupported() <em>Fill Factor Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFillFactorSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FILL_FACTOR_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFillFactorSupported() <em>Fill Factor Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFillFactorSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean fillFactorSupported = FILL_FACTOR_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isIncludedColumnsSupported() <em>Included Columns Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIncludedColumnsSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INCLUDED_COLUMNS_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIncludedColumnsSupported() <em>Included Columns Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIncludedColumnsSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean includedColumnsSupported = INCLUDED_COLUMNS_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumIdentifierLength() <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumIdentifierLength() <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected int maximumIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IndexDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.Literals.INDEX_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PercentFreeTerminology getPercentFreeTerminology() {
		return percentFreeTerminology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPercentFreeTerminology(PercentFreeTerminology newPercentFreeTerminology) {
		PercentFreeTerminology oldPercentFreeTerminology = percentFreeTerminology;
		percentFreeTerminology = newPercentFreeTerminology == null ? PERCENT_FREE_TERMINOLOGY_EDEFAULT : newPercentFreeTerminology;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.INDEX_DEFINITION__PERCENT_FREE_TERMINOLOGY, oldPercentFreeTerminology, percentFreeTerminology));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPercentFreeChangeable() {
		return percentFreeChangeable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPercentFreeChangeable(boolean newPercentFreeChangeable) {
		boolean oldPercentFreeChangeable = percentFreeChangeable;
		percentFreeChangeable = newPercentFreeChangeable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.INDEX_DEFINITION__PERCENT_FREE_CHANGEABLE, oldPercentFreeChangeable, percentFreeChangeable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isClusteringSupported() {
		return clusteringSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClusteringSupported(boolean newClusteringSupported) {
		boolean oldClusteringSupported = clusteringSupported;
		clusteringSupported = newClusteringSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.INDEX_DEFINITION__CLUSTERING_SUPPORTED, oldClusteringSupported, clusteringSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isClusterChangeable() {
		return clusterChangeable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClusterChangeable(boolean newClusterChangeable) {
		boolean oldClusterChangeable = clusterChangeable;
		clusterChangeable = newClusterChangeable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.INDEX_DEFINITION__CLUSTER_CHANGEABLE, oldClusterChangeable, clusterChangeable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFillFactorSupported() {
		return fillFactorSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFillFactorSupported(boolean newFillFactorSupported) {
		boolean oldFillFactorSupported = fillFactorSupported;
		fillFactorSupported = newFillFactorSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.INDEX_DEFINITION__FILL_FACTOR_SUPPORTED, oldFillFactorSupported, fillFactorSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIncludedColumnsSupported() {
		return includedColumnsSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncludedColumnsSupported(boolean newIncludedColumnsSupported) {
		boolean oldIncludedColumnsSupported = includedColumnsSupported;
		includedColumnsSupported = newIncludedColumnsSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.INDEX_DEFINITION__INCLUDED_COLUMNS_SUPPORTED, oldIncludedColumnsSupported, includedColumnsSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumIdentifierLength() {
		return maximumIdentifierLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumIdentifierLength(int newMaximumIdentifierLength) {
		int oldMaximumIdentifierLength = maximumIdentifierLength;
		maximumIdentifierLength = newMaximumIdentifierLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.INDEX_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH, oldMaximumIdentifierLength, maximumIdentifierLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabaseDefinitionPackage.INDEX_DEFINITION__PERCENT_FREE_TERMINOLOGY:
				return getPercentFreeTerminology();
			case DatabaseDefinitionPackage.INDEX_DEFINITION__PERCENT_FREE_CHANGEABLE:
				return isPercentFreeChangeable() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__CLUSTERING_SUPPORTED:
				return isClusteringSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__CLUSTER_CHANGEABLE:
				return isClusterChangeable() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__FILL_FACTOR_SUPPORTED:
				return isFillFactorSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__INCLUDED_COLUMNS_SUPPORTED:
				return isIncludedColumnsSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				return new Integer(getMaximumIdentifierLength());
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
			case DatabaseDefinitionPackage.INDEX_DEFINITION__PERCENT_FREE_TERMINOLOGY:
				setPercentFreeTerminology((PercentFreeTerminology)newValue);
				return;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__PERCENT_FREE_CHANGEABLE:
				setPercentFreeChangeable(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__CLUSTERING_SUPPORTED:
				setClusteringSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__CLUSTER_CHANGEABLE:
				setClusterChangeable(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__FILL_FACTOR_SUPPORTED:
				setFillFactorSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__INCLUDED_COLUMNS_SUPPORTED:
				setIncludedColumnsSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				setMaximumIdentifierLength(((Integer)newValue).intValue());
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
			case DatabaseDefinitionPackage.INDEX_DEFINITION__PERCENT_FREE_TERMINOLOGY:
				setPercentFreeTerminology(PERCENT_FREE_TERMINOLOGY_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__PERCENT_FREE_CHANGEABLE:
				setPercentFreeChangeable(PERCENT_FREE_CHANGEABLE_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__CLUSTERING_SUPPORTED:
				setClusteringSupported(CLUSTERING_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__CLUSTER_CHANGEABLE:
				setClusterChangeable(CLUSTER_CHANGEABLE_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__FILL_FACTOR_SUPPORTED:
				setFillFactorSupported(FILL_FACTOR_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__INCLUDED_COLUMNS_SUPPORTED:
				setIncludedColumnsSupported(INCLUDED_COLUMNS_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				setMaximumIdentifierLength(MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT);
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
			case DatabaseDefinitionPackage.INDEX_DEFINITION__PERCENT_FREE_TERMINOLOGY:
				return percentFreeTerminology != PERCENT_FREE_TERMINOLOGY_EDEFAULT;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__PERCENT_FREE_CHANGEABLE:
				return percentFreeChangeable != PERCENT_FREE_CHANGEABLE_EDEFAULT;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__CLUSTERING_SUPPORTED:
				return clusteringSupported != CLUSTERING_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__CLUSTER_CHANGEABLE:
				return clusterChangeable != CLUSTER_CHANGEABLE_EDEFAULT;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__FILL_FACTOR_SUPPORTED:
				return fillFactorSupported != FILL_FACTOR_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__INCLUDED_COLUMNS_SUPPORTED:
				return includedColumnsSupported != INCLUDED_COLUMNS_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.INDEX_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				return maximumIdentifierLength != MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (percentFreeTerminology: "); //$NON-NLS-1$
		result.append(percentFreeTerminology);
		result.append(", percentFreeChangeable: "); //$NON-NLS-1$
		result.append(percentFreeChangeable);
		result.append(", clusteringSupported: "); //$NON-NLS-1$
		result.append(clusteringSupported);
		result.append(", clusterChangeable: "); //$NON-NLS-1$
		result.append(clusterChangeable);
		result.append(", fillFactorSupported: "); //$NON-NLS-1$
		result.append(fillFactorSupported);
		result.append(", includedColumnsSupported: "); //$NON-NLS-1$
		result.append(includedColumnsSupported);
		result.append(", maximumIdentifierLength: "); //$NON-NLS-1$
		result.append(maximumIdentifierLength);
		result.append(')');
		return result.toString();
	}

} //IndexDefinitionImpl
