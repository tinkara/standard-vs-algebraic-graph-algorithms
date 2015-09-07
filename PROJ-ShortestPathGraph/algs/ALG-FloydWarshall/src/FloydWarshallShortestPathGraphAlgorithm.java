/**
 *
 * @author ...
 */
public class FloydWarshallShortestPathGraphAlgorithm extends ShortestPathGraphAbsAlgorithm {

	protected void execute (int[][][] povezave, int[] d){
		int stVozlisc=povezave.length;
		int[][] D=new int[stVozlisc][stVozlisc];
		//Edge [][] pov=new Edge[stVozlisc][stVozlisc];
		//najprej nastavimo vse utezi na neskoncno, razen diagonale, ki je 0
		for (int i=0; i<stVozlisc;i++){
			for (int j=0; j<stVozlisc;j++){
				if(i==j){
					D[i][j]=0;
				}
				else{
					D[i][j]=Integer.MAX_VALUE;
				}
			}
		}
		//ponastavimo na prave utezi kjer imamo vrednosti v grafu
		for (int i=0; i<stVozlisc;i++){
			for(int j=0; j<povezave[i].length;j++){
				int temp[]=povezave[i][j];
				D[i][temp[0]]=(int)(temp[1]);
				//pov[i][temp.getK()]=temp;
			}
		}
		
		//izracun minimalnih poti
		for (int i=0; i<stVozlisc;i++){
			for (int j=0; j<stVozlisc;j++){
				for(int k=0; k<stVozlisc;k++){
					int temp=0;
					if(D[j][i]==Integer.MAX_VALUE || D[i][k]==Integer.MAX_VALUE){
						temp=Integer.MAX_VALUE;
					}
					else{
						temp=D[j][i]+D[i][k];
					}
					
					if(D[j][k]>temp){
						D[j][k]=temp;
						//pov[j][k]=pov[i][k];
					}
				}
				//preverjanje za negativni cikel
				if(D[j][j]<0.0){
					System.out.println("Negativen cikel");
				}
			}
		}
		int vozlisce=1;
		for(int i=0; i<stVozlisc;i++){
			d[i]=D[vozlisce-1][i];
		}
	}
}
