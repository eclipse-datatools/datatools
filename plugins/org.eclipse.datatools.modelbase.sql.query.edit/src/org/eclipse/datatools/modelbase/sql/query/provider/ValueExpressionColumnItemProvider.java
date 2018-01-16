/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionColumnItemProvider.java,v 1.1 2007/09/25 23:18:02 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.provider;


import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
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

/**
 * This is the item provider adapter for a {@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ValueExpressionColumnItemProvider
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
  public ValueExpressionColumnItemProvider(AdapterFactory adapterFactory) {
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

            addAssignmentExprTargetPropertyDescriptor(object);
            addInsertStatementPropertyDescriptor(object);
            addTableExprPropertyDescriptor(object);
            addTableInDatabasePropertyDescriptor(object);
            addMergeInsertSpecPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Assignment Expr Target feature.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected void addAssignmentExprTargetPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ValueExpressionColumn_assignmentExprTarget_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ValueExpressionColumn_assignmentExprTarget_feature", "_UI_ValueExpressionColumn_type"),
                 SQLQueryModelPackage.Literals.VALUE_EXPRESSION_COLUMN__ASSIGNMENT_EXPR_TARGET,
                 true,
                 false,
                 false,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Table Expr feature.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected void addTableExprPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ValueExpressionColumn_tableExpr_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ValueExpressionColumn_tableExpr_feature", "_UI_ValueExpressionColumn_type"),
                 SQLQueryModelPackage.Literals.VALUE_EXPRESSION_COLUMN__TABLE_EXPR,
                 true,
                 false,
                 false,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Table In Database feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addTableInDatabasePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ValueExpressionColumn_tableInDatabase_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ValueExpressionColumn_tableInDatabase_feature", "_UI_ValueExpressionColumn_type"),
                 SQLQueryModelPackage.Literals.VALUE_EXPRESSION_COLUMN__TABLE_IN_DATABASE,
                 true,
                 false,
                 false,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Merge Insert Spec feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addMergeInsertSpecPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ValueExpressionColumn_mergeInsertSpec_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ValueExpressionColumn_mergeInsertSpec_feature", "_UI_ValueExpressionColumn_type"),
                 SQLQueryModelPackage.Literals.VALUE_EXPRESSION_COLUMN__MERGE_INSERT_SPEC,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Insert Statement feature.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected void addInsertStatementPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ValueExpressionColumn_insertStatement_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ValueExpressionColumn_insertStatement_feature", "_UI_ValueExpressionColumn_type"),
                 SQLQueryModelPackage.Literals.VALUE_EXPRESSION_COLUMN__INSERT_STATEMENT,
                 true,
                 false,
                 false,
                 null,
                 null,
                 null));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getText(Object object) {
        String label = ((ValueExpressionColumn)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_ValueExpressionColumn_type") :
            getString("_UI_ValueExpressionColumn_type") + " " + label;
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
        super.notifyChanged(notification);
    }

}
