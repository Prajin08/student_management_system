import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentsManage {

    private static final String url = "jdbc:mysql://localhost:3306/students";
    private static final String username = "root";
    private static final String password = "Prajin@2004";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        Scanner scanf = new Scanner(System.in);
        try{
            Connection con = DriverManager.getConnection(url, username, password);
            Students s = new Students(con, scanf);
            while(true){
                System.out.println("Student Management system: ");
                System.out.println("1. Add Student: ");
                System.out.println("2. View Students: ");
                System.out.println("3. View student by id: ");
                System.out.println("4. Update: ");
                System.out.println("5. Delete ");
                System.out.println("6. Exit:");
                System.out.println("Enter your choice: ");
                int choice = scanf.nextInt();

                switch (choice){
                    case 1:
                        s.addStudent();
                        System.out.println();
                        break;
                    case 2:
                        s.viewStudents();
                        System.out.println();
                        break;
                    case 3:
                        System.out.println("Enter id: ");
                        int id = scanf.nextInt();
                        s.getStudentById(id);
                        System.out.println();
                        break;
                    case 4:
                        System.out.println("Enter id: ");
                        int idd = scanf.nextInt();
                        System.out.println("Enter resi: ");
                        String resi = scanf.next();
                        s.updateResi(idd, resi);
                        System.out.println();
                        break;
                    case 5:
                        System.out.println("Enter id: ");
                        int idi = scanf.nextInt();
                        s.deleteStudent(idi);
                        break;
                    case 6:
                        System.out.println("Thank You for Using Student Management System!!!");
                        return;
                }

            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }


    }
}
