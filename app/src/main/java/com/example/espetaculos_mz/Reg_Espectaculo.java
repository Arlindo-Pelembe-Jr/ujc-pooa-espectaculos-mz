package com.example.espetaculos_mz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Reg_Espectaculo extends AppCompatActivity {
    private EditText name;
    private EditText promotorName;
    private EditText local;
    private EditText qtdBilhete;
    private EditText desc;
    private Button btnRegEspec;
    private FirebaseFirestore db;
    ProgressDialog pd;
    private static final String TAG = "MyActivity";




    @Override
    public  void onStart(){
        super.onStart();
        db = FirebaseFirestore.getInstance();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_espectaculo);
        name = findViewById(R.id.nome_espectaculo);
        promotorName = findViewById(R.id.promotor_nome);
        local = findViewById(R.id.local);
        qtdBilhete = findViewById(R.id.qtd_bilhete);
        desc = findViewById(R.id.desc_espectaculo);
        btnRegEspec = findViewById(R.id.pub_espectaculo_form);

        pd = new ProgressDialog(this);

        btnRegEspec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtName = name.getText().toString();
                String txtPromotor= promotorName.getText().toString();
                String txtLocal = local.getText().toString();
                String txtQtdBilh = qtdBilhete.getText().toString();
                String txtDesc = desc.getText().toString();

                regEspectaculo(txtName,txtPromotor,txtLocal,txtQtdBilh,txtDesc);
            }
        });
    }

    private void regEspectaculo(String nameE,String promotor,String local,String qtd,String des){
        pd.setMessage("Please Wait!");
        pd.show();
        HashMap<String , Object> map = new HashMap<>();
        map.put("nome",nameE);
        map.put("promotor" , promotor);
        map.put("local" , local);
        map.put("quantidade",qtd);
        map.put("descricao",des);

        db.collection("espectaculos")
                .add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Reg_Espectaculo.this, "DocumentSnapshot added with ID: " + documentReference.getId(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Reg_Espectaculo.this, Home_Screen.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Reg_Espectaculo.this, "Error adding document: "+ e, Toast.LENGTH_SHORT).show();

                    }
                });

    }
}