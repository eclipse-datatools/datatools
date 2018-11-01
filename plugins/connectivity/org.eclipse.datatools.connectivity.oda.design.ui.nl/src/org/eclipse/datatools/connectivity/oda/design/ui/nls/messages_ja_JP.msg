#
#************************************************************************
# Copyright (c) 2006, 2013 Actuate Corporation.
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
adaptableDataSourceProfile_unableAccessProfile=({0}) 内のリンクされたプロファイルにアクセスできません。データソースデザイン内のローカルプロパティを代わりに使用します。\n {1} で例外が発生しました。
common_notInDesignSession=現在デザイン・セッション中ではありません。
common_nullArgument=引数は null にできません。
common_missingDataSourceDesign=OdaDesignSession 引数のデータ・ソース・デザインがありません。
common_createClassFailed=クラス ({0}) の検索または作成ができません。
extension_mustInheritFromODAPage=無効なウィザード・ページ ({0}) の実装。 ODA カスタム・ウィザード・ページは {1} から拡張する必要があります。
extension_mustInheritFromODAWizard=無効なウィザード ({0}) の実装。 ODA カスタム・ウィザードは {1} を使用するか、ここから拡張されている必要があります。
extension_missingManifestElement=ODA デザイン UI 拡張ポイント ({0}) が実装されていません。
extension_missingPropertyPage=ODA デザイン UI 拡張のプロパティー・ページが実装されていません。
wizard_dataSource_defaultTitle=新規データ・ソース
wizard_missingDataSourceId=接続プロファイル属性の ODA データ・ソース ID がありません。
wizard_invalidManifest=oda.design.ui.dataSource 拡張の無効なプラグイン・マニフェスト・コンテンツ。
page_defaultDataSourceTitle=カスタム・データ・ソース・プロパティー
designSession_alreadyHasLinkedProfile=データソース ({0}) は、接続プロファイルでプロパティがすでに外部化されています。  エクスポートし直すことができません。
designSession_flushFailed=現在のデータセットのデザインセッションをフラッシュできません。
designSession_invalidArgument=デザイン・セッション・メソッドで無効な引数が指定されました。
designSession_invalidEditApiCall=デザインを編集するデザインセッションの API 呼び出しが無効です。
designSession_invalidNewDesignApiCall=新しいデザインを作成するデザインセッションの API 呼び出しが無効です。
designSession_invalidProfileName=指定された接続プロファイルストア内で、リンクされたプロファイル ({0}) が見つかりません。
designSession_invalidProfileNameOrFilePath=データソースデザイン内で指定された、リンクされたプロファイル ({0}) または接続プロファイルの保存ファイル ({1}) が見つかりません。
designSession_missingDataSetUIElement=OdaDataSourceId ({0}) および OdaDataSetId ({1}) に対して dataSetUI 要素が定義されていません。
manifest_missingAttributeValue=必要な属性 ({0}) の値が欠落しています。
manifest_missingDataSetElementId=dataSetElementID 引数に値が必要です。
manifest_invalidDataSetElementId=無効な dataSet 要素 ID ({0})。
manifest_dataSetUi_missingPageId=pageId 値が必要です。
manifest_dataSetUi_invalidPageId=無効な pageId 値 ({0})。
manifest_dataSetUi_missingElement=拡張に {0} 要素が必要です。
ui_defaultDataSourceTitle=データソース接続プロパティー
ui_replaceFilePrompt={0} はすでに存在します。 置き換えますか ?
ui_requiredFieldsMissingValue=値が指定されていない必須フィールド('*' でマークされています)が 1 つ以上あります。
ui_errorLabel=エラー
ui_errorCreatingCustomCtrls=カスタム・コントロールの作成および初期化中にエラーが発生しました。
ui_saveAsTitle=名前を付けて保存
profilePage_pageTitle=接続プロファイルの選択
profilePage_pageLabel=接続プロファイル
profilePage_selectProfileDefaultMessage=データソースの作成元とする接続プロファイルを選択してください。
profilePage_label_profileStore=接続プロファイルストア(&S)
profilePage_button_browse=参照(&R)...
profilePage_button_relativepath=相対パス(&R)
profilePage_button_absolutepath=絶対パス(&A)
profilePage_button_browse_filter=表示するファイル拡張子(&E):
profilePage_button_browse_filter_tooltiptext=下に表示されるファイルにフィルタを適用する 1 つまたは複数のコンマ区切りファイルの拡張子を指定してください。\n例 : .txt、.profiles
profilePage_button_new=新規作成(&W)...
profilePage_button_browse_tooltip=接続プロファイルストアファイルを参照します。\n相対パス : ホストリソースを基準とした相対パスです。\n絶対パス : ファイルシステムの絶対パスです。
profilePage_resourcebaseuri_failure=アプリケーションリソースのベース URI を取得できません。
profilePage_browse_noselection_error=接続プロファイルストアファイル名を指定してください。 
profilePage_odaTreeName=ODA データソース
profilePage_checkboxLabel_useDefaultName=デフォルトのデータソース名を使用する(&U)
profilePage_label_dataSourceName=データソース名(&D) :
profilePage_error_emptyName=データソース名は空白にできません。
profilePage_checkboxTooltip_maintainLink=データソースには、常に接続プロファイルストアの外部化したプロパティ値を使用します。
profilePage_checkboxLabel_maintainLink=接続プロファイルストアの外部化したプロパティ値を使用する(&E)
profilePage_error_invalidName=データソース名が無効です。
profilePage_error_invalidProfileStorePath=接続プロファイルストアのパスが無効です。
profilePage_deprecatedProfileDisplayName={0} ({1} で置き換え)
profileStoreCreationDialog_button_new=新規作成(&W)...
profileStoreCreationDialog_title=接続プロファイルストアの作成
profileStoreCreationDialog_grouptext=ストアに含める接続プロファイルを選択してください(&P) :
profileStoreCreationDialog_newbutton_tooltiptext=新しい接続プロファイルの作成
profileStoreCreationDialog_fileext_error=接続プロファイルストアファイルにはファイル拡張子を付ける必要があります。\nファイル名にデフォルトのファイル拡張子 ".{0}" を付けるには、[OK] を選択します。
dbProfilePage_defaultPageMessage=JDBC ドライバと接続の詳細を確認してください。
dbProfilePage_editPageMessage=JDBC ドライバと接続の詳細を編集してください。
dbProfilePage_invalidDataSource=データソースが無効です。  接続プロファイルの参照を更新するか、代わりに新しいデータソースを作成してください。
dbProfilePage_notInDesignSession=ODA デザインセッション以外でプロパティページを開くことはできません。
dbProfilePage_noCustomPage=データベースドライバ特有のプロパティページを検索して開くことはできません。
preferencesPage_openDataAccessDescription=このノードの下にあるページを使って、オープンデータアクセスの\n個々のデータソースを設定してください。
