/*******************************************************************************
 * Copyright (c) 2011, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import org.eclipse.datatools.enablement.ibm.catalog.util.ICatalogQuery;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface IQueryMap 
{
	public enum QueryType {
		TABLE__CONSTRAINT_QUERY,
		TABLE__ROW_COUNT,
		TABLE__MAIN_QUERY,
		VIEW__MAIN_QUERY,
		MQT__MAIN_QUERY,
		ALIAS__MAIN_QUERY,
		TRIGGER__MAIN_QUERY,
		STOREDPROC__MAIN_QUERY,
		UDF__MAIN_QUERY,
		UDT__MAIN_QUERY,
		PACKAGE__MAIN_QUERY,
		SEQUENCE__MAIN_QUERY,
		SYNONYM__MAIN_QUERY,
		SCHEMA__MAIN_QUERY,
		INDEX__MAIN_QUERY,
		CONSTRAINT__MAIN_QUERY,
		TABLESPACE__MAIN_QUERY,
		XMLSCHEMA__MAIN_QUERY;
	};
	
	/**
	 * 
	 * @param feature, used to lookup queries in map
	 * @return array of ICatalogQuery objects for given feature
	 */
	public ICatalogQuery[] get(EStructuralFeature feature);
	
	/**
	 * 
	 * @param queryType, String used to map to an adhoc query not
	 * defined by a feature
	 * @return array of ICataloqQuery objects for given String
	 */
	public ICatalogQuery[] get(QueryType queryType);
}
