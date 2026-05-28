public class Question {
    String text;
    String[] options;
    String correctAns;
    String userAns;

    public Question(String text, String[] options, String correctAns) {
        this.text = text;
        this.options = options;
        this.correctAns = correctAns;
        this.userAns = "";
    }
}
