package awesomefat.com.tutr;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuestionList extends AppCompatActivity
{
    private ListView theQuestionList;
    private QuestionList qList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Core.theQuestions.clear();
        this.qList = this;
        setContentView(R.layout.activity_question_list);

        this.theQuestionList = (ListView)this.findViewById(R.id.questionList);
        this.theQuestionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(qList, answerAQuestion.class);
                Core.selectedQuestion = Core.theQuestions.get(position);
                qList.startActivity(i);
            }
        });

        final ArrayAdapter<Question> adapter = new ArrayAdapter<Question>(this, android.R.layout.simple_list_item_1,Core.theQuestions);
        this.theQuestionList.setAdapter(adapter);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("questions");
        ValueEventListener questionListener = new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI


                for(DataSnapshot aClass : dataSnapshot.getChildren())
                {
                    for(DataSnapshot aQuestion: aClass.getChildren())
                    {
                        //System.out.println("*******************" + aQuestion.getKey());
                        Question temp = new Question(aQuestion.child("title").getValue().toString(), aQuestion.child("question").getValue().toString(), aQuestion.child("course").getValue().toString());
                        temp.setKey(aQuestion.getKey().toString());
                        Core.theQuestions.add(temp);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase.addValueEventListener(questionListener);
    }
    public void mainMenuButtonPressed (View v)
    {
        Intent i = new Intent (this, MainActivity.class);
        this.startActivity(i);
    }
}
