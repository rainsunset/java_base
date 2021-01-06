package com.ligw.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 二叉树
 * @Author: Amo
 * @CreateDate: 2020/9/9
 */
public class BinaryTreeNode {
    /**
     * 特征：
     * 每个节点最多有两个子节点
     * <p>
     * 特殊二叉树：二叉搜索树(binary search tree)
     * 二叉搜索树要求：若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
     * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
     * 它的左、右子树也分别为二叉排序树
     */
    private int data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(int data) {
        this.data = data;
    }

    public BinaryTreeNode(int data, BinaryTreeNode left, BinaryTreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * 前序遍历输出列表
     * 根->左->右
     *
     * @param binaryTreeNode the binary tree node
     * @return the list
     * @author : ligangwei / 2020-09-09
     */
    public static List<Integer> preOrder(BinaryTreeNode binaryTreeNode) {
        List<Integer> preOrderList = new ArrayList<Integer>();
        if (null == binaryTreeNode) {
            return preOrderList;
        }
        preOrderList.add(binaryTreeNode.getData());
        preOrderList.addAll(preOrder(binaryTreeNode.getLeft()));
        preOrderList.addAll(preOrder(binaryTreeNode.getRight()));
        return preOrderList;
    }
    // TODO 中序遍历 左->根->右
    // TODO 后续遍历 左->右->根

    public static void main(String[] args) {
        // test go through
        /**
         *             1
         *      2              3
         *   4    5         6     7
         *      8   9
         *           10
         */
        BinaryTreeNode node10 = new BinaryTreeNode(10, null, null);
        BinaryTreeNode node8 = new BinaryTreeNode(8, null, null);
        BinaryTreeNode node9 = new BinaryTreeNode(9, null, node10);
        BinaryTreeNode node4 = new BinaryTreeNode(4, null, null);
        BinaryTreeNode node5 = new BinaryTreeNode(5, node8, node9);
        BinaryTreeNode node6 = new BinaryTreeNode(6, null, null);
        BinaryTreeNode node7 = new BinaryTreeNode(7, null, null);
        BinaryTreeNode node2 = new BinaryTreeNode(2, node4, node5);
        BinaryTreeNode node3 = new BinaryTreeNode(3, node6, node7);
        BinaryTreeNode node1 = new BinaryTreeNode(1, node2, node3);

        System.out.println(preOrder(node1));

    }
}
