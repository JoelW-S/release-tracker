package com.github.joelws.release.tracker.interfaces;

public interface Adapter<In, Out> {
    Out adapt(final In in);
}
