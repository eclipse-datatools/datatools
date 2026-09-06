/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query.provider;


import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.ProcedureReference;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;

/**
 * This is the item provider adapter for a {@link org.eclipse.datatools.modelbase.sql.query.ProcedureReference} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ProcedureReferenceItemProvider
    extends SQLQueryObjectItemProvider {
    /**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ProcedureReferenceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

    /**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addProcedurePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

    /**
	 * This adds a property descriptor for the Procedure feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addProcedurePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ProcedureReference_procedure_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ProcedureReference_procedure_feature", "_UI_ProcedureReference_type"),
				 SQLQueryModelPackage.Literals.PROCEDURE_REFERENCE__PROCEDURE,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

    /**
	 * This returns ProcedureReference.gif.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ProcedureReference"));
	}

    /**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
	public String getText(Object object) {
		String label = ((ProcedureReference)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ProcedureReference_type") :
			getString("_UI_ProcedureReference_type") + " " + label;
	}

    /**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);
		super.notifyChanged(notification);
	}

}
