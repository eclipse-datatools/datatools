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
package org.eclipse.datatools.modelbase.sql.schema.impl;

import java.io.ObjectStreamException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;

import org.eclipse.datatools.modelbase.sql.schema.Comment;
import org.eclipse.datatools.modelbase.sql.schema.Dependency;
import org.eclipse.datatools.modelbase.sql.schema.ObjectExtension;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl#getPrivileges <em>Privileges</em>}</li>
 * </ul>
 * </p>
 *
 * @generated not
 */
public abstract class SQLObjectImpl extends ENamedElementImpl implements SQLObject {
	/**
	 * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList dependencies;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getComments() <em>Comments</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComments()
	 * @generated
	 * @ordered
	 */
	protected EList comments;

	/**
	 * The cached value of the '{@link #getExtensions() <em>Extensions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList extensions;

	/**
	 * The cached value of the '{@link #getPrivileges() <em>Privileges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivileges()
	 * @generated
	 * @ordered
	 */
	protected EList privileges;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SQLObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLSchemaPackage.Literals.SQL_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDependencies() {
		if (dependencies == null) {
			dependencies = new EObjectContainmentEList(Dependency.class, this, SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES);
		}
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.SQL_OBJECT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.SQL_OBJECT__LABEL, oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getComments() {
		if (comments == null) {
			comments = new EObjectWithInverseResolvingEList(Comment.class, this, SQLSchemaPackage.SQL_OBJECT__COMMENTS, SQLSchemaPackage.COMMENT__SQL_OBJECT);
		}
		return comments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getExtensions() {
		if (extensions == null) {
			extensions = new EObjectContainmentWithInverseEList(ObjectExtension.class, this, SQLSchemaPackage.SQL_OBJECT__EXTENSIONS, SQLSchemaPackage.OBJECT_EXTENSION__SQL_OBJECT);
		}
		return extensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPrivileges() {
		if (privileges == null) {
			privileges = new EObjectWithInverseResolvingEList(Privilege.class, this, SQLSchemaPackage.SQL_OBJECT__PRIVILEGES, SQLAccessControlPackage.PRIVILEGE__OBJECT);
		}
		return privileges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public EAnnotation addEAnnotation(String source) {
		EAnnotation eAnnotation = this.getEAnnotation(source);
		if (eAnnotation == null) {
			eAnnotation = (EAnnotation)EcoreFactory.eINSTANCE.create(EcorePackage.eINSTANCE.getEAnnotation());
			eAnnotation.setSource(source);
			this.getEAnnotations().add(eAnnotation);
		}
		
		return eAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void addEAnnotationDetail(EAnnotation eAnnotation, String key, String value) {
		if (eAnnotation != null) {
			EStringToStringMapEntryImpl mapEntry =
				(EStringToStringMapEntryImpl)EcoreFactory.eINSTANCE.create(EcorePackage.eINSTANCE.getEStringToStringMapEntry());
				
			mapEntry.setTypedKey(key);
			mapEntry.setTypedValue(value);
			eAnnotation.getDetails().add(mapEntry);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public String getEAnnotationDetail(EAnnotation eAnnotation, String key) {
		String value = ""; //$NON-NLS-1$
		if (eAnnotation != null) {
			Iterator eAnnotationDetailsIterator = eAnnotation.getDetails().iterator();
			while(eAnnotationDetailsIterator.hasNext()) {
				EStringToStringMapEntryImpl currentMapEntry = (EStringToStringMapEntryImpl)eAnnotationDetailsIterator.next();
				if (currentMapEntry.getTypedKey().equalsIgnoreCase(key)) {
					value = currentMapEntry.getTypedValue();
				}
			}
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setAnnotationDetail(EAnnotation eAnnotation, String key, String value) {
		if (eAnnotation != null) {
			Iterator eAnnotationDetailsIterator = eAnnotation.getDetails().iterator();
			while(eAnnotationDetailsIterator.hasNext()) {
				EStringToStringMapEntryImpl currentMapEntry = (EStringToStringMapEntryImpl)eAnnotationDetailsIterator.next();
				if (currentMapEntry.getTypedKey().equalsIgnoreCase(key)) {
					currentMapEntry.setTypedValue(value);
				}
			}
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void removeEAnnotationDetail(EAnnotation eAnnotation, String key) {
		if (eAnnotation != null) {
	         BasicEList deferredRemove = new BasicEList();
				Iterator eAnnotationDetailsIterator = eAnnotation.getDetails().iterator();
				while(eAnnotationDetailsIterator.hasNext()) {
					EStringToStringMapEntryImpl currentMapEntry = (EStringToStringMapEntryImpl)eAnnotationDetailsIterator.next();
					if (currentMapEntry.getTypedKey().equalsIgnoreCase(key)) {
	               deferredRemove.add(currentMapEntry);
					}
				}
	         for (Iterator iter = deferredRemove.iterator(); iter.hasNext();) {
	            eAnnotation.getDetails().remove(iter.next());
	         }
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public EAnnotation getEAnnotation(String source) {
		EAnnotation eAnnotation = null;
		Iterator eAnnotationIterator = this.getEAnnotations().iterator();
		while(eAnnotationIterator.hasNext()) {
			EAnnotation currentEAnnotation = (EAnnotation)eAnnotationIterator.next();
			if (currentEAnnotation.getSource().equalsIgnoreCase(source)) {
				eAnnotation = currentEAnnotation;
			}
		}
		return eAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLSchemaPackage.SQL_OBJECT__COMMENTS:
				return ((InternalEList)getComments()).basicAdd(otherEnd, msgs);
			case SQLSchemaPackage.SQL_OBJECT__EXTENSIONS:
				return ((InternalEList)getExtensions()).basicAdd(otherEnd, msgs);
			case SQLSchemaPackage.SQL_OBJECT__PRIVILEGES:
				return ((InternalEList)getPrivileges()).basicAdd(otherEnd, msgs);
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
			case SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES:
				return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
			case SQLSchemaPackage.SQL_OBJECT__COMMENTS:
				return ((InternalEList)getComments()).basicRemove(otherEnd, msgs);
			case SQLSchemaPackage.SQL_OBJECT__EXTENSIONS:
				return ((InternalEList)getExtensions()).basicRemove(otherEnd, msgs);
			case SQLSchemaPackage.SQL_OBJECT__PRIVILEGES:
				return ((InternalEList)getPrivileges()).basicRemove(otherEnd, msgs);
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
			case SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES:
				return getDependencies();
			case SQLSchemaPackage.SQL_OBJECT__DESCRIPTION:
				return getDescription();
			case SQLSchemaPackage.SQL_OBJECT__LABEL:
				return getLabel();
			case SQLSchemaPackage.SQL_OBJECT__COMMENTS:
				return getComments();
			case SQLSchemaPackage.SQL_OBJECT__EXTENSIONS:
				return getExtensions();
			case SQLSchemaPackage.SQL_OBJECT__PRIVILEGES:
				return getPrivileges();
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
			case SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SQL_OBJECT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLSchemaPackage.SQL_OBJECT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLSchemaPackage.SQL_OBJECT__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SQL_OBJECT__EXTENSIONS:
				getExtensions().clear();
				getExtensions().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SQL_OBJECT__PRIVILEGES:
				getPrivileges().clear();
				getPrivileges().addAll((Collection)newValue);
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
			case SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLSchemaPackage.SQL_OBJECT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLSchemaPackage.SQL_OBJECT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLSchemaPackage.SQL_OBJECT__COMMENTS:
				getComments().clear();
				return;
			case SQLSchemaPackage.SQL_OBJECT__EXTENSIONS:
				getExtensions().clear();
				return;
			case SQLSchemaPackage.SQL_OBJECT__PRIVILEGES:
				getPrivileges().clear();
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
			case SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLSchemaPackage.SQL_OBJECT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLSchemaPackage.SQL_OBJECT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLSchemaPackage.SQL_OBJECT__COMMENTS:
				return comments != null && !comments.isEmpty();
			case SQLSchemaPackage.SQL_OBJECT__EXTENSIONS:
				return extensions != null && !extensions.isEmpty();
			case SQLSchemaPackage.SQL_OBJECT__PRIVILEGES:
				return privileges != null && !privileges.isEmpty();
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
		result.append(" (description: "); //$NON-NLS-1$
		result.append(description);
		result.append(", label: "); //$NON-NLS-1$
		result.append(label);
		result.append(')');
		return result.toString();
	}

} //SQLObjectImpl
