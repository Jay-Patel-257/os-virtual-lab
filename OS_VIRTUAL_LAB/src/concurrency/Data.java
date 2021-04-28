package concurrency;

import javafx.beans.property.SimpleStringProperty;

public class Data {
	
	private SimpleStringProperty algo;
	private SimpleStringProperty mutex;
	private SimpleStringProperty bound;
	private SimpleStringProperty progress;
	private SimpleStringProperty deadlock;
	
	Data(String algo, String mutex, String bound, String progress, String deadlock){
		this.algo = new SimpleStringProperty(algo);
		this.mutex = new SimpleStringProperty(mutex);
		this.bound = new SimpleStringProperty(bound);
		this.progress = new SimpleStringProperty(progress);
		this.deadlock = new SimpleStringProperty(deadlock);
	}
	
	public String getAlgo() {
		return algo.get();
	}
	
	public void setAlgo(String algo) {
		this.algo.set(algo);
	}
	
	public String getMutex() {
		return mutex.get();
	}
	
	public void setMutex(String mutex) {
		this.mutex.set(mutex);
	}
	
	public String getBound() {
		return bound.get();
	}
	
	public void setBound(String bound) {
		this.bound.set(bound);
	}
	
	public String getProgress() {
		return progress.get();
	}
	
	public void setProgress(String progress) {
		this.progress.set(progress);
	}
	
	public String getDeadlock() {
		return deadlock.get();
	}
	
	public void setDeadlock(String deadlock) {
		this.deadlock.set(deadlock);
	}
}
