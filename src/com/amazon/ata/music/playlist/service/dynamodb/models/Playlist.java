package com.amazon.ata.music.playlist.service.dynamodb.models;

import com.amazon.ata.music.playlist.service.converters.AlbumTrackLinkedListConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.sql.Struct;
import java.util.HashSet;
import java.util.List;

/**
 * Represents a record in the playlists table.
 */
@DynamoDBTable(tableName = "playlists")
public class Playlist {
    private String id;
    private List<AlbumTrack> songList;

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
    private HashSet<String> tags;
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
    public HashSet<String> getTags() {
        return tags;
    }

    public Playlist setTags(HashSet<String> tags) {
        this.tags = tags;
        return this;
    }

    @DynamoDBTypeConverted(converter = AlbumTrackLinkedListConverter.class)
    @DynamoDBAttribute(attributeName = "songList")
    public List<AlbumTrack> getSongList() {
        return songList;
    }

    public void setSongList(List<AlbumTrack> songList) {
        this.songList = songList;
    }
}
