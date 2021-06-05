package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Songs;

public class SongsDao {
	
	private Connection connection;
	private final String ALL_SONGS_QUERY = "Select * from songs";
	private final String CREATE_SONG_QUERY = "insert into songs (name, artist) values(?, ?)";
	private final String UPDATE_SONG_QUERY = "update songs set name = ? where id = ?";
	private final String DELETE_SONG_QUERY = "delete from songs where id = ?";
	
	public SongsDao() {
		connection = DBConnection.getInstance().getConnection();
	}
	public List<Songs> getAllSongs() throws SQLException {
		List<Songs> out = new ArrayList<>();
		ResultSet rs = connection.prepareStatement(ALL_SONGS_QUERY).executeQuery();
		while (rs.next()) {
			out.add(new Songs(rs.getInt("id"), rs.getString("name"), rs.getString("artist")));
		}
		return out;
	}
	
	public void createSong(String name, String artist) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_SONG_QUERY);
		ps.setString(1, name);
		ps.setString(2, artist);
		ps.executeUpdate();
	}
	public void updateSong(String name, int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_SONG_QUERY);
		ps.setString(1, name);
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	
	public void deleteSong(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_SONG_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void close() {
		DBConnection.getInstance().closeConnection();
	}
}
