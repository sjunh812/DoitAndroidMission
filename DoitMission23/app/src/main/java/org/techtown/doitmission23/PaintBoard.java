package org.techtown.doitmission23;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class PaintBoard extends View {
    private static final String TAG = "PaintBoard";

    private Canvas canvas;
    private Bitmap bitmap;
    private Paint paint;
    private Path path;
    private Rect rect;

    private float lastX;
    private float lastY;
    private float curX;
    private float curY;
    private float curMidX;
    private float curMidY;

    private static final float TOUCH_TOLERANCE = 8f;
    private static final int RECT_EXTRA_BORDER = 10;

    public PaintBoard(Context context) {
        super(context);

        init(context);
    }

    public PaintBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        path = new Path();

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);

        lastX = -1;
        lastY = -1;
    }

    public void setPaint(int color, float width, Paint.Cap cap) {
        paint.setColor(color);
        paint.setStrokeWidth(width);
        paint.setStrokeCap(cap);
    }

    public void reset() {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    // 뷰가 그려지기 전에 메모리에 비트맵객체 생성
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Bitmap _bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas _canvas = new Canvas();
        _canvas.setBitmap(_bitmap);
        _canvas.drawColor(Color.WHITE);

        bitmap = _bitmap;
        canvas = _canvas;
    }

    // 뷰가 그려질 때 호출됨
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
        curX = event.getX();
        curY = event.getY();

        switch(action) {
            case MotionEvent.ACTION_DOWN:
                touchDown();

                return true;
            case MotionEvent.ACTION_MOVE:
                rect = touchMove();

                if(rect != null) {
                    invalidate(rect);
                }

                return true;
            case MotionEvent.ACTION_UP:
                path.rewind();

                return true;
        }

        return false;
    }

    private void touchDown() {
        path.moveTo(curX, curY);

        lastX = curX;
        lastY = curY;
        curMidX = curX;
        curMidY = curY;
    }

    private Rect touchMove() {
        float dx = Math.abs(curX - lastX);
        float dy = Math.abs(curY - lastY);

        Rect rect = new Rect();

        if(dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            float cX = curMidX = (lastX + curX) / 2;
            float cY = curMidY = (lastY + curY) / 2;

            path.quadTo(lastX, lastY, cX, cY);
            rect.union((int)lastX - RECT_EXTRA_BORDER, (int)lastY - RECT_EXTRA_BORDER, (int)lastX + RECT_EXTRA_BORDER, (int)lastY + RECT_EXTRA_BORDER);
            rect.union((int)cX - RECT_EXTRA_BORDER, (int)cY - RECT_EXTRA_BORDER, (int)cX + RECT_EXTRA_BORDER, (int)cY + RECT_EXTRA_BORDER);

            lastX = curX;
            lastY = curY;

            canvas.drawPath(path, paint);
        }

        return rect;
    }

    private float getDistance(float x1, float y1, float x2, float y2) {
        float dx = x1 - x2;
        float dy = y1 - y2;

        return (float)Math.sqrt(dx * dx + dy * dy);
    }
}
