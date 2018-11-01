#
#************************************************************************
# Copyright (c) 2007 Actuate Corporation.
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
# Externalized messages of the Web Services Data Source Designer

#page.type.name

# Common

# WebServiceSelectionPage
webServiceSelectionPage.message.default=Web サービスデータソース
webServiceSelectionPage.message.error=SOAP エンドポイントの URL を指定するか、WSDL 記述子を選択してください。
webServiceSelectionPage.group.wsdl=WSDL 記述子
webServiceSelectionPage.group.endPoint=SOAP エンドポイント(&S)
webServiceSelectionPage.group.customClass=カスタムドライバクラス(&C)
webServiceSelectionPage.group.classPath=ドライバクラスのパス(&D)
webServiceSelectionPage.label.wsdl=Web サービスの WSDL 記述子が使用可能な場合は、その URL を入力するか、ローカルファイルシステム内での場所を参照して指定してください。
webServiceSelectionPage.label.wsdlURI=WSDL の URL または場所(&W) :
webServiceSelectionPage.label.endPoint=SOAP のエンドポイント URL の指定 (WSDL で定義したエンドポイントを使用する場合や、エンドポイントの \nURL を必要としないカスタム接続クラスを使用する場合は、この値を省略できます)
webServiceSelectionPage.label.customClass=カスタム Java クラスを使用して Web サービスへの接続やクエリを行う場合は、ここに完全修飾クラス名を入力してください。
webServiceSelectionPage.label.classPath=追加の JAR ファイルや、カスタムドライバクラスの検索先ディレクトリをセミコロン (;) で区切ったリスト
webServiceSelectionPage.button.browse=参照(&R)...

# OperationPage
operationPage.message.default=データソースの WSDL 記述子で定義されている処理を選択してください。
operationPage.message.regenerate.SOAPRequest=変更された操作に従って、SOAP リクエストの再生成とエンドポイントへの再接続を行います。
operationPage.title.operationChanged=操作の変更
operationPage.label.selectOpearation=選択済みの処理(&S)
operationPage.label.document=文書(&D)

# SoapParametersPage
soapParametersPage.message.default=指定した WSDL 操作のパラメタを入力してください。
soapParametersPage.button.selectAll=すべて選択(&S)
soapParametersPage.button.deselectAll=すべて選択解除(&D)
soapParametersPage.column.paramName=名前(&A)
soapParametersPage.column.dataType=データ型(&T)

# SoapRequestPage
soapRequestPage.message.defaultTitle=このデータセットの SOAP リクエストテンプレートを編集してください。 
soapRequestPage.message.regenerateTemplate=指定した SOAP 操作に基づいたクエリテキストのテンプレートが再生成されるため、テキストへの変更はすべて上書きされます。\n\n続行しますか ?
soapRequestPage.label.prompt=カスタム接続クラスにクエリテキストが必要ない場合は、テキストを入力する必要はありません。
soapRequestPage.title.regenerateTemplate=テンプレートの再生成
soapRequestPage.button.regenerateTemplate=テンプレートの再生成(&R)
soapRequestPage.button.insertParameter=パラメタの編集(&E)...
soapRequestPage.button.clear=クリア(&C)
soapRequestPage.paramEditDialog.title=パラメタプロパティの編集

# ParameterInputDialog
parameterInputDialog.column.name=名前(&N)
parameterInputDialog.column.type=データ型(&T)
parameterInputDialog.column.defaultValue=デフォルト値(&V)
parameterInputDialog.button.edit=編集(&E)...

# SoapResponsePage
soapResponsePage.message.default=SOAP 応答の編集
soapResponsePage.group.schema=SOAP 応答スキーマの選択
soapResponsePage.group.xml=サンプル SOAP 応答メッセージの選択
soapResponsePage.radio.defaultSchema=データソース WSDL で定義した処理応答スキーマを使用する(&O)
soapResponsePage.radio.reponseSchema=レスポンスからのスキーマを使用する(&R)
soapResponsePage.radio.externalSchema=外部 XML スキーマファイルを使用する(&X) :
soapResponsePage.radio.endPoint=SOAP エンドポイントで Web サービスをクエリする(&E) :
soapResponsePage.radio.externalXML=外部サンプルデータファイルを使用する(&D)
soapResponsePage.button.browse1=参照(&W)...
soapResponsePage.button.browse2=参照(&S)...
soapResponsePage.button.connect=今すぐ接続(&C)

# XMLTableMappingPage

# XMLColumnMappingPage

# WSConsole
wsConsole.message.error.cantRetrieveSOAPResponse=SOAP 応答を取得できません。ネットワークと SOAP 応答の設定を確認してください。
