package com.github.joelws.release.tracker.dao.release

import com.github.joelws.release.tracker.entity.release.Release
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import javax.persistence.EntityManager
import javax.persistence.EntityTransaction
import javax.persistence.Query

@RunWith(MockitoJUnitRunner::class)
class ReleaseDaoImplTest {

    @Mock
    private var mockEntityManager: EntityManager? = null

    @Mock
    private var mockEntityTransaction: EntityTransaction? = null

    @Mock
    private var mockQuery: Query? = null

    private var subject = ReleaseDaoImpl()

    private var testRelease = Release()

    @Before
    fun setUp() {

        subject.entityManager = mockEntityManager

        testRelease = Release(name = "R1")
    }

    @Test
    fun testList() {
        val mockedResultList: List<Release> = arrayListOf(testRelease)

        Mockito.`when`(mockEntityManager?.transaction).thenReturn(mockEntityTransaction)

        Mockito.`when`(mockEntityManager?.createQuery(Mockito.anyString())).thenReturn(mockQuery)

        Mockito.`when`(mockQuery?.resultList).thenReturn(mockedResultList)

        val result = subject.list()

        Mockito.verify(mockEntityManager)?.createQuery(Mockito.anyString())?.resultList

        assertEquals(mockedResultList, result)
    }
}