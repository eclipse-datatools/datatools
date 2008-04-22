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

DataTypeResolver.DATA_TYPE_UNDETERMINED 		= Impossible de déterminer le type de données de "{0}".
DataTypeResolver.VARIABLE_TYPE_UNDETERMINED 	= Impossible de déterminer le type de données de variable "{0}".
DataTypeResolver.DATA_TYPES_INCOMPATIBLE		= Les types de données de "{0}" et "{1}" sont incompatibles.

TableReferenceResolver.SCHEMAS_NOT_LOADED   	= Impossible de trouver les schémas de la base de données. Vérifier la connexion à la base de données "{0}".
TableReferenceResolver.UNRESOLVED_COLUMN		= Impossible de trouver une table pour la colonne "{0}".
TableReferenceResolver.UNRESOLVED_TABLE			= Impossible de trouver une table "{0}".
TableReferenceResolver.TABLE_HAS_NO_SUCH_COLUMN	= La table "{0}" ne contient aucune colonne "{1}".
TableReferenceResolver.AMBIGUOUS_COLUMN			= Le nom de la colonne "{0}" est ambigu.
TableReferenceResolver.AMBIGUOUS_TABLE			= Le nom de la table "{0}" est ambigu.
TableReferenceResolver.AMBIGUOUS_TABLE_ALIAS	= Le pseudo de la table "{0}" est ambigu.




#
# following messages to translate names of syntax rules that were violated and caused parser error
# fill-ins - no sentences! first letter should be lower case if not a KEYWORD
#
! uppercase words are keywords and not to be translated
! "predicate" is a SQL language term equivalent to the word "test"
! "term", "factor" are closely related to mathematical terms "term" and "factor"
SQLQueryParser.<alias_name> 					= nom de pseudo
SQLQueryParser.<all_or_any_cond>		 		= comparaison quantifiée
SQLQueryParser.<as_alias> 						= nom de pseudo AS
SQLQueryParser.<boolean_expression>				= Expression de valeur booléenne
SQLQueryParser.<boolean_factor> 				= Facteur booléen
SQLQueryParser.<boolean_primary> 				= Primaire booléen
SQLQueryParser.<boolean_term> 					= Terme booléen
SQLQueryParser.<boolean_values> 				= valeur vraie
SQLQueryParser.<case_expression> 				= expression CASE
SQLQueryParser.<case_search_when> 				= condition WHEN-THEN
SQLQueryParser.<case_search_when_list> 			= liste des conditions WHEN-THEN
SQLQueryParser.<case_simple_when> 				= expression WHEN-THEN
SQLQueryParser.<case_simple_when_list>	 		= liste des expressions WHEN-THEN
SQLQueryParser.<cast_expression> 				= expression CAST
SQLQueryParser.<column> 						= colonne
SQLQueryParser.<column_name> 					= nom de colonne
SQLQueryParser.<column_name_list> 				= liste de noms de colonnes
SQLQueryParser.<column_ref> 					= référence de colonne
SQLQueryParser.<condition> 						= condition
SQLQueryParser.<constant> 						= valeur simple
SQLQueryParser.<datatype> 						= type de données
SQLQueryParser.<datatype_array_type> 			= type de données ARRAY
SQLQueryParser.<datatype_binary> 				= type de données binaires
SQLQueryParser.<datatype_predefined>			= type de données prédéfinies
SQLQueryParser.<datatype_character> 			= type de données de caractères
SQLQueryParser.<datatype_character_national>	= type de données de caractères nationaux
SQLQueryParser.<datatype_collection_type> 		= type de données de collecte
SQLQueryParser.<datatype_date> 					= type de données de date
SQLQueryParser.<datatype_multiset_type> 		= type de données MULTISET
SQLQueryParser.<datatype_numerical> 			= type de données numériques
SQLQueryParser.<datatype_numerical_approximate>	= type de données numériques approximatives
SQLQueryParser.<datatype_numerical_exact> 		= type de données numériques exactes
SQLQueryParser.<datatype_numerical_fixed_precision>	= type de données numériques de précision fixes
SQLQueryParser.<datatype_numerical_integer> 	= type de données d'entiers
SQLQueryParser.<datatype_opt_size_unit> 		= multiplicateur de longueur de type de données (K, M ou G)
SQLQueryParser.<datatype_time> 					= type de données de temps
SQLQueryParser.<datatype_user_defined_distinct>	= type de données distinct défini par l'utilisateur
SQLQueryParser.<delete_stmt> 					= instruction DELETE
SQLQueryParser.<duration> 						= durée
SQLQueryParser.<exists> 						= prédicat EXISTS
SQLQueryParser.<expression> 					= expression de valeur
SQLQueryParser.<expression_commalist> 			= liste d'expressions de valeurs séparées par des virgules
SQLQueryParser.<expression_commalist_multiple_elements>	= liste d'expressions de valeurs séparées par des virgules (éléments multiples)
SQLQueryParser.<expression_factor> 				= facteur d'expression de valeur
SQLQueryParser.<expression_term>		 		= terme d'expression de valeur
SQLQueryParser.<func_ref> 						= référence de fonction
SQLQueryParser.<function> 						= fonction
SQLQueryParser.<grouping> 						= clause GROUP BY
SQLQueryParser.<grouping_exp> 					= expression de regroupement
SQLQueryParser.<grouping_sets> 					= GROUPING SETS
SQLQueryParser.<grouping_sets_element> 			= élément GROUPING SETS
SQLQueryParser.<grouping_sets_element_exp> 		= expression d'élément GROUPING SETS
SQLQueryParser.<grouping_sets_element_exp_list>	= liste d'expressions d'éléments GROUPING SETS
SQLQueryParser.<grouping_sets_element_list> 	= liste d'éléments GROUPING SETS
SQLQueryParser.<grouping_spec> 					= spécification GROUP BY
SQLQueryParser.<grouping_spec_list> 			= liste de spécifications GROUP BY
SQLQueryParser.<identifier> 					= identificateur
SQLQueryParser.<in_cond> 						= prédicat IN
SQLQueryParser.<insert_row> 					= insérer une ligne
SQLQueryParser.<insert_row_commalist> 			= liste de lignes d'insertion séparées par des virgules
SQLQueryParser.<insert_stmt> 					= instruction INSERT
SQLQueryParser.<intervaltest> 					= prédicat BETWEEN
SQLQueryParser.<liketest> 						= prédicat LIKE
! "predicate" here could also be "test" 
SQLQueryParser.<nulltest> 						= prédicat NULL
SQLQueryParser.<opt_all_distinct> 				= ALL ou DISTINCT
SQLQueryParser.<opt_as> 						= AS facultatif
SQLQueryParser.<opt_as_alias> 					= nom de pseudo AS facultatif
SQLQueryParser.<opt_as_target_table> 			= table cible AS facultative
SQLQueryParser.<opt_asc_desc> 					= ASC ou DESC facultatif
SQLQueryParser.<opt_case_else> 					= cas ELSE facultatif
SQLQueryParser.<opt_column_name_list> 			= liste facultative de noms de colonnes
SQLQueryParser.<opt_escape> 					= expression ESCAPE facultative
SQLQueryParser.<opt_expression_commalist> 		= liste facultative d'expressions de valeurs séparées par des virgules
SQLQueryParser.<opt_group_by_clause> 			= clause GROUP BY facultative
SQLQueryParser.<opt_having_clause> 				= clause HAVING facultative
SQLQueryParser.<opt_join_type> 					= type JOIN facultatif
SQLQueryParser.<opt_join_type_outer> 			= OUTER facultatif
SQLQueryParser.<opt_order_by_clause> 			= clause ORDER BY facultative
! "Schema" is a database related term
SQLQueryParser.<opt_schema_dot> 				= qualificateur de schéma facultatif
! "table" is a database related term
SQLQueryParser.<opt_table_correlation> 			= pseudo de table facultatif
SQLQueryParser.<opt_target_column_list> 		= liste facultative de colonnes cibles
SQLQueryParser.<opt_where_clause> 				= clause WHERE facultative
SQLQueryParser.<ordering_spec> 					= spécification de classement
SQLQueryParser.<ordering_spec_commalist> 		= liste de spécifications de classement séparées par des virgules
SQLQueryParser.<parameter> 						= paramètre
SQLQueryParser.<project> 						= sélection
SQLQueryParser.<query_combined> 				= requête combinée
SQLQueryParser.<query_combined_operator>		= opération de requête combinée UNION, EXCEPT ou INTERSECT
SQLQueryParser.<query_exp>						= expression de requête
SQLQueryParser.<query_exp_root>					= expression de requête
SQLQueryParser.<query_select>					= requête SELECT
SQLQueryParser.<query_stmt>						= instruction de requête SQL
SQLQueryParser.<query_term>						= terme de requête
SQLQueryParser.<query_values>					= constructeur de la table VALUES
SQLQueryParser.<relop>							= opérateur relationnel
SQLQueryParser.<scalcomp>						= comparaison scalaire
SQLQueryParser.<schema>							= schéma
SQLQueryParser.<selection>						= sélection
SQLQueryParser.<simplecomp>						= comparaison simple
SQLQueryParser.<special_register>				= registre spécial
SQLQueryParser.<sql_dml_stmt>					= instruction DML SQL
SQLQueryParser.<sql_dml_stmt_list>				= liste d'instructions DML SQL
SQLQueryParser.<sql_dml_stmt_xspan>				= liste d'instructions DML SQL
SQLQueryParser.<subquery>						= sous-requête
SQLQueryParser.<super_groups>					= regroupement CUBE ou ROLLUP
SQLQueryParser.<super_groups_element>			= expression de regroupement
SQLQueryParser.<super_groups_element_exp>		= expression de regroupement
SQLQueryParser.<super_groups_element_exp_list>	= liste d'expressions de regroupement
SQLQueryParser.<super_groups_element_list>		= liste d'expressions de regroupement
SQLQueryParser.<table>							= table
SQLQueryParser.<table_correlation>				= pseudo de table
SQLQueryParser.<table_join>						= table associée
SQLQueryParser.<table_name_or_alias> 			= nom de table ou pseudo de table
SQLQueryParser.<table_query>					= requête de table imbriquée
SQLQueryParser.<table_ref>						= référence de table
SQLQueryParser.<table_ref_commalist>			= liste de références de tables séparées par des virgules
SQLQueryParser.<target_column_list>				= liste de colonnes cibles
SQLQueryParser.<target_table>					= table cible
SQLQueryParser.<update_assignment_clause>		= clause d'affectation UPDATE
SQLQueryParser.<update_assignment_expression>	= expression d'affectation UPDATE
SQLQueryParser.<update_assignment_expression_commalist>	= liste d'expressions d'affectation de mise à jour séparées par des virgules
SQLQueryParser.<update_stmt>					= instruction UPDATE
SQLQueryParser.<values_row>						= ligne de valeurs
SQLQueryParser.<values_row>						= ligne de valeurs
SQLQueryParser.<values_row_commalist>			= liste de lignes de valeurs séparées par des virgules
SQLQueryParser.<with_clause>					= clause WITH
SQLQueryParser.<with_table_spec>				= spécification de la table WITH
SQLQueryParser.<with_table_spec_list>			= liste de spécifications de table WITH



SQLQueryDB2Parser.<olap_aggregation_function>	= fonction d'agrégation OLAP
SQLQueryDB2Parser.<olap_current_row>			= CURRENT ROW
SQLQueryDB2Parser.<olap_function>				= fonction OLAP
SQLQueryDB2Parser.<olap_group_end>				= expression FOLLOWING
SQLQueryDB2Parser.<olap_group_start>			= expression PRECEDING
SQLQueryDB2Parser.<olap_group_type>				= type de regroupement OLAP
SQLQueryDB2Parser.<olap_numbering_function>		= fonction de numérotage OLAP
SQLQueryDB2Parser.<olap_order_by_expression>	= expression OLAP ORDER BY
SQLQueryDB2Parser.<olap_order_by_list>			= liste d'expressions OLAP ORDER BY
SQLQueryDB2Parser.<olap_partitioning_expression_list>	= liste d'expressions de partitionnement OLAP
SQLQueryDB2Parser.<olap_partitioning_expression>		= expression de partitionnement OLAP
SQLQueryDB2Parser.<olap_rank_or_dense_rank>				= RANK() ou DENSE_RANK()
SQLQueryDB2Parser.<olap_ranking_function>				= fonction de classement OLAP
SQLQueryDB2Parser.<olap_rows_or_range>					= ROWS ou RANGE
SQLQueryDB2Parser.<olap_window_order_clause>			= clause d'ordre de fenêtre OLAP
SQLQueryDB2Parser.<opt_olap_asc_desc>					= spécification OLAP ASC/DESC
SQLQueryDB2Parser.<opt_olap_window_aggregation_group>	= groupe facultatif d'agrégation de fenêtre OLAP
SQLQueryDB2Parser.<opt_olap_window_order_agregration_group>	= groupe facultatif d'agrégation d'ordre de fenêtre OLAP
SQLQueryDB2Parser.<opt_olap_window_order_clause>			= clause facultative d'ordre de fenêtre OLAP
SQLQueryDB2Parser.<opt_olap_window_parition_clause>			= clause facultative de partition de fenêtre OLAP

SQLQueryDB2Parser.<opt_xml_element_attributes_function>		= fonction facultative XMLATTRIBUTES
SQLQueryDB2Parser.<opt_xml_element_content_list>			= liste facultative d'expressions de contenu d'éléments XML
SQLQueryDB2Parser.<opt_xml_element_namespaces_function>		= fonction facultative XMLNAMESPACES
SQLQueryDB2Parser.<opt_xml_forest_namespaces_function>		= fonction facultative XMLNAMESPACES
SQLQueryDB2Parser.<xml_agg_function>			= fonction XMLAGG
SQLQueryDB2Parser.<xml_attribute_list>			= liste d'expressions d'attributs XML
SQLQueryDB2Parser.<xml_attribute>				= expression d'attributs XML
SQLQueryDB2Parser.<xml_attributes_function>		= fonction XMLATTRIBUTES
SQLQueryDB2Parser.<xml_concat_function>			= fonction XMLCONCAT
SQLQueryDB2Parser.<xml_element_content_list>	= liste d'expressions de contenu d'éléments XML
SQLQueryDB2Parser.<xml_element_content>			= expression de contenu d'éléments XML
SQLQueryDB2Parser.<xml_element_function>		= fonction XMLELEMENT
SQLQueryDB2Parser.<xml_forest_element_list>		= liste d'éléments XMLFOREST
SQLQueryDB2Parser.<xml_forest_element>			= expression d'élément XMLFOREST
SQLQueryDB2Parser.<xml_forest_function>			= Fonction XMLFOREST XMLSERIALIZE ou XML2CLOB
SQLQueryDB2Parser.<xml_function>				= fonction XML 
SQLQueryDB2Parser.<xml_namespace_list>			= liste d'espaces nom XML
SQLQueryDB2Parser.<xml_namespace_prefix>		= préfixe d'espace nom XML
SQLQueryDB2Parser.<xml_namespace_uri>			= URI d'espace nom XML
SQLQueryDB2Parser.<xml_namespace>				= espace nom XML
SQLQueryDB2Parser.<xml_namespaces_function>		= fonction XMLNAMESPACES
SQLQueryDB2Parser.<xml_value_datatype>			= type de données de valeurs XML
SQLQueryDB2Parser.<xml_value_function_list>		= liste de fonctions de valeurs XML
SQLQueryDB2Parser.<xml_value_function>			= fonction de valeur XML
