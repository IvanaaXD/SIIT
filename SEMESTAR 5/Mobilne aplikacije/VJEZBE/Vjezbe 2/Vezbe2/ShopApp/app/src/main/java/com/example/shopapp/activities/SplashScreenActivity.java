package com.example.shopapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.shopapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /*
        * Ovom opcijom je sakriven toolbar unutar ove aktivnosti
        * */
        getSupportActionBar().hide();
        int SPLASH_TIME_OUT = 3000;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                /*
                 * Intent je glavna klasa unutar Android-a za pokretanje ili prelazak na druge delove
                 * vase aplikacije. Da bi pokrenuli drugu aktivnost imamo dve opcije
                 * Prva opcija je eksplicitan intent, gde moramo da kazemo sa koje aktivnosti prelazimo na koju aktivnost:
                 * NPR: sa SplashScreenActivity.this prelazimo na HomeActivity.class
                 * Druga opcija je implicitni intent, gde ne moramo da kažemo gde prelazimo ali moramo
                 * da kažemo šta planiramo da uradimo.
                 */
                Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                /*
                 * Pozivom startActivity metode, saljemo poruku Android-u da on za nas pokrene drugu aktivnost,
                 * nakon cega korisnik biva prebacen na novu aktivnost.
                 **/
                startActivity(intent);
                /*
                * Da ne bi moglo da se vrati na SplashScreen ako korisnik
                * klikne na back dugme
                * */
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}