DROP TABLE PCATEGORY CASCADE CONSTRAINTS;
DROP TABLE PRODUCT CASCADE CONSTRAINTS;
DROP TABLE USERES CASCADE CONSTRAINTS;
DROP TABLE DELIVERYBOY CASCADE CONSTRAINTS;
DROP TABLE BILL CASCADE CONSTRAINTS;
DROP TABLE BILLPRODUCT CASCADE CONSTRAINTS;
DROP TABLE BOYORDER CASCADE CONSTRAINTS;
Drop table CreditCard cascade constraints;

CREATE TABLE PCATEGORY
(CategoryName VARCHAR2(50) PRIMARY KEY);

CREATE TABLE PRODUCT
(ProductName VARCHAR2(50) PRIMARY KEY,
 Description VARCHAR2(100),
 Price NUMBER,
 Quantity NUMBER,
 CategoryName VARCHAR(50),
 CONSTRAINTS fkProduct FOREIGN KEY(CategoryName) REFERENCES PCATEGORY(CategoryName));


CREATE TABLE USERES
(UserName VARCHAR2(50) PRIMARY KEY,
 UserPassword VARCHAR2(50),
 FullName VARCHAR(50),
 MobileNumber VARCHAR(12),
 USERTYPE VARCHAR2(20));

 CREATE TABLE DELIVERYBOY
(BoyName VARCHAR2(50) PRIMARY KEY,
 MobileNumber VARCHAR2(12),
 Address VARCHAR(50),
 Age NUMBER,
 STATUS VARCHAR2(50));
 
 
 CREATE TABLE BILL
(BillId NUMBER PRIMARY KEY,
 BillDate DATE,
 Customer VARCHAR(50)  REFERENCES USERES(UserName),
 Deliveryboy VARCHAR(50) REFERENCES DELIVERYBOY(BoyName),
 TotalPrice NUMBER);
 
 
 CREATE TABLE BILLPRODUCT
(BILLID NUMBER REFERENCES BILL(BILLID),
 PRODUCTNAME VARCHAR2(50) REFERENCES PRODUCT(ProductName),
 CONSTRAINTS pkBillProduct PRIMARY KEY(BILLID, PRODUCTNAME));

CREATE TABLE BOYORDER
(BILLID NUMBER REFERENCES BILL(BILLID),
 BOYNAME VARCHAR2(50) REFERENCES DELIVERYBOY(BOYNAME),
 STATUS VARCHAR2(1),
 CONSTRAINTS pkBillOrder PRIMARY KEY(BILLID, BOYNAME));

Create table CreditCard
(Nameoncard varchar(50),
Numberr varchar2(20),
CVV varchar2(20),
Expiration_date varchar2(20));


INSERT INTO PCATEGORY VALUES ('Clothes');
INSERT INTO PCATEGORY VALUES ('Phones');
INSERT INTO PCATEGORY VALUES ('Electronics');
INSERT INTO PCATEGORY VALUES ('Computing');

INSERT INTO PRODUCT VALUES ('Shirt', 'Style: super comfortable, relaxed fit, lightweight and fashionable.', 200, 20, 'Clothes');
INSERT INTO PRODUCT VALUES ('Shoes', 'Upper Material:Genuine Leather', 150, 10, 'Clothes');
INSERT INTO PRODUCT VALUES ('T-Shirt', 'Men Crew Neck T-Shirt with Short Sleeves - DARK BLACK', 120, 15, 'Clothes');
INSERT INTO PRODUCT VALUES ('Jacket', 'Cotton material, Zipper closure, Long Sleeve and Side pockets', 200, 5, 'Clothes');

INSERT INTO PRODUCT VALUES ('Samsung','Samsung Galaxy J6+ - 6.0-inch 32GB 4G Mobile Phone - Red', 2700, 10, 'Phones');
INSERT INTO PRODUCT VALUES ('Xiaomi','Xiaomi Redmi 7 - 6.26-inch 64GB Dual SIM 4G Mobile Phone - Eclipse Black', 3000, 8, 'Phones');
INSERT INTO PRODUCT VALUES ('Apple','Apple iPhone 6s Plus - 32GB - Silver', 7000, 3, 'Phones');
INSERT INTO PRODUCT VALUES ('Huawei','Huawei Y5 Lite - 5.45-inch 16GB 4G Mobile Phone - Blue', 1500, 5, 'Phones');

INSERT INTO PRODUCT VALUES ('TV','Samsung UA65RU7100 - 65-inch HDR Flat 4K UHD Smart TV', 15000, 5, 'Electronics');
INSERT INTO PRODUCT VALUES ('Receiver','Jac NGR-666 Full HD Mini Receiver', 200, 10, 'Electronics');
INSERT INTO PRODUCT VALUES ('HDMI Cable','Generic High Speed HDMI To HDMI Cable - 3 Meters', 50, 5, 'Electronics');
INSERT INTO PRODUCT VALUES ('PlayStation','Sony PlayStation 4 Slim - 1TB Gaming Console - Black', 6666, 3, 'Electronics');

INSERT INTO PRODUCT VALUES ('Lenovo','Lenovo IdeaPad 330-15IKBR Laptop - Intel Core i7 - 8GB RAM - 2TB HDD - Onyx Black', 12000, 4, 'Computing');
INSERT INTO PRODUCT VALUES ('Projector','Unic UC28+ Mini LED Projector Home Cinema Theater Support 1080p', 1600, 10, 'Computing');
INSERT INTO PRODUCT VALUES ('Printer','HP DeskJet 2620 All-in-One Wireless Printer', 500, 20, 'Computing');
INSERT INTO PRODUCT VALUES ('USB','Kingston Data Traveler DT50 USB V3.0 Flash Memory - 32 GB', 100, 30, 'Computing');

INSERT INTO USERES VALUES ('safwan','safwan', 'Safwan', '0154545624', 'MANAGER');
INSERT INTO USERES VALUES ('pola','pola', 'Pola Mikhail', '0128975554', 'CUSTOMER');
INSERT INTO DELIVERYBOY VALUES ('tony','011756465', '21 st el abbassya', 21, 'A');


COMMIT;