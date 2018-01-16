--       10        20        30        40        50        60        70        80        90
--3456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789
update cl_sched as an set col1 = 'abc', col2 = 256, col3 = null where col4 = other.col4 + 250;
update cl_sched set (col1, col2, col3) = ('abc', 256, null) where not col4 = '000250' and col1 = 'ABC';
--update cl_sched set col1 = '3565' where col2 = '000190' or col2;
--update cl_sched set col1 = '3565' where col2 = '000190' or (col2 = '000180');
--update cl_sched set col1 = '3565' where col2 = '000190' or (col2 = '000180') and col3 = 500;
--update cl_sched set col1 = '3565' where col2 = '000190' or (col2 = '000180' and col3 = 500);
            	        
