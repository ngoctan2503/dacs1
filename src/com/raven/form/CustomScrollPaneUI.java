/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.form;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.awt.*;

public class CustomScrollPaneUI extends BasicScrollPaneUI {

    protected void paintBorder(Graphics g) {
        // Không làm gì để ẩn đường viền
    }

    protected void paintViewportBorder(Graphics g, Insets insets, Rectangle bounds) {
        // Không làm gì để ẩn đường viền
    }
}

