create database bank_krwi;
use bank_krwi;

drop table if exists jednostka_krwi_status;
drop table if exists jednostka_krwi;
drop table if exists bank;
drop table if exists dawca;

#------------------------------------------------------------
drop table if exists dawca;
create table dawca(
dawca_id int primary key not null,
dawca_waga float,
dawca_data_urodzenia date,
dawca_imie_nazwisko varchar(100)
);

#------------------------------------------------------------
drop table if exists bank;
create table bank(
bank_id int primary key not null,
bank_nazwa varchar(50),
bank_miasto varchar(50),
bank_kod_pocztowy varchar(6),
bank_ulica varchar(50),
bank_email varchar(50),
bank_tel varchar(50)
);

#------------------------------------------------------------
drop table if exists jednostka_krwi;
create table jednostka_krwi(
jednostka_krwi_id int primary key not null,
dawca_id int,
foreign key (dawca_id) references dawca(dawca_id),
jednostka_krwi_Rh enum('+','-'),
jednostka_krwi_grupa enum('0','A','B','AB'),
jednostka_krwi_data_oddania date,
bank_id int,
foreign key (bank_id) references bank(bank_id),
empty_var varchar(50)
);

#------------------------------------------------------------
drop table if exists jednostka_krwi_status;
create table jednostka_krwi_status(
jednostka_krwi_id int,
foreign key (jednostka_krwi_id) references jednostka_krwi(jednostka_krwi_id),
dawca_id int,
jednostka_krwi_data_oddania date,
jednostka_krwi_status_var varchar(50)
);





#------------------------------------------------------------# STATUS

drop trigger if exists after_jednostka_krwi_insert;
DELIMITER $$
CREATE TRIGGER after_jednostka_krwi_insert
AFTER INSERT 
ON jednostka_krwi FOR EACH ROW

BEGIN
declare krew_status varchar(50);
set krew_status = 'gotowa do transfuzji';

insert into jednostka_krwi_status (jednostka_krwi_id, dawca_id, jednostka_krwi_data_oddania) 
SELECT new.jednostka_krwi_id, new.dawca_id, new.jednostka_krwi_data_oddania;

UPDATE jednostka_krwi_status
SET jednostka_krwi_status_var=krew_status;


END $$
 
DELIMITER ;

#------------------------------------------------------------# DATA

drop PROCEDURE if exists sprawdzenie_daty;
DELIMITER $$
CREATE PROCEDURE sprawdzenie_daty
()

BEGIN
	UPDATE jednostka_krwi_status
	SET jednostka_krwi_status_var='do utylizacji'
    WHERE (DATEDIFF(DATE(NOW()), jednostka_krwi_data_oddania) > 42);
END $$
DELIMITER ;

#------------------------------------------------------------# TRANSFUZJA

drop PROCEDURE if exists transfuzja;
DELIMITER $$
CREATE PROCEDURE transfuzja
(id_transfuzja int)

BEGIN
declare stan varchar(50);
select jednostka_krwi_status_var from jednostka_krwi_status where jednostka_krwi_id=id_transfuzja into stan;

if (stan='gotowa do transfuzji') then
update jednostka_krwi_status set  jednostka_krwi_status_var='przetransfuzowana' where jednostka_krwi_id=id_transfuzja;
end if;
    
END $$
DELIMITER ;

#------------------------------------------------------------# WIDOK ZAPASY + PROCEDURA DLA WSZYSTKICH PLACÓWEK

drop PROCEDURE if exists sprawdzenie_zapasow;
DELIMITER $$
CREATE PROCEDURE sprawdzenie_zapasow
()

BEGIN

declare brak_potrzeb varchar (50);
declare mala_potrzeba varchar(50);
declare srednia_potrzeba varchar(50);
declare duza_potrzeba varchar(50);

set brak_potrzeb = 'brak potrzeb';
set mala_potrzeba = 'stan optymalny';
set srednia_potrzeba = 'stan średni';
set duza_potrzeba = 'pilnie potrzebna';

drop table if exists sprawdzenie_zapasow_tabela;
create table sprawdzenie_zapasow_tabela as 
select s.jednostka_krwi_status_var, j.jednostka_krwi_grupa as grupa, j.jednostka_krwi_Rh as Rh, count(jednostka_krwi_grupa) as ilosc, j.bank_id as id_banku, j.empty_var as potrzeba from jednostka_krwi j join jednostka_krwi_status s on j.jednostka_krwi_id=s.jednostka_krwi_id where (s.jednostka_krwi_status_var='gotowa do transfuzji') group by grupa, Rh, id_banku order by id_banku desc;

UPDATE sprawdzenie_zapasow_tabela
SET potrzeba=brak_potrzeb
WHERE ilosc>100;

UPDATE sprawdzenie_zapasow_tabela
SET potrzeba=mala_potrzeba
WHERE ilosc BETWEEN 80 AND 100;

UPDATE sprawdzenie_zapasow_tabela
SET potrzeba=srednia_potrzeba
WHERE ilosc BETWEEN 30 AND 79;

UPDATE sprawdzenie_zapasow_tabela
SET potrzeba=duza_potrzeba
WHERE ilosc BETWEEN 1 AND 29;

drop view if exists sprawdzenie_zapasow_widok;
create view sprawdzenie_zapasow_widok as 
select grupa, Rh, ilosc, potrzeba, id_banku from sprawdzenie_zapasow_tabela group by grupa, Rh, id_banku order by id_banku;


END $$
DELIMITER ;

#------------------------------------------------------------# ZESTAWIEINE DLA ZADANEJ GRUPY - Rh (dane dawcy/zapasy???)

drop PROCEDURE if exists sprawdzenie_zapasow_wybrane;
DELIMITER $$
CREATE PROCEDURE sprawdzenie_zapasow_wybrane
(grupa_input varchar(2), rh_input varchar(1))

BEGIN

drop table if exists sprawdzenie_zapasow_wybrane_tabela;
create table sprawdzenie_zapasow_wybrane_tabela as 
select grupa, Rh, ilosc, potrzeba from sprawdzenie_zapasow_tabela where (grupa_input=grupa and rh_input=Rh);

drop view if exists sprawdzenie_zapasow_wybrane_widok;
create view sprawdzenie_zapasow_wybrane_widok as 
select * from sprawdzenie_zapasow_wybrane_tabela;

END $$
DELIMITER ;

#------------------------------------------------------------# WIDOKI - CENTRUM

drop view if exists bank_widok;
create view bank_widok as 
select * from bank;

drop view if exists dawca_widok;
create view dawca_widok as 
select * from dawca;

drop view if exists dawca_jednostka_krwi_status_widok;
create view dawca_jednostka_krwi_status_widok as 
select d.dawca_id, d.dawca_waga, d.dawca_data_urodzenia, d.dawca_imie_nazwisko, j.jednostka_krwi_id, j.jednostka_krwi_grupa, j.jednostka_krwi_Rh, j.jednostka_krwi_data_oddania, s.jednostka_krwi_status_var, j.bank_id from jednostka_krwi j join dawca d on j.dawca_id=d.dawca_id join jednostka_krwi_status s on j.jednostka_krwi_id=s.jednostka_krwi_id;

# + SPRAWDZENIE_ZAPASÓW_WIDOK

#------------------------------------------------------------# WIDOKI - BANK

drop PROCEDURE if exists bank_jednostki;
DELIMITER $$
CREATE PROCEDURE bank_jednostki
(bank_id_input int)

BEGIN

drop table if exists bank_jednostki_tabela;
create table bank_jednostki_tabela as 
select d.dawca_id, d.dawca_waga, d.dawca_data_urodzenia, d.dawca_imie_nazwisko, j.jednostka_krwi_id, j.jednostka_krwi_grupa, j.jednostka_krwi_Rh, j.jednostka_krwi_data_oddania, s.jednostka_krwi_status_var, j.bank_id from jednostka_krwi j join dawca d on j.dawca_id=d.dawca_id join jednostka_krwi_status s on j.jednostka_krwi_id=s.jednostka_krwi_id where (bank_id_input=j.bank_id);

drop view if exists bank_jednostki_widok;
create view bank_jednostki_widok as 
select * from bank_jednostki_tabela;

select * from bank_jednostki_widok;

END $$
DELIMITER ;

# + SPRAWDZENIE_ZAPASÓW_WIDOK

#------------------------------------------------------------# TEST:

INSERT INTO bank (bank_id, bank_nazwa, bank_miasto, bank_kod_pocztowy, bank_ulica, bank_email, bank_tel)
VALUES (10, 'Bank_1', 'Breslau-Birkenau', '69-430', 'ulica1','Bank_1@mail.ch','+48 100 200 300');
INSERT INTO bank (bank_id, bank_nazwa, bank_miasto, bank_kod_pocztowy, bank_ulica, bank_email, bank_tel)
VALUES (20, 'Bank_2', 'Breslau', '69-430', 'ulica2','Bank_2@mail.ch','+48 100 222 300');



INSERT INTO dawca (dawca_id, dawca_waga, dawca_data_urodzenia, dawca_imie_nazwisko)
VALUES 
(1, 63, '1998-09-13', 'Jurij Owsiak'),
(2, 70, '1999-10-21', 'Artion Kovalienko'),
(3, 79, '1967-11-01', 'Aaron Schultz')
;
INSERT INTO jednostka_krwi (jednostka_krwi_id, dawca_id, jednostka_krwi_Rh, jednostka_krwi_grupa, jednostka_krwi_data_oddania, bank_id)
VALUES 
(1, 1, '+', '0', '2020-02-03', '10'),
(2, 2, '-', 'AB', '2020-02-03', '10'),
(3, 3, '-', 'B', '2020-01-03', '10')
;
INSERT INTO jednostka_krwi (jednostka_krwi_id, dawca_id, jednostka_krwi_Rh, jednostka_krwi_grupa, jednostka_krwi_data_oddania, bank_id)
VALUES 
(4, 1, '+', '0', '2020-02-03', '20'),
(5, 2, '-', 'AB', '2019-02-03', '20'),
(6, 3, '-', 'AB', '2020-01-03', '20')
;

#CALL:
call sprawdzenie_daty(); 					# wykonywane przed każdą procedurą sprawdzenie daty - 'aktualizacja' bazy;
call transfuzja(1);     					# oddanie jednostki_krwi => status: "przetransfuzowana"; 
call sprawdzenie_zapasow;					# sprawdzenie zapasów wszystkich banków (dla grupy, Rh - srotowane po id banku);

INSERT INTO jednostka_krwi (jednostka_krwi_id, dawca_id, jednostka_krwi_Rh, jednostka_krwi_grupa, jednostka_krwi_data_oddania, bank_id)
VALUES 
(4, 1, '+', '0', '2020-02-03', '10')
;

call sprawdzenie_zapasów_wybrane('0', '+'); # przed sprawdzenie_zapasów_wybrane wykonać sprawdzenie_daty(); i sprawdzenie_zapasów();		!!!
call bank_jednostki(10);

#------------------------------------------------------------UŻYTKOWNICY

DROP USER IF EXISTS 'Bank_1'@'localhost';
CREATE USER 'Bank_1'@'localhost' IDENTIFIED BY 'Bank_1'; 
GRANT ALL ON bank_krwi TO 'Bank_1'@'localhost'; 
GRANT EXECUTE ON PROCEDURE sprawdzenie_daty TO 'Bank_1'@'localhost'; 
GRANT EXECUTE ON PROCEDURE sprawdzenie_zapasow TO 'Bank_1'@'localhost'; 
GRANT EXECUTE ON PROCEDURE transfuzja TO 'Bank_1'@'localhost'; 
GRANT EXECUTE ON PROCEDURE sprawdzenie_zapasów_wybrane TO 'Bank_1'@'localhost'; 
GRANT EXECUTE ON PROCEDURE bank_jednostki TO 'Bank_1'@'localhost'; 
GRANT EXECUTE ON FUNCTION get_bank_id TO 'Bank_2'@'localhost'; 
GRANT SELECT ON bank_krwi.* TO 'Bank_1'@'localhost'; 
GRANT UPDATE ON bank_krwi.* TO 'Bank_1'@'localhost'; 
GRANT CREATE ON bank_krwi.* TO 'Bank_1'@'localhost'; 
GRANT DELETE ON bank_krwi.* TO 'Bank_1'@'localhost'; 
FLUSH PRIVILEGES;



DROP USER IF EXISTS 'Bank_2'@'localhost';
CREATE USER 'Bank_2'@'localhost' IDENTIFIED BY 'Bank_2'; 
GRANT ALL ON bank_krwi TO 'Bank_2'@'localhost'; 
GRANT EXECUTE ON PROCEDURE sprawdzenie_daty TO 'Bank_2'@'localhost'; 
GRANT EXECUTE ON PROCEDURE sprawdzenie_zapasow TO 'Bank_2'@'localhost'; 
GRANT EXECUTE ON PROCEDURE transfuzja TO 'Bank_2'@'localhost'; 
GRANT EXECUTE ON PROCEDURE sprawdzenie_zapasów_wybrane TO 'Bank_2'@'localhost'; 
GRANT EXECUTE ON PROCEDURE bank_jednostki TO 'Bank_2'@'localhost'; 
GRANT EXECUTE ON FUNCTION get_bank_id TO 'Bank_2'@'localhost'; 
GRANT SELECT ON bank_krwi.* TO 'Bank_2'@'localhost'; 
GRANT UPDATE ON bank_krwi.* TO 'Bank_2'@'localhost'; 
GRANT CREATE ON bank_krwi.* TO 'Bank_2'@'localhost'; 
GRANT DELETE ON bank_krwi.* TO 'Bank_2'@'localhost'; 
FLUSH PRIVILEGES;

#------------------------------------------------------------ GET BANK ID

drop procedure if exists get_bank_id;
drop function if exists get_bank_id;
DELIMITER $$
CREATE function get_bank_id
(bank_nazwa_input varchar(50))
RETURNS int DETERMINISTIC
BEGIN

SET @where_id = (select bank_id from bank where (bank_nazwa="Bank_1"));

return @where_id;

END $$
DELIMITER ;

call get_bank_id("Bank_1");
SET @where_id = (select bank_id from bank where (bank_nazwa= 'Bank_1' ));
select @where_id;

call bank_jednostki(get_bank_id("Bank_1")); # !!!
#------------------------------------------------------------
# triggery itp - dodanie statusu (gotowa do transfuzji, zutylizowana, przetransfuzowana)
# triggery itp. - dodanie stanu zapotrzebowania enum('0','A','B','AB') / # zapasy jako funkcja