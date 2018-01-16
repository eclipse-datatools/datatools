###############################################################################
# Copyright (c) 2001, 2004, 2008 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
# 
# Contributors:
#     IBM Corporation - initial API and implementation
###############################################################################
pluginName=Eclipse Data Tools Platform SQM UI Plug-in
providerName=Eclipse Data Tools Platform

#
## Extension Point Names
#
LABEL_SERVICE_EXTENSION_POINT_NAME=labelService
ELEMENT_ID_EXTENSION_POINT_NAME=elementID
MARKER_NAVIGATION_PROVIDER_EXTENSION_POINT_NAME=markerNavigationProvider
DATA_SOURCE_EXPLORER_SORTER=sorterValidatorProvider
FILTER_INFORMATION_EXTENSION_POINT_NAME=filterInformation

#
## Model Explorer Selection
#
datatools.core.ui.modelExplorerSelection = モデルエクスプローラの選択

#
## Preferences for Label Decoration
#
datatools.core.ui.preferences.labeldecorators = ラベルの装飾

#
## Preferences
#
datatools.core.ui.preferences.data = SQL 開発
datatools.core.ui.preferences.output = 出力

#
## Views
#
datatools.core.ui.modelElementProperties = データモデル要素のプロパティ
datatools.core.ui.views.interactiveSQL = SQL を表示

#
## Model Explorer
#
datatools.core.ui.contentProviders = 物理モデルのコンテンツのプロバイダ
datatools.core.ui.parserProviders = 物理モデルのパーサのプロバイダ
datatools.core.ui.iconProviders = 物理モデルのアイコンのプロバイダ

#
## Nature 
#
datatools.core.ui.projectNature = データベースプロジェクトのデータの本質

#
## Wizards
# 
datatools.core.ui.category.project = データベース
datatools.core.ui.category.model = データベースモデル
datatools.core.ui.project.basic = データデザインプロジェクト
datatools.core.ui.project.description = データデザインプロジェクトの作成
datatools.core.ui.model.basic = 物理データモデル

#
## Preferences
#
datatools.core.ui.preferences.modeler = データ
datatools.core.ui.preferences.default = データベースのデフォルト値

#
## Popup && Actions
#
datatools.core.ui.actions.close = 閉じる
datatools.core.ui.actions.add = SQL の追加
datatools.core.ui.actions.addMQT = MQT
datatools.core.ui.actions.addSchema = スキーマ
datatools.core.ui.actions.addTable = テーブル
datatools.core.ui.actions.addView = ビュー
datatools.core.ui.actions.addColumn = 列
datatools.core.ui.actions.delete = 削除
datatools.core.ui.actions.addTrigger = トリガ
datatools.core.ui.globalActionHandlerProviders = データのグローバルアクション
datatools.core.ui.actions.selectSupplier = 固有の制約テーブルへの移動
datatools.core.ui.actions.fe=DDL の生成...
datatools.core.ui.actions.re=DDL からのリバースエンジニアリング...
datatools.core.ui.actions.dataModeler = データモデル
datatools.core.ui.actions.addDomain = ドメイン
datatools.core.ui.actions.addSequence = シーケンス
datatools.core.ui.actions.addStoredProcedure = ストアドプロシージャ
datatools.core.ui.actions.addJavaStoredProcedure = Java ストアドプロシージャ
datatools.core.ui.actions.generateForeignKeyIndex = インデックス
datatools.core.ui.actions.generatePrimaryKeyIndex = インデックス
datatools.core.ui.actions.addDatabase = データベース
datatools.core.ui.actions.addTableIndex = インデックス
datatools.core.ui.actions.addTableCheckConstraint = チェックの制約
datatools.core.ui.actions.addUniqueConstraint = 固有の制約
datatools.core.ui.actions.addAlias = エイリアス
datatools.core.ui.actions.addDistinctUDT = 個別のユーザ定義型
datatools.core.ui.actions.addStructuredUDT = 構造化されたユーザ定義型
datatools.core.ui.actions.addMQTIndex = インデックス
datatools.core.ui.actions.addMQTTrigger = トリガ
#
## Editors
#
datatools.core.ui.model.editor = SQL モデルエディタ

#
## Decorators
#
datatools.core.ui.modelexplorer.modelExplorerColumnDecoration = データツール - 列のデータ型
datatools.core.ui.modelexplorer.modelExplorerForeignKeyDecoration = データツール - 外部キーまたはプライマリキーのインジケータ
datatools.core.ui.modelexplorer.DependencyDecoration = データツール - 依存関係の装飾
datatools.core.ui.modelexplorer.bookmarkDecoration  = データツール - ブックマークのインジケータ
datatools.core.ui.modelexplorer.filterDecoration = データツール - サーバエクスプローラのフィルタノードインジケータ
datatools.core.ui.modelexplorer.indextriggerDecoration = データツール - インデックスとトリガの装飾

#
## Model Explorer Sorter Provider
#
datatools.core.ui.modelexplorer.sorterProviders = モデルエクスプローラのソートプロバイダ
datatools.core.ui.modelexplorer.menu.storageOrderSorter = 保存の順序
datatools.core.ui.modelexplorer.menu.name = 名前
datatools.core.ui.modelexplorer.menu.type = 種類
datatools.core.ui.modelexplorer.menu.stereotype = ステレオタイプ
datatools.core.ui.modelexplorer.menu.visibility = 表示/非表示

#
## Model Explorer DnD
#
datatools.core.ui.modelexplorer.dragDrop = ドラッグ＆ドロップ

#
## Marker
#
datatools.core.ui.modelexplorer.marker.dangling = 宙吊り参照


#
## Type for Label Service 
#
database = データベース
schema = スキーマ
baseTable = テーブル
column = 列
domain = ドメイン
udt = ユーザ定義型
index = インデックス
procedure = ストアドプロシージャ
view = ビュー
parameter = パラメタ
trigger = トリガ
sequence = シーケンス
source= ソース
attributeDefinition = 属性の定義
function = ユーザ定義の関数
dependency = 依存関係
primaryKey = プライマリキー
foreignKey = 外部キー
folder = 論理フォルダ
CheckConstraint = チェックの制約
UniqueConstraint = 固有の制約
existingConnection = 接続フォルダ
connectedConnection = ライブ接続
disconnectedConnection = 切断された接続
cachedConnection = キャッシュされた接続
catalog = カタログ
user = ユーザ
group = グループ
role = 役割
authorization = 認証 ID

DEFAULT_SCHEMA_FILTER_PROPERTY_PAGE_NAME=デフォルトのスキーマフィルタ
DEFAULT_TABLE_FILTER_PROPERTY_PAGE_NAME=デフォルトのテーブルフィルタ
DEFAULT_STORED_PROCEDURE_FILTER_PROPERTY_PAGE_NAME=デフォルトのストアドプロシージャフィルタ
FILTER_SETTINGS_PROPERTY_PAGE_NAME=フィルタの設定
