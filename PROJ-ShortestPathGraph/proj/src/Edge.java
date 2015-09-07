
/*Razred za predstavitev povezave grafa*/

public class Edge {
	private int k; //konec
	private double w; //utez povezava
	
	public Edge(int b, double c){
		k=b;
		w=c;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}
	public String toString(){
		return this.k+" "+this.w;
	}
	
}
