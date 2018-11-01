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

import org.eclipse.datatools.modelbase.sql.routines.Source;

import org.eclipse.datatools.modelbase.sql.tables.ViewTable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ingres View Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresViewTable#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage#getIngresViewTable()
 * @model
 * @generated
 */
public interface IngresViewTable extends ViewTable {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2008 Ingres Corporation and others.\r\nAll rights reserved. This program and the accompanying materials\r\nare made available under the terms of the Eclipse Public License 2.0\r\nwhich accompanies this distribution, and is available at\r\nhttps://www.eclipse.org/legal/epl-2.0/\r\n\r\nContributors:\r\n  Ingres Corporation - initial API and implementation";

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Source)
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage#getIngresViewTable_Source()
	 * @model
	 * @generated
	 */
	Source getSource();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresViewTable#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Source value);

} // IngresViewTable
