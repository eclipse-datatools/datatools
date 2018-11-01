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
webServiceSelectionPage.message.default=웹 서비스 데이터 원본
webServiceSelectionPage.message.error=SOAP 끝점 URL을 지정하거나 WSDL 설명자를 선택하십시오.
webServiceSelectionPage.group.wsdl=WSDL 설명자
webServiceSelectionPage.group.endPoint=SOAP 끝점(&S)
webServiceSelectionPage.group.customClass=사용자 지정 드라이버 클래스(&C)
webServiceSelectionPage.group.classPath=드라이버 클래스 경로(&D)
webServiceSelectionPage.label.wsdl=웹 서비스의 WSDL 설명자가 있으면 해당 URL을 입력하거나 로컬 파일 시스템에서 위치를 찾습니다.
webServiceSelectionPage.label.wsdlURI=WSDL URL 또는 위치(&W):
webServiceSelectionPage.label.endPoint=SOAP 끝점 URL을 지정합니다. WSDL에 정의된 끝점을 사용하는 경우 또는 끝점 URL이 필요 없는\n사용자 지정 연결 클래스를 사용하는 경우 이 값을 생략해도 됩니다.
webServiceSelectionPage.label.customClass=웹 서비스 연결 및 쿼리가 사용자 지정 Java 클래스를 통해 이루어진 경우 여기에 정규화된 클래스 이름을 입력하십시오.
webServiceSelectionPage.label.classPath=사용자 지정 드라이버 클래스를 검색할 세미콜론(;)으로 구분된 추가 JAR 파일 및 디렉터리 목록입니다.
webServiceSelectionPage.button.browse=찾아보기(&R)...

# OperationPage
operationPage.message.default=데이터 원본의 WSDL 설명자에 정의된 작업을 선택합니다.
operationPage.message.regenerate.SOAPRequest=SOAP 요청을 다시 생성하고 변경된 작업에 따라 끝점에 다시 연결합니다.
operationPage.title.operationChanged=작업이 변경됨
operationPage.label.selectOpearation=선택된 작업(&S)
operationPage.label.document=설명서(&D)

# SoapParametersPage
soapParametersPage.message.default=선택한 WSDL 작업의 매개 변수를 입력합니다.
soapParametersPage.button.selectAll=모두 선택(&S)
soapParametersPage.button.deselectAll=모두 선택 취소(&D)
soapParametersPage.column.paramName=이름(&A)
soapParametersPage.column.dataType=데이터 형식(&T):

# SoapRequestPage
soapRequestPage.message.defaultTitle=이 데이터 집합의 SOAP 요청 템플릿을 편집합니다. 
soapRequestPage.message.regenerateTemplate=선택한 SOAP 작업에 근거한 쿼리 텍스트 템플릿이 다시 생성될 것입니다. 이전에 해당 텍스트에 기록한 변경 사항이 모두 사라질 것입니다.\n\n계속하시겠습니까?
soapRequestPage.label.prompt=사용자 지정 연결 클래스에 쿼리 텍스트가 필요하지 않을 경우 입력란을 비워두셔도 됩니다.
soapRequestPage.title.regenerateTemplate=템플릿 다시 생성
soapRequestPage.button.regenerateTemplate=템플릿 다시 생성(&R)
soapRequestPage.button.insertParameter=매개 변수 편집(&E)...
soapRequestPage.button.clear=지우기(&C)
soapRequestPage.paramEditDialog.title=매개 변수 속성 편집

# ParameterInputDialog
parameterInputDialog.column.name=이름(&N)
parameterInputDialog.column.type=데이터 형식(&T):
parameterInputDialog.column.defaultValue=기본값(&V):
parameterInputDialog.button.edit=편집(&E)...

# SoapResponsePage
soapResponsePage.message.default=SOAP 응답을 편집합니다.
soapResponsePage.group.schema=SOAP 응답 스키마를 선택합니다.
soapResponsePage.group.xml=샘플 SOAP 응답 메시지를 선택합니다.
soapResponsePage.radio.defaultSchema=데이터 원본 WSDL에 정의된 작업 응답 스키마를 사용합니다(&O).
soapResponsePage.radio.reponseSchema=응답 스키마 사용(&R)
soapResponsePage.radio.externalSchema=외부 XML 스키마 파일 사용(&X):
soapResponsePage.radio.endPoint=SOAP 끝점에서 웹 서비스 쿼리(&E):
soapResponsePage.radio.externalXML=외부 샘플 데이터 파일 사용(&D)
soapResponsePage.button.browse1=찾아보기(&W)...
soapResponsePage.button.browse2=찾아보기(&S)...
soapResponsePage.button.connect=지금 연결하기(&C)

# XMLTableMappingPage

# XMLColumnMappingPage

# WSConsole
wsConsole.message.error.cantRetrieveSOAPResponse=SOAP 응답을 검색할 수 없습니다. 네트워크나 SOAP 응답 설정을 확인하십시오.
