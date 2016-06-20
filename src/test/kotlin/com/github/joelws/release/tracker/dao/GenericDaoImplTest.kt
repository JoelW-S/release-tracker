package com.github.joelws.release.tracker.dao

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import javax.persistence.EntityManager
import javax.persistence.EntityTransaction
import javax.persistence.Query

class GenericDaoImplTest {

    private class TestingGenericDao : GenericDaoImpl<EntityForTestPurpose, String>()

    private data class EntityForTestPurpose(val field: String = "TEST DATA")

    private lateinit var mockEntityManager: EntityManager

    private lateinit var mockEntityTransaction: EntityTransaction

    private lateinit var mockQuery: Query

    private var subject = TestingGenericDao()

    private var testObject = EntityForTestPurpose()

    @Before
    fun setUp() {
        mockEntityManager = mock(EntityManager::class.java)
        mockEntityTransaction = mock(EntityTransaction::class.java)
        mockQuery = mock(Query::class.java)
        subject.entityManager = mockEntityManager

    }

    @Test
    fun testCreate() {

        `when`(mockEntityManager.transaction).thenReturn(mockEntityTransaction)

        val result = subject.create(testObject)

        verify(mockEntityManager).persist(testObject)

        assertEquals(testObject, result)

    }

    @Test
    fun testRead() {

        `when`(mockEntityManager.transaction).thenReturn(mockEntityTransaction)
        `when`(mockEntityManager.find(any(Class::class.java), anyString())).thenReturn(testObject)

        val result = subject.read(testObject.field)

        verify(mockEntityManager).find(EntityForTestPurpose::class.java, testObject.field)

        assertEquals(testObject, result)
    }

    @Test
    fun testUpdate() {

        `when`(mockEntityManager.transaction).thenReturn(mockEntityTransaction)
        `when`(mockEntityManager.merge(any(Any::class.java))).thenReturn(testObject)
        val result = subject.update(testObject)

        verify(mockEntityManager).merge(testObject)

        assertEquals(testObject, result)

    }

    @Test
    fun testDelete() {

        `when`(mockEntityManager.transaction).thenReturn(mockEntityTransaction)

        subject.delete(testObject.field)
        verify(mockEntityManager).remove(mockEntityManager.getReference(EntityForTestPurpose::class.java, testObject.field))

    }
}