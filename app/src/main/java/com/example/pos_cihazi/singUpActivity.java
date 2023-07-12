package com.example.pos_cihazi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class singUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signupEmail,signupPassword;
    private TextView loginRedirectText;
    private Button signupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        auth=FirebaseAuth.getInstance();
        signupEmail=findViewById(R.id.singupEmail);
        signupPassword=findViewById(R.id.singuppassword);
        signupButton=findViewById(R.id.singupButton);
        loginRedirectText=findViewById(R.id.loginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=signupEmail.getText().toString().trim();
                String pass=signupPassword.getText().toString().trim();

                if (user.isEmpty()){
                    signupEmail.setError("E mail can not be empty");
                }
                if (pass.isEmpty()){
                    signupPassword.setError("Password can not be emty");
                }
                else {
                    auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(singUpActivity.this,"başarılı",Toast.LENGTH_SHORT).show();
                                startActivities(new Intent[]{new Intent(singUpActivity.this,loginActivity.class)});
                            }
                            else {
                                Toast.makeText(singUpActivity.this,"signup Failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(singUpActivity.this,loginActivity.class)});
            }
        });
    }

    private void startActivities(Intent intent) {
    }
}