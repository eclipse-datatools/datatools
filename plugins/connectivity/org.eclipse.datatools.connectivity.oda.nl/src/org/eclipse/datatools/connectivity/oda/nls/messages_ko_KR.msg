#
#************************************************************************
# Copyright (c) 2004, 2013 Actuate Corporation.
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
logManager_duplicateName=이름이 같은 로거(logger)가 이미 있습니다.
#
manifest_CANNOT_FIND_EXTENSION=ODA 데이터 원본 확장({0})을 찾을 수 없습니다.\n 확장 번들 및 종속성 로드에 관련된 모든 문제는 작업 영역 로그 파일을 확인하십시오.
manifest_nullArgument=인수는 널(null)일 수 없습니다.
manifest_NO_DATA_SOURCE_EXTN_ID_DEFINED=ODA 드라이버 plugin.xml 데이터 소스 확장에 ID 속성이 누락되었습니다.
manifest_INVALID_VERSION_VALUE=ODA 드라이버 plugin.xml의 데이터 소스 확장({1})에 올바르지 않은 odaVersion 값({0})이 있습니다.
manifest_NO_DRIVER_CLASS_DEFINED=ODA 드라이버 plugin.xml의 데이터 소스 확장({0})에 driverClass 속성이 누락되었습니다.
manifest_INVALID_SET_THREAD_CONTEXT_CLASSLOADER_VALUE=ODA 드라이버 plugin.xml의 데이터 소스 확장({1})에 올바르지 않은 setThreadContextClassLoader 값({0})이 있습니다.
manifest_NO_DATA_SET_TYPES_DEFINED=ODA 데이터 원본 확장({0})에 확장 매니페스트에 정의된 <dataSet> 요소가 없습니다. 이는 구성상 오류는 아니지만 ODA 확장 기능을 제한합니다.
manifest_NO_ATTRIBUTE_ID_DEFINED=ODA 드라이버 플러그인 확장의 요소({1})에 속성({0})이 누락되었습니다.
manifest_NO_DATA_TYPE_MAPPINGS_DEFINED=ODA 드라이버 plugin.xml에는 <dataSet>({0})에 대해 적어도 하나의 <dataTypeMapping> 요소가 정의되어야 합니다.
manifest_NO_NATIVE_TYPE_CODE_DEFINED=ODA 드라이버 plugin.xml에서, <dataSet>({1})의 <dataTypeMapping> ({0})에  nativeDataTypeCode 속성이 누락되었습니다.
manifest_INVALID_NATIVE_TYPE_CODE_VALUE=ODA 드라이버 plugin.xml에서, <dataSet>({2})의 <dataTypeMapping>({1})에  nativeDataTypeCode 값({0})이 올바르지 않습니다.
manifest_NO_NATIVE_TYPE_NAME_DEFINED=ODA 드라이버 plugin.xml에서, <dataSet>({0})의 <dataTypeMapping>에 nativeDataType 속성이 누락되었습니다.
manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_1=ODA 드라이버 plugin.xml의 <dataTypeMapping>({0})에 odaScalarDataType 속성이 누락되었습니다.
manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_2=ODA 드라이버 plugin.xml에서, <dataTypeMapping>({0})의 <alternativeOdaDataType>에 odaScalarDataType 속성이 누락되었습니다.
manifest_INVALID_ODA_SCALAR_DATA_TYPE_VALUE=ODA 드라이버 plugin.xml의 <dataTypeMapping>({1})에 올바르지 않은 odaScalarDataType 값({0})이 있습니다.
manifest_INVALID_LOG_LEVEL_VALUE=ODA 드라이버 plugin.xml의 데이터 소스 확장({1})에 올바르지 않은 logLevel 값({0})이 있습니다.
manifest_MISSING_ELEMENT_IN_EXTENSION_MANIFEST=확장({0})에 {1} 요소가 없습니다.
manifest_NO_LOG_FILENAME_PREFIX_DEFINED=ODA 드라이버 plugin.xml에서, 데이터 소스 확장({0})의 <traceLogging>에 logFileNamePrefix 속성이 누락되었습니다.
manifest_UNKNOWN_BUNDLE_LOCATION=ODA 확장 프로그램({0})의 설치 경로를 찾을 수 없습니다.  ODA 소비자 응용 프로그램은 경로를 확인할 수 있도록 appContext에 리소스 식별자를 지정해야 합니다.
#
sortSpec_INVALID_SORT_MODE_SPECIFIED=올바르지 않은 정렬 모드 지정\: {0}.
sortSpec_NULL_COLUMN_NAME_SPECIFIED=널(null) 열 이름이 지정되었습니다.
sortSpec_INVALID_COLUMN_NAME_SPECIFIED=올바르지 않은 열 이름 지정\: {0}.
sortSpec_INVALID_NULL_ORDERING=잘못된 널(null) 순서 값: {0}.
sortSpec_INVALID_SORT_ORDER_SPECIFIED=올바르지 않은 정렬 순서 지정\: {0}.
sortSpec_NO_DYNAMIC_SORT_KEY_FOR_SORTMODENONE=sortModeNone이 있는 sortSpec에는 동적 정렬 키가 허용되지 않습니다.
sortSpec_ONE_SORTCOLUMN_FOR_SINGLE_COLUMN_MODE=정렬 열이 이미 있습니다. sortModeSingleColumn 모드는 최대 하나의 동적 정렬 열을 허용합니다.
sortSpec_ONE_SORTORDER_FOR_SINGLE_ORDER_MODE=sortModeSingleOrder 모드는 하나의 하나의 정렬 열만 지원합니다.
sortSpec_ONLY_IN_SINGLE_ORDER_MODE=sortModeSingleOrder 모드에서만 지원됩니다.
sortSpec_INDEX_OUT_OF_BOUND=인덱스: {0}, 카운트: {1}.
#
stringSubUtil_NO_STRING_VALUE_TO_REPLACE=바꿀 문자열 값 없음\: {0}.
stringSubUtil_DELIMITER_CANNOT_BE_EMPTY=분리문자는 빈 문자열 또는 공백만을 포함할 수 없습니다.
stringSubUtil_DELIMITER_CANNOT_BE_NULL=분리문자는 널(null)일 수 없습니다.
stringSubUtil_SUBSTITUTION_LIST_CANNOT_BE_NULL=문자열 대체 목록은 널(null)일 수 없습니다.
stringSubUtil_SUBSTITUTION_VALUE_CANNOT_BE_NULL=문자열 대체 값은 널(null)일 수 없습니다.
stringSubUtil_TEXT_STRING_CANNOT_BE_NULL=텍스트 문자열은 널(null)일 수 없습니다.
stringSubUtil_NAME_VALUE_MAP_CANNOT_BE_NULL=이름-값 맵핑은 널(null)일 수 없습니다.
#
profileProperty_PROFILE_NAME=연결 프로필 이름(&P)
profileProperty_PROFILE_STORE_PATH=연결 프로필 저장 URL(&U)
profileProperty_GROUP_PROPERTIES=외부 연결 프로필 참조
#
querySpec_CUSTOM_AGGR_EXCEED_MAX_ARGS=The custom aggregate expression ({0}) has {1} arguments, which exceeds the allowed maximum of {2} arguments.
querySpec_CUSTOM_AGGR_INCOMPATIBLE_DUPL_CHECK=사용자 지정 집계 형식({0})에서 중복 값을 무시할 수 없습니다.
querySpec_CUSTOM_AGGR_INCOMPATIBLE_NULL_CHECK=사용자 지정 집계 형식({0})에서 널(null) 값을 무시할 수 없습니다.
querySpec_CUSTOM_AGGR_LESS_THAN_MIN_ARGS=사용자 지정 집계 식({0})에 이미 {1}개의 인수가 있지만 최소 {2}개의 인수를 추가할 수 있습니다.
querySpec_CUSTOM_FILTER_EXCEED_MAX_ARGS=The custom filter expression ({0}) has {1} arguments, which exceeds the allowed maximum of {2} arguments.
querySpec_CUSTOM_FILTER_LESS_THAN_MIN_ARGS=사용자 지정 필터 식({0})에 이미 {1}개의 인수가 있지만 최소 {2}개의 인수를 추가할 수 있습니다.
querySpec_CUSTOM_FUNC_EXCEED_MAX_ARGS=The custom function value expression ({0}) has {1} arguments, which exceeds the allowed maximum of {2} arguments.
querySpec_CUSTOM_FUNC_INCOMPATIBLE_DUPL_CHECK=The custom function type ({0}) cannot ignore duplicate values.
querySpec_CUSTOM_FUNC_LESS_THAN_MIN_ARGS=The custom function value expression ({0}) has {1} arguments, but requires a minimum of {2} arguments.
querySpec_EXTENSION_ID_NOT_FOUND=ID({1})가 있는 {0} 확장명을 찾을 수 없습니다.
querySpec_ILLEGAL_CONSTRUCTOR_ARG=Illegal {0} constructor argument
querySpec_INCOMPLETE_COMBINED_QUERY=결합된 쿼리 사양이 불완전합니다.
querySpec_INVALID_AGGR_EXPR=집계 식이 잘못되었습니다.
querySpec_INVALID_AGGR_HIDE_COLUMN=숨겨진 열 {0}의 집계 프로젝션이 잘못되었습니다.
querySpec_INVALID_ARG=잘못된 인수: {0}
querySpec_INVALID_BASE_QUERY=잘못된 기본 쿼리입니다.
querySpec_INVALID_CLASS_TYPE_ATTRIBUTE=잘못된 클래스 유형({0})입니다.  {1} 특성은 {2}의 인스턴스여야 합니다.
querySpec_INVALID_COLUMN_IDENTIFIER=Invalid column reference {0}.
querySpec_INVALID_EXT_POINT_ATTR_VALUE={0} 확장({1})에 {3} 특성에 대해 잘못된 값({2})이 있습니다.
querySpec_INVALID_EXT_POINT_ELEMENT={0} 확장({1})이 잘못되었습니다.  필요한 콘텐츠에 대한 스키마 정의를 참조하십시오.
querySpec_INVALID_FILTER_EXPR=필터 식이 잘못되었습니다.
querySpec_INVALID_RESULT_PROJ=결과 프로젝션이 잘못되었습니다.
querySpec_INVALID_SORT_KEY=정렬 키가 잘못되었습니다.
querySpec_INVALID_SORT_SPEC=정렬 지정이 잘못되었습니다.
querySpec_INVALID_VALUE_EXPR=Invalid value expression.
querySpec_MAX_ONE_NEGATING_EXPR={0}에 부정 식을 두 개 이상 추가할 수 없습니다.
querySpec_MISSING_ATOMIC_QUERY_TEXT=원자성 기본 쿼리에서 쿼리 텍스트가 누락되었습니다.
querySpec_MISSING_COMBINED_MATCHING_EXPR=결합된 조건에서 일치하는 식이 누락되었습니다.
querySpec_MISSING_COMPOSITE_MIN_CHILDREN=복합 {0}에는 {1}개 이상의 자식 식이 있어야 합니다.
querySpec_MISSING_EXPR_VARIABLE=필터 식({0})에 관련 변수가 없습니다.
querySpec_MISSING_EXT_POINT_ATTR_VALUE={0} 확장({1})에 {3} 요소의 {2} 특성 값이 없습니다.
querySpec_MISSING_EXT_POINT_ELEMENT={0} 확장({1})에 {2} 요소가 없습니다.
querySpec_MISSING_EXT_POINT_MIN_ELEMENT={0} 확장({1})에는 {2}개 이상의 요소가 정의되어 있어야 합니다.
querySpec_NON_DEFINED_COMBINED_OP=dynamicResultSet 확장 레지스트리에 통합 식 연산자 형식({0})이 없습니다.
querySpec_NON_DEFINED_CUSTOM_AGGR=dynamicResultSet 확장 레지스트리에 사용자 지정 집계식({0})이 없습니다.
querySpec_NON_DEFINED_CUSTOM_FILTER=dynamicResultSet 확장 레지스트리에 사용자 지정 필터 식({0})이 없습니다.
querySpec_NON_DEFINED_CUSTOM_FUNC=The custom function ({0}) is not found in the dynamicResultSet extension registry.
querySpec_NON_RELATED_QUERYSPEC=결합 쿼리가 아닌 쿼리로는 결합된 쿼리 식의 열 {0}을(를) 한정할 수 없습니다.
querySpec_NONSUPPORTED_NULL_ORDERING=널(null) 순서 지정은 데이터 원본 공급자에서 지원되지 않습니다.
querySpec_NONSUPPORTED_VAR_DATA_TYPE=The ODA data type ({0}) of the value expression ({1}) is not supported by the type of custom expression used.
querySpec_NOT_EXPR_INCOMPATIBLE={1}에 무효화할 수 없는 식({0})이 잘못 사용되었습니다.
querySpec_NOT_EXPR_MISSING_CHILD=NotExpression에 부정 식이 누락되었습니다: ({0}).
querySpec_NULL_CONSTRUCTOR_3ARGS=Null {0} constructor argument(s): {1}, {2}, {3}.
querySpec_NULL_CONSTRUCTOR_ARG=Null {0} constructor argument
querySpec_UNABLE_ADD_TO_NON_LIST_COLLECTION=기존 값에 값을 추가할 수 없습니다.  기존 값이 java.util.List가 아닙니다.
querySpec_UNEXPECTED_AGGR_EXPR_EXTENSION=다른 확장명의 사용자 지정 집계 식({0})은 지원할 수 없습니다.
querySpec_UNEXPECTED_AGGR_EXPR_TYPE=사용자 지정 집계 식({0})이 {1} 인스턴스가 아닙니다. 소비자 응용 프로그램은 ExpressionFactory\#createCustomAggregate를 사용하여 사용자 지정 식 인스턴스를 생성해야 합니다.
querySpec_UNEXPECTED_CUSTOM_EXPR_EXTENSION=다른 확장명의 사용자 지정 필터 식({0})은 지원할 수 없습니다.
querySpec_UNEXPECTED_CUSTOM_EXPR_TYPE=사용자 지정 필터 식({0})이 {1} 인스턴스가 아닙니다.\n 소비자 응용 프로그램은 ExpressionFactory\#createCustomExpression을 사용하여 사용자 지정 필터 식 인스턴스를 생성해야 합니다.
querySpec_UNEXPECTED_EXPR_VARIABLE_TYPE=이 ExpressionVariable({1}) 형식을 지원할 수 없습니다.
querySpec_UNEXPECTED_FUNC_EXPR_EXTENSION=다른 확장명의 사용자 지정 함수({0})를 지원할 수 없습니다.
querySpec_UNEXPECTED_FUNC_EXPR_TYPE=사용자 지정 함수({0})가 {1} 인스턴스가 아닙니다. 소비자 응용 프로그램은 ExpressionFactory\#createCustomFunction을 사용하여 사용자 지정 함수의 인스턴스를 생성해야 합니다.
