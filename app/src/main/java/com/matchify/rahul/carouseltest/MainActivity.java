package com.matchify.rahul.carouseltest;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Arrays;
import android.view.GestureDetector;
import android.view.View.OnTouchListener;


public class MainActivity extends AppCompatActivity {

    ImageView mImageView;
    Integer[] mData = {R.drawable.cartoon,R.drawable.small_car, R.drawable.small_flower};
    ArrayList<Integer> mDataList;
    private GestureDetectorCompat mGestureDetectorCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataList = new ArrayList(Arrays.asList(mData));

        mImageView = (ImageView)findViewById(R.id.image_view);
        mImageView.setImageResource(R.drawable.cartoon);

        this.mGestureDetectorCompat = new GestureDetectorCompat(this,new MyGestureDetector());
        mImageView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mGestureDetectorCompat.onTouchEvent(event);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class MyGestureDetector extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onDown(MotionEvent e){


            return true;
        }

        @Override
        public boolean onFling (MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){

            boolean result = false;

            Log.v("MOTION EVENT 1",e1.toString());
            Log.v("MOTION EVENT 2",e2.toString());
            Log.v("VELOCITY X", Float.toString(velocityX));
            Log.v("VELOCITY Y",Float.toString(velocityY));

            final float SWIPE_THRESHOLD = 100;
            final float SWIPE_VELOCITY_THRESHOLD = 100;


            float startX = e1.getX();
            float startY = e1.getY();

            float endX = e2.getX();
            float endY = e2.getY();

            float diffX =  endX - startX;
            float diffY = endY - startY;

            if(Math.abs(diffX) > Math.abs(diffY)){

                if(Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD){

                    if(diffX > 0){
                        Log.v("SWIPE RIGHT","SWIPE RIGHT");
                        mImageView.setAnimation(new Animation(){




                        });
                        mImageView.setImageResource(R.drawable.small_car);
                    }
                    else{
                        Log.v("SWIPE LEFT","SWIPEM LEFT");
                        mImageView.setImageResource(R.drawable.cartoon);
                    }
                }
            }

            else{

               if(Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD){

                   if(diffY > 0){

                       Log.v("SWIPE DOWN","SWIPE DOWN");
                   }
                   else{

                       Log.v("SWIPE UP","SWIPE UP");
                   }
               }

            }
            return true;
        }
    }

}
