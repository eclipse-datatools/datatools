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
webServiceSelectionPage.message.error=Indique la URL del punto final de SOAP o seleccione un descriptor WSDL.
webServiceSelectionPage.group.wsdl=Descriptor WSDL
webServiceSelectionPage.group.endPoint=Punto final de SOAP
webServiceSelectionPage.group.customClass=Clase de controlador personalizado
webServiceSelectionPage.group.classPath=Ruta de clase del controlador
webServiceSelectionPage.label.wsdl=Si el descriptor WSDL del servicio Web está disponible, introduzca su URL o busque su ubicación en el sistema de archivos local.
webServiceSelectionPage.label.wsdlURI=URL o ubicación de &WSDL:
webServiceSelectionPage.label.endPoint=Especifique la URL del punto &final de SOAP. Puede omitir este valor si se debe utilizar el punto final definido en WSDL o si no está utilizando un tipo de conexión personalizada que no necesita una URL de punto final.
webServiceSelectionPage.label.customClass=Si se ha realizado una conexión o consulta de los servicios Web a través de una clase &Java personalizada, introduzca el nombre de clase completo aquí.
webServiceSelectionPage.label.classPath=Una lista separada por punto y coma (;) de directorios y archivos JAR adicionales para buscar la clase de controlador personalizado.
webServiceSelectionPage.button.browse=&Examinar...

# OperationPage
operationPage.message.default=Seleccione una operación definida en el descriptor WSDL de la fuente de datos.
operationPage.message.operationChanged=Confirme para volver a generar la solicitud de SOAP y volver a conectar el punto final.
operationPage.title.operationChanged=Operación cambiada
operationPage.label.selectOpearation=Operación seleccionada
operationPage.label.document=Documentación

# SoapParametersPage
soapParametersPage.message.default=Introduzca los parámetros de la operación WSDL que ha seleccionado.
soapParametersPage.button.selectAll=&Seleccionar todo
soapParametersPage.button.deselectAll=&Anular la selección de todo

# SoapRequestPage
soapRequestPage.message.default=Edite la plantilla de solicitud SOAP de este conjunto de datos. Es posible dejar el campo de texto vacío si el código de la clase de conexión personalizada de la fuente de datos no necesita texto de consulta.
soapRequestPage.message.regenerateTemplate=Esto volverá a generar la plantilla del texto de consulta según la operación SOAP que ha seleccionado. Esta acción eliminará cualquier modificación que haya introducido en el texto.\n\n¿Está seguro?
soapRequestPage.title.regenerateTemplate=Volver a generar la plantilla
soapRequestPage.button.regenerateTemplate=&Volver a generar la plantilla
soapRequestPage.button.insertParameter=&Insertar parámetro...
soapRequestPage.button.clear=&Borrar
soapRequestPage.paramEditDialog.title=Editar propiedad del parámetro

# ParameterInputDialog
parameterInputDialog.column.name=Nombre
parameterInputDialog.column.type=Tipo de datos
parameterInputDialog.column.defaultValue=Valor predeterminado

# SoapResponsePage
soapResponsePage.message.default=Editar la respuesta SOAP.
soapResponsePage.group.schema=Seleccionar el esquema de respuesta SOAP.
soapResponsePage.group.xml=Seleccione un mensaje de respuesta SOAP de muestra.
soapResponsePage.radio.defaultSchema=Utilizar esquema de la respuesta de &operación definido en fuente de datos WSDL.
soapResponsePage.radio.reponseSchema=Utilizar esquema de &respuesta.
soapResponsePage.radio.externalSchema=Utilizar archivo del e%esquema XML externo:
soapResponsePage.radio.endPoint=Consultar servicios web en el punto &final de SOAP:
soapResponsePage.radio.externalXML=Utilizar archivo de &datos de muestra externo
soapResponsePage.button.browse=&Examinar...
soapResponsePage.button.connect=&Conectar ahora

# XMLTableMappingPage

# XMLColumnMappingPage

# WSConsole
wsConsole.message.error.cantRetrieveSOAPResponse=La respuesta SOAP no se puede recuperar, verifique la red o la configuración de respuesta SOAP.
