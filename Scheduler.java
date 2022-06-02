package OSSchedulingAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Scheduler {
    public static void main(String[] args) {
        //creating JFrame for initial operations
        JFrame frame=new JFrame();
        frame.setSize(600,600);
        frame.setTitle("Operating System Project");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,50,20));

        //JCombo box creation with options
        String[] options={"FCFS","NonPreemptive SJF","Preemptive SJF"};
        JComboBox box=new JComboBox(options);

        //setting font of combo box
        box.setFont(new Font("Times_New_Roman",Font.BOLD,40));

        //setting options accordingly
        JLabel l1=new JLabel("Enter Arrival Time:-");
        JLabel l2=new JLabel("Enter Burst Time:-");
        l1.setPreferredSize(new Dimension(400,50));
        l2.setPreferredSize(new Dimension(400,50));
        l1.setFont(new Font("Times_New_Roman",Font.BOLD,20));
        l2.setFont(new Font("Times_New_Roman",Font.BOLD,20));
        l1.setBackground(Color.cyan);
        l2.setBackground(Color.cyan);
        l1.setOpaque(true);
        l2.setOpaque(true);


        //fields for reading arrival and burst times
        JTextField field1=new JTextField();
        JTextField field2=new JTextField();
        field1.setPreferredSize(new Dimension(400,100));
        field2.setPreferredSize(new Dimension(400,100));
        field1.setFont(new Font("Times_New_Roman",Font.BOLD,30));
        field2.setFont(new Font("Times_New_Roman",Font.BOLD,30));

        //button to calculate results
        JButton bt=new JButton();
        bt.setText("Calculate");
        bt.setFont(new Font("Times_New_Roman",Font.BOLD,20));
        bt.setPreferredSize(new Dimension(200,50));
        bt.setBackground(Color.cyan);



            //adding action listener to button
            bt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //storing input initially into arrayLists
                    ArrayList<Integer> atVar = new ArrayList<Integer>();
                    ArrayList<Integer> btVar = new ArrayList<Integer>();

                    //////////////////////////////////////////////////////////////////////////
                    //reading input from fields
                    String str = field1.getText();
                    str = str.trim();
                    int m = 0;
                    int n = 0;
                    while (m != -1) {
                        m = str.indexOf(" ", m + 1);
                        if (m != -1) {
                            //handling exceptions
                            try {
                                atVar.add(Integer.parseInt(str.substring(n, m)));
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Enter Only Integer With One Space", "Error", JOptionPane.WARNING_MESSAGE);
                            }
                            n = m + 1;
                        }
                    }
                    if (n != str.length()) {
                        try {
                            atVar.add(Integer.parseInt(str.substring(n, str.length())));
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Enter Only Integer With One Space", "Error", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    ///////////////////////////////////////////////////////////////////////////////////

                    String str1 = field2.getText();
                    str1 = str1.trim();
                    m = 0;
                    n = 0;
                    while (m != -1) {
                        m = str1.indexOf(" ", m + 1);
                        if (m != -1) {
                            try {
                                btVar.add(Integer.parseInt(str1.substring(n, m)));
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Enter Only Integer With One Space", "Error", JOptionPane.WARNING_MESSAGE);
                            }
                            n = m + 1;
                        }

                    }
                    if (n != str1.length()) {
                        try {
                            btVar.add(Integer.parseInt(str1.substring(n, str1.length())));
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Enter Only Integer With One Space", "Error", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    ////////////////////////////////////////////////////////////////////////////////////

                    //printing result only when arrival time and burst time
                    //are equal in number
                    //otherwise printing error
                    if (atVar.size() == btVar.size()) {

                        //creating process array to store data for further computation
                        Process[] pArray = new Process[atVar.size()];
                        for (int i = 0; i < pArray.length; i++) {
                            pArray[i] = new Process();
                        }
                        //adding data
                        for (int i = 0; i < pArray.length; i++) {
                            pArray[i].at = atVar.get(i);
                            pArray[i].bt = btVar.get(i);
                            pArray[i].pid = i + 1;
                            pArray[i].ct = 0;
                            pArray[i].wt = 0;
                            pArray[i].tat = 0;
                        }


                        //creating second frame for output
                        JFrame frame2 = new JFrame();
                        frame2.setSize(1200, 700);
                        frame2.setTitle("Result");
                        frame2.setLayout(null);

                        //adding gantt chart to frame
                        JLabel info = new JLabel("Gantt Chart");
                        info.setFont(new Font("Times_New_Roman", Font.BOLD, 20));
                        info.setBounds(250, 10, 150, 30);
                        frame2.add(info);

                        if (box.getSelectedIndex() == 0) {
                            //printing result of fcfs
                            fcfs obj = new fcfs(pArray);
                            obj.completionTime();
                            obj.turnTime();
                            obj.waitTime();
                            obj.ganttChart(frame2);
                            result(frame2, pArray);
                        } else if (box.getSelectedIndex() == 1) {
                            //printing result of nonPremptSJF
                            nonPremptSJF obj = new nonPremptSJF(pArray);
                            obj.completionTime();
                            obj.turnTime();
                            obj.waitTime();
                            obj.ganttChart(frame2);
                            result(frame2, pArray);
                        } else if (box.getSelectedIndex() == 2) {
                            //printing result of premptSJF
                            premptSJF obj = new premptSJF(pArray, pArray.length);
                            obj.btStore();
                            obj.completionTime();
                            obj.reinitBt();
                            obj.turnTime();
                            obj.waitTime();
                            obj.ganttChart(frame2);
                            obj.reinitBt();
                            result(frame2, pArray);
                        }

                    }
                    //printing error if arrival times and burst times
                    //are not equal
                    else {
                        JOptionPane.showMessageDialog(null, "Please Ensure Number Of Burst Time Equal to Number Of Arrival Time", "Error", JOptionPane.WARNING_MESSAGE);
                    }

                }
            });


        //adding main frame components to main frame
        frame.add(box);
        frame.add(l1);
        frame.add(field1);
        frame.add(l2);
        frame.add(field2);
        frame.add(bt);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    //static method to print result in form of text fields
    static void result(JFrame frame,Process[] pArray){

        Collections.sort(Arrays.asList(pArray),new PidSort());
        JPanel panel=new JPanel(new FlowLayout(FlowLayout.LEADING,20,20));
        panel.setBounds(100,100,1150,600);

        JTextField[] top=new JTextField[6];
        top[0]=new JTextField("Process Id");
        top[1]=new JTextField("Arrival Time");
        top[2]=new JTextField("Burst Time");
        top[3]=new JTextField("Completion Time");
        top[4]=new JTextField("Turn Around Time");
        top[5]=new JTextField("Waiting Time");

        for(int i=0;i<6;i++){
            top[i].setFont(new Font("Times_New_Roman",Font.BOLD,15));
            top[i].setHorizontalAlignment(JTextField.CENTER);
            top[i].setBackground(Color.CYAN);
            top[i].setEditable(false);
            top[i].setPreferredSize(new Dimension(150,30));
            panel.add(top[i]);
        }

        //creating 2D array of fields to store data
        JTextField[][] data=new JTextField[pArray.length][6];

        for(int i=0;i<pArray.length;i++){
            data[i][0]=new JTextField("P"+Integer.toString(pArray[i].pid));
            String str="Ayush Sajwan 10000015514";
            data[i][0].setFont(new Font("Times_New_Roman",Font.BOLD,15));
            data[i][0].setHorizontalAlignment(JTextField.CENTER);
            data[i][0].setBackground(Color.CYAN);
            data[i][0].setEditable(false);
            data[i][0].setPreferredSize(new Dimension(150,30));

            data[i][1]=new JTextField(Integer.toString(pArray[i].at));
            data[i][1].setFont(new Font("Times_New_Roman",Font.BOLD,15));
            data[i][1].setHorizontalAlignment(JTextField.CENTER);
            data[i][1].setBackground(Color.CYAN);
            data[i][1].setEditable(false);
            data[i][1].setPreferredSize(new Dimension(150,30));

            data[i][2]=new JTextField(Integer.toString(pArray[i].bt));
            data[i][2].setFont(new Font("Times_New_Roman",Font.BOLD,15));
            data[i][2].setHorizontalAlignment(JTextField.CENTER);
            data[i][2].setBackground(Color.CYAN);
            data[i][2].setEditable(false);
            data[i][2].setPreferredSize(new Dimension(150,30));

            data[i][3]=new JTextField(Integer.toString(pArray[i].ct));
            data[i][3].setFont(new Font("Times_New_Roman",Font.BOLD,15));
            data[i][3].setHorizontalAlignment(JTextField.CENTER);
            data[i][3].setBackground(Color.CYAN);
            data[i][3].setEditable(false);
            data[i][3].setPreferredSize(new Dimension(150,30));

            data[i][4]=new JTextField(Integer.toString(pArray[i].tat));
            data[i][4].setFont(new Font("Times_New_Roman",Font.BOLD,15));
            data[i][4].setHorizontalAlignment(JTextField.CENTER);
            data[i][4].setBackground(Color.CYAN);
            data[i][4].setEditable(false);
            //Ayush Sajwan 1000015514
            data[i][4].setPreferredSize(new Dimension(150,30));

            data[i][5]=new JTextField(Integer.toString(pArray[i].wt));
            data[i][5].setFont(new Font("Times_New_Roman",Font.BOLD,15));
            data[i][5].setHorizontalAlignment(JTextField.CENTER);
            data[i][5].setBackground(Color.CYAN);
            data[i][5].setEditable(false);
            data[i][5].setPreferredSize(new Dimension(150,30));

        }

        for(int i=0;i<pArray.length;i++){
            panel.add(data[i][0]);
            panel.add(data[i][1]);
            panel.add(data[i][2]);
            panel.add(data[i][3]);
            panel.add(data[i][4]);
            panel.add(data[i][5]);
        }

        JLabel[] blank=new JLabel[3];
        for(int i=0;i<3;i++){
            blank[i]=new JLabel();
            blank[i].setPreferredSize(new Dimension(150,30));
            panel.add(blank[i]);
        }

        JTextField avg=new JTextField("Average:-");
        avg.setHorizontalAlignment(JTextField.CENTER);
        avg.setPreferredSize(new Dimension(150,30));
        avg.setFont(new Font("Times_New_Roman",Font.BOLD,15));
        avg.setBackground(Color.cyan);
        avg.setEditable(false);
        panel.add(avg);

        float avgTat=0;
        float avgWat=0;
        for(int i=0;i<pArray.length;i++){
            avgTat+=pArray[i].tat;
            avgWat+=pArray[i].wt;
        }
        avgTat=(avgTat/pArray.length);
        avgWat=(avgWat/pArray.length);

        JTextField Tat=new JTextField(Float.toString(avgTat));
        Tat.setHorizontalAlignment(JTextField.CENTER);
        Tat.setPreferredSize(new Dimension(150,30));
        Tat.setFont(new Font("Times_New_Roman",Font.BOLD,15));
        Tat.setBackground(Color.cyan);
        Tat.setEditable(false);
        panel.add(Tat);

        JTextField Wat=new JTextField(Float.toString(avgWat));
        Wat.setHorizontalAlignment(JTextField.CENTER);
        Wat.setPreferredSize(new Dimension(150,30));
        Wat.setFont(new Font("Times_New_Roman",Font.BOLD,15));
        Wat.setBackground(Color.cyan);
        Wat.setEditable(false);
        panel.add(Wat);

        JLabel name=new JLabel("Created By:Piyush Gupta \n Sap id:1000014993");
        name.setPreferredSize(new Dimension(600,50));
        name.setFont(new Font("Times_New_Roman",Font.ITALIC,20));
        panel.add(name);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
