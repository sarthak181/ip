package duke;

public class Parser {
    private static Ui ui;

    public Parser() {
        ui = new Ui();
    }

    /**
     * The method simply returns the case to use for the switch in the parse method.
     *
     * @param str the string that the user inputted.
     * @return the string that defines the case.
     */
    public static String getSwitch(String str) {
        if (str.equals("list")) {
            return "list";
        }
        if (str.startsWith("mark ")) {
            return "mark";
        }
        if (str.startsWith("unmark ")) {
            return "unmark";
        }
        if (str.startsWith("todo ")) {
            return "todo";
        }
        if (str.startsWith("deadline ")) {
            return "deadline";
        }
        if (str.startsWith("event ")) {
            return "event";
        }
        if (str.startsWith("delete ")) {
            return "delete";
        }
        if (str.startsWith("find ")) {
            return "find";
        }
        if (str.equals("bye")) {
            ui.bye();
            return "do nothing";
        }
        return "na";
    }

    /**
     * The method makes sense of the command that the user has inputted and takes the
     * necessary action.
     *
     * @param str is the string that the user inputted
     * @param taskList is that list that will be acted on by the command.
     */
    public static void parse(String str, TaskList taskList) {
        switch (getSwitch(str)) {
        case "list":
            taskList.recite();
            break;

        case "mark":
            String num = str.split(" ", 2)[1];
            int n = Integer.parseInt(num) - 1;
            taskList.mark(n, true);
            break;
        case "unmark":
            String num_1 = str.split(" ", 2)[1];
            int n_1 = Integer.parseInt(num_1) - 1;
            taskList.mark(n_1, false);
            break;
        case "todo":
            Task a;
            try {
                a = new Todo(str.replace("todo ", ""));
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                break;
            }
            taskList.addTask(a);
            break;
        case "deadline":
            Task b = null;
            try {
                String[] descriptionBy = str.replace("deadline ", "").split(" /by ");
                b = new Deadline(descriptionBy[0], descriptionBy[1]);
                b.isDate();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                break;
            }
            taskList.addTask(b);
            break;
        case "event":
            Task c = null;
            try {
                String[] descriptionFromTo = str.replace("event ", "").split(" /from ");
                String[] fromTo = descriptionFromTo[1].split(" /to ");
                c = new Event(descriptionFromTo[0], fromTo[0], fromTo[1]);
                c.isDate();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                break;
            }
            taskList.addTask(c);
            break;
        case "delete":
            String num_D = str.split(" ", 2)[1];
            int nD = Integer.parseInt(num_D) - 1;
            taskList.delete(nD);
            break;
        case "find":
            String keyword = str.replace("find ", "");
            taskList.find(keyword);
            break;
        case "na":
            ui.unknownCommand();
            break;

        case "do nothing":
            break;
        }
    }
}
