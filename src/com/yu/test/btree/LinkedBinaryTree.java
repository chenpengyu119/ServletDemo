package com.yu.test.btree;

public class LinkedBinaryTree implements BinaryTree {
    /**
     * 根节点
     */
    private Node root;

    public LinkedBinaryTree() {
    }

    public LinkedBinaryTree(Node root) {
        this.root = root;
    }

    public int getSum(){
        return this.getSum(root);
    }

    private int getSum(Node root) {
        if(root==null){
            return 0;
        }
        int sum = (Integer)root.data;
        sum+=getSum(root.leftNode);
        sum+= getSum(root.rightNode);
        return sum;

    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public int size() {
        return this.size(root) ;
    }

    private int size(Node root) {
        if (root==null){
            return 0;
        }
        int leftSize = size(root.leftNode);
        int rightSize = size(root.rightNode);
        return leftSize+rightSize+1;
    }

    // 高度为左子树和右子树高度中大的那个加1
    @Override
    public int getHeight() {
        return this.getHeight(root);
    }

    private int getHeight(Node root) {
        if (root==null){
            // 空节点没有高度
            return 0;
        }
        return getHeight(root.leftNode)>getHeight(root.rightNode)?getHeight(root.leftNode)+1:getHeight(root.rightNode)+1;
    }

    @Override
    public Node findKey(int value) {
        return null;
    }

    /**
     * 先序遍历
     */
    @Override
    public void preOrderTraverse() {
        System.out.println("先序遍历");
        this.preOrderTraverse(root);
    }

    private void preOrderTraverse(Node root) {
        if (root==null){
            return;
        }
        // 输出根
        System.out.print(root.data+"  ");
        // 遍历左子树
        preOrderTraverse(root.leftNode);
        // 遍历右子树
        preOrderTraverse(root.rightNode);
    }

    @Override
    public void inOrderTraverse() {

    }

    @Override
    public void postOrderTraverse() {

    }

    @Override
    public void postOrderTraverse(Node node) {

    }

    @Override
    public void inOrderByStack() {

    }

    @Override
    public void preOrderByStack() {

    }

    @Override
    public void postOrderByStack() {

    }

    @Override
    public void levelOrderByStack() {

    }
}
