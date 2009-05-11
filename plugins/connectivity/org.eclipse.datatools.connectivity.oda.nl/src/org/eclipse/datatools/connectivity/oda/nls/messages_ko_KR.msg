#
#************************************************************************
# Copyright (c) 2004, 2009 Actuate Corporation.
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
logManager_duplicateName=이름이 같은 로거(logger)가 이미 있습니다.
#
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
querySpec_CUSTOM_AGGR_INCOMPATIBLE_DUPL_CHECK=The custom aggregate type ({0}) cannot ignore duplicate values.
querySpec_CUSTOM_AGGR_INCOMPATIBLE_NULL_CHECK=The custom aggregate type ({0}) cannot ignore null values.
querySpec_CUSTOM_FILTER_EXCEED_MAX_ARGS=The custom filter expression ({0}) has {1} arguments, but allows a maximum of {2} arguments.
querySpec_CUSTOM_FILTER_MISSING_MIN_ARGS=The custom filter expression ({0}) has {1} arguments, but requires a minimum of {2} arguments.
querySpec_EXTENSION_ID_NOT_FOUND=No {0} extension found with the id ({1}).
querySpec_INVALID_ARG=Invalid argument: {0}.
querySpec_INVALID_CLASS_TYPE_ATTRIBUTE=Invalid class type ({0}).  The {1} attribute must be an instance of {2}.
querySpec_INVALID_EXT_POINT_ATTR_VALUE=The {0} extension ({1}) has an invalid value ({2}) for the {3} attribute.
querySpec_INVALID_EXT_POINT_ELEMENT=The {0} extension ({1}) is invalid.  See the schema definition for required content.
querySpec_MISSING_COMPOSITE_MIN_CHILDREN=The composite {0} must contain at least {1} child expressions.
querySpec_MISSING_EXT_POINT_ATTR_VALUE=The {0} extension ({1}) is missing the {2} attribute value in the {3} element.
querySpec_MISSING_EXT_POINT_ELEMENT=The {0} extension ({1}) is missing the {2} element.
querySpec_MISSING_EXT_POINT_MIN_ELEMENT=The {0} extension ({1}) must have at least one {2} element defined .
querySpec_NON_DEFINED_CUSTOM_AGGR=The custom aggregate expression ({0}.{1}) is not found in the dynamicResultSet extension registry.
querySpec_NON_DEFINED_CUSTOM_FILTER=The custom filter expression ({0}.{1}) is not found in the dynamicResultSet extension registry.
querySpec_NOT_EXPR_INCOMPATIBLE=Invalid use of a non-negatable expression ({0}) in a {1}.
querySpec_NOT_EXPR_MISSING_CHILD=Missing negating expression in NotExpression: ({0}).
querySpec_UNABLE_ADD_TO_NON_LIST_COLLECTION=Unable to add a value to existing values.  The existing value is not a java.util.List.
