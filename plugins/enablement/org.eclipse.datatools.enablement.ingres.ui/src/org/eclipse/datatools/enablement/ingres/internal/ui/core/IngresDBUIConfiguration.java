package org.eclipse.datatools.enablement.ingres.internal.ui.core;

import java.util.HashMap;

import org.eclipse.datatools.enablement.ingres.internal.ui.plan.IngresExplainSQLActionDelegate;
import org.eclipse.datatools.sqltools.core.services.SQLEditorUIService;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;

public class IngresDBUIConfiguration extends SQLDevToolsUIConfiguration 
{
	public SQLEditorUIService getSQLEditorUIService() 
	{
		return new SQLEditorUIService() {
			public HashMap getAdditionalActions() {
				// XXX Add an Ingres specific extension to the editors context menu
				// (until QEP generation is enabled by default)
				HashMap additions = super.getAdditionalActions();
				additions.put("", new IngresExplainSQLActionDelegate());
				return additions;
			}
		};
	}
}
