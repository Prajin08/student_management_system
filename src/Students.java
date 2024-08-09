import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class Students {
    private Connection con;
    private Scanner scanf;

    public Students(Connection con, Scanner scanf){
        this.con = con;
        this.scanf = scanf;
    }

    public void addStudent(){
        System.out.println("Enter Student name: ");
        String name = scanf.next();
        System.out.println("Enter id: ");
        int id = scanf.nextInt();
        System.out.println("Enter department: ");
        String dept = scanf.next();
        System.out.println("Enter e-mail(eg: xxxx@gmail.com): ");
        String email = scanf.next();
        System.out.println("Enter passing year: ");
        int year = scanf.nextInt();
        System.out.println("Enter Hosteller/Dayscholar: ");
        String resi = scanf.next();

        try {
            String query = "INSERT INTO student(id, name, dept, year, resi, email) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, dept);
            preparedStatement.setInt(4, year);
            preparedStatement.setString(5, resi);
            preparedStatement.setString(6, email);
            int rows = preparedStatement.executeUpdate();

            if(rows>0){
                System.out.println("Student added successfully😎");
            }
            else{
                System.out.println("Failed to add Student😣");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void viewStudents(){
        String query = "select * from student";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("| ID | Name        | Dept      | Year_passing| Resident  | email               |");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String dept = resultSet.getString("dept");
                int year = resultSet.getInt("year");
                String resi = resultSet.getString("resi");
                String email = resultSet.getString("email");
                System.out.printf("|%-4s|%-13s|%-11s|%-13s|%-12s|%-21s|", id, name, dept, year, resi, email);
                System.out.println();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void getStudentById(int id){
        String query = "SELECT * FROM student WHERE id = ?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("| Name        | Dept      | Year_passing| Resident  | email               |");
            if(resultSet.next()){
                String name = resultSet.getString("name");
                String dept = resultSet.getString("dept");
                int year = resultSet.getInt("year");
                String resi = resultSet.getString("resi");
                String email = resultSet.getString("email");
                System.out.printf("|%-13s|%-11s|%-13d|%-12s|%-10s|", name, dept, year, resi, email);
            }
        }
        catch(SQLException | MissingFormatArgumentException e){
            e.printStackTrace();
        }
    }

    public void updateResi(int ide, String resii){
        String query  = "update student set resi =?  where id = ?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, resii);
            preparedStatement.setInt(2, ide);
            preparedStatement.executeUpdate();
            System.out.println("Resi updated!");

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteStudent(int ide){
        String qu = "DELETE FROM student WHERE id = ?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(qu);
            preparedStatement.setInt(1, ide);
            preparedStatement.executeUpdate();
            System.out.println("Student deleted successfully!!!");

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
