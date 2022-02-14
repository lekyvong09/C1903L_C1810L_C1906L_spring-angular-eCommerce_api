# public_spring-angular-eCommerce_api

# Database - book & bookCategory

```
DROP database IF EXISTS spring_angular_ecommerce_2;

CREATE database spring_angular_ecommerce_2;

USE spring_angular_ecommerce_2;

 CREATE TABLE IF NOT EXISTS spring_angular_ecommerce_2.product_category (
   id BIGINT(20) NOT NULL AUTO_INCREMENT,
   category_name VARCHAR(255) NULL DEFAULT NULL,
   PRIMARY KEY (id))
 ENGINE=InnoDB
 AUTO_INCREMENT = 1;

 CREATE TABLE IF NOT EXISTS spring_angular_ecommerce_2.product (
   id BIGINT(20) NOT NULL AUTO_INCREMENT,
   sku VARCHAR(255) DEFAULT NULL,
   name VARCHAR(255) DEFAULT NULL,
   description VARCHAR(1255) DEFAULT NULL,
   unit_price DECIMAL(13,2) DEFAULT NULL,
   image_url VARCHAR(255) DEFAULT NULL,
   active BIT DEFAULT 1,
   units_in_stock INT(11) DEFAULT NULL,
   date_created DATETIME(6) DEFAULT NULL,
   last_updated DATETIME(6) DEFAULT NULL,
   category_id BIGINT(20) NOT NULL,
   PRIMARY KEY (id),
   KEY fk_category (category_id),
   CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES product_category (id)
 ) 
 ENGINE=InnoDB
 AUTO_INCREMENT = 1;

 INSERT INTO product_category(category_name) VALUES ('COMIC');
 INSERT INTO product_category(category_name) VALUES ('FICTION');
 INSERT INTO product_category(category_name) VALUES ('ROMANTIC');
 INSERT INTO product_category(category_name) VALUES ('PROGRAMMING');

 INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
 unit_price, category_id, date_created)
 VALUES ('BOOK-COMIC-1000', 'Superma: Action Comics Volume 5: The House of Kentn', 'The House of Kent, Superman, Superboy, Supergirl, the Legion of Super-Heroes’ Brainiac 5, and Young Justice’s Conner Kent must all unite to face an enemy from another dimension unleashed by the Invisible Mafia! This kind of power can lay waste to an entire family of super-people! All of this plus the future of the Daily Planet revealed!',
 'assets/images/products/BOOK-COMIC-1000.jpg'
 ,1,100,12.99,1, NOW());

 INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
 unit_price, category_id, date_created)
 VALUES ('BOOK-COMIC-1001', 'Batman: The Silver Age Omnibus Vol. 1 ', 'The Caped Crusader is known for protecting the streets of Gotham from the villains who wish to cause harm. Follow along on some of his most adventurous tales in Batman: The Silver Age Omnibus Vol. 1 collecting Batman #101-116',
 'assets/images/products/BOOK-COMIC-1001.jpg'
 ,1,100,99.99,1, NOW());

 INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
 unit_price, category_id, date_created)
VALUES ('BOOK-FICTION-1002', 'The Fifth Science', 'The Fifth Science is a collection of 12 stories, beginning at the start of the Galactic Human Empire and following right through to its final days. We’ll see some untypical things along the way, meet some untypical folk: galactic lighthouses from the distant future, alien tombs from the distant past, murderers, emperors, archaeologists and drunks; mad mathematicians attempting to wake the universe itself up.And when humans have fallen back into savagery, when the secrets of space folding and perfect wisdom are forgotten, we’ll attend the empire’s deathbed, hold its hand as it goes. Unfortunately that may well only be the beginning.',
 'assets/images/products/BOOK-FICTION-1002.jpg'
 ,1,100,24.99,2, NOW());

 INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
 unit_price, category_id, date_created)
 VALUES ('BOOK-ROMANTIC-1003', 'The Summer House: A gorgeous feel good romance that will have you hooked', 'Just when true happiness seems within reach, Callie and Olivia find a diary full of secrets... secrets that stretch across the island, and have the power to turn lives upside down. As Callie reads, she unravels a mystery that makes her heart drop through the floor. Will Callie and Luke be pulled apart by the storm it unleashes, or can true love save them?',
 'assets/images/products/BOOK-ROMANTIC-1003.jpg'
 ,1,100,15.00,3, NOW());

 INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
 unit_price, category_id, date_created)
 VALUES ('BOOK-PROGRAMMING-1004', 'The Art of Computer Programming', 'These four books comprise what easily could be the most important set of information on any serious programmer’s bookshelf.',
 'assets/images/products/BOOK-PROGRAMMING-1004.jpg'
 ,1,100,187.99,4, NOW());
 
 INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
unit_price, category_id, date_created)
VALUES ('BOOK-PROGRAMMING-1005', 'Python Programming for Beginners : The Ultimate Guide for Beginners to Learn Python Programming: Crash Course on Python Programming for Beginners', 'Python is a high-level interpreted programming language that is used throughout the world for general-purpose programming. It is an open-source programming language licensed by both the Free Software Foundation (FSF) and Open-Source Initiative (OSI). Like some other programming languages, its source code is also available under the GNU General Public License (GPL). Throughout this book, we will be focusing more on the Python 3.x version, which is the latest and is currently in active development.',
'assets/images/products/BOOK-PROGRAMMING-1005.jpg'
,1,100,21.99,4, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
unit_price, category_id, date_created)
VALUES ('BOOK-PROGRAMMING-1006', 'The Self-Taught Programmer: The Definitive Guide to Programming Professionally', 'This book is not just about learning to program; although you will learn to code. If you want to program professionally, it is not enough to learn to code; that is why, in addition to helping you learn to program, I also cover the rest of the things you need to know to program professionally that classes and books don\'t teach you. "The Self-taught Programmer" is a roadmap, a guide to take you from writing your first Python program, to passing your first technical interview.',
'assets/images/products/BOOK-PROGRAMMING-1006.jpg'
,1,100,21.87,4, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
unit_price, category_id, date_created)
VALUES ('BOOK-PROGRAMMING-1007', 'Computer Programming: The Bible: Learn from the basics to advanced of Python, C, C++, C#, HTML Coding, and Black Hat Hacking Step-by-Step in No Time!', 'Are you ready to learn and start programming with any language in less than 12 hours? The world of technology is changing and those who know how to handle it and who have the most knowledge about it are the ones who will get ahead. If you are a beginner who is interested in learning more and getting ahead, then this guidebook is the one for you.',
'assets/images/products/BOOK-PROGRAMMING-1007.jpg'
,1,100,14.95,4, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
unit_price, category_id, date_created)
VALUES ('BOOK-PROGRAMMING-1008', 'Effective C: An Introduction to Professional C Programming', 'Effective C will teach you how to write professional, secure, and portable C code that will stand the test of time and help strengthen the foundation of the computing world.',
'assets/images/products/BOOK-PROGRAMMING-1008.jpg'
,1,100,35.01,4, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
unit_price, category_id, date_created)
VALUES ('BOOK-PROGRAMMING-1009', 'Head First Design Patterns: Building Extensible and Maintainable Object-Oriented Software 2nd Edition', 'If you\'ve read a Head First book, you know what to expect: a visually rich format designed for the way your brain works. With Head First Design Patterns, 2E you\'ll learn design principles and patterns in a way that won\'t put you to sleep, so you can get out there to solve software design problems and speak the language of patterns with others on your team.',
'assets/images/products/BOOK-PROGRAMMING-1009.jpg'
,1,100,32.43,4, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
unit_price, category_id, date_created)
VALUES ('BOOK-PROGRAMMING-1010', 'Beginning Programming All-in-One Desk Reference For Dummies', 'Beginning Programming All In One Desk Reference For Dummies shows you how to decide what you want your program to do, turn your instructions into “machine language” that the computer understands, use programming best practices, explore the “how” and “why” of data structuring, and more. You’ll even get a look into various applications like database management, bioinformatics, computer security, and artificial intelligence. Soon you’ll realize that — wow! You’re a programmer!',
'assets/images/products/BOOK-PROGRAMMING-1010.jpg'
,1,100,32.89,4, NOW());

INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
unit_price, category_id, date_created)
VALUES ('BOOK-PROGRAMMING-1011', 'Machine Learning: 4 Books in 1: A Complete Overview for Beginners to Master the Basics of Python Programming and Understand How to Build Artificial Intelligence Through Data Science', 'Created with the beginner in mind, this powerful bundle delves into the fundamentals behind Python and machine learning, from basic code and mathematical formulas to complex neural networks and ensemble modeling. Inside, you’ll discover everything you need to know to get started with Python and machine learning and begin your journey to success!',
'assets/images/products/BOOK-PROGRAMMING-1011.jpg'
,1,100,35.01,4, NOW());


USE spring_angular_ecommerce_2;

 INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
 unit_price, category_id, date_created)
 VALUES ('BOOK-ROMANTIC-1012', 'The Summer House: A gorgeous feel good romance that will have you hooked', 'Just when true happiness seems within reach, Callie and Olivia find a diary full of secrets... secrets that stretch across the island, and have the power to turn lives upside down. As Callie reads, she unravels a mystery that makes her heart drop through the floor. Will Callie and Luke be pulled apart by the storm it unleashes, or can true love save them?',
 'assets/images/products/placeholder.png'
 ,1,100,15.00,3, NOW());
  INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
 unit_price, category_id, date_created)
 VALUES ('BOOK-ROMANTIC-1013', 'The Summer House: A gorgeous feel good romance that will have you hooked', 'Just when true happiness seems within reach, Callie and Olivia find a diary full of secrets... secrets that stretch across the island, and have the power to turn lives upside down. As Callie reads, she unravels a mystery that makes her heart drop through the floor. Will Callie and Luke be pulled apart by the storm it unleashes, or can true love save them?',
 'assets/images/products/placeholder.png'
 ,1,100,15.00,3, NOW());
  INSERT INTO product (sku, name, description, image_url, active, units_in_stock,
 unit_price, category_id, date_created)
 VALUES ('BOOK-ROMANTIC-1014', 'The Summer House: A gorgeous feel good romance that will have you hooked', 'Just when true happiness seems within reach, Callie and Olivia find a diary full of secrets... secrets that stretch across the island, and have the power to turn lives upside down. As Callie reads, she unravels a mystery that makes her heart drop through the floor. Will Callie and Luke be pulled apart by the storm it unleashes, or can true love save them?',
 'assets/images/products/placeholder.png'
 ,1,100,15.00,3, NOW());
```

# Database - Countries & States
```
USE spring_angular_ecommerce_2;

 SET foreign_key_checks = 0;

 DROP TABLE IF EXISTS country;

 CREATE TABLE country (
   id smallint unsigned NOT NULL,
   code varchar(2) DEFAULT NULL,
   name varchar(255) DEFAULT NULL,
   PRIMARY KEY (id)
 ) ENGINE=InnoDB;


 INSERT INTO country VALUES 
 (1,'BR','Brazil'),
 (2,'CA','Canada'),
 (3,'DE','Germany'),
 (4,'IN','India'),
 (5,'TR','Turkey'),
 (6,'US','United States');


 DROP TABLE IF EXISTS state;

 CREATE TABLE state (
   id smallint unsigned NOT NULL AUTO_INCREMENT,
   name varchar(255) DEFAULT NULL,
   country_id smallint unsigned NOT NULL,
   PRIMARY KEY (id),
   KEY fk_country (country_id),
   CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES country (id)
 ) ENGINE=InnoDB AUTO_INCREMENT=1;


 INSERT INTO state VALUES 
 (1,'Acre',1),
 (2,'Alagoas',1),
 (3,'Amapá',1),
 (4,'Amazonas',1),
 (5,'Bahia',1),
 (6,'Ceará',1),
 (7,'Distrito Federal',1),
 (8,'Espírito Santo',1),
 (9,'Goiás',1),
 (10,'Maranhão',1),
 (11,'Mato Grosso do Sul',1),
 (12,'Mato Grosso',1),
 (13,'Minas Gerais',1),
 (14,'Paraná',1),
 (15,'Paraíba',1),
 (16,'Pará',1),
 (17,'Pernambuco',1),
 (18,'Piaui',1),
 (19,'Rio de Janeiro',1),
 (20,'Rio Grande do Norte',1),
 (21,'Rio Grande do Sul',1),
 (22,'Rondônia',1),
 (23,'Roraima',1),
 (24,'Santa Catarina',1),
 (25,'Sergipe',1),
 (26,'São Paulo',1),
 (27,'Tocantins',1),
 (28,'Alberta',2),
 (29,'British Columbia',2),
 (30,'Manitoba',2),
 (31,'New Brunswick',2),
 (32,'Newfoundland and Labrador',2),
 (33,'Northwest Territories',2),
 (34,'Nova Scotia',2),
 (35,'Nunavut',2),
 (36,'Ontario',2),
 (37,'Prince Edward Island',2),
 (38,'Quebec',2),
 (39,'Saskatchewan',2),
 (40,'Yukon',2),
 (41,'Baden-Württemberg',3),
 (42,'Bavaria',3),
 (43,'Berlin',3),
 (44,'Brandenburg',3),
 (45,'Bremen',3),
 (46,'Hamburg',3),
 (47,'Hesse',3),
 (48,'Lower Saxony',3),
 (49,'Mecklenburg-Vorpommern',3),
 (50,'North Rhine-Westphalia',3),
 (51,'Rhineland-Palatinate',3),
 (52,'Saarland',3),
 (53,'Saxony',3),
 (54,'Saxony-Anhalt',3),
 (55,'Schleswig-Holstein',3),
 (56,'Thuringia',3),
 (57,'Andhra Pradesh',4),
 (58,'Arunachal Pradesh',4),
 (59,'Assam',4),
 (60,'Bihar',4),
 (61,'Chhattisgarh',4),
 (62,'Goa',4),
 (63,'Gujarat',4),
 (64,'Haryana',4),
 (65,'Himachal Pradesh',4),
 (66,'Jammu & Kashmir',4),
 (67,'Jharkhand',4),
 (68,'Karnataka',4),
 (69,'Kerala',4),
 (70,'Madhya Pradesh',4),
 (71,'Maharashtra',4),
 (72,'Manipur',4),
 (73,'Meghalaya',4),
 (74,'Mizoram',4),
 (75,'Nagaland',4),
 (76,'Odisha',4),
 (77,'Punjab',4),
 (78,'Rajasthan',4),
 (79,'Sikkim',4),
 (80,'Tamil Nadu',4),
 (81,'Telangana',4),
 (82,'Tripura',4),
 (83,'Uttar Pradesh',4),
 (84,'Uttarakhand',4),
 (85,'West Bengal',4),
 (86,'Andaman and Nicobar Islands',4),
 (87,'Chandigarh',4),
 (88,'Dadra and Nagar Haveli',4),
 (89,'Daman & Diu',4),
 (90,'Lakshadweep',4),
 (91,'Puducherry',4),
 (92,'The Government of NCT of Delhi',4),
 (93,'Alabama',6),
 (94,'Alaska',6),
 (95,'Arizona',6),
 (96,'Arkansas',6),
 (97,'California',6),
 (98,'Colorado',6),
 (99,'Connecticut',6),
 (100,'Delaware',6),
 (101,'District Of Columbia',6),
 (102,'Florida',6),
 (103,'Georgia',6),
 (104,'Hawaii',6),
 (105,'Idaho',6),
 (106,'Illinois',6),
 (107,'Indiana',6),
 (108,'Iowa',6),
 (109,'Kansas',6),
 (110,'Kentucky',6),
 (111,'Louisiana',6),
 (112,'Maine',6),
 (113,'Maryland',6),
 (114,'Massachusetts',6),
 (115,'Michigan',6),
 (116,'Minnesota',6),
 (117,'Mississippi',6),
 (118,'Missouri',6),
 (119,'Montana',6),
 (120,'Nebraska',6),
 (121,'Nevada',6),
 (122,'New Hampshire',6),
 (123,'New Jersey',6),
 (124,'New Mexico',6),
 (125,'New York',6),
 (126,'North Carolina',6),
 (127,'North Dakota',6),
 (128,'Ohio',6),
 (129,'Oklahoma',6),
 (130,'Oregon',6),
 (131,'Pennsylvania',6),
 (132,'Rhode Island',6),
 (133,'South Carolina',6),
 (134,'South Dakota',6),
 (135,'Tennessee',6),
 (136,'Texas',6),
 (137,'Utah',6),
 (138,'Vermont',6),
 (139,'Virginia',6),
 (140,'Washington',6),
 (141,'West Virginia',6),
 (142,'Wisconsin',6),
 (143,'Wyoming',6),
 (144,'Adıyaman',5),
 (145,'Afyonkarahisar',5),
 (146,'Ağrı',5),
 (147,'Aksaray',5),
 (148,'Amasya',5),
 (149,'Ankara',5),
 (150,'Antalya',5),
 (151,'Ardahan',5),
 (152,'Artvin',5),
 (153,'Aydın',5),
 (154,'Balıkesir',5),
 (155,'Bartın',5),
 (156,'Batman',5),
 (157,'Bayburt',5),
 (158,'Bilecik',5),
 (159,'Bingöl',5),
 (160,'Bitlis',5),
 (161,'Bolu',5),
 (162,'Burdur',5),
 (163,'Bursa',5),
 (164,'Çanakkale',5),
 (165,'Çankırı',5),
 (166,'Çorum',5),
 (167,'Denizli',5),
 (168,'Diyarbakır',5),
 (169,'Düzce',5),
 (170,'Edirne',5),
 (171,'Elazığ',5),
 (172,'Erzincan',5),
 (173,'Erzurum',5),
 (174,'Eskişehir',5),
 (175,'Gaziantep',5),
 (176,'Giresun',5),
 (177,'Gümüşhane',5),
 (178,'Hakkâri',5),
 (179,'Hatay',5),
 (180,'Iğdır',5),
 (181,'Isparta',5),
 (182,'İstanbul',5),
 (183,'İzmir',5),
 (184,'Kahramanmaraş',5),
 (185,'Karabük',5),
 (186,'Karaman',5),
 (187,'Kars',5),
 (188,'Kastamonu',5),
 (189,'Kayseri',5),
 (190,'Kırıkkale',5),
 (191,'Kırklareli',5),
 (192,'Kırşehir',5),
 (193,'Kilis',5),
 (194,'Kocaeli',5),
 (195,'Konya',5),
 (196,'Kütahya',5),
 (197,'Malatya',5),
 (198,'Manisa',5),
 (199,'Mardin',5),
 (200,'Mersin',5),
 (201,'Muğla',5),
 (202,'Muş',5),
 (203,'Nevşehir',5),
 (204,'Niğde',5),
 (205,'Ordu',5),
 (206,'Osmaniye',5),
 (207,'Rize',5),
 (208,'Sakarya',5),
 (209,'Samsun',5),
 (210,'Siirt',5),
 (211,'Sinop',5),
 (212,'Sivas',5),
 (213,'Şanlıurfa',5),
 (214,'Şırnak',5),
 (215,'Tekirdağ',5),
 (216,'Tokat',5),
 (217,'Trabzon',5),
 (218,'Tunceli',5),
 (219,'Uşak',5),
 (220,'Van',5),
 (221,'Yalova',5),
 (222,'Yozgat',5),
 (223,'Zonguldak',5);

 SET foreign_key_checks = 1;
```


# Database - Orders & Customer
```
USE spring_angular_ecommerce_2;

 SET FOREIGN_KEY_CHECKS=0;
 DROP TABLE IF EXISTS order_item;
 DROP TABLE IF EXISTS orders;
 DROP TABLE IF EXISTS customer;
 DROP TABLE IF EXISTS address;
 SET FOREIGN_KEY_CHECKS=1;


 CREATE TABLE address (
   id bigint NOT NULL AUTO_INCREMENT,
   city varchar(255) DEFAULT NULL,
   country varchar(255) DEFAULT NULL,
   state varchar(255) DEFAULT NULL,
   street varchar(255) DEFAULT NULL,
   zip_code varchar(255) DEFAULT NULL,
   PRIMARY KEY (id)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


 CREATE TABLE customer (
   id bigint NOT NULL AUTO_INCREMENT,
   first_name varchar(255) DEFAULT NULL,
   last_name varchar(255) DEFAULT NULL,
   email varchar(255) DEFAULT NULL,
   PRIMARY KEY (id)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


 CREATE TABLE orders (
   id bigint NOT NULL AUTO_INCREMENT,
   order_tracking_number varchar(255) DEFAULT NULL,
   total_price decimal(19,2) DEFAULT NULL,
   total_quantity int DEFAULT NULL,
   billing_address_id bigint DEFAULT NULL,
   customer_id bigint DEFAULT NULL,
   shipping_address_id bigint DEFAULT NULL,
   status varchar(128) DEFAULT NULL,
   date_created datetime(6) DEFAULT NULL,
   last_updated datetime(6) DEFAULT NULL,
   PRIMARY KEY (id),
   UNIQUE KEY UK_billing_address_id (billing_address_id),
   UNIQUE KEY UK_shipping_address_id (shipping_address_id),
   KEY K_customer_id (customer_id),
   CONSTRAINT FK_customer_id FOREIGN KEY (customer_id) REFERENCES customer (id),
   CONSTRAINT FK_billing_address_id FOREIGN KEY (billing_address_id) REFERENCES address (id),
   CONSTRAINT FK_shipping_address_id FOREIGN KEY (shipping_address_id) REFERENCES address (id)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


 CREATE TABLE order_item (
   id bigint NOT NULL AUTO_INCREMENT,
   image_url varchar(255) DEFAULT NULL,
   quantity int DEFAULT NULL,
   unit_price decimal(19,2) DEFAULT NULL,
   order_id bigint DEFAULT NULL,
   product_id bigint DEFAULT NULL,
   PRIMARY KEY (id),
   KEY K_order_id (order_id),
   CONSTRAINT FK_order_id FOREIGN KEY (order_id) REFERENCES orders (id),
   CONSTRAINT FK_product_id FOREIGN KEY (product_id) REFERENCES product (id)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

```

# Database User, Role, Privilege
```
use spring_angular_ecommerce_2;
set foreign_key_checks = 0;

drop table if exists authority;
create table authority (
    id int(11) NOT NULL AUTO_INCREMENT,
    privilege varchar(250) NOT NULL,
    PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
insert into authority(privilege) values ('user:read'),('user:update'),('user:create'),('user:delete');


drop table if exists role;
create table role (
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
    
insert into role(name) values ('ROLE_USER_READ'),('ROLE_USER_EDIT'),('ROLE_USER_CREATE'),('ROLE_USER_DELETE');


drop table if exists role_authority;
CREATE TABLE role_authority (
    role_id int(11) default null,
    authority_id int(11) default null,
    
    PRIMARY KEY (role_id, authority_id),
    KEY FK_USER_idx (role_id),
    
	CONSTRAINT FK_role_authority_01 FOREIGN KEY (role_id) REFERENCES role (id) on delete no action on update no action,
    CONSTRAINT FK_role_authority_02 FOREIGN KEY (authority_id) REFERENCES authority (id) on delete no action on update no action
) engine=InnoDB auto_increment=1 default charset=utf8mb4;

insert into role_authority(role_id,authority_id) values (1,1),(2,1),(2,2),(3,1),(3,2),(3,3),(4,1),(4,2),(4,3),(4,4);


drop table if exists user;
create table user (
    id int(11) NOT NULL AUTO_INCREMENT,
    user_id varchar(50) NOT NULL,
    first_name varchar(150) NOT NULL,
    last_name varchar(150) NOT NULL,
    username varchar(50) NOT NULL, 
    password varchar(150) NOT NULL,
    email varchar(250) NULL,
    profile_image_url varchar(1250) NULL,
    last_login_date DATETIME NULL,
    last_login_date_display DATETIME NULL,
    join_date DATETIME NULL,
    is_active tinyint(1) NOT NULL,
    is_not_locked tinyint(1) NOT NULL,
    PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


drop table if exists user_role;
CREATE TABLE user_role (
    user_id int(11) default null,
    role_id int(11) default null,
    
    PRIMARY KEY (user_id, role_id),
    KEY FK_USER_idx (user_id),

	CONSTRAINT FK_user_role_01 FOREIGN KEY (user_id) REFERENCES user (id) on delete no action on update no action,
    CONSTRAINT FK_user_role_02 FOREIGN KEY (role_id) REFERENCES role (id) on delete no action on update no action
) engine=InnoDB auto_increment=1 default charset=utf8mb4;


drop table if exists user_authority;
CREATE TABLE user_authority (
    user_id int(11) default null,
    authority_id int(11) default null,
    
    PRIMARY KEY (user_id, authority_id),
    KEY FK_USER_idx (user_id),

	CONSTRAINT FK_user_authority_01 FOREIGN KEY (user_id) REFERENCES user (id) on delete no action on update no action,
    CONSTRAINT FK_user_authority_02 FOREIGN KEY (authority_id) REFERENCES authority (id) on delete no action on update no action
) engine=InnoDB auto_increment=1 default charset=utf8mb4;


set foreign_key_checks = 1;
```


# Database - Replace Customer table with User
```
USE spring_angular_ecommerce_2;

 --
 -- Prep work
 --
 SET FOREIGN_KEY_CHECKS=0;
 DROP TABLE IF EXISTS order_item;
 DROP TABLE IF EXISTS orders;
 DROP TABLE IF EXISTS customer;
 DROP TABLE IF EXISTS address;
 SET FOREIGN_KEY_CHECKS=1;

 --
 -- Table structure for table address
 --
 CREATE TABLE address (
   id bigint NOT NULL AUTO_INCREMENT,
   city varchar(255) DEFAULT NULL,
   country varchar(255) DEFAULT NULL,
   state varchar(255) DEFAULT NULL,
   street varchar(255) DEFAULT NULL,
   zip_code varchar(255) DEFAULT NULL,
   PRIMARY KEY (id)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


 --
 -- Table structure for table orders
 --
 CREATE TABLE orders (
   id bigint NOT NULL AUTO_INCREMENT,
   order_tracking_number varchar(255) DEFAULT NULL,
   total_price decimal(19,2) DEFAULT NULL,
   total_quantity int DEFAULT NULL,
   billing_address_id bigint DEFAULT NULL,
   user_id int DEFAULT NULL,
   shipping_address_id bigint DEFAULT NULL,
   status varchar(128) DEFAULT NULL,
   date_created datetime(6) DEFAULT NULL,
   last_updated datetime(6) DEFAULT NULL,
   PRIMARY KEY (id),
   UNIQUE KEY UK_billing_address_id (billing_address_id),
   UNIQUE KEY UK_shipping_address_id (shipping_address_id),
   KEY K_user_id (user_id),
   CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES user (id),
   CONSTRAINT FK_billing_address_id FOREIGN KEY (billing_address_id) REFERENCES address (id),
   CONSTRAINT FK_shipping_address_id FOREIGN KEY (shipping_address_id) REFERENCES address (id)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

 --
 -- Table structure for table order_items
 --
 CREATE TABLE order_item (
   id bigint NOT NULL AUTO_INCREMENT,
   image_url varchar(255) DEFAULT NULL,
   quantity int DEFAULT NULL,
   unit_price decimal(19,2) DEFAULT NULL,
   order_id bigint DEFAULT NULL,
   product_id bigint DEFAULT NULL,
   PRIMARY KEY (id),
   KEY K_order_id (order_id),
   CONSTRAINT FK_order_id FOREIGN KEY (order_id) REFERENCES orders (id),
   CONSTRAINT FK_product_id FOREIGN KEY (product_id) REFERENCES product (id)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
```

### Database - Add column isFavorite to Product table
```
 ALTER TABLE spring_angular_ecommerce.product ADD COLUMN is_favorite BIT DEFAULT 0;
```