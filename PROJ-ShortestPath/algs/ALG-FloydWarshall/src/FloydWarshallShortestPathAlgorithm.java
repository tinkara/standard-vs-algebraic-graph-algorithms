/**
 *
 * @author ...
 */
public class FloydWarshallShortestPathAlgorithm extends ShortestPathAbsAlgorithm {

	protected void execute (int[][] matrika, int[] d){
		//pripravimo si polje kamor shranjujemo rezultat
		int stVozlisc=matrika.length;
		//rezultat je na zacetku kar cena povezav v matriki cen
		int[][] rezultat=new int[stVozlisc][stVozlisc];
		for(int i=0; i<stVozlisc;i++){
			for(int j=0; j<stVozlisc;j++){
				rezultat[i][j]=matrika[i][j];
			}
		}
		for(int m=0; m<stVozlisc;m++){
			for(int i=0; i<stVozlisc;i++){
				for(int j=0; j<stVozlisc;j++){
					matrika[i][j]=rezultat[i][j];
				}
			}
			for(int i=0; i<stVozlisc;i++){
				for(int j=0; j<stVozlisc;j++){
					int temp=0;
					if(matrika[i][m]==Integer.MAX_VALUE || matrika[m][j]==Integer.MAX_VALUE){
						temp=Integer.MAX_VALUE;
					}
					else{
						temp=matrika[i][m]+matrika[m][j];
					}
					//ce najdemo cenejso pot to popravimo v nasem rezultatu
					if(temp<rezultat[i][j]){
						rezultat[i][j]=temp;
						
					}
				}
			}
		}
		//vedno testiramo za vozlisce 1, da imamo za vse algoritme enako
		int vozlisce=1;
		for(int i=0; i<stVozlisc;i++){
			d[i]=rezultat[vozlisce-1][i];
		}
	}
}
