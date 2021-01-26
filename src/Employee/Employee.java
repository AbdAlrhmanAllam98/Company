package Employee;

//abstract ya3ni mynf3sh a3ml mno OBJECT 
import static Company.HR.allEmployees;
import Project.Task;
import static Company.HR.input;
import Project.Project;
import java.sql.*;
import java.util.ArrayList;

public abstract class Employee {

    protected String name;
    protected String nationalId;
    protected static double salary = 1500.0;
    protected char gender;
    protected int availableVacations;
    protected int availablePermissionHours;
    protected static double deductionPerDay = 85.5;
    protected static double deductionPerHour = 15.5;
    protected double actualSalary;
    protected static ArrayList<Project> worksOn;

    public Employee() {
        name = "";
        nationalId = "";
        gender = ' '; //lazm el char ya5od space
        availablePermissionHours = 20;
        availableVacations = 15;
        actualSalary = salary;
        worksOn = new ArrayList<>();
    }

    public Employee(String name, String nationalId, char gender) {
        this.name = name;
        this.nationalId = nationalId;
        this.gender = gender;
        this.availablePermissionHours = 20;
        availableVacations = 15;
        actualSalary = salary;
        worksOn = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public int getAvailableVacations() {
        return availableVacations;
    }

    public void setAvailableVacations(int availableVacations) {
        this.availableVacations = availableVacations;
    }

    public int getAvailablePermissionHours() {
        return availablePermissionHours;
    }

    public void setAvailablePermissionHours(int availablePermissionHours) {
        this.availablePermissionHours = availablePermissionHours;
    }

    public double getActualSalary() {
        return actualSalary;
    }

    public void setActualSalary(double actualSalary) {
        this.actualSalary = actualSalary;
    }

    public ArrayList<Project> getWorksOn() {
        return worksOn;
    }

    public void setWorksOn(ArrayList<Project> worksOn) {
        this.worksOn = worksOn;
    }

    void addProjectToEmp(Project p) {
        worksOn.add(p);
    }

    public void view() {
        System.out.println("name : " + name);
        System.out.println("national id : " + nationalId);
        System.out.println("salary : " + salary);
    }

    public void viewDetailed() {
        view();
        System.out.println("Available vacations : " + availableVacations);
        System.out.println("Available Hours : " + availablePermissionHours);
        System.out.println("Actual salary : " + actualSalary);
        System.out.println("Deduction per day: " + deductionPerDay);
        System.out.println("Deduction per hour: " + deductionPerHour);
    }

    public void takeVacation(int numOfDays) {
        availableVacations -= numOfDays;
        if (availableVacations < 0) {
            calculateActualSalary();
        }
    }

    public void takePermissionHours(int numOfHours) {
        availablePermissionHours -= numOfHours;
        if (availablePermissionHours < 0) {
            calculateActualSalary();
        }
    }

    public static void addEmployee() {
        System.out.println("Enter employee name : ");
        String name = input.nextLine();
        System.out.println("Enter employee ID : ");
        String id = input.nextLine();
        System.out.println("Enter employee Gender :");
        char gender = input.next().charAt(0);
        Employee emp = null;
        System.out.println("1-Developer\n2-Accountant");
        int option = input.nextInt();
        if (option == 1) {
            input.nextLine();
            System.out.println("what is the framework?");
            String frameWork = input.nextLine();
            emp = new Developer(name, id, gender, frameWork);
        } else if (option == 2) {
            input.nextLine();
            emp = new Accountant(name, id, gender);

        }
        allEmployees.add(emp);
        addEmployeeDB(emp);
    }

    private static void addEmployeeDB(Employee emp) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/company", "root", "");
            Statement stmt = conn.createStatement();
            String query = null;
            if (emp instanceof Developer) {
                query = "INSERT INTO `employee`(`id`, `name`, `nationalId`, `status`, `gender`, `actualSalary`, `availableVacations`, `availablePermissionHours`, `certifiedExcel`, `frameWork`) VALUES "
                        + "(null,'" + emp.getName() + "'," + emp.getNationalId() + ",'Developer','" + emp.getGender() + "'," + emp.getActualSalary() + "," + emp.getAvailableVacations() + "," + emp.getAvailablePermissionHours() + ",null,'" + ((Developer) emp).frameWork + "')";
            } else if (emp instanceof Accountant) {
                query = "INSERT INTO `employee`(`id`, `name`, `nationalId`, `status`, `gender`, `actualSalary`, `availableVacations`, `availablePermissionHours`, `certifiedExcel`, `frameWork`) VALUES "
                        + "(null,'" + emp.getName() + "'," + emp.getNationalId() + ",'Accountant','" + emp.getGender() + "'," + emp.getActualSalary() + "," + emp.getAvailableVacations() + "," + emp.getAvailablePermissionHours() + "," + ((Accountant) emp).certifiedExcel + ",null)";
            }
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Employee> retrieveEmployees() {
        allEmployees = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/company", "root", "");
            Statement stmt = conn.createStatement();
            String query = "SELECT * From employee";
            ResultSet res = stmt.executeQuery(query);
            Employee emp = null;
            while (res.next()) {
                if ("Developer".equals(res.getString("status"))) {
                    emp = new Developer(res.getString("name"), res.getLong("nationalId") + "", res.getString("gender").charAt(0), res.getString("frameWork"));
                } else if ("Accountant".equals(res.getString("status"))) {
                    emp = new Accountant(res.getString("name"), res.getLong("nationalId") + "", res.getString("gender").charAt(0));
                }
                emp.setActualSalary(res.getDouble("actualSalary"));
                emp.setAvailableVacations(res.getInt("availableVacations"));
                emp.setAvailablePermissionHours(res.getInt("availablePermissionHours"));

                allEmployees.add(emp);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return allEmployees;
    }

    public static Employee searchEmp_By_ID(String id) {
        for (int i = 0; i < allEmployees.size(); i++) {
            if (allEmployees.get(i).getNationalId().equals(id)) {
                return allEmployees.get(i);
            }
        }
        return null;
    }

    public static Employee searchEmp_By_Name(String name) {
        for (int i = 0; i < allEmployees.size(); i++) {
            if (allEmployees.get(i).getName().equals(name)) {
                return allEmployees.get(i);
            }
        }
        return null;
    }

    public double calculateActualSalary() {
        actualSalary = salary;
        if (availableVacations < 0) {
            actualSalary += (deductionPerDay * availableVacations);
        }
        if (availablePermissionHours < 0) {
            actualSalary += (deductionPerHour * availablePermissionHours);
        }
        return actualSalary;
    }

    public static void printPayRoll_To_AllEmployees(ArrayList<Employee> allEmps) {
        for (int i = 0; i < allEmps.size(); i++) {
            System.out.println(
                    allEmps.get(i).getName() + " : "
                    + allEmps.get(i).calculateActualSalary());
        }
    }

    public static double PayRoll_To_AllEmployees(ArrayList<Employee> allEmps) {
        double sum = 0;
        for (int i = 0; i < allEmps.size(); i++) {
            sum += allEmps.get(i).calculateActualSalary();
        }
        return sum;
    }

    abstract boolean runTask(Task t);

    public void freeAll() {
        availableVacations = 15;
        availablePermissionHours = 20;
        actualSalary = salary;
    }
}
