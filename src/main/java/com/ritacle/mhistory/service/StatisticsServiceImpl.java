package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.stats.LastListen;
import com.ritacle.mhistory.persistence.model.stats.TopAlbum;
import com.ritacle.mhistory.persistence.model.stats.TopArtist;
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
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

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
            Class.forName(driver);
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
                String songTitle = resultSet.getString("songtitle");
                String artist = resultSet.getString("artist");
                int listenCount = resultSet.getInt("listencount");

                TopSong listenEntity = new TopSong(songTitle, null, artist, null, listenCount);
                result.add(listenEntity);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        logger.debug("Getting top listens user:{} [{} - {}) count= {}  ", userMail, startDate, endDate, result.size());
        return result;
    }

    @Override
    public List<TopAlbum> getUserTopAlbums(String userMail, Date startDate, Date endDate) {
        String SQL_SELECT = "select\n" +
                "         a.title as albumTitle,\n" +
                "         (select name from artist where id = a.artist_id) as artistName,\n" +
                "         count(*) as listenCount\n" +
                "  from listen l\n" +
                "         join song s on l.song_id = s.id\n" +
                "         join album a on s.album_id = a.id\n" +
                "  where  l.user_id =? AND listen_date BETWEEN ? AND ?" +
                "  group by albumTitle, artistName\n" +
                "  order by listenCount desc LIMIT 100;";

        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(dbURL + "?sslmode=require", dbUser, dbUserPassword);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
        }

        List<TopAlbum> result = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(SQL_SELECT);
            preparedStatement.setLong(1, userRepository.findUserByMailIgnoreCase(userMail).getId());
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(atStartOfDate(startDate).getTime()));
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(atEndOfDate(endDate).getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String albumName = resultSet.getString("albumTitle");
                String artist = resultSet.getString("artistName");
                int listenCount = resultSet.getInt("listenCount");

                TopAlbum albumEntity = new TopAlbum(albumName, artist, userMail, listenCount);
                result.add(albumEntity);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        logger.debug("Getting top albums user:{} [{} - {}) count= {}  ", userMail, startDate, endDate, result.size());
        return result;
    }

    @Override
    public List<TopArtist> getUserTopArtists(String mail, Date startDate, Date endDate) {
        String SQL_SELECT = "select\n" +
                "         r.name as artistName,\n" +
                "         count(*) as listenCount\n" +
                "  from listen l\n" +
                "         join song s on l.song_id = s.id\n" +
                "         join album a on s.album_id = a.id\n" +
                "         join artist r on a.artist_id = r.id\n" +
                "  where  l.user_id =? AND listen_date BETWEEN ? AND ?" +
                "  group by artistName\n" +
                "  order by listenCount desc LIMIT 100;";

        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(dbURL + "?sslmode=require", dbUser, dbUserPassword);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
        }

        List<TopArtist> result = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(SQL_SELECT);
            preparedStatement.setLong(1, userRepository.findUserByMailIgnoreCase(mail).getId());
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(atStartOfDate(startDate).getTime()));
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(atEndOfDate(endDate).getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String artist = resultSet.getString("artistName");
                int listenCount = resultSet.getInt("listenCount");
                TopArtist artistEntity = new TopArtist(artist, mail, listenCount);
                result.add(artistEntity);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        logger.debug("Getting top artists user:{} [{} - {}) count= {}  ", mail, startDate, endDate, result.size());
        return result;
    }

}
