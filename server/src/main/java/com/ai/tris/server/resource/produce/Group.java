package com.ai.tris.server.resource.produce;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group {

    public String group;

    public Group() {

    }

    public Group(String g) {
        this.group = g;
    }

    public String getGroup() {
        return group;
    }

}