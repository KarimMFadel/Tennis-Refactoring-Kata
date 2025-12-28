import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class TennisGame2 implements TennisGame {
    public int P1point = 0;
    public int P2point = 0;

    public String P1res = "";
    public String P2res = "";
    private String player1Name;
    private String player2Name;

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String player) {
        if (Objects.equals(player, "player1"))
            P1Score();
        else
            P2Score();
    }

    public String getScore() {
        AtomicReference<String> score = new AtomicReference<>("");

        checkTieInMatch(score);
        checkMatchInProgress(score);
        if (!checkWinnerInMatch(score)) {
            checkAdvantageInMatch(score);
        }

        return score.toString();
    }

    private void checkTieInMatch(AtomicReference<String> score) {
        if (P1point == P2point) {
            switch (P1point) {
                case 0 -> score.set("Love-All");
                case 1 -> score.set("Fifteen-All");
                case 2 -> score.set("Thirty-All");
                default -> score.set("Deuce");
            }
        }
    }

    private void checkMatchInProgress(AtomicReference<String> score) {
        if (P2point < 4 && P1point < 4 && P1point != P2point) {
            P1res = switch (P1point) {
                case 0 -> "Love";
                case 1 -> "Fifteen";
                case 2 -> "Thirty";
                default -> "Forty";
            };
            P2res = switch (P2point) {
                case 0 -> "Love";
                case 1 -> "Fifteen";
                case 2 -> "Thirty";
                default -> "Forty";
            };
            score.set(P1res + "-" + P2res);
        }
    }

    private boolean checkWinnerInMatch(AtomicReference<String> score) {
        if (P1point >= 4 && P2point >= 0 && (P1point - P2point) >= 2) {
            score.set("Win for player1");
            return true;
        } else if (P2point >= 4 && P1point >= 0 && (P2point - P1point) >= 2) {
            score.set("Win for player2");
            return true;
        }
        return false;
    }

    private void checkAdvantageInMatch(AtomicReference<String> score) {
        if (P1point > P2point && P2point >= 3) {
            score.set("Advantage player1");
        } else if (P2point > P1point && P1point >= 3) {
            score.set("Advantage player2");
        }
    }

    private void P1Score() {
        P1point++;
    }

    private void P2Score() {
        P2point++;
    }
}