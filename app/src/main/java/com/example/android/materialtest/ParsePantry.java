package com.example.android.materialtest;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.UUID;

/**
 * Created by Prince on 8/15/15.
 */
@ParseClassName("Pantry")
public class ParsePantry extends ParseObject {

    public String getTitle(){
        return getString("title");
    }

    public void setTitle(String title){
        put("title", title);
    }

    public ParseUser getAuthor(){
        return getParseUser("author");
    }

    public void setAuthor(ParseUser currentUser){
        put("author", currentUser);
    }

    public boolean isDraft(){
        return getBoolean("isDraft");
    }

    public void setDraft(boolean isDraft){
        put("isDraft", isDraft);
    }

    public void setUuidString(){
        UUID uuid = UUID.randomUUID();
        put("uuid", uuid.toString());
    }

    public String getUuidString(){
        return getString("uuid");
    }

    public static ParseQuery<ParsePantry> getQuery(){
        return ParseQuery.getQuery(ParsePantry.class);
    }
}
