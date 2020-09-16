package app.report;

import app.output.Output;

public class DailyReport extends Report {
    public DailyReport(Output output) {
        super(output);
    }

    @Override
    public void report() {
        System.out.println("Creating daily report ...");
        output.generate();
    }
}
