package com.css.gitapi.model;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/1 16:07
 */
public class ForkProjectParams {
    /**
     * 项目ID或者项目的URL路径
     * (required)
     */
    private String id;
    /**
     * The ID of the namespace that the project will be forked to
     */
    private Integer namespace_id;
    /**
     * The path of the namespace that the project will be forked to
     */
    private String namespace_path;

    /**
     * The path that will be assigned to the resultant project after forking
     */
    private String path;
    /**
     * The name that will be assigned to the resultant project after forking
     */
    private String name;


    public ForkProjectParams(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Integer getNamespace_id() {
        return namespace_id;
    }

    public void setNamespace_id(Integer namespace_id) {
        this.namespace_id = namespace_id;
    }

    public String getNamespace_path() {
        return namespace_path;
    }

    public void setNamespace_path(String namespace_path) {
        this.namespace_path = namespace_path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
