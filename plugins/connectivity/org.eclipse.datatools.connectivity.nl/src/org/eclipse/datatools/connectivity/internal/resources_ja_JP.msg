## Copyright (c) 2005, 2011 Sybase, Inc. and others.
## All rights reserved. This program and the accompanying materials
## are made available under the terms of the Eclipse Public License v1.0
## which accompanies this distribution, and is available at
## http://www.eclipse.org/legal/epl-v10.html
##
## Contributors:
##    Sybase, Inc. - initial API and implementation
##    IBM Corporation - migrated to new wizard framework
##    IBM Corporation - fix for defect 222818
################################################################################ 
plugin.internal_error=内部エラー - org.eclipse.datatools.connectivity
ConnectionProfileConstants.unknownVersion=不明
dialog.title.error.loadconnectionfactory="{0}" での接続ファクトリーのロード中エラーが発生しました。
ConnectJob.name={0} への接続を作成中です。
ConnectJob.status.info=1 つ以上の拡張に対して {0} への接続を作成できませんでした。
ConnectJob.status.error={0} へ接続できませんでした。
DisconnectJob.name={0} への接続を終了中です。
DisconnectJob.status.info=1 つ以上の拡張に対して {0} への接続を終了できませんでした。
DisconnectJob.status.error={0} を切断できませんでした。
CreateConnectionJob.name={0} を {1} に作成中。
CreateConnectionJob.error={1} への {0} 接続の作成中にエラーが発生しました。 (エラー\: {2})
OpenConnectionEventJob.name={0} の接続オープン・イベントを処理中です。
CloseManagedConnectionJob.name={1} への {0} 接続を切断します。
CloseManagedConnectionJob.error={1} への {0} 接続を切断中にエラーが発生しました (エラー\: {2})。
CloseConnectionEventJob.name={0} の接続終了イベントを処理中です。
assert.invalid.profile=構文解析エラー\: 無効な属性です ({0})
assert.invalid.profile.duplicateFactory = 解析エラー \: ファクトリ ID での優先順位 ({0}) が重複しています。
assert.invalid.profile.nullFactory = 解析エラー \: ファクトリまたはファクトリ ID が NULL ({0}/{1}) です。
dialog.title.error.loadwizard="{0}" での新規ウィザードのロード中にエラーが発生しました。
operation.new.connectionprofile=プロファイルの作成中です\: "{0}"
error.connect=プロファイル "{0}" の接続中に問題が発生しました。詳細についてはログ・ファイルを参照してください。
marker.error=プロファイル {0} の接続中に問題が発生しました。  (エラー\: {1})
drivermarker.error = ドライバ "{0}" に問題があります (エラー \: {1})
drivermarker.import.error = プロファイル "{0}" のインポート中に作成されたドライバ定義に JAR の一覧がありません。
profile.duplicate =プロファイル名 "{0}" の接続が重複しています\!
profile.notexist=接続プロファイル "{0}" が存在しません\!
duplicate.profile.name={0} のコピー ({1})
DriverConnectionBase.error.unknown=接続が、未指定のエラーで失敗しました。
DriverConnectionBase.error.driverDefinitionNotSpecified=ドライバー定義が指定されていません。
DriverConnectionBase.error.driverDefinitionNotFound=ドライバー定義が見つかりません。
error.loadprofilesxml=接続プロファイル・データのロード中にエラーが発生しました。
error.saveprofilesxml=接続プロファイル・データの保管中にエラーが発生しました。
error.loaddriversxml = ドライバ定義のデータを読み込み中にエラーが発生しました。
error.savedriversxml = ドライバ定義のデータを保存中にエラーが発生しました。
trace.error.propertiesPersistenceHook=propertiesPersistenceHook をロードできませんでした。デフォルトを使用します (プロファイル・タイプ\={0}, 拡張子\={1})
trace.error.propertiesPersistenceHookSaveError=プロパティー永続フックの起動中にエラーが発生しました; すべてのプロパティーを保持します (プロファイル\={0}、拡張子\={1})
trace.error.propertiesPersistenceHookLoadError=プロパティー永続フックの起動中にエラーが発生しました; 一時プロパティーが設定されていません (プロファイル\={0}、拡張子\={1})
trace.error.migration = 接続プロファイルのプロバイダ (ID={0}、移行クラス={1}) の移行要素を読み込み中にエラーが発生しました。
trace.error.drivermigration = プロファイルのドライバインスタンスの作成中にエラーが発生しました (id={0}, driver id={1})。

migration.error.providerDoesNotExist = 移行要素の読み込み中にエラーが発生しました。指定した newProviderID が無効です (newProviderID={0})。
migration.error.invalidPropertyMigrationElement = 移行要素の読み込み中にエラーが発生しました。propertyMigration には、newPropertyKey か oldPropertyKey、またはその両方を指定する必要があります。
migration.error.unknownParentElement = 移行要素の読み込み中にエラーが発生しました。親要素が不明です (親要素名={0})。

DriverUtil.taskName = {0} でクラスを処理中です。
Category.Name.Unknown=サポートされていません。

WorkOfflineJob.name = オフラインモードで作業するために {0} を変更しています。
WorkOfflineJob.status.error = 1 つまたは複数の拡張機能用の {1} へのオフライン接続を確立できません。

SaveWorkOfflineDataJob.name = {0} のオフラインデータを保存します。
SaveWorkOfflineDataJob.status = オフラインデータのステータスを保存します。

CreateOfflineConnectionJob.name = {1} へのオフラインの {0} 接続を確立しています。
CreateOfflineConnectionJob.error = {1} へのオフラインの {0} 接続を確立中にエラーが発生しました (エラー\: {2})。

ManagedConnection_save_not_connected_error = オフラインデータを保存するには、サーバに接続する必要があります。
ManagedConnection_offline_not_supported_error = {0} ではオフラインで作業できません。

drivers.error.finding.property.no.template = ドライバのテンプレートから {0} を取得しようとしたときにエラーが発生しました。

JDBCConnection.technologyName = JDBC
JDBCConnection.invalidDriverDefinition = {0}\: {1}. データベースプロファイルで定義したドライバのプロパティを代わりに使用します。

error.database.not.start = データベースの起動コマンドは発行されていません。 ErrorCode = {0}、SQLState = {1}
PluginResourceLocator.invalidURL = URL {0} が無効です (エラー \: {1})

