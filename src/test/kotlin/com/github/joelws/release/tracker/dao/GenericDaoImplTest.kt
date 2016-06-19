package com.github.joelws.release.tracker.dao

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
class GenericDaoImplTest {

    private class TestingGenericDao : GenericDaoImpl<EntityForTestPurpose, String>()

    private data class EntityForTestPurpose(val field: String = "TEST DATA")

    @Mock
    private var mockEntityManager: EntityManager? = null

    @Mock
    private var mockEntityTransaction: EntityTransaction? = null

    @Mock
    private var mockQuery: Query? = null

    private var subject = TestingGenericDao()

    private var testObject = EntityForTestPurpose()

    @Before
    fun setUp() {

        subject.entityManager = mockEntityManager

    }

    @Test
    fun testCreate() {

        `when`(mockEntityManager?.transaction).thenReturn(mockEntityTransaction)

        val result = subject.create(testObject)

        verify(mockEntityManager)?.persist(testObject)

        assertEquals(testObject, result)

    }

    @Test
    fun testRead() {

        `when`(mockEntityManager?.transaction).thenReturn(mockEntityTransaction)
        `when`(mockEntityManager?.find(any<Any>() as Class<*>?, anyString())).thenReturn(testObject)

        val result = subject.read(testObject.field)

        verify(mockEntityManager)?.find(EntityForTestPurpose::class.java, testObject.field)

        assertEquals(testObject, result);
    }

    @Test
    fun testUpdate() {

        `when`(mockEntityManager?.transaction).thenReturn(mockEntityTransaction)
        `when`(mockEntityManager?.merge(testObject)).thenReturn(testObject)
        val result = subject.update(testObject)

        verify(mockEntityManager)?.merge(testObject)

        assertEquals(testObject, result);

    }

    @Test
    fun testDelete() {

        `when`(mockEntityManager?.transaction)?.thenReturn(mockEntityTransaction)

        subject.delete(testObject.field)
        verify(mockEntityManager)?.remove(mockEntityManager?.getReference(EntityForTestPurpose::class.java, testObject.field))

    }
}