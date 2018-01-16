/**
 * <copyright>
 * </copyright>
 *
 * $Id: TriggerDefinitionImpl.java,v 1.2 2006/03/09 23:48:17 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Trigger Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TriggerDefinitionImpl#getMaximumReferencePartLength <em>Maximum Reference Part Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TriggerDefinitionImpl#getMaximumActionBodyLength <em>Maximum Action Body Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TriggerDefinitionImpl#isTypeSupported <em>Type Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TriggerDefinitionImpl#isWhenClauseSupported <em>When Clause Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TriggerDefinitionImpl#isGranularitySupported <em>Granularity Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TriggerDefinitionImpl#isReferencesClauseSupported <em>References Clause Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TriggerDefinitionImpl#isPerColumnUpdateTriggerSupported <em>Per Column Update Trigger Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TriggerDefinitionImpl#isInsteadOfTriggerSupported <em>Instead Of Trigger Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TriggerDefinitionImpl#isRowTriggerReferenceSupported <em>Row Trigger Reference Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TriggerDefinitionImpl#isTableTriggerReferenceSupported <em>Table Trigger Reference Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TriggerDefinitionImpl#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TriggerDefinitionImpl extends EObjectImpl implements TriggerDefinition {
	/**
	 * The default value of the '{@link #getMaximumReferencePartLength() <em>Maximum Reference Part Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumReferencePartLength()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_REFERENCE_PART_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumReferencePartLength() <em>Maximum Reference Part Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumReferencePartLength()
	 * @generated
	 * @ordered
	 */
	protected int maximumReferencePartLength = MAXIMUM_REFERENCE_PART_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumActionBodyLength() <em>Maximum Action Body Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumActionBodyLength()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_ACTION_BODY_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumActionBodyLength() <em>Maximum Action Body Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumActionBodyLength()
	 * @generated
	 * @ordered
	 */
	protected int maximumActionBodyLength = MAXIMUM_ACTION_BODY_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #isTypeSupported() <em>Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTypeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TYPE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTypeSupported() <em>Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTypeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean typeSupported = TYPE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isWhenClauseSupported() <em>When Clause Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWhenClauseSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean WHEN_CLAUSE_SUPPORTED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isWhenClauseSupported() <em>When Clause Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWhenClauseSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean whenClauseSupported = WHEN_CLAUSE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isGranularitySupported() <em>Granularity Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGranularitySupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean GRANULARITY_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isGranularitySupported() <em>Granularity Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGranularitySupported()
	 * @generated
	 * @ordered
	 */
	protected boolean granularitySupported = GRANULARITY_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isReferencesClauseSupported() <em>References Clause Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReferencesClauseSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REFERENCES_CLAUSE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReferencesClauseSupported() <em>References Clause Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReferencesClauseSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean referencesClauseSupported = REFERENCES_CLAUSE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isPerColumnUpdateTriggerSupported() <em>Per Column Update Trigger Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPerColumnUpdateTriggerSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PER_COLUMN_UPDATE_TRIGGER_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPerColumnUpdateTriggerSupported() <em>Per Column Update Trigger Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPerColumnUpdateTriggerSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean perColumnUpdateTriggerSupported = PER_COLUMN_UPDATE_TRIGGER_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isInsteadOfTriggerSupported() <em>Instead Of Trigger Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInsteadOfTriggerSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INSTEAD_OF_TRIGGER_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInsteadOfTriggerSupported() <em>Instead Of Trigger Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInsteadOfTriggerSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean insteadOfTriggerSupported = INSTEAD_OF_TRIGGER_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isRowTriggerReferenceSupported() <em>Row Trigger Reference Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowTriggerReferenceSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ROW_TRIGGER_REFERENCE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRowTriggerReferenceSupported() <em>Row Trigger Reference Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowTriggerReferenceSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean rowTriggerReferenceSupported = ROW_TRIGGER_REFERENCE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isTableTriggerReferenceSupported() <em>Table Trigger Reference Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTableTriggerReferenceSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TABLE_TRIGGER_REFERENCE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTableTriggerReferenceSupported() <em>Table Trigger Reference Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTableTriggerReferenceSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean tableTriggerReferenceSupported = TABLE_TRIGGER_REFERENCE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumIdentifierLength() <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumIdentifierLength() <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected int maximumIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TriggerDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.Literals.TRIGGER_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumReferencePartLength() {
		return maximumReferencePartLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumReferencePartLength(int newMaximumReferencePartLength) {
		int oldMaximumReferencePartLength = maximumReferencePartLength;
		maximumReferencePartLength = newMaximumReferencePartLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TRIGGER_DEFINITION__MAXIMUM_REFERENCE_PART_LENGTH, oldMaximumReferencePartLength, maximumReferencePartLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumActionBodyLength() {
		return maximumActionBodyLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumActionBodyLength(int newMaximumActionBodyLength) {
		int oldMaximumActionBodyLength = maximumActionBodyLength;
		maximumActionBodyLength = newMaximumActionBodyLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TRIGGER_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH, oldMaximumActionBodyLength, maximumActionBodyLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTypeSupported() {
		return typeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeSupported(boolean newTypeSupported) {
		boolean oldTypeSupported = typeSupported;
		typeSupported = newTypeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TRIGGER_DEFINITION__TYPE_SUPPORTED, oldTypeSupported, typeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isWhenClauseSupported() {
		return whenClauseSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWhenClauseSupported(boolean newWhenClauseSupported) {
		boolean oldWhenClauseSupported = whenClauseSupported;
		whenClauseSupported = newWhenClauseSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TRIGGER_DEFINITION__WHEN_CLAUSE_SUPPORTED, oldWhenClauseSupported, whenClauseSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isGranularitySupported() {
		return granularitySupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGranularitySupported(boolean newGranularitySupported) {
		boolean oldGranularitySupported = granularitySupported;
		granularitySupported = newGranularitySupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TRIGGER_DEFINITION__GRANULARITY_SUPPORTED, oldGranularitySupported, granularitySupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReferencesClauseSupported() {
		return referencesClauseSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencesClauseSupported(boolean newReferencesClauseSupported) {
		boolean oldReferencesClauseSupported = referencesClauseSupported;
		referencesClauseSupported = newReferencesClauseSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TRIGGER_DEFINITION__REFERENCES_CLAUSE_SUPPORTED, oldReferencesClauseSupported, referencesClauseSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPerColumnUpdateTriggerSupported() {
		return perColumnUpdateTriggerSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPerColumnUpdateTriggerSupported(boolean newPerColumnUpdateTriggerSupported) {
		boolean oldPerColumnUpdateTriggerSupported = perColumnUpdateTriggerSupported;
		perColumnUpdateTriggerSupported = newPerColumnUpdateTriggerSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TRIGGER_DEFINITION__PER_COLUMN_UPDATE_TRIGGER_SUPPORTED, oldPerColumnUpdateTriggerSupported, perColumnUpdateTriggerSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInsteadOfTriggerSupported() {
		return insteadOfTriggerSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInsteadOfTriggerSupported(boolean newInsteadOfTriggerSupported) {
		boolean oldInsteadOfTriggerSupported = insteadOfTriggerSupported;
		insteadOfTriggerSupported = newInsteadOfTriggerSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TRIGGER_DEFINITION__INSTEAD_OF_TRIGGER_SUPPORTED, oldInsteadOfTriggerSupported, insteadOfTriggerSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRowTriggerReferenceSupported() {
		return rowTriggerReferenceSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRowTriggerReferenceSupported(boolean newRowTriggerReferenceSupported) {
		boolean oldRowTriggerReferenceSupported = rowTriggerReferenceSupported;
		rowTriggerReferenceSupported = newRowTriggerReferenceSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TRIGGER_DEFINITION__ROW_TRIGGER_REFERENCE_SUPPORTED, oldRowTriggerReferenceSupported, rowTriggerReferenceSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTableTriggerReferenceSupported() {
		return tableTriggerReferenceSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTableTriggerReferenceSupported(boolean newTableTriggerReferenceSupported) {
		boolean oldTableTriggerReferenceSupported = tableTriggerReferenceSupported;
		tableTriggerReferenceSupported = newTableTriggerReferenceSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TRIGGER_DEFINITION__TABLE_TRIGGER_REFERENCE_SUPPORTED, oldTableTriggerReferenceSupported, tableTriggerReferenceSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumIdentifierLength() {
		return maximumIdentifierLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumIdentifierLength(int newMaximumIdentifierLength) {
		int oldMaximumIdentifierLength = maximumIdentifierLength;
		maximumIdentifierLength = newMaximumIdentifierLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TRIGGER_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH, oldMaximumIdentifierLength, maximumIdentifierLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__MAXIMUM_REFERENCE_PART_LENGTH:
				return new Integer(getMaximumReferencePartLength());
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH:
				return new Integer(getMaximumActionBodyLength());
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__TYPE_SUPPORTED:
				return isTypeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__WHEN_CLAUSE_SUPPORTED:
				return isWhenClauseSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__GRANULARITY_SUPPORTED:
				return isGranularitySupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__REFERENCES_CLAUSE_SUPPORTED:
				return isReferencesClauseSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__PER_COLUMN_UPDATE_TRIGGER_SUPPORTED:
				return isPerColumnUpdateTriggerSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__INSTEAD_OF_TRIGGER_SUPPORTED:
				return isInsteadOfTriggerSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__ROW_TRIGGER_REFERENCE_SUPPORTED:
				return isRowTriggerReferenceSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__TABLE_TRIGGER_REFERENCE_SUPPORTED:
				return isTableTriggerReferenceSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				return new Integer(getMaximumIdentifierLength());
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__MAXIMUM_REFERENCE_PART_LENGTH:
				setMaximumReferencePartLength(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH:
				setMaximumActionBodyLength(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__TYPE_SUPPORTED:
				setTypeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__WHEN_CLAUSE_SUPPORTED:
				setWhenClauseSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__GRANULARITY_SUPPORTED:
				setGranularitySupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__REFERENCES_CLAUSE_SUPPORTED:
				setReferencesClauseSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__PER_COLUMN_UPDATE_TRIGGER_SUPPORTED:
				setPerColumnUpdateTriggerSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__INSTEAD_OF_TRIGGER_SUPPORTED:
				setInsteadOfTriggerSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__ROW_TRIGGER_REFERENCE_SUPPORTED:
				setRowTriggerReferenceSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__TABLE_TRIGGER_REFERENCE_SUPPORTED:
				setTableTriggerReferenceSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				setMaximumIdentifierLength(((Integer)newValue).intValue());
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__MAXIMUM_REFERENCE_PART_LENGTH:
				setMaximumReferencePartLength(MAXIMUM_REFERENCE_PART_LENGTH_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH:
				setMaximumActionBodyLength(MAXIMUM_ACTION_BODY_LENGTH_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__TYPE_SUPPORTED:
				setTypeSupported(TYPE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__WHEN_CLAUSE_SUPPORTED:
				setWhenClauseSupported(WHEN_CLAUSE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__GRANULARITY_SUPPORTED:
				setGranularitySupported(GRANULARITY_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__REFERENCES_CLAUSE_SUPPORTED:
				setReferencesClauseSupported(REFERENCES_CLAUSE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__PER_COLUMN_UPDATE_TRIGGER_SUPPORTED:
				setPerColumnUpdateTriggerSupported(PER_COLUMN_UPDATE_TRIGGER_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__INSTEAD_OF_TRIGGER_SUPPORTED:
				setInsteadOfTriggerSupported(INSTEAD_OF_TRIGGER_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__ROW_TRIGGER_REFERENCE_SUPPORTED:
				setRowTriggerReferenceSupported(ROW_TRIGGER_REFERENCE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__TABLE_TRIGGER_REFERENCE_SUPPORTED:
				setTableTriggerReferenceSupported(TABLE_TRIGGER_REFERENCE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				setMaximumIdentifierLength(MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__MAXIMUM_REFERENCE_PART_LENGTH:
				return maximumReferencePartLength != MAXIMUM_REFERENCE_PART_LENGTH_EDEFAULT;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH:
				return maximumActionBodyLength != MAXIMUM_ACTION_BODY_LENGTH_EDEFAULT;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__TYPE_SUPPORTED:
				return typeSupported != TYPE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__WHEN_CLAUSE_SUPPORTED:
				return whenClauseSupported != WHEN_CLAUSE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__GRANULARITY_SUPPORTED:
				return granularitySupported != GRANULARITY_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__REFERENCES_CLAUSE_SUPPORTED:
				return referencesClauseSupported != REFERENCES_CLAUSE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__PER_COLUMN_UPDATE_TRIGGER_SUPPORTED:
				return perColumnUpdateTriggerSupported != PER_COLUMN_UPDATE_TRIGGER_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__INSTEAD_OF_TRIGGER_SUPPORTED:
				return insteadOfTriggerSupported != INSTEAD_OF_TRIGGER_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__ROW_TRIGGER_REFERENCE_SUPPORTED:
				return rowTriggerReferenceSupported != ROW_TRIGGER_REFERENCE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__TABLE_TRIGGER_REFERENCE_SUPPORTED:
				return tableTriggerReferenceSupported != TABLE_TRIGGER_REFERENCE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				return maximumIdentifierLength != MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (maximumReferencePartLength: "); //$NON-NLS-1$
		result.append(maximumReferencePartLength);
		result.append(", maximumActionBodyLength: "); //$NON-NLS-1$
		result.append(maximumActionBodyLength);
		result.append(", typeSupported: "); //$NON-NLS-1$
		result.append(typeSupported);
		result.append(", whenClauseSupported: "); //$NON-NLS-1$
		result.append(whenClauseSupported);
		result.append(", granularitySupported: "); //$NON-NLS-1$
		result.append(granularitySupported);
		result.append(", referencesClauseSupported: "); //$NON-NLS-1$
		result.append(referencesClauseSupported);
		result.append(", perColumnUpdateTriggerSupported: "); //$NON-NLS-1$
		result.append(perColumnUpdateTriggerSupported);
		result.append(", insteadOfTriggerSupported: "); //$NON-NLS-1$
		result.append(insteadOfTriggerSupported);
		result.append(", rowTriggerReferenceSupported: "); //$NON-NLS-1$
		result.append(rowTriggerReferenceSupported);
		result.append(", tableTriggerReferenceSupported: "); //$NON-NLS-1$
		result.append(tableTriggerReferenceSupported);
		result.append(", maximumIdentifierLength: "); //$NON-NLS-1$
		result.append(maximumIdentifierLength);
		result.append(')');
		return result.toString();
	}

} //TriggerDefinitionImpl
