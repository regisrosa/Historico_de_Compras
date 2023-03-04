package com.example.historicodecompras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CadastrarActivity extends AppCompatActivity {

    EditText etNomeProduto;
    EditText etNomeEstabelecimento;
    EditText etPreco;
    EditText etDia;
    EditText etMes;
    EditText etAno;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        etNomeProduto = findViewById(R.id.et_nomeProduto);
        etNomeEstabelecimento = findViewById(R.id.et_nomeEstabelecimento);
        etPreco = findViewById(R.id.et_preco);
        etDia = findViewById(R.id.et_dia);
        etMes = findViewById(R.id.et_mes);
        etAno = findViewById(R.id.et_ano);

        db = Room.databaseBuilder(getApplicationContext(), Database.class, "DB_HistoricoCompras").build();

    }


    public void salvar(View view) {

        String nomeProduto = etNomeProduto.getText().toString().toUpperCase();
        String nomeEstab = etNomeEstabelecimento.getText().toString().toUpperCase();
        String preco = etPreco.getText().toString();
        String dia = etDia.getText().toString();
        String mes = etMes.getText().toString();
        String ano = etAno.getText().toString();

        if (nomeProduto.isEmpty() || nomeEstab.isEmpty() || preco.isEmpty() || dia.isEmpty() || mes.isEmpty() || ano.isEmpty()) {

            Toast.makeText(this, "Favor não deixar nenhum \ncampo em branco!", Toast.LENGTH_SHORT).show();

        } else {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Compra compra = new Compra(nomeProduto, nomeEstab, dia, mes, ano, preco);
                    CompraDao compraDao = db.compraDao();
                    compraDao.insert(compra);
                    db.close();

                    Toast.makeText(CadastrarActivity.this, "Produto cadastrado com sucesso.", Toast.LENGTH_LONG).show();
                }

            }).start();

        }

    }
}