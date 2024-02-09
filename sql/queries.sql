--Queries to be answered:


--Modifying Data

--Question 1: https://pgexercises.com/questions/updates/insert.html
INSERT INTO cd.facilities (
  facid, name, membercost, guestcost, 
  initialoutlay, monthlymaintenance
) 
VALUES 
  (9, 'Spa', 20, 30, 100000, 800);

--Question 2: https://pgexercises.com/questions/updates/insert3.html
INSERT INTO cd.facilities (
  facid, name, membercost, guestcost, 
  initialoutlay, monthlymaintenance
) 
select 
  (
    select 
      max(facid) 
    from 
      cd.facilities
  )+ 1, 
  'Spa', 
  20, 
  30, 
  100000, 
  800;


--Question 3: https://pgexercises.com/questions/updates/update.html 
UPDATE 
  cd.facilities 
SET 
  initialoutlay = 10000 
WHERE 
  facid = 1;

--Question 4: https://pgexercises.com/questions/updates/updatecalculated.html
UPDATE 
  cd.facilities 
SET 
  membercost = (
    SELECT 
      membercost1.1 
    FROM 
      cd.facilities 
    WHERE 
      facid = 0
  ), 
  guestcost = (
    SELECT 
      guestcost1.1 
    FROM 
      cd.facilities 
    WHERE 
      facid = 0
  ) 
WHERE 
  facid = 1;

--Question 5: https://pgexercises.com/questions/updates/delete.html
DELETE from cd.bookings;

--Question 6: https://pgexercises.com/questions/updates/deletewh.html
DELETE from 
  cd.members 
WHERE 
  memid = 37;

--Basics

--Question 7: https://pgexercises.com/questions/basic/where2.html
SELECT 
  facid, 
  name, 
  membercost, 
  monthlymaintenance 
FROM 
  cd.facilities 
WHERE 
  membercost < (monthlymaintenance * 0.02) 
  AND membercost > 0;

--Question 8: https://pgexercises.com/questions/basic/where3.html
SELECT 
  * 
FROM 
  cd.facilities 
WHERE 
  name like '%Tennis%';

--Question 9: https://pgexercises.com/questions/basic/where4.html
SELECT 
  * 
FROM 
  cd.facilities 
WHERE 
  (facid = 1) 
  or (facid = 5);

--Question 10: https://pgexercises.com/questions/basic/date.html
SELECT 
  memid, 
  surname, 
  firstname, 
  joindate 
FROM 
  cd.members 
WHERE 
  joindate >= '2012-09-01';

--Question 11: https://pgexercises.com/questions/basic/union.html 
SELECT 
  surname 
FROM 
  cd.members 
UNION 
SELECT 
  name 
FROM 
  cd.facilities;

--Join

--Question 12: https://pgexercises.com/questions/joins/simplejoin.html
SELECT 
  book.starttime 
FROM 
  cd.bookings AS book 
  INNER JOIN cd.members AS mem ON book.memid = mem.memid 
WHERE 
  mem.firstname = 'David' 
  AND mem.surname = 'Farrell';

--Question 13: https://pgexercises.com/questions/joins/simplejoin2.html
SELECT 
  book.starttime, 
  fac.name 
FROM 
  cd.bookings AS book 
  INNER JOIN cd.facilities AS fac ON book.facid = fac.facid 
WHERE 
  (
    fac.name like 'Tennis Court%' 
    AND book.starttime >= '2012-09-21' 
    AND book.starttime < '2012-09-22'
  ) 
ORDER BY 
  starttime;

--Question 14: https://pgexercises.com/questions/joins/self2.html
SELECT 
  mem.firstname as memfname, 
  mem.surname as memsname, 
  rec.firstname as recfan, 
  rec.surname as recsname 
FROM 
  cd.members as mem 
  LEFT OUTER JOIN cd.members as rec ON mem.recommendedby = rec.memid 
ORDER BY 
  memsname, 
  memfname;

--Question 15: https://pgexercises.com/questions/joins/self.html
SELECT 
  distinct rec.firstname, 
  rec.surname 
FROM 
  cd.members as mems 
  INNER JOIN cd.members as rec ON mems.recommendedby = rec.memid 
ORDER BY 
  rec.surname, 
  rec.firstname;

--Question 16: https://pgexercises.com/questions/joins/sub.html
select 
  distinct concat(mem.firstname, ' ', mem.surname) as member, 
  (
    select 
      concat(rec.firstname, ' ', rec.surname) as recommender 
    from 
      cd.members as rec 
    where 
      mem.recommendedby = rec.memid
  ) 
from 
  cd.members mem 
order by 
  member;


--Aggregation

--Question 17: https://pgexercises.com/questions/aggregates/count3.html
select 
  recommendedby, 
  count(recommendedby) 
from 
  cd.members 
where 
  recommendedby is not null 
group by 
  recommendedby 
order by 
  recommendedby;

--Question 18: https://pgexercises.com/questions/aggregates/fachours.html
select 
  cd.bookings.facid as facid, 
  sum(cd.bookings.slots) as "Total Slot" 
from 
  cd.bookings 
group by 
  cd.bookings.facid 
order by 
  cd.bookings.facid;

--Question 19: https://pgexercises.com/questions/aggregates/fachoursbymonth.html
select 
  book.facid, 
  sum(book.slots) 
from 
  cd.bookings as book 
where 
  book.starttime >= '2012-09-01' 
  and book.starttime < '2012-10-1' 
group by 
  facid 
order by 
  sum(book.slots);

--Question 20: https://pgexercises.com/questions/aggregates/fachoursbymonth2.html 
select 
  facid, 
  extract(
    month 
    from 
      starttime
  ) as Month, 
  sum(slots) as "Total Slots" 
from 
  cd.bookings 
where 
  starttime >= '2012-01-01' 
  and starttime < '2013-01-01' 
group by 
  facid, 
  Month 
order by 
  facid, 
  month;

--Question 21: https://pgexercises.com/questions/aggregates/members1.html
select 
  count(distinct memid) 
from 
  cd.bookings;

--Question 22: https://pgexercises.com/questions/aggregates/nbooking.html
select 
  mem.surname, 
  mem.firstname, 
  mem.memid, 
  min(book.starttime) 
from 
  cd.members as mem 
  inner join cd.bookings as book on mem.memid = book.memid 
where 
  book.starttime >= '2012-09-01' 
group by 
  mem.surname, 
  mem.firstname, 
  mem.memid 
order by 
  mem.memid;

--Question 23: https://pgexercises.com/questions/aggregates/countmembers.html 
select 
  count(*) over(), 
  cd.members.firstname, 
  cd.members.surname 
from 
  cd.members 
order by 
  cd.members.joindate;

--Question 24: https://pgexercises.com/questions/aggregates/nummembers.html
select 
  row_number() over(), 
  firstname, 
  surname 
from 
  cd.members 
order by 
  joindate;

--Question 25: https://pgexercises.com/questions/aggregates/fachours4.html 
select 
  facid, 
  total 
from 
  (
    select 
      facid, 
      total, 
      RANK() OVER (
        order by 
          total desc
      ) as rank 
    from 
      (
        select 
          distinct facid, 
          sum(slots) over (partition by facid) as total 
        FROM 
          cd.bookings
      ) as sub
  ) as ranked 
where 
  rank = 1 
order by 
  total desc;

--Question 26: https://pgexercises.com/questions/string/concat.html
select 
  concat(surname, ', ', firstname) 
from 
  cd.members;

--Question 27: https://pgexercises.com/questions/string/reg.html
select 
  memid, 
  telephone 
from 
  cd.members 
where 
  telephone like '%(%' 
  or telephone like '%)%';

--Question 28: https://pgexercises.com/questions/string/substr.html
select 
  substr (mems.surname, 1, 1) as firstletter, 
  count(*) as count 
from 
  cd.members mems 
group by 
  firstletter 
order by 
  firstletter;