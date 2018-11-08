package com.example.yangl.androidsample.someActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.model.Info;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InnerClassActivity extends AppCompatActivity {

    @BindView(R.id.show)
    TextView show;
    @BindView(R.id.change)
    Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_class);
        ButterKnife.bind(this);

        final Info info = new Info("22");
        info.setName("33");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.setText(info.getName());
                info.setName("yy");
                show.setText(info.getName());
            }
        });
    }


}
