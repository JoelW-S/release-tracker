package com.joelws.release.tracker.roundtrip

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import com.jayway.restassured.RestAssured.*
import com.jayway.restassured.http.ContentType.JSON
import com.joelws.release.tracker.boot.Application
import com.joelws.release.tracker.configuration.DbUnitConfig
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.support.DirtiesContextTestExecutionListener
import ratpack.server.RatpackServer

@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = arrayOf(Application::class, DbUnitConfig::class))
@TestExecutionListeners(DependencyInjectionTestExecutionListener::class,
        DirtiesContextTestExecutionListener::class,
        TransactionDbUnitTestExecutionListener::class)
@TestPropertySource("/application-integration.properties")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
@DirtiesContext(classMode = BEFORE_CLASS)
open class ArtifactEndpointRoundTrip {

    @Autowired
    private lateinit var server: RatpackServer

    @Value("\${rest.service.port}")
    private var servicePort: Int? = null

    @Before
    fun setup() {
        server.start()
        port = servicePort!!
    }


    @Test
    fun canCreateArtifact() {
        given()
                .body("{\"groupId\" : \"com.github.joelws\", \"artifactId\" : \"example-project\", \"version\" : \"1.0\"}")
                .contentType(JSON)
                .`when`()
                .post("/artifact")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("artifactId", `is`("example-project"))
                .body("groupId", `is`("com.github.joelws"))
                .body("version", `is`("1.0"))

        given()
                .queryParam("query", "{\"groupId\" : \"com.github.joelws\", \"artifactId\" : \"example-project\", \"version\" : \"1.0\"}")
                .contentType(JSON)
                .`when`()
                .get("/artifact")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("artifactId", `is`("example-project"))
                .body("groupId", `is`("com.github.joelws"))
                .body("version", `is`("1.0"))


    }

    @Test
    @DatabaseSetup("/META-INF/db-unit/test-data.xml")
    fun canReadArtifact() {
        given()
                .queryParam("query", "{\"groupId\" : \"com.example\", \"artifactId\" : \"example-project\", \"version\" : \"1.0\"}")
                .`when`()
                .get("/artifact")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("artifactId", `is`("example-project"))
                .body("groupId", `is`("com.example"))
                .body("version", `is`("1.0"))
    }

    @Test
    @DatabaseSetup("/META-INF/db-unit/test-data.xml")
    fun canUpdateArtifact() {
        given()
                .body("{\"groupId\" : \"com.example\", \"artifactId\" : \"example-project\", \"version\" : \"1.0\"}")
                .contentType(JSON)
                .`when`()
                .put("/artifact")
                .then()
                .statusCode(405)
    }

    @Test
    @DatabaseSetup("/META-INF/db-unit/test-data.xml")
    fun canDeleteArtifact() {
        given()
                .queryParam("query", "{\"groupId\" : \"com.example\", \"artifactId\" : \"funky-example-project\", \"version\" : \"1.0-SNAPSHOT\"}")
                .contentType(JSON)
                .`when`()
                .delete("/artifact")
                .then()
                .statusCode(200)

        given()
                .queryParam("query", "{\"groupId\" : \"com.example\", \"artifactId\" : \"funky-example-project\", \"version\" : \"1.0-SNAPSHOT\"}")
                .`when`()
                .get("/artifact")
                .then()
                .statusCode(404)
                .contentType(JSON)
                .body("error", `is`("Artifact doesn't exist"))
    }

    @Test
    @DatabaseSetup("/META-INF/db-unit/test-data.xml")
    fun canListArtifact() {
        `when`()
                .get("/artifact")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("artifactId", containsInAnyOrder("example-project", "example-project", "funky-example-project"))
                .body("groupId", containsInAnyOrder("com.example", "com.example", "com.example"))
                .body("version", containsInAnyOrder("1.0", "1.1", "1.0-SNAPSHOT"))
    }
}
