/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASATempTable.java,v 1.4 2007/06/05 14:41:04 hcao Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTempTable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Temp Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATempTable#getPctfree <em>Pctfree</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage#getSybaseASATempTable()
 * @model
 * @generated
 */
public interface SybaseASATempTable extends SybaseASABaseTempTable
{
    /**
	 * Returns the value of the '<em><b>Pctfree</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pctfree</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pctfree</em>' attribute.
	 * @see #setPctfree(int)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage#getSybaseASATempTable_Pctfree()
	 * @model default="-1"
	 * @generated
	 */
	int getPctfree();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATempTable#getPctfree <em>Pctfree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pctfree</em>' attribute.
	 * @see #getPctfree()
	 * @generated
	 */
	void setPctfree(int value);

} // SybaseASATempTable