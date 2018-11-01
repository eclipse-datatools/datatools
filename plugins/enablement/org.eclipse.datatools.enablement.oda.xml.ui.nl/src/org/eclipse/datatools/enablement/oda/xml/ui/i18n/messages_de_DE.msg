###############################################################################
# Copyright (c) 2004, 2008 Actuate Corporation.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License 2.0
# which accompanies this distribution, and is available at
# https://www.eclipse.org/legal/epl-2.0/
# 
# Contributors:
#  Actuate Corporation - initial API and implementation
#  
###############################################################################
# wizard dialog messages
wizard.title.selectFolder=XML-Datenquelle
wizard.defaultMessage.selectFolder=Geben Sie die URLs und die Schemainformation für die XML-Datei an.
wizard.title.newDataSet=Neuer Datensatz
wizard.title.selectXmlFile=XML-Daten für Vorschau auswählen
wizard.defaultMessage.selectFile=Wählen Sie eine XML-Beispieldatenquelle aus, um die Vorschau in dieser Sitzung zu aktivieren.
wizard.title.xPathChoose=Tabellenzuordnung definieren
wizard.defaultMessage.selectXPath=Definieren Sie die Zeilenzuordnung für Datensätze durch Auswahl eines Elements aus der XML-Struktur.\nJedes Auftreten des gewählten XML-Elements im XML-Dokument wird einer Datensatzzeile zugeordnet.
wizard.title.selectColumn=Spaltenzuordnung definieren
wizard.title.defineColumnMapping=Erstellen Sie Datensatzspalten durch Auswahl und Hinzufügen von Elementen aus der XML-Struktur.\nJedes hinzugefügte XML-Element oder -Attribut wird einer Datensatzspalte zugeordnet. 

#preference
designer.preview.preference.numberOfElementPassedToGetSchema=Anzahl der zu analysierenden Datenelemente für XML-Schema:
designer.preview.preference.numberOfElementPassedToGetSchema.errormessage=Geben Sie eine Zahl zwischen 0 und {0} ein.
designer.preview.preference.columnMappingPage.group.title=Spaltenzuordnung

#XML data source folder selection wizard page
XMLSelectionWizardPage.message.error.emptyXMLSource=Geben Sie die URL der XML-Quelldatei an, oder durchsuchen Sie die XML-Datei.

#row mapping page messages
xPathChoosePage.messages.elementSelection.label=XML-Element-Auswahl
xPathChoosePage.messages.elementSelection.item.absolutePath=&XML-Elemente mit dem Namen '{0}' an feststehendem absolutem Pfad\n
xPathChoosePage.messages.elementSelection.item.anyLocation=XML-&Elemente mit dem Namen '{0}' an beliebigem Speicherort\n
xPathChoosePage.messages.elementSelection.disable.absolutePath=&XML-Elemente an feststehendem absolutem Pfad\n 
xPathChoosePage.messages.elementSelection.disable.anyLocation=XML-&Elemente an beliebigem Speicherort\n 
xPathChoosePage.messages.elementSelection.item.custom=Ben&utzerdefinierter XPath-Ausdruck
xPathChoosePage.messages.xPathExpression=&XPath-Ausdruck
xPathChoosePage.messages.xmlStructure=XML-Struktur
xPathChoosePage.messages.xmlColumnMapping=Spaltenzuordnung
xPathChoosePage.messages.xpathChange=Stellen Sie sicher, dass die geänderte Tabellenzuordnung mit der Spaltenzuordnung kompatibel ist (falls zutreffend).   
xPathChoosePage.FileNameIcon=Dateinamensymbol
xPathChoosePage.XMLElementIcon=XML-Element-Symbol
xPathChoosePage.ColumnIcon=Spaltensymbol
xPathChoosePage.messages.btnAdd.tooltip=Ausgewählten Knoten als XPath-Ausdruck verwenden.
xPathChoosePage.messages.noURLOfSourceOrSchema=Keine URL von XML-Quelldatei oder XML-Schema angegeben.

#sample xml data file page messages
xmlDataSetSelectionPage.messages.lineofdata=Zeilen der Quelldaten anzeigen
xmlDataSetSelectionPage.warning.errorReadXMLFile=Fehler beim Lesen der XML-Daten in Datei [{0}]\n{1}. Dennoch fortfahren?

#column mapping page messages
button.refresh=Aktualisieren
menu.menuItem.removeAll=Alles entfernen
menu.menuItem.remove=Entfernen
menu.button.add=&Hinzufügen...
menu.button.edit=&Bearbeiten...
menu.button.remove=&Entfernen
menu.button.up=Nach &oben
menu.button.down=Nach &unten
menu.button.preview=Beispieldaten &anzeigen
lable.selectXmlFile=Geben Sie den URL der &XML-Quelldatei an, oder suchen Sie die Datei mit den Daten:
label.selectEncoding=&Codierung für oben festgelegte XML-Quelldatei und -Schema auswählen:
lable.selectXmlSchmaFile=Geben Sie den URL des X&ML-Schemas an, oder suchen\n Sie nach der Datei, die das\n Schema enthält. Lassen Sie das Feld leer, falls kein Schema vorhanden ist:
label.preview=&Erste
label.useXMLFileFromDataSource=Die in der Datenquelle definierte XML-Datei &verwenden.
RowMappingDialog.title=Wählen oder bearbeiten Sie den XPath-Ausdruck.
RowMappingDialog.info=Wählen Sie für den gewählten Knoten einen der vordefinierten XPath-Ausdrücke,\n oder geben Sie einen anderen Ausdruck an:
ColumnMappingPage.AddSingleButton.tooltip=Den ausgewählten Knoten als Spaltenzuordnung verwenden.
ColumnMappingPage.AddAllButton.tooltip=Alle untergeordneten Spalten des ausgewählten Knotens als Spaltenzuordnung verwenden.
ColumnMappingTable.upButton.tooltip=Spaltenzuordnung nach oben verschieben.
ColumnMappingTable.downButton.tooltip=Spaltenzuordnung nach unten verschieben.
ColumnMappingTable.removeButton.tooltip=Spaltenzuordnung entfernen.
ColumnMappingTable.editButton.tooltip=Spaltenzuordnung bearbeiten.
ColumnMappingTable.previewButton.tooltip=Vorschau der Beispiel-XML-Daten anzeigen. 
ColumnMappingDialog.group.message=XPath-Auswahl
ColumnMappingDialog.info.columnName=&Spaltenname:
ColumnMappingDialog.info.xPath=&XPath-Ausdruck:
ColumnMappingDialog.info.dataType=&Datentyp:
ColumnMappingDialog.dialog.info=Wählen Sie einen vordefinierten XPath für die aktuelle Spalte, oder geben Sie einen anderen XPath-Ausdruck ein. 
ColumnMappingDialog.prompt.maxRow=Die Quelldaten umfassen mehr als 500 Zeilen. Auf dieser Seite kann nur eine Vorschau der ersten 500 Zeilen angezeigt werden.
ColumnMappingDialog.MenuItem.CreateParameter=Parameter erstellen
ColumnMappingDialog.MenuItem.DeleteParameter=Parameter löschen
XMLDataPreviewDialog.title=Beispiel-XML-Daten

dataset.editor.columnName=Name
dataset.editor.xpathexpression=XPath
dataset.editor.datatype=Typ
dataset.editor.rowelement=Zeilenelement
dataset.error.populateXMLTree=Die XML-Datenquelle kann nicht abgerufen werden. Die XML-Datenquellendatei ist ungültig oder existiert nicht.
dataset.error.CannotPopulateXMLTree=Die XML-Datenquelle kann nicht abgerufen werden.

error.label=Fehler
error.selectFolder=Der URL für die gewählte Datei ist ungültig oder nicht vorhanden.
error.emptyPath=Der URL der Datei enthält keine Daten
error.xpath.canNotContainAttribute=Der XPath-Ausdruck kann nicht einem XML-Attribut zugeordnet werden.
error.xpath.getPathList=Beim Abrufen der XPath-Liste durch den ausgewählten Eintrag im XML-Strukturbaum ist ein Fehler aufgetreten.
error.dataset.Error.MissingFileLocation=Geben Sie den Speicherort der XML-Datei an.
error.dataset.maxRowNumberError=Der Wert für die maximale Zeilenzahl muss eine Zahl sein.
error.columnMapping.createPage=Auf der Spaltenzuordnungsseite ist ein Fehler aufgetreten. 
error.columnMapping.columnElement.create=Bei der Zuordnung eines Spaltenzuordnungselements ist ein Fehler aufgetreten.
error.columnMapping.columnElement.edit=Bei der Bearbeitung eines Spaltenzuordnungselements ist ein Fehler aufgetreten.
error.columnMapping.ATreeNode.getChildren=Beim Abrufen der untergeordneten Knoten des ausgewählten XML-Elements tritt ein Fehler auf.
error.columnMapping.SelectedTreeItem.notNull=Es ist kein Eintrag im Strukturbaum zur Erstellung des Spaltenzuordnungselements ausgewählt. Wählen Sie einen oder mehrere Einträge aus.
error.columnMapping.noMappingColumn=Ordnen Sie mindestens eine Spalte zu.
error.columnMapping.tableMappingXPathNotExist=Es gibt keinen XPath für die Tabellenzuordnung.
error.file.null=Keiner der URLs enthält Daten. Sie haben foglich keine Datenquelle ausgewählt. Soll diese Auswahl beibehalten werden?
error.columnMapping.sameColumnName=Der Spaltenname [{0}] ist bereits vorhanden. Wählen Sie einen anderen Namen.
error.invalidXpath=Der XPath-Ausdruck ist ungültig.
error.invalidSource=Die XML-Quelldatei kann nicht gefunden werden.

#data types for column mapping
datatypes.dateTime=Datum/Uhrzeit
datatypes.decimal=Dezimal
datatypes.float=Gleitkomma
datatypes.integer=Ganzzahl
datatypes.string=String
datatypes.date=Date
datatypes.time=Time
datatypes.boolean=Boolesch

#column name in column mapping table
columnName.name=Name
columnName.xPathName=XPath
columnName.typeName=Typ

dataset.new=Neuer Datensatz
file.choose=&Durchsuchen ...
schema.choose=Durch&suchen...
title.warning=Warnung
FileSelectionButton.menuItem.absolutePath=&Absoluter Pfad
FileSelectionButton.menuItem.relativePath=&Relativer Pfad
SelectRelativeFileDialog.Title=Datei auswählen

datasource.propertyPage.tooltip=XML-Datenquelle
wizard.autoEncoding=Automatisch
