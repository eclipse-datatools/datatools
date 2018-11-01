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

import java.util.Collection;

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.TableSpaceType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Space Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl#isTypeSupported <em>Type Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl#isExtentSizeSupported <em>Extent Size Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl#isPrefetchSizeSupported <em>Prefetch Size Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl#isManagedBySupported <em>Managed By Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl#isPageSizeSupported <em>Page Size Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl#isBufferPoolSupported <em>Buffer Pool Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl#isDefaultSupported <em>Default Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl#isContainerMaximumSizeSupported <em>Container Maximum Size Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl#isContainerInitialSizeSupported <em>Container Initial Size Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl#isContainerExtentSizeSupported <em>Container Extent Size Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl#getTableSpaceType <em>Table Space Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableSpaceDefinitionImpl extends EObjectImpl implements TableSpaceDefinition {
	/**
	 * The default value of the '{@link #isTypeSupported() <em>Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTypeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TYPE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTypeSupported() <em>Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTypeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean typeSupported = TYPE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isExtentSizeSupported() <em>Extent Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExtentSizeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXTENT_SIZE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExtentSizeSupported() <em>Extent Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExtentSizeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean extentSizeSupported = EXTENT_SIZE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isPrefetchSizeSupported() <em>Prefetch Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrefetchSizeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PREFETCH_SIZE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPrefetchSizeSupported() <em>Prefetch Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrefetchSizeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean prefetchSizeSupported = PREFETCH_SIZE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isManagedBySupported() <em>Managed By Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isManagedBySupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MANAGED_BY_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isManagedBySupported() <em>Managed By Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isManagedBySupported()
	 * @generated
	 * @ordered
	 */
	protected boolean managedBySupported = MANAGED_BY_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isPageSizeSupported() <em>Page Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPageSizeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PAGE_SIZE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPageSizeSupported() <em>Page Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPageSizeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean pageSizeSupported = PAGE_SIZE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isBufferPoolSupported() <em>Buffer Pool Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBufferPoolSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BUFFER_POOL_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBufferPoolSupported() <em>Buffer Pool Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBufferPoolSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean bufferPoolSupported = BUFFER_POOL_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isDefaultSupported() <em>Default Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefaultSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEFAULT_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDefaultSupported() <em>Default Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefaultSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean defaultSupported = DEFAULT_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isContainerMaximumSizeSupported() <em>Container Maximum Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isContainerMaximumSizeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONTAINER_MAXIMUM_SIZE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isContainerMaximumSizeSupported() <em>Container Maximum Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isContainerMaximumSizeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean containerMaximumSizeSupported = CONTAINER_MAXIMUM_SIZE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isContainerInitialSizeSupported() <em>Container Initial Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isContainerInitialSizeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONTAINER_INITIAL_SIZE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isContainerInitialSizeSupported() <em>Container Initial Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isContainerInitialSizeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean containerInitialSizeSupported = CONTAINER_INITIAL_SIZE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isContainerExtentSizeSupported() <em>Container Extent Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isContainerExtentSizeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONTAINER_EXTENT_SIZE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isContainerExtentSizeSupported() <em>Container Extent Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isContainerExtentSizeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean containerExtentSizeSupported = CONTAINER_EXTENT_SIZE_SUPPORTED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTableSpaceType() <em>Table Space Type</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTableSpaceType()
	 * @generated
	 * @ordered
	 */
	protected EList tableSpaceType;

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
	protected TableSpaceDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.Literals.TABLE_SPACE_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTypeSupported() {
		return typeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeSupported(boolean newTypeSupported) {
		boolean oldTypeSupported = typeSupported;
		typeSupported = newTypeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__TYPE_SUPPORTED, oldTypeSupported, typeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExtentSizeSupported() {
		return extentSizeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtentSizeSupported(boolean newExtentSizeSupported) {
		boolean oldExtentSizeSupported = extentSizeSupported;
		extentSizeSupported = newExtentSizeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__EXTENT_SIZE_SUPPORTED, oldExtentSizeSupported, extentSizeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPrefetchSizeSupported() {
		return prefetchSizeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrefetchSizeSupported(boolean newPrefetchSizeSupported) {
		boolean oldPrefetchSizeSupported = prefetchSizeSupported;
		prefetchSizeSupported = newPrefetchSizeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__PREFETCH_SIZE_SUPPORTED, oldPrefetchSizeSupported, prefetchSizeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isManagedBySupported() {
		return managedBySupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManagedBySupported(boolean newManagedBySupported) {
		boolean oldManagedBySupported = managedBySupported;
		managedBySupported = newManagedBySupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__MANAGED_BY_SUPPORTED, oldManagedBySupported, managedBySupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPageSizeSupported() {
		return pageSizeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPageSizeSupported(boolean newPageSizeSupported) {
		boolean oldPageSizeSupported = pageSizeSupported;
		pageSizeSupported = newPageSizeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__PAGE_SIZE_SUPPORTED, oldPageSizeSupported, pageSizeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBufferPoolSupported() {
		return bufferPoolSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBufferPoolSupported(boolean newBufferPoolSupported) {
		boolean oldBufferPoolSupported = bufferPoolSupported;
		bufferPoolSupported = newBufferPoolSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__BUFFER_POOL_SUPPORTED, oldBufferPoolSupported, bufferPoolSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDefaultSupported() {
		return defaultSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultSupported(boolean newDefaultSupported) {
		boolean oldDefaultSupported = defaultSupported;
		defaultSupported = newDefaultSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__DEFAULT_SUPPORTED, oldDefaultSupported, defaultSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isContainerMaximumSizeSupported() {
		return containerMaximumSizeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainerMaximumSizeSupported(boolean newContainerMaximumSizeSupported) {
		boolean oldContainerMaximumSizeSupported = containerMaximumSizeSupported;
		containerMaximumSizeSupported = newContainerMaximumSizeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__CONTAINER_MAXIMUM_SIZE_SUPPORTED, oldContainerMaximumSizeSupported, containerMaximumSizeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isContainerInitialSizeSupported() {
		return containerInitialSizeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainerInitialSizeSupported(boolean newContainerInitialSizeSupported) {
		boolean oldContainerInitialSizeSupported = containerInitialSizeSupported;
		containerInitialSizeSupported = newContainerInitialSizeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__CONTAINER_INITIAL_SIZE_SUPPORTED, oldContainerInitialSizeSupported, containerInitialSizeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isContainerExtentSizeSupported() {
		return containerExtentSizeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainerExtentSizeSupported(boolean newContainerExtentSizeSupported) {
		boolean oldContainerExtentSizeSupported = containerExtentSizeSupported;
		containerExtentSizeSupported = newContainerExtentSizeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__CONTAINER_EXTENT_SIZE_SUPPORTED, oldContainerExtentSizeSupported, containerExtentSizeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTableSpaceType() {
		if (tableSpaceType == null) {
			tableSpaceType = new EDataTypeUniqueEList(TableSpaceType.class, this, DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__TABLE_SPACE_TYPE);
		}
		return tableSpaceType;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH, oldMaximumIdentifierLength, maximumIdentifierLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__TYPE_SUPPORTED:
				return isTypeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__EXTENT_SIZE_SUPPORTED:
				return isExtentSizeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__PREFETCH_SIZE_SUPPORTED:
				return isPrefetchSizeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__MANAGED_BY_SUPPORTED:
				return isManagedBySupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__PAGE_SIZE_SUPPORTED:
				return isPageSizeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__BUFFER_POOL_SUPPORTED:
				return isBufferPoolSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__DEFAULT_SUPPORTED:
				return isDefaultSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__CONTAINER_MAXIMUM_SIZE_SUPPORTED:
				return isContainerMaximumSizeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__CONTAINER_INITIAL_SIZE_SUPPORTED:
				return isContainerInitialSizeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__CONTAINER_EXTENT_SIZE_SUPPORTED:
				return isContainerExtentSizeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__TABLE_SPACE_TYPE:
				return getTableSpaceType();
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
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
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__TYPE_SUPPORTED:
				setTypeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__EXTENT_SIZE_SUPPORTED:
				setExtentSizeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__PREFETCH_SIZE_SUPPORTED:
				setPrefetchSizeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__MANAGED_BY_SUPPORTED:
				setManagedBySupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__PAGE_SIZE_SUPPORTED:
				setPageSizeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__BUFFER_POOL_SUPPORTED:
				setBufferPoolSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__DEFAULT_SUPPORTED:
				setDefaultSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__CONTAINER_MAXIMUM_SIZE_SUPPORTED:
				setContainerMaximumSizeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__CONTAINER_INITIAL_SIZE_SUPPORTED:
				setContainerInitialSizeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__CONTAINER_EXTENT_SIZE_SUPPORTED:
				setContainerExtentSizeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__TABLE_SPACE_TYPE:
				getTableSpaceType().clear();
				getTableSpaceType().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
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
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__TYPE_SUPPORTED:
				setTypeSupported(TYPE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__EXTENT_SIZE_SUPPORTED:
				setExtentSizeSupported(EXTENT_SIZE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__PREFETCH_SIZE_SUPPORTED:
				setPrefetchSizeSupported(PREFETCH_SIZE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__MANAGED_BY_SUPPORTED:
				setManagedBySupported(MANAGED_BY_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__PAGE_SIZE_SUPPORTED:
				setPageSizeSupported(PAGE_SIZE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__BUFFER_POOL_SUPPORTED:
				setBufferPoolSupported(BUFFER_POOL_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__DEFAULT_SUPPORTED:
				setDefaultSupported(DEFAULT_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__CONTAINER_MAXIMUM_SIZE_SUPPORTED:
				setContainerMaximumSizeSupported(CONTAINER_MAXIMUM_SIZE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__CONTAINER_INITIAL_SIZE_SUPPORTED:
				setContainerInitialSizeSupported(CONTAINER_INITIAL_SIZE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__CONTAINER_EXTENT_SIZE_SUPPORTED:
				setContainerExtentSizeSupported(CONTAINER_EXTENT_SIZE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__TABLE_SPACE_TYPE:
				getTableSpaceType().clear();
				return;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
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
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__TYPE_SUPPORTED:
				return typeSupported != TYPE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__EXTENT_SIZE_SUPPORTED:
				return extentSizeSupported != EXTENT_SIZE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__PREFETCH_SIZE_SUPPORTED:
				return prefetchSizeSupported != PREFETCH_SIZE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__MANAGED_BY_SUPPORTED:
				return managedBySupported != MANAGED_BY_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__PAGE_SIZE_SUPPORTED:
				return pageSizeSupported != PAGE_SIZE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__BUFFER_POOL_SUPPORTED:
				return bufferPoolSupported != BUFFER_POOL_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__DEFAULT_SUPPORTED:
				return defaultSupported != DEFAULT_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__CONTAINER_MAXIMUM_SIZE_SUPPORTED:
				return containerMaximumSizeSupported != CONTAINER_MAXIMUM_SIZE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__CONTAINER_INITIAL_SIZE_SUPPORTED:
				return containerInitialSizeSupported != CONTAINER_INITIAL_SIZE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__CONTAINER_EXTENT_SIZE_SUPPORTED:
				return containerExtentSizeSupported != CONTAINER_EXTENT_SIZE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__TABLE_SPACE_TYPE:
				return tableSpaceType != null && !tableSpaceType.isEmpty();
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
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
		result.append(" (typeSupported: "); //$NON-NLS-1$
		result.append(typeSupported);
		result.append(", extentSizeSupported: "); //$NON-NLS-1$
		result.append(extentSizeSupported);
		result.append(", prefetchSizeSupported: "); //$NON-NLS-1$
		result.append(prefetchSizeSupported);
		result.append(", managedBySupported: "); //$NON-NLS-1$
		result.append(managedBySupported);
		result.append(", pageSizeSupported: "); //$NON-NLS-1$
		result.append(pageSizeSupported);
		result.append(", bufferPoolSupported: "); //$NON-NLS-1$
		result.append(bufferPoolSupported);
		result.append(", defaultSupported: "); //$NON-NLS-1$
		result.append(defaultSupported);
		result.append(", containerMaximumSizeSupported: "); //$NON-NLS-1$
		result.append(containerMaximumSizeSupported);
		result.append(", containerInitialSizeSupported: "); //$NON-NLS-1$
		result.append(containerInitialSizeSupported);
		result.append(", containerExtentSizeSupported: "); //$NON-NLS-1$
		result.append(containerExtentSizeSupported);
		result.append(", tableSpaceType: "); //$NON-NLS-1$
		result.append(tableSpaceType);
		result.append(", maximumIdentifierLength: "); //$NON-NLS-1$
		result.append(maximumIdentifierLength);
		result.append(')');
		return result.toString();
	}

} //TableSpaceDefinitionImpl
