package app.report;

import app.output.Output;

abstract public class Report {
    protected final Output output;

    public Report(Output output) {
        this.output = output;
    }

    abstract public void report();
}
