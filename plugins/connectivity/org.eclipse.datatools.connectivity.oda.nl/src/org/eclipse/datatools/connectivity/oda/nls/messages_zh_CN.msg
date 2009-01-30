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
manifest_MISSING_ELEMENT_IN_EXTENSION_MANIFEST=The extension ({0}) is missing a {1} element.
manifest_NO_LOG_FILENAME_PREFIX_DEFINED=在 ODA 驱动程序 plugin.xml 中，数据源扩展（{0}）的 <traceLogging> 缺少 logFileNamePrefix 属性。
#
sortSpec_INVALID_SORT_MODE_SPECIFIED=指定了无效的排序方式：{0}。
sortSpec_NULL_COLUMN_NAME_SPECIFIED=指定了空列名。
sortSpec_INVALID_COLUMN_NAME_SPECIFIED=指定了无效的列名：{0}。
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
