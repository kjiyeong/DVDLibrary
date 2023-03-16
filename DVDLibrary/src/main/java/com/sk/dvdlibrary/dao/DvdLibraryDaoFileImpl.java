/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.dvdlibrary.dao;

import com.sk.dvdlibrary.dto.DvdLibrary;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author steve
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
    
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    
    private DvdLibrary unmarshallDvd(String DvdLibraryAsText) {
        String[] dvdTokens = DvdLibraryAsText.split(DELIMITER);
        String title = dvdTokens[0];
        DvdLibrary dvdFromFile = new DvdLibrary(title);
        
       
        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMpaaRating(dvdTokens[2]);
        dvdFromFile.setDirectorName(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setUserRating(dvdTokens[5]);
        return dvdFromFile;
    }
    
    private void loadLibrary() throws DvdLibraryDaoException {
        Scanner scanner;
        
        try {
            // create scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
            } catch (FileNotFoundException e) {
                throw new DvdLibraryDaoException("-_- Could not load library data into memory.", e);
            }
        // currentLine holds the most recent line read from the file
        String currentLine;
        //currentDvdLibrary holds the most recent dvd unmarshelled
        DvdLibrary currentDvdLibrary;
        // Go through LIBRARY_FILE line by line, decoding each line into a
        // DvdLibrary object by calling the unmarshallingDvdLibrary method.
        // Process have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a DvdLibrary
            currentDvdLibrary = unmarshallDvd(currentLine);
            
            // We are going to use the title as the map key for our DvdLibrary object.
            // Put currentDvdLibrary into the map using title as the key
            dvds.put(currentDvdLibrary.getTitle(), currentDvdLibrary);
            
        }
        // close scanner
        scanner.close();
    }
    private String marshallDvd(DvdLibrary aDvdLibrary) {
        
        String DvdLibraryAsText = aDvdLibrary.getTitle() + DELIMITER;
        DvdLibraryAsText += aDvdLibrary.getReleaseDate() + DELIMITER;
        DvdLibraryAsText += aDvdLibrary.getMpaaRating() + DELIMITER;
        DvdLibraryAsText += aDvdLibrary.getDirectorName() + DELIMITER;
        DvdLibraryAsText += aDvdLibrary.getStudio() + DELIMITER;
        DvdLibraryAsText += aDvdLibrary.getUserRating();
        return DvdLibraryAsText;
    }
    
    private void writeLibrary() throws DvdLibraryDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException("Could not save data.", e);
        }
        
        String DvdLibraryAsText;
        List<DvdLibrary> DvdLibraryList = new ArrayList(dvds.values());
        for (DvdLibrary currentDvdLibrary : DvdLibraryList) {
            DvdLibraryAsText = marshallDvd(currentDvdLibrary);
            out.println(DvdLibraryAsText);
            out.flush();
        }
        out.close();
    }
    
    
    private Map<String, DvdLibrary> dvds = new HashMap<>();
  

    @Override
    public DvdLibrary addDvd(String title, DvdLibrary dvd) throws DvdLibraryDaoException {
        loadLibrary();
        DvdLibrary newDvdLibrary = dvds.put(title, dvd);
        writeLibrary();
        return newDvdLibrary;
    }

    @Override
    public List<DvdLibrary> getAllDvds() throws DvdLibraryDaoException {
        loadLibrary();
        return new ArrayList<DvdLibrary>(dvds.values());
    }

    @Override
    public DvdLibrary getDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        return dvds.get(title);
    }
    
    @Override
    public DvdLibrary editDvd(String title, DvdLibrary dvd) throws DvdLibraryDaoException {
        DvdLibrary editDvd = dvds.put(title, dvd);
        return editDvd;

    }

    @Override
    public DvdLibrary removeDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        DvdLibrary removeDvd = dvds.remove(title);
        writeLibrary();
        return removeDvd;
    }

    
    
}
