###############################################################################
# Copyright (c) 2004, 2008 Actuate Corporation.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License 2.0
# which accompanies this distribution, and is available at
# https://www.eclipse.org/legal/epl-2.0/
# 
# Contributors:
#  Actuate Corporation - initial API and implementation
#  
###############################################################################
# wizard dialog messages
wizard.title.selectFolder=XML 데이터 원본
wizard.defaultMessage.selectFolder=XML 파일과 스키마 정보의 URL을 정의합니다.
wizard.title.newDataSet=새 데이터 집합
wizard.title.selectXmlFile=미리 볼 XML 데이터 선택
wizard.defaultMessage.selectFile=이 세션에서 데이터 미리 보기에 사용할 예제 XML 소스를 선택합니다.
wizard.title.xPathChoose=테이블 매핑 정의
wizard.defaultMessage.selectXPath=XML 구조에서 요소를 선택하여 데이터 집합 행 매핑을 정의합니다.\nXML 문서에서 선택한 XML 요소의 각 항목이 데이터 집합 행에 매핑됩니다.
wizard.title.selectColumn=열 매핑 정의
wizard.title.defineColumnMapping=XML 구조에서 항목을 선택하고 추가하여 데이터 집합 열을 생성합니다.\n추가한 각 XML 요소 또는 특성이 데이터 집합 열에 매핑됩니다. 

#preference
designer.preview.preference.numberOfElementPassedToGetSchema=XML 스키마를 구문 분석할 데이터 요소 개수:
designer.preview.preference.numberOfElementPassedToGetSchema.errormessage=0과 {0} 사이에 있는 숫자를 입력하십시오.
designer.preview.preference.columnMappingPage.group.title=열 매핑

#XML data source folder selection wizard page
XMLSelectionWizardPage.message.error.emptyXMLSource=XML 소스 URL을 지정하거나 XML 파일을 찾아보십시오.

#row mapping page messages
xPathChoosePage.messages.elementSelection.label=XML 요소 선택
xPathChoosePage.messages.elementSelection.item.absolutePath=고정된 절대 경로에서 이름이 "{0}"인 XML 요소(&X)\n
xPathChoosePage.messages.elementSelection.item.anyLocation=모든 위치에서 이름이 "{0}"인 XML 요소(&E)\n
xPathChoosePage.messages.elementSelection.disable.absolutePath=고정된 절대 경로의 XML 요소(&X)\n 
xPathChoosePage.messages.elementSelection.disable.anyLocation=모든 위치의 XML 요소(&E)\n 
xPathChoosePage.messages.elementSelection.item.custom=사용자 지정 XPath 식(&U)
xPathChoosePage.messages.xPathExpression=XPath 식(&X)
xPathChoosePage.messages.xmlStructure=XML 구조
xPathChoosePage.messages.xmlColumnMapping=열 매핑
xPathChoosePage.messages.xpathChange=변경된 테이블 매핑이 열 매핑과 호환되는지 확인합니다(해당하는 경우).   
xPathChoosePage.FileNameIcon=파일 이름 아이콘
xPathChoosePage.XMLElementIcon=XML 요소 아이콘
xPathChoosePage.ColumnIcon=열 아이콘
xPathChoosePage.messages.btnAdd.tooltip=선택한 노드를 XPath 식으로 사용
xPathChoosePage.messages.noURLOfSourceOrSchema=XML 소스 또는 XML 스키마의 URL을 입력하지 않았습니다.

#sample xml data file page messages
xmlDataSetSelectionPage.messages.lineofdata=줄 미리보기(&P)
xmlDataSetSelectionPage.warning.errorReadXMLFile=파일[{0}]에서 XML 데이터를 읽는 도중 오류가 발생했습니다\n{1}. 계속하시겠습니까?

#column mapping page messages
button.refresh=새로 고침
menu.menuItem.removeAll=모두 제거
menu.menuItem.remove=제거
menu.button.add=추가(&A)...
menu.button.edit=편집(&E)...
menu.button.remove=제거(&R)
menu.button.up=위로(&U)
menu.button.down=아래로(&D)
menu.button.preview=샘플 데이터 표시(&S)
lable.selectXmlFile=XML 소스의 URL을 입력하거나 소스 데이터가 포함된 파일을 찾습니다(&X).
label.selectEncoding=위에 지정된 XML 원본 및 스키마의 인코딩 선택(&E):
lable.selectXmlSchmaFile=XML 스키마의 URL을 입력하거나 스키마가 포함된 파일을\n 찾습니다. 사용 가능한 스키마가 없는 경우 아무 것도 입력하지 않습니다(&M).
label.preview=소스 데이터 처음
label.useXMLFileFromDataSource=데이터 원본에 정의된 XML 파일 사용(&U).
RowMappingDialog.title=XPath 식을 선택하거나 편집합니다.
RowMappingDialog.info=선택한 노드에 대해 미리 정의된 XPath 식 중 하나를 선택하거나\n 다른 식을 입력합니다.
ColumnMappingPage.AddSingleButton.tooltip=선택한 노드를 열 매핑으로 사용
ColumnMappingPage.AddAllButton.tooltip=선택한 노드의 모든 하위 열을 열 매핑으로 사용
ColumnMappingTable.upButton.tooltip=열 매핑을 위로 이동
ColumnMappingTable.downButton.tooltip=열 매핑을 아래로 이동
ColumnMappingTable.removeButton.tooltip=열 매핑 제거
ColumnMappingTable.editButton.tooltip=열 매핑 편집
ColumnMappingTable.previewButton.tooltip=샘플 XML 데이터 미리 보기 
ColumnMappingDialog.group.message=XPath 선택
ColumnMappingDialog.info.columnName=열 이름(&C):
ColumnMappingDialog.info.xPath=XPath 식(&X):
ColumnMappingDialog.info.dataType=데이터 형식(&D):
ColumnMappingDialog.dialog.info=현재 열에 대해 미리 정의된 XPath 중 하나를 선택하거나 다른 XPath 식을 입력하십시오. 
ColumnMappingDialog.prompt.maxRow=원본 데이터가 500줄 이상입니다. 이 페이지에는 처음 500줄만 표시됩니다.
ColumnMappingDialog.MenuItem.CreateParameter=매개 변수 생성
ColumnMappingDialog.MenuItem.DeleteParameter=매개 변수 삭제
XMLDataPreviewDialog.title=샘플 XML 데이터

dataset.editor.columnName=이름
dataset.editor.xpathexpression=XPath
dataset.editor.datatype=형식
dataset.editor.rowelement=행 요소
dataset.error.populateXMLTree=XML 데이터 소스를 검색할 수 없습니다. XML 데이터 소스 파일이 잘못되었거나 존재하지 않습니다.
dataset.error.CannotPopulateXMLTree=XML 데이터 소스를 검색할 수 없습니다.

error.label=오류
error.selectFolder=선택한 파일 URL이 잘못되었거나 파일이 없습니다.
error.emptyPath=파일 URL이 비어 있습니다.
error.xpath.canNotContainAttribute=XPath 식이 XML 특성으로 매핑할 수 없습니다.
error.xpath.getPathList=선택한 XML 트리 항목별 XPath 목록을 가져오는 도중 오류가 발생했습니다.
error.dataset.Error.MissingFileLocation=XML 파일 위치를 지정하십시오.
error.dataset.maxRowNumberError=최대 행은 숫자여야 합니다.
error.columnMapping.createPage=열 매핑 페이지에 오류가 발생했습니다. 
error.columnMapping.columnElement.create=열 매핑 요소를 매핑하는 도중 오류가 발생했습니다.
error.columnMapping.columnElement.edit=매핑 열 요소를 편집하는 도중 오류가 발생했습니다.
error.columnMapping.ATreeNode.getChildren=선택한 XML 요소의 하위 노드를 가져오는 도중 오류가 발생했습니다.
error.columnMapping.SelectedTreeItem.notNull=열 매핑 요소를 만들기 위해 선택한 트리 항목이 없습니다. 하나 이상의 항목을 선택하십시오.
error.columnMapping.noMappingColumn=적어도 하나의 열을 매핑하십시오.
error.columnMapping.tableMappingXPathNotExist=테이블 매핑 XPath가 없습니다.
error.file.null=URL이 모두 비어 있습니다. 데이터 원본을 선택하지 않은 것입니다. 계속하시겠습니까?
error.columnMapping.sameColumnName=열 이름 [{0}]이(가) 이미 있습니다. 다른 이름을 사용하십시오.
error.invalidXpath=XPath 식이 잘못되었습니다.
error.invalidSource=xml 소스 파일을 찾을 수 없습니다.

#data types for column mapping
datatypes.dateTime=날짜 시간
datatypes.decimal=십진수
datatypes.float=실수
datatypes.integer=정수
datatypes.string=문자열
datatypes.date=날짜
datatypes.time=시간
datatypes.boolean=부울

#column name in column mapping table
columnName.name=이름
columnName.xPathName=XPath
columnName.typeName=형식

dataset.new=새 데이터 집합
file.choose=찾아보기(&O)...
schema.choose=찾아보기(&S)...
title.warning=경고
FileSelectionButton.menuItem.absolutePath=절대 경로(&A)
FileSelectionButton.menuItem.relativePath=상대 경로(&R)
SelectRelativeFileDialog.Title=파일 선택

datasource.propertyPage.tooltip=XML 데이터 원본
wizard.autoEncoding=자동
