/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateItemProvider.java,v 1.1 2007/03/22 17:10:10 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.provider;


import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
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
 * This is the item provider adpater for a {@link org.eclipse.datatools.modelbase.sql.query.Predicate} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PredicateItemProvider
  extends QuerySearchConditionItemProvider
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
  public PredicateItemProvider(AdapterFactory adapterFactory) {
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

            addNegatedPredicatePropertyDescriptor(object);
            addHasSelectivityPropertyDescriptor(object);
            addSelectivityValuePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Negated Predicate feature.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected void addNegatedPredicatePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Predicate_negatedPredicate_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Predicate_negatedPredicate_feature", "_UI_Predicate_type"),
                 SQLQueryModelPackage.eINSTANCE.getPredicate_NegatedPredicate(),
                 true,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
    }

    /**
     * This adds a property descriptor for the Has Selectivity feature.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected void addHasSelectivityPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Predicate_hasSelectivity_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Predicate_hasSelectivity_feature", "_UI_Predicate_type"),
                 SQLQueryModelPackage.eINSTANCE.getPredicate_HasSelectivity(),
                 true,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
    }

    /**
     * This adds a property descriptor for the Selectivity Value feature.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected void addSelectivityValuePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Predicate_selectivityValue_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Predicate_selectivityValue_feature", "_UI_Predicate_type"),
                 SQLQueryModelPackage.eINSTANCE.getPredicate_SelectivityValue(),
                 true,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getText(Object object) {
        String label = ((Predicate)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_Predicate_type") :
            getString("_UI_Predicate_type") + " " + label;
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

        switch (notification.getFeatureID(Predicate.class)) {
            case SQLQueryModelPackage.PREDICATE__NEGATED_PREDICATE:
            case SQLQueryModelPackage.PREDICATE__HAS_SELECTIVITY:
            case SQLQueryModelPackage.PREDICATE__SELECTIVITY_VALUE:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
        }
        super.notifyChanged(notification);
    }

    /**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public ResourceLocator getResourceLocator() {
        return SQLQueryEditPlugin.INSTANCE;
    }

}
