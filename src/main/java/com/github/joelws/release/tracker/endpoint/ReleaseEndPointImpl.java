package com.github.joelws.release.tracker.endpoint;

import com.github.joelws.release.tracker.common.GenericEndPointImpl;
import com.github.joelws.release.tracker.exception.DatabaseException;
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
            try
            {
                LOGGER.info("Creating release: " + type.getName());
                return Response.ok(service.create(type)).build();
            }
            catch (DatabaseException de)
            {
                LOGGER.error("Failed to create " + type.getName() + " reason: ", de.getCause());
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @Override public Response update(Release type)
    {
        if (type != null)
        {
            try
            {
                LOGGER.info("Updating release: " + type.getName());
                return Response.ok(service.update(type)).build();
            }
            catch (DatabaseException de)
            {
                LOGGER.error("Failed to update " + type.getName() + " reason: ", de.getCause());
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @Override public Response delete(String identifier)
    {
        if (identifier != null)
        {
            try
            {
                LOGGER.info("Deleting release " + identifier);
                service.delete(identifier);
                return Response.ok().build();
            }
            catch (DatabaseException de)
            {
                LOGGER.error("Failed to delete " + identifier + " reason: ", de.getCause());
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @Override public Release read(String identifier)
    {
        if (identifier != null)
        {
            try
            {
                LOGGER.info("Finding release " + identifier);
                return service.read(identifier);
            }
            catch (DatabaseException de)
            {
                LOGGER.error("Failed to delete " + identifier + " reason: ", de.getCause());
                return null;
            }
        }
        return null;
    }
}
