/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.draw.gui;

import eci.pdsw.draw.controller.IController;
import eci.pdsw.draw.model.Shape;
import eci.pdsw.pattern.observer.Observer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

/**
 *
 * @author fchaves
 */
public class ShapeList extends JPanel implements Observer {

    private final IController controller;
    private DefaultListModel<String> listModel;
    private JList<String> shapeList;
    private JButton deleteButton;

    public ShapeList(IController control) {
        super(new BorderLayout());
        this.listModel = new DefaultListModel();
        this.shapeList = new JList(listModel);
        this.controller = control;
        this.add(shapeList, BorderLayout.CENTER);
        shapeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.deleteButton = new JButton("Delete Shape");

        deleteButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = shapeList.getSelectedIndex();
                if (index >= 0) {
                    controller.deleteShape(index);
                }
            }
        });

        this.add(deleteButton, BorderLayout.SOUTH);
        this.controller.addObserver(this);
    }

    
    public int getSelectedShapeIndex(){
        return shapeList.getSelectedIndex();
    }
    
    @Override
    public void update() {
        listModel.removeAllElements();
        List<Shape> shapes = controller.getShapes();
        for (Shape elem : shapes) {
            listModel.addElement(elem.getElementType().name());
        }
    }
}
