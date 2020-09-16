package app;

import app.output.JsonOutput;
import app.output.XmlOutput;
import app.report.DailyReport;
import app.report.HourlyReport;
import app.report.Report;

public class Main {
    public static void main(String[] args) {
        Report dailyJsonReport = new DailyReport(new JsonOutput());
        dailyJsonReport.report();

        Report hourlyXmlReport = new HourlyReport(new XmlOutput());
        hourlyXmlReport.report();
    }
}
