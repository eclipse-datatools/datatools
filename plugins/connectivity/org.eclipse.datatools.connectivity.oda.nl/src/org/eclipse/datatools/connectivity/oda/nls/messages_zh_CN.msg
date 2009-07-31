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
logManager_duplicateName=已存在同名的记录器。
#
manifest_nullArgument=参数不能为空。
manifest_NO_DATA_SOURCE_EXTN_ID_DEFINED=在 ODA 驱动程序 plugin.xml 中，数据源扩展缺少 id 属性。
manifest_INVALID_VERSION_VALUE=在 ODA 驱动程序 plugin.xml 中，数据源扩展（{1}）包含无效的 odaVersion 值（{0}）。
manifest_NO_DRIVER_CLASS_DEFINED=在 ODA 驱动程序 plugin.xml 中，数据源扩展（{0}）缺少 driverClass 属性。
manifest_INVALID_SET_THREAD_CONTEXT_CLASSLOADER_VALUE=在 ODA 驱动程序 plugin.xml 中，数据源扩展（{1}）包含无效的 setThreadContextClassLoader 值（{0}）。
manifest_NO_DATA_SET_TYPES_DEFINED=ODA 数据源扩展 ({0}) 未在扩展清单中定义 <dataSet> 元素。这是有效的配置，但是会限制 ODA 扩展功能。
manifest_NO_ATTRIBUTE_ID_DEFINED=ODA 驱动程序插件扩展的（{1}）元素缺少属性（{0}）。
manifest_NO_DATA_TYPE_MAPPINGS_DEFINED=在 ODA 驱动程序 plugin.xml 中，应该至少为 <dataSet>（{0}）定义一个 <dataTypeMapping> 元素。
manifest_NO_NATIVE_TYPE_CODE_DEFINED=在 ODA 驱动程序 plugin.xml 中，<dataSet>（{1}）的 <dataTypeMapping>（{0}）缺少 nativeDataTypeCode 属性。
manifest_INVALID_NATIVE_TYPE_CODE_VALUE=在 ODA 驱动程序 plugin.xml 中，<dataSet>（{2}）的 <dataTypeMapping>（{1}）包含无效的 nativeDataTypeCode 值（{0}）。
manifest_NO_NATIVE_TYPE_NAME_DEFINED=在 ODA 驱动程序 plugin.xml 中，<dataSet>（{0}）的 <dataTypeMapping> 缺少 nativeDataType 属性。
manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_1=在 ODA 驱动程序 plugin.xml 中，<dataTypeMapping>（{0}）缺少 odaScalarDataType 属性。
manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_2=在 ODA 驱动程序 plugin.xml 中，<dataTypeMapping>（{0}）的 <alternativeOdaDataType> 缺少 odaScalarDataType 属性。
manifest_INVALID_ODA_SCALAR_DATA_TYPE_VALUE=在 ODA 驱动程序 plugin.xml 中，<dataTypeMapping>（{1}）包含无效的 odaScalarDataType 值（{0}）。
manifest_INVALID_LOG_LEVEL_VALUE=在 ODA 驱动程序 plugin.xml 中，数据源扩展（{1}）包含无效的 logLevel 值（{0}）。
manifest_MISSING_ELEMENT_IN_EXTENSION_MANIFEST=扩展({0})缺少 {1} 元素。
manifest_NO_LOG_FILENAME_PREFIX_DEFINED=在 ODA 驱动程序 plugin.xml 中，数据源扩展（{0}）的 <traceLogging> 缺少 logFileNamePrefix 属性。
#
sortSpec_INVALID_SORT_MODE_SPECIFIED=指定了无效的排序方式：{0}。
sortSpec_NULL_COLUMN_NAME_SPECIFIED=指定了空列名。
sortSpec_INVALID_COLUMN_NAME_SPECIFIED=指定了无效的列名：{0}。
sortSpec_INVALID_NULL_ORDERING=无效的空顺序值: {0}。
sortSpec_INVALID_SORT_ORDER_SPECIFIED=指定了无效的排序顺序：{0}。
sortSpec_NO_DYNAMIC_SORT_KEY_FOR_SORTMODENONE=具有 sortModeNone 的 sortSpec 不允许使用任何动态排序键。
sortSpec_ONE_SORTCOLUMN_FOR_SINGLE_COLUMN_MODE=排序列已存在。sortModeSingleColumn 方式最多只允许使用一个动态排序列。
sortSpec_ONE_SORTORDER_FOR_SINGLE_ORDER_MODE=sortModeSingleOrder 方式只支持一个排序顺序。
sortSpec_ONLY_IN_SINGLE_ORDER_MODE=只有在 sortModeSingleOrder 方式下才受支持。
sortSpec_INDEX_OUT_OF_BOUND=索引: {0}，计数: {1}。
#
stringSubUtil_NO_STRING_VALUE_TO_REPLACE=没有要替换的字符串值：{0}。
stringSubUtil_DELIMITER_CANNOT_BE_EMPTY=定界符不能是空字符串，也不能只包含空格。
stringSubUtil_DELIMITER_CANNOT_BE_NULL=定界符不能为空。
stringSubUtil_SUBSTITUTION_LIST_CANNOT_BE_NULL=字符串替换列表不能为空。
stringSubUtil_SUBSTITUTION_VALUE_CANNOT_BE_NULL=字符串替换值不能为空。
stringSubUtil_TEXT_STRING_CANNOT_BE_NULL=文本字符串不能为空。
stringSubUtil_NAME_VALUE_MAP_CANNOT_BE_NULL=“名称－值”映射不能为空。
#
profileProperty_PROFILE_NAME=连接配置文件名称(&P)
profileProperty_PROFILE_STORE_PATH=连接配置文件存储 URL(&U)
profileProperty_GROUP_PROPERTIES=外部连接配置文件引用
#
querySpec_CUSTOM_AGGR_EXCEED_MAX_ARGS=自定义筛选表达式 ({0}) 具有 {1} 个参数，但是最多允许 {2} 个参数。
querySpec_CUSTOM_AGGR_INCOMPATIBLE_DUPL_CHECK=自定义聚合类型 ({0}) 无法忽略重复值。
querySpec_CUSTOM_AGGR_INCOMPATIBLE_NULL_CHECK=自定义聚合类型 ({0}) 无法忽略空值。
querySpec_CUSTOM_AGGR_LESS_THAN_MIN_ARGS=The custom aggregate expression ({0}) has {1} arguments, but requires a minimum of {2} arguments.
querySpec_CUSTOM_FILTER_EXCEED_MAX_ARGS=自定义筛选表达式 ({0}) 具有 {1} 个参数，但是最多允许 {2} 个参数。
querySpec_CUSTOM_FILTER_LESS_THAN_MIN_ARGS=自定义筛选表达式 ({0}) 具有 {1} 个参数，但是最少允许 {2} 个参数。
querySpec_EXTENSION_ID_NOT_FOUND=使用 id ({1}) 未找到任何 {0} 扩展。
querySpec_INVALID_AGGR_EXPR=Invalid aggregate expression.
querySpec_INVALID_AGGR_HIDE_COLUMN=Invalid aggregate projection on a hidden column {0}.
querySpec_INVALID_ARG=无效参数: {0}。
querySpec_INVALID_CLASS_TYPE_ATTRIBUTE=无效类类型 ({0})。  {1} 属性必须是 {2} 的实例。
querySpec_INVALID_EXT_POINT_ATTR_VALUE={0} 扩展 ({1}) 的 {3} 属性具有无效值 ({2})。
querySpec_INVALID_EXT_POINT_ELEMENT={0} 扩展 ({1}) 无效。  参见架构定义以了解所需内容。
querySpec_INVALID_FILTER_EXPR=Invalid filter expression.
querySpec_INVALID_RESULT_PROJ=Invalid result projection.
querySpec_INVALID_SORT_KEY=Invalid sort key.
querySpec_INVALID_SORT_SPEC=Invalid sort specification.
querySpec_MAX_ONE_NEGATING_EXPR=不能向 {0} 添加多个否定表达式。
querySpec_MISSING_COMPOSITE_MIN_CHILDREN=组合 {0} 必须至少包含 {1} 个子表达式。
querySpec_MISSING_EXPR_VARIABLE=筛选表达式 ({0}) 缺少关联的变量。
querySpec_MISSING_EXT_POINT_ATTR_VALUE={0} 扩展 ({1}) 缺少 {3} 元素的 {2} 属性值。
querySpec_MISSING_EXT_POINT_ELEMENT={0} 扩展 ({1}) 缺少 {2} 元素。
querySpec_MISSING_EXT_POINT_MIN_ELEMENT={0} 扩展 ({1}) 必须至少具有一个已定义的 {2} 元素。
querySpec_NON_DEFINED_CUSTOM_AGGR=在 dynamicResultSet 扩展注册表中未找到自定义聚合表达式 ({0})。
querySpec_NON_DEFINED_CUSTOM_FILTER=在 dynamicResultSet 扩展注册表中未找到自定义筛选表达式 ({0})。
querySpec_NONSUPPORTED_VAR_DATA_TYPE=The native data type ({0}) of the expression variable ({1}) is not supported by the type of custom expression used.
querySpec_NOT_EXPR_INCOMPATIBLE=在 {1} 中使用的非否定表达式 ({0}) 无效。
querySpec_NOT_EXPR_MISSING_CHILD=NotExpression: ({0}) 中缺少否定表达式。
querySpec_UNABLE_ADD_TO_NON_LIST_COLLECTION=无法向现有值添加值。  现有值不是 java.util.List。
querySpec_UNEXPECTED_AGGR_EXPR_EXTENSION=Unable to support the custom aggregate expression ({0}) of other extension.
querySpec_UNEXPECTED_AGGR_EXPR_TYPE=The custom aggregate expression ({0}) is not a {1} instance. Consumer application must use ExpressionFactory\#createCustomAggregate to create a custom expression instance.
querySpec_UNEXPECTED_CUSTOM_EXPR_EXTENSION=无法支持其它扩展名的自定义筛选表达式 ({0})。
querySpec_UNEXPECTED_CUSTOM_EXPR_TYPE=自定义筛选表达式({0}) 不是 {1} 实例。\n 客户应用程序必须使用 ExpressionFactory\#createCustomExpression 才能创建自定义筛选表达式实例。
querySpec_UNEXPECTED_EXPR_VARIABLE_TYPE=不支持此类型的 ExpressionVariable ({1})。
