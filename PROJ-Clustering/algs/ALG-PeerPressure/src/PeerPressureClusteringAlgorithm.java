
/**
 *
 * @author ...
 */
public class PeerPressureClusteringAlgorithm extends ClusteringAbsAlgorithm {

  protected void execute(double[][] matrika, int[] gruce){
	  int stVozlisc=matrika.length;
		double[][] C=new double[stVozlisc][stVozlisc];
		for(int i=0; i<stVozlisc;i++){
			for(int j=0; j<stVozlisc;j++){
				if(i==j){
					C[i][j]=1;
				}
				else{
					C[i][j]=0;
				}
			}
		}
		double matrika2[][]=normalizirajMatriko(matrika);

		double[][] C_fin=PeerPressure_matrix_recursive(matrika2,C,stVozlisc);
		
		//izluscenje gruc
		int st_gruc=0;
		boolean novCl=false;
		for(int i=0; i<stVozlisc;i++){
			novCl=false;
			for(int j=0; j<stVozlisc;j++){
				if(C_fin[j][i]==1){
					if(gruce[j]==0){
						gruce[j]=st_gruc;
						novCl=true;
					}
				}
			}
			if(novCl){
				st_gruc++;
			}
		}
  }
  public static double[][] PeerPressure_matrix_recursive(double[][] matrika, double[][] C, int stVozlisc){
	  double[][] T=Add.mnozenje_double(matrika,C);
	  double[][] C_fin=new double[stVozlisc][stVozlisc];
	  //nastavimo clusterje tako da das tisti cluster za katerega ima vozlisce najvec glasov
	  //deluje tako, da vedno izbere prvega najvecjega, ki ga najde - tako se izognemo tezavam z izenacitvijo izidov
	  for(int i=0; i<stVozlisc;i++){
		  double maxVote=0;
		  int maxCluster=0;
		  for(int j=0; j<stVozlisc;j++){
			  if(T[i][j]>maxVote){
				  maxVote=T[i][j];
				  maxCluster=j;
			  }
		  }
		  C_fin[i][maxCluster]=1;
	  }
	  int enaka=1;
	  for(int i=0; i<stVozlisc;i++){
		  for(int j=0; j<stVozlisc;j++){
			  if(C[i][j]!=C_fin[i][j]){
				  enaka=0;
				  break;
			  }
		  }
	  }
	  if(enaka==0){
		  C_fin=PeerPressure_matrix_recursive(matrika,C_fin,stVozlisc);
	  }
	  return C_fin;
  }
  
  public static double[][] normalizirajMatriko(double[][] matrika){
		int stVozlisc=matrika.length;
		//dodajanje zank
		for(int i=0; i<stVozlisc;i++){
			matrika[i][i]=1;
		}
		//normalizacija matrike (tranzicijska matrika)
		double vsota=0;
		for(int i=0; i<stVozlisc;i++){
			vsota=0;
			for(int j=0; j<stVozlisc;j++){
				vsota+=matrika[j][i];
			}
			if(vsota>0){
				for(int j=0; j<stVozlisc;j++){
					matrika[j][i]=matrika[j][i]/vsota;
				}
			}
		}
		return matrika;
	}
}
