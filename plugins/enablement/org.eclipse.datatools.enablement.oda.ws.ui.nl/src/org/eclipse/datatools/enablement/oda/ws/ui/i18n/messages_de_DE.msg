#
#************************************************************************
# Copyright (c) 2007 Actuate Corporation.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#  Actuate Corporation - initial API and implementation
#  
#************************************************************************
#
# Externalized messages of the Web Services Data Source Designer

#page.type.name

# Common

# WebServiceSelectionPage
webServiceSelectionPage.message.default=Datenquelle für Web-Dienste
webServiceSelectionPage.message.error=Geben Sie den URL des SOAP-Endpunkts an, oder wählen Sie einen WSDL-Deskriptor.
webServiceSelectionPage.group.wsdl=WSDL-Deskriptor
webServiceSelectionPage.group.endPoint=SOAP-Endpunkt
webServiceSelectionPage.group.customClass=Benutzerdefinierte Treiberklasse
webServiceSelectionPage.label.wsdl=Wenn der WSDL-Deskriptor der Web-Dienste verfügbar ist, geben Sie den URL ein, oder geben Sie den Speicherort im lokalen Dateisystem an.
webServiceSelectionPage.label.wsdlURI=URL oder Speicherort für &WSDL:
webServiceSelectionPage.label.endPoint=Geben Sie die URL des SOAP-&Endpunkts an. Sie können diesen Wert weglassen, wenn Sie den im WSDL definierten Endpunkt\noder eine benutzerdefinierte Verbindungsklasse verwenden, für die keine Endpunkt-URL erforderlich ist.
webServiceSelectionPage.label.customClass=Wenn Verbindung und Abfrage für die Web-Dienste über eine benutzerdefinierte &Java-Klasse erfolgen, geben Sie hier den vollständig qualifizierten Klassennamen ein.
webServiceSelectionPage.button.browse=&Durchsuchen...

# OperationPage
operationPage.message.default=Wählen Sie einen im WSDL-Deskriptor der Datenquelle definierten Vorgang aus.
operationPage.message.operationChanged=Bestätigen Sie zum Neuerzeugen der SOAP-Anforderung und zum Neuverbinden des Endpunkts.
operationPage.title.operationChanged=Vorgang geändert
operationPage.label.selectOpearation=Ausgewählter Vorgang
operationPage.label.document=Dokumentation

# SoapParametersPage
soapParametersPage.message.default=Geben Sie die Parameter des gewählten WSDL-Vorgangs ein.

# SoapRequestPage
soapRequestPage.message.default=Bearbeiten Sie die Vorlage für die SOAP-Anforderung dieses Datensatzes. Wenn für den Code der benutzerdefinierten Verbindungsklasse Ihrer Datenquelle kein Abfragetext erforderlich ist, brauchen Sie keinen Text einzugeben.
soapRequestPage.message.regenerateTemplate=Die Abfragetext-Vorlage wird auf Grundlage des gewählten SOAP-Vorgangs neu erstellt. Alle am Text vorgenommenen Änderungen gehen verloren.\n\nMöchten Sie wirklich fortfahren?
soapRequestPage.title.regenerateTemplate=Vorlage neu erstellen
soapRequestPage.button.regenerateTemplate=Vorlage &neu erstellen
soapRequestPage.button.insertParameter=Parameter e&infügen...
soapRequestPage.button.clear=&Entf


# ParameterInputDialog
parameterInputDialog.column.name=Name
parameterInputDialog.column.type=Datentyp
parameterInputDialog.column.defaultValue=Standardwert

# SoapResponsePage
soapResponsePage.message.default=SOAP-Antwort bearbeiten
soapResponsePage.group.schema=SOAP-Antwortschema auswählen
soapResponsePage.group.xml=Beispiel-SOAP-Antwortnachricht auswählen
soapResponsePage.radio.defaultSchema=In &Datenquellen-WSDL definiertes Vorgangs-Antwortschema verwenden
soapResponsePage.radio.externalSchema=Externe XML-&Schema-Datei verwenden:
soapResponsePage.radio.endPoint=Web-Dienste an SOAP-&Endpunkt abfragen:
soapResponsePage.radio.externalXML=Externe Beispiel-&Datendatei verwenden
soapResponsePage.button.browse=&Durchsuchen...
soapResponsePage.button.connect=&Jetzt verbinden

# XMLTableMappingPage

# XMLColumnMappingPage

# WSConsole
wsConsole.message.error.cantRetrieveSOAPResponse=SOAP-Antwort kann nicht abgerufen werden. Überprüfen Sie die Netzwerkeinstellungen oder die Einstellungen für die SOAP-Antwort.
