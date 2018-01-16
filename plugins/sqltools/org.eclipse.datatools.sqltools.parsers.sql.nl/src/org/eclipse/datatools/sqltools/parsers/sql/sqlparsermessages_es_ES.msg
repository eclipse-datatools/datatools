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

SQLParserException.GENERAL_MESSAGE=No es posible analizar la entrada: "{0}".
SQLParseErrorInfo.NO_CORRECTION_AVAILABLE = No hay ninguna corrección disponible.

! "token" is a technical term, see note above
SQLParserException.LEX_ERROR_CODE 		= Carácter inesperado "{0}" omitido.
SQLParserException.ERROR_CODE 			= El análisis ha terminado en este token "{0}".
SQLParserException.BEFORE_CODE			= "{0}" insertado antes de este token "{1}".
SQLParserException.INSERTION_CODE 		= Se espera "{0}" después de este token "{1}".
SQLParserException.INVALID_CODE   		= Entrada inesperada desechada "{0}".
SQLParserException.SUBSTITUTION_CODE  	= Se espera "{0}" en lugar de esta entrada "{1}".
SQLParserException.SECONDARY_CODE 		= Se espera "{0}" en lugar de esta entrada "{1}".
SQLParserException.DELETION_CODE  		= Se han omitido los tokens inesperados "{0}".
SQLParserException.MERGE_CODE 			= "{0}" formados a partir de tokens fusionados.
SQLParserException.MISPLACED_CODE 		= Creaciones mal ubicadas "{0}".
SQLParserException.SCOPE_CODE 			= "{0}" insertado para completar el alcance.
SQLParserException.EOF_CODE   			= Fin de archivo alcanzado después del token "{0}".
SQLParserException.INVALID_TOKEN_CODE 	= "{0}" no es válida.

! Note to translater: statement is a well known term related to the SQL language
SQLParserManager.MULTIPLE_STATEMENTS  	= No se permiten varias sentencias de SQL en este contexto.

#
# following message keys not yet in use, for future release
#

DATATOOLS.PROJECT.DEV.PROP.PRESERVE_USER_FORMAT 	= &Conservar formato de texto original de SQL
DATATOOLS.PROJECT.DEV.PROP.ALWAYS_QUALIFY_COLUMNS   = &Cualificar referencias de columnas no ambiguas en texto original de SQL

! the following decribes that we try to be smart and figure out column references
! in the SQL source, even when not correctly specified, we correct the input then, e.g. SELECT tableA.col1 FROM schema1.tableA AS TA would be corrected to SELECT TA.col1 FROM schema1.tableA AS TA
DATATOOLS.PROJECT.DEV.PROP.SMART_RESOLVING  		= C&orregir automáticamente la cualificación de referencias de columna en el texto original en SQL
