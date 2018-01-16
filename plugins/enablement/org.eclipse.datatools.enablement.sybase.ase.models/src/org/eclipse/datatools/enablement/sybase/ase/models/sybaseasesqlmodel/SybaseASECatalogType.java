/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASECatalogType.java,v 1.10 2007/07/06 08:40:15 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Sybase ASE Catalog Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECatalogType()
 * @model
 * @generated
 */
public final class SybaseASECatalogType extends AbstractEnumerator {
	/**
     * The '<em><b>PROXYCATALOG</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PROXYCATALOG</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #PROXYCATALOG_LITERAL
     * @model
     * @generated
     * @ordered
     */
	public static final int PROXYCATALOG = 1;

	/**
     * The '<em><b>TEMPCATALOG</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TEMPCATALOG</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #TEMPCATALOG_LITERAL
     * @model
     * @generated
     * @ordered
     */
	public static final int TEMPCATALOG = 2;

	/**
     * The '<em><b>USERCATALOG</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>USERCATALOG</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #USERCATALOG_LITERAL
     * @model
     * @generated
     * @ordered
     */
	public static final int USERCATALOG = 3;

	/**
     * The '<em><b>PROXYCATALOG</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #PROXYCATALOG
     * @generated
     * @ordered
     */
	public static final SybaseASECatalogType PROXYCATALOG_LITERAL = new SybaseASECatalogType(PROXYCATALOG, "PROXYCATALOG", "PROXYCATALOG"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>TEMPCATALOG</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #TEMPCATALOG
     * @generated
     * @ordered
     */
	public static final SybaseASECatalogType TEMPCATALOG_LITERAL = new SybaseASECatalogType(TEMPCATALOG, "TEMPCATALOG", "TEMPCATALOG"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>USERCATALOG</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #USERCATALOG
     * @generated
     * @ordered
     */
	public static final SybaseASECatalogType USERCATALOG_LITERAL = new SybaseASECatalogType(USERCATALOG, "USERCATALOG", "USERCATALOG"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * An array of all the '<em><b>Sybase ASE Catalog Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static final SybaseASECatalogType[] VALUES_ARRAY =
		new SybaseASECatalogType[]
        {
            PROXYCATALOG_LITERAL,
            TEMPCATALOG_LITERAL,
            USERCATALOG_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>Sybase ASE Catalog Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Sybase ASE Catalog Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static SybaseASECatalogType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            SybaseASECatalogType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal))
            {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Sybase ASE Catalog Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static SybaseASECatalogType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            SybaseASECatalogType result = VALUES_ARRAY[i];
            if (result.getName().equals(name))
            {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Sybase ASE Catalog Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static SybaseASECatalogType get(int value) {
        switch (value)
        {
            case PROXYCATALOG: return PROXYCATALOG_LITERAL;
            case TEMPCATALOG: return TEMPCATALOG_LITERAL;
            case USERCATALOG: return USERCATALOG_LITERAL;
        }
        return null;
    }

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private SybaseASECatalogType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //SybaseASECatalogType
