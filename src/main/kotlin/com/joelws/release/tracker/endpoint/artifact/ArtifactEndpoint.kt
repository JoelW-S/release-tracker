package com.joelws.release.tracker.endpoint.artifact

import com.joelws.release.tracker.interfaces.BusinessService
import com.joelws.release.tracker.interfaces.ResourceEndpoint
import ratpack.exec.Blocking
import ratpack.func.Action
import ratpack.handling.Chain

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
*/open class ArtifactEndpoint(service: BusinessService<String>) : ResourceEndpoint<String>(service) {
    override val endpoints: Action<Chain>
        get() = Action { chain ->
            chain
                    .path("artifact", { ctx ->
                        ctx
                                .byMethod { s ->
                                    s
                                            .get {
                                                val query = ctx.request.queryParams["query"]
                                                if (query == null) {
                                                    Blocking.get { service.list() }.then { generateResponse ->
                                                        generateResponse(ctx)
                                                    }
                                                } else {
                                                    service.read(query)(ctx)
                                                }
                                            }
                                            .post {
                                                ctx.request.body.then { body ->
                                                    Blocking.get { service.create(body.text) }.then { generateResponse ->
                                                        generateResponse(ctx)
                                                    }
                                                }
                                            }
                                            .delete {
                                                val query = ctx.request.queryParams["query"] ?: ""
                                                service.delete(query)(ctx)
                                            }
                                }

                    })

        }
}
