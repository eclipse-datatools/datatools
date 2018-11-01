#
#************************************************************************
# Copyright (c) 2007 Actuate Corporation.
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
# Externalized messages of the Web Services Data Source Designer

#page.type.name

# Common

# WebServiceSelectionPage
webServiceSelectionPage.message.default=Datenquelle für Web-Dienste
webServiceSelectionPage.message.error=Geben Sie die URL des SOAP-Endpunkts an, oder wählen Sie einen WSDL-Deskriptor.
webServiceSelectionPage.group.wsdl=WSDL-Deskriptor
webServiceSelectionPage.group.endPoint=&SOAP-Endpunkt
webServiceSelectionPage.group.customClass=&Benutzerdefinierte Treiberklasse
webServiceSelectionPage.group.classPath=&Treiberklassenpfad
webServiceSelectionPage.label.wsdl=Wenn der WSDL-Deskriptor der Web-Dienste verfügbar ist, geben Sie die URL ein, oder geben Sie den Speicherort im lokalen Dateisystem an.
webServiceSelectionPage.label.wsdlURI=URL oder Speicherort für &WSDL:
webServiceSelectionPage.label.endPoint=Geben Sie die URL des SOAP-Endpunkts an. Sie können diesen Wert weglassen, wenn Sie den im WSDL definierten Endpunkt\noder eine benutzerdefinierte Verbindungsklasse verwenden, für die keine Endpunkt-URL erforderlich ist.
webServiceSelectionPage.label.customClass=Wenn Verbindung und Abfrage für die Web-Dienste über eine benutzerdefinierte Java-Klasse erfolgen, geben Sie hier den vollständig qualifizierten Klassennamen ein.
webServiceSelectionPage.label.classPath=Eine durch Semikolon (;) getrennte Liste von zusätzlichen JAR-Dateien und Verzeichnissen, um nach der benutzerdefinierten Treiberklasse zu suchen.
webServiceSelectionPage.button.browse=Du&rchsuchen...

# OperationPage
operationPage.message.default=Wählen Sie einen im WSDL-Deskriptor der Datenquelle definierten Vorgang aus.
operationPage.message.regenerate.SOAPRequest=Erstellen Sie die SOAP-Anforderung neu, und verbinden Sie den Endpunkt neu gemäß dem geänderten Vorgang.
operationPage.title.operationChanged=Vorgang geändert
operationPage.label.selectOpearation=Au&sgewählter Vorgang
operationPage.label.document=&Dokumentation

# SoapParametersPage
soapParametersPage.message.default=Geben Sie die Parameter des gewählten WSDL-Vorgangs ein.
soapParametersPage.button.selectAll=&Alles markieren
soapParametersPage.button.deselectAll=A&uswahl aufheben
soapParametersPage.column.paramName=N&ame
soapParametersPage.column.dataType=Daten&typ

# SoapRequestPage
soapRequestPage.message.defaultTitle=Bearbeiten Sie die Vorlage für die SOAP-Anforderung dieses Datensatzes. 
soapRequestPage.message.regenerateTemplate=Die Abfragetext-Vorlage wird auf Grundlage des gewählten SOAP-Vorgangs neu erstellt. Alle am Text vorgenommenen Änderungen gehen verloren.\n\nMöchten Sie wirklich fortfahren?
soapRequestPage.label.prompt=Wenn für die benutzerdefinierte Verbindungsklasse kein Abfragetext erforderlich ist, brauchen Sie keinen Text einzugeben.
soapRequestPage.title.regenerateTemplate=Vorlage neu erstellen
soapRequestPage.button.regenerateTemplate=Vorlage &neu erstellen
soapRequestPage.button.insertParameter=Parameter b&earbeiten...
soapRequestPage.button.clear=&Entf
soapRequestPage.paramEditDialog.title=Parametereigenschaft bearbeiten

# ParameterInputDialog
parameterInputDialog.column.name=&Name
parameterInputDialog.column.type=Daten&typ
parameterInputDialog.column.defaultValue=Standard&wert
parameterInputDialog.button.edit=B&earbeiten...

# SoapResponsePage
soapResponsePage.message.default=SOAP-Antwort bearbeiten.
soapResponsePage.group.schema=SOAP-Antwortschema auswählen.
soapResponsePage.group.xml=Beispiel-SOAP-Antwortnachricht auswählen.
soapResponsePage.radio.defaultSchema=In &Datenquellen-WSDL definiertes Vorgangs-Antwortschema verwenden.
soapResponsePage.radio.reponseSchema=Schema von &Antwort verwenden.
soapResponsePage.radio.externalSchema=Externe &XML-Schema-Datei verwenden:
soapResponsePage.radio.endPoint=Web-Dienste an SOAP-&Endpunkt abfragen:
soapResponsePage.radio.externalXML=Externe Beispiel-&Datendatei verwenden
soapResponsePage.button.browse1=Durchsu&chen...
soapResponsePage.button.browse2=Durch&suchen...
soapResponsePage.button.connect=&Jetzt verbinden

# XMLTableMappingPage

# XMLColumnMappingPage

# WSConsole
wsConsole.message.error.cantRetrieveSOAPResponse=SOAP-Antwort kann nicht abgerufen werden. Überprüfen Sie die Netzwerkeinstellungen oder die Einstellungen für die SOAP-Antwort.
