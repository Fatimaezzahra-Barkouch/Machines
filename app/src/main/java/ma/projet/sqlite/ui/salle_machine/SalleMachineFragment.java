package ma.projet.sqlite.ui.salle_machine;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ma.projet.sqlite.R;
import ma.projet.sqlite.bean.Salle;
import ma.projet.sqlite.databinding.FragmentSalleMachineBinding;
import ma.projet.sqlite.databinding.FragmentSalleMachineListBinding;
import ma.projet.sqlite.databinding.FragmentSlideshowBinding;
import ma.projet.sqlite.service.MachineService;
import ma.projet.sqlite.service.SalleService;

/**
 * A fragment representing a list of Items.
 */
public class SalleMachineFragment extends Fragment  {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private MachineService ms;
    private SalleService ss;
    private FragmentSalleMachineListBinding binding;

    private ListView liste;
    private Spinner spinner;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SalleMachineFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SalleMachineFragment newInstance(int columnCount) {
        SalleMachineFragment fragment = new SalleMachineFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_salle_machine_list, container, false);
        binding = FragmentSalleMachineListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();




        RecyclerView recyclerView =  binding.listForMachineSalle;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), mColumnCount));
            }
            recyclerView.setHasFixedSize(true);

            ms=new MachineService(getContext());
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(ms.findAll()));
//        }


        spinner = (Spinner) binding.spinner;
//        liste = (ListView) binding.listView;
        ss=new SalleService(getContext());
        ArrayAdapter<String> adapter;
        List<String> list = new ArrayList<String>();
        for (Salle salle : ss.findAll()){
            list.add(salle.getCode());
        }
        Log.d("tag",list.toString());
        adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, list);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int id = ss.findByCode(spinner.getSelectedItem().toString()).getId();
                recyclerView.setAdapter(new MyItemRecyclerViewAdapter(ms.findMachines(id)));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return root;
    }





}