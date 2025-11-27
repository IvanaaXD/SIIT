package com.example.shopapp.fragments.products;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.shopapp.adapters.ProductListAdapter;
import com.example.shopapp.databinding.FragmentProductsListBinding;
import com.example.shopapp.model.Product;
import java.util.ArrayList;

public class ProductsListFragment extends ListFragment {
    private ProductListAdapter adapter;
    private static final String ARG_PARAM = "param";
    private ArrayList<Product> mProducts;
    private FragmentProductsListBinding binding;

    public static ProductsListFragment newInstance(ArrayList<Product> products){
        ProductsListFragment fragment = new ProductsListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM, products);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ShopApp", "onCreate Products List Fragment");
        if (getArguments() != null) {
            mProducts = getArguments().getParcelableArrayList(ARG_PARAM);
            adapter = new ProductListAdapter(getActivity(), mProducts);
            setListAdapter(adapter);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("ShopApp", "onCreateView Products List Fragment");
        binding = FragmentProductsListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
