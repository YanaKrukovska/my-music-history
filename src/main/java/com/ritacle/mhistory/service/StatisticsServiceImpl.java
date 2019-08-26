package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.stats.LastListen;
import com.ritacle.mhistory.persistence.model.stats.TopSong;
import com.ritacle.mhistory.persistence.repository.LastListenRepository;
import com.ritacle.mhistory.persistence.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.ritacle.mhistory.utils.DateUtils.atEndOfDate;
import static com.ritacle.mhistory.utils.DateUtils.atStartOfDate;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);
    @Value("${spring.datasource.username}")
    private String dbUser;
    @Value("${spring.datasource.url}")
    private String dbURL;
    @Value("${spring.datasource.password}")
    private String dbUserPassword;


    @Autowired
    LastListenRepository lastListenRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public List<LastListen> getLastUserListens(String userMail, Sort sort) {
        return lastListenRepository.findFirst30ByUserMailIgnoreCase(userMail, sort);
    }

    @Override
    public List<TopSong> getUserTopListens(String userMail, Date startDate, Date endDate) {

        String SQL_SELECT = "select\n" +
                "         s.title as songTitle,\n" +
                "         (select name from artist where id = a.artist_id) as artist,\n" +
                "         count(*) as listenCount\n" +
                "  from listen l\n" +
                "         join song s on l.song_id = s.id\n" +
                "         join album a on s.album_id = a.id\n" +
                "  where  l.user_id =? AND listen_date BETWEEN ? AND ?" +
                "  group by songTitle, artist\n" +
                "  order by listenCount desc LIMIT 100;";


        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(dbURL + "?sslmode=require", dbUser, dbUserPassword);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
        }


        List<TopSong> result = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(SQL_SELECT);
            preparedStatement.setLong(1, userRepository.findUserByMailIgnoreCase(userMail).getId());
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(atStartOfDate(startDate).getTime()));
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(atEndOfDate(endDate).getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                String songtitle = resultSet.getString("songtitle");
                String artist = resultSet.getString("artist");
                int listencount = resultSet.getInt("listencount");

                TopSong listenEntity = new TopSong(songtitle, null, artist, null, listencount);
                result.add(listenEntity);

            }

        } catch (SQLException e) {
            logger.error(e.getMessage());

        }
        logger.debug("Getting top listens user:{} [{} - {}) count= {}  ", userMail, startDate, endDate, result.size());
        return result;
    }


}
