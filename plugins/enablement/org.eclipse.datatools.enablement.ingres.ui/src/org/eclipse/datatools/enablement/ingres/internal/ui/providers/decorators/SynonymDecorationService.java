/*******************************************************************************
 * Copyright (c) 2006, 2007, 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.providers.decorators;

import java.text.MessageFormat;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.impl.AbstractDecorationService;
import org.eclipse.datatools.enablement.ingres.internal.ui.util.Messages;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym;
import org.eclipse.jface.viewers.IDecoration;

/**
 * TODO
 * 
 * 
 * @author enrico.schenk@ingres.com
 */
public class SynonymDecorationService extends AbstractDecorationService {

	private static final String SYNONYM_NAME_DECORATION = Messages
			.getString("Synonym.NameDecoration"); //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object,
	 *      org.eclipse.jface.viewers.IDecoration)
	 */
	public void decorate(Object element, IDecoration decoration) {
		if (element instanceof IngresSynonym) {
			decorate((IngresSynonym) element, decoration);
		}
	}

	public void decorate(IngresSynonym synonym, IDecoration decoration) {
		decoration.addSuffix(MessageFormat.format(SYNONYM_NAME_DECORATION,
				new String[] { synonym.getTableName() }));
	}

}
