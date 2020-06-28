package com.ohar.pdb.factory;

import com.ohar.pdb.services.SearchPersonService;
import com.ohar.pdb.services.SearchUserService;
import com.ohar.pdb.services.ValidationService;
import com.ohar.pdb.services.impl.SearchPersonServiceImpl;
import com.ohar.pdb.services.impl.SearchUserServiceImpl;
import com.ohar.pdb.services.impl.ValidationServiceImpl;

public class AbstractServiceFactory {
    private static final AbstractServiceFactory factory = new AbstractServiceFactory();
    private SearchPersonService personService = new SearchPersonServiceImpl();
    private SearchUserService userService = new SearchUserServiceImpl();
    private ValidationService validationService = new ValidationServiceImpl();

    private AbstractServiceFactory() {
    }

    public SearchPersonService getSearchPersonService() {
        return personService;
    }

    public SearchUserService getSearchUserService() {
        return userService;
    }

    public ValidationService getValidationService() {
        return validationService;
    }

    public static AbstractServiceFactory getInstance() {
        return factory;
    }
}
