package com.promoteprovider.promotevideodownloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.hcr2bot.instagramvideosdownloader.InstaVideo;
import com.promoteprovider.promotevideodownloader.databinding.ActivityInstagramMainBinding;

public class Instagram_Main extends AppCompatActivity {
    private ActivityInstagramMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_instagram_main);

        binding.Download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                InstaVideo.downloadVideo(Instagram_Main.this,binding.instagramUrl.getText().toString());

                InstaVideo.downloadVideo(Instagram_Main.this,binding.instagramUrl.getText().toString(),"Instagram"); // it'll create new folder in gallery with provided directory name.
            }
        });

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        else { //you dont need to worry about these stuff below api level 23

        }

    }
}