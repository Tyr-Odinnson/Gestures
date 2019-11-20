package com.example.gestures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.gesture.Gesture;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    GestureDetector gestureDetector;
    ConstraintLayout myLayout;
    ImageView img;
    float deltaX, deltaY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLayout = findViewById(R.id.myLayout);
        img = findViewById(R.id.img);
        gestureDetector = new GestureDetector(this);
        img.setOnTouchListener(this);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d("testing", "onDown");
        return false;

    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        img.animate().scaleX(1).setDuration(1000);
        img.animate().scaleY(1).setDuration(1000);
        Log.d("testing", "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        img.animate().scaleX(4).setDuration(500);
        img.animate().scaleY(4).setDuration(500);
        Log.d("testing", "onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
       img.animate().translationXBy(motionEvent1.getX()).setDuration(1000);
 //      img.animate().translationYBy(motionEvent1.getY()).setDuration(1000);
        Log.d("testing", "onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.d("testing", "onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
//        deltaX = motionEvent.getX() + v - motionEvent1.getX() + v1;
//        deltaY = motionEvent.getY() + v - motionEvent1.getY() + v1;
//        double degree = Math.atan2(deltaY, deltaX);
//        int deg = (int) Math.toDegrees(degree);
//         img.setRotationX(deg);
//         img.setRotationY(deg);
//        img.animate().rotationBy(deg).setDuration(1000);
//        Log.d("testing", "onFling" + deltaX + " " + deltaY + "tan " + degree + "degree ");
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.d("testing", "onTouch");
        gestureDetector.onTouchEvent(motionEvent);
        return true;
    }
}
