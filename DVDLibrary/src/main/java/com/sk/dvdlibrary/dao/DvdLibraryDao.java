/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.dvdlibrary.dao;

import com.sk.dvdlibrary.dto.DvdLibrary;
import java.util.List;

/**
 *
 * @author steve
 */
public interface DvdLibraryDao {
    /**
     * Adds the given DvdLibrary to the library and associates it with the given
     * title. If there is already a DvdLibrary associated with the given
     * title it will return that DvdLibrary object, otherwise it will
     * return null.
     *
     * @param title id with which student is to be associated
     * @param DvdLibrary dvd to be added to the library
     * @return the DvdLibrary object previously associated with the given  
     * title if it exists, null otherwise
     */
    DvdLibrary addDvd(String title, DvdLibrary dvd) throws DvdLibraryDaoException;
    
    /**
     * Returns a List of all DvdLibrary in the library.
     *
     * @return List containing all DvdLibrary in the library.
     */
    List<DvdLibrary> getAllDvds() throws DvdLibraryDaoException;
    
    /**
     * Returns the DvdLibrary object associated with the given title.
     * Returns null if no such DvdLibrary exists
     *
     * @param title ID of the DvdLibrary to retrieve
     * @return the DvdLibrary object associated with the given title,  
     * null if no such DvdLibrary exists
     */
    DvdLibrary getDvd(String title) throws DvdLibraryDaoException;
    
    DvdLibrary editDvd(String title, DvdLibrary dvd) throws DvdLibraryDaoException;
    
    /**
     * Removes from the library the DvdLibrary associated with the given title.
     * Returns the DvdLibrary object that is being removed or null if
     * there is no DvdLibrary associated with the given title.
     *
     * @param title of DvdLibrary to be removed
     * @return DvdLibrary object that was removed or null if no DvdLibrary
     * was associated with the given title.
     */
    DvdLibrary removeDvd(String title) throws DvdLibraryDaoException;

    
}
