import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Workshop {
    public static String MENU_ITEM_TITLE    = "Please select an option:";
    public static String MENU_ITEM_ADD      = "add    [a]";
    public static String MENU_ITEM_REMOVE   = "remove [r]";
    public static String MENU_ITEM_LIST     = "list   [l]";
    public static String MENU_ITEM_EXIT     = "exit   [e]";
    public static String CSV_FILE_NAME      = "tasks.csv";

    public static void main(String[] args) {
        //wczytanie danych z pliku;
        String [][] tasksArr =  importCsvFile(CSV_FILE_NAME);

        showMenu(tasksArr);

        //listTasks(tasksArr);

        //add task
        //tasksArr = addTask(tasksArr);

        //saveTasksToCsvFile(tasksArr);

        //remove task
        //tasksArr = removeTasks(tasksArr);

        //listTasks(tasksArr);

        //saveTasksToCsvFile(tasksArr);


    }
    public static void showMenu(String[][] tasksArray) {

        StringBuilder sb = new StringBuilder();

        sb.append(pl.coderslab.ConsoleColors.BLUE).append(MENU_ITEM_TITLE).append("\n");
        sb.append(pl.coderslab.ConsoleColors.RESET).append(MENU_ITEM_ADD).append("\n");
        sb.append(MENU_ITEM_REMOVE).append("\n");
        sb.append(MENU_ITEM_LIST).append("\n");
        sb.append( MENU_ITEM_EXIT).append("\n");

        System.out.println(String.valueOf(sb));










    }
    public static String[][] importCsvFile(String fileName) {
        Path csvPath            = Paths.get(fileName);
        File csvFile            = new File(fileName);
        String[][] csvDataArr   = new String[0][3];
        String impLine          = "";

        int err                 = 0;
        int counter             = 0;

        if (!Files.exists(csvPath)) {
            try {
                csvFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to create file " + fileName);
                err = 1;
            }
        }

        try {
            for (String line : Files.readAllLines(csvPath)) {
                //dodaj poszczególne linie z pliku csv do pól tablicy//
                String[] csvLine = line.split(",");

                //rozszerz tablicę o nową linię:
                csvDataArr = Arrays.copyOf(csvDataArr, csvDataArr.length + 1);

                line = String.valueOf(counter) + "," + line;

                csvDataArr[csvDataArr.length - 1] = line.split(",");

                counter ++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvDataArr;
    }
    public static void listTasks(String[][] tasksArray) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasksArray.length; i++) {
            for (int j = 0; j < tasksArray[i].length; j++) {

                sb.append(tasksArray[i][j].trim()).append(" ");
                if (j == 0) {
                    sb.append(" : ");
                }
                if (j == 3) {
                    sb.append("\n");
                }
            }
        }
        System.out.println(String.valueOf(sb));
    }
    public static void exitProgram() {
        System.out.println("Program Tasks terminated");
        System.exit(0);
    }
    public static String[][] addTask(String [][] taskArray) {
        String taskDesc    = "";
        String taskDueDate = "";
        String taskUrgency = "";

        //  max id zadania//
        int newTaskId = getMaxIndex(taskArray) + 1;

        //opis zadania//
        taskDesc = getDataFromConsole("Please add Task description", 0, 0);
        System.out.println("Task descriprion: " + taskDesc);

        //temin wykonania//
        taskDueDate = getDataFromConsole("Please add Task due date", 0, 0);
        System.out.println("Task due date: " + taskDueDate);

        //pilność zadania//
        taskUrgency = getDataFromConsole("Is your Task important: true/false", 1, 0);
        System.out.println("Task urgency: " + taskUrgency);

        //budowanie ciągu tekstowego do umieszczenia w nowej linii tablicy//
        String line = String.valueOf(newTaskId) + "," + taskDesc.trim() + "," + taskDueDate.trim() + "," + taskUrgency.trim();

        //kopiowanie tablicy i rozszerzenie jej o nowy wiersz
        taskArray = Arrays.copyOf(taskArray, taskArray.length + 1);

        //pprzypisanie danych do nowego poziomu tablicy
        taskArray[taskArray.length - 1] = line.split(",");

        //wylistaowanie zawartości tabelicy
        listTasks(taskArray);

        return taskArray;
    }
    public static String[][] removeTasks(String[][] taskArray) {
        StringBuilder sb = new StringBuilder();

        String taskIdStr = getDataFromConsole("Please select number to remove", 2, getMaxIndex( taskArray ));

        for (int i = 0; i < taskArray.length; i++) {

            if (Integer.parseInt(taskIdStr) == Integer.parseInt(taskArray[i][0])) {
                taskArray = ArrayUtils.remove( taskArray, i);
                break;
            }
        }

        return taskArray;

    }
    public static void saveTasksToCsvFile(String[][] tasksArray){

        Path csvPath = Paths.get(CSV_FILE_NAME);
        StringBuilder sb = new StringBuilder();

        //budowanie ciągu tekstowego do zapisania do pliku//
        for (int i = 0; i < tasksArray.length; i++) {

            //indeks j zaczyna się od drugiego elementu (1) bo indeks zadania nie jest zapisywany do pliku//
            for (int j = 1; j < tasksArray[i].length; j++) {
                sb.append(tasksArray[i][j].trim());
                if (j < 3) {sb.append(",");}
            }

            //każdy wiersz zadania zakończyć znakiem nowej linii//
            sb.append("\n");
        }

        System.out.println(sb);

        try {
            Files.writeString(csvPath, String.valueOf(sb));
        } catch (IOException ex) {
            System.out.println("Unable to write t file.");
        }

    }
    public static int getMaxIndex(String[][] tasksArray) {
        int maxTaxIndex = 0;

        for (int i = 0; i < tasksArray.length; i++ ) {
            try {
                if (maxTaxIndex < Integer.parseInt(tasksArray[i][0])) {
                    maxTaxIndex = Integer.parseInt(tasksArray[i][0]);
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Id nie jest liczbą: " + tasksArray[i][0]);
            }

        }

        return maxTaxIndex;
    }
    public static String getDataFromConsole(String promptStr, int flag, int maxTaskId ) {
        Scanner scan = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int num = 0;

        scan.useDelimiter("\n");

        sb.append(pl.coderslab.ConsoleColors.YELLOW).append(promptStr).append("\n").append(pl.coderslab.ConsoleColors.RESET);
        System.out.println(sb);

        if (flag == 1) {
            while( (!scan.hasNext("true")) && (!scan.hasNext("false") ) ){
                scan.nextLine();
                System.out.println("Data incorrect");
                System.out.println(sb);
            }
        }
        else if (flag == 0) {
            while (!scan.hasNext()) {
                scan.nextLine();
                System.out.println("Data incorrect");
                System.out.println(sb);
            }
        }
        else if (flag == 2) {
            while (scan.hasNext()) {
                if (scan.hasNextInt()) {
                    num = scan.nextInt();
                    if (num >= 0 && num <= maxTaskId)  {
                         break;
                    } else {
                        System.out.println("Data incorrect");
                        System.out.println(sb);
                        continue;
                    }
                } else {
                    System.out.println("Data incorrect");
                    System.out.println(sb);
                    scan.next();
                }
            }
        }

        if (flag == 2 ) {

            return String.valueOf( num );
        }
        else {
            return scan.nextLine();
        }
    }











    public static void colors( ) {
        System.out.println(pl.coderslab.ConsoleColors.GREEN_BOLD + " green bold ");
        System.out.println(pl.coderslab.ConsoleColors.RED + " red ");
        System.out.println(pl.coderslab.ConsoleColors.BLUE + " blue ");
        System.out.println(pl.coderslab.ConsoleColors.RESET + " back to default");
    }
}
