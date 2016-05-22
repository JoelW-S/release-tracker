package com.github.joelws.release.tracker.endpoint;

import com.github.joelws.release.tracker.common.GenericEndPointImpl;
import com.github.joelws.release.tracker.model.Release;
import com.github.joelws.release.tracker.service.ReleaseService;
import org.apache.log4j.Logger;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("releases") public class ReleaseEndPointImpl extends GenericEndPointImpl<Release, String>
        implements ReleaseEndpoint
{

    private ReleaseService service;

    private static Logger LOGGER = Logger.getLogger(ReleaseEndPointImpl.class);

    public ReleaseEndPointImpl(ReleaseService releaseService)
    {
        this.service = releaseService;
    }

    @Override public Response create(Release type)
    {
        if (type != null)
        {

            LOGGER.info("Starting create process: " + type.getName());
                return Response.ok(service.create(type)).build();

        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @Override public Response update(Release type)
    {
        if (type != null)
        {

            LOGGER.info("Starting update process: " + type.getName());
                return Response.ok(service.update(type)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @Override public Response delete(String identifier)
    {
        if (identifier != null)
        {
            LOGGER.info("Starting delete process " + identifier);
                service.delete(identifier);
                return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @Override
    public Response read(String identifier)
    {
        if (identifier != null)
        {
            LOGGER.info("Starting search " + identifier);
            return Response.ok().entity(service.read(identifier)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
