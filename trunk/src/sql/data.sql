INSERT INTO T_USER (ID,PASSWORD,ADDRESS,CONTACT,EMAIL,FIRSTNAME,LASTNAME,NAME,CODE,CREATEDDATE,MODIFIEDDATE,CREATEDBY,MODIFIEDBY,STATUS,ACTIVE) VALUES (1,'wLE3/i15JFnyb/djzORFdKW1qwM=','admin','admin','admin','admin','admin','admin',null,null,null,null,null,null,1);

insert into T_ROLE (NAME, code, active, id) 
values ('Hotel_Admin', 'Hotel_Admin',1,1);

insert into T_ROLE (NAME, code, active, id) 
values ('Sys_Admin', 'Sys_Admin',1,2);

insert into T_ROLE (NAME, code, active, id) 
values ('Customer', 'Customer',1,3);

insert into T_ROLE (NAME, code, active, id) 
values ('Agent', 'Agent',1,4);

INSERT INTO T_USER_T_ROLE (T_USER_ID,ROLES_ID) VALUES (1,2);

INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(1,'sys.menu.manageuser.newuser','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(2,'sys.menu.manageuser.newrole','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(3,'sys.menu.manageuser.newcustomer','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(4,'sys.menu.manageuser.assignrolefunctions','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(5,'sys.menu.manageuser.main','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(6,'sys.menu.managehotel.main','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(7,'sys.menu.managehotel.newhotel','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(8,'sys.menu.managehotel.extraitem','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(9,'sys.menu.manageroom.roomtype','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(10,'sys.menu.manageroom.newroom','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(11,'sys.menu.manageseason.main','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(12,'sys.menu.manageseason.newseason','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(13,'sys.menu.manageseason.seasonalrate','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(14,'sys.menu.manageallocation.main','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(15,'sys.menu.manageallocation.roomavailability','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(16,'sys.menu.manageallocation.dailyavailability','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(17,'sys.menu.reservation.main','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(18,'sys.menu.reservation.search','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(19,'sys.menu.reservation.viewbookings','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(20,'sys.menu.reservation.bookingreport','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(21,'sys.menu.reservation.reservationreport','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(22,'sys_logic_isusercanlogin','logic',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(23,'sys.menu.manageuser.resetpw','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(24,'sys.logic.paymenttype.online','logic',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(25,'sys.logic.paymenttype.cash','logic',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(26,'sys.logic.paymenttype.onrequest','logic',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(27,'sys.menu.manageroom.main','menu',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(28,'sys.logic.booking.changebookinguser','logic',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(29,'sys.logic.paymenttype.later','logic',1);
INSERT INTO T_FUNC (ID,FUNC_KEY,FUNC_TYPE,active)VALUES(30,'sys.logic.booking.report.asign.hotels.only','logic',1);

INSERT INTO T_ROLE_T_FUNC (T_ROLE_ID, FUNCTION_ID) VALUES(2,1);
INSERT INTO T_ROLE_T_FUNC (T_ROLE_ID, FUNCTION_ID) VALUES(2,2);
INSERT INTO T_ROLE_T_FUNC (T_ROLE_ID, FUNCTION_ID) VALUES(2,3);
INSERT INTO T_ROLE_T_FUNC (T_ROLE_ID, FUNCTION_ID) VALUES(2,4);
INSERT INTO T_ROLE_T_FUNC (T_ROLE_ID, FUNCTION_ID) VALUES(2,5);
INSERT INTO T_ROLE_T_FUNC (T_ROLE_ID, FUNCTION_ID) VALUES(2,22);
INSERT INTO T_ROLE_T_FUNC (T_ROLE_ID, FUNCTION_ID) VALUES(2,23);