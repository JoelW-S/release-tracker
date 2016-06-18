package com.github.joelws.release.tracker.service.release.execution;

import com.github.joelws.release.tracker.dao.release.ReleaseDao;
import com.github.joelws.release.tracker.entity.release.Release;
import com.github.joelws.release.tracker.service.ServiceExecution;

import java.util.List;

public class ListReleaseServiceExecution implements ServiceExecution<Void, List<Release>> {

    private final ReleaseDao releaseDao;

    public ListReleaseServiceExecution(ReleaseDao releaseDao) {
        this.releaseDao = releaseDao;
    }

    @Override
    public List<Release> execute(final Void param) {
        return releaseDao.list();
    }
}
