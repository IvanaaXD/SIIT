package com.example.shopapp.fragments.products;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shopapp.R;
import com.example.shopapp.databinding.FragmentProductsPageBinding;
import com.example.shopapp.fragments.FragmentTransition;
import com.example.shopapp.model.Product;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class ProductsPageFragment extends Fragment {

    public static ArrayList<Product> products = new ArrayList<Product>();
    private ProductsPageViewModel productsViewModel;
    private FragmentProductsPageBinding binding;
    private boolean isFirstSelection = true;

    public static ProductsPageFragment newInstance() {
        return new ProductsPageFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Instanciranje ViewModel-a
        // ViewModelProvider osigurava da se ViewModel ne stvara svaki
        // put prilikom promjene konfiguracije (npr. rotacije ekrana),
        // već se ponovno koristi postojeća instanca, čime se očuvaju podaci.
        productsViewModel = new ViewModelProvider(this).get(ProductsPageViewModel.class);

        binding = FragmentProductsPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        prepareProductList(products);

        SearchView searchView = binding.searchText;
        /* Posmatranje LiveData objekta i ažuriranje UI-a:
        * Ovaj deo koda dodaje observer na LiveData<String> objekat unutar
        * ProductsPageViewModel. Svaki put kada dođe do promene podatka
        * unutar LiveData objekta (u ovom slučaju searchText),
        * ta promena se automatski prosleđuje i aktivira se metoda
        * setQueryHint na SearchView komponenti sa novom vrednošću.
        * Funkcija getViewLifecycleOwner() garantuje da se observer
        * povezuje sa životnim ciklusom vlasnika prikaza fragmenta,
        * što znači da će se observer automatski ukloniti kada fragment
        * više nije vidljiv ili je uništen, sprečavajući time moguće curenje memorije.
        * */
        productsViewModel.getText().observe(getViewLifecycleOwner(), searchView::setQueryHint);

        Button btnFilters = binding.btnFilters;
        btnFilters.setOnClickListener(v -> {
            Log.i("ShopApp", "Bottom Sheet Dialog");
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.FullScreenBottomSheetDialog);
            View dialogView = getLayoutInflater().inflate(R.layout.bottom_sheet_filter, null);
            bottomSheetDialog.setContentView(dialogView);
            bottomSheetDialog.show();
        });

        Spinner spinner = binding.btnSort;
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.sort_array));
        // Specify the layout to use when the list of choices appears
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Provera da li je ovo prvi poziv
                if (isFirstSelection) {
                    isFirstSelection = false;
                    return; // Izlazak iz metode bez prikazivanja dijaloga
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setMessage("Change the sort option?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.v("ShopApp", (String) parent.getItemAtPosition(position));
                                ((TextView) parent.getChildAt(0)).setTextColor(Color.MAGENTA);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = dialog.create();
                alert.show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        FragmentTransition.to(ProductsListFragment.newInstance(products), getActivity(),
                false, R.id.scroll_products_list);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void prepareProductList(ArrayList<Product> products){
        products.clear();
        products.add(new Product(1L, "Samsung S23 Ultra White", "Description 1", R.drawable.samsung_galaxy_s23_ultra));
        products.add(new Product(2L, "Samsung S23 Ultra Gray", "Description 2", R.drawable.samsung_galaxy_s23_ultra_green));
        products.add(new Product(3L, "Samsung S23 Ultra White", "Description 1", R.drawable.samsung_galaxy_s23_ultra));
        products.add(new Product(4L, "Samsung S23 Ultra Gray", "Description 2", R.drawable.samsung_galaxy_s23_ultra_green));
    }
}