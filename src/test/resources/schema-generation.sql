CREATE or replace VIEW song_stats_by_date AS
  select ROW_NUMBER() OVER () as ID,
          l2.mail                                         as userMail,
         s.title                                          as title,
         a.title                                          as album,
         (select name from artist where id = a.artist_id) as artist,
         listen_date                                      as listenDate
  from listen l
         join song s on l.song_id = s.id
         join album a on s.album_id = a.id
         join listener l2 on l.user_id = l2.id;