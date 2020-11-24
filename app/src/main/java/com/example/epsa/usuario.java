package com.example.epsa;

class Usuario {

    private String nome;
    private String senha;
    private String curso;
    private String turno;

    public Usuario(){}

    public Usuario(String nome,String senha, String curso,String turno){
        this.nome = nome;
        this.senha = senha;
        this.curso = curso;
        this.turno = turno;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
