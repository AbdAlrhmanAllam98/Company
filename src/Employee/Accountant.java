package Employee;

import Company.Task;
import java.util.ArrayList;

public class Accountant extends Employee {

    boolean certifiedExcel;
    static double Taxes = 0.15;

    public Accountant(String name, String nationalId, char gender) {
        super(name, nationalId, gender);
        certifiedExcel = true;
    }

    @Override
    public double calculateActualSalary() {

        return super.calculateActualSalary() - (Taxes * actualSalary);
    }
    static void printPayRoll_To_Accountant(ArrayList<Employee> allEmps) {
        for (int i = 0; i < allEmps.size(); i++) {
            if (allEmps.get(i) instanceof Accountant) {
                System.out.println(allEmps.get(i).getName() + " : " + allEmps.get(i).calculateActualSalary());
            }
        }
    }
    static double PayRoll_To_Accountant(ArrayList<Employee> allEmps) {
        double sum = 0;
        for (int i = 0; i < allEmps.size(); i++) {
            if (allEmps.get(i) instanceof Accountant) {
                sum += allEmps.get(i).calculateActualSalary();
            }
        }
        return sum;
    }
    @Override
    boolean runTask(Task t) {
        System.out.println("Analyze");
        System.out.println("Journalize");
        System.out.println("Posting");
        System.out.println("Summarize");
        System.out.println("Adjusting");
        System.out.println("Correcting");
        System.out.println("Organizing");
        System.out.println("Closing");
        System.out.println("Finalizing");
        return true;
    }

}
