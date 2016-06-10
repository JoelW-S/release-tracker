package com.github.joelws.release.tracker.dao.release;

import com.github.joelws.release.tracker.dao.GenericDaoImpl;
import com.github.joelws.release.tracker.entity.release.Release;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReleaseDaoImpl extends GenericDaoImpl<Release, String> implements ReleaseDao {

    public ReleaseDaoImpl() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Release> list() {
        final String query = "SELECT r FROM Release r";
        return (List<Release>) getEntityManager().createQuery(query).getResultList();
    }
}
