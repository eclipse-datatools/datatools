/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.sqm.core.definition;

import org.eclipse.datatools.connectivity.sqm.internal.core.definition.ConfigElementDatabaseRecognizer;

/**
 *  A default IDatabaseRecognizer implementation class that can be specified 
 *  as a value in the recognizer.class attribute of an extension that implements the
 *  org.eclipse.datatools.connectivity.sqm.core.databaseRecognition extension point.
 */
public class DefaultDatabaseRecognizer extends ConfigElementDatabaseRecognizer
        implements IDatabaseRecognizer
{

}
