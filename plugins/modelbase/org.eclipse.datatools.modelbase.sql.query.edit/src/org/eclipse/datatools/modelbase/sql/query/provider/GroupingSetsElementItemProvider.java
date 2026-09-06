/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingSetsElementItemProvider.java,v 1.1 2007/09/25 23:18:03 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.provider;


import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

/**
 * This is the item provider adapter for a {@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GroupingSetsElementItemProvider
  extends SQLQueryObjectItemProvider {
    /**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public GroupingSetsElementItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
	}

    /**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public String getText(Object object) {
		String label = ((GroupingSetsElement)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_GroupingSetsElement_type") :
			getString("_UI_GroupingSetsElement_type") + " " + label;
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
