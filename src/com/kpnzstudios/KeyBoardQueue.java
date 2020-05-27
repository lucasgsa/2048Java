package com.kpnzstudios;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyBoardQueue {
	
	private ArrayList<String> queue;

	public KeyBoardQueue() {
		queue = new ArrayList<String>();
	}
	
	public boolean hasRight() {
		if (queue.contains("RIGHT")) {
			queue.remove("RIGHT");
			return true;
		}
		return false;
	}
	
	public boolean hasLeft() {
		if (queue.contains("LEFT")) {
			queue.remove("LEFT");
			return true;
		}
		return false;
	}
	
	public boolean hasUp() {
		if (queue.contains("UP")) {
			queue.remove("UP");
			return true;
		}
		return false;
	}
	
	public boolean hasDown() {
		if (queue.contains("DOWN")) {
			queue.remove("DOWN");
			return true;
		}
		return false;
	}
	
	public boolean hasZ() {
		if (queue.contains("Z")) {
			queue.remove("Z");
			return true;
		}
		return false;
	}
	
	public boolean hasR() {
		if (queue.contains("R")) {
			queue.remove("R");
			return true;
		}
		return false;
	}
	
	public void pressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_RIGHT && !queue.contains("RIGHT")) {
			queue.add("RIGHT");
		}
		if (keyCode == KeyEvent.VK_LEFT && !queue.contains("LEFT")) {
			queue.add("LEFT");
		}
		if (keyCode == KeyEvent.VK_UP && !queue.contains("UP")) {
			queue.add("UP");
		}
		if (keyCode == KeyEvent.VK_DOWN && !queue.contains("DOWN")) {
			queue.add("DOWN");
		}
		if (keyCode == KeyEvent.VK_Z && !queue.contains("Z")) {
			queue.add("Z");
		}
		if (keyCode == KeyEvent.VK_R && !queue.contains("R")) {
			queue.add("R");
		}
		System.out.println(queue.toString());
	}
}
