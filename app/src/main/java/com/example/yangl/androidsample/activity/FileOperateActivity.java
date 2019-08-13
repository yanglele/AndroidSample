package com.example.yangl.androidsample.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.tools.FileTools;
import com.facebook.common.file.FileUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileOperateActivity extends AppCompatActivity {

    @BindView(R.id.create_file)
    Button createFile;
    @BindView(R.id.create_file_edit)
    EditText createFileEdit;
    @BindView(R.id.show_path)
    TextView showPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_operate);
        ButterKnife.bind(this);
        setShowPath();
    }

    @OnClick({ R.id.create_file, R.id.create_file_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.create_file:
                String string = createFileEdit.getText().toString();
                int result = FileTools.createFile(string,"");
                if(result == FileTools.CREATE_SUCCESS){
                    Toast.makeText(this,"成功",Toast.LENGTH_SHORT).show();
                }else if(result == FileTools.CREATE_FAILED){
                    Toast.makeText(this,"失败",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"已经存在",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.create_file_edit:
                break;
        }
    }

    private void setShowPath() {
        String path1 = Environment.getDataDirectory().getAbsolutePath();
        String path2 = getFilesDir().getAbsolutePath();
        String path3 = getCacheDir().getAbsolutePath();

        String path4 = Environment.getExternalStorageDirectory().getAbsolutePath();
        String path5 = getExternalCacheDir().getAbsolutePath();

        StringBuilder builder = new StringBuilder();
        builder.append("Environment.getDataDirectory().getAbsolutePath() = ").append(path1).append("\n")
                .append("getFilesDir().getAbsolutePath() = ").append(path2).append("\n")
                .append("getCacheDir().getAbsolutePath() = ").append(path3).append("\n")
                .append("Environment.getExternalStorageDirectory().getAbsolutePath() = ").append(path4).append("\n")
                .append("getExternalCacheDir().getAbsolutePath() = ").append(path5);
        showPath.setText(builder.toString());

        createFileEdit.setText(path2);
    }
}
