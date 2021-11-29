package com.example.espetaculos_mz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Payments_Events extends AppCompatActivity {
    private TextView qtd;
    private Button btn;
    private Button btn2;
    private Button btn3;
    private EditText nrMpesa;
    private FirebaseAuth mAuth;

    private FirebaseFirestore db;
    ProgressDialog pd;
    int currentQtd=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments__events);
        qtd = findViewById(R.id.qtdPay);
        nrMpesa = findViewById(R.id.nr_mpesa);
        ImageView imgView=(ImageView) findViewById(R.id.img_event_pay);
        Drawable drawable  = getResources().getDrawable(R.drawable.eventscom);
        imgView.setImageDrawable(drawable);
        btn = findViewById(R.id.increment);
        btn2 = findViewById(R.id.decrement);
        btn3=findViewById(R.id.payment);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);

        Bundle extras = getIntent().getExtras();
        Toast.makeText(Payments_Events.this, ""+extras.get("id")+"\n"+extras.get("nome")+"\n"+extras.get("quantidade")+"\n"+extras.get("qtdVendida"), Toast.LENGTH_SHORT).show();
//        extras.get("nome");
//        extras.get("quantidade");
//        extras.get("qtdVendida");

        qtd.setText("Quantidade: "+currentQtd);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                currentQtd++;
                currentQtd = currentQtd + 1;
                display(currentQtd);
//                qtd.notifyAll();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentQtd > 0) {
                    currentQtd = currentQtd - 1;
                    display(currentQtd);
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtNrMpesa = nrMpesa.getText().toString();
                double val= Double.parseDouble(extras.get("preco").toString()) * currentQtd;
                String email=mAuth.getCurrentUser().getEmail();
                double qutd=Double.parseDouble(extras.get("quantidade").toString());
                String id=extras.getString("id");
                String nome=extras.get("nome").toString();
                double price=Double.parseDouble(extras.get("preco").toString());
                regVenda(email,val,currentQtd,txtNrMpesa,price,id,nome,Payments_Events.this);
//                regVenda("test",120,200,"kfk",2,"f","azgo",Payments_Events.this);

            }
        });
    }
    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.qtdPay);
        displayInteger.setText("Quantidade: " + number);
    }

    private void regVenda(String email, double valor, double qtdBilhet, String nrMpesa, double preco,String idEspectaculo,String nomeEspectaculo, Context context){
        pd.setMessage("Please Wait!");
        pd.show();
        HashMap<String , Object> map = new HashMap<>();
        map.put("email",email);
        map.put("valorPago" , valor);
        map.put("qtdBilhete" , qtdBilhet);
        map.put("nrMpesa",nrMpesa);
        map.put("preco",preco);
        map.put("idEspectaculo",idEspectaculo);
        map.put("nomeEspecataculo",nomeEspectaculo);
//        HashMap<String , Object> map1 = new HashMap<>();
//        map1.put("")


        db.collection("vendas")
                .add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Payments_Events.this, "DocumentSnapshot added with ID: " + documentReference.getId(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Payments_Events.this, Home_Screen.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
//                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Payments_Events.this, "Error adding document: "+ e, Toast.LENGTH_SHORT).show();

                    }
                });

//        db.collection("espectaculos")
//                .document(idEspectaculo)
//                .set(map1)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(Payments_Events.this, "DocumentSnapshot updated ", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(Payments_Events.this, Home_Screen.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });

    }

}