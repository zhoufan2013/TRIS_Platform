package com.ai.tris.server.resource;

import com.ai.tris.server.resource.produce.Category;
import com.ai.tris.server.resource.produce.Group;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Category Resource
 * <p/>
 * Created by Sam on 2015/6/2.
 */
@Path("category")
public class CategoryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<Category>();
        Category category = new Category();
        category.setCategory("All");
        categories.add(category);

        Category category1 = new Category();
        category1.setCategory("Functional");
        category1.addToGroup(new Group("BVTs"));
        category1.addToGroup(new Group("Core Tests"));
        category1.addToGroup(new Group("Func Tests"));
        categories.add(category1);

        Category category2 = new Category();
        category2.setCategory("Priority");
        category2.addToGroup(new Group("High"));
        category2.addToGroup(new Group("Medium"));
        category2.addToGroup(new Group("Low"));
        categories.add(category2);
        return categories;
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Category postSomething(HashMap obj) {
        Category category1 = new Category();
        category1.setCategory("Functional");
        category1.addToGroup(new Group("BVTs"));
        category1.addToGroup(new Group("Core Tests"));
        category1.addToGroup(new Group("Func Tests"));
        return category1;
    }
}
