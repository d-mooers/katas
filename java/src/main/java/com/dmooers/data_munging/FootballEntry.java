package com.dmooers.data_munging;

import java.util.Optional;

public class FootballEntry {
    final String name;
    final int goals;
    final int goalsAgainst;

    FootballEntry(String name, int goals, int goalsAgainst) {
        this.name = name;
        this.goals = goals;
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalsSpread() {
        return Math.abs(goals - goalsAgainst);
    }

    public String toString() {
        return String.format("Team: %s, Points Spread: %d", name, getGoalsSpread());
    }

    public static class Reader {

        public static Optional<FootballEntry> read(String[] tokens) {

            try {
                String teamName = tokens[1];

                int pointsFor = Integer.parseInt(tokens[6]);
                int pointsAgainst = Integer.parseInt(tokens[8]);



                return Optional.of(new FootballEntry(teamName, pointsFor, pointsAgainst));
            } catch (Exception e) {
                return Optional.empty();
            }

        }
    }
    
}
