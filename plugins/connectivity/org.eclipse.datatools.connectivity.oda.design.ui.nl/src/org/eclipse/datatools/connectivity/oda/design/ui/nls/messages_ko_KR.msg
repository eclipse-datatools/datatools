#
#************************************************************************
# Copyright (c) 2006, 2013 Actuate Corporation.
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
adaptableDataSourceProfile_unableAccessProfile=({0})의 연결 프로필에 액세스할 수 없습니다. 데이터 원본 디자인의 로컬 속성을 대신 사용하십시오.\n 예외 발생: {1}
common_notInDesignSession=현재 디자인 세션에 없습니다.
common_nullArgument=인수는 널(null)일 수 없습니다.
common_missingDataSourceDesign=OdaDesignSession 인수에서 데이터 소스 디자인이 누락되었습니다.
common_createClassFailed=클래스({0})를 찾거나 작성할 수 없습니다.
extension_mustInheritFromODAPage=올바르지 않은 마법사 페이지({0}) 구현입니다. {1}에서 ODA 사용자 정의 마법사 페이지를 확장해야 합니다.
extension_mustInheritFromODAWizard=올바르지 않은 마법사({0}) 구현입니다. ODA 사용자 정의 마법사를 사용하거나 {1}에서 확장해야 합니다.
extension_missingManifestElement=ODA 디자인 UI 확장점({0})의 구현 누락
extension_missingPropertyPage=ODA 디자인 UI 확장에서 특성 페이지 구현이 누락되었습니다.
wizard_dataSource_defaultTitle=새 데이터 소스
wizard_missingDataSourceId=연결 프로파일 속성에서 ODA 데이터 소스 ID가 누락되었습니다.
wizard_invalidManifest=oda.design.ui.dataSource 확장의 플러그인 Manifest 컨텐츠가 올바르지 않습니다.
page_defaultDataSourceTitle=사용자 정의 데이터 소스 특성
designSession_alreadyHasLinkedProfile=데이터 원본({0})이 연결 프로필에 있는 해당 속성을 이미 외부화했으므로  데이터 원본을 다시 내보낼 수 없습니다.
designSession_flushFailed=현재 데이터 집합 디자인 세션을 플러시할 수 없습니다.
designSession_invalidArgument=디자인 세션 메소드에 올바르지 않은 인수가 지정되었습니다.
designSession_invalidEditApiCall=잘못된 디자인 세션 API에는 디자인 편집이 필요합니다.
designSession_invalidNewDesignApiCall=잘못된 디자인 세션 API에는 새로운 디자인 생성이 필요합니다.
designSession_invalidProfileName=지정한 연결 프로필 저장소에서 연결 프로필({0})을 찾을 수 없습니다.
designSession_invalidProfileNameOrFilePath=데이터 원본 디자인에서 지정한 연결 프로필({0}) 또는 연결 프로필 저장소 파일({1})을 찾을 수 없습니다.
designSession_missingDataSetUIElement=OdaDataSourceId({0}) 및 OdaDataSetId({1})에 dataSetUI 인수가 정의되지 않았습니다.
manifest_missingAttributeValue=필수 속성({0})에서 값이 누락되었습니다.
manifest_missingDataSetElementId=dataSetElementID 인수에 값이 필요합니다.
manifest_invalidDataSetElementId=dataSet 요소 ID({0})가 올바르지 않습니다.
manifest_dataSetUi_missingPageId=pageId 값은 필수입니다.
manifest_dataSetUi_invalidPageId=pageId 값({0})이 올바르지 않습니다.
manifest_dataSetUi_missingElement=확장에는 {0} 요소가 필수입니다.
ui_defaultDataSourceTitle=데이터 소스 연결 속성
ui_replaceFilePrompt={0}이(가) 이미 있습니다. 바꾸시겠습니까?
ui_requiredFieldsMissingValue=하나 이상의 필수 필드('*'로 표시됨) 값이 누락되었습니다.
ui_errorLabel=오류
ui_errorCreatingCustomCtrls=사용자 정의 제어 작성 및 초기화 중 오류가 발생했습니다.
ui_saveAsTitle=다른 이름으로 저장
profilePage_pageTitle=연결 프로필 선택
profilePage_pageLabel=연결 프로필
profilePage_selectProfileDefaultMessage=데이터 원본을 생성할 연결 프로필 선택
profilePage_label_profileStore=연결 프로필 저장(&S):
profilePage_button_browse=찾아보기(&R)...
profilePage_button_relativepath=상대 경로(&R)
profilePage_button_absolutepath=절대 경로(&A)
profilePage_button_browse_filter=표시되는 파일 확장명(&E):
profilePage_button_browse_filter_tooltiptext=쉼표로 구분된 파일 확장명을 하나 이상 지정하여 아래 표시된 파일을 필터링하십시오.\n예: txt, 프로필 등
profilePage_button_new=새로 만들기(&W)...
profilePage_button_browse_tooltip=연결 프로필 저장소 파일을 찾아봅니다.\n 상대 경로: 호스트 리소스를 기반으로 한 상대 경로 \n절대 경로: 파일 시스템의 절대 경로
profilePage_resourcebaseuri_failure=응용 프로그램 리소스 기본 URI를 가져오지 못했습니다!
profilePage_browse_noselection_error=연결 프로필 저장소 파일 이름을 지정하십시오. 
profilePage_odaTreeName=ODA 데이터 원본
profilePage_checkboxLabel_useDefaultName=기본 데이터 원본 이름 사용(&U)
profilePage_label_dataSourceName=데이터 원본 이름(&D):
profilePage_error_emptyName=데이터 원본 이름은 비어 있지 않아야 함
profilePage_checkboxTooltip_maintainLink=데이터 원본에서 연결 프로필 저장의 외부화된 속성 값을 항상 사용하십시오.
profilePage_checkboxLabel_maintainLink=연결 프로필 저장의 외부화된 속성(&E) 사용
profilePage_error_invalidName=데이터 원본 이름이 잘못되었습니다.
profilePage_error_invalidProfileStorePath=연결 프로필 저장소에 대한 잘못된 경로
profilePage_deprecatedProfileDisplayName={0}({1}(으)로 교체)
profileStoreCreationDialog_button_new=새로 만들기(&W)...
profileStoreCreationDialog_title=연결 프로필 저장소 생성
profileStoreCreationDialog_grouptext=저장소에 포함할 연결 프로필 선택(&P):
profileStoreCreationDialog_newbutton_tooltiptext=새 연결 프로필을 생성합니다.
profileStoreCreationDialog_fileext_error=연결 프로필 저장소 파일은 파일 확장명이 있어야 합니다.\n"확인"을 눌러 기본 파일 확장명 ".{0}"를 파일 이름에 추가하십시오.
dbProfilePage_defaultPageMessage=JDBC 드라이버 및 연결 상세 정보 확인
dbProfilePage_editPageMessage=JDBC 드라이버 및 연결 상세 정보 편집
dbProfilePage_invalidDataSource=데이터 원본이 잘못되었습니다.  연결 프로필 참조를 업데이트하거나 새 데이터 원본을 생성하십시오.
dbProfilePage_notInDesignSession=ODA 디자인 세션 외부에서 속성 페이지를 열 수 없습니다.
dbProfilePage_noCustomPage=데이터베이스 드라이버별 속성 페이지를 찾아 열 수 없습니다.
preferencesPage_openDataAccessDescription=이 노드에 포함된 페이지를 사용하여 각 개방형 데이터 액세스(ODA)에\n대한 기본 설정을 지정하십시오.
