package OSSchedulingAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class fcfs {
    //creating a process array
    Process[] pArray;

    //initializing pArray as main class pArray;
    fcfs(Process[] pArray){
        this.pArray=pArray;
    }

    //function for calculating completion time
    void completionTime(){
        //sorting process as per arrival time
        Collections.sort(Arrays.asList(pArray),new atSort());
        //creating ct variable to store completion time
        int ct=pArray[0].at;
        //calculating respective completion times
        for(int i=0;i<pArray.length;i++){
            ct+=pArray[i].bt;
            pArray[i].ct=ct;
        }
    }

    //function for calculating turn around time
    void turnTime(){
        for(int i=0;i<pArray.length;i++){
            pArray[i].tat=pArray[i].ct-pArray[i].at;
        }
    }

    //function for calculating waiting time
    void waitTime(){
        for(int i=0;i<pArray.length;i++){
            pArray[i].wt=pArray[i].tat-pArray[i].bt;
        }
    }

    //function for printing gantt chat of fcfs
    void ganttChart(JFrame frame){
        Collections.sort(Arrays.asList(pArray), new atSort());
        int ct = pArray[0].at;

        //creating array of labels and processes
        JTextField[] process = new JTextField[20];
        JLabel[] labels = new JLabel[20];

        //adding first block of gantt chart
        process[0] = new JTextField("P" + (pArray[0].pid));
        process[0].setHorizontalAlignment(JTextField.CENTER);
        process[0].setBackground(Color.cyan);
        process[0].setBounds(400, 10, 60, 30);
        process[0].setFont(new Font("Times_New_Roman", Font.BOLD, 20));
        process[0].setEditable(false);
        frame.add(process[0]);

        labels[0] = new JLabel(Integer.toString(pArray[0].at));
        labels[0].setBackground(Color.cyan);
        labels[0].setHorizontalAlignment(JLabel.CENTER);
        labels[0].setFont(new Font("Times_New_Roman", Font.BOLD, 20));
        labels[0].setBounds(400 - 5, 40, 30, 30);
        frame.add(labels[0]);
        //storing no of labels and blocks created
        int ganttVar=1;
        int ganttLabels=1;
        //creating ct variable to store completion times
        ct+=pArray[0].bt;

        for(int i=1;i<pArray.length;i++){
            //adding more blocks to gantt chart
            process[ganttVar] = new JTextField("P" + pArray[i].pid);
            process[ganttVar].setHorizontalAlignment(JTextField.CENTER);
            process[ganttVar].setBackground(Color.cyan);
            //getting position of previous block to print further blocks
            process[ganttVar].setBounds(process[ganttVar - 1].getX() + 60, 10, 60, 30);
            process[ganttVar].setFont(new Font("Times_New_Roman", Font.BOLD, 20));
            process[ganttVar].setEditable(false);
            //created by:Piyush Gupta 1000014993
            frame.add(process[ganttVar]);

            labels[ganttLabels] = new JLabel(Integer.toString(ct));
            labels[ganttLabels].setBackground(Color.cyan);
            labels[ganttLabels].setHorizontalAlignment(JLabel.LEADING);
            labels[ganttLabels].setFont(new Font("Times_New_Roman", Font.BOLD, 20));
            labels[ganttLabels].setBounds(process[ganttVar - 1].getX() + 55, process[ganttVar - 1].getY() + 30, 30, 30);
            frame.add(labels[ganttLabels]);
            ct+=pArray[i].bt;
            ganttVar++;
            ganttLabels++;

            //condition to add last label of gantt chart with end time
            if(i==pArray.length-1){
                labels[ganttLabels]=new JLabel(Integer.toString(ct));
                labels[ganttLabels].setBackground(Color.cyan);
                String str="Piyush Gupta 10000014993";
                labels[ganttLabels].setHorizontalAlignment(JLabel.LEADING);
                labels[ganttLabels].setFont(new Font("Times_New_Roman",Font.BOLD,20));
                labels[ganttLabels].setBounds(process[ganttVar-1].getX()+55,process[ganttVar-1].getY()+30,30,30);
                frame.add(labels[ganttLabels]);
            }
        }
}
}