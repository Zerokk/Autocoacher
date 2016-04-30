/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasets;

/**
 *
 * @author hp
 */
public class Registry {
    
    private int id;
    private int topic_id;
    private int registry_num;
    private String title;
    private String success;
    private String description;
    private String tips;

    public Registry(){}
    public Registry(int topic_id, String success, String description, String tips) {
        this.topic_id = topic_id;
        this.success = success;
        this.description = description;
        this.tips = tips;
    }
    public Registry(int topic_id, int registry_num, String title, String success, String description, String tips) {
        this.topic_id = topic_id;
        this.registry_num = registry_num;
        this.success = success;
        this.description = description;
        this.tips = tips;
        this.title = title;
    }
    
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the topic_id
     */
    public int getTopic_id() {
        return topic_id;
    }

    /**
     * @param topic_id the topic_id to set
     */
    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    /**
     * @return the registry_num
     */
    public int getRegistry_num() {
        return registry_num;
    }

    /**
     * @param registry_num the registry_num to set
     */
    public void setRegistry_num(int registry_num) {
        this.registry_num = registry_num;
    }

    /**
     * @return the success
     */
    public String getSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(String success) {
        this.success = success;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the tips
     */
    public String getTips() {
        return tips;
    }

    /**
     * @param tips the tips to set
     */
    public void setTips(String tips) {
        this.tips = tips;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
