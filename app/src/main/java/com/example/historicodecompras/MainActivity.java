package com.example.historicodecompras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Database db;

    ArrayList<Compra> compras;

    RecyclerAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    Spinner spinner;

    String txt_mes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        spinner();
        

    }


    @Override
    protected void onResume(){
        super.onResume();

        listarDados();
    }


    public void listarDados(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    db = Room.databaseBuilder(getApplicationContext(), Database.class, "DB_HistoricoCompras").build();
                    //para setar a activity é necessário abrir uma thread para manipular o bd e setar o recyclerView
                    recyclerView =findViewById(R.id.recyclerView);

                    compras = new ArrayList<>();
                    compras = (ArrayList<Compra>) db.compraDao().getAll();

                    linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    adapter = new RecyclerAdapter(compras);

                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();


    }


    public void filtrar(View view){
        txt_mes = null;

        Object mesEscolhido = spinner.getSelectedItem();

        if(mesEscolhido.equals("1-Jan")){
            txt_mes = "01";
        }else if(mesEscolhido.equals("2-Fev")){
            txt_mes = "02;";
        }else if (mesEscolhido.equals("3-Mar")){
            txt_mes = "03";
        }else if(mesEscolhido.equals("4-Abr")){
            txt_mes = "04";
        }else if (mesEscolhido.equals("5-Mai")){
            txt_mes = "05";
        }else if (mesEscolhido.equals("6-Jun")){
            txt_mes = "06";
        }else if (mesEscolhido.equals("7-Jul")){
            txt_mes = "07";
        }else if (mesEscolhido.equals("8-Ago")){
            txt_mes = "08";
        }else if (mesEscolhido.equals("9-Set")){
            txt_mes = "09";
        }else if (mesEscolhido.equals("10-Out")){
            txt_mes = "10";
        }else if (mesEscolhido.equals("11-Nov")){
            txt_mes = "11";
        }else {
            txt_mes = "12";
        }

        //Fiz isso para limpar a recyclerView de entrada quando é feito uma busca filtrada, pois a mesma pode interferir/poluir o fragment de filtragem
        recyclerView.setLayoutManager(null);
        recyclerView.setAdapter(null);

        Fragment fragment = FiltragemFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString("MES", txt_mes);

        fragment.setArguments(bundle);
        transaction.replace(R.id.frame_container, fragment, "filtragem_fragment");

        transaction.commit();


    }


    public void spinner(){
        spinner = findViewById(R.id.spinner);

        String[] stringsSpinner ={"", "1-Jan", "2-Fev", "3-Mar", "4-Abr", "5-Mai", "6-Jun", "7-Jul",
                "8-Ago", "9-Set", "10-Out", "11-Nov", "12-Dez"};

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, stringsSpinner);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);

    }

    public void cadastrar(View view) {

        Intent intent = new Intent(this, CadastrarActivity.class);
        startActivity(intent);
    }
}