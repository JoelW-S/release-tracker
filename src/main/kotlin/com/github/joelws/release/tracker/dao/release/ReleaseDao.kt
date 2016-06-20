package com.github.joelws.release.tracker.dao.release

import com.github.joelws.release.tracker.dao.GenericDao
import com.github.joelws.release.tracker.entity.release.Release

interface ReleaseDao : GenericDao<Release, String> {
    fun list(): List<Release>
}
