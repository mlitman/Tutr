package awesomefat.com.tutr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegisterOrSignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_or_sign_in);
    }

    public void registerButtonPressed (View v)
    {
        System.out.println("here*****");

        Intent i = new Intent(this, Register.class);
        this.startActivity(i);
    }

    public void signInButtonPressed (View v)
    {
        Intent i = new Intent(this, SignIn.class);
        this.startActivity(i);
    }
}
