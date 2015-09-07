/**
 *
 * @author ...
 */
public class MarkovClusterClusteringAlgorithm extends ClusteringAbsAlgorithm {
	protected void execute(double [][] matrika, int[] gruce){
		int stVozlisc=matrika.length;
		matrika=normalizirajMatriko(matrika);
		//izpisi_matrix_double(matrika);
		int enaka=0;
		double[][] C_f=new double[stVozlisc][stVozlisc];
		for(int i=0; i<stVozlisc;i++){
			for(int j=0; j<stVozlisc;j++){
				C_f[i][j]=matrika[i][j];
			}
		}
		int i_i=2;
		int r=2;
		while (enaka==0){
			//potenciranje
			for(int i=0; i<i_i-1;i++){
				C_f=Add.mnozenje_double(matrika,C_f);
			}
			//izpisi_matrix_double(matrika);
			//inflacija
			int vsota=0;
			for(int i=0; i<stVozlisc;i++){
				vsota=0;
				for(int j=0; j<stVozlisc;j++){
					vsota+=Math.pow(C_f[j][i],r);
				}
				if(vsota>0){
					for(int j=0; j<stVozlisc;j++){
						C_f[j][i]=Math.pow(C_f[j][i],r)/vsota;
					}
				}
			}
			//System.out.println("inflacija");
			//izpisi_matrix_double(matrika);
			enaka=1;
			for(int i=0; i<stVozlisc;i++){
				for(int j=0; j<stVozlisc;j++){
					if(C_f[i][j]< matrika[i][j]-0.01 || C_f[i][j]> matrika[i][j]+0.01){
						enaka=0;
						continue;
					}
				}
			}
			for(int i=0; i<stVozlisc;i++){
				for(int j=0; j<stVozlisc;j++){
					matrika[i][j]=C_f[i][j];
				}
			}
		}
		//izluscenje gruc
		int st_gruc=0;
		boolean novCl=false;
		for(int i=0; i<stVozlisc;i++){
			novCl=false;
			for(int j=0; j<stVozlisc;j++){
				if(C_f[i][j]>0.1){
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
