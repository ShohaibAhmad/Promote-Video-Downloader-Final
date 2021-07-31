package com.promoteprovider.promotevideodownloader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;


import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.firebase.messaging.FirebaseMessaging;

import com.promoteprovider.promotevideodownloader.databinding.ActivityMainBinding;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;


import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private InterstitialAd mInterstitialAd;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppAd.disableSplash();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
     //notification
        FirebaseMessaging.getInstance().subscribeToTopic("notification");

        StartAppSDK.init(this, "206798011", true);

        binding.WhatsUp.setOnClickListener(view -> {

                    Intent intent = new Intent(MainActivity.this, WhatsUpActivity.class);
                    startActivity(intent);
                    loadAds();
        });

        binding.Facebook.setOnClickListener(view -> {

                        Intent intent = new Intent(MainActivity.this, FacebookActivity.class);
                        startActivity(intent);
            loadAds();

                });

   binding.ShareChat.setOnClickListener(view -> {
                   Intent intent = new Intent(MainActivity.this, ShareChatActivity.class);
                   startActivity(intent);
       loadAds();
        });

   binding.File.setOnClickListener(view -> {

                   Intent intent = new Intent(MainActivity.this, InstagramActivity.class);
                   startActivity(intent);
       loadAds();
        });

   binding.About.setOnClickListener(view -> {

                   Intent intent = new Intent(MainActivity.this, About.class);
                   startActivity(intent);
       loadAds();
        });

   binding.Policy.setOnClickListener(view -> {

                   Intent intent = new Intent(MainActivity.this, Policy.class);
                   startActivity(intent);
       loadAds();

        });
   showPermissionDialog();
   checkPermission();

    }

    private void loadAds() {
        StartAppAd.showAd(MainActivity.this);
    }


    private void showPermissionDialog() {
        if (SDK_INT >= Build.VERSION_CODES.R) {

            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", new Object[]{getApplicationContext().getPackageName()})));
                startActivityForResult(intent, 2000);
            } catch (Exception e) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 2000);

            }

        } else
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, 333);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void checkPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager();
        } else {
            int write = ContextCompat.checkSelfPermission(getApplicationContext(),
                    WRITE_EXTERNAL_STORAGE);
            int read = ContextCompat.checkSelfPermission(getApplicationContext(),
                    READ_EXTERNAL_STORAGE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 333) {
            if (grantResults.length > 0) {
                boolean write = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean read = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (read && write){

                }else {

                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000) {
            if (SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {

                } else {

                }
            }
        }
    }
}