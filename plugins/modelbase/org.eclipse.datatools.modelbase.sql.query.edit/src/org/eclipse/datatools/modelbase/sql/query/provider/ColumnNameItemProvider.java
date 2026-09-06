/**
 * <copyright>
 * </copyright>
 *
 * $Id: ColumnNameItemProvider.java,v 1.1 2007/09/25 23:18:03 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.provider;


import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.ColumnName;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

/**
 * This is the item provider adapter for a {@link org.eclipse.datatools.modelbase.sql.query.ColumnName} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ColumnNameItemProvider
  extends SQLQueryObjectItemProvider {
    /**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ColumnNameItemProvider(AdapterFactory adapterFactory) {
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
		String label = ((ColumnName)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ColumnName_type") :
			getString("_UI_ColumnName_type") + " " + label;
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
