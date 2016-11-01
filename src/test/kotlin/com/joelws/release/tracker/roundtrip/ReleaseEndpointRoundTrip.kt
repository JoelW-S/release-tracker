package com.joelws.release.tracker.roundtrip

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import com.jayway.restassured.RestAssured.*
import com.jayway.restassured.http.ContentType.JSON
import com.joelws.release.tracker.boot.Application
import com.joelws.release.tracker.configuration.DbUnitConfig
import com.joelws.release.tracker.model.artifact.ArtifactModel
import com.joelws.release.tracker.model.release.ReleaseModel
import org.hamcrest.Matchers
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.hasItems
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
open class ReleaseEndpointRoundTrip {

    @Autowired
    private lateinit var server: RatpackServer

    @Value("\${rest.service.port}")
    var servicePort: Int? = null

    @Before
    fun setup() {
        server.start()
        port = servicePort!!
    }


    @Test
    fun canCreateReleaseUsingDefaults() {
        given()
                .body("{\"name\" : \"Release1\"}")
                .contentType(JSON)
                .`when`()
                .post("/release")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("Release1"))
                .body("artifacts", `is`(emptyList<ArtifactModel>()))
                .body("hotfixes", `is`(emptyList<ReleaseModel>()))

        given()
                .pathParam("name", "Release1")
                .`when`()
                .get("/release/{name}")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("Release1"))
                .body("artifacts", `is`(emptyList<ArtifactModel>()))
                .body("hotfixes", `is`(emptyList<ReleaseModel>()))
    }

    @Test
    @DatabaseSetup("/META-INF/db-unit/test-data.xml")
    fun canCreateReleaseWithArtifact() {
        given()
                .body("{\"name\" : \"Release1\", \"artifacts\" : [{\"groupId\" : \"com.example\", \"artifactId\" : \"funky-example-project\", \"version\" : \"1.0-SNAPSHOT\"}]}")
                .contentType(JSON)
                .`when`()
                .post("/release")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("Release1"))
                .body("artifacts.artifactId", hasItems("funky-example-project"))
                .body("artifacts.groupId", `is`(listOf("com.example")))
                .body("artifacts.version", `is`(listOf("1.0-SNAPSHOT")))
                .body("hotfixes", `is`(emptyList<ReleaseModel>()))

        given()
                .pathParam("name", "Release1")
                .`when`()
                .get("/release/{name}")
                .then()
                .statusCode(200)
                .contentType(JSON)

                .body("name", `is`("Release1"))

                .body("artifacts.artifactId", `is`(listOf("funky-example-project")))
                .body("artifacts.groupId", `is`(listOf("com.example")))
                .body("artifacts.version", `is`(listOf("1.0-SNAPSHOT")))

                .body("hotfixes", `is`(emptyList<ReleaseModel>()))
    }

    @Test
    @DatabaseSetup("/META-INF/db-unit/test-data.xml")
    fun canCreateRelease() {
        given()
                .body("{\"name\" : \"Release1\", \"artifacts\" : [{\"groupId\" : \"com.example\", \"artifactId\" : \"funky-example-project\", \"version\" : \"1.0-SNAPSHOT\"}], \"hotfixes\": [{\"name\" : \"ExampleHotfix\" }]}")
                .contentType(JSON)
                .`when`()
                .post("/release")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("Release1"))

                .body("artifacts.artifactId", `is`(listOf("funky-example-project")))
                .body("artifacts.groupId", `is`(listOf("com.example")))
                .body("artifacts.version", `is`(listOf("1.0-SNAPSHOT")))
                .body("hotfixes.name", `is`(listOf("ExampleHotfix")))

        given()
                .pathParam("name", "Release1")
                .`when`()
                .get("/release/{name}")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("Release1"))

                .body("artifacts.artifactId", `is`(listOf("funky-example-project")))
                .body("artifacts.groupId", `is`(listOf("com.example")))
                .body("artifacts.version", `is`(listOf("1.0-SNAPSHOT")))

                .body("hotfixes.name", `is`(listOf("ExampleHotfix")))
                .body("hotfixes.artifacts.artifactId", `is`(listOf(listOf("example-project"))))
                .body("hotfixes.artifacts.groupId", `is`(listOf(listOf("com.example"))))
                .body("hotfixes.artifacts.version", `is`(listOf(listOf("1.0"))))
                .body("hotfixes.hotfixes", `is`(listOf(emptyList<ReleaseModel>())))
    }

    @Test
    @DatabaseSetup("/META-INF/db-unit/test-data.xml")
    fun canUpdateReleaseWithArtifact() {
        given()
                .pathParam("name", "ExampleRelease")
                .`when`()
                .get("/release/{name}")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("ExampleRelease"))
                .body("artifacts", `is`(emptyList<ArtifactModel>()))
                .body("hotfixes", `is`(emptyList<ReleaseModel>()))

        given()
                .body("{\"name\" : \"ExampleRelease\", \"artifacts\" : [{\"groupId\" : \"com.example\", \"artifactId\" : \"funky-example-project\", \"version\" : \"1.0-SNAPSHOT\"}]}")
                .contentType(JSON)
                .`when`()
                .put("/release")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("ExampleRelease"))
                .body("artifacts.artifactId", `is`(listOf("funky-example-project")))
                .body("artifacts.groupId", `is`(listOf("com.example")))
                .body("artifacts.version", `is`(listOf("1.0-SNAPSHOT")))
                .body("hotfixes", `is`(emptyList<ReleaseModel>()))
    }

    @Test
    @DatabaseSetup("/META-INF/db-unit/test-data.xml")
    fun canUpdateRelease() {
        given()
                .pathParam("name", "ExampleRelease")
                .`when`()
                .get("/release/{name}")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("ExampleRelease"))
                .body("artifacts", `is`(emptyList<ArtifactModel>()))
                .body("hotfixes", `is`(emptyList<ReleaseModel>()))

        given()
                .body("{\"name\" : \"ExampleRelease\", \"artifacts\" : [{\"groupId\" : \"com.example\", \"artifactId\" : \"funky-example-project\", \"version\" : \"1.0-SNAPSHOT\"}], \"hotfixes\": [{\"name\" : \"ExampleHotfix\" }]}")
                .contentType(JSON)
                .`when`()
                .put("/release")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("ExampleRelease"))

                .body("artifacts.artifactId", `is`(listOf("funky-example-project")))
                .body("artifacts.groupId", `is`(listOf("com.example")))
                .body("artifacts.version", `is`(listOf("1.0-SNAPSHOT")))

                .body("hotfixes.name", `is`(listOf("ExampleHotfix")))
                .body("hotfixes.artifacts.artifactId", `is`(listOf(listOf("example-project"))))
                .body("hotfixes.artifacts.groupId", `is`(listOf(listOf("com.example"))))
                .body("hotfixes.artifacts.version", `is`(listOf(listOf("1.0"))))

                .body("hotfixes.hotfixes", `is`(listOf(emptyList<ReleaseModel>())))
    }

    @Test
    @DatabaseSetup("/META-INF/db-unit/test-data.xml")
    fun canDeleteRelease() {
        given()
                .pathParam("name", "ExampleRelease")
                .`when`()
                .get("/release/{name}")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("ExampleRelease"))
                .body("artifacts", `is`(emptyList<ArtifactModel>()))
                .body("hotfixes", `is`(emptyList<ReleaseModel>()))

        given()
                .pathParam("name", "ExampleRelease")
                .`when`()
                .delete("/release/{name}")
                .then()
                .statusCode(200)

        given()
                .pathParam("name", "ExampleRelease")
                .`when`()
                .get("/release/{name}")
                .then()
                .statusCode(404)
                .contentType(JSON)
                .body("error", `is`("Release doesn't exist"))
    }

    @Test
    @DatabaseSetup("/META-INF/db-unit/test-data.xml")
    fun canDeleteReleaseWhichHasDependent() {
        given()
                .pathParam("name", "ReleaseWithHotfix")
                .`when`()
                .get("/release/{name}")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", `is`("ReleaseWithHotfix"))
                .body("artifacts", `is`(emptyList<ArtifactModel>()))

                .body("hotfixes.name", `is`(listOf("ExampleHotfix")))
                .body("hotfixes.artifacts.artifactId", `is`(listOf(listOf("example-project"))))
                .body("hotfixes.artifacts.groupId", `is`(listOf(listOf("com.example"))))
                .body("hotfixes.artifacts.version", `is`(listOf(listOf("1.0"))))

        given()
                .pathParam("name", "ExampleHotfix")
                .`when`()
                .delete("/release/{name}")
                .then()
                .statusCode(400)
                .contentType(JSON)
                .body("error", `is`("Release exists as another release's hotfix"))
    }

    @Test
    @DatabaseSetup("/META-INF/db-unit/test-data.xml")
    fun canListRelease() {
        `when`()
                .get("/release")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("name", Matchers.containsInAnyOrder("ReleaseWithHotfix", "ExampleHotfix", "ExampleRelease"))
                .body("artifacts.groupId", `is`(listOf(listOf("com.example"), emptyList(), emptyList())))
                .body("artifacts.artifactId", `is`(listOf(listOf("example-project"), emptyList<String>(), emptyList<String>())))
                .body("artifacts.version", `is`(listOf(listOf("1.0"), emptyList<String>(), emptyList<String>())))
    }
}
