package controller;

import domain.Node;

import javax.swing.*;

public class GameController implements GameControllerInterface {

    public static final int YES = 0;
    private static Node root;

    public GameController() {
        this.init();
    }

    @Override
    public void init() {
        if (this.root == null) {
            this.root = new Node(
                    "massa",
                    new Node("Bolo de chocolate"),
                    new Node("Lasanha")
            );
        }
    }

    @Override
    public void play() {
        boolean exitControl = true;
        int notExit;
        Object[] option = {"OK"};

        do {
            notExit = JOptionPane.showOptionDialog(
                        null,
                        "Pense em um prato que gosta",
                        "JogoGourmet",
                        JOptionPane.OK_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        option,
                        option[0]
                    );
            if (notExit == JOptionPane.CLOSED_OPTION) {
                exitControl = false;
            } else {
                exitControl = askQuestion(root);
            }
        } while (exitControl);
    }

    @Override
    public void insert(Node element) {

        String tmp = element.getValue();

        String food = JOptionPane.showInputDialog(
                null, "Qual prato você pensou?",
                "Desisto",
                JOptionPane.QUESTION_MESSAGE
        );
        String toQuestion = JOptionPane.showInputDialog(
                String.format("%s é ______, mas %s não.",
                        food,
                        tmp)
        );

        element.setValue(toQuestion);
        element.setRight(new Node(food));
        element.setLeft(new Node(tmp));

    }

    @Override
    public boolean askQuestion(Node element) {

        Object[] options = { "SIM", "NÃO" };
         int awnser = JOptionPane.showOptionDialog(
                null,
                String.format("O prato que você pensou é %s ?", element.getValue()),
                "Confirm",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (awnser == JOptionPane.CLOSED_OPTION) {
            return false;
        }

        if (awnser == YES) {
            if (element.getRight() == null) {
                JOptionPane.showMessageDialog(null, "Acertei de novo!");
            } else {
                return askQuestion(element.getRight());
            }
        } else {
            if (element.getLeft() == null) {
                insert(element);
            } else {
                return askQuestion(element.getLeft());
            }
        }

        return true;
    }

    @Override
    public Node getRoot() {
        return this.root;
    }

    @Override
    public void setRoot(Node element) {
        this.root = element;
    }
}
