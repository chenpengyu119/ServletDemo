package com.yu.test.btree;

/**
 * 测试
 * @author pengyu
 */
public class TestBianryTree {

    public static void main(String[] args) {

        Node node5 = new Node(5);
        Node node4 = new Node(4, null, node5);
        Node node7 = new Node(7);
        Node node6 = new Node(6, null, node7);
        Node node3 = new Node(3);
        Node node2 = new Node(2, node3, node6);
        Node node1 = new Node(1, node4, node2);

        LinkedBinaryTree tree = new LinkedBinaryTree(node1);
        // System.out.println(tree.isEmpty());
        // 计算节点数
        // System.out.println(tree.size());
        // 计算高度
        // System.out.println(tree.getHeight());
        // 先序遍历
        tree.preOrderTraverse();
        System.out.println(tree.getSum());
    }
}
