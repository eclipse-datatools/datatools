## Copyright (c) 2005, 2011 Sybase, Inc. and others.
## All rights reserved. This program and the accompanying materials
## are made available under the terms of the Eclipse Public License v1.0
## which accompanies this distribution, and is available at
## http://www.eclipse.org/legal/epl-v10.html
##
## Contributors:
##    Sybase, Inc. - initial API and implementation
##    IBM Corporation - migrated to new wizard framework
##    IBM Corporation - fix for defect 222818
################################################################################ 
plugin.internal_error=Interner Fehler - org.eclipse.datatools.connectivity
ConnectionProfileConstants.unknownVersion=Unbekannt
dialog.title.error.loadconnectionfactory = Error loading connection factory in "{0}"!
ConnectJob.name=Verbindungen zu {0} werden erstellt.
ConnectJob.status.info=Für eine oder mehrere Erweiterungen konnten keine Verbindungen zu {0} erstellt werden.
ConnectJob.status.error=Die Verbindung zu {0} konnte nicht hergestellt werden.
DisconnectJob.name=Verbindungen zu {0} werden geschlossen.
DisconnectJob.status.info=Für eine oder mehrere Erweiterungen konnten die Verbindungen zu {0} nicht geschlossen werden.
DisconnectJob.status.error=Die Verbindung zu {0} konnte nicht getrennt werden.
CreateConnectionJob.name={0} mit {1} wird erstellt.
CreateConnectionJob.error=Beim Herstellen der Verbindung {0} zu {1} trat ein Fehler auf. (Fehler\: {2})
OpenConnectionEventJob.name=Das Ereignis zum Öffnen der Verbindung für {0} wird verarbeitet.
CloseManagedConnectionJob.name=Verbindung {0} zu {1} wird geschlossen.
CloseManagedConnectionJob.error=Fehler beim Schließen der Verbindung {0} zu {1}. (Fehler\: {2})
CloseConnectionEventJob.name=Das Ereignis zum Schließen der Verbindung für {0} wird verarbeitet.
assert.invalid.profile = Parsing error\: invalid attribute ({0})
assert.invalid.profile.duplicateFactory = Analysefehler\: Doppelte Factory-ID mit doppelter Priorität ({0})
assert.invalid.profile.nullFactory = Analysefehler\: Factory oder Factory-ID ({0}/{1}) ist gleich null.
dialog.title.error.loadwizard = Error loading new wizard in "{0}"!
operation.new.connectionprofile = Creating profile\: "{0}"
error.connect = Problem connecting to profile "{0}."  See log file for more details.
marker.error = Problem connecting to profile {0}.  (Error\: {1})
drivermarker.error = Problem mit Treiber "{0}". (Fehler\: {1})
drivermarker.import.error = Fehlende JAR-Liste für Treiberdefinition bei Import von Profil "{0}" erstellt.
profile.duplicate =Der Verbindungsprofilname "{0}" ist doppelt vorhanden\!
profile.notexist = Connection profile "{0}" does not exist!
duplicate.profile.name = Copy of {0}({1})
DriverConnectionBase.error.unknown=Die Verbindung ist mit einem nicht angegebenen Fehler fehlgeschlagen.
DriverConnectionBase.error.driverDefinitionNotSpecified=Die Treiberdefinition war nicht angegeben.
DriverConnectionBase.error.driverDefinitionNotFound=Die Treiberdefinition wurde nicht gefunden.
error.loadprofilesxml = Error loading connection profile data.
error.saveprofilesxml = Error saving connection profile data.
error.loaddriversxml = Fehler beim Laden der Treiberdefinitionsdaten.
error.savedriversxml = Fehler beim Speichern der Treiberdefinitionsdaten.

trace.error.propertiesPersistenceHook = Could not load propertiesPersistenceHook, using default (profile type={0}, extension={1})
trace.error.propertiesPersistenceHookSaveError = Error invoking properties persistent hook; persisting all properties (profile={0}, extension={1})
trace.error.propertiesPersistenceHookLoadError = Error invoking properties persistent hook; transient properties not set (profile={0}, extension={1})
trace.error.migration = Fehler beim Laden des Migrationselements für Verbindungsprofil-Anbieter (id={0}, migration class={1})
trace.error.drivermigration = Fehler beim Erstellen der Treiberinstanz des Profils (ID={0}, Treiber-ID={1})

migration.error.providerDoesNotExist = Fehler beim Laden des Migrationselements: Angegebene neue Anbieter-ID ist ungültig (newProviderID={0})
migration.error.invalidPropertyMigrationElement = Fehler beim Laden des Migrationselements: Eigenschaftsmigration muss neuen oder alten Eigenschaftsschlüssel oder beides enthalten
migration.error.unknownParentElement = Fehler beim Laden des Migrationselements: Unbekanntes übergeordnetes Element (parent element name={0})

DriverUtil.taskName = Klassen werden in {0} verarbeitet

Category.Name.Unknown=Nicht unterstützt

WorkOfflineJob.name = {0} wird in Offline-Modus geändert.
WorkOfflineJob.status.error = Offline-Verbindungen zu {1} konnten für eine oder mehrere Erweiterungen nicht erstellt werden.

SaveWorkOfflineDataJob.name = Speichern Sie die Offline-Daten für {0}.
SaveWorkOfflineDataJob.status = Speichern Sie den Status der Offline-Daten.

CreateOfflineConnectionJob.name = Offline-Verbindung {0} zu {1} wird erstellt.
CreateOfflineConnectionJob.error = Fehler beim Erstellen der Offline-Verbindung {0} zu {1}. (Fehler\: {2})

ManagedConnection_save_not_connected_error = Verbindung zum Server erforderlich, um Offline-Daten zu speichern.
ManagedConnection_offline_not_supported_error = {0} unterstützt Offline-Arbeiten nicht.

drivers.error.finding.property.no.template = Beim Versuch, {0} aus der Treibervorlage abzurufen, ist ein Problem aufgetreten.

JDBCConnection.technologyName = JDBC
JDBCConnection.invalidDriverDefinition = {0}\: {1}. Stattdessen werden die Treibereigenschaften verwendet, die für das Datenbankprofil definiert sind.

error.database.not.start = Es wurde kein Befehl zum Start der Datenbank ausgegeben. ErrorCode = {0}, SQL-Status = {1}.
PluginResourceLocator.invalidURL = Ungültige URL {0} (Fehler\: {1})
ConnectivityPlugin.error.noDefaultWorkspace = Der standardmäßige Speicherort des Arbeitsplatzes kann nicht ermittelt werden.  Prüfen Sie die Konfiguration der Plattform ohne OSGi für den Plug-In- oder den Datentool-Arbeitsplatzpfad.

