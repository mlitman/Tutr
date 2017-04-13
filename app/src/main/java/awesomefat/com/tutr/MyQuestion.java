package awesomefat.com.tutr;

/**
 * Created by mumbl1 on 4/8/17.
 */

public class MyQuestion
{
    private String title;
    private String question;
    private String course;
    private String key;

    public MyQuestion(){}
    public MyQuestion(String title, String question, String course)
    {
        this.title = title;
        this.question = question;
        this.course = course;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getTitle()
    {
        return title;
    }

    public String getQuestion()
    {
        return question;
    }

    public String toString()
    {
        return this.course + " -> " + this.title;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
