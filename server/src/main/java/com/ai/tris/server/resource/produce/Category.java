package com.ai.tris.server.resource.produce;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Category
 * Created by Sam on 2015/6/2.
 */
@XmlRootElement
public class Category {
    public String category;
    public List<Group> groups = new ArrayList<Group>();

    public void setCategory(String category) {
        this.category = category;
    }

    public void addToGroup(Group g) {
        this.groups.add(g);
    }
}
