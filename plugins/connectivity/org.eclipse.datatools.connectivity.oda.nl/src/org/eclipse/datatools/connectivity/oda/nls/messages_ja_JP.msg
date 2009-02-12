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
logManager_duplicateName=同じ名前のロガーが存在します。
#
manifest_nullArgument=引数は null にできません。
manifest_NO_DATA_SOURCE_EXTN_ID_DEFINED=ODA ドライバー plugin.xml のデータ・ソース拡張に、ID 属性がありません。
manifest_INVALID_VERSION_VALUE=ODA のドライバー plugin.xml は、データ・ソース拡張 ({1}) に無効な odaVersion 値 ({0}) を持っています。
manifest_NO_DRIVER_CLASS_DEFINED=ODA のドライバー plugin.xml に、データ・ソース拡張 ({0}) 内の driverClass 属性がありません。
manifest_INVALID_SET_THREAD_CONTEXT_CLASSLOADER_VALUE=ODA のドライバー plugin.xml は、データ・ソース拡張 ({1}) 内に無効な setThreadContextClassLoader 値 ({0}) を持っています。
manifest_NO_DATA_SET_TYPES_DEFINED=拡張マニフェストに、ODA データソース拡張 ({0}) の <dataSet> 要素が定義されていません。この設定は有効ですが、ODA 拡張機能の一部が使用できなくなります。
manifest_NO_ATTRIBUTE_ID_DEFINED=ODA ドライバーのプラグイン拡張に、({1}) 要素内の属性 ({0}) がありません。
manifest_NO_DATA_TYPE_MAPPINGS_DEFINED=ODA のドライバー plugin.xml は、<dataSet> ({0}) に定義された <dataTypeMapping> 要素を少なくとも 1 つ持っていなければなりません。
manifest_NO_NATIVE_TYPE_CODE_DEFINED=ODA ドライバー plugin.xml に、<dataSet> ({1}) の <dataTypeMapping> ({0}) 内の nativeDataTypeCode 属性がありません。
manifest_INVALID_NATIVE_TYPE_CODE_VALUE=ODA のドライバー plugin.xml は、<dataSet> ({2}) の <dataTypeMapping> ({1}) に、無効な nativeDataTypeCode 値 ({0}) を持っています。
manifest_NO_NATIVE_TYPE_NAME_DEFINED=ODA ドライバー plugin.xml に、<dataSet> ({0}) の <dataTypeMapping> 内の nativeDataType 属性がありません。
manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_1=ODA ドライバー plugin.xml に、<dataTypeMapping> ({0}) 内の odaScalarDataType 属性がありません。
manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_2=ODA ドライバー plugin.xml に、<dataTypeMapping> ({0}) の <alternativeOdaDataType> 内の odaScalarDataType 属性がありません。
manifest_INVALID_ODA_SCALAR_DATA_TYPE_VALUE=ODA のドライバー plugin.xml は、<dataTypeMapping> ({1}) 内に無効な odaScalarDataType 値 ({0}) を持っています。
manifest_INVALID_LOG_LEVEL_VALUE=ODA のドライバー plugin.xml は、データ・ソース拡張 ({1}) 内に無効な logLevel 値 ({0}) を持っています。
manifest_MISSING_ELEMENT_IN_EXTENSION_MANIFEST=拡張子 ({0}) 内で要素 {1} が見つかりません。
manifest_NO_LOG_FILENAME_PREFIX_DEFINED=ODA ドライバー plugin.xml に、データ・ソース拡張 ({0}) の <traceLogging> 内の logFileNamePrefix 属性がありません。
#
sortSpec_INVALID_SORT_MODE_SPECIFIED=無効なソートのモードが指定されました\: {0}。
sortSpec_NULL_COLUMN_NAME_SPECIFIED=NULL が列の名前に指定されました。
sortSpec_INVALID_COLUMN_NAME_SPECIFIED=無効な列の名前が指定されました\: {0}。
sortSpec_INVALID_SORT_ORDER_SPECIFIED=無効なソート順が指定されました\: {0}.
sortSpec_NO_DYNAMIC_SORT_KEY_FOR_SORTMODENONE=この sortModeNone をともなう sortSpec は、すべてのダイナミック・ソート・キーを許可しません。
sortSpec_ONE_SORTCOLUMN_FOR_SINGLE_COLUMN_MODE=ソート・カラムはすでに存在します。sortModeSingleColumn モードでは、1 つの最大ダイナミック・ソート・カラムのみが許可されています。
sortSpec_ONE_SORTORDER_FOR_SINGLE_ORDER_MODE=sortModeSingleOrder モードは 1 つのソート順のみをサポートします。
sortSpec_ONLY_IN_SINGLE_ORDER_MODE=sortModeSingleOrder モードのときのみサポートされます。
sortSpec_INDEX_OUT_OF_BOUND=インデックス : {0}、カウント : {1}
#
stringSubUtil_NO_STRING_VALUE_TO_REPLACE=置換する文字列値がありません\: {0}
stringSubUtil_DELIMITER_CANNOT_BE_EMPTY=区切り文字は、空のストリングにしたり、空白文字を含んだりしてはいけません。
stringSubUtil_DELIMITER_CANNOT_BE_NULL=区切り文字は NULL にできません。
stringSubUtil_SUBSTITUTION_LIST_CANNOT_BE_NULL=ストリング置換リストは NULL にできません。
stringSubUtil_SUBSTITUTION_VALUE_CANNOT_BE_NULL=ストリング置換値は NULL にできません。
stringSubUtil_TEXT_STRING_CANNOT_BE_NULL=テキスト・ストリングは NULL にできません。
stringSubUtil_NAME_VALUE_MAP_CANNOT_BE_NULL=名前値マップは NULL にできません。
#
profileProperty_PROFILE_NAME=接続プロファイル名(&P)
profileProperty_PROFILE_STORE_PATH=接続プロファイルストアの URL(&U)
profileProperty_GROUP_PROPERTIES=接続プロファイルの外部参照
