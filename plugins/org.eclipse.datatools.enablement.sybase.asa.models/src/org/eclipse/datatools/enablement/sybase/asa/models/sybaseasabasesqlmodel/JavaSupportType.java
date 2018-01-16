/**
 * <copyright>
 * </copyright>
 *
 * $Id: JavaSupportType.java,v 1.4 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Java Support Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getJavaSupportType()
 * @model
 * @generated
 */
public final class JavaSupportType extends AbstractEnumerator {
    /**
	 * The '<em><b>OFF</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OFF</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OFF_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OFF = 0;

    /**
	 * The '<em><b>ON</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ON</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ON_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ON = 1;

    /**
	 * The '<em><b>JDK13</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>JDK13</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JDK13_LITERAL
	 * @model literal="JDK '1.3'"
	 * @generated
	 * @ordered
	 */
	public static final int JDK13 = 2;

    /**
	 * The '<em><b>JDK118</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>JDK118</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JDK118_LITERAL
	 * @model literal="JDK '1.1.8'"
	 * @generated
	 * @ordered
	 */
	public static final int JDK118 = 3;

    /**
	 * The '<em><b>OFF</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OFF
	 * @generated
	 * @ordered
	 */
	public static final JavaSupportType OFF_LITERAL = new JavaSupportType(OFF, "OFF", "OFF");

    /**
	 * The '<em><b>ON</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ON
	 * @generated
	 * @ordered
	 */
	public static final JavaSupportType ON_LITERAL = new JavaSupportType(ON, "ON", "ON");

    /**
	 * The '<em><b>JDK13</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JDK13
	 * @generated
	 * @ordered
	 */
	public static final JavaSupportType JDK13_LITERAL = new JavaSupportType(JDK13, "JDK13", "JDK '1.3'");

    /**
	 * The '<em><b>JDK118</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JDK118
	 * @generated
	 * @ordered
	 */
	public static final JavaSupportType JDK118_LITERAL = new JavaSupportType(JDK118, "JDK118", "JDK '1.1.8'");

    /**
	 * An array of all the '<em><b>Java Support Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final JavaSupportType[] VALUES_ARRAY =
        new JavaSupportType[] {
			OFF_LITERAL,
			ON_LITERAL,
			JDK13_LITERAL,
			JDK118_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Java Support Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Java Support Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JavaSupportType get(String literal)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			JavaSupportType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Java Support Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JavaSupportType getByName(String name)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			JavaSupportType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Java Support Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JavaSupportType get(int value)
    {
		switch (value) {
			case OFF: return OFF_LITERAL;
			case ON: return ON_LITERAL;
			case JDK13: return JDK13_LITERAL;
			case JDK118: return JDK118_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private JavaSupportType(int value, String name, String literal)
    {
		super(value, name, literal);
	}

} //JavaSupportType
