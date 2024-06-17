package game.scoreboard;

import java.time.LocalDate;
import java.util.Objects;

public class PlayerScore {
    private String playerNickname;
    private int amountOfPoints;
    private LocalDate scoreDate;

    public PlayerScore(String playerNickname, int amountOfPoints) {
        this.playerNickname = playerNickname;
        this.amountOfPoints = amountOfPoints;
        scoreDate = LocalDate.now();
    }

    private PlayerScore(String playerNickname, int amountOfPoints, LocalDate scoreDate) {
        this.playerNickname = playerNickname;
        this.amountOfPoints = amountOfPoints;
        this.scoreDate = scoreDate;
    }

    public static PlayerScore createPlayerScore(String playerNickname, int amountOfPoints, LocalDate scoreDate) {
        return new PlayerScore(playerNickname, amountOfPoints, scoreDate);
    }

    public int getAmountOfPoints() {
        return amountOfPoints;
    }

    public void setAmountOfPoints(int amountOfPoints) {
        this.amountOfPoints = amountOfPoints;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }

    public void setPlayerNickname(String playerNickname) {
        this.playerNickname = playerNickname;
    }

    public LocalDate getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(LocalDate scoreDate) {
        this.scoreDate = scoreDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerScore that)) return false;
        return amountOfPoints == that.amountOfPoints && Objects.equals(playerNickname, that.playerNickname) && Objects.equals(scoreDate, that.scoreDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerNickname, amountOfPoints, scoreDate);
    }

    @Override
    public String toString() {
        return String.format("%s - %d points, %s", playerNickname, amountOfPoints, scoreDate);
    }
}
