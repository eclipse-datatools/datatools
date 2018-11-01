/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/

package org.eclipse.datatools.enablement.ase;

import java.sql.SQLException;

import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.enablement.ase.ddl.SybaseASEDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.IControlConnectionManager;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.dbitem.IDBItem;
import org.eclipse.datatools.sqltools.core.internal.dbitem.SQLObjectItem;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.db.generic.GenericDBConfiguration;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.internal.core.AbstractControlConnection;
import org.eclipse.datatools.sqltools.internal.core.ControlConnectionManager;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;

/**
 * 
 * @author linsong
 *
 */
public class ASEConfig extends GenericDBConfiguration {
	public static final String[] ASE_ALIASES = new String[] { "Sybase_ASE",
			"Adaptive Server Enterprise" };

	public boolean recognize(String product, String version) {
		DatabaseVendorDefinitionId targetid = new DatabaseVendorDefinitionId(
				product, version);
		for (int i = 0; i < ASE_ALIASES.length; i++) {
			DatabaseVendorDefinitionId id = new DatabaseVendorDefinitionId(
					ASE_ALIASES[i], getDatabaseVendorDefinitionId()
							.getVersion());
			if (id.equals(targetid)) {
				return true;
			}
		}
		return false;
	}

	public ConnectionService getConnectionService() {
		return new ConnectionService() {
			public IControlConnection createControlConnection(
					DatabaseIdentifier databaseIdentifier) throws SQLException {
				try {
					return new ASEControlConnection(
							(ControlConnectionManager) EditorCorePlugin
									.getControlConnectionManager(),
							databaseIdentifier);
				} catch (Exception e) {
					throw new SQLException(e.getMessage());
				}
			}
		};
	}

	public static class ASEControlConnection extends AbstractControlConnection
			implements IControlConnection {

		public ASEControlConnection(IControlConnectionManager manager,
				DatabaseIdentifier databaseIdentifier) {
			super(manager, databaseIdentifier);
		}

		protected IDBItem createDBItem(ProcIdentifier proc) {
			SQLObject obj = ModelUtil.findProceduralObject(proc);
			if (obj == null) {
				obj = ModelUtil.findProceduralObject(proc, true);
			}
			if (obj != null) {
				return new ASEItemWithCode(proc, obj, this);
			}
			return null;
		}
	}

	public static class ASEItemWithCode extends SQLObjectItem {

		public ASEItemWithCode(ProcIdentifier proc, SQLObject routine,
				IControlConnection controlConn) {
			super(proc, routine, controlConn);
		}

		public String getCode() throws SQLException {
			String code = "";
			SybaseDdlGenerator ddlg = new SybaseASEDdlGenerator();
			if (ddlg != null) {
				EngineeringOption[] opts = ddlg.createGenerationOptions();
				boolean qualifyNames = ddlg.generateFullyQualifiedNames(opts);
				boolean fullSyntax = ddlg.generateFullSyntax(opts);
				boolean quotedId = SQLDevToolsUtil.getQuotedIdentifier(_proc
						.getDatabaseIdentifier());

				SybaseDdlScript script = new SybaseDdlScript();
				ddlg.createStatement(_routine, quotedId, qualifyNames,
						fullSyntax, script, null, 1);

				String[] ddl = script.getStatements();
				if (ddl != null && ddl.length > 0) {
					// skip the "use database" and temp table creaion/drop
					// statment
					code = ddl[ddl.length / 2];
				}
			}
			return code;
		}
	}
}
