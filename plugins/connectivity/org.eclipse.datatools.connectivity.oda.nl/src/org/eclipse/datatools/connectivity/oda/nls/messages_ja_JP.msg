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
logManager_duplicateName=同じ名前のロガーが存在します。
#
manifest_CANNOT_FIND_EXTENSION=ODA データソース拡張 ({0}) が見つかりません。\nワークスペースのログファイルを参照して、拡張バンドルおよびその依存関係の読み込みに問題がないか確認してください。
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
manifest_UNKNOWN_BUNDLE_LOCATION=ODA 拡張機能 ({0}) のインストールパスが見つかりません。  ODA コンシューマアプリケーションでは、appContext で ResourceIdentifiers を指定してパスを解決する必要があります。
#
sortSpec_INVALID_SORT_MODE_SPECIFIED=無効なソートのモードが指定されました\: {0}。
sortSpec_NULL_COLUMN_NAME_SPECIFIED=NULL が列の名前に指定されました。
sortSpec_INVALID_COLUMN_NAME_SPECIFIED=無効な列の名前が指定されました\: {0}。
sortSpec_INVALID_NULL_ORDERING=Null 順序値が無効です : {0}。
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
#
querySpec_CUSTOM_AGGR_EXCEED_MAX_ARGS=The custom aggregate expression ({0}) has {1} arguments, which exceeds the allowed maximum of {2} arguments.
querySpec_CUSTOM_AGGR_INCOMPATIBLE_DUPL_CHECK=カスタム集計型 ({0}) は、値の重複を無視できません。
querySpec_CUSTOM_AGGR_INCOMPATIBLE_NULL_CHECK=カスタム集計型 ({0}) は、NULL 値を無視できません。
querySpec_CUSTOM_AGGR_LESS_THAN_MIN_ARGS=カスタム集計式 ({0}) には {1} 個の引数がありますが、{2} 個以上の引数を設定してください。
querySpec_CUSTOM_FILTER_EXCEED_MAX_ARGS=The custom filter expression ({0}) has {1} arguments, which exceeds the allowed maximum of {2} arguments.
querySpec_CUSTOM_FILTER_LESS_THAN_MIN_ARGS=カスタムフィルタ式 ({0}) には {1} 個の引数がありますが、{2} 個以上の引数を設定してください。
querySpec_CUSTOM_FUNC_EXCEED_MAX_ARGS=The custom function value expression ({0}) has {1} arguments, which exceeds the allowed maximum of {2} arguments.
querySpec_CUSTOM_FUNC_INCOMPATIBLE_DUPL_CHECK=The custom function type ({0}) cannot ignore duplicate values.
querySpec_CUSTOM_FUNC_LESS_THAN_MIN_ARGS=The custom function value expression ({0}) has {1} arguments, but requires a minimum of {2} arguments.
querySpec_EXTENSION_ID_NOT_FOUND=ID ({1}) を持つ {0} 拡張が見つかりません。
querySpec_ILLEGAL_CONSTRUCTOR_ARG=Illegal {0} constructor argument
querySpec_INCOMPLETE_COMBINED_QUERY=混合クエリの指定が不完全です。
querySpec_INVALID_AGGR_EXPR=集計式が無効です。
querySpec_INVALID_AGGR_HIDE_COLUMN=非表示の列 {0} の集計プロジェクションが無効です。
querySpec_INVALID_ARG=引数 {0} が無効です。
querySpec_INVALID_BASE_QUERY=ベースクエリが無効です。
querySpec_INVALID_CLASS_TYPE_ATTRIBUTE=クラス型 ({0}) が無効です。  {1} 属性には、{2} のインスタンスを指定してください。
querySpec_INVALID_COLUMN_IDENTIFIER=Invalid column reference {0}.
querySpec_INVALID_EXT_POINT_ATTR_VALUE={0} 拡張 ({1}) の値 ({2}) が {3} 属性に対して無効です。
querySpec_INVALID_EXT_POINT_ELEMENT={0} 拡張 ({1}) が無効です。  必要なコンテンツについては、スキーマの定義を参照してください。
querySpec_INVALID_FILTER_EXPR=フィルタ式が無効です。
querySpec_INVALID_RESULT_PROJ=結果プロジェクションが無効です。
querySpec_INVALID_SORT_KEY=ソートキーが無効です。
querySpec_INVALID_SORT_SPEC=ソート順が無効です。
querySpec_INVALID_VALUE_EXPR=Invalid value expression.
querySpec_MAX_ONE_NEGATING_EXPR={0} には複数の負数の式は追加できません。
querySpec_MISSING_ATOMIC_QUERY_TEXT=アトミックベースクエリ内のクエリテキストが見つかりません。
querySpec_MISSING_COMBINED_MATCHING_EXPR=混合条件内に一致する式がありません。
querySpec_MISSING_COMPOSITE_MIN_CHILDREN=合成 {0} には、少なくとも {1} 個の子の式を含めてください。
querySpec_MISSING_EXPR_VARIABLE=フィルタ式 ({0}) に関連付けられている変数が見つかりません。
querySpec_MISSING_EXT_POINT_ATTR_VALUE={0} 拡張 ({1}) に、{3} 要素の {2} 属性値がありません。
querySpec_MISSING_EXT_POINT_ELEMENT={0} 拡張 ({1}) に {2} 要素がありません。
querySpec_MISSING_EXT_POINT_MIN_ELEMENT={0} 拡張 ({1}) には、少なくとも 1 つの {2} 要素を定義してください。
querySpec_NON_DEFINED_COMBINED_OP=混合式演算子の型 ({0}) が dynamicResultSet 拡張レジストリにありません。
querySpec_NON_DEFINED_CUSTOM_AGGR=カスタム集計式 ({0}) が dynamicResultSet 拡張レジストリにありません。
querySpec_NON_DEFINED_CUSTOM_FILTER=カスタムフィルタ式 ({0}) が dynamicResultSet 拡張レジストリにありません。
querySpec_NON_DEFINED_CUSTOM_FUNC=The custom function ({0}) is not found in the dynamicResultSet extension registry.
querySpec_NON_RELATED_QUERYSPEC=混合クエリの式内の列 {0} を、混合クエリのいずれかではないクエリによって修飾することはできません。
querySpec_NONSUPPORTED_NULL_ORDERING=データソースプロバイダは Null の順序の指定をサポートしていません。
querySpec_NONSUPPORTED_VAR_DATA_TYPE=The ODA data type ({0}) of the value expression ({1}) is not supported by the type of custom expression used.
querySpec_NOT_EXPR_INCOMPATIBLE={1} では、ネゲート不可能な式 ({0}) は無効です。
querySpec_NOT_EXPR_MISSING_CHILD=NotExpression ({0}) にネゲート式がありません。
querySpec_NULL_CONSTRUCTOR_3ARGS=Null {0} constructor argument(s): {1}, {2}, {3}.
querySpec_NULL_CONSTRUCTOR_ARG=Null {0} constructor argument
querySpec_UNABLE_ADD_TO_NON_LIST_COLLECTION=既存の値に値を追加することはできません。  既存の値は java.util.List でありません。
querySpec_UNEXPECTED_AGGR_EXPR_EXTENSION=その他の拡張子のカスタム集計式 ({0}) はサポートされていません。
querySpec_UNEXPECTED_AGGR_EXPR_TYPE=カスタム集計式 ({0}) は {1} インスタンスではありません。 カスタム式のインスタンスを作成するには、使用者は ExpressionFactory\#createCustomAggregate を使用してください。
querySpec_UNEXPECTED_CUSTOM_EXPR_EXTENSION=その他の拡張子のカスタムフィルタ式 ({0}) はサポートされていません。
querySpec_UNEXPECTED_CUSTOM_EXPR_TYPE=カスタムフィルタ式 ({0}) は {1} インスタンスではありません。\nカスタムフィルタ式のインスタンスを作成するには、使用者は ExpressionFactory\#createCustomExpression を使用してください。
querySpec_UNEXPECTED_EXPR_VARIABLE_TYPE=この型の ExpressionVariable ({1}) は使用できません。
querySpec_UNEXPECTED_FUNC_EXPR_EXTENSION=その他の拡張子のカスタム関数 ({0}) はサポートされていません。
querySpec_UNEXPECTED_FUNC_EXPR_TYPE=カスタム関数 ({0}) は {1} インスタンスではありません。 カスタム関数のインスタンスを作成するには、使用者は ExpressionFactory\#createCustomFunction を使用してください。
