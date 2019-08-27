package com.example.yangl.androidsample.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.tools.SMSHelper;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContentProviderActivity extends AppCompatActivity {

    private final int REQUEST_CONTACTS = 101;
    private final int START_FOR_RESULT = 102;
    private final int REQUEST_CONTACTS_LIST = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.get_contract_num,R.id.get_contract_list})
    public void onViewClicked(View view) {
        if(view.getId() == R.id.get_contract_num){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(ContentProviderActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContentProviderActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CONTACTS);
                } else {
                    intentToContact();
                }
            } else {
                intentToContact();
            }
        }else if(view.getId() == R.id.get_contract_list){
            List<SMSHelper.ContactsBean> contactsList = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(ContentProviderActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContentProviderActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CONTACTS_LIST);
                } else {
                    showContactList();
                }
            } else {
                showContactList();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                intentToContact();
            } else {
                Toast.makeText(ContentProviderActivity.this, "授权被禁止", Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == REQUEST_CONTACTS_LIST){
            showContactList();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showContactList(){
        List<SMSHelper.ContactsBean> contactsList = SMSHelper.getContactsList(ContentProviderActivity.this);
        StringBuilder stringBuilder = new StringBuilder();
        for(SMSHelper.ContactsBean bean : contactsList){
            stringBuilder.append(bean.getName()).append(" tel = ").append(bean.getNumList().get(0)).append("\n");
        }
        Toast.makeText(ContentProviderActivity.this, stringBuilder, Toast.LENGTH_SHORT).show();
    }

    private void intentToContact() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("vnd.android.cursor.dir/phone_v2");
        startActivityForResult(intent, START_FOR_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == START_FOR_RESULT && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                String phoneNum = null;
                String contactName = null;
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = null;
                if (uri != null) {
                    cursor = contentResolver.query(uri, new String[]{"display_name", "data1"}, null, null, null);
                }
                while (cursor.moveToNext()) {
                    contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    phoneNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }

                cursor.close();

                if (phoneNum != null) {
                    phoneNum = phoneNum.replaceAll("-", "");
                }

                Toast.makeText(ContentProviderActivity.this, contactName + " : " + phoneNum, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.get_contract_list)
    public void onViewClicked() {
    }
}
