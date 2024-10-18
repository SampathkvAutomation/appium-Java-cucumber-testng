package com.qa.utils;

public class MyGlobalParams {

    protected static ThreadLocal <String> platformName = new ThreadLocal<>();
    protected static ThreadLocal <String> deviceName = new ThreadLocal<>();
    protected static ThreadLocal <String> udid = new ThreadLocal<>();
    protected static ThreadLocal <String> systemPort = new ThreadLocal<>();
    protected static ThreadLocal <String> chromeDriverPort = new ThreadLocal<>();
    protected static ThreadLocal <String> wdaLocalPort = new ThreadLocal<>();
    protected static ThreadLocal <String> webkitDebugProxyPort = new ThreadLocal<>();

    public String getPlatformName() {
        return platformName.get();
    }

    public void setPlatformName(String platformName1) {
        platformName.set(platformName1);
    }

    public String getDeviceName() {
        return deviceName.get();
    }

    public void setDeviceName(String deviceName1) {
        deviceName.set(deviceName1);
    }

    public String getUdid() {
        return udid.get();
    }

    public void setUdid(String udid1) {
        udid.set(udid1);
    }

    public String getSystemPort() {
        return systemPort.get();
    }

    public void setSystemPort(String systemPort1) {
        systemPort.set(systemPort1);
    }

    public String getChromeDriverPort() {
        return chromeDriverPort.get();
    }

    public void setChromeDriverPort(String chromeDriverPort1) {
        chromeDriverPort.set(chromeDriverPort1);
    }

    public String getWdaLocalPort() {
        return wdaLocalPort.get();
    }

    public void setWdaLocalPort(String wdaLocalPort1) {
        wdaLocalPort.set(wdaLocalPort1);
    }

    public String getWebkitDebugProxyPort() {
        return webkitDebugProxyPort.get();
    }

    public void setWebkitDebugProxyPort(String webkitDebugProxyPort1) {
        webkitDebugProxyPort.set(webkitDebugProxyPort1);
    }

    public void initializeGlobalParams() throws IllegalAccessException {
        MyGlobalParams myGlobalParams = new MyGlobalParams();
        myGlobalParams.setPlatformName(System.getProperty("platformName", "iOS")); // Android iOS
        myGlobalParams.setDeviceName(System.getProperty("deviceName", "iPhone_15_Pro_Max")); // Pixel_7_Emulator iPhone_15_Pro_Max
        myGlobalParams.setUdid(System.getProperty("udid", "CCEC5603-EF28-4266-BE0C-2FC66A8E00A6")); // emulator-5554 CCEC5603-EF28-4266-BE0C-2FC66A8E00A6

        switch(myGlobalParams.getPlatformName()) {
            case "Android":
                myGlobalParams.setSystemPort(System.getProperty("systemPort", "10000"));
                myGlobalParams.setChromeDriverPort(System.getProperty("chromeDriverPort", "11000"));
                break;
            case "iOS":
                myGlobalParams.setWdaLocalPort(System.getProperty("wdaLocalPort", "10001"));
                myGlobalParams.setWebkitDebugProxyPort(System.getProperty("webkitDebugProxyPort", "11001"));
                break;
            default:
                throw new IllegalAccessException("Invalid Platform Name");
        }
    }
}
