/**
 * <copyright>
 * </copyright>
 *
 * $Id: LockingSchemaType.java,v 1.12 2007/07/06 08:40:16 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Locking Schema Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getLockingSchemaType()
 * @model
 * @generated
 */
public final class LockingSchemaType extends AbstractEnumerator {
	/**
     * The '<em><b>LOCKDATAROWS</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOCKDATAROWS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #LOCKDATAROWS_LITERAL
     * @model
     * @generated
     * @ordered
     */
	public static final int LOCKDATAROWS = 0;

	/**
     * The '<em><b>LOCKDATAPAGES</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOCKDATAPAGES</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #LOCKDATAPAGES_LITERAL
     * @model
     * @generated
     * @ordered
     */
	public static final int LOCKDATAPAGES = 1;

	/**
     * The '<em><b>LOCKALLPAGES</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOCKALLPAGES</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #LOCKALLPAGES_LITERAL
     * @model
     * @generated
     * @ordered
     */
	public static final int LOCKALLPAGES = 2;

	/**
     * The '<em><b>SERVERDEFAULT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SERVERDEFAULT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SERVERDEFAULT_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int SERVERDEFAULT = 3;

	/**
     * The '<em><b>LOCKDATAROWS</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #LOCKDATAROWS
     * @generated
     * @ordered
     */
	public static final LockingSchemaType LOCKDATAROWS_LITERAL = new LockingSchemaType(LOCKDATAROWS, "LOCKDATAROWS", "LOCKDATAROWS"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>LOCKDATAPAGES</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #LOCKDATAPAGES
     * @generated
     * @ordered
     */
	public static final LockingSchemaType LOCKDATAPAGES_LITERAL = new LockingSchemaType(LOCKDATAPAGES, "LOCKDATAPAGES", "LOCKDATAPAGES"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>LOCKALLPAGES</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #LOCKALLPAGES
     * @generated
     * @ordered
     */
	public static final LockingSchemaType LOCKALLPAGES_LITERAL = new LockingSchemaType(LOCKALLPAGES, "LOCKALLPAGES", "LOCKALLPAGES"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>SERVERDEFAULT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SERVERDEFAULT LITERAL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SERVERDEFAULT
     * @generated
     * @ordered
     */
    public static final LockingSchemaType SERVERDEFAULT_LITERAL = new LockingSchemaType(SERVERDEFAULT, "SERVERDEFAULT", "SERVERDEFAULT");

	/**
     * An array of all the '<em><b>Locking Schema Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static final LockingSchemaType[] VALUES_ARRAY =
		new LockingSchemaType[]
        {
            LOCKDATAROWS_LITERAL,
            LOCKDATAPAGES_LITERAL,
            LOCKALLPAGES_LITERAL,
            SERVERDEFAULT_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>Locking Schema Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Locking Schema Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static LockingSchemaType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            LockingSchemaType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal))
            {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Locking Schema Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static LockingSchemaType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            LockingSchemaType result = VALUES_ARRAY[i];
            if (result.getName().equals(name))
            {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Locking Schema Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static LockingSchemaType get(int value) {
        switch (value)
        {
            case LOCKDATAROWS: return LOCKDATAROWS_LITERAL;
            case LOCKDATAPAGES: return LOCKDATAPAGES_LITERAL;
            case LOCKALLPAGES: return LOCKALLPAGES_LITERAL;
            case SERVERDEFAULT: return SERVERDEFAULT_LITERAL;
        }
        return null;
    }

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private LockingSchemaType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //LockingSchemaType
