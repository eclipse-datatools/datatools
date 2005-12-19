select
	col1, tbl.col1, sch.tbl.col1, 1, 'abc',

	:v, :v1 :v2, :v1 indicator :v2,

	client acctng,
	client applname,
	client userid,
	client wrkstnname,
	current date, current_date,
	current dbpartitionnum, current_dbpartitionnum,
	current default transform group, current_default_transform_group,
	current degree, current_degree,
	current explain mode, current_explain_mode,
	current explain snapshot, current_explain_snapshot,
	current maintained table types for optimization,
	current path, current_path,
	current query optimization, current_query_optimization,
	current refresh age,
	current schema, current_schema,
	current server, current_server,
	current time, current_time,
	current timestamp, current_timestamp,
	current timezone, current_timezone,
	user,

	(select * from a),
	(select * from a intersect all select * from b),

	year, a(col1) years, 
	month, (col1 + col2) months,
	day, 12 days,
	hour, col1 hours,
	minute, :v1 minutes,
	second, col1 seconds,
	microsecond, col1 microseconds,

	col1->name1,
	(col1 + col2) -> name1 ('1997', '1998'),

	over,
	row_number() over(),
	rank() over(partition by col1, col2, col3),
	dense_rank() over(order by col1, col2 asc, col3 desc nulls first, order of td),
	colf(col1, col2) over (partition by col1, col2 order by col1, col2 desc, col3 asc nulls last, order of td),
	colf(col1, col2) over (partition by col1, col2 order by col1, col2 desc, col3 asc nulls last, order of td rows unbounded preceding),
	colf(col1, col2) over (partition by col1, col2 order by col1, col2 desc, col3 asc nulls last, order of td rows unbounded preceding range 10 following),
	colf(col1, col2) over (range between unbounded preceding and unbounded following),

	xml2clob,
	xml2clob(xmlelement(name ename)),
	xml2clob(xmlelement(name ename 1, 2, 3)),
	xml2clob(xmlelement(name ename xmlattributes(col1, col2 as cname, col3))),
	xml2clob(xmlelement(name ename xmlattributes(col1, col2 as cname, col3), 1, 2, 3)),
	xml2clob(xmlagg(xmlelement(name ename))),
	xml2clob(xmlagg(xmlelement(name ename) order by col1, 1 asc, col * 2 desc)),
	
	a..b,
	a..b..c..d,
	a..b..(c + d)..d(col1, col2, col3),

	treat,
	treate(col1 as integer),
	treate(col2 as integer)..rgb(),

	nextval, prevval,
	nextval for sn, prevval for tbl.sn,

	col1 + col2 * col3 / col4 || col5 concat col6
into
	:v, :v1 :v2, :v1 indicator :v2
from 
	tbl,
	only (tblname) al1,
	(select * from a) as al2,
	table(fn(col1, col2)) as al3(col1, col2),
	table(select * from a union select * from b),
	tb1 left join tb2 on tb1.c1 = tb2.c1 right join tb3 left join tb4 on tb3.c1 = tb4.c1 on tb1.c1 = tb3.c1,
	(tb1 left join tb2 on tb1.c1 = tb2.c1) right join (tb3 left join tb4 on tb3.c1 = tb4.c1) on tb1.c1 = tb3.c1
where 
	not foo(parm, parm) = 1 selectivity 0.004 and
	col1 = col2 or 
	col1 <> col2 or
	col1 > col2 and
	col1 < col2 or
	col1 >= col2 and
	col1 <= col2 or 
	col1 between col2 and col3 and
	col2 not between col2 and col3 or
	col1 in (select * from a) and
	col1 in (col1, col2, col3, col4) or
	(col1, col2) not in (select * from a union select * from b) or
	col1 like 'abcd' and
	col1 not like 'abcde' escape 'defc' or
	col1 is null and
	col1 is not null or
	col1 = any (select * from a) and
	col1 <> some (select * from b) or
	col1 > all(select * from c) and
	exists (select * from d) or
	col1 is of (col1, only col2, col3, only col4) or
	col1 is not of (only col1, col2, only col3, col4) and
	col1 not of dynamic type (col1, col2) or
	col2 of dynamic type (col1, col2)
group by 
	col1,
	grouping sets((col1)),
	grouping sets((col1, col2, col3)),
	grouping sets((col2, col1), (col2), ()),
	rollup(col1, col2),
	cube(col1, year(col2), col3)
having 
	not foo(parm, parm) = 1 selectivity 0.004 and
	col1 = col2 or 
	col1 <> col2 or
	col1 > col2 and
	col1 < col2 or
	col1 >= col2 and
	col1 <= col2 or 
	col1 between col2 and col3 and
	col2 not between col2 and col3 or
	col1 in (select * from a) and
	col1 in (col1, col2, col3, col4) or
	(col1, col2) not in (select * from a union select * from b) or
	col1 like 'abcd' and
	col1 not like 'abcde' escape 'defc' or
	col1 is null and
	col1 is not null or
	col1 = any (select * from a) and
	col1 <> some (select * from b) or
	col1 > all(select * from c) and
	exists (select * from d) or
	col1 is of (col1, only col2, col3, only col4) or
	col1 is not of (only col1, col2, only col3, col4) and
	col1 not of dynamic type (col1, col2) or
	col2 of dynamic type (col1, col2)
order by 
	col1, 1 asc, (col1 + col2) desc, order of utable
fetch first 1 row only
fetch first 10 rows only
for update
for update of col1, col2, col3
for read only
for fetch only
optimize for 1 row
optimize for 10 rows
;

select r1, r2
from (values('group 1', 'group 2')) as x(r1, r2);

select e.empno, xml2clob(xmlelement(name "emp", 
						xmlelement(name "name", e.firstnme || ' ' || e.lastname),
						xmlelement(name "hiredate", e.hiredate))) as "Result"
from employee e
where e.edlevel = 12;

select xml2clob(xmlelement(name "Department", 
				   xmlattributes(e.workdept as "name"),
				   xmlagg(xmlelement(name "emp", e.lastname) order by e.lastname))) as "dept_lsit"
from employee e
where e.workdept in ('c01', 'e21')
group by workdept;	

select e.empno, xml2clob(xmlelement(name "emp", e.firstnme || ' ' || e.lastname)) as "Resulte"
from employee e
where e.edlevel = 12;

select empno, lastname, firstnme, total_salary, rank_salary
from (select empno, lastname, firstnme, salary + bonus as total_salary,
		rank() over (order by salary + bonus desc, firstname) as rank_salary
	from employee) as ranked_employee
where rank_salary < 6
order by rank_salary;

select empno, lastname, firstnme, salary + bonus as total_salary,
	rank() over (order by salary + bonus desc) as rank_salary
from employee 
where salary + bonus > 3000
order by lastname;





