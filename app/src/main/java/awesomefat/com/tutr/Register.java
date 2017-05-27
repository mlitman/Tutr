package awesomefat.com.tutr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static awesomefat.com.tutr.R.layout.activity_main;
import static awesomefat.com.tutr.R.layout.activity_register;

public class Register extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    private EditText signUpemail;
    private EditText signUpPassword;
    private EditText confirmPassword;
    private Button signUpButton;
    private Register registerActivity;
    private EditText firstName;
    private EditText lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.getSupportActionBar().setTitle("Register");
        super.onCreate(savedInstanceState);
        setContentView(activity_register);
        mAuth = FirebaseAuth.getInstance();
        this.signUpButton = (Button)this.findViewById(R.id.signUpButton);
        this.signUpemail = (EditText)this.findViewById(R.id.emailRegisterET);
        this.signUpPassword = (EditText)this.findViewById(R.id.passwordRegisterET);
        this.confirmPassword = (EditText)this.findViewById(R.id.confirmPasswordRegisterET);
        this.registerActivity = this;
    }

    public void signUpButtonTapped(View v)
    {

       if(signUpemail.getText().toString().equals("") || signUpPassword.getText().toString().equals(""))
       {
           Toast.makeText(registerActivity, "Enter Information", Toast.LENGTH_SHORT).show();
           /*Intent i = new Intent(registerActivity, MainActivity.class);
           registerActivity.startActivity(i);*/
       }
       else
       {
               if(signUpPassword.getText().toString().equals(confirmPassword.getText().toString()))
               {
                   mAuth.createUserWithEmailAndPassword(signUpemail.getText().toString(), signUpPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                   {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task)
                       {
                               if(task.isSuccessful())
                               {
                                   //Log.d(TAG, "createUserWithEmail:success");
                                   Core.currentUser = mAuth.getCurrentUser();
                                   //updateUI(null);



                               }
                               else
                               {
                                   //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                   Toast.makeText(registerActivity, "Sign Up Failed Make Sure All Fields are Filled.", Toast.LENGTH_SHORT).show();
                                   // updateUI(null);

                               }

                       }
                   });
                   Intent i = new Intent(registerActivity, MainActivity.class);
                   registerActivity.startActivity(i);
               }
               else
               {
                   Toast.makeText(registerActivity, "Passwords don't match!", Toast.LENGTH_SHORT).show();
               }

       }


    }
}
