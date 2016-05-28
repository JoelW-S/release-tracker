package com.github.joelws.release.tracker.interfaces;


import com.github.joelws.release.tracker.handler.JsonResponse;

import javax.ws.rs.core.Response;

public interface RestResponse {

    Response build(Integer status, Object entity);

    Response build(Integer status);

    Response build(final JsonResponse jsonResponse);
}
