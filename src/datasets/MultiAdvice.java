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
public class MultiAdvice extends Advice{
    private int[]memberIDs;

    public MultiAdvice(){}
    public MultiAdvice(int[] memberIDs, int id, String tip) {
        super(id, false, tip);
        this.memberIDs = memberIDs;
       
    }
    
    public String stringifyMembers(){
        
        StringBuilder sb = new StringBuilder();
        for(int s : getMemberIDs()){
            sb.append(s).append("/");
        }
        return sb.toString();
    
    }
    
    public void setMembersFromString(String members){
        String[] split = members.split("/");
        memberIDs = new int[split.length];
        for(int i=0;i<split.length;i++){
            if(!(split[i].equals("")) && (split[i] != null))
            memberIDs[i] = Integer.valueOf(split[i]);
        }
    }

    /**
     * @return the memberIDs
     */
    public int[] getMemberIDs() {
        return memberIDs;
    }

    /**
     * @param memberIDs the memberIDs to set
     */
    public void setMemberIDs(int[] memberIDs) {
        this.memberIDs = memberIDs;
    }
   
    

}
