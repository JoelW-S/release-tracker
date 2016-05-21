package com.github.joelws.release.tracker.dao;

import com.github.joelws.release.tracker.common.GenericDaoImpl;
import com.github.joelws.release.tracker.model.Release;
import org.springframework.stereotype.Repository;

@Repository
public class ReleaseDaoImpl extends GenericDaoImpl<Release, String> implements ReleaseDao
{
    public ReleaseDaoImpl()
    {
        super(Release.class);
    }
}
