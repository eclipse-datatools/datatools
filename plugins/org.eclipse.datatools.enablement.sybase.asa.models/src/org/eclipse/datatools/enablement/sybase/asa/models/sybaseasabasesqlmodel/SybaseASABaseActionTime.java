/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseActionTime.java,v 1.11 2007/07/16 08:01:55 linsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Sybase ASA Base Action Time</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseActionTime()
 * @model
 * @generated
 */
public final class SybaseASABaseActionTime extends AbstractEnumerator {
    /**
     * The '<em><b>BEFORE</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BEFORE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #BEFORE_LITERAL
     * @model
     * @generated
     * @ordered
     */
	public static final int BEFORE = 0;

    /**
     * The '<em><b>AFTER</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AFTER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #AFTER_LITERAL
     * @model
     * @generated
     * @ordered
     */
	public static final int AFTER = 1;

    /**
     * The '<em><b>RESOLVE</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RESOLVE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #RESOLVE_LITERAL
     * @model
     * @generated
     * @ordered
     */
	public static final int RESOLVE = 2;

    /**
     * The '<em><b>ASE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>ASE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ASE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int ASE = 3;

    /**
     * The '<em><b>BEFORE</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #BEFORE
     * @generated
     * @ordered
     */
	public static final SybaseASABaseActionTime BEFORE_LITERAL = new SybaseASABaseActionTime(BEFORE, "BEFORE", "BEFORE");

    /**
     * The '<em><b>AFTER</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #AFTER
     * @generated
     * @ordered
     */
	public static final SybaseASABaseActionTime AFTER_LITERAL = new SybaseASABaseActionTime(AFTER, "AFTER", "AFTER");

    /**
     * The '<em><b>RESOLVE</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #RESOLVE
     * @generated
     * @ordered
     */
	public static final SybaseASABaseActionTime RESOLVE_LITERAL = new SybaseASABaseActionTime(RESOLVE, "RESOLVE", "RESOLVE");

    /**
     * The '<em><b>ASE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ASE
     * @generated
     * @ordered
     */
    public static final SybaseASABaseActionTime ASE_LITERAL = new SybaseASABaseActionTime(ASE, "ASE", "ASE");

    /**
     * An array of all the '<em><b>Sybase ASA Base Action Time</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static final SybaseASABaseActionTime[] VALUES_ARRAY =
        new SybaseASABaseActionTime[]
        {
            BEFORE_LITERAL,
            AFTER_LITERAL,
            RESOLVE_LITERAL,
            ASE_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Sybase ASA Base Action Time</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Sybase ASA Base Action Time</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static SybaseASABaseActionTime get(String literal)
    {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            SybaseASABaseActionTime result = VALUES_ARRAY[i];
            if (result.toString().equals(literal))
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Sybase ASA Base Action Time</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static SybaseASABaseActionTime getByName(String name)
    {
        for (int i = 0; i < VALUES_ARRAY.length; ++i)
        {
            SybaseASABaseActionTime result = VALUES_ARRAY[i];
            if (result.getName().equals(name))
            {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Sybase ASA Base Action Time</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static SybaseASABaseActionTime get(int value)
    {
        switch (value)
        {
            case BEFORE: return BEFORE_LITERAL;
            case AFTER: return AFTER_LITERAL;
            case RESOLVE: return RESOLVE_LITERAL;
            case ASE: return ASE_LITERAL;
        }
        return null;
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private SybaseASABaseActionTime(int value, String name, String literal)
    {
        super(value, name, literal);
    }

} //SybaseASABaseActionTime
