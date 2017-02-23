/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spellchecker;

import co.org2.nlangproc.StandardEditingDistanceEvaluator;
import org.freeoffice.autocomplete.AutocompleteAssistant;

/**
 *
 * @author hcadavid
 */
public class ExampleOfUse {
    
    public static void main(String args[]){
        String word="alien";
        
        AutocompleteAssistant aca=new AutocompleteAssistant(new StandardEditingDistanceEvaluator());
        
        System.out.println(aca.getSimilarWords(word));
        
        
    }    
    
}
