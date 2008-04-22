# NLS_MESSAGEFORMAT_VAR          needed to handle quotes correctly
#
# Copyright (c) 2002, 2007. All Rights Reserved IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which is available at
# http://www.eclipse.org/legal/epl-v10.html
#  
# Contributors:
#     IBM Corporation - initial API and implementation
#
# Properties file for component:  SQLQ - SQL builder

! Please do not translate following strings  in this file (appears exactly, case sensitive) :
! WITH
! SQL
! SELECT
! FULLSELECT
! UNION
! CASE
! CAST

!
! views/update/RemoveUpdateColumnAction.java
!
_UI_ACTION_REMOVE_UPDATE_COLUMN = 列の削除(&C)

!
! views/update/UpdateTreeElement.java
!
_UI_COLUMN_UPDATE_GROUP = グループの更新

!
! views/update/UpdateTreeViewer.java
!
_UI_COLUMN_UPDATE_COLUMN     = 列の更新
_UI_COLUMN_UPDATE_EXPRESSION = 式

!
!views/update/UpdateViewer.java
!
_UI_WORKBOOK_SET    = 設定
_UI_WORKBOOK_WHERE  = 説明


!
! Actions
!
!! NOTE TO TRANSLATOR: Do not translate "WITH" in the following line
_UI_ACTION_ADD_TABLE          = テーブルの追加(&A)...
_UI_ACTION_ADD_TABLE_REPLACE  = テーブルの置換(&E)...
!! NOTE TO TRANSLATOR: Do not translate "FULLSELECT" in the following line
_UI_ACTION_ADD_FULLSELECT     = FULLSELECT 文の追加(&F)
_UI_ACTION_REMOVE_TABLE       = テーブルの削除(&M)...

!actions/ChangeStatementTypeAction
_UI_ACTION_CHANGE_STATEMENT_TYPE		= 文の種類を変更(&C)...

!actions/OmitCurrentSchemaAction
_UI_ACTION_OMIT_CURRENT_SCHEMA		= 現在のスキーマを省略(&O)...

!actions/AddValueRowAction
_UI_ACTION_ADD_VALUE_ROW=値の行の追加(&R)

!actions/AddValuesAction.java
_UI_ACTION_ADD_VALUES=値の追加(&V)

!actions/AddSubSelectAction.java
!! NOTE TO TRANSLATOR: Do not translate "SELECT" in the following line
_UI_ACTION_ADD_SELECT=SELECT 文の追加(&S)

!actions/CreateWithTableAction.java
_UI_ACTION_ADD_COMMON_TABLE=共通のテーブル式 (WITH) を追加(&A)

!actions/DeleteStatementAction.java
_UI_ACTION_DELETE_STATEMENT=削除(&D)

!expressionbuilder/actions/DeleteExpressionAction.java
_UI_ACTION_DELETE_EXPRESSION=式の削除(&R)

!expressionbuilder/actions/DeleteCaseClauseAction.java
_UI_ACTION_DELETE_CLAUSE=句の削除(&R)

!expressionbuilder/actions/DeleteFunctionParameterAction.java
_UI_ACTION_DELETE_PARAMETER=パラメタの削除(&R)

!expressionbuilder/case/CastExpressionWizard.java
!! NOTE TO TRANSLATOR: Do not translate "CAST" in the following line
_UI_WIZARD_CAST_EXPRESSION_TITLE=CAST 式ビルダ

!expressionbuilder/caseexpr/CaseSimplePage.java
_UI_COMBO_ADDFUNCTION=関数式の構築...

!! NOTE TO TRANSLATOR: Do not translate "CASE" in the following line
_UI_COMBO_ADDCASE=CASE 式の構築...
!! NOTE TO TRANSLATOR: Do not translate "CAST" in the following line
_UI_COMBO_ADDCAST=CAST 式の構築...
_UI_COMBO_ADDCONSTANT=定数式の構築...
_UI_COMBO_SELECTSUBQUERY=サブクエリの選択...
_UI_COMBO_ADDEXPRBYOPERATOR=演算子を使って式を構築...
_UI_WIZARD_CASE_SIMPLE_TITLE=CASE 簡易式
_UI_WIZARD_CASE_SIMPLE_HEADING=CASE 簡易式を構築します。
!! NOTE TO TRANSLATOR: Do not translate "WHEN-THEN" and "ELSE" in the following line
_UI_LABEL_BUILD_WHEN_CLAUSE=WHEN-THEN 句の構築と ELSE 句の追加(&U)
!! NOTE TO TRANSLATOR: Do not translate "ELSE" in the following line
_UI_CHECKBOX_ADD_ELSE=ELSE 句の追加(&E)
!! NOTE TO TRANSLATOR: Do not translate "WHEN" in the following line
_UI_BUTTON_ADD_WHEN=WHEN 句の追加(&W)
_UI_LABEL_EXPRESSION_PREVIEW=CASE 式のプレビュー :

!expressionbuilder/function/FunctionBuilderPage.java:

!expressionbuilder/caseexpr/CaseSearchTable.java
_UI_COLUMN_CASE_SEARCH_EXPRESSION=式
_UI_COLUMN_CASE_SEARCH_OPERATOR=演算子
!! NOTE TO TRANSLATOR: Do not translate "WHEN" in the following line
_UI_ACTION_DELETE_WHEN_CLAUSE_SEARCH=WHEN 句の削除(&R)
_UI_ACTION_DELETE_SEARCH_CONDITION=検索条件の削除(&R)
!! NOTE TO TRANSLATOR: Do not translate "ELSE" in the following line
_UI_ACTION_DELETE_ELSE_EXPRESSION_SEARCH=ELSE 式の削除(&R)

!expressionbuilder/caseexpr/CaseSearchPage.java
_UI_WIZARD_CASE_SEARCH_HEADING=検索した CASE 式
_UI_WIZARD_CASE_SEARCH_EXPR=検索した CASE 式を構築します。

!expressionbuilder/caseexpr/CaseSimpleTable.java
_UI_COLUMN_CASE_EXPRESSION=式
_UI_COLUMN_RESULT_EXPRESSION=結果の式
!! NOTE TO TRANSLATOR: Do not translate "WHEN" in the following line
_UI_ACTION_DELETE_WHEN_CLAUSE=WHEN 句の削除(&R)
!! NOTE TO TRANSLATOR: Do not translate "ELSE" in the following line
_UI_ACTION_DELETE_ELSE_EXPRESSION=ELSE 式の削除(&R)

!expressionbuilder/...Signatures...
_UI_FCN_ALL=すべて
_UI_FCN_COLUMN=列
_UI_FCN_SPECIAL_REG=特殊レジスタ
_UI_FCN_SCALAR=スカラー
_UI_FCN_CONVERSION=変換
_UI_FCN_SECURITY=セキュリティ
_UI_FCN_DATE_TIME=日付と時刻
_UI_FCN_DATE=日付
_UI_FCN_DB2=DB2
_UI_FCN_LOGICAL=論理
_UI_FCN_MATH=数式
_UI_FCN_BIT=ビット
_UI_FCN_NUMBER=番号
_UI_FCN_CHARACTER=文字
_UI_FCN_SUMMARY=サマリ
_UI_FCN_TEXT=テキスト
_UI_FCN_SYSTEM=システム
_UI_FCN_STRING=文字列
_UI_FCN_AGGREGATE=総計
_UI_FCN_MISCELLANEOUS_SINGLE_ROW=その他の単一行
_UI_FCN_MISCELLANEOUS=その他
_UI_FCN_OBJECT_REFERENCE=オブジェクトの参照
_UI_FCN_CONTROL_FLOW=コントロールフロー
_UI_FCN_TEXT_EXTENDERS=テキストの拡張子
_UI_FCN_AIV_EXTENDERS=オーディオ/イメージ/ビデオの拡張子
_UI_FCN_XMLFUNCTIONS=XML
_UI_FCN_NOT_SUPPORTED=サポートされていません。
_UI_FCN_UDF=ユーザ定義関数 (UDF)
_UI_FCN_DATALINK=データリンク
_UI_FCN_MQ_EXTENDER=メッセージキュー (MQ)

!expressionbuilder/subquery/SubQueryWizard.java
_UI_WINDOW_TITLE_CHOICES=サブクエリの選択

!expressionbuilder/function/FunctionBuilderPage.java
_UI_WIZARD_FUNCTION_BUILDER_HEADING=関数ビルダ
_UI_WIZARD_FUNCTION_BUILDER_EXPL=ここでは関数式を構築します。
_UI_WIZARD_FUNCTION_BUILDER_INSTR_CAT=1. 関数のカテゴリを選択(&C) :
_UI_WIZARD_FUNCTION_BUILDER_INSTR_FCN=2. 関数を選択(&U) :
_UI_WIZARD_FUNCTION_BUILDER_INSTR_SIG=3. 関数の署名を選択(&S) :
_UI_WIZARD_FUNCTION_BUILDER_INSTR_EXPR=4. 引数の式を選択(&E) :
_UI_WIZARD_FUNCTION_BUILDER_INSTR_PRE=5. 関数式のプレビュー :

!expressionbuilder/caseexpr/CaseExpressionDialog.java

!! NOTE TO TRANSLATOR: Do not translate "CASE" in the following 2 lines
_UI_WIZARD_CASE_OPTION_HEADING=CASE オプション
_UI_WIZARD_CASE_OPTION_EXPL=構築する CASE 式の種類を選択します。

_UI_GROUP_CASE_TYPE=CASE の種類
!! NOTE TO TRANSLATOR: Do not translate "WHEN" in the following line
_UI_RADIO_SEARCHED_WHEN=検索した WHEN 句(&S)

!! NOTE TO TRANSLATOR: Translate only "for example," in the following line
_UI_TOOLTIP_SEARCHED_WHEN = 例 : CASE WHEN i>j THEN k END

!! NOTE TO TRANSLATOR: Do not translate "WHEN" in the following line
_UI_RADIO_SIMPLE_WHEN=簡易 WHEN 句(&I)

!! NOTE TO TRANSLATOR: Translate only "for example," in the following line
_UI_TOOLTIP_SIMPLE_WHEN = 例 : CASE i WHEN 8 THEN j END

!expressionbuilder/caseexpr/CaseExpressionDialog.java
!! NOTE TO TRANSLATOR: Do not translate "CASE" in the following line
_UI_WIZARD_CASE_EXPRESSION_TITLE=CASE 式ビルダ

!expressionbuilder/constant/ConstantExpressionWizard.java
!! NOTE TO TRANSLATOR: Do not translate the following line
_UI_NUMERIC_CONSTANT=0123456789-+E.

!expressionbuilder/case/CaseBuilderPage.java
!! NOTE TO TRANSLATOR: Do not translate "CAST" where it occurs in the following 6 lines
_UI_WIZARD_CAST_BUILDER_TITLE=CAST ビルダ
_UI_WIZARD_CAST_BUILDER_HEADING=ここでは CAST 式を構築します。
!! NOTE TO TRANSLATOR: Do not translate "SCOPE" in the following line
_UI_LABEL_PREVIEW_CAST_EXPRESSION=CAST 式のプレビュー :
_UI_LABEL_DATATYPE_LENGTH=データ型の長さ :

!expressionbuilder/constant/ConstantExpressionDialog.java
_UI_WIZARD_CONSTANT_TITLE=定数式ビルダ

!expressionbuilder/constant/NumericConstantBuilderPage.java
_UI_WIZARD_NUMERIC_CONSTANT_TITLE=数値定数ビルダ
_UI_WIZARD_NUMERIC_CONSTANT_HEADER=数値定数ビルダ
_UI_WIZARD_NUMERIC_CONSTANT_EXPL=ここでは数値定数式を構築できます。
_UI_BUTTON_CLEAR=クリア(&C)

!! NOTE TO TRANSLATOR: Do not translate the following 2 lines
_UI_BUTTON_FLOAT_TO_EXP=F <-> E(&F)
_UI_BUTTON_PLUS_MINUS=+/-(&/)

!expressionbuilder/constant/ConstantOptions.java
_UI_WIZARD_CONSTANT_OPTIONS_TITLE=定数オプション
_UI_WIZARD_CONSTANT_OPTIONS_HEADING=定数オプション
_UI_WIZARD_CONSTANT_OPTIONS_EXPL=構築する定数式の型を選択します。
_UI_GROUP_CONSTANT_TYPE=定数の型
_UI_RADIO_NUMERIC=数値定数 : 整数、浮動小数点、または小数(&U)
_UI_RADIO_STRING=文字列定数 : 文字列・グラフィック列、またはホスト変数名(&S)

!expressionbuilder/constant/StringConstantBuilderPage.java
_UI_WIZARD_STRING_CONSTANT_TITLE=文字列定数ビルダ
_UI_WIZARD_STRING_CONSTANT_HEADING=文字列定数ビルダ
_UI_WIZARD_STRING_CONSTANT_EXPL=ここでは文字列定数式を構築できます。テキストフィールドに式を入力してください。
_UI_GROUP_STRING_TYPE=文字列定数の型
_UI_RADIO_CHARACTER=文字列定数(&A)
_UI_RADIO_HEX=16 進数の定数(&H)
_UI_RADIO_HOST_VARIABLE_NAME=ホスト変数名(&V)
_UI_RADIO_GRAPHIC=グラフィック文字列の定数(&G)
_UI_LABEL_PREVIEW_CONSTANT_EXPRESSION=定数式のプレビュー :

!expressionbuilder/function/FunctionExpressionDialog.java
_UI_WIZARD_FUNCTION_EXPRESSION_TITLE=関数式ビルダ

!ExpressionBuilder/MainExpressionPage.java
_UI_WIZARD_MAIN_EXPRESSION_TITLE=式ビルダ
_UI_WIZARD_MAIN_EXPRESSION_HEADING=式の種類
_UI_WIZARD_MAIN_EXPRESSION_EXPL=構築する式の種類を選択します。
_UI_RADIO_FUNCTION=関数(&F) :

!! NOTE TO TRANSLATOR: Do not translate "CASE" in the following line
_UI_RADIO_CASE=CASE - 検索済みまたは簡易(&C)

!! NOTE TO TRANSLATOR: Do not translate the following line
_UI_RADIO_CAST=CAST(&A)

_UI_RADIO_CONSTANT=定数 - 数値、文字列、またはホスト変数(&O)
_UI_RADIO_SUBQUERY=サブクエリ(&S)
_UI_RADIO_OPERATORS=演算子使って式を構築(&U)
_UI_COLUMN_EXPRESSION=式
_UI_COLUMN_OPERATOR=演算子
_UI_COLUMN_PARAMETER=パラメタ
_UI_COLUMN_VALUE=値

!expressionbuilder/multiexpr/ExpressionsByOperatorsWizard.java
_UI_WIZARD_EXPRESSION_BY_OPERATOR_TITLE=演算子を使って式を構築
_UI_WIZARD_EXPRESSION_BY_OPERATOR_HEADING=演算子を使った式
_UI_WIZARD_EXPRESSION_BY_OPERATOR_EXPL=演算子を選択して長い式を構築します。
_UI_LABEL_BUILD_LEFT_OP_RIGHT=右側で演算子を選択して左側で式を構築する(&U):
_UI_LABEL_PREVIEW_EXPRESSION=式のプレビュー :

!expressionbuilder/subquery/SubQueryPage.java
_UI_WIZARD_SUBQUERY_HEADING=サブクエリ
_UI_WIZARD_SUBQUERY_EXPL=サブクエリを選択します。
_UI_LABEL_CHOICES=選択肢 :
_UI_RADIO_EXISTING_STATEMENTS=既存のクエリに基づいてサブクエリを作成する(&E)
_UI_RADIO_SCRATCH_SELECT=オリジナルのサブクエリを作成する(&S)
!! NOTE TO TRANSLATOR: Do not translate "SELECT" in the following 2 lines
_UI_LABEL_EXISTING_SELECT=SELECT 文を作成・編集する(&R) :
_UI_LABEL_NEW_SELECT_INFO=SELECT 文を入力する : 仮想テーブルを指定できます(&T)

!views/CustomSashForm.java
_UI_TOOLTIP_RESTORE_PANE=ペインを元に戻す
_UI_TOOLTIP_HIDE_PANE=ペインを非表示にする


!preferences/SQLBuilderPreferencePage.java
_UI_PREFERENCES_OMIT_SCHEMA_DESC=SQL 文に設定する [現在のスキーマを省略] のデフォルト値
_UI_PREFERENCES_OMIT_SCHEMA_IN_SQL=生成された SQL 文で現在のスキーマを省略する(&O)
_UI_PREFERENCES_OMIT_SCHEMA_SPECIFY_SCHEMA_GROUP_TITLE=現在のスキーマ
_UI_PREFERENCES_OMIT_SCHEMA_USE_AUID_AS_CURRENT_SCHEMA=認証 ID(&A)
_UI_PREFERENCES_OMIT_SCHEMA_SCHEMA_NAME=スキーマ名(&S) :

!dialogs/ChangeStatementTypeDialog
_UI_DIALOG_CHANGE_STATEMENT_TYPE_TITLE=SQL 文の種類を変更
_UI_DIALOG_CHANGE_STATEMENT_TYPE_NOTE=SQL 文の種類を変更すると、現在の文が消去され、新しい空の文に置き換えられます。
_UI_DIALOG_CHANGE_STATEMENT_TYPE_GROUP_TITLE=文の種類

!dialogs/OmitCurrentSchemaDialog
_UI_DIALOG_OMIT_SCHEMA_TITLE=現在のスキーマを省略
_UI_DIALOG_OMIT_SCHEMA_IN_SQL=生成された SQL 文で現在のスキーマを省略する(&O)
_UI_DIALOG_OMIT_SCHEMA_SPECIFY_SCHEMA_GROUP_TITLE=現在のスキーマ
_UI_DIALOG_OMIT_SCHEMA_USE_AUID_USER_AS_CURRENT_SCHEMA=認証 ID <{0}>(&A)
_UI_DIALOG_OMIT_SCHEMA_USE_AUID_AS_CURRENT_SCHEMA=認証 ID(&A)
_UI_DIALOG_OMIT_SCHEMA_SCHEMA_NAME=スキーマ名(&S) :

!views/execute/ParameterWizards.java
_UI_WIZARD_SPECIFY_VARIABLE_VALUES_TITLE=変数値の指定

!views/execute/ParameterWizardDialog.java
_UI_WIZARD_PARAMETER_MARKERS_TITLE=パラメタのマーカ

!views/execute/ParameterMarkerPage.java
_UI_WIZARD_PARAMETER_MARKER_TITLE=代替変数
_UI_WIZARD_PARAMETER_MARKER_HEADING=ホスト変数値
_UI_WIZARD_PARAMETER_MARKER_EXPL=使用する変数値の指定

!views/execute/ParameterTableViewer.java
_UI_COLUMN_MARKER_NAME=名前
_UI_COLUMN_TYPE=種類
_UI_COLUMN_PARAMETER_VALUE=値


!views/AddTableDialog.java
_UI_LABEL_TABLE_NAME            = テーブル名(&N) :
_UI_DIALOG_REPLACE_TABLE_TITLE  = テーブルの置換
_UI_DIALOG_ADD_TABLE_TITLE      = テーブルの追加
_UI_LABEL_TABLE_ALIAS           = テーブルのエイリアス(&A) :
_UI_DIALOG_TABLE_ALREADY_ADDED  = テーブルはすでに追加されています。エイリアスを指定してください。
_UI_DIALOG_ADD_VIEW_TO_SELF     = ビューを自身の定義に追加できません。


!views/GridViewer.java
_UI_COLUMN_DEFAULT_COLUMN=列

!views/insert/InsertGridViewer.java
_UI_COLUMN_INSERT_VALUE=値

!views/criteria/CriteriaGridViewer.java
_UI_COLUMN_COLUMN=列
_UI_COLUMN_CRITERIA_OPERATOR=演算子
_UI_COLUMN_CRITERIA_VALUE=値

!! NOTE TO TRANSLATOR: Do not translate the following line
_UI_COLUMN_CRTIERIA_AND_OR=AND/OR

!views/criteria/CriteriaValueExpression.java
_UI_ACTION_UPDATE_VALUE_EXPRESSON=値式の更新(&U)
_UI_ACTION_REMOVE_CONDITION=条件の削除(&R)
_UI_MENU_EXECUTE= SQL の実行(&R)

!views/fullselect/FullSelectViewer.java
!! NOTE TO TRANSLATOR: Do not translate VALUES in the following entry, since it is a SQL keyword
_UI_LABEL_FULL_SELECT_VALUES_HELP=値の行を追加するには、アウトラインビューで VALUES を右クリックしてください。

!views/fullselect/ValuesRowViewer.java
_UI_COLUMN_VALUES_ROW_ITEM=値の行の項目
_UI_ACTION_REMOVE_VALUES_ROW=項目の削除(&R)

!views/fullselect/FullSelectGridViewer.java
_UI_COLUMN_STATEMENT=文
_UI_COLUMN_FULL_SELECT_OPERATOR=演算子

!views/graph/editparts/TableEditPart.java
_UI_ACTION_UPDATE_ALIAS=エイリアスの更新(&U)...
_UI_DIALOG_CHANGE_ALIAS_TITLE=テーブルエイリアスの変更
_UI_DIALOG_CHANGE_ALIAS_TEXT=新規エイリアス(&E) :
_UI_ACTION_CREATE_JOIN=ジョインの作成(&J)...
_UI_ACTION_REMOVE_TABLE_EDIT=テーブルの削除(&M)


!views/graph/GraphContextMenuProvider.java:
_UI_ACTION_REMOVE_JOIN=ジョインの削除(&R)

!views/graph/SourceTargetDialog.java
_UI_DIALOG_SOURCE_AND_TARGET_TITLE=ジョインの作成
_UI_DIALOG_SOURCE_AND_TARGET_TEXT=ジョインのソース列とターゲット列を選択してください。
_UI_LABEL_JOIN_SRCCOL_TYPE = ソース列の種類 :
_UI_LABEL_JOIN_TGTCOL_TYPE = ターゲット列の種類 :

_UI_GROUP_SOURCE=ソース
_UI_LABEL_TABLE=テーブル (エイリアス)(&T)
_UI_LABEL_COLUMN=列(&C)
_UI_GROUP_TARGET=ターゲット
_UI_LABEL_TARGET_TABLE=テーブル (エイリアス)(&A)
_UI_LABEL_TARGET_COLUMN=列(&O)

_UI_GRAPH_PROMPT = テーブルを追加するには、このペインを右クリックしてポップアップメニューを使用してください。
_UI_GRAPH_PARSE_FAILED = この SQL 構文は無効か、現在はサポートされていません。\
代わりに手動で SQL を入力できます。SQL ビルダの一部の機能が無効になっています。\
これらの機能を有効にするには、構文を変更してください。\n\n変更を元に戻すには、\
[SQL] メニューの [変更前のソースに戻す] を選択します。
_UI_GRAPH_NO_TABLE_FOUND = テーブル "{0}" が見つかりません。テーブルが存在しないか、フィルタされた可能性があります。\
SQL ビルダの一部の機能が無効になっています。\
これらの機能を有効にするには、構文を変更してから文を保存してください。\n\n変更を元に戻すには、\
[SQL] メニューの [変更前のソースに戻す] を選択します。
_UI_GRAPH_NO_COLUMN_FOUND = 列 {0} が見つかりません。SQL ビルダの一部の機能が無効です。\
これらの機能を有効にするには、構文を変更してから文を保存してください。\n\n変更を元に戻すには、\
[SQL] メニューの [変更前のソースに戻す] を選択します。

!views/inster/InsertTypeViewer.java
_UI_GROUP_INSERT_ROWS=行の挿入元 :
_UI_RADIO_VALUES=値
_UI_RADIO_INSERT_FROM_SUBQUERY=サブクエリ
_UI_ACTION_REMOVE_COLUMN=列の削除(&R)

!views/insert/InsertSelectViewer.java
_UI_LABEL_QUERY_NAME=クエリ名 :

!views/select/GroupByContentViewer.java
_UI_LABEL_GROUPS_TYPE=次のように入力します。
_UI_COMBOBOXITEM_GROUPING_ROOT=グループ化のルート
_UI_ACTION_REMOVE=削除(&R)
_UI_NONE=なし
_UI_SIMPLE=簡易

!! NOTE TO TRANSLATOR: Do not translate following line
_UI_CUBE=CUBE

!! NOTE TO TRANSLATOR: Do not translate following line
_UI_ROLLUP=ROLLUP

_UI_GROUPING=グループ化
_UI_GROUPINGSETS=グループ化セット

! views/select/SelectGridViewer.java:
!
_UI_COMBO_SORT_UNSORTED   = ソート未処理
_UI_COMBO_SORT_ASCENDING  = 昇順
_UI_COMBO_SORT_DESCENDING = 降順
_UI_COMBO_SORT_DEFAULT = デフォルト

!
!views/select/RemoveSelectColumnsAction.java
!
_UI_ACTION_REMOVE_SELECT_COLUMN = 列式の削除(&C)

!
!views/select/RemoveOrderByExpressionAction.java
!
_UI_ACTION_REMOVE_ORDER_BY_EXPRESSION = Order By 式の削除(&O)

!
! views/select/SelectGridViewer.java
!
_UI_COLUMN_SELECT_ALIAS      = エイリアス
_UI_COLUMN_SELECT_OUTPUT     = 出力
_UI_COLUMN_SELECT_SORT_TYPE  = ソート基準
_UI_COLUMN_SELECT_SORT_ORDER = ソート順

!
! views/select/SelectViewer.java
!
_UI_WORKBOOKPAGE_COLUMNS          = 列
_UI_WORKBOOKPAGE_CONDITIONS       = 条件
_UI_WORKBOOKPAGE_GROUPS           = グループ
_UI_WORKBOOKPAGE_GROUP_CONDITIONS = グループの条件

!
! views/SQLBuilderActionBarContributor.java
!
_UI_TOOLTIP_EXECUTE_SQL = SQL 文を実行する
_UI_MENU_SQLBUILDER     = SQL(&S)

!
! views/source/SQLSourceViewer.java & SQLSourceParser
!
!! NOTE TO TRANSLATOR: Do not translate the following four lines
_UI_INSERT                   = INSERT
_UI_UPDATE                   = UPDATE
_UI_DELETE                   = DELETE
_UI_SELECT                   = SELECT

_UI_PARSE_ERROR_ENCOUNTERED  = 解析エラーが発生しました。


!
! views/StatementNameViewer.java
!
_UI_LABEL_STATEMENT = 文 :


!
! Join stuff - DefineJoinDialog.java, DefineJoinTypeDialog.java
!
_UI_DEFINE_JOIN_DIALOG_TITLE = ジョインの指定
_UI_DEFINE_JOIN_DIALOG_INSTR = ジョインの型
_UI_RADIO_INNER_JOIN         = インナージョイン(&I)
_UI_RADIO_LEFT_OUTER_JOIN    = 左アウタージョイン(&L)
_UI_RADIO_RIGHT_OUTER_JOIN   = 右アウタージョイン(&R)
_UI_RADIO_FULL_OUTER_JOIN    = 左右アウタージョイン(&F)
_UI_ACTION_SPECIFY_JOIN_TYPE = ジョインの型を指定(&S)...
_UI_MSG_INVALID_JOIN_TITLE = 互換性のない型

!
!views/update/UpdateDesignViewer.java
!
_UI_TOOLTIP_UPDATE_ADD_COL = 1 列ごとに文に追加する
_UI_TOOLTIP_UPDATE_ADD_GRP = 列をまとめて文に追加する

!
!views/with/WithTableViewer.java
!
_UI_GROUP_WITH_TABLE     = テーブル
_UI_GROUP_WITH_COLUMN    = 列
_UI_BUTTON_ADD_RIGHT     = 追加 >>
_UI_BUTTON_REMOVE_LEFT   = << 削除

!
! views/with/WithStatementViewer
!
_UI_LABEL_WITH_STATEMENT  = 文の名前 :

! CreateWithTableAction.java

!! NOTE TO TRANSLATOR: Do not translate "WithTable%1" in the following line
_UI_WITH_TABLE_NAME		  = WithTable%1
!
! SQLPreferencePage.java
!
_UI_VALIDATE_PREFERENCE_LABEL = クエリ文を保存する前に実行して検証する(&V)
_UI_ERROR_VALUE_GREATER_THAN_0 = この値には、1 以上の整数を指定してください。

!
!views/SQLBuilder.java
!
_UI_VALIDATE_FAILED_TITLE    = 検証エラー
_UI_CONTINUE_WITH_SAVE       = 文を保存しますか ?
_UI_VALIDATING_STATEMENT     = 文を検証しています...
_UI_OBTAINING_CONNECTION     = 接続を取得しています...
_UI_VALIDATE_DONE            = 検証が完了しました...
_UI_SAVING_STATEMENT         = 文を保存しています...

!model/SQLBuilderConstants.java:
_UI_BUILD_EXPRESSION   = 式の構築...
_UI_EDIT_EXPRESSION    = 式の編集...
_UI_REPLACE_EXPRESSION = 式の置換...
!! NOTE TO TRANSLATOR: Do not translate the words that are fully capitalized in the following 3 lines
_UI_ADD_SELECT         = SELECT 文の追加...
_UI_ADD_FULLSELECT     = FULLSELECT 文の追加...
_UI_ADD_WITH           = WITH 文の追加...
_UI_ADD_VALUES         = 値の追加
_UI_SPECIFY_VALUE	   = 値の指定...
_UI_SPECIFY_VALUE_TITLE	   = 値の指定


!
!
! Here is the list of Warning string that have message IDs - make sure they are unique
!
!
_WARN_MESSAGES_ENCOUNTERED      = 警告メッセージが発生しました。
_WARN_DATA_WAS_TRUNCATED        = データが切り捨てられました。
_WARN_SQL_WARNINGS_FOUND        = SQL 警告が見つかりました。

_WARN_BAD_JOIN_1                = ジョインの型に互換性がありません。
_WARN_INVALID_JOIN              = ジョインが無効です。
_WARN_COLUMN_USED               = 列は、テーブル間のジョインですでに使用されています。

! Usage:
!   {0} replaced with the column name and {1} replaced with the column datatype
!
_WARN_BAD_JOIN_TYPES               = 列 {0} の型は {1} です。


!
!
! Here is the list of Error string that have message IDs - make sure they are unique
!
!

_ERROR_DIALOG_TITLE_EXPR_EDIT_FAILED = 式を編集できません。
_ERROR_DIALOG_MSG_REBUILD_EXPR       = 式を再構築してください。

_ERROR_TABLE_USED                    = 固有なテーブルを選択するか、エイリアスを入力してください。
_ERROR_ALIAS_USED                    = 固有なエイリアスを入力してください。

_ERROR_ALIAS_NOT_UNIQUE_TEXT         = エイリアスが固有ではありません。


_EXC_OPEN_SQL_FILE_RESOURCE          = SQL 入力ファイルのリソースを開く際にエラーが発生しました。
_EXC_OPEN_SQL_FILE_NOT_CONNECTED     = データベース "{0}" に接続してから、SQL クエリビルダで SQL ファイルを開いてください。

!! NOTE TO TRANSLATOR: Do not translate "ISQLBuilderEditorInput" in the following line
_EXC_INPUT_NOT_ISQLBUILDEREDITORINPUT = 入力が ISQLBuilderEditorInput ではありません。

_ERROR_ALIAS_NOT_ALPHANUMERIC        = エイリアスはすべて英数字にしてください。
_ERROR_ALIAS_TABLE_USED              = データベースのテーブル名はエイリアスに使えません。
_ERROR_ENTER_ALIAS                   = テーブル名が使用されています。エイリアスを入力してください。

_ERROR_DATABASE_NOT_AVAILABLE        = データベース接続が使用できません。
_ERROR_INPUT_NOT_RECOGNIZED          = エディタの入力ファイルの形式がサポートされていません。
_ERROR_OPEN_SQL_STORAGE_RESOURCE     = SQL 入力ストレージリソースを開く際にエラーが発生しました。
_ERROR_OPEN_SQL_STRING_RESOURCE      = 入力ファイルを開く際にエラーが発生しました。
_ERROR_OPEN_INPUT_PARSE_FAILED       = SQL が無効か、現在サポートされていないため、解析できません。
_QUESTION_TITLE_OPEN_INPUT_PARSE_FAILED    = SQL クエリビルダの解析エラー
_QUESTION_MESSAGE_OPEN_INPUT_PARSE_FAILED    = SQL クエリビルダに空の SQL 文が作成されます。

!
!model/SQLDomainModel.java
!
!note we reuse messages from ErrorMessages.java in org.eclipse.ui.texteditor.ErrorMessages

Editor_error_save_title=保存のエラー
Editor_error_save_message=保存できません。
Editor_error_save_readonly=\n\n原因 :\n読み込み専用ファイルには書き込めません :
Editor_error_save_failed=\n\n原因 :\nファイルに書き込めません :

!Strings from B2BGUI properties file
_UI_ERROR_CREATING_FILE_TITLE = ファイルの作成エラー
_UI_ERROR_CREATING_FILE_SHORT_DESC = ファイル "{0}" の作成中にエラーが発生しました。
_UI_ERROR_CREATING_FILE_LONG_DESC = ファイル "{0}" を作成しようとしてエラーが発生しました。
_UI_PARENT_FOLDER_IS_READ_ONLY = 親ディレクトリ "{0}" は読み取り専用です。
_UI_UNKNOWN_ERROR_WITH_HINT = 不明なエラーです。親ディレクトリ "{0}" が読み込み専用でないことを確認してください。
_UI_UNKNOWN_ERROR = 不明なエラーです。親ディレクトリ "{0}" が読み込み専用でないことを確認してください。
! TextViewerOperationAction
_UI_MENU_COPY         = コピー(&C)
_UI_MENU_CUT          = 切り取り(&T)
_UI_MENU_DELETE       = 削除
_UI_MENU_PASTE        = 貼り付け(&P)
_UI_MENU_PREFIX       = 接頭辞
_UI_MENU_REDO         = やり直し
_UI_MENU_SELECT_ALL   = すべて選択
_UI_MENU_SHIFT_LEFT   = 左方向にシフト
_UI_MENU_SHIFT_RIGHT  = 右方向にシフト
_UI_MENU_STRIP_PREFIX = 接頭辞を削除
_UI_MENU_UNDO         = 元に戻す

!SQL Editor
datatools_sqlbuilder_sqleditor_tooltip = SQL エディタを開く
datatools_sqlbuilder_sqleditor_title = SQL エディタ(&S)

! SQL Query Builder
datatools_sqlbuilder_sqlbuilder_DML_error = エラー
datatools_sqlbuilder_sqlbuilder_multipleDML = SQL ビルダは、単一の SQL DML 文を作成する場合にのみ使用できます。\
ファイル {0} には単一の DML 文が含まれていません。SQL エディタを使ってファイルを開きますか ?
datatools_sqlbuilder_sqlbuilder_multipleDML_VE = Visual Explain は、単一の SQL DM 文の場合にのみ使用できます。\
ファイル {0} には単一の DML 文が含まれていません。
datatools_sqlbuilder_sqlbuilder_unsupport_VE = このデータベース接続では Visual Explain がサポートされていません。
!Clear to Skeleton and Revert to Last Correct
_UI_CLEAR_TO_TEMPLATE 			     = テンプレートに戻す
_UI_REVERT_TO_LAST_CORRECT_SOURCE    = 変更前のソースに戻す
_UI_LAST_CORRECT_SOURCE 			 = 変更前のソース
!Change Statement Type
_UI_CHANGE_STATEMENT_TYPE			= 文の種類を変更...
!Omit Current Schema
_UI_OMIT_CURRENT_SCHEMA              = 現在のスキーマを省略... 

!
! views/execute/ExecuteViewer.java & views/execute/QueryOutputHelper.java
!
_UI_DIALOG_EXECUTE_SQL_TITLE = SQL 文の実行
_UI_BUTTON_EXECUTE_CLOSE     = 閉じる(&C)
_UI_LABEL_SQL_STATEMENT      = SQL 文(&S) :
_UI_BUTTON_EXECUTE           = 実行(&R)
_UI_LABEL_QUERY_RESULTS      = クエリの結果 :
_UI_MONITOR_EXECUTING_QUERY  = SQL 文を実行しています...
_UI_LABEL_NO_CONNECTION      = データベース接続がないか、リレーショナルデータベースのスキーマモデルが破損しています。
_UI_LABEL_NO_CONNECTION_FILE = 接続の XMI ファイルがないため、データベース接続を使用できません。

_UI_DIALOG_WARNING_TITLE       = 警告
_UI_DIALOG_OP_FAILED_TITLE     = 操作エラー
_UI_LABEL_EXECUTION_SUCCESSFUL = 文は正常に実行されましたが、表示する結果がありません。
!! NOTE TO TRANSLATOR: the MAX_RECORD_EXCEEDED string should match
!! the strings for RECORD_RETURNED and RECORD_DISPLAYED.
_UI_MSG_MAX_RECORD_EXCEEDED    = 1000 件以上のレコードが返されました。最初の 1000 件のレコードを表示します。
_UI_MSG_RECORD_RETURNED        = 件のレコードが返されました。


! SQL Statement validation errors
! usage note: %1 will be replaced with a SQL statement name
_ERROR_STATEMENT_NAME_EXISTS         = %1 という名前の文はすでに存在しています。
_ERROR_INVALID_STATEMENT_NAME = 有効な名前を入力してください。
! usage note: %1 is replaced with the invalid character entered by the user
_ERROR_INVALID_CHAR_IN_STATEMENT_NAME = 次の文字は文の名前では使用できません : %1
_ERROR_STATEMENT_NAME_TOO_LONG = 文の名前は 160 文字以内にしてください。


! SelectDBTablePage resources
datatools_selectDBTablePage_statements = 文

!
! SQL Statement actions
!
datatools_sqlbuilder_sqlstatement_actions_new = 新規作成...
datatools_sqlbuilder_sqlstatement_actions_newSQLStatement = SQL 文の新規作成...
datatools_sqlbuilder_sqlstatement_actions_newSQLStatement_tooltip = クエリ文の新規作成
datatools_sqlbuilder_sqlstatement_actions_openwith = プログラムから開く
datatools_sqlbuilder_sqlstatement_actions_sqlbuilder = SQL クエリビルダ
datatools_sqlbuilder_sqlstatement_actions_sqleditor = SQL エディタ

!
! New SQL statement wizard, dialog, and page
!
datatools_sqlbuilder_newStatementWizard_title = SQL 文の新規作成
datatools_sqlbuilder_newStatementWizard_description = SQL 文を作成します。
datatools_sqlbuilder_newStatementDialog_title = SQL 文の新規作成
datatools_sqlbuilder_newStatementPage_title = 文の種類
datatools_sqlbuilder_newStatementPage_description = この文に名前を付け、テンプレートの種類を選択してください。
datatools_sqlbuilder_newStatementPage_statementID = 文の識別子
datatools_sqlbuilder_newStatementPage_statementName = 文の名前(&S) :
datatools_sqlbuilder_newStatementPage_statementType = 文のテンプレート(&T) :
datatools_sqlbuilder_newStatementPage_editUsing = プログラムで編集
datatools_sqlbuilder_newStatementPage_sqlQueryBuilder = SQL ビルダ(&Q)
datatools_sqlbuilder_newStatementPage_sqlEditor = SQL エディタ(&E)
datatools_sqlbuilder_newStatementPage_sampleStatements = サンプル文
datatools_sqlbuilder_newStatementPage_none = なし
!
! Project selection page, for new SQL statement wizard
!
datatools_sqlbuilder_projectPage_title = プロジェクトの選択
datatools_sqlbuilder_projectPage_description = 既存のプロジェクトを選択するか、この文のプロジェクトを新しく追加します。

!
! Generated comment text for template SQL for New SQL Statement action
!
_UI_SAMPLE_DML_TEMPLATE_STATEMENTS = サンプル DML テンプレートの文
_UI_SAMPLE_DDL_TEMPLATE_STATEMENTS = サンプル DDL テンプレートの文

!
! String for the AddTable With Table
!! NOTE TO TRANSLATOR: Do not translate "WITH" in the following line
_UI_AVAILABLE_TABLES_TREE_PROVIDER = WITH テーブル

!actions/ConvertToFullSelectAction.java
!! NOTE TO TRANSLATOR: Do not translate "FULLSELECT" and "UNION" in the following line
_UI_ACTION_CONVERT_TO_FULL_SELECT=FULLSELECT (UNION) に変換(&C) 


! org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect/fullselectElement.java
!! NOTE TO TRANSLATOR: Do not translate the words that are fully capitalized in the following 3 lines
_UI_VIEWS_FULLSELECTELEMENT_FULLSELECT = FULLSELECT 文
_UI_VIEWS_FULLSELECTELEMENT_SELECT = SELECT 文
_UI_VIEWS_FULLSELECTELEMENT_VALUES = VALUES

! org.eclipse.datatools.sqltools.sqlbuilder.views.graph/GraphContextMenuProvider.java
_UI_VIEWS_GRAPH_SELECT_ALL_COLUMN = すべての列を選択
_UI_VIEWS_GRAPH_DESELECT_ALL_COLUMN = すべての列を削除

!SQLQueryBuilder Dialog
_UI_SQLQUERYBUILDERDIALOG_NAME = SQL クエリビルダ
SQLQueryBuilderDialogEditTabName=編集
SQLQueryBuilderDialogNoInput=SQLBuilder ダイアログが開いたときに入力が渡されませんでした。
SQLQueryBuilderDialogParseFailOnOpenAskUserTitle=SQL クエリビルダ
SQLQueryBuilderDialogParseFailOnOpenAskUserMessage=続行すると、SQL クエリビルダに空の SQL 文が作成されます。続行してよろしいですか ?
SQLQueryBuilderDialogResultsTabName=SQL の結果


# plugin.xml.  preference page extension point
_UI_SQLQUERY_NAME = SQL クエリ

## Resources for the standard editor actions

ContentAssistProposal_label       =コンテンツのアシスト(&N)@Ctrl+SPACE
ContentAssistProposal_tooltip     =コンテンツのアシスト
ContentAssistProposal_image       =
ContentAssistProposal_description =コンテンツのアシスト

ContentTip_label       =コンテンツのヒント@Ctrl+SHIFT+SPACE
ContentTip_tooltip     =コンテンツのヒント
ContentTip_image       =
ContentTip_description =コンテンツのヒント

## Resources for other SQL Query Builder actions

datatools_sqlbuilder_RunSQLAction_label       = SQL の実行(&R)
datatools_sqlbuilder_RunSQLAction_tooltip     = SQL 文を実行する
datatools_sqlbuilder_RunSQLAction_image       =
datatools_sqlbuilder_RunSQLAction_description =

datatools_sqlbuilder_RevertToPreviousAction_label       = 変更前のソースに戻す
datatools_sqlbuilder_RevertToPreviousAction_tooltip     =
datatools_sqlbuilder_RevertToPreviousAction_image       =
datatools_sqlbuilder_RevertToPreviousAction_description =

datatools_sqlbuilder_RevertToDefaultAction_label       = テンプレートに戻す
datatools_sqlbuilder_RevertToDefaultAction_tooltip     =
datatools_sqlbuilder_RevertToDefaultAction_image       =
datatools_sqlbuilder_RevertToDefaultAction_description =

datatools_sqlbuilder_ChangeStatementTypeAction_label   = 文の種類を変更...
datatools_sqlbuilder_ChangeStatementTypeAction_tooltip     =
datatools_sqlbuilder_ChangeStatementTypeAction_image       =
datatools_sqlbuilder_ChangeStatementTypeAction_description =

datatools_sqlbuilder_OmitCurrentSchemaAction_label       = 現在のスキーマを省略...
datatools_sqlbuilder_OmitCurrentSchemaAction_tooltip     =
datatools_sqlbuilder_OmitCurrentSchemaAction_image       =
datatools_sqlbuilder_OmitCurrentSchemaAction_description =

!RDBTableDropListener
_UI_INVALID_TABLE_ADDED = データベースがプロジェクトに関連付けられていない場合は、データベースから文にテーブルを追加できません。

## Resources for utilities

VIEW_UTILITY_DEFAULT_FONT_UI_ = ＭＳ Ｐゴシック
STRING_MATCH_EXPECTED_EXCEPTION_MESSAGE = 必要な戻り値 : 
STRING_MATCH_RETURNED_EXCEPTION_MESSAGE =  実際の戻り値 :
