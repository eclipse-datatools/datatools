-- Update Examples 

update cl_sched 
	set col1 = '3565'
	where col2 = '000190';

update cl_sched an
	set col1 = col1 + 100
	where col2 = 'd11';

update cl_sched as an
	set col1 = null, col2..an1..an2 = null, col3 = null
	where col4 = '000250';

update cl_sched 
	set (col1, col2, col3) = (null, null, null)
	where col4 = '000250';

update cl_sched
	set col1 = (select count(*) 
				from department
				where deptno = 'e21')
	where col1 = 'e21';

update cl_sched
	set col1 = 2 * col1
	where current of c1;

update cl_sched
	set col1 = col1 + 1000,
	    col2 = update_resume(:hv_resume)
	where col1 = :hv_emp_rowid;

update cl_sched
	set col1 = 1.10 * col1
	where col1 < (select avg(col1) 
				from cl_sched y
				where x.col1 = y.col1);
			
update cl_sched
	set col1 = (select avg(t2.col1) from cl_sched t2)
	where col2 = 'e11' and col1 < (select avg(t3.col1) from cl_sched t3);

update cl_sched
	set col1 = (select .10 * col1 from cl_sched y where col1 = y.col1)
	where current of c1;
	
update sch.t1
	set col1 = 10;
	
update db.sch.t1
	set col1 = 10, col2 = 20, (col3, col4) = (30, 40)
	where col1 = col2;
	
update t1
	set col1 = col2
	where current of cn;

--**1**
UPDATE HIPPO_LIKE
  SET LEGNO = CASE POUNDS
                WHEN  6000 THEN 2222
                WHEN  8090 THEN 2220
                WHEN 10100 THEN 22
                WHEN 12050 THEN 2022
                WHEN  1450 THEN 2222
                ELSE 0
              END;

--**3**
UPDATE HIPPO_LIKE
  SET FOOD = CASE MOUTHS
               WHEN '061' THEN MOUTHS
               WHEN '010' THEN FOOD
               WHEN '118' THEN MOUTHS
               WHEN '087' THEN FOOD
               WHEN '000' THEN MOUTHS
               ELSE NULL
             END;

--* THIS UPDATE WILL UPDATE ONLY ONE COLUMN.
--* THE SUBSELECTS SHOULD PICK JUST THREE NAMELENGHTS (15,6, AND 16)
--* THESE CORRESPOND TO SERIALNO (5090, 5098 AND 6854).  BUT OF THESE
--* THREE ROWS ONLY 5090 HAS AN EMPLENGTH THAT IS NOT NULL
  UPDATE TBEN4504
  SET MONTHSAL = (MONTHSAL * (EMPLENGTH+2)) - MONTHSAL
  WHERE EMPLENGTH IS NOT NULL AND
        SERIALNO IN (SELECT V1.SERIALNO FROM VWEN4502 V1, VWEN4503 V2
                      WHERE V1.SERIALNO = V2.SERIAL
                      AND V2.NAMELENGTH IN (SELECT AVG(V3.NAMELENGTH)
                                            FROM VWEN4503 V3
                                            GROUP BY NAMELENGTH
                                            HAVING COUNT(*) = 1));

--*STMT1**********************************************
--* VALID DATATYPE: TIMESTAMP, DOUBLE
--* KEY POINTS:     COUNT IN SUBQUERIES OF BOOLEAN OPERATORS
UPDATE VWIPR301 SET FILENO = 'OK'
  WHERE 3 = (SELECT COUNT(DISTINCT TIMESTMP) FROM TBIPR302)
        AND
        2 = (SELECT COUNT(DISTINCT DOUBLE1) FROM TBIPR302)
        AND
        '700' > RECID;

--*STMT5**********************************************
--* VALID DATATYPE: CHAR
--* KEY POINTS:     UPDATE COLUMN WITH VALUE RETURNED BY COUNT
UPDATE TBIPR301 SET SMINT = 100
WHERE 'CHARNNWD' = CHARNNWD
       AND 'CHARDU  ' = CHARDU
       AND 11 = (SELECT COUNT(*) FROM TBIPR302)
       AND 'STLVMUID' = DEFSQLID
       AND '858' > RECID;

--*STMT10*********************************************
--* KEY POINTS:     TRY TO UPDATE A RO VIEW
UPDATE VWIPR303 SET CNT_SMINT = 1
WHERE 13 = (SELECT COUNT(*) FROM TBIPR303);

--*STMT10*********************************************
--* KEY POINTS:      CHAR, ROWID IN WHERE CLAUSE OF UPDATE SUBSELECT
UPDATE TBKT0902 SET INTCOLNN   = DAYOFWEEK(DATE1),
                    INTCOLNND  = DAYOFMONTH(DATE1),
                    INTCOLNNWD = DAYOFYEAR(DATE1)
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
              OR DATE('2000-01-01') > DATE1)
         AND DAYOFYEAR('2000-01-01') <=
                 DAYOFMONTH('2000-01-01'));

--**171
--*update
UPDATE TBNN1200.employees E1
  SET    EMP_ID = 'UPD111'
  WHERE E1.EMP_ID IN
        (SELECT TBNN1200.employees.EMP_ID
        FROM TBNN1200.employees INNER JOIN
        (TBNN1200.old_offices FULL JOIN TBNN1200.new_offices
        ON TBNN1200.old_offices.emp_id = TBNN1200.new_offices.emp_id)
        ON TBNN1200.employees.emp_id = TBNN1200.new_offices.emp_id);

UPDATE TBNN1200.employees
  SET    EMP_ID = 'UPD444'
  WHERE    EMP_ID IN
        (SELECT TBNN1200.employees.EMP_ID
        FROM TBNN1200.employees FULL JOIN
         (TBNN1200.old_offices FULL JOIN TBNN1200.new_offices
         ON TBNN1200.old_offices.emp_id = TBNN1200.new_offices.emp_id)
  ON TBNN1200.employees.emp_id = TBNN1200.new_offices.emp_id);

UPDATE TBNN1202.employees
  SET SALARY = SALARY+1000
  WHERE TBNN1202.employees.EMP_ID IN
  (SELECT TBNN1202.employees.EMP_ID
   FROM TBNN1202.employees INNER JOIN
        (TBNN1202.old_offices FULL JOIN TBNN1202.new_offices
        ON TBNN1202.old_offices.emp_id = TBNN1202.new_offices.emp_id)
   ON TBNN1202.employees.emp_id = TBNN1202.new_offices.emp_id);

UPDATE TBNM08F1 SET (INTA, CHARA) = (
SELECT intcol, charcol FROM TBLV0100.manytypes_ctrl
WHERE intcol in (
SELECT t1.intcol
FROM
((((TBLV0100.mtypes_1 T1 FULL JOIN TBLV0100.mtypes_2 T2
ON T1.charcol = T2.vcharcol AND T1.intcol = T2.dec62col)
FULL JOIN
(TBLV0100.mtypes_2 T4 FULL JOIN TBLV0100.mtypes_1 T3
ON T4.smintcol = T3.dec62col)
ON T1.intcol = T4.intcol AND T2.charcol = T3.charcol)
FULL JOIN
((TBLV0100.mtypes_1 T5 FULL JOIN TBLV0100.mtypes_2 T6
ON T5.charcol = T6.vcharcol AND T5.intcol = T6.dec62col)
FULL JOIN
(TBLV0100.mtypes_2 T7 FULL JOIN TBLV0100.mtypes_1 T8
ON T7.smintcol = T8.dec62col)
ON T5.intcol = T8.dec62col AND T6.charcol = T7.charcol)
ON T5.dec62col = T1.dec62col AND T5.intcol = T3.intcol)
FULL JOIN
(((TBLV0100.mtypes_1 T9 FULL JOIN TBLV0100.mtypes_2 T10
ON T9.charcol = T10.vcharcol AND T9.intcol = T10.dec62col)
FULL JOIN
(TBLV0100.mtypes_2 T12 FULL JOIN TBLV0100.mtypes_1 T11
ON T12.smintcol = T11.dec62col)
ON T9.intcol = T12.intcol AND T10.charcol = T11.charcol)
FULL JOIN
((TBLV0100.mtypes_1 T13 FULL JOIN TBLV0100.mtypes_2 T14
ON T13.charcol = T14.vcharcol AND T13.intcol = T14.dec62col)
FULL JOIN
(TBLV0100.mtypes_2 T15 FULL JOIN TBLV0100.mtypes_1 T16
ON T15.smintcol = T16.dec62col)
ON T13.intcol = T16.dec62col AND T14.charcol = T15.charcol)
ON T13.dec62col = T9.dec62col AND T13.intcol = T11.intcol)
ON T12.dec62col = T2.dec62col AND T14.intcol = T8.intcol)
FULL JOIN
((((TBLV0100.mtypes_1 T17 FULL JOIN TBLV0100.mtypes_2 T18
ON T17.charcol = T18.vcharcol AND T17.intcol = T18.dec62col)
FULL JOIN
(TBLV0100.mtypes_2 T20 FULL JOIN TBLV0100.mtypes_1 T19
ON T20.smintcol = T19.dec62col)
ON T17.intcol = T20.intcol AND T18.charcol = T19.charcol)
FULL JOIN
((TBLV0100.mtypes_1 T21 FULL JOIN TBLV0100.mtypes_2 T22
ON T21.charcol = T22.vcharcol AND T21.intcol = T22.dec62col)
FULL JOIN
(TBLV0100.mtypes_2 T23 FULL JOIN TBLV0100.mtypes_1 T24
ON T23.smintcol = T24.dec62col)
ON T21.intcol = T24.dec62col AND T22.charcol = T23.charcol)
ON T21.dec62col = T17.dec62col AND T21.intcol = T19.intcol)
FULL JOIN
(((TBLV0100.mtypes_1 T25 FULL JOIN TBLV0100.mtypes_2 T26
ON T25.charcol = T26.vcharcol AND T25.intcol = T26.dec62col)
FULL JOIN
(TBLV0100.mtypes_2 T28 FULL JOIN TBLV0100.mtypes_1 T27
ON T28.smintcol = T27.dec62col)
ON T25.intcol = T28.intcol AND T26.charcol = T27.charcol)
FULL JOIN
(TBLV0100.mtypes_1 T29 FULL JOIN TBLV0100.mtypes_2 T30
ON T29.charcol = T30.vcharcol AND T29.intcol = T30.dec62col)
ON T29.dec62col = T25.dec62col AND T29.intcol = T27.intcol)
ON T28.dec62col = T18.dec62col AND T30.intcol = T24.intcol)
ON T28.dec62col = T2.dec62col AND T30.intcol = T14.intcol)
and charcol = 'Two');
