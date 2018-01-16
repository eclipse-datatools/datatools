package org.eclipse.datatools.connectivity.internal.ui;

import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeFilter;
import org.eclipse.jface.viewers.Viewer;

public class DriverTableFilter extends DriverTreeFilter {

	public DriverTableFilter() {
		super();
	}
	
	public DriverTableFilter ( boolean flag ) {
		super(flag);
	}

	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof IPropertySet) {
			IPropertySet propSet = (IPropertySet) element;
			DriverInstance di = new DriverInstance(propSet);
			TemplateDescriptor td = di.getTemplate();
			if (td != null) {
				if (this.okTemplateIds.contains(td.getId()))
					return true;
				if (this.okCategoryIds.contains(td.getParentCategory()))
					return true;
				if (td.getParent() != null && td.getParent().getParent() != null) {
					if (this.okCategoryIds.contains(td.getParent().getParentCategory()))
						return true;
				}
			}
			return false;
		}
		return super.select(viewer, parentElement, element);
	}
}
