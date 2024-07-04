import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyFile.help();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] commands = input.split(" ");
            String command = commands[0];

            switch (command) {
                case "ls":
                    if (commands.length == 2) {
                        MyFile.listDirectory(commands[1]);
                    } else {
                        System.out.println("Usage: ls <path>");
                    }
                    break;
                case "ls_py":
                    if (commands.length == 2) {
                        MyFile.listPythonFiles(commands[1]);
                    } else {
                        System.out.println("Usage: ls_py <path>");
                    }
                    break;
                case "is_dir":
                    if (commands.length == 2) {
                        MyFile.isDirectory(commands[1]);
                    } else {
                        System.out.println("Usage: is_dir <path>");
                    }
                    break;
                case "define":
                    if (commands.length == 2) {
                        MyFile.define(commands[1]);
                    } else {
                        System.out.println("Usage: define <path>");
                    }
                    break;
                case "readmod":
                    if (commands.length == 2) {
                        MyFile.printPermissions(commands[1]);
                    } else {
                        System.out.println("Usage: readmod <path>");
                    }
                    break;
                case "setmod":
                    if (commands.length == 3) {
                        MyFile.setPermissions(commands[1], commands[2]);
                    } else {
                        System.out.println("Usage: setmod <path> <perm>");
                    }
                    break;
                case "cat":
                    if (commands.length == 2) {
                        MyFile.printContent(commands[1]);
                    } else {
                        System.out.println("Usage: cat <path>");
                    }
                    break;
                case "append":
                    if (commands.length == 2) {
                        MyFile.appendFooter(commands[1]);
                    } else {
                        System.out.println("Usage: append <path>");
                    }
                    break;
                case "bc":
                    if (commands.length == 2) {
                        MyFile.createBackup(commands[1]);
                    } else {
                        System.out.println("Usage: bc <path>");
                    }
                    break;
                case "greplong":
                    if (commands.length == 2) {
                        MyFile.printLongestWord(commands[1]);
                    } else {
                        System.out.println("Usage: greplong <path>");
                    }
                    break;
                case "help":
                    MyFile.help();
                    break;
                case "exit":
                    MyFile.exit();
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' for a list of commands.");
                    break;
            }
        }
    }
}
