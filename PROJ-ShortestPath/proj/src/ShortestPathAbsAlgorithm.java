import si.fri.algotest.entities.ParameterType;
import si.fri.algotest.entities.EParameter;
import si.fri.algotest.entities.ParameterSet;
import si.fri.algotest.entities.TestCase;
import si.fri.algotest.execute.AbsAlgorithm;
import si.fri.algotest.global.ErrorStatus;


public abstract class ShortestPathAbsAlgorithm extends AbsAlgorithm {

  ShortestPathTestCase ShortestPathTestCase;
  int [] resultC;
  @Override
  public ErrorStatus init(TestCase test) {
    if (test instanceof ShortestPathTestCase) {
      ShortestPathTestCase = (ShortestPathTestCase) test;
      
      int n = ShortestPathTestCase.matrix != null ? ShortestPathTestCase.matrix.length : 0;
      resultC = new int[n];
      return ErrorStatus.STATUS_OK;
    } else
      return ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR_CANT_PERFORM_TEST, "Invalid test:" + test);
  }
  
  @Override
  public void run() {
    execute(ShortestPathTestCase.matrix, resultC);
  }

  
  @Override
  public ParameterSet done() {
    ParameterSet result = new ParameterSet(ShortestPathTestCase.getParameters());
    String ok = ShortestPathMatch(ShortestPathTestCase.shortestPath,resultC) ? "OK" : "NOK";
    EParameter passPar = new EParameter("Check", "", ParameterType.STRING, ok);
    result.addParameter(passPar, true);
    
                  
    return result;
  }  
  
  private boolean ShortestPathMatch(int[] result1, int[] result2){
	  /*
	  for(int i=0; i<result1.length;i++){
		  System.out.println(result1[i]+" : "+ result2[i]);
	  }
	  */
	  for(int i=0; i<result1.length;i++){
		  if(result1[i]!=result2[i]){
			  //System.out.println("prav:"+ result1[i]+" dobimo: "+ result2[i]);
			  return false;
		  }
	  }
	  return true;
  }

  protected abstract void execute(int [][] tabela, int[] rezultat);
  
}

