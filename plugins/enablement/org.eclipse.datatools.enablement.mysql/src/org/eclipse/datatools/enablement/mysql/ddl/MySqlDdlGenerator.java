 /*******************************************************************************
  * Copyright (c) 2005, 2008. Versant Corporation and others.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License 2.0
  * which accompanies this distribution, and is available at
  * https://www.eclipse.org/legal/epl-2.0/
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
