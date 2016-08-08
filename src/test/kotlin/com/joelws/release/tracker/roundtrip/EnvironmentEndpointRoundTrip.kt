package com.joelws.release.tracker.roundtrip

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.jayway.restassured.RestAssured.given
import com.jayway.restassured.RestAssured.port
import com.jayway.restassured.http.ContentType.JSON
import com.joelws.release.tracker.boot.Application
import com.joelws.release.tracker.configuration.DbUnitConfig
import com.joelws.release.tracker.model.artifact.ArtifactModel
import com.joelws.release.tracker.model.release.ReleaseModel
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.support.DirtiesContextTestExecutionListener

@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = arrayOf(Application::class, DbUnitConfig::class))
@TestExecutionListeners(DependencyInjectionTestExecutionListener::class,
        DirtiesContextTestExecutionListener::class,
        TransactionDbUnitTestExecutionListener::class)
@TestPropertySource("/application-integration.properties")
@DirtiesContext(classMode = BEFORE_CLASS)
open class EnvironmentEndpointRoundTrip {

    @Value("\${rest.service.port}")
    var servicePort: Int? = null

    @Before
    fun setup() {
        port = servicePort!!
    }


    @Test
    @DatabaseSetup("/META-INF/db-unit/test-data.xml")
    fun canCreateEnvironment() {
        given()
                .body("{\"name\" : \"ProdTest\",\"release\" : {\"name\" : \"ExampleRelease\"}}")
                .contentType(JSON)
                .`when`()
                .post("/environment")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("ProdTest"))
                .body("release.name", `is`("ExampleRelease"))
                .body("release.artifacts", `is`(emptyList<ArtifactModel>()))
                .body("release.hotfixes", `is`(emptyList<ReleaseModel>()))

        given()
                .pathParam("name", "ProdTest")
                .`when`()
                .get("/environment/{name}")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("ProdTest"))
                .body("release.name", `is`("ExampleRelease"))
                .body("release.artifacts", `is`(emptyList<ArtifactModel>()))
                .body("release.hotfixes", `is`(emptyList<ReleaseModel>()))

    }

    @Test
    @DatabaseSetup("/META-INF/db-unit/test-data.xml")
    fun canUpdateEnvironment() {
        given()
                .body("{\"name\" : \"ProdTest\",\"release\" : {\"name\" : \"ExampleRelease\"}}")
                .contentType(JSON)
                .`when`()
                .post("/environment")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("ProdTest"))
                .body("release.name", `is`("ExampleRelease"))
                .body("release.artifacts", `is`(emptyList<ArtifactModel>()))
                .body("release.hotfixes", `is`(emptyList<ReleaseModel>()))

        given()
                .body("{\"name\" : \"ProdTest\",\"release\" : {\"name\" : \"ExampleHotfix\"}}")
                .contentType(JSON)
                .`when`()
                .put("/environment")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("ProdTest"))
                .body("release.name", `is`("ExampleHotfix"))
                .body("release.artifacts.groupId", `is`(listOf("com.example")))
                .body("release.artifacts.artifactId", `is`(listOf("example-project")))
                .body("release.artifacts.version", `is`(listOf("1.0")))
                .body("release.hotfixes", `is`(emptyList<ReleaseModel>()))
    }

    @Test
    @DatabaseSetup("/META-INF/db-unit/test-data.xml")
    fun canDeleteEnvironment() {
        given()
                .body("{\"name\" : \"ProdTest\",\"release\" : {\"name\" : \"ExampleRelease\"}}")
                .contentType(JSON)
                .`when`()
                .post("/environment")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("ProdTest"))
                .body("release.name", `is`("ExampleRelease"))
                .body("release.artifacts", `is`(emptyList<ArtifactModel>()))
                .body("release.hotfixes", `is`(emptyList<ReleaseModel>()))

        given()
                .pathParam("name", "ProdTest")
                .`when`()
                .delete("/environment/{name}")
                .then()
                .statusCode(200)

        given()
                .pathParam("name", "ProdTest")
                .`when`()
                .get("/environment/{name}")
                .then()
                .statusCode(404)
                .contentType(JSON)
                .body("error", `is`("Environment doesn't exist"))

    }

    @Test
    @DatabaseSetup("/META-INF/db-unit/test-data.xml")
    fun canListEnvironment() {
        given()
                .body("{\"name\" : \"ProdTest\",\"release\" : {\"name\" : \"ExampleRelease\"}}")
                .contentType(JSON)
                .`when`()
                .post("/environment")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("ProdTest"))
                .body("release.name", `is`("ExampleRelease"))
                .body("release.artifacts", `is`(emptyList<ArtifactModel>()))
                .body("release.hotfixes", `is`(emptyList<ReleaseModel>()))

        given()
                .`when`()
                .get("/environment")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`(listOf("ProdTest")))
                .body("release.name", `is`(listOf("ExampleRelease")))
                .body("release.artifacts", `is`(listOf(emptyList<ArtifactModel>())))
                .body("release.hotfixes", `is`(listOf(emptyList<ReleaseModel>())))


    }

}
