package com.joelws.release.tracker.dao.artifact

import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.entity.artifact.ArtifactPK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import javax.persistence.EntityManager
import javax.persistence.EntityTransaction
import javax.persistence.Query

class ArtifactDaoImplTest {

    private lateinit var mockEntityManager: EntityManager

    private lateinit var mockEntityTransaction: EntityTransaction

    private lateinit var mockQuery: Query

    private var subject = ArtifactDaoImpl()

    private lateinit var testArtifact: Artifact

    @Before
    fun setUp() {

        mockEntityManager = mock(EntityManager::class.java)
        mockEntityTransaction = mock(EntityTransaction::class.java)
        mockQuery = mock(Query::class.java)

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

        `when`(mockEntityManager.transaction).thenReturn(mockEntityTransaction)

        `when`(mockEntityManager.createQuery(anyString())).thenReturn(mockQuery)

        `when`(mockQuery.resultList).thenReturn(mockedResultList)

        val result = subject.list()

        verify(mockEntityManager).createQuery(anyString())

        assertEquals(mockedResultList, result)

    }
}
