/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.swt.widgets.Combo;



public class FunctionNamesAndSignatures
{
  public static String db2All = Messages._UI_FCN_ALL;
  public static String db2Column = Messages._UI_FCN_COLUMN;
  public static String db2SpecialRegisters = Messages._UI_FCN_SPECIAL_REG;
  public static String db2Scalar = Messages._UI_FCN_SCALAR;
  public static String db2Conversion = Messages._UI_FCN_CONVERSION;
  public static String db2DateAndTime = Messages._UI_FCN_DATE_TIME;
  public static String db2Db2 = Messages._UI_FCN_DB2;
  public static String db2Logical = Messages._UI_FCN_LOGICAL;
  public static String db2Math = Messages._UI_FCN_MATH;
  public static String db2Summary = Messages._UI_FCN_SUMMARY;
  public static String db2Text = Messages._UI_FCN_TEXT;
  public static String db2TextExtender = Messages._UI_FCN_TEXT_EXTENDERS;
  public static String db2AIVExtenders = Messages._UI_FCN_AIV_EXTENDERS;
  public static String db2NotSupported = Messages._UI_FCN_NOT_SUPPORTED;
  public static String db2UDF = Messages._UI_FCN_UDF;
  
  private static final String allFunctions[] =
  {
    "ABS", "ABSVAL",
    "ACOS",
    "ASCII",
    "ASIN",
    "ATAN",
    "ATAN2",
    "AVG",
    "BIGINT",
    "BLOB",
    "CEILING", "CEIL",
    "CHAR",
    "CHR",
    "CLOB",
    "COALESCE",
    "CONCAT",
    "CORRELATION", "CORR",
    "COS",
    "COT",
    "COUNT",
    "COUNT_BIG",
    "COVARIANCE", "COVAR",
    "CURRENT DATE",
    "CURRENT DEFAULT TRANSFORM GROUP",
    "CURRENT DEGREE",
    "CURRENT EXPLAIN MODE",
    "CURRENT EXPLAIN SNAPSHOT",
    "CURRENT NODE",
    "CURRENT PATH",
    "CURRENT QUERY OPTIMIZATION",
    "CURRENT REFRESH AGE",
    "CURRENT SCHEMA",
    "CURRENT SERVER",
    "CURRENT TIME",
    "CURRENT TIMESTAMP",
    "CURRENT TIMEZONE",
    "DATE",
    "DAY",
    "DAYNAME",
    "DAYOFWEEK",
    "DAYOFWEEK_ISO",
    "DAYOFYEAR",
    "DAYS",
    "DB2TX.CCSID",
    "DB2TX.CONTAINS",
    "DB2TX.FILE",
    "DB2TX.FORMAT",
    "DB2TX.HANDLE",
    "DB2TX.HANDLE_LIST",
    "DB2TX.INIT_TEXT_HANDLE",
    "DB2TX.LANGUAGE",
    "DB2TX.NO_OF_DOCUMENTS",
    "DB2TX.NO_OF_MATCHES",
    "DB2TX.RANK",
    "DB2TX.REFINE",
    "DB2TX.SEARCH_RESULT",
    "DBCLOB",
    "DECIMAL", "DEC",
    "DEGREES",
    "DEREF",
    "DIFFERENCE",
    "DIGITS",
    "DLCOMMENT",
    "DLLINKTYPE",
    "DLURLCOMPLETE",
    "DLURLPATH",
    "DLURLPATHONLY",
    "DLURLSCHEME",
    "DLURLSERVER",
    "DLVALUE",
    "DOUBLE", "DOUBLE_PRECISION",
    "EVENT_MON_STATE",
    "EXP",
    "FLOAT",
    "FLOOR",
    "GENERATE_UNIQUE",
    "GRAPHIC",
    "GROUPING",
    "HEX",
    "HOUR",
    "IDENTITY_VAL_LOCAL",
    "INSERT",
    "INTEGER", "INT",
    "JULIAN_DAY",
    "LCASE", "LOWER",
    "LEFT",
    "LENGTH",
    "LN",
    "LOCATE",
    "LOG",
    "LOG10",
    "LONG_VARCHAR",
    "LONG_VARGRAPHIC",
    "LTRIM",
    "MAX",
    "MIN",
    "MICROSECOND",
    "MIDNIGHT_SECONDS",
    "MINUTE",
    "MMDBSYS.ALIGNVALUE",
    "MMDBSYS.ASPECTRATIO",
    "MMDBSYS.BITSPERSAMPLE",
    "MMDBSYS.BYTESPERSEC",
    "MMDBSYS.COMMENT",
    "MMDBSYS.COMPRESSTYPE",
    "MMDBSYS.CONTENT",
    "MMDBSYS.DB2AUDIO",
    "MMDBSYS.DB2IMAGE",
    "MMDBSYS.DB2VIDEO",
    "MMDBSYS.DURATION",
    "MMDBSYS.FILENAME",
    "MMDBSYS.FINDINSTRUMENT",
    "MMDBSYS.FINDTRACKNAME",
    "MMDBSYS.FORMAT",
    "MMDBSYS.FRAMERATE",
    "MMDBSYS.GETINSTRUMENTS",
    "MMDBSYS.GETTRACKNAMES",
    "MMDBSYS.HEIGHT",
    "MMDBSYS.IMPORTER",
    "MMDBSYS.IMPORTTIME",
    "MMDBSYS.MAXBYTESPERSEC",
    "MMDBSYS.NUMAUDIOTRACKS",
    "MMDBSYS.NUMCHANNELS",
    "MMDBSYS.NUMCOLORS",
    "MMDBSYS.NUMFRAMES",
    "MMDBSYS.NUMVIDEOTRACKS",
    "MMDBSYS.QBSCOREFROMNAME",
    "MMDBSYS.QBSCOREFROMSTR",
    "MMDBSYS.QBSCORETBFROMNAME",
    "MMDBSYS.QBSCORETBFROMSTR",
    "MMDBSYS.REPLACE",
    "MMDBSYS.SAMPLINGRATE",
    "MMDBSYS.SIZE",
    "MMDBSYS.THUMBNAIL",
    "MMDBSYS.TICKSPERQNOTE",
    "MMDBSYS.TICKSPERSEC",
    "MMDBSYS.UPDATER",
    "MMDBSYS.UPDATETIME",
    "MMDBSYS.WIDTH",
    "MOD",
    "MONTH",
    "MONTHNAME",
    "NODENUMBER",
    "NULLIF",
    "PARTITION",
    "POSSTR",
    "POWER",
    "QUARTER",
    "RADIANS",
    "RAISE_ERROR",
    "RAND",
    "REAL",
    "REGR_AVGX",
    "REGR_AVGY",
    "REGR_COUNT",
    "REGR_INTERCEPT", "REGR_ICPT",
    "REGR_R2",
    "REGR_SLOPE",
    "REGR_SXX",
    "REGR_SXY",
    "REGR_SYY",
    "REPEAT",
    "REPLACE",
    "RIGHT",
    "ROUND",
    "RTRIM",
    "SECOND",
    "SIGN",
    "SIN",
    "SMALLINT",
    "SOUNDEX",
    "SPACE",
    "SQRT",
    "STDDEV",
    "SUBSTR",
    "SUM",
    "TABLE_NAME",
    "TABLE_SCHEMA",
    "TAN",
    "TIME",
    "TIMESTAMP",
    "TIMESTAMP_ISO",
    "TIMESTAMPDIFF",
    "TRANSLATE",
    "TRIM",
    "TRUNCATE", "TRUNC",
    "TYPE_ID",
    "TYPE_NAME",
    "TYPE_SCHEMA",
    "UCASE", "UPPER",
    "USER",
    "VALUE",
    "VARCHAR",
    "VARGRAPHIC",
    "VARIANCE", "VAR",
    "WEEK",
    "WEEK_ISO",
    "YEAR"
  };

  private static final String allColumnSupportedFunctions[] =
  {
    "AVG",
    "CORRELATION", "CORR",
    "COUNT",
    "COUNT_BIG",
    "COVARIANCE", "COVAR",
    "DATE",
    "DAY",
    "DAYNAME",
    "DAYOFWEEK",
    "DAYOFYEAR",
    "DAYS",
    "GROUPING",
    "HOUR",
    "JULIAN_DAY",
    "MAX",
    "MICROSECOND",
    "MIN",
    "MINUTE",
    "MONTH",
    "MONTHNAME",
    "REGR_AVGX",
    "REGR_AVGY",
    "REGR_COUNT",
    "REGR_INTERCEPT", "REGR_ICPT",
    "REGR_R2",
    "REGR_SLOPE",
    "REGR_SXX",
    "REGR_SXY",
    "REGR_SYY",
    "QUARTER",
    "SECOND",
    "STDDEV",
    "SUM",
    "TIME",
    "TIMESTAMP",
    "TIMESTAMP_ISO",
    "TIMESTAMPDIFF",
    "VARIANCE", "VAR",
    "WEEK",
    "WEEK_ISO",
    "YEAR"
  };

  private static final String noBracketFunctions[] =
  {
    "CURRENT DATE",
    "CURRENT DEFAULT TRANSFORM GROUP",
    "CURRENT DEGREE",
    "CURRENT EXPLAIN MODE",
    "CURRENT EXPLAIN SNAPSHOT",
    "CURRENT NODE",
    "CURRENT PATH",
    "CURRENT QUERY OPTIMIZATION",
    "CURRENT REFRESH AGE",
    "CURRENT SCHEMA",
    "CURRENT SERVER",
    "CURRENT TIME",
    "CURRENT TIMESTAMP",
    "CURRENT TIMEZONE",
    "USER"
  };

  private static final String specialRegisters[] =
  {
    "CURRENT DATE",
    "CURRENT DEFAULT TRANSFORM GROUP",
    "CURRENT DEGREE",
    "CURRENT EXPLAIN MODE",
    "CURRENT EXPLAIN SNAPSHOT",
    "CURRENT NODE",
    "CURRENT PATH",
    "CURRENT QUERY OPTIMIZATION",
    "CURRENT REFRESH AGE",
    "CURRENT SCHEMA",
    "CURRENT SERVER",
    "CURRENT TIME",
    "CURRENT TIMESTAMP",
    "CURRENT TIMEZONE",
    "USER"
  };

  private static final String columnFunctions[] =
  {
    "AVG",
    "CORRELATION", "CORR",
    "COUNT",
    "COUNT_BIG",
    "COVARIANCE", "COVAR",
    "GROUPING",
    "MAX",
    "MIN",
    "REGR_AVGX",
    "REGR_AVGY",
    "REGR_COUNT",
    "REGR_INTERCEPT", "REGR_ICPT",
    "REGR_R2",
    "REGR_SLOPE",
    "REGR_SXX",
    "REGR_SXY",
    "REGR_SYY",
    "STDDEV",
    "SUM",
    "VARIANCE", "VAR"
  };

  private static final String functionsSupportingStar[] =
  {
    "COUNT",
    "COUNT_BIG",
  };

  private static final String unsupportedFunctions[] =
  {
    "MMDBSYS.QBSCORETBFROMNAME",
    "MMDBSYS.QBSCORETBFROMSTR"
  };

  private static final String scalarFunctions[] =
  {
    "ABS", "ABSVAL",
    "ACOS",
    "ASCII",
    "ASIN",
    "ATAN",
    "ATAN2",
    "BIGINT",
    "BLOB",
    "CEILING", "CEIL",
    "CHAR",
    "CHR",
    "CLOB",
    "COALESCE",
    "CONCAT",
    "COS",
    "COT",
    "DATE",
    "DAY",
    "DAYNAME",
    "DAYOFWEEK",
    "DAYOFWEEK_ISO",
    "DAYOFYEAR",
    "DAYS",
    "DBCLOB",
    "DECIMAL", "DEC",
    "DEGREES",
    "DEREF",
    "DIFFERENCE",
    "DIGITS",
    "DLCOMMENT",
    "DLLINKTYPE",
    "DLURLCOMPLETE",
    "DLURLPATH",
    "DLURLPATHONLY",
    "DLURLSCHEME",
    "DLURLSERVER",
    "DLVALUE",
    "DOUBLE", "DOUBLE_PRECISION",
    "EVENT_MON_STATE",
    "EXP",
    "FLOAT",
    "FLOOR",
    "GENERATE_UNIQUE",
    "GRAPHIC",
    "HEX",
    "HOUR",
    "INSERT",
    "INTEGER", "INT",
    "JULIAN_DAY",
    "LCASE", "LOWER",
    "LEFT",
    "LENGTH",
    "LN",
    "LOCATE",
    "LOG",
    "LOG10",
    "LONG_VARCHAR",
    "LONG_VARGRAPHIC",
    "LTRIM",
    "MICROSECOND",
    "MIDNIGHT_SECONDS",
    "MINUTE",
    "MOD",
    "MONTH",
    "MONTHNAME",
    "NODENUMBER",
    "NULLIF",
    "PARTITION",
    "POSSTR",
    "POWER",
    "QUARTER",
    "RADIANS",
    "RAISE_ERROR",
    "RAND",
    "REAL",
    "REPEAT",
    "REPLACE",
    "RIGHT",
    "ROUND",
    "RTRIM",
    "SECOND",
    "SIGN",
    "SIN",
    "SMALLINT",
    "SOUNDEX",
    "SPACE",
    "SQRT",
    "SUBSTR",
    "TABLE_NAME",
    "TABLE_SCHEMA",
    "TAN",
    "TIME",
    "TIMESTAMP",
    "TIMESTAMP_ISO",
    "TIMESTAMPDIFF",
    "TRANSLATE",
    "TRIM",
    "TRUNCATE", "TRUNC",
    "TYPE_ID",
    "TYPE_NAME",
    "TYPE_SCHEMA",
    "UCASE", "UPPER",
    "VALUE",
    "VARCHAR",
    "VARGRAPHIC",
    "WEEK",
    "WEEK_ISO",
    "YEAR"
  };

  private static final String dateTimeFunctions[] =
  {
    "DATE",
    "DAY",
    "DAYNAME",
    "DAYOFWEEK",
    "DAYOFYEAR",
    "DAYS",
    "HOUR",
    "JULIAN_DAY",
    "MICROSECOND",
    "MINUTE",
    "MONTH",
    "MONTHNAME",
    "QUARTER",
    "SECOND",
    "TIME",
    "TIMESTAMP",
    "TIMESTAMP_ISO",
    "TIMESTAMPDIFF",
    "WEEK",
    "WEEK_ISO",
    "YEAR"
  };

  private static final String db2Functions[] =
  {
    "EVENT_MON_STATE",
    "IDENTITY_VAL_LOCAL",
    "RAISE_ERROR",
    "TABLE_NAME",
    "TABLE_SCHEMA"
  };

  private static final String logicalFunctions[] =
  {
    "CEILING", "CEIL",
    "COALESCE",
    "FLOOR",
    "NULLIF",
    "VALUE"
  };

  private static final String conversionFunctions[] =
  {
    "DECIMAL",
    "DIGITS",
    "DOUBLE",
    "FLOAT",
    "HEX",
    "INTEGER", "INT",
    "SMALLINT",
    "TRUNCATE",
    "VARCHAR",
    "VARGRAPHIC"
  };

  private static final String mathFunctions[] =
  {
    "ABSVAL",
    "ACOS",
    "ASIN",
    "ATAN",
    "ATAN2",
    "COS",
    "COT",
    "DEGREES",
    "EXP",
    "LOG10",
    "MOD",
    "POWER",
    "RADIANS",
    "RAND",
    "ROUND",
    "SIGN",
    "SIN",
    "SQRT",
    "TAN"
  };

  private static final String summaryFunctions[] =
  {
    "AVG",
    "COUNT",
    "MAX",
    "MIN",
    "SUM"
  };

  private static final String textFunctions[] =
  {
    "ASCII",
    "BLOB",
    "CHR",
    "CLOB",
    "CONCAT",
    "DBCLOB",
    "DIFFERENCE",
    "GRAPHIC",
    "INSERT",
    "LCASE", "LOWER",
    "LEFT",
    "LENGTH",
    "LOCATE",
    "LONG_VARCHAR",
    "LONG_VARGRAPHIC",
    "LTRIM",
    "POSSTR",
    "REPEAT",
    "REPLACE",
    "RIGHT",
    "RTRIM",
    "SOUNDEX",
    "SPACE",
    "SUBSTR",
    "TRANSLATE",
    "TRIM",
    "UCASE", "UPPER"
  };

  private static final String textExtenderFunctions[] =
  {
    "DB2TX.CCSID",
    "DB2TX.CONTAINS",
    "DB2TX.FILE",
    "DB2TX.FORMAT",
    "DB2TX.HANDLE",
    "DB2TX.HANDLE_LIST",
    "DB2TX.INIT_TEXT_HANDLE",
    "DB2TX.LANGUAGE",
    "DB2TX.NO_OF_DOCUMENTS",
    "DB2TX.NO_OF_MATCHES",
    "DB2TX.RANK",
    "DB2TX.REFINE",
    "DB2TX.SEARCH_RESULT"
  };

  private static final String aivExtenderFunctions[] =
  {
    "MMDBSYS.ALIGNVALUE",
    "MMDBSYS.ASPECTRATIO",
    "MMDBSYS.BITSPERSAMPLE",
    "MMDBSYS.BYTESPERSEC",
    "MMDBSYS.COMMENT",
    "MMDBSYS.COMPRESSTYPE",
    "MMDBSYS.CONTENT",
    "MMDBSYS.DB2AUDIO",
    "MMDBSYS.DB2IMAGE",
    "MMDBSYS.DB2VIDEO",
    "MMDBSYS.DURATION",
    "MMDBSYS.FILENAME",
    "MMDBSYS.FINDINSTRUMENT",
    "MMDBSYS.FINDTRACKNAME",
    "MMDBSYS.FORMAT",
    "MMDBSYS.FRAMERATE",
    "MMDBSYS.GETINSTRUMENTS",
    "MMDBSYS.GETTRACKNAMES",
    "MMDBSYS.HEIGHT",
    "MMDBSYS.IMPORTER",
    "MMDBSYS.IMPORTTIME",
    "MMDBSYS.MAXBYTESPERSEC",
    "MMDBSYS.NUMAUDIOTRACKS",
    "MMDBSYS.NUMCHANNELS",
    "MMDBSYS.NUMCOLORS",
    "MMDBSYS.NUMFRAMES",
    "MMDBSYS.NUMVIDEOTRACKS",
    "MMDBSYS.QBSCOREFROMNAME",
    "MMDBSYS.QBSCOREFROMSTR",
    "MMDBSYS.QBSCORETBFROMNAME",
    "MMDBSYS.QBSCORETBFROMSTR",
    "MMDBSYS.REPLACE",
    "MMDBSYS.SAMPLINGRATE",
    "MMDBSYS.SIZE",
    "MMDBSYS.THUMBNAIL",
    "MMDBSYS.TICKSPERQNOTE",
    "MMDBSYS.TICKSPERSEC",
    "MMDBSYS.UPDATER",
    "MMDBSYS.UPDATETIME",
    "MMDBSYS.WIDTH"
  };

  private static final Integer LIST000 = new Integer(0);
  private static final Integer LIST010 = new Integer(10);
  private static final Integer LIST020 = new Integer(20);
  private static final Integer LIST030 = new Integer(30);
  private static final Integer LIST040 = new Integer(40);
  private static final Integer LIST050 = new Integer(50);
  private static final Integer LIST060 = new Integer(60);
  private static final Integer LIST070 = new Integer(70);
  private static final Integer LIST080 = new Integer(80);
  private static final Integer LIST090 = new Integer(90);
  private static final Integer LIST100 = new Integer(100);
  private static final Integer LIST110 = new Integer(110);
  private static final Integer LIST120 = new Integer(120);
  private static final Integer LIST130 = new Integer(130);
  private static final Integer LIST140 = new Integer(140);
  private static final Integer LIST150 = new Integer(150);
  private static final Integer LIST160 = new Integer(160);
  private static final Integer LIST170 = new Integer(170);
  private static final Integer LIST180 = new Integer(180);
  private static final Integer LIST190 = new Integer(190);
  private static final Integer LIST200 = new Integer(200);
  private static final Integer LIST210 = new Integer(210);
  private static final Integer LIST220 = new Integer(220);
  private static final Integer LIST230 = new Integer(230);
  private static final Integer LIST240 = new Integer(240);
  private static final Integer LIST250 = new Integer(250);
  private static final Integer LIST260 = new Integer(260);
  private static final Integer LIST270 = new Integer(270);
  private static final Integer LIST280 = new Integer(280);
  private static final Integer LIST290 = new Integer(290);
  private static final Integer LIST300 = new Integer(300);
  private static final Integer LIST310 = new Integer(310);
  private static final Integer LIST320 = new Integer(320);
  private static final Integer LIST330 = new Integer(330);
  private static final Integer LIST340 = new Integer(340);
  private static final Integer LIST350 = new Integer(350);
  private static final Integer LIST360 = new Integer(360);
  private static final Integer LIST370 = new Integer(370);
  private static final Integer LIST380 = new Integer(380);
  private static final Integer LIST390 = new Integer(390);
  private static final Integer LIST400 = new Integer(400);
  private static final Integer LIST410 = new Integer(410);
  private static final Integer LIST420 = new Integer(420);
  private static final Integer LIST430 = new Integer(430);
  private static final Integer LIST440 = new Integer(440);
  private static final Integer LIST450 = new Integer(450);
  private static final Integer LIST460 = new Integer(460);
  private static final Integer LIST470 = new Integer(470);
  private static final Integer LIST480 = new Integer(480);
  private static final Integer LIST490 = new Integer(490);
  private static final Integer LIST500 = new Integer(500);
  private static final Integer LIST510 = new Integer(510);
  private static final Integer LIST520 = new Integer(520);
  private static final Integer LIST530 = new Integer(530);
  private static final Integer LIST540 = new Integer(540);
  private static final Integer LIST550 = new Integer(550);
  private static final Integer LIST560 = new Integer(560);
  private static final Integer LIST570 = new Integer(570);
  private static final Integer LIST580 = new Integer(580);
  private static final Integer LIST590 = new Integer(590);
  private static final Integer LIST600 = new Integer(600);
  private static final Integer LIST610 = new Integer(610);
  private static final Integer LIST620 = new Integer(620);
  private static final Integer LIST630 = new Integer(630);
  private static final Integer LIST640 = new Integer(640);
  private static final Integer LIST650 = new Integer(650);
  private static final Integer LIST660 = new Integer(660);
  private static final Integer LIST670 = new Integer(670);
  private static final Integer LIST680 = new Integer(680);
  private static final Integer LIST690 = new Integer(690);
  private static final Integer LIST700 = new Integer(700);
  private static final Integer LIST710 = new Integer(710);
  private static final Integer LIST720 = new Integer(720);
  private static final Integer LIST730 = new Integer(730);
  private static final Integer LIST740 = new Integer(740);
  private static final Integer LIST750 = new Integer(750);
  private static final Integer LIST760 = new Integer(760);
  private static final Integer LIST770 = new Integer(770);
  private static final Integer LIST780 = new Integer(780);
  private static final Integer LIST790 = new Integer(790);
  private static final Integer LIST800 = new Integer(800);
  private static final Integer LIST810 = new Integer(810);
  private static final Integer LIST820 = new Integer(820);
  private static final Integer LIST830 = new Integer(830);
  private static final Integer LIST840 = new Integer(840);
  private static final Integer LIST850 = new Integer(850);
  private static final Integer LIST860 = new Integer(860);
  private static final Integer LIST870 = new Integer(870);
  private static final Integer LIST880 = new Integer(880);
  private static final Integer LIST890 = new Integer(890);
  private static final Integer LIST900 = new Integer(900);
  private static final Integer LIST910 = new Integer(910);
  private static final Integer LIST920 = new Integer(920);
  private static final Integer LIST930 = new Integer(930);
  private static final Integer LIST940 = new Integer(940);
  private static final Integer LIST950 = new Integer(950);
  private static final Integer LIST960 = new Integer(960);
  private static final Integer LIST970 = new Integer(970);
  private static final Integer LIST980 = new Integer(980);
  private static final Integer LIST990 = new Integer(990);
  private static final Integer LIST1000 = new Integer(1000);
  private static final Integer LIST1010 = new Integer(1010);
  private static final Integer LIST1020 = new Integer(1020);
  private static final Integer LIST1030 = new Integer(1030);
  private static final Integer LIST1040 = new Integer(1040);
  private static final Integer LIST1050 = new Integer(1050);
  private static final Integer LIST1060 = new Integer(1060);
  private static final Integer LIST1070 = new Integer(1070);
  private static final Integer LIST1080 = new Integer(1080);
  private static final Integer LIST1090 = new Integer(1090);
  private static final Integer LIST1100 = new Integer(1100);
  private static final Integer LIST1110 = new Integer(1110);
  private static final Integer LIST1120 = new Integer(1120);
  private static final Integer LIST1130 = new Integer(1130);
  private static final Integer LIST1140 = new Integer(1140);
  private static final Integer LIST1150 = new Integer(1150);
  private static final Integer LIST1160 = new Integer(1160);
  private static final Integer LIST1170 = new Integer(1170);
  private static final Integer LIST1180 = new Integer(1180);
  private static final Integer LIST1190 = new Integer(1190);
  private static final Integer LIST1200 = new Integer(1200);
  private static final Integer LIST1210 = new Integer(1210);
  private static final Integer LIST1220 = new Integer(1220);
  private static final Integer LIST1230 = new Integer(1230);
  private static final Integer LIST1240 = new Integer(1240);
  private static final Integer LIST1250 = new Integer(1250);
  private static final Integer LIST1260 = new Integer(1260);

  private static final int FUNC000 = 0;
  private static final int FUNC010 = 10;
  private static final int FUNC020 = 20;
  private static final int FUNC030 = 30;
  private static final int FUNC040 = 40;
  private static final int FUNC050 = 50;
  private static final int FUNC060 = 60;
  private static final int FUNC070 = 70;
  private static final int FUNC080 = 80;
  private static final int FUNC090 = 90;
  private static final int FUNC100 = 100;
  private static final int FUNC110 = 110;
  private static final int FUNC120 = 120;
  private static final int FUNC130 = 130;
  private static final int FUNC140 = 140;
  private static final int FUNC150 = 150;
  private static final int FUNC160 = 160;
  private static final int FUNC170 = 170;
  private static final int FUNC180 = 180;
  private static final int FUNC190 = 190;
  private static final int FUNC200 = 200;
  private static final int FUNC210 = 210;
  private static final int FUNC220 = 220;
  private static final int FUNC230 = 230;
  private static final int FUNC240 = 240;
  private static final int FUNC250 = 250;
  private static final int FUNC260 = 260;
  private static final int FUNC270 = 270;
  private static final int FUNC280 = 280;
  private static final int FUNC290 = 290;
  private static final int FUNC300 = 300;
  private static final int FUNC310 = 310;
  private static final int FUNC320 = 320;
  private static final int FUNC330 = 330;
  private static final int FUNC340 = 340;
  private static final int FUNC350 = 350;
  private static final int FUNC360 = 360;
  private static final int FUNC370 = 370;
  private static final int FUNC380 = 380;
  private static final int FUNC390 = 390;
  private static final int FUNC400 = 400;
  private static final int FUNC410 = 410;
  private static final int FUNC420 = 420;
  private static final int FUNC430 = 430;
  private static final int FUNC440 = 440;
  private static final int FUNC450 = 450;
  private static final int FUNC460 = 460;
  private static final int FUNC470 = 470;
  private static final int FUNC480 = 480;
  private static final int FUNC490 = 490;
  private static final int FUNC500 = 500;
  private static final int FUNC510 = 510;
  private static final int FUNC520 = 520;
  private static final int FUNC530 = 530;
  private static final int FUNC540 = 540;
  private static final int FUNC550 = 550;
  private static final int FUNC560 = 560;
  private static final int FUNC570 = 570;
  private static final int FUNC580 = 580;
  private static final int FUNC590 = 590;
  private static final int FUNC600 = 600;
  private static final int FUNC610 = 610;
  private static final int FUNC620 = 620;
  private static final int FUNC630 = 630;
  private static final int FUNC640 = 640;
  private static final int FUNC650 = 650;
  private static final int FUNC660 = 660;
  private static final int FUNC670 = 670;
  private static final int FUNC680 = 680;
  private static final int FUNC690 = 690;
  private static final int FUNC700 = 700;
  private static final int FUNC710 = 710;
  private static final int FUNC720 = 720;
  private static final int FUNC730 = 730;
  private static final int FUNC740 = 740;
  private static final int FUNC750 = 750;
  private static final int FUNC760 = 760;
  private static final int FUNC770 = 770;
  private static final int FUNC780 = 780;
  private static final int FUNC790 = 790;
  private static final int FUNC800 = 800;
  private static final int FUNC810 = 810;
  private static final int FUNC820 = 820;
  private static final int FUNC830 = 830;
  private static final int FUNC840 = 840;
  private static final int FUNC850 = 850;
  private static final int FUNC860 = 860;
  private static final int FUNC870 = 870;
  private static final int FUNC880 = 880;
  private static final int FUNC890 = 890;
  private static final int FUNC900 = 900;
  private static final int FUNC910 = 910;
  private static final int FUNC920 = 920;
  private static final int FUNC930 = 930;
  private static final int FUNC940 = 940;
  private static final int FUNC950 = 950;
  private static final int FUNC960 = 960;
  private static final int FUNC970 = 970;
  private static final int FUNC980 = 980;
  private static final int FUNC990 = 990;
  private static final int FUNC1000 = 1000;
  private static final int FUNC1010 = 1010;
  private static final int FUNC1020 = 1020;
  private static final int FUNC1030 = 1030;
  private static final int FUNC1040 = 1040;
  private static final int FUNC1050 = 1050;
  private static final int FUNC1060 = 1060;
  private static final int FUNC1070 = 1070;
  private static final int FUNC1080 = 1080;
  private static final int FUNC1090 = 1090;
  private static final int FUNC1100 = 1100;
  private static final int FUNC1110 = 1110;
  private static final int FUNC1120 = 1120;
  private static final int FUNC1130 = 1130;
  private static final int FUNC1140 = 1140;
  private static final int FUNC1150 = 1150;
  private static final int FUNC1160 = 1160;
  private static final int FUNC1170 = 1170;
  private static final int FUNC1180 = 1180;
  private static final int FUNC1190 = 1190;
  private static final int FUNC1200 = 1200;
  private static final int FUNC1210 = 1210;
  private static final int FUNC1220 = 1220;
  private static final int FUNC1230 = 1230;
  private static final int FUNC1240 = 1240;
  private static final int FUNC1250 = 1250;
  private static final int FUNC1260 = 1260;

  private static final Hashtable names2index = new Hashtable();

  static
  {
    names2index.put("ABS",     LIST000);
    names2index.put("ABSVAL",  LIST000);
    names2index.put("CEILING", LIST000);
    names2index.put("CEIL",    LIST000);
    names2index.put("FLOOR",   LIST000);
    names2index.put("SIGN",    LIST000);

    names2index.put("DATE",    LIST010);

    names2index.put("DAY",      LIST020);
    names2index.put("MONTH",    LIST020);
    names2index.put("YEAR",     LIST020);

    names2index.put("HOUR",     LIST030);
    names2index.put("MINUTE",   LIST030);
    names2index.put("SECOND",   LIST030);

    names2index.put("ACOS",     LIST040);
    names2index.put("ASIN",     LIST040);
    names2index.put("ATAN",     LIST040);
    // names2index.put("ATAN2",    LIST040);
    names2index.put("COS",      LIST040);
    names2index.put("COT",      LIST040);
    names2index.put("DEGREES",  LIST040);
    names2index.put("EXP",      LIST040);
    names2index.put("LOG",      LIST040);
    names2index.put("LN",       LIST040);
    names2index.put("LOG10",    LIST040);
    names2index.put("RADIANS",  LIST040);
    names2index.put("SIN",      LIST040);
    names2index.put("SQRT",     LIST040);
    names2index.put("STDDEV",   LIST040);
    names2index.put("TAN",      LIST040);
    names2index.put("VAR",      LIST040);
    names2index.put("VARIANCE", LIST040);

    names2index.put("ATAN2",    LIST050);

    names2index.put("MOD",      LIST060);

    names2index.put("POWER",    LIST070);

    names2index.put("RAND",     LIST080);

    names2index.put("GENERATE_UNIQUE", LIST090);

    names2index.put("TABLE_NAME",   LIST100);
    names2index.put("TABLE_SCHEMA", LIST100);

    names2index.put("DLVALUE",  LIST110);

    names2index.put("DECIMAL",  LIST120);
    names2index.put("DEC",      LIST120);

    names2index.put("TIMESTAMP",LIST130);

    names2index.put("TRANSLATE",LIST140);

    names2index.put("ROUND",    LIST150);
    names2index.put("TRUNCATE", LIST150);
    names2index.put("TRUNC",    LIST150);

    names2index.put("DAYNAME",  LIST160);
    names2index.put("MONTHNAME",LIST160);

    names2index.put("TIMESTAMP_ISO", LIST170);

    names2index.put("DAYOFWEEK",     LIST180);
    names2index.put("DAYOFWEEK_ISO", LIST180);
    names2index.put("DAYOFYEAR",     LIST180);
    names2index.put("DAYS",          LIST180);
    names2index.put("JULIAN_DAY",    LIST180);
    names2index.put("QUARTER",       LIST180);
    names2index.put("WEEK",          LIST180);
    names2index.put("WEEK_ISO",      LIST180);

    names2index.put("TIMESTAMPDIFF", LIST190);

    names2index.put("ASCII",         LIST200);

    names2index.put("CHR",           LIST210);

    names2index.put("INSERT",        LIST220);

    names2index.put("LTRIM",         LIST230);
    names2index.put("RTRIM",         LIST230);
    names2index.put("TRIM",          LIST230);

    names2index.put("LCASE",         LIST240);

    names2index.put("LOWER",         LIST250);

    names2index.put("LOCATE",        LIST260);

    names2index.put("SOUNDEX",       LIST270);

    names2index.put("DIFFERENCE",    LIST280);

    names2index.put("REPEAT",        LIST290);
    names2index.put("RIGHT",         LIST290);
    names2index.put("LEFT",          LIST290);

    names2index.put("SPACE",         LIST300);

    names2index.put("REPLACE",       LIST310);

    names2index.put("CHAR",          LIST320);

    names2index.put("DOUBLE",        LIST330);

    names2index.put("DOUBLE_PRECISION", LIST340);
    names2index.put("FLOAT",         LIST340);

    names2index.put("DIGITS",        LIST350);

    names2index.put("EVENT_MON_STATE",  LIST360);

    names2index.put("UCASE",         LIST370);
    names2index.put("UPPER",         LIST370);

    names2index.put("DLCOMMENT",     LIST380);
    names2index.put("DLLINKTYPE",    LIST380);
    names2index.put("DLURLCOMPLETE", LIST380);
    names2index.put("DLURLPATH",     LIST380);
    names2index.put("DLURLPATHONLY", LIST380);
    names2index.put("DLURLSCHEME",   LIST380);
    names2index.put("DLURLSERVER",   LIST380);

    names2index.put("MICROSECOND",   LIST390);

    names2index.put("TIME",          LIST400);

    names2index.put("MIDNIGHT_SECONDS", LIST410);

    names2index.put("AVG",           LIST420);

    names2index.put("BIGINT",        LIST430);

    names2index.put("INT",           LIST440);
    names2index.put("INTEGER",       LIST440);

    names2index.put("SMALLINT",      LIST450);

    names2index.put("SUM",           LIST460);

    names2index.put("REAL",          LIST470);

    names2index.put("NODENUMBER",    LIST480);
    names2index.put("PARTITION",     LIST480);

    names2index.put("GROUPING",      LIST490);

    names2index.put("COUNT_BIG",     LIST500);

    names2index.put("HEX",           LIST510);
    names2index.put("TYPE_NAME",     LIST510);
    names2index.put("TYPE_SCHEMA",   LIST510);

    names2index.put("LENGTH",        LIST520);
    names2index.put("COUNT",         LIST520);
    names2index.put("TYPE_ID",       LIST520);

    names2index.put("MAX",           LIST530);
    names2index.put("MIN",           LIST530);

    names2index.put("NULLIF",        LIST540);

    names2index.put("CONCAT",        LIST550);

    names2index.put("RAISE_ERROR",   LIST560);

    names2index.put("POSSTR",        LIST570);

    names2index.put("LONG_VARCHAR",  LIST580);

    names2index.put("LONG_VARGRAPHIC", LIST590);

    names2index.put("COALESCE",      LIST600);
    names2index.put("VALUE",         LIST600);

    names2index.put("SUBSTR",        LIST610);

    names2index.put("BLOB",          LIST620);

    names2index.put("CLOB",          LIST630);

    names2index.put("CORR",          LIST640);
    names2index.put("CORRELATION",   LIST640);
    names2index.put("COVAR",         LIST640);
    names2index.put("COVARIANCE",    LIST640);
    names2index.put("REGR_AVGX",     LIST640);
    names2index.put("REGR_AVGY",     LIST640);
    names2index.put("REGR_ICPT",     LIST640);
    names2index.put("REGR_INTERCEPT",LIST640);
    names2index.put("REGR_R2",       LIST640);
    names2index.put("REGR_SLOPE",    LIST640);
    names2index.put("REGR_SXX",      LIST640);
    names2index.put("REGR_SXY",      LIST640);
    names2index.put("REGR_SYY",      LIST640);

    names2index.put("REGR_COUNT",    LIST650);

    names2index.put("DBCLOB",        LIST660);

    names2index.put("GRAPHIC",       LIST670);

    names2index.put("VARCHAR",       LIST680);

    names2index.put("VARGRAPHIC",    LIST690);

    names2index.put("VEBLOB_CP_SMALL", LIST700);
    names2index.put("VEBLOB_CP_LARGE", LIST700);

    names2index.put("CURRENT DATE", LIST710);
    names2index.put("CURRENT DEFAULT TRANSFORM GROUP", LIST710);
    names2index.put("CURRENT DEGREE", LIST710);
    names2index.put("CURRENT EXPLAIN MODE", LIST710);
    names2index.put("CURRENT EXPLAIN SNAPSHOT", LIST710);
    names2index.put("CURRENT NODE", LIST710);
    names2index.put("CURRENT PATH", LIST710);
    names2index.put("CURRENT QUERY OPTIMIZATION", LIST710);
    names2index.put("CURRENT REFRESH AGE", LIST710);
    names2index.put("CURRENT SCHEMA", LIST710);
    names2index.put("CURRENT SERVER", LIST710);
    names2index.put("CURRENT TIME", LIST710);
    names2index.put("CURRENT TIMESTAMP", LIST710);
    names2index.put("CURRENT TIMEZONE", LIST710);
    names2index.put("USER", LIST710);

    names2index.put("DB2TX.CCSID", LIST720);
    names2index.put("DB2TX.CONTAINS", LIST730);
    names2index.put("DB2TX.FILE", LIST740);
    names2index.put("DB2TX.FORMAT", LIST750);
    names2index.put("DB2TX.HANDLE", LIST760);
    names2index.put("DB2TX.HANDLE_LIST", LIST770);
    names2index.put("DB2TX.INIT_TEXT_HANDLE", LIST780);
    names2index.put("DB2TX.LANGUAGE", LIST790);
    names2index.put("DB2TX.NO_OF_DOCUMENTS", LIST800);
    names2index.put("DB2TX.NO_OF_MATCHES", LIST810);
    names2index.put("DB2TX.RANK", LIST820);
    names2index.put("DB2TX.REFINE", LIST830);
    names2index.put("DB2TX.SEARCH_RESULT", LIST840);

    names2index.put("MMDBSYS.ALIGNVALUE", LIST850);
    names2index.put("MMDBSYS.ASPECTRATIO", LIST860);
    names2index.put("MMDBSYS.BITSPERSAMPLE", LIST870);
    names2index.put("MMDBSYS.BYTESPERSEC", LIST880);
    names2index.put("MMDBSYS.COMMENT", LIST890);
    names2index.put("MMDBSYS.COMPRESSTYPE", LIST900);
    names2index.put("MMDBSYS.CONTENT", LIST910);
    names2index.put("MMDBSYS.DB2AUDIO", LIST920);
    names2index.put("MMDBSYS.DB2IMAGE", LIST930);
    names2index.put("MMDBSYS.DB2VIDEO", LIST940);
    names2index.put("MMDBSYS.DURATION", LIST950);
    names2index.put("MMDBSYS.FILENAME", LIST960);
    names2index.put("MMDBSYS.FINDINSTRUMENT", LIST970);
    names2index.put("MMDBSYS.FINDTRACKNAME", LIST980);
    names2index.put("MMDBSYS.FORMAT", LIST990);
    names2index.put("MMDBSYS.FRAMERATE", LIST1000);
    names2index.put("MMDBSYS.GETINSTRUMENTS", LIST1010);
    names2index.put("MMDBSYS.GETTRACKNAMES", LIST1020);
    names2index.put("MMDBSYS.HEIGHT", LIST1030);
    names2index.put("MMDBSYS.IMPORTER", LIST1040);
    names2index.put("MMDBSYS.IMPORTTIME", LIST1050);
    names2index.put("MMDBSYS.MAXBYTESPERSEC", LIST1060);
    names2index.put("MMDBSYS.NUMAUDIOTRACKS", LIST1070);
    names2index.put("MMDBSYS.NUMCHANNELS", LIST1080);
    names2index.put("MMDBSYS.NUMCOLORS", LIST1090);
    names2index.put("MMDBSYS.NUMFRAMES", LIST1100);
    names2index.put("MMDBSYS.NUMVIDEOTRACKS", LIST1110);
    names2index.put("MMDBSYS.QBSCOREFROMNAME", LIST1120);
    names2index.put("MMDBSYS.QBSCOREFROMSTR", LIST1130);
    names2index.put("MMDBSYS.QBSCORETBFROMNAME", LIST1140);
    names2index.put("MMDBSYS.QBSCORETBFROMSTR", LIST1150);
    names2index.put("MMDBSYS.REPLACE", LIST1160);
    names2index.put("MMDBSYS.SAMPLINGRATE", LIST1170);
    names2index.put("MMDBSYS.SIZE", LIST1180);
    names2index.put("MMDBSYS.THUMBNAIL", LIST1190);
    names2index.put("MMDBSYS.TICKSPERQNOTE", LIST1200);
    names2index.put("MMDBSYS.TICKSPERSEC", LIST1210);
    names2index.put("MMDBSYS.UPDATER", LIST1220);
    names2index.put("MMDBSYS.UPDATETIME", LIST1230);
    names2index.put("MMDBSYS.WIDTH", LIST1240);
    
    names2index.put("IDENTITY_VAL_LOCAL", LIST1250);
    names2index.put("DEREF", LIST1260);
  }

  public static Object[][] getParms(Integer listnum)
  {
    Object[][] list = new Object[1][2];
    list[0][0] = "???";            list[0][1] = "???";
    if (listnum != null)
      switch( listnum.intValue() )
      {
        // ==========> ABS, ABSVAL, CEILING, CEIL, FLOOR, SIGN
        case FUNC000:
          list = new Object[4][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "DOUBLE";
          list[1][0] = "INTEGER";        list[1][1] = "INTEGER";
          list[2][0] = "SMALLINT";       list[2][1] = "SMALLINT";
          list[3][0] = "BIGINT";         list[3][1] = "BIGINT";
          break;
        // ==========> DATE  (added (lee457))
        case FUNC010:
          list = new Object[4][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DATE";           list[0][1] = "DATE";
          list[1][0] = "DATE";           list[1][1] = "TIMESTAMP";
          list[2][0] = "DATE";           list[2][1] = "DOUBLE";
          list[3][0] = "DATE";           list[3][1] = "VARCHAR";
          break;
        // ==========> DAY, MONTH, YEAR   (added (lee457))
        case FUNC020:
          list = new Object[4][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "VARCHAR";
          list[1][0] = "INTEGER";        list[1][1] = "DATE";
          list[2][0] = "INTEGER";        list[2][1] = "TIMESTAMP";
          list[3][0] = "INTEGER";        list[3][1] = "DECIMAL";
          break;
        // ==========> HOUR, MINUTE, SECOND  (added (lee457))
        case FUNC030:
          list = new Object[4][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "VARCHAR";
          list[1][0] = "INTEGER";        list[1][1] = "TIME";
          list[2][0] = "INTEGER";        list[2][1] = "TIMESTAMP";
          list[3][0] = "INTEGER";        list[3][1] = "DECIMAL";
          break;
        // ==========> ACOS, ASIN, ATAN, (ATAN2 moved to signature= output:DOUBLE,
        // ==========> input:DOUBLE,DOUBLE), COS, COT, DEGREES, EXP, LOG, LN,
        // ==========> LOG10, RADIANS, SIN, SQRT, STDDEV, TAN, VAR, VARIANCE
        case FUNC040:
          list = new Object[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "DOUBLE";
          break;
        // ==========> ATAN2 (added (lee457))
        case FUNC050:
          list = new Object[1][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "DOUBLE";        list[0][2] = "DOUBLE";
          break;
        // ==========> MOD
        case FUNC060:
          list = new Object[3][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "INTEGER";       list[0][2] = "INTEGER";
          list[1][0] = "SMALLINT";       list[1][1] = "SMALLINT";      list[1][2] = "SMALLINT";
          list[2][0] = "BIGINT";         list[2][1] = "BIGINT";        list[2][2] = "BIGINT";
          break;
        // ==========> POWER
        case FUNC070:
          list = new Object[4][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "DOUBLE";        list[0][2] = "DOUBLE";
          list[1][0] = "DOUBLE";         list[1][1] = "DOUBLE";        list[1][2] = "INTEGER";
          list[2][0] = "INTEGER";        list[2][1] = "INTEGER";       list[2][2] = "INTEGER";
          list[3][0] = "BIGINT";         list[3][1] = "BIGINT";        list[3][2] = "BIGINT";
          break;
        // ==========> RAND    (How does this work? lee457)
        case FUNC080:
          list = new Object[2][];

          list[0] = new Object[1];
          list[1] = new Object[2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";
          list[1][0] = "DOUBLE";         list[1][1] = "INTEGER";
          break;
        // ==========> GENERATE_UNIQUE    (How does this work? lee457)
        case FUNC090:
          list = new Object[1][];
          list[0] = new Object[1];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CHARACTER";
          break;
        // ==========> TABLE_NAME      (added (lee457)) (How does this work? lee457)
        // ==========> TABLE_SCHEMA    (added (lee457)) (How does this work? lee457)
        case FUNC100:
          list = new Object[2][];

          list[0] = new Object[2];
          list[1] = new Object[3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "VARCHAR";
          list[1][0] = "VARCHAR";        list[1][1] = "VARCHAR";       list[1][2] = "VARCHAR";
          break;
        // ==========> DLVALUE      (added (lee457)) (How does this work? lee457)
        case FUNC110:
          list = new Object[3][];

          list[0] = new Object[2];
          list[1] = new Object[3];
          list[2] = new Object[4];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DATALINK";       list[0][1] = "VARCHAR";
          list[1][0] = "DATALINK";       list[1][1] = "VARCHAR";       list[1][2] = "VARCHAR";
          list[2][0] = "DATALINK";       list[2][1] = "VARCHAR";       list[2][2] = "VARCHAR"; list[2][3] = "VARCHAR";
          break;
        // ==========> DECIMAL      (added (lee457)) (How does this work? lee457)
        // ==========> DEC          (added (lee457)) (How does this work? lee457)
        case FUNC120:
          list = new Object[22][];

          list[0] = new Object[2];
          list[1] = new Object[3];
          list[2] = new Object[4];
          list[3] = new Object[5];
          list[4] = new Object[2];
          list[5] = new Object[2];
          list[6] = new Object[2];
          list[7] = new Object[2];
          list[8] = new Object[2];
          list[9] = new Object[2];
          list[10] = new Object[3];
          list[11] = new Object[3];
          list[12] = new Object[3];
          list[13] = new Object[3];
          list[14] = new Object[3];
          list[15] = new Object[3];
          list[16] = new Object[4];
          list[17] = new Object[4];
          list[18] = new Object[4];
          list[19] = new Object[4];
          list[20] = new Object[4];
          list[21] = new Object[4];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DECIMAL";        list[0][1] = "VARCHAR";
          list[1][0] = "DECIMAL";        list[1][1] = "VARCHAR";       list[1][2] = "INTEGER";
          list[2][0] = "DECIMAL";        list[2][1] = "VARCHAR";       list[2][2] = "INTEGER";       list[2][3] = "INTEGER";
          list[3][0] = "DECIMAL";        list[3][1] = "VARCHAR";       list[3][2] = "INTEGER";       list[3][3] = "INTEGER"; list[3][4] = "VARCHAR";

          list[4][0] = "DECIMAL";        list[4][1] = "SMALLINT";
          list[5][0] = "DECIMAL";        list[5][1] = "INTEGER";
          list[6][0] = "DECIMAL";        list[6][1] = "BIGINT";
          list[7][0] = "DECIMAL";        list[7][1] = "DECIMAL";
          list[8][0] = "DECIMAL";        list[8][1] = "REAL";
          list[9][0] = "DECIMAL";        list[9][1] = "DOUBLE";

          list[10][0] = "DECIMAL";       list[10][1] = "SMALLINT";     list[10][2] = "INTEGER";
          list[11][0] = "DECIMAL";       list[11][1] = "INTEGER";      list[11][2] = "INTEGER";
          list[12][0] = "DECIMAL";       list[12][1] = "BIGINT";       list[12][2] = "INTEGER";
          list[13][0] = "DECIMAL";       list[13][1] = "DECIMAL";      list[13][2] = "INTEGER";
          list[14][0] = "DECIMAL";       list[14][1] = "REAL";         list[14][2] = "INTEGER";
          list[15][0] = "DECIMAL";       list[15][1] = "DOUBLE";       list[15][2] = "INTEGER";

          list[16][0] = "DECIMAL";       list[16][1] = "SMALLINT";     list[16][2] = "INTEGER";      list[16][3] = "INTEGER";
          list[17][0] = "DECIMAL";       list[17][1] = "INTEGER";      list[17][2] = "INTEGER";      list[17][3] = "INTEGER";
          list[18][0] = "DECIMAL";       list[18][1] = "BIGINT";       list[18][2] = "INTEGER";      list[18][3] = "INTEGER";
          list[19][0] = "DECIMAL";       list[19][1] = "DECIMAL";      list[19][2] = "INTEGER";      list[19][3] = "INTEGER";
          list[20][0] = "DECIMAL";       list[20][1] = "REAL";         list[20][2] = "INTEGER";      list[20][3] = "INTEGER";
          list[21][0] = "DECIMAL";       list[21][1] = "DOUBLE";       list[21][2] = "INTEGER";      list[21][3] = "INTEGER";
          break;
        // ==========> TIMESTAMP      (added (lee457)) (How does this work? lee457)
        case FUNC130:
          list = new Object[6][];

          list[0] = new Object[2];
          list[1] = new Object[2];
          list[2] = new Object[3];
          list[3] = new Object[3];
          list[4] = new Object[3];
          list[5] = new Object[3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "TIMESTAMP";      list[0][1] = "TIMESTAMP";
          list[1][0] = "TIMESTAMP";      list[1][1] = "VARCHAR";
          list[2][0] = "TIMESTAMP";      list[2][1] = "VARCHAR";       list[2][2] = "VARCHAR";
          list[3][0] = "TIMESTAMP";      list[3][1] = "VARCHAR";       list[3][2] = "TIME";
          list[4][0] = "TIMESTAMP";      list[4][1] = "DATE";          list[4][2] = "VARCHAR";
          list[5][0] = "TIMESTAMP";      list[5][1] = "DATE";          list[5][2] = "TIME";
          break;
        // ==========> TRANSLATE      (added (lee457)) (How does this work? lee457)
        case FUNC140:
          list = new Object[10][];

          list[0] = new Object[2];
          list[1] = new Object[2];
          list[2] = new Object[4];
          list[3] = new Object[4];
          list[4] = new Object[5];
          list[5] = new Object[5];
          list[6] = new Object[4];
          list[7] = new Object[4];
          list[8] = new Object[5];
          list[9] = new Object[5];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CHARACTER";      list[0][1] = "CHARACTER";
          list[1][0] = "VARCHAR";        list[1][1] = "VARCHAR";
          list[2][0] = "CHARACTER";      list[2][1] = "CHARACTER";     list[2][2] = "VARCHAR";       list[2][3] = "VARCHAR";
          list[3][0] = "VARCHAR";        list[3][1] = "VARCHAR";       list[3][2] = "VARCHAR";       list[3][3] = "VARCHAR";
          list[4][0] = "CHARACTER";      list[4][1] = "CHARACTER";     list[4][2] = "VARCHAR";       list[4][3] = "VARCHAR";       list[4][4] = "VARCHAR";
          list[5][0] = "VARCHAR";        list[5][1] = "VARCHAR";       list[5][2] = "VARCHAR";       list[5][3] = "VARCHAR";       list[5][4] = "VARCHAR";
          list[6][0] = "GRAPHIC";        list[6][1] = "GRAPHIC";       list[6][2] = "VARGRAPHIC";    list[6][3] = "VARGRAPHIC";
          list[7][0] = "VARGRAPHIC";     list[7][1] = "VARGRAPHIC";    list[7][2] = "VARGRAPHIC";    list[7][3] = "VARGRAPHIC";
          list[8][0] = "GRAPHIC";        list[8][1] = "GRAPHIC";       list[8][2] = "VARGRAPHIC";    list[8][3] = "VARGRAPHIC";    list[8][4] = "VARGRAPHIC";
          list[9][0] = "VARGRAPHIC";     list[9][1] = "VARGRAPHIC";    list[9][2] = "VARGRAPHIC";    list[9][3] = "VARGRAPHIC";    list[9][4] = "VARGRAPHIC";
          break;
        // ==========> ROUND, TRUNCATE, TRUNC
        case FUNC150:
          list = new Object[3][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "DOUBLE";        list[0][2] = "INTEGER";
          list[1][0] = "INTEGER";        list[1][1] = "INTEGER";        list[1][2] = "INTEGER";
          list[2][0] = "BIGINT";         list[2][1] = "BIGINT";        list[2][2] = "INTEGER";
          break;
        // ==========> DAYNAME, MONTHNAME
        case FUNC160:
          list = new Object[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "DATE";
          list[1][0] = "VARCHAR";        list[1][1] = "VARCHAR";
          list[2][0] = "VARCHAR";        list[2][1] = "TIMESTAMP";
          break;
        // ==========> TIMESTAMP_ISO
        case FUNC170:
          list = new Object[4][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "TIMESTAMP";      list[0][1] = "DATE";
          list[1][0] = "TIMESTAMP";      list[1][1] = "TIME";
          list[2][0] = "TIMESTAMP";      list[2][1] = "TIMESTAMP";
          list[3][0] = "TIMESTAMP";      list[3][1] = "VARCHAR";
          break;
        // ==========> DAYOFWEEK,
        // ==========> DAYOFWEEK_ISO, (added (lee457))
        // ==========> DAYOFYEAR,
        // ==========> DAYS, (added (lee457))
        // ==========> JULIAN_DAY, QUARTER, WEEK,
        // ==========> WEEK_ISO, (added (lee457))
        case FUNC180:
          list = new Object[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "DATE";
          list[1][0] = "INTEGER";        list[1][1] = "VARCHAR";
          list[2][0] = "INTEGER";        list[2][1] = "TIMESTAMP";
          break;
        // ==========> TIMESTAMPDIFF
        case FUNC190:
          list = new Object[1][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "INTEGER";       list[0][2] = "CHARACTER";
          break;
        // ==========> ASCII
        case FUNC200:
          list = new Object[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "CHARACTER";
          list[1][0] = "INTEGER";        list[1][1] = "VARCHAR";
          list[2][0] = "INTEGER";        list[2][1] = "CLOB";
          break;
        // ==========> CHR
        case FUNC210:
          list = new Object[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CHARACTER";      list[0][1] = "INTEGER";
          break;
        // ==========> INSERT
        case FUNC220:
          list = new Object[3][5];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "VARCHAR";       list[0][2] = "INTEGER";       list[0][3] = "INTEGER";       list[0][4] = "VARCHAR";
          list[1][0] = "CLOB";           list[1][1] = "CLOB";          list[1][2] = "INTEGER";       list[1][3] = "INTEGER";       list[1][4] = "CLOB";
          list[2][0] = "BLOB";           list[2][1] = "BLOB";          list[2][2] = "INTEGER";       list[2][3] = "INTEGER";       list[2][4] = "BLOB";
          break;
        // ==========> LTRIM, RTRIM, TRIM
        case FUNC230:
          list = new Object[5][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CLOB";         list[0][1] = "CLOB";
          list[1][0] = "VARCHAR";      list[1][1] = "VARCHAR";
          list[2][0] = "VARCHAR";      list[2][1] = "CHARACTER";
          list[3][0] = "VARGRAPHIC";   list[3][1] = "GRAPHIC";
          list[4][0] = "VARGRAPHIC";   list[4][1] = "VARGRAPHIC";
          break;
        // ==========> LCASE  (added (lee457))
        case FUNC240:
          list = new Object[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CLOB";         list[0][1] = "CLOB";
          list[1][0] = "VARCHAR";      list[1][1] = "VARCHAR";
          list[2][0] = "CHARACTER";    list[2][1] = "CHARACTER";
          break;
        // ==========> LOWER  (added (lee457))
        case FUNC250:
          list = new Object[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CLOB";         list[0][1] = "CLOB";
          list[1][0] = "VARCHAR";      list[1][1] = "VARCHAR";
          list[2][0] = "CHARACTER";    list[2][1] = "CHARACTER";
          break;
        // ==========> LOCATE     (How does this work? (lee457))
        case FUNC260:
          list = new Object[6][];

          list[0] = new Object[4];
          list[1] = new Object[4];
          list[2] = new Object[4];
          list[3] = new Object[3];
          list[4] = new Object[3];
          list[5] = new Object[3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";          list[0][1] = "VARCHAR";       list[0][2] = "VARCHAR";       list[0][3] = "INTEGER";
          list[1][0] = "INTEGER";        list[1][1] = "CLOB";          list[1][2] = "CLOB";          list[1][3] = "INTEGER";
          list[2][0] = "INTEGER";        list[2][1] = "BLOB";          list[2][2] = "BLOB";          list[2][3] = "INTEGER";
          list[3][0] = "INTEGER";        list[3][1] = "VARCHAR";       list[3][2] = "VARCHAR";
          list[4][0] = "INTEGER";        list[4][1] = "CLOB";          list[4][2] = "CLOB";
          list[5][0] = "INTEGER";        list[5][1] = "BLOB";          list[5][2] = "BLOB";
          break;
        // ==========> SOUNDEX
        case FUNC270:
          list = new Object[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CHARACTER";      list[0][1] = "VARCHAR";
          break;
        // ==========> DIFFERENCE
        case FUNC280:
          list = new Object[1][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "VARCHAR";       list[0][2] = "VARCHAR";
          break;
        // ==========> REPEAT, RIGHT, LEFT
        case FUNC290:
          list = new Object[3][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "VARCHAR";       list[0][2] = "INTEGER";
          list[1][0] = "CLOB";           list[1][1] = "CLOB";          list[1][2] = "INTEGER";
          list[2][0] = "BLOB";           list[2][1] = "BLOB";          list[2][2] = "INTEGER";
          break;
        // ==========> SPACE
        case FUNC300:
          list = new Object[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "INTEGER";
          break;
        // ==========> REPLACE
        case FUNC310:
          list = new Object[3][4];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "VARCHAR";       list[0][2] = "VARCHAR";       list[0][3] = "VARCHAR";
          list[1][0] = "CLOB";           list[1][1] = "CLOB";          list[1][2] = "CLOB";          list[1][3] = "CLOB";
          list[2][0] = "BLOB";           list[2][1] = "BLOB";          list[2][2] = "BLOB";          list[2][3] = "BLOB";
          break;
        // ==========> CHAR   (added (lee457))
        case FUNC320:
          list = new Object[32][2];

          list[0] = new Object[2];
          list[1] = new Object[2];
          list[2] = new Object[2];
          list[3] = new Object[2];

          list[4] = new Object[3];
          list[5] = new Object[3];
          list[6] = new Object[3];
          list[7] = new Object[3];

          list[8] = new Object[2];
          list[9] = new Object[2];
          list[10] = new Object[2];

          list[11] = new Object[3];
          list[12] = new Object[3];
          list[13] = new Object[3];
          list[14] = new Object[3];
          list[15] = new Object[3];
          list[16] = new Object[3];
          list[17] = new Object[3];
          list[18] = new Object[3];
          list[19] = new Object[3];
          list[20] = new Object[3];
          list[21] = new Object[3];
          list[22] = new Object[3];
          list[23] = new Object[3];
          list[24] = new Object[3];
          list[25] = new Object[3];

          list[26] = new Object[2];
          list[27] = new Object[2];
          list[28] = new Object[2];
          list[29] = new Object[2];

          list[30] = new Object[3];

          list[31] = new Object[2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CHARACTER";      list[0][1] = "CHARACTER";
          list[1][0] = "CHARACTER";      list[1][1] = "VARCHAR";
          list[2][0] = "CHARACTER";      list[2][1] = "LONG VARCHAR";
          list[3][0] = "CHARACTER";      list[3][1] = "CLOB";

          list[4][0] = "CHARACTER";      list[4][1] = "CHARACTER";     list[4][2] = "INTEGER";
          list[5][0] = "CHARACTER";      list[5][1] = "VARCHAR";       list[5][2] = "INTEGER";
          list[6][0] = "CHARACTER";      list[6][1] = "LONG VARCHAR";  list[6][2] = "INTEGER";
          list[7][0] = "CHARACTER";      list[7][1] = "CLOB";          list[7][2] = "INTEGER";

          list[8][0] = "CHARACTER";      list[8][1] = "DATE";
          list[9][0] = "CHARACTER";      list[9][1] = "TIME";
          list[10][0] = "CHARACTER";     list[10][1] = "TIMESTAMP";

          list[11][0] = "CHARACTER";     list[11][1] = "DATE";         list[11][2] = "ISO";
          list[12][0] = "CHARACTER";     list[12][1] = "DATE";         list[12][2] = "USA";
          list[13][0] = "CHARACTER";     list[13][1] = "DATE";         list[13][2] = "EUR";
          list[14][0] = "CHARACTER";     list[14][1] = "DATE";         list[14][2] = "JIS";
          list[15][0] = "CHARACTER";     list[15][1] = "DATE";         list[15][2] = "LOCAL";
          list[16][0] = "CHARACTER";     list[16][1] = "TIME";         list[16][2] = "ISO";
          list[17][0] = "CHARACTER";     list[17][1] = "TIME";         list[17][2] = "USA";
          list[18][0] = "CHARACTER";     list[18][1] = "TIME";         list[18][2] = "EUR";
          list[19][0] = "CHARACTER";     list[19][1] = "TIME";         list[19][2] = "JIS";
          list[20][0] = "CHARACTER";     list[20][1] = "TIME";         list[20][2] = "LOCAL";
          list[21][0] = "CHARACTER";     list[21][1] = "TIMESTAMP";    list[21][2] = "ISO";
          list[22][0] = "CHARACTER";     list[22][1] = "TIMESTAMP";    list[22][2] = "USA";
          list[23][0] = "CHARACTER";     list[23][1] = "TIMESTAMP";    list[23][2] = "EUR";
          list[24][0] = "CHARACTER";     list[24][1] = "TIMESTAMP";    list[24][2] = "JIS";
          list[25][0] = "CHARACTER";     list[25][1] = "TIMESTAMP";    list[25][2] = "LOCAL";

          list[26][0] = "CHARACTER";     list[26][1] = "SMALLINT";
          list[27][0] = "CHARACTER";     list[27][1] = "INTEGER";
          list[28][0] = "CHARACTER";     list[28][1] = "BIGINT";
          list[29][0] = "CHARACTER";     list[29][1] = "DECIMAL";

          list[30][0] = "CHARACTER";     list[30][1] = "DECIMAL";      list[30][2] = "VARCHAR";

          list[31][0] = "CHARACTER";     list[31][1] = "DOUBLE";
          break;
        // ==========> DOUBLE  (added (lee457))
        case FUNC330:
          list = new Object[7][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "SMALLINT";
          list[1][0] = "DOUBLE";         list[1][1] = "INTEGER";
          list[2][0] = "DOUBLE";         list[2][1] = "BIGINT";
          list[3][0] = "DOUBLE";         list[3][1] = "DECIMAL";
          list[4][0] = "DOUBLE";         list[4][1] = "REAL";
          list[5][0] = "DOUBLE";         list[5][1] = "DOUBLE";
          list[6][0] = "DOUBLE";         list[6][1] = "VARCHAR";
          break;
        // ==========> DOUBLE_PRECISION  (added (lee457))
        // ==========> FLOAT  (added (lee457))
        case FUNC340:
          list = new Object[6][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "SMALLINT";
          list[1][0] = "DOUBLE";         list[1][1] = "INTEGER";
          list[2][0] = "DOUBLE";         list[2][1] = "BIGINT";
          list[3][0] = "DOUBLE";         list[3][1] = "DECIMAL";
          list[4][0] = "DOUBLE";         list[4][1] = "REAL";
          list[5][0] = "DOUBLE";         list[5][1] = "DOUBLE";
          break;
        // ==========> DIGITS (added lee457)
        case FUNC350:
          list = new Object[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CHARACTER";      list[0][1] = "DECIMAL";
          break;
        // ==========> EVENT_MON_STATE (added lee457)
        case FUNC360:
          list = new Object[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "VARCHAR";
          break;
        // ==========> UCASE (added lee457)
        // ==========> UPPER (added lee457)
        case FUNC370:
          list = new Object[2][2];

          //Function Returns               Function Input Parm Type
          list[0][0] = "VARCHAR";         list[0][1] = "VARCHAR";
          list[1][0] = "CHARACTER";       list[1][1] = "CHARACTER";
          break;
        // ==========> DLCOMMENT, (added lee457)
        // ==========> DLLINKTYPE, (added lee457)
        // ==========> DLURLCOMPLETE, (added lee457)
        // ==========> DLURLPATH, (added lee457)
        // ==========> DLURLPATHONLY, (added lee457)
        // ==========> DLURLSCHEME, (added lee457)
        // ==========> DLURLSERVER, (added lee457)
        case FUNC380:
          list = new Object[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "DATALINK";
          break;
        // ==========> MICROSECOND  (added (lee457))
        case FUNC390:
          list = new Object[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "VARCHAR";
          list[1][0] = "INTEGER";        list[1][1] = "TIMESTAMP";
          list[2][0] = "INTEGER";        list[2][1] = "DECIMAL";
          break;
        // ==========> TIME  (added (lee457))
        case FUNC400:
          list = new Object[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "TIME";           list[0][1] = "TIME";
          list[1][0] = "TIME";           list[1][1] = "TIMESTAMP";
          list[2][0] = "TIME";           list[2][1] = "VARCHAR";
          break;
        // ==========> MIDNIGHT_SECONDS
        case FUNC410:
          list = new Object[3][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "TIME";
          list[1][0] = "INTEGER";        list[1][1] = "TIMESTAMP";
          list[2][0] = "INTEGER";        list[2][1] = "VARCHAR";
          break;
        // ==========> AVG  (added (lee457))
        case FUNC420:
          list = new Object[6][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "SMALLINT";
          list[1][0] = "*";              list[1][1] = "INTEGER";   // various
          list[2][0] = "*";              list[2][1] = "BIGINT";    // various
          list[3][0] = "*";              list[3][1] = "DECIMAL";   // various
          list[4][0] = "DOUBLE";         list[4][1] = "REAL";
          list[5][0] = "*";              list[5][1] = "DOUBLE";    // various
          break;
        // ==========> BIGINT  (added (lee457))
        case FUNC430:
          list = new Object[7][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "BIGINT";         list[0][1] = "SMALLINT";
          list[1][0] = "BIGINT";         list[1][1] = "INTEGER";
          list[2][0] = "BIGINT";         list[2][1] = "BIGINT";
          list[3][0] = "BIGINT";         list[3][1] = "DECIMAL";
          list[4][0] = "BIGINT";         list[4][1] = "REAL";
          list[5][0] = "BIGINT";         list[5][1] = "DOUBLE";
          list[6][0] = "BIGINT";         list[6][1] = "VARCHAR";
          break;
        // ==========> INT  (added (lee457))
        // ==========> INTEGER  (added (lee457))
        case FUNC440:
          list = new Object[7][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "SMALLINT";
          list[1][0] = "INTEGER";        list[1][1] = "INTEGER";
          list[2][0] = "INTEGER";        list[2][1] = "BIGINT";
          list[3][0] = "INTEGER";        list[3][1] = "DECIMAL";
          list[4][0] = "INTEGER";        list[4][1] = "REAL";
          list[5][0] = "INTEGER";        list[5][1] = "DOUBLE";
          list[6][0] = "INTEGER";        list[6][1] = "VARCHAR";
          break;
        // ==========> SMALLINT  (added (lee457))
        case FUNC450:
          list = new Object[7][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "SMALLINT";       list[0][1] = "SMALLINT";
          list[1][0] = "SMALLINT";       list[1][1] = "INTEGER";
          list[2][0] = "SMALLINT";       list[2][1] = "BIGINT";
          list[3][0] = "SMALLINT";       list[3][1] = "DECIMAL";
          list[4][0] = "SMALLINT";       list[4][1] = "REAL";
          list[5][0] = "SMALLINT";       list[5][1] = "DOUBLE";
          list[6][0] = "SMALLINT";       list[6][1] = "VARCHAR";
          break;
        // ==========> SUM  (added (lee457))
        case FUNC460:
          list = new Object[6][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "SMALLINT";
          list[1][0] = "INTEGER";        list[1][1] = "INTEGER";
          list[2][0] = "BIGINT";         list[2][1] = "BIGINT";
          list[3][0] = "DECIMAL";        list[3][1] = "DECIMAL";
          list[4][0] = "DOUBLE";         list[4][1] = "REAL";
          list[5][0] = "DOUBLE";         list[5][1] = "DOUBLE";
          break;
        // ==========> REAL  (added (lee457))
        case FUNC470:
          list = new Object[6][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "REAL";           list[0][1] = "SMALLINT";
          list[1][0] = "REAL";           list[1][1] = "INTEGER";
          list[2][0] = "REAL";           list[2][1] = "BIGINT";
          list[3][0] = "REAL";           list[3][1] = "DECIMAL";
          list[4][0] = "REAL";           list[4][1] = "REAL";
          list[5][0] = "REAL";           list[5][1] = "DOUBLE";
          break;
        // ==========> NODENUMBER  (added (lee457))
        // ==========> PARTITION  (added (lee457))
        case FUNC480:
          list = new Object[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "*";   // any
          break;
        // ==========> GROUPING  (added (lee457))
        case FUNC490:
          list = new Object[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "SMALLINT";       list[0][1] = "*";   // any
          break;
        // ==========> COUNT_BIG  (added (lee457))
        case FUNC500:
          list = new Object[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DECIMAL";        list[0][1] = "*";  // various
          break;
        // ==========> HEX  (added (lee457))
        // ==========> TYPE_NAME  (added (lee457))
        // ==========> TYPE_SCHEMA  (added (lee457))
        case FUNC510:
          list = new Object[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "*";  // various
          break;
        // ==========> LENGTH  (added (lee457))
        // ==========> COUNT  (added (lee457))
        // ==========> TYPE_ID  (added (lee457))
        case FUNC520:
          list = new Object[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "*";  // various
          break;
        // ==========> MAX  (added (lee457))
        // ==========> MIN  (added (lee457))
        case FUNC530:
          list = new Object[1][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "*";              list[0][1] = "*";  // various, various
          break;
        // ==========> NULLIF  (added (lee457))
        case FUNC540:
          list = new Object[1][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "*";              list[0][1] = "*";             list[0][2] = "*";  // various, various, various
          break;
        // ==========> CONCAT  (added (lee457))
        case FUNC550:
          list = new Object[9][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "*";              list[0][1] = "CHARACTER";     list[0][2] = "*";  // all "*" are various in this section
          list[1][0] = "*";              list[1][1] = "VARCHAR";       list[1][2] = "*";
          list[2][0] = "*";              list[2][1] = "LONG VARCHAR";  list[2][2] = "*";
          list[3][0] = "*";              list[3][1] = "CLOB";          list[3][2] = "*";
          list[4][0] = "*";              list[4][1] = "GRAPHIC";       list[4][2] = "*";
          list[5][0] = "*";              list[5][1] = "VARGRAPHIC";    list[5][2] = "*";
          list[6][0] = "*";              list[6][1] = "LONG VARGRAPHIC"; list[6][2] = "*";
          list[7][0] = "*";              list[7][1] = "DBCLOB";        list[7][2] = "*";
          list[8][0] = "BLOB";           list[8][1] = "BLOB";          list[8][2] = "*";
          break;
        // ==========> RAISE_ERROR   (added (lee457))
        case FUNC560:
          list = new Object[1][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "*";              list[0][1] = "VARCHAR";       list[0][2] = "VARCHAR";
          break;
        // ==========> POSSTR  (added (lee457))
        case FUNC570:
          list = new Object[9][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "CHARACTER";     list[0][2] = "*";  // all "*" are various in this section
          list[1][0] = "INTEGER";        list[1][1] = "VARCHAR";       list[1][2] = "*";
          list[2][0] = "INTEGER";        list[2][1] = "LONG VARCHAR";  list[2][2] = "*";
          list[3][0] = "INTEGER";        list[3][1] = "CLOB";          list[3][2] = "*";
          list[4][0] = "INTEGER";        list[4][1] = "GRAPHIC";       list[4][2] = "*";
          list[5][0] = "INTEGER";        list[5][1] = "VARGRAPHIC";    list[5][2] = "*";
          list[6][0] = "INTEGER";        list[6][1] = "LONG VARGRAPHIC"; list[6][2] = "*";
          list[7][0] = "INTEGER";        list[7][1] = "DBCLOB";        list[7][2] = "*";
          list[8][0] = "INTEGER";        list[8][1] = "BLOB";          list[8][2] = "*";
          break;
        // ==========> LONG_VARCHAR  (added (lee457))
        case FUNC580:
          list = new Object[4][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "LONG VARCHAR";   list[0][1] = "CHARACTER";
          list[1][0] = "LONG VARCHAR";   list[1][1] = "VARCHAR";
          list[2][0] = "LONG VARCHAR";   list[2][1] = "LONG VARCHAR";
          list[3][0] = "LONG VARCHAR";   list[3][1] = "CLOB";
          break;
        // ==========> LONG_VARGRAPHIC  (added (lee457))
        case FUNC590:
          list = new Object[4][2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "LONG VARGRAPHIC";  list[0][1] = "GRAPHIC";
          list[1][0] = "LONG VARGRAPHIC";  list[1][1] = "VARGRAPHIC";
          list[2][0] = "LONG VARGRAPHIC";  list[2][1] = "LONG VARGRAPHIC";
          list[3][0] = "LONG VARGRAPHIC";  list[3][1] = "DBCLOB";
          break;
        // ==========> COALESCE  (added (lee457))
        // ==========> VALUE  (added (lee457))
        case FUNC600:
          list = new Object[1][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "*";              list[0][1] = "*";             list[0][2] = "*";
          break;
        // ==========> SUBSTR  (added (lee457))
        case FUNC610:
          list = new Object[18][];

          list[0] = new Object[3];
          list[1] = new Object[3];
          list[2] = new Object[3];
          list[3] = new Object[3];
          list[4] = new Object[3];
          list[5] = new Object[3];
          list[6] = new Object[3];
          list[7] = new Object[3];
          list[8] = new Object[3];

          list[9] = new Object[4];
          list[10] = new Object[4];
          list[11] = new Object[4];
          list[12] = new Object[4];
          list[13] = new Object[4];
          list[14] = new Object[4];
          list[15] = new Object[4];
          list[16] = new Object[4];
          list[17] = new Object[4];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CHARACTER";      list[0][1] = "CHARACTER";     list[0][2] = "INTEGER";
          list[1][0] = "VARCHAR";        list[1][1] = "VARCHAR";       list[1][2] = "INTEGER";
          list[2][0] = "LONG VARCHAR";   list[2][1] = "LONG VARCHAR";  list[2][2] = "INTEGER";
          list[3][0] = "CLOB";           list[3][1] = "CLOB";          list[3][2] = "INTEGER";
          list[4][0] = "GRAPHIC";        list[4][1] = "GRAPHIC";       list[4][2] = "INTEGER";
          list[5][0] = "VARGRAPHIC";     list[5][1] = "VARGRAPHIC";    list[5][2] = "INTEGER";
          list[6][0] = "LONG VARGRAPHIC";  list[6][1] = "LONG VARGRAPHIC";  list[6][2] = "INTEGER";
          list[7][0] = "DBCLOB";         list[7][1] = "DBCLOB";        list[7][2] = "INTEGER";
          list[8][0] = "BLOB";          list[8][1] = "BLOB";            list[8][2] = "INTEGER";

          list[9][0] =  "CHARACTER";     list[9][1] = "CHARACTER";     list[9][2] = "INTEGER";       list[9][3] = "INTEGER";
          list[10][0] = "VARCHAR";       list[10][1] = "VARCHAR";      list[10][2] = "INTEGER";      list[10][3] = "INTEGER";
          list[11][0] = "LONG VARCHAR";  list[11][1] = "LONG VARCHAR";  list[11][2] = "INTEGER";      list[11][3] = "INTEGER";
          list[12][0] = "CLOB";          list[12][1] = "CLOB";         list[12][2] = "INTEGER";      list[12][3] = "INTEGER";
          list[13][0] = "GRAPHIC";       list[13][1] = "GRAPHIC";      list[13][2] = "INTEGER";      list[13][3] = "INTEGER";
          list[14][0] = "VARGRAPHIC";    list[14][1] = "VARGRAPHIC";   list[14][2] = "INTEGER";      list[14][3] = "INTEGER";
          list[15][0] = "LONG VARGRAPHIC";  list[15][1] = "LONG VARGRAPHIC";  list[15][2] = "INTEGER";  list[15][3] = "INTEGER";
          list[16][0] = "DBCLOB";        list[16][1] = "DBCLOB";       list[16][2] = "INTEGER";      list[16][3] = "INTEGER";
          list[17][0] = "BLOB";             list[17][1] = "BLOB";         list[17][2] = "INTEGER";      list[17][3] = "INTEGER";
          break;
        // ==========> BLOB  (added (lee457))
        case FUNC620:
          list = new Object[18][];

          list[0] = new Object[2];
          list[1] = new Object[2];
          list[2] = new Object[2];
          list[3] = new Object[2];
          list[4] = new Object[2];
          list[5] = new Object[2];
          list[6] = new Object[2];
          list[7] = new Object[2];
          list[8] = new Object[2];

          list[9] = new Object[3];
          list[10] = new Object[3];
          list[11] = new Object[3];
          list[12] = new Object[3];
          list[13] = new Object[3];
          list[14] = new Object[3];
          list[15] = new Object[3];
          list[16] = new Object[3];
          list[17] = new Object[3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "BLOB";           list[0][1] = "CHARACTER";
          list[1][0] = "BLOB";           list[1][1] = "VARCHAR";
          list[2][0] = "BLOB";           list[2][1] = "LONG VARCHAR";
          list[3][0] = "BLOB";           list[3][1] = "CLOB";
          list[4][0] = "BLOB";           list[4][1] = "GRAPHIC";
          list[5][0] = "BLOB";           list[5][1] = "VARGRAPHIC";
          list[6][0] = "BLOB";           list[6][1] = "LONG VARGRAPHIC";
          list[7][0] = "BLOB";           list[7][1] = "DBCLOB";
          list[8][0] = "BLOB";           list[8][1] = "BLOB";

          list[9][0] = "BLOB";           list[9][1] = "CHARACTER";     list[9][2] = "INTEGER";
          list[10][0] = "BLOB";          list[10][1] = "VARCHAR";      list[10][2] = "INTEGER";
          list[11][0] = "BLOB";          list[11][1] = "LONG VARCHAR"; list[11][2] = "INTEGER";
          list[12][0] = "BLOB";          list[12][1] = "CLOB";         list[12][2] = "INTEGER";
          list[13][0] = "BLOB";          list[13][1] = "GRAPHIC";      list[13][2] = "INTEGER";
          list[14][0] = "BLOB";          list[14][1] = "VARGRAPHIC";   list[14][2] = "INTEGER";
          list[15][0] = "BLOB";          list[15][1] = "LONG VARGRAPHIC";  list[15][2] = "INTEGER";
          list[16][0] = "BLOB";          list[16][1] = "DBCLOB";       list[16][2] = "INTEGER";
          list[17][0] = "BLOB";          list[17][1] = "BLOB";         list[17][2] = "INTEGER";
          break;
        // ==========> CLOB  (added (lee457))
        case FUNC630:
          list = new Object[8][];

          list[0] = new Object[2];
          list[1] = new Object[2];
          list[2] = new Object[2];
          list[3] = new Object[2];

          list[4] = new Object[3];
          list[5] = new Object[3];
          list[6] = new Object[3];
          list[7] = new Object[3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "CLOB";           list[0][1] = "CHARACTER";
          list[1][0] = "CLOB";           list[1][1] = "VARCHAR";
          list[2][0] = "CLOB";           list[2][1] = "LONG VARCHAR";
          list[3][0] = "CLOB";           list[3][1] = "CLOB";
          list[4][0] = "CLOB";           list[4][1] = "CHARACTER";     list[4][2] = "INTEGER";
          list[5][0] = "CLOB";           list[5][1] = "VARCHAR";       list[5][2] = "INTEGER";
          list[6][0] = "CLOB";           list[6][1] = "LONG VARCHAR";  list[6][2] = "INTEGER";
          list[7][0] = "CLOB";           list[7][1] = "CLOB";          list[7][2] = "INTEGER";
          break;
        // ==========> CORR  (added (lee457))
        // ==========> CORRELATION  (added (lee457))
         // ==========> COVAR  (added (lee457))
         // ==========> COVARIANCE  (added (lee457))
         // ==========> REGR_AVGX  (added (lee457))
         // ==========> REGR_AVGY  (added (lee457))
         // ==========> REGR_ICPT  (added (lee457))
         // ==========> REGR_INTERCEPT  (added (lee457))
         // ==========> REGR_R2  (added (lee457))
         // ==========> REGR_SLOPE  (added (lee457))
         // ==========> REGR_SXX  (added (lee457))
         // ==========> REGR_SXY  (added (lee457))
         // ==========> REGR_SYY  (added (lee457))
        case FUNC640:
          list = new Object[36][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DOUBLE";         list[0][1] = "SMALLINT";      list[0][2] = "SMALLINT";
          list[1][0] = "DOUBLE";         list[1][1] = "SMALLINT";      list[1][2] = "INTEGER";
          list[2][0] = "DOUBLE";         list[2][1] = "SMALLINT";      list[2][2] = "BIGINT";
          list[3][0] = "DOUBLE";         list[3][1] = "SMALLINT";      list[3][2] = "DECIMAL";
          list[4][0] = "DOUBLE";         list[4][1] = "SMALLINT";      list[4][2] = "REAL";
          list[5][0] = "DOUBLE";         list[5][1] = "SMALLINT";      list[5][2] = "DOUBLE";

          list[6][0] = "DOUBLE";         list[6][1] = "INTEGER";       list[6][2] = "SMALLINT";
          list[7][0] = "DOUBLE";         list[7][1] = "INTEGER";       list[7][2] = "INTEGER";
          list[8][0] = "DOUBLE";         list[8][1] = "INTEGER";       list[8][2] = "BIGINT";
          list[9][0] = "DOUBLE";         list[9][1] = "INTEGER";       list[9][2] = "DECIMAL";
          list[10][0] = "DOUBLE";        list[10][1] = "INTEGER";      list[10][2] = "REAL";
          list[11][0] = "DOUBLE";        list[11][1] = "INTEGER";      list[11][2] = "DOUBLE";

          list[12][0] = "DOUBLE";        list[12][1] = "BIGINT";       list[12][2] = "SMALLINT";
          list[13][0] = "DOUBLE";        list[13][1] = "BIGINT";       list[13][2] = "INTEGER";
          list[14][0] = "DOUBLE";        list[14][1] = "BIGINT";       list[14][2] = "BIGINT";
          list[15][0] = "DOUBLE";        list[15][1] = "BIGINT";       list[15][2] = "DECIMAL";
          list[16][0] = "DOUBLE";        list[16][1] = "BIGINT";       list[16][2] = "REAL";
          list[17][0] = "DOUBLE";        list[17][1] = "BIGINT";       list[17][2] = "DOUBLE";

          list[18][0] = "DOUBLE";        list[18][1] = "DECIMAL";      list[18][2] = "SMALLINT";
          list[19][0] = "DOUBLE";        list[19][1] = "DECIMAL";      list[19][2] = "INTEGER";
          list[20][0] = "DOUBLE";        list[20][1] = "DECIMAL";      list[20][2] = "BIGINT";
          list[21][0] = "DOUBLE";        list[21][1] = "DECIMAL";      list[21][2] = "DECIMAL";
          list[22][0] = "DOUBLE";        list[22][1] = "DECIMAL";      list[22][2] = "REAL";
          list[23][0] = "DOUBLE";        list[23][1] = "DECIMAL";      list[23][2] = "DOUBLE";

          list[24][0] = "DOUBLE";        list[24][1] = "REAL";         list[24][2] = "SMALLINT";
          list[25][0] = "DOUBLE";        list[25][1] = "REAL";         list[25][2] = "INTEGER";
          list[26][0] = "DOUBLE";        list[26][1] = "REAL";         list[26][2] = "BIGINT";
          list[27][0] = "DOUBLE";        list[27][1] = "REAL";         list[27][2] = "DECIMAL";
          list[28][0] = "DOUBLE";        list[28][1] = "REAL";         list[28][2] = "REAL";
          list[29][0] = "DOUBLE";        list[29][1] = "REAL";         list[29][2] = "DOUBLE";

          list[30][0] = "DOUBLE";        list[30][1] = "DOUBLE";       list[30][2] = "SMALLINT";
          list[31][0] = "DOUBLE";        list[31][1] = "DOUBLE";       list[31][2] = "INTEGER";
          list[32][0] = "DOUBLE";        list[32][1] = "DOUBLE";       list[32][2] = "BIGINT";
          list[33][0] = "DOUBLE";        list[33][1] = "DOUBLE";       list[33][2] = "DECIMAL";
          list[34][0] = "DOUBLE";        list[34][1] = "DOUBLE";       list[34][2] = "REAL";
          list[35][0] = "DOUBLE";        list[35][1] = "DOUBLE";       list[35][2] = "DOUBLE";
          break;
        // ==========> REGR_COUNT  (added (lee457))
        case FUNC650:
          list = new Object[36][3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "INTEGER";        list[0][1] = "SMALLINT";      list[0][2] = "SMALLINT";
          list[1][0] = "INTEGER";        list[1][1] = "SMALLINT";      list[1][2] = "INTEGER";
          list[2][0] = "INTEGER";        list[2][1] = "SMALLINT";      list[2][2] = "BIGINT";
          list[3][0] = "INTEGER";        list[3][1] = "SMALLINT";      list[3][2] = "DECIMAL";
          list[4][0] = "INTEGER";        list[4][1] = "SMALLINT";      list[4][2] = "REAL";
          list[5][0] = "INTEGER";        list[5][1] = "SMALLINT";      list[5][2] = "DOUBLE";

          list[6][0] = "INTEGER";        list[6][1] = "INTEGER";       list[6][2] = "SMALLINT";
          list[7][0] = "INTEGER";        list[7][1] = "INTEGER";       list[7][2] = "INTEGER";
          list[8][0] = "INTEGER";        list[8][1] = "INTEGER";       list[8][2] = "BIGINT";
          list[9][0] = "INTEGER";        list[9][1] = "INTEGER";       list[9][2] = "DECIMAL";
          list[10][0] = "INTEGER";       list[10][1] = "INTEGER";      list[10][2] = "REAL";
          list[11][0] = "INTEGER";       list[11][1] = "INTEGER";      list[11][2] = "DOUBLE";

          list[12][0] = "INTEGER";       list[12][1] = "BIGINT";       list[12][2] = "SMALLINT";
          list[13][0] = "INTEGER";       list[13][1] = "BIGINT";       list[13][2] = "INTEGER";
          list[14][0] = "INTEGER";       list[14][1] = "BIGINT";       list[14][2] = "BIGINT";
          list[15][0] = "INTEGER";       list[15][1] = "BIGINT";       list[15][2] = "DECIMAL";
          list[16][0] = "INTEGER";       list[16][1] = "BIGINT";       list[16][2] = "REAL";
          list[17][0] = "INTEGER";       list[17][1] = "BIGINT";       list[17][2] = "DOUBLE";

          list[18][0] = "INTEGER";       list[18][1] = "DECIMAL";      list[18][2] = "SMALLINT";
          list[19][0] = "INTEGER";       list[19][1] = "DECIMAL";      list[19][2] = "INTEGER";
          list[20][0] = "INTEGER";       list[20][1] = "DECIMAL";      list[20][2] = "BIGINT";
          list[21][0] = "INTEGER";       list[21][1] = "DECIMAL";      list[21][2] = "DECIMAL";
          list[22][0] = "INTEGER";       list[22][1] = "DECIMAL";      list[22][2] = "REAL";
          list[23][0] = "INTEGER";       list[23][1] = "DECIMAL";      list[23][2] = "DOUBLE";

          list[24][0] = "INTEGER";       list[24][1] = "REAL";         list[24][2] = "SMALLINT";
          list[25][0] = "INTEGER";       list[25][1] = "REAL";         list[25][2] = "INTEGER";
          list[26][0] = "INTEGER";       list[26][1] = "REAL";         list[26][2] = "BIGINT";
          list[27][0] = "INTEGER";       list[27][1] = "REAL";         list[27][2] = "DECIMAL";
          list[28][0] = "INTEGER";       list[28][1] = "REAL";         list[28][2] = "REAL";
          list[29][0] = "INTEGER";       list[29][1] = "REAL";         list[29][2] = "DOUBLE";

          list[30][0] = "INTEGER";       list[30][1] = "DOUBLE";       list[30][2] = "SMALLINT";
          list[31][0] = "INTEGER";       list[31][1] = "DOUBLE";       list[31][2] = "INTEGER";
          list[32][0] = "INTEGER";       list[32][1] = "DOUBLE";       list[32][2] = "BIGINT";
          list[33][0] = "INTEGER";       list[33][1] = "DOUBLE";       list[33][2] = "DECIMAL";
          list[34][0] = "INTEGER";       list[34][1] = "DOUBLE";       list[34][2] = "REAL";
          list[35][0] = "INTEGER";       list[35][1] = "DOUBLE";       list[35][2] = "DOUBLE";
          break;
        // ==========> DBCLOB  (added (lee457))
        case FUNC660:
          list = new Object[8][];

          list[0] = new Object[2];
          list[1] = new Object[2];
          list[2] = new Object[2];
          list[3] = new Object[2];

          list[4] = new Object[3];
          list[5] = new Object[3];
          list[6] = new Object[3];
          list[7] = new Object[3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "DBCLOB";         list[0][1] = "GRAPHIC";
          list[1][0] = "DBCLOB";         list[1][1] = "VARGRAPHIC";
          list[2][0] = "DBCLOB";         list[2][1] = "LONG VARGRAPHIC";
          list[3][0] = "DBCLOB";         list[3][1] = "DBCLOB";

          list[4][0] = "DBCLOB";         list[4][1] = "GRAPHIC";       list[4][2] = "INTEGER";
          list[5][0] = "DBCLOB";         list[5][1] = "VARGRAPHIC";    list[5][2] = "INTEGER";
          list[6][0] = "DBCLOB";         list[6][1] = "LONG VARGRAPHIC";  list[6][2] = "INTEGER";
          list[7][0] = "DBCLOB";         list[7][1] = "DBCLOB";        list[7][2] = "INTEGER";
          break;
        // ==========> GRAPHIC  (added (lee457))
        case FUNC670:
          list = new Object[8][];

          list[0] = new Object[2];
          list[1] = new Object[2];
          list[2] = new Object[2];
          list[3] = new Object[2];

          list[4] = new Object[3];
          list[5] = new Object[3];
          list[6] = new Object[3];
          list[7] = new Object[3];

          //Function Returns             Function Input Parm Type
          list[0][0] = "GRAPHIC";        list[0][1] = "GRAPHIC";
          list[1][0] = "GRAPHIC";        list[1][1] = "VARGRAPHIC";
          list[2][0] = "GRAPHIC";        list[2][1] = "LONG VARGRAPHIC";
          list[3][0] = "GRAPHIC";        list[3][1] = "DBCLOB";

          list[4][0] = "GRAPHIC";        list[4][1] = "GRAPHIC";       list[4][2] = "INTEGER";
          list[5][0] = "GRAPHIC";        list[5][1] = "VARGRAPHIC";    list[5][2] = "INTEGER";
          list[6][0] = "GRAPHIC";        list[6][1] = "LONG VARGRAPHIC";  list[6][2] = "INTEGER";
          list[7][0] = "GRAPHIC";        list[7][1] = "DBCLOB";        list[7][2] = "INTEGER";
          break;
        // ==========> VARCHAR  (added (lee457))
        case FUNC680:
          list = new Object[11][];

          list[0] = new Object[2];
          list[1] = new Object[2];
          list[2] = new Object[2];
          list[3] = new Object[2];

          list[4] = new Object[3];
          list[5] = new Object[3];
          list[6] = new Object[3];
          list[7] = new Object[3];

          list[8] = new Object[2];
          list[9] = new Object[2];
          list[10] = new Object[2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARCHAR";        list[0][1] = "CHARACTER";
          list[1][0] = "VARCHAR";        list[1][1] = "VARCHAR";
          list[2][0] = "VARCHAR";        list[2][1] = "LONG VARCHAR";
          list[3][0] = "VARCHAR";        list[3][1] = "CLOB";

          list[4][0] = "VARCHAR";        list[4][1] = "CHARACTER";     list[4][2] = "INTEGER";
          list[5][0] = "VARCHAR";        list[5][1] = "VARCHAR";       list[5][2] = "INTEGER";
          list[6][0] = "VARCHAR";        list[6][1] = "LONG VARCHAR";  list[6][2] = "INTEGER";
          list[7][0] = "VARCHAR";        list[7][1] = "CLOB";          list[7][2] = "INTEGER";

          list[8][0] = "VARCHAR";        list[8][1] = "DATE";
          list[9][0] = "VARCHAR";        list[9][1] = "TIME";
          list[10][0] = "VARCHAR";       list[10][1] = "TIMESTAMP";
          break;
        // ==========> VARGRAPHIC  (added (lee457))
        case FUNC690:
          list = new Object[9][];

          list[0] = new Object[2];
          list[1] = new Object[2];
          list[2] = new Object[2];
          list[3] = new Object[2];

          list[4] = new Object[3];
          list[5] = new Object[3];
          list[6] = new Object[3];
          list[7] = new Object[3];

          list[8] = new Object[2];

          //Function Returns             Function Input Parm Type
          list[0][0] = "VARGRAPHIC";     list[0][1] = "GRAPHIC";
          list[1][0] = "VARGRAPHIC";     list[1][1] = "VARGRAPHIC";
          list[2][0] = "VARGRAPHIC";     list[2][1] = "LONG VARGRAPHIC";
          list[3][0] = "VARGRAPHIC";     list[3][1] = "DBCLOB";

          list[4][0] = "VARGRAPHIC";     list[4][1] = "GRAPHIC";       list[4][2] = "INTEGER";
          list[5][0] = "VARGRAPHIC";     list[5][1] = "VARGRAPHIC";    list[5][2] = "INTEGER";
          list[6][0] = "VARGRAPHIC";     list[6][1] = "LONG VARGRAPHIC"; list[6][2] = "INTEGER";
          list[7][0] = "VARGRAPHIC";     list[7][1] = "DBCLOB";        list[7][2] = "INTEGER";

          list[8][0] = "VARGRAPHIC";     list[8][1] = "VARCHAR";
          break;
        // ==========> VEBLOB_CP_SMALL, VEBLOB_CP_LARGE  (Where is this documented? (lee457))
        case FUNC700:
          list = new Object[1][5];

          //Function Returns             Function Input Parm Type
          list[0][0] = "BLOB";           list[0][1] = "BLOB";          list[0][2] = "INTEGER";       list[0][3] = "INTEGER";       list[0][4] = "INTEGER";
          break;
        // ==========> Special Registers
        case FUNC710:
          list = new Object[1][];

          list[0] = new Object[1];

          //Function Returns             Function Input Parm Type
          list[0][0] = "STRING";
          break;
        case FUNC720:
          // "DB2TX.CCSID",
          list = new Object[2][2];
          list[0][0] = "SMALLINT";           list[0][1] = "DB2TEXTFH";
          list[1][0] = "SMALLINT";           list[1][1] = "DB2TEXTH";
          break;
        case FUNC730:
          // "DB2TX.CONTAINS",
          list = new Object[2][3];
          list[0][0] = "INTEGER";            list[0][1] = "DB2TEXTFH";             list[0][2] = "LONG VARCHAR";
          list[1][0] = "INTEGER";            list[1][1] = "DB2TEXTH";              list[1][2] = "LONG VARCHAR";
          break;
        case FUNC740:
          // "DB2TX.FILE",
          list = new Object[2][];
          list[0] = new Object[2];
          list[1] = new Object[3];

          list[0][0] = "DB2TEXTFH";            list[0][1] = "DB2TEXTFH";
          list[1][0] = "DB2TEXTFH";            list[1][1] = "DB2TEXTFH";             list[1][2] = "VARCHAR(150)";
          break;
        case FUNC750:
          // "DB2TX.FORMAT",
          list = new Object[6][];
          list[0] = new Object[2];
          list[1] = new Object[2];
          list[2] = new Object[3];
          list[3] = new Object[3];
          list[4] = new Object[3];
          list[5] = new Object[3];

          list[0][0] = "VARCHAR(30)";          list[0][1] = "DB2TEXTFH";
          list[1][0] = "VARCHAR(30)";          list[1][1] = "DB2TEXTH";
          list[2][0] = "DB2TEXTFH";            list[2][1] = "DB2TEXTFH";             list[2][2] = "VARCHAR(30)";
          list[3][0] = "DB2TEXTH";             list[3][1] = "DB2TEXTFH";             list[3][2] = "VARCHAR(30)";
          list[4][0] = "DB2TEXTFH";            list[4][1] = "DB2TEXTH";              list[4][2] = "VARCHAR(30)";
          list[5][0] = "DB2TEXTH";             list[5][1] = "DB2TEXTH";              list[5][2] = "VARCHAR(30)";
          break;
        case FUNC760:
          // "DB2TX.HANDLE",
          list = new Object[4][3];
          list[0][0] = "DB2TEXTFH";         list[0][1] = "DB2TEXTHLISTP";      list[0][2] = "INTEGER";
          list[1][0] = "DB2TEXTFH";         list[1][1] = "DB2TEXTFHLISTP";     list[1][2] = "INTEGER";
          list[2][0] = "DB2TEXTH";          list[2][1] = "DB2TEXTHLISTP";      list[2][2] = "INTEGER";
          list[3][0] = "DB2TEXTH";          list[3][1] = "DB2TEXTFHLISTP";     list[3][2] = "INTEGER";
          break;
        case FUNC770:
          // "DB2TX.HANDLE_LIST",
          list = new Object[4][3];
          list[0][0] = "DB2TEXTFHLISTP";        list[0][1] = "DB2TEXTH";       list[0][2] = "LONG VARCHAR";
          list[1][0] = "DB2TEXTHLISTP";         list[1][1] = "DB2TEXTH";       list[1][2] = "LONG VARCHAR";
          list[2][0] = "DB2TEXTFHLISTP";        list[2][1] = "DB2TEXTFH";      list[2][2] = "LONG VARCHAR";
          list[3][0] = "DB2TEXTHLISTP";         list[3][1] = "DB2TEXTFH";      list[3][2] = "LONG VARCHAR";
          break;
        case FUNC780:
          // "DB2TX.INIT_TEXT_HANDLE",
          list = new Object[2][];
          list[0] = new Object[3];
          list[1] = new Object[5];

          list[0][0] = "DB2TEXTH";        list[0][1] = "VARCHAR(30)";       list[0][2] = "VARCHAR(30)";
          list[1][0] = "DB2TEXTFH";       list[1][1] = "CCSID";             list[1][2] = "VARCHAR(30)";  list[1][3] = "VARCHAR(30)";  list[1][4] = "VARCHAR(150)";
          break;
        case FUNC790:
          // "DB2TX.LANGUAGE",
          list = new Object[6][];
          list[0] = new Object[2];
          list[1] = new Object[2];
          list[2] = new Object[3];
          list[3] = new Object[3];
          list[4] = new Object[3];
          list[5] = new Object[3];

          list[0][0] = "VARCHAR(30)";        list[0][1] = "DB2TEXTFH";
          list[1][0] = "VARCHAR(30)";        list[1][1] = "DB2TEXTH";
          list[2][0] = "DB2TEXTFH";          list[2][1] = "DB2TEXTH";      list[2][2] = "VARCHAR(30)";
          list[3][0] = "DB2TEXTH";           list[3][1] = "DB2TEXTH";      list[3][2] = "VARCHAR(30)";
          list[4][0] = "DB2TEXTFH";          list[4][1] = "DB2TEXTFH";     list[4][2] = "VARCHAR(30)";
          list[5][0] = "DB2TEXTH";           list[5][1] = "DB2TEXTFH";     list[5][2] = "VARCHAR(30)";
          break;
        case FUNC800:
          // "DB2TX.NO_OF_DOCUMENTS",
          list = new Object[2][2];
          list[0][0] = "INTEGER";         list[0][1] = "DB2TEXTHLISTP";
          list[1][0] = "INTEGER";         list[1][1] = "DB2TEXTFHLISTP";
          break;
        case FUNC810:
          // "DB2TX.NO_OF_MATCHES",
          list = new Object[2][3];
          list[0][0] = "INTEGER";         list[0][1] = "DB2TEXTFH";       list[0][2] = "LONG VARCHAR";
          list[1][0] = "INTEGER";         list[1][1] = "DB2TEXTH";        list[1][2] = "LONG VARCHAR";
          break;
        case FUNC820:
          // "DB2TX.RANK",
          list = new Object[2][3];
          list[0][0] = "DOUBLE";         list[0][1] = "DB2TEXTFH";       list[0][2] = "LONG VARCHAR";
          list[1][0] = "DOUBLE";         list[1][1] = "DB2TEXTH";        list[1][2] = "LONG VARCHAR";
          break;
        case FUNC830:
          // "DB2TX.REFINE",
          list = new Object[1][3];
          list[0][0] = "LONG VARCHAR";         list[0][1] = "LONG VARCHAR";       list[0][2] = "LONG VARCHAR";
          break;
        case FUNC840:
          //"DB2TX.SEARCH_RESULT"
          list = new Object[1][5];
          list[0][0] = "LONG VARCHAR";         list[0][1] = "schema";       list[0][2] = "table";   list[0][3] = "handle";  list[0][4] = "LONG VARCHAR";
          break;
        case FUNC850:
          //"MMDBSYS.AlignValue",
          list = new Object[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle";
          break;
        case FUNC860:
          //"MMDBSYS.AspectRatio",
          list = new Object[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle";
          break;
        case FUNC870:
          //"MMDBSYS.BitsPerSample",
          list = new Object[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle";
          break;
        case FUNC880:
          //"MMDBSYS.BytesPerSec",
          list = new Object[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle";
          break;
        case FUNC890:
          //"MMDBSYS.Comment",
          list = new Object[2][];
          list[0] = new Object[2];
          list[1] = new Object[3];

          list[0][0] = "LONG VARCHAR";      list[0][1] = "handle";
          list[1][0] = "handle";            list[1][1] = "handle";      list[1][2] = "LONG VARCHAR";

          break;
        case FUNC900:
          //"MMDBSYS.CompressType",
          list = new Object[1][2];

          list[0][0] = "VARCHAR(8)";      list[0][1] = "handle";
          break;
        case FUNC910:
          //"MMDBSYS.Content",
          list = new Object[15][];
          list[0] = new Object[2];
          list[1] = new Object[4];
          list[2] = new Object[4];
          list[3] = new Object[3];
          list[4] = new Object[5];
          list[5] = new Object[4];
          list[6] = new Object[6];
          list[7] = new Object[5];
          list[8] = new Object[5];
          list[9] = new Object[6];
          list[10] = new Object[6];
          list[11] = new Object[6];
          list[12] = new Object[6];
          list[13] = new Object[7];
          list[14] = new Object[7];

          list[0][0] = "content";        list[0][1] = "handle";
          list[1][0] = "content";        list[1][1] = "handle";     list[1][2] = "offset";        list[1][3] = "size";
          list[2][0] = "content";        list[2][1] = "handle";     list[2][2] = "target_file";   list[2][3] = "overwrite";
          list[3][0] = "content";        list[3][1] = "handle";     list[3][2] = "target_format";
          list[4][0] = "content";        list[4][1] = "handle";     list[4][2] = "target_file";   list[4][3] = "overwrite";       list[4][4] = "target_format";
          list[5][0] = "content";        list[5][1] = "handle";     list[5][2] = "target_format"; list[5][3] = "conversion_options";
          list[6][0] = "content";        list[6][1] = "handle";     list[6][2] = "target_file";   list[6][3] = "overwrite";       list[6][4] = "target_format";   list[6][5] = "conversion_options";
          list[7][0] = "handle";         list[7][1] = "handle";     list[7][2] = "content";       list[7][3] = "source_format";   list[7][4] = "target_file";
          list[8][0] = "handle";         list[8][1] = "handle";     list[8][2] = "source_file";   list[8][3] = "source_format";   list[8][4] = "stortype";
          list[9][0] = "handle";         list[9][1] = "handle";     list[9][2] = "content";       list[9][3] = "target_file";     list[9][4] = "attrs";           list[9][5] = "thumbnail";
          list[10][0] = "handle";        list[10][1] = "handle";    list[10][2] = "source_file";  list[10][3] = "stortype";       list[10][4] = "attrs";          list[10][5] = "thumbnail";
          list[11][0] = "handle";        list[11][1] = "handle";    list[11][2] = "content";      list[11][3] = "source_format";  list[11][4] = "target_format";  list[11][5] = "target_file";
          list[12][0] = "handle";        list[12][1] = "handle";    list[12][2] = "source_file";  list[12][3] = "source_format";  list[12][4] = "target_format";  list[12][5] = "target_file";
          list[13][0] = "handle";        list[13][1] = "handle";    list[13][2] = "content";      list[13][3] = "source_format";  list[13][4] = "target_format";  list[13][5] = "conversion_options";   list[13][6] = "target_file";
          list[14][0] = "handle";        list[14][1] = "handle";    list[14][2] = "source_file";  list[14][3] = "source_format";  list[14][4] = "target_format";  list[14][5] = "conversion_options";   list[14][6] = "target_file";

          break;
        case FUNC920:
          //"MMDBSYS.DB2Audio",
          list = new Object[4][6];

          list[0][0] = "handle";    list[0][1] = "dbname";     list[0][2] = "content";       list[0][3] = "format";       list[0][4] = "target_file";   list[0][5] = "comment";
          list[1][0] = "handle";    list[1][1] = "dbname";     list[1][2] = "source_file";   list[1][3] = "format";       list[1][4] = "stortype";      list[1][5] = "comment";
          list[2][0] = "handle";    list[2][1] = "dbname";     list[2][2] = "content";       list[2][3] = "target_file";  list[2][4] = "comment";       list[2][5] = "attrs";
          list[3][0] = "handle";    list[3][1] = "dbname";     list[3][2] = "source_file";   list[3][3] = "stortype";     list[3][4] = "comment";       list[3][5] = "attrs";

          break;
        case FUNC930:
          //"MMDBSYS.DB2Image",
          list = new Object[8][];
          list[0] = new Object[6];
          list[1] = new Object[6];
          list[2] = new Object[7];
          list[3] = new Object[7];
          list[4] = new Object[7];
          list[5] = new Object[7];
          list[6] = new Object[8];
          list[7] = new Object[8];

          list[0][0] = "handle";    list[0][1] = "dbname";     list[0][2] = "content";       list[0][3] = "source_format";       list[0][4] = "target_file";   list[0][5] = "comment";
          list[1][0] = "handle";    list[1][1] = "dbname";     list[1][2] = "source_file";   list[1][3] = "source_format";       list[1][4] = "stortype";      list[1][5] = "comment";
          list[2][0] = "handle";    list[2][1] = "dbname";     list[2][2] = "content";       list[2][3] = "target_file";         list[2][4] = "comment";       list[2][5] = "attrs";              list[2][6] = "thumbnail";
          list[3][0] = "handle";    list[3][1] = "dbname";     list[3][2] = "source_file";   list[3][3] = "stortype";            list[3][4] = "comment";       list[3][5] = "attrs";              list[3][6] = "thumbnail";
          list[4][0] = "handle";    list[4][1] = "dbname";     list[4][2] = "content";       list[4][3] = "source_format";       list[4][4] = "target_format"; list[4][5] = "target_file";        list[4][6] = "comment";
          list[5][0] = "handle";    list[5][1] = "dbname";     list[5][2] = "source_file";   list[5][3] = "source_format";       list[5][4] = "target_format"; list[5][5] = "target_file";        list[5][6] = "comment";
          list[6][0] = "handle";    list[6][1] = "dbname";     list[6][2] = "content";       list[6][3] = "source_format";       list[6][4] = "target_format"; list[6][5] = "conversion_options"; list[6][6] = "target_file";  list[6][7] = "comment";
          list[7][0] = "handle";    list[7][1] = "dbname";     list[7][2] = "source_file";   list[7][3] = "source_format";       list[7][4] = "target_format"; list[7][5] = "conversion_options"; list[7][6] = "target_file";  list[7][7] = "comment";

          break;
        case FUNC940:
          //"MMDBSYS.DB2Video",
          list = new Object[4][];
          list[0] = new Object[6];
          list[1] = new Object[6];
          list[2] = new Object[7];
          list[3] = new Object[7];

          list[0][0] = "handle";    list[0][1] = "dbname";     list[0][2] = "content";       list[0][3] = "format";       list[0][4] = "target_file";   list[0][5] = "comment";
          list[1][0] = "handle";    list[1][1] = "dbname";     list[1][2] = "source_file";   list[1][3] = "format";       list[1][4] = "stortype";      list[1][5] = "comment";
          list[2][0] = "handle";    list[2][1] = "dbname";     list[2][2] = "content";       list[2][3] = "target_file";  list[2][4] = "comment";       list[2][5] = "attrs";              list[2][6] = "thumbnail";
          list[3][0] = "handle";    list[3][1] = "dbname";     list[3][2] = "source_file";   list[3][3] = "stortype";     list[3][4] = "comment";       list[3][5] = "attrs";              list[3][6] = "thumbnail";

          break;
        case FUNC950:
          //"MMDBSYS.Duration",
          list = new Object[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle";
          break;
        case FUNC960:
          //"MMDBSYS.Filename",
          list = new Object[1][2];

          list[0][0] = "VARCHAR(250)";      list[0][1] = "handle";
          break;
        case FUNC970:
          //"MMDBSYS.FindInstrument",
          list = new Object[1][3];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2AUDIO)";    list[0][2] = "instrument(VARCHAR(255))";
          break;
        case FUNC980:
          //"MMDBSYS.FindTrackName",
          list = new Object[1][3];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2AUDIO)";    list[0][2] = "trackname(VARCHAR(255))";
          break;
        case FUNC990:
          //"MMDBSYS.Format",
          list = new Object[1][2];

          list[0][0] = "VARCHAR(8)";      list[0][1] = "handle";
          break;
        case FUNC1000:
          //"MMDBSYS.FrameRate",
          list = new Object[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2VIDEO)";
          break;
        case FUNC1010:
          //"MMDBSYS.GetInstruments",
          list = new Object[1][2];

          list[0][0] = "VARCHAR(1536)";      list[0][1] = "handle(DB2AUDIO)";
          break;
        case FUNC1020:
          //"MMDBSYS.GetTrackNames",
          list = new Object[1][2];

          list[0][0] = "VARCHAR(1536)";      list[0][1] = "handle(DB2AUDIO)";
          break;
        case FUNC1030:
          //"MMDBSYS.Height",
          list = new Object[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle(DB2IMAGE|DB2VIDEO)";
          break;
        case FUNC1040:
          //"MMDBSYS.Importer",
          list = new Object[1][2];

          list[0][0] = "CHAR(8)";      list[0][1] = "handle(DB2IMAGE|DB2AUDIO|DB2VIDEO)";
          break;
        case FUNC1050:
          //"MMDBSYS.ImportTime",
          list = new Object[1][2];

          list[0][0] = "TIMESTAMP";      list[0][1] = "handle(DB2IMAGE|DB2AUDIO|DB2VIDEO)";
          break;
        case FUNC1060:
          //"MMDBSYS.MaxBytesPerSec",
          list = new Object[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle(DB2VIDEO)";
          break;
        case FUNC1070:
          //"MMDBSYS.NumAudioTracks",
          list = new Object[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2AUDIO|DB2VIDEO)";
          break;
        case FUNC1080:
          //"MMDBSYS.NumChannels",
          list = new Object[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2AUDIO|DB2VIDEO)";
          break;
        case FUNC1090:
          //"MMDBSYS.NumColors",
          list = new Object[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle(DB2IMAGE)";
          break;
        case FUNC1100:
          //"MMDBSYS.NumFrames",
          list = new Object[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle(DB2VIDEO)";
          break;
        case FUNC1110:
          //"MMDBSYS.NumVideoTracks",
          list = new Object[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2VIDEO)";
          break;
        case FUNC1120:
          //"MMDBSYS.QbScoreFromName",  // EEE only.  deprecated
          list = new Object[1][3];

          list[0][0] = "DOUBLE";      list[0][1] = "imgHandle(DB2IMAGE)";    list[0][2] = "VARCHAR(18)";
          break;
        case FUNC1130:
          //"MMDBSYS.QbScoreFromStr",
          list = new Object[1][3];

          list[0][0] = "DOUBLE";      list[0][1] = "imgHandle(DB2IMAGE)";    list[0][2] = "VARCHAR(1024)";
          break;
        case FUNC1140:
          //"MMDBSYS.QbScoreTBFromName",  // EEE only
          list = new Object[1][3];

          list[0][0] = "DOUBLE";      list[0][1] = "imgHandle(DB2IMAGE)";    list[0][2] = "VARCHAR(18)";

          list = new Object[1][1];
          list[0][0] = db2NotSupported;
          break;
        case FUNC1150:
          //"MMDBSYS.QbScoreTBFromStr",

          list = new Object[2][];
          list[0] = new Object[4];
          list[1] = new Object[5];

          list[0][0] = "DOUBLE";      list[0][1] = "query(VARCHAR(1024))";    list[0][2] = "table(CHAR(18))";    list[0][3] = "column(CHAR(18))";
          list[1][0] = "DOUBLE";      list[1][1] = "query(VARCHAR(1024))";    list[1][2] = "table(CHAR(18))";    list[1][3] = "column(CHAR(18))";    list[1][4] = "maxReturns(INTEGER)";

          list = new Object[1][1];
          list[0][0] = db2NotSupported;
          break;
        case FUNC1160:
          //"MMDBSYS.Replace",
          list = new Object[8][];
          list[0] = new Object[7];
          list[1] = new Object[6];
          list[2] = new Object[7];
          list[3] = new Object[7];
          list[4] = new Object[7];
          list[5] = new Object[7];
          list[6] = new Object[8];
          list[7] = new Object[8];

          list[0][0] = "handle";      list[0][1] = "handle";    list[0][2] = "content";       list[0][3] = "source_format";    list[0][4] = "target_file";    list[0][5] = "attrs";               list[0][6] = "comment";
          list[1][0] = "handle";      list[1][1] = "handle";    list[1][2] = "source_file";   list[1][3] = "source_format";    list[1][4] = "stortype";       list[1][5] = "comment";
          list[2][0] = "handle";      list[2][1] = "handle";    list[2][2] = "content";       list[2][3] = "target_file";      list[2][4] = "comment";        list[2][5] = "attrs";               list[2][6] = "thumbnail";
          list[3][0] = "handle";      list[3][1] = "handle";    list[3][2] = "source_file";   list[3][3] = "stortype";         list[3][4] = "comment";        list[3][5] = "attrs";               list[3][6] = "thumbnail";
          list[4][0] = "handle";      list[4][1] = "handle";    list[4][2] = "content";       list[4][3] = "source_format";    list[4][4] = "target_format";  list[4][5] = "target_file";         list[4][6] = "comment";
          list[5][0] = "handle";      list[5][1] = "handle";    list[5][2] = "source_file";   list[5][3] = "source_format";    list[5][4] = "target_format";  list[5][5] = "target_file";         list[5][6] = "comment";
          list[6][0] = "handle";      list[6][1] = "handle";    list[6][2] = "content";       list[6][3] = "source_format";    list[6][4] = "target_format";  list[6][5] = "target_file";         list[6][6] = "conversion_options";   list[6][7] = "comment";
          list[7][0] = "handle";      list[7][1] = "handle";    list[7][2] = "source_file";   list[7][3] = "source_format";    list[7][4] = "target_format";  list[7][5] = "conversion_options";  list[7][6] = "target_file";          list[7][7] = "comment";

          break;
        case FUNC1170:
          //"MMDBSYS.SamplingRate",
          list = new Object[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle(DB2AUDIO|DB2VIDEO)";
          break;
        case FUNC1180:
          //"MMDBSYS.Size",
          list = new Object[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle(DB2IMAGE|DB2AUDIO|DB2VIDEO)";
          break;
        case FUNC1190:
          //"MMDBSYS.Thumbnail",
          list = new Object[2][];
          list[0] = new Object[2];
          list[1] = new Object[3];

          list[0][0] = "LONG VARCHAR FOR BIT DATA";      list[0][1] = "handle(DB2IMAGE|DB2VIDEO)";
          list[1][0] = "handle(DB2IMAGE|DB2VIDEO)";      list[1][1] = "handle(DB2IMAGE|DB2VIDEO)";   list[1][2] = "new_thumbnail(LONG VARCHAR FOR BIT DATA)";
          break;
        case FUNC1200:
          //"MMDBSYS.TicksPerQNote",
          list = new Object[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2AUDIO)";
          break;
        case FUNC1210:
          //"MMDBSYS.TicksPerSec",
          list = new Object[1][2];

          list[0][0] = "SMALLINT";      list[0][1] = "handle(DB2AUDIO)";
          break;
        case FUNC1220:
          //"MMDBSYS.Updater",
          list = new Object[1][2];

          list[0][0] = "CHAR(8)";      list[0][1] = "handle(DB2IMAGE|DB2AUDIO|DB2VIDEO)";
          break;
        case FUNC1230:
          //"MMDBSYS.UpdateTime",
          list = new Object[1][2];

          list[0][0] = "TIMESTAMP";      list[0][1] = "handle(DB2IMAGE|DB2AUDIO|DB2VIDEO)";
          break;
        case FUNC1240:
          //"MMDBSYS.Width"
          list = new Object[1][2];

          list[0][0] = "INTEGER";      list[0][1] = "handle(DB2IMAGE|DB2VIDEO)";
          break;
        case FUNC1250:
          //"IDENTITY_VAL_LOCAL"
          list = new Object[1][1];
          
          list[0][0] = "DECIMAL";
          break;          
        case FUNC1260:
          //"DEREF"
          list = new Object[1][2];
          
          list[0][0] = "instance";    list[0][1] = "REF";
          break;          
      } // end switch()
    return list;
  } // end getParms

  public static Object[][] getParameterFormats(String functionName)
  {
  	Integer lIndex = (Integer)names2index.get(functionName);
  	if (lIndex != null )
  		return getParms(lIndex);
  	
  	return UDFNamesAndSignatures.getUDFParams(functionName);
  }

  public static void fillCategoryCombo(Combo functionsCategoryCombo, boolean isColumn)
  {
    if (!isColumn)
    {
      functionsCategoryCombo.add(db2All);
      functionsCategoryCombo.add(db2AIVExtenders);
      functionsCategoryCombo.add(db2Column);
      functionsCategoryCombo.add(db2SpecialRegisters);
      functionsCategoryCombo.add(db2Scalar);
      functionsCategoryCombo.add(db2Conversion);
      functionsCategoryCombo.add(db2DateAndTime);
      functionsCategoryCombo.add(db2Db2);
      functionsCategoryCombo.add(db2Logical);
      functionsCategoryCombo.add(db2Math);
      functionsCategoryCombo.add(db2Summary);
      functionsCategoryCombo.add(db2Text);
      functionsCategoryCombo.add(db2TextExtender);
      functionsCategoryCombo.add(db2UDF);
    }
    else
    {
      functionsCategoryCombo.add(db2All);
      functionsCategoryCombo.add(db2AIVExtenders);
      functionsCategoryCombo.add(db2Column);
      functionsCategoryCombo.add(db2DateAndTime);
      functionsCategoryCombo.add(db2TextExtender);
      functionsCategoryCombo.add(db2UDF);
    }
  }

  // Set up superclass for the various vendor function names and signatures
  public static boolean isFunctionSupportingStar(String s)
  {
    List funcList = Arrays.asList(functionsSupportingStar);
    if (funcList.contains(s))
    {
      return true;
    }
    return false;
  }

  public static boolean isColumnFunctionExpression(String functionName)
  {
    List funcList = Arrays.asList(columnFunctions);
    if (funcList.contains(functionName))
    {
      return true;
    }
    return false;
  }

  public static boolean requiresNoBrackets(String functionName)
  {
    List funcList = Arrays.asList(noBracketFunctions);
    if (funcList.contains(functionName))
    {
      return true;
    }
    return false;
  }

  public static boolean isNotSupported(String functionName)
  {
    List funcList = Arrays.asList(unsupportedFunctions);
    if (funcList.contains(functionName))
    {
      return true;
    }
    return false;
  }

  public static String[] getFunctionList(String category, boolean isColumn,SQLDomainModel domainModel )
  {
    if (category.equals(db2All))
    {
      if (!isColumn)
      {
        return UDFNamesAndSignatures.mergeTwoArrays(allFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
      }
      
      return UDFNamesAndSignatures.mergeTwoArrays(allColumnSupportedFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
    }
    else if (category.equals(db2Column))
    {
      return columnFunctions;
    }
    else if (category.equals(db2Scalar))
    {
      return scalarFunctions;
    }
    else if (category.equals(db2SpecialRegisters))
    {
      return specialRegisters;
    }
    else if (category.equals(db2Conversion))
    {
      return conversionFunctions;
    }
    else if (category.equals(db2DateAndTime))
    {
      return dateTimeFunctions;
    }
    else if (category.equals(db2Db2))
    {
      return db2Functions;
    }
    else if (category.equals(db2Logical))
    {
      return logicalFunctions;
    }
    else if (category.equals(db2Math))
    {
      return mathFunctions;
    }
    else if (category.equals(db2Summary))
    {
      return summaryFunctions;
    }
    else if (category.equals(db2Text))
    {
      return textFunctions;
    }
    else if (category.equals(db2TextExtender))
    {
      return textExtenderFunctions;
    }
    else if (category.equals(db2AIVExtenders))
    {
      return aivExtenderFunctions;
    }
    else if (category.equals(db2UDF))
    {
      return UDFNamesAndSignatures.getUDFNames(domainModel);
    }
    return UDFNamesAndSignatures.mergeTwoArrays(allFunctions,UDFNamesAndSignatures.getUDFNames(domainModel)) ;
  }
}