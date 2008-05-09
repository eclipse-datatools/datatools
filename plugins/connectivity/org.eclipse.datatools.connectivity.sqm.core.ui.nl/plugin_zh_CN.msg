###############################################################################
# Copyright (c) 2001, 2004 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
# 
# Contributors:
#     IBM Corporation - initial API and implementation
###############################################################################
pluginName=Eclipse Data Tools Platform SQM UI Plug-in
providerName=Eclipse.org

#
## Extension Point Names
#
LABEL_SERVICE_EXTENSION_POINT_NAME=labelService
ELEMENT_ID_EXTENSION_POINT_NAME=elementID
MARKER_NAVIGATION_PROVIDER_EXTENSION_POINT_NAME=markerNavigationProvider
DATA_SOURCE_EXPLORER_SORTER=sorterValidatorProvider
FILTER_INFORMATION_EXTENSION_POINT_NAME=筛选器信息

#
## Model Explorer Selection
#
datatools.core.ui.modelExplorerSelection = 模型资源浏览器选项

#
## Preferences for Label Decoration
#
datatools.core.ui.preferences.labeldecorators = 标签修饰

#
## Preferences
#
datatools.core.ui.preferences.data = SQL 开发
datatools.core.ui.preferences.output = 输出

#
## Views
#
datatools.core.ui.modelElementProperties = 数据模型元素属性
datatools.core.ui.views.interactiveSQL = 显示 SQL

#
## Model Explorer
#
datatools.core.ui.contentProviders = 物理模型内容提供程序
datatools.core.ui.parserProviders = 物理模型分析程序提供程序
datatools.core.ui.iconProviders = 物理模型图标提供程序

#
## Nature 
#
datatools.core.ui.projectNature = 数据库项目数据本质

#
## Wizards
# 
datatools.core.ui.category.project = 数据库
datatools.core.ui.category.model = 数据库模型
datatools.core.ui.project.basic = 数据设计项目
datatools.core.ui.project.description = 创建数据设计项目
datatools.core.ui.model.basic = 物理数据模型

#
## Preferences
#
datatools.core.ui.preferences.modeler = 数据
datatools.core.ui.preferences.default = 数据库缺省值

#
## Popup && Actions
#
datatools.core.ui.actions.close = 关闭
datatools.core.ui.actions.add = 添加 SQL
datatools.core.ui.actions.addMQT = MQT
datatools.core.ui.actions.addSchema = 架构
datatools.core.ui.actions.addTable = 表
datatools.core.ui.actions.addView = 视图
datatools.core.ui.actions.addColumn = 列
datatools.core.ui.actions.delete = 删除
datatools.core.ui.actions.addTrigger = 触发器
datatools.core.ui.globalActionHandlerProviders = 数据全局操作
datatools.core.ui.actions.selectSupplier = 导航至唯一约束表
datatools.core.ui.actions.fe=生成 DDL...
datatools.core.ui.actions.re=从 DDL 进行反向工程...
datatools.core.ui.actions.dataModeler = 数据建模器
datatools.core.ui.actions.addDomain = 域
datatools.core.ui.actions.addSequence = 序列
datatools.core.ui.actions.addStoredProcedure = 存储过程
datatools.core.ui.actions.addJavaStoredProcedure = Java 存储过程
datatools.core.ui.actions.generateForeignKeyIndex = 索引
datatools.core.ui.actions.generatePrimaryKeyIndex = 索引
datatools.core.ui.actions.addDatabase = 数据库
datatools.core.ui.actions.addTableIndex = 索引
datatools.core.ui.actions.addTableCheckConstraint = 检查约束
datatools.core.ui.actions.addUniqueConstraint = 唯一约束
datatools.core.ui.actions.addAlias = 别名
datatools.core.ui.actions.addDistinctUDT = 单值用户定义类型
datatools.core.ui.actions.addStructuredUDT = 结构化用户定义类型
datatools.core.ui.actions.addMQTIndex = 索引
datatools.core.ui.actions.addMQTTrigger = 触发器
#
## Editors
#
datatools.core.ui.model.editor = SQL 模型编辑器

#
## Decorators
#
datatools.core.ui.modelexplorer.modelExplorerColumnDecoration = 数据工具 - 列数据类型
datatools.core.ui.modelexplorer.modelExplorerForeignKeyDecoration = 数据工具 - 外键或主键指示器
datatools.core.ui.modelexplorer.DependencyDecoration = 数据工具 - 依赖项修饰
datatools.core.ui.modelexplorer.bookmarkDecoration  = 数据工具 - 书签指示器
datatools.core.ui.modelexplorer.filterDecoration = 数据工具 - 服务器资源管理器筛选器节点指示器

#
## Model Explorer Sorter Provider
#
datatools.core.ui.modelexplorer.sorterProviders = 模型资源浏览器排序器提供程序
datatools.core.ui.modelexplorer.menu.storageOrderSorter = 存储器顺序
datatools.core.ui.modelexplorer.menu.name = 名称
datatools.core.ui.modelexplorer.menu.type = 类型
datatools.core.ui.modelexplorer.menu.stereotype = 立体声类型
datatools.core.ui.modelexplorer.menu.visibility = 可见性

#
## Model Explorer DnD
#
datatools.core.ui.modelexplorer.dragDrop = 拖放

#
## Marker
#
datatools.core.ui.modelexplorer.marker.dangling = 悬挂引用


#
## Type for Label Service 
#
database = 数据库
schema = 架构
baseTable = 表
column = 列
domain = 域
udt = 用户定义类型
index = 索引
procedure = 存储过程
view = 视图
parameter = 参数
trigger = 触发器
sequence = 序列
source= 源
attributeDefinition = 属性定义
function = 用户定义函数
dependency = 相关性
primaryKey = 主键
foreignKey = 外键
folder = 逻辑文件夹
CheckConstraint = 检查约束
UniqueConstraint = 唯一约束
existingConnection = 连接文件夹
connectedConnection = 活动连接
disconnectedConnection = 已断开的连接
cachedConnection = 已高速缓存的连接
catalog = 目录

DEFAULT_SCHEMA_FILTER_PROPERTY_PAGE_NAME=缺省架构筛选器
DEFAULT_TABLE_FILTER_PROPERTY_PAGE_NAME=缺省表筛选器
DEFAULT_STORED_PROCEDURE_FILTER_PROPERTY_PAGE_NAME=缺省存储过程筛选器
FILTER_SETTINGS_PROPERTY_PAGE_NAME=筛选器设置
