package controller;

import domain.ActionAnswer;
import domain.Node;

import javax.swing.*;

public class AskController {

    public ActionAnswer showGameStartMessage() {
        Object[] option = {"OK"};
        String title = "JogoGourmet";
        String message = "Pense em um prato que gosta";
        int awnser = JOptionPane.showOptionDialog(
                null,
                message,
                title,
                JOptionPane.OK_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                option,
                option[0]
        );
        return parseAnswer(awnser);
    }

    public String getDescriptionMessage(String tmp, String food) {
        String message = String.format("%s é ______, mas %s não.", food, tmp);
        return JOptionPane.showInputDialog(message);
    }

    public String getFoodMessage() {
        String message = "Qual prato você pensou?";
        String title = "Desisto";
        return JOptionPane.showInputDialog(
                null,
                message,
                title,

                JOptionPane.QUESTION_MESSAGE
        );
    }


    public void showSuccessMessage() {
        JOptionPane.showMessageDialog(null, "Acertei de novo!");
    }

    public ActionAnswer showQuestionCorrectAnswer(Node element) {
        Object[] options = {"SIM", "NÃO"};

        String message = String.format("O prato que você pensou é %s ?", element.getValue());
        String title = "Confirm";
        int answer = JOptionPane.showOptionDialog(
                null,
                message,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        return parseAnswer(answer);
    }

    private ActionAnswer parseAnswer(int answer) {
        switch (answer) {
            case JOptionPane.YES_OPTION:
                return ActionAnswer.YES;
            case JOptionPane.NO_OPTION:
                return ActionAnswer.NO;
            default:
                return ActionAnswer.CLOSED;
        }
    }
}
