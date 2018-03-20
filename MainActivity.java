package declanbrophy.firebaseauthexample;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button register,signIn;
    EditText emailAddress, password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        register = (Button) findViewById(R.id.register);
        signIn = (Button) findViewById(R.id.signIn);
        emailAddress = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailAddress.getText().toString();
                String pWord = password.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pWord)){
                    Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,pWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            if (password.length() < 8){
                                password.setError("Too short");
                            }else {
                                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                startActivity(intent);
                                finish();
                            }



                        }
                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailAddress.getText().toString();
                String pWord = password.getText().toString();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Please enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pWord)){
                    Toast.makeText(getApplicationContext(),"Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,pWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            if (password.length() < 8){
                                password.setError("Too short");
                            }else {
                                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                });
                mAuth.signInWithEmailAndPassword(email,pWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(MainActivity.this, "Registered Successfully",Toast.LENGTH_SHORT).show();

                        if (!task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Authentication Failed"+task.getException(),Toast.LENGTH_SHORT).show();
                        }else {
                            startActivity(new Intent(MainActivity.this, SecondActivity.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
}
