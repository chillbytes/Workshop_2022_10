import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Workshop {
    public static String MENU_ITEM_TITLE    = "Please select an option:";
    public static String MENU_ITEM_ADD      = "add    [a]";
    public static String MENU_ITEM_REMOVE   = "remove [r]";
    public static String MENU_ITEM_LIST     = "list   [l]";
    public static String MENU_ITEM_EXIT     = "exit   [e]";

    public static String CSV_FILE_NAME      = "tasks.csv";

    public static void main(String[] args) {

        String [][] tasksArr =  importCsvFile(CSV_FILE_NAME);
        showMenu();

        listTasks(tasksArr);


        exitProgram();




    }
    public static void showMenu() {

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

                //rozszez tablicę o nową linię:
                csvDataArr = Arrays.copyOf(csvDataArr, csvDataArr.length + 1);

                System.out.println("Wielkość tablicy: " + csvDataArr.length);

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

                sb.append(tasksArray[i][j]);
                if (j == 0) {
                    sb.append(" : ");
                }
                if (j == 3) {
                    sb.append("\n");
                }
            }
        }
        System.out.println(String.valueOf(sb));


        //for (int i = 0; i < tasksArr.length; i ++) {
        //   for (int j = 0; j < tasksArr[i].length; j++) {
        //        System.out.println("[" + i + "][" + j + "]: " + tasksArr[i][j] );
        //    }
        //}

    }

    public static void exitProgram() {
        System.out.println("Program Tasks terminated");
        System.exit(0);
    }

    public static void addTask(String [][] taskArray) {
        //get max index

        //add name

        //add date

        //urgency (true/false)

    }
    public static void removeTasks(String[][] taskArray) {

    }
    public static void saveTasksToCsvFile(String[][] tasksArray){

    }

    public static int getMaxIndex(String[][] tasksArray) {

    }










    public static void colors( ) {
        System.out.println(pl.coderslab.ConsoleColors.GREEN_BOLD + " green bold ");
        System.out.println(pl.coderslab.ConsoleColors.RED + " red ");
        System.out.println(pl.coderslab.ConsoleColors.BLUE + " blue ");
        System.out.println(pl.coderslab.ConsoleColors.RESET + " back to default");
    }
}
