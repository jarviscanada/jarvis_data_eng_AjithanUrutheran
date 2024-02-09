# Introduction
This project will introduce us to the SQL and focuses on exploring the different features SQL has to offer in order to interact with data. Topics including insertion, deletion, querying, aggregation functions, scalar functions, window functions, and joins will be covered in this project. This project will have the user setup the following tables: **cd.members**, **cd.facilities**, **cd.bookings**. We will perform queries to these tables in order to practice our knowledge of SQL. 
# SQL Queries

##### Table Setup (DDL)

CREATE TABLE cd.members(
	memid integer NOT NULL,
	surname character varying(200) NOT NULL,
	firstname character varying(200) NOT NULL,
	address character varying(300) NOT NULL,
	zipcode integer NOT NULL,
	telephone integer NOT NULL,
	recommendedby integer,
	joindate timestamp NOT NULL,
	CONSTRAINT members_pk PRIMARY KEY (memid),
	CONSTRAINT members_recommendedby_fk FOREIGN KEY (recommendedby) REFERENCES cd.members(memid) ON DELETE SET NULL
);

CREATE TABLE cd.facilities(
	facid integer NOT NULL,
	name character varying(100) NOT NULL,
	membercost numeric NOT NULL,
	guestcost numeric NOT NULL,
	initialoutlay numeric NOT NULL,
	monthlymaintenance numeric NOT NULL,
	CONSTRAINT facilities_pk PRIMARY KEY (facid)
);

CREATE TABLE cd.bookings(
	facid integer NOT NULL,
	memid integer NOT NULL,
	starttime timestamp NOT NULL,
	slots integer NOT NULL,
	CONSTRAINT bookings_pk PRIMARY KEY (facid),
	CONSTRAINT bookings_memid_fk FOREIGN KEY (memid) REFERENCES cd.members(memid) ON DELETE SET NULL,
	CONSTRAINT bookings_facid_fk FOREIGN KEY (facid) REFERENCES cd.facilities(facid) ON DELETE SET NULL
);



