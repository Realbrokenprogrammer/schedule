/*
 * Copyright (C) 2016 oskarmendel
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package tb.bmanager.util.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User validation utility tool, helps validate user data.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name UserValidation.java
 * %date 03:08:12 AM, Jun 16, 2016
 */
public class UserValidation {
    
    private static UserValidation instance = null;
    private Pattern pattern;
    private Matcher matcher;
    
    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,20}$";
    private static final String DISPLAYNAME_PATTERN = "^[a-z0-9_-]{3,20}$";
    private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "^.{4,30}$";
    
    protected UserValidation() {
        
    }
    
    /**
     * 
     * @return instance of the UserValidation singleton.
     */
    public static UserValidation getInstance() {
        if (instance == null) {
            instance = new UserValidation();
        }
        return instance;
    }
    
    /**
     * 
     * @param username - username to validate.
     * @return true or false depending if the length and formatting of the 
     * username is allowed or not.
     */
    public boolean validateUsername(String username) {
        //TODO check for illegal words or phrases
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @param displayname - display name to validate.
     * @return true or false depending if the length and formatting of the
     * display name is considered valid or not.
     */
    public boolean validateDisplayname(String displayname) {
        //TODO check for illegal words or phrases
        pattern = Pattern.compile(DISPLAYNAME_PATTERN);
        matcher = pattern.matcher(displayname);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @param email - email to validate.
     * @return true or false depending if the formatting of the
     * email is considered valid or not.
     */
    public boolean validateEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
    
    public boolean validatePassword(String password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
