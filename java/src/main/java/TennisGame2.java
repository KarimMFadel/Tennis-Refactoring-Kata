import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class TennisGame2 implements TennisGame {
    public int P1point = 0;
    public int P2point = 0;

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
        String P1res = "", P2res = "";
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
            return P1res + "-" + P2res;
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

    private void P1Score() {
        P1point++;
    }

    private void P2Score() {
        P2point++;
    }
}