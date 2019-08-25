
CREATE or replace VIEW song_stats_by_date AS
  select ROW_NUMBER() OVER () as ID,
         l2.mail                                         as user_Mail,
         s.title                                          as title,
         a.title                                          as album,
         (select name from artist where id = a.artist_id) as artist,
         listen_date                                      as LISTEN_DATE
  from listen l
         join song s on l.song_id = s.id
         join album a on s.album_id = a.id
         join listener l2 on l.user_id = l2.id;


insert into Listener (ID, GENDER,MAIL,NICK_NAME,PASSWORD,USER_NAME,BIRTH_DATE,COUNTRY_ID)
values (1,'F', 'jana.krua@gmail.com', 'Yana', '', 'Yana', '2001-09-05',null);

insert into Listener (ID, GENDER,MAIL,NICK_NAME,PASSWORD,USER_NAME,BIRTH_DATE,COUNTRY_ID)
values (2,'M', 'v.krukovskyy@gmail.com', 'Slava', '', 'Slava', '1978-11-13',null);

insert into artist (id, name) values(1, 'Ava Max');
insert into artist (id, name) values(2,'Tom Odell');

insert into ALBUM(ID, TITLE, ARTIST_ID, GENRE, RELEASE_DATE ) VALUES (1,'Sweet But Psycho', 1, 'Pop','2018-08-01');
insert into ALBUM(ID, TITLE,ARTIST_ID, GENRE, RELEASE_DATE ) VALUES (2,'Long Way Down', 2, 'Pop','2013-01-01');
insert into ALBUM(ID, TITLE,ARTIST_ID, GENRE, RELEASE_DATE ) VALUES (3,'So Am I', 1, 'Pop','2019-01-20');

insert into song ( ID, ALBUM_ID, TITLE)
values (1, 3, 'So Am I');

insert into song ( ID, ALBUM_ID, TITLE)
values (2, 1, 'Sweet But Psycho');

insert into song ( ID, ALBUM_ID, TITLE)
values (3, 2, 'I Know');

insert into listen ( ID, LISTEN_DATE, SONG_ID, USER_ID)
values (1, '2019-07-13 14:03:00', 2, 2);

insert into listen ( ID, LISTEN_DATE, SONG_ID, USER_ID)
values (2, '2019-07-13 13:53:50', 2, 1);

insert into listen ( ID, LISTEN_DATE, SONG_ID, USER_ID)
values (3, '2019-07-11 14:03:00', 1, 2);

insert into listen ( ID, LISTEN_DATE, SONG_ID, USER_ID)
values (4, '2019-07-14 14:03:00', 3, 2);