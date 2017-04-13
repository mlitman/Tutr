package awesomefat.com.tutr;

/**
 * Created by mumbl1 on 4/8/17.
 */

public class Answer
{
    private String answer;
    private String key;

    public Answer (String answer)
    {
        this.answer = answer;
    }

    public String getAnswer() {

        return answer;
    }
    public String toString()
    {
        return "User" + " -> " + this.answer;
    }
    public void setKey(String key)
    {
        this.key = key;
    }

    public String getKey()
    {
        return key;
    }
}
