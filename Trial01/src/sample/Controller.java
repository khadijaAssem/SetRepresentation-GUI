package sample;

import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sun.awt.Win32ColorModel24;

public class Controller {
    @FXML
    private TextField universe;
    @FXML
    private TextField addSubset;
    @FXML
    private Label subsets;
    @FXML
    private Label Error;
    @FXML
    private TextField operation;
    @FXML
    private Label answer;
    @FXML
    private Pane screen;

    static ArrayList<String []> sets = new ArrayList<String []>();
    String[] Universal;
    char setName = 'A';

    @FXML
    public void findSets(){
        LinkedList<Circle> circles = new LinkedList<>();
        for (int i=0 ;i<sets.size();i++){
            Circle Ccls = new Circle();

        }
        Universal= universe.getText().split(",");
        String[] newSet =(addSubset.getText().split(","));
        boolean v = validsubset(newSet,Universal);
        while(!v){
            Error.setText("Invalid subset!");
            addSubset.setText("");
            return;
        }
        Error.setText("");
        sets.add(setName-65,newSet);
        subsets.setText(subsets.getText()+"\n"+ setName +" : "+addSubset.getText());
        setName++;
        addSubset.setText("");
    }
    @FXML
    public void operation(){
        String statement = operation.getText();
        String[] Stat = (statement.split("\\s"));
        operation.setText("");
        if (Stat[1].toUpperCase().equals("I")) {
            String[] A = sets.get(Stat[0].charAt(0) - 65);
            A = removeDuplicates(A);
            String[] B = sets.get(Stat[2].charAt(0) - 65);
            B = removeDuplicates(B);
            if (intersection(A, B).size()==0)
                answer.setText(statement + " = " +"PHI");
            else
                answer.setText(statement + " = " + intersection(A, B));
        } else if (Stat[1].toUpperCase().equals("U")) {
            String[] A = sets.get(Stat[0].charAt(0) - 65);
            A = removeDuplicates(A);
            String[] B = sets.get(Stat[2].charAt(0) - 65);
            B = removeDuplicates(B);
            answer.setText(statement + " = " +Union(A, B));
        } else if (Stat[1].toUpperCase().equals("C")) {
            String[] A = sets.get(Stat[0].charAt(0) - 65);
            A = removeDuplicates(A);
            if (complement(Universal, A).size()==0)
                answer.setText(statement + " = " +"PHI");
            else
                answer.setText(statement + " = " +complement(Universal, A));
        }
    }
    public static boolean validsubset(String[]set1,String[]set2){
        int found =0;
        for(int i=0;i<set1.length;i++){
            for(int j=0;j<set2.length;j++){
                if (set1[i].equals(set2[j])){
                    found++;
                    break;
                }
            }
        }
        if(found==set1.length)
            return true;
        else
            return false;
    }
    public static LinkedList<String> intersection (String[] set1,String[] set2){
        LinkedList<String> Intersection = new LinkedList<String>();
        for (int i=0;i<set1.length;i++){
            for (int j=0;j<set2.length;j++){
                if (set1[i].equals(set2[j])){
                    Intersection.add(set1[i]);
                    break;
                }
            }
        }
        Intersection = RemoveDuplicates(Intersection);
        return Intersection;
    }
    public static LinkedList<String> complement (String []universal,String[] set1){
        LinkedList<String> complenemt = new LinkedList<String>();
        int found;
        for (int i=0;i<universal.length;i++){
            found=0;
            for (int j=0;j<set1.length;j++){
                if (universal[i].equals(set1[j])){
                    break;
                }
                found++;
            }
            if(found==set1.length)
                complenemt.add(universal[i]);
        }
        complenemt = RemoveDuplicates(complenemt);
        return complenemt;
    }
    public static LinkedList<String> Union (String[] set1, String[] set2){
        LinkedList<String> Union = new LinkedList<String>();
        for (int i=0;i<set1.length;i++)
            Union.add(set1[i]);
        for (int i=0;i<set2.length;i++)
            Union.add(set2[i]);
        Union = RemoveDuplicates(Union);
        return Union;
    }
    public static String[] removeDuplicates(String[] list){
        Set set = new LinkedHashSet();// Create a new LinkedHashSet
        set.addAll(Arrays.asList(list));// Add the elements to set
        String[] List = new String[set.size()];// Clear the list
        int i =0;
        for (Object W:set)
            List[i++] = (String) W;// add the elements of set with no duplicates to the list
        return list;
    }
    public static LinkedList RemoveDuplicates(LinkedList list){
        Set set = new LinkedHashSet();// Create a new LinkedHashSet
        set.addAll(list);// Add the elements to set
        list.clear();// Clear the list
        list.addAll(set);// add the elements of set with no duplicates to the list
        return list;
    }
    public static LinkedList<String> Difference (String []set1,String[] set2){
        LinkedList<String> difference = new LinkedList<String>();
        int found;
        for (int i=0;i<set1.length;i++){
            found=0;
            for (int j=0;j<set2.length;j++){
                if (set1[i].equals(set2[j])){
                    found=1;
                }
            }
            if(found==0)
                difference.add(set1[i]);
        }
        difference = RemoveDuplicates(difference);
        return difference;
    }
}
