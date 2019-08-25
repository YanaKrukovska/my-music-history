package com.ritacle.mhistory;

import com.ritacle.mhistory.persistence.model.stats.TopSong;
import org.junit.Test;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.ritacle.mhistory.utils.DateUtils.atEndOfDate;
import static com.ritacle.mhistory.utils.DateUtils.atStartOfDate;
import static com.ritacle.mhistory.utils.DateUtils.createDate;

public class JdbcTest {

    private static final String SQL_SELECT = "select\n" +
            "         s.title as songTitle,\n" +
            "         (select name from artist where id = a.artist_id) as artist,\n" +
            "         count(*) as listenCount\n" +
            "  from listen l\n" +
            "         join song s on l.song_id = s.id\n" +
            "         join album a on s.album_id = a.id\n" +
            "  where  l.user_id =? AND listen_date BETWEEN ? AND ?" +
            "  group by songTitle, artist\n" +
            "  order by listenCount desc;";

    @Test
    public void test() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-170-5.eu-west-1.compute.amazonaws.com:5432/d1aac9s76vgasj?sslmode=require",
                    "ugzdqifhzmadad", "cfb7126cea034067418a48ebe68681392a83214d1e2151092293c3d2fd63d541");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement preparedStatement = con.prepareStatement(SQL_SELECT);
            preparedStatement.setInt(1,1);
            Date startDate = new Date(119, 7, 23);
            Date endDate = new Date(119, 7, 23);
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(atStartOfDate(startDate).getTime()));
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(atEndOfDate(endDate).getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();


            List<TopSong> result = new LinkedList<>();
            while (resultSet.next()) {

                String songtitle = resultSet.getString("songtitle");
                String artist = resultSet.getString("artist");
                int listencount = resultSet.getInt("listencount");

                TopSong listenEntity = new TopSong(songtitle, null, artist, null, listencount);

                result.add(listenEntity);

            }
            result.forEach(x -> System.out.println(x));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
