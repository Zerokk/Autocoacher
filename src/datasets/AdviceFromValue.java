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
public class AdviceFromValue extends Advice{
    
    private String defaultTip;
    private float[]valueRanges;
    private String[]tips;

    public AdviceFromValue(int id, String defaultTip, float[]valueRanges, String[]tips, String question) {
        super(id, question, defaultTip);
        setActive(true);
        this.defaultTip = defaultTip;
        this.valueRanges = valueRanges;
        this.tips = tips;
        
    }
    
    
    
    public void updateTip(float value){
 
        for(int i=0;i<getValueRanges().length;i++){
            if(getValueRanges()[i] < value){
                setTip(getTips()[i]);
                System.out.println("Set tip to: "+getTip());
                setActive(true);
            } else {
              if(!getTip().equals(defaultTip) && i==0){
                  setTip(getDefaultTip());
              }
                
                break;
            }
        }
    }

    /**
     * @return the valueRanges
     */
    public float[] getValueRanges() {
        return valueRanges;
    }

    /**
     * @param valueRanges the valueRanges to set
     */
    public void setValueRanges(float[] valueRanges) {
        this.valueRanges = valueRanges;
    }

    /**
     * @return the tips
     */
    public String[] getTips() {
        return tips;
    }

    /**
     * @param tips the tips to set
     */
    public void setTips(String[] tips) {
        this.tips = tips;
    }

    /**
     * @return the defaultTip
     */
    public String getDefaultTip() {
        return defaultTip;
    }

    /**
     * @param defaultTip the defaultTip to set
     */
    public void setDefaultTip(String defaultTip) {
        this.defaultTip = defaultTip;
    }
    
    public String stringifyTips(){
     
        StringBuilder sb = new StringBuilder();
        for(String s : tips){
            sb.append(s).append("/");
        }
        return sb.toString();
    }
        
    public String stringifyVals(){
     
        StringBuilder sb = new StringBuilder();
        for(float s : valueRanges){
            sb.append(s).append("/");
        }
        return sb.toString();
    }
    
    
    
    
    
    
}
