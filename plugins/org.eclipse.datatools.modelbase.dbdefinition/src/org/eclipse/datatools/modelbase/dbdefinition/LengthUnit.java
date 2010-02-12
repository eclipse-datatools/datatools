/**
 * <copyright>
 * </copyright>
 *
 * $Id: LengthUnit.java,v 1.3 2006/10/11 20:34:55 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Length Unit</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getLengthUnit()
 * @model
 * @generated
 */
public final class LengthUnit extends AbstractEnumerator {
	/**
	 * The '<em><b>DECIMAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DECIMAL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DECIMAL = 0;

	/**
	 * The '<em><b>BIT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BIT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BIT = 1;

	/**
	 * The '<em><b>BYTE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BYTE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BYTE = 2;

	/**
	 * The '<em><b>DOUBLE BYTE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DOUBLE_BYTE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DOUBLE_BYTE = 3;

	/**
	 * The '<em><b>DECIMAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DECIMAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DECIMAL
	 * @generated
	 * @ordered
	 */
	public static final LengthUnit DECIMAL_LITERAL = new LengthUnit(DECIMAL, "DECIMAL", "DECIMAL"); //$NON-NLS-1$

	/**
	 * The '<em><b>BIT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BIT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BIT
	 * @generated
	 * @ordered
	 */
	public static final LengthUnit BIT_LITERAL = new LengthUnit(BIT, "BIT", "BIT"); //$NON-NLS-1$

	/**
	 * The '<em><b>BYTE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BYTE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BYTE
	 * @generated
	 * @ordered
	 */
	public static final LengthUnit BYTE_LITERAL = new LengthUnit(BYTE, "BYTE", "BYTE"); //$NON-NLS-1$

	/**
	 * The '<em><b>DOUBLE BYTE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DOUBLE BYTE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DOUBLE_BYTE
	 * @generated
	 * @ordered
	 */
	public static final LengthUnit DOUBLE_BYTE_LITERAL = new LengthUnit(DOUBLE_BYTE, "DOUBLE_BYTE", "DOUBLE_BYTE"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Length Unit</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LengthUnit[] VALUES_ARRAY =
		new LengthUnit[] {
			DECIMAL_LITERAL,
			BIT_LITERAL,
			BYTE_LITERAL,
			DOUBLE_BYTE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Length Unit</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Length Unit</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LengthUnit get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LengthUnit result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Length Unit</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LengthUnit getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LengthUnit result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Length Unit</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LengthUnit get(int value) {
		switch (value) {
			case DECIMAL: return DECIMAL_LITERAL;
			case BIT: return BIT_LITERAL;
			case BYTE: return BYTE_LITERAL;
			case DOUBLE_BYTE: return DOUBLE_BYTE_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LengthUnit(int value, String name, String literal) {
		super(value, name, literal);
	}

} //LengthUnit
