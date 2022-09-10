package model;

import java.util.ArrayList;
import java.util.Optional;

public class BST {

    private TreeNode root;

    public BST(){}

    public String showScoreboard(){
        ArrayList<String> sorted = inOrder(root, new ArrayList<>());
        Optional<String> scoreboard = sorted.stream().reduce((a, b) -> a + b);

        return scoreboard.isPresent()
                ? scoreboard.get()
                : "No one has completed the game yet";

    }

    public ArrayList<String> inOrder(TreeNode node, ArrayList<String> scoreboard){

        if(node!= null ){
            inOrder(node.getRight(), scoreboard);
            scoreboard.add(node.getValue().getNickname() + ": " + node.getValue().getScore() + "\n");
            inOrder(node.getLeft(), scoreboard);
        }

        return scoreboard;
    }

    public void add(Game newGame){
        TreeNode newNode = new TreeNode(newGame);
        add(root, newNode);
    }

    private void add(TreeNode current, TreeNode newNode){

        if(root == null) {
            root = newNode;
            return;
        }

        if(newNode.getValue().getScore() > current.getValue().getScore()) {

            if (current.getRight() == null) {
                current.setRight(newNode);
            } else {
                add(current.getRight(), newNode);
            }

        }

        if(newNode.getValue().getScore() < current.getValue().getScore()) {

            if(current.getLeft() == null) {
                current.setLeft(newNode);
            }else {
                add(current.getLeft(), newNode);
            }

        }

    }
    
}
