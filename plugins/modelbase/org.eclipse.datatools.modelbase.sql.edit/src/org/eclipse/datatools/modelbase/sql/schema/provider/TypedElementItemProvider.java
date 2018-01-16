/**
 * <copyright>
 * </copyright>
 *
 * $Id: TypedElementItemProvider.java,v 1.3 2007/05/31 00:29:18 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.sql.schema.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesFactory;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
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
 * This is the item provider adapter for a {@link org.eclipse.datatools.modelbase.sql.schema.TypedElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TypedElementItemProvider
	extends SQLObjectItemProvider
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
	public TypedElementItemProvider(AdapterFactory adapterFactory) {
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

			addReferencedTypePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Referenced Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addReferencedTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TypedElement_referencedType_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_TypedElement_referencedType_feature", "_UI_TypedElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SQLSchemaPackage.Literals.TYPED_ELEMENT__REFERENCED_TYPE,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(SQLSchemaPackage.Literals.TYPED_ELEMENT__CONTAINED_TYPE);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns TypedElement.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TypedElement")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((TypedElement)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_TypedElement_type") : //$NON-NLS-1$
			getString("_UI_TypedElement_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(TypedElement.class)) {
			case SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(SQLSchemaPackage.Literals.TYPED_ELEMENT__CONTAINED_TYPE,
				 SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType()));

		newChildDescriptors.add
			(createChildParameter
				(SQLSchemaPackage.Literals.TYPED_ELEMENT__CONTAINED_TYPE,
				 SQLDataTypesFactory.eINSTANCE.createBooleanDataType()));

		newChildDescriptors.add
			(createChildParameter
				(SQLSchemaPackage.Literals.TYPED_ELEMENT__CONTAINED_TYPE,
				 SQLDataTypesFactory.eINSTANCE.createIntervalDataType()));

		newChildDescriptors.add
			(createChildParameter
				(SQLSchemaPackage.Literals.TYPED_ELEMENT__CONTAINED_TYPE,
				 SQLDataTypesFactory.eINSTANCE.createBinaryStringDataType()));

		newChildDescriptors.add
			(createChildParameter
				(SQLSchemaPackage.Literals.TYPED_ELEMENT__CONTAINED_TYPE,
				 SQLDataTypesFactory.eINSTANCE.createTimeDataType()));

		newChildDescriptors.add
			(createChildParameter
				(SQLSchemaPackage.Literals.TYPED_ELEMENT__CONTAINED_TYPE,
				 SQLDataTypesFactory.eINSTANCE.createFixedPrecisionDataType()));

		newChildDescriptors.add
			(createChildParameter
				(SQLSchemaPackage.Literals.TYPED_ELEMENT__CONTAINED_TYPE,
				 SQLDataTypesFactory.eINSTANCE.createDataLinkDataType()));

		newChildDescriptors.add
			(createChildParameter
				(SQLSchemaPackage.Literals.TYPED_ELEMENT__CONTAINED_TYPE,
				 SQLDataTypesFactory.eINSTANCE.createDateDataType()));

		newChildDescriptors.add
			(createChildParameter
				(SQLSchemaPackage.Literals.TYPED_ELEMENT__CONTAINED_TYPE,
				 SQLDataTypesFactory.eINSTANCE.createApproximateNumericDataType()));

		newChildDescriptors.add
			(createChildParameter
				(SQLSchemaPackage.Literals.TYPED_ELEMENT__CONTAINED_TYPE,
				 SQLDataTypesFactory.eINSTANCE.createIntegerDataType()));

		newChildDescriptors.add
			(createChildParameter
				(SQLSchemaPackage.Literals.TYPED_ELEMENT__CONTAINED_TYPE,
				 SQLDataTypesFactory.eINSTANCE.createXMLDataType()));
	}

}
