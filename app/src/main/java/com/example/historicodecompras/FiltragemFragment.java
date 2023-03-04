package com.example.historicodecompras;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FiltragemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FiltragemFragment extends Fragment{

    Database db;

    List<Compra> dadosFiltrados = new ArrayList<>();
    String txt_mes;


    public FiltragemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment FiltragemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FiltragemFragment newInstance() {
        FiltragemFragment fragment = new FiltragemFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onResume();
        db = Room.databaseBuilder(getActivity(), Database.class, "DB_HistoricoCompras").build();

        View view =  inflater.inflate(R.layout.fragment_filtragem, container, false);
        RecyclerView recyclerViewFiltragem = view.findViewById(R.id.recyclerview_Filtragem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        Bundle bundle = getArguments();
        txt_mes = bundle.getString("MES");


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    dadosFiltrados = db.compraDao().getByMonth(txt_mes);

                    recyclerViewFiltragem.setLayoutManager(linearLayoutManager);
                    RecyclerAdapter adapter = new RecyclerAdapter(dadosFiltrados);
                    recyclerViewFiltragem.setAdapter(adapter);
                    db.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();

        return view;
    }

}

