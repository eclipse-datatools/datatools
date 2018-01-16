/**
 * <copyright>
 * </copyright>
 *
 * $Id: CacheStrategyType.java,v 1.11 2007/07/06 08:40:16 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Cache Strategy Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getCacheStrategyType()
 * @model
 * @generated
 */
public final class CacheStrategyType extends AbstractEnumerator
{
	/**
     * The '<em><b>PREFETCH</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>PREFETCH</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PREFETCH_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int PREFETCH = 1;

	/**
     * The '<em><b>MRU</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>MRU</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #MRU_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int MRU = 2;

	/**
     * The '<em><b>MRU SERVER DEFAULT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>MRU SERVER DEFAULT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #MRU_SERVER_DEFAULT_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int MRU_SERVER_DEFAULT = 4;

	/**
     * The '<em><b>PREFETCH SERVER DEFAULT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>PREFETCH SERVER DEFAULT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PREFETCH_SERVER_DEFAULT_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int PREFETCH_SERVER_DEFAULT = 8;

	/**
     * The '<em><b>PREFETCH</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PREFETCH
     * @generated
     * @ordered
     */
    public static final CacheStrategyType PREFETCH_LITERAL = new CacheStrategyType(PREFETCH, "PREFETCH", "PREFETCH"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>MRU</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MRU
     * @generated
     * @ordered
     */
    public static final CacheStrategyType MRU_LITERAL = new CacheStrategyType(MRU, "MRU", "MRU"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>MRU SERVER DEFAULT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MRU_SERVER_DEFAULT
     * @generated
     * @ordered
     */
    public static final CacheStrategyType MRU_SERVER_DEFAULT_LITERAL = new CacheStrategyType(MRU_SERVER_DEFAULT, "MRU_SERVER_DEFAULT", "MRU_SERVER_DEFAULT"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>PREFETCH SERVER DEFAULT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PREFETCH_SERVER_DEFAULT
     * @generated
     * @ordered
     */
    public static final CacheStrategyType PREFETCH_SERVER_DEFAULT_LITERAL = new CacheStrategyType(PREFETCH_SERVER_DEFAULT, "PREFETCH_SERVER_DEFAULT", "PREFETCH_SERVER_DEFAULT"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * An array of all the '<em><b>Cache Strategy Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final CacheStrategyType[] VALUES_ARRAY =
		new CacheStrategyType[]
        {
            PREFETCH_LITERAL,
            MRU_LITERAL,
            MRU_SERVER_DEFAULT_LITERAL,
            PREFETCH_SERVER_DEFAULT_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>Cache Strategy Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Cache Strategy Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static CacheStrategyType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            CacheStrategyType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal))
            {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Cache Strategy Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static CacheStrategyType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            CacheStrategyType result = VALUES_ARRAY[i];
            if (result.getName().equals(name))
            {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Cache Strategy Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static CacheStrategyType get(int value) {
        switch (value)
        {
            case PREFETCH: return PREFETCH_LITERAL;
            case MRU: return MRU_LITERAL;
            case MRU_SERVER_DEFAULT: return MRU_SERVER_DEFAULT_LITERAL;
            case PREFETCH_SERVER_DEFAULT: return PREFETCH_SERVER_DEFAULT_LITERAL;
        }
        return null;
    }

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private CacheStrategyType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //CacheStrategyType
