import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyFile {

    public static void listDirectory(String path) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("Path is not a directory");
        }
    }

    public static void listPythonFiles(String path) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            for (File file : Objects.requireNonNull(dir.listFiles((d, name) -> name.endsWith(".py")))) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("Path is not a directory");
        }
    }

    public static void isDirectory(String path) {
        File file = new File(path);
        System.out.println(file.isDirectory());
    }

    public static void define(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            System.out.println("директория");
        } else if (file.isFile()) {
            System.out.println("файл");
        } else {
            System.out.println("Unknown type");
        }
    }

    public static void printPermissions(String path) {
        File file = new File(path);
        String permissions = (file.canRead() ? "r" : "-") + (file.canWrite() ? "w" : "-") + (file.canExecute() ? "x" : "-");
        System.out.println(permissions);
    }

    public static void setPermissions(String path, String permissions) {
        File file = new File(path);
        file.setReadable(permissions.charAt(0) == 'r');
        file.setWritable(permissions.charAt(1) == 'w');
        file.setExecutable(permissions.charAt(2) == 'x');
    }

    public static void printContent(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    public static void appendFooter(String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write("# Autogenerated line");
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    public static void createBackup(String path) {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        File src = new File(path);
        File dest = new File("/tmp/" + date + ".backup/" + src.getName());

        try {
            if (src.isDirectory()) {
                copyDirectory(src.toPath(), dest.toPath());
            } else {
                Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            System.out.println("Error creating backup");
        }
    }

    private static void copyDirectory(Path src, Path dest) throws IOException {
        Files.walk(src).forEach(source -> {
            try {
                Files.copy(source, dest.resolve(src.relativize(source)), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void printLongestWord(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            String longestWord = "";
            while ((line = br.readLine()) != null) {
                for (String word : line.split("\\W+")) {
                    if (word.length() > longestWord.length()) {
                        longestWord = word;
                    }
                }
            }
            System.out.println(longestWord);
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    public static void help() {
        System.out.println("MyFS 1.0 команды:");
        System.out.println("ls <path>               выводит список всех файлов и директорий для path");
        System.out.println("ls_py <path>            выводит список файлов с расширением .py в path");
        System.out.println("is_dir <path>           выводит true, если path это директория, в других случаях false");
        System.out.println("define <path>           выводит директория или файл в зависимости от типа path");
        System.out.println("readmod <path>          выводит права для файла в формате rwx для текущего пользователя");
        System.out.println("setmod <path> <perm>    устанавливает права для файла path");
        System.out.println("cat <path>              выводит контент файла");
        System.out.println("append <path>           добавляет строку # Autogenerated line в конец path");
        System.out.println("bc <path>               создает копию path в директорию /tmp/${date}.backup где, date - это дата в формате dd-mm-yyyy");
        System.out.println("greplong <path>         выводит самое длинное слово в файле");
        System.out.println("help                    выводит список команд и их описание");
        System.out.println("exit                    завершает работу программы");
    }

    public static void exit() {
        System.out.println("Goodbye");
        System.exit(0);
    }
}
