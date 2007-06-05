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
webServiceSelectionPage.message.default=Fuente de datos de servicios web
webServiceSelectionPage.message.error=Especifique la URL del punto final de SOAP o seleccione un descriptor WSDL
webServiceSelectionPage.group.wsdl=Descriptor WSDL
webServiceSelectionPage.group.endPoint=Punto final de SOAP
webServiceSelectionPage.group.customClass=Clase de conexión personalizada
webServiceSelectionPage.label.wsdl=Si está disponible el descriptor WSDL de los servicios web, introduzca su URL o busque su ubicación en el sistema de archivos locales
webServiceSelectionPage.label.wsdlURI=URL o ubicación de &WSDL:
webServiceSelectionPage.label.endPoint=Especifique la URL del punto &final de SOAP. Este valor es opcional si se utiliza una clase de conexión personalizada.
webServiceSelectionPage.label.customClass=Si se ha realizado una conexión o consulta de los servicios web a través de una clase &Java personalizada, introduzca el nombre de clase completo aquí.
webServiceSelectionPage.button.browse=&Examinar...

# OperationPage
operationPage.message.default=Seleccione una operación definida en el descriptor WSDL de la fuente de datos
operationPage.message.operationChanged=Confrim to regenerate the SOAP request and reconnect the End Point
operationPage.title.operationChanged=Operation Changed
operationPage.label.selectOpearation=Operación seleccionada
operationPage.label.document=Documentación

# SoapParametersPage
soapParametersPage.message.default=Enter the parameters of chosen WSDL operation

# SoapRequestPage
soapRequestPage.message.default=Edite la plantilla de solicitud de SOAP de este conjunto de datos. Si el código de la clase de conexión personalizada de la fuente de datos no requiere un texto de consulta, puede dejar vacío el texto
soapRequestPage.message.regenerateTemplate=This will regenerate the query text template based on the SOAP operation you have selected. It will remove any modifications you have made to the text.\n\nAre you sure?
soapRequestPage.title.regenerateTemplate=Regenerate Template
soapRequestPage.button.regenerateTemplate=&Regenerate Template
soapRequestPage.button.insertParameter=&Insertar parámetro...
soapRequestPage.button.clear=&Borrar

# ParameterInputDialog
parameterInputDialog.column.name=Nombre
parameterInputDialog.column.type=Tipo de datos
parameterInputDialog.column.defaultValue=Valor predeterminado

# SoapResponsePage
soapResponsePage.message.default=Editar la respuesta de SOAP
soapResponsePage.group.schema=Seleccionar esquema de respuesta de SOAP
soapResponsePage.group.xml=Seleccionar mensaje de respuesta de SOAP de muestra
soapResponsePage.radio.defaultSchema=Utilizar esquema de la respuesta de &operación definido en fuente de datos WSDL
soapResponsePage.radio.externalSchema=Utilizar archivo del e%esquema XML externo:
soapResponsePage.radio.endPoint=Consultar servicios web en el punto &final de SOAP:
soapResponsePage.radio.externalXML=Utilizar archivo de &datos de muestra externo
soapResponsePage.button.browse=&Examinar...
soapResponsePage.button.connect=&Conectar ahora

# XMLTableMappingPage

# XMLColumnMappingPage

# WSConsole
wsConsole.message.error.cantRetrieveSOAPResponse=La respuesta SOAP no se puede recuperar, verifique el punto final.
