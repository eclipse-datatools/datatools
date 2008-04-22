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

SQLParserException.GENERAL_MESSAGE=입력 값을 구문 분석할 수 없습니다: "{0}"
SQLParseErrorInfo.NO_CORRECTION_AVAILABLE = 수정할 수 없습니다.

! "token" is a technical term, see note above
SQLParserException.LEX_ERROR_CODE 		= 예기치 못한 문자 "{0}"이(가) 무시되었습니다.
SQLParserException.ERROR_CODE 			= 토큰 "{0}"에서 구문 분석이 종료되었습니다.
SQLParserException.BEFORE_CODE			= 토큰 "{1}" 앞에 "{0}"을 삽입했습니다.
SQLParserException.INSERTION_CODE 		= 토큰 "{1}" 뒤에 "{0}"이 필요합니다.
SQLParserException.INVALID_CODE   		= 예기치 못한 입력 값이 "{0}"을(를) 취소했습니다.
SQLParserException.SUBSTITUTION_CODE  	= 입력 "{1}" 대신 "{0}"이(가) 필요합니다.
SQLParserException.SECONDARY_CODE 		= 입력 "{1}" 대신 "{0}"이(가) 필요합니다.
SQLParserException.DELETION_CODE  		= 예기치 못한 토큰 "{0}"을(를) 삭제했습니다.
SQLParserException.MERGE_CODE 			= 병합된 토큰에서 "{0}"을(를) 구성하였습니다.
SQLParserException.MISPLACED_CODE 		= 구문 "{0}"의 위치가 잘못되었습니다.
SQLParserException.SCOPE_CODE 			= 전체 범위에 "{0}"(을)를 삽입했습니다.
SQLParserException.EOF_CODE   			= 파일의 끝이 토큰 "{0}" 뒤에 연결되어 있습니다.
SQLParserException.INVALID_TOKEN_CODE 	= "{0}"이(가) 잘못되었습니다.

! Note to translater: statement is a well known term related to the SQL language
SQLParserManager.MULTIPLE_STATEMENTS  	= 다중 SQL 문은 이 컨텍스트에 허용되지 않습니다.

#
# following message keys not yet in use, for future release
#

DATATOOLS.PROJECT.DEV.PROP.PRESERVE_USER_FORMAT 	= 원본 텍스트의 형식 유지(&P)
DATATOOLS.PROJECT.DEV.PROP.ALWAYS_QUALIFY_COLUMNS   = SQL 원본 텍스트의 모호한 열 참조 한정(&Q)

! the following decribes that we try to be smart and figure out column references
! in the SQL source, even when not correctly specified, we correct the input then, e.g. SELECT tableA.col1 FROM schema1.tableA AS TA would be corrected to SELECT TA.col1 FROM schema1.tableA AS TA
DATATOOLS.PROJECT.DEV.PROP.SMART_RESOLVING  		= SQL 원본 텍스트의 열 참조 한정 자동 수정(&C)
