import java.util.Scanner;

public class ring {
    public static void main(String[] args) {
        int temp, i, j;
        int arr[] = new int[10];
        Rr proc[] = new Rr[10];

        // Initialize process objects
        for (i = 0; i < proc.length; i++) {
            proc[i] = new Rr();
        }

        // Scanner for user input
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        int num = in.nextInt();

        // Get input for each process
        for (i = 0; i < num; i++) {
            proc[i].index = i;
            System.out.println("Enter the ID of process " + (i + 1) + ": ");
            proc[i].id = in.nextInt();
            proc[i].state = "active";
            proc[i].f = 0;
        }

        // Sort processes by ID
        for (i = 0; i < num - 1; i++) {
            for (j = 0; j < num - i - 1; j++) {
                if (proc[j].id > proc[j + 1].id) {
                    temp = proc[j].id;
                    proc[j].id = proc[j + 1].id;
                    proc[j + 1].id = temp;
                }
            }
        }

        // Display sorted processes
        System.out.println("Processes sorted by ID:");
        for (i = 0; i < num; i++) {
            System.out.print(" [" + i + "] " + proc[i].id);
        }
        System.out.println();

        // Set the last process as inactive (simulating failure)
        proc[num - 1].state = "inactive";
        System.out.println("\nProcess " + proc[num - 1].id + " selected as coordinator.");

        // Menu for election and termination
        while (true) {
            System.out.println("\n1. Election 2. Quit");
            int ch = in.nextInt();

            // Reset flags for all processes
            for (i = 0; i < num; i++) {
                proc[i].f = 0;
            }

            switch (ch) {
                case 1:
                    System.out.println("\nEnter the process number that initiates the election: ");
                    int init = in.nextInt() - 1; // Convert to zero-based index
                    int temp2 = init;
                    int temp1 = init + 1;
                    i = 0;

                    // Election process
                    while (temp2 != temp1) {
                        if ("active".equals(proc[temp1].state) && proc[temp1].f == 0) {
                            System.out.println("\nProcess " + proc[init].id + " sends message to " + proc[temp1].id);
                            proc[temp1].f = 1;
                            init = temp1;
                            arr[i] = proc[temp1].id;
                            i++;
                        }
                        if (temp1 == num - 1) {
                            temp1 = 0;
                        } else {
                            temp1++;
                        }
                    }
                    System.out.println("\nProcess " + proc[init].id + " sends message to " + proc[temp1].id);
                    arr[i] = proc[temp1].id;
                    i++;

                    // Determine new coordinator
                    int max = -1;
                    for (j = 0; j < i; j++) {
                        if (max < arr[j]) {
                            max = arr[j];
                        }
                    }
                    System.out.println("\nProcess " + max + " selected as coordinator.");
                    for (i = 0; i < num; i++) {
                        if (proc[i].id == max) {
                            proc[i].state = "inactive";
                        }
                    }
                    break;

                case 2:
                    System.out.println("Program terminated.");
                    in.close();
                    return;

                default:
                    System.out.println("\nInvalid response.\n");
                    break;
            }
        }
    }
}

class Rr {
    public int index; // Index of process
    public int id;    // ID of process
    public int f;     // Flag for election participation
    String state;     // State (active/inactive)
}

/*-----------------OTPUT-----------------------------------------


--------------------------
PS C:\Users\HP\Downloads\Assignment 4\Assignment 4> javac ring.java 
PS C:\Users\HP\Downloads\Assignment 4\Assignment 4> java ring    
Enter the number of processes: 
4
Enter the ID of process 1: 
1
Enter the ID of process 2: 
2
Enter the ID of process 3: 
3
Enter the ID of process 4: 
4
Processes sorted by ID:
 [0] 1 [1] 2 [2] 3 [3] 4

Process 4 selected as coordinator.

1. Election 2. Quit
1

Enter the process number that initiates the election:
2

Process 2 sends message to 3
Process 3 sends message to 1

Process 1 sends message to 2

Process 3 selected as coordinator.

1. Election 2. Quit
2
------------------------------------------------------------------------------------*/
