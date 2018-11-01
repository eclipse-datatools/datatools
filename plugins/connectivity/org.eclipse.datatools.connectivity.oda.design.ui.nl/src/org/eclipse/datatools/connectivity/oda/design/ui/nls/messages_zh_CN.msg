#
#************************************************************************
# Copyright (c) 2006, 2013 Actuate Corporation.
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
adaptableDataSourceProfile_unableAccessProfile=无法访问({0})中的链接配置文件；使用数据源设计中的本地属性。\n 由异常引起: {1}
common_notInDesignSession=当前不在设计会话中。
common_nullArgument=参数不能为空。
common_missingDataSourceDesign=在 OdaDesignSession 参数中缺少数据源设计。
common_createClassFailed=找不到或者无法创建类（{0}）。
extension_mustInheritFromODAPage=向导页（{0}）实现无效。ODA 定制向导页必须扩展自 {1}。
extension_mustInheritFromODAWizard=向导（{0}）实现无效。ODA 定制向导必须使用或扩展自 {1}。
extension_missingManifestElement=缺少 ODA 设计 UI 扩展点（{0}）的实现
extension_missingPropertyPage=在 ODA 设计 UI 扩展中缺少属性页实现。
wizard_dataSource_defaultTitle=新建数据源
wizard_missingDataSourceId=在“连接概要文件”属性中缺少 ODA 数据源标识。
wizard_invalidManifest=oda.design.ui.dataSource 扩展包含无效的插件清单内容。
page_defaultDataSourceTitle=定制数据源属性
designSession_alreadyHasLinkedProfile=数据源({0})已在连接配置文件对其属性进行外部化。  不能重新导出。
designSession_flushFailed=无法刷新当前数据集设计会话。
designSession_invalidArgument=在设计会话方法中指定了无效的参数。
designSession_invalidEditApiCall=用于编辑设计的设计会话 API 调用无效。
designSession_invalidNewDesignApiCall=用于创建新设计的设计会话 API 调用无效。
designSession_invalidProfileName=指定的连接配置文件存储中无法找到链接配置文件({0})。
designSession_invalidProfileNameOrFilePath=无法找到数据源设计中指定的链接配置文件({0})或连接配置文件存储({1})。
designSession_missingDataSetUIElement=没有为 OdaDataSourceId（{0}）和 OdaDataSetId（{1}）定义 dataSetUI 元素。
manifest_missingAttributeValue=在必需属性（{0}）中缺少值。
manifest_missingDataSetElementId=dataSetElementID 参数必需有值。
manifest_invalidDataSetElementId=数据集元素标识（{0}）无效。
manifest_dataSetUi_missingPageId=页标识值是必需的。
manifest_dataSetUi_invalidPageId=页标识值（{0}）无效。
manifest_dataSetUi_missingElement=在扩展中，{0} 元素是必需的。
ui_defaultDataSourceTitle=数据源连接属性
ui_replaceFilePrompt={0} 已存在。 要替换它吗？
ui_requiredFieldsMissingValue=一个或多个必需字段（用“*”标出）缺少值。
ui_errorLabel=错误
ui_errorCreatingCustomCtrls=创建并初始化定制控件时出错。
ui_saveAsTitle=另存为
profilePage_pageTitle=选择连接配置文件
profilePage_pageLabel=连接配置文件
profilePage_selectProfileDefaultMessage=选择用于创建数据源的连接配置文件
profilePage_label_profileStore=连接配置文件存储(&S):
profilePage_button_browse=浏览(&R)...
profilePage_button_relativepath=相对路径(&R)
profilePage_button_absolutepath=绝对路径(&A)
profilePage_button_browse_filter=要显示的文件扩展名(&E):
profilePage_button_browse_filter_tooltiptext=指定用于筛选下面显示的文件的一个或多个以逗号分隔的文件扩展名。\n例如: .txt、.profiles
profilePage_button_new=新建(&W)...
profilePage_button_browse_tooltip=浏览连接配置文件存储文件。\n相对路径: 基于主机资源的相对路径。\n绝对路径: 文件系统的绝对路径。
profilePage_resourcebaseuri_failure=无法获得应用程序资源基本 URI！
profilePage_browse_noselection_error=请指定连接配置文件存储文件名 
profilePage_odaTreeName=ODA 数据源
profilePage_checkboxLabel_useDefaultName=使用默认数据源名称(&U)
profilePage_label_dataSourceName=数据源名称(&D):
profilePage_error_emptyName=数据源名称不能为空。
profilePage_checkboxTooltip_maintainLink=始终使用数据源中连接配置文件存储的外部化属性值。
profilePage_checkboxLabel_maintainLink=使用连接配置文件存储中的外部化属性(&E)
profilePage_error_invalidName=数据源名称无效。
profilePage_error_invalidProfileStorePath=连接配置文件存储的无效路径
profilePage_deprecatedProfileDisplayName={0} (替换为 {1})
profileStoreCreationDialog_button_new=新建(&W)...
profileStoreCreationDialog_title=创建连接配置文件存储
profileStoreCreationDialog_grouptext=选择要包括在存储中的连接配置文件(&P):
profileStoreCreationDialog_newbutton_tooltiptext=创建新的连接配置文件
profileStoreCreationDialog_fileext_error=连接配置文件存储文件必须具有文件扩展名。\n按“确定”将默认文件扩展名 ".{0}" 添加到文件名。
dbProfilePage_defaultPageMessage=验证 JDBC 驱动程序和连接详细信息
dbProfilePage_editPageMessage=编辑 JDBC 驱动程序和连接详细信息
dbProfilePage_invalidDataSource=无效数据源。  更新连接配置文件引用，或者创建新的数据源。
dbProfilePage_notInDesignSession=无法在 ODA 设计会话外部打开属性页。
dbProfilePage_noCustomPage=无法找到并打开数据库驱动程序特定属性页。
preferencesPage_openDataAccessDescription=使用此节点下面的页面为各个开放数据访问数据源\n设置首选项。
