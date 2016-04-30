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
public class Advice {

    private int id;
    private int topic_id;
    private String question;  // Texto a salir en los checkboxes
    private boolean active = false;
    private boolean tipOnActive = true;
    private String tip;

    public Advice(){}
    public Advice(int id, boolean active, String tip) {
        this.id = id;
        this.active = active;
        this.tip = tip;
    }

     public Advice(int id, String question, String tip) {
        this.id = id;
        this.question = question;
        this.tip = tip;
    }
    
    public Advice(int id, String question, String tip, boolean tipOnActive) {
        this.id = id;
        this.question = question;
        this.tip = tip;
        this.tipOnActive = tipOnActive;
    }

    public Advice(int id, String question, boolean active, String tip, boolean tipOnActive) {
        this.id = id;
        this.question = question;
        this.active = active;
        this.tip = tip;
        this.tipOnActive = tipOnActive; 
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
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        if(isTipOnActive()){
            return active;
        }else{
            return !active;
        }
        
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the tip
     */
    public String getTip() {
        return tip;
    }

    /**
     * @param tip the tip to set
     */
    public void setTip(String tip) {
        this.tip = tip;
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
     * @return the tipOnActive
     */
    public boolean isTipOnActive() {
        return tipOnActive;
    }

    /**
     * @param tipOnActive the tipOnActive to set
     */
    public void setTipOnActive(boolean tipOnActive) {
        this.tipOnActive = tipOnActive;
    }
}
