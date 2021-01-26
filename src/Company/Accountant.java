package Company;

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
