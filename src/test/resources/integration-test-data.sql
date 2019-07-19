
insert into Listener (ID, GENDER,MAIL,NICK_NAME,PASSWORD,USER_NAME,BIRTH_DATE,COUNTRY_ID)
values (1,'F', 'jana.krua@gmail.com', 'Yana', '', 'Yana', '2001-09-05',null);

insert into Listener (ID, GENDER,MAIL,NICK_NAME,PASSWORD,USER_NAME,BIRTH_DATE,COUNTRY_ID)
values (2,'M', 'v.krukovskyy@gmail.com', 'Slava', '', 'Slava', '1978-11-13',null);

insert into ALBUM(ID, TITLE, GENRE, RELEASE_DATE ) VALUES (1,'Sweet But Psycho','Pop','2018-08-01');
insert into ALBUM(ID, TITLE, GENRE, RELEASE_DATE ) VALUES (2,'Long Way Down','Pop','2013-01-01');
insert into ALBUM(ID, TITLE, GENRE, RELEASE_DATE ) VALUES (3,'So Am I','Pop','2019-01-20');

insert into artist (id, name) values(1, 'Ava Max');
insert into artist (id, name) values(2,'Tom Odell');

insert into song ( ID, ALBUM_ID, ARTIST_ID, TITLE)
values (1, 3, 1, 'So Am I');

insert into song ( ID, ALBUM_ID, ARTIST_ID, TITLE)
values (2, 1,1, 'Sweet But Psycho');

insert into song ( ID, ALBUM_ID, ARTIST_ID, TITLE)
values (3, 2,2, 'I Know');

insert into listen ( ID, LISTEN_DATE, SONG_ID, USER_ID)
values (1, '2019-07-13 14:03:00', 2, 2);

insert into listen ( ID, LISTEN_DATE, SONG_ID, USER_ID)
values (2, '2019-07-13 13:53:50', 2, 1);