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
webServiceSelectionPage.group.endPoint=SOAP 끝점
webServiceSelectionPage.group.customClass=사용자 지정 연결 클래스
webServiceSelectionPage.label.wsdl=웹 서비스의 WSDL 설명자가 있으면 해당 URL을 입력하거나 로컬 파일 시스템에서 위치를 찾으십시오.
webServiceSelectionPage.label.wsdlURI=WSDL URL 또는 위치(&W):
webServiceSelectionPage.label.endPoint=SOAP 끝점 URL을 지정합니다. 사용자 지정 연결 클래스를 사용할 경우 이 값은 옵션 항목입니다(&E).
webServiceSelectionPage.label.customClass=웹 서비스 연결 및 쿼리가 사용자 지정 Java 클래스를 통해 이루어진 경우 여기에 정규화된 클래스 이름을 입력하십시오(&J).
webServiceSelectionPage.button.browse=찾아보기(&B)...

# OperationPage
operationPage.message.default=데이터 원본의 WSDL 설명자에 정의된 작업을 선택하십시오.
operationPage.message.operationChanged=Confrim to regenerate the SOAP request and reconnect the End Point
operationPage.title.operationChanged=Operation Changed
operationPage.label.selectOpearation=선택된 작업
operationPage.label.document=설명서

# SoapParametersPage
soapParametersPage.message.default=Enter the parameters of chosen WSDL operation

# SoapRequestPage
soapRequestPage.message.default=이 데이터 집합의 SOAP 요청 템플릿을 편집합니다. 데이터 원본의 사용자 지정 연결 클래스 코드가 쿼리 텍스트를 필요로 하지 않을 경우 입력란을 비워두셔도 됩니다.
soapRequestPage.message.regenerateTemplate=This will regenerate the query text template based on the SOAP operation you have selected. It will remove any modifications you have made to the text.\n\nAre you sure?
soapRequestPage.title.regenerateTemplate=Regenerate Template
soapRequestPage.button.regenerateTemplate=&Regenerate Template
soapRequestPage.button.insertParameter=매개 변수 삽입(&I)...
soapRequestPage.button.clear=지우기(&C)


# ParameterInputDialog
parameterInputDialog.column.name=이름
parameterInputDialog.column.type=데이터 형식
parameterInputDialog.column.defaultValue=기본값

# SoapResponsePage
soapResponsePage.message.default=SOAP 응답 편집
soapResponsePage.group.schema=SOAP 응답 스키마 선택
soapResponsePage.group.xml=SOAP 응답 메시지 샘플 선택
soapResponsePage.radio.defaultSchema=데이터 원본 WSDL에 정의된 작업 응답 스키마 사용(&O)
soapResponsePage.radio.externalSchema=외부 XML 스키마 파일 사용(&S):
soapResponsePage.radio.endPoint=SOAP 끝점에서 웹 서비스 쿼리(&E):
soapResponsePage.radio.externalXML=외부 샘플 데이터 파일 사용(&D)
soapResponsePage.button.browse=찾아보기(&B)...
soapResponsePage.button.connect=지금 연결하기(&C)

# XMLTableMappingPage

# XMLColumnMappingPage

# WSConsole
wsConsole.message.error.cantRetrieveSOAPResponse=SOAP 응답을 검색할 수 없습니다. 끝점을 확인하십시오.
