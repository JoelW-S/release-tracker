package com.github.joelws.release.tracker.interfaces;

import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;

public interface BusinessService<I> {
    @Transactional
    Response create(final String json);

    @Transactional
    Response read(final I identifier);

    @Transactional
    Response update(final String json);

    @Transactional
    Response delete(final I identifier);

    @Transactional
    Response list();
}
