package psm7;

public class PartOfThePlate {
//public double width,heigth;
public double temperature;
boolean isNeeded =true;
int height,width;
boolean needToCalculate=false;
public PartOfThePlate(double temperature) {
	this.temperature = temperature;
}



@Override
public String toString() {
	return "PartOfThePlate [temperature=" + temperature + ", isNeeded=" + isNeeded + ", height=" + height + ", width="
			+ width + ", needToCalculate=" + needToCalculate + "]";
}



public PartOfThePlate() {
	temperature=0;
}
public PartOfThePlate(boolean isNeeded) {
	this.isNeeded=isNeeded;
	temperature=0;
}

public PartOfThePlate(double temperature, boolean isNeeded,boolean needToCalculate) {
	this.temperature = temperature;
	this.isNeeded = isNeeded;
	this.needToCalculate=needToCalculate;
}
	
public PartOfThePlate(double temperature, boolean isNeeded,boolean needToCalculate,int h, int w) {
	this.temperature = temperature;
	this.isNeeded = isNeeded;
	this.needToCalculate=needToCalculate;
	height=h;width=w;
}
}
