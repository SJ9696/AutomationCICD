package rahulshettyacadmy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
 
	//Sec 22 Lect 180 
	//Note- retry fail test case again
	
	int count =0;
	int maxTrY=2;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if (count<maxTrY) {
			count++;
			return true;
		}
		return false;
	}

}
