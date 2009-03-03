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
webServiceSelectionPage.message.error=Geben Sie die URL des SOAP-Endpunkts an, oder wählen Sie einen WSDL-Deskriptor.
webServiceSelectionPage.group.wsdl=WSDL-Deskriptor
webServiceSelectionPage.group.endPoint=&SOAP End Point
webServiceSelectionPage.group.customClass=&Custom Driver Class
webServiceSelectionPage.group.classPath=&Driver Class Path
webServiceSelectionPage.label.wsdl=Wenn der WSDL-Deskriptor der Web-Dienste verfügbar ist, geben Sie die URL ein, oder geben Sie den Speicherort im lokalen Dateisystem an.
webServiceSelectionPage.label.wsdlURI=URL oder Speicherort für &WSDL:
webServiceSelectionPage.label.endPoint=Specify the SOAP End Point URL. You may omit this value if the end point defined in the WSDL is to be used,\nor if you are using a custom connection class that does not require a end point URL.
webServiceSelectionPage.label.customClass=If connection and query to the Web Services is made through a custom Java class, enter the fully qualified class name here.
webServiceSelectionPage.label.classPath=Eine durch Semikolon (;) getrennte Liste von zusätzlichen JAR-Dateien und Verzeichnissen, um nach der benutzerdefinierten Treiberklasse zu suchen.
webServiceSelectionPage.button.browse=B&rowse...

# OperationPage
operationPage.message.default=Wählen Sie einen im WSDL-Deskriptor der Datenquelle definierten Vorgang aus.
operationPage.message.operationChanged=Bestätigen Sie zum Neuerzeugen der SOAP-Anforderung und zum Neuverbinden des Endpunkts.
operationPage.title.operationChanged=Vorgang geändert
operationPage.label.selectOpearation=&Selected Operation
operationPage.label.document=&Documentation

# SoapParametersPage
soapParametersPage.message.default=Geben Sie die Parameter des gewählten WSDL-Vorgangs ein.
soapParametersPage.button.selectAll=&Alles markieren
soapParametersPage.button.deselectAll=A&uswahl aufheben
soapParametersPage.column.paramName=N&ame
soapParametersPage.column.dataType=Data &Type

# SoapRequestPage
soapRequestPage.message.default=Bearbeiten Sie die Vorlage für die SOAP-Anforderung dieses Datensatzes. Wenn für den Code der benutzerdefinierten Verbindungsklasse Ihrer Datenquelle kein Abfragetext erforderlich ist, brauchen Sie keinen Text einzugeben.
soapRequestPage.message.regenerateTemplate=Die Abfragetext-Vorlage wird auf Grundlage des gewählten SOAP-Vorgangs neu erstellt. Alle am Text vorgenommenen Änderungen gehen verloren.\n\nMöchten Sie wirklich fortfahren?
soapRequestPage.title.regenerateTemplate=Vorlage neu erstellen
soapRequestPage.button.regenerateTemplate=Vorlage &neu erstellen
soapRequestPage.button.insertParameter=&Edit Parameter...
soapRequestPage.button.clear=&Entf
soapRequestPage.paramEditDialog.title=Parametereigenschaft bearbeiten

# ParameterInputDialog
parameterInputDialog.column.name=&Name
parameterInputDialog.column.type=Data &Type
parameterInputDialog.column.defaultValue=Default &Value
parameterInputDialog.button.edit=&Edit...

# SoapResponsePage
soapResponsePage.message.default=SOAP-Antwort bearbeiten.
soapResponsePage.group.schema=SOAP-Antwortschema auswählen.
soapResponsePage.group.xml=Beispiel-SOAP-Antwortnachricht auswählen.
soapResponsePage.radio.defaultSchema=In &Datenquellen-WSDL definiertes Vorgangs-Antwortschema verwenden.
soapResponsePage.radio.reponseSchema=Schema von Antwo&rt verwenden
soapResponsePage.radio.externalSchema=Use external &XML schema file:
soapResponsePage.radio.endPoint=Web-Dienste an SOAP-&Endpunkt abfragen:
soapResponsePage.radio.externalXML=Externe Beispiel-&Datendatei verwenden
soapResponsePage.button.browse1=Bro&wse...
soapResponsePage.button.browse2=Brow&se...
soapResponsePage.button.connect=&Jetzt verbinden

# XMLTableMappingPage

# XMLColumnMappingPage

# WSConsole
wsConsole.message.error.cantRetrieveSOAPResponse=SOAP-Antwort kann nicht abgerufen werden. Überprüfen Sie die Netzwerkeinstellungen oder die Einstellungen für die SOAP-Antwort.
