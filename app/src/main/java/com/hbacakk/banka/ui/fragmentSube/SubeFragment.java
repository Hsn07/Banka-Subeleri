package com.hbacakk.banka.ui.fragmentSube;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.hbacakk.banka.R;
import com.hbacakk.banka.data.models.Sube;
import com.hbacakk.banka.databinding.FragmentSubeBinding;
import com.hbacakk.banka.viewmodels.MainViewModel;

public class SubeFragment extends Fragment implements SubeListener {

    FragmentSubeBinding subeBinding;
    MainViewModel mainViewModel;

    SubeAdapter subeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        subeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sube, container, false);

        initialize();

        return subeBinding.getRoot();
    }


    private void initialize() {
        //region: ViewModelProvider
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        //endregion
        //region: RecyclerView Adapter oluşturma
        subeAdapter = new SubeAdapter();
        subeAdapter.setSubeListener(this);
        subeBinding.recyclerView.setAdapter(subeAdapter);
        //endregion


        //region: SearchBar
        subeBinding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                subeAdapter.search(s);
                return false;
            }
        });
        //endregion
    }

    @Override
    public void onResume() {
        super.onResume();
        getBankaData();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void getBankaData() {
        if (isNetworkConnected()) {
            subeBinding.setLoading(true);
            mainViewModel.getBankaSubeleri().observe(getActivity(), response -> {
                if (response != null) {
                    subeBinding.setLoading(false);
                    subeAdapter.setSubeArrayList(response);
                }
            });
        } else {
            showMessage();
        }
    }

    private void showMessage() {
    }

    @Override
    public void SelectSube(Sube sube) {
        SubeFragmentDirections.ActionSubeFragmentToSubeDetayFragment action = SubeFragmentDirections.actionSubeFragmentToSubeDetayFragment();
        action.setSube(sube);
        Navigation.findNavController(subeBinding.getRoot()).navigate(action);
    }
}