/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.dvdlibrary.ui;

import com.sk.dvdlibrary.dto.DvdLibrary;
import java.util.List;

/**
 *
 * @author steve
 */
public class DvdLibraryView {
    
    private UserIO io;
    
    public DvdLibraryView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print("DVD Library Main Menu");
        io.print("1. List DVDs");
        io.print("2. Create New DVD");
        io.print("3. View DVD");
        io.print("4. Edit DVD");
        io.print("5. Remove DVD");
        io.print("6. Exit");
        
        return io.readInt("Please select from the above.", 1, 6);
    }
    
    public DvdLibrary getNewDvdInfo() {
        String title = io.readString("Please enter the Title");
        String releaseDate = io.readString("Please enter the Release Date");
        String mpaaRating = io.readString("Please enter the MPAA Rating");
        String directorName = io.readString("Please enter the Director's Name");
        String studio = io.readString("Please enter the Studio's Name");
        String userRating = io.readString("Please enter your comments of the movie");
        DvdLibrary currentDvdLibrary = new DvdLibrary(title);
        currentDvdLibrary.setTitle(title);
        currentDvdLibrary.setReleaseDate(releaseDate);
        currentDvdLibrary.setMpaaRating(mpaaRating);
        currentDvdLibrary.setDirectorName(directorName);
        currentDvdLibrary.setStudio(studio);
        currentDvdLibrary.setUserRating(userRating);
        return currentDvdLibrary;
        
    }
    
    public void displayCreateDvdLibraryBanner() {
        io.print("=== Create DVD ===");
    }
    
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created. Please hit enter to continue");
    }
    
    public void displayDigitalVideoDiscList(List<DvdLibrary> dvdLibraryList) {
        for (DvdLibrary currentDvdLibrary : dvdLibraryList) {
            String DvdLibraryInfo = String.format("%s : %s : %s : %s : %s : %s", 
                    currentDvdLibrary.getTitle(), 
                    currentDvdLibrary.getReleaseDate(), 
                    currentDvdLibrary.getMpaaRating(),
                    currentDvdLibrary.getDirectorName(),
                    currentDvdLibrary.getStudio(),
                    currentDvdLibrary.getUserRating());
            io.print(DvdLibraryInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVD ===");
    }
    
    public void displayDisplayDVDBanner() {
        io.print("=== Display DVD ===");
    }
    
    public String getTitleChoice() {
        return io.readString("Please enter the title of the DVD.");
    }
    
    public void displayDVD(DvdLibrary dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getMpaaRating());
            io.print(dvd.getDirectorName());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDVDEditBanner() {
	io.print("=== Edit DVD ===");
	}
    
    public int printDVDEditMenu() {
        io.print("1. Edit Release Date");
	io.print("2. Edit MPAA Rating");
	io.print("3. Edit Director Name");
	io.print("4. Edit Studio");
	io.print("5. Edit User Rating");
        
        return io.readInt("Please select from the above.", 1, 5);
    }
    

    public String editDVDReleaseDate(){
        return io.readString("Please enter the updated Release Date.");
    }

    public String editDVDMpaaRating(){
        return io.readString("Please enter the updated MPAA Rating.");
    }

    public String editDirectorName(){
        return io.readString("Please enter the director's name.");
    }

    public String editStudio(){
        return io.readString("Please enter the updated Studio.");
    }

    public String editUserRating(){
        return io.readString("Please enter the updated User Rating");
    }

    public void displayEditSuccessBanner(){
        io.readString("DVD Edited. Press enter to go to main menu.");
    }
    
    
    public void displayEditDVDSuccessBanner() {
        io.readString("DVD successfully edited. Please hit enter to continue");
    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove DVD ===");
    }
    
    
    public void displayRemoveResult(DvdLibrary dvdRecord) {
        if (dvdRecord != null) {
            io.print("DVD successfully removed.");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print ("=== ERROR ===");
        io.print(errorMsg);
    }
               
}
