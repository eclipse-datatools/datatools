# NLS_MESSAGEFORMAT_VAR          needed to handle quotes correctly
#
# Copyright (c) 2002, 2007. All Rights Reserved IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which is available at
# http://www.eclipse.org/legal/epl-v10.html
#  
# Contributors:
#     IBM Corporation - initial API and implementation
#
# Properties file for component:  SQLQ - SQL builder

! Please do not translate following strings  in this file (appears exactly, case sensitive) :
! WITH
! SQL
! SELECT
! FULLSELECT
! UNION
! CASE
! CAST

!
! views/update/RemoveUpdateColumnAction.java
!
_UI_ACTION_REMOVE_UPDATE_COLUMN = 열 제거(&C)

!
! views/update/UpdateTreeElement.java
!
_UI_COLUMN_UPDATE_GROUP = 그룹 업데이트

!
! views/update/UpdateTreeViewer.java
!
_UI_COLUMN_UPDATE_COLUMN     = 열 업데이트
_UI_COLUMN_UPDATE_EXPRESSION = Expression

!
!views/update/UpdateViewer.java
!
_UI_WORKBOOK_SET    = 설정
_UI_WORKBOOK_WHERE  = 여기서


!
! Actions
!
!! NOTE TO TRANSLATOR: Do not translate "WITH" in the following line
_UI_ACTION_ADD_TABLE          = 테이블 추가(&A)...
_UI_ACTION_ADD_TABLE_REPLACE  = 테이블 바꾸기(&E)...
!! NOTE TO TRANSLATOR: Do not translate "FULLSELECT" in the following line
_UI_ACTION_ADD_FULLSELECT     = FULLSELECT 문 추가(&F)
_UI_ACTION_REMOVE_TABLE       = 테이블 제거(&M)...

!actions/ChangeStatementTypeAction
_UI_ACTION_CHANGE_STATEMENT_TYPE		= 문 형식 변경(&C)...

!actions/OmitCurrentSchemaAction
_UI_ACTION_OMIT_CURRENT_SCHEMA		= 현재 스키마 생략(&O)...

!actions/AddValueRowAction
_UI_ACTION_ADD_VALUE_ROW=값 행 추가(&R)

!actions/AddValuesAction.java
_UI_ACTION_ADD_VALUES=값 추가(&V)

!actions/AddSubSelectAction.java
!! NOTE TO TRANSLATOR: Do not translate "SELECT" in the following line
_UI_ACTION_ADD_SELECT=SELECT 문 추가(&S)

!actions/CreateWithTableAction.java
_UI_ACTION_ADD_COMMON_TABLE=공통 테이블 식 추가(&A)(WITH)

!actions/DeleteStatementAction.java
_UI_ACTION_DELETE_STATEMENT=삭제(&D)

!expressionbuilder/actions/DeleteExpressionAction.java
_UI_ACTION_DELETE_EXPRESSION=식 제거(&R)

!expressionbuilder/actions/DeleteCaseClauseAction.java
_UI_ACTION_DELETE_CLAUSE=절 제거(&R)

!expressionbuilder/actions/DeleteFunctionParameterAction.java
_UI_ACTION_DELETE_PARAMETER=매개 변수 제거(&R)

!expressionbuilder/case/CastExpressionWizard.java
!! NOTE TO TRANSLATOR: Do not translate "CAST" in the following line
_UI_WIZARD_CAST_EXPRESSION_TITLE=CAST 식 빌더

!expressionbuilder/caseexpr/CaseSimplePage.java
_UI_COMBO_ADDFUNCTION=함수 식 빌드...

!! NOTE TO TRANSLATOR: Do not translate "CASE" in the following line
_UI_COMBO_ADDCASE=CASE 식 빌드...
!! NOTE TO TRANSLATOR: Do not translate "CAST" in the following line
_UI_COMBO_ADDCAST=CAST 식 빌드...
_UI_COMBO_ADDCONSTANT=빌드 상수 식...
_UI_COMBO_SELECTSUBQUERY=하위 쿼리 선택...
_UI_COMBO_ADDEXPRBYOPERATOR=연산자로 식 빌드...
_UI_WIZARD_CASE_SIMPLE_TITLE=단순 CASE 식
_UI_WIZARD_CASE_SIMPLE_HEADING=단순 CASE 식을 빌드하십시오.
!! NOTE TO TRANSLATOR: Do not translate "WHEN-THEN" and "ELSE" in the following line
_UI_LABEL_BUILD_WHEN_CLAUSE=WHEN-THEN 절을 빌드하고(&B), ELSE 절 또는 둘 다 추가하십시오.
!! NOTE TO TRANSLATOR: Do not translate "ELSE" in the following line
_UI_CHECKBOX_ADD_ELSE=ELSE 절 추가(&E)
!! NOTE TO TRANSLATOR: Do not translate "WHEN" in the following line
_UI_BUTTON_ADD_WHEN=WHEN 절 추가(&W)
_UI_LABEL_EXPRESSION_PREVIEW=CASE 식 미리 보기:

!expressionbuilder/function/FunctionBuilderPage.java:

!expressionbuilder/caseexpr/CaseSearchTable.java
_UI_COLUMN_CASE_SEARCH_EXPRESSION=Expression
_UI_COLUMN_CASE_SEARCH_OPERATOR=연산자
!! NOTE TO TRANSLATOR: Do not translate "WHEN" in the following line
_UI_ACTION_DELETE_WHEN_CLAUSE_SEARCH=WHEN 절 제거(&R)
_UI_ACTION_DELETE_SEARCH_CONDITION=검색 조건 제거(&R)
!! NOTE TO TRANSLATOR: Do not translate "ELSE" in the following line
_UI_ACTION_DELETE_ELSE_EXPRESSION_SEARCH=ELSE 문 제거(&R)

!expressionbuilder/caseexpr/CaseSearchPage.java
_UI_WIZARD_CASE_SEARCH_HEADING=검색한 CASE 식
_UI_WIZARD_CASE_SEARCH_EXPR=검색한 CASE 식을 빌드하십시오.

!expressionbuilder/caseexpr/CaseSimpleTable.java
_UI_COLUMN_CASE_EXPRESSION=Expression
_UI_COLUMN_RESULT_EXPRESSION=결과 식
!! NOTE TO TRANSLATOR: Do not translate "WHEN" in the following line
_UI_ACTION_DELETE_WHEN_CLAUSE=WHEN 절 제거(&R)
!! NOTE TO TRANSLATOR: Do not translate "ELSE" in the following line
_UI_ACTION_DELETE_ELSE_EXPRESSION=ELSE 문 제거(&R)

!expressionbuilder/...Signatures...
_UI_FCN_ALL=모두
_UI_FCN_COLUMN=열
_UI_FCN_SPECIAL_REG=특별 등록
_UI_FCN_SCALAR=스칼라
_UI_FCN_CONVERSION=변환
_UI_FCN_SECURITY=보안
_UI_FCN_DATE_TIME=날짜와 시간
_UI_FCN_DATE=날짜
_UI_FCN_DB2=DB2
_UI_FCN_LOGICAL=논리
_UI_FCN_MATH=Math
_UI_FCN_BIT=비트
_UI_FCN_NUMBER=번호
_UI_FCN_CHARACTER=문자
_UI_FCN_SUMMARY=요약
_UI_FCN_TEXT=텍스트
_UI_FCN_SYSTEM=시스템
_UI_FCN_STRING=문자열
_UI_FCN_AGGREGATE=집계
_UI_FCN_MISCELLANEOUS_SINGLE_ROW=기타 단일 행
_UI_FCN_MISCELLANEOUS=기타
_UI_FCN_OBJECT_REFERENCE=개체 참조
_UI_FCN_CONTROL_FLOW=제어 흐름
_UI_FCN_TEXT_EXTENDERS=텍스트 Extender
_UI_FCN_AIV_EXTENDERS=오디오/이미지/비디오 Extenders
_UI_FCN_XMLFUNCTIONS=XML
_UI_FCN_NOT_SUPPORTED=지원되지 않음
_UI_FCN_UDF=사용자 정의 함수(UDF)
_UI_FCN_DATALINK=데이터링크
_UI_FCN_MQ_EXTENDER=메시지 큐(MQ)

!expressionbuilder/subquery/SubQueryWizard.java
_UI_WINDOW_TITLE_CHOICES=하위 쿼리 선택

!expressionbuilder/function/FunctionBuilderPage.java
_UI_WIZARD_FUNCTION_BUILDER_HEADING=함수 빌더
_UI_WIZARD_FUNCTION_BUILDER_EXPL=함수 식을 빌드하려면 이 페이지를 사용하십시오.
_UI_WIZARD_FUNCTION_BUILDER_INSTR_CAT=1. 함수 범주 선택(&C):
_UI_WIZARD_FUNCTION_BUILDER_INSTR_FCN=2. 함수 선택(&U):
_UI_WIZARD_FUNCTION_BUILDER_INSTR_SIG=3. 함수 시그너처 선택(&S):
_UI_WIZARD_FUNCTION_BUILDER_INSTR_EXPR=4. 인수 식 선택(&E):
_UI_WIZARD_FUNCTION_BUILDER_INSTR_PRE=5. 함수 식 미리 보기:

!expressionbuilder/caseexpr/CaseExpressionDialog.java

!! NOTE TO TRANSLATOR: Do not translate "CASE" in the following 2 lines
_UI_WIZARD_CASE_OPTION_HEADING=CASE 옵션
_UI_WIZARD_CASE_OPTION_EXPL=빌드하려는 CASE 식의 형식을 선택하십시오.

_UI_GROUP_CASE_TYPE=CASE 형식
!! NOTE TO TRANSLATOR: Do not translate "WHEN" in the following line
_UI_RADIO_SEARCHED_WHEN=검색한-WHEN-절(&S)

!! NOTE TO TRANSLATOR: Translate only "for example," in the following line
_UI_TOOLTIP_SEARCHED_WHEN = 예: CASE WHEN i>j THEN k END

!! NOTE TO TRANSLATOR: Do not translate "WHEN" in the following line
_UI_RADIO_SIMPLE_WHEN=단순-WHEN-절(&I)

!! NOTE TO TRANSLATOR: Translate only "for example," in the following line
_UI_TOOLTIP_SIMPLE_WHEN = 예: CASE i WHEN 8 THEN j END

!expressionbuilder/caseexpr/CaseExpressionDialog.java
!! NOTE TO TRANSLATOR: Do not translate "CASE" in the following line
_UI_WIZARD_CASE_EXPRESSION_TITLE=CASE 식 빌더

!expressionbuilder/constant/ConstantExpressionWizard.java
!! NOTE TO TRANSLATOR: Do not translate the following line
_UI_NUMERIC_CONSTANT=0123456789-+E.

!expressionbuilder/case/CaseBuilderPage.java
!! NOTE TO TRANSLATOR: Do not translate "CAST" where it occurs in the following 6 lines
_UI_WIZARD_CAST_BUILDER_TITLE=CAST 빌더
_UI_WIZARD_CAST_BUILDER_HEADING=CAST 식을 빌드하려면 이 페이지를 사용하십시오.
!! NOTE TO TRANSLATOR: Do not translate "SCOPE" in the following line
_UI_LABEL_PREVIEW_CAST_EXPRESSION=CAST 식 미리 보기:
_UI_LABEL_DATATYPE_LENGTH=데이터 형식 길이:

!expressionbuilder/constant/ConstantExpressionDialog.java
_UI_WIZARD_CONSTANT_TITLE=상수 식 빌더

!expressionbuilder/constant/NumericConstantBuilderPage.java
_UI_WIZARD_NUMERIC_CONSTANT_TITLE=숫자 상수 빌더
_UI_WIZARD_NUMERIC_CONSTANT_HEADER=숫자 상수 빌더
_UI_WIZARD_NUMERIC_CONSTANT_EXPL=이 페이지는 숫자 상수 빌더를 빌드하도록 도와줍니다.
_UI_BUTTON_CLEAR=지우기(&C)

!! NOTE TO TRANSLATOR: Do not translate the following 2 lines
_UI_BUTTON_FLOAT_TO_EXP=&F <-> E
_UI_BUTTON_PLUS_MINUS=+&/-

!expressionbuilder/constant/ConstantOptions.java
_UI_WIZARD_CONSTANT_OPTIONS_TITLE=상수 옵션
_UI_WIZARD_CONSTANT_OPTIONS_HEADING=상수 옵션
_UI_WIZARD_CONSTANT_OPTIONS_EXPL=빌드하려는 상수 식의 형식을 선택하십시오.
_UI_GROUP_CONSTANT_TYPE=상수 형식
_UI_RADIO_NUMERIC=숫자 상수(&U): 정수, 부동 소수점, 또는 십진수
_UI_RADIO_STRING=문자열 상수(&S): 문자 또는 그래픽 문자열, 16진수, 또는 호스트 변수 이름

!expressionbuilder/constant/StringConstantBuilderPage.java
_UI_WIZARD_STRING_CONSTANT_TITLE=문자열 상수 빌더
_UI_WIZARD_STRING_CONSTANT_HEADING=문자열 상수 빌더
_UI_WIZARD_STRING_CONSTANT_EXPL=이 페이지는 문자열 상수 식을 만드는 데 도움이 됩니다. 텍스트 빌드에 식을 입력하십시오.
_UI_GROUP_STRING_TYPE=문자열 상수 형식
_UI_RADIO_CHARACTER=문자열 상수(&A)
_UI_RADIO_HEX=16진수 상수(&H)
_UI_RADIO_HOST_VARIABLE_NAME=호스트 변수 이름(&V)
_UI_RADIO_GRAPHIC=그래픽 문자열 상수(&G)
_UI_LABEL_PREVIEW_CONSTANT_EXPRESSION=상수 식 미리 보기:

!expressionbuilder/function/FunctionExpressionDialog.java
_UI_WIZARD_FUNCTION_EXPRESSION_TITLE=함수 식 빌더

!ExpressionBuilder/MainExpressionPage.java
_UI_WIZARD_MAIN_EXPRESSION_TITLE=연산식 빌더
_UI_WIZARD_MAIN_EXPRESSION_HEADING=연산식 형식
_UI_WIZARD_MAIN_EXPRESSION_EXPL=빌드하려는 식의 형식을 선택하십시오.
_UI_RADIO_FUNCTION=함수(&F)

!! NOTE TO TRANSLATOR: Do not translate "CASE" in the following line
_UI_RADIO_CASE=CASE(&C) - 검색한 또는 단순

!! NOTE TO TRANSLATOR: Do not translate the following line
_UI_RADIO_CAST=CAST(&C)

_UI_RADIO_CONSTANT=Constant - numeric, string, or host variable(&O)
_UI_RADIO_SUBQUERY=Subquery(&S)
_UI_RADIO_OPERATORS=Build up expressions by operators(&U)
_UI_COLUMN_EXPRESSION=Expression
_UI_COLUMN_OPERATOR=연산자
_UI_COLUMN_PARAMETER=Parameter
_UI_COLUMN_VALUE=Value

!expressionbuilder/multiexpr/ExpressionsByOperatorsWizard.java
_UI_WIZARD_EXPRESSION_BY_OPERATOR_TITLE=연산자로 식 빌드(&U)
_UI_WIZARD_EXPRESSION_BY_OPERATOR_HEADING=연산자에 의한 식
_UI_WIZARD_EXPRESSION_BY_OPERATOR_EXPL=제공된 연산자를 사용하여 더 큰 식을 빌드하십시오.
_UI_LABEL_BUILD_LEFT_OP_RIGHT=왼쪽의 식을 빌드하고 오른쪽의 연산자를 선택하십시오:
_UI_LABEL_PREVIEW_EXPRESSION=식 미리 보기:

!expressionbuilder/subquery/SubQueryPage.java
_UI_WIZARD_SUBQUERY_HEADING=하위 쿼리
_UI_WIZARD_SUBQUERY_EXPL=하위 쿼리를 선택하십시오.
_UI_LABEL_CHOICES=사용 가능한 선택:
_UI_RADIO_EXISTING_STATEMENTS=기존 쿼리 기반 하위 쿼리 생성(&E)
_UI_RADIO_SCRATCH_SELECT=원본 하위 쿼리 생성(&S)
!! NOTE TO TRANSLATOR: Do not translate "SELECT" in the following 2 lines
_UI_LABEL_EXISTING_SELECT=SELECT 문 생성 또는 편집(&R):
_UI_LABEL_NEW_SELECT_INFO=SELECT 문을 입력하십시오(&T). 가상 테이블을 지정할 수 있습니다.

!views/CustomSashForm.java
_UI_TOOLTIP_RESTORE_PANE=창 복원
_UI_TOOLTIP_HIDE_PANE=창 숨기기


!preferences/SQLBuilderPreferencePage.java
_UI_PREFERENCES_OMIT_SCHEMA_DESC=SQL 문과 연결된 현재 스키마 생략 설정의 기본값
_UI_PREFERENCES_OMIT_SCHEMA_IN_SQL=생성된 SQL 문에서 현재 스키마 생략(&O)
_UI_PREFERENCES_OMIT_SCHEMA_SPECIFY_SCHEMA_GROUP_TITLE=현재 스키마
_UI_PREFERENCES_OMIT_SCHEMA_USE_AUID_AS_CURRENT_SCHEMA=승인 ID(&A)
_UI_PREFERENCES_OMIT_SCHEMA_SCHEMA_NAME=스키마 이름(&S):

!dialogs/ChangeStatementTypeDialog
_UI_DIALOG_CHANGE_STATEMENT_TYPE_TITLE=SQL 문 형식 변경
_UI_DIALOG_CHANGE_STATEMENT_TYPE_NOTE=SQL 문 형식을 변경하면 현재 문이 비어 있는 새 문으로 교체됩니다.
_UI_DIALOG_CHANGE_STATEMENT_TYPE_GROUP_TITLE=문 형식

!dialogs/OmitCurrentSchemaDialog
_UI_DIALOG_OMIT_SCHEMA_TITLE=현재 스키마 생략
_UI_DIALOG_OMIT_SCHEMA_IN_SQL=생성된 SQL 문에서 현재 스키마 생략(&O)
_UI_DIALOG_OMIT_SCHEMA_SPECIFY_SCHEMA_GROUP_TITLE=현재 스키마
_UI_DIALOG_OMIT_SCHEMA_USE_AUID_USER_AS_CURRENT_SCHEMA=승인 ID <{0}>(&A)
_UI_DIALOG_OMIT_SCHEMA_USE_AUID_AS_CURRENT_SCHEMA=승인 ID(&A)
_UI_DIALOG_OMIT_SCHEMA_SCHEMA_NAME=스키마 이름(&S):

!views/execute/ParameterWizards.java
_UI_WIZARD_SPECIFY_VARIABLE_VALUES_TITLE=변수 값 지정

!views/execute/ParameterWizardDialog.java
_UI_WIZARD_PARAMETER_MARKERS_TITLE=매개 변수 표지

!views/execute/ParameterMarkerPage.java
_UI_WIZARD_PARAMETER_MARKER_TITLE=변수 교체
_UI_WIZARD_PARAMETER_MARKER_HEADING=호스트 변수 값
_UI_WIZARD_PARAMETER_MARKER_EXPL=사용할 변수 값 지정

!views/execute/ParameterTableViewer.java
_UI_COLUMN_MARKER_NAME=이름
_UI_COLUMN_TYPE=형식
_UI_COLUMN_PARAMETER_VALUE=Value


!views/AddTableDialog.java
_UI_LABEL_TABLE_NAME            = 테이블 이름(&N):
_UI_DIALOG_REPLACE_TABLE_TITLE  = 테이블 교체
_UI_DIALOG_ADD_TABLE_TITLE      = 테이블 추가
_UI_LABEL_TABLE_ALIAS           = 테이블 별칭(&A):
_UI_DIALOG_TABLE_ALREADY_ADDED  = 이미 추가된 테이블 - 별칭 지정
_UI_DIALOG_ADD_VIEW_TO_SELF     = 정의 소유에 보기를 추가할 수 없습니다.


!views/GridViewer.java
_UI_COLUMN_DEFAULT_COLUMN=열

!views/insert/InsertGridViewer.java
_UI_COLUMN_INSERT_VALUE=Value

!views/criteria/CriteriaGridViewer.java
_UI_COLUMN_COLUMN=열
_UI_COLUMN_CRITERIA_OPERATOR=연산자
_UI_COLUMN_CRITERIA_VALUE=Value

!! NOTE TO TRANSLATOR: Do not translate the following line
_UI_COLUMN_CRTIERIA_AND_OR=AND/OR

!views/criteria/CriteriaValueExpression.java
_UI_ACTION_UPDATE_VALUE_EXPRESSON=값 식 업데이트(&U)
_UI_ACTION_REMOVE_CONDITION=조건 제거(&R)
_UI_MENU_EXECUTE= SQL 실행(&R)

!views/fullselect/FullSelectViewer.java
!! NOTE TO TRANSLATOR: Do not translate VALUES in the following entry, since it is a SQL keyword
_UI_LABEL_FULL_SELECT_VALUES_HELP=값 행을 추가하려면, 윤곽선 보기의 VALUES를 마우스 오른쪽 단추로 누르십시오.

!views/fullselect/ValuesRowViewer.java
_UI_COLUMN_VALUES_ROW_ITEM=값 행 항목
_UI_ACTION_REMOVE_VALUES_ROW=항목 제거(&R)

!views/fullselect/FullSelectGridViewer.java
_UI_COLUMN_STATEMENT=문
_UI_COLUMN_FULL_SELECT_OPERATOR=연산자

!views/graph/editparts/TableEditPart.java
_UI_ACTION_UPDATE_ALIAS=별칭 업데이트(&U)...
_UI_DIALOG_CHANGE_ALIAS_TITLE=테이블 별칭 변경
_UI_DIALOG_CHANGE_ALIAS_TEXT=새 별칭(&E):
_UI_ACTION_CREATE_JOIN=조인 생성(&J)...
_UI_ACTION_REMOVE_TABLE_EDIT=테이블 제거(&M)


!views/graph/GraphContextMenuProvider.java:
_UI_ACTION_REMOVE_JOIN=조인 제거(&R)

!views/graph/SourceTargetDialog.java
_UI_DIALOG_SOURCE_AND_TARGET_TITLE=조인 생성
_UI_DIALOG_SOURCE_AND_TARGET_TEXT=조인의 원본과 대상 열을 선택하십시오.
_UI_LABEL_JOIN_SRCCOL_TYPE = 원본 열 형식:
_UI_LABEL_JOIN_TGTCOL_TYPE = 대상 열 형식:

_UI_GROUP_SOURCE=원본
_UI_LABEL_TABLE=테이블(&T)(별칭)
_UI_LABEL_COLUMN=열(&C)
_UI_GROUP_TARGET=대상
_UI_LABEL_TARGET_TABLE=테이블(&A)(별칭)
_UI_LABEL_TARGET_COLUMN=열(&O)

_UI_GRAPH_PROMPT = 테이블을 추가하려면, 이 창에서 마우스 오른쪽 단추를 눌러 팝업 메뉴를 사용하십시오.
_UI_GRAPH_PARSE_FAILED = 이 SQL 구문은 잘못되었거나 현재 지원되지 않습니다. \
SQL 구문을 수동으로 입력할 수 있습니다. 일부 SQL 빌더 함수를 사용할 수 없습니다. \
이러한 함수를 사용하려면 구문을 변경합니다.\n\n최근에 변경한 내용을 취소하려면 \
SQL 메뉴에서 이전 원본으로 되돌리기를 선택하십시오.
_UI_GRAPH_NO_TABLE_FOUND = 테이블 {0}을(를) 찾을 수 없습니다. 존재하지 않거나 필터링되었을 수 있습니다. \
일부 SQL 빌더 함수를 사용할 수 없습니다. \
이러한 함수를 사용하려면 구문을 변경하고 해당 문을 저장하십시오.\n\n최근에 변경한 내용을 취소하려면 \
SQL 메뉴에서 이전 원본으로 되돌리기를 선택하십시오.
_UI_GRAPH_NO_COLUMN_FOUND = 열 {0}을(를) 찾을 수 없습니다. 일부 SQL 빌더 함수를 사용할 수 없습니다. \
이러한 함수를 사용하려면 구문을 변경하고 해당 문을 저장하십시오.\n\n최근에 변경한 내용을 취소하려면 \
SQL 메뉴에서 이전 원본으로 되돌리기를 선택하십시오.

!views/inster/InsertTypeViewer.java
_UI_GROUP_INSERT_ROWS=다음에서 행 삽입:
_UI_RADIO_VALUES=값
_UI_RADIO_INSERT_FROM_SUBQUERY=하위 쿼리
_UI_ACTION_REMOVE_COLUMN=열 제거(&R)

!views/insert/InsertSelectViewer.java
_UI_LABEL_QUERY_NAME=쿼리 이름:

!views/select/GroupByContentViewer.java
_UI_LABEL_GROUPS_TYPE=종류:
_UI_COMBOBOXITEM_GROUPING_ROOT=루트 그룹화
_UI_ACTION_REMOVE=제거(&R)
_UI_NONE=없음
_UI_SIMPLE=단순

!! NOTE TO TRANSLATOR: Do not translate following line
_UI_CUBE=CUBE

!! NOTE TO TRANSLATOR: Do not translate following line
_UI_ROLLUP=ROLLUP

_UI_GROUPING=Grouping
_UI_GROUPINGSETS=Grouping Sets

! views/select/SelectGridViewer.java:
!
_UI_COMBO_SORT_UNSORTED   = 정렬 안 함
_UI_COMBO_SORT_ASCENDING  = 오름차순
_UI_COMBO_SORT_DESCENDING = 내림차순
_UI_COMBO_SORT_DEFAULT = 기본값

!
!views/select/RemoveSelectColumnsAction.java
!
_UI_ACTION_REMOVE_SELECT_COLUMN = 열 식 제거(&C)

!
!views/select/RemoveOrderByExpressionAction.java
!
_UI_ACTION_REMOVE_ORDER_BY_EXPRESSION = Order By 식 제거(&O)

!
! views/select/SelectGridViewer.java
!
_UI_COLUMN_SELECT_ALIAS      = 별칭
_UI_COLUMN_SELECT_OUTPUT     = 출력
_UI_COLUMN_SELECT_SORT_TYPE  = 정렬화 형식
_UI_COLUMN_SELECT_SORT_ORDER = 정렬 순서

!
! views/select/SelectViewer.java
!
_UI_WORKBOOKPAGE_COLUMNS          = 열
_UI_WORKBOOKPAGE_CONDITIONS       = 조건
_UI_WORKBOOKPAGE_GROUPS           = 그룹
_UI_WORKBOOKPAGE_GROUP_CONDITIONS = 그룹 조건

!
! views/SQLBuilderActionBarContributor.java
!
_UI_TOOLTIP_EXECUTE_SQL = SQL 문 실행
_UI_MENU_SQLBUILDER     = SQL(&S)

!
! views/source/SQLSourceViewer.java & SQLSourceParser
!
!! NOTE TO TRANSLATOR: Do not translate the following four lines
_UI_INSERT                   = INSERT
_UI_UPDATE                   = UPDATE
_UI_DELETE                   = DELETE
_UI_SELECT                   = SELECT

_UI_PARSE_ERROR_ENCOUNTERED  = 구문 분석 오류가 발생했습니다.


!
! views/StatementNameViewer.java
!
_UI_LABEL_STATEMENT = 문:


!
! Join stuff - DefineJoinDialog.java, DefineJoinTypeDialog.java
!
_UI_DEFINE_JOIN_DIALOG_TITLE = 조인 지정
_UI_DEFINE_JOIN_DIALOG_INSTR = 조인 형식
_UI_RADIO_INNER_JOIN         = 내부 조인(&I)
_UI_RADIO_LEFT_OUTER_JOIN    = 왼쪽 우선 외부 조인(&L)
_UI_RADIO_RIGHT_OUTER_JOIN   = 오른쪽 우선 외부 조인(&R)
_UI_RADIO_FULL_OUTER_JOIN    = 완전 외부 조인(&F)
_UI_ACTION_SPECIFY_JOIN_TYPE = 조인 형식 지정(&S)...
_UI_MSG_INVALID_JOIN_TITLE = 호환되지 않는 형식

!
!views/update/UpdateDesignViewer.java
!
_UI_TOOLTIP_UPDATE_ADD_COL = 문에 개별 열 추가
_UI_TOOLTIP_UPDATE_ADD_GRP = 그룹으로 문에 열 추가

!
!views/with/WithTableViewer.java
!
_UI_GROUP_WITH_TABLE     = 테이블
_UI_GROUP_WITH_COLUMN    = 열
_UI_BUTTON_ADD_RIGHT     = 추가>>
_UI_BUTTON_REMOVE_LEFT   = <<제거

!
! views/with/WithStatementViewer
!
_UI_LABEL_WITH_STATEMENT  = 문 이름:

! CreateWithTableAction.java

!! NOTE TO TRANSLATOR: Do not translate "WithTable%1" in the following line
_UI_WITH_TABLE_NAME		  = WithTable%1
!
! SQLPreferencePage.java
!
_UI_VALIDATE_PREFERENCE_LABEL = 저장하기 전 실행으로 쿼리 문 확인(&V)
_UI_ERROR_VALUE_GREATER_THAN_0 = 값은 0보다 큰 정수여야 합니다.

!
!views/SQLBuilder.java
!
_UI_VALIDATE_FAILED_TITLE    = 유효성 검사 실패
_UI_CONTINUE_WITH_SAVE       = 저장을 계속하시겠습니까?
_UI_VALIDATING_STATEMENT     = 문 유효성을 확인하는 중...
_UI_OBTAINING_CONNECTION     = 연결하기...
_UI_VALIDATE_DONE            = 확인 완료...
_UI_SAVING_STATEMENT         = 문 저장...

!model/SQLBuilderConstants.java:
_UI_BUILD_EXPRESSION   = 식 빌드...
_UI_EDIT_EXPRESSION    = 식 편집...
_UI_REPLACE_EXPRESSION = 식 바꾸기...
!! NOTE TO TRANSLATOR: Do not translate the words that are fully capitalized in the following 3 lines
_UI_ADD_SELECT         = SELECT 문 추가...
_UI_ADD_FULLSELECT     = FULLSELECT 문 추가...
_UI_ADD_WITH           = WITH 문 추가...
_UI_ADD_VALUES         = 값 추가
_UI_SPECIFY_VALUE	   = 값 지정...
_UI_SPECIFY_VALUE_TITLE	   = 값 지정


!========================================================================================
!
! Here is the list of Warning string that have message IDs - make sure they are unique
!
!========================================================================================
_WARN_MESSAGES_ENCOUNTERED      = 경고 메시지가 발생했습니다.
_WARN_DATA_WAS_TRUNCATED        = 데이터가 잘렸습니다.
_WARN_SQL_WARNINGS_FOUND        = SQL 경고가 발견되었습니다.

_WARN_BAD_JOIN_1                = 호환되지 않는 조인 형식
_WARN_INVALID_JOIN              = 잘못된 조인
_WARN_COLUMN_USED               = 열이 테이블 사이의 조인에 이미 사용되었습니다.

! Usage:
!   {0} replaced with the column name and {1} replaced with the column datatype
!
_WARN_BAD_JOIN_TYPES               = 열 {0}의 형식은 is {1}입니다.


!======================================================================================
!
! Here is the list of Error string that have message IDs - make sure they are unique
!
!======================================================================================

_ERROR_DIALOG_TITLE_EXPR_EDIT_FAILED = 식 편집 실패
_ERROR_DIALOG_MSG_REBUILD_EXPR       = 식을 다시 빌드해야 합니다.

_ERROR_TABLE_USED                    = 고유 테이블을 선택하거나 별칭을 입력하십시오.
_ERROR_ALIAS_USED                    = 고유한 별칭을 입력하십시오.

_ERROR_ALIAS_NOT_UNIQUE_TEXT         = 별칭이 고유하지 않습니다.


_EXC_OPEN_SQL_FILE_RESOURCE          = 입력 SQL 파일 리소스를 여는 중 오류가 발생했습니다.
_EXC_OPEN_SQL_FILE_NOT_CONNECTED     = SQL 쿼리 빌더의 SQL 파일을 열기 전에 데이터베이스 "{0}"에 연결해야 합니다.

!! NOTE TO TRANSLATOR: Do not translate "ISQLBuilderEditorInput" in the following line
_EXC_INPUT_NOT_ISQLBUILDEREDITORINPUT = 입력이 ISQLBuilderEditorInput이 아닙니다.

_ERROR_ALIAS_NOT_ALPHANUMERIC        = 별칭은 모든 영숫자 문자를 포함합니다.
_ERROR_ALIAS_TABLE_USED              = 별칭은 데이터베이스 테이블 이름이 아니어야 합니다.
_ERROR_ENTER_ALIAS                   = 테이블 이름이 사용되고 있습니다. 별칭을 입력하십시오.

_ERROR_DATABASE_NOT_AVAILABLE        = 데이터베이스 연결을 사용할 수 없습니다.
_ERROR_INPUT_NOT_RECOGNIZED          = 편집기 입력 형식이 지원되지 않습니다.
_ERROR_OPEN_SQL_STORAGE_RESOURCE     = 입력 SQL 저장소 리소스를 여는 중 오류가 발생했습니다.
_ERROR_OPEN_SQL_STRING_RESOURCE      = 입력을 여는 도중 오류가 발생했습니다.
_ERROR_OPEN_INPUT_PARSE_FAILED       = SQL이 잘못되었거나 현재 지원되지 않기 때문에 구문 분석할 수 없습니다.
_QUESTION_TITLE_OPEN_INPUT_PARSE_FAILED    = SQL 쿼리 빌더 구문 분석 오류
_QUESTION_MESSAGE_OPEN_INPUT_PARSE_FAILED    = 빈 SQL 문은 SQL 쿼리 빌더에서 만들어집니다.

!
!model/SQLDomainModel.java
!
!note we reuse messages from ErrorMessages.java in org.eclipse.ui.texteditor.ErrorMessages

Editor_error_save_title=문제 저장
Editor_error_save_message=저장을 완료할 수 없습니다.
Editor_error_save_readonly=\n\n원인:\n읽기 전용 파일에 쓸 수 없습니다:
Editor_error_save_failed=\n\n원인:\n파일에 쓸 수 없습니다:

!Strings from B2BGUI properties file
_UI_ERROR_CREATING_FILE_TITLE = 파일을 만드는 중 오류가 발생했습니다.
_UI_ERROR_CREATING_FILE_SHORT_DESC = 파일 "{0}"을 만드는 중 오류가 발생했습니다.
_UI_ERROR_CREATING_FILE_LONG_DESC = 파일 "{0}"을 만드는 중 오류가 발생했습니다.
_UI_PARENT_FOLDER_IS_READ_ONLY = 부모 디렉터리 "{0}"는 읽기 전용입니다.
_UI_UNKNOWN_ERROR_WITH_HINT = 알 수 없는 오류. 부모 디렉터리 "{0}"이(가) 쓰기 가능한지 확인하십시오.
_UI_UNKNOWN_ERROR = 알 수 없는 오류. 부모 디렉터리 "{0}"이(가) 쓰기 가능한지 확인하십시오.
! TextViewerOperationAction
_UI_MENU_COPY         = 복사(&C)
_UI_MENU_CUT          = 잘라내기(&T)
_UI_MENU_DELETE       = 삭제
_UI_MENU_PASTE        = 붙여넣기(&P)
_UI_MENU_PREFIX       = 접두사
_UI_MENU_REDO         = 다시 실행
_UI_MENU_SELECT_ALL   = 모두 선택
_UI_MENU_SHIFT_LEFT   = 왼쪽으로 이동
_UI_MENU_SHIFT_RIGHT  = 오른쪽으로 이동
_UI_MENU_STRIP_PREFIX = 접두사 제거
_UI_MENU_UNDO         = 실행 취소

!SQL Editor
datatools_sqlbuilder_sqleditor_tooltip = SQL 편집기 열기
datatools_sqlbuilder_sqleditor_title = SQL 편집기(&S)

! SQL Query Builder
datatools_sqlbuilder_sqlbuilder_DML_error = 오류
datatools_sqlbuilder_sqlbuilder_multipleDML = SQL 빌더는 단일 SQL DML 문을 생성하는 데만 사용할 수 \
있습니다. 파일 {0}에 단일 DML 문이 없습니다. SQL 편집기를 사용하여 파일을 여시겠습니까?
datatools_sqlbuilder_sqlbuilder_multipleDML_VE = Visual Explain은 단일 SQL DML 문을 생성하는 데만 \
사용할 수 있습니다. 파일 {0}에 단일 DML 문이 없습니다.
datatools_sqlbuilder_sqlbuilder_unsupport_VE = Visual Explain은 이 데이터베이스 연결에서 지원되지 않습니다.
!Clear to Skeleton and Revert to Last Correct
_UI_CLEAR_TO_TEMPLATE 			     = 템플릿 지우기
_UI_REVERT_TO_LAST_CORRECT_SOURCE    = 이전 원본으로 되돌리기
_UI_LAST_CORRECT_SOURCE 			 = 이전 원본?
!Change Statement Type
_UI_CHANGE_STATEMENT_TYPE			= 문 형식 변경...
!Omit Current Schema
_UI_OMIT_CURRENT_SCHEMA              = 현재 스키마 생략... 

!
! views/execute/ExecuteViewer.java & views/execute/QueryOutputHelper.java
!
_UI_DIALOG_EXECUTE_SQL_TITLE = SQL 문 실행
_UI_BUTTON_EXECUTE_CLOSE     = 닫기(&C)
_UI_LABEL_SQL_STATEMENT      = SQL 문(&S):
_UI_BUTTON_EXECUTE           = 실행(&R)
_UI_LABEL_QUERY_RESULTS      = 쿼리 결과:
_UI_MONITOR_EXECUTING_QUERY  = SQL 문 실행 중...
_UI_LABEL_NO_CONNECTION      = 데이터베이스 연결이 누락되었거나 관계형 데이터베이스 스키마 모델이 손상되었습니다.
_UI_LABEL_NO_CONNECTION_FILE = 연결 xmi 파일이 누락되어 데이터베이스 연결을 사용할 수 없습니다.

_UI_DIALOG_WARNING_TITLE       = 경고
_UI_DIALOG_OP_FAILED_TITLE     = 작업 실패
_UI_LABEL_EXECUTION_SUCCESSFUL = 문을 성공적으로 실행하였으나, 표시할 결과가 없습니다.
!! NOTE TO TRANSLATOR: the MAX_RECORD_EXCEEDED string should match
!! the strings for RECORD_RETURNED and RECORD_DISPLAYED.
_UI_MSG_MAX_RECORD_EXCEEDED    = 반환된 1000개 이상의 레코드 중 1000개의 레코드만 표시됩니다.
_UI_MSG_RECORD_RETURNED        = 레코드가 반환되었습니다.


! SQL Statement validation errors
! usage note: %1 will be replaced with a SQL statement name
_ERROR_STATEMENT_NAME_EXISTS         = 이름이 %1인 문이 이미 있습니다.
_ERROR_INVALID_STATEMENT_NAME = 문 이름을 입력하십시오.
! usage note: %1 is replaced with the invalid character entered by the user
_ERROR_INVALID_CHAR_IN_STATEMENT_NAME = 문 이름에 문자 %1를 포함할 수 없습니다.
_ERROR_STATEMENT_NAME_TOO_LONG = 문 이름은 문자 160자를 초과할 수 없습니다.


! SelectDBTablePage resources
datatools_selectDBTablePage_statements = 문

!
! SQL Statement actions
!
datatools_sqlbuilder_sqlstatement_actions_new = 새로 만들기...
datatools_sqlbuilder_sqlstatement_actions_newSQLStatement = 새 SQL 문...
datatools_sqlbuilder_sqlstatement_actions_newSQLStatement_tooltip = 새 Query 문 생성
datatools_sqlbuilder_sqlstatement_actions_openwith = 연결 프로그램
datatools_sqlbuilder_sqlstatement_actions_sqlbuilder = SQL 쿼리 빌더
datatools_sqlbuilder_sqlstatement_actions_sqleditor = SQL 편집기

!
! New SQL statement wizard, dialog, and page
!
datatools_sqlbuilder_newStatementWizard_title = 새 SQL 문
datatools_sqlbuilder_newStatementWizard_description = SQL 문을 생성합니다.
datatools_sqlbuilder_newStatementDialog_title = 새 SQL 문
datatools_sqlbuilder_newStatementPage_title = 문 형식
datatools_sqlbuilder_newStatementPage_description = 이름을 지정하고 문의 템플릿 형식을 선택하십시오.
datatools_sqlbuilder_newStatementPage_statementID = 문 확인
datatools_sqlbuilder_newStatementPage_statementName = 문 이름(&S)
datatools_sqlbuilder_newStatementPage_statementType = 문 템플릿(&T):
datatools_sqlbuilder_newStatementPage_editUsing = 다음을 사용하여 편집
datatools_sqlbuilder_newStatementPage_sqlQueryBuilder = SQL 빌더(&Q)
datatools_sqlbuilder_newStatementPage_sqlEditor = SQL 편집기(&E)
datatools_sqlbuilder_newStatementPage_sampleStatements = 샘플 문
datatools_sqlbuilder_newStatementPage_none = 없음
!
! Project selection page, for new SQL statement wizard
!
datatools_sqlbuilder_projectPage_title = 프로젝트 선택
datatools_sqlbuilder_projectPage_description = 기존 프로젝트를 선택하거나 이 문에 새로운 프로젝트를 추가하십시오.

!
! Generated comment text for template SQL for New SQL Statement action
!
_UI_SAMPLE_DML_TEMPLATE_STATEMENTS = 샘플 DML 템플릿 문
_UI_SAMPLE_DDL_TEMPLATE_STATEMENTS = 샘플 DDL 템플릿 문

!
! String for the AddTable With Table
!! NOTE TO TRANSLATOR: Do not translate "WITH" in the following line
_UI_AVAILABLE_TABLES_TREE_PROVIDER = WITH 테이블

!actions/ConvertToFullSelectAction.java
!! NOTE TO TRANSLATOR: Do not translate "FULLSELECT" and "UNION" in the following line
_UI_ACTION_CONVERT_TO_FULL_SELECT=FULLSELECT(UNION)으로 변환(&C)


! org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect/fullselectElement.java
!! NOTE TO TRANSLATOR: Do not translate the words that are fully capitalized in the following 3 lines
_UI_VIEWS_FULLSELECTELEMENT_FULLSELECT = FULLSELECT 문
_UI_VIEWS_FULLSELECTELEMENT_SELECT = SELECT 문
_UI_VIEWS_FULLSELECTELEMENT_VALUES = VALUES

! org.eclipse.datatools.sqltools.sqlbuilder.views.graph/GraphContextMenuProvider.java
_UI_VIEWS_GRAPH_SELECT_ALL_COLUMN = 모든 열 선택
_UI_VIEWS_GRAPH_DESELECT_ALL_COLUMN = 모든 열 선택 취소

!SQLQueryBuilder Dialog
_UI_SQLQUERYBUILDERDIALOG_NAME = SQL 쿼리 빌더
SQLQueryBuilderDialogEditTabName=편집
SQLQueryBuilderDialogNoInput=SQL 빌더 대화 상자가 열려는 입력에 전달되지 않았습니다.
SQLQueryBuilderDialogParseFailOnOpenAskUserTitle=SQL 쿼리 빌더
SQLQueryBuilderDialogParseFailOnOpenAskUserMessage=계속할 경우, SQL 쿼리 빌더에 빈 SQL 문이 만들어집니다. 계속하시겠습니까?
SQLQueryBuilderDialogResultsTabName=SQL 결과


# plugin.xml.  preference page extension point
_UI_SQLQUERY_NAME = SQL 쿼리

## Resources for the standard editor actions

ContentAssistProposal_label       =내용 지원(&N)@Ctrl+SPACE
ContentAssistProposal_tooltip     =내용 지원
ContentAssistProposal_image       =
ContentAssistProposal_description =내용 지원

ContentTip_label       =내용 팁@Ctrl+SHIFT+SPACE
ContentTip_tooltip     =내용 팁
ContentTip_image       =
ContentTip_description =내용 팁

## Resources for other SQL Query Builder actions

datatools_sqlbuilder_RunSQLAction_label       = SQL 실행(&R)
datatools_sqlbuilder_RunSQLAction_tooltip     = SQL 문 실행
datatools_sqlbuilder_RunSQLAction_image       =
datatools_sqlbuilder_RunSQLAction_description =

datatools_sqlbuilder_RevertToPreviousAction_label       = 이전 원본으로 되돌리기
datatools_sqlbuilder_RevertToPreviousAction_tooltip     =
datatools_sqlbuilder_RevertToPreviousAction_image       =
datatools_sqlbuilder_RevertToPreviousAction_description =

datatools_sqlbuilder_RevertToDefaultAction_label       = 템플릿 지우기
datatools_sqlbuilder_RevertToDefaultAction_tooltip     =
datatools_sqlbuilder_RevertToDefaultAction_image       =
datatools_sqlbuilder_RevertToDefaultAction_description =

datatools_sqlbuilder_ChangeStatementTypeAction_label   = 문 형식 변경...
datatools_sqlbuilder_ChangeStatementTypeAction_tooltip     =
datatools_sqlbuilder_ChangeStatementTypeAction_image       =
datatools_sqlbuilder_ChangeStatementTypeAction_description =

datatools_sqlbuilder_OmitCurrentSchemaAction_label       = 현재 스키마 생략...
datatools_sqlbuilder_OmitCurrentSchemaAction_tooltip     =
datatools_sqlbuilder_OmitCurrentSchemaAction_image       =
datatools_sqlbuilder_OmitCurrentSchemaAction_description =

!RDBTableDropListener
_UI_INVALID_TABLE_ADDED = 데이터베이스에서 문으로 테이블을 추가하려면 데이터베이스가 프로젝트와 연결되어 있어야 합니다.

## Resources for utilities

VIEW_UTILITY_DEFAULT_FONT_UI_ = MS Sans Serif
STRING_MATCH_EXPECTED_EXCEPTION_MESSAGE = 필요: 
STRING_MATCH_RETURNED_EXCEPTION_MESSAGE =  반환:
