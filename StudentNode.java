public class StudentNode {
    private Student Student;
    private StudentNode Next;

    public StudentNode GetNext()
    {
        return Next;
    }

    public Student GetStudent()
    {
        return Student;
    }

    public void SetNext(StudentNode nextStudent)
    {
        Next = nextStudent;
    }

    public void SetStudent(Student student)
    {
        Student = student;
    }

    public StudentNode(Student student)
    {
        Student = student;
        Next = null;
    }
}