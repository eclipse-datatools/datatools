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
webServiceSelectionPage.message.default=Web Services 数据源
webServiceSelectionPage.message.error=指定 SOAP 终结点 URL 或选择 WSDL 描述符。
webServiceSelectionPage.group.wsdl=WSDL 描述符
webServiceSelectionPage.group.endPoint=SOAP 终结点(&S)
webServiceSelectionPage.group.customClass=自定义驱动程序类(&C)
webServiceSelectionPage.group.classPath=驱动程序类路径(&D)
webServiceSelectionPage.label.wsdl=如果 Web Services 的 WSDL 描述符可用，请输入其 URL 或浏览到其在本地文件系统中的所在位置。
webServiceSelectionPage.label.wsdlURI=WSDL URL 或位置(&W):
webServiceSelectionPage.label.endPoint=指定 SOAP 终结点 URL。如果要使用 WSDL 中定义的终结点，\n或者您正在使用不需要终结点 URL 的自定义连接类，则可以忽略此值。
webServiceSelectionPage.label.customClass=如果 Web Services 的连接和查询是通过自定义 Java 类来完成的，请在此处输入完全限定类名称。
webServiceSelectionPage.label.classPath=其它 JAR 文件和目录的分号(;)分隔列表，用于搜索自定义驱动程序类。
webServiceSelectionPage.button.browse=浏览(&R)...

# OperationPage
operationPage.message.default=选择在数据源的 WSDL 描述符中定义的操作。
operationPage.message.regenerate.SOAPRequest=重新生成 SOAP 请求并根据更改的操作重新连接终结点。
operationPage.title.operationChanged=操作已更改
operationPage.label.selectOpearation=选定的操作(&S)
operationPage.label.document=文档(&D)

# SoapParametersPage
soapParametersPage.message.default=输入所选 WSDL 操作的参数。
soapParametersPage.button.selectAll=全选(&S)
soapParametersPage.button.deselectAll=取消全选(&D)
soapParametersPage.column.paramName=名称(&A)
soapParametersPage.column.dataType=数据类型(&T)

# SoapRequestPage
soapRequestPage.message.defaultTitle=编辑此数据集的 SOAP 请求模板。 
soapRequestPage.message.regenerateTemplate=这将根据您选择的 SOAP 操作重新生成查询文本模板。它将删除您对文本所做的任何修改。\n\n是否确定?
soapRequestPage.label.prompt=如果您的自定义连接类不需要查询文本，您可以将文本留空。
soapRequestPage.title.regenerateTemplate=重新生成模板
soapRequestPage.button.regenerateTemplate=重新生成模板(&R)
soapRequestPage.button.insertParameter=编辑参数(&E)...
soapRequestPage.button.clear=清除(&C)
soapRequestPage.paramEditDialog.title=编辑参数属性

# ParameterInputDialog
parameterInputDialog.column.name=名称(&N)
parameterInputDialog.column.type=数据类型(&T)
parameterInputDialog.column.defaultValue=默认值(&V)
parameterInputDialog.button.edit=编辑(&E)...

# SoapResponsePage
soapResponsePage.message.default=编辑 SOAP 响应。
soapResponsePage.group.schema=选择 SOAP 响应架构。
soapResponsePage.group.xml=选择示例 SOAP 响应消息。
soapResponsePage.radio.defaultSchema=使用在数据源 WSDL 中定义的操作响应架构(&O)。
soapResponsePage.radio.reponseSchema=使用响应中的架构(&R)。
soapResponsePage.radio.externalSchema=使用外部 XML 架构文件(&X):
soapResponsePage.radio.endPoint=在 SOAP 终结点查询 Web Services(&E):
soapResponsePage.radio.externalXML=使用外部示例数据文件(&D)
soapResponsePage.button.browse1=浏览(&W)...
soapResponsePage.button.browse2=浏览(&S)...
soapResponsePage.button.connect=立即连接(&C)

# XMLTableMappingPage

# XMLColumnMappingPage

# WSConsole
wsConsole.message.error.cantRetrieveSOAPResponse=无法检索 SOAP 响应，请验证网络或 SOAP 响应设置。
