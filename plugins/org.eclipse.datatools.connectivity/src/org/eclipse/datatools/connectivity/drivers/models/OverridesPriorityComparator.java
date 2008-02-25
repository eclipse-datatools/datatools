package org.eclipse.datatools.connectivity.drivers.models;

import java.util.Comparator;

public class OverridesPriorityComparator implements Comparator {

	public int compare(Object arg0, Object arg1) {
		OverrideTemplateDescriptor otd0 = (OverrideTemplateDescriptor) arg0;
		OverrideTemplateDescriptor otd1 = (OverrideTemplateDescriptor) arg1;
		Integer priority0 = new Integer(otd0.getPriority());
		Integer priority1 = new Integer(otd1.getPriority());
		return priority0.compareTo(priority1);
	}

}
