package OSSchedulingAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class PidSort implements Comparator<Process>{
    @Override
    public int compare(Process o1, Process o2) {
        return o1.pid-o2.pid;
    }
}