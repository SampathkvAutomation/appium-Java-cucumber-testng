package com.qa.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

public class ServerManager {

    private static ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();
    private TestUtils utils = new TestUtils();

    public AppiumDriverLocalService getServer() {
        return server.get();
    }

    public void startServer() {
        utils.logger().info("Starting Appium Server...");
        AppiumDriverLocalService server = getAppiumService();
        server.start();
        if(server == null || !server.isRunning()) {
            utils.logger().fatal("Appium Server Not Started. ABORT");
            throw new AppiumServerHasNotBeenStartedLocallyException("Appium Server not started. ABORT");
        }
        server.clearOutPutStreams();
        this.server.set(server);
        utils.logger().info("Appium Server Started!");
    }

    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    public AppiumDriverLocalService getAppiumService() {
        HashMap<String, String> environment = new HashMap<>();
        environment.put("PATH", "/opt/homebrew/bin:/opt/homebrew/sbin:/Users/jaime.contreras/Library/Android/sdk/platform-tools:/Users/jaime.contreras/Library/Android/sdk/cmdline-tools:/Users/jaime.contreras/Library/Java/JavaVirtualMachines/corretto-21.0.4/Contents/Home/bin:/Users/jaime.contreras/anaconda3/bin:/Users/jaime.contreras/anaconda3/condabin:/usr/local/bin:/System/Cryptexes/App/usr/bin:/usr/bin:/bin:/usr/sbin:/sbin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/local/bin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/bin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/appleinternal/bin:/Library/Apple/usr/bin");
        environment.put("ANDROID_HOME", "/Users/jaime.contreras/Library/Android/sdk");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/NODE"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withEnvironment(environment)
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withLogFile(new File("logs/appium_server.log"))
        );
    }
}
