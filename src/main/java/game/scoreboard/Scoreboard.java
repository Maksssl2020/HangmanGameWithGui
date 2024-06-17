package game.scoreboard;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Scoreboard {
    public static final String PLAYER_NICKNAME_COLUMN = "PLAYER_NICKNAME";
    public static final String PLAYER_POINTS_COLUMN = "PLAYER_POINTS";
    public static final String GAME_DATE_COLUMN = "GAME_DATE";
    private static final String SAVE_PLAYER_SCORE_SQL = "INSERT INTO SCOREBOARD_PLAYERS_DATA (PLAYER_NICKNAME, PLAYER_POINTS, GAME_DATE) VALUES (?, ?, ?)";
    private static final String FIND_BY_PLAYER_NICKNAME_SQL = "SELECT * FROM SCOREBOARD_PLAYERS_DATA WHERE PLAYER_NICKNAME = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM SCOREBOARD_PLAYERS_DATA";
    private final Connection dataBaseConnection;

    public Scoreboard() {
        try {
            dataBaseConnection = DriverManager.getConnection("jdbc:h2:~/hangman-scoreboard-data");
            dataBaseConnection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String save(PlayerScore playerScore) {
        try {
            PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(SAVE_PLAYER_SCORE_SQL);
            preparedStatement.setString(1, playerScore.getPlayerNickname());
            preparedStatement.setInt(2, playerScore.getAmountOfPoints());
            preparedStatement.setDate(3, Date.valueOf(playerScore.getScoreDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return String.format("%s score has been saved!", playerScore.getPlayerNickname());
    }

    public ArrayList<PlayerScore> findByPlayerNickname(String playerNickname) {
        ArrayList<PlayerScore> playerScores = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(FIND_BY_PLAYER_NICKNAME_SQL);
            preparedStatement.setString(1, playerNickname);
            extractPlayerScoreFromFoundData(playerScores, preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return playerScores;
    }

    public ArrayList<PlayerScore> findAll() {
        ArrayList<PlayerScore> allPlayersScore = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(FIND_ALL_SQL);
            extractPlayerScoreFromFoundData(allPlayersScore, preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allPlayersScore;
    }

    private void extractPlayerScoreFromFoundData(ArrayList<PlayerScore> allPlayersScore, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String foundPlayerNickname = resultSet.getString(PLAYER_NICKNAME_COLUMN);
            int foundPlayerPoints = resultSet.getInt(PLAYER_POINTS_COLUMN);
            LocalDate foundGameDate = LocalDate.parse(resultSet.getDate(GAME_DATE_COLUMN).toString());

            allPlayersScore.add(PlayerScore.createPlayerScore(foundPlayerNickname, foundPlayerPoints, foundGameDate));
        }
    }

    public Connection getDataBaseConnection() {
        return dataBaseConnection;
    }
}
