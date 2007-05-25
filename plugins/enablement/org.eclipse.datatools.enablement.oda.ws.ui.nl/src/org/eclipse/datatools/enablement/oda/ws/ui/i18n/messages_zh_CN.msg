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
webServiceSelectionPage.message.default=Web Services 数据源
webServiceSelectionPage.message.error=指定 SOAP 终结点 URL 或选择 WSDL 描述符
webServiceSelectionPage.group.wsdl=WSDL 描述符
webServiceSelectionPage.group.endPoint=SOAP 终结点
webServiceSelectionPage.group.customClass=自定义连接类
webServiceSelectionPage.label.wsdl=如果 Web Services 的 WSDL 描述符可用，请输入其 URL 或浏览到其在本地文件系统中的所在位置
webServiceSelectionPage.label.wsdlURI=WSDL URL 或位置:
webServiceSelectionPage.label.endPoint=指定 SOAP 终结点 URL。如果使用了自定义连接类，此值可选。
webServiceSelectionPage.label.customClass=如果 Web Services 的连接和查询是通过自定义 Java 类来完成的，请在此处输入完全限定类名称
webServiceSelectionPage.button.browse=浏览...

# OperationPage
operationPage.message.default=选择在数据源的 WSDL 描述符中定义的操作
operationPage.label.selectOpearation=选定的操作
operationPage.label.document=文档

# SoapRequestPage
soapRequestPage.message.default=编辑此数据集的 SOAP 请求模板。如果您的数据源自定义连接类代码不需要查询文本，您可以将文本留空。
soapRequestPage.button.generateTemplate=生成模板
soapRequestPage.button.insertParameter=插入\n 参数...
soapRequestPage.button.clear=清除

# ParameterInputDialog
parameterInputDialog.column.name=名称
parameterInputDialog.column.type=数据类型
parameterInputDialog.column.defaultValue=默认值

# SoapResponsePage
soapResponsePage.message.default=编辑 SOAP 响应
soapResponsePage.group.schema=选择 SOAP 响应架构
soapResponsePage.group.xml=选择示例 SOAP 响应消息
soapResponsePage.radio.defaultSchema=使用在数据源 WSDL 中定义的操作响应架构
soapResponsePage.radio.externalSchema=使用外部l XML 架构文件:
soapResponsePage.radio.endPoint=在 SOAP 终结点查询 Web Services:
soapResponsePage.radio.externalXML=使用外部示例数据文件
soapResponsePage.button.browse=浏览...
soapResponsePage.button.connect=立即连接

# XMLTableMappingPage

# XMLColumnMappingPage
