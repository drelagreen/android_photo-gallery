package com.crafsed.webant_practice.api_stuff;

public class PojoImage
{
    private String file;

    private String name;

    private String id;

    public String getFile ()
    {
        return file;
    }

    public void setFile (String file)
    {
        this.file = file;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [file = "+file+", name = "+name+", id = "+id+"]";
    }
}
