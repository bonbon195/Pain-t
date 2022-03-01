package ru.bonbon.pain_t;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class MyView extends View {
    public static final int defaultStrokeWidth = 10;
    public static final int defaultColor = Color.BLACK;
    private static final float tolerance = 5;

    public static int currentColor;
    public static int alpha = 0xff;
    public static int strokeWidth;

    private Paint paint;
    private ArrayList<FingerPath> paths = new ArrayList<>();
    private Bitmap bitmap;
    private float mX, mY;
    private Path path;
    private Canvas canvas;


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(defaultColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setXfermode(null);
        paint.setAlpha(alpha);
    }

    public void init(DisplayMetrics displayMetrics){
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        currentColor = defaultColor;
        strokeWidth = defaultStrokeWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        for (FingerPath fingerPath: paths) {
            paint.setColor(fingerPath.color);
            paint.setStrokeWidth(fingerPath.strokeWidth);
            paint.setAlpha(fingerPath.alpha);
            canvas.drawPath(fingerPath.path, paint);
        }

    }
    private void touchDown(float x, float y){
        path = new Path();
        FingerPath fingerPath = new FingerPath(currentColor, path, strokeWidth, 0xff);
        paths.add(fingerPath);
        path.reset();
        path.moveTo(x, y);
        mX = x;
        mY= y;
    }

    private void touchUp(float x, float y){
        path.lineTo(mX, mY);
    }

    private void touchMove(float x, float y){
        float dX = Math.abs(x-mX);
        float dY = Math.abs(y-mY);
        if (dX >= tolerance || dY >= tolerance) {
            path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchDown(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;

        }
        return true;
    }
    public static void setStrokeWidth(int width){
        strokeWidth = width;
    }
    public static void setCurrentColor(int color){
        currentColor = color;
    }

}
