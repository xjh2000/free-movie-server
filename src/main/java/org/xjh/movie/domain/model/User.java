package org.xjh.movie.domain.model;

import org.bson.types.ObjectId;

/**
 * @author xjh
 * @date 6/18/2022 8:05 PM
 */
public class User {

    public ObjectId id;
    public String username;
    public String password;
    public String mail;
}
