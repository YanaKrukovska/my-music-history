
insert into Listener (ID, GENDER,MAIL,NICK_NAME,PASSWORD,USER_NAME,BIRTH_DATE,COUNTRY_ID)
values (1,'F', 'jana.krua@gmail.com', 'Yana', '', 'Yana', '2001-09-05',null);

insert into Listener (ID, GENDER,MAIL,NICK_NAME,PASSWORD,USER_NAME,BIRTH_DATE,COUNTRY_ID)
values (2,'M', 'v.krukovskyy@gmail.com', 'Slava', '', 'Slava', '1978-11-13',null);

insert into song ( ID, ALBUM, ARTIST, TITLE)
values (1, 'So Am I', 'Ava Max', 'So Am I');

insert into song ( ID, ALBUM, ARTIST, TITLE)
values (2, 'Sweet But Psycho', 'Ava Max', 'Sweet But Psycho');

insert into song ( ID, ALBUM, ARTIST, TITLE)
values (3, 'Long Way Down', 'Tom Odell', 'I Know');

insert into listen ( ID, LISTEN_DATE, SONG_ID, USER_ID)
values (1, '2019-07-13 14:03:00', 2, 2);

insert into listen ( ID, LISTEN_DATE, SONG_ID, USER_ID)
values (2, '2019-07-13 13:53:50', 2, 1);