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
    private ListView courseList;
    private String[] courses = {"CSC150","CSC175","CSC200","CSC250","CSC300","CSC150","CSC175","CSC200","CSC250","CSC300","CSC150","CSC175","CSC200","CSC250","CSC300"};
    private QuestionList qList;
    private TextView currCourseTV;
    private EditText titleAskET;
    private EditText bodyAskET;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_aquestion);
        this.currCourseTV = (TextView)this.findViewById(R.id.currCourseTV);
        this.titleAskET = (EditText)this.findViewById(R.id.titleAskET);
        this.bodyAskET = (EditText)this.findViewById(R.id.bodyAskET);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,this.courses);
        this.courseList = (ListView)this.findViewById(R.id.courseList);
        this.courseList.setAdapter(adapter);
        this.courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currCourseTV.setText(courses[position]);
            }
        });


    }

    public void submitQuestionButtonPressed(View v)
    {
        Question questionToSave = new Question(titleAskET.getText().toString(), bodyAskET.getText().toString(), currCourseTV.getText().toString());
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("questions").child(currCourseTV.getText().toString()).push();
        mDatabase.setValue(questionToSave);
        Core.theQuestions.add(questionToSave);
        Intent i = new Intent(this, QuestionList.class);
        this.startActivity(i);
    }
}
