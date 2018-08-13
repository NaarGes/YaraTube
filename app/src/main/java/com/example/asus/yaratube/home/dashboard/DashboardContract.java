package com.example.asus.yaratube.home.dashboard;

import com.example.asus.yaratube.data.model.Store;
import com.example.asus.yaratube.util.BaseView;

public interface DashboardContract {

    interface View extends BaseView {

        void showDashboard(Store store);
    }

    interface Presenter {

        void onLoadDashboard();
    }
}
