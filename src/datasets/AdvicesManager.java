/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasets;

import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class AdvicesManager {

    public Topic topic;
    public ArrayList<Advice> advices = new ArrayList<>();
    public ArrayList<MultiAdvice> multiAdvices = new ArrayList<>();

    StringBuilder output = new StringBuilder();

    public void checkMultiAdvices() {

        /*
         i = Iterates over the multiadvices
         f = Iterates over the member IDs of every multiadvice to check it
         u = Iterates over the advices to access to their properties
         temp = Checks whether all the member IDs of each multiadvice are active
         */
        for (int i = 0; i < multiAdvices.size(); i++) {
            int temp = 0;
            for (int f = 0; f < multiAdvices.get(i).getMemberIDs().length; f++) {
                for (int u = 0; u < advices.size(); u++) {
                    if (advices.get(u).getId() == multiAdvices.get(i).getMemberIDs()[f] && advices.get(u).isActive()) {
                        temp++;
                    }
                }
            }
            if (temp == multiAdvices.get(i).getMemberIDs().length) {
                    multiAdvices.get(i).setActive(true);
                    output.append("» ").append(multiAdvices.get(i).getTip()).append("\n\n");
                }

        }
    }

    public void checkAdvices() {

        for (int i = 0; i < advices.size(); i++) {
            System.out.println("Active: "+advices.get(i).isActive()+", Tiponactive: "+advices.get(i).isTipOnActive());
            if(!advices.get(i).getTip().equals("") && advices.get(i).isActive()){
                output.append("» ").append(advices.get(i).getTip()).append("\n");
            }
        }
    }

    public String outputAdvices() {
        String answer = output.toString();
        output = new StringBuilder();
        return answer;
    }

}
