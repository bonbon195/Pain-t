package ru.bonbon.pain_t;

import android.graphics.Path;

public class FingerPath {
    public int color;
    public Path path;
    public int strokeWidth;
    public int alpha;

    public FingerPath(int color, Path path, int strokeWidth, int alpha) {
        this.color = color;
        this.path = path;
        this.strokeWidth = strokeWidth;
        this.alpha = alpha;
    }
}
