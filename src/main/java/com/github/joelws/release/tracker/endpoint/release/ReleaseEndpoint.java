package com.github.joelws.release.tracker.endpoint.release;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndPoint;

import javax.ws.rs.Path;

@Path("releases") public class ReleaseEndpoint extends ResourceEndPoint<String>
{
    public ReleaseEndpoint(BusinessService<String> service)
    {
        super(service);
    }

}
