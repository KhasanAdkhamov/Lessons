package COLLECTIONS.LinkedList;

import java.util.LinkedList;
import java.util.List;

public class Work {

    public static void main(String[] args) {
        List<String> todoSmt = new LinkedList<>();
        List<String> redo = new LinkedList<>();
        doAction("to buy smt", todoSmt, redo);
        doAction("to sell smt", todoSmt, redo);
        doAction("to bring smt", todoSmt, redo);
        doAction("to eat smt", todoSmt, redo);
        doAction("to drink smt", todoSmt, redo);
        undo(todoSmt, redo);
        redo(todoSmt, redo);

    }

    public static void doAction(String action, List<String> toDo, List<String> reDo) {
        if (action.isEmpty()) {
            throw new NullPointerException("");
        }
        toDo.add(action);
        reDo.clear();
        System.out.println("действие добавлено " + action);
    }

    public static void undo(List<String> toDo, List<String> reDo) {
        if (toDo.isEmpty()) {
            throw new NullPointerException("");
        }
        String lastAction = toDo.removeLast();
        reDo.add(lastAction);
        System.out.println("удалил последнее действие и добавил в отмененные" + lastAction);
    }

    public static void redo(List<String> toDo, List<String> reDo) {
        if (reDo.isEmpty()) {
            throw new NullPointerException("");
        }
        String lastAction = reDo.removeLast();
        toDo.addLast(lastAction);
        System.out.println("отиенил действие из отложенных действий и добавил " +lastAction);
    }
}
