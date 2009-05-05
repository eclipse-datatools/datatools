/**
 * <copyright>
 * </copyright>
 *
 * $Id: TypeOfDefault.java,v 1.4 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Type Of Default</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getTypeOfDefault()
 * @model
 * @generated
 */
public final class TypeOfDefault extends AbstractEnumerator {
    /**
	 * The '<em><b>NO DEFAULT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NO DEFAULT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_DEFAULT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NO_DEFAULT = 0;

    /**
	 * The '<em><b>USER DEFINED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>USER DEFINED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #USER_DEFINED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int USER_DEFINED = 1;

    /**
	 * The '<em><b>SYSTEM DEFINED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SYSTEM DEFINED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SYSTEM_DEFINED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SYSTEM_DEFINED = 2;

    /**
	 * The '<em><b>COMPUTED VALUE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COMPUTED VALUE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COMPUTED_VALUE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int COMPUTED_VALUE = 3;

    /**
	 * The '<em><b>NO DEFAULT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_DEFAULT
	 * @generated
	 * @ordered
	 */
	public static final TypeOfDefault NO_DEFAULT_LITERAL = new TypeOfDefault(NO_DEFAULT, "NO_DEFAULT", "NO_DEFAULT");

    /**
	 * The '<em><b>USER DEFINED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #USER_DEFINED
	 * @generated
	 * @ordered
	 */
	public static final TypeOfDefault USER_DEFINED_LITERAL = new TypeOfDefault(USER_DEFINED, "USER_DEFINED", "USER_DEFINED");

    /**
	 * The '<em><b>SYSTEM DEFINED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SYSTEM_DEFINED
	 * @generated
	 * @ordered
	 */
	public static final TypeOfDefault SYSTEM_DEFINED_LITERAL = new TypeOfDefault(SYSTEM_DEFINED, "SYSTEM_DEFINED", "SYSTEM_DEFINED");

    /**
	 * The '<em><b>COMPUTED VALUE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMPUTED_VALUE
	 * @generated
	 * @ordered
	 */
	public static final TypeOfDefault COMPUTED_VALUE_LITERAL = new TypeOfDefault(COMPUTED_VALUE, "COMPUTED_VALUE", "COMPUTED_VALUE");

    /**
	 * An array of all the '<em><b>Type Of Default</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TypeOfDefault[] VALUES_ARRAY =
        new TypeOfDefault[] {
			NO_DEFAULT_LITERAL,
			USER_DEFINED_LITERAL,
			SYSTEM_DEFINED_LITERAL,
			COMPUTED_VALUE_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Type Of Default</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Type Of Default</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TypeOfDefault get(String literal)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TypeOfDefault result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Type Of Default</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TypeOfDefault getByName(String name)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TypeOfDefault result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Type Of Default</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TypeOfDefault get(int value)
    {
		switch (value) {
			case NO_DEFAULT: return NO_DEFAULT_LITERAL;
			case USER_DEFINED: return USER_DEFINED_LITERAL;
			case SYSTEM_DEFINED: return SYSTEM_DEFINED_LITERAL;
			case COMPUTED_VALUE: return COMPUTED_VALUE_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private TypeOfDefault(int value, String name, String literal)
    {
		super(value, name, literal);
	}

} //TypeOfDefault
