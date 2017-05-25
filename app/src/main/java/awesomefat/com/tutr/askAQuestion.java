package awesomefat.com.tutr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class askAQuestion extends AppCompatActivity
{
    private QuestionList qList;
    private TextView currCourseET;
    private EditText titleAskET;
    private EditText bodyAskET;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_aquestion);
        this.currCourseET = (EditText)this.findViewById(R.id.currCourseET);
        this.titleAskET = (EditText)this.findViewById(R.id.titleAskET);
        this.bodyAskET = (EditText)this.findViewById(R.id.bodyAskET);
    }

    public void submitQuestionButtonPressed(View v)
    {
        Question questionToSave = new Question(titleAskET.getText().toString(), bodyAskET.getText().toString(), currCourseET.getText().toString());
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("questions").child(currCourseET.getText().toString()).push();
        mDatabase.setValue(questionToSave);
        Core.theQuestions.add(questionToSave);
        Intent i = new Intent(this, QuestionList.class);
        this.startActivity(i);
    }
}
