package awesomefat.com.tutr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyQuestions extends AppCompatActivity
{
    private ListView theQuestionList;
    private MyQuestions qList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_questions);
        this.qList = this;
        Core.theMyQuestions.clear();
        this.theQuestionList = (ListView)this.findViewById(R.id.questionList);
        this.theQuestionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(qList, ReceivedAnswers.class);
                Core.selectedMyQuestion = Core.theMyQuestions.get(position);
                qList.startActivity(i);
            }
        });

        final ArrayAdapter<MyQuestion> adapter = new ArrayAdapter<MyQuestion>(this, android.R.layout.simple_list_item_1,Core.theMyQuestions);
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
                        MyQuestion temp = new MyQuestion(aQuestion.child("title").getValue().toString(), aQuestion.child("question").getValue().toString(), aQuestion.child("course").getValue().toString());
                        temp.setKey(aQuestion.getKey().toString());
                        Core.theMyQuestions.add(temp);
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
