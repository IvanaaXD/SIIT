package com.example.shopapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shopapp.R;


/*
 * Da bi napravili Fragment, potrebno je da napravite klasu koja nasledjuje
 * klasu Fragment. Za razliku od aktivnosti, fragmente ne moramo da
 * specificiramo unutar Manifest file-a.
 * Za rasliku od aktivnosti, fragmenti ne mogu da postoje nezavniso. Njih vezemo za neku aktivnost.
 * Time postizemo lakse izmenjiv interface i mogucnost da iskoristimo
 * celokupan ekran pogotovo u landscap-e rezimu.
 * */
public class SecondFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "description";

    private String title;
    private String description;

    public SecondFragment() {
        // Required empty public constructor
    }

    /*
     * Da bi napravili Fragment, treba da izbegavamo direktan poziv konstruktora
     * Za to mozemo iskoristiti staticku metodu koja to moze da uradi za nas.
     * Staticka funkcija emulira konstruktor.
     * Ako je potrebno da fragmentu prosledimo nekakve parametre, to mozemo da uradimo
     * koristeci dodatne parametre opisane klasom Bundle.
     * */
    public static SecondFragment newInstance(String title, String description) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        args.putString(ARG_PARAM2, description);
        fragment.setArguments(args);
        return fragment;
    }
    /*
     * Zivotni ciklus fragmenta je vezan za zivnoti ciklus aktivnosti.
     * Parametre koje smo prosledili u metodi newInstance koristeci Bundle, kasnije mozemo
     * da dobijemo nazad koristeci metodu getArguments.
     * */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ShopApp", "SecondFragment onCreate()");

        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
            description = getArguments().getString(ARG_PARAM2);
        }
    }
    /*
     * Kao i aktivnost i fragment ima specificnu metodu za postavljanje layout-a fragment-a.
     * U metodi onCreateView kreiramo View fragmenta
     * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("ShopApp", "SecondFragment onCreateView()");
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        TextView fragment2Title = view.findViewById(R.id.fragment2Title);
        fragment2Title.setText(title);
        TextView fragment2Description = view.findViewById(R.id.fragment2Description);
        fragment2Description.setText(description);
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("ShopApp", "SecondFragment onAttach()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("ShopApp", "SecondFragment onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("ShopApp", "SecondFragment onDetach()");
    }
}