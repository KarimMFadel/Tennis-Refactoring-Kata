import models.Player;
import models.TennisScore;

import java.util.Objects;

public class TennisGame2 implements TennisGame {
    private static final int DEUCE_THRESHOLD = 3;
    private static final int WIN_THRESHOLD = 4;
    private static final String DEUCE = "Deuce";
    private static final String WIN_PREFIX = "Win for ";
    private static final String ADVANTAGE_PREFIX = "Advantage ";
    private final Player player1;
    private final Player player2;

    public TennisGame2(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
    }

    @Override
    public void wonPoint(String player) {
        if (Objects.equals(player, player1.getName())) {
            player1.incrementScore();
        } else {
            player2.incrementScore();
        }
    }

    @Override
    public String getScore() {
        int p1point = player1.getScore();
        int p2point = player2.getScore();

        if (p1point == p2point) {
            return formatTieScore(p1point);
        }
        if (p2point < WIN_THRESHOLD && p1point < WIN_THRESHOLD) {
            return formatRegularScore(p1point, p2point);
        }
        if (Math.abs(p1point - p2point) >= 2) {
            return formatWinnerMessage(p1point, p2point);
        }

        return formatAdvantageMessage(p1point, p2point);
    }

    private String formatTieScore(int P1point) {
        if (P1point >= DEUCE_THRESHOLD) {
            return DEUCE;
        }
        return TennisScore.fromValue(P1point) + "-All";
    }

    private String formatRegularScore(int P1point, int P2point) {
        return TennisScore.fromValue(P1point) + "-" + TennisScore.fromValue(P2point);
    }

    private String formatWinnerMessage(int P1point, int P2point) {
        return (P1point - P2point) > 0 ? WIN_PREFIX + "player1" : WIN_PREFIX + "player2";
    }

    private String formatAdvantageMessage(int P1point, int P2point) {
        return (P1point > P2point) ?
                ADVANTAGE_PREFIX + "player1" : ADVANTAGE_PREFIX + "player2";
    }
}