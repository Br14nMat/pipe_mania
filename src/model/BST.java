package model;

public class BST {

    private TreeNode root;

    public BST(){}

    public String showScoreboard(){
        return inOrder(root, "");
    }

    public String inOrder(TreeNode node, String scoreboard){

        if(node!= null ){
            inOrder(node.getRight(), scoreboard);
            scoreboard+= node.getValue().getNickname() + ": " + node.getValue().getScore();
            inOrder(node.getLeft(), scoreboard);
            return scoreboard;
        }else {
            return scoreboard;
        }
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
