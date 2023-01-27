import java.util.ArrayList;

public class StudentList {
    private StudentNode head;

    public StudentNode GetHead()
    {
        return head;
    }

    public StudentList()
    {
        head = null;
    }

    //add student to student list
    public void AddStudent(StudentNode studentNode)
    {
        // If the Linked List is empty,
        // then make the new node as head
        if (head == null) {
            head = studentNode;
        }
        
        else {
            // Else traverse till the last node
            // and insert the new_node there
            StudentNode lastNode = head;
            while (lastNode.GetNext() != null) {
                lastNode = lastNode.GetNext();
            }
   
            // Insert the new_node at last node
            lastNode.SetNext(studentNode);
        }
    }

    //delete a student
    public Boolean DeleteStudent(String firstName, String lastName)
    {
        if(SearchStudent(firstName, lastName))
        {
            StudentNode prev = null;
            StudentNode current = head;
            while (current != null) 
            {
                Student student = current.GetStudent();
                if(student.getLastName().equals(lastName) && student.getFirstName().equals(firstName))
                {
                    //delete head node
                    if(current == head)
                    {
                        head = head.GetNext();
                        current = head;
                    }

                    else
                    {
                        prev.SetNext(current.GetNext());
                        current = current.GetNext();
                    }
                }

                else {
                    prev = current;
                    current = current.GetNext();
                }
            } 
            return true;
        }

        return false;
    }
    
    //search a student
    public boolean SearchStudent(String firstName, String lastName)
    {
        StudentNode currNode = head;
        // Traverse through the LinkedList
        while (currNode != null) 
        {
            //student 
            Student student = currNode.GetStudent();
            if(student.getLastName().equals(lastName) && student.getFirstName().equals(firstName))
            {
                return true;
            }
            // Go to next node
            currNode = currNode.GetNext();
        }
        //not found
        return false;
    }

    //update a student
    public void UpdateStudent(String firstName, String lastName, String property, String value)
    {
        StudentNode currNode = head;
        // Traverse through the LinkedList
        while (currNode != null) 
        {
            //student 
            Student student = currNode.GetStudent();   
            if(student.getLastName().equals(lastName) && student.getFirstName().equals(firstName))
            {
                //Name
                if(property.equals("Name"))
                {   
                    currNode.GetStudent().setFirstName(value.substring(0,value.indexOf(",")));
                    currNode.GetStudent().setLastName(value.substring(value.indexOf(",") + 1));
                }
                //grade
                else if(property.equals("Grade"))
                {
                    currNode.GetStudent().setGrade(Integer.parseInt(value));
                }
                //gpa
                else if(property.equals("Gpa"))
                {
                    currNode.GetStudent().setGpa(Double.parseDouble(value));
                }
                System.out.println("Student updated successfully.");
                return;
            }
            // Go to next node
            currNode = currNode.GetNext();
        }
    }

    public ArrayList<Student> PrintList(String property, String value)
    {
        ArrayList<Student> filteredList = new ArrayList<Student>();
        StudentNode currNode = head;
        // Traverse through the LinkedList
        while (currNode != null) 
        {
            //student 
            Student student = currNode.GetStudent();
            if(property.equals("Name"))
            {
                filteredList.add(student);
            }

            else if(property.equals("Grade"))
            {
                if(Integer.parseInt(value) == student.getGrade())
                {
                    filteredList.add(student);
                }
            }

            else if(property.equals("Gpa"))
            {
                if(Double.parseDouble(value) == student.getGpa())
                {
                    filteredList.add(student);
                }
            }
            // Go to next node
            currNode = currNode.GetNext();
        }

        return filteredList;
    }

    public Student ReturnStudent(String firstName,String lastName)
    {
        StudentNode currNode = head;
        // Traverse through the LinkedList
        while (currNode != null) 
        {
            //student 
            Student student = currNode.GetStudent();
            if(student.getLastName().equals(lastName) && student.getFirstName().equals(firstName))
            {
                return student;
            }
            // Go to next node
            currNode = currNode.GetNext();
        }

        //not found
        return null;
    }
}
