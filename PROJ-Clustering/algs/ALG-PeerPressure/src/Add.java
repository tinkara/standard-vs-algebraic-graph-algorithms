
public class Add {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}
	
	/******************************************/
	/**                                      **/
	/**               MATRIKE                **/
	/**                                      **/
	/******************************************/
	
	/*sestevanje dveh matrik
	 * vhodni podatki:
	 * - 2D matrika1 in 2D matrika2, ki morata biti enakih dimenzij
	 * izhodni podatki
	 * - 2D matrika C enakih dimenzija kot matrika1 in matrika2, za katero velja C=matrika1+matrika2
	 */
	public static int [][] sestej(int[][] matrika1, int [][] matrika2){
		int dolzina=matrika1.length;
		int sirina=matrika1[0].length;
		int [][] matrika=new int[dolzina][sirina];
		for (int i=0; i<dolzina; i++){
			for (int j=0; j<sirina; j++){
				matrika[i][j]=matrika2[i][j]+matrika1[i][j];
			}
		}
		return matrika;
	}
	/*odstevanje dveh matrik
	 * vhodni podatki:
	 * - 2D matrika1 in 2D matrika2, ki morata biti enakih dimenzij
	 * izhodni podatki
	 * - 2D matrika C enakih dimenzija kot matrika1 in matrika2, za katero velja C=matrika1-matrika2
	 */
	public static int[][] odstej(int[][] matrika1, int [][] matrika2){
		int dolzina=matrika1.length;
		int sirina=matrika1[0].length;
		int [][] matrika=new int[dolzina][sirina];
		for (int i=0; i<dolzina; i++){
			for (int j=0; j<sirina; j++){
				matrika[i][j]=matrika1[i][j]-matrika2[i][j];
			}
		}
		return matrika;
	}
	/*transponiranje matrike
	 * vhodni podatki:
	 * - 2D matrika A, ki jo zelimo transponirati
	 * izhodni podatki:
	 * - 2D matrika B, za katero velja B=AT
	 * 
	 */
	public static int[][] transponiraj(int[][] matrika){
		int[][] rezultat = new int[matrika[0].length][matrika.length];
		for (int i=0; i<matrika.length; i++){
			for (int j=0; j<matrika[0].length; j++){
				//matriki samo zamenjamo stolpce in vrstice
				rezultat[j][i]=matrika[i][j];
			}
		}
		return rezultat;
	}
	/*
	 * mnozenje Ax
	 * vhodni podatki:
	 * - 2D matrika1 in 1D vektor s katerim zelimo mnoziti
	 * izhodni podatki:
	 * - vektor v, za katero velja v=matrika1 x vektor
	 */
	public static int[] mnozenjeVektor(int[][] matrika1, int[] vektor){
		int visina1=vektor.length;
		int sirina2=matrika1[0].length;
		int[] rezultat= new int[visina1];
		for(int i=0; i<visina1; i++){
			for(int j=0; j<sirina2; j++){
				rezultat[i] += matrika1[i][j]*vektor[j];
			}
		}
		return rezultat;
	}
	/*
	 * mnozenje A x B
	 * vhodni podatki:
	 * - 2D matrika1 in 2D matrika 2
	 * izhodni podatki:
	 * - matrika C, za katero velja C=matrika1 x matrika2
	 */
	public static int[][] mnozenje(int[][] matrika2, int[][] matrika1){
		int sirina1=matrika2[0].length;
		int visina1=matrika2.length;
		int sirina2=matrika1[0].length;
		int[][] rezultat= new int[visina1][sirina2];
		for(int i=0; i<visina1; i++){
			for(int j=0; j<sirina2; j++){
				for(int k=0; k<sirina1; k++){
					rezultat[i][j] += matrika2[i][k]*matrika1[k][j];
				}
			}
		}
		return rezultat;
	}
	/*
	 * mnozenje A x B
	 * vhodni podatki:
	 * - 2D matrika1 in 2D matrika 2
	 * izhodni podatki:
	 * - matrika C, za katero velja C=matrika1 x matrika2
	 */
	public static double[][] mnozenje_double(double[][] matrika2, double[][] matrika1){
		int sirina1=matrika2[0].length;
		int visina1=matrika2.length;
		int sirina2=matrika1[0].length;
		double[][] rezultat= new double[visina1][sirina2];
		for(int i=0; i<visina1; i++){
			for(int j=0; j<sirina2; j++){
				for(int k=0; k<sirina1; k++){
					rezultat[i][j] += matrika2[i][k]*matrika1[k][j];
				}
			}
		}
		return rezultat;
	}
	
	/*
	 * mnozenje AT x B
	 * vhodni podatki:
	 * - 2D matrika1 in 2D matrika 2
	 * izhodni podatki:
	 * - matrika C, za katero velja C=matrika1T x matrika2
	 */
	public static int[][] mnozenjeTransponirano(int[][] matrika2, int[][] matrika1){
		int sirina1=matrika2[0].length;
		int visina1=matrika2.length;
		int sirina2=matrika1[0].length;
		int[][] rezultat= new int[visina1][sirina2];
		int [][] matrika3=transponiraj(matrika1);
		for(int i=0; i<visina1; i++){
			for(int j=0; j<sirina2; j++){
				for(int k=0; k<sirina1; k++){
					rezultat[i][j] += matrika2[i][k]*matrika3[k][j];
				}
			}
		}
		return rezultat;
	}
	
	/*mnozenje v kolobarju (min,+)
	 * vhodni podatki
	 * - 2D matrika
	 * izhodni podatki
	 * - kvadrat vhodne matrike v kolobarju (min,+)
	 */
	public static int[][] mnozenjeMinPlus(int[][] matrika){
		int sirina1=matrika[0].length;
		int visina1=matrika.length;
		int sirina2=sirina1;
		int[][] rezultat= new int[visina1][sirina2];
		for(int i=0; i<visina1; i++){
			for(int j=0; j<sirina2; j++){
				//na zacetku je min neskoncno
				int min=Integer.MAX_VALUE;
				for(int k=0; k<sirina1; k++){
					int temp=matrika[i][k]+matrika[k][j];
					//ce najdemo vrednost, ki je manjsa od trenutnega minimuma, je to nas nov minimum
					if(temp<min){
						min=temp;
					}
				}
				rezultat[i][j]+=min;
			}
		}
		return rezultat;
	}
	
	/* mnozenje vektorja z matriko (xA) v polkolobarju (min,+) ->UPORABLJENO
	 * vhodni podatki:
	 * - 2D matrika
	 * - vektor s katerim zelimo mnoziti
	 * izhodni podatki:
	 * - vektor rezultat, za katerega velja rezultat= x min.+ A
	 */
	public static int[] mnozenjeMINPLUSZDesne(int[][] matrika, int[] vektor){
		//dimenzija vektorja
		int sirina=vektor.length;
		int temp=0;
		//inicializacija vektorja, v katerem bo rezultat
		int[] rezultat=new int[sirina];
		for(int i=0; i<sirina;i++){
			//na zacetku je minimum maksimalna vrednost
			int min=Integer.MAX_VALUE;
			for(int j=0; j<sirina;j++){
				if (matrika[j][i]==Integer.MAX_VALUE || vektor[j]==Integer.MAX_VALUE){
					temp=Integer.MAX_VALUE;
				}
				else{
					temp=vektor[j]+matrika[j][i];
				}
				//ce je sestevek manjsi kot trenutni minimum, minimum spremenimo
				if(temp<min){
					min=temp;
				}
			}
			rezultat[i]=min;
		}
		return rezultat;
	}
	
	//mnozenje v polkolobarju (min,+), kjer si zapomnimo se vozlisce skozi katerega smo nasli krajso pot
	public static int[][][] mnozenjeMinPlus_path(int[][][] matrika){
		int sirina1=matrika[0].length;
		int visina1=matrika.length;
		int sirina2=sirina1;
		int[][][] rezultat= matrika.clone();
		for(int i=0; i<visina1; i++){
			for(int j=0; j<sirina2; j++){
				for(int k=0; k<sirina1; k++){
					int temp=matrika[i][k][1]+matrika[k][j][1];
					if(temp<rezultat[i][j][1] && temp>=0){
						rezultat[i][j][1]=temp;
						//zapomnimo si predhodnika
						rezultat[i][j][0]=k+1;
					}
				}
			}
		}
		return rezultat;
	}
	public static int findMin(int[] d, int[] vozlisca) {
		int min=Integer.MAX_VALUE;
		int minInd=0;
		for(int i=0; i<d.length;i++){
			if(d[i]<min && vozlisca[i]==1){
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
	
	/******************************************/
	/**                                      **/
	/**                GRAFI                 **/
	/**                                      **/
	/******************************************/

}
