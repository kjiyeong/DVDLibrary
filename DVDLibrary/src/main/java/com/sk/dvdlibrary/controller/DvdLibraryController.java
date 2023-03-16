/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.dvdlibrary.controller;

import com.sk.dvdlibrary.dao.DvdLibraryDao;
import com.sk.dvdlibrary.dao.DvdLibraryDaoException;
import com.sk.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.sk.dvdlibrary.dto.DvdLibrary;
import com.sk.dvdlibrary.ui.DvdLibraryView;
import com.sk.dvdlibrary.ui.UserIO;
import com.sk.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author steve
 */
public class DvdLibraryController {
    
    private UserIO io = new UserIOConsoleImpl();
    private DvdLibraryView view;
    private DvdLibraryDao dao;
    
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view){
        this.dao = dao;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
        while (keepGoing) {
            
            menuSelection = getMenuSelection();
            
            switch (menuSelection) {
                case 1:
                    listDvdLibrary();
                    break;
                case 2:
                    createDvd();
                    break;
                case 3:
                    viewDVD();
                    break;
                case 4:
                    editDVD();
                    break;
                case 5:
                    removeDvd();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();
    } catch (DvdLibraryDaoException e) {
        view.displayErrorMessage(e.getMessage());
    }
    }
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void createDvd() throws DvdLibraryDaoException {
        view.displayCreateDvdLibraryBanner();
        DvdLibrary newDigitalVideoDisc = view.getNewDvdInfo();
        dao.addDvd(newDigitalVideoDisc.getTitle(), newDigitalVideoDisc);
        view.displayCreateSuccessBanner();
    }
    
    private void listDvdLibrary() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DvdLibrary> dvdList = dao.getAllDvds();
        view.displayDigitalVideoDiscList(dvdList);
    }
    
    private void viewDVD() throws DvdLibraryDaoException {
        view.displayDisplayDVDBanner();
        String title = view.getTitleChoice();
        DvdLibrary dvd = dao.getDvd(title);
        view.displayDVD(dvd);
    }
    
    private int getEditDVDSelection() {
        return view.printDVDEditMenu();
    }
    private void editDVD() throws DvdLibraryDaoException {
        view.displayDVDEditBanner();
        String title = view.getTitleChoice();
        DvdLibrary dvd = dao.getDvd(title);
        int menuEditSelection = 0;
        menuEditSelection = getEditDVDSelection();
        switch (menuEditSelection) {
            case 1:
                String updatedReleaseDate = view.editDVDReleaseDate();
                dvd.setReleaseDate(updatedReleaseDate);
                break;
            case 2:
                String updatedMPAARating = view.editDVDMpaaRating();
                dvd.setMpaaRating(updatedMPAARating);
                break;
            case 3:
                String updatedDirectorName = view.editDirectorName();
                dvd.setDirectorName(updatedDirectorName);
                break;
            case 4:
                String updatedStudio = view.editStudio();
                dvd.setStudio(updatedStudio);
                break;
            case 5:
                String updatedUserRating = view.editUserRating();
                dvd.setUserRating(updatedUserRating);
                break;
        }
        dao.editDvd(dvd.getTitle(), dvd);
        view.displayEditSuccessBanner();
    }
    
    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String title = view.getTitleChoice();
        DvdLibrary removeDvd = dao.removeDvd(title);
        view.displayRemoveResult(removeDvd);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
}
