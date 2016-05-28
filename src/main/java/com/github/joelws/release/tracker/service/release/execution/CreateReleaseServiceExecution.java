package com.github.joelws.release.tracker.service.release.execution;

import com.github.joelws.release.tracker.dao.release.ReleaseDao;
import com.github.joelws.release.tracker.entity.release.Release;
import com.github.joelws.release.tracker.service.ServiceExecution;

public class CreateReleaseServiceExecution implements ServiceExecution<Release, Release>
{
    private final ReleaseDao releaseDao;

    public CreateReleaseServiceExecution(ReleaseDao releaseDao)
    {
        this.releaseDao = releaseDao;
    }

    @Override public Release execute(Release param)
    {
        return (releaseDao.read(param.getName()) == null) ? releaseDao.create(param) : null;
    }
}
