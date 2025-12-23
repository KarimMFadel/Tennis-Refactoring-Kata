import models.Player;

import java.util.Objects;

public class TennisGame1 implements TennisGame {

    private final Player player1;
    private final Player player2;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
    }

    public void wonPoint(String playerName) {
        if (Objects.equals(playerName, player1.getName()))
            player1.incrementScore();
        else
            player2.incrementScore();
    }

    public String getScore() {
        int m_score1 = player1.getScore();
        int m_score2 = player2.getScore();

        if (m_score1 == m_score2) {
            return getScoreInEqualizeState(m_score1);
        } else if (m_score1 >= 4 || m_score2 >= 4) {
            return getScoreInWinStateAndAdvantageState(m_score1, m_score2);
        }
        return getScoreByCombineBothPlayerScores(player1.getDisplayScoreName(),
                player2.getDisplayScoreName());

    }

    private String getScoreInEqualizeState(int m_score) {
        return switch (m_score) {
            case 0 -> "Love-All";
            case 1 -> "Fifteen-All";
            case 2 -> "Thirty-All";
            default -> "Deuce";
        };
    }

    private String getScoreInWinStateAndAdvantageState(int m_score1, int m_score2) {
        String score;
        int minusResult = m_score1 - m_score2;
        if (minusResult == 1) score = "Advantage player1";
        else if (minusResult == -1) score = "Advantage player2";
        else if (minusResult >= 2) score = "Win for player1";
        else score = "Win for player2";
        return score;
    }

    private String getScoreByCombineBothPlayerScores(String player1ScoreName, String player2ScoreName) {
        return player1ScoreName + "-" + player2ScoreName;
    }
}
