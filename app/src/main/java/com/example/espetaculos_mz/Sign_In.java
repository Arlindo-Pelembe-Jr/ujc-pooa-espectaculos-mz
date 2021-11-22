package com.example.espetaculos_mz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Sign_In extends AppCompatActivity {
    private EditText username;
    private EditText email;
    private EditText password;
    private Button register;
    private TextView loginUser;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    ProgressDialog pd;
    private static final String TAG = "MyActivity";

        @Override
    public  void onStart(){
        super.onStart();
        db = FirebaseFirestore.getInstance();
            mAuth = FirebaseAuth.getInstance();

        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in);
        username = findViewById(R.id.user_name);
        email = findViewById(R.id.email_reg);
        password = findViewById(R.id.txt_pass);
        register = findViewById(R.id.registrar);
        loginUser = findViewById(R.id.text_v_app_name2);

//        mAuth = FirebaseAuth.getInstance();
//        db = FirebaseFirestore.getInstance();
        pd = new ProgressDialog(this);

        register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String txtUsername = username.getText().toString();
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();

                if (TextUtils.isEmpty(txtUsername)
                        || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)){
                    Toast.makeText(Sign_In.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                } else if (txtPassword.length() < 6){
                    Toast.makeText(Sign_In.this, "Password too short!", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txtUsername ,  txtEmail , txtPassword);
                }
            }
        });
    }


    private void registerUser(final String username, final String email, String password){
        pd.setMessage("Please Wait!");
        pd.show();

        mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                HashMap<String , Object> map = new HashMap<>();
                map.put("email", email);
                map.put("name" , username);
                map.put("id" , mAuth.getCurrentUser().getUid());

                db.collection("users")
                        .add(map)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(Sign_In.this, "DocumentSnapshot added with ID: " + documentReference.getId(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Sign_In.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Sign_In.this, "Error adding document: "+ e, Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(Sign_In.this, "Error : "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("Error : "+ e.getMessage());

                Log.i(TAG, "Error "+e.getMessage() );
            }
        });
    }
}