-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 09, 2017 at 01:11 AM
-- Server version: 10.1.22-MariaDB
-- PHP Version: 7.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `university`
--

-- --------------------------------------------------------

--
-- Table structure for table `building`
--

CREATE TABLE `building` (
  `B_Code` varchar(10) NOT NULL,
  `B_Name` varchar(25) NOT NULL,
  `Location` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `building`
--

INSERT INTO `building` (`B_Code`, `B_Name`, `Location`) VALUES
('CB', 'Chicago Bears', 'Warren Street, NJIT'),
('DC', 'Dallas Cowboys', 'Sumit Street, NJIT'),
('NEP', 'New England Patriots', 'Warren Street, NJIT'),
('PE', 'Philadelphia Eagles', 'Sumit Street, NJIT');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `C_Code` varchar(10) NOT NULL,
  `C_Name` varchar(20) NOT NULL,
  `Credit` int(1) NOT NULL,
  `TAReq` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`C_Code`, `C_Name`, `Credit`, `TAReq`) VALUES
('CS111', 'Data Structures', 4, 2),
('CS213', 'Software Methodology', 4, 2),
('MT233', 'Intro to Calculus', 3, 1),
('PY101', 'Intro to Physics I', 3, 1),
('PY102', 'Intro to Physics II', 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `D_Code` varchar(6) NOT NULL,
  `Dept_Name` varchar(25) NOT NULL,
  `C_PersonID` bigint(11) NOT NULL,
  `A_Budget` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`D_Code`, `Dept_Name`, `C_PersonID`, `A_Budget`) VALUES
('CS', 'Computer Science', 1234567890, 1000000),
('MT', 'Mathematics', 7418989802, 500000),
('PY', 'Physics', 8521471230, 200000);

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE `faculty` (
  `Staff_ID` bigint(10) NOT NULL,
  `Course_Load` int(2) NOT NULL,
  `Rank` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`Staff_ID`, `Course_Load`, `Rank`) VALUES
(1234567890, 3, 'Beginner'),
(7418989802, 2, 'Intermediate'),
(8521470236, 1, 'Experienced'),
(8521471230, 2, 'Intermediate');

-- --------------------------------------------------------

--
-- Table structure for table `major`
--

CREATE TABLE `major` (
  `M_Name` varchar(20) NOT NULL,
  `D_Code` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `major`
--

INSERT INTO `major` (`M_Name`, `D_Code`) VALUES
('Computer Science', 'CS'),
('Math', 'MT'),
('Physics', 'PY');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `Room_No` varchar(10) NOT NULL,
  `B_Code` varchar(10) NOT NULL,
  `Capacity` int(3) NOT NULL,
  `AV_Equip` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`Room_No`, `B_Code`, `Capacity`, `AV_Equip`) VALUES
('105', 'NEP', 25, 0),
('111', 'CB', 50, 0),
('222', 'DC', 100, 1),
('305', 'PE', 300, 1);

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE `section` (
  `Sec_No` int(5) NOT NULL,
  `Room_No` varchar(10) NOT NULL,
  `C_Code` varchar(10) NOT NULL,
  `Instructor_ID` bigint(10) NOT NULL,
  `Lec_Time` time NOT NULL,
  `NumDays` int(1) NOT NULL,
  `MaxEnrollment` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `section`
--

INSERT INTO `section` (`Sec_No`, `Room_No`, `C_Code`, `Instructor_ID`, `Lec_Time`, `NumDays`, `MaxEnrollment`) VALUES
(1002, '105', 'CS111', 1234567890, '12:00:00', 3, 10),
(1003, '111', 'CS111', 1234567890, '09:00:00', 3, 10),
(5112, '111', 'CS213', 1234567890, '09:00:00', 3, 5),
(5113, '105', 'CS213', 1234567890, '15:00:00', 3, 5),
(5578, '305', 'PY101', 8521470236, '19:00:00', 3, 10),
(8852, '105', 'PY102', 8521471230, '08:30:00', 2, 6),
(8892, '222', 'MT233', 7418989802, '15:00:00', 3, 7);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `SSN` bigint(10) NOT NULL,
  `FirstName` varchar(25) NOT NULL,
  `LastName` varchar(25) NOT NULL,
  `City` varchar(10) NOT NULL,
  `State` varchar(10) NOT NULL,
  `Salary` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`SSN`, `FirstName`, `LastName`, `City`, `State`, `Salary`) VALUES
(1234567890, 'John', 'Smith', 'Newark', 'NJ', 56000),
(1472583690, 'Mary', 'Kom', 'Edison', 'NJ', 40000),
(3332221110, 'Glen', 'Mcgrath', 'Crnabury', 'NJ', 30000),
(7418989802, 'Venu', 'Dudela', 'Monroe', 'NJ', 85000),
(8521470236, 'Mark', 'Collingwood', 'Hoboken', 'NJ', 90000),
(8521471230, 'Brett', 'Lee', 'PerthAmboy', 'NJ', 75000);

-- --------------------------------------------------------

--
-- Table structure for table `staffdepartment`
--

CREATE TABLE `staffdepartment` (
  `SSN` bigint(10) NOT NULL,
  `D_Code` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staffdepartment`
--

INSERT INTO `staffdepartment` (`SSN`, `D_Code`) VALUES
(1234567890, 'CS'),
(1472583690, 'MT'),
(3332221110, 'CS'),
(7418989802, 'MT'),
(8521470236, 'PY'),
(8521471230, 'PY');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `SID` int(9) NOT NULL,
  `SSN` bigint(10) NOT NULL,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Major` varchar(25) NOT NULL,
  `City` varchar(20) NOT NULL,
  `State` varchar(20) NOT NULL,
  `HighSchool` varchar(15) NOT NULL,
  `Year` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`SID`, `SSN`, `FirstName`, `LastName`, `Major`, `City`, `State`, `HighSchool`, `Year`) VALUES
(1, 4789452310, 'Parth', 'Patel', 'Computer Science', 'Somerset', 'NJ', 'Franklin', 2011),
(2, 9517536840, 'Tom', 'Hardy', 'Math', 'Edison', 'NJ', 'Edison ', 2013),
(3, 8796874130, 'Alex', 'Phoy', 'Math', 'Bernands', 'NJ', 'Bernads', 2012),
(4, 7569843256, 'Maggie', 'Corn', 'Physics', 'Scranton', 'PA', 'Scranton HS', 2010),
(5, 4488663210, 'Matt', 'Hampton', 'Computer Science', 'Monroe', 'NJ', 'Monroe HS', 2011);

-- --------------------------------------------------------

--
-- Table structure for table `studentsection`
--

CREATE TABLE `studentsection` (
  `SID` int(9) NOT NULL,
  `Sec_No` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studentsection`
--

INSERT INTO `studentsection` (`SID`, `Sec_No`) VALUES
(1, 1002),
(3, 8892),
(4, 5578),
(5, 5112),
(4, 8852),
(1, 8892),
(1, 5112),
(2, 5578),
(2, 8892),
(5, 5113),
(4, 1003),
(2, 1002);

-- --------------------------------------------------------

--
-- Table structure for table `ta`
--

CREATE TABLE `ta` (
  `Staff_ID` bigint(10) NOT NULL,
  `Work/Hr` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ta`
--

INSERT INTO `ta` (`Staff_ID`, `Work/Hr`) VALUES
(1472583690, 8),
(3332221110, 12);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `building`
--
ALTER TABLE `building`
  ADD PRIMARY KEY (`B_Code`),
  ADD UNIQUE KEY `B_Code` (`B_Code`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`C_Code`),
  ADD UNIQUE KEY `C_Code` (`C_Code`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`D_Code`),
  ADD UNIQUE KEY `D_Code` (`D_Code`),
  ADD UNIQUE KEY `Dept_Name` (`Dept_Name`),
  ADD KEY `C_PersonID` (`C_PersonID`);

--
-- Indexes for table `faculty`
--
ALTER TABLE `faculty`
  ADD KEY `Staff_ID` (`Staff_ID`);

--
-- Indexes for table `major`
--
ALTER TABLE `major`
  ADD PRIMARY KEY (`M_Name`),
  ADD UNIQUE KEY `M_Name` (`M_Name`),
  ADD KEY `D_Code` (`D_Code`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`Room_No`),
  ADD UNIQUE KEY `Room_No` (`Room_No`),
  ADD KEY `B_Code` (`B_Code`);

--
-- Indexes for table `section`
--
ALTER TABLE `section`
  ADD PRIMARY KEY (`Sec_No`),
  ADD UNIQUE KEY `Sec_No` (`Sec_No`),
  ADD KEY `C_Code` (`C_Code`),
  ADD KEY `Room_No` (`Room_No`),
  ADD KEY `Instructor_ID` (`Instructor_ID`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`SSN`),
  ADD UNIQUE KEY `SSN` (`SSN`);

--
-- Indexes for table `staffdepartment`
--
ALTER TABLE `staffdepartment`
  ADD KEY `SSN` (`SSN`),
  ADD KEY `D_Code` (`D_Code`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`SID`),
  ADD KEY `Major` (`Major`);

--
-- Indexes for table `studentsection`
--
ALTER TABLE `studentsection`
  ADD KEY `studentsection_ibfk_1` (`SID`),
  ADD KEY `studentsection_ibfk_2` (`Sec_No`);

--
-- Indexes for table `ta`
--
ALTER TABLE `ta`
  ADD KEY `Staff_ID` (`Staff_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `SID` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `department`
--
ALTER TABLE `department`
  ADD CONSTRAINT `department_ibfk_1` FOREIGN KEY (`C_PersonID`) REFERENCES `faculty` (`Staff_ID`);

--
-- Constraints for table `faculty`
--
ALTER TABLE `faculty`
  ADD CONSTRAINT `faculty_ibfk_1` FOREIGN KEY (`Staff_ID`) REFERENCES `staff` (`SSN`);

--
-- Constraints for table `major`
--
ALTER TABLE `major`
  ADD CONSTRAINT `D_Code` FOREIGN KEY (`D_Code`) REFERENCES `department` (`D_Code`);

--
-- Constraints for table `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `room_ibfk_1` FOREIGN KEY (`B_Code`) REFERENCES `building` (`B_Code`);

--
-- Constraints for table `section`
--
ALTER TABLE `section`
  ADD CONSTRAINT `section_ibfk_1` FOREIGN KEY (`C_Code`) REFERENCES `course` (`C_Code`),
  ADD CONSTRAINT `section_ibfk_2` FOREIGN KEY (`Room_No`) REFERENCES `room` (`Room_No`),
  ADD CONSTRAINT `section_ibfk_3` FOREIGN KEY (`Instructor_ID`) REFERENCES `faculty` (`Staff_ID`);

--
-- Constraints for table `staffdepartment`
--
ALTER TABLE `staffdepartment`
  ADD CONSTRAINT `staffdepartment_ibfk_1` FOREIGN KEY (`SSN`) REFERENCES `staff` (`SSN`),
  ADD CONSTRAINT `staffdepartment_ibfk_2` FOREIGN KEY (`D_Code`) REFERENCES `department` (`D_Code`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `Major` FOREIGN KEY (`Major`) REFERENCES `major` (`M_Name`);

--
-- Constraints for table `studentsection`
--
ALTER TABLE `studentsection`
  ADD CONSTRAINT `studentsection_ibfk_1` FOREIGN KEY (`SID`) REFERENCES `student` (`SID`),
  ADD CONSTRAINT `studentsection_ibfk_2` FOREIGN KEY (`Sec_No`) REFERENCES `section` (`Sec_No`);

--
-- Constraints for table `ta`
--
ALTER TABLE `ta`
  ADD CONSTRAINT `ta_ibfk_1` FOREIGN KEY (`Staff_ID`) REFERENCES `staff` (`SSN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
