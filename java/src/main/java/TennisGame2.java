import models.Player;
import models.TennisScore;

import java.util.Objects;

public class TennisGame2 implements TennisGame {
    private final Player player1;
    private final Player player2;

    public TennisGame2(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
    }

    public void wonPoint(String player) {
        if (Objects.equals(player, player1.getName()))
            player1.incrementScore();
        else
            player2.incrementScore();
    }

    public String getScore() {
        int P1point = player1.getScore();
        int P2point = player2.getScore();
        String score;

        score = checkTieInMatch(P1point, P2point);
        if(score == null) score = checkMatchInProgress(P1point, P2point);
        if(score == null) score = checkWinnerInMatch(P1point, P2point);
        if(score == null) score = checkAdvantageInMatch(P1point, P2point);


        return score;
    }

    private String checkTieInMatch(int P1point, int P2point) {
        if (P1point == P2point) {
            return switch (P1point) {
                case 0 -> "Love-All";
                case 1 -> "Fifteen-All";
                case 2 -> "Thirty-All";
                default -> "Deuce";
            };
        }
        return null;
    }

    private String checkMatchInProgress(int P1point, int P2point) {
        if (P2point < 4 && P1point < 4 && P1point != P2point) {
            return TennisScore.fromValue(P1point) + "-" + TennisScore.fromValue(P2point);
        }
        return null;
    }

    private String checkWinnerInMatch(int P1point, int P2point) {
        if (P1point >= 4 && P2point >= 0 && (P1point - P2point) >= 2) {
            return "Win for player1";
        } else if (P2point >= 4 && P1point >= 0 && (P2point - P1point) >= 2) {
            return "Win for player2";
        }
        return null;
    }

    private String checkAdvantageInMatch(int P1point, int P2point) {
        if (P1point > P2point && P2point >= 3) {
            return "Advantage player1";
        } else if (P2point > P1point && P1point >= 3) {
            return "Advantage player2";
        }
        return null;
    }
}