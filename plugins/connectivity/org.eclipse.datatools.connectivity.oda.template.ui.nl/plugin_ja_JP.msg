###############################################################################
# Copyright (c) 2006 Actuate Corporation.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#  Actuate Corporation - initial API and implementation
#
###############################################################################
# NLS String
###############################################################################
#
plugin.name=DTP ODA プラグインテンプレートの新規作成ウィザード
template.runtimeDriver.name=ODA データソースの実行時ドライバ
template.runtimeDriver.desc=\
このウィザードでは、オープンデータアクセス (ODA) のデータソース実行時ドライバのプラグインを作成します。\
<p>ODA 実行時ドライバのプラグインは、UI には<b>反映されません</b>。\
前ページのプラグインオプションで、[UI に反映させる] チェックボックスを選択していないことを確認してください。</p>\
<p><b>使用する拡張ポイント</b></p>\
<li>org.eclipse.datatools.connectivity.oda.dataSource</li>
template.designer.name=ODA データソースデザイナ
template.designer.desc=\
このウィザードでは、オープンデータアクセス (ODA) のデータソースデザイナを作成します。\
<p>このウィザードを使って ODA デザイナのプラグインを作成するには、\
<b>ODA データソース実行時ドライバ</b>のテンプレートで、実行時プラグインを\
作成しておく必要があります。</p>\
<p><b>使用する拡張ポイント</b></p>\
<li>org.eclipse.datatools.connectivity.oda.design.ui.dataSource</li>\
<li>org.eclipse.datatools.connectivity.connectionProfile</li>\
<li>org.eclipse.ui.propertyPages</li>

