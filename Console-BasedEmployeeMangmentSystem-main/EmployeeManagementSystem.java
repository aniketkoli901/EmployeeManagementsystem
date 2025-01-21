package HardProgram;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagementSystem
{
	private static ArrayList<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        boolean exitProgram = false;

        while (!exitProgram) {
            if (login()) {
                boolean logout = false;
                while (!logout) {
                    int choice = displayMainMenu();
                    switch (choice) {
                        case 1:
                            addEmployee();
                            break;
                        case 2:
                            updateEmployee();
                            break;
                        case 3:
                            deleteEmployee();
                            break;
                        case 4:
                            displayAllEmployees();
                            break;
                        case 5:
                            selectEmployee();
                            break;
                        case 6:
                            logout = true;
                            System.out.println("Logged out successfully.\n");
                            break;
                        case 7:
                            logout = true;
                            exitProgram = true;
                            System.out.println("Exiting the program. Goodbye!");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("Exceeded maximum login attempts. Exiting program.");
                exitProgram = true;
            }
        }

        scanner.close();
    }

    
    private static boolean login() {
        int attempts = 0;
        int maxAttempts = 3;

        System.out.println("=== Employee Management System Login ===");

        while (attempts < maxAttempts) {
            System.out.print("Enter username: ");
            String inputUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String inputPassword = scanner.nextLine();

            if (inputUsername.equals(USERNAME) && inputPassword.equals(PASSWORD)) {
                System.out.println("Login successful!\n");
                return true;
            } else {
                attempts++;
                System.out.println("Invalid credentials. Attempts left: " + (maxAttempts - attempts) + "\n");
            }
        }

        return false;
    }

    
    private static int displayMainMenu() {
        System.out.println("=== Main Menu ===");
        System.out.println("1. Add Employee");
        System.out.println("2. Update Employee");
        System.out.println("3. Delete Employee");
        System.out.println("4. Display All Employees");
        System.out.println("5. Select Particular Employee");
        System.out.println("6. Logout");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");

        int choice = -1;

       
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine(); 
        } else {
            System.out.println("Invalid input. Please enter a number.\n");
            scanner.nextLine(); 
        }

        return choice;
    }

    
    private static void addEmployee() {
        try {
            System.out.print("Enter employee ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("========================================================");

            System.out.print("Enter employee name: ");
            String name = scanner.nextLine();
            System.out.println("=========================================================");

            System.out.print("Enter employee salary: ");
            double salary = Double.parseDouble(scanner.nextLine());
            System.out.println("=========================================================");
            
            System.out.println("Select Employee Type:");
            System.out.println("1. Manager");
            System.out.println("2. Sales Executive");
            System.out.println("3. Developer");
            System.out.print("Enter your choice: ");
            int type = Integer.parseInt(scanner.nextLine());

            Employee employee;
            switch (type) {
                case 1:
                    employee = new Manager(id, name, salary);
                    break;
                case 2:
                    employee = new SalesExe(id, name, salary);
                    break;
                case 3:
                    employee = new Developer(id, name, salary);
                    break;
                default:
                    System.out.println("Invalid employee type. Employee not added.\n");
                    return;
            }

            employees.add(employee);
            System.out.println("Employee added successfully!\n");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter numeric values for ID and salary.\n");
        }
    }

 
    private static void updateEmployee() {
        try {
            System.out.print("Enter employee ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());

            Employee employee = findEmployeeById(id);
            if (employee != null) {
                System.out.print("Enter new name (leave blank to keep unchanged): ");
                String newName = scanner.nextLine();
                if (!newName.trim().isEmpty()) {
                    employee.setName(newName);
                }

                System.out.print("Enter new salary (enter -1 to keep unchanged): ");
                double newSalary = Double.parseDouble(scanner.nextLine());
                if (newSalary >= 0) {
                    employee.setSalary(newSalary);
                }

                System.out.println("Employee updated successfully!\n");
            } else {
                System.out.println("Employee not found.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter numeric values for ID and salary.\n");
        }
    }

   
    private static void deleteEmployee() {
        try {
            System.out.print("Enter employee ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            Employee employee = findEmployeeById(id);
            if (employee != null) {
                employees.remove(employee);
                System.out.println("Employee deleted successfully!\n");
            } else {
                System.out.println("Employee not found.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter a numeric value for ID.\n");
        }
    }

    
    private static void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.\n");
        } else {
            System.out.println("=== Employee List ===");
            for (Employee employee : employees) {
                employee.display();
            }
            System.out.println(); 
        }
    }

    
    private static void selectEmployee() {
        try {
            System.out.print("Enter employee ID to view: ");
            int id = Integer.parseInt(scanner.nextLine());

            Employee employee = findEmployeeById(id);
            if (employee != null) {
                System.out.println("=== Employee Details ===");
                employee.display();
                System.out.println(); 
            } else {
                System.out.println("Employee not found.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter a numeric value for ID.\n");
        }
    }

    
    private static Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) 
            {
                return employee;
            }
        }
        return null;
    }
}
