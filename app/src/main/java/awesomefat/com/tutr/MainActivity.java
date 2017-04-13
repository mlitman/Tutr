package awesomefat.com.tutr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void askButton(View v)
    {
        Intent i = new Intent(this, askAQuestion.class);
        this.startActivity(i);
    }

    public void answerButton(View v)
    {
        Intent i = new Intent(this, QuestionList.class);
        this.startActivity(i);
    }
    public void viewProfileButton (View v)
    {
        Intent i = new Intent (this, viewProfile.class);
        this.startActivity(i);
    }
}





