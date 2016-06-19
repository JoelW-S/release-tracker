package com.github.joelws.release.tracker.dao.artifact

import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.entity.artifact.ArtifactPK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner
import javax.persistence.EntityManager
import javax.persistence.EntityTransaction
import javax.persistence.Query

@RunWith(MockitoJUnitRunner::class)
class ArtifactDaoImplTest {

    @Mock
    private var mockEntityManager: EntityManager? = null

    @Mock
    private var mockEntityTransaction: EntityTransaction? = null

    @Mock
    private var mockQuery: Query? = null

    private var subject = ArtifactDaoImpl()

    private var testArtifact = Artifact()

    @Before
    fun setUp() {

        subject.entityManager = mockEntityManager

        testArtifact = Artifact(id = ArtifactPK().apply {
            groupId = "example.group.id"
            artifactId = "artifact"
            version = "1.0"
        })

    }

    @Test
    fun testList() {
        val mockedResultList: List<Artifact> = arrayListOf(testArtifact)

        `when`(mockEntityManager?.transaction).thenReturn(mockEntityTransaction)

        `when`(mockEntityManager?.createQuery(anyString())).thenReturn(mockQuery)

        `when`(mockQuery?.resultList).thenReturn(mockedResultList)

        val result = subject.list()

        verify(mockEntityManager)?.createQuery(anyString())?.resultList

        assertEquals(mockedResultList, result)

    }
}