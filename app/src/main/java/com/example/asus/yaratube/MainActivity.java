package com.example.asus.yaratube;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.developerinfo.AboutFragment;
import com.example.asus.yaratube.developerinfo.ContactFragment;
import com.example.asus.yaratube.home.BNHolderFragment;
import com.example.asus.yaratube.productlist.ProductListFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements TransferBetweenFragments {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FIXME minimum sdk is 17, use another way
        // change app direction to RTL
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        setDrawer();
        setBottomNavigationFragment();

    }

    public void setDrawer() {

        // drawer settings
        drawerLayout = findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.Open, R.string.Close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // click listener
        NavigationView navigationView = findViewById(R.id.drawer_nv);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                drawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.profile_nd:
                        // TODO
                        Toast.makeText(MainActivity.this, "پروفایل کاربر", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.about_nd:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_container, AboutFragment.newInstance()).addToBackStack("about fragment").commit();
                        return true;

                    case R.id.contact_nd:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_container, ContactFragment.newInstance()).addToBackStack("contact fragment").commit();
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    public void setBottomNavigationFragment() {

        BNHolderFragment bnHolderFragment = BNHolderFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, bnHolderFragment).commit();
    }

    // Open the drawer when the button is tapped
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return (actionBarDrawerToggle.onOptionsItemSelected(item)) || super.onOptionsItemSelected(item);
    }

    // close the drawer if opened when back button tapped
    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void goFromCategoryToProductList(int categoryId) {


        ProductListFragment productListFragment = ProductListFragment.newInstance(categoryId);
        getSupportFragmentManager().beginTransaction().addToBackStack("productlist fragment")
                .replace(R.id.main_container, productListFragment).commit();
    }
}
