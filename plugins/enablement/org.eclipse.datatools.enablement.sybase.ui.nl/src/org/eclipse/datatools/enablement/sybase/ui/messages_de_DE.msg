# *******************************************************************************
# * Copyright (c) 2008 Sybase, Inc.
# * 
# * All rights reserved. This program and the accompanying materials are made
# * available under the terms of the Eclipse Public License 2.0 which
# * accompanies this distribution, and is available at
# * https://www.eclipse.org/legal/epl-2.0/
# * 
# * Contributors: Sybase - initial API and implementation
# ******************************************************************************/
# package: com.sybase.suade.servers.sample.jdbc
NewConnectionProfileWizard.ASA.title = Neues ASA-Verbindungsprofil
NewConnectionProfileWizard.ASIQ.title = Neues ASIQ-Verbindungsprofil
NewConnectionProfileWizard.error.title = Fehler bei der Erstellung eines neuen JDBC-Verbindungsprofils
NewConnectionProfileWizard.error.msg = Ein Ausnahmefehler ist bei der Erstellung eines neuen JDBC-Profils aufgetreten.
NewConnectionProfileWizard.confirmation.title=Bestätigung
NewConnectionProfileWizard.confirmation.msg=Verbindungsprofil \"{0}\" für Datenbank \"{1}\" existiert schon.\nÜberschreiben?
								
								
JDBCASAPropertyWizardPage.title = Treiber- und Verbindungsdetails angeben
JDBCPropertyWizardPage.PingButton=
JDBCASAPropertyWizardPage.msg = Wählen Sie einen Treiber aus der Dropdown-Liste, und geben Sie Anmeldedetails für die Verbindung an.
JDBCASAPropertyWizardPage.driverCombo.label = Wählen Sie einen Treiber aus der Dropdown-Liste aus:
JDBCASAPropertyWizardPage.hostName.label = Host:
JDBCASAPropertyWizardPage.databaseName.label = Datenbankname:
JDBCASAPropertyWizardPage.portNumber.label = Anschluss:
JDBCASAPropertyWizardPage.userName.label = Benutzername:
JDBCASAPropertyWizardPage.password.label = Kennwort:
JDBCASAPropertyWizardPage.filterGroup.label = Filtereigenschaften
JDBCASAPropertyWizardPage.catalog.label = Katalog:
JDBCASAPropertyWizardPage.schema.label = Schema:
JDBCASAPropertyWizardPage.PingButton=Testverbindung
JDBCASAProfilePropertyPage.PingButton=Testverbindung
JDBCASAPropertyWizardPage.procedureName.label = Prozedurname:
JDBCASAPropertyWizardPage.optionalProps.label = Verbindungseigenschaften
JDBCASAPropertyWizardPage.summary.driverName.label = Treibername
JDBCASAPropertyWizardPage.summary.connProps.label = Verbindungseigenschaften
JDBCASAPropertyWizardPage.summary.host.label = Host:
JDBCASAPropertyWizardPage.summary.databaseName.label = Datenbankname
JDBCASAPropertyWizardPage.summary.port.label = Port
JDBCASAPropertyWizardPage.summary.userName.label = Benutzername
JDBCASAPropertyWizardPage.summary.password.label = Kennwort
JDBCASAPropertyWizardPage.summary.driverClass.label = Treiberklasse
JDBCASAPropertyWizardPage.summary.catalog.label = Katalog
JDBCASAPropertyWizardPage.summary.schema.label = Schema
JDBCASAPropertyWizardPage.summary.procedureName.label = Prozedurname
JDBCASAPropertyWizardPage.summary.url.label = URL

ASAWSMFConnection.error.DriverNotExist = Der Treiber existiert nicht.
ASAWSMFConnection.error.EmptyJarList = Die JAR-Liste in der Treiberdefinition ist leer.

JDBCASAProfileTabs.errors.requiredHost = Host erforderlich
JDBCASAProfileTabs.errors.requiredPort = Port erforderlich
JDBCASAProfileTabs.errors.requiredPortValidNumber = Portnummer muss gültig sein
JDBCASAProfileTabs.otherPropertiesTab.commonOptions=APPLICATIONNAME=SYBASE WORKSPACE,CHARSET=UTF8,CONNECTION_FAILOVER=TRUE,SQLINITSTRING=SET QUOTED_IDENTIFIER ON,JCONNECT_VERSION=6,JCONNECT_VERSION=0

MultiASAWizardPage.title=Überprüfung auf Multiple Database ASA-Server (Optional)
MultiASAWizardPage.description=Überprüfen Sie, ob der ASA-Server mehr als eine Datenbank exponiert. Falls ja, wählen Sie welche Datenbanken als Profile exponiert werden sollen. Klicken Sie 'Überprüfung auf mehrere Datenbanken', um zu beginnen.
MultiASADialog.dialog.title=Erstellen mehrerer ASA-Profile
MultiASADialog.dialog.message=Überprüfen Sie, ob der ASA-Server mehr als eine Datenbank exponiert. Falls ja, wählen Sie welche Datenbanken als Profile exponiert werden sollen. Klicken Sie 'Überprüfung auf mehrere Datenbanken', um zu beginnen.
MultiASAWizardPage.OnlyOneDBMessage=Da nur eine Datenbank exponiert ist, ist keine zusätzliche Arbeit erforderlich.
MultiASADialog.dialog.message.title=Überprüfung auf Multiple Database ASA-Server (Optional)
MultiASAWizardPage.checkButton.label=Überprüfung auf mehrere Datenbanken
MultiASAWizardPage.Label.ChooseDbs=Wählen Sie, welche Datenbanken als Profile exponiert werden sollen:
MultiASAWizardPage.Error.title.CannotConnect=Verbindung nicht möglich
MultiASAWizardPage.Error.msg.CannotConnect=Es gab einen Fehler bei der Verbindung zu dem Profil. Gehen Sie zurück zu der Eigenschaftsseite, und überprüfen Sie die Profilinformationen.
MultiASAWizardPage.selectAllButton.label=Alles markieren
MultiASAWizardPage.deselectAllButton.label=Auswahl aufheben
MultiASAWizardPage.Summary.Label=Datenbank(en) auswählen

ProfileASAMultiDBAction.error.failedprofileupdate = Fehler bei Update des Profils ''{0}''.
ProfileASAMultiDBAction.error.title.profilealreadyexists=Profil existiert schon
ProfileASAMultiDBAction.msg.profilealreadyexists = Ein Profil besteht schon unter dem Namen ''{0}''. Soll es ersetzt werden? 


# package: com.sybase.suade.servers.sample.jdbc
NewConnectionProfileWizard.title = Neues JDBC-Verbindungsprofil
NewConnectionProfileWizard.error.title = Fehler bei der Erstellung eines neuen JDBC-Verbindungsprofils
NewConnectionProfileWizard.error.msg = Ein Ausnahmefehler ist bei der Erstellung eines neuen JDBC-Profils aufgetreten.

JDBCPropertyWizardPage.title = Treiber- und Verbindungsdetails angeben
JDBCPropertyWizardPage.msg = Wählen Sie einen Treiber aus der Dropdown-Liste, und geben Sie Anmeldedetails für die Verbindung an.
JDBCPropertyWizardPage.driverCombo.label = Wählen Sie einen Treiber aus der Dropdown-Liste aus:
JDBCPropertyWizardPage.url.label = URL:
JDBCPropertyWizardPage.PingButton=Testverbindung
JDBCPropertyWizardPage.userName.label = Benutzername:
JDBCPropertyWizardPage.password.label = Kennwort:
JDBCPropertyWizardPage.filterGroup.label = Optionale Filtereigenschaften
JDBCPropertyWizardPage.catalog.label = Katalog:
JDBCPropertyWizardPage.schema.label = Schema:
JDBCProfilePropertyPage.PingButton=Testverbindung
JDBCPropertyWizardPage.procedureName.label = Prozedurname:
JDBCPropertyWizardPage.optionalProps.label = Optionale Eigenschaften:
JDBCPropertyWizardPage.summary.driverName.label = Treibername
JDBCPropertyWizardPage.summary.connProps.label = Verbindungseigenschaften
JDBCPropertyWizardPage.summary.userName.label = Benutzername
JDBCPropertyWizardPage.summary.password.label = Kennwort
JDBCPropertyWizardPage.summary.driverClass.label = Treiberklasse
JDBCPropertyWizardPage.summary.catalog.label = Katalog
JDBCPropertyWizardPage.summary.schema.label = Schema
JDBCPropertyWizardPage.summary.procedureName.label = Prozedurname
JDBCPropertyWizardPage.summary.url.label = URL

JDBCConnectionUtils.Milliseconds=\ milli(s)
JDBCConnectionUtils.StatusTitle=JDBCConnectionUtils
JDBCConnectionUtils.TimeoutInXMilliSecondsMsg=Login Zeitüberschreitung in 
JDBCConnectionUtils.AttemptToConnectMessage=Verbindungsversuch...
JDBCConnectionUtils.DBConnectAttemptJob=Datenbank Verbindungsversuch
JDBCConnectionUtils.ClassNotFoundStatus=Klasse konnte nicht geladen werden: 
JDBCConnectionUtils.ConnectionTimeoutMessage=Zeitüberschreitung der Verbindung.

JDBCConnection.technologyName = JDBC
JDBCConnection.error.DriverNotExist = Der Treiber existiert nicht.
JDBCConnection.error.EmptyJarList = Die JAR-Liste in der Treiberdefinition ist leer.

JDBCProfileTabs.tabs.label.connection = &Verbindung
JDBCProfileTabs.tabs.label.filters = &Filter
JDBCProfileTabs.tabs.label.other = Sonstige &Eigenschaften
JDBCProfileTabs.errors.requiredURL = JDBC URL erforderlich
JDBCProfileTabs.errors.requiredUID = Benutzername erforderlich

# package: com.sybase.suade.servers.sample.jdbc
NewConnectionProfileWizard.title = Neues JDBC-Verbindungsprofil
NewConnectionProfileWizard.error.title = Fehler bei der Erstellung eines neuen JDBC-Verbindungsprofils
NewConnectionProfileWizard.error.msg = Ein Ausnahmefehler ist bei der Erstellung eines neuen JDBC-Profils aufgetreten.

JDBCPropertyWizardPage.title = Treiber- und Verbindungsdetails angeben
JDBCPropertyWizardPage.msg = Wählen Sie einen Treiber aus der Dropdown-Liste, und geben Sie Anmeldedetails für die Verbindung an.
JDBCPropertyWizardPage.driverCombo.label = Wählen Sie einen Treiber aus der Dropdown-Liste aus:
JDBCPropertyWizardPage.url.label = URL:
JDBCPropertyWizardPage.PingButton=Testverbindung
JDBCPropertyWizardPage.userName.label = Benutzername:
JDBCPropertyWizardPage.password.label = Kennwort:
JDBCPropertyWizardPage.filterGroup.label = Optionale Filtereigenschaften
JDBCPropertyWizardPage.catalog.label = Katalog:
JDBCPropertyWizardPage.schema.label = Schema:
JDBCProfilePropertyPage.PingButton=Testverbindung
JDBCPropertyWizardPage.procedureName.label = Prozedurname:
JDBCPropertyWizardPage.optionalProps.label = Optionale Eigenschaften:
JDBCPropertyWizardPage.summary.driverName.label = Treibername
JDBCPropertyWizardPage.summary.connProps.label = Verbindungseigenschaften
JDBCPropertyWizardPage.summary.userName.label = Benutzername
JDBCPropertyWizardPage.summary.password.label = Kennwort
JDBCPropertyWizardPage.summary.driverClass.label = Treiberklasse
JDBCPropertyWizardPage.summary.catalog.label = Katalog
JDBCPropertyWizardPage.summary.schema.label = Schema
JDBCPropertyWizardPage.summary.procedureName.label = Prozedurname
JDBCPropertyWizardPage.summary.url.label = URL

JDBCConnectionUtils.Milliseconds=\ milli(s)
JDBCConnectionUtils.StatusTitle=JDBCConnectionUtils
JDBCConnectionUtils.TimeoutInXMilliSecondsMsg=Login Zeitüberschreitung in 
JDBCConnectionUtils.AttemptToConnectMessage=Verbindungsversuch...
JDBCConnectionUtils.DBConnectAttemptJob=Datenbank Verbindungsversuch
JDBCConnectionUtils.ClassNotFoundStatus=Klasse konnte nicht geladen werden: 
JDBCConnectionUtils.ConnectionTimeoutMessage=Zeitüberschreitung der Verbindung.

JDBCConnection.technologyName = JDBC
JDBCConnection.error.DriverNotExist = Der Treiber existiert nicht.
JDBCConnection.error.EmptyJarList = Die JAR-Liste in der Treiberdefinition ist leer.

JDBCProfileTabs.tabs.label.connection = &Verbindung
JDBCProfileTabs.tabs.label.filters = &Filter
JDBCProfileTabs.tabs.label.other = Sonstige &Eigenschaften
JDBCProfileTabs.errors.requiredURL = JDBC URL erforderlich
JDBCProfileTabs.errors.requiredUID = Benutzername erforderlich
events.folder.name=Ereignisse
webservices.folder.name=Web-Services
datatypes.folder.name=Datentypen

SybaseDatabaseProfileSettingPage.showOwner=Show database object &owner
SybaseDatabaseProfileSettingPage.showSchema=&Schema anzeigen
