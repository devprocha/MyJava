package ds.heap;

public class MinBinaryHeap extends BinaryHeap {

	@Override
	public boolean compare(int parentData, int childData) {
		return (parentData <= childData ? true : false);
	}
}
