package com.example.kumar.testbuild;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{
    private EditText tvEmail, tvPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvEmail = findViewById(R.id.tvEmail);
        tvPassword = findViewById(R.id.tvPassword);

        mAuth = FirebaseAuth.getInstance();
    }

    public void registerUser(View view)
    {
        String email = tvEmail.getText().toString();
        String password = tvPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("MainActivity", "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                }
                else
                {
                    Log.w("MainActivity", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
