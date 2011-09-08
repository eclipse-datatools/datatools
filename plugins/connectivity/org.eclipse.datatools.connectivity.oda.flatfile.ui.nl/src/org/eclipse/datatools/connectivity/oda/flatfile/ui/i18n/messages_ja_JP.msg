wizard.title.selectColumns=列の選択
wizard.defaultMessage.selectFolder=フラットファイルが入っているフォルダを選択するか、フラットファイルの URI を入力してください。
wizard.defaultMessage.selectFile=データセットのファイルと列を選択してください。

label.selectFolder=ホームフォルダの選択(&E): 
label.fileURI=ファイルの URI の入力(&U):
lable.fileURI.tooltip=ファイルの URI を入力するか、ローカルファイルを選択してください。 
label.selectFile=ファイルの選択(&I) :
label.fileFilter=ファイルフィルタ(&R) :
label.includeTypeLine=2 行目をデータ型のインジケータとして使用する(&D)
label.includeColumnNameLine = 1 行目を列名のインジケータとして使用する(&A)
label.selectCharset=文字セットの選択(&C) :
label.selectFlatfileStyle=フラットファイルのスタイルの選択(&S) :
label.flatfileComma=CSV
label.flatfileSemicolon=SSV
label.flatfilePipe=PSV
label.flatfileTab=TSV
label.trailNull=末尾 NULL 列を使用する(&N)

button.selectFolder.browse=参照(&R)...
button.selectFileURI.browse=参照(&B)...
button.selectFileURI.menuItem.relativePath=相対パス
button.selectFileURI.menuItem.absolutePath=絶対パス
button.selectFileURI.browse.tooltips=ローカルファイルの URI を選択してください。
button.moveUp=上へ(&U)
button.moveDown=下へ(&W)
button.delete=削除(&E)

editor.title.name=名前
editor.title.originalName=元の名前
editor.title.type=型


datatypes.dateTime=日時
datatypes.decimal=10進数値
datatypes.float=浮動小数値
datatypes.integer=整数値
datatypes.string=文字列
datatypes.date=日付
datatypes.time=時間
datatypes.boolean=論理

tooltip.flatfilestyle=フラットファイルの各スタイルにマウスのポインタを置くと詳しい説明が表示されます。
tooltip.csv=値をコンマ { , } で区切る
tooltip.ssv=値をセミコロン { ; } で区切る
tooltip.psv=値をパイプ { | } で区切る
tooltip.tsv=値をタブで区切る
tooltip.columnnameline=選択したファイルの 1 行目が列名であることを\n確認してください。
tooltip.typeline=選択したファイルの 2 行目がデータ型であることを\n確認してください。
tooltip.button.add=選択したすべての列を、左のテーブルからフラットファイルのデータセットに追加します。
tooltip.button.up=選択した項目を上へ移動します。
tooltip.button.down=選択した項目を下へ移動します。
tooltip.button.delete=選択した項目を削除します。
tooltip.trailNull=データ行を読むときに末尾 NULL 列を使用します。 
error.selectFolder=有効なフォルダーを選択してください。
error.selectColumns=このデータ・セットに対して、1 つ以上の列を選択してください。
error.emptyPath=パスを入力してください。
error.unexpectedError=原因不明のエラーが発生しました。
error.invalidConnectionFilePath=接続プロファイルのソースパスとして "{0}" が指定されましたが、このパスは存在しません。
error.invalidFlatFilePath=フラットファイルのデータソースパスが無効です。接続プロファイルを修正してください。
error.errorExist=現行ページにエラーがあります。  保存しますか?
error.noCSVFiles=フォルダー "{0}" には指定されたファイルがありません。
error.invalidFilePath=ファイル・パスが無効です。  続行しますか?
error.emptyFilePath=ファイル・パスが空です。  続行しますか?
error.duplicatedNameValueOrEmpty=名前の値が重複しているか、空白です。
error.invalidSavedColumnsInfo = 保存された列情報が無効です。
error.invalidColumnName=列名が無効です。
error.invalidQureyText=クエリテキストが無効です。
error.invalidURIPath=無効な URI が入力されました。
error.URIConnectionFailure=URI リソースへの接続を開けません。

title.warning=警告
warning.fileExtensionInvalid=選択したファイルは、有効なフラットファイルではない可能性があります。
confirm.reselectFileFilterTitle=異なるファイル・フィルターを選択することを確認してください。
confirm.reselectFileFilterMessage=別のファイルフィルタを選択すると、これまでの作業が保存されずに破棄されます。\n\nこのフィルタを選択しますか ?

confirm.reselectFileNameTitle=別のフラットファイルの選択の確認
confirm.reselectFileNameMessage=現在の定義と同じ列選択を維持しますか ?
warning.columnNotExist=選択した列 "{0}" が存在しません。

fileURIChanged.warning.reselectColumnsTitle=データソース内のファイルの URI が変更されています。
fileURIChanged.warning.reselectColumnsMessage=データソース内のファイルの URI が変更されています。\n列をもう一度選択してください。
Connection.warning.untested=接続がテストされていません。 このデータソースプロファイルを保存する前に接続をテストしてください。
Connection.error.invalidPath=有効なフラットファイルの場所が見つかりません。
Connection.error.invalidHomeFolder=フラットファイルのホームフォルダ <{0}> が無効です。
Connection.error.invalidFileURI=プラットファイルの URI パス <{0}> が無効です。
