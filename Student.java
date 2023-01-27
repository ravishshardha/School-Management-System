public class Student
{
    public Student(String firstName, String lastName, int grade, double gpa)
    {
        Grade = grade;
        FirstName = firstName;
        LastName = lastName;
        Gpa = gpa;
    }

    //age
    private int Grade;
    public int getGrade() {
        return Grade;
    }
    public void setGrade(int grade) {
        this.Grade = grade;
    }

    //first name
    private String FirstName;
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    //last name
    private String LastName;
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        this.LastName = lastName;
    }
    
    //gpa
    private double Gpa;
    public double getGpa(){return Gpa;}
    public void setGpa(double gpa) {
        this.Gpa = gpa;
    }
}