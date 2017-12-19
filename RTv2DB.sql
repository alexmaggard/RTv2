--
-- to create a database, always drop the name you are going to use for the database
-- to ensure that you're not saving on top of or using another database with the same name
--
DROP DATABASE IF EXISTS capstone;

CREATE DATABASE capstone;

USE capstone;

--
-- dropping weak tables first so strogn entities can be dropped if tables have referential integrity
--

DROP TABLE IF EXISTS CS_TimeInAndOut;
DROP TABLE IF EXISTS CS_Employees;
DROP TABLE IF EXISTS CS_WorkHours;

-- create table for employees
CREATE TABLE CS_Employees (
	EmployeeID INT NOT NULL auto_increment,
	FirstName VARCHAR(128),
	LastName VARCHAR(128),
	PayRate DOUBLE,
	AuthLevel INT,
	Status BOOLEAN,
	Password VARCHAR(128),
	PRIMARY KEY (EmployeeID)
);

-- AuthLevel: 1 = managerial position, 2 = normal employee
INSERT INTO CS_Employees(EmployeeID, FirstName, LastName, PayRate, AuthLevel, Status, Password) VALUES
	(1,'Alex','Maggard',50.50,1,TRUE, 'test'),
	(2,'Daniel','Martinez',45.6,2,FALSE, 'test'),
	(3,'John','Alcala',45.6,2,TRUE, 'test');

-- create table for work hours
CREATE TABLE CS_WorkHours (
	DayID VARCHAR(128) NOT NULL,
	EmployeeID INT,
	StartTime VARCHAR(128),
	LunchOut VARCHAR(128),
	LunchIn VARCHAR(128),
	EndTime VARCHAR(128),
	PRIMARY KEY (DayID),
	FOREIGN KEY (EmployeeID) REFERENCES CS_Employees(EmployeeID)
);

INSERT INTO CS_WorkHours(EmployeeID, DayID, StartTime, LunchOut, LunchIn, EndTime) VALUES
	 (1, NOW(),NOW(),NOW(),'21:50:50','21:51:00'),
	 (2, '2017-11-19','08:30:30','08:30:30','08:30:30','08:30:30');
/*	 
-- create connecting table for employee and work hours tables
CREATE TABLE CS_TimeInAndOut (
	eid_fk INT UNSIGNED NOT NULL,
	did_fk DATE NOT NULL,
	PRIMARY KEY (eid_fk, did_fk),
	CONSTRAINT FK1_CS_TimeInAndOut
		FOREIGN KEY (eid_fk)
		REFERENCES CS_Employees(EmployeeID)
		ON DELETE RESTRICT
		ON UPDATE CASCADE,
	CONSTRAINT FK2_CS_TimeInAndOut 
		FOREIGN KEY (did_fk)
		REFERENCES CS_WorkHours(date_id)
		ON DELETE RESTRICT
		ON UPDATE CASCADE
);

INSERT INTO CS_TimeInAndOut (eid_fk, did_fk) VALUES
(1,'2017-11-18'),
(2,'2017-11-18'),
(3,'2017-11-18'),
(1,'2017-11-19');*/