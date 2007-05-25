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
webServiceSelectionPage.label.wsdlURI=URL o ubicación de WSDL:
webServiceSelectionPage.label.endPoint=Especifique la URL del punto final de SOAP. Este valor es opcional, si se usa una clase de conexión personalizada
webServiceSelectionPage.label.customClass=Si la conexión y la consulta a los servicios web se realiza a través de una clase Java personalizada, introduzca aquí el nombre completo de la clase
webServiceSelectionPage.button.browse=Examinar...

# OperationPage
operationPage.message.default=Seleccione una operación definida en el descriptor WSDL de la fuente de datos
operationPage.label.selectOpearation=Operación seleccionada
operationPage.label.document=Documentación

# SoapRequestPage
soapRequestPage.message.default=Edite la plantilla de solicitud de SOAP de este conjunto de datos. Si el código de la clase de conexión personalizada de la fuente de datos no requiere un texto de consulta, puede dejar vacío el texto
soapRequestPage.button.generateTemplate=Generar plantilla
soapRequestPage.button.insertParameter=Insertar\nparámetro...
soapRequestPage.button.clear=Borrar

# ParameterInputDialog
parameterInputDialog.column.name=Nombre
parameterInputDialog.column.type=Tipo de datos
parameterInputDialog.column.defaultValue=Valor predeterminado

# SoapResponsePage
soapResponsePage.message.default=Editar la respuesta de SOAP
soapResponsePage.group.schema=Seleccionar esquema de respuesta de SOAP
soapResponsePage.group.xml=Seleccionar mensaje de respuesta de SOAP de muestra
soapResponsePage.radio.defaultSchema=Usar el esquema de respuesta de la operación definido en la fuente de datos WSDL
soapResponsePage.radio.externalSchema=Usar archivo de esquema XML externo:
soapResponsePage.radio.endPoint=Consulta a los servicios web en el punto final de SOAP:
soapResponsePage.radio.externalXML=Usar archivo de datos de muestra externos
soapResponsePage.button.browse=Examinar...
soapResponsePage.button.connect=Conectar ahora

# XMLTableMappingPage

# XMLColumnMappingPage
