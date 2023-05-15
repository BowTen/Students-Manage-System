package bean;

public class Student{
    private String id = null;
    private String passWord = null;

    private String name = null;
    private String academy = null;
    private String major = null;
    private String _class = null;

    public Student() {
    }

    public Student(String id, String passWord) {
        this.id = id;
        this.passWord = passWord;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != Student.class)
            return false;
        Student s = (Student)obj;
        return id.equals(s.id)&&passWord.equals(s.passWord);
    }
}
