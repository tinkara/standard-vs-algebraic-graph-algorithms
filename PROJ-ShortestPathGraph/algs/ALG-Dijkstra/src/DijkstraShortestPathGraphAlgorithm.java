/**
 *
 * @author ...
 */
public class DijkstraShortestPathGraphAlgorithm extends ShortestPathGraphAbsAlgorithm {

	protected void execute (int[][][] povezave, int[] d){
		//vedno testiramo za vozlisce 1, da imamo za vse algoritme enako
		int v=1;
		int stVozlisc=povezave.length;
		//mnozica vozlisc za pregled (1 pomeni, da je vozlisce na i-tem mestu notri, 0 pa da ni)
		int[] vozlisca=new int [stVozlisc];
		//izhodiscu dodelimo najcenejso pot 0, ostalim pa neskoncno
		//na zacetku so vsa vozlisca v "mnozici vozlisc za preiskovanje"
		for(int i=0; i<stVozlisc;i++){
			d[i]=Integer.MAX_VALUE;
			vozlisca[i]=1;
		}
		d[v-1]=0;
		//dokler obstajajo se nepregledana vozlisca
		while(obstajaEnica(vozlisca)){
			int trenutnoV=findMin(d,vozlisca);
			vozlisca[trenutnoV]=0;
			//po vseh izhodnih povezavah trenutnega vozlisca
			for(int i=0; i<povezave[trenutnoV].length;i++){
				int dist=0;
				if (d[trenutnoV]==Integer.MAX_VALUE){
					dist=Integer.MAX_VALUE;
				}
				else{
					dist=d[trenutnoV]+(int)(povezave[trenutnoV][i][1]);
				}
				if(dist<d[povezave[trenutnoV][i][0]]){
					d[povezave[trenutnoV][i][0]]=dist;
				}
			}
		}
		
	}
	public static boolean obstajaEnica(int[] vozlisca) {
		for(int i=0; i<vozlisca.length;i++){
			if(vozlisca[i]==1){
				return true;
			}
		}
		return false;
	}
	public static int findMin(int[] d, int[] vozlisca) {
		int min=Integer.MAX_VALUE;
		int minInd=0;
		for(int i=0; i<d.length;i++){
			if(d[i]<=min && vozlisca[i]==1){
				min=d[i];
				minInd=i;
			}
		}
		return minInd;
	}
}
