package model;

public class TreeNode {

    private TreeNode left;
    private TreeNode right;
    private Game value;

    public TreeNode(Game value){
        this.value = value;
    }

    public Game getValue(){
        return this.value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void setValue(Game value) {
        this.value = value;
    }
}
