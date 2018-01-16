/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryUpdateStatementItemProvider.java,v 1.1 2007/09/25 23:18:03 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateSource;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class QueryUpdateStatementItemProvider
  extends QueryChangeStatementItemProvider
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
  public QueryUpdateStatementItemProvider(AdapterFactory adapterFactory) {
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

        }
        return itemPropertyDescriptors;
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated NOT
     */
  public Collection getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(SQLQueryModelPackage.eINSTANCE.getUpdateSourceQuery_QueryExpr());
            //childrenFeatures.add(SQLQueryModelPackage.eINSTANCE.getQueryUpdateStatement_AssignmentClause());
            //childrenFeatures.add(SQLQueryModelPackage.eINSTANCE.getQueryUpdateStatement_WhereCurrentOfClause());
            //childrenFeatures.add(SQLQueryModelPackage.eINSTANCE.getQueryUpdateStatement_WhereClause());
            //childrenFeatures.add(SQLQueryModelPackage.eINSTANCE.getQueryUpdateStatement_TargetTable());
        }
        return childrenFeatures;
    }
  
  /**
   * This implements {@link ITreeItemContentProvider#getChildren ITreeItemContentProvider.getChildren}. If children are
   * already cached in a {@link ChildrenStore}, they are returned. Otherwise, children are collected from the features
   * returned by {@link #getChildrenFeatures getChildrenFeatures}.  The collected children may or may not be cached,
   * depending on the result of {@link #createChildrenStore createChildrenStore}; by default, no store is returned if
   * {@link #getChildrenFeatures getChildrenFeatures} returns only containment references. All children are optionally
   * {@link #wrap wrapped} before being cached and returned. Subclasses may override {@link #createWrapper
   * createWrapper} to specify when and with what to wrap children.
     * @generated NOT
   */
  public Collection getChildren(Object object)
  {
  	List children = new ArrayList() ;
  	QueryUpdateStatement updateStmt = (QueryUpdateStatement)object ;
  	Iterator iter = updateStmt.getAssignmentClause().iterator();
  	while (iter.hasNext()) {
  		UpdateAssignmentExpression assignExpr = (UpdateAssignmentExpression)iter.next();
  		UpdateSource updateSource = assignExpr.getUpdateSource(); 
  		if (updateSource instanceof UpdateSourceQuery) {
  			children.add(((UpdateSourceQuery)updateSource).getQueryExpr()) ;
  		}
  	}
  
  	return children ;
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
     * This returns QueryUpdateStatement.gif.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/QueryUpdateStatement"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getText(Object object) {
        String label = ((QueryUpdateStatement)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_QueryUpdateStatement_type") :
            getString("_UI_QueryUpdateStatement_type") + " " + label;
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

        switch (notification.getFeatureID(QueryUpdateStatement.class)) {
            case SQLQueryModelPackage.QUERY_UPDATE_STATEMENT__ASSIGNMENT_CLAUSE:
            case SQLQueryModelPackage.QUERY_UPDATE_STATEMENT__WHERE_CURRENT_OF_CLAUSE:
            case SQLQueryModelPackage.QUERY_UPDATE_STATEMENT__WHERE_CLAUSE:
            case SQLQueryModelPackage.QUERY_UPDATE_STATEMENT__TARGET_TABLE:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
                return;
        }
        super.notifyChanged(notification);
    }

}
