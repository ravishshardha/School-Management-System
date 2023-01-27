import java.util.*;
import java.util.Scanner;

class SchoolManagement {
    private static HashMap<String, StudentList> StudentsHashMap;
    public static void main(String[] args) 
    {
       StudentsHashMap = new HashMap<String, StudentList>();
       AddKeysToHashMap();
       boolean quit = false;
       System.out.println();
       System.out.println("Welcome to Student Administration System");
       System.out.println();
       System.out.println();

       while(!quit)
       {
            System.out.println();
            System.out.println("These are the menu items, What would you like to do?");
            System.out.println("1 -> Add a student");
            System.out.println("2 -> Update a student");
            System.out.println("3 -> Delete a student");
            System.out.println("4 -> Print report of students");
            System.out.println("5 -> Search a student");
            System.out.println("6 -> Quit the program");
            System.out.println();

            Boolean validInput = false;
            //get input
            Scanner input = new Scanner(System.in);
            int selectedMenu = 0;
            //check for integer
            do {
                // validate that the input is an integer
                if (input.hasNextInt() == true) 
                {
                    selectedMenu = input.nextInt();
                    if(selectedMenu == 1 || selectedMenu == 2 || selectedMenu == 3 || selectedMenu == 4 || selectedMenu == 5 || selectedMenu == 6)
                    {
                        validInput = true;
                    }
                    else
                    {
                     
                        System.out.println("Please select only from menu items shown above:");
                        System.out.println();
                        selectedMenu = 0;
                    }
                } 
                
                else 
                {   
                   
                    System.out.println("Please enter a number only!");
                    System.out.println();
                    selectedMenu = 0;
                    input.next();
                }
    
            } while (!validInput);

            switch(selectedMenu)
            {
                case 1:
                    AddStudent();
                    break;

                case 2:
                    UpdateStudent();
                    break;
                
                case 3:
                    DeleteStudent();
                    break;  
                
                case 4:
                    PrintReport();
                    break;
                
                case 5:
                    SearchStudent();
                    break;

                case 6:
                    quit = true;
                    QuitProgram();
                    break;

                default:
                    break;
            }
        }
       
    }

    private static void AddKeysToHashMap()
    {
        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; // new array    
        //add 26 alphabet letter in hashmap
        for(int i=0; i<alphabet.length;i++)
        {
            StudentList studentList = new StudentList();
            //add the key and value to hashmap
            StudentsHashMap.put(alphabet[i], studentList);
        }
    }

    private static void QuitProgram()
    {
        System.out.println();
        System.out.println("Thank you for using School Administration. Have a nice day!");
        System.out.println("Thank you!");
    }

    private static void AddStudent()
    {
        //get input
        Scanner input = new Scanner(System.in);
        String firstName = null;
        String lastName = null;

        //get the first name
        do
        {
            System.out.println();
            System.out.println("Please enter student's First Name: ");
            firstName = input.nextLine();
        }while(!IsValidName(firstName));

        //get the last name
        do
        {
            System.out.println("Please enter student's Last Name: ");
            lastName = input.nextLine();
        }while(!IsValidName(lastName));
        
        //capitalize the first character
        firstName = CapitalizeString(firstName);
        lastName = CapitalizeString(lastName);

        //get the gpa
        String gpaString = null;
        do
        {
            System.out.println("Please enter student's Gpa: ");
            gpaString = input.nextLine();
        }while(!IsValidGpa(gpaString));
        
        //cast gpa to double
        Double gpa = 0.0;
        gpa = Double.parseDouble(gpaString);
        
        String gradeString = null;
        do
        {
            
            System.out.println("Please enter student's Grade: ");
            gradeString = input.nextLine();
        }while(!IsValidGrade(gradeString));

        //cast grade to int
        int grade = 9;
        grade = Integer.parseInt(gradeString);

        Student student = new Student(firstName,lastName,grade,gpa);
        StudentNode studentNode = new StudentNode(student);

        StudentList studentListByLastName = StudentsHashMap.get(lastName.substring(0, 1));
        studentListByLastName.AddStudent(studentNode);
        
        System.out.println();
        System.out.println("Student " + firstName + " " + lastName + " was added successfully!");
        System.out.println();

    }

    private static void DeleteStudent()
    {
        //get input
        Scanner input = new Scanner(System.in);
        String firstName = null;
        String lastName = null;

        //get the first name
        do
        {
            System.out.println();
            System.out.println("Please enter student's First Name to delete: ");
            firstName = input.nextLine();
        }while(!IsValidName(firstName));

        //get the last name
        do
        {
            
            System.out.println("Please enter student's Last Name to delete: ");
            lastName = input.nextLine();
        }while(!IsValidName(lastName));
        
        //capitalize the first character
        firstName = CapitalizeString(firstName);
        lastName = CapitalizeString(lastName);

        StudentList studentListByLastName = StudentsHashMap.get(lastName.substring(0, 1));
        Boolean result = studentListByLastName.DeleteStudent(firstName, lastName);
        if(result)
        {
            System.out.println();
            System.out.println("Student deleted successfully");
            System.out.println();
        }
        else
        {
            System.out.println();
            System.out.println("Student did not found so can't be deleted.");
            System.out.println();
        }
    }

    private static void UpdateStudent()
    {
        //get input
        Scanner input = new Scanner(System.in);
        String firstName = null;
        String lastName = null;

        //get the first name
        do
        {
            System.out.println();
            System.out.println("Please enter student's First Name to update: ");
            firstName = input.nextLine();
        }while(!IsValidName(firstName));

        //get the last name
        do
        {
            System.out.println("Please enter student's Last Name to update: ");
            lastName = input.nextLine();
        }while(!IsValidName(lastName));
        
        //capitalize the first character
        firstName = CapitalizeString(firstName);
        lastName = CapitalizeString(lastName);

        StudentList studentListByLastName = StudentsHashMap.get(lastName.substring(0, 1));
        if(studentListByLastName.SearchStudent(firstName, lastName))
        {
            System.out.println();
            System.out.println("What information would you like to update?");
            System.out.println("1 -> Student's Name");
            System.out.println("2 -> Student's GPA");
            System.out.println("3 -> Student's Grade");
            System.out.println();

             //check for integer
            int selectedMenu = 0;
            Boolean validInput = false;
            do {
                // validate that the input is an integer
                if (input.hasNextInt() == true) 
                {
                    selectedMenu = input.nextInt();
                    if(selectedMenu == 1 || selectedMenu == 2 || selectedMenu == 3)
                    {
                        validInput = true;
                    }
                    else
                    {
                        System.out.println();
                        System.out.println("Please select only from menu items shown above:");
                        selectedMenu = 0;
                    }
                } 
                
                else 
                {
                    System.out.println();
                    System.out.println("Please enter a number only!");
                    selectedMenu = 0;
                    input.next();
                }
    
            } while (!validInput);

            input.nextLine();
            switch(selectedMenu)
            {
                case 1:
                    String newFirstName = null;
                    String newLastName = null;

                    //get the first name
                    do
                    {
                        System.out.println();
                        System.out.println("Please enter student's new first name:");
                        newFirstName = input.nextLine();
                    }while(!IsValidName(newFirstName));

                    //get the last name
                    do
                    {
                        System.out.println("Please enter student's new last name:");
                        newLastName = input.nextLine();
                    }while(!IsValidName(newLastName));
                
                    //capitalize the first character
                    newFirstName = CapitalizeString(newFirstName);
                    newLastName = CapitalizeString(newLastName);

                    //last name first letter changed
                    if(!(newLastName.substring(0,1).equals(lastName.substring(0,1))))
                    {
                        SwapStudents(studentListByLastName, firstName, lastName, newFirstName, newLastName);
                    }
                    
                    else
                    {
                        studentListByLastName.UpdateStudent(firstName, lastName, "Name", newFirstName + "," + newLastName);
                    }
                    
                    break;
                
                case 2:
                    //get the gpa
                    String gpaString = null;
                    do
                    {
                        System.out.println();
                        System.out.println("Please enter student's new Gpa: ");
                        gpaString = input.nextLine();
                    }while(!IsValidGpa(gpaString));

                    studentListByLastName.UpdateStudent(firstName, lastName, "Gpa", gpaString);
                    break;
                
                case 3:
                    //get the grade
                    String gradeString = null;
                    do
                    {
                        System.out.println();
                        System.out.println("Please enter student's new grade: ");
                        gradeString = input.nextLine();
                    }while(!IsValidGrade(gradeString));

                    studentListByLastName.UpdateStudent(firstName, lastName, "Grade", gradeString);
                    break;
            }
        }

        else
        {
            System.out.println();
            System.out.println("Student not found!");
            System.out.println();

        }
    }

    private static void PrintReport()
    {
        System.out.println();
        System.out.println("How would you like to print the report by?");
        System.out.println("1-> Last Name");
        System.out.println("2-> Grade");
        System.out.println("3-> GPA");
        System.out.println("4-> All students");
        System.out.println();

        //get input
        Scanner input = new Scanner(System.in);
        //integer selection
        int selectedMenu = 0;
        Boolean validInput = false;
        
        do {
            // validate that the input is an integer
            if (input.hasNextInt() == true) 
            {
                selectedMenu = input.nextInt();
                if(selectedMenu == 1 || selectedMenu == 2 || selectedMenu == 3 || selectedMenu == 4)
                {
                    validInput = true;
                }
                else
                {
                    System.out.println();
                    System.out.println("Please select only from menu items shown above:");
                    System.out.println();
                    selectedMenu = 0;
                }
            } 
            
            else 
            {
                System.out.println();
                System.out.println("Please enter a number only!");
                selectedMenu = 0;
                input.next();
            }

        } while (!validInput);
        input.nextLine();

        switch(selectedMenu)
        {
            case 1:
                String lastNameLetter = null;
                Boolean isValid = false;

                while(!isValid)
                {
                    System.out.println("Please enter the initials of the last name:");
                    System.out.println("For example: S for Samson!");
                    lastNameLetter = input.nextLine();
                    if(lastNameLetter.length()!=1)
                    {
                        System.out.println();
                        System.out.println("Please enter a letter only!");
                        System.out.println();
                        isValid = false;
                        continue;
                    }

                    isValid = IsValidName(lastNameLetter); 
                }

                StudentList studentListByLastName = StudentsHashMap.get(lastNameLetter.toUpperCase());                
                //list to save filter
                ArrayList<Student> studentsList = studentListByLastName.PrintList("Name", lastNameLetter);
                if(studentsList.size() == 0){
                    System.out.println();
                    System.out.println("No students exist with the selected last name.");
                    System.out.println();
                }
                else{
                    System.out.println();
                    PrintStudents(studentsList);
                    System.out.println();
                }
                break;

            case 2:
                //get the grade
                String gradeString = null;
                do
                {
                    System.out.println("Please enter the grade:");
                    gradeString = input.nextLine();
                }while(!IsValidGrade(gradeString));

                ArrayList<ArrayList<Student>> studentsFilteredByGrade = new ArrayList<ArrayList<Student>>();
                //add all the students list
                for (String key : StudentsHashMap.keySet() ) {
                    studentsFilteredByGrade.add(StudentsHashMap.get(key).PrintList("Grade", gradeString));
                }

                if(IsListEmpty(studentsFilteredByGrade)){
                    System.out.println();
                    System.out.println("No student exist with the selected grade.");
                    System.out.println();
                }
                else{
                    //for each list 
                    for (ArrayList<Student> studentArrayList : studentsFilteredByGrade) {
                        PrintStudents(studentArrayList);
                    }
                   
                }

                break;

            case 3:
                //get the gpa
                String gpaString = null;
                do
                {
                    System.out.println("Please enter the Gpa: ");
                    gpaString = input.nextLine();
                }while(!IsValidGpa(gpaString));

                ArrayList<ArrayList<Student>> studentsFilteredByGpa = new ArrayList<ArrayList<Student>>();
                //add all the students list
                for (String key : StudentsHashMap.keySet() ) {
                    studentsFilteredByGpa.add(StudentsHashMap.get(key).PrintList("Gpa", gpaString));
                }

                if(IsListEmpty(studentsFilteredByGpa)){
                    System.out.println();
                    System.out.println("No students exist with the selected GPA.");
                    System.out.println();
                }
                else{
                    //for each list 
                    for (ArrayList<Student> studentArrayList : studentsFilteredByGpa) {
                        PrintStudents(studentArrayList);
                    }
                }
                break;
            
            case 4:
                ArrayList<ArrayList<Student>> allStudents = new ArrayList<ArrayList<Student>>();
                //add all the students list
                for (String key : StudentsHashMap.keySet() ) {
                    allStudents.add(StudentsHashMap.get(key).PrintList("Name", ""));
                }

                if(IsListEmpty(allStudents)){
                    System.out.println();
                    System.out.println("No students exist in the School Management System.");
                    System.out.println();
                }
                else{
                    //for each list 
                    for (ArrayList<Student> studentArrayList : allStudents) {
                        PrintStudents(studentArrayList);
                    }
                }
                break;
        }

    }

    private static void SearchStudent()
    {
        //get input
        Scanner input = new Scanner(System.in);
        String firstName = null;
        String lastName = null;

        //get the first name
        do
        {
            System.out.println();
            System.out.println("Please enter student's First Name to search: ");
            firstName = input.nextLine();
        }while(!IsValidName(firstName));

        //get the last name
        do
        {
            System.out.println("Please enter student's Last Name to search: ");
            lastName = input.nextLine();
        }while(!IsValidName(lastName));
        
        //capitalize the first character
        firstName = CapitalizeString(firstName);
        lastName = CapitalizeString(lastName);

        StudentList studentListByLastName = StudentsHashMap.get(lastName.substring(0, 1));
        if(studentListByLastName.SearchStudent(firstName, lastName))
        {
            Student student = studentListByLastName.ReturnStudent(firstName, lastName);
            System.out.println();
            System.out.println(student.getFirstName() + " " + student.getLastName() + " " + student.getGpa() + " " + student.getGrade());   
            System.out.println();
   
        }
        else
        {
            System.out.println();
            System.out.println("Student not found!");
            System.out.println();
        }

    }

    private static String CapitalizeString(String stringToCapitalize)
    {
        return stringToCapitalize.substring(0, 1).toUpperCase() + stringToCapitalize.substring(1).toLowerCase();
    }

    private static void SwapStudents(StudentList studentListByLastName, String firstName, String lastName, String newFirstName, String newLastName)
    {
        //get current student to swap
        Student currentStudent = studentListByLastName.ReturnStudent(firstName, lastName);
        if(currentStudent!=null)
        {
            //create updated student with new first and last name
            Student updatedStudent = new Student(newFirstName, newLastName, currentStudent.getGrade(), currentStudent.getGpa());
            //delete old student
            Boolean result = studentListByLastName.DeleteStudent(firstName, lastName);
            if(result)
            {
                //create student node for updated student
                StudentNode studentNode = new StudentNode(updatedStudent);
                //get the new list with new last name
                StudentList studentListByUpdatedLastName = StudentsHashMap.get(newLastName.substring(0, 1));
                //add the student with updated information
                studentListByUpdatedLastName.AddStudent(studentNode);
            }
        }
    }

    private static Boolean IsValidName(String name)
    {
        if(name == null || name.isEmpty())
        {
            System.out.println("Name can't be empty!");
            System.out.println();
            return false;
        }
        //starts with a number
        if(Character.isDigit(name.charAt(0)))
        {
            System.out.println("Name can't begin with a number!");
            System.out.println();
            return false;
        }

        return true;
    }

    private static Boolean IsValidGpa(String gpa)
    {
        if(gpa == null || gpa.isEmpty())
        {
            System.out.println("GPA can't be blank!");
            System.out.println();
            return false;
        }

        if(OnlyNumbers(gpa))
        {
            Double gpaDouble = Double.parseDouble(gpa);
            if(gpaDouble < 0.0 || gpaDouble > 4.0)
            {
                System.out.println("Please enter a valid GPA in range from 0 to 4.0!");
                System.out.println();
                return false;
            }
        }
        
        else
        {
            System.out.println("Please enter a valid GPA in range from 0 to 4.0!");
            System.out.println();
            return false;
        }

        return true;
    }

    private static Boolean IsValidGrade(String grade)
    {
        if(grade == null || grade.isEmpty())
        {
            System.out.println("Grade can't be blank!");
            System.out.println();
            return false;
        }

        if(OnlyNumbers(grade))
        {
            //number contains a . then it is double
            if(grade.indexOf(".")>-1)
            {
                System.out.println("Please enter whole numbers only!");
                System.out.println();
                return false;
            }

            int gradeInt = Integer.parseInt(grade);
            if(!(gradeInt==9 || gradeInt==10 || gradeInt==11 || gradeInt==12))
            {
                System.out.println("Please enter a valid grade in range from 9 to 12!");
                System.out.println();
                return false;
            }
        }

        else
        {
            System.out.println("Please enter a valid grade in range from 9 to 12!");
            System.out.println();
            return false;
        }

        return true;
    }

    private static Boolean OnlyNumbers(String stringToCheck)
    {
        char[] chars = stringToCheck.toCharArray();
        for(char c : chars){
            if(c != '.' && !Character.isDigit(c)){
               return false;
            }
        }

        return true;
    }

    private static void PrintStudents(ArrayList<Student> studentsList)
    {
        for (Student student : studentsList) {
            System.out.println(student.getFirstName() + " " + student.getLastName() + " " + student.getGpa() + " " + student.getGrade());
        }
    }

    private static Boolean IsListEmpty(ArrayList<ArrayList<Student>> studentsList)
    {
        for (ArrayList<Student> arrayList : studentsList) {
            if(arrayList.size()>0)
            {
                return false;
            }
        }
        
        return true;
    }
}
