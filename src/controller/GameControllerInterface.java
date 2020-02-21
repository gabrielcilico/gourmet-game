package controller;

import domain.Node;

public interface GameControllerInterface {

    public void init();

    public void play();

    public void insert(Node element);

    public boolean askQuestion(Node element);

    public Node getRoot();
    
    public void setRoot(Node element);
}
