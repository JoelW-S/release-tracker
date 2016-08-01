package com.joelws.release.tracker.dao.release

import com.joelws.release.tracker.dao.GenericDao
import com.joelws.release.tracker.entity.release.Release

interface ReleaseDao : GenericDao<Release, String> {
    fun list(): List<Release>
}
