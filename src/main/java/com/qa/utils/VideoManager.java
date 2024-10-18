package com.qa.utils;

import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class VideoManager {

    TestUtils utils = new TestUtils();

    public void startRecording() {
        ((CanRecordScreen) new DriverManager().getDriver()).startRecordingScreen();
    }

    public void stopRecording(String scenarioName) throws IOException {
        MyGlobalParams myGlobalParams = new MyGlobalParams();
        String media = ((CanRecordScreen) new DriverManager().getDriver()).stopRecordingScreen();
        String dir = "videos" + File.separator + myGlobalParams.getPlatformName() + " - " + myGlobalParams.getDeviceName() + " - " + myGlobalParams.getUdid()
                + File.separator + TestUtils.dateTime();
        File videoDir = new File(dir);
        synchronized(videoDir) {
            if(!videoDir.exists())
                videoDir.mkdirs();
        }
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(videoDir + File.separator +  scenarioName + ".mp4");
            stream.write(Base64.decodeBase64(media));
            stream.close();

            TestUtils.logger().info("Video path: " + videoDir + File.separator + scenarioName + ".mp4");
        } catch (Exception e) {
            TestUtils.logger().error("Error creating video..." + e.toString());
        } finally {
            if(stream != null) {
                stream.close();
            }
        }
    }
}
