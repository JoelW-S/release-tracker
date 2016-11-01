package com.joelws.release.tracker.endpoint.release


import com.joelws.release.tracker.interfaces.BusinessService
import com.joelws.release.tracker.interfaces.ResourceEndpoint
import ratpack.exec.Blocking
import ratpack.func.Action
import ratpack.handling.Chain

open class ReleaseEndpoint(service: BusinessService<String>) : ResourceEndpoint<String>(service) {

    override val endpoints: Action<Chain>
        get() = Action { chain ->
            chain
                    .prefix("release", { ctx ->
                        ctx
                                .path { ctx ->
                                    ctx
                                            .byMethod { s ->
                                                s
                                                        .get {
                                                            Blocking.get { service.list() }.then { generateResponse ->
                                                                generateResponse(ctx)
                                                            }
                                                        }
                                                        .post {
                                                            ctx.request.body.then { body ->
                                                                Blocking.get { service.create(body.text) }.then { generateResponse ->
                                                                    generateResponse(ctx)
                                                                }
                                                            }
                                                        }
                                                        .put {
                                                            ctx.request.body.then { body ->
                                                                Blocking.get { service.update(body.text) }.then { generateResponse ->
                                                                    generateResponse(ctx)
                                                                }
                                                            }
                                                        }

                                            }
                                }

                                .path(":name", { ctx ->
                                    ctx.byMethod { s ->
                                        val name = ctx.pathTokens["name"]
                                        s.get {
                                            Blocking.get { service.read(name!!) }.then { generateResponse ->
                                                generateResponse(ctx)
                                            }
                                        }
                                                .delete {
                                                    Blocking.get { service.delete(name!!) }.then { generateResponse ->
                                                        generateResponse(ctx)
                                                    }
                                                }
                                    }

                                })

                    })
        }

}
