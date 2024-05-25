package com.raven.form;

import ConnectMySQL.MySQL;
import com.raven.component.Item;
import com.raven.event.EventItem;
import com.raven.model.ModelItem;
import com.raven.swing.PanelItem;
import com.raven.swing.ScrollBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.jar.Attributes.Name;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import raven.table.Testt;

public class FormHome extends javax.swing.JPanel {
    ModelItem item = new ModelItem();
    public void setEvent(EventItem event) {
        this.event = event;
    }

    private EventItem event;

    public FormHome() {
        initComponents();
        scroll.setVerticalScrollBar(new ScrollBar());
    }

    public void addItem(ModelItem data) {
        Item item = new Item();
        item.setData(data);
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    event.itemClick(item, data);
                }
            }
        });
        panelItem.add(item);
        panelItem.repaint();
        panelItem.revalidate();
    }

    public void setSelected(Component item) {
        for (Component com : panelItem.getComponents()) {
            Item i = (Item) com;
            if (i.isSelected()) {
                i.setSelected(false);
            }
        }
        ((Item) item).setSelected(true);
    }

    public void showItem(ModelItem data) {
        lbItemName.setText(data.getItemName());
        txtDescription.setText(data.getDescription());
        lbBrand.setText(data.getBrandName());
        DecimalFormat df = new DecimalFormat("#,##000");
        lbPrice.setText("$"+df.format(data.getPrice()));
    }

    public Point getPanelItemLocation() {
        Point p = scroll.getLocation();
        return new Point(p.x, p.y - scroll.getViewport().getViewPosition().y);
    }
    public PanelItem getPanelItem() {
    return panelItem;
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        panelItem = new com.raven.swing.PanelItem();
        jPanel1 = new javax.swing.JPanel();
        lbItemName = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        lbBrand = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtDescription = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();

        setOpaque(false);

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelItem.setBackground(new java.awt.Color(245, 245, 245));
        scroll.setViewportView(panelItem);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        lbItemName.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lbItemName.setForeground(new java.awt.Color(76, 76, 76));
        lbItemName.setText("Item Name");

        lbPrice.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(76, 76, 76));
        lbPrice.setText("$0.00");

        lbBrand.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbBrand.setForeground(new java.awt.Color(76, 76, 76));
        lbBrand.setText("Brand");

        txtDescription.setBorder(null);
        txtDescription.setFont(new java.awt.Font("sansserif", 1, 12));
        txtDescription.setForeground(new java.awt.Color(178, 178, 178));
        txtDescription.setFocusable(false);

        jButton1.setBackground(java.awt.Color.black);
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ADD TO CART");
        jButton1.setBorder(null);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbBrand)
                            .addComponent(lbPrice))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbItemName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescription, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbItemName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBrand)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        scroll.getVerticalScrollBar().setUnitIncrement(66);
        scroll.getVerticalScrollBar().setBlockIncrement(64);
        //jPanel1.setBackground(new Color(242,242,242));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
        jButton1.setBackground(Color.GREEN);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        // TODO add your handling code here:
        jButton1.setBackground(Color.BLACK);
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ImageIcon customIcon = new ImageIcon("C:\\Xaydungungdungmuabangiaytrendesktop - Copy\\src\\com\\raven\\icon\\tichxanh.png");
        String priceText = lbPrice.getText().replace("$", "").replace(",", "");
        double price = Double.parseDouble(priceText);
        try {
            MySQL.getConnection().addItem(lbItemName.getText(), txtDescription.getText(), 1, price, lbBrand.getText(), price);
            JOptionPane.showMessageDialog(null, "Đã thêm sản phẩm vào giỏ hàng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE,customIcon);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbBrand;
    private javax.swing.JLabel lbItemName;
    private javax.swing.JLabel lbPrice;
    private com.raven.swing.PanelItem panelItem;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextPane txtDescription;
    // End of variables declaration//GEN-END:variables
}
