###############################################################################
# Copyright (c) 2004, 2008 Actuate Corporation.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License 2.0
# which accompanies this distribution, and is available at
# https://www.eclipse.org/legal/epl-2.0/
# 
# Contributors:
#  Actuate Corporation - initial API and implementation
#  
###############################################################################
# wizard dialog messages
wizard.title.selectFolder=XML データソース
wizard.defaultMessage.selectFolder=XML ファイルとスキーマ情報に URL を定義してください。
wizard.title.newDataSet=新規データセット
wizard.title.selectXmlFile=プレビューする XML データの選択
wizard.defaultMessage.selectFile=このセッションでデータをプレビューするには、サンプルの XML ソースを選択してください。
wizard.title.xPathChoose=テーブルマッピングの定義
wizard.defaultMessage.selectXPath=XML 構造から要素を選択して、データセット行のマッピングを定義します。\n選択した XML 要素は、XML 文書内で使用されるたびにデータセット行に割り当てられます。
wizard.title.selectColumn=列マッピングの定義
wizard.title.defineColumnMapping=XML 構造から項目を選択・追加して、データセットの列を作成します。\n追加した XML 要素または属性は、データセット列に割り当てられます。 

#preference
designer.preview.preference.numberOfElementPassedToGetSchema=XML スキーマで解析するデータ要素の数 :
designer.preview.preference.numberOfElementPassedToGetSchema.errormessage=0～{0} までの数値を入力してください。
designer.preview.preference.columnMappingPage.group.title=列のマッピング

#XML data source folder selection wizard page
XMLSelectionWizardPage.message.error.emptyXMLSource=XML ソース URL を指定するか、XML ファイルを参照してください。

#row mapping page messages
xPathChoosePage.messages.elementSelection.label=XML 要素の選択
xPathChoosePage.messages.elementSelection.item.absolutePath=固定された絶対パスにある XML 要素 "{0}"(&X)\n
xPathChoosePage.messages.elementSelection.item.anyLocation=任意の場所にある XML 要素 "{0}"(&E)\n
xPathChoosePage.messages.elementSelection.disable.absolutePath=固定された絶対パスにある XML 要素(&X)\n 
xPathChoosePage.messages.elementSelection.disable.anyLocation=任意の場所にある XML 要素(&E)\n 
xPathChoosePage.messages.elementSelection.item.custom=カスタム XPath 式(&U)
xPathChoosePage.messages.xPathExpression=XPath 式(&X)
xPathChoosePage.messages.xmlStructure=XML 構造
xPathChoosePage.messages.xmlColumnMapping=列マッピング
xPathChoosePage.messages.xpathChange=変更後のテーブルマッピングが列マッピングと互換性があることを確認してください (該当する場合)。   
xPathChoosePage.FileNameIcon=ファイル名アイコン
xPathChoosePage.XMLElementIcon=XML 要素アイコン
xPathChoosePage.ColumnIcon=列アイコン
xPathChoosePage.messages.btnAdd.tooltip=選択したノードを XPath 式として使用します。
xPathChoosePage.messages.noURLOfSourceOrSchema=XML ソースまたは XML スキーマの URL が指定されていません。

#sample xml data file page messages
xmlDataSetSelectionPage.messages.lineofdata=行をプレビュー(&P)
xmlDataSetSelectionPage.warning.errorReadXMLFile=ファイル [{0}] {1} から XML データを読み込めません。\n続行しますか ?

#column mapping page messages
button.refresh=更新
menu.menuItem.removeAll=すべて削除
menu.menuItem.remove=削除
menu.button.add=追加(&A)...
menu.button.edit=編集(&E)...
menu.button.remove=削除(&R)
menu.button.up=上へ(&U)
menu.button.down=下へ(&D)
menu.button.preview=サンプルデータの表示(&S)
lable.selectXmlFile=XML ソースの URL を入力するか、データが入っているファイルを参照してください(&X) :
label.selectEncoding=上で指定した XML ソースとスキーマのエンコード(&E) :
lable.selectXmlSchmaFile=XML スキーマの URL を入力するか、スキーマが入っている\nファイルを参照してください。スキーマがない場合は、このフィールドを空白のままにしておいてください(&M) :
label.preview=ソースデータの最初の
label.useXMLFileFromDataSource=データソースに定義されている XML ファイルを使用する(&U)
RowMappingDialog.title=XPath 式の選択・編集
RowMappingDialog.info=選択したノードに適用する定義済みの XPath 式を\n選択するか、別の式を入力してください :
ColumnMappingPage.AddSingleButton.tooltip=選択したノードを列マッピングとして使用します。
ColumnMappingPage.AddAllButton.tooltip=選択したノードのすべての子列を列マッピングとして使用します。
ColumnMappingTable.upButton.tooltip=列マッピングを上に移動します。
ColumnMappingTable.downButton.tooltip=列マッピングを下に移動します。
ColumnMappingTable.removeButton.tooltip=列マッピングを削除します。
ColumnMappingTable.editButton.tooltip=列マッピングを編集します。
ColumnMappingTable.previewButton.tooltip=サンプル XML データをプレビューします。 
ColumnMappingDialog.group.message=XPath の選択
ColumnMappingDialog.info.columnName=列名(&C) :
ColumnMappingDialog.info.xPath=XPath 式(&X) :
ColumnMappingDialog.info.dataType=データ型(&D) :
ColumnMappingDialog.dialog.info=現在の列に定義済みの XPath を選択するか、別の XPath 式を入力してください。 
ColumnMappingDialog.prompt.maxRow=500 行以上のソースデータがあります。このページには最初の 500 行だけが表示されます。
ColumnMappingDialog.MenuItem.CreateParameter=パラメタの作成
ColumnMappingDialog.MenuItem.DeleteParameter=パラメタの削除
XMLDataPreviewDialog.title=サンプル XML データ

dataset.editor.columnName=名前
dataset.editor.xpathexpression=XPath
dataset.editor.datatype=型
dataset.editor.rowelement=行要素
dataset.error.populateXMLTree=XML データソースを取得できません。 XML データソースファイルが無効かファイルが存在しません。
dataset.error.CannotPopulateXMLTree=XML データソースを取得できません。

error.label=エラー
error.selectFolder=選択したファイルの URL は無効か、ファイルが存在しません。
error.emptyPath=ファイルの URL が空白です。
error.xpath.canNotContainAttribute=XPath 式は XML 属性にマッピングできません。
error.xpath.getPathList=選択した XML ツリー項目で XPath リストを取得中にエラーが発生しました。
error.dataset.Error.MissingFileLocation=XML ファイルの場所を指定してください。
error.dataset.maxRowNumberError=最大行数には、数値を指定してください。
error.columnMapping.createPage=列マッピングページでエラーが発生しました。 
error.columnMapping.columnElement.create=列マッピング要素を割り当て中にエラーが発生しました。
error.columnMapping.columnElement.edit=列マッピング要素の編集中にエラーが発生しました。
error.columnMapping.ATreeNode.getChildren=選択した XML 要素の子ノードをフェッチできません。
error.columnMapping.SelectedTreeItem.notNull=列マッピング要素を作成するツリー項目が選択されていません。1 つまたは複数のツリー項目を選択してください。
error.columnMapping.noMappingColumn=少なくとも 1 つの列をマッピングしてください。
error.columnMapping.tableMappingXPathNotExist=テーブルをマッピングするための XPath が存在しません。
error.file.null=データソースが選択されていないため、URL がすべて空白になっています。このまま続行しますか ?
error.columnMapping.sameColumnName=列名 [{0}] はすでに使用されています。別の名前を使用してください。
error.invalidXpath=XPath 式が無効です。
error.invalidSource=xml ソースファイルが見つかりません。

#data types for column mapping
datatypes.dateTime=Date Time
datatypes.decimal=Decimal
datatypes.float=Float
datatypes.integer=Integer
datatypes.string=String
datatypes.date=Date
datatypes.time=Time
datatypes.boolean=Boolean

#column name in column mapping table
columnName.name=名前
columnName.xPathName=XPath
columnName.typeName=型

dataset.new=新規データセット
file.choose=参照(&O)...
schema.choose=参照(&S)...
title.warning=警告
FileSelectionButton.menuItem.absolutePath=絶対パス(&A)
FileSelectionButton.menuItem.relativePath=相対パス(&R)
SelectRelativeFileDialog.Title=ファイルの選択

datasource.propertyPage.tooltip=XML データソース
wizard.autoEncoding=自動
