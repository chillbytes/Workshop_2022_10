public class Workshop {
    public static String MENU_ITEM_TITLE    = "Please select an option:";
    public static String MENU_ITEM_ADD      = "add    [a]";
    public static String MENU_ITEM_REMOVE   = "remove [r]";
    public static String MENU_ITEM_LIST     = "list   [l]";
    public static String MENU_ITEM_EXIT     = "exit   [e]";

    public static void main(String[] args) {

        showMenu();

    }
    public static void showMenu() {

        StringBuilder sb = new StringBuilder();
        sb.append(MENU_ITEM_TITLE).append("\n");
        sb.append(MENU_ITEM_ADD).append("\n");
        sb.append(MENU_ITEM_REMOVE).append("\n");
        sb.append(MENU_ITEM_LIST).append("\n");
        sb.append(MENU_ITEM_EXIT).append("\n");



        System.out.println(String.valueOf(sb));
    }


}
