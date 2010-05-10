# NLS_MESSAGEFORMAT_VAR          needed to handle quotes correctly
#
# File: sqlqueryparsermessage.properties
# Description: Properties file for classes in the SQLQueryParser Plugin

# ==============================================================================
# Copyright (c) 2004, 2005 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials 
# are made available under the terms of the Eclipse Public License v1.0
# which is available at
# http://www.eclipse.org/legal/epl-v10.html
# ==============================================================================

DataTypeResolver.DATA_TYPE_UNDETERMINED 		= "{0}"의 데이터 형식을 확인할 수 없습니다.
DataTypeResolver.VARIABLE_TYPE_UNDETERMINED 	= 변수 "{0}"의 데이터 형식을 확인할 수 없습니다.
DataTypeResolver.DATA_TYPES_INCOMPATIBLE		= "{0}"와(과) "{1}"의 데이터 형식은 호환하지 않습니다.

TableReferenceResolver.SCHEMAS_NOT_LOADED   	= 데이터베이스 스키마를 찾을 수 없습니다. 데이터베이스 연결 "{0}"을(를) 확인하십시오.
TableReferenceResolver.UNRESOLVED_COLUMN		= 열 "{0}"의 테이블을 찾을 수 없습니다.
TableReferenceResolver.UNRESOLVED_TABLE			= 테이블 "{0}"을(를) 찾을 수 없습니다.
TableReferenceResolver.TABLE_HAS_NO_SUCH_COLUMN	= 테이블 "{0}"에 열 "{1}"이(가) 없습니다.
TableReferenceResolver.AMBIGUOUS_COLUMN			= 열 이름 "{0}"이(가) 모호합니다.
TableReferenceResolver.AMBIGUOUS_TABLE			= 테이블 이름 "{0}"이(가) 모호합니다.
TableReferenceResolver.AMBIGUOUS_TABLE_ALIAS	= 테이블 별칭 "{0}"이(가) 모호합니다.

RoutineReferenceResolver.UNRESOLVED_PROC        = 프로시저를 찾을 수 없습니다.
RoutineReferenceResolver.INCORRECT_PARM_COUNT	= 매개 변수 수가 잘못되었습니다.

#
# following messages to translate names of syntax rules that were violated and caused parser error
# fill-ins - no sentences! first letter should be lower case if not a KEYWORD
#
! uppercase words are keywords and not to be translated
! "predicate" is a SQL language term equivalent to the word "test"
! "term", "factor" are closely related to mathematical terms "term" and "factor"
SQLQueryParser.<alias_name> 					= 별칭 이름
SQLQueryParser.<all_or_any_cond>		 		= 정량화한 비교
SQLQueryParser.<as_alias> 						= AS 별칭 이름
SQLQueryParser.<boolean_expression>				= 부울 값 식
SQLQueryParser.<boolean_factor> 				= 부울 인수
SQLQueryParser.<boolean_primary> 				= 부울 기본
SQLQueryParser.<boolean_term> 					= 부울 항
SQLQueryParser.<boolean_values> 				= 사실 값
SQLQueryParser.<case_expression> 				= CASE 식
SQLQueryParser.<case_search_when> 				= WHEN-THEN 조건
SQLQueryParser.<case_search_when_list> 			= WHEN-THEN 조건 목록
SQLQueryParser.<case_simple_when> 				= WHEN-THEN 식
SQLQueryParser.<case_simple_when_list>	 		= WHEN-THEN 식 목록
SQLQueryParser.<cast_expression> 				= CAST 식
SQLQueryParser.<column> 						= 열
SQLQueryParser.<column_name> 					= 열 이름
SQLQueryParser.<column_name_list> 				= 열 이름 목록
SQLQueryParser.<column_ref> 					= 열 참조
SQLQueryParser.<condition> 						= 조건
SQLQueryParser.<constant> 						= 단순 값
SQLQueryParser.<datatype> 						= 데이터 형식
SQLQueryParser.<datatype_array_type> 			= ARRAY 데이터 형식
SQLQueryParser.<datatype_binary> 				= 이진 데이터 형식
SQLQueryParser.<datatype_predefined>			= 미리 정의된 데이터 형식
SQLQueryParser.<datatype_character> 			= 문자 데이터 형식
SQLQueryParser.<datatype_character_national>	= 국가별 문자 데이터 형식
SQLQueryParser.<datatype_collection_type> 		= 컬렉션 데이터 형식
SQLQueryParser.<datatype_date> 					= 날짜 데이터 형식
SQLQueryParser.<datatype_multiset_type> 		= MULTISET 데이터 형식
SQLQueryParser.<datatype_numerical> 			= 숫자 데이터 형식
SQLQueryParser.<datatype_numerical_approximate>	= 근사치 데이터 형식
SQLQueryParser.<datatype_numerical_exact> 		= 정확한 수치 데이터 형식
SQLQueryParser.<datatype_numerical_fixed_precision>	= 고정 정밀도 숫자 데이터 형식
SQLQueryParser.<datatype_numerical_integer> 	= 정수 데이터 형식
SQLQueryParser.<datatype_opt_size_unit> 		= 데이터 형식 승수(K, M 또는 G)
SQLQueryParser.<datatype_time> 					= 시간 데이터 형식
SQLQueryParser.<datatype_user_defined_distinct>	= 고유 사용자 정의 데이터 형식
SQLQueryParser.<delete_stmt> 					= DELETE 문
SQLQueryParser.<duration> 						= 기간
SQLQueryParser.<exists> 						= EXISTS 검사
SQLQueryParser.<expression> 					= 값 식
SQLQueryParser.<expression_commalist> 			= 값 식의 쉼표로 구분된 목록
SQLQueryParser.<expression_commalist_multiple_elements>	= 값 식의 쉼표로 구분된 목록(여러 요소)
SQLQueryParser.<expression_factor> 				= 값 식 인수
SQLQueryParser.<expression_term>		 		= 값 식 항
SQLQueryParser.<func_ref> 						= 함수 참조
SQLQueryParser.<function> 						= 함수
SQLQueryParser.<grouping> 						= GROUP BY 절
SQLQueryParser.<grouping_exp> 					= 그룹화 문
SQLQueryParser.<grouping_sets> 					= GROUPING SETS
SQLQueryParser.<grouping_sets_element> 			= GROUPING SETS 요소
SQLQueryParser.<grouping_sets_element_exp> 		= GROUPING SETS 요소 식
SQLQueryParser.<grouping_sets_element_exp_list>	= GROUPING SETS 요소 식 목록
SQLQueryParser.<grouping_sets_element_list> 	= GROUPING SETS 요소 목록
SQLQueryParser.<grouping_spec> 					= GROUP BY 사양
SQLQueryParser.<grouping_spec_list> 			= GROUP BY 사양 목록
SQLQueryParser.<identifier> 					= ID
SQLQueryParser.<in_cond> 						= IN 검사
SQLQueryParser.<insert_row> 					= 행 삽입
SQLQueryParser.<insert_row_commalist> 			= 쉼표로 구분된 행 삽입 목록
SQLQueryParser.<insert_stmt> 					= INSERT 문
SQLQueryParser.<intervaltest> 					= BETWEEN 검사
SQLQueryParser.<liketest> 						= LIKE 검사
! "predicate" here could also be "test" 
SQLQueryParser.<nulltest> 						= NULL 검사
SQLQueryParser.<opt_all_distinct> 				= ALL 또는 DISTINCT
SQLQueryParser.<opt_as> 						= 옵션 AS
SQLQueryParser.<opt_as_alias> 					= 옵션 AS 별칭 이름
SQLQueryParser.<opt_as_target_table> 			= 옵션 AS 대상 테이블
SQLQueryParser.<opt_asc_desc> 					= 옵션 ASC 또는 DESC
SQLQueryParser.<opt_case_else> 					= 옵션 ELSE 사례
SQLQueryParser.<opt_column_name_list> 			= 열 이름 옵션 목록
SQLQueryParser.<opt_escape> 					= 옵션 ESCAPE 식
SQLQueryParser.<opt_expression_commalist> 		= 값 식의 쉼표로 구분된 옵션 목록
SQLQueryParser.<opt_group_by_clause> 			= 옵션 GROUP BY 절
SQLQueryParser.<opt_having_clause> 				= 옵션 HAVING 절
SQLQueryParser.<opt_join_type> 					= 옵션 JOIN 형식
SQLQueryParser.<opt_join_type_outer> 			= 옵션 OUTER
SQLQueryParser.<opt_order_by_clause> 			= 옵션 ORDER BY 절
! "Schema" is a database related term
SQLQueryParser.<opt_schema_dot> 				= 옵션 스키마 한정자
! "table" is a database related term
SQLQueryParser.<opt_table_correlation> 			= 옵션 테이블 별칭
SQLQueryParser.<opt_target_column_list> 		= 대상 열 옵션 목록
SQLQueryParser.<opt_where_clause> 				= 옵션 WHERE 절
SQLQueryParser.<ordering_spec> 					= 순서 사양
SQLQueryParser.<ordering_spec_commalist> 		= 순서 사양의 쉼표로 구분된 목록
SQLQueryParser.<parameter> 						= 매개 변수
SQLQueryParser.<project> 						= 선택 항목
SQLQueryParser.<query_combined> 				= 통합 쿼리
SQLQueryParser.<query_combined_operator>		= 통합 쿼리 연산자 UNION, EXCEPT 또는 INTERSECT
SQLQueryParser.<query_exp>						= 쿼리 문
SQLQueryParser.<query_exp_root>					= 쿼리 문
SQLQueryParser.<query_select>					= SELECT 쿼리
SQLQueryParser.<query_stmt>						= SQL 쿼리 문
SQLQueryParser.<query_term>						= 쿼리 항
SQLQueryParser.<query_values>					= VALUES 테이블 생성자
SQLQueryParser.<relop>							= 관계형 연산자
SQLQueryParser.<scalcomp>						= 스칼라 비교
SQLQueryParser.<schema>							= 스키마
SQLQueryParser.<selection>						= 선택 항목
SQLQueryParser.<simplecomp>						= 단순 비교
SQLQueryParser.<special_register>				= 특별 등록
SQLQueryParser.<sql_dml_stmt>					= SQL DML 문
SQLQueryParser.<sql_dml_stmt_list>				= SQL DML 문 목록
SQLQueryParser.<sql_dml_stmt_xspan>				= SQL DML 문 목록
SQLQueryParser.<subquery>						= 하위 쿼리
SQLQueryParser.<super_groups>					= CUBE 또는 ROLLUP 그룹화
SQLQueryParser.<super_groups_element>			= 그룹화 문
SQLQueryParser.<super_groups_element_exp>		= 그룹화 문
SQLQueryParser.<super_groups_element_exp_list>	= 그룹화 문 목록
SQLQueryParser.<super_groups_element_list>		= 그룹화 문 목록
SQLQueryParser.<table>							= 테이블
SQLQueryParser.<table_correlation>				= 테이블 별칭
SQLQueryParser.<table_join>						= 조인된 테이블
SQLQueryParser.<table_name_or_alias> 			= 테이블 이름 또는 테이블 별칭
SQLQueryParser.<table_query>					= 중첩된 테이블 쿼리
SQLQueryParser.<table_ref>						= 테이블 참조
SQLQueryParser.<table_ref_commalist>			= 쉼표로 구분된 테이블 참조의 목록
SQLQueryParser.<target_column_list>				= 대상 열 목록
SQLQueryParser.<target_table>					= 대상 테이블
SQLQueryParser.<update_assignment_clause>		= UPDATE 할당 절
SQLQueryParser.<update_assignment_expression>	= UPDATE 할당 식
SQLQueryParser.<update_assignment_expression_commalist>	= 쉼표로 구분된 UPDATE 할당 절 목록
SQLQueryParser.<update_stmt>					= UPDATE 문
SQLQueryParser.<values_row>						= 값 행
SQLQueryParser.<values_row>						= 값 행
SQLQueryParser.<values_row_commalist>			= 쉼표로 구분된 값 행 목록
SQLQueryParser.<with_clause>					= WITH 절
SQLQueryParser.<with_table_spec>				= WITH 테이블 사양
SQLQueryParser.<with_table_spec_list>			= WITH BY 사양 목록



SQLQueryDB2Parser.<olap_aggregation_function>	= OLAP 집계 함수
SQLQueryDB2Parser.<olap_current_row>			= CURRENT ROW
SQLQueryDB2Parser.<olap_function>				= OLAP 함수
SQLQueryDB2Parser.<olap_group_end>				= FOLLOWING 식
SQLQueryDB2Parser.<olap_group_start>			= PRECEDING 식
SQLQueryDB2Parser.<olap_group_type>				= OLAP 그룹화 형식
SQLQueryDB2Parser.<olap_numbering_function>		= OLAP 번호 매기기 함수
SQLQueryDB2Parser.<olap_order_by_expression>	= OLAP ORDER BY 식
SQLQueryDB2Parser.<olap_order_by_list>			= OLAP ORDER BY 식 목록
SQLQueryDB2Parser.<olap_partitioning_expression_list>	= OLAP 분할 식 목록
SQLQueryDB2Parser.<olap_partitioning_expression>		= OLAP 분할 식
SQLQueryDB2Parser.<olap_rank_or_dense_rank>				= RANK() 또는 DENSE_RANK()
SQLQueryDB2Parser.<olap_ranking_function>				= OLAP 순위 함수
SQLQueryDB2Parser.<olap_rows_or_range>					= ROWS 또는 RANGE
SQLQueryDB2Parser.<olap_window_order_clause>			= OLAP 창 정렬 절
SQLQueryDB2Parser.<opt_olap_asc_desc>					= OLAP ASC/DESC 사양
SQLQueryDB2Parser.<opt_olap_window_aggregation_group>	= 옵션 OLAP 창 집계 그룹
SQLQueryDB2Parser.<opt_olap_window_order_agregration_group>	= 옵션 OLAP 창 정렬 집계 그룹
SQLQueryDB2Parser.<opt_olap_window_order_clause>			= 옵션 OLAP 창 정렬 절
SQLQueryDB2Parser.<opt_olap_window_parition_clause>			= 옵션 OLAP 창 파티션 절

SQLQueryDB2Parser.<opt_xml_element_attributes_function>		= 옵션 XMLATTRIBUTES 함수
SQLQueryDB2Parser.<opt_xml_element_content_list>			= 옵션 XML 요소 내용 식
SQLQueryDB2Parser.<opt_xml_element_namespaces_function>		= 옵션 XMLNAMESPACES 함수
SQLQueryDB2Parser.<opt_xml_forest_namespaces_function>		= 옵션 XMLNAMESPACES 함수
SQLQueryDB2Parser.<xml_agg_function>			= XMLAGG 함수
SQLQueryDB2Parser.<xml_attribute_list>			= XML 특성 식 목록
SQLQueryDB2Parser.<xml_attribute>				= XML 특성 식
SQLQueryDB2Parser.<xml_attributes_function>		= XMLATTRIBUTES 함수
SQLQueryDB2Parser.<xml_concat_function>			= XMLCONCAT 함수
SQLQueryDB2Parser.<xml_element_content_list>	= XML 요소 내용 식 목록
SQLQueryDB2Parser.<xml_element_content>			= XML 요소 내용 식
SQLQueryDB2Parser.<xml_element_function>		= XMLELEMENT 함수
SQLQueryDB2Parser.<xml_forest_element_list>		= XML SETS 포리스트 요소 목록
SQLQueryDB2Parser.<xml_forest_element>			= XMLFOREST 요소 식
SQLQueryDB2Parser.<xml_forest_function>			= XMLFOREST 함수 XMLSERIALIZE 또는 XML2CLOB
SQLQueryDB2Parser.<xml_function>				= XML 함수 
SQLQueryDB2Parser.<xml_namespace_list>			= XML 네임스페이스 목록
SQLQueryDB2Parser.<xml_namespace_prefix>		= XML 네임스페이스 접두사
SQLQueryDB2Parser.<xml_namespace_uri>			= XML 네임스페이스 URI
SQLQueryDB2Parser.<xml_namespace>				= XML 네임스페이스
SQLQueryDB2Parser.<xml_namespaces_function>		= XMLNAMESPACES 함수
SQLQueryDB2Parser.<xml_value_datatype>			= XML 값 데이터 형식
SQLQueryDB2Parser.<xml_value_function_list>		= XML 값 함수 목록
SQLQueryDB2Parser.<xml_value_function>			= XML 값 함수


