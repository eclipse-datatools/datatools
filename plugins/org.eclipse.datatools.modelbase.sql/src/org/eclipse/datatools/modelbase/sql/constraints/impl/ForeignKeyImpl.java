/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.constraints.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.MatchType;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Foreign Key</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.ForeignKeyImpl#getMatch <em>Match</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.ForeignKeyImpl#getOnUpdate <em>On Update</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.ForeignKeyImpl#getOnDelete <em>On Delete</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.ForeignKeyImpl#getUniqueConstraint <em>Unique Constraint</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.ForeignKeyImpl#getReferencedMembers <em>Referenced Members</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.ForeignKeyImpl#getUniqueIndex <em>Unique Index</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.ForeignKeyImpl#getReferencedTable <em>Referenced Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForeignKeyImpl extends ReferenceConstraintImpl implements ForeignKey {
	/**
	 * The default value of the '{@link #getMatch() <em>Match</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatch()
	 * @generated
	 * @ordered
	 */
	protected static final MatchType MATCH_EDEFAULT = MatchType.MATCH_SIMPLE_LITERAL;

	/**
	 * The cached value of the '{@link #getMatch() <em>Match</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatch()
	 * @generated
	 * @ordered
	 */
	protected MatchType match = MATCH_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnUpdate() <em>On Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnUpdate()
	 * @generated
	 * @ordered
	 */
	protected static final ReferentialActionType ON_UPDATE_EDEFAULT = ReferentialActionType.NO_ACTION_LITERAL;

	/**
	 * The cached value of the '{@link #getOnUpdate() <em>On Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnUpdate()
	 * @generated
	 * @ordered
	 */
	protected ReferentialActionType onUpdate = ON_UPDATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnDelete() <em>On Delete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnDelete()
	 * @generated
	 * @ordered
	 */
	protected static final ReferentialActionType ON_DELETE_EDEFAULT = ReferentialActionType.NO_ACTION_LITERAL;

	/**
	 * The cached value of the '{@link #getOnDelete() <em>On Delete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnDelete()
	 * @generated
	 * @ordered
	 */
	protected ReferentialActionType onDelete = ON_DELETE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getUniqueConstraint() <em>Unique Constraint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUniqueConstraint()
	 * @generated
	 * @ordered
	 */
	protected UniqueConstraint uniqueConstraint;

	/**
	 * The cached value of the '{@link #getReferencedMembers() <em>Referenced Members</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedMembers()
	 * @generated
	 * @ordered
	 */
	protected EList referencedMembers;

	/**
	 * The cached value of the '{@link #getUniqueIndex() <em>Unique Index</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUniqueIndex()
	 * @generated
	 * @ordered
	 */
	protected Index uniqueIndex;

	/**
	 * The cached value of the '{@link #getReferencedTable() <em>Referenced Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedTable()
	 * @generated
	 * @ordered
	 */
	protected BaseTable referencedTable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ForeignKeyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLConstraintsPackage.Literals.FOREIGN_KEY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MatchType getMatch() {
		return match;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMatch(MatchType newMatch) {
		MatchType oldMatch = match;
		match = newMatch == null ? MATCH_EDEFAULT : newMatch;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.FOREIGN_KEY__MATCH, oldMatch, match));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferentialActionType getOnUpdate() {
		return onUpdate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnUpdate(ReferentialActionType newOnUpdate) {
		ReferentialActionType oldOnUpdate = onUpdate;
		onUpdate = newOnUpdate == null ? ON_UPDATE_EDEFAULT : newOnUpdate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.FOREIGN_KEY__ON_UPDATE, oldOnUpdate, onUpdate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferentialActionType getOnDelete() {
		return onDelete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnDelete(ReferentialActionType newOnDelete) {
		ReferentialActionType oldOnDelete = onDelete;
		onDelete = newOnDelete == null ? ON_DELETE_EDEFAULT : newOnDelete;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.FOREIGN_KEY__ON_DELETE, oldOnDelete, onDelete));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UniqueConstraint getUniqueConstraint() {
		if (uniqueConstraint != null && uniqueConstraint.eIsProxy()) {
			InternalEObject oldUniqueConstraint = (InternalEObject)uniqueConstraint;
			uniqueConstraint = (UniqueConstraint)eResolveProxy(oldUniqueConstraint);
			if (uniqueConstraint != oldUniqueConstraint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_CONSTRAINT, oldUniqueConstraint, uniqueConstraint));
			}
		}
		return uniqueConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UniqueConstraint basicGetUniqueConstraint() {
		return uniqueConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUniqueConstraint(UniqueConstraint newUniqueConstraint, NotificationChain msgs) {
		UniqueConstraint oldUniqueConstraint = uniqueConstraint;
		uniqueConstraint = newUniqueConstraint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_CONSTRAINT, oldUniqueConstraint, newUniqueConstraint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUniqueConstraint(UniqueConstraint newUniqueConstraint) {
		if (newUniqueConstraint != uniqueConstraint) {
			NotificationChain msgs = null;
			if (uniqueConstraint != null)
				msgs = ((InternalEObject)uniqueConstraint).eInverseRemove(this, SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY, UniqueConstraint.class, msgs);
			if (newUniqueConstraint != null)
				msgs = ((InternalEObject)newUniqueConstraint).eInverseAdd(this, SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY, UniqueConstraint.class, msgs);
			msgs = basicSetUniqueConstraint(newUniqueConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_CONSTRAINT, newUniqueConstraint, newUniqueConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getReferencedMembers() {
		if (referencedMembers == null) {
			referencedMembers = new EObjectResolvingEList(Column.class, this, SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_MEMBERS);
		}
		return referencedMembers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Index getUniqueIndex() {
		if (uniqueIndex != null && uniqueIndex.eIsProxy()) {
			InternalEObject oldUniqueIndex = (InternalEObject)uniqueIndex;
			uniqueIndex = (Index)eResolveProxy(oldUniqueIndex);
			if (uniqueIndex != oldUniqueIndex) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_INDEX, oldUniqueIndex, uniqueIndex));
			}
		}
		return uniqueIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Index basicGetUniqueIndex() {
		return uniqueIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUniqueIndex(Index newUniqueIndex, NotificationChain msgs) {
		Index oldUniqueIndex = uniqueIndex;
		uniqueIndex = newUniqueIndex;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_INDEX, oldUniqueIndex, newUniqueIndex);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUniqueIndex(Index newUniqueIndex) {
		if (newUniqueIndex != uniqueIndex) {
			NotificationChain msgs = null;
			if (uniqueIndex != null)
				msgs = ((InternalEObject)uniqueIndex).eInverseRemove(this, SQLConstraintsPackage.INDEX__FOREIGN_KEY, Index.class, msgs);
			if (newUniqueIndex != null)
				msgs = ((InternalEObject)newUniqueIndex).eInverseAdd(this, SQLConstraintsPackage.INDEX__FOREIGN_KEY, Index.class, msgs);
			msgs = basicSetUniqueIndex(newUniqueIndex, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_INDEX, newUniqueIndex, newUniqueIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseTable getReferencedTable() {
		if (referencedTable != null && referencedTable.eIsProxy()) {
			InternalEObject oldReferencedTable = (InternalEObject)referencedTable;
			referencedTable = (BaseTable)eResolveProxy(oldReferencedTable);
			if (referencedTable != oldReferencedTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_TABLE, oldReferencedTable, referencedTable));
			}
		}
		return referencedTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseTable basicGetReferencedTable() {
		return referencedTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReferencedTable(BaseTable newReferencedTable, NotificationChain msgs) {
		BaseTable oldReferencedTable = referencedTable;
		referencedTable = newReferencedTable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_TABLE, oldReferencedTable, newReferencedTable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencedTable(BaseTable newReferencedTable) {
		if (newReferencedTable != referencedTable) {
			NotificationChain msgs = null;
			if (referencedTable != null)
				msgs = ((InternalEObject)referencedTable).eInverseRemove(this, SQLTablesPackage.BASE_TABLE__REFERENCING_FOREIGN_KEYS, BaseTable.class, msgs);
			if (newReferencedTable != null)
				msgs = ((InternalEObject)newReferencedTable).eInverseAdd(this, SQLTablesPackage.BASE_TABLE__REFERENCING_FOREIGN_KEYS, BaseTable.class, msgs);
			msgs = basicSetReferencedTable(newReferencedTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_TABLE, newReferencedTable, newReferencedTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_CONSTRAINT:
				if (uniqueConstraint != null)
					msgs = ((InternalEObject)uniqueConstraint).eInverseRemove(this, SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY, UniqueConstraint.class, msgs);
				return basicSetUniqueConstraint((UniqueConstraint)otherEnd, msgs);
			case SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_INDEX:
				if (uniqueIndex != null)
					msgs = ((InternalEObject)uniqueIndex).eInverseRemove(this, SQLConstraintsPackage.INDEX__FOREIGN_KEY, Index.class, msgs);
				return basicSetUniqueIndex((Index)otherEnd, msgs);
			case SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_TABLE:
				if (referencedTable != null)
					msgs = ((InternalEObject)referencedTable).eInverseRemove(this, SQLTablesPackage.BASE_TABLE__REFERENCING_FOREIGN_KEYS, BaseTable.class, msgs);
				return basicSetReferencedTable((BaseTable)otherEnd, msgs);
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
			case SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_CONSTRAINT:
				return basicSetUniqueConstraint(null, msgs);
			case SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_INDEX:
				return basicSetUniqueIndex(null, msgs);
			case SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_TABLE:
				return basicSetReferencedTable(null, msgs);
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
			case SQLConstraintsPackage.FOREIGN_KEY__MATCH:
				return getMatch();
			case SQLConstraintsPackage.FOREIGN_KEY__ON_UPDATE:
				return getOnUpdate();
			case SQLConstraintsPackage.FOREIGN_KEY__ON_DELETE:
				return getOnDelete();
			case SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_CONSTRAINT:
				if (resolve) return getUniqueConstraint();
				return basicGetUniqueConstraint();
			case SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_MEMBERS:
				return getReferencedMembers();
			case SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_INDEX:
				if (resolve) return getUniqueIndex();
				return basicGetUniqueIndex();
			case SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_TABLE:
				if (resolve) return getReferencedTable();
				return basicGetReferencedTable();
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
			case SQLConstraintsPackage.FOREIGN_KEY__MATCH:
				setMatch((MatchType)newValue);
				return;
			case SQLConstraintsPackage.FOREIGN_KEY__ON_UPDATE:
				setOnUpdate((ReferentialActionType)newValue);
				return;
			case SQLConstraintsPackage.FOREIGN_KEY__ON_DELETE:
				setOnDelete((ReferentialActionType)newValue);
				return;
			case SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_CONSTRAINT:
				setUniqueConstraint((UniqueConstraint)newValue);
				return;
			case SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_MEMBERS:
				getReferencedMembers().clear();
				getReferencedMembers().addAll((Collection)newValue);
				return;
			case SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_INDEX:
				setUniqueIndex((Index)newValue);
				return;
			case SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_TABLE:
				setReferencedTable((BaseTable)newValue);
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
			case SQLConstraintsPackage.FOREIGN_KEY__MATCH:
				setMatch(MATCH_EDEFAULT);
				return;
			case SQLConstraintsPackage.FOREIGN_KEY__ON_UPDATE:
				setOnUpdate(ON_UPDATE_EDEFAULT);
				return;
			case SQLConstraintsPackage.FOREIGN_KEY__ON_DELETE:
				setOnDelete(ON_DELETE_EDEFAULT);
				return;
			case SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_CONSTRAINT:
				setUniqueConstraint((UniqueConstraint)null);
				return;
			case SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_MEMBERS:
				getReferencedMembers().clear();
				return;
			case SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_INDEX:
				setUniqueIndex((Index)null);
				return;
			case SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_TABLE:
				setReferencedTable((BaseTable)null);
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
			case SQLConstraintsPackage.FOREIGN_KEY__MATCH:
				return match != MATCH_EDEFAULT;
			case SQLConstraintsPackage.FOREIGN_KEY__ON_UPDATE:
				return onUpdate != ON_UPDATE_EDEFAULT;
			case SQLConstraintsPackage.FOREIGN_KEY__ON_DELETE:
				return onDelete != ON_DELETE_EDEFAULT;
			case SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_CONSTRAINT:
				return uniqueConstraint != null;
			case SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_MEMBERS:
				return referencedMembers != null && !referencedMembers.isEmpty();
			case SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_INDEX:
				return uniqueIndex != null;
			case SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_TABLE:
				return referencedTable != null;
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
		result.append(" (match: "); //$NON-NLS-1$
		result.append(match);
		result.append(", onUpdate: "); //$NON-NLS-1$
		result.append(onUpdate);
		result.append(", onDelete: "); //$NON-NLS-1$
		result.append(onDelete);
		result.append(')');
		return result.toString();
	}

} //ForeignKeyImpl
