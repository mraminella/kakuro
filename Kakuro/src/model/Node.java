package model;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private int i;
	private int j;
	private int value;
	private List<Node> children;
	private Node father;
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
	public Node(int i, int j) {
		super();
		this.i = i;
		this.j = j;
		children = new ArrayList<Node>();
	}
	public Node getFather() {
		return father;
	}
	public void setFather(Node father) {
		this.father = father;
	}
}
