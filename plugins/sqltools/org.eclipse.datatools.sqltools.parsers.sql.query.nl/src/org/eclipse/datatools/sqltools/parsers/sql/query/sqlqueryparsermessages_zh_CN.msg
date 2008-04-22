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

DataTypeResolver.DATA_TYPE_UNDETERMINED 		= 不能确定“{0}”的数据类型。
DataTypeResolver.VARIABLE_TYPE_UNDETERMINED 	= 不能确定变量“{0}”的数据类型。
DataTypeResolver.DATA_TYPES_INCOMPATIBLE		= “{0}”和“{1}”的数据类型不兼容。

TableReferenceResolver.SCHEMAS_NOT_LOADED   	= 找不到数据库架构。检查数据库连接“{0}”。
TableReferenceResolver.UNRESOLVED_COLUMN		= 找不到列“{0}”的表。
TableReferenceResolver.UNRESOLVED_TABLE			= 找不到表“{0}”。
TableReferenceResolver.TABLE_HAS_NO_SUCH_COLUMN	= 表“{0}”未包含列“{1}”。
TableReferenceResolver.AMBIGUOUS_COLUMN			= 列名“{0}”有歧义。
TableReferenceResolver.AMBIGUOUS_TABLE			= 表名“{0}”有歧义。
TableReferenceResolver.AMBIGUOUS_TABLE_ALIAS	= 表别名“{0}”有歧义。




#
# following messages to translate names of syntax rules that were violated and caused parser error
# fill-ins - no sentences! first letter should be lower case if not a KEYWORD
#
! uppercase words are keywords and not to be translated
! "predicate" is a SQL language term equivalent to the word "test"
! "term", "factor" are closely related to mathematical terms "term" and "factor"
SQLQueryParser.<alias_name> 					= 别名
SQLQueryParser.<all_or_any_cond>		 		= 定量比较
SQLQueryParser.<as_alias> 						= AS 别名
SQLQueryParser.<boolean_expression>				= 布尔值表达式
SQLQueryParser.<boolean_factor> 				= 布尔因子
SQLQueryParser.<boolean_primary> 				= 布尔主键
SQLQueryParser.<boolean_term> 					= 布尔项
SQLQueryParser.<boolean_values> 				= 真实值
SQLQueryParser.<case_expression> 				= CASE 表达式
SQLQueryParser.<case_search_when> 				= WHEN-THEN 条件
SQLQueryParser.<case_search_when_list> 			= WHEN-THEN 条件列表
SQLQueryParser.<case_simple_when> 				= WHEN-THEN 表达式
SQLQueryParser.<case_simple_when_list>	 		= WHEN-THEN 表达式列表
SQLQueryParser.<cast_expression> 				= CAST 表达式
SQLQueryParser.<column> 						= 列
SQLQueryParser.<column_name> 					= 列名称
SQLQueryParser.<column_name_list> 				= 列名称列表
SQLQueryParser.<column_ref> 					= 列引用
SQLQueryParser.<condition> 						= 条件
SQLQueryParser.<constant> 						= 简单值
SQLQueryParser.<datatype> 						= 数据类型
SQLQueryParser.<datatype_array_type> 			= ARRAY 数据类型
SQLQueryParser.<datatype_binary> 				= 二进制数据类型
SQLQueryParser.<datatype_predefined>			= 预定义数据类型
SQLQueryParser.<datatype_character> 			= 字符数据类型
SQLQueryParser.<datatype_character_national>	= 国家或地区字符数据类型
SQLQueryParser.<datatype_collection_type> 		= 集合数据类型
SQLQueryParser.<datatype_date> 					= 日期数据类型
SQLQueryParser.<datatype_multiset_type> 		= MULTISET 数据类型
SQLQueryParser.<datatype_numerical> 			= 数字数据类型
SQLQueryParser.<datatype_numerical_approximate>	= 近似数字数据类型
SQLQueryParser.<datatype_numerical_exact> 		= 精确数字数据类型
SQLQueryParser.<datatype_numerical_fixed_precision>	= 固定精度数字数据类型
SQLQueryParser.<datatype_numerical_integer> 	= 整数数据类型
SQLQueryParser.<datatype_opt_size_unit> 		= 数据类型长度乘数(K、M 或 G)
SQLQueryParser.<datatype_time> 					= 时间数据类型
SQLQueryParser.<datatype_user_defined_distinct>	= 单值用户定义数据类型
SQLQueryParser.<delete_stmt> 					= DELETE 语句
SQLQueryParser.<duration> 						= 持续时间
SQLQueryParser.<exists> 						= EXISTS 谓词
SQLQueryParser.<expression> 					= 值表达式
SQLQueryParser.<expression_commalist> 			= 用逗号隔开的值表达式列表
SQLQueryParser.<expression_commalist_multiple_elements>	= 用逗号隔开的值表达式列表（多个元素）
SQLQueryParser.<expression_factor> 				= 值表达式因子
SQLQueryParser.<expression_term>		 		= 值表达式项
SQLQueryParser.<func_ref> 						= 函数引用
SQLQueryParser.<function> 						= 函数
SQLQueryParser.<grouping> 						= GROUP BY 子句
SQLQueryParser.<grouping_exp> 					= 分组表达式
SQLQueryParser.<grouping_sets> 					= GROUPING SETS
SQLQueryParser.<grouping_sets_element> 			= GROUPING SETS 元素
SQLQueryParser.<grouping_sets_element_exp> 		= GROUPING SETS 元素表达式
SQLQueryParser.<grouping_sets_element_exp_list>	= GROUPING SETS 元素表达式列表
SQLQueryParser.<grouping_sets_element_list> 	= GROUPING SETS 元素列表
SQLQueryParser.<grouping_spec> 					= GROUP BY 规范
SQLQueryParser.<grouping_spec_list> 			= GROUP BY 规范列表
SQLQueryParser.<identifier> 					= 标识符
SQLQueryParser.<in_cond> 						= IN 谓词
SQLQueryParser.<insert_row> 					= 插入行
SQLQueryParser.<insert_row_commalist> 			= 用逗号隔开的插入行列表
SQLQueryParser.<insert_stmt> 					= INSERT 语句
SQLQueryParser.<intervaltest> 					= BETWEEN 谓词
SQLQueryParser.<liketest> 						= LIKE 谓词
! "predicate" here could also be "test" 
SQLQueryParser.<nulltest> 						= NULL 谓词
SQLQueryParser.<opt_all_distinct> 				= ALL 或 DISTINCT
SQLQueryParser.<opt_as> 						= 可选 AS
SQLQueryParser.<opt_as_alias> 					= 可选 AS 别名
SQLQueryParser.<opt_as_target_table> 			= 可选 AS 目标表
SQLQueryParser.<opt_asc_desc> 					= 可选 ASC 或 DESC
SQLQueryParser.<opt_case_else> 					= 可选 ELSE CASE
SQLQueryParser.<opt_column_name_list> 			= 可选列名称列表
SQLQueryParser.<opt_escape> 					= 可选 ESCAPE 表达式
SQLQueryParser.<opt_expression_commalist> 		= 用逗号隔开的可选值表达式列表
SQLQueryParser.<opt_group_by_clause> 			= 可选 GROUP BY 子句
SQLQueryParser.<opt_having_clause> 				= 可选 HAVING 子句
SQLQueryParser.<opt_join_type> 					= 可选 JOIN 类型
SQLQueryParser.<opt_join_type_outer> 			= 可选 OUTER
SQLQueryParser.<opt_order_by_clause> 			= 可选 ORDER BY 子句
! "Schema" is a database related term
SQLQueryParser.<opt_schema_dot> 				= 可选架构限定符
! "table" is a database related term
SQLQueryParser.<opt_table_correlation> 			= 可选表别名
SQLQueryParser.<opt_target_column_list> 		= 可选目标列列表
SQLQueryParser.<opt_where_clause> 				= 可选 WHERE 子句
SQLQueryParser.<ordering_spec> 					= 排序规范
SQLQueryParser.<ordering_spec_commalist> 		= 用逗号隔开的排序规范列表
SQLQueryParser.<parameter> 						= 参数
SQLQueryParser.<project> 						= 选择
SQLQueryParser.<query_combined> 				= 组合查询
SQLQueryParser.<query_combined_operator>		= 组合查询运算符 UNION、EXCEPT 或 INTERSECT
SQLQueryParser.<query_exp>						= 查询表达式
SQLQueryParser.<query_exp_root>					= 查询表达式
SQLQueryParser.<query_select>					= SELECT 查询
SQLQueryParser.<query_stmt>						= SQL 查询语句
SQLQueryParser.<query_term>						= 查询项
SQLQueryParser.<query_values>					= VALUES 表构造函数
SQLQueryParser.<relop>							= 关系运算符
SQLQueryParser.<scalcomp>						= 标量比较
SQLQueryParser.<schema>							= 架构
SQLQueryParser.<selection>						= 选择
SQLQueryParser.<simplecomp>						= 简单比较
SQLQueryParser.<special_register>				= 专用寄存器
SQLQueryParser.<sql_dml_stmt>					= SQL DML 语句
SQLQueryParser.<sql_dml_stmt_list>				= SQL DML 语句列表
SQLQueryParser.<sql_dml_stmt_xspan>				= SQL DML 语句列表
SQLQueryParser.<subquery>						= 子查询
SQLQueryParser.<super_groups>					= CUBE 或 ROLLUP 分组
SQLQueryParser.<super_groups_element>			= 分组表达式
SQLQueryParser.<super_groups_element_exp>		= 分组表达式
SQLQueryParser.<super_groups_element_exp_list>	= 分组表达式列表
SQLQueryParser.<super_groups_element_list>		= 分组表达式列表
SQLQueryParser.<table>							= 表
SQLQueryParser.<table_correlation>				= 表别名
SQLQueryParser.<table_join>						= 联接的表
SQLQueryParser.<table_name_or_alias> 			= 表名或表别名
SQLQueryParser.<table_query>					= 嵌套表查询
SQLQueryParser.<table_ref>						= 表引用
SQLQueryParser.<table_ref_commalist>			= 用逗号隔开的表引用列表
SQLQueryParser.<target_column_list>				= 目标列列表
SQLQueryParser.<target_table>					= 目标表
SQLQueryParser.<update_assignment_clause>		= UPDATE 指定子句
SQLQueryParser.<update_assignment_expression>	= UPDATE 指定表达式
SQLQueryParser.<update_assignment_expression_commalist>	= 用逗号隔开的更新指定表达式列表
SQLQueryParser.<update_stmt>					= UPDATE 语句
SQLQueryParser.<values_row>						= 值行
SQLQueryParser.<values_row>						= 值行
SQLQueryParser.<values_row_commalist>			= 用逗号隔开的值行列表
SQLQueryParser.<with_clause>					= WITH 子句
SQLQueryParser.<with_table_spec>				= WITH 表规范
SQLQueryParser.<with_table_spec_list>			= WITH 表规范列表



SQLQueryDB2Parser.<olap_aggregation_function>	= OLAP 聚合函数
SQLQueryDB2Parser.<olap_current_row>			= CURRENT ROW
SQLQueryDB2Parser.<olap_function>				= OLAP 函数
SQLQueryDB2Parser.<olap_group_end>				= FOLLOWING 表达式
SQLQueryDB2Parser.<olap_group_start>			= PRECEDING 表达式
SQLQueryDB2Parser.<olap_group_type>				= OLAP 分组类型
SQLQueryDB2Parser.<olap_numbering_function>		= OLAP 编号函数
SQLQueryDB2Parser.<olap_order_by_expression>	= OLAP ORDER BY 表达式
SQLQueryDB2Parser.<olap_order_by_list>			= OLAP ORDER BY 表达式列表
SQLQueryDB2Parser.<olap_partitioning_expression_list>	= OLAP 分区表达式列表
SQLQueryDB2Parser.<olap_partitioning_expression>		= OLAP 分区表达式
SQLQueryDB2Parser.<olap_rank_or_dense_rank>				= RANK() 或 DENSE_RANK()
SQLQueryDB2Parser.<olap_ranking_function>				= OLAP 评级函数
SQLQueryDB2Parser.<olap_rows_or_range>					= ROWS 或 RANGE
SQLQueryDB2Parser.<olap_window_order_clause>			= OLAP 窗口排序子句
SQLQueryDB2Parser.<opt_olap_asc_desc>					= OLAP ASC/DESC 规范
SQLQueryDB2Parser.<opt_olap_window_aggregation_group>	= 可选 OLAP 窗口聚合组
SQLQueryDB2Parser.<opt_olap_window_order_agregration_group>	= 可选 OLAP 窗口排序聚合组
SQLQueryDB2Parser.<opt_olap_window_order_clause>			= 可选 OLAP 窗口排序子句
SQLQueryDB2Parser.<opt_olap_window_parition_clause>			= 可选 OLAP 窗口分区子句

SQLQueryDB2Parser.<opt_xml_element_attributes_function>		= 可选 XMLATTRIBUTES 函数
SQLQueryDB2Parser.<opt_xml_element_content_list>			= 可选 XML 元素内容表达式列表
SQLQueryDB2Parser.<opt_xml_element_namespaces_function>		= 可选 XMLNAMESPACES 函数
SQLQueryDB2Parser.<opt_xml_forest_namespaces_function>		= 可选 XMLNAMESPACES 函数
SQLQueryDB2Parser.<xml_agg_function>			= XMLAGG 函数
SQLQueryDB2Parser.<xml_attribute_list>			= XML 属性表达式列表
SQLQueryDB2Parser.<xml_attribute>				= XML 属性表达式
SQLQueryDB2Parser.<xml_attributes_function>		= XMLATTRIBUTES 函数
SQLQueryDB2Parser.<xml_concat_function>			= XMLCONCAT 函数
SQLQueryDB2Parser.<xml_element_content_list>	= 可选 XML 元素内容表达式
SQLQueryDB2Parser.<xml_element_content>			= XML 元素内容表达式
SQLQueryDB2Parser.<xml_element_function>		= XMLELEMENT 函数
SQLQueryDB2Parser.<xml_forest_element_list>		= XML 森林元素列表
SQLQueryDB2Parser.<xml_forest_element>			= XMLFOREST 元素表达式
SQLQueryDB2Parser.<xml_forest_function>			= XMLFOREST 函数 XMLSERIALIZE 或 XML2CLOB
SQLQueryDB2Parser.<xml_function>				= XML 函数 
SQLQueryDB2Parser.<xml_namespace_list>			= XML 名称空间列表
SQLQueryDB2Parser.<xml_namespace_prefix>		= XML 名称空间前缀
SQLQueryDB2Parser.<xml_namespace_uri>			= XML 名称空间 URI
SQLQueryDB2Parser.<xml_namespace>				= XML 名称空间
SQLQueryDB2Parser.<xml_namespaces_function>		= XMLNAMESPACES 函数
SQLQueryDB2Parser.<xml_value_datatype>			= XML 值数据类型
SQLQueryDB2Parser.<xml_value_function_list>		= XML 值函数列表
SQLQueryDB2Parser.<xml_value_function>			= XML 值函数
