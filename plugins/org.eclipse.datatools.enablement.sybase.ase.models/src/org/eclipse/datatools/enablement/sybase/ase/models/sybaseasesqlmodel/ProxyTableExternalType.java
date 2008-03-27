/**
 * <copyright>
 * </copyright>
 *
 * $Id: ProxyTableExternalType.java,v 1.10 2007/07/06 08:40:18 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Proxy Table External Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getProxyTableExternalType()
 * @model
 * @generated
 */
public final class ProxyTableExternalType extends AbstractEnumerator {
	/**
     * The '<em><b>TABLE</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TABLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #TABLE_LITERAL
     * @model literal="table"
     * @generated
     * @ordered
     */
	public static final int TABLE = 0;

	/**
     * The '<em><b>PROCEDURE</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PROCEDURE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #PROCEDURE_LITERAL
     * @model literal="procedure"
     * @generated
     * @ordered
     */
	public static final int PROCEDURE = 1;

	/**
     * The '<em><b>FILE</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FILE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #FILE_LITERAL
     * @model literal="file"
     * @generated
     * @ordered
     */
	public static final int FILE = 2;

	/**
     * The '<em><b>DIRECTORY</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DIRECTORY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #DIRECTORY_LITERAL
     * @model literal="directory"
     * @generated
     * @ordered
     */
	public static final int DIRECTORY = 3;

	/**
     * The '<em><b>TABLE</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #TABLE
     * @generated
     * @ordered
     */
	public static final ProxyTableExternalType TABLE_LITERAL = new ProxyTableExternalType(TABLE, "TABLE", "table"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>PROCEDURE</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #PROCEDURE
     * @generated
     * @ordered
     */
	public static final ProxyTableExternalType PROCEDURE_LITERAL = new ProxyTableExternalType(PROCEDURE, "PROCEDURE", "procedure"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>FILE</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #FILE
     * @generated
     * @ordered
     */
	public static final ProxyTableExternalType FILE_LITERAL = new ProxyTableExternalType(FILE, "FILE", "file"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>DIRECTORY</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #DIRECTORY
     * @generated
     * @ordered
     */
	public static final ProxyTableExternalType DIRECTORY_LITERAL = new ProxyTableExternalType(DIRECTORY, "DIRECTORY", "directory"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * An array of all the '<em><b>Proxy Table External Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static final ProxyTableExternalType[] VALUES_ARRAY =
		new ProxyTableExternalType[]
        {
            TABLE_LITERAL,
            PROCEDURE_LITERAL,
            FILE_LITERAL,
            DIRECTORY_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>Proxy Table External Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Proxy Table External Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static ProxyTableExternalType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            ProxyTableExternalType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal))
            {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Proxy Table External Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static ProxyTableExternalType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            ProxyTableExternalType result = VALUES_ARRAY[i];
            if (result.getName().equals(name))
            {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Proxy Table External Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static ProxyTableExternalType get(int value) {
        switch (value)
        {
            case TABLE: return TABLE_LITERAL;
            case PROCEDURE: return PROCEDURE_LITERAL;
            case FILE: return FILE_LITERAL;
            case DIRECTORY: return DIRECTORY_LITERAL;
        }
        return null;
    }

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private ProxyTableExternalType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //ProxyTableExternalType
