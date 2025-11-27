package com.example.shopapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shopapp.R;
/*
 * Aktivnost pravimo tako sto napravimo klasu koja nasledjuje AppCompatActivity klasu.
 * Nasledjivanjem ove klase, dobijamo metode zivotnog ciklusa Aktivnosti.
 * NAPOMENA: NE KORISTITI KONSTRUKTOR PRILIKOM KREIRANJA AKTIVNOSTI!!
 * */
public class HomeActivity extends AppCompatActivity {
    /*
     * Unutar onCreate metode, postavljamo izgled nase aktivnosti koristeci setContentView
     * U ovoj metodi mozemo dobaviti sve view-e (widget-e, komponente interface-a).
     * Moramo voditi racuna, ovde se ne sme nalaziti kod koji ce blokirati prelazak aktivnosti
     * u naredne metode! To znaci da izvrsavanje dugackih operacija treba izbegavati ovde.
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         * Metoda setContentview() veze aktivnost sa layout-om
         * R -> referenca na resources folder tj. na resurse
         * R.layout.activity_home -> pristupamo preko naziva layout-a
         * */
        setContentView(R.layout.activity_home);
        /*
        * Log klasa se koristi za logovanje informacija, errora, warning-a unutar aplikacije.
        * Logovi se ispisuju u logcat delu i moguce ih je filtrirati po zadatom tag-u
        * (prvi parametar)
        * */
        Log.d("ShopApp", "HomeActivity onCreate()");
        /*
         * Toast klasa se koristi za prikazivanje obavestenja za odredjeni vremenski interval.
         * Posle nekog vremena nestaje. Ne blokira interakciju korisnika
         * */
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
        /*
         * Koristeci metodu findViewById, mozemo dobaviti tacnu komponentu interface-a preko
         * njenog jedinstvenog id-a (vise o tome kasnije).
         * Na komponente mozemo dodavati razne akcije listenere na slican nacin
         * kako se to radi u drugi programskim jezicima.
         */
        Button btnExplicitIntent = findViewById(R.id.btnExplicitIntent);
        btnExplicitIntent.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);

            }
        });

        Button btnImplicitIntent = findViewById(R.id.btnImplicitIntent);
        btnImplicitIntent.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                /*
                 * Drugi nacin da se pokrene neka aktivnost jeste implicitan intent.
                 * Razlika od prethodnog jeste u tome sto ovde ne kazemo na koju aktvnost zelimo da predjeno
                 * nego specificiramo parametre, pa ce nam Android ponuditi sve moguce opcije od kojih mi izaberemo
                 * onu koja nam je najbolja (u slucaju da imamo vise apliacija koje mogu da obve isti posao), ili
                 * ce pokrenuti podrazumevanu (ako je takva postavljena ili ako nema drugih aplikacija koje
                 * zadovojlvaju taj kriterijum).
                 * NPR: Ako korisnik ima instalirano vise browser-a na uredjaju, a hocemo da otvorimo https://www.google.com,
                 * Android ce nam ponuditi sve pretrazivace koji mogu da zavrse tu akciju. Ako imamo samo jedan instaliran
                 * onda ce pokrenuti taj jedan jedini.
                 * */
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(intent);
            }
        });

    }
    /*
    * onStart se poziva kada se aktivnost prvi put startuje, posle onCreate metode ili
    * kada se vratimo klikom na back dugme ponovo na aktivnost
    * */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ShopApp", "HomeActivity onStart()");
    }
    /*
     * onResume se poziva kada je aktivnost u fokusu i korisnik
     * je u interakciji sa aktivnosti.
     * */
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("ShopApp", "HomeActivity onResume()");
    }

    /*
     * onPause se poziva kada je aktivnost delimicno prekrivena.
     * */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ShopApp", "HomeActivity onPause()");
    }
    /*
     * onStop se poziva kada je aktivnost u potpunosti prekrivena nekom drugom aktivnošću
     * */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ShopApp", "HomeActivity onStop()");
    }
    /*
     * onDestory se poziva kada je aktivnost u potpunosti unistena,
     * ondosno kada je aplikacija zatvorena
     * Izbrisana je iz background-a.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ShopApp", "HomeActivity onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ShopApp", "HomeActivity onRestart()");
    }
}