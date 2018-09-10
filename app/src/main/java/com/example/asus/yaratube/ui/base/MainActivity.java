package com.example.asus.yaratube.ui.base;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.UserRepository;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.data.model.Product;
import com.example.asus.yaratube.ui.home.BottomHolderFragment;
import com.example.asus.yaratube.ui.productdetail.ProductDetailFragment;
import com.example.asus.yaratube.ui.productlist.ProductListFragment;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements TransferBetweenFragments {


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // change app direction to RTL
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        //Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        setBottomNavigationFragment();

        AppDatabase database = AppDatabase.getAppDatabase(this);
        UserRepository userRepository = new UserRepository(this);
        userRepository.setDatabase(database);
    }

    public void setBottomNavigationFragment() {

        BottomHolderFragment bottomHolderFragment = BottomHolderFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, bottomHolderFragment).commit();
    }

    @Override
    public void goFromCategoryToProductList(Category category) {

        ProductListFragment productListFragment = ProductListFragment.newInstance(category);
        getSupportFragmentManager().beginTransaction().addToBackStack(productListFragment.getClass().getName())
                .add(R.id.main_container, productListFragment).commit();
    }

    @Override
    public void goToProductDetail(Product product, String categoryName) {

        ProductDetailFragment productDetailFragment = ProductDetailFragment.newInstance(product, categoryName);
        getSupportFragmentManager().beginTransaction().addToBackStack(productDetailFragment.getClass().getName())
                .add(R.id.main_container, productDetailFragment).commit();
    }
}
