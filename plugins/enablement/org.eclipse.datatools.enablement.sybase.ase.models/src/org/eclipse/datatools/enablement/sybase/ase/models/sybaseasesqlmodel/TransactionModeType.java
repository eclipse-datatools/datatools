/**
 * <copyright>
 * </copyright>
 *
 * $Id: TransactionModeType.java,v 1.10 2007/07/06 08:40:16 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Transaction Mode Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getTransactionModeType()
 * @model
 * @generated
 */
public final class TransactionModeType extends AbstractEnumerator {
	/**
     * The '<em><b>CHAINED</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CHAINED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #CHAINED_LITERAL
     * @model
     * @generated
     * @ordered
     */
	public static final int CHAINED = 16;

	/**
     * The '<em><b>UNCHAINED</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNCHAINED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #UNCHAINED_LITERAL
     * @model
     * @generated
     * @ordered
     */
	public static final int UNCHAINED = 0;

	/**
     * The '<em><b>ANYMODE</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ANYMODE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #ANYMODE_LITERAL
     * @model
     * @generated
     * @ordered
     */
	public static final int ANYMODE = 32;

	/**
     * The '<em><b>CHAINED</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #CHAINED
     * @generated
     * @ordered
     */
	public static final TransactionModeType CHAINED_LITERAL = new TransactionModeType(CHAINED, "CHAINED", "CHAINED"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>UNCHAINED</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #UNCHAINED
     * @generated
     * @ordered
     */
	public static final TransactionModeType UNCHAINED_LITERAL = new TransactionModeType(UNCHAINED, "UNCHAINED", "UNCHAINED"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>ANYMODE</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #ANYMODE
     * @generated
     * @ordered
     */
	public static final TransactionModeType ANYMODE_LITERAL = new TransactionModeType(ANYMODE, "ANYMODE", "ANYMODE"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * An array of all the '<em><b>Transaction Mode Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static final TransactionModeType[] VALUES_ARRAY =
		new TransactionModeType[]
        {
            CHAINED_LITERAL,
            UNCHAINED_LITERAL,
            ANYMODE_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>Transaction Mode Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Transaction Mode Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static TransactionModeType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            TransactionModeType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal))
            {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Transaction Mode Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static TransactionModeType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            TransactionModeType result = VALUES_ARRAY[i];
            if (result.getName().equals(name))
            {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Transaction Mode Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static TransactionModeType get(int value) {
        switch (value)
        {
            case CHAINED: return CHAINED_LITERAL;
            case UNCHAINED: return UNCHAINED_LITERAL;
            case ANYMODE: return ANYMODE_LITERAL;
        }
        return null;
    }

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private TransactionModeType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //TransactionModeType
