
public class BellmanFordShortestPathGraphAlgorithm extends ShortestPathGraphAbsAlgorithm {

	protected void execute (int[][][] povezave, int[] d){
		//vedno testiramo za vozlisce 1, da imamo za vse algoritme enako
		int v=1;
		
		int stVozlisc=povezave.length;
		for (int i=0; i<stVozlisc; i++){
			d[i]=Integer.MAX_VALUE;
		}
		//razdalja od zelenega vozlisca do sebe je 0
		d[v-1]=0;
		for (int j=0; j<stVozlisc;j++){
			for(int k=0; k<povezave[j].length;k++){
				//v 1 je w, v 0 je začetek
				int temp=d[j] + povezave[j][k][1];
				if (d[j]==Integer.MAX_VALUE){
					temp=Integer.MAX_VALUE;
				}
				if (temp < d[povezave[j][k][0]]) {
					d[povezave[j][k][0]] = temp;
				}
			}
		}
		/*
		//preverjanje negativnih ciklov
        for (int i = 0; i < stVozlisc; i++){
        	for (int j = 0; j < povezave[i].length; j++){
        		if (d[i] + povezave[i][j][1] < d[povezave[i][j][0]]){
        			System.out.println("Graf ima negativen cikel"); 
        		}
        	}
        }
        */		
	} 
}


