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

SQLParserException.GENERAL_MESSAGE=Impossible d'analyser l'entrée : "{0}".
SQLParseErrorInfo.NO_CORRECTION_AVAILABLE = Aucune correction disponible.

! "token" is a technical term, see note above
SQLParserException.LEX_ERROR_CODE 		= Caractère "{0}" inattendu ignoré.
SQLParserException.ERROR_CODE 			= Analyse terminée au niveau de ce jeton "{0}".
SQLParserException.BEFORE_CODE			= "{0}" inséré avant ce jeton "{1}".
SQLParserException.INSERTION_CODE 		= "{0}" attendu après ce jeton "{1}".
SQLParserException.INVALID_CODE   		= Entrée inattendue refusée "{0}".
SQLParserException.SUBSTITUTION_CODE  	= "{0}" attendu à la place de cette entrée "{1}".
SQLParserException.SECONDARY_CODE 		= "{0}" attendu à la place de cette entrée "{1}".
SQLParserException.DELETION_CODE  		= Jeton(s) "{0}" inattendu(s) ignoré(s).
SQLParserException.MERGE_CODE 			= "{0}" formé à partir de jetons fusionnés.
SQLParserException.MISPLACED_CODE 		= Construction(s) mal placée(s) "{0}".
SQLParserException.SCOPE_CODE 			= "{0}" inséré pour achever l'étendue.
SQLParserException.EOF_CODE   			= Fin du fichier atteinte après le jeton "{0}".
SQLParserException.INVALID_TOKEN_CODE 	= "{0}" est invalide.

! Note to translater: statement is a well known term related to the SQL language
SQLParserManager.MULTIPLE_STATEMENTS  	= Les instructions SQL multiples ne sont pas autorisées dans ce contexte.

#
# following message keys not yet in use, for future release
#

DATATOOLS.PROJECT.DEV.PROP.PRESERVE_USER_FORMAT 	= &Conserver le formatage du texte source SQL
DATATOOLS.PROJECT.DEV.PROP.ALWAYS_QUALIFY_COLUMNS   = &Qualifier les références de colonnes non ambiguës dans le texte source SQL

! the following decribes that we try to be smart and figure out column references
! in the SQL source, even when not correctly specified, we correct the input then, e.g. SELECT tableA.col1 FROM schema1.tableA AS TA would be corrected to SELECT TA.col1 FROM schema1.tableA AS TA
DATATOOLS.PROJECT.DEV.PROP.SMART_RESOLVING  		= &Corriger automatiquement la qualification des références des colonnes dans le texte source SQL
