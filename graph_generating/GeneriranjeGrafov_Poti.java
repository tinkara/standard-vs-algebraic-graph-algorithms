import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Random;


public class GeneriranjeGrafov_Poti {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//for za spremembo stevila vozlisc
		for(int a=0; a<10;a++){
			//for za spremembo stevila povezav
			for(int aa=0; aa<200;aa++){
				//st vozlisc od 100 do 1000 s korakom 100
				int  n = 100*(a+1);
				//st povezav od n do n2/4 
				int v=(int) (n+((Math.pow(n, 2)/4-n)/200)*aa);
				int[][] tempMatrix=generirajGrafPath(n,v);
				String name1="/Users/tinka/Desktop/grafi_mag/najkrajse_poti/example_graph_path_v"+String.valueOf(n)+"p"+String.valueOf(v)+".txt";						PrintWriter writer=null;
				try {
					writer = new PrintWriter(name1, "UTF-8");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				//vpisemo stevilo vozlisc
				writer.println(n);
				//vpisemo stevilo povezav
				writer.println(v);
				//vpisemo vrstico po vrstico
				for(int j=0; j<tempMatrix.length;j++){
					for (int k=0; k<tempMatrix.length;k++){
						if(tempMatrix[j][k]>0){
							writer.println(j+" "+k+" "+tempMatrix[j][k]);
						}
					}
				}
				writer.close();
				
			}
		}
		
	}
	public static int[][] generirajGrafPath(int stVozlisc, int stPovezav ){
		int[][] matrika=new int[stVozlisc][stVozlisc];
		
		int stRazdeljenih=0;
		
		while(stRazdeljenih!=stPovezav){
		//razdelimo povezave
			Random rand = new Random();
			//na random dolocimo vozlisce od in do, kjer random poteka od 0 do velikosti izbranega clusterja
			int from=rand.nextInt(stVozlisc);
			int to=rand.nextInt(stVozlisc);	
			if(matrika[from][to]==0){
				int cena=rand.nextInt(5)+1;
				matrika[from][to]=cena;
				stRazdeljenih++;
			}
		}
			
		
		return matrika;
	}
	
	private static void izpisi_matrix(double[][] result) {
		for(int i=0; i<result.length;i++){
			for(int j=0; j<result[i].length;j++){
				System.out.print(" "+result[i][j]+" ");
			} 
			System.out.println();
		}		
	}

}
