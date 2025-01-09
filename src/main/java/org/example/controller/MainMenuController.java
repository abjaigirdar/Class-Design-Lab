package org.example.controller;

import org.example.model.Album;
import org.example.model.Artist;
import org.example.model.Song;
import org.example.repo.AlbumRepo;
import org.example.repo.ArtistRepo;
import org.example.view.ConsoleIO;

public class MainMenuController {
    ConsoleIO io;
    AlbumRepo albums;
    ArtistRepo artists;
    // TODO: add Users user
    private static final int CHOICE_VIEW_ALL_ALBUMS = 1;
    private static final int CHOICE_VIEW_ALBUMS_BY_ARTIST = 2;
    private static final int CHOICE_VIEW_ALBUM = 3;
    private static final int LOGIN = 4;
    private static final int CHOICE_QUIT = 5;
    private static final int MIN_CHOICE = 1;
    private static final int MAX_CHOICE = 4;

    public MainMenuController(ConsoleIO io, AlbumRepo albums, ArtistRepo artists) {
        this.albums = albums;
        this.artists = artists;
        this.io = io;
    }

    public void run() {
        io.writeMessage("========================");
        io.writeMessage("Bodacious Music Database");
        io.writeMessage("========================");
//        io.writeMessage("\n\n");

        boolean running = true;

        while (running) {
            printMainMenuHeader();
            int choice = io.getIntegerInBetween("> ", MIN_CHOICE, MAX_CHOICE);

            // TODO: add a while loop to see if any user is already logged in. So I think I'll be using a boolean
            //  userLoggedIn to false so I can show the option for login, else there won't be a login option.

            switch (choice) {
                case CHOICE_VIEW_ALL_ALBUMS:
                    doTheThing();
                    break;
                case CHOICE_VIEW_ALBUMS_BY_ARTIST:
                    viewAlbumsByArtist();
                    break;
                case CHOICE_VIEW_ALBUM:
                    viewAlbum();
                    break;
                case LOGIN:
                    printSelectUserMenu();

                    // TODO: once the user is selected an logged in, print the main menu again

                    break;
                case CHOICE_QUIT:
                    io.writeMessage("Goodbye!");
                    running = false;
                    break;
            }
        }
    }




    private void viewAlbum() {
        int albumId = io.getInteger("Enter album id:");
        Album album = albums.getAlbum(albumId);
        if (album != null) {
            io.writeMessage(">>>> Album Details >>>>");
            io.writeMessage(album.getTitle());
            io.writeMessage(album.getArtist().getName());
            io.writeMessage("");
            for (Song s : album.getSongs()) {
                io.writeMessage(s.getId() + ": " + s.getTitle());
            }
        }
        io.writeMessage("");
    }

    private void viewAlbumsByArtist() {
        io.writeMessage(">>> Artists");
        for (Artist artist : artists.getAllArtists()) {
            io.writeMessage(artist.getId() + "  -  " + artist.getName());
        }

        int choice = io.getInteger("Select an artist");
        Artist artist = artists.getArtist(choice);

        if (artist == null) {
            io.writeMessage("Invalid artist selected.");
            return;
        }

        Album[] discography = albums.getAlbumsByArtist(artist);
        if (discography.length == 0) {
            io.writeMessage("No albums by this artist exist.");
            return;
        }

        for (Album album : discography) {
            io.writeMessage(album.getId() + "  -  " + album.getTitle());
        }
    }

    private void doTheThing() {
        // List all the albums in the repository
        Album[] discography = albums.getAllAlbums();
        if (discography.length == 0) {
            io.writeMessage("No albums exist.");
            return;
        }

        for (Album album : discography) {
            io.writeMessage(album.getId() + "  -  " + album.getTitle() + "  -  " + album.getArtist().getName());
        }
    }

    /*
    public void printMainMenuHeader() {
        io.writeMessage(">>> Main Menu <<<");

        // TODO: show which user is logged in IF anyone is logged in

        io.writeMessage(CHOICE_VIEW_ALL_ALBUMS + "  -  View All Albums");
        io.writeMessage(CHOICE_VIEW_ALBUMS_BY_ARTIST + "  -  View Albums by Artist");
        io.writeMessage(CHOICE_VIEW_ALBUM + "  -  View Album Details");
        io.writeMessage(LOGIN + "  -  Login");
        io.writeMessage(CHOICE_QUIT + "  -  Quit");
    }
     */

    public void printSelectUserMenu() {
        // TODO: add a method that will print a list of users
        io.writeMessage(">>> Select User Menu <<<");

    }
}
