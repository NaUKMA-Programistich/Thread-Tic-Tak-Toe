package lection2.tictak;

public class Worker extends Thread{

	private final static String lock = "";

	private final int currentId;
	private final int count;
	private final Object syncObj;
	private final Object nextObj;
	private final Data data;
	
	public Worker(int currentId, Object syncObj, Object nextObj, Data data, int count){
		this.currentId = currentId;
		this.syncObj = syncObj;
		this.nextObj = nextObj;
		this.data = data;
		this.count = count;
		this.start();
	}

	public Worker(int currentId, Object syncObj, Object nextObj, Data data){
		this(currentId, syncObj, nextObj, data, 5);
	}
	
	@Override
	public void run() {
		for (int i = 0; i < count; i++) {
			try {
				waitFor();
				print();
				signalToNext();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void signalToNext() throws InterruptedException {
		synchronized (nextObj) {
			nextObj.notify();
		}
	}

	private void print() {
		switch (currentId) {
			case 1: data.Tic(); break;
			case 2: data.Tak(); break;
			case 3: data.Toe(); break;
		}
	}

	private void waitFor() throws InterruptedException {
		synchronized (syncObj) {
			syncObj.wait();
		}
	}
}
