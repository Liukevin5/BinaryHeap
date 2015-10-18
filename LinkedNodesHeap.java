package Kevin_Ruoxi_Liu;

import java.util.ArrayList;

public class LinkedNodesHeap<V extends Comparable<V>> implements Heap<V> {
	private Node root = null;
	private int size = 0;
	

	/*
	 * Methods used to add
	 */
	// ///////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////
	public void add(V value) {
		int tempSize = size + 1;
		Node currentNode = root;
		if (size == 0 || root == null) {
			root = new Node(value, null);
			currentNode = root;
			//System.out.println(currentNode.value + " is added at root");
		} else {
			boolean left = true;
			for (int i = 0; i < nextDepth() - 1; i++) {

				if ((((Math.pow(2, nextDepth() + 1 - i) - 1)) - (tempSize)) >= Math
						.pow(2, nextDepth() - i - 1)) {
					currentNode = currentNode.leftChild;
					left = true;
				//	System.out.println("moved left");
				} else {
					currentNode = currentNode.rightChild;
					left = false;
				//	System.out.println("moved right");
				}

				if (left) {
					tempSize = (int) (tempSize - Math.pow(2, nextDepth() - i
							- 1));
				} else {
					tempSize = (int) (tempSize - Math.pow(2, nextDepth() - i));
				}
			}

			if (currentNode.leftChild == null) {
				currentNode.leftChild = new Node(value, currentNode);
				//System.out.println(currentNode.leftChild.value
				//		+ " added to left. parent is " + currentNode.value);
				siftUp(currentNode.leftChild, "add");

			} else if (currentNode.leftChild != null
					&& currentNode.rightChild == null) {
				currentNode.rightChild = new Node(value, currentNode);
//
			//	System.out.println(currentNode.rightChild.value
				//		+ " added to right. parent is " + currentNode.value);
				siftUp(currentNode.rightChild, "add");

			}

		}
		size++;
	}

	// /////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////
	
	public V remove() {
		V removedValue = root.value;

		Node currentNode = root;
		int tempSize = size;
		boolean left = true;

		if (size <= 1) {

			root = null;
			if (size > 0) {
				size--;
			}
			return removedValue;
		} else if (size == 2) {
			root.leftChild.parent = null;
			root = root.leftChild;
			size--;
			return removedValue;
		}
		for (int i = 0; i < depth(); i++) {

			if ((((Math.pow(2, depth() + 1 - i) - 1)) - (tempSize)) >= Math
					.pow(2, depth() - i - 1)) {

				currentNode = currentNode.leftChild;

				left = true;
			//	System.out.println("moved left");
			} else {

				currentNode = currentNode.rightChild;
				left = false;
			//System.out.println("moved right");
			}

			if (left) {
				tempSize = (int) (tempSize - Math.pow(2, depth() - i - 1));
			} else {
				tempSize = (int) (tempSize - Math.pow(2, depth() - i));
			}

		}
		root.value = currentNode.value;
		if (left && size > 1) {
			currentNode.parent.leftChild = null;
		} else if (!left && size > 2) {
			currentNode.parent.rightChild = null;
		}
	//	System.out.println("root is now " + root.value);
		siftDown(root);

		if (size > 0) {

			size--;
		}

		return removedValue;
	}

	public V peek() {
		return root.value;
	}

	/*
	 * here lies the array methods of the heap.
	 */
	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////
	public void fromArray(V[] array) {
		V[] valueArray = array;
		for (int i = 0; i < valueArray.length; i++) {
			add(valueArray[i]);
		}

	}

	public V[] getSortedContents(V[] array) {
		V[] sortedArray = (V[]) java.lang.reflect.Array.newInstance(array
				.getClass().getComponentType(), size);
		sortedArray = toArray(array);
		
		if(size == 1){
			return sortedArray;
		}
		for (int i = 0; i < (size)  ; i++) {
			swap(sortedArray, 0, sortedArray.length - 1 - i);
			arraySiftDown(sortedArray, i + 1);
		}
		swap(sortedArray, 0, 1);

		return sortedArray;
	}

	private void swap(V[] array, int i1, int i2) {
		V[] valueArray = array;
		V temp = valueArray[i1];
		valueArray[i1] = valueArray[i2];
		valueArray[i2] = temp;
	}

	private void arraySiftDown(V[] array, int counter) {
		int pointer = 0;
		int left;
		int right;
		
		for (int i = 0; i < (Math.floor(Math.log(size - counter) / Math.log(2))); i++) {
			left = 2 * pointer + 1;
			right = 2 * pointer + 2;
			if (left >= array.length - counter) {
				return;
			}
			if ((right >= array.length - counter) && (left < array.length - counter)) {
				if (array[pointer].compareTo(array[left]) > 0) {
					swap(array, pointer, left);
					pointer = left;
				}

			}

			if (left < array.length - counter && right < array.length - counter) {
				if(array[right].compareTo(array[left]) > 0){
					if(array[pointer].compareTo(array[right]) < 0){
						swap(array, pointer, right);
						pointer = right;
					}
				} else{
					if(array[pointer].compareTo(array[left]) < 0 ){
						swap(array, pointer, left);
						pointer = left;
					}
				}
			}
		}
	}

	public V[] toArray(V[] array) {
		int counter = 0;
		Node tempNode;
		V[] valuesArray = (V[]) java.lang.reflect.Array.newInstance(array
				.getClass().getComponentType(), size);
		ArrayList queue = new ArrayList<Node>();
		queue.add(root);

		while (!queue.isEmpty()) {
			tempNode = (Node) queue.remove(0);
			valuesArray[counter] = tempNode.value;
			if (tempNode.leftChild != null) {
				queue.add(tempNode.leftChild);
			}
			if (tempNode.rightChild != null) {
				queue.add(tempNode.rightChild);
			}
			counter++;
		}

		return valuesArray;
	}

	// /////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////

	/*
	 * These are methods used to shift nodes around.
	 */
	// //////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////
	private void siftUp(Node node, String string) {
		Node n = node;
		if (n.parent == null) {
			return;
		}

		for (int i = 0; i < nextDepth(); i++) {

			if (n.value.compareTo(n.parent.value) > 0) {
				swap(n, n.parent);
				n = n.parent;
			} else {
				break;
			}
		}

	}

	private void siftDown(Node node) {
		Node n = node;
		for (int i = 0; i < depth(); i++) {

			if (n.leftChild != null && n.rightChild != null) {
				if (n.leftChild.value.compareTo(n.value) > 0
						|| n.rightChild.value.compareTo(n.value) > 0) {

					if (n.leftChild.value.compareTo(n.rightChild.value) > 0) {
						swap(n, n.leftChild);
						n = n.leftChild;
					} else {
						swap(n, n.rightChild);
						n = n.rightChild;
					}

				}
			} else if (n.leftChild != null && n.rightChild == null) {
				if (n.leftChild.value.compareTo(n.value) > 0) {
					swap(n, n.leftChild);
					n = n.leftChild;
				}
			} else if (n.rightChild != null && n.leftChild == null) {

				if (n.rightChild.value.compareTo(n.value) > 0) {
					swap(n, n.rightChild);
					n = n.rightChild;
				}
			}
		}

	}

	private void swap(Node node1, Node node2) {
		
//		System.out.println(node1.value + " is swapped with " + node2.value);
		V temp = node1.value;
		node1.value = node2.value;
		node2.value = temp;
	}

	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////
	private int depth() {
		int depth = (int) Math.floor(((double) Math.log(size) / (double) Math
				.log(2)));
		return depth;
	}

	private int nextDepth() {
		int depth = (int) Math
				.floor(((double) Math.log(size + 1) / (double) Math.log(2)));
		return depth;
	}

	private class Node {

		protected V value;
		protected Node rightChild;
		protected Node leftChild;
		protected Node parent;

		Node(V v, Node parent) {

			value = v;
			this.parent = parent;
		}
	}

}