package concurrency;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class tableSchema {
	public class Data {
        private SimpleStringProperty process;
        private SimpleIntegerProperty cResource0;
        private SimpleIntegerProperty cResource1;
        private SimpleIntegerProperty cResource2;
        private SimpleIntegerProperty cResource3;
        private SimpleIntegerProperty cResource4;
        private SimpleIntegerProperty cResource5;
        private SimpleIntegerProperty cResource6;
        private SimpleIntegerProperty cResource7;
        private SimpleIntegerProperty cResource8;
        private SimpleIntegerProperty cResource9;
        
        public Data(String fName, Integer[] arr) {
        	this.process = new SimpleStringProperty(fName);
        	this.cResource0 = new SimpleIntegerProperty(arr[0]);
        	this.cResource1 = new SimpleIntegerProperty(arr[1]);
        	this.cResource2 = new SimpleIntegerProperty(arr[2]);
        	this.cResource3 = new SimpleIntegerProperty(arr[3]);
        	this.cResource4 = new SimpleIntegerProperty(arr[4]);
        	this.cResource5 = new SimpleIntegerProperty(arr[5]);
        	this.cResource6 = new SimpleIntegerProperty(arr[6]);
        	this.cResource7 = new SimpleIntegerProperty(arr[7]);
        	this.cResource8 = new SimpleIntegerProperty(arr[8]);
        	this.cResource9 = new SimpleIntegerProperty(arr[9]);
        }
 
        public String getProcess() {
            return process.get();
        }
        public void setProcess(String fName) {
            process.set(fName);
        }
        public Integer getCResource0() {
        	return cResource0.get();
        }
        public void setCResource0(Integer arr) {
        	cResource0.set(arr);
        }
        public Integer getCResource1() {
        	return cResource1.get();
        }
        public void setCResource1(Integer arr) {
        	cResource1.set(arr);
        }
        public Integer getCResource2() {
        	return cResource2.get();
        }
        public void setCResource2(Integer arr) {
        	cResource2.set(arr);
        }
        public Integer getCResource3() {
        	return cResource3.get();
        }
        public void setCResource3(Integer arr) {
        	cResource3.set(arr);
        }
        public Integer getCResource4() {
        	return cResource4.get();
        }
        public void setCResource4(Integer arr) {
        	cResource4.set(arr);
        }
        public Integer getCResource5() {
        	return cResource5.get();
        }
        public void setCResource5(Integer arr) {
        	cResource5.set(arr);
        }
        public Integer getCResource6() {
        	return cResource6.get();
        }
        public void setCResource6(Integer arr) {
        	cResource6.set(arr);
        }
        public Integer getCResource7() {
        	return cResource7.get();
        }
        public void setCResource7(Integer arr) {
        	cResource7.set(arr);
        }
        public Integer getCResource8() {
        	return cResource8.get();
        }
        public void setCResource8(Integer arr) {
        	cResource8.set(arr);
        }
        public Integer getCResource9() {
        	return cResource9.get();
        }
        public void setCResource9(Integer arr) {
        	cResource9.set(arr);
        }        
    }
}