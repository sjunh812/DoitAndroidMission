package org.techtown.doitmission24;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class Panel extends View {
    private static final int RECT_SIZE = 60;

    private Canvas canvas;
    private Paint paint;
    private Bitmap bitmap;

    private Rect rect;

    public Panel(Context context) {
        super(context);

        init(context);
    }

    public Panel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Bitmap _bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas _canvas = new Canvas();
        _canvas.setBitmap(_bitmap);
        _canvas.drawColor(Color.WHITE);

        bitmap = _bitmap;
        canvas = _canvas;

        rect = new Rect();
        rect.set(0, 0, RECT_SIZE, RECT_SIZE);
        drawRect(rect);
    }

    private void drawRect(Rect rect) {
        canvas.drawRect(rect, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float curX = event.getX();
        float curY = event.getY();

        switch(action) {
            case MotionEvent.ACTION_DOWN:
                actionDown(curX, curY);

                break;
            case MotionEvent.ACTION_MOVE:
                actionDown(curX, curY);

                break;
            case MotionEvent.ACTION_UP:
                return true;
        }

        invalidate();

        return true;
    }

    private void actionDown(float curX, float curY) {
        int left = (int)curX - RECT_SIZE / 2;
        int right = (int)curX + RECT_SIZE /2;
        int top = (int)curY - RECT_SIZE / 2;
        int bottom = (int)curY + RECT_SIZE / 2;

        rect.set(left, top, right, bottom);

        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        drawRect(rect);
    }
}
