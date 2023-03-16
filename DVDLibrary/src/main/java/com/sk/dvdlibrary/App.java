/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.dvdlibrary;

import com.sk.dvdlibrary.controller.DvdLibraryController;
import com.sk.dvdlibrary.dao.DvdLibraryDao;
import com.sk.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.sk.dvdlibrary.ui.DvdLibraryView;
import com.sk.dvdlibrary.ui.UserIO;
import com.sk.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author steve
 */
public class App {
    
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DvdLibraryView myView = new DvdLibraryView(myIo);
        DvdLibraryDao myDao = new DvdLibraryDaoFileImpl();
        DvdLibraryController controller = new DvdLibraryController(myDao, myView);
        controller.run();
    }
}
