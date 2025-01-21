package HardProgram;

public class Manager extends Employee
{

	public Manager(int id, String name, double salary) {
        super(id, name, salary);
    }

    @Override
    public void display() {
        System.out.print("Manager - ");
        super.display();
    }

}
