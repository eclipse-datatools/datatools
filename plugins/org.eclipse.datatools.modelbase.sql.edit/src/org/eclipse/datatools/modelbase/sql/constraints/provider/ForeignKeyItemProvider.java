/**
 * <copyright>
 * </copyright>
 *
 * $Id: ForeignKeyItemProvider.java,v 1.1 2005/08/02 22:56:19 ledunnel Exp $
 */
package org.eclipse.datatools.modelbase.sql.constraints.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.schema.provider.SqlmodelEditPlugin;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;


/**
 * This is the item provider adapter for a {@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ForeignKeyItemProvider
	extends ReferenceConstraintItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForeignKeyItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addMatchPropertyDescriptor(object);
			addOnUpdatePropertyDescriptor(object);
			addOnDeletePropertyDescriptor(object);
			addUniqueConstraintPropertyDescriptor(object);
			addReferencedMembersPropertyDescriptor(object);
			addUniqueIndexPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Match feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMatchPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ForeignKey_match_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ForeignKey_match_feature", "_UI_ForeignKey_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SQLConstraintsPackage.eINSTANCE.getForeignKey_Match(),
				 true,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the On Update feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOnUpdatePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ForeignKey_onUpdate_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ForeignKey_onUpdate_feature", "_UI_ForeignKey_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SQLConstraintsPackage.eINSTANCE.getForeignKey_OnUpdate(),
				 true,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the On Delete feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOnDeletePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ForeignKey_onDelete_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ForeignKey_onDelete_feature", "_UI_ForeignKey_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SQLConstraintsPackage.eINSTANCE.getForeignKey_OnDelete(),
				 true,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Unique Constraint feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUniqueConstraintPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ForeignKey_uniqueConstraint_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ForeignKey_uniqueConstraint_feature", "_UI_ForeignKey_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SQLConstraintsPackage.eINSTANCE.getForeignKey_UniqueConstraint(),
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Referenced Members feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addReferencedMembersPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ForeignKey_referencedMembers_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ForeignKey_referencedMembers_feature", "_UI_ForeignKey_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SQLConstraintsPackage.eINSTANCE.getForeignKey_ReferencedMembers(),
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Unique Index feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUniqueIndexPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ForeignKey_uniqueIndex_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ForeignKey_uniqueIndex_feature", "_UI_ForeignKey_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SQLConstraintsPackage.eINSTANCE.getForeignKey_UniqueIndex(),
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This returns ForeignKey.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return getResourceLocator().getImage("full/obj16/ForeignKey"); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((ForeignKey)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ForeignKey_type") : //$NON-NLS-1$
			getString("_UI_ForeignKey_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ForeignKey.class)) {
			case SQLConstraintsPackage.FOREIGN_KEY__MATCH:
			case SQLConstraintsPackage.FOREIGN_KEY__ON_UPDATE:
			case SQLConstraintsPackage.FOREIGN_KEY__ON_DELETE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing all of the children that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return SqlmodelEditPlugin.INSTANCE;
	}

}
