package com.github.joelws.release.tracker.dao.release;

import com.github.joelws.release.tracker.dao.GenericDao;
import com.github.joelws.release.tracker.entity.release.Release;

import java.util.List;

public interface ReleaseDao extends GenericDao<Release, String>
{
    List<Release> list();
}
