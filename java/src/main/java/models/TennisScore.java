package models;

public enum TennisScore {
    LOVE(0, "Love"), FIFTEEN(1, "Fifteen"),
    THIRTY(2, "Thirty"), FORTY(3, "Forty");

    private final int points;
    private final String displayName;

    TennisScore(int points, String displayName) {
        this.points = points;
        this.displayName = displayName;
    }

    public static String fromValue(int value) {
        for (TennisScore score : TennisScore.values()) {
            if (score.points == value) {
                return score.displayName;
            }
        }
        throw new IllegalArgumentException("Invalid score value: " + value);
    }

    public String getDisplayName() {
        return displayName;
    }
}
