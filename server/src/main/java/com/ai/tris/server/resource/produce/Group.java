package com.ai.tris.server.resource.produce;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group {

    public String group;

    /**
     * Reserved for MoxyJson
     */
    @SuppressWarnings("unused")
    public Group() {

    }

    public Group(String g) {
        this.group = g;
    }

}