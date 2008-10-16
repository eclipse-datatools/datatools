###############################################################################
# Copyright (c) 2001, 2004, 2008 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
# 
# Contributors:
#     IBM Corporation - initial API and implementation
###############################################################################
pluginName=Eclipse Data Tools Platform SQM UI Plug-in
providerName=Eclipse.org

#
## Extension Point Names
#
LABEL_SERVICE_EXTENSION_POINT_NAME=labelService
ELEMENT_ID_EXTENSION_POINT_NAME=elementID
MARKER_NAVIGATION_PROVIDER_EXTENSION_POINT_NAME=markerNavigationProvider
DATA_SOURCE_EXPLORER_SORTER=sorterValidatorProvider
FILTER_INFORMATION_EXTENSION_POINT_NAME=filterInformation

#
## Model Explorer Selection
#
datatools.core.ui.modelExplorerSelection = 모델 탐색기 선택

#
## Preferences for Label Decoration
#
datatools.core.ui.preferences.labeldecorators = 레이블 장식

#
## Preferences
#
datatools.core.ui.preferences.data = SQL 개발
datatools.core.ui.preferences.output = 출력

#
## Views
#
datatools.core.ui.modelElementProperties = 데이터 모델 요소 속성
datatools.core.ui.views.interactiveSQL = SQL 표시

#
## Model Explorer
#
datatools.core.ui.contentProviders = 물리적 모델 콘텐츠 공급자
datatools.core.ui.parserProviders = 물리적 모델 파서 공급자
datatools.core.ui.iconProviders = 물리적 모델 아이콘 공급자

#
## Nature 
#
datatools.core.ui.projectNature = 데이터베이스 프로젝트 데이터 특성

#
## Wizards
# 
datatools.core.ui.category.project = 데이터베이스
datatools.core.ui.category.model = 데이터베이스 모델
datatools.core.ui.project.basic = 데이터 디자인 프로젝트
datatools.core.ui.project.description = 데이터 디자인 프로젝트 생성
datatools.core.ui.model.basic = 물리적 데이터 모델

#
## Preferences
#
datatools.core.ui.preferences.modeler = 데이터
datatools.core.ui.preferences.default = 데이터베이스 기본값

#
## Popup && Actions
#
datatools.core.ui.actions.close = 닫기
datatools.core.ui.actions.add = SQL 추가
datatools.core.ui.actions.addMQT = MQT
datatools.core.ui.actions.addSchema = 스키마
datatools.core.ui.actions.addTable = 테이블
datatools.core.ui.actions.addView = 뷰
datatools.core.ui.actions.addColumn = 열
datatools.core.ui.actions.delete = 삭제
datatools.core.ui.actions.addTrigger = 트리거
datatools.core.ui.globalActionHandlerProviders = 데이터 전역 작업
datatools.core.ui.actions.selectSupplier = UNIQUE 제약 조건 테이블로 이동
datatools.core.ui.actions.fe=DDL 생성...
datatools.core.ui.actions.re=DDL로부터 리버스 엔지니어링...
datatools.core.ui.actions.dataModeler = 데이터 모델러
datatools.core.ui.actions.addDomain = 도메인
datatools.core.ui.actions.addSequence = 시퀀스
datatools.core.ui.actions.addStoredProcedure = 저장된 프로시저
datatools.core.ui.actions.addJavaStoredProcedure = Java 저장된 프로시저
datatools.core.ui.actions.generateForeignKeyIndex = 인덱스
datatools.core.ui.actions.generatePrimaryKeyIndex = 인덱스
datatools.core.ui.actions.addDatabase = 데이터베이스
datatools.core.ui.actions.addTableIndex = 인덱스
datatools.core.ui.actions.addTableCheckConstraint = CHECK 제약 조건
datatools.core.ui.actions.addUniqueConstraint = UNIQUE 제약 조건
datatools.core.ui.actions.addAlias = 별칭
datatools.core.ui.actions.addDistinctUDT = 고유 사용자 정의 형식
datatools.core.ui.actions.addStructuredUDT = 구조적 사용자 정의 형식
datatools.core.ui.actions.addMQTIndex = 인덱스
datatools.core.ui.actions.addMQTTrigger = 트리거
#
## Editors
#
datatools.core.ui.model.editor = SQL 모델 편집기

#
## Decorators
#
datatools.core.ui.modelexplorer.modelExplorerColumnDecoration = 데이터 도구- 열 데이터 형식
datatools.core.ui.modelexplorer.modelExplorerForeignKeyDecoration = 데이터 도구 - 외래 키 또는 기본 키 표시
datatools.core.ui.modelexplorer.DependencyDecoration = 데이터 도구 - 종속 장식
datatools.core.ui.modelexplorer.bookmarkDecoration  = 데이터 도구 - 책갈피 표시기
datatools.core.ui.modelexplorer.filterDecoration = 데이터 도구 - 서버 탐색기 필터 노드 표시기
datatools.core.ui.modelexplorer.indextriggerDecoration = 데이터 도구 - 인덱스 및 트리거 장식

#
## Model Explorer Sorter Provider
#
datatools.core.ui.modelexplorer.sorterProviders = 모델 탐색기 정렬 공급자
datatools.core.ui.modelexplorer.menu.storageOrderSorter = 저장소 순서
datatools.core.ui.modelexplorer.menu.name = 이름
datatools.core.ui.modelexplorer.menu.type = 형식
datatools.core.ui.modelexplorer.menu.stereotype = 스테레오타입
datatools.core.ui.modelexplorer.menu.visibility = 표시 유형

#
## Model Explorer DnD
#
datatools.core.ui.modelexplorer.dragDrop = 끌어서 놓기

#
## Marker
#
datatools.core.ui.modelexplorer.marker.dangling = 현수 참조


#
## Type for Label Service 
#
database = 데이터베이스
schema = 스키마
baseTable = 테이블
column = 열
domain = 도메인
udt = 사용자 정의 형식
index = 인덱스
procedure = 저장된 프로시저
view = 뷰
parameter = Parameter
trigger = 트리거
sequence = 시퀀스
source= 원본
attributeDefinition = 특성 정의
function = 사용자 정의 함수
dependency = 종속 관계
primaryKey = 기본 키
foreignKey = 외래 키
folder = 논리 폴더
CheckConstraint = CHECK 제약 조건
UniqueConstraint = UNIQUE 제약 조건
existingConnection = 연결 폴더
connectedConnection = 실시간 연결
disconnectedConnection = 연결 해제
cachedConnection = 캐시된 연결
catalog = 카탈로그
user = 사용자
group = 그룹
role = 역할
authorization = 인증 ID

DEFAULT_SCHEMA_FILTER_PROPERTY_PAGE_NAME=기본값 스키마 필터
DEFAULT_TABLE_FILTER_PROPERTY_PAGE_NAME=기본값 테이블 필터
DEFAULT_STORED_PROCEDURE_FILTER_PROPERTY_PAGE_NAME=기본값 저장된 프로시저 필터
FILTER_SETTINGS_PROPERTY_PAGE_NAME=필터 설정
