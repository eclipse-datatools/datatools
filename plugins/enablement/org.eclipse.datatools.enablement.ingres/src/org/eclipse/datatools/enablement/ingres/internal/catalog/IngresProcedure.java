/*******************************************************************************
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.catalog;

import java.lang.ref.SoftReference;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCProcedure;
import org.eclipse.datatools.enablement.ingres.internal.loaders.IngresProcedureSourceLoader;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesFactory;
import org.eclipse.datatools.modelbase.sql.routines.Source;

/**
 * Ingres related extension of JDBCProcedure to provide support for loading the
 * procedure source.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresProcedure extends JDBCProcedure {
	
	private final Object SOURCE_LOCK = new Object();

	private Boolean sourceLoaded = Boolean.FALSE;

	private SoftReference sourceLoaderRef;

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCProcedure#refresh()
	 */
	public void refresh() {
		synchronized (SOURCE_LOCK) {
			if (sourceLoaded.booleanValue()) {
				sourceLoaded = Boolean.FALSE;
			}
		}
		super.refresh();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl#getSource()
	 */
	public Source getSource() {
		synchronized (SOURCE_LOCK) {
			if (!sourceLoaded.booleanValue()) {
				loadSource();
			}
		}
		return super.getSource();
	}

	protected final IngresProcedureSourceLoader getSourceLoader() {
		if (sourceLoaderRef == null || sourceLoaderRef.get() == null) {
			sourceLoaderRef = new SoftReference(createSourceLoader());
		}
		return (IngresProcedureSourceLoader) sourceLoaderRef.get();
	}

	protected IngresProcedureSourceLoader createSourceLoader() {
		return new IngresProcedureSourceLoader(this);
	}

	private void loadSource() {
		boolean deliver = eDeliver();
		try {
			eSetDeliver(false);

			Source loadedSource = SQLRoutinesFactory.eINSTANCE.createSource();
			getSourceLoader().loadSource(getSchema().getName(), getName(),
					loadedSource);
			setSource(loadedSource);

			sourceLoaded = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			eSetDeliver(deliver);
		}
	}

}
