package com.github.joelws.release.tracker.service.release.execution;


import com.github.joelws.release.tracker.dao.release.ReleaseDao;
import com.github.joelws.release.tracker.entity.release.Release;
import com.github.joelws.release.tracker.service.ServiceExecution;

public class ReadReleaseServiceExecution implements ServiceExecution<String, Release> {
    private final ReleaseDao releaseDao;

    public ReadReleaseServiceExecution(ReleaseDao releaseDao) {
        this.releaseDao = releaseDao;
    }

    @Override
    public Release execute(String identifier) {
        return releaseDao.read(identifier);
    }
}
