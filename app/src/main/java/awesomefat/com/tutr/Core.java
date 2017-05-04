package awesomefat.com.tutr;

import java.util.ArrayList;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by mumbl1 on 4/8/17.
 */

public class Core
{
    static Question selectedQuestion = null;
    static MyQuestion selectedMyQuestion = null;
    static ArrayList<Question> theQuestions = new ArrayList<Question>();
    static ArrayList<Answer> theAnswers = new ArrayList<Answer>();
    static ArrayList<MyQuestion> theMyQuestions = new ArrayList<MyQuestion>();
    static FirebaseUser currentUser;
}
