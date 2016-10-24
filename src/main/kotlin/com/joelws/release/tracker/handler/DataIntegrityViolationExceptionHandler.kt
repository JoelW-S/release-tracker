package com.joelws.release.tracker.handler

import com.joelws.release.tracker.response.ErrorMessage
import com.joelws.release.tracker.response.build
import org.springframework.dao.DataIntegrityViolationException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper

/*
Copyright 2016 Joel Whittaker-Smith

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/class DataIntegrityViolationExceptionHandler : ExceptionMapper<DataIntegrityViolationException> {
    override fun toResponse(exception: DataIntegrityViolationException): Response {
        return ErrorMessage.ENTITY_DEPENDENCY.response.build()
    }
}
