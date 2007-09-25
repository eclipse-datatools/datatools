/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionFunctionItemProvider.java,v 1.1 2007/03/22 17:10:10 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
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
 * This is the item provider adpater for a {@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ValueExpressionFunctionItemProvider
  extends ValueExpressionAtomicItemProvider
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
  public ValueExpressionFunctionItemProvider(AdapterFactory adapterFactory) {
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

            addSpecialRegisterPropertyDescriptor(object);
            addDistinctPropertyDescriptor(object);
            addColumnFunctionPropertyDescriptor(object);
            addFunctionPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Special Register feature.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected void addSpecialRegisterPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ValueExpressionFunction_specialRegister_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ValueExpressionFunction_specialRegister_feature", "_UI_ValueExpressionFunction_type"),
                 SQLQueryModelPackage.eINSTANCE.getValueExpressionFunction_SpecialRegister(),
                 true,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
    }

    /**
     * This adds a property descriptor for the Distinct feature.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected void addDistinctPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ValueExpressionFunction_distinct_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ValueExpressionFunction_distinct_feature", "_UI_ValueExpressionFunction_type"),
                 SQLQueryModelPackage.eINSTANCE.getValueExpressionFunction_Distinct(),
                 true,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
    }

    /**
     * This adds a property descriptor for the Column Function feature.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected void addColumnFunctionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ValueExpressionFunction_columnFunction_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ValueExpressionFunction_columnFunction_feature", "_UI_ValueExpressionFunction_type"),
                 SQLQueryModelPackage.eINSTANCE.getValueExpressionFunction_ColumnFunction(),
                 true,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
    }

    /**
     * This adds a property descriptor for the Function feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addFunctionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ValueExpressionFunction_function_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ValueExpressionFunction_function_feature", "_UI_ValueExpressionFunction_type"),
                 SQLQueryModelPackage.eINSTANCE.getValueExpressionFunction_Function(),
                 true));
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
            childrenFeatures.add(SQLQueryModelPackage.eINSTANCE.getValueExpressionFunction_ParameterList());
        }
        return childrenFeatures;
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getText(Object object) {
        String label = ((ValueExpressionFunction)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_ValueExpressionFunction_type") :
            getString("_UI_ValueExpressionFunction_type") + " " + label;
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

        switch (notification.getFeatureID(ValueExpressionFunction.class)) {
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__SPECIAL_REGISTER:
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DISTINCT:
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__COLUMN_FUNCTION:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST:
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
