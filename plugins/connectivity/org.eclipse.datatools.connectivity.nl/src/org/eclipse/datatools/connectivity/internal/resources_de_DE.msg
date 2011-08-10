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
assert.invalid.profile.duplicateFactory = Analysefehler\: Doppelte Factory-ID mit doppelter Priorität ({0})
assert.invalid.profile.nullFactory = Analysefehler\: Factory oder Factory-ID ({0}/{1}) ist gleich null.
drivermarker.error = Problem mit Treiber "{0}". (Fehler\: {1})
drivermarker.import.error = Fehlende JAR-Liste für Treiberdefinition bei Import von Profil "{0}" erstellt.
profile.duplicate =Der Verbindungsprofilname "{0}" ist doppelt vorhanden\!
DriverConnectionBase.error.unknown=Die Verbindung ist mit einem nicht angegebenen Fehler fehlgeschlagen.
DriverConnectionBase.error.driverDefinitionNotSpecified=Die Treiberdefinition war nicht angegeben.
DriverConnectionBase.error.driverDefinitionNotFound=Die Treiberdefinition wurde nicht gefunden.
error.loaddriversxml = Fehler beim Laden der Treiberdefinitionsdaten.
error.savedriversxml = Fehler beim Speichern der Treiberdefinitionsdaten.

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
ConnectivityPlugin.error.noDefaultWorkspace = Unable to determine the default workspace location.  Check your OSGi-less platform configuration of the plugin or datatools workspace path.

