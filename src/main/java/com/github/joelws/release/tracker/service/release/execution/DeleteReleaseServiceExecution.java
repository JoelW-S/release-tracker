package com.github.joelws.release.tracker.service.release.execution;

import com.github.joelws.release.tracker.dao.release.ReleaseDao;
import com.github.joelws.release.tracker.service.ServiceExecution;

public class DeleteReleaseServiceExecution implements ServiceExecution<String, Void> {
    private final ReleaseDao releaseDao;

    public DeleteReleaseServiceExecution(ReleaseDao releaseDao) {
        this.releaseDao = releaseDao;
    }

    @Override
    public Void execute(String identifier) {
        releaseDao.delete(identifier);
        return null;
    }
}
