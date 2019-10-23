package com.example.backgroundmove;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;

public class SurfaceBackground extends SurfaceView implements GestureDetector.OnGestureListener, View.OnTouchListener {

    SurfaceHolder surfaceHolder;
    Canvas canvas;
    Context context;
    int index = 0;
    GestureDetector gestureDetector;
    float imgx = 0, imgy = 0;
    Paint img_paint = new Paint();

    boolean moveleft = false, moveright = false;

    Bitmap bitmap_bk[] = new Bitmap[3];
    int img_list[] = {R.drawable.gaming1, R.drawable.republic, R.drawable.sunset};


    public SurfaceBackground(Context context) {
        super(context);
        init(context);
    }

    public SurfaceBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SurfaceBackground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SurfaceBackground(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
    public void init (Context context) {
        surfaceHolder = getHolder();
        gestureDetector = new GestureDetector(this);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                image_Init();
                draw(canvas);
                Log.d("Testing", "Surface created");
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.d("Testing", "Surface Changed");
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d("Testing", "Surface destroyed");
            }
        });
    }

    public void image_Init() {
        for (int i = 0; i < bitmap_bk.length; i++) {
            bitmap_bk[i] = BitmapFactory.decodeResource(getResources(), img_list[i]);
        }
        Log.d("Testing", "init called!    ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas = surfaceHolder.lockCanvas();

        Bitmap scale_bk = Bitmap.createScaledBitmap(bitmap_bk[index], canvas.getWidth(), canvas.getHeight(), true);
        canvas.drawBitmap(scale_bk, imgx, imgy, null);

        invalidate();
        surfaceHolder.unlockCanvasAndPost(canvas);
        super.onDraw(canvas);
        Log.d("Testing", "Draw Called...   ");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if (e1.getX() < e2.getX()) {
            if (index > 0) {
                //index = index - 1;
                index = --index % img_list.length;
                load_next_bk(canvas);
                Log.d("Testing", "Fling right move called");
            }
        } else {
            moveright = true;
            index = ++index % img_list.length;
            load_next_bk(canvas);
            Log.d("Testing", "Fling left move called");
        }
        Log.d("Testing", "Fling called");
        return false;
    }

    public void load_next_bk(Canvas canvas) {
        canvas = surfaceHolder.lockCanvas();
        img_paint.setShadowLayer(160, imgx - 100, imgy, Color.MAGENTA);
        Bitmap scale_bk = Bitmap.createScaledBitmap(bitmap_bk[index], canvas.getWidth(), canvas.getHeight(), true);
        canvas.drawBitmap(scale_bk, imgx, imgy, img_paint);
        surfaceHolder.unlockCanvasAndPost(canvas);
        Log.d("Testing", "Load and next called");
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
