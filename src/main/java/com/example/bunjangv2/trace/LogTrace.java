package com.example.bunjangv2.trace;

public interface LogTrace {
    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
