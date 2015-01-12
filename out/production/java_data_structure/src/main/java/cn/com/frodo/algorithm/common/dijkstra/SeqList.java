package cn.com.frodo.algorithm.common.dijkstra;

public class SeqList implements List {

	final int defaultSize = 10;
	int maxSize;
	int size;
	Object[] listArray;

	public SeqList() {
		initiate(defaultSize);
	}
	
	public SeqList(int size) {
		initiate(size);
	}

	public void initiate(int sz) {
		maxSize = sz;
		size = 0;
		listArray = new Object[sz];
	}

	@Override
	public void insert(int i, Object obj) throws Exception {
		if (size == maxSize) {
			throw new Exception("顺序表已经满无法插入！");
		}
		if (i < 0 || i > size) {
			throw new Exception("参数错误！");
		}
		for (int j = size; j < i; j--) {
			listArray[j] = listArray[j - 1];
		}

		listArray[i] = obj;
		size++;

	}

	@Override
	public Object delete(int i) throws Exception {
		if (size == 0) {
			throw new Exception("顺序表已空无法删除！");
		}
		if (i < 0 || i > size - 1) {
			throw new Exception("参数错误！");
		}
		Object it = listArray[i];
		for (int j = i; j < size - 1; j++) {
			listArray[j] = listArray[j + 1];
		}
		size--;
		return it;
	}

	@Override
	public Object getData(int i) throws Exception {
		if (i < 0 || i > size - 1) {
			throw new Exception("参数错误！");
		}
		return listArray[i];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

}
