/**
 * <copyright>
 * </copyright>
 *
 * $Id: PageSizeType.java,v 1.8 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Page Size Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * DB2 Universal Database SQL Reference Version 8.1 (Vol.1 and 2)
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * PAGESIZE integer [K]
 * 
 * Defines the size of pages used for the bufferpool. The valid values for integer without the suffix K are 4 096, 8 192, 16 384 or 32 768. The valid values for integer with the suffix K are 4, 8, 16 or 32. An error occurs if the page size is not one of these values (SQLSTATE 428DE). The default is 4 096 byte (4K) pages. Any number of spaces is allowed between integer and K, including no space.
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getPageSizeType()
 * @model
 * @generated
 */
public final class PageSizeType extends AbstractEnumerator {
	/**
	 * The '<em><b>FOUR K</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FOUR_K_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FOUR_K = 4096;

	/**
	 * The '<em><b>EIGHT K</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EIGHT_K_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int EIGHT_K = 8192;

	/**
	 * The '<em><b>SIXTEEN K</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SIXTEEN_K_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SIXTEEN_K = 16384;

	/**
	 * The '<em><b>THIRTY TWO K</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #THIRTY_TWO_K_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int THIRTY_TWO_K = 32768;

	/**
	 * The '<em><b>FOUR KB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FOUR_KB_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FOUR_KB = 4;

	/**
	 * The '<em><b>EIGHT KB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EIGHT_KB_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int EIGHT_KB = 8;

	/**
	 * The '<em><b>SIXTEEN KB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SIXTEEN_KB_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SIXTEEN_KB = 16;

	/**
	 * The '<em><b>THIRTY TWO KB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #THIRTY_TWO_KB_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int THIRTY_TWO_KB = 32;

	/**
	 * The '<em><b>FOUR K</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FOUR K</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FOUR_K
	 * @generated
	 * @ordered
	 */
	public static final PageSizeType FOUR_K_LITERAL = new PageSizeType(FOUR_K, "FOUR_K", "FOUR_K"); //$NON-NLS-1$

	/**
	 * The '<em><b>EIGHT K</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EIGHT K</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EIGHT_K
	 * @generated
	 * @ordered
	 */
	public static final PageSizeType EIGHT_K_LITERAL = new PageSizeType(EIGHT_K, "EIGHT_K", "EIGHT_K"); //$NON-NLS-1$

	/**
	 * The '<em><b>SIXTEEN K</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SIXTEEN K</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SIXTEEN_K
	 * @generated
	 * @ordered
	 */
	public static final PageSizeType SIXTEEN_K_LITERAL = new PageSizeType(SIXTEEN_K, "SIXTEEN_K", "SIXTEEN_K"); //$NON-NLS-1$

	/**
	 * The '<em><b>THIRTY TWO K</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>THIRTY TWO K</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #THIRTY_TWO_K
	 * @generated
	 * @ordered
	 */
	public static final PageSizeType THIRTY_TWO_K_LITERAL = new PageSizeType(THIRTY_TWO_K, "THIRTY_TWO_K", "THIRTY_TWO_K"); //$NON-NLS-1$

	/**
	 * The '<em><b>FOUR KB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FOUR KB</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FOUR_KB
	 * @generated
	 * @ordered
	 */
	public static final PageSizeType FOUR_KB_LITERAL = new PageSizeType(FOUR_KB, "FOUR_KB", "FOUR_KB"); //$NON-NLS-1$

	/**
	 * The '<em><b>EIGHT KB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EIGHT KB</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EIGHT_KB
	 * @generated
	 * @ordered
	 */
	public static final PageSizeType EIGHT_KB_LITERAL = new PageSizeType(EIGHT_KB, "EIGHT_KB", "EIGHT_KB"); //$NON-NLS-1$

	/**
	 * The '<em><b>SIXTEEN KB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SIXTEEN KB</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SIXTEEN_KB
	 * @generated
	 * @ordered
	 */
	public static final PageSizeType SIXTEEN_KB_LITERAL = new PageSizeType(SIXTEEN_KB, "SIXTEEN_KB", "SIXTEEN_KB"); //$NON-NLS-1$

	/**
	 * The '<em><b>THIRTY TWO KB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>THIRTY TWO KB</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #THIRTY_TWO_KB
	 * @generated
	 * @ordered
	 */
	public static final PageSizeType THIRTY_TWO_KB_LITERAL = new PageSizeType(THIRTY_TWO_KB, "THIRTY_TWO_KB", "THIRTY_TWO_KB"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Page Size Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PageSizeType[] VALUES_ARRAY =
		new PageSizeType[] {
			FOUR_K_LITERAL,
			EIGHT_K_LITERAL,
			SIXTEEN_K_LITERAL,
			THIRTY_TWO_K_LITERAL,
			FOUR_KB_LITERAL,
			EIGHT_KB_LITERAL,
			SIXTEEN_KB_LITERAL,
			THIRTY_TWO_KB_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Page Size Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Page Size Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PageSizeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PageSizeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Page Size Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PageSizeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PageSizeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Page Size Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PageSizeType get(int value) {
		switch (value) {
			case FOUR_K: return FOUR_K_LITERAL;
			case EIGHT_K: return EIGHT_K_LITERAL;
			case SIXTEEN_K: return SIXTEEN_K_LITERAL;
			case THIRTY_TWO_K: return THIRTY_TWO_K_LITERAL;
			case FOUR_KB: return FOUR_KB_LITERAL;
			case EIGHT_KB: return EIGHT_KB_LITERAL;
			case SIXTEEN_KB: return SIXTEEN_KB_LITERAL;
			case THIRTY_TWO_KB: return THIRTY_TWO_KB_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private PageSizeType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //PageSizeType
