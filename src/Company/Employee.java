package Company;

//abstract ya3ni mynf3sh a3ml mno OBJECT 
import java.util.ArrayList;

enum Gender {
    Male, Female
};

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

    abstract boolean runTask(Task t);
    
    double calculateActualSalary() {
        actualSalary = salary;
        if (availableVacations < 0) {
            actualSalary += (deductionPerDay * availableVacations);
        }
        if (availablePermissionHours < 0) {
            actualSalary += (deductionPerHour * availablePermissionHours);
        }
        return actualSalary;
    }

    public void freeAll() {
        availableVacations = 15;
        availablePermissionHours = 20;
        actualSalary = salary;
    }
}
