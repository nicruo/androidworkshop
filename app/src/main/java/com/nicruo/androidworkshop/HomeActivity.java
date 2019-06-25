package com.nicruo.androidworkshop;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nicruo.androidworkshop.databinding.ActivityHomeBinding;
import com.nicruo.androidworkshop.viewmodel.HomeViewModel;

public class HomeActivity extends AppCompatActivity {

    HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        viewModel = new HomeViewModel("Odish", 99);
        binding.setViewModel(viewModel);
        viewModel.loadData(getIntent().getExtras().getInt("pokemonID"));
    }
}
