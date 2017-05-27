package awesomefat.com.tutr;

import android.content.Intent;
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

public class SignIn extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    private EditText signInEmail;
    private EditText signInPassword;
    private SignIn signInActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        this.signInEmail = (EditText)this.findViewById(R.id.signInEmailET);
        this.signInPassword = (EditText)this.findViewById(R.id.signInPasswordET);
        this.signInActivity = this;
        mAuth = FirebaseAuth.getInstance();
    }

    public void signInButtonPressed (View v)
    {
        if (signInEmail.getText().toString().equals("") || signInPassword.getText().toString().equals("")) {
            Toast.makeText(signInActivity, "Enter Information", Toast.LENGTH_SHORT).show();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(signInEmail.getText().toString(), signInPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {
                        // Sign in success, update UI with the signed-in user's information
                        Core.currentUser = mAuth.getCurrentUser();
                        Intent i = new Intent(signInActivity, MainActivity.class);
                        signInActivity.startActivity(i);
                    }
                    else
                    {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(signInActivity, "Authentication failed.",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            
        }
    }
}
