/**
 * <copyright>
 * </copyright>
 *
 * $Id: SearchConditionCombinedItemProvider.java,v 1.1 2007/03/22 17:10:12 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adpater for a {@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SearchConditionCombinedItemProvider
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
  public SearchConditionCombinedItemProvider(AdapterFactory adapterFactory) {
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

            addCombinedOperatorPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Combined Operator feature.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected void addCombinedOperatorPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SearchConditionCombined_combinedOperator_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_SearchConditionCombined_combinedOperator_feature", "_UI_SearchConditionCombined_type"),
                 SQLQueryModelPackage.eINSTANCE.getSearchConditionCombined_CombinedOperator(),
                 true,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
            childrenFeatures.add(SQLQueryModelPackage.eINSTANCE.getSearchConditionCombined_LeftCondition());
            childrenFeatures.add(SQLQueryModelPackage.eINSTANCE.getSearchConditionCombined_RightCondition());
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
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getText(Object object) {
        String label = ((SearchConditionCombined)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_SearchConditionCombined_type") :
            getString("_UI_SearchConditionCombined_type") + " " + label;
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

        switch (notification.getFeatureID(SearchConditionCombined.class)) {
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__COMBINED_OPERATOR:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION:
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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
