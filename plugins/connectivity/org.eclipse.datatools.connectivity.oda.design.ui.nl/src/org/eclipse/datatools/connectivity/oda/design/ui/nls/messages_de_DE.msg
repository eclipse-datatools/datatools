#
#************************************************************************
# Copyright (c) 2006, 2013 Actuate Corporation.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License 2.0
# which accompanies this distribution, and is available at
# https://www.eclipse.org/legal/epl-2.0/
#
# Contributors:
#  Actuate Corporation - initial API and implementation
#  
#************************************************************************
#
adaptableDataSourceProfile_unableAccessProfile=Zugriff auf das verknüpfte Profil in ({0}) nicht möglich; stattdessen werden die lokalen Eigenschaften des Datenquellen-Designs verwendet.\n Verursacht von Ausnahme: {1}
common_notInDesignSession=Gegenwärtig besteht keine Entwurfssitzung.
common_nullArgument=Das Argument darf nicht Null sein.
common_missingDataSourceDesign=Im Argument 'OdaDesignSession' fehlt der Datenquellenentwurf.
common_createClassFailed=Die Klasse ({0}) konnte nicht gefunden oder erstellt werden.
extension_mustInheritFromODAPage=Die Implementierung der Assistentenseite ({0}) ist ungültig. Eine angepasste ODA-Assistentenseite muss {1} erweitern.
extension_mustInheritFromODAWizard=Die Implementierung des Assistenten ({0}) ist ungültig. Ein angepassterODA-Assistent muss {1} erweitern.
extension_missingManifestElement=Die Implementierung des Erweiterungspunkts für die ODA-Entwurfsbenutzerschnittstelle ({0}) fehlt.
extension_missingPropertyPage=In der Erweiterung für die ODA-Entwurfsbenutzerschnittstelle fehlt die Implementierung der Eigenschaftenseite.
wizard_dataSource_defaultTitle=Neue Datenquelle
wizard_missingDataSourceId=Im Attribut für das Verbindungsprofil fehlt die ID der ODA-Datenquelle.
wizard_invalidManifest=Der Inhalt des Plug-in-Manifestes in der Erweiterung 'oda.design.ui.dataSource' ist ungültig.
page_defaultDataSourceTitle=Eigenschaften der Datenquelle anpassen
designSession_alreadyHasLinkedProfile=Die Eigenschaften der Datenquelle ({0}) wurden bereits in ein Verbindungsprofil externalisiert.  Sie können nicht erneut exportiert werden.
designSession_flushFailed=Die aktuelle Datensatz-Designsitzung kann nicht beendet werden.
designSession_invalidArgument=Im Entwurfssitzungsmodus ist ein ungültiges Argument angegeben.
designSession_invalidEditApiCall=Ungültiger Designsitzungs-API-Aufruf zum Bearbeiten eines Designs.
designSession_invalidNewDesignApiCall=Ungültiger Designsitzungs-API-Aufruf zum Bearbeiten eines neuen Designs.
designSession_invalidProfileName=Das verknüpfte Profil ({0}) wurde im angegebenen Verbindungsprofilspeicher nicht gefunden.
designSession_invalidProfileNameOrFilePath=Das im Datenquellen-Design angegebene verknüpfte Profil ({0}) oder die Verbindungsprofilspeicherdatei ({1}) wurde nicht gefunden.
designSession_missingDataSetUIElement=Für OdaDataSourceId ({0}) und OdaDataSetId ({1}) ist kein Element ''dataSetUI'' definiert.
manifest_missingAttributeValue=Im erforderlichen Attribut ({0}) fehlt ein Wert.
manifest_missingDataSetElementId=Das Argument 'dataSetElementID' erfordert einen Wert.
manifest_invalidDataSetElementId=Die ID für das Element ''dataSet'' ({0}) ist ungültig.
manifest_dataSetUi_missingPageId=Für 'pageId' ist ein Wert erforderlich.
manifest_dataSetUi_invalidPageId=Der Wert für ''pageId'' ({0}) ist ungültig.
manifest_dataSetUi_missingElement=In der Erweiterung ist ein Element {0} erforderlich.
ui_defaultDataSourceTitle=Eigenschaften der Datenquellenverbindung
ui_replaceFilePrompt={0} ist bereits vorhanden. Möchten Sie ersetzen?
ui_requiredFieldsMissingValue=Bei einen oder mehreren erforderlichen Feldern (gekennzeichnet mit '*') fehlt ein Wert.
ui_errorLabel=Fehler
ui_errorCreatingCustomCtrls=Bei der Erstellung und Initialisierung von angepassten Steuerelementen trat ein Fehler auf.
ui_saveAsTitle=Speichern unter
profilePage_pageTitle=Verbindungsprofil wählen
profilePage_pageLabel=Verbindungsprofil
profilePage_selectProfileDefaultMessage=Wählen Sie das Verbindungsprofil, aus dem eine Datenquelle erstellt werden soll.
profilePage_label_profileStore=&Speicher für Verbindungsprofile:
profilePage_button_browse=Du&rchsuchen...
profilePage_button_relativepath=&Relativer Pfad
profilePage_button_absolutepath=&Absoluter Pfad
profilePage_button_browse_filter=Anzuzeigende Datei&erweiterung(en):
profilePage_button_browse_filter_tooltiptext=Geben Sie eine oder mehrere kommagetrennte Dateierweiterungen an, um die unten angezeigten Dateien zu filtern.\nBeispiel: .txt, .profiles
profilePage_button_new=N&eu...
profilePage_button_browse_tooltip=Suchen Sie nach einer Verbindungsprofilspeicherdatei.\nRelativer Pfad: der relative Pfad abhängig von den Host-Ressourcen.\nAbsoluter Pfad: der absolute Pfad des Dateisystems.
profilePage_resourcebaseuri_failure=Basis-URI von Anwendungsressource konnte nicht abgerufen werden.
profilePage_browse_noselection_error=Geben Sie einen Namen für die Verbindungsprofilspeicherdatei an. 
profilePage_odaTreeName=ODA-Datenquellen
profilePage_checkboxLabel_useDefaultName=&Standard-Datenquellnamen verwenden
profilePage_label_dataSourceName=&Datenquellname:
profilePage_error_emptyName=Der Datenquellname darf nicht leer sein.
profilePage_checkboxTooltip_maintainLink=Verwenden Sie stets die externalisierten Eigenschaftswerte des Speichers für Verbindungsprofile in der Datenquelle.
profilePage_checkboxLabel_maintainLink=&Externalisierte Eigenschaften im Speicher für Verbindungsprofile verwenden
profilePage_error_invalidName=Datenquellname ist ungültig.
profilePage_error_invalidProfileStorePath=Ungültiger Pfad für Verbindungsprofilspeicher
profilePage_deprecatedProfileDisplayName={0} (durch {1} ersetzt)
profileStoreCreationDialog_button_new=N&eu...
profileStoreCreationDialog_title=Speicher für Verbindungsprofile erstellen
profileStoreCreationDialog_grouptext=Verbindungs&profile wählen, die der Speicher enthalten soll:
profileStoreCreationDialog_newbutton_tooltiptext=Neues Verbindungsprofil erstellen
profileStoreCreationDialog_fileext_error=Eine Verbindungsprofilspeicherdatei muss über eine Dateierweiterung verfügen.\nKlicken Sie auf 'OK', um die Standard-Dateierweiterung '.{0}' zum Dateinamen hinzuzufügen.
dbProfilePage_defaultPageMessage=JDBC-Treiber- und Verbindungsdetails prüfen
dbProfilePage_editPageMessage=JDBC-Treiber- und Verbindungsdetails bearbeiten
dbProfilePage_invalidDataSource=Ungültige Datenquelle.  Aktualisieren Sie die Verbindungsprofilreferenz, oder erstellen Sie eine neue Datenquelle.
dbProfilePage_notInDesignSession=Eigenschaftenseite kann außerhalb einer ODA-Designsitzung nicht geöffnet werden.
dbProfilePage_noCustomPage=Die für die Datenbanktreiber spezifische Eigenschaftenseite kann nicht gefunden und geöffnet werden.
preferencesPage_openDataAccessDescription=Legen Sie die Einstellungen für einzelne Open Data Access-Datenquellen\nüber die Seiten unter diesem Knoten fest.
