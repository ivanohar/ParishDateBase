package com.ohar.pdb.helper.exception;

import com.ohar.pdb.helper.WindowConfig;

public class CanNotCreateWindowException extends RuntimeException {
    public CanNotCreateWindowException(Throwable cause, WindowConfig windowName) {
        super("Can't create window " + windowName, cause);
    }
}
