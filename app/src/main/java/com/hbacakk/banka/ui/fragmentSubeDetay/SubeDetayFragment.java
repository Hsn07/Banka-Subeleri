package com.hbacakk.banka.ui.fragmentSubeDetay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.hbacakk.banka.R;
import com.hbacakk.banka.data.models.Sube;
import com.hbacakk.banka.databinding.FragmentSubeDetayBinding;

public class SubeDetayFragment extends Fragment {

    FragmentSubeDetayBinding detayBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        detayBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sube_detay, container, false);

        initialize();

        return detayBinding.getRoot();
    }

    private void initialize() {
        if (getArguments() == null) {
            Toast.makeText(getActivity(), "İllegal Erişim", Toast.LENGTH_SHORT).show();
        }

        Sube sube = SubeDetayFragmentArgs.fromBundle(getArguments()).getSube();

        detayBinding.setSube(sube);

    }
}