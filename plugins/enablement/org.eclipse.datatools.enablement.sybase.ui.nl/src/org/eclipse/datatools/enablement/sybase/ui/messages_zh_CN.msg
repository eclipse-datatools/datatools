# *******************************************************************************
# * Copyright (c) 2008 Sybase, Inc.
# * 
# * All rights reserved. This program and the accompanying materials are made
# * available under the terms of the Eclipse Public License 2.0 which
# * accompanies this distribution, and is available at
# * https://www.eclipse.org/legal/epl-2.0/
# * 
# * Contributors: Sybase - initial API and implementation
# ******************************************************************************/
# package: com.sybase.suade.servers.sample.jdbc
NewConnectionProfileWizard.ASA.title = 新建 ASA 连接配置文件
NewConnectionProfileWizard.ASIQ.title = 新建 ASIQ 连接配置文件
NewConnectionProfileWizard.error.title = 创建新的 JDBC 连接配置文件时出错
NewConnectionProfileWizard.error.msg = 创建新的 JDBC 连接配置文件时发生了异常。
NewConnectionProfileWizard.confirmation.title=确认
NewConnectionProfileWizard.confirmation.msg=数据库“{1}”的连接配置文件“{0}”已存在。\n要覆盖吗?
								
								
JDBCASAPropertyWizardPage.title = 指定驱动程序和连接详细信息
JDBCPropertyWizardPage.PingButton=
JDBCASAPropertyWizardPage.msg = 从下拉列表中选择驱动程序并提供连接的登录详细信息。
JDBCASAPropertyWizardPage.driverCombo.label = 从下拉列表中选择驱动程序:
JDBCASAPropertyWizardPage.hostName.label = 主机:
JDBCASAPropertyWizardPage.databaseName.label = 数据库名称:
JDBCASAPropertyWizardPage.portNumber.label = 端口:
JDBCASAPropertyWizardPage.userName.label = 用户名:
JDBCASAPropertyWizardPage.password.label = 密码:
JDBCASAPropertyWizardPage.filterGroup.label = 筛选器属性
JDBCASAPropertyWizardPage.catalog.label = 目录:
JDBCASAPropertyWizardPage.schema.label = 架构:
JDBCASAPropertyWizardPage.PingButton=测试连接
JDBCASAProfilePropertyPage.PingButton=测试连接
JDBCASAPropertyWizardPage.procedureName.label = 过程名称:
JDBCASAPropertyWizardPage.optionalProps.label = 连接属性
JDBCASAPropertyWizardPage.summary.driverName.label = 驱动程序名称
JDBCASAPropertyWizardPage.summary.connProps.label = 连接属性
JDBCASAPropertyWizardPage.summary.host.label = 主机
JDBCASAPropertyWizardPage.summary.databaseName.label = 数据库名称
JDBCASAPropertyWizardPage.summary.port.label = 端口
JDBCASAPropertyWizardPage.summary.userName.label = 用户名
JDBCASAPropertyWizardPage.summary.password.label = 密码
JDBCASAPropertyWizardPage.summary.driverClass.label = 驱动程序类
JDBCASAPropertyWizardPage.summary.catalog.label = 目录
JDBCASAPropertyWizardPage.summary.schema.label = 架构
JDBCASAPropertyWizardPage.summary.procedureName.label = 过程名称
JDBCASAPropertyWizardPage.summary.url.label = URL

ASAWSMFConnection.error.DriverNotExist = 驱动程序不存在。
ASAWSMFConnection.error.EmptyJarList = 驱动程序定义中的空 jar 列表。

JDBCASAProfileTabs.errors.requiredHost = 需要主机
JDBCASAProfileTabs.errors.requiredPort = 需要端口
JDBCASAProfileTabs.errors.requiredPortValidNumber = 端口号必须为有效数字
JDBCASAProfileTabs.otherPropertiesTab.commonOptions=APPLICATIONNAME=SYBASE WORKSPACE,CHARSET=UTF8,CONNECTION_FAILOVER=TRUE,SQLINITSTRING=SET QUOTED_IDENTIFIER ON,JCONNECT_VERSION=6,JCONNECT_VERSION=0

MultiASAWizardPage.title=检查多数据库 ASA 服务器（可选）
MultiASAWizardPage.description=查看 ASA 服务器是否展示了多个数据库。如果 ASA 服务器展示了多个数据库，请选择要展示为配置文件的数据库。单击“检查多个数据库”以开始。
MultiASADialog.dialog.title=创建多个 ASA 配置文件
MultiASADialog.dialog.message=查看 ASA 服务器是否展示了多个数据库。如果 ASA 服务器展示了多个数据库，请选择要展示为配置文件的数据库。单击“检查多个数据库”以开始。
MultiASAWizardPage.OnlyOneDBMessage=因为只展示了一个数据库，所以不需要执行其他操作。
MultiASADialog.dialog.message.title=检查多数据库 ASA 服务器
MultiASAWizardPage.checkButton.label=检查多个数据库
MultiASAWizardPage.Label.ChooseDbs=选择要展示为配置文件的数据库:
MultiASAWizardPage.Error.title.CannotConnect=不能连接
MultiASAWizardPage.Error.msg.CannotConnect=连接至配置文件时存在问题。请返回属性页面并检查配置文件信息。
MultiASAWizardPage.selectAllButton.label=全选
MultiASAWizardPage.deselectAllButton.label=取消全选
MultiASAWizardPage.Summary.Label=选定的数据库

ProfileASAMultiDBAction.error.failedprofileupdate = 未能更新配置文件“{0}”。
ProfileASAMultiDBAction.error.title.profilealreadyexists=配置文件已存在
ProfileASAMultiDBAction.msg.profilealreadyexists = 名为“{0}”的配置文件已存在。应替换它吗？ 


# package: com.sybase.suade.servers.sample.jdbc
NewConnectionProfileWizard.title = 新建 JDBC 连接配置文件
NewConnectionProfileWizard.error.title = 创建新的 JDBC 连接配置文件时出错
NewConnectionProfileWizard.error.msg = 创建新的 JDBC 连接配置文件时发生了异常。

JDBCPropertyWizardPage.title = 指定驱动程序和连接详细信息
JDBCPropertyWizardPage.msg = 从下拉列表中选择驱动程序并提供连接的登录详细信息。
JDBCPropertyWizardPage.driverCombo.label = 从下拉列表中选择驱动程序:
JDBCPropertyWizardPage.url.label = URL:
JDBCPropertyWizardPage.PingButton=测试连接
JDBCPropertyWizardPage.userName.label = 用户名:
JDBCPropertyWizardPage.password.label = 密码:
JDBCPropertyWizardPage.filterGroup.label = 可选筛选器属性
JDBCPropertyWizardPage.catalog.label = 目录:
JDBCPropertyWizardPage.schema.label = 架构:
JDBCProfilePropertyPage.PingButton=测试连接
JDBCPropertyWizardPage.procedureName.label = 过程名称:
JDBCPropertyWizardPage.optionalProps.label = 可选属性:
JDBCPropertyWizardPage.summary.driverName.label = 驱动程序名称
JDBCPropertyWizardPage.summary.connProps.label = 连接属性
JDBCPropertyWizardPage.summary.userName.label = 用户名
JDBCPropertyWizardPage.summary.password.label = 密码
JDBCPropertyWizardPage.summary.driverClass.label = 驱动程序类
JDBCPropertyWizardPage.summary.catalog.label = 目录
JDBCPropertyWizardPage.summary.schema.label = 架构
JDBCPropertyWizardPage.summary.procedureName.label = 过程名称
JDBCPropertyWizardPage.summary.url.label = URL

JDBCConnectionUtils.Milliseconds=\ ms
JDBCConnectionUtils.StatusTitle=JDBCConnectionUtils
JDBCConnectionUtils.TimeoutInXMilliSecondsMsg=登录超时 
JDBCConnectionUtils.AttemptToConnectMessage=正在尝试连接...
JDBCConnectionUtils.DBConnectAttemptJob=数据库连接尝试
JDBCConnectionUtils.ClassNotFoundStatus=无法加载类: 
JDBCConnectionUtils.ConnectionTimeoutMessage=连接已超时。

JDBCConnection.technologyName = JDBC
JDBCConnection.error.DriverNotExist = 驱动程序不存在。
JDBCConnection.error.EmptyJarList = 驱动程序定义中的空 jar 列表。

JDBCProfileTabs.tabs.label.connection = 连接(&C)
JDBCProfileTabs.tabs.label.filters = 筛选器(&F)
JDBCProfileTabs.tabs.label.other = 其他属性(&P)
JDBCProfileTabs.errors.requiredURL = 需要 JDBC URL
JDBCProfileTabs.errors.requiredUID = 需要用户名

# package: com.sybase.suade.servers.sample.jdbc
NewConnectionProfileWizard.title = 新建 JDBC 连接配置文件
NewConnectionProfileWizard.error.title = 创建新的 JDBC 连接配置文件时出错
NewConnectionProfileWizard.error.msg = 创建新的 JDBC 连接配置文件时发生了异常。

JDBCPropertyWizardPage.title = 指定驱动程序和连接详细信息
JDBCPropertyWizardPage.msg = 从下拉列表中选择驱动程序并提供连接的登录详细信息。
JDBCPropertyWizardPage.driverCombo.label = 从下拉列表中选择驱动程序:
JDBCPropertyWizardPage.url.label = URL:
JDBCPropertyWizardPage.PingButton=测试连接
JDBCPropertyWizardPage.userName.label = 用户名:
JDBCPropertyWizardPage.password.label = 密码:
JDBCPropertyWizardPage.filterGroup.label = 可选筛选器属性
JDBCPropertyWizardPage.catalog.label = 目录:
JDBCPropertyWizardPage.schema.label = 架构:
JDBCProfilePropertyPage.PingButton=测试连接
JDBCPropertyWizardPage.procedureName.label = 过程名称:
JDBCPropertyWizardPage.optionalProps.label = 可选属性:
JDBCPropertyWizardPage.summary.driverName.label = 驱动程序名称
JDBCPropertyWizardPage.summary.connProps.label = 连接属性
JDBCPropertyWizardPage.summary.userName.label = 用户名
JDBCPropertyWizardPage.summary.password.label = 密码
JDBCPropertyWizardPage.summary.driverClass.label = 驱动程序类
JDBCPropertyWizardPage.summary.catalog.label = 目录
JDBCPropertyWizardPage.summary.schema.label = 架构
JDBCPropertyWizardPage.summary.procedureName.label = 过程名称
JDBCPropertyWizardPage.summary.url.label = URL

JDBCConnectionUtils.Milliseconds=\ ms
JDBCConnectionUtils.StatusTitle=JDBCConnectionUtils
JDBCConnectionUtils.TimeoutInXMilliSecondsMsg=登录超时 
JDBCConnectionUtils.AttemptToConnectMessage=正在尝试连接...
JDBCConnectionUtils.DBConnectAttemptJob=数据库连接尝试
JDBCConnectionUtils.ClassNotFoundStatus=无法加载类: 
JDBCConnectionUtils.ConnectionTimeoutMessage=连接已超时。

JDBCConnection.technologyName = JDBC
JDBCConnection.error.DriverNotExist = 驱动程序不存在。
JDBCConnection.error.EmptyJarList = 驱动程序定义中的空 jar 列表。

JDBCProfileTabs.tabs.label.connection = 连接(&C)
JDBCProfileTabs.tabs.label.filters = 筛选器(&F)
JDBCProfileTabs.tabs.label.other = 其他属性(&P)
JDBCProfileTabs.errors.requiredURL = 需要 JDBC URL
JDBCProfileTabs.errors.requiredUID = 需要用户名
events.folder.name=事件
webservices.folder.name=Web 服务
datatypes.folder.name=数据类型

SybaseDatabaseProfileSettingPage.showOwner=Show database object &owner
SybaseDatabaseProfileSettingPage.showSchema=显示架构(&S)
