package learning;

public class Day13_PlaywrightInspector {
    public static void main(String[] args) {
        /* To Verify elements in inspector just open inspector by command below and goto inspect and console

        mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen    https://formy-project.herokuapp.com/form"
  To see single element   =>  playwright.$('#first-name')
  To see multiple element =>  playwright.$$('input')
  To inspect element  => playwright.inspect('#first-name')
     */
    }
}
