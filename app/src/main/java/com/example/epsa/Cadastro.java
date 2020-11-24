package com.example.epsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Cadastro extends AppCompatActivity {

    DatabaseReference fireDB = FirebaseDatabase.getInstance().getReference();

    EditText usuario;
    EditText senha;
    EditText confirmaSenha;
    EditText curso;
    EditText turno;
    boolean existe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        usuario = findViewById(R.id.eTUsuario);
        senha = findViewById(R.id.eTSenha);
        confirmaSenha = findViewById(R.id.eTConfirmaSenha);
        curso = findViewById(R.id.eTCurso);
        turno = findViewById(R.id.eTTurno);

    }

    public void InserirDados(){
        //Instancia um novo Usuário
        Usuario aluno = new Usuario(usuario.getText().toString().trim(), senha.getText().toString(),
                curso.getText().toString().trim(), turno.getText().toString().trim());
        //Insere um novo Usuário no banco
        fireDB.child("EPSA").child(aluno.getNome()).setValue(aluno);
        existe = false;
        Limpar();
        Toast.makeText(this,"Usuário cadastrado com Sucesso!",Toast.LENGTH_LONG).show();
    }

    public void ConferirDados(View v) {
        if (!usuario.getText().toString().isEmpty()) {
            if (!senha.getText().toString().isEmpty()) {
                if (!confirmaSenha.getText().toString().isEmpty()) {
                    if (senha.getText().toString().equals(confirmaSenha.getText().toString())) {
                        if (!curso.getText().toString().isEmpty()) {
                            if (!turno.getText().toString().isEmpty()) {
                                buscar();
                            } else {
                                //Turno em branco
                                turno.requestFocus();
                                turno.setError("Informe o turno!");
                            }
                        } else {
                            //Curso em branco
                            curso.requestFocus();
                            curso.setError("Informe o curso!");
                        }
                    } else {
                        //senha diferente
                        confirmaSenha.requestFocus();
                        confirmaSenha.setError("Senhas não batem!");
                    }
                } else {
                    //Confirma senha em branco
                    confirmaSenha.requestFocus();
                    confirmaSenha.setError("Repita a senha!");
                }
            } else {
                //Senha em branco
                senha.requestFocus();
                senha.setError("Insira a Senha!");
            }
        } else {
            //Usuario em branco
            usuario.requestFocus();
            usuario.setError("Insira o nome do Usuário!");
        }
    }


    public void buscar() {
        //Acessa o firebase
        FirebaseDatabase.getInstance().getReference("EPSA").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Retorna TRUE caso exista o nome do Usuario no Firebase
                existe = snapshot.child(usuario.getText().toString().trim()).exists();
                if (existe){
                    if(!usuario.getText().toString().isEmpty()){
                        usuario.requestFocus();
                        usuario.setError("Usuário " + usuario.getText().toString().trim() + " já existe!");
                    }
                } else {
                    InserirDados();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void btnLimpar(View v){
        Limpar();
    }

    public void Limpar(){
        usuario.setText("");
        senha.setText("");
        confirmaSenha.setText("");
        curso.setText("");
        turno.setText("");
    }

}
