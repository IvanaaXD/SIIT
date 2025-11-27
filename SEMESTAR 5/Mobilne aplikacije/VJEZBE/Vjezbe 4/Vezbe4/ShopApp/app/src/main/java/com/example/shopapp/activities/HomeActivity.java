package com.example.shopapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.shopapp.R;
import com.example.shopapp.databinding.ActivityHomeBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.HashSet;
import java.util.Set;


public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private NavController navController;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Set<Integer> topLevelDestinations = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ShopApp", "HomeActivity onCreate()");

        /* Umesto referenci na resurse unutar nekog layout-a moguće je
        * koristiti ViewBinding. Na osnovu konfiguracije u build.gradle fajlu
        * izgenerisana je "ActivityHomeBinding" klasa koja je povezana sa
        * XML datotekom koja sadrži rasporede elemenata korisničkog interfejsa.
        * Ova klasa sadrži reference na sve elemente unutar rasporeda,
        * omogućavajući vam pristup i manipulaciju tim elementima
        * iz Java koda na jednostavniji način. To eliminiše potrebu
        * za ručnim pronalaženjem i povezivanjem tih elemenata kroz
        * "findViewById" metode. Ukoliko imamo xml datoteku "activity_home.xml",
        * sistem će generisati klasu "ActivityHomeBinding".
        * */
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.activityHomeBase.floatingActionButton.setOnClickListener(v -> {
            Log.i("ShopApp", "Floating Action Button");
            /*
             * Ako nasoj aktivnosti zelimo da posaljemo nekakve podatke
             * za to ne treba da koristimo konstruktor. Treba da iskoristimo
             * identicnu strategiju koju smo koristili kda smo radili sa
             * fragmentima! Koristicemo Bundle za slanje podataka. Tacnije
             * intent ce formirati Bundle za nas, ali mi treba da pozovemo
             * odgovarajucu putExtra metodu.
             * */
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            intent.putExtra("title", "Cart");
            startActivity(intent);
        });

        drawer = binding.drawerLayout;
        navigationView = binding.navView;
        toolbar = binding.activityHomeBase.toolbar;
        // Postavljamo toolbar kao glavnu traku za ovu aktivnost
        setSupportActionBar(toolbar);
        // Dobavljamo referencu na glavnu traku za ovu aktivnost
        actionBar = getSupportActionBar();
        if(actionBar != null){
            // postavlja prikazivanje "strelice prema nazad" (back arrow)
            // kao indikatora navigacije na lijevoj strani Toolbar-a.
            actionBar.setDisplayHomeAsUpEnabled(false);
            // postavlja ikonu koja se prikazuje umjesto strelice prema nazad.
            // U ovom slučaju, postavljena je ikona hamburger iz drawable resursa (ic_hamburger).
            actionBar.setHomeAsUpIndicator(R.drawable.ic_hamburger);
            //ovo omogućuje da se klikom na 'home' na Toolbar-u
            // aktivira povratak na prethodnu aktivnost.
            actionBar.setHomeButtonEnabled(false);
        }

        //  ActionBarDrawerToggle se koristi za povezivanje i upravljanje navigation drawer-om
        //  unutar Android aplikacije. ActionBarDrawerToggle je klasa koja olakšava sinhronizaciju
        //  između navigation drawer-a i ActionBar-a (ili Toolbar-a) te omogućava otvaranje
        //  i zatvaranje navigation drawer-a putem ikone u ActionBar-u ili Toolbar-u.
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // dodajemo navigation drawer-u listener za događaje koji se dese.
        // actionBarDrawerToggle prati promene stanja drawera i reaguje na njih.
        drawer.addDrawerListener(actionBarDrawerToggle);
        // syncState() se koristi kako bi se uskladile ikone (npr. "hamburger" ikona)
        // i stanja između ActionBar-a (ili Toolbar-a) i drawer-a. Ova metoda osigurava
        // da se ikona na ActionBar-u (ili Toolbar-u) pravilno menja u zavisnosti
        // od stanja drawer-a (otvoreno ili zatvoreno).
        actionBarDrawerToggle.syncState();

        topLevelDestinations.add(R.id.nav_language);
        topLevelDestinations.add(R.id.nav_settings);
        // NavigationController se koristi za upravljanje promenama destinacija unutar Android
        // aplikacije korištenjem Android Navigation komponente.
        // Pomoću NavController i OnDestinationChangedListener, prati se promena trenutne
        // destinacije (screen-a/fragmenta) unutar aplikacije.
        navController = Navigation.findNavController(this, R.id.fragment_nav_content_main);
        navController.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            Log.i("ShopApp", "Destination Changed");
            // Implementacija koja se poziva kada se promijeni destinacija
            // Check if the current destination is a top-level destination (destination outside the drawer)
            int id = navDestination.getId();
            boolean isTopLevelDestination = topLevelDestinations.contains(id);
            /* Logic to determine if the destination is top level */;
            if (!isTopLevelDestination) {
                if (id == R.id.nav_products) {
                    Toast.makeText(HomeActivity.this, "Products", Toast.LENGTH_SHORT).show();
                    /* Do something when this item is selected,
                    * such as navigating to a specific fragment
                    * For example:
                    * navController.navigate(R.id.nav_products);
                    * Replace with your destination fragment ID
                    */
                } else if (id == R.id.nav_new) {
                    Toast.makeText(HomeActivity.this, "New product", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_profile) {
                    Toast.makeText(HomeActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_logout) {
                    Toast.makeText(HomeActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                }
                // Close the drawer if the destination is not a top-level destination
                drawer.closeDrawers();
            } else {
                if (id == R.id.nav_settings) {
                    Toast.makeText(HomeActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_language) {
                    Toast.makeText(HomeActivity.this, "Language", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // AppBarConfiguration odnosi se na konfiguraciju ActionBar-a (ili Toolbar-a) u Android aplikaciji
        // kako bi se omogućila navigacija koristeći Android Navigation komponentu.
        // Takođe, postavlja se bočni meni (navigation drawer) u skladu sa
        // konfiguracijom akcione trake i navigacije.
        // Svaki ID menija prosleđuje se kao skup ID-ova jer svaki meni treba smatrati odredištima najvišeg nivoa.
        mAppBarConfiguration = new AppBarConfiguration
                .Builder(R.id.nav_products, R.id.nav_new, R.id.nav_profile, R.id.nav_logout, R.id.nav_settings, R.id.nav_language)
                .setOpenableLayout(drawer)
                .build();
        // Ova linija koda postavlja navigationView da radi zajedno sa NavController-om.
        // To znači da će NavigationView reagovati na korisničke interakcije i navigaciju kroz aplikaciju putem NavController-a.
        NavigationUI.setupWithNavController(navigationView, navController);
        // Ova linija koda povezuje NavController sa ActionBar-om (ili Toolbar-om) tako da ActionBar (ili Toolbar)
        // može pravilno reagovati na navigaciju kroz različite destinacije koje su navedene unutar mAppBarConfiguration.
        // NavController će upravljati povratnom strelicom i ponašanjem akcione trake u skladu sa postavljenim destinacijama.
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

    }

    /*
     * Android aktivnosti koja se koristi za inflaciju (kreiranje) menija u aplikaciji.
     * Ova metoda je dio životnog ciklusa aktivnosti i poziva se kada se izrađuje akcijska traka (action bar)
     * za prikazivanje opcija u meniju (menu items) za određenu aktivnost.
     * Ako zelimo da nasa aktivnost/fragment prikaze ikonice unutar toolbar-a
     * to se odvija u nekoliko koraka:
     * 1. Potrebno je da napravimo listu koja specificira koje sve ikonice imamo unutar menija,
     * koje ikone koristimo i da li se uvek prikazuju ili ne.
     * 2. Nakon toga koristeci metodu onCreateOptionsMenu postavljamo ikone na toolbar.
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menu.clear();
        // koristimo ako je nasa arhitekrura takva da imamo jednu aktivnost
        // i vise fragmentaa gde svaki od njih ima svoj menu unutar toolbar-a

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    /*
     * Prestavlja deo aktivnosti unutar Android aplikacije koji se koristi
     * za upravljanje akcijama koje se odvijaju kada korisnik odabere opciju
     * izbornika (menu item) u ActionBar-u (alatnoj traci) ili Toolbar-u aplikacije.
     * Da bi znali na koju ikonicu je korisnik kliknuo
     * treba da iskoristimo jedinstveni identifikator za svaki element u listi.
     * Metoda onOptionsItemSelected ce vratiti MenuItem na koji je korisnik kliknuo.
     * Ako znamo da ce on vratiti i id, tacno znamo na koji element je korisnik
     * kliknuo, i shodno tome reagujemo.
     * */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.nav_settings:
//                Toast.makeText(HomeActivity.this, "Settings", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.nav_language:
//                Toast.makeText(HomeActivity.this, "Language", Toast.LENGTH_SHORT).show();
//                break;
//        }
        // U ovoj metodi, prvo se pomoću Navigation komponente pronalazi NavController.
        // NavController je odgovoran za upravljanje navigacijom unutar aplikacije
        // koristeći Androidov servis za navigaciju.
        navController = Navigation.findNavController(this, R.id.fragment_nav_content_main);
        // Nakon toga, koristi se NavigationUI.onNavDestinationSelected(item, navController)
        // kako bi se omogućila integracija između MenuItem-a i odredišta unutar aplikacije
        // definisanih unutar navigacionog grafa (NavGraph).
        // Ova funkcija proverava da li je odabrana stavka izbornika povezana s nekim
        // odredištem unutar navigacionog grafa i pokreće tu navigaciju ako postoji
        // odgovarajuće podudaranje.
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    // koristi se u Android aktivnosti kako bi se definisalo ponašanje povratka
    // unutar aplikacije kada se koristi navigacija kroz različite fragmente ili destinacije
    // unutar aplikacije.
    // Kada korisnik pritisne "back" ili navigacijsku strelicu unutar aplikacije,
    // ova metoda se poziva kako bi odredila ponašanje povratka.
    @Override
    public boolean onSupportNavigateUp() {
        navController = Navigation.findNavController(this, R.id.fragment_nav_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}