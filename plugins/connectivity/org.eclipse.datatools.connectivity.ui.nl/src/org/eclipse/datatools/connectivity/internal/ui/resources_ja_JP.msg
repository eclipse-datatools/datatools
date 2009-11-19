## Copyright (c) 2005, 2009 Sybase, Inc.
## All rights reserved. This program and the accompanying materials
## are made available under the terms of the Eclipse Public License v1.0
## which accompanies this distribution, and is available at
## http://www.eclipse.org/legal/epl-v10.html
##
## Contributors:
##    Sybase, Inc. - initial API and implementation
##	  IBM Corporation = bug fix #203829
##	  Actuate Corporation - bug fix #261452
################################################################################ 
plugin.internal_error=内部エラー - org.eclipse.datatools.connectivity
ImportProfilesDialog.btnOverwrite.text=同じ名前を持つ既存の接続プロファイルを上書き(&O)
ExportProfilesDialog.button.text2=参照(&B)...
ExportProfilesDialog.group.text=エクスポートする接続プロファイルの選択\:
ExportProfilesDialog.button.text1=選択をすべて解除(&D)
ExportProfilesDialog.button.text=すべて選択(&S)
ImportProfilesDialog.button.text=参照(&B)...
ExportProfilesDialog.label.text=ファイル名の指定(&F)\:
ImportProfilesDialog.label.text=ファイル名の指定(&F)\:
ImportProfilesDialog.text.text=注\: インポートする接続プロファイルのタイプは、新規接続プロファイル・\nウィザードを使用して作成できるタイプでなければなりません。サポートされていないタイプの\n接続プロファイルは廃棄されます。
ExportProfilesDialog.null.title=接続プロファイルのエクスポート
ImportProfilesDialog.null.title=接続プロファイルのインポート
ExportProfilesDialog.btnEncryption.text=ファイルコンテンツを暗号化する(&E)
actions.export.noselection=少なくとも 1 つの接続プロファイルを選択してください。
actions.export.nofile=ファイル名を指定してください。
actions.export.norepository = ターゲットリポジトリを指定してください。
actions.export.notvalidfile = 有効なファイル名とパスを指定してください。
dialog.exception.title=例外
dialog.exception.message=キャッチされた例外\: {0}
NewCPWizard.title=新規接続プロファイル
IntroductionWizardPage.title=概要ページ
IntroductionWizardPage.intro=このウィザードは、接続プロファイルの作成手順を説明します。
CPWizardSelectionPage.title = 接続プロファイル
CPWizardSelectionPage.desc = 接続プロファイルの種類を選択してください。
CPWizardSelectionPage.group = 接続プロファイルの種類(&C) :
CPWizardSelectionPage.defaultName =新規 {0}
CPWizardSelectionPage.defaultNameExtended =新しい {0}({1})
actions.connect.job=サーバー接続ジョブ
actions.connect.uijob=ビューの更新中
actions.ping.job=サーバー ping ジョブ
actions.ping.uijob=ダイアログ表示ジョブ
actions.connect.title=サーバーに接続中...
actions.ping.title=サーバーの ping 中...
actions.ping.success=ping が正常に完了しました。
actions.ping.failure=ping が失敗しました。
actions.delete.title=削除の確認
actions.delete.confirm=接続プロファイル "{0}" を削除しますか?
properties.dialog=プロパティー・ダイアログ
properties.category.general=一般
properties.connectionprofile.name=プロファイル名
properties.connectionprofile.desc=説明
properties.connectionprofile.autoconnect=自動接続
properties.category.name=カテゴリー名
properties.category.id=カテゴリー ID
ServersView.action.showproperties=プロパティー(&P)
ServersView.action.refresh=更新(&R)
ServersView.action.newCP=新規(&N)...
ServersView.action.exportCPs=エクスポート(&E)...
ServersView.action.importCPs=インポート(&I)...
NewConnectionProfileWizard.title=新規接続プロファイル
NewConnectionProfileWizard.create.failure=接続プロファイルの作成の失敗
NewConnectionProfileWizardPage.Status.DuplicateName=接続プロファイル名はすでに存在します。
NewConnectionProfileWizardPage.Status.NoName=接続プロファイル名を指定してください。
NewConnectionProfileWizardPage.Status.NoRepository = リポジトリを指定してください。
NewConnectionProfileWizardPage.title = 接続プロファイルの作成
NewConnectionProfileWizardPage.desc = プロファイルの情報を入力してください。
NewConnectionProfileWizardPage.cp_name = 名前(&M) :
NewConnectionProfileWizardPage.cp_desc = 説明 (オプション)(&D) :
NewConnectionProfileWizardPage.choices=選択してください\:
NewConnectionProfileWizardPage.summary_cp_name=名前
NewConnectionProfileWizardPage.summary_cp_desc=説明
NewConnectionProfileWizardPage.Button.AutoConnect = 起動時に自動接続する(&A)
NewConnectionProfileWizardPage.Button.AutoConnect1 = スタートアップ時に自動接続
NewConnectionProfileWizardPage.summary_autoconnect = スタートアップ時に自動接続
NewConnectionProfileWizardPage.summary_autoconnect_on_finish = 終了時に自動接続
NewConnectionProfileWizardPage.localRepository = ワークスペースリポジトリに作成 (ローカル)
NewConnectionProfileWizardPage.chooseRepository = リポジトリの選択 :
ConnectionProfileDetailsPage.Button.TestConnection=接続テスト(&T)
ConnectionProfileDetailsPage.AskConfirmation=確認ダイアログ
ConnectionProfileDetailsPage.AskReconnect=この接続プロファイルの接続情報は変更されています。サーバーに再接続しますか?
SummaryWizardPage.title=要約
SummaryWizardPage.description=以下の情報が収集されました。
SummaryWizardPage.property=プロパティー
SummaryWizardPage.value=値
dialog.title.error=エラー
dialog.title.success=成功
rename.dialog.title=名前変更ダイアログ
rename.dialog.message=新規接続プロファイル名を入力してください\:
rename.dialog.errmsg.existing=名前は存在しています。別の名前を入力してください。
rename.dialog.errmsg.invalid=無効な名前です。有効な名前を入力してください。
error.connect={1}。  詳細についてはログ・ファイルを参照してください。
error.contentExtension=プロファイル "{1}" の "{0}" 拡張への接続中にエラーがありました (エラー\: {2})
CPVersionPropertyPage.desc={0} のバージョン情報。\nこの情報は直前の接続試行から取得されました。  直前の接続試行が正常に行われなかった場合、使用可能なバージョン情報はありません。
CPVersionPropertyPage.button.updateVersion=接続してバージョン情報を更新
CPVersionPropertyPage.label.versionUnavailable=このプロファイルのバージョン情報が使用できません。
CPVersionPropertyPage.label.techVersions=このサーバーでは以下のテクノロジーが使用可能です\:
ConnectionProfileSummaryPage.button.testConnection=接続テスト(&T)
CommonLabelProviderBase.label.version={0} ({1} v. {2}) 
CommonLabelProviderBase.label.version.versionless={0} ({1}) 
CommonLabelProviderBase.label.localRepository=ワークスペースリポジトリ (ローカル)

properties.connectionprofile.type = プロファイルの種類
DSE.Refresh.label = 更新(&R)
DSE.View.Tooltip = データソースのエクスプローラ

CPDeleteChange.name = {0} 接続プロファイルの削除
CPDeleteChange.error.ProfileDoesNotExist = 削除しようとしている接続プロファイルは存在しません。
CPDeleteChange.delete.failure = 接続プロファイルを削除できませんでした。
CPDeleteProcessor.name = DTP 接続の削除プロセッサ
CPDuplicateProcessor.name = DTP 接続プロファイルの重複プロセッサ
CPCopyChange.name = {0} 接続プロファイルの重複
CPCopyChange.error.ProfileDoesNotExist = 重複しようとしている接続プロファイルは存在しません。
CPCopyChange.title.error = 接続プロファイルを重複できませんでした。
CPCopyChange.error.InvalidTarget = 指定されたターゲットは、有効な接続プロファイルのリポジトリではありません。
CPCopyChange.error.RepositoryNotConnected = ターゲットの接続プロファイルリポジトリ {0} に接続されていません。
CPMoveProcessor.name = DTP 接続プロファイルの移動プロセッサ
CPMoveChange.name = {0} 接続プロファイルの移動
CPMoveChange.error.ProfileDoesNotExist = 移動しようとしている接続プロファイルは存在しません。
CPMoveChange.title.error = 接続プロファイルを移動できませんでした。
CPCreateChange.name = {0} 接続プロファイルの作成
CPCreateChange.error.NewProfileDoesNotHaveName = 新しい接続プロファイルを作成するには、名前が必要です。
CPCreateChange.error.NewProfileAlreadyExists = この名前の接続プロファイルは、すでに使用されています。
CPCreateChange.error.NewProfileDoesNotHaveProviderID = 新しい接続プロファイルを作成するには、接続プロファイルのプロバイダ ID が必要です。
CPCreateChange.error.NewProfileDoesNotHaveNonNullPropertiesObject = 新しい接続プロファイルを作成するには、Null 以外のプロパティオブジェクトが必要です。
CPCreateChange.create.failure = 接続プロファイルを作成できませんでした。
CPCreateProcessor.name = DTP 接続の作成プロセッサ
CPRenameChange.name = {0} 接続プロファイルの名前の変更
CPRenameProcessor.name = DTP 接続の名前の変更プロセッサ
CPRenameChange.title.error = 接続プロファイルの名前を変更できませんでした。

PropertyDescriptor.error.title = ドライバ定義のプロパティ記述子の処理エラー
PropertyDescriptor.error.msg = ドライバ定義のプロパティを処理中にエラーが発生しました。
PasswordTextPropertyDescriptor.property.label = パスワード
DriverClassBrowsePropertyDescriptor.property.label = ドライバクラス
DriverClassBrowsePropertyDescriptor.jardialog.title = jar リストの使用可能なクラス
DriverClassBrowsePropertyDescriptor.jardialog.msg = ドライバクラスの名前を入力するか、使用可能な JAR の一覧からクラスを選択してください。
DriverClassBrowsePropertyDescriptor.noclasses.title = ドライバクラスがありません
DriverClassBrowsePropertyDescriptor.noclasses.msg = 現在の jar リストには、使用できるドライバクラスがありません。

StatusBar.Connected = 接続されています
StatusBar.Disconnected = 切断されています
StatusBar.ConnectedMessage = {0} ({1})

StatusBar.WorkingOffline = オフラインで作業中

DATATOOLS.SERVER.UI.EXPLORER.WORKOFFLINE = オフラインで作業(&W)
DATATOOLS.SERVER.UI.EXPLORER.UPDATE_OFFLINE = オフラインで保存(&S)

DriverPropertyEditorDescriptor.InvalidID = ドライバプロパティエディタの記述子に、有効な ID が含まれていません。
DriverPropertyEditorDescriptor.InvalidTargetTemplateID = ドライバプロパティエディタの記述子に、有効なターゲットテンプレート ID が含まれていません。
DriverPropertyEditorDescriptor.InvalidTargetPropertyId = ドライバプロパティエディタの記述子に、有効なターゲットプロパティ ID が含まれていません。
DriverPropertyEditorDescriptor.InvalidCustomPropertyEditorClass = ドライバプロパティエディタの記述子に、有効なカスタムプロパティエディタが含まれていません。

ExtensibleProfileDetailsWizardPage.title = ドライバと接続の詳細を指定
ExtensibleProfileDetailsWizardPage.description = ドロップダウンリストからドライバを選択し、接続用のログイン情報を指定してください。
ExtensibleProfileDetailsWizardPage.driverCombo.label = ドライバ(&D) :
ExtensibleNewConnectionProfileWizard.title = 新しい接続プロファイル

DriverUIContributorComposite.group = プロパティ(&P)

CommonDriverUIContributor.optionaltab = オプション(&O)
CommonDriverUIContributor.optionalProps.label = その他のプロパティ
CommonDriverUIContributor.generaltab = 全般(&E)

OtherDriverUIContributor.databaseName.label = データベース(&A) :
OtherDriverUIContributor.url.label = URL(&U) :
OtherDriverUIContributor.userName.label = ユーザ名(&U) :
OtherDriverUIContributor.password.label = パスワード(&W) :
OtherDriverUIContributor.savePassword.label = パスワードの保存(&V)
OtherDriverUIContributor.summaryData.database = データベース
OtherDriverUIContributor.summaryData.username = ユーザ名
OtherDriverUIContributor.summaryData.savePassword = パスワードの保存
OtherDriverUIContributor.summaryData.true = true
OtherDriverUIContributor.summaryData.false = false
OtherDriverUIContributor.summaryData.url = URL

OtherDriverUIContributor.errormessage.requiresdatabase = データベース名を入力してください。
OtherDriverUIContributor.errormessage.requiresurl = URL を入力してください。
OtherDriverUIContributor.errormessage.requiresusername = ユーザ名を入力してください。

ConnectAction.title = {0} のプロパティ

Boolean.true = true
Boolean.false = false

NewRepositoryWizard.title = レポジトリの新規作成ウィザードウィンドウ

NewConnectionProfileWizardPage.Status.ContainsColon= 接続プロファイル名にはコロン (:) を使用できません。
