package framework;

import java.io.File;
import java.util.ArrayList;

public class FrameworkUtils {

    private ArrayList<String[]> fullLog;
    private int step;
    private String evidencePath;
    private int example = 1;
    private String scenarioName = "";

    public FrameworkUtils() {
        createLogTest();
    }

    public void setEvidencePath(String scenario) {
        if (!scenario.equals(scenarioName)) {
            this.scenarioName = scenario;
            this.example = 1;
        }
        String path = System.getProperty("user.dir") + "/" + "output/";
        File dir = new File(path);
        dir.mkdir();
        path = path + scenario;
        dir = new File(path);
        dir.mkdir();
        path = path + "/Caso_De_Teste_" + String.valueOf(this.example);
        dir = new File(path);
        dir.mkdir();
        this.step = 0;
        this.example++;
        this.evidencePath = path;
    }

    private void createLogTest() {
        this.fullLog = new ArrayList<String[]>();
        step = 0;
    }

    public String getExampleName() {
        return "Caso_De_Teste_" + String.valueOf(this.example - 1);
    }

    public void addLogStep(String result, String expectedResult, String actualResult) {
        this.fullLog.add(new String[]{getStep(), result, expectedResult, actualResult});
    }

    public String getEvidencePath() {
        return this.evidencePath;
    }

    public String getStep() {
        return String.format("%03d", this.step);
    }

    public ArrayList<String[]> getLogTest() {
        return this.fullLog;
    }

    public void addStep() {
        this.step++;
    }

}
