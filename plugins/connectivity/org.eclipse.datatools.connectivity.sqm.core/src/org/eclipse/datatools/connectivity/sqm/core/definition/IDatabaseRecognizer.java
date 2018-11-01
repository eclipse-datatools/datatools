/*
 *************************************************************************
 * Copyright (c) 2001, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  IBM Corporation - initial API and implementation
 *  Actuate Corporation - moved interface from internal package
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.sqm.core.definition;

import java.sql.Connection;

/**
 *  A database recognizer for determining the {@link DatabaseDefinition} instance 
 *  that is appropriate for a given {@link Connection} object. 
 *  <br>Its implementation class is required in the recognizer.class attribute
 *  of an extension that implements the
 *  <code>org.eclipse.datatools.connectivity.sqm.core.databaseRecognition</code> extension point.
 */
public interface IDatabaseRecognizer
{
    public DatabaseDefinition recognize( Connection connection );

}
