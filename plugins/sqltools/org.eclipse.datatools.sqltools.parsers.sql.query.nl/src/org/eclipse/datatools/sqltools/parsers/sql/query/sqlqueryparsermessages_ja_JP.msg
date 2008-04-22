# NLS_MESSAGEFORMAT_VAR          needed to handle quotes correctly
#
# File: sqlqueryparsermessage.properties
# Description: Properties file for classes in the SQLQueryParser Plugin

# =============================================================================
# Copyright (c) 2004, 2005 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials 
# are made available under the terms of the Eclipse Public License v1.0
# which is available at
# http://www.eclipse.org/legal/epl-v10.html
# =============================================================================

DataTypeResolver.DATA_TYPE_UNDETERMINED 		= "{0}" のデータ型が不明です。
DataTypeResolver.VARIABLE_TYPE_UNDETERMINED 	= 変数 "{0}" のデータ型が不明です。
DataTypeResolver.DATA_TYPES_INCOMPATIBLE		= "{0}" と "{1}" のデータ型に互換性がありません。

TableReferenceResolver.SCHEMAS_NOT_LOADED   	= データベーススキーマが見つかりません。データベース接続 "{0}" を確認してください。
TableReferenceResolver.UNRESOLVED_COLUMN		= 列 "{0}" のテーブルが見つかりません。
TableReferenceResolver.UNRESOLVED_TABLE			= テーブル "{0}" が見つかりません。
TableReferenceResolver.TABLE_HAS_NO_SUCH_COLUMN	= テーブル "{0}" に列 "{1}" が含まれていません。
TableReferenceResolver.AMBIGUOUS_COLUMN			= 列名 "{0}" があいまいです。
TableReferenceResolver.AMBIGUOUS_TABLE			= テーブル名 "{0}" があいまいです。
TableReferenceResolver.AMBIGUOUS_TABLE_ALIAS	= テーブルのエイリアス "{0}" があいまいです。




#
# following messages to translate names of syntax rules that were violated and caused parser error
# fill-ins - no sentences! first letter should be lower case if not a KEYWORD
#
! uppercase words are keywords and not to be translated
! "predicate" is a SQL language term equivalent to the word "test"
! "term", "factor" are closely related to mathematical terms "term" and "factor"
SQLQueryParser.<alias_name> 					= エイリアス名
SQLQueryParser.<all_or_any_cond>		 		= 量化比較
SQLQueryParser.<as_alias> 						= AS のエイリアス名
SQLQueryParser.<boolean_expression>				= 論理値の式
SQLQueryParser.<boolean_factor> 				= 論理値のファクタ
SQLQueryParser.<boolean_primary> 				= 論理値のプライマリ
SQLQueryParser.<boolean_term> 					= 論理値の項
SQLQueryParser.<boolean_values> 				= truth 値
SQLQueryParser.<case_expression> 				= CASE 式
SQLQueryParser.<case_search_when> 				= WHEN-THEN 条件
SQLQueryParser.<case_search_when_list> 			= WHEN-THEN 条件のリスト
SQLQueryParser.<case_simple_when> 				= WHEN-THEN 式
SQLQueryParser.<case_simple_when_list>	 		= WHEN-THEN 式のリスト
SQLQueryParser.<cast_expression> 				= CAST 式
SQLQueryParser.<column> 						= 列
SQLQueryParser.<column_name> 					= 列名
SQLQueryParser.<column_name_list> 				= 列名のリスト
SQLQueryParser.<column_ref> 					= 列参照
SQLQueryParser.<condition> 						= 条件
SQLQueryParser.<constant> 						= 簡易値
SQLQueryParser.<datatype> 						= データ型
SQLQueryParser.<datatype_array_type> 			= ARRAY データ型
SQLQueryParser.<datatype_binary> 				= バイナリのデータ型
SQLQueryParser.<datatype_predefined>			= 定義済みのデータ型
SQLQueryParser.<datatype_character> 			= 文字のデータ型
SQLQueryParser.<datatype_character_national>	= ナショナルキャラクタのデータ型
SQLQueryParser.<datatype_collection_type> 		= コレクションのデータ型
SQLQueryParser.<datatype_date> 					= 日付のデータ型
SQLQueryParser.<datatype_multiset_type> 		= MULTISET データ型
SQLQueryParser.<datatype_numerical> 			= 数値のデータ型
SQLQueryParser.<datatype_numerical_approximate>	= 近似値のデータ型
SQLQueryParser.<datatype_numerical_exact> 		= 正確値のデータ型
SQLQueryParser.<datatype_numerical_fixed_precision>	= 固定小数点のデータ型
SQLQueryParser.<datatype_numerical_integer> 	= 整数のデータ型
SQLQueryParser.<datatype_opt_size_unit> 		= データ型の長さの乗数 (K、M、または G)
SQLQueryParser.<datatype_time> 					= 時間のデータ型
SQLQueryParser.<datatype_user_defined_distinct>	= 個別のユーザ定義型
SQLQueryParser.<delete_stmt> 					= DELETE 文
SQLQueryParser.<duration> 						= 期間
SQLQueryParser.<exists> 						= EXISTS 述部
SQLQueryParser.<expression> 					= 値式
SQLQueryParser.<expression_commalist> 			= 値式のコンマ区切りリスト
SQLQueryParser.<expression_commalist_multiple_elements>	= 値式のコンマ区切りリスト (複数の要素)
SQLQueryParser.<expression_factor> 				= 値式のファクタ
SQLQueryParser.<expression_term>		 		= 値式の項
SQLQueryParser.<func_ref> 						= 関数の参照
SQLQueryParser.<function> 						= 関数
SQLQueryParser.<grouping> 						= GROUP BY 句
SQLQueryParser.<grouping_exp> 					= グループ式
SQLQueryParser.<grouping_sets> 					= GROUPING SETS
SQLQueryParser.<grouping_sets_element> 			= GROUPING SETS の要素
SQLQueryParser.<grouping_sets_element_exp> 		= GROUPING SETS の要素の式
SQLQueryParser.<grouping_sets_element_exp_list>	= GROUPING SETS の要素の式のリスト
SQLQueryParser.<grouping_sets_element_list> 	= GROUPING SETS の要素のリスト
SQLQueryParser.<grouping_spec> 					= GROUP BY の指定
SQLQueryParser.<grouping_spec_list> 			= GROUP BY の指定のリスト
SQLQueryParser.<identifier> 					= 識別子
SQLQueryParser.<in_cond> 						= IN 述部
SQLQueryParser.<insert_row> 					= 行の挿入
SQLQueryParser.<insert_row_commalist> 			= 挿入行のカンマ区切りリスト
SQLQueryParser.<insert_stmt> 					= INSERT 文
SQLQueryParser.<intervaltest> 					= BETWEEN 述部
SQLQueryParser.<liketest> 						= LIKE 述部
! "predicate" here could also be "test" 
SQLQueryParser.<nulltest> 						= NULL 述部
SQLQueryParser.<opt_all_distinct> 				= ALL または DISTINCT
SQLQueryParser.<opt_as> 						= オプションの AS
SQLQueryParser.<opt_as_alias> 					= オプションの AS のエイリアス名
SQLQueryParser.<opt_as_target_table> 			= オプションの AS のターゲットテーブル
SQLQueryParser.<opt_asc_desc> 					= オプションの ASC または DESC
SQLQueryParser.<opt_case_else> 					= オプションの ELSE 節
SQLQueryParser.<opt_column_name_list> 			= 列名のオプションのリスト
SQLQueryParser.<opt_escape> 					= オプションの ESCAPE 式
SQLQueryParser.<opt_expression_commalist> 		= 値式のオプションのカンマ区切りリスト
SQLQueryParser.<opt_group_by_clause> 			= オプションの GROUP BY 句
SQLQueryParser.<opt_having_clause> 				= オプションの HAVING 句
SQLQueryParser.<opt_join_type> 					= オプションの JOIN 型
SQLQueryParser.<opt_join_type_outer> 			= オプションの OUTER
SQLQueryParser.<opt_order_by_clause> 			= オプションの ORDER BY 句
! "Schema" is a database related term
SQLQueryParser.<opt_schema_dot> 				= オプションのスキーマ修飾子
! "table" is a database related term
SQLQueryParser.<opt_table_correlation> 			= オプションのテーブルエイリアス
SQLQueryParser.<opt_target_column_list> 		= ターゲット列のオプションのリスト
SQLQueryParser.<opt_where_clause> 				= オプションの WHERE 句
SQLQueryParser.<ordering_spec> 					= 順序の指定
SQLQueryParser.<ordering_spec_commalist> 		= 順序の指定のカンマ区切りリスト
SQLQueryParser.<parameter> 						= パラメタ
SQLQueryParser.<project> 						= 選択
SQLQueryParser.<query_combined> 				= 混合クエリ
SQLQueryParser.<query_combined_operator>		= 混合クエリ演算子 UNION、EXCEPT、または INTERSECT
SQLQueryParser.<query_exp>						= クエリ式
SQLQueryParser.<query_exp_root>					= クエリ式
SQLQueryParser.<query_select>					= SELECT クエリ
SQLQueryParser.<query_stmt>						= SQL クエリ文
SQLQueryParser.<query_term>						= クエリ展開
SQLQueryParser.<query_values>					= VALUES テーブルコンストラクタ
SQLQueryParser.<relop>							= リレーショナル演算子
SQLQueryParser.<scalcomp>						= スカラー比較
SQLQueryParser.<schema>							= スキーマ
SQLQueryParser.<selection>						= 選択
SQLQueryParser.<simplecomp>						= 簡易比較
SQLQueryParser.<special_register>				= 特殊レジスタ
SQLQueryParser.<sql_dml_stmt>					= SQL DML 文
SQLQueryParser.<sql_dml_stmt_list>				= SQL DML 文のリスト
SQLQueryParser.<sql_dml_stmt_xspan>				= SQL DML 文のリスト
SQLQueryParser.<subquery>						= サブクエリ
SQLQueryParser.<super_groups>					= CUBE または ROLLUP のグループ化
SQLQueryParser.<super_groups_element>			= グループ式
SQLQueryParser.<super_groups_element_exp>		= グループ式
SQLQueryParser.<super_groups_element_exp_list>	= 式のグループ化のリスト
SQLQueryParser.<super_groups_element_list>		= 式のグループ化のリスト
SQLQueryParser.<table>							= テーブル
SQLQueryParser.<table_correlation>				= テーブルのエイリアス
SQLQueryParser.<table_join>						= ジョインされたテーブル
SQLQueryParser.<table_name_or_alias> 			= テーブル名またはテーブルのエイリアス
SQLQueryParser.<table_query>					= ネストされたテーブルのクエリ
SQLQueryParser.<table_ref>						= テーブル参照
SQLQueryParser.<table_ref_commalist>			= テーブル参照のコンマ区切りリスト
SQLQueryParser.<target_column_list>				= ターゲット列のリスト
SQLQueryParser.<target_table>					= ターゲットテーブル
SQLQueryParser.<update_assignment_clause>		= UPDATE 代入句
SQLQueryParser.<update_assignment_expression>	= UPDATE 代入式
SQLQueryParser.<update_assignment_expression_commalist>	= UPDATE 代入式のコンマ区切りリスト
SQLQueryParser.<update_stmt>					= UPDATE 文
SQLQueryParser.<values_row>						= 値の行
SQLQueryParser.<values_row>						= 値の行
SQLQueryParser.<values_row_commalist>			= 値の行のコンマ区切りリスト
SQLQueryParser.<with_clause>					= WITH 句
SQLQueryParser.<with_table_spec>				= WITH テーブルの指定
SQLQueryParser.<with_table_spec_list>			= WITH テーブルの指定のリスト



SQLQueryDB2Parser.<olap_aggregation_function>	= OLAP 総計関数
SQLQueryDB2Parser.<olap_current_row>			= 現在の行
SQLQueryDB2Parser.<olap_function>				= OLAP 関数
SQLQueryDB2Parser.<olap_group_end>				= FOLLOWING 式
SQLQueryDB2Parser.<olap_group_start>			= PRECEDING 式
SQLQueryDB2Parser.<olap_group_type>				= OLAP グループの型
SQLQueryDB2Parser.<olap_numbering_function>		= OLAP 番号付け関数
SQLQueryDB2Parser.<olap_order_by_expression>	= OLAP ORDER BY 式
SQLQueryDB2Parser.<olap_order_by_list>			= OLAP ORDER BY 式のリスト
SQLQueryDB2Parser.<olap_partitioning_expression_list>	= OLAP パーティション式のリスト
SQLQueryDB2Parser.<olap_partitioning_expression>		= OLAP パーティション式
SQLQueryDB2Parser.<olap_rank_or_dense_rank>				= RANK() または DENSE_RANK()
SQLQueryDB2Parser.<olap_ranking_function>				= OLAP ランク付け関数
SQLQueryDB2Parser.<olap_rows_or_range>					= ROWS または RANGE
SQLQueryDB2Parser.<olap_window_order_clause>			= OLAP ウィンドウの ORDER 句
SQLQueryDB2Parser.<opt_olap_asc_desc>					= OLAP ASC/DESC の指定
SQLQueryDB2Parser.<opt_olap_window_aggregation_group>	= オプションの OLAP ウィンドウの総計グループ
SQLQueryDB2Parser.<opt_olap_window_order_agregration_group>	= オプションの OLAP ウィンドウの ORDER 句の総計グループ 
SQLQueryDB2Parser.<opt_olap_window_order_clause>			= オプションの OLAP ウィンドウの ORDER 句
SQLQueryDB2Parser.<opt_olap_window_parition_clause>			= オプションの OLAP ウィンドウの PARTITION 句

SQLQueryDB2Parser.<opt_xml_element_attributes_function>		= オプションの XMLATTRIBUTES 関数
SQLQueryDB2Parser.<opt_xml_element_content_list>			= XML 要素のコンテキスト式のオプションのリスト
SQLQueryDB2Parser.<opt_xml_element_namespaces_function>		= オプションの XMLNAMESPACES 関数
SQLQueryDB2Parser.<opt_xml_forest_namespaces_function>		= オプションの XMLNAMESPACES 関数
SQLQueryDB2Parser.<xml_agg_function>			= XMLAGG 関数
SQLQueryDB2Parser.<xml_attribute_list>			= XML 属性式のリスト
SQLQueryDB2Parser.<xml_attribute>				= XML 属性式
SQLQueryDB2Parser.<xml_attributes_function>		= XMLATTRIBUTES 関数
SQLQueryDB2Parser.<xml_concat_function>			= XMLCONCAT 関数
SQLQueryDB2Parser.<xml_element_content_list>	= XML 要素のコンテキスト式のリスト
SQLQueryDB2Parser.<xml_element_content>			= XML 要素のコンテキスト式
SQLQueryDB2Parser.<xml_element_function>		= XMLELEMENT 関数
SQLQueryDB2Parser.<xml_forest_element_list>		= XMLFOREST 要素のリスト
SQLQueryDB2Parser.<xml_forest_element>			= XMLFOREST 要素の式
SQLQueryDB2Parser.<xml_forest_function>			= XMLFOREST 関数のXMLSERIALIZE または XML2CLOB
SQLQueryDB2Parser.<xml_function>				= XML 関数 
SQLQueryDB2Parser.<xml_namespace_list>			= XML ネームスペースのリスト
SQLQueryDB2Parser.<xml_namespace_prefix>		= XML ネームスペースの接頭辞
SQLQueryDB2Parser.<xml_namespace_uri>			= XML ネームスペースの URI
SQLQueryDB2Parser.<xml_namespace>				= XML ネームスペース
SQLQueryDB2Parser.<xml_namespaces_function>		= XMLNAMESPACES 関数
SQLQueryDB2Parser.<xml_value_datatype>			= XML 値のデータ型
SQLQueryDB2Parser.<xml_value_function_list>		= XML 値の関数のリスト
SQLQueryDB2Parser.<xml_value_function>			= XML 値の関数
