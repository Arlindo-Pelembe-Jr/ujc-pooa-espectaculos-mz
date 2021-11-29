package com.example.espetaculos_mz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class view_events extends AppCompatActivity {
        String espect [] = {"espectaculo1","espectaculo2","espectaculo3","espectaculo1","espectaculo2","espectaculo3","espectaculo1","espectaculo2","espectaculo3",};
        int img []={R.drawable.eventscom,R.drawable.eventscom,R.drawable.eventscom,R.drawable.eventscom,R.drawable.eventscom,R.drawable.eventscom,R.drawable.eventscom,R.drawable.eventscom,R.drawable.eventscom,};
        ListView listView;
        ArrayList<String> espe;
        ArrayList<Espectaculos_Model> espectaculo;
        int img1=R.drawable.eventscom;
    private FirebaseFirestore db;
    private static final String TAG = "MyActivity";

//    @Override
//    public  void onStart(){
//        super.onStart();
//        db = FirebaseFirestore.getInstance();
//
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);
        espectaculo=new ArrayList<Espectaculos_Model>();
        listView = (ListView) findViewById(R.id.list_view_events);
        listView.setHasTransientState(true);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),espectaculo,img1);
        listView.setAdapter(customBaseAdapter);

        db = FirebaseFirestore.getInstance();

        db.collection("espectaculos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
           if(error!=null){
               Log.e(TAG, "Error getting documents.", error);
           }
           for(DocumentChange doc : value.getDocumentChanges()){
               if(doc.getType() == DocumentChange.Type.ADDED){
//                   Espectaculos_Model espectaculos_model = doc.getDocument().toObject(Espectaculos_Model.class);
                   String id=doc.getDocument().getId();
                   String des=doc.getDocument().getString("descricao");
                   String local =doc.getDocument().getString("local");
                   String nome=doc.getDocument().getString("nome");
                   String promotor=doc.getDocument().getString("promotor");
                   double qtdVed=doc.getDocument().getLong("qtdVendida").doubleValue();
                   double qtd=doc.getDocument().getLong("quantidade").doubleValue();
                   double price=doc.getDocument().getLong("preco").doubleValue();

                   Espectaculos_Model espectaculos_model = new Espectaculos_Model(id,des,local,nome,promotor,qtdVed,qtd,price);
                   espectaculo.add(espectaculos_model);
//                   espe.add( doc.getDocument().get("nome").toString());
                   customBaseAdapter.notifyDataSetChanged();


               }
           }
            }
        });
//        db.collection("espectaculos")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Toast.makeText(view_events.this, "INFO : "+document.getId() + " => " + document.getData().get("nome"), Toast.LENGTH_LONG).show();
//                                espe.add( document.getData().get("nome").toString());
//                                Log.d(TAG, document.getId() + " => " + document.getData().get("nome"));
//                                customBaseAdapter.notifyDataSetChanged();
//                            }
//                        } else {
//                            Log.w(TAG, "Error getting documents.", task.getException());
//                        }
//                    }
//                });

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Toast.makeText(view_events.this,"Clicked"+position,Toast.LENGTH_SHORT).show();
//                Toast.makeText(view_events.this,"Object"+parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view_events.this,Payments_Events.class);
                i.putExtra("id",espectaculo.get(position).getId());

                i.putExtra("nome",espectaculo.get(position).getNome());
                i.putExtra("quantidade",espectaculo.get(position).getQuantidade());
                i.putExtra("qtdVendida",espectaculo.get(position).getQtdVendida());
                i.putExtra("preco",espectaculo.get(position).getPreco());

                startActivity(i);
            }
        });
    }
}