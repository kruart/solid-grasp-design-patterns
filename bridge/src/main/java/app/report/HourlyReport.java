package app.report;

import app.output.Output;

public class HourlyReport extends Report {
    public HourlyReport(Output output) {
        super(output);
    }

    @Override
    public void report() {
        System.out.println("Creating hourly report ...");
        output.generate();
    }
}
