package com.example.asus.yaratube.home.dashboard;

import android.content.Context;

import com.example.asus.yaratube.data.Repository;
import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.data.model.Store;
import com.example.asus.yaratube.data.remote.ApiResult;

import java.util.List;

public class DashboardPresenter implements DashboardContract.Presenter {

    private DashboardContract.View view;
    private Repository repository;

    DashboardPresenter(DashboardContract.View view, Context context) {

        this.view = view;
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

                view.showErrorMessage(errorMessage);
            }
        });

    }
}
