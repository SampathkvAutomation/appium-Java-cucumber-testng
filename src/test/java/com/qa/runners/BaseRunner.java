package com.qa.runners;

import com.qa.utils.DriverManager;
import com.qa.utils.MyGlobalParams;
import com.qa.utils.ServerManager;
import com.qa.utils.TestUtils;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.*;

public class BaseRunner {
    private static final ThreadLocal<TestNGCucumberRunner> testNGCucumberRunner = new ThreadLocal<>();
    private MyGlobalParams globalParams = new MyGlobalParams();

    public static TestNGCucumberRunner getRunner(){
        return testNGCucumberRunner.get();
    }

    private static void setRunner(TestNGCucumberRunner testNGCucumberRunner1){
        testNGCucumberRunner.set(testNGCucumberRunner1);
    }

    @Parameters({"platformName", "udid", "deviceName", "systemPort", "chromeDriverReport", "wdaLocalPort", "webkitDebugProxyPort"})
    @BeforeClass(alwaysRun = true)
    public void setUpClass(String platformName, String udid, String deviceName,
                           @Optional("Android") String systemPort, @Optional("Android") String chromeDriverReport,
                           @Optional("iOS") String wdaLocalPort, @Optional("iOS") String webkitDebugProxyPort) throws Exception {
        ThreadContext.put("ROUTINGKEY", platformName + "_" + deviceName);

        globalParams.setPlatformName(platformName);
        globalParams.setUdid(udid);
        globalParams.setDeviceName(deviceName);
        switch(platformName) {
            case "Android":
                globalParams.setSystemPort(systemPort);
                globalParams.setChromeDriverPort(chromeDriverReport);
                break;
            case "iOS":
                globalParams.setWdaLocalPort(wdaLocalPort);
                globalParams.setWebkitDebugProxyPort(webkitDebugProxyPort);
                break;
        }

        new ServerManager().startServer();
        new DriverManager().initDriver();

        setRunner(new TestNGCucumberRunner(this.getClass()));
        TestUtils.logger().info("Starting tests...");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        TestUtils.logger().info("Ending tests...");

        DriverManager driverManager = new DriverManager();
        if(driverManager.getDriver() != null) {
            driverManager.getDriver().quit();
            driverManager.setDriver(null);
        }
        ServerManager serverManager = new ServerManager();
        if(serverManager.getServer() != null) {
            serverManager.getServer().stop();
        }
        if(getRunner() != null) {
            getRunner().finish();
        }
        TestUtils.logger().info("Execution ended for device: " + globalParams.getDeviceName());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) throws Throwable {
        getRunner().runScenario(pickle.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return getRunner().provideScenarios();
    }
}
