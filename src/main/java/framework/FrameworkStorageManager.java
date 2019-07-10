package framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;

public class FrameworkStorageManager {
    private JavascriptExecutor js;
    private WebStorage webstorage;
    private LocalStorage localStorage;

    public FrameworkStorageManager(FrameworkDriver webDriver) {
        webDriver.setWebStorage();
        this.webstorage = webDriver.webstorage;
        this.localStorage = this.webstorage.getLocalStorage();
    }

    public void removeItemFromLocalStorage(String item) {
        js.executeScript(String.format(
                "window.localStorage.removeItem('%s');", item));
    }

    public boolean isItemPresentInLocalStorage(String item) {
        return !(js.executeScript(String.format(
                "return window.localStorage.getItem('%s');", item)) == null);
    }

    public String getItemFromLocalStorage(String key) {
        return localStorage.getItem(key);
    }

    public boolean isItemFromLocalStoragePresent(String key) {
        try {
            return !localStorage.getItem(key).isEmpty();
        } catch (Exception e) {
            return false;
        }

    }

    public String getKeyFromLocalStorage(int key) {
        return (String) js.executeScript(String.format(
                "return window.localStorage.key('%s');", key));
    }

    public Long getLocalStorageLength() {
        return (Long) js.executeScript("return window.localStorage.length;");
    }

    public void setItemInLocalStorage(String item, String value) {
        localStorage.setItem(item, value);
        //js.executeScript(String.format(
        //"window.localStorage.setItem('%s','%s');", item, value));
    }

    public void clearLocalStorage() {
        js.executeScript(String.format("window.localStorage.clear();"));
    }
}