package com.github.joelws.release.tracker.service.release.execution;

import com.github.joelws.release.tracker.dao.release.ReleaseDao;
import com.github.joelws.release.tracker.entity.release.Release;
import com.github.joelws.release.tracker.service.ServiceExecution;

public class UpdateReleaseServiceExecution implements ServiceExecution<Release, Release> {
    private final ReleaseDao releaseDao;

    public UpdateReleaseServiceExecution(ReleaseDao releaseDao) {
        this.releaseDao = releaseDao;
    }

    @Override
    public Release execute(Release param) {
        return (releaseDao.read(param.getName()) != null) ? releaseDao.update(param) : null;
    }
}
