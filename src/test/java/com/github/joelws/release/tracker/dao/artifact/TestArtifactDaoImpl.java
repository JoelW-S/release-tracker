package com.github.joelws.release.tracker.dao.artifact;

import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.entity.artifact.ArtifactPK;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestArtifactDaoImpl {

    @Mock
    private EntityManager mockEntityManager;

    @Mock
    private EntityTransaction mockEntityTransaction;

    @Mock
    private Query mockQuery;

    private ArtifactDaoImpl subject;

    private ArtifactPK testArtifactPK;

    private Artifact testArtifact;

    @Before
    public void setUp() throws Exception {

        subject = new ArtifactDaoImpl();
        subject.setEntityManager(mockEntityManager);

        testArtifactPK = new ArtifactPK();
        testArtifactPK.setArtifactId("artifact");
        testArtifactPK.setGroupId("example.group.id");
        testArtifactPK.setVersion("1.0");

        testArtifact = new Artifact();
        testArtifact.setId(testArtifactPK);
    }

    @Test
    public void testList() throws Exception {

    }

    @Test
    public void testCreate() throws Exception {

        when(mockEntityManager.getTransaction()).thenReturn(mockEntityTransaction);

        final Artifact result = subject.create(testArtifact);

        verify(mockEntityManager).persist(testArtifact);

        assertEquals(testArtifact.getId().getArtifactId(), result.getId().getArtifactId());
        assertEquals(testArtifact.getId().getGroupId(), result.getId().getGroupId());
        assertEquals(testArtifact.getId().getVersion(), result.getId().getVersion());

    }

    @Test
    public void testRead() throws Exception {

        when(mockEntityManager.getTransaction()).thenReturn(mockEntityTransaction);
        when(mockEntityManager.find(any(), any(ArtifactPK.class))).thenReturn(testArtifact);

        final Artifact result = subject.read(testArtifactPK);

        verify(mockEntityManager).find(Artifact.class, testArtifactPK);

        assertEquals(testArtifact.getId().getArtifactId(), result.getId().getArtifactId());
        assertEquals(testArtifact.getId().getGroupId(), result.getId().getGroupId());
        assertEquals(testArtifact.getId().getVersion(), result.getId().getVersion());
    }

    @Test
    public void testUpdate() throws Exception {

        when(mockEntityManager.getTransaction()).thenReturn(mockEntityTransaction);
        when(mockEntityManager.merge(testArtifact)).thenReturn(testArtifact);
        final Artifact result = subject.update(testArtifact);

        verify(mockEntityManager).merge(testArtifact);

        assertEquals(testArtifact.getId().getArtifactId(), result.getId().getArtifactId());
        assertEquals(testArtifact.getId().getGroupId(), result.getId().getGroupId());
        assertEquals(testArtifact.getId().getVersion(), result.getId().getVersion());
    }

    @Test
    public void testDelete() throws Exception {

        when(mockEntityManager.getTransaction()).thenReturn(mockEntityTransaction);

        subject.delete(testArtifactPK);
        verify(mockEntityManager).remove(mockEntityManager.getReference(Artifact.class, testArtifactPK));

    }
}