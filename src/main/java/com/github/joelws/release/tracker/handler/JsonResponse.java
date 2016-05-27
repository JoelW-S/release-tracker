package com.github.joelws.release.tracker.handler;

public class JsonResponse
{
    private static final String DEFAULT_REASON = "Error occured";

    private Integer status;

    private String reason;

    public JsonResponse(final Integer status, final String reason)
    {
        this.status = status;
        this.reason = reason;
    }

    public JsonResponse(final Integer status)
    {
        this.status = status;
        this.reason = DEFAULT_REASON;

    }

    public JsonResponse()
    {
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }
}
