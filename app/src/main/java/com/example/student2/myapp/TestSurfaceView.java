package com.example.student2.myapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;

/**
 * Created by student2 on 26.01.18.
 */





class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback {              //Callback - внутренний интерфейс класса SurfaceHolder

    SurfaceHolder holder;

    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        return super.onTouchEvent(event);
    }


    public TestSurfaceView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        p.setColor(YELLOW);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }


    Paint p = new Paint();

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        new MyThread().start();
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }


    float x = 0;


    class MyThread extends Thread {

        @Override
        public void run() {

            while (true) {
                Canvas canvas = holder.lockCanvas();
                canvas.drawColor(Color.BLUE);
                canvas.drawCircle(x, 200, 30, p);
                holder.unlockCanvasAndPost(canvas);

                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                x++;
            }
        }
    }
}
