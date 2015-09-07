
/**
 *
 * @author ...
 */
public class DijkstraShortestPathAlgorithm extends ShortestPathAbsAlgorithm {

	protected void execute (int[][] matrika, int[] d){
		//vedno testiramo za vozlisce 1, da imamo za vse algoritme enako
		int vozlisce=1;
		int stVozlisc=matrika.length;
		for(int i=0; i<stVozlisc;i++){
			d[i]=matrika[vozlisce-1][i];
		}
		int[] s=new int[stVozlisc];
		for(int i=0; i<stVozlisc;i++){
			s[i]=1;
		}
		s[vozlisce-1]=0;
		while (obstajaEnica(s)){
			int trenutnoV=findMin(d,s);
			s[trenutnoV]=0;
			int temp=0;
			
			for(int i=0; i<stVozlisc;i++){
				int min=Integer.MAX_VALUE;
				for(int j=trenutnoV; j<trenutnoV+1;j++){
					
					if(d[j]==Integer.MAX_VALUE || matrika[j][i]==Integer.MAX_VALUE){
						temp=Integer.MAX_VALUE;
					}
					else{
						temp=d[j]+matrika[j][i];
					}
					if(temp<min){
						min=temp;
					}
					
				}
				if(temp<d[i]){
					d[i]=min;
					
				}
			}
		}
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
	public static boolean obstajaEnica(int[] vozlisca) {
		for(int i=0; i<vozlisca.length;i++){
			if(vozlisca[i]==1){
				return true;
			}
		}
		return false;
	}
}
