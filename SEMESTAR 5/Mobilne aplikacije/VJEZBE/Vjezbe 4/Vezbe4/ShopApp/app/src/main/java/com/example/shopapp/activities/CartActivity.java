package com.example.shopapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shopapp.R;
import com.example.shopapp.fragments.cart.FirstFragment;
import com.example.shopapp.fragments.FragmentTransition;
import com.example.shopapp.fragments.cart.SecondFragment;

public class CartActivity extends AppCompatActivity {

    // Requesting permission to INTERNET and RECORD AUDIO
    private boolean isPermissions = true;
    private String [] permissions = {
            Manifest.permission.INTERNET,
            Manifest.permission.RECORD_AUDIO
    };
    private static final int REQUEST_PERMISSIONS = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        TextView cartTitle = findViewById(R.id.cart_title);
        cartTitle.setText(getIntent().getStringExtra("title"));
        /*
         * Fragmenti ne mogu da postoje samostalno, njih lepimo na aktivnosti.
         * Pogledati detalje ovih metoda.
         * */
        Button btnFragment1 = findViewById(R.id.btnFragment1);
        btnFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransition.to(FirstFragment.newInstance("Fragment 1", "Ovo je fragment 1"), CartActivity.this, false, R.id.downView);
            }
        });
        Button btnFragment2 = findViewById(R.id.btnFragment2);
        btnFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransition.to(SecondFragment.newInstance("Fragment 2", "Ovo je fragment 2"), CartActivity.this, false, R.id.downView);
            }
        });
        /*
         * Proveravanje prava pristupa
         * */
        onRequestPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_PERMISSIONS:
                for(int i = 0; i < permissions.length; i++) {
                    Log.i("ShopApp", "permission " + permissions[i] + " " + grantResults[i]);
                    if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                        isPermissions = false;
                    }
                }
                break;
        }

        if (!isPermissions) {
            Log.e("ShopApp", "Error: no permission");
            finishAndRemoveTask();
        }

    }

    private void onRequestPermission(){
        Log.i("ShopApp", "onRequestPermission");
        ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS);
    }

}