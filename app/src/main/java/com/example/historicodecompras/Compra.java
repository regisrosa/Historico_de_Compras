package com.example.historicodecompras;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class Compra implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "produto")
    private String produto;

    @ColumnInfo(name = "supermercado")
    private String supermercado;

    @ColumnInfo(name = "dia")
    private String dia;

    @ColumnInfo(name = "mes")
    private String mes;

    @ColumnInfo(name = "ano")
    private String ano;

    @ColumnInfo(name = "preco")
    private String preco;


    public Compra(String produto, String supermercado, String dia, String mes, String ano, String preco) {

        this.produto = produto;
        this.supermercado = supermercado;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(String supermercado) {
        this.supermercado = supermercado;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
