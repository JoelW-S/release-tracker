package com.github.joelws.release.tracker.service;

public interface ServiceExecution<In, Out> {
    Out execute(In param);
}
