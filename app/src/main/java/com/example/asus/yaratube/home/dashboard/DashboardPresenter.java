package com.example.asus.yaratube.home.dashboard;

import com.example.asus.yaratube.data.Repository;
import com.example.asus.yaratube.data.model.Store;
import com.example.asus.yaratube.data.remote.ApiResult;

public class DashboardPresenter implements DashboardContract.Presenter {

    private DashboardContract.View view;
    private Repository repository;

    DashboardPresenter(DashboardContract.View view) {

        this.view = view;
        this.repository = new Repository();
    }

    @Override
    public void onLoadDashboard() {

        view.showProgressBar();

        repository.getStore(new ApiResult.StoreResult() {
            @Override
            public void onSuccess(Store store) {

                view.hideProgressBar();
                view.showDashboard(store);
            }

            @Override
            public void onFail() {

                view.showErrorMessage();
            }
        });

    }
}
