/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasets;

import java.awt.Color;

/**
 *
 * @author hp
 */
public class Topic {
    
    private int id;
    private String name;
    private String[]satisfactionLevels;
    private int[]colors;
    
    
    public enum opciones{
        
        FIRST("Tipo base", 5), SECOND("Tipo variado", 10);
        
        private String tipo;
        private int valor;
        
        private opciones(String tipo, int valor){
            this.tipo = tipo;
            this.valor = valor;
        }

        /**
         * @return the tipo
         */
        public String getTipo() {
            return tipo;
        }

        /**
         * @param tipo the tipo to set
         */
        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        /**
         * @return the valor
         */
        public int getValor() {
            return valor;
        }

        /**
         * @param valor the valor to set
         */
        public void setValor(int valor) {
            this.valor = valor;
        }
        
        
    }

    public Topic(){}
    public Topic(String[]satisfactionLevels, int[]colors){
        this.satisfactionLevels = satisfactionLevels;
        this.colors = colors;
        
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the satisfactionLevels
     */
    public String[] getSatisfactionLevels() {
        return satisfactionLevels;
    }

    /**
     * @param satisfactionLevels the satisfactionLevels to set
     */
    public void setSatisfactionLevels(String[] satisfactionLevels) {
        this.satisfactionLevels = satisfactionLevels;
    }
    
    public String stringifySatisfactionLevels(){
        StringBuilder sb = new StringBuilder();
        for(String s : satisfactionLevels){
            sb.append(s).append("/");
        }
        return sb.toString();
    }
    public String stringifyColors(){
        StringBuilder sb = new StringBuilder();
        for(int s : colors){
            sb.append(s).append("/");
        }
        return sb.toString();
    }
    /**
     * @return the colors
     */
    public int[] getColors() {
        return colors;
    }

    /**
     * @param colors the colors to set
     */
    public void setColors(int[] colors) {
        this.colors = colors;
    }
}
