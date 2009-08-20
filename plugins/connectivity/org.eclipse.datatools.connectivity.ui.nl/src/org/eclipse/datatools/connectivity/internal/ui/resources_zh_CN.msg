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
plugin.internal_error=内部错误 － org.eclipse.datatools.connectivity
ImportProfilesDialog.btnOverwrite.text=使用相同的名称覆盖现有的连接概要文件(&O)
ExportProfilesDialog.button.text2=浏览(&B)...
ExportProfilesDialog.group.text=选择要导出的连接概要文件：
ExportProfilesDialog.button.text1=全部不选(&D)
ExportProfilesDialog.button.text=全部选中(&S)
ImportProfilesDialog.button.text=浏览(&B)...
ExportProfilesDialog.label.text=指定文件名(&F)：
ImportProfilesDialog.label.text=指定文件名(&F)：
ImportProfilesDialog.text.text=注意：要导入的连接概要文件应该是可以使用\n  “新建连接概要文件”向导创建的连接概要文件类型，\n  类型不受支持的连接概要文件将被废弃。
ExportProfilesDialog.null.title=导出连接概要文件
ImportProfilesDialog.null.title=导入连接概要文件
ExportProfilesDialog.btnEncryption.text=加密文件内容(&E)
actions.export.noselection=请至少选择一个连接概要文件！
actions.export.nofile=请指定文件名！
actions.export.norepository = 请指定一个目标存储库!
actions.export.notvalidfile = 请指定有效的文件名和路径!
dialog.exception.title=异常
dialog.exception.message=捕获异常：{0}
NewCPWizard.title=新建连接概要文件
IntroductionWizardPage.title=简介页
IntroductionWizardPage.intro=本向导指导您完成创建连接概要文件时需要执行的步骤。
CPWizardSelectionPage.title = 连接配置文件
CPWizardSelectionPage.desc = 选择连接配置文件类型。
CPWizardSelectionPage.group = 连接配置文件类型(&C):
CPWizardSelectionPage.defaultName =新建 {0}
CPWizardSelectionPage.defaultNameExtended =新建 {0}({1})
actions.connect.job=连接服务器作业
actions.connect.uijob=正在刷新视图
actions.ping.job=Ping 服务器作业
actions.ping.uijob=显示对话框作业
actions.connect.title=正在连接至服务器...
actions.ping.title=正在 ping 服务器...
actions.ping.success=Ping 成功！
actions.ping.failure=Ping 失败！
actions.delete.title=确认删除
actions.delete.confirm=确实要删除连接概要文件“{0}”吗？
properties.dialog=属性对话框
properties.category.general=常规
properties.connectionprofile.name=概要文件名
properties.connectionprofile.desc=描述
properties.connectionprofile.autoconnect=自动连接
properties.category.name=类别名
properties.category.id=类别标识
ServersView.action.showproperties=属性(&P)
ServersView.action.refresh=刷新(&R)
ServersView.action.newCP=新建(&N)...
ServersView.action.exportCPs=导出(&E)...
ServersView.action.importCPs=导入(&I)...
NewConnectionProfileWizard.title=新建连接概要文件
NewConnectionProfileWizard.create.failure=创建连接概要文件失败
NewConnectionProfileWizardPage.Status.DuplicateName=连接概要文件名已存在！
NewConnectionProfileWizardPage.Status.NoName=请指定连接概要文件名！
NewConnectionProfileWizardPage.Status.NoRepository = 请指定一个存储库。
NewConnectionProfileWizardPage.title = 创建连接配置文件
NewConnectionProfileWizardPage.desc = 输入配置文件信息。
NewConnectionProfileWizardPage.cp_name = 名称(&M):
NewConnectionProfileWizardPage.cp_desc = 描述(可选)(&D):
NewConnectionProfileWizardPage.choices=请选择：
NewConnectionProfileWizardPage.summary_cp_name=名称
NewConnectionProfileWizardPage.summary_cp_desc=描述
NewConnectionProfileWizardPage.Button.AutoConnect = 启动时自动连接(&A)
NewConnectionProfileWizardPage.Button.AutoConnect1 = 启动时自动连接。
NewConnectionProfileWizardPage.summary_autoconnect = 启动时自动连接
NewConnectionProfileWizardPage.summary_autoconnect_on_finish = 完成时自动连接
NewConnectionProfileWizardPage.localRepository = 在工作区存储库中创建(本地)
NewConnectionProfileWizardPage.chooseRepository = 选择存储库:
ConnectionProfileDetailsPage.Button.TestConnection=测试连接(&T)
ConnectionProfileDetailsPage.AskConfirmation=确认对话框
ConnectionProfileDetailsPage.AskReconnect=此连接概要文件的连接信息已更改，要重新连接服务器吗？
SummaryWizardPage.title=总结
SummaryWizardPage.description=从先前各页收集的信息。
SummaryWizardPage.property=属性
SummaryWizardPage.value=值
dialog.title.error=错误
dialog.title.success=成功
rename.dialog.title=重命名对话框
rename.dialog.message=请输入新的连接概要文件名：
rename.dialog.errmsg.existing=名称已存在，请输入另一名称！
rename.dialog.errmsg.invalid=名称无效，请输入有效名称！
error.connect={1}。请参阅日志文件以了解更多详细信息。
error.contentExtension=连接到概要文件“{1}”上的“{0}”扩展时出错（错误：{2}）
CPVersionPropertyPage.desc={0} 的版本信息。\n此信息是进行上次连接尝试时获取的。如果上次连接尝试未成功，则将没有版本信息。
CPVersionPropertyPage.button.updateVersion=连接和更新版本信息
CPVersionPropertyPage.label.versionUnavailable=没有此概要文件的版本信息。
CPVersionPropertyPage.label.techVersions=在此服务器上提供了下列技术：
ConnectionProfileSummaryPage.button.testConnection=测试连接(&T)
CommonLabelProviderBase.label.version={0} ({1} v. {2}) 
CommonLabelProviderBase.label.version.versionless={0} ({1}) 
CommonLabelProviderBase.label.localRepository=工作区存储库(本地)

properties.connectionprofile.type = 配置文件类型
DSE.Refresh.label = 刷新(&R)
DSE.View.Tooltip = 数据源资源管理器

CPDeleteChange.name = 删除 {0} 连接配置文件
CPDeleteChange.error.ProfileDoesNotExist = 您尝试删除的连接配置文件不存在。
CPDeleteChange.delete.failure = 未能删除连接配置文件。
CPDeleteProcessor.name = DTP 连接删除处理程序
CPDuplicateProcessor.name = DTP 连接配置文件复制处理程序
CPCopyChange.name = 复制 {0} 连接配置文件
CPCopyChange.error.ProfileDoesNotExist = 您尝试复制的连接配置文件不存在。
CPCopyChange.title.error = 未能复制连接配置文件。
CPCopyChange.error.InvalidTarget = 指定的目标不是有效的连接配置文件存储库。
CPCopyChange.error.RepositoryNotConnected = 未连接至目标连接配置文件存储库 {0}。
CPMoveProcessor.name = DTP 连接配置文件移动处理程序
CPMoveChange.name = 移动 {0} 连接配置文件
CPMoveChange.error.ProfileDoesNotExist = 您尝试移动的连接配置文件不存在。
CPMoveChange.title.error = 未能移动连接配置文件。
CPCreateChange.name = 创建 {0} 连接配置文件
CPCreateChange.error.NewProfileDoesNotHaveName = 创建新连接配置文件需要名称。
CPCreateChange.error.NewProfileAlreadyExists = 具有新名称的连接配置文件已存在。
CPCreateChange.error.NewProfileDoesNotHaveProviderID = 创建新连接配置文件需要连接配置文件提供者 ID。
CPCreateChange.error.NewProfileDoesNotHaveNonNullPropertiesObject = 创建新连接配置文件需要非空属性对象。
CPCreateChange.create.failure = 未能创建连接配置文件。
CPCreateProcessor.name = DTP 连接创建处理程序
CPRenameChange.name = 重命名 {0} 连接配置文件
CPRenameProcessor.name = DTP 连接重命名处理程序
CPRenameChange.title.error = 未能重命名连接配置文件。

PropertyDescriptor.error.title = 处理驱动程序定义的属性描述符时出错
PropertyDescriptor.error.msg = 处理驱动程序定义的属性时出错。
PasswordTextPropertyDescriptor.property.label = 密码
DriverClassBrowsePropertyDescriptor.property.label = 驱动程序类
DriverClassBrowsePropertyDescriptor.jardialog.title = Jar 列表中的可用类
DriverClassBrowsePropertyDescriptor.jardialog.msg = 提供驱动程序类的名称，或从可用的 Jar 中选择类。
DriverClassBrowsePropertyDescriptor.noclasses.title = 没有可用的驱动程序类
DriverClassBrowsePropertyDescriptor.noclasses.msg = 没有可供当前 jar 列表使用的驱动程序类。

StatusBar.Connected = 已连接
StatusBar.Disconnected = 已断开连接
StatusBar.ConnectedMessage = {0} ({1})

StatusBar.WorkingOffline = 脱机工作

DATATOOLS.SERVER.UI.EXPLORER.WORKOFFLINE = 脱机工作(&W)
DATATOOLS.SERVER.UI.EXPLORER.UPDATE_OFFLINE = 脱机保存(&S)

DriverPropertyEditorDescriptor.InvalidID = 驱动程序属性编辑器描述符缺少有效的 ID
DriverPropertyEditorDescriptor.InvalidTargetTemplateID = 驱动程序属性编辑器描述符缺少有效的目标模板 ID
DriverPropertyEditorDescriptor.InvalidTargetPropertyId = 驱动程序属性编辑器描述符缺少有效的目标属性 ID
DriverPropertyEditorDescriptor.InvalidCustomPropertyEditorClass = 驱动程序属性编辑器描述符缺少有效的自定义属性编辑器

ExtensibleProfileDetailsWizardPage.title = 指定驱动程序和连接详细信息
ExtensibleProfileDetailsWizardPage.description = 从下拉列表中选择驱动程序并提供连接的登录详细信息。
ExtensibleProfileDetailsWizardPage.driverCombo.label = 驱动程序(&D):
ExtensibleNewConnectionProfileWizard.title = 新连接配置文件

DriverUIContributorComposite.group = 属性(&P)

CommonDriverUIContributor.optionaltab = 可选(&O)
CommonDriverUIContributor.optionalProps.label = 其它属性:
CommonDriverUIContributor.generaltab = 常规(&E)

OtherDriverUIContributor.databaseName.label = 数据库(&A):
OtherDriverUIContributor.url.label = URL(&U):
OtherDriverUIContributor.userName.label = 用户名(&S):
OtherDriverUIContributor.password.label = 密码(&W):
OtherDriverUIContributor.savePassword.label = 保存密码(&V)
OtherDriverUIContributor.summaryData.database = 数据库
OtherDriverUIContributor.summaryData.username = 用户名
OtherDriverUIContributor.summaryData.savePassword = 保存密码
OtherDriverUIContributor.summaryData.true = true
OtherDriverUIContributor.summaryData.false = false
OtherDriverUIContributor.summaryData.url = URL

OtherDriverUIContributor.errormessage.requiresdatabase = 输入显示名称。
OtherDriverUIContributor.errormessage.requiresurl = 输入 URL。
OtherDriverUIContributor.errormessage.requiresusername = 输入用户名。

ConnectAction.title = {0} 的属性

Boolean.true = 真
Boolean.false = 假

NewRepositoryWizard.title = 新建存储库向导窗口

NewConnectionProfileWizardPage.Status.ContainsColon= 连接配置文件名称不能包含冒号(:)!

