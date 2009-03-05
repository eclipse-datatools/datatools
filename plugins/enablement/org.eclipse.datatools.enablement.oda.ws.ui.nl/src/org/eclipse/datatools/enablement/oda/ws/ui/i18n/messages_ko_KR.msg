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
webServiceSelectionPage.message.default=웹 서비스 데이터 원본
webServiceSelectionPage.message.error=SOAP 끝점 URL을 지정하거나 WSDL 설명자를 선택하십시오.
webServiceSelectionPage.group.wsdl=WSDL 설명자
webServiceSelectionPage.group.endPoint=SOAP 끝점(&S)
webServiceSelectionPage.group.customClass=사용자 지정 드라이버 클래스(&C)
webServiceSelectionPage.group.classPath=드라이버 클래스 경로(&D)
webServiceSelectionPage.label.wsdl=웹 서비스의 WSDL 설명자가 있으면 해당 URL을 입력하거나 로컬 파일 시스템에서 위치를 찾습니다.
webServiceSelectionPage.label.wsdlURI=WSDL URL 또는 위치(&W):
webServiceSelectionPage.label.endPoint=Specify the SOAP End Point URL. You may omit this value if the end point defined in the WSDL is to be used,\nor if you are using a custom connection class that does not require a end point URL.
webServiceSelectionPage.label.customClass=If connection and query to the Web Services is made through a custom Java class, enter the fully qualified class name here.
webServiceSelectionPage.label.classPath=사용자 지정 드라이버 클래스를 검색할 세미콜론(;)으로 구분된 추가 JAR 파일 및 디렉터리 목록입니다.
webServiceSelectionPage.button.browse=찾아보기(&R)...

# OperationPage
operationPage.message.default=데이터 원본의 WSDL 설명자에 정의된 작업을 선택합니다.
operationPage.message.operationChanged=SOAP 요청을 다시 생성하고 끝점에 다시 연결할 것인지 확인합니다.
operationPage.title.operationChanged=작업이 변경됨
operationPage.label.selectOpearation=선택된 작업(&S)
operationPage.label.document=설명서(&D)

# SoapParametersPage
soapParametersPage.message.default=선택한 WSDL 작업의 매개 변수를 입력합니다.
soapParametersPage.button.selectAll=모두 선택(&S)
soapParametersPage.button.deselectAll=모두 선택 취소(&D)
soapParametersPage.column.paramName=N&ame
soapParametersPage.column.dataType=Data &Type

# SoapRequestPage
soapRequestPage.message.default=이 데이터 집합의 SOAP 요청 템플릿을 편집합니다. 데이터 원본의 사용자 지정 연결 클래스 코드에 쿼리 텍스트가 필요하지 않을 경우 입력란을 비워두어도 됩니다.
soapRequestPage.message.regenerateTemplate=선택한 SOAP 작업에 근거한 쿼리 텍스트 템플릿이 다시 생성될 것입니다. 이전에 해당 텍스트에 기록한 변경 사항이 모두 사라질 것입니다.\n\n계속하시겠습니까?
soapRequestPage.title.regenerateTemplate=템플릿 다시 생성
soapRequestPage.button.regenerateTemplate=템플릿 다시 생성(&R)
soapRequestPage.button.insertParameter=&Edit Parameter...
soapRequestPage.button.clear=지우기(&C)
soapRequestPage.paramEditDialog.title=매개 변수 속성 편집

# ParameterInputDialog
parameterInputDialog.column.name=&Name
parameterInputDialog.column.type=Data &Type
parameterInputDialog.column.defaultValue=Default &Value
parameterInputDialog.button.edit=&Edit...

# SoapResponsePage
soapResponsePage.message.default=SOAP 응답을 편집합니다.
soapResponsePage.group.schema=SOAP 응답 스키마를 선택합니다.
soapResponsePage.group.xml=샘플 SOAP 응답 메시지를 선택합니다.
soapResponsePage.radio.defaultSchema=데이터 원본 WSDL에 정의된 작업 응답 스키마를 사용합니다(&O).
soapResponsePage.radio.reponseSchema=응답 스키마 사용(&R)
soapResponsePage.radio.externalSchema=Use external &XML schema file:
soapResponsePage.radio.endPoint=SOAP 끝점에서 웹 서비스 쿼리(&E):
soapResponsePage.radio.externalXML=외부 샘플 데이터 파일 사용(&D)
soapResponsePage.button.browse1=Bro&wse...
soapResponsePage.button.browse2=Brow&se...
soapResponsePage.button.connect=지금 연결하기(&C)

# XMLTableMappingPage

# XMLColumnMappingPage

# WSConsole
wsConsole.message.error.cantRetrieveSOAPResponse=SOAP 응답을 검색할 수 없습니다. 네트워크나 SOAP 응답 설정을 확인하십시오.
