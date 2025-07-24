package org.example;

import org.example.logic.ArtGallery;
import org.example.model.Art;
import org.example.service.ArtService;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ArtGalleryTest {

    private ArtGallery artGallery;
    private ArtService mockArtService;

    @BeforeEach
    void setUp() throws JSONException, IOException {
        mockArtService = mock(ArtService.class);
        artGallery = new ArtGallery(mockArtService);

        Art fakeNextArt = new Art("Starry Night", "Van Gogh", "img.jpg");
        when(mockArtService.getArtworkByIdSkippingInvalid(anyInt(), eq(1)))
                .thenReturn(fakeNextArt);

        Art fakePrevArt = new Art("Mona Lisa", "Da Vinci", "img.jpg");
        when(mockArtService.getArtworkByIdSkippingInvalid(anyInt(), eq(-1)))
                .thenReturn(fakePrevArt);
    }

    @Test
    void testGetNextArtwork_returnsArt_andUpdatesId() throws Exception {
        Art result = artGallery.getNextArtwork();
        assertEquals("Starry Night", result.getTitle());
        verify(mockArtService).getArtworkByIdSkippingInvalid(anyInt(), eq(1));
    }

    @Test
    void testGetPreviousArtwork_returnsArt_andUpdatesId() throws Exception {
        Art result = artGallery.getPreviousArtwork();
        assertEquals("Mona Lisa", result.getTitle());
        verify(mockArtService).getArtworkByIdSkippingInvalid(anyInt(), eq(-1));
    }

    @Test
    void testSetCurrentId_withinRange_setsId() {
        artGallery.setCurrentId(100);
        assertEquals(100, artGallery.getCurrentId());
    }

    @Test
    void testSetCurrentId_outOfRange_doesNotSetId() {
        artGallery.setCurrentId(-10);
        assertEquals(0, artGallery.getCurrentId());
    }

    @ParameterizedTest
    @CsvSource({
            "-10, 0",
            "0, 0",
            "5, 5",
            "100, 100",
            "129001, 0"
    })
    void testSetCurrentId(int inputId, int expectedId) {
        artGallery.setCurrentId(inputId);
        assertEquals(expectedId, artGallery.getCurrentId());
    }

}