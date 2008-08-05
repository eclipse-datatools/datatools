 /*******************************************************************************
  * Copyright (c) 2005, 2008. Versant Corporation and others.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License v1.0
  * which accompanies this distribution, and is available at
  * http://www.eclipse.org/legal/epl-v10.html
  * 
  * Contributors:
  *     Versant Corporation - initial API and implementation
  *     IBM Corporation - fix for 237964
  *******************************************************************************/
package org.eclipse.datatools.enablement.mysql.ddl;

import org.eclipse.datatools.enablement.mysql.ddl.shared.MySqlDdlGeneratorHelper;

 /**
  * This class uses the MySqlDdlBuilder to generates the sql scripts.
  */
 public class MySqlDdlGenerator  extends MySqlDdlGeneratorHelper{
 	public MySqlDdlGenerator() {
 		super();
		this.setBuilder(new MySqlDdlBuilder());
	}
}
