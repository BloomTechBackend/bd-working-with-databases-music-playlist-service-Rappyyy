package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.models.requests.CreatePlaylistRequest;
import com.amazon.ata.music.playlist.service.models.requests.GetPlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.CreatePlaylistResult;
import com.amazon.ata.music.playlist.service.models.results.GetPlaylistResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreatePlaylistActivityTest {
    @Mock
    private PlaylistDao playlistDao;
    private GetPlaylistActivity getPlaylistActivity;
    private CreatePlaylistActivity createPlaylistActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getPlaylistActivity = new GetPlaylistActivity(playlistDao);
        createPlaylistActivity = new CreatePlaylistActivity(playlistDao);
    }

    @Test
    public void handleRequest_savedPlaylistFound_returnsPlaylistModelInResult() {
        // GIVEN
        String expectedId = "expectedCreatedId";
        String expectedName = "expectedCreatedName";
        String expectedCustomerId = "expectedCreatedCustomerId";
        int expectedSongCount = 1;
        List<String> expectedTags = Lists.newArrayList("CreatedTag");

        Playlist playlist = new Playlist();
        playlist.setId(expectedId);
        playlist.setName(expectedName);
        playlist.setCustomerId(expectedCustomerId);
        playlist.setSongCount(expectedSongCount);
        playlist.setTags(Sets.newHashSet(expectedTags));

        when(playlistDao.getPlaylist(expectedId)).thenReturn(playlist);

        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
                .withCustomerId(expectedId)
                .withTags(expectedTags)
                .withName(expectedName)
                .build();

        // WHEN
        CreatePlaylistResult result = createPlaylistActivity.handleRequest(request, null);

        GetPlaylistRequest requestPlaylist = GetPlaylistRequest.builder()
                .withId(expectedId)
                .build();

        // WHEN
        GetPlaylistResult getResult = getPlaylistActivity.handleRequest(requestPlaylist, null);

        // THEN
        assertEquals(expectedId, getResult.getPlaylist().getId());
        assertEquals(expectedName, getResult.getPlaylist().getName());
        assertEquals(expectedCustomerId, getResult.getPlaylist().getCustomerId());
        assertEquals(expectedSongCount, getResult.getPlaylist().getSongCount());
        assertEquals(expectedTags, getResult.getPlaylist().getTags());
    }
}