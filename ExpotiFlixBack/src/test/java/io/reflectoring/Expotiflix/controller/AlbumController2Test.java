package io.reflectoring.Expotiflix.controller;

import io.reflectoring.Expotiflix.model.album.AlbumInfo;
import io.reflectoring.Expotiflix.service.AlbumService2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlbumController2Test {
    @Mock
    private AlbumService2 albumService2;

    @InjectMocks
    private AlbumController2 albumController2;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAlbumInfo_ReturnsAlbumInfoWithOK() {
        // Arrange
        AlbumInfo mockAlbum = new AlbumInfo();
        mockAlbum.setName("Test Album");

        when(albumService2.getAlbumInfo()).thenReturn(mockAlbum);

        // Act
        ResponseEntity<AlbumInfo> response = albumController2.getAlbumInfo();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Test Album", response.getBody().getName());
    }
}
