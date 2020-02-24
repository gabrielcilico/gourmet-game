package controller;

import domain.ActionAnswer;
import domain.Node;

import static domain.ActionAnswer.*;

public class GameController {

    private Node root;
    private AskController askController;

    public GameController() {
        this.createRoot();
        this.askController = new AskController();
    }


    public void createRoot() {
        this.root = new Node(
                "massa",
                new Node("Bolo de chocolate"),
                new Node("Lasanha")
        );
    }


    public void play() {

        ActionAnswer exitControl;

        do {
            exitControl = askController.showGameStartMessage();
            if (isNotClosed(exitControl)) {
                exitControl = askQuestion(root);
            }
        } while (isNotClosed(exitControl));
    }

    private boolean isNotClosed(ActionAnswer answer) {
        return answer != CLOSED;
    }

    public void insert(Node element) {

        String tmp = element.getValue();

        String food = askController.getFoodMessage();
        String toQuestion = askController.getDescriptionMessage(tmp, food);

        element.setValue(toQuestion);
        element.setRight(new Node(food));
        element.setLeft(new Node(tmp));

    }

    public ActionAnswer askQuestion(Node element) {

        ActionAnswer answer = askController.showQuestionCorrectAnswer(element);

        return checkAnswer(element, answer);
    }

    private ActionAnswer checkAnswer(Node element, ActionAnswer answer) {
        if (answer == CLOSED) {
            return CLOSED;
        }

        if (answer == YES) {
            if (element.hasRight()) {
                return askQuestion(element.getRight());
            }
            askController.showSuccessMessage();
        }

        if (answer == NO) {
            if (element.hasLeft()) {
                return askQuestion(element.getLeft());
            }
            insert(element);
        }
        return CONTINUE;
    }
}
