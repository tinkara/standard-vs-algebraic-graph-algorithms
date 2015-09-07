/**
 *
 * @author ...
 */
public class PeerPressureClusteringGraphAlgorithm extends ClusteringGraphAbsAlgorithm {

  protected void execute(double[][][] povezave, int[] gruce){
	  int stVozlisc=povezave.length;
		int[] C = new int[stVozlisc];
		for(int i=0; i<stVozlisc;i++){
			C[i]=i;
		}
		int[] vmes=PeerPressure_recursive(povezave,C,stVozlisc);	
		for(int i=0; i<stVozlisc;i++){
			gruce[i]=vmes[i];
		}
  }
  /*
	 * vmesna metoda za PeerPresure algoritem
	 * Vhod je graf in razporeditev v clustre.
	 * Clustre oznacimo z 1 do N, na zacetku je vsako vozlisce v svojem clustru
	*/
	public static int[] PeerPressure_recursive(double[][][] povezave, int[] C, int stVozlisc){
		int enaka=0;
		int[] C_fin=new int[stVozlisc];
		for(int i=0; i<stVozlisc;i++){
			C_fin[i]=C[i];
		}
		while(enaka==0){
			enaka=1;
			for(int i=0; i<stVozlisc;i++){
				C[i]=C_fin[i];
			}
			int[][] T=new int[stVozlisc][stVozlisc];
			//nastavimo na zacetku vse glasove na 0
			for(int i=0; i<stVozlisc;i++){
				for(int j=0; j<stVozlisc; j++){
					T[i][j]=0;
				}
			}
			//po vseh povezavah poberemo glasove za clusterje
			for(int i=0; i<stVozlisc;i++){
				for (int j=0;j<povezave[i].length;j++){
					T[(int)(povezave[i][j][0])][C[i]]=(int)(T[(int)(povezave[i][j][0])][C[i]]+povezave[i][j][1]);
				}
			}
			//nastavimo clusterje tako da das tisti cluster za katerega ima vozlisce najvec glasov
			//deluje tako, da vedno izbere prvega najvecjega, ki ga najde - tako se izognemo tezavam z izenacitvijo izidov
			for(int i=0; i<stVozlisc;i++){
				int maxVote=0;
				int maxCluster=0;
				for(int j=0; j<stVozlisc;j++){
					if(T[i][j]>maxVote){
						maxVote=T[i][j];
						maxCluster=j;
					}
				}
				if(maxVote>0){
					C_fin[i]=maxCluster;
				}
				else{
					C_fin[i]=C[i];
				}
			}
			
			for(int i=0; i<stVozlisc;i++){
				if(C[i]!=C_fin[i]){
					enaka=0;
				}
			}
		}
		return C;
	}
}
