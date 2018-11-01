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

public class ConfigMessage
{
  protected String msgText = null;
  protected String platform = null;

  /**
   * Create a structure to hold the <message> element in the xml config file.
   *
   * @param  aMsgText  The msg.
   * @param  aPlatform The platform attribute.
   */
  public ConfigMessage(String aMsgText, String aPlatform) {
    msgText = aMsgText;
    platform = aPlatform;
  }

  /**
   * Get the msg text.
   *
   * @return String    The msg text.
   */
  public String getMsgText() {
    return msgText;
  }

  /**
   * Get the platform text.
   *
   * @return String    The platform attribute.
   */
  public String getPlatform() {
    return platform;
  }

  /**
   * Set the msg text.
   *
   * @param  String    The msg attribute.
   */
  public void setMsgText(String aMsgText) {
    msgText = aMsgText;
  }

  /**
   * Set the platform text.
   *
   * @param  String    The platform attribute.
   */
  public void setPlatform(String aPlatform) {
    platform = aPlatform;
  }
}

