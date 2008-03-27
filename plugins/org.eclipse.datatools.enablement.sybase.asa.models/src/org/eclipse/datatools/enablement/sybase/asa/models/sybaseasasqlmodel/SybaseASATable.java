/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASATable.java,v 1.4 2007/06/05 14:41:04 hcao Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATable#getPctfree <em>Pctfree</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage#getSybaseASATable()
 * @model
 * @generated
 */
public interface SybaseASATable extends SybaseASABaseTable
{
    /**
	 * Returns the value of the '<em><b>Pctfree</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * value = -1 means no pctfree has been set
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pctfree</em>' attribute.
	 * @see #setPctfree(int)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage#getSybaseASATable_Pctfree()
	 * @model default="-1"
	 * @generated
	 */
	int getPctfree();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATable#getPctfree <em>Pctfree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pctfree</em>' attribute.
	 * @see #getPctfree()
	 * @generated
	 */
	void setPctfree(int value);

} // SybaseASATable