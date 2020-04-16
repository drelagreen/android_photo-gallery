package com.crafsed.webant_practice.api_stuff;


public class Data
{
    private String newS;

    private Image image;

    private String name;

    private String description;

    private String id;

    private String dateCreate;

    private String popular;

    private String user;

    public String getNew ()
    {
        return newS;
    }

    public void setNew (String newS)
    {
        this.newS = newS;
    }

    public Image getImage ()
    {
        return image;
    }

    public void setImage (Image image)
    {
        this.image = image;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDateCreate ()
    {
        return dateCreate;
    }

    public void setDateCreate (String dateCreate)
    {
        this.dateCreate = dateCreate;
    }

    public String getPopular ()
    {
        return popular;
    }

    public void setPopular (String popular)
    {
        this.popular = popular;
    }

    public String getUser ()
    {
        return user;
    }

    public void setUser (String user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [new = "+newS+", image = "+image+", name = "+name+", description = "+description+", id = "+id+", dateCreate = "+dateCreate+", popular = "+popular+", user = "+user+"]";
    }
}

