create table Eliza(
messageID number generated by default on null as identity,
message  varchar2(500),
messagetype varchar(100));

insert into eliza (message,messageType) values ('Interesting, Please tell me more','Hedge');
insert into eliza (message,messageType) values ('wait a minute...I didn''t quite get that...','Hedge');
insert into eliza (message,messageType) values ('Many of my patients tell me the same thing','Hedge');
insert into eliza (message,messageType) values ('It is getting late, maybe we had better quit','Hedge');
insert into eliza (message,messageType) values ('Why do you say that','qualifier');
insert into eliza (message,messageType) values ('You seem to think that','qualifier');
insert into eliza (message,messageType) values ('So, you are concerned that','qualifier');
insert into eliza (message,messageType) values ('Hi','Salutation');
insert into eliza (message,messageType) values ('Hello','Salutation');
insert into eliza (message,messageType) values ('Greetings','Salutation');

select * from eliza;

--------------random
SELECT *
FROM   (
    SELECT *
    FROM   customers
    ORDER BY DBMS_RANDOM.RANDOM)
WHERE  rownum=1;

create table replacements(
replacementid number generated by default on null as identity,
firstperson varchar(500),
secondperson varchar(500)
);

insert into replacements (firstperson,secondperson) values ('I','you');
insert into replacements (firstperson,secondperson) values ('me','you');
insert into replacements (firstperson,secondperson) values ('my','your');
insert into replacements (firstperson,secondperson) values ('am','are');

select * from replacements;