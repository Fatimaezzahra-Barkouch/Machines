package ma.projet.sqlite.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ma.projet.sqlite.bean.Salle;
import ma.projet.sqlite.databinding.FragmentHomeBinding;
import ma.projet.sqlite.service.SalleService;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    SalleService db = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView code = binding.code;
        final TextView libelle = binding.libelle;
        final Button button = binding.addSalle;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db =  new SalleService(getContext());
                db.add(new Salle(code.getText().toString(), libelle.getText().toString()));
                Toast.makeText(getContext(), " Salle crée avec succès :)" , Toast.LENGTH_LONG).show();

            }
        });

//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}