package com.example.yangl.androidsample.GestureDector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GestureDectorActivity extends AppCompatActivity implements View.OnTouchListener{

    @BindView(R.id.text_view)
    TextView textView;

    private String TAG = GestureDectorActivity.class.getSimpleName();

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_dector);
        ButterKnife.bind(this);

        gestureDetector = new GestureDetector(this, new MySimpleGestureListener());
        textView.setOnTouchListener(this);
        textView.setClickable(true);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

}
