/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryCombinedItemProvider.java,v 1.2 2008/01/31 02:58:42 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
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
 * This is the item provider adapter for a {@link org.eclipse.datatools.modelbase.sql.query.QueryCombined} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class QueryCombinedItemProvider
  extends QueryExpressionBodyItemProvider
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
  public QueryCombinedItemProvider(AdapterFactory adapterFactory) {
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
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_QueryCombined_combinedOperator_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_QueryCombined_combinedOperator_feature", "_UI_QueryCombined_type"),
                 SQLQueryModelPackage.Literals.QUERY_COMBINED__COMBINED_OPERATOR,
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
            childrenFeatures.add(SQLQueryModelPackage.Literals.QUERY_COMBINED__LEFT_QUERY);
            childrenFeatures.add(SQLQueryModelPackage.Literals.QUERY_COMBINED__RIGHT_QUERY);
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
     * This returns QueryCombined.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public Object getImage(Object object) {
        // return getResourceLocator().getImage("full/obj16/QueryCombined");
        Object image = null;
        
        if (object instanceof QueryCombined) {
            QueryCombined queryCombined = (QueryCombined) object;
            QueryCombinedOperator oper = queryCombined.getCombinedOperator();
            int enumVal = oper.getValue();
            switch (enumVal) {
                case QueryCombinedOperator.EXCEPT: 
                    image = getResourceLocator().getImage("full/obj16/QueryCombinedOperatorExcept");
                    break;
                case QueryCombinedOperator.EXCEPT_ALL:
                    image = getResourceLocator().getImage("full/obj16/QueryCombinedOperatorExceptAll");
                    break;
                case QueryCombinedOperator.INTERSECT:
                    image = getResourceLocator().getImage("full/obj16/QueryCombinedOperatorIntersect");
                    break;
                case QueryCombinedOperator.INTERSECT_ALL:
                    image = getResourceLocator().getImage("full/obj16/QueryCombinedOperatorIntersectAll");
                    break;
                case QueryCombinedOperator.UNION:
                    image = getResourceLocator().getImage("full/obj16/QueryCombinedOperatorUnion");
                    break;
                case QueryCombinedOperator.UNION_ALL:
                    image = getResourceLocator().getImage("full/obj16/QueryCombinedOperatorUnionAll");
                    break;
                default:
                    break;
            }
        }
        
        return image;
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated NOT
     */
  public String getText(Object object) {
      QueryCombined queryCombined = (QueryCombined) object;
      QueryCombinedOperator oper = queryCombined.getCombinedOperator();
      String operName = oper.toString();
      if (operName == null || operName.length() == 0) {
          operName = getString("_UI_QueryCombinedOperator_UNION_literal");
      }
      operName = operName.replaceAll("_"," ");
      return operName;
//        String label = ((QueryCombined)object).getName();
//        //label = label.replaceAll("_"," ");
//        return label == null || label.length() == 0 ?
//            //getString("_UI_QueryCombined_type") :
//        		getString("_UI_QueryCombinedOperator_UNION_literal") :
//           	//getString("_UI_QueryCombined_type") + " " + label;
//            	label.replaceAll("_"," ");	
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

        switch (notification.getFeatureID(QueryCombined.class)) {
            case SQLQueryModelPackage.QUERY_COMBINED__COMBINED_OPERATOR:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case SQLQueryModelPackage.QUERY_COMBINED__LEFT_QUERY:
            case SQLQueryModelPackage.QUERY_COMBINED__RIGHT_QUERY:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
                return;
        }
        super.notifyChanged(notification);
    }

}
