package ds.heap;

public class MaxBinaryHeap extends BinaryHeap {

	@Override
	public boolean compare(int parentData, int childData) {
		return (parentData >= childData ? true : false);
	}
}
