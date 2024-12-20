package algo3prakt;

class MyClosedHashtableForInts2 {
	protected int[] tagArray;
	private int[] bucketArray;
	protected int size;
	protected int capacity;
	static final int EMPTY = 0;
	static final int OCCUPIED = 1;
	static final int DELETED = 2;

	public int size() {
		return size;
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	MyClosedHashtableForInts2(int aCapacity) {
		this.capacity = aCapacity;
		tagArray = new int[capacity];
		bucketArray = new int[capacity];
		for (int i = 0; i < capacity; i++) {
			tagArray[i] = EMPTY;
			bucketArray[i] = Integer.MIN_VALUE;
		}
		size = 0;
	}

	/* Die Hashfunktion */
	public int getInitialHashIndex(int key) {
		return Math.abs(key) % capacity;

	}

	/*
	 * sucht in der Hashtabelle nach Eintrag mit Schl"ussel key und liefert den
	 * zugeh"origen Index oder -1 zur"uck
	 */
	public int searchForKey(int key) {
		int hashIndex = getInitialHashIndex(key);
		int startIndex = hashIndex;
		while (tagArray[hashIndex] != EMPTY) {
			if (tagArray[hashIndex] == OCCUPIED && bucketArray[hashIndex] == key) {
				return hashIndex;
			}
			hashIndex = (hashIndex + 1) % capacity;
			if (hashIndex == startIndex) {
				break;
			}
		}
		return -1;
	}

	public void add(int key) {
		if (size == capacity) {
			System.out.println("Tabelle ist voll");
			return;
		}
		int hashIndex = getInitialHashIndex(key);
		while (tagArray[hashIndex] == OCCUPIED) {
			if (bucketArray[hashIndex] == key) {
				System.out.println("Schlüssel existiert bereits ");
				return;
			}
			hashIndex = (hashIndex + 1) % capacity;
		}
		bucketArray[hashIndex] = key;
		tagArray[hashIndex] = OCCUPIED;
		size++;
	}

	public boolean remove(int key) {
		int hashIndex = searchForKey(key);
		if (hashIndex != -1) {
			tagArray[hashIndex] = DELETED;
			size--;
			return true;
		}
		return false; // Key not found
	}

	public boolean find(int key) {
		if (searchForKey(key) != -1) {
			return true;
		} else {
			return false; // Key not found
		}
	}

	public void printTable() {
		for (int i = 0; i < getCapacity(); i++) {
			switch (tagArray[i]) {
			case EMPTY:
				System.out.print("[  ]");
				break;
			case OCCUPIED:
				System.out.print("(");
				System.out.print(bucketArray[i]);
				System.out.print(")");
				break;
			case DELETED:
				System.out.print("{");
				System.out.print("}");
				break;
			}
			System.out.print(" ");
		}
		System.out.println();
	}

	public static void testRoutines() {
		// write tests for the class here
		MyClosedHashtableForInts2 ht = new MyClosedHashtableForInts2(11);
		ht.add(1);
		ht.add(2);
		ht.add(3);
		ht.add(4);
		ht.add(5);
		ht.add(6);
		ht.add(7);
		ht.add(8);
		ht.add(12);
		ht.add(15);
		ht.printTable();
		ht.add(16);
		ht.printTable();
		System.out.println(ht.find(9));
		System.out.println(ht.find(3));
		ht.remove(3);
		ht.remove(6);
		ht.remove(8);
		ht.printTable();
	}

	public static void main(String[] args) {
		testRoutines();
	}
}