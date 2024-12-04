import java.util.Scanner;

public class bully {
    static boolean[] state = new boolean[5]; // Array to maintain the status of processes
    int coordinator; // Variable to hold the current coordinator

    public static void up(int up) {
        if (state[up - 1]) {
            System.out.println("Process " + up + " is already up.");
        } else {
            state[up - 1] = true;
            System.out.println("Process " + up + " held an election.");
            for (int i = up; i < 5; ++i) {
                System.out.println("Election message sent from process " + up + " to process " + (i + 1));
            }
            for (int i = up + 1; i <= 5; ++i) {
                if (!state[i - 1]) continue;
                System.out.println("Alive message sent from process " + i + " to process " + up);
                break;
            }
        }
    }

    public static void down(int down) {
        if (!state[down - 1]) {
            System.out.println("Process " + down + " is already down.");
        } else {
            state[down - 1] = false;
            System.out.println("Process " + down + " is now down.");
        }
    }

    public static void mess(int mess) {
        if (state[mess - 1]) {
            if (state[4]) {
                System.out.println("Message OK from process " + mess + " to coordinator process 5.");
            } else {
                System.out.println("Process " + mess + " held an election.");
                for (int i = mess; i < 5; ++i) {
                    System.out.println("Election message sent from process " + mess + " to process " + (i + 1));
                }
                for (int i = 5; i >= mess; --i) {
                    if (!state[i - 1]) continue;
                    System.out.println("Coordinator message sent from process " + i + " to all.");
                    break;
                }
            }
        } else {
            System.out.println("Process " + mess + " is down.");
        }
    }

    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);

        // Initialize all processes as active
        for (int i = 0; i < 5; ++i) {
            state[i] = true;
        }

        System.out.println("5 active processes are:");
        System.out.println("Processes up = P1 P2 P3 P4 P5");
        System.out.println("Process 5 is the coordinator.");

        do {
            System.out.println(".........");
            System.out.println("1. Bring up a process.");
            System.out.println("2. Bring down a process.");
            System.out.println("3. Send a message.");
            System.out.println("4. Exit.");
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.println("Bring process up:");
                    int up = sc.nextInt();
                    if (up == 5) {
                        System.out.println("Process 5 is the coordinator.");
                        state[4] = true;
                    } else {
                        up(up);
                    }
                    break;
                }
                case 2: {
                    System.out.println("Bring down a process:");
                    int down = sc.nextInt();
                    down(down);
                    break;
                }
                case 3: {
                    System.out.println("Which process will send the message?");
                    int mess = sc.nextInt();
                    mess(mess);
                    break;
                }
                case 4: {
                    System.out.println("Exiting the program.");
                    break;
                }
                default: {
                    System.out.println("Invalid choice. Try again.");
                    break;
                }
            }
        } while (choice != 4);

        sc.close();
    }
}
/*---------------------------------------------------OUTPUT-----------------------

PS C:\Users\HP\Downloads\Assignment 4\Assignment 4> javac bully.java
PS C:\Users\HP\Downloads\Assignment 4\Assignment 4> java bully
5 active process are:
Process up = p1 p2 p3 p4 p5
Process 5 is coordinator
.........
1 up a process.
2.down a process
3 send a message
4.Exit
2
bring down any process.
5
.........
1 up a process.
2.down a process
3 send a message
4.Exit
3
which process will send message
2
process2election
election send from process2 to process 3
election send from process2 to process 4
election send from process2 to process 5
Coordinator message send from process4to all
.........
1 up a process.
2.down a process
3 send a message
4.Exit
4
 */