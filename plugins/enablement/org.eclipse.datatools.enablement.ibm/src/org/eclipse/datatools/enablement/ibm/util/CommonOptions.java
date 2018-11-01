/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.util.Vector;

public class CommonOptions
{
  protected Vector vConfigMessage = null;
  protected Vector vConfigVariable = null;

  /**
   * Create a structure to hold the <common_options> element in the xml config file.
   *
   * @param  aConfigMessages  A vector of ConfigMessage objects.
   * @param  aConfigVariables A vector of ConfigVariable objects.
   */
  public CommonOptions(Vector aConfigMessages, Vector aConfigVariables) {
    vConfigMessage = aConfigMessages;
    vConfigVariable= aConfigVariables;
  }

  /**
   * Get the configMessages objects.
   *
   * @return Vector  A vector of ConfigMessage objects.
   */
  public Vector getConfigMessages() {
    return vConfigMessage;
  }

  /**
   * Get the configVariables objects.
   *
   * @return Vector  A vector of ConfigVariable objects.
   */
  public Vector getConfigVariables() {
    return vConfigVariable;
  }

  /**
   * Set the configVariables objects.
   *
   * @param  aConfigVariables A vector of ConfigVariable objects.
   */
  public void   setConfigVariables(Vector aConfigVariables) {
    vConfigVariable= aConfigVariables;
  }

  /**
   * Set the configMessages objects.
   *
   * @param  aConfigMessages  A vector of ConfigMessage objects.
   */
  public void   setConfigMessages(Vector aConfigMessages) {
    vConfigMessage = aConfigMessages;
  }
}

