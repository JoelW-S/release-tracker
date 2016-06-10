package com.github.joelws.release.tracker.interfaces;


import com.github.joelws.release.tracker.handler.JsonResponse;

import javax.ws.rs.core.Response;

public interface RestResponse {

    Response build(final Integer status, final Object entity);

    Response build(final Integer status);

    Response build(final JsonResponse jsonResponse);
}
