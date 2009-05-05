/**
 * <copyright>
 * </copyright>
 *
 * $Id: TransactionOption.java,v 1.4 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Transaction Option</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getTransactionOption()
 * @model
 * @generated
 */
public final class TransactionOption extends AbstractEnumerator {
    /**
	 * The '<em><b>DELETE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DELETE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DELETE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DELETE = 1;

    /**
	 * The '<em><b>PRESERVE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PRESERVE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PRESERVE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PRESERVE = 2;

    /**
	 * The '<em><b>NOT TRANSACTION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NOT TRANSACTION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_TRANSACTION_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NOT_TRANSACTION = 0;

    /**
	 * The '<em><b>DELETE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DELETE
	 * @generated
	 * @ordered
	 */
	public static final TransactionOption DELETE_LITERAL = new TransactionOption(DELETE, "DELETE", "DELETE");

    /**
	 * The '<em><b>PRESERVE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PRESERVE
	 * @generated
	 * @ordered
	 */
	public static final TransactionOption PRESERVE_LITERAL = new TransactionOption(PRESERVE, "PRESERVE", "PRESERVE");

    /**
	 * The '<em><b>NOT TRANSACTION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_TRANSACTION
	 * @generated
	 * @ordered
	 */
	public static final TransactionOption NOT_TRANSACTION_LITERAL = new TransactionOption(NOT_TRANSACTION, "NOT_TRANSACTION", "NOT_TRANSACTION");

    /**
	 * An array of all the '<em><b>Transaction Option</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TransactionOption[] VALUES_ARRAY =
        new TransactionOption[] {
			DELETE_LITERAL,
			PRESERVE_LITERAL,
			NOT_TRANSACTION_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Transaction Option</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Transaction Option</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionOption get(String literal)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransactionOption result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Transaction Option</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionOption getByName(String name)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransactionOption result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Transaction Option</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionOption get(int value)
    {
		switch (value) {
			case DELETE: return DELETE_LITERAL;
			case PRESERVE: return PRESERVE_LITERAL;
			case NOT_TRANSACTION: return NOT_TRANSACTION_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private TransactionOption(int value, String name, String literal)
    {
		super(value, name, literal);
	}

} //TransactionOption
