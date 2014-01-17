package org.eclipse.datatools.enablement.ibm.db2.luw.model;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Member State Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getMemberStateType()
 * @model
 * @generated
 */
public final class MemberStateType extends AbstractEnumerator {
	/**
	 * The '<em><b>STARTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STARTED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STARTED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int STARTED = 0;

	/**
	 * The '<em><b>STOPPED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STOPPED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STOPPED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int STOPPED = 1;

	/**
	 * The '<em><b>RESTARTING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RESTARTING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RESTARTING_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RESTARTING = 2;

	/**
	 * The '<em><b>WAITING FOR FAILBACK</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WAITING FOR FAILBACK</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WAITING_FOR_FAILBACK_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int WAITING_FOR_FAILBACK = 3;

	/**
	 * The '<em><b>ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ERROR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ERROR_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ERROR = 4;

	/**
	 * The '<em><b>UNKNOWN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNKNOWN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNKNOWN = 5;

	/**
	 * The '<em><b>BECOMING PRIMARY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BECOMING PRIMARY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BECOMING_PRIMARY_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BECOMING_PRIMARY = 6;

	/**
	 * The '<em><b>PRIMARY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PRIMARY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PRIMARY_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PRIMARY = 7;

	/**
	 * The '<em><b>CATCHUP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CATCHUP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CATCHUP_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CATCHUP = 8;

	/**
	 * The '<em><b>PEER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PEER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PEER_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PEER = 9;

	/**
	 * The '<em><b>STARTED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STARTED
	 * @generated
	 * @ordered
	 */
	public static final MemberStateType STARTED_LITERAL = new MemberStateType(STARTED, "STARTED", "STARTED"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>STOPPED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STOPPED
	 * @generated
	 * @ordered
	 */
	public static final MemberStateType STOPPED_LITERAL = new MemberStateType(STOPPED, "STOPPED", "STOPPED"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>RESTARTING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESTARTING
	 * @generated
	 * @ordered
	 */
	public static final MemberStateType RESTARTING_LITERAL = new MemberStateType(RESTARTING, "RESTARTING", "RESTARTING"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>WAITING FOR FAILBACK</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WAITING_FOR_FAILBACK
	 * @generated
	 * @ordered
	 */
	public static final MemberStateType WAITING_FOR_FAILBACK_LITERAL = new MemberStateType(WAITING_FOR_FAILBACK, "WAITING_FOR_FAILBACK", "WAITING_FOR_FAILBACK"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>ERROR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ERROR
	 * @generated
	 * @ordered
	 */
	public static final MemberStateType ERROR_LITERAL = new MemberStateType(ERROR, "ERROR", "ERROR"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>UNKNOWN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN
	 * @generated
	 * @ordered
	 */
	public static final MemberStateType UNKNOWN_LITERAL = new MemberStateType(UNKNOWN, "UNKNOWN", "UNKNOWN"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>BECOMING PRIMARY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BECOMING_PRIMARY
	 * @generated
	 * @ordered
	 */
	public static final MemberStateType BECOMING_PRIMARY_LITERAL = new MemberStateType(BECOMING_PRIMARY, "BECOMING_PRIMARY", "BECOMING_PRIMARY"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>PRIMARY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PRIMARY
	 * @generated
	 * @ordered
	 */
	public static final MemberStateType PRIMARY_LITERAL = new MemberStateType(PRIMARY, "PRIMARY", "PRIMARY"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>CATCHUP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CATCHUP
	 * @generated
	 * @ordered
	 */
	public static final MemberStateType CATCHUP_LITERAL = new MemberStateType(CATCHUP, "CATCHUP", "CATCHUP"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>PEER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PEER
	 * @generated
	 * @ordered
	 */
	public static final MemberStateType PEER_LITERAL = new MemberStateType(PEER, "PEER", "PEER"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Member State Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final MemberStateType[] VALUES_ARRAY =
		new MemberStateType[] {
			STARTED_LITERAL,
			STOPPED_LITERAL,
			RESTARTING_LITERAL,
			WAITING_FOR_FAILBACK_LITERAL,
			ERROR_LITERAL,
			UNKNOWN_LITERAL,
			BECOMING_PRIMARY_LITERAL,
			PRIMARY_LITERAL,
			CATCHUP_LITERAL,
			PEER_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Member State Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Member State Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MemberStateType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MemberStateType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Member State Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MemberStateType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MemberStateType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Member State Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MemberStateType get(int value) {
		switch (value) {
			case STARTED: return STARTED_LITERAL;
			case STOPPED: return STOPPED_LITERAL;
			case RESTARTING: return RESTARTING_LITERAL;
			case WAITING_FOR_FAILBACK: return WAITING_FOR_FAILBACK_LITERAL;
			case ERROR: return ERROR_LITERAL;
			case UNKNOWN: return UNKNOWN_LITERAL;
			case BECOMING_PRIMARY: return BECOMING_PRIMARY_LITERAL;
			case PRIMARY: return PRIMARY_LITERAL;
			case CATCHUP: return CATCHUP_LITERAL;
			case PEER: return PEER_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private MemberStateType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //MemberStateType
