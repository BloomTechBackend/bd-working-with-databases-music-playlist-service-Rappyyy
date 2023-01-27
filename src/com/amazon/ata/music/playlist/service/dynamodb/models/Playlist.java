package com.amazon.ata.music.playlist.service.dynamodb.models;

import com.amazon.ata.music.playlist.service.converters.AlbumTrackLinkedListConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Represents a record in the playlists table.
 */
@DynamoDBTable(tableName = "playlists")
public class Playlist {
    private String id;
    private LinkedList<AlbumTrack> songList;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String name;
    private String customerId;
    private List<AlbumTrack> listSongs;
    private Integer songCount;
    private Set<String> tags;
    @DynamoDBRangeKey(attributeName = "name")
    public String getName() {
        return name;
    }

    public Playlist setName(String name) {
        this.name = name;
        return this;
    }
    @DynamoDBAttribute(attributeName = "Id")
    public String getCustomerId() {
        return customerId;
    }

    public Playlist setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }
    @DynamoDBAttribute(attributeName = "songs")
    public List<AlbumTrack> getListSongs() {
        return listSongs;
    }

    public Playlist setListSongs(List<AlbumTrack> listSongs) {
        this.listSongs = listSongs;
        return this;
    }
    @DynamoDBAttribute(attributeName = "songCount")
    public Integer getSongCount() {
        return songCount;
    }

    public Playlist setSongCount(Integer songCount) {
        this.songCount = songCount;
        return this;
    }
    @DynamoDBAttribute(attributeName = "tags")
    public Set<String> getTags() {
        return tags;
    }

    public Playlist setTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }

    @DynamoDBTypeConverted(converter = AlbumTrackLinkedListConverter.class)
    @DynamoDBAttribute(attributeName = "songList")
    public LinkedList<AlbumTrack> getSongList() {
        return songList;
    }

    public void setSongList(LinkedList<AlbumTrack> songList) {
        this.songList = songList;
    }
}
