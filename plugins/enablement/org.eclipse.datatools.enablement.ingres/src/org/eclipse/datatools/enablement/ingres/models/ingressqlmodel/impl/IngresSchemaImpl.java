/**
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *   Ingres Corporation - initial API and implementation
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCSchema;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresDBEvent;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSchema;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ingres Schema</b></em>'.
 * Inherits from JDBCSchema to benefit from the DTP JDBC loading mechanism.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresSchemaImpl#getDBEvents <em>DB Events</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresSchemaImpl#getSynonyms <em>Synonyms</em>}</li>
 * </ul>
 * </p>
 *
 * generated NOT
 */
public class IngresSchemaImpl extends JDBCSchema implements IngresSchema {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2008 Ingres Corporation and others.\r\nAll rights reserved. This program and the accompanying materials\r\nare made available under the terms of the Eclipse Public License 2.0\r\nwhich accompanies this distribution, and is available at\r\nhttps://www.eclipse.org/legal/epl-2.0/\r\n\r\nContributors:\r\n  Ingres Corporation - initial API and implementation";

	/**
	 * The cached value of the '{@link #getDBEvents() <em>DB Events</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDBEvents()
	 * @generated
	 * @ordered
	 */
	protected EList dBEvents;

	/**
	 * The cached value of the '{@link #getSynonyms() <em>Synonyms</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSynonyms()
	 * @generated
	 * @ordered
	 */
	protected EList synonyms;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IngresSchemaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return IngressqlmodelPackage.Literals.INGRES_SCHEMA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDBEvents() {
		if (dBEvents == null) {
			dBEvents = new EObjectWithInverseResolvingEList(IngresDBEvent.class, this, IngressqlmodelPackage.INGRES_SCHEMA__DB_EVENTS, IngressqlmodelPackage.INGRES_DB_EVENT__SCHEMA);
		}
		return dBEvents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSynonyms() {
		if (synonyms == null) {
			synonyms = new EObjectWithInverseResolvingEList(IngresSynonym.class, this, IngressqlmodelPackage.INGRES_SCHEMA__SYNONYMS, IngressqlmodelPackage.INGRES_SYNONYM__SCHEMA);
		}
		return synonyms;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IngressqlmodelPackage.INGRES_SCHEMA__DB_EVENTS:
				return ((InternalEList)getDBEvents()).basicAdd(otherEnd, msgs);
			case IngressqlmodelPackage.INGRES_SCHEMA__SYNONYMS:
				return ((InternalEList)getSynonyms()).basicAdd(otherEnd, msgs);
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
			case IngressqlmodelPackage.INGRES_SCHEMA__DB_EVENTS:
				return ((InternalEList)getDBEvents()).basicRemove(otherEnd, msgs);
			case IngressqlmodelPackage.INGRES_SCHEMA__SYNONYMS:
				return ((InternalEList)getSynonyms()).basicRemove(otherEnd, msgs);
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
			case IngressqlmodelPackage.INGRES_SCHEMA__DB_EVENTS:
				return getDBEvents();
			case IngressqlmodelPackage.INGRES_SCHEMA__SYNONYMS:
				return getSynonyms();
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
			case IngressqlmodelPackage.INGRES_SCHEMA__DB_EVENTS:
				getDBEvents().clear();
				getDBEvents().addAll((Collection)newValue);
				return;
			case IngressqlmodelPackage.INGRES_SCHEMA__SYNONYMS:
				getSynonyms().clear();
				getSynonyms().addAll((Collection)newValue);
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
			case IngressqlmodelPackage.INGRES_SCHEMA__DB_EVENTS:
				getDBEvents().clear();
				return;
			case IngressqlmodelPackage.INGRES_SCHEMA__SYNONYMS:
				getSynonyms().clear();
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
			case IngressqlmodelPackage.INGRES_SCHEMA__DB_EVENTS:
				return dBEvents != null && !dBEvents.isEmpty();
			case IngressqlmodelPackage.INGRES_SCHEMA__SYNONYMS:
				return synonyms != null && !synonyms.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //IngresSchemaImpl
