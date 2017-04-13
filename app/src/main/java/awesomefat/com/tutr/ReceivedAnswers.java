package awesomefat.com.tutr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReceivedAnswers extends AppCompatActivity
{
    private TextView titleTV;
    private TextView questionTV;
    private ListView theAnswerList;
    private EditText bodyAnswerET;
    private answerAQuestion aList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Core.theAnswers.clear();
        setContentView(R.layout.activity_received_answers);
        this.titleTV = (TextView) findViewById(R.id.titleTV);
        this.questionTV = (TextView)findViewById(R.id.questionTV);
        this.bodyAnswerET = (EditText)findViewById(R.id.bodyAnswerET);

        if(Core.selectedQuestion != null)
        {
            this.titleTV.setText(Core.selectedMyQuestion.getTitle());
            this.questionTV.setText(Core.selectedMyQuestion.getQuestion());

        }


        this.theAnswerList = (ListView)this.findViewById(R.id.answerList);


        final ArrayAdapter<Answer> adapter = new ArrayAdapter<Answer>(this, android.R.layout.simple_list_item_1,Core.theAnswers);
        this.theAnswerList.setAdapter(adapter);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("answers").child(Core.selectedMyQuestion.getCourse()).child(Core.selectedMyQuestion.getKey());
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName)
            {
                //System.out.println("*******************" + dataSnapshot);
                Answer temp = new Answer(dataSnapshot.child("answer").getValue().toString());
                temp.setKey(dataSnapshot.getKey().toString());
                Core.theAnswers.add(temp);
                /*
                for(DataSnapshot aQuestion : dataSnapshot.getChildren())
                {
                    for(DataSnapshot aAnswer: aQuestion.getChildren())
                    {
                        System.out.println("*******************" + aQuestion);
                        //Answer temp = new Answer(aAnswer.child("answer").getValue().toString());
                        //temp.setKey(aAnswer.getKey().toString());
                        //Core.theAnswers.add(temp);
                    }
                }
                */
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase.addChildEventListener(childEventListener);
    }

    public void submitAnswerButtonPressed (View v)
    {
        Answer answerToSave = new Answer(bodyAnswerET.getText().toString());
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("answers").child(Core.selectedMyQuestion.getCourse()).child(Core.selectedMyQuestion.getKey()).push();
        mDatabase.setValue(answerToSave);
        Core.theAnswers.add(answerToSave);
        Intent i = new Intent(this, answerAQuestion.class);
        this.startActivity(i);
    }
    public void mainMenuButtonPressed (View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
    }

}
