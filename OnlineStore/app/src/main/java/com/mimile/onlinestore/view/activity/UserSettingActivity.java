package com.mimile.onlinestore.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mimile.base.view.activity.CommonBaseActivity;
import com.mimile.onlinestore.R;

import java.io.File;
import java.util.ArrayList;
/**
 * Created by caidongdong on 2016/12/6 14:50
 * email : mircaidong@163.com
 */
public class UserSettingActivity extends CommonBaseActivity implements View.OnClickListener{
    private RelativeLayout modifyHeadImg;
    private RelativeLayout modifyCommonAddress;
    private static final int REQUEST_IMAGE = 2;
    private ArrayList<String> mSelectPath;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        modifyHeadImg = (RelativeLayout) findViewById(R.id.image_head);
        modifyCommonAddress = (RelativeLayout) findViewById(R.id.modify_user_common_address);
        title = (TextView) findViewById(R.id.activity_user_setting_title_tv);
        title.setOnClickListener(this);
        modifyHeadImg.setOnClickListener(this);
        modifyCommonAddress.setOnClickListener(this);
    }

    private void selectPicture() {
        Intent intent = new Intent(this, MultiImageSelectorActivity.class);
        // whether show camera
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
        // max select image amount
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
        startActivityForResult(intent, REQUEST_IMAGE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_head:
                selectPicture();
                break;
            case R.id.modify_user_common_address:
                Intent intent = new Intent(this,AddressActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_user_setting_title_tv:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                for (String p : mSelectPath) {
                    sb.append(p);
                }
                final String filePath = sb.toString();
                File file = new File(sb.toString());
                boolean exists = file.exists();
                if (exists) {
                    Toast.makeText(this,filePath,Toast.LENGTH_SHORT).show();
                }
//                new OkHttpRequest.Builder().url(LinkContext.UPLOAD_HEAD_IMG).addHeader("Content-Disposition", "form-data").files(new Pair<String, File>("headImg", file)).upload(new ResultCallback<String>() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//                        showMsg("网络连接失败");
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        showMsg(response);
//                        //TODO 将头像路径写入数据库
//                        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
//                        if (bitmap != null) {
//                            headerview.setImageBitmap(bitmap);
//                        }
//                    }
//                });
            }
        }
    }
}
