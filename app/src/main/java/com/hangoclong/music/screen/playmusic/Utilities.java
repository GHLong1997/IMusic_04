package com.hangoclong.music.screen.playmusic;

public class Utilities {

    private static final int ONE_THOUSAND_MILLISECONDS = 1000;
    private static final int SIXTY_SECONDS = 60;
    private static final int PERCENT = 100;

    public String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        int hours =
                (int) (milliseconds / (ONE_THOUSAND_MILLISECONDS * SIXTY_SECONDS * SIXTY_SECONDS));
        int minutes =
                (int) (milliseconds % (ONE_THOUSAND_MILLISECONDS * SIXTY_SECONDS * SIXTY_SECONDS))
                        / (ONE_THOUSAND_MILLISECONDS * SIXTY_SECONDS);
        int seconds =
                (int) ((milliseconds % (ONE_THOUSAND_MILLISECONDS * SIXTY_SECONDS * SIXTY_SECONDS))
                        % (ONE_THOUSAND_MILLISECONDS * SIXTY_SECONDS) / ONE_THOUSAND_MILLISECONDS);

        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds)
                : String.format("%02d:%02d", minutes, seconds);

    }

    public int getProgressPercentage(long currentDuration, long totalDuration) {
        Double percentage;

        long currentSeconds = (int) (currentDuration / ONE_THOUSAND_MILLISECONDS);
        long totalSeconds = (int) (totalDuration / ONE_THOUSAND_MILLISECONDS);

        percentage = (((double) currentSeconds) / totalSeconds) * PERCENT;

        return percentage.intValue();
    }

    public int progressToTimer(int progress, int totalDuration) {
        int currentDuration = 0;
        totalDuration = (totalDuration / ONE_THOUSAND_MILLISECONDS);
        currentDuration = (int) ((((double) progress) / PERCENT) * totalDuration);

        return currentDuration * ONE_THOUSAND_MILLISECONDS;
    }
}
