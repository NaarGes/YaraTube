package com.example.asus.yaratube.ui.home.dashboard;

import com.example.asus.yaratube.data.model.Product;
import com.example.asus.yaratube.data.model.Store;
import com.example.asus.yaratube.ui.base.BaseView;

public interface DashboardContract {

    interface View extends BaseView {

        void showDashboard(Store store);
        void showSnackbar();
    }

    interface Presenter {

        void onLoadDashboard();
    }

    interface onHomeItemClickListener {

        void onProductClick(Product product);
    }
}
