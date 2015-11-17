package examples;

public class MyPriorityQueue<K extends Comparable<? super K>, E> implements
	PriorityQueue<K, E> {

	//auxiliary class for the locators
	class PQLoc<K1 extends Comparable<? super K1>, E1> implements Locator<K1 ,E1>{
		
		Object creator = MyPriorityQueue.this;
		E1 elem;
		K1 key;
		int pos;  // index in our data stor (heap array)
		
		@Override
		public E1 element() {
			return elem;
		}

		@Override
		public K1 key() {
			return key;
		}
		
	}

	private PQLoc<K,E> [] stor = (PQLoc<K,E>[]) new PQLoc[256];
	private int size;
	
	@Override
	public Locator<K, E> showMin() {
		return stor[1];
	}

	@Override
	public Locator<K, E> removeMin() {
		
		return null;
		
	}

	@Override
	public Locator<K, E> insert(K key, E element) {
		PQLoc<K,E> loc = new PQLoc<>();
		loc.key = key;
		loc.elem = element;
		stor[++size] = loc;
		upHeap(size);
		loc.pos=size;
		return loc;
	}

	private void upHeap(int i) {
		
	}

	@Override
	public void remove(Locator<K, E> loc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void replaceKey(Locator<K, E> loc, K newKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyPriorityQueue<Integer, String> pq = new MyPriorityQueue<>();
		pq.insert(12, "hallo");
		pq.insert(1, "scheisse");
		pq.insert(3, "schnaps");
		
		System.out.println(pq.showMin().key());
		
	}

}
