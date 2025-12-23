
public class TennisGame1 implements TennisGame {

    private final Player player1;
    private final Player player2;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
    }

    public void wonPoint(String playerName) {
        if (playerName == player1.getName())
            player1.incrementScore();
        else
            player2.incrementScore();
    }

    public String getScore() {
        String score = "";
        int tempScore = 0;
        if (player1.getScore() == player2.getScore()) {
            score = switch (player1.getScore()) {
                case 0 -> "Love-All";
                case 1 -> "Fifteen-All";
                case 2 -> "Thirty-All";
                default -> "Deuce";
            };
        } else if (player1.getScore() >= 4 || player2.getScore() >= 4) {
            int minusResult = player1.getScore() - player2.getScore();
            if (minusResult == 1) score = "Advantage player1";
            else if (minusResult == -1) score = "Advantage player2";
            else if (minusResult >= 2) score = "Win for player1";
            else score = "Win for player2";
        } else {
            for (int i = 1; i < 3; i++) {
                if (i == 1) tempScore = player1.getScore();
                else {
                    score += "-";
                    tempScore = player2.getScore();
                }
                score += switch (tempScore) {
                    case 0 -> "Love";
                    case 1 -> "Fifteen";
                    case 2 -> "Thirty";
                    default -> "Forty"; // 3
                };
            }
        }
        return score;
    }
}

class Player {
    private final String name;
    private int score;

    Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public int getScore() {return score;}
    public void incrementScore() {this.score+=1;}
    public String getName() {return name;}
}
