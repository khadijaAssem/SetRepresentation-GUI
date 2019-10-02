package sample;

import javafx.event.ActionEvent;
import java.awt.*;
import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML
    public static TextField universal;
    @FXML
    public static Pane screen;
    @FXML
    public static VBox pane_main_grid;
    public static void findSets(){
//        Scanner input = new Scanner(System.in);
//        String Un = input.nextLine();
        String[] Universal = universal.getText().split("\\s");
        for(String w:Universal)
            System.out.println(w);
//        String a = input.nextLine();
//        String[] A = a.split("\\s");
//        A = removeDuplicates(A);
//        for(String w:Universal)
//            System.out.println(w);
//        System.out.println(intersection(Universal,A));
    }
    public static LinkedList<String> intersection (String[] set1, String[] set2){
        LinkedList<String> Intersection = new LinkedList<>();
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

    public static LinkedList<String> Union (String[] set1, String[] set2){
        LinkedList<String> Union = new LinkedList<>();
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

    static String name = "set";
    static int countSet = 0;
    @FXML
    public void newSet(ActionEvent actionEvent){
        name = name + countSet ;
        TextField name = new TextField();
//        TextField textField[] = new TextField[15];
//        textField[countSet] = new TextField();
//        root.add(textField[countSet], 5, countSet);
        TextField newField = new TextField();
        newField.setMinWidth(120);

        pane_main_grid.getChildren().add(newField);
    }
}
