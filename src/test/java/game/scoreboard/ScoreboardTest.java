package game.scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ScoreboardTest {
    private Scoreboard scoreboard;
    private PlayerScore p10 = new PlayerScore("Frank2020", 5);
    private PlayerScore p11 = new PlayerScore("Frank2020", 2);
    private PlayerScore p12 = new PlayerScore("Frank2020", 8);
    private PlayerScore p20 = new PlayerScore("Frank2021", 6);
    private PlayerScore p30 = new PlayerScore("Frank2022", 7);

    @BeforeEach
    void setUp() throws SQLException {
        scoreboard = new Scoreboard();
        scoreboard.getDataBaseConnection().setAutoCommit(false);
    }

    @Test
    void testSavingOnePlayerScore() {
        String savingResult = scoreboard.save(p10);

        assertThat(savingResult).isEqualTo("Frank2020 score has been saved!");
    }

    @Test
    void testSavingTwoPlayersScore() {
        scoreboard.save(p10);
        String savingResult = scoreboard.save(p20);

        assertThat(savingResult).isEqualTo("Frank2021 score has been saved!");
    }

    @Test
    void testFindingPlayerScoresByNickname() {
        ArrayList<PlayerScore> expectedResult = new ArrayList<>(List.of(p10, p11, p12));
        scoreboard.save(p10);
        scoreboard.save(p11);
        scoreboard.save(p12);

        ArrayList<PlayerScore> result = scoreboard.findByPlayerNickname("Frank2020");
        System.out.println(result);
        assertThat(result.containsAll(expectedResult)).isTrue();
    }

    @Test
    void testFindingAllPlayersScore() {
        ArrayList<PlayerScore> expectedResult = new ArrayList<>(List.of(p10, p11, p12, p20, p30, p30));
        scoreboard.save(p10);
        scoreboard.save(p11);
        scoreboard.save(p12);
        scoreboard.save(p20);
        scoreboard.save(p30);

        ArrayList<PlayerScore> result = scoreboard.findAll();
        System.out.println(expectedResult);
        System.out.println(result);
        assertThat(result.containsAll(expectedResult)).isTrue();
    }
}