/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseColumnImpl.java,v 1.1 2007/03/05 15:52:15 jgraham Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.util.Utils;
import org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Base Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseColumnImpl#getColumnConstraint <em>Column Constraint</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseColumnImpl#getTypeOfDefault <em>Type Of Default</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseColumnImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseColumnImpl#isIsComputedColumn <em>Is Computed Column</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASABaseColumnImpl extends ColumnImpl implements SybaseASABaseColumn 
{
    /**
     * The cached value of the '{@link #getColumnConstraint() <em>Column Constraint</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getColumnConstraint()
     * @generated
     * @ordered
     */
	protected SybaseASABaseColumnCheckConstraint columnConstraint = null;

    /**
     * The default value of the '{@link #getTypeOfDefault() <em>Type Of Default</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTypeOfDefault()
     * @generated
     * @ordered
     */
	protected static final TypeOfDefault TYPE_OF_DEFAULT_EDEFAULT = TypeOfDefault.NO_DEFAULT_LITERAL;

    /**
     * The cached value of the '{@link #getTypeOfDefault() <em>Type Of Default</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTypeOfDefault()
     * @generated
     * @ordered
     */
	protected TypeOfDefault typeOfDefault = TYPE_OF_DEFAULT_EDEFAULT;

    /**
     * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUnique()
     * @generated
     * @ordered
     */
	protected static final boolean UNIQUE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isUnique() <em>Unique</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUnique()
     * @generated
     * @ordered
     */
	protected boolean unique = UNIQUE_EDEFAULT;

    /**
     * The default value of the '{@link #isIsComputedColumn() <em>Is Computed Column</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isIsComputedColumn()
     * @generated
     * @ordered
     */
	protected static final boolean IS_COMPUTED_COLUMN_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsComputedColumn() <em>Is Computed Column</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isIsComputedColumn()
     * @generated
     * @ordered
     */
	protected boolean isComputedColumn = IS_COMPUTED_COLUMN_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASABaseColumnImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass()
    {
        return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_COLUMN;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASABaseColumnCheckConstraint getColumnConstraint()
    {
        return columnConstraint;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetColumnConstraint(SybaseASABaseColumnCheckConstraint newColumnConstraint, NotificationChain msgs)
    {
        SybaseASABaseColumnCheckConstraint oldColumnConstraint = columnConstraint;
        columnConstraint = newColumnConstraint;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__COLUMN_CONSTRAINT, oldColumnConstraint, newColumnConstraint);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setColumnConstraint(SybaseASABaseColumnCheckConstraint newColumnConstraint)
    {
        if (newColumnConstraint != columnConstraint)
        {
            NotificationChain msgs = null;
            if (columnConstraint != null)
                msgs = ((InternalEObject)columnConstraint).eInverseRemove(this, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__COLUMN, SybaseASABaseColumnCheckConstraint.class, msgs);
            if (newColumnConstraint != null)
                msgs = ((InternalEObject)newColumnConstraint).eInverseAdd(this, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__COLUMN, SybaseASABaseColumnCheckConstraint.class, msgs);
            msgs = basicSetColumnConstraint(newColumnConstraint, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__COLUMN_CONSTRAINT, newColumnConstraint, newColumnConstraint));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TypeOfDefault getTypeOfDefault()
    {
        return typeOfDefault;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTypeOfDefault(TypeOfDefault newTypeOfDefault)
    {
        TypeOfDefault oldTypeOfDefault = typeOfDefault;
        typeOfDefault = newTypeOfDefault == null ? TYPE_OF_DEFAULT_EDEFAULT : newTypeOfDefault;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__TYPE_OF_DEFAULT, oldTypeOfDefault, typeOfDefault));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isUnique()
    {
        return unique;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setUnique(boolean newUnique)
    {
        boolean oldUnique = unique;
        unique = newUnique;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__UNIQUE, oldUnique, unique));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isIsComputedColumn()
    {
        return isComputedColumn;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setIsComputedColumn(boolean newIsComputedColumn)
    {
        boolean oldIsComputedColumn = isComputedColumn;
        isComputedColumn = newIsComputedColumn;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__IS_COMPUTED_COLUMN, oldIsComputedColumn, isComputedColumn));
    }

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT 
	 */
	public boolean isLiteralDefault() {
		return Utils.isLiteralDefault(this.getTypeOfDefault(), this.getDefaultValue());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getGlobalIncrementPartitionSize() {
		return Utils.getDefaultGlobalIncrementPartitionSize(this.getTypeOfDefault(), this.getDefaultValue());
	}

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__COLUMN_CONSTRAINT:
                if (columnConstraint != null)
                    msgs = ((InternalEObject)columnConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__COLUMN_CONSTRAINT, null, msgs);
                return basicSetColumnConstraint((SybaseASABaseColumnCheckConstraint)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__COLUMN_CONSTRAINT:
                return basicSetColumnConstraint(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
        switch (featureID)
        {
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__COLUMN_CONSTRAINT:
                return getColumnConstraint();
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__TYPE_OF_DEFAULT:
                return getTypeOfDefault();
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__UNIQUE:
                return isUnique() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__IS_COMPUTED_COLUMN:
                return isIsComputedColumn() ? Boolean.TRUE : Boolean.FALSE;
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__COLUMN_CONSTRAINT:
                setColumnConstraint((SybaseASABaseColumnCheckConstraint)newValue);
                return;
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__TYPE_OF_DEFAULT:
                setTypeOfDefault((TypeOfDefault)newValue);
                return;
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__UNIQUE:
                setUnique(((Boolean)newValue).booleanValue());
                return;
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__IS_COMPUTED_COLUMN:
                setIsComputedColumn(((Boolean)newValue).booleanValue());
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eUnset(int featureID)
    {
        switch (featureID)
        {
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__COLUMN_CONSTRAINT:
                setColumnConstraint((SybaseASABaseColumnCheckConstraint)null);
                return;
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__TYPE_OF_DEFAULT:
                setTypeOfDefault(TYPE_OF_DEFAULT_EDEFAULT);
                return;
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__UNIQUE:
                setUnique(UNIQUE_EDEFAULT);
                return;
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__IS_COMPUTED_COLUMN:
                setIsComputedColumn(IS_COMPUTED_COLUMN_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean eIsSet(int featureID)
    {
        switch (featureID)
        {
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__COLUMN_CONSTRAINT:
                return columnConstraint != null;
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__TYPE_OF_DEFAULT:
                return typeOfDefault != TYPE_OF_DEFAULT_EDEFAULT;
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__UNIQUE:
                return unique != UNIQUE_EDEFAULT;
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN__IS_COMPUTED_COLUMN:
                return isComputedColumn != IS_COMPUTED_COLUMN_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String toString()
    {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (typeOfDefault: "); //$NON-NLS-1$
        result.append(typeOfDefault); 
        result.append(", unique: "); //$NON-NLS-1$
        result.append(unique);
        result.append(", isComputedColumn: "); //$NON-NLS-1$
        result.append(isComputedColumn);
        result.append(')');
        return result.toString();
    }

} //SybaseASABaseColumnImpl