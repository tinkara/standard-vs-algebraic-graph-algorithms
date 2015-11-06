
public class BellmanFordShortestPathAlgorithm extends ShortestPathAbsAlgorithm {

	protected void execute (int[][] matrika, int[] d){
		//vedno testiramo za vozlisce 1, da imamo za vse algoritme enako
		int vozlisce=1;
		int stVozlisc=matrika.length;
		//dolocanje za katero vozlisce potrebujemo minimalno pot
		for(int i=0; i<stVozlisc;i++){
			if(i==(vozlisce-1)){
				d[i]=0;
			}
			else{
				d[i]=Integer.MAX_VALUE;
			}
		}
		//izracun po definiciji
		int[] temp=new int[stVozlisc];
		for(int j=0; j<stVozlisc-1;j++){
			temp=mnozenjeMINPLUSZDesne(matrika,d);
			for(int i=0; i<stVozlisc;i++){
				d[i]=temp[i];
			}
		}
		//ugotavljanje ce obstaja negativni cikel
		/*
		temp=mnozenjeMINPLUSZDesne(matrika,d);
		for(int k=0; k<stVozlisc;k++){
			if(temp[k] != d[k]){
				System.out.println("Obstaja negativen cikel");
				break;
			}
		}
		*/
		
	} 
	public static int[] mnozenjeMINPLUSZDesne(int[][] matrika, int[] vektor){
		//dimenzija vektorja
		int sirina=vektor.length;
		for(int i=0; i<sirina;i++){
			//na zacetku je minimum maksimalna vrednost
			for(int j=0; j<sirina;j++){
				int temp=0;
				if (matrika[i][j]==Integer.MAX_VALUE || vektor[i]==Integer.MAX_VALUE){
					temp=Integer.MAX_VALUE;
				}
				else{
					temp=vektor[i]+matrika[i][j];
				}
				if(vektor[j]>temp){
					vektor[j]=temp;
				}
			}
		}
		return vektor;
	}
}
