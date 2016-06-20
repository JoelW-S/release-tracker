package com.github.joelws.release.tracker.dao.release

import com.github.joelws.release.tracker.entity.release.Release
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import javax.persistence.EntityManager
import javax.persistence.EntityTransaction
import javax.persistence.Query

class ReleaseDaoImplTest {

    private lateinit var mockEntityManager: EntityManager

    private lateinit var mockEntityTransaction: EntityTransaction

    private lateinit var mockQuery: Query

    private var subject = ReleaseDaoImpl()

    private var testRelease = Release()

    @Before
    fun setUp() {

        mockEntityManager = mock(EntityManager::class.java)
        mockEntityTransaction = mock(EntityTransaction::class.java)
        mockQuery = mock(Query::class.java)

        subject.entityManager = mockEntityManager

        testRelease = Release(name = "R1")
    }

    @Test
    fun testList() {
        val mockedResultList: List<Release> = arrayListOf(testRelease)

        `when`(mockEntityManager.transaction).thenReturn(mockEntityTransaction)

        `when`(mockEntityManager.createQuery(anyString())).thenReturn(mockQuery)

        `when`(mockQuery.resultList).thenReturn(mockedResultList)

        val result = subject.list()

        verify(mockEntityManager).createQuery(anyString())

        assertEquals(mockedResultList, result)
    }
}