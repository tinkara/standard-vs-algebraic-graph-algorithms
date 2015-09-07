import si.fri.algotest.entities.ParameterType;
import si.fri.algotest.entities.EParameter;
import si.fri.algotest.entities.ParameterSet;
import si.fri.algotest.entities.TestCase;
import si.fri.algotest.execute.AbsAlgorithm;
import si.fri.algotest.global.ErrorStatus;
import java.util.*;

/**
 *
 * @author ...
 */
public abstract class ClusteringAbsAlgorithm extends AbsAlgorithm {

  ClusteringTestCase clusteringTestCase;
  int [] resultC;
  @Override
  public ErrorStatus init(TestCase test) {
    if (test instanceof ClusteringTestCase) {
      clusteringTestCase = (ClusteringTestCase) test;
   // prepare space for the result
      int n = clusteringTestCase.matrix != null ? clusteringTestCase.matrix.length : 0;
      resultC = new int[n];
      
      return ErrorStatus.STATUS_OK;
    } else
      return ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR_CANT_PERFORM_TEST, "Invalid test:" + test);
  }
  
  @Override
  public void run() {

    execute(clusteringTestCase.matrix, resultC);
  }

  
  @Override
  public ParameterSet done() {

	  ParameterSet result = new ParameterSet(clusteringTestCase.getParameters());
	  	//izpisivektor(clusteringTestCase.clusters);
	  	//izpisivektor(resultC);
	  	String ok = ClustersMatch_v1(clusteringTestCase.clusters,resultC) ? "OK" : "NOK";
	    String ok2=ClustersMatch(clusteringTestCase.clusters,resultC) ? "OK" : "NOK";
	    EParameter passPar = new EParameter("Check", "", ParameterType.STRING, ok);
	    result.addParameter(passPar, true);
	    EParameter passPar2 = new EParameter("CheckV2", "", ParameterType.STRING, ok2);
	    result.addParameter(passPar2, true);
	    
	    return result;
  }   
  
  private boolean ClustersMatch_v1(int[] result1, int[] result2){
	  for(int i=0; i<result1.length;i++){
		  int cl1=result1[i];
		  int cl2=result2[i];
		  
		  Set<Integer> set1 = getVozliscaVCluster(result1, cl1);
		  Set<Integer> set2 = getVozliscaVCluster(result2, cl2);
		  
		  if(!set1.equals(set2)){
			  return false;
		  }
		  
	  }
	  return true;
  }
  private boolean ClustersMatch(int[] result1, int[] result2){
	  for(int i=0; i<result1.length;i++){
		  for (int j=0; j<result1.length;j++){
			  /*za vsak par vozlisc preverimo ali sta v rezultatu v istih clusterjih, v resitvi pa v razlicnih*/
			  if(i!=j){
				  int cl1_resitev=result1[i];
				  int cl2_resitev=result1[j];
				  int cl1_rezultat=result2[i];
				  int cl2_rezultat=result2[j];
				  if(cl1_resitev!=cl2_resitev && cl1_rezultat==cl2_rezultat){
					  return false;
				  }
			  }
		  }
		  
	  }
	  return true;
  }
  
  public void izpisivektor(int[] vektor){
	  for(int i=0; i<vektor.length;i++){
		  System.out.print(vektor[i]);
	  }
	  System.out.println();
  }
  private static void izpisi_matrix_double(double[][] result) {
		for(int i=0; i<result.length;i++){
			for(int j=0; j<result[i].length;j++){
				System.out.print(" "+result[i][j]+" ");
			} 
			System.out.println();
		}		
	}
  
  private Set<Integer> getVozliscaVCluster(int[] res, int cl){
	  Set<Integer> set= new TreeSet<Integer>();
	  for(int i=0; i<res.length;i++){
		  if(res[i]==cl){
			  set.add(i);
		  }
	  }
	  return set;
	  
  }

  protected abstract void execute(double [][] tabela, int[] rezultat);
  
}




