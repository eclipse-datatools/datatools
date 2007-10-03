#
#************************************************************************
# Copyright (c) 2007 Actuate Corporation.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
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
webServiceSelectionPage.message.default=Web サービスのデータソース
webServiceSelectionPage.message.error=SOAP エンドポイントの URL を指定するか、WSDL 記述子を選択してください。
webServiceSelectionPage.group.wsdl=WSDL 記述子
webServiceSelectionPage.group.endPoint=SOAP エンドポイント
webServiceSelectionPage.group.customClass=カスタムドライバクラス
webServiceSelectionPage.label.wsdl=Web サービスの WSDL 記述子が使用可能な場合は、その URL を入力するか、ローカルファイルシステム内での場所を参照して指定してください。
webServiceSelectionPage.label.wsdlURI=WSDL の URL または場所(&W) :
webServiceSelectionPage.label.endPoint=SOAP のエンドポイント URL の指定(&E) (WSDL で定義したエンドポイントを使用する場合や、エンドポイントの \nURL を必要としないカスタム接続クラスを使用する場合は、この値を省略できます)
webServiceSelectionPage.label.customClass=カスタム Java クラスを使用して Web サービスへの接続やクエリを行う場合は、ここに完全修飾クラス名を入力してください(&J)
webServiceSelectionPage.button.browse=参照(&B)...

# OperationPage
operationPage.message.default=データソースの WSDL 記述子で定義されている処理を選択してください。
operationPage.message.operationChanged=SOAP リクエストの生成とエンドポイントへの接続をやり直しますか ?
operationPage.title.operationChanged=操作の変更
operationPage.label.selectOpearation=選択済みの処理
operationPage.label.document=文書

# SoapParametersPage
soapParametersPage.message.default=指定した WSDL 操作のパラメタを入力してください。

# SoapRequestPage
soapRequestPage.message.default=このデータセットの SOAP リクエストテンプレートを編集してください。データソースのカスタム接続クラスコードにクエリテキストが必要ない場合は、テキストを入力する必要はありません。
soapRequestPage.message.regenerateTemplate=指定した SOAP 操作に基づいたクエリテキストのテンプレートが再生成されるため、テキストへの変更はすべて上書きされます。\n\n続行しますか ?
soapRequestPage.title.regenerateTemplate=テンプレートの再生成
soapRequestPage.button.regenerateTemplate=テンプレートの再生成(&R)
soapRequestPage.button.insertParameter=パラメタの挿入(&I)...
soapRequestPage.button.clear=クリア(&C)

# ParameterInputDialog
parameterInputDialog.column.name=名前
parameterInputDialog.column.type=データ型
parameterInputDialog.column.defaultValue=デフォルト値

# SoapResponsePage
soapResponsePage.message.default=SOAP 応答の編集
soapResponsePage.group.schema=SOAP 応答スキーマの選択
soapResponsePage.group.xml=サンプル SOAP 応答メッセージの選択
soapResponsePage.radio.defaultSchema=データソース WSDL で定義した処理応答スキーマを使用する(&O)
soapResponsePage.radio.externalSchema=外部 XML スキーマファイルを使用する(&S) :
soapResponsePage.radio.endPoint=SOAP エンドポイントで Web サービスをクエリする(&E) :
soapResponsePage.radio.externalXML=外部サンプルデータファイルを使用する(&D)
soapResponsePage.button.browse=参照(&B)...
soapResponsePage.button.connect=今すぐ接続(&C)

# XMLTableMappingPage

# XMLColumnMappingPage

# WSConsole
wsConsole.message.error.cantRetrieveSOAPResponse=SOAP 応答を取得できません。ネットワークと SOAP 応答の設定を確認してください。
