package OSSchedulingAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class nonPremptSJF {
    Process[] pArray;
    nonPremptSJF(Process[] pArray){
        this.pArray=pArray;
    }

    //function for calculating completion times
    void completionTime(){
        //sorting on basis of arrival time
        Collections.sort(Arrays.asList(pArray),new atSort());
        int ct=pArray[0].at;
        //selecting process with lowest burst time after completion of one
       for(int i=0;i<pArray.length;i++){
           for(int j=i+1;j< pArray.length;j++){
               if(pArray[j].at<=ct &&(pArray[j].bt<pArray[i].bt)){
                  Process temp=new Process();
                  temp=pArray[i];
                  pArray[i]=pArray[j];
                  pArray[j]=temp;
               }
           }
           ct+=pArray[i].bt;
           pArray[i].ct=ct;
       }
    }

    //function for printing gantt chart
    void ganttChart(JFrame frame){
        Collections.sort(Arrays.asList(pArray), new atSort());
        int ct = pArray[0].at;

        JTextField[] process = new JTextField[15];
        JLabel[] labels = new JLabel[20];

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
        String str="Ayush Sajwan 10000015514";
        labels[0].setFont(new Font("Times_New_Roman", Font.BOLD, 20));
        labels[0].setBounds(400 - 5, 40, 30, 30);
        frame.add(labels[0]);
        int ganttVar=1;
        int ganttLabels=1;

        ct+=pArray[0].bt;

        for(int i=1;i<pArray.length;i++){

            for(int j=i+1;j< pArray.length;j++){

                if(pArray[j].at<=ct &&(pArray[j].bt<pArray[i].bt)){
                    Process temp=new Process();
                    temp=pArray[i];
                    pArray[i]=pArray[j];
                    pArray[j]=temp;

                }
            }
            process[ganttVar] = new JTextField("P" + pArray[i].pid);
            process[ganttVar].setHorizontalAlignment(JTextField.CENTER);
            process[ganttVar].setBackground(Color.cyan);
            process[ganttVar].setBounds(process[ganttVar - 1].getX() + 60, 10, 60, 30);
            process[ganttVar].setFont(new Font("Times_New_Roman", Font.BOLD, 20));
            process[ganttVar].setEditable(false);
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

            if(i==pArray.length-1){
                labels[ganttLabels]=new JLabel(Integer.toString(ct));
                labels[ganttLabels].setBackground(Color.cyan);
                labels[ganttLabels].setHorizontalAlignment(JLabel.LEADING);
                //Ayush Sajwan 1000015514
                labels[ganttLabels].setFont(new Font("Times_New_Roman",Font.BOLD,20));
                labels[ganttLabels].setBounds(process[ganttVar-1].getX()+55,process[ganttVar-1].getY()+30,30,30);
                frame.add(labels[ganttLabels]);
            }

        }

    }

    void turnTime(){
        for(int i=0;i<pArray.length;i++){
            pArray[i].tat=pArray[i].ct-pArray[i].at;
        }
    }

    void waitTime(){
        for(int i=0;i<pArray.length;i++){
            pArray[i].wt=pArray[i].tat-pArray[i].bt;
        }
    }
}
