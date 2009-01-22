################################################################################
# Copyright (c) 2007 Sybase, Inc.
# 
# All rights reserved. This program and the accompanying materials are made
# available under the terms of the Eclipse Public License v1.0 which
# accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
# 
# Contributors: Sybase, Inc. - initial API and implementation
################################################################################

To make this sample work in Eclipse 3.2.2, you will need to do the following:

1) Change the Versions to Match for the Eclipse Platform dependencies in the
	Manifest editor from 3.3 to 3.2

2) Remove or comment out the sections of the plugin.xml headed with:
 	NOTE: This is supported in Eclipse 3.3 only
 	
It should then compile and work in 3.2.