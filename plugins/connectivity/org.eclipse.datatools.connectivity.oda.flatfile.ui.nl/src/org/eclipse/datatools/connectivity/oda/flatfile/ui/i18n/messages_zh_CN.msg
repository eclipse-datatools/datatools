wizard.title.selectColumns=选择列
wizard.defaultMessage.selectFolder=选择包含平面文件的文件夹。
wizard.defaultMessage.selectFile=为数据集选择文件和列。

label.selectFolder=S&elect home folder: 
label.fileURI=Enter file &URI:
lable.fileURI.tooltip=Enter a URI for the file or select a local file. 
label.selectFile=选择文件(&I):
label.fileFilter=文件筛选(&R):
label.includeTypeLine=将第二行用作数据类型指示器(&D)。
label.includeColumnNameLine = 将第一行用作列名称指示器(&A)。
label.selectCharset=选择字符集(&C):
label.selectFlatfileStyle=选择平面文件样式(&S):
label.flatfileComma=CSV
label.flatfileSemicolon=SSV
label.flatfilePipe=PSV
label.flatfileTab=TSV
label.trailNull=使用空后缀列(&N)。

button.selectFolder.browse=浏览(&R)...
button.selectFileURI.browse=&Browse...
button.selectFileURI.menuItem.relativePath=Relative path
button.selectFileURI.menuItem.absolutePath=Absolute path
button.selectFileURI.browse.tooltips=Select a local file URI.
button.moveUp=上移(&U)
button.moveDown=下移(&W)
button.delete=删除(&E)

editor.title.name=名称
editor.title.originalName=原始名称
editor.title.type=类型


datatypes.dateTime=日期时间
datatypes.decimal=小数
datatypes.float=浮点
datatypes.integer=整数
datatypes.string=字符串
datatypes.date=日期
datatypes.time=时间
datatypes.boolean=布尔

tooltip.flatfilestyle=将鼠标移动到每个平面文件样式以获取更多信息。
tooltip.csv=逗号 { , } 分隔的值
tooltip.ssv=分号 { ; } 分隔的值
tooltip.psv=竖线 { | } 分隔的值
tooltip.tsv=制表符 { Tab } 分隔的值
tooltip.columnnameline=请确保选择的文件第一行\n中存在列名称。
tooltip.typeline=请确保选择的文件第二行\n中存在数据类型行。
tooltip.button.add=将左表中所有选择的列添加到平面文件数据集。
tooltip.button.up=上移选定项目。
tooltip.button.down=下移选定项目。
tooltip.button.delete=删除选定项目。
tooltip.trailNull=读取数据行时出现空后缀列。 
error.selectFolder=请选择有效文件夹。
error.selectColumns=请至少为此数据集选择一列。
error.emptyPath=请输入路径。
error.unexpectedError=发生意外错误。
error.invalidConnectionFilePath=指定的连接配置文件源路径“{0}”不存在。
error.invalidFlatFilePath=平面文件数据源路径无效。请改正连接配置文件。
error.errorExist=当前页面存在错误。一定要保存它吗？
error.noCSVFiles=文件夹“{0}”中没有指定任何文件。
error.invalidFilePath=文件路径无效。要继续吗？
error.emptyFilePath=文件路径是空的。要继续吗？
error.duplicatedNameValueOrEmpty=名称值重复或为空
error.invalidSavedColumnsInfo = 无效的保存列信息
error.invalidColumnName=无效列名称
error.invalidQureyText=无效查询文本
error.invalidURIPath=The URI entered is invalid.
error.URIConnectionFailure=Cannot open connection to the URI resource.

title.warning=警告
warning.fileExtensionInvalid=选择的文件可能不是有效的平面文件。
confirm.reselectFileFilterTitle=确认选择另一文件过滤器。
confirm.reselectFileFilterMessage=选择另一文件筛选器将不保存以上操作。\n\n您确定吗？

confirm.reselectFileNameTitle=确认选择另一个平面文件。
confirm.reselectFileNameMessage=是否要保持列选择与当前定义相同?
warning.columnNotExist=选择的列“{0}”不存在!

fileURIChanged.warning.reselectColumnsTitle=File URI in the data source is changed.
fileURIChanged.warning.reselectColumnsMessage=File URI in the data source is changed!\nPlease select columns again.
Connection.warning.untested=Connection has not been tested. Please test connection before saving this data source profile.
Connection.error.invalidPath=No valid flat file locations found.
Connection.error.invalidHomeFolder=Invalid flat file home folder <{0}>.
Connection.error.invalidFileURI=Invalid flat file URI path <{0}>.
