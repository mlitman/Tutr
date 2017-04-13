package awesomefat.com.tutr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class viewProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
    }
    public void viewButtonPressed (View v)
    {
        Intent i = new Intent(this, MyQuestions.class);
        this.startActivity(i);
    }

    public void editProfileButtonPressed (View v)
    {
        /*Intent i = new Intent(this, MyQuestions.class);
        this.startActivity(i);*/
    }

    public void mainMenuButtonPressed (View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
    }

}
