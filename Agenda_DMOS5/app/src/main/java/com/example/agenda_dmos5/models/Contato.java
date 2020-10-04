package com.example.agenda_dmos5.models;

public class Contato {

    private String nome;
    private String telefone;
    private String celular;

    public Contato(String nome, String telefone, String celular){
        this.nome = nome;
        this.telefone = telefone;
        this.celular = celular;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Contato" +
                "Nome:" + nome + '\'' +
                ", telefone:'" + telefone + '\'' +
                ", Celular:'" + celular + '\'' +
                '}';
    }
}
