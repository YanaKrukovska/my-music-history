create view song_stats_by_date as
  SELECT row_number() OVER ()                                             AS id,
         l2.mail                                                          AS user_mail,
         s.title,
         a.title                                                          AS album,
         (SELECT artist.name FROM artist WHERE (artist.id = a.artist_id)) AS artist,
         l.listen_date
  FROM (((listen l
      JOIN song s ON ((l.song_id = s.id)))
      JOIN album a ON ((s.album_id = a.id)))
      JOIN listener l2 ON ((l.user_id = l2.id)));
