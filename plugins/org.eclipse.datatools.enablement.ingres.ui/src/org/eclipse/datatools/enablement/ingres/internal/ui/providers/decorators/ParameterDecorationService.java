package org.eclipse.datatools.enablement.ingres.internal.ui.providers.decorators;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.impl.AbstractDecorationService;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.jface.viewers.IDecoration;


/**
 * TODO org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.impl.ColumnDecorationService
 *
 * @author enrico.schenk@ingres.com
 */
public class ParameterDecorationService extends AbstractDecorationService {

	public void decorate(Object element, IDecoration decoration) {
		if (element instanceof Parameter) {
			decorate((Parameter) element, decoration);
		}
	}
	
	private void decorate(Parameter parameter, IDecoration decoration) {
		String dataType = getDataType(parameter);
		if (dataType != null && dataType.trim().length() > 0) {
			dataType = " [" + dataType + "]";
		}
		decoration.addSuffix(dataType);
	}
	
	// copied from org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.helpers.ColumnHelper
	private DatabaseDefinitionRegistry dbRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
	private static final String BLANK = "";
	/**
	 * TODO copied from org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.helpers.ColumnHelper
	 *
	 * @param parameter
	 * @return
	 */
	public String getDataType(Parameter parameter) {
		Routine routine;
		Schema schema;
		Catalog catalog;
		Database database;
		DataType datatype = parameter.getDataType();
		
		if ((routine = parameter.getRoutine()) != null
				&& (schema = routine.getSchema()) != null
				&& (((catalog = schema.getCatalog()) != null && (database = catalog
						.getDatabase()) != null) || (database = schema
						.getDatabase()) != null)) {
			DatabaseDefinition definition = dbRegistry.getDefinition(database);
			if (datatype != null) {
				if (datatype instanceof PredefinedDataType) {
					return definition
							.getPredefinedDataTypeFormattedName((PredefinedDataType) datatype);
				} else {
					return datatype.getName();
				}
			}
		}
		return BLANK;
	}


}
