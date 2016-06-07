package com.github.joelws.release.tracker.interfaces;

import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;

public interface BusinessService<I>
{
    @Transactional Response create(String json);

    @Transactional Response read(I identifier);

    @Transactional Response update(String json);

    @Transactional Response delete(I identifier);
}
