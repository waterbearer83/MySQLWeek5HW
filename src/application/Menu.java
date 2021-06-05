package application;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.SongsDao;
import entity.Songs;

public class Menu {
	
	private Scanner scanner = new Scanner(System.in);
	private String[] menuOpts = {"View Songs", "Create Song", "Update Song", "Delete Song"};
	private SongsDao songsDao = new SongsDao();
	
	private void printMenu() {
		System.out.println("---------------- \n Playlist");
		for (int i = 1; i <= menuOpts.length; i++) {
			System.out.println(i + ") " + menuOpts[i-1]);
		} System.out.print("----------------- \n Select an option: ");
	}
	private void viewSongs() throws SQLException {
		List<Songs> mySongs = songsDao.getAllSongs();
		System.out.println("All Songs:\nID Title \tArtist");
		for (Songs s:mySongs) {
			System.out.println(s.getId() + " " + s.getName() + " -- " + s.getArtist());
		}
	}
	private void createSong() throws SQLException {
		System.out.print("Enter Name of Song: ");
		String name = scanner.nextLine();
		System.out.print("Enter Artist name: ");
		String artist = scanner.nextLine();
		songsDao.createSong(name, artist);
	}
	private void updateSong() throws SQLException {
		System.out.println("Which song ID would you like to update?");
		viewSongs();
		int id = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter new song title: ");
		String name = scanner.nextLine();
		songsDao.updateSong(name, id);
	}
	
	private void deleteSong() throws SQLException {
		System.out.println("Which song ID would you like to delete?");
		viewSongs();
		int id = Integer.parseInt(scanner.nextLine());
		songsDao.deleteSong(id);
	}
	
	public void end() {
		System.out.println("Bye");
		scanner.close();
		songsDao.close();
	}
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			try {
				switch (selection) {
					case "1":
						viewSongs();
						break;
					case "2":
						createSong();
						break;
					case "3":
						updateSong();
						break;
					case "4":
						deleteSong();
						break;
					default:
						selection = "-1";
				}
			}catch (SQLException e) {
				e.printStackTrace();
				end();
			}
			if (!selection.equals("-1")) {
				System.out.println("Press enter to continue");
				scanner.nextLine();
			}
		} while (!selection.equals("-1"));
		end();
	}
}
