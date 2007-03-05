package org.eclipse.datatools.enablement.sybase.asa.catalog;

import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseSchema;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SchemaASABaseLoader;
import org.eclipse.datatools.enablement.sybase.asa.loaders.SchemaASALoader;

public class SybaseASACatalogSchema extends SybaseASACatalogBaseSchema {

	private static final long serialVersionUID = 8718743376255553112L;
	
	protected SchemaASABaseLoader createSchemaLoader()
	{
		return new SchemaASALoader(this);
	}
	
}
