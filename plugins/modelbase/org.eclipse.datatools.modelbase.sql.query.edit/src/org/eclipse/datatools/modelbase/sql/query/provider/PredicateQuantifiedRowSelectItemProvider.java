/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateQuantifiedRowSelectItemProvider.java,v 1.1 2007/09/25 23:18:02 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
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
 * This is the item provider adapter for a {@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PredicateQuantifiedRowSelectItemProvider
  extends PredicateQuantifiedItemProvider
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
  public PredicateQuantifiedRowSelectItemProvider(AdapterFactory adapterFactory) {
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

            addQuantifiedTypePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Quantified Type feature.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected void addQuantifiedTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_PredicateQuantifiedRowSelect_quantifiedType_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_PredicateQuantifiedRowSelect_quantifiedType_feature", "_UI_PredicateQuantifiedRowSelect_type"),
                 SQLQueryModelPackage.Literals.PREDICATE_QUANTIFIED_ROW_SELECT__QUANTIFIED_TYPE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
            childrenFeatures.add(SQLQueryModelPackage.Literals.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR);
            childrenFeatures.add(SQLQueryModelPackage.Literals.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST);
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
        String label = ((PredicateQuantifiedRowSelect)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_PredicateQuantifiedRowSelect_type") :
            getString("_UI_PredicateQuantifiedRowSelect_type") + " " + label;
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

        switch (notification.getFeatureID(PredicateQuantifiedRowSelect.class)) {
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUANTIFIED_TYPE:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR:
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
                return;
        }
        super.notifyChanged(notification);
    }

}
