package org.eclipse.datatools.enablement.ibm.db2.luw.model;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Member Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWMemberType()
 * @model
 * @generated
 */
public final class LUWMemberType extends AbstractEnumerator {
	/**
	 * The '<em><b>MEMBER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MEMBER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MEMBER_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MEMBER = 0;

	/**
	 * The '<em><b>CF</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CF</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CF_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CF = 1;

	/**
	 * The '<em><b>MEMBER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MEMBER
	 * @generated
	 * @ordered
	 */
	public static final LUWMemberType MEMBER_LITERAL = new LUWMemberType(MEMBER, "MEMBER", "MEMBER"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>CF</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CF
	 * @generated
	 * @ordered
	 */
	public static final LUWMemberType CF_LITERAL = new LUWMemberType(CF, "CF", "CF"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Member Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LUWMemberType[] VALUES_ARRAY =
		new LUWMemberType[] {
			MEMBER_LITERAL,
			CF_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Member Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Member Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWMemberType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWMemberType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Member Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWMemberType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWMemberType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Member Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWMemberType get(int value) {
		switch (value) {
			case MEMBER: return MEMBER_LITERAL;
			case CF: return CF_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LUWMemberType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //LUWMemberType
