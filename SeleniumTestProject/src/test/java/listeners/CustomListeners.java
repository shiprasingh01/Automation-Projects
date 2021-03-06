package listeners;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseClass;
import util.TestUtil;

public class CustomListeners extends BaseClass implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println("Test Case Started : " + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Case Passed : " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test Case Fail : " + result.getName());
		TestUtil testUtil = new TestUtil();
		String methodName = result.getName().toString().trim();
		try {
			testUtil.captureScreenshot(methodName, BaseClass.driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Case Skipped : " + result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		System.out.println("Started Execution for suite : " + context.getName());
	}

	public void onFinish(ITestContext context) {
		System.out.println("Finished Execution for suite : " + context.getName());
	}
}
