package com.company;

import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame {
    private JPanel mainPanel;
    private JTextArea historyTextArea;
    private JTextField formulaInput;
    private JList funcionsList;
    private JButton evalButton;
    private JScrollPane scrollContainerPane;
    private Double lastResult=0.;
    private String lastEntry="";
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;

    public Calculator(String title){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
        setTitle(title);
        setSize(800, 500);
        setMinimumSize(new Dimension(600, 400));
        historyTextArea.setEditable(false);

        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        menuBar.add(menu);
        menuItem = new JMenuItem("Reset");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                historyTextArea.selectAll();
                historyTextArea.replaceSelection("");
            }
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(menuItem);
        setJMenuBar(menuBar);

        Function sin=new Function("Sinus","sin()");
        Function cos=new Function("Cosinus","cos()");
        Function tan=new Function("Tangens","tan()");
        Function ctg=new Function("Cotangens","ctan()");
        Function sqrt=new Function("Square root","sqrt()");
        Function addi=new Function("Addition","+");
        Function subs=new Function("Subtraction","-");
        Function mult=new Function("Multiplication","*");
        Function pi=new Function("PI Value","pi");
        Function e=new Function("E number","e");
        Function phi=new Function("Phi number","[phi]");
        Function last=new Function("Last Result","");
        DefaultListModel<Function> listModel=new DefaultListModel<>();
        listModel.addElement(sin);
        listModel.addElement(cos);
        listModel.addElement(tan);
        listModel.addElement(ctg);
        listModel.addElement(sqrt);
        listModel.addElement(addi);
        listModel.addElement(subs);
        listModel.addElement(mult);
        listModel.addElement(pi);
        listModel.addElement(e);
        listModel.addElement(phi);
        listModel.addElement(last);
        funcionsList.setModel(listModel);

        evalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Expression expression = new Expression(formulaInput.getText());
                if (expression.checkSyntax()) {
                    Double result = expression.calculate();
                    historyTextArea.append(formulaInput.getText()+"\n                         = "+result+"\n------\n");
                    lastResult=result;
                }
                else {
                    String errorMessage = expression.getErrorMessage();
                    JOptionPane.showMessageDialog(null, "Wrong Input!\n" , "Calculator ERROR", JOptionPane.ERROR_MESSAGE);
                }
                lastEntry=formulaInput.getText();
                formulaInput.setText("");
            }
        });

        formulaInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if(e.getKeyCode() == 38) {
                    formulaInput.setText(lastEntry);
                }

                if(e.getKeyCode() == 10) {
                    Expression expression = new Expression(formulaInput.getText());
                    if (expression.checkSyntax()) {
                        Double result = expression.calculate();
                        historyTextArea.append(formulaInput.getText()+"\n                          = "+result+"\n------\n");
                        lastResult=result;
                    }
                    else {
                        String errorMessage = expression.getErrorMessage();
                        JOptionPane.showMessageDialog(null, "Wrong Input!\n" , "Calculator ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    lastEntry=formulaInput.getText();
                    formulaInput.setText("");
                }
            }
        });

        funcionsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList klik=(JList)e.getSource();
                if (e.getClickCount() == 2) {
                    int index = klik.locationToIndex(e.getPoint());
                    if(klik.getSelectedValue().toString().equals("Last Result")!=true)
                    {
                        Function m=(Function)klik.getSelectedValue();
                        formulaInput.setText(m.getsName());

                        if(m.getsName().contains("()"))
                            formulaInput.setCaretPosition(m.getsName().length()-1);

                    }
                    else
                        formulaInput.setText(lastResult.toString());

                    formulaInput.requestFocus();
                    lastEntry=formulaInput.getText();
                }
            }
        });
    }
}
