/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWContainerType.java,v 1.7 2008/02/05 02:01:24 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Container Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * DB2 Universal Database SQL Reference Version 8.1 (Vol.1 and 2)
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWContainerType()
 * @model
 * @generated
 */
public final class LUWContainerType extends AbstractEnumerator {
	/**
	 * The '<em><b>DEVICE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEVICE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DEVICE = 0;

	/**
	 * The '<em><b>DIRECTORY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIRECTORY_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTORY = 1;

	/**
	 * The '<em><b>FILE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FILE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FILE = 2;

	/**
	 * The '<em><b>DEVICE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DEVICE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DEVICE
	 * @generated
	 * @ordered
	 */
	public static final LUWContainerType DEVICE_LITERAL = new LUWContainerType(DEVICE, "DEVICE", "DEVICE"); //$NON-NLS-1$

	/**
	 * The '<em><b>DIRECTORY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DIRECTORY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DIRECTORY
	 * @generated
	 * @ordered
	 */
	public static final LUWContainerType DIRECTORY_LITERAL = new LUWContainerType(DIRECTORY, "DIRECTORY", "DIRECTORY"); //$NON-NLS-1$

	/**
	 * The '<em><b>FILE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FILE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FILE
	 * @generated
	 * @ordered
	 */
	public static final LUWContainerType FILE_LITERAL = new LUWContainerType(FILE, "FILE", "FILE"); //$NON-NLS-1$

	/**
	 * An array of all the '<em><b>Container Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LUWContainerType[] VALUES_ARRAY =
		new LUWContainerType[] {
			DEVICE_LITERAL,
			DIRECTORY_LITERAL,
			FILE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Container Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Container Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWContainerType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWContainerType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Container Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWContainerType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LUWContainerType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Container Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LUWContainerType get(int value) {
		switch (value) {
			case DEVICE: return DEVICE_LITERAL;
			case DIRECTORY: return DIRECTORY_LITERAL;
			case FILE: return FILE_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LUWContainerType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //LUWContainerType
