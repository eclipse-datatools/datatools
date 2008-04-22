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

SQLParserException.GENERAL_MESSAGE=无法分析输入:“{0}”。
SQLParseErrorInfo.NO_CORRECTION_AVAILABLE = 没有可用的相关项。

! "token" is a technical term, see note above
SQLParserException.LEX_ERROR_CODE 		= 意外字符“{0}”被忽略。
SQLParserException.ERROR_CODE 			= 分析在标记“{0}”处终止。
SQLParserException.BEFORE_CODE			= 在标记“{1}”之前插入了“{0}”。
SQLParserException.INSERTION_CODE 		= 标记“{1}”之后应该为“{0}”。
SQLParserException.INVALID_CODE   		= 意外地输入了已废弃的“{0}”。
SQLParserException.SUBSTITUTION_CODE  	= 应该输入“{0}”而不是“{1}”。
SQLParserException.SECONDARY_CODE 		= 应该输入“{0}”而不是“{1}”。
SQLParserException.DELETION_CODE  		= 意外标记“{0}”被忽略。
SQLParserException.MERGE_CODE 			= 合并标记形成了“{0}”。
SQLParserException.MISPLACED_CODE 		= 构造“{0}”放错了位置。
SQLParserException.SCOPE_CODE 			= 插入了“{0}”以补充作用域。
SQLParserException.EOF_CODE   			= 在标记“{0}”之后到达文件结尾。
SQLParserException.INVALID_TOKEN_CODE 	= “{0}”无效。

! Note to translater: statement is a well known term related to the SQL language
SQLParserManager.MULTIPLE_STATEMENTS  	= 此上下文中不允许出现多个 SQL 语句。

#
# following message keys not yet in use, for future release
#

DATATOOLS.PROJECT.DEV.PROP.PRESERVE_USER_FORMAT 	= 保留 SQL 源文本的格式(&P)
DATATOOLS.PROJECT.DEV.PROP.ALWAYS_QUALIFY_COLUMNS   = 限定 SQL 源文本中的无歧义列引用(&Q)

! the following decribes that we try to be smart and figure out column references
! in the SQL source, even when not correctly specified, we correct the input then, e.g. SELECT tableA.col1 FROM schema1.tableA AS TA would be corrected to SELECT TA.col1 FROM schema1.tableA AS TA
DATATOOLS.PROJECT.DEV.PROP.SMART_RESOLVING  		= 自动更正 SQL 源文本中的列引用的限定
