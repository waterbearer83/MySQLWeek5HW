package entity;

public class Songs {
	
	private int id;
	private String name;
	private String artist;
	
	public Songs(int id, String name, String artist) {
		this.setId(id);
		this.setName(name);
		this.setArtist(artist);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name.toUpperCase();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist.toUpperCase();
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

}
