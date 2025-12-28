import models.Player;
import models.TennisScore;

import java.util.Objects;

public class TennisGame2 implements TennisGame {
    private static final int DEUCE_THRESHOLD = 3;
    private static final int WIN_THRESHOLD = 4;
    private static final String DEUCE = "Deuce";
    private static final String WIN_PREFiX = "Win for ";
    private static final String ADVANTAGE_PREFiX = "Advantage ";
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

        if (P1point == P2point) {
            return checkTieInMatch(P1point);
        }
        if (P2point < WIN_THRESHOLD && P1point < WIN_THRESHOLD) {
            return checkMatchInProgress(P1point, P2point);
        }
        if (Math.abs(P1point - P2point) >= 2)
            return checkWinnerInMatch(P1point, P2point);

        return checkAdvantageInMatch(P1point, P2point);
    }

    private String checkTieInMatch(int P1point) {
        if (P1point >= DEUCE_THRESHOLD)
            return DEUCE;
        return TennisScore.fromValue(P1point) + "-All";
    }

    private String checkMatchInProgress(int P1point, int P2point) {
        return TennisScore.fromValue(P1point) + "-" + TennisScore.fromValue(P2point);
    }

    private String checkWinnerInMatch(int P1point, int P2point) {
        return (P1point - P2point) > 0? WIN_PREFiX + "player1" : WIN_PREFiX + "player2";
    }

    private String checkAdvantageInMatch(int P1point, int P2point) {
        return (P1point > P2point) ?
                ADVANTAGE_PREFiX + "player1" : ADVANTAGE_PREFiX + "player2";
    }
}