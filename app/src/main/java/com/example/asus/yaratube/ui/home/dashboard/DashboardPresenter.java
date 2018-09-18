package com.example.asus.yaratube.ui.home.dashboard;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.remote.Repository;
import com.example.asus.yaratube.data.model.Store;
import com.example.asus.yaratube.data.remote.ApiResult;
import com.example.asus.yaratube.util.Util;

public class DashboardPresenter implements DashboardContract.Presenter {

    private DashboardContract.View view;
    private Repository repository;
    private Context context;

    DashboardPresenter(DashboardContract.View view, Context context) {

        this.view = view;
        this.context = context;
        this.repository = new Repository(context);
    }

    @Override
    public void onLoadDashboard() {

        view.showProgressBar();

        repository.getStore(new ApiResult<Store>() {
            @Override
            public void onSuccess(Store store) {

                view.hideProgressBar();
                view.showDashboard(store);
            }

            @Override
            public void onFail(String errorMessage) {

                view.hideProgressBar();
                if (!Util.isNetworkAvailable(context))
                    view.showSnackbar();
            }
        });

    }
}
