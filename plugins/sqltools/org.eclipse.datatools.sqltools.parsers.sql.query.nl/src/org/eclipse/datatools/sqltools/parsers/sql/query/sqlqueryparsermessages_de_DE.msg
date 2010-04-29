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

DataTypeResolver.DATA_TYPE_UNDETERMINED 		= Der Datentyp von "{0}" konnte nicht ermittelt werden.
DataTypeResolver.VARIABLE_TYPE_UNDETERMINED 	= Der Datentyp der Variable "{0}" konnte nicht ermittelt werden.
DataTypeResolver.DATA_TYPES_INCOMPATIBLE		= Die Datentypen "{0}" und "{1}" sind nicht kompatibel.

TableReferenceResolver.SCHEMAS_NOT_LOADED   	= Datenbankschemata nicht gefunden. Prüfen Sie die Datenbankverbindung "{0}".
TableReferenceResolver.UNRESOLVED_COLUMN		= Tabelle für Spalte "{0}" nicht gefunden
TableReferenceResolver.UNRESOLVED_TABLE			= Tabelle "{0}" nicht gefunden
TableReferenceResolver.TABLE_HAS_NO_SUCH_COLUMN	= Tabelle "{0}" enthält keine Spalte "{1}".
TableReferenceResolver.AMBIGUOUS_COLUMN			= Spaltenname "{0}" ist zweideutig.
TableReferenceResolver.AMBIGUOUS_TABLE			= Tabellenname "{0}" ist zweideutig.
TableReferenceResolver.AMBIGUOUS_TABLE_ALIAS	= Tabellenalias "{0}" ist zweideutig.

RoutineReferenceResolver.UNRESOLVED_PROC        = Unable to find procedure.
RoutineReferenceResolver.INCORRECT_PARM_COUNT	= Incorrect number of parameters.

#
# following messages to translate names of syntax rules that were violated and caused parser error
# fill-ins - no sentences! first letter should be lower case if not a KEYWORD
#
! uppercase words are keywords and not to be translated
! "predicate" is a SQL language term equivalent to the word "test"
! "term", "factor" are closely related to mathematical terms "term" and "factor"
SQLQueryParser.<alias_name> 					= Alias-Name
SQLQueryParser.<all_or_any_cond>		 		= quantifizierter Vergleich
SQLQueryParser.<as_alias> 						= AS-Aliasname
SQLQueryParser.<boolean_expression>				= Boolescher Werteausdruck
SQLQueryParser.<boolean_factor> 				= Boolescher Faktor
SQLQueryParser.<boolean_primary> 				= Boolesche Primärzahl
SQLQueryParser.<boolean_term> 					= Boolescher Begriff
SQLQueryParser.<boolean_values> 				= Wahrheitswert
SQLQueryParser.<case_expression> 				= CASE-Ausdruck
SQLQueryParser.<case_search_when> 				= WHEN-THEN Bedingung
SQLQueryParser.<case_search_when_list> 			= Liste der WHEN-THEN-Bedingungen
SQLQueryParser.<case_simple_when> 				= WHEN-THEN-Ausdruck
SQLQueryParser.<case_simple_when_list>	 		= Liste der WHEN-THEN-Ausdrücke
SQLQueryParser.<cast_expression> 				= CAST-Ausdruck
SQLQueryParser.<column> 						= Spalte
SQLQueryParser.<column_name> 					= Spaltenname
SQLQueryParser.<column_name_list> 				= Liste der Spaltennamen
SQLQueryParser.<column_ref> 					= Spaltenreferenz
SQLQueryParser.<condition> 						= Bedingung
SQLQueryParser.<constant> 						= einfacher Wert
SQLQueryParser.<datatype> 						= Datentyp
SQLQueryParser.<datatype_array_type> 			= ARRAY-Datentyp
SQLQueryParser.<datatype_binary> 				= binärer Datentyp
SQLQueryParser.<datatype_predefined>			= vordefinierter Datentyp
SQLQueryParser.<datatype_character> 			= Zeichen-Datentyp
SQLQueryParser.<datatype_character_national>	= Datentyp für nationales Zeichen
SQLQueryParser.<datatype_collection_type> 		= Sammlung-Datentyp
SQLQueryParser.<datatype_date> 					= Datums-Datentyp
SQLQueryParser.<datatype_multiset_type> 		= MULTISET-Datentyp
SQLQueryParser.<datatype_numerical> 			= numerischer Datentyp
SQLQueryParser.<datatype_numerical_approximate>	= Datentyp für ungefähre numerische Angaben
SQLQueryParser.<datatype_numerical_exact> 		= Datentyp für exakte numerische Angaben
SQLQueryParser.<datatype_numerical_fixed_precision>	= Datentyp für numerische Angaben mit fester Präzision
SQLQueryParser.<datatype_numerical_integer> 	= Ganzzahl-Datentyp
SQLQueryParser.<datatype_opt_size_unit> 		= Datentyp für Längenmultiplikator (K, M oder G)
SQLQueryParser.<datatype_time> 					= Zeit-Datentyp
SQLQueryParser.<datatype_user_defined_distinct>	= Bestimmter benutzerdefinierter Typ
SQLQueryParser.<delete_stmt> 					= DELETE-Anweisung
SQLQueryParser.<duration> 						= Dauer
SQLQueryParser.<exists> 						= EXISTS-Prädikat
SQLQueryParser.<expression> 					= Wertausdruck
SQLQueryParser.<expression_commalist> 			= kommagetrennte Liste von Wertausdrücken
SQLQueryParser.<expression_commalist_multiple_elements>	= kommagetrennte Liste von Wertausdrücken (mehrere Elemente)
SQLQueryParser.<expression_factor> 				= Wertausdrucksfaktor
SQLQueryParser.<expression_term>		 		= Wertausdrucksbegriff
SQLQueryParser.<func_ref> 						= Funktionsreferenz
SQLQueryParser.<function> 						= Funktion
SQLQueryParser.<grouping> 						= GROUP BY-Klausel
SQLQueryParser.<grouping_exp> 					= Gruppierungsausdruck
SQLQueryParser.<grouping_sets> 					= GROUPING SETS
SQLQueryParser.<grouping_sets_element> 			= GROUPING SETS-Element
SQLQueryParser.<grouping_sets_element_exp> 		= GROUPING SETS-Elementausdruck
SQLQueryParser.<grouping_sets_element_exp_list>	= Liste der GROUPING SETS-Elementausdrücke
SQLQueryParser.<grouping_sets_element_list> 	= Liste der GROUPING SETS-Elemente
SQLQueryParser.<grouping_spec> 					= GROUP BY-Spezifikation
SQLQueryParser.<grouping_spec_list> 			= Liste der GROUP BY-Spezifikationen
SQLQueryParser.<identifier> 					= Kennung
SQLQueryParser.<in_cond> 						= IN-Prädikat
SQLQueryParser.<insert_row> 					= Zeile einfügen
SQLQueryParser.<insert_row_commalist> 			= kommagetrennte Liste von Einfügungszeilen
SQLQueryParser.<insert_stmt> 					= INSERT-Anweisung
SQLQueryParser.<intervaltest> 					= BETWEEN-Prädikat
SQLQueryParser.<liketest> 						= LIKE-Prädikat
! "predicate" here could also be "test" 
SQLQueryParser.<nulltest> 						= NULL-Prädikat
SQLQueryParser.<opt_all_distinct> 				= ALL oder DISTINCT
SQLQueryParser.<opt_as> 						= optionales AS
SQLQueryParser.<opt_as_alias> 					= optionaler AS-Aliasname
SQLQueryParser.<opt_as_target_table> 			= optionale AS-Zieltabelle
SQLQueryParser.<opt_asc_desc> 					= optionales ASC oder DESC
SQLQueryParser.<opt_case_else> 					= optionaler ELSE-Case
SQLQueryParser.<opt_column_name_list> 			= optionale Liste der Spaltennamen
SQLQueryParser.<opt_escape> 					= optionaler ESCAPE-Ausdruck
SQLQueryParser.<opt_expression_commalist> 		= optionale kommagetrennte Liste von Wertausdrücken
SQLQueryParser.<opt_group_by_clause> 			= optionale GROUP BY-Klausel
SQLQueryParser.<opt_having_clause> 				= optionale HAVING-Klausel
SQLQueryParser.<opt_join_type> 					= optionaler JOIN-Typ
SQLQueryParser.<opt_join_type_outer> 			= optionales OUTER
SQLQueryParser.<opt_order_by_clause> 			= optionale ORDER BY-Klausel
! "Schema" is a database related term
SQLQueryParser.<opt_schema_dot> 				= optionaler Schemabezeichner
! "table" is a database related term
SQLQueryParser.<opt_table_correlation> 			= optionales Tabellenalias
SQLQueryParser.<opt_target_column_list> 		= optionale Liste der Zielspalten
SQLQueryParser.<opt_where_clause> 				= optionale WHERE-Klausel
SQLQueryParser.<ordering_spec> 					= Reihenfolgenspezifikation
SQLQueryParser.<ordering_spec_commalist> 		= kommagetrennte Liste der Reihenfolgenspezifikationen
SQLQueryParser.<parameter> 						= Parameter
SQLQueryParser.<project> 						= Auswahl
SQLQueryParser.<query_combined> 				= kombinierte Abfrage
SQLQueryParser.<query_combined_operator>		= kombinierter Abfragenoperator UNION, EXCEPT oder INTERSECT
SQLQueryParser.<query_exp>						= Abfrageausdruck
SQLQueryParser.<query_exp_root>					= Abfrageausdruck
SQLQueryParser.<query_select>					= SELECT-Abfrage
SQLQueryParser.<query_stmt>						= SQL-Abfrageanweisung
SQLQueryParser.<query_term>						= Abfragebegriff
SQLQueryParser.<query_values>					= VALUES-Tabellenkonstruktor
SQLQueryParser.<relop>							= relationaler Operator
SQLQueryParser.<scalcomp>						= Skalarvergleich
SQLQueryParser.<schema>							= Schema
SQLQueryParser.<selection>						= Auswahl
SQLQueryParser.<simplecomp>						= einfacher Vergleich
SQLQueryParser.<special_register>				= spezielles Register
SQLQueryParser.<sql_dml_stmt>					= SQL DML-Anweisung
SQLQueryParser.<sql_dml_stmt_list>				= Liste der SQL DML-Anweisungen
SQLQueryParser.<sql_dml_stmt_xspan>				= Liste der SQL DML-Anweisungen
SQLQueryParser.<subquery>						= Teilabfrage
SQLQueryParser.<super_groups>					= CUBE- oder ROLLUP-Gruppierung
SQLQueryParser.<super_groups_element>			= Gruppierungsausdruck
SQLQueryParser.<super_groups_element_exp>		= Gruppierungsausdruck
SQLQueryParser.<super_groups_element_exp_list>	= Liste der Gruppierungsausdrücke
SQLQueryParser.<super_groups_element_list>		= Liste der Gruppierungsausdrücke
SQLQueryParser.<table>							= Tabelle
SQLQueryParser.<table_correlation>				= Tabellenalias
SQLQueryParser.<table_join>						= verknüpfte Tabelle
SQLQueryParser.<table_name_or_alias> 			= Tabellenname oder Tabellenalias
SQLQueryParser.<table_query>					= verschachtelte Tabellenabfrage
SQLQueryParser.<table_ref>						= Tabellenreferenz
SQLQueryParser.<table_ref_commalist>			= kommagetrennte Liste der Tabellenreferenzen
SQLQueryParser.<target_column_list>				= Liste der Zielspalten
SQLQueryParser.<target_table>					= Zieltabelle
SQLQueryParser.<update_assignment_clause>		= UPDATE-Zuordnungsklausel
SQLQueryParser.<update_assignment_expression>	= UPDATE-Zuordnungsausdruck
SQLQueryParser.<update_assignment_expression_commalist>	= kommagetrennte Liste der UPDATE-Zuordnungsausdrücke
SQLQueryParser.<update_stmt>					= UPDATE-Anweisung
SQLQueryParser.<values_row>						= Wertezeile
SQLQueryParser.<values_row>						= Wertezeile
SQLQueryParser.<values_row_commalist>			= kommagetrennte Liste von Wertezeilen
SQLQueryParser.<with_clause>					= WITH-Klausel
SQLQueryParser.<with_table_spec>				= WITH-Tabellenspezifikation
SQLQueryParser.<with_table_spec_list>			= Liste der WITH-Tabellenspezifikationen



SQLQueryDB2Parser.<olap_aggregation_function>	= OLAP-Aggregatfunktion
SQLQueryDB2Parser.<olap_current_row>			= CURRENT ROW
SQLQueryDB2Parser.<olap_function>				= OLAP-Funktion
SQLQueryDB2Parser.<olap_group_end>				= FOLLOWING-Ausdruck
SQLQueryDB2Parser.<olap_group_start>			= PRECEDING-Ausdruck
SQLQueryDB2Parser.<olap_group_type>				= OLAP-Gruppierungstyp
SQLQueryDB2Parser.<olap_numbering_function>		= OLAP-Nummerierungsfunktion
SQLQueryDB2Parser.<olap_order_by_expression>	= OLAP ORDER BY-Ausdruck
SQLQueryDB2Parser.<olap_order_by_list>			= Liste der OLAP ORDER BY-Ausdrücke
SQLQueryDB2Parser.<olap_partitioning_expression_list>	= Liste der OLAP-Partitionierungsausdrücke
SQLQueryDB2Parser.<olap_partitioning_expression>		= OLAP-Partitionierungsausdruck
SQLQueryDB2Parser.<olap_rank_or_dense_rank>				= RANK() oder DENSE_RANK()
SQLQueryDB2Parser.<olap_ranking_function>				= OLAP-Rangfunktion
SQLQueryDB2Parser.<olap_rows_or_range>					= ROWS oder RANGE
SQLQueryDB2Parser.<olap_window_order_clause>			= OLAP-Fensterreihenfolgenklausel
SQLQueryDB2Parser.<opt_olap_asc_desc>					= OLAP ASC/DESC-Spezifikation
SQLQueryDB2Parser.<opt_olap_window_aggregation_group>	= optionale OLAP-Fensteraggregatgruppe
SQLQueryDB2Parser.<opt_olap_window_order_agregration_group>	= optionale OLAP Fensterreihenfolgenaggregatgruppe
SQLQueryDB2Parser.<opt_olap_window_order_clause>			= optionale OLAP-Fensterreihenfolgenklausel
SQLQueryDB2Parser.<opt_olap_window_parition_clause>			= optionale OLAP-Fensterpartitionsklausel

SQLQueryDB2Parser.<opt_xml_element_attributes_function>		= optionale XMLATTRIBUTES-Funktion
SQLQueryDB2Parser.<opt_xml_element_content_list>			= optionale Liste der XML-Elementinhaltsausdrücke
SQLQueryDB2Parser.<opt_xml_element_namespaces_function>		= optionale XMLNAMESPACES-Funktion
SQLQueryDB2Parser.<opt_xml_forest_namespaces_function>		= optionale XMLNAMESPACES-Funktion
SQLQueryDB2Parser.<xml_agg_function>			= XMLAGG-Funktion
SQLQueryDB2Parser.<xml_attribute_list>			= Liste der XML-Attributausdrücke
SQLQueryDB2Parser.<xml_attribute>				= XML-Attributausdruck
SQLQueryDB2Parser.<xml_attributes_function>		= XMLATTRIBUTES-Funktion
SQLQueryDB2Parser.<xml_concat_function>			= XMLCONCAT-Funktion
SQLQueryDB2Parser.<xml_element_content_list>	= Liste der XML-Elementinhaltsausdrücke
SQLQueryDB2Parser.<xml_element_content>			= XML-Elementinhaltsausdruck
SQLQueryDB2Parser.<xml_element_function>		= XMLELEMENT-Funktion
SQLQueryDB2Parser.<xml_forest_element_list>		= Liste der XMLFOREST-Elemente
SQLQueryDB2Parser.<xml_forest_element>			= XMLFOREST-Elementausdruck
SQLQueryDB2Parser.<xml_forest_function>			= XMLFOREST-Funktion XMLSERIALIZE oder XML2CLOB
SQLQueryDB2Parser.<xml_function>				= XML-Funktion 
SQLQueryDB2Parser.<xml_namespace_list>			= Liste der XML-Namespaces
SQLQueryDB2Parser.<xml_namespace_prefix>		= XML-Namespace-Präfix
SQLQueryDB2Parser.<xml_namespace_uri>			= XML-Namespace-URI
SQLQueryDB2Parser.<xml_namespace>				= XML-Namespace
SQLQueryDB2Parser.<xml_namespaces_function>		= XMLNAMESPACES-Funktion
SQLQueryDB2Parser.<xml_value_datatype>			= XML-Wertdatentyp
SQLQueryDB2Parser.<xml_value_function_list>		= Liste der XML-Wertfunktionen
SQLQueryDB2Parser.<xml_value_function>			= XML-Wertfunktion


