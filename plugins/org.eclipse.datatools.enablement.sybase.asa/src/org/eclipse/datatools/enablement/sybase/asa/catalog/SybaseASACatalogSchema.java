package org.eclipse.datatools.enablement.sybase.asa.catalog;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseSchema;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SchemaASABaseLoader;
import org.eclipse.datatools.enablement.sybase.asa.loaders.SchemaASALoader;

public class SybaseASACatalogSchema extends SybaseASACatalogBaseSchema implements IAdaptable {

	private static final long serialVersionUID = 8718743376255553112L;
	
	protected SchemaASABaseLoader createSchemaLoader()
	{
		return new SchemaASALoader(this);
	}
	
	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}	
	
}
