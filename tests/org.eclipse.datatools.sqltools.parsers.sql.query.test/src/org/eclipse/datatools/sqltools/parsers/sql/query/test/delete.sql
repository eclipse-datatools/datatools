delete from tbl;

delete from v1 al;

delete from nn as al 
	where col1 = 10;

delete from only(tbl1) al 
	with rr;

delete from only(v1) 
	where col1 = 10 
	with cs;

delete from tbl1 where current of cn;


--------*STMT75*********************************************
--* VALID DATATYPE:  DOUBLE-PRECISION FLOATING POINT
--* KEY POINTS:      ABS AND IFNULL IN SELECT AND WHERE
--*                  CLAUSE OF FULLSELECT WITH
--*                  DEFAULT NULL.
DELETE FROM TBKT2101
 WHERE ABS(IFNULL(ABS(FLOAT4WDN),ABS(DOUBLE1)))= DOUBLE1;

--------*STMT100********************************************
--* VALID DATATYPE:  DATE, TIMESTAMP
--* KEY POINTS:      DAYOFWEEK, DAYOFMONTH, DAYOFYEAR FUNCTIONS
--*                  IN SELECT AND WHERE CLAUSE OF FULLSELECT,
--*                  WITH DATE ARGUMENT
DELETE FROM TBKT2101
WHERE RECID = '001'
  AND ABS(DAYOFWEEK(DATE(CURRENT TIMESTAMP))) =
  ABS(DAYOFWEEK(DATE(CURRENT DATE)))
  AND ABS(DAYOFMONTH(DATE(CURRENT TIMESTAMP))) =
  ABS(DAYOFMONTH(CURRENT DATE))
  AND ABS(DAYOFYEAR(DATE(CURRENT TIMESTAMP))) =
  ABS(DAYOFYEAR(DATE(CURRENT TIMESTAMP)));

--------*STMT120********************************************
--* VALID DATATYPE:  CHAR, FLOAT, REAL, DOUBLE, VARCHAR
--*                  FOR BIT DATA
--* KEY POINTS:      ABS, CEIL, FLOOR IN SELECT/FROM/WHERE CLAUSE OF
--*                    NESTED TABLE EXPRESSION WITH CORRELATED QUERY
--*                    AND IN SEARCHED-WHEN CLAUSE OF CASE STMT
DELETE FROM TBKT2101
 WHERE RECID = '100'
                AND ABS(CEILING(INT(DOUBLE(RECID)))) =
                   ABS(FLOOR(SMALLINT(FILENO)))
  AND CASE
          WHEN ABS(CEIL(INT(VARCHAR20BIT)))
            =  ABS(FLOOR(INTEGER(100.)))                 THEN 0
          WHEN ABS(CEILING(FLOAT(123*2)))
            <> ABS(FLOOR(DOUBLE_PRECISION(123*2)))       THEN 1
          WHEN ABS(CEIL(INTCOLNND*2.10000))
            =  ABS(FLOOR(1234*2.1))                      THEN 2
          WHEN ABS(CEILING(FLOAT4NNWD*10.1))
            >= ABS(FLOOR(FLOAT4NNWD*10.1))               THEN 3
          WHEN CEIL(INT(FILENO)/SMALLINT(RECID))
            =  ABS(FLOOR(INT(FILENO)/INT(RECID)))    THEN FLOOR(DEC150)
          WHEN ABS(CEIL(INT(FILENO)/INT(RECID)))
            <  ABS(FLOOR(INT(FILENO)/INT(RECID)))      THEN CEIL(DEC150)
          WHEN ABS(CEIL(INT(FILENO)/INT(RECID)))
            >  ABS(FLOOR(INT(FILENO)/INT(RECID)))        THEN 4
           ELSE 5 END IN (0,1,2,3,4,999);

--------*STMT135********************************************
--* VALID DATATYPE:  INTEGER, FLOAT, DOUBLE, DECIMAL
--* KEY POINTS:      ABS, CEIL, FLOOR FUNCTION
--*                  IN SELECT OF FULLSELECT
--*                  AND WHERE CLAUSE OF FULLSELECT
DELETE FROM TBKT2101
  WHERE INT(DECIMAL(FLOAT(ABS(DOUBLE(CEIL(FLOOR(INTCOLNN))))))) =
   INT(DECIMAL(FLOAT(ABS(DOUBLE(CEIL(FLOOR(INTCOLNN)))))));


--*STMT5**********************************************
--* KEY POINTS:     DELETE WITH COUNT AND TABLE
--*                 EXPRESSIONS IN SELECT
DELETE FROM TBIPR302
        WHERE
         SMINT<>(SELECT COUNT(*) FROM
                   (
                    SELECT COUNT(DISTINCT RECID) FROM TBIPR301
                    GROUP BY CHAR7
                   ) AS TBLEXP
                );

--*STMT10*********************************************
--* KEY POINTS:      DAYOFWEEK, DAYOFMONTH, DAYOFYEAR FUNCTIONS
--*                  IN WHERE CLAUSE OF DELETE SUBSELECT
DELETE FROM TBKT0902
 WHERE RECID = CHAR(INTEGER(DIGITS(SMALLINT(FILENO))))
   AND DAYOFWEEK(DATE2) IN
      (SELECT DAYOFWEEK(CHAR(DATE2))
       FROM VWKT0901
       WHERE CHAR(SMALLINT(RECID)) = FILENO
         AND (DAYOFMONTH(DATE1) >= DAYOFMONTH(DATE2)
              OR DAYOFWEEK(CHAR(DATE1)) = DAYOFWEEK(VARCHAR(DATE2))
              OR DAYOFYEAR(DATE1) <= DAYOFMONTH(DATE1))
         AND (DAYOFYEAR('2000-01-01-00.00.00') =
                 DAYOFYEAR(TIMESTMP)
              OR DATE('2000-01-01') > DATE2)
         AND DAYOFYEAR('2000-01-01') <=
                 DAYOFMONTH('2000-01-01'));

--** delete
DELETE FROM TBNN1200.employees E1
  WHERE E1.EMP_ID IN
        (SELECT TBNN1200.employees.EMP_ID
        FROM TBNN1200.employees INNER JOIN
        (TBNN1200.old_offices FULL JOIN TBNN1200.new_offices
        ON TBNN1200.old_offices.emp_id = TBNN1200.new_offices.emp_id)
        ON TBNN1200.employees.emp_id = TBNN1200.new_offices.emp_id);

DELETE FROM TBNN1200.old_offices
  WHERE EMP_ID IN
  (SELECT  TBNN1200.old_offices.EMP_ID
  FROM (TBNN1200.old_offices T1 FULL JOIN TBNN1200.new_offices T2
        ON T1.emp_id = T2.emp_id),
       (TBNN1200.old_offices T3 FULL JOIN TBNN1200.new_offices T4
       ON T3.emp_id = T4.emp_id)
  WHERE T1.emp_id = T3.emp_id);

DELETE FROM TBNN1201.old_offices
  WHERE OLD_OFFICE = 'U'||SUBSTR(OLD_OFFICE,2,3) AND
        EMP_ID IN
   (SELECT TBNN1201.old_offices.EMP_ID
    FROM (TBNN1201.old_offices T1 FULL JOIN TBNN1201.new_offices T2
        ON T1.emp_id = T2.emp_id),
          (TBNN1201.old_offices T3 FULL JOIN TBNN1201.new_offices T4
           ON T3.emp_id = T4.emp_id)
    WHERE T1.emp_id = T3.emp_id);
