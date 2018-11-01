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
package org.eclipse.datatools.enablement.ingres.models.ingressqlmodel;

import org.eclipse.datatools.modelbase.sql.schema.Schema;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ingres Schema</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSchema#getDBEvents <em>DB Events</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSchema#getSynonyms <em>Synonyms</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage#getIngresSchema()
 * @model
 * @generated
 */
public interface IngresSchema extends Schema {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2008 Ingres Corporation and others.\r\nAll rights reserved. This program and the accompanying materials\r\nare made available under the terms of the Eclipse Public License 2.0\r\nwhich accompanies this distribution, and is available at\r\nhttps://www.eclipse.org/legal/epl-2.0/\r\n\r\nContributors:\r\n  Ingres Corporation - initial API and implementation";

	/**
	 * Returns the value of the '<em><b>DB Events</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresDBEvent}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresDBEvent#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>DB Events</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>DB Events</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage#getIngresSchema_DBEvents()
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresDBEvent#getSchema
	 * @model type="org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresDBEvent" opposite="schema"
	 * @generated
	 */
	EList getDBEvents();

	/**
	 * Returns the value of the '<em><b>Synonyms</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Synonyms</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Synonyms</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage#getIngresSchema_Synonyms()
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym#getSchema
	 * @model type="org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym" opposite="schema"
	 * @generated
	 */
	EList getSynonyms();

} // IngresSchema
