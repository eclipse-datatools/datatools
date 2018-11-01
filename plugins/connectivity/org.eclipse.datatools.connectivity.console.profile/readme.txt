###############################################################################
# Copyright (c) 2008 Actuate Corporation.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License 2.0
# which accompanies this distribution, and is available at
# https://www.eclipse.org/legal/epl-2.0/
#
# Contributors:
#  Actuate Corporation - initial API and implementation
#
###############################################################################

This Data Tools Platform (DTP) plug-in provides a console editor application for users to 
view and update the contents of an exported file containing DTP connection profiles.

It is intended for use at a system console to make minor changes to an exported connection profile, 
such as the file path of JDBC driver jars, a connection URL, an ODA data source file path, etc.
When an exported file is copied to a server platform for deployment, an user can use 
this editor tool to quickly adjust the connection profile properties without having to
bring up the DTP Data Source Explorer UI workbench.  Any user updates are saved in 
a separate file for all the connection profiles.

To use the tool, make sure the plug-in jar 
(org.eclipse.datatools.connectivity.console.profile_<version>.jar) is installed in
your Eclipse environment, together with the other DTP plug-ins.
From within your Eclipse home directory, enter the command in the system console
using the syntax described below.  
(Note that it could take a good number of seconds for the Eclipse executable to start up.)

Command syntax:
    eclipse[c] -nosplash -application org.eclipse.datatools.connectivity.console.profile.StorageFileEditor
      [ -? |
        -in <connectionProfileFile> |
        -out <saveAsFile> |
        -profile <profileName> ]

Use "eclipsec" in command on Windows, or "eclipse" on other platforms.

Optional command line arguments:
    -? : displays this help message
    -in <connectionProfileFile> : specifies the name of the connection profile storage file to view/change
    -out <saveAsFile> : specifies the name of the output file to save your changes
    -profile <profileName> : specifies the name of a connection profile to view/change; if none is specified,
                             the tool will step through all the profiles found in the input file
    If an argument value is not specified, the editor will prompt for an input value.
