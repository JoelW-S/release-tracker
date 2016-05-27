package com.github.joelws.release.tracker.service;

public interface ServiceExecution<T>
{
    T execute(T param);
}
