# NLS_MESSAGEFORMAT_VAR          needed to handle quotes correctly
#
# File: sqlparsermessages.properties
# Description: Properties file for classes in the SQLParser Plugin

# =============================================================================
# Copyright (c) 2000, 2005 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials 
# are made available under the terms of the Eclipse Public License v1.0
# which is available at
# http://www.eclipse.org/legal/epl-v10.html
# 
# Contributors:
#     IBM Corporation - initial API and implementation
# =============================================================================

# "token" is a technical term used in the context of parsers, might not be translated
# "terminal symbol" might be used instead or used as source for tranlation, it is the
# smallest unit in a parser input, like a keyword, identifier or a special single character
!
# Strings for SQL parser error messages

# Attention translators: mnemonics (short keys, marked with "&") can vary in translations
# message values should not use the mnemonics "reserved" for standard buttons like "Back", "Next", "Finish"

SQLParserException.GENERAL_MESSAGE=入力 "{0}" を解析できません。
SQLParseErrorInfo.NO_CORRECTION_AVAILABLE = 修正できません。

! "token" is a technical term, see note above
SQLParserException.LEX_ERROR_CODE 		= 不要な文字 "{0}" は無視されます。
SQLParserException.ERROR_CODE 			= 解析がトークン "{0}" で中止されました。
SQLParserException.BEFORE_CODE			= トークン "{1}" の前に "{0}" が挿入されました。
SQLParserException.INSERTION_CODE 		= トークン "{1}" の後には "{0}" が必要です。
SQLParserException.INVALID_CODE   		= 不要な入力で "{0}" が破棄されました。
SQLParserException.SUBSTITUTION_CODE  	= 入力には "{1}" ではなく、"{1}" が必要です。
SQLParserException.SECONDARY_CODE 		= 入力には "{1}" ではなく、"{1}" が必要です。
SQLParserException.DELETION_CODE  		= 不要なトークン "{0}" は無視されます。
SQLParserException.MERGE_CODE 			= "{0}" は結合トークンから形成されました。
SQLParserException.MISPLACED_CODE 		= コンストラクト "{0}" の配置が間違っています。
SQLParserException.SCOPE_CODE 			= スコープを終了する "{0}" が挿入されました。
SQLParserException.EOF_CODE   			= トークン "{0}" の後でファイルの終わりになりました。
SQLParserException.INVALID_TOKEN_CODE 	= "{0}" は無効です。

! Note to translater: statement is a well known term related to the SQL language
SQLParserManager.MULTIPLE_STATEMENTS  	= このコンテキストでは複数の SQL 文を使用できません。

#
# following message keys not yet in use, for future release
#

DATATOOLS.PROJECT.DEV.PROP.PRESERVE_USER_FORMAT 	= SQL ソーステキストの書式設定を維持する(&P)
DATATOOLS.PROJECT.DEV.PROP.ALWAYS_QUALIFY_COLUMNS   = SQL ソーステキストの明確な列参照を修飾する(&Q)

! the following decribes that we try to be smart and figure out column references
! in the SQL source, even when not correctly specified, we correct the input then, e.g. SELECT tableA.col1 FROM schema1.tableA AS TA would be corrected to SELECT TA.col1 FROM schema1.tableA AS TA
DATATOOLS.PROJECT.DEV.PROP.SMART_RESOLVING  		= SQL ソーステキストの列参照の修飾を自動修正する(&C)
