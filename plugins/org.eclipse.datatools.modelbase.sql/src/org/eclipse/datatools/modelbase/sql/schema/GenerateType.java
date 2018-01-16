/**
 * <copyright>
 * </copyright>
 *
 * $Id: GenerateType.java,v 1.3 2006/09/07 00:19:47 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.sql.schema;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Generate Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.14.4 Identity columns
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getGenerateType()
 * @model
 * @generated
 */
public final class GenerateType extends AbstractEnumerator {
	/**
	 * The '<em><b>DEFAULT GENERATED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEFAULT_GENERATED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DEFAULT_GENERATED = 0;

	/**
	 * The '<em><b>ALWAYS GENERATED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALWAYS_GENERATED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ALWAYS_GENERATED = 1;

	/**
	 * The '<em><b>DEFAULT GENERATED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DEFAULT GENERATED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DEFAULT_GENERATED
	 * @generated
	 * @ordered
	 */
	public static final GenerateType DEFAULT_GENERATED_LITERAL = new GenerateType(DEFAULT_GENERATED, "DEFAULT_GENERATED", "DEFAULT_GENERATED"); //$NON-NLS-1$

	/**
	 * The '<em><b>ALWAYS GENERATED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ALWAYS GENERATED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALWAYS_GENERATED
	 * @generated
	 * @ordered
	 */
	public static final GenerateType ALWAYS_GENERATED_LITERAL = new GenerateType(ALWAYS_GENERATED, "ALWAYS_GENERATED", "ALWAYS_GENERATED"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Generate Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final GenerateType[] VALUES_ARRAY =
		new GenerateType[] {
			DEFAULT_GENERATED_LITERAL,
			ALWAYS_GENERATED_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Generate Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Generate Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GenerateType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			GenerateType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Generate Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GenerateType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			GenerateType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Generate Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GenerateType get(int value) {
		switch (value) {
			case DEFAULT_GENERATED: return DEFAULT_GENERATED_LITERAL;
			case ALWAYS_GENERATED: return ALWAYS_GENERATED_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private GenerateType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //GenerateType
