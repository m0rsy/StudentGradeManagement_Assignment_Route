import java.util.Scanner;

public class StudentGradeManagement {

    public static void showMenu() {
        System.out.println("\nMAIN MENU");
        System.out.println("1. Show All Students names");
        System.out.println("2. Show all Students grades in each subject");
        System.out.println("3. Search Student by name");
        System.out.println("4. Count Passed Students");
        System.out.println("5. Display average for each subject");
        System.out.println("6. Display highest grade in each subject");
        System.out.println("7. Display letter grades");
        System.out.println("0. Exit");
    }

    public static void inputStudents(Scanner scanner, String[] students) {
        for (int i = 0; i < students.length; i++) {
            System.out.print("Enter student " + (i + 1) + " name: ");
            students[i] = scanner.nextLine();
        }
    }

    public static void inputGrades(Scanner scanner, String[] students, String[] subjects, int[][] grades) {
        for (int i = 0; i < students.length; i++) {
            System.out.println("Enter grades for " + students[i] + ":");

            for (int j = 0; j < subjects.length; j++) {
                System.out.print("Enter grade for " + subjects[j] + ": ");
                int grade = scanner.nextInt();

                while (grade < 0 || grade > 100) {
                    System.out.println("Invalid grade.");
                    System.out.print("Enter grade for " + subjects[j] + ": ");
                    grade = scanner.nextInt();
                }

                grades[i][j] = grade;
            }
            scanner.nextLine();
        }
    }

    public static void showAllStudents(String[] students) {
        System.out.println("List of Students:");
        for (String student : students) {
            System.out.println(student);
        }
    }

    public static void showAllGrades(String[] students, String[] subjects, int[][] grades) {
        System.out.println("Grades for all students:");
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i] + ":");
            for (int j = 0; j < subjects.length; j++) {
                System.out.println(subjects[j] + ": " + grades[i][j]);
            }
        }
    }

    public static void searchStudent(String[] students, String[] subjects, int[][] grades, String searchName) {
        boolean found = false;

        for (int i = 0; i < students.length; i++) {
            if (students[i].equalsIgnoreCase(searchName)) {
                found = true;
                System.out.println("Grades for " + students[i] + ":");

                for (int j = 0; j < subjects.length; j++) {
                    System.out.println(subjects[j] + ": " + grades[i][j]);
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public static int countPassed(int[][] grades) {
        int count = 0;

        for (int i = 0; i < grades.length; i++) {
            boolean passed = true;

            for (int j = 0; j < grades[i].length; j++) {
                if (grades[i][j] < 50) {
                    passed = false;
                    break;
                }
            }

            if (passed) {
                count++;
            }
        }

        return count;
    }

    public static double getSubjectAverage(int[][] grades, int subjectIndex) {
        int sum = 0;

        for (int i = 0; i < grades.length; i++) {
            sum += grades[i][subjectIndex];
        }

        return (double) sum / grades.length;
    }

    public static double getStudentAverage(int[] studentGrades) {
        int sum = 0;

        for (int grade : studentGrades) {
            sum += grade;
        }

        return (double) sum / studentGrades.length;
    }

    public static int getHighest(int[][] grades, int subjectIndex) {
        int highest = grades[0][subjectIndex];

        for (int i = 1; i < grades.length; i++) {
            if (grades[i][subjectIndex] > highest) {
                highest = grades[i][subjectIndex];
            }
        }

        return highest;
    }

    public static double showHighestGrade(double value) {
        return Math.round(value * 100) / 100.0;
    }

    public static char getLetterGrade(int grade) {
        if (grade >= 85) {
            return 'A';
        } else if (grade >= 75) {
            return 'B';
        } else if (grade >= 65) {
            return 'C';
        } else if (grade >= 50) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static void showLetterGrades(String[] students, String[] subjects, int[][] grades) {
        System.out.println("Letter grades for all students:");

        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i] + ":");

            for (int j = 0; j < subjects.length; j++) {
                System.out.println(subjects[j] + ": " + getLetterGrade(grades[i][j]));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] students = new String[5];
        String[] subjects = {"Java", "Python", "C++"};
        int[][] grades = new int[5][3];

        inputStudents(scanner, students);
        inputGrades(scanner, students, subjects, grades);

        showAllStudents(students);
        showAllGrades(students, subjects, grades);

        while (true) {
            showMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showAllStudents(students);
                    break;

                case 2:
                    showAllGrades(students, subjects, grades);
                    break;

                case 3:
                    System.out.print("Enter student name to search: ");
                    searchStudent(students, subjects, grades, scanner.nextLine());
                    break;

                case 4:
                    System.out.println("Number of students who passed: " + countPassed(grades));
                    break;

                case 5:
                    System.out.println("Average grades for each subject:");

                    for (int i = 0; i < subjects.length; i++) {
                        System.out.println(subjects[i] + ": " + getSubjectAverage(grades, i));
                    }
                    break;

                case 6:
                    System.out.println("Highest grades for each subject:");

                    for (int i = 0; i < subjects.length; i++) {
                        System.out.println(subjects[i] + ": " + showHighestGrade(getHighest(grades, i)));
                    }
                    break;

                case 7:
                    showLetterGrades(students, subjects, grades);
                    break;

                case 0:
                    System.out.println("Have a nice day!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
