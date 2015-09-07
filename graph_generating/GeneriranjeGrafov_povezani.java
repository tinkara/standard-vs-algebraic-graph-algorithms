import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


public class GeneriranjeGrafov_povezani {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//for za spremembo stevila vozlisc
		for(int a=0; a<10;a++){
			//for za spremembo stevila povezav
			for(int aa=0; aa<10;aa++){
				//for za spremembo stevila clusterjev
				for(int aaa=0; aaa<10;aaa++){
					//for za spremembo verjetnosti, da vozlisce pade v cluster
					for (int ver=60;ver<81;ver+=10){
						Random rand = new Random();
						//st vozlisc od 100 do 1000 s korakom 100
						int  n = 100*(a+1);
						//st povezav od 2*n do 10000
						int v=2*n+((10000-2*n)/10)*aa;
						//st clusterjev od 2 do n/2
						int c=2+((n/2-2)/10)*aaa;
						LinkedList<int[][]> result=generirajGrafCluster(n,v,c,ver);
						int[][] tempMatrix=result.get(0);
						int[][] realResult=result.get(1);
						String name1="/Users/tinka/Desktop/grafi_mag/clustering/example_graph_clusters_v"+String.valueOf(n)+"p"+String.valueOf(v)+"c"+String.valueOf(c)+"ver"+String.valueOf(ver)+".txt";
						String name2="/Users/tinka/Desktop/grafi_mag/clustering/result_graph_clusters_"+String.valueOf(n)+"p"+String.valueOf(v)+"c"+String.valueOf(c)+"ver"+String.valueOf(ver)+".txt";

						PrintWriter writer=null;
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
								if(tempMatrix[j][k]==1){
									writer.println(j+" "+k);
								}
							}
						}
						writer.close();
						
						//vpisemo se rezultat
						PrintWriter writer1=null;
						try {
							writer1 = new PrintWriter(name2, "UTF-8");
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						//rezultat v datoteki: vozlisce cluster
						for(int j=0; j<realResult.length;j++){
							writer1.println(j+" "+realResult[j][0]);
						}
						writer1.close();
					}
				}
			}
		}
		
	}
	public static LinkedList<int[][]> generirajGrafCluster(int stVozlisc, int stPovezav, int stClusterjev, int verjetnost    ){
		//stevec za razdeljevanje vozlisc po clusterjih
		int stPorabljenih=0;
		//stevec za razdeljevanje clusterjev
		int stRazdeljenih=0;
		//naredimo, da bo graf povezan
		LinkedList<Integer> set_nepovezane=new LinkedList<Integer>();
		LinkedList<Integer> set_povezane=new LinkedList<Integer>();
		for(int i=0; i<stVozlisc;i++){
			set_nepovezane.add(i);
		}
		//izberemo prvi 2 vozlisci, ki ju povezemo
		Random r=new Random();
		
		int v1=r.nextInt(set_nepovezane.size());
		int vozlisce1=set_nepovezane.get(v1);
		
		set_povezane.add(vozlisce1);
		set_nepovezane.remove(v1);
		int v2=r.nextInt(set_nepovezane.size());
		int vozlisce2=set_nepovezane.get(v2);
		set_povezane.add(vozlisce2);
		set_nepovezane.remove(v2);
		
		int[][] matrika=new int[stVozlisc][stVozlisc];
		
		matrika[vozlisce1][vozlisce2]=1;
		stRazdeljenih++;
		
		
		while(set_nepovezane.size()>0){
			//vzamemo vozlisce iz povezanega dela
			v1=r.nextInt(set_povezane.size()); 
			vozlisce1=set_povezane.get(v1);
			//prikljucimo vozlisce iz nepovezanega dela
			v2=r.nextInt(set_nepovezane.size());
			vozlisce2=set_nepovezane.get(v2);
			set_povezane.add(vozlisce2);
			set_nepovezane.remove(v2);
			
			matrika[vozlisce1][vozlisce2]=1;
			stRazdeljenih++;
		}
		
		
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] clusterji= new LinkedList[stClusterjev];
		
		//naredimo seznam vozlisc s clusterji
		for(int j=0; j<stClusterjev;j++){
			clusterji[j]=new LinkedList<Integer>();
		}
		//pripravimo seznam vozlisc od koder bomo jemali vozlisca
		LinkedList<Integer> vozlisca=new LinkedList<Integer>();
		for(int i=0; i<stVozlisc;i++){
			vozlisca.add(i);
		}
		
		//dolocimo velikosti clusterjev in jih napolnemo
		
		for(int i=0; i<stClusterjev;i++){
			Random rand=new Random();
			int n=0;
			//ce smo v zadnjem koraku, za zadnji cluster vzamemo preostalo stevilo vozlisc
			if(i==stClusterjev-1){
				n=stVozlisc-stPorabljenih;
			}
			//drugace izberemo velikost clusterja na random
			else{
				//zagotovimo, da bo vsak cluster velik vsaj 2 vozlisca in da izbiramo velikost izmed 
				n=rand.nextInt(stVozlisc-stClusterjev*2+2*i-2-stPorabljenih)+2;
			}
			//izberemo vozlisca za ta cluster izmed preostalih
			stPorabljenih+=n;
			for(int j=0; j<n;j++){
				//izberemo indeks izmed preostalih vozlisc
				int vi=rand.nextInt(vozlisca.size());
				//dodamo izbrano vozlisce v trenutni cluster
				clusterji[i].add(vozlisca.get(vi));
				//odstranimo vozlisce izmed preostalih vozlisc;
				vozlisca.remove(vi);
			}
		}
		
		
		while(stRazdeljenih!=stPovezav){
		//razdelimo povezave
			Random rand = new Random();
			int  n = rand.nextInt(100);
			if(n<verjetnost){
				//na random izberemo v katerem clustru se doda povezava
				int clust = rand.nextInt(stClusterjev);
				//na random dolocimo vozlisce od in do, kjer random poteka od 0 do velikosti izbranega clusterja
				int from=clusterji[clust].get(rand.nextInt(clusterji[clust].size()));
				int to=clusterji[clust].get(rand.nextInt(clusterji[clust].size()));
				
				//pazimo, da dvakrat ne izberemo istega vozlisca
				while(from==to){
					to=clusterji[clust].get(rand.nextInt(clusterji[clust].size()));
				}
				
				if(matrika[from][to]==0){
					matrika[from][to]=1;
					stRazdeljenih++;
				}
			}
			else{
				//drugace izberemo med katerima clusterjema bomo naredili povezavo
				int clust1=rand.nextInt(stClusterjev);
				int clust2=rand.nextInt(stClusterjev);
				
				//pazimo, da ne pademo spet v isti cluster
				while(clust1==clust2){
					clust2=rand.nextInt(stClusterjev);
				}
				
				int from=clusterji[clust1].get(rand.nextInt(clusterji[clust1].size()));
				int to=clusterji[clust2].get(rand.nextInt(clusterji[clust2].size()));
				
				if(matrika[from][to]==0){
					matrika[from][to]=1;
					stRazdeljenih++;
				}
			}
		}	
		
		LinkedList<int[][]> result=new LinkedList<int[][]>();
		result.add(matrika);
		
		//generiramo se pravi rezultat
		int[][] realResult=new int[stVozlisc][1];
		for(int i=0; i<stClusterjev;i++){
			for(int j=0; j<clusterji[i].size();j++){
				realResult[clusterji[i].get(j)][0]=i;
			}
		}
		result.add(realResult);
		
		return result;
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
