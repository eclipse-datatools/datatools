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

SQLParserException.GENERAL_MESSAGE=Eingabe kann nicht geparst werden: "{0}".
SQLParseErrorInfo.NO_CORRECTION_AVAILABLE = Keine Korrektur verfügbar.

! "token" is a technical term, see note above
SQLParserException.LEX_ERROR_CODE 		= Unerwartetes Zeichen "{0}" wurde ignoriert.
SQLParserException.ERROR_CODE 			= Analyse brach bei diesem Token "{0}" ab.
SQLParserException.BEFORE_CODE			= "{0}" wurde vor diesem Token "{1}" eingefügt.
SQLParserException.INSERTION_CODE 		= "{0}" wurde nach diesem Token "{1}" erwartet.
SQLParserException.INVALID_CODE   		= Unerwartete Eingabe wurde verworfen "{0}".
SQLParserException.SUBSTITUTION_CODE  	= "{0}" wurde anstelle dieser Eingabe "{1}" erwartet.
SQLParserException.SECONDARY_CODE 		= "{0}" wurde anstelle dieser Eingabe "{1}" erwartet.
SQLParserException.DELETION_CODE  		= Unerwartete(s) Token(s) "{0}" wurde(n) ignoriert.
SQLParserException.MERGE_CODE 			= "{0}" wurde aus verbundenen Token gebildet.
SQLParserException.MISPLACED_CODE 		= Falsch platzierte(s) Konstrukt(e) "{0}".
SQLParserException.SCOPE_CODE 			= "{0}" wurde zur Vervollständigung des Umfangs eingefügt.
SQLParserException.EOF_CODE   			= Ende der Datei erreicht nach Token "{0}".
SQLParserException.INVALID_TOKEN_CODE 	= "{0}" ist ungültig.

! Note to translater: statement is a well known term related to the SQL language
SQLParserManager.MULTIPLE_STATEMENTS  	= Mehrere SQL-Anweisungen sind in diesem Kontext nicht zulässig.

#
# following message keys not yet in use, for future release
#

DATATOOLS.PROJECT.DEV.PROP.PRESERVE_USER_FORMAT 	= Formatierung des SQL-Quelltexts &beibehalten
DATATOOLS.PROJECT.DEV.PROP.ALWAYS_QUALIFY_COLUMNS   = Unzweideutige Spaltenreferenzen im SQL-Quelltext &qualifizieren

! the following decribes that we try to be smart and figure out column references
! in the SQL source, even when not correctly specified, we correct the input then, e.g. SELECT tableA.col1 FROM schema1.tableA AS TA would be corrected to SELECT TA.col1 FROM schema1.tableA AS TA
DATATOOLS.PROJECT.DEV.PROP.SMART_RESOLVING  		= Die Qualifizierung von Spaltenreferenzen im SQL-Quelltext automatisch &korrigieren

