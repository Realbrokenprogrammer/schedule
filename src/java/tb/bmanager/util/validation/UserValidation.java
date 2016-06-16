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
    
    protected UserValidation() {
        
    }
    
    public static UserValidation getInstance() {
        if (instance == null) {
            instance = new UserValidation();
        }
        return instance;
    }
    
    public boolean validateUsername(String username) {
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
