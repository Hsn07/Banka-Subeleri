package com.hbacakk.banka.ui.fragmentSubeDetay;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.hbacakk.banka.R;
import com.hbacakk.banka.data.models.Sube;
import com.hbacakk.banka.databinding.DialogInfoBinding;
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

        detayBinding.layoutYolTarifiAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sube.Adres != null && sube.Adres.length() > 10) {
                    alertDialog("Google Maps Açılıyor", "Konum bilgisi için google maps açılacaktır. Devam etmek istiyor musunuz?", sube.Adres);
                } else {
                    Toast.makeText(getActivity(), "Hatalı veya eksik adress", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void alertDialog(String title, String message, String adress) {
        AlertDialog.Builder _builder = new AlertDialog.Builder(getActivity());
        DialogInfoBinding dialogInfoBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()),
                R.layout.dialog_info, null, false);

        _builder.setView(dialogInfoBinding.getRoot());

        AlertDialog dialog = _builder.create();

        dialogInfoBinding.setTitle(title);
        dialogInfoBinding.setMessage(message);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        dialogInfoBinding.buttonOk.setOnClickListener(view -> {
            openTheGoogleMaps(adress);
            dialog.dismiss();
        });
        dialogInfoBinding.imageViewClose.setOnClickListener(view -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    private void openTheGoogleMaps(String adress) {
        String uri = "http://maps.google.co.in/maps?q=" + adress;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

}