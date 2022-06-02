package OSSchedulingAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class premptSJF {
    Process[] pArray;
    int [] btArray;

    premptSJF(Process[] pArray,int n){
        this.pArray=pArray;
        btArray=new int[n];
    }

    //function to store burst time so to
    //initialize afterwards for calculating tat and wt
    void btStore(){
        for(int i=0;i<pArray.length;i++){
            btArray[i]=pArray[i].bt;
        }
    }

    //function for calculating completion time
    void completionTime(){
        //sorting on the basis of arrival time
        Collections.sort(Arrays.asList(pArray),new atSort());
        int ct=pArray[0].at;

           for(int i=0;i<pArray.length;i++){
               while(true) {
                   //selecting the process with lowest burst time after every unti time
                   for (int j = i + 1; j < pArray.length; j++) {
                       if(pArray[j].at<=ct&&(pArray[j].bt<pArray[i].bt)){
                           Process temp;
                           temp=pArray[i];
                           pArray[i]=pArray[j];
                           pArray[j]=temp;
                       }
                   }
                   if(pArray[i].bt==0){
                       //once a process is completed then breaking the while loop to move further
                       pArray[i].ct=ct;
                       break;
                   }
                   else{
                       pArray[i].bt-=1;
                       ct+=1;
                   }
               }
            }

    }

    //function for printing gantt chart
    //uses sjf algo to print gantt chart blocks after every instant
    void ganttChart(JFrame frame) {
        Collections.sort(Arrays.asList(pArray), new atSort());
        int ct = pArray[0].at;

        JTextField[] process = new JTextField[15];
        JLabel[] labels = new JLabel[20];

        int ganttVar = 0;

        int ganttLabels=0;

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

        ganttVar++;
        ganttLabels++;

        boolean flag=false;

        boolean flag1=true;

        for (int i = 0; i < pArray.length; i++) {

            while (true) {

                for (int j = i + 1; j < pArray.length; j++) {

                    if (pArray[j].at <= ct && (pArray[j].bt < pArray[i].bt)) {
                        Process temp;
                        temp = pArray[i];
                        pArray[i] = pArray[j];
                        pArray[j] = temp;
                        flag=true;
                    }

                }
                if(flag) {
                    process[ganttVar] = new JTextField("P" + pArray[i].pid);
                    process[ganttVar].setHorizontalAlignment(JTextField.CENTER);
                    String str="Ayush Sajwan 10000015514";
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

                    ganttVar++;
                    ganttLabels++;
                    flag=false;
                }

                    if (pArray[i].bt == 0) {
                        pArray[i].ct = ct;
                        break;
                    }
                    else {
                        if(i==pArray.length-1&&flag1){
                            process[ganttVar] = new JTextField("P" + pArray[i].pid);
                            process[ganttVar].setHorizontalAlignment(JTextField.CENTER);
                            process[ganttVar].setBackground(Color.cyan);
                            process[ganttVar].setBounds(process[ganttVar - 1].getX() + 60, 10, 60, 30);
                            process[ganttVar].setFont(new Font("Times_New_Roman", Font.BOLD, 20));
                            process[ganttVar].setEditable(false);
                            frame.add(process[ganttVar]);

                            labels[ganttLabels] = new JLabel(Integer.toString(ct));
                            labels[ganttLabels].setBackground(Color.cyan);
                            String str="Ayush Sajwan 10000015514";
                            labels[ganttLabels].setHorizontalAlignment(JLabel.LEADING);
                            labels[ganttLabels].setFont(new Font("Times_New_Roman", Font.BOLD, 20));
                            labels[ganttLabels].setBounds(process[ganttVar - 1].getX() + 55, process[ganttVar - 1].getY() + 30, 30, 30);
                            frame.add(labels[ganttLabels]);

                            ganttVar++;
                            ganttLabels++;
                            flag1=false;
                        }
                        pArray[i].bt -= 1;
                        ct += 1;
                    }

            }

            if(i==pArray.length-1){
                labels[ganttLabels]=new JLabel(Integer.toString(ct));
                labels[ganttLabels].setBackground(Color.cyan);
                labels[ganttLabels].setHorizontalAlignment(JLabel.LEADING);
                labels[ganttLabels].setFont(new Font("Times_New_Roman",Font.BOLD,20));
                labels[ganttLabels].setBounds(process[ganttVar-1].getX()+55,process[ganttVar-1].getY()+30,30,30);
                frame.add(labels[ganttLabels]);
            }

        }
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }

        //function to reinitialize the burst times
    void reinitBt(){
        Collections.sort(Arrays.asList(pArray),new PidSort());
        for(int i=0;i<pArray.length;i++){
            pArray[i].bt=btArray[i];
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
