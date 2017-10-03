package ru.job4j.tracker.start;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class MenuTracker.
 *
 * @author Alexander Ivanov
 * @version 2.0
 * @since 03.10.2017
 */
public class MenuTracker {
    /**
     * variable of input.
     */
    private Input input;

    /**
     * Connection to database.
     */
    private Connection connection;

    /**
     * constructor for MenuTracker.
     *
     * @param input      for enter information.
     * @param connection to database.
     */
    public MenuTracker(Input input, Connection connection) {
        this.input = input;
        this.connection = connection;
    }

    /**
     * menu of actions.
     */
    private ArrayList<UserAction> actions = new ArrayList<>();


    /**
     * fill menu of actions.
     */
    public void fillActions() {
        this.actions.add(this.new AddItem("Add new task."));
        this.actions.add(new EditItem("Edit task."));
        this.actions.add(new DeleteItem("Delete task."));
        this.actions.add(new ShowItems("Show all tasks."));
        this.actions.add(new FindItemByName("Find task by name."));
        this.actions.add(new FindItemById("Find task by ID."));
        this.actions.add(new AddComment("Add comment to task."));
        this.actions.add(new ShowComments("Show all comments of task."));
    }

    /**
     * method for getting array of actions.
     *
     * @param action to add.
     */
    public void addAction(UserAction action) {
        this.actions.add(action);
    }

    /**
     * method for getting array of actions.
     *
     * @return actions - int[].
     */
    public ArrayList<Integer> getRangeActions() {
        ArrayList<Integer> rangeActions = new ArrayList<>();
        for (int i = 0; i < actions.size(); i++) {
            rangeActions.add(actions.get(i).key());
        }
        return rangeActions;
    }

    /**
     * method for execute action.
     *
     * @param key for select action.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.connection);
    }

    /**
     * method for show user actions.
     */
    public void show() {
        System.out.println("Choose action from 0 to 8");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Inner class to add task.
     */
    private class AddItem extends BaseAction {

        /**
         * constructor for AddItem.
         *
         * @param name of object's.
         */
        AddItem(String name) {
            super(name);
        }

        /**
         * key for choose.
         *
         * @return int key.
         */
        public int key() {
            return 0;
        }

        /**
         * method for execute adding.
         *
         * @param input      for enter information.
         * @param connection to database.
         */
        public void execute(Input input, Connection connection) {
            String taskName = input.ask("Please enter the task's name: ");
            String desc = input.ask("Please enter the task's desc: ");
            try {
                PreparedStatement statement = connection.
                        prepareStatement("INSERT INTO tasks (name, description) VALUES (?, ?)");
                statement.setString(1, taskName);
                statement.setString(2, desc);
                statement.executeUpdate();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT MAX(task_id) FROM tasks");
                if (rs.next()) {
                    System.out.println("Task successfully added to database. Your task id is: "
                            + rs.getString(1));
                }
            } catch (SQLException e) {
                for (Throwable t : e) {
                    t.printStackTrace();
                }
            }
        }
    }

    /**
     * Inner static class to edit task.
     */
    private static class EditItem extends BaseAction {

        /**
         * constructor for EditItem.
         *
         * @param name of object's.
         */
        EditItem(String name) {
            super(name);
        }

        /**
         * key for choose.
         *
         * @return int key.
         */
        public int key() {
            return 1;
        }

        /**
         * method for execute editing.
         *
         * @param input      for enter information.
         * @param connection to database.
         */
        public void execute(Input input, Connection connection) {
            String taskID = input.ask("Enter ID of edit task - ");
            try {
                Integer id = Integer.parseInt(taskID);
                PreparedStatement preparedStatement = connection.
                        prepareStatement("SELECT task_id FROM tasks WHERE task_id = ?");
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    String name = input.ask("Change name of task - ");
                    String desc = input.ask("Change description of task - ");
                    preparedStatement = connection.
                            prepareStatement("UPDATE tasks SET name = ?, description = ? WHERE task_id = ?");
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, desc);
                    preparedStatement.setInt(3, id);
                    preparedStatement.executeUpdate();
                    System.out.println("Task " + id + " updated");
                } else {
                    System.out.println("Invalid ID");
                }
            } catch (SQLException e) {
                for (Throwable t : e) {
                    t.printStackTrace();
                }
            } catch (NumberFormatException e) {
                System.out.println("Input Integer number for task ID");
            }
        }
    }

    /**
     * Inner class to show all tasks.
     */
    private class ShowItems extends BaseAction {

        /**
         * constructor for ShowItems.
         *
         * @param name of object's.
         */
        ShowItems(String name) {
            super(name);
        }

        /**
         * key for choose.
         *
         * @return int key.
         */
        public int key() {
            return 3;
        }

        /**
         * method for execute showing.
         *
         * @param input      for enter information.
         * @param connection to database.
         */
        public void execute(Input input, Connection connection) {
            System.out.println("The List of tasks: ");
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT task_id, name FROM tasks ORDER BY task_id");
                boolean firstRow = true;
                while (rs.next()) {
                    if (firstRow) {
                        System.out.println("Task_ID     | Task_Name");
                        System.out.println("-------------------------------------");
                        firstRow = false;
                    }
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    System.out.println(String.format("%-12d| %s", id, name));
                }
            } catch (SQLException e) {
                for (Throwable t : e) {
                    t.printStackTrace();
                }
            }
        }
    }

    /**
     * Inner class to find tasks by name.
     */
    private class FindItemByName extends BaseAction {
        /**
         * constructor for FindItemByName.
         *
         * @param name of object's.
         */
        FindItemByName(String name) {
            super(name);
        }

        /**
         * key for choose.
         *
         * @return int key.
         */
        public int key() {
            return 4;
        }

        /**
         * method for execute finding by name.
         *
         * @param input      for enter information.
         * @param connection to database.
         */
        public void execute(Input input, Connection connection) {
            String name = input.ask("Enter name of task to search - ");
            try {
                PreparedStatement preparedStatement = connection.
                        prepareStatement("SELECT task_id, description FROM tasks WHERE name = ? ORDER BY task_id");
                preparedStatement.setString(1, name);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.isBeforeFirst()) {
                    System.out.println("All tasks with name " + name + " :");
                    System.out.println("Task_ID     | Description");
                    System.out.println("-------------------------------------");
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String desc = rs.getString(2);
                        System.out.println(String.format("%-12d| %s", id, desc));
                    }
                } else {
                    System.out.println("This name is not exist at database");
                }
            } catch (SQLException e) {
                for (Throwable t : e) {
                    t.printStackTrace();
                }
            }
        }
    }

    /**
     * Inner class to find tasks by ID.
     */
    private class FindItemById extends BaseAction {
        /**
         * constructor for FindItemById.
         *
         * @param name of object's.
         */
        FindItemById(String name) {
            super(name);
        }

        /**
         * key for choose.
         *
         * @return int key.
         */
        public int key() {
            return 5;
        }

        /**
         * method for execute finding by ID.
         *
         * @param input      for enter information.
         * @param connection to database.
         */
        public void execute(Input input, Connection connection) {
            String taskID = input.ask("Enter ID of task to search - ");
            try {
                int id = Integer.parseInt(taskID);
                PreparedStatement preparedStatement = connection.
                        prepareStatement("SELECT name, description FROM tasks WHERE task_id = ?");
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.isBeforeFirst()) {
                    System.out.println("All tasks with ID " + id + " :");
                    System.out.println("Task_Name           | Description");
                    System.out.println("-------------------------------------");
                    while (rs.next()) {
                        String name = rs.getString(1);
                        String desc = rs.getString(2);
                        System.out.println(String.format("%-20s| %s", name, desc));
                    }
                } else {
                    System.out.println("This ID is not exist at database");
                }
            } catch (SQLException e) {
                for (Throwable t : e) {
                    t.printStackTrace();
                }
            } catch (NumberFormatException e) {
                System.out.println("Input Integer number for task ID");
            }
        }
    }

    /**
     * Inner class to add comment to task.
     */
    private class AddComment extends BaseAction {
        /**
         * constructor for AddComment.
         *
         * @param name of object's.
         */
        AddComment(String name) {
            super(name);
        }

        /**
         * key for choose.
         *
         * @return int key.
         */
        public int key() {
            return 6;
        }

        /**
         * method for execute adding comment.
         *
         * @param input      for enter information.
         * @param connection to database.
         */
        public void execute(Input input, Connection connection) {
            String taskID = input.ask("Enter id of task to comment - ");
            try {
                int id = Integer.parseInt(taskID);
                PreparedStatement preparedStatement = connection.
                        prepareStatement("SELECT task_id FROM tasks WHERE task_id = ?");
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    String comment = input.ask("Enter your comment - ");
                    preparedStatement = connection.
                            prepareStatement("INSERT  INTO comments (comment, task_id) VALUES (?, ?)");
                    preparedStatement.setString(1, comment);
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                    System.out.println("Comment added to task " + id);
                } else {
                    System.out.println("Invalid ID");
                }
            } catch (SQLException e) {
                for (Throwable t : e) {
                    t.printStackTrace();
                }
            } catch (NumberFormatException e) {
                System.out.println("Input Integer number for task ID");
            }
        }
    }

    /**
     * Inner class to show comments of task.
     */
    private class ShowComments extends BaseAction {
        /**
         * constructor for ShowComments.
         *
         * @param name of object's.
         */
        ShowComments(String name) {
            super(name);
        }

        /**
         * key for choose.
         *
         * @return int key.
         */
        public int key() {
            return 7;
        }

        /**
         * method for execute showing comments.
         *
         * @param input      for enter information.
         * @param connection to database.
         */
        public void execute(Input input, Connection connection) {
            String taskID = input.ask("Enter id of task to show comments - ");
            try {
                int id = Integer.parseInt(taskID);
                PreparedStatement preparedStatement = connection.
                        prepareStatement("SELECT task_id FROM tasks WHERE task_id = ?");
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    preparedStatement = connection.
                            prepareStatement("SELECT comment FROM comments WHERE task_id = ?");
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.isBeforeFirst()) {
                        System.out.println("All comments for Task " + taskID);
                        System.out.println("-------------------------------------------------------");
                        while (resultSet.next()) {
                            System.out.println(resultSet.getString(1));
                        }
                        System.out.println("-------------------------------------------------------");
                    } else {
                        System.out.println("Task " + id + " has no comments");
                    }
                } else {
                    System.out.println("Invalid ID");
                }
            } catch (SQLException e) {
                for (Throwable t : e) {
                    t.printStackTrace();
                }
            } catch (NumberFormatException e) {
                System.out.println("Input Integer number for task ID");
            }
        }
    }
}

/**
 * Outer class to delete task.
 */
class DeleteItem extends BaseAction {
    /**
     * constructor for DeleteItem.
     *
     * @param name of object's.
     */
    DeleteItem(String name) {
        super(name);
    }

    /**
     * key for choose.
     *
     * @return int key.
     */
    public int key() {
        return 2;
    }

    /**
     * method for execute deleting task.
     *
     * @param input      for enter information.
     * @param connection to database.
     */
    public void execute(Input input, Connection connection) {
        String taskID = input.ask("Enter id of task to delete - ");
        try {
            int id = Integer.parseInt(taskID);
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT task_id FROM tasks WHERE task_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                preparedStatement = connection.
                        prepareStatement("DELETE FROM comments WHERE task_id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("DELETE FROM tasks WHERE task_id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Task " + taskID + " and all comments to this task are deleted");
            } else {
                System.out.println("Invalid ID");
            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        } catch (NumberFormatException e) {
            System.out.println("Input Integer number for task ID");
        }
    }
}
