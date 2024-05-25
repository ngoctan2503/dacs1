/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.raven.event.EventItem;
import com.raven.model.ModelItem;
import com.raven.swing.EventCallBack;
import com.raven.swing.EventTextField;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

/**
 *
 * @author ledat
 */
public class Form8 extends javax.swing.JPanel {

    /**
     * Creates new form Form8
     */
    private FormHome home;
    private Animator animator;
    private Point animatePoint;
    private ModelItem itemSelected;
    public ArrayList<ModelItem> itemListWomen;
    /**
     * Creates new form Form7
     */
    public Form8() {
        initComponents();
        setOpaque(false);
        init();
        //  Animator start form animatePoint to TagetPoint
        animator = PropertySetter.createAnimator(500, mainPanel1, "imageLocation", animatePoint, mainPanel1.getTargetLocation());
        animator.addTarget(new PropertySetter(mainPanel1, "imageSize", new Dimension(180, 120), mainPanel1.getTargetSize()));
        animator.addTarget(new TimingTargetAdapter() {
            @Override
            public void end() {
                mainPanel1.setImageOld(null);
            }
        });
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        textFieldAnimation1.addEvent(new EventTextField() {
    @Override
    public void onPressed(EventCallBack call) {
        try {
            String searchText = textFieldAnimation1.getText(); 
            ArrayList<ModelItem> searchResults = new ArrayList<>();

            for (ModelItem item : itemListWomen) {
                if (item.getItemName().toLowerCase().contains(searchText.toLowerCase())) {
                  
                    searchResults.add(item);
                }
            }

            
            home.getPanelItem().removeAll();
            

            for (ModelItem result : searchResults) {
                home.addItem(result);
            }
            call.done();
        }  
        catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void onCancel() {
        
    }
});
         combobox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPriceRange = (String) combobox1.getSelectedItem();
                filterItemsByPrice(selectedPriceRange);
            }
        });
        
    }

    private void init() {
        home = new FormHome();
        mainPanel1.setLayout(new BorderLayout());
        mainPanel1.add(home);
        itemListWomen = new ArrayList<>();
            itemListWomen.add(new ModelItem(1, "BUSHIDO III WIDE WOMENS", "Get ready to conquer the most technical trails with the all-new Bushido III Wide Women's Mountain Running® shoe. Building on the incredible 10-year legacy of the original Bushido, this updated version features a new outsole and rubber compound design, updated aesthetics, increased breathability and now offered with a Wide Fit! Whether you’re an experienced trail runner or just starting out, the Bushido III is an easy choice if you want to take your performance to the next level.", 1, 160, "Adidas", 160, new ImageIcon(getClass().getResource("/com/raven/images/wm1.png"))));
            itemListWomen.add(new ModelItem(2, "BUSHIDO III WOMENS", "Get ready to conquer the most technical trails with the all-new Bushido III Women's Mountain Running® shoe. Building on the incredible 10-year legacy of the original Bushido, this updated version features a new outsole and rubber compound design, updated aesthetics, increased breathability and now offered with a Wide Fit option! Whether you’re an experienced trail runner or just starting out, the Bushido III is an easy choice if you want to take your performance to the next level..", 1, 460, "Adidas", 460, new ImageIcon(getClass().getResource("/com/raven/images/wm2.png"))));
            itemListWomen.add(new ModelItem(3, "PRODIGIO WOMENS", "Introducing the Prodigio Womens, a revolutionary new trail running shoe featuring new XFlow™ foam; a nitrogen-infused EVA supercritical foam technology that provides unparalleled cushioning, stability and energy return making it the ultimate choice for trail runners demanding the best. Weighing in under 300g, the Prodigio is lightweight and responsive, helping you move faster and more efficiently on the trail. Don’t worry about being a Mountain Running prodigy - with the La Sportiva Prodigio, anyone can experience revolutionary trail running technology.", 1, 155, "Adidas", 155, new ImageIcon(getClass().getResource("/com/raven/images/wm3.png"))));
            itemListWomen.add(new ModelItem(4, "LEVANTE WOMENS", "The Levante is the result of direct collaboration with our female athletes. Working closely together, they selected every detail from the fit, lacing, and upper to the sole, creating a thoroughly researched, athlete-approved, long-distance running shoe. New XFlow™ super foam provides cushioning, reactivity, and stability while the upper is constructed to encase, support and protect without constricting the foot. The Trail Rocker™ encourages the athlete to strike a natural stance with a harmonious roll as the flex grooves ensure flexibility and agility even for the most demanding runners.", 1, 155, "Adidas", 155, new ImageIcon(getClass().getResource("/com/raven/images/wm4.png"))));
            itemListWomen.add(new ModelItem(5, "JACKAL II WOMENS", "Designed for runners looking for plush cushion, a wider fit and higher volume, the Jackal II Women's trail-running shoes keep your feet steady and stoked for long runs through rough and technical terrain. The updated model maintains all the best aspects of the original now with new eco-friendly upper materials, a new heel/tongue construction and enhanced breathability - all in a lighter weight design.", 1, 165, "Adidas", 165, new ImageIcon(getClass().getResource("/com/raven/images/wm5.png"))));
            itemListWomen.add(new ModelItem(6, "JACKAL II BOA WOMENS", "The Jackal II BOA Women's is a performance-oriented Mountain Running® shoe designed to take on any distance but built with Ultra-Long distance adventures in mind. It shares the same sole package as the Jackal II which is equipped with Infinitoo™ cushion technology for less energy expenditure. The protective upper with a precise and secure fit ensures stability even on the most technical sections of trail while the double BOA® Fit System makes heel/forefoot volume adjustments a breeze while on the move.", 1, 160, "Adidas", 160, new ImageIcon(getClass().getResource("/com/raven/images/wm6.png"))));
            itemListWomen.add(new ModelItem(7, "JACKAL II GTX WOMENS", "Designed for runners looking for plush cushion, a wider fit and higher volume, the Jackal II GTX Women's trail-running shoes keep your feet steady and stoked for long runs through rough, technical terrain in all conditions. The updated model maintains all the best aspects of the original Jackal GTX now with new eco-friendly upper materials, a new heel/tongue construction and enhanced breathability - all in a lighter weight design.", 1, 195, "Adidas", 195, new ImageIcon(getClass().getResource("/com/raven/images/wm7.png"))));
            itemListWomen.add(new ModelItem(8, "BUSHIDO II WOMENS", "For those looking for the perfect combination of responsiveness, stability and comfort with bomber protection and superb traction, the Bushido II has all that and more. They offer enhanced cushioning while also providing the user with a neutral, stable, lightweight, sticky and aggressive outsole. With unbeatable performance and protection on technical terrain, the Bushido II will excel on any mountain run.", 1, 93, "Adidas", 93, new ImageIcon(getClass().getResource("/com/raven/images/wm8.png"))));
            itemListWomen.add(new ModelItem(9, "BUSHIDO II GTX WOMENS", "With the Bushido II GTX, the cold, snow, rain or ice won’t stop you from running on your favorite trails in every season. The Gore-Tex® Invisible Fit™ membrane repels the elements without compromising fit while a balanced blend of traction and grip will keep you upright and secure on all types of terrain.", 1, 111, "Adidas", 111, new ImageIcon(getClass().getResource("/com/raven/images/wm9.png"))));
            itemListWomen.add(new ModelItem(10, "AKASHA II WOMENS", "Delivering the perfect blend of cushion, responsiveness and outsole traction, the Akasha II Women's is the preferred shoe for trail runners seeking technical terrain or high mileage outings. In addition to aesthetic modifications, the 2022 updates of the Akasha II Women's focus on increased durability, protection and breathability.", 1, 175, "Adidas", 175, new ImageIcon(getClass().getResource("/com/raven/images/wm10.png"))));
            itemListWomen.add(new ModelItem(11, "MUTANT WOMENS", "Featuring an aggressive outsole, burly lugs and an adaptable fit, the Mutant Women's is built for covering serious terrain. Equipped with the stickiest FriXion® XF 2.0 rubber to ensure positive traction over wet and loose trails and featuring a unique FusionGate™ lacing system, the Mutant inspires confidence and speed in the mountains. Updated graphics inspired by the topography of the Val di Fiemme and manufactured with more sustainable materials.", 1, 165, "Adidas", 165, new ImageIcon(getClass().getResource("/com/raven/images/wm11.png"))));
            itemListWomen.add(new ModelItem(12, "JACKAL GTX WOMENS", "The Jackal GTX Women's is designed to meet the demands of long distance runners looking for exceptional cushioning and a wider fit, with the waterproof protection offered by Gore-Tex®.", 1, 97, "Adidas", 97, new ImageIcon(getClass().getResource("/com/raven/images/wm12.png"))));
            itemListWomen.add(new ModelItem(13, "CYKLON WOMENS", "When you need precision, stability and synergy between your foot and the trail look no further than the Cyklon Women's. Through extensive R&D between La Sportiva and BOA®, the Cyklon was designed for skyraces and offroad technical terrain for short to medium distances.", 1, 185, "Adidas", 185, new ImageIcon(getClass().getResource("/com/raven/images/wm13.png"))));
            itemListWomen.add(new ModelItem(14, "CYKLON CROSS GTX WOMENS", "Balancing warmth, protection and traction is an ever evolving journey to create the best winter running shoe possible. The new Cyklon Cross GTX Women's combines vegan materials, an ultra-lightweight BOA® Fit System, and the tried and true Cyklon midsole/outsole package and a GORE-TEX® waterproof membrane for all your winter running desires.", 1, 545, "Adidas", 545, new ImageIcon(getClass().getResource("/com/raven/images/wm14.png"))));
            itemListWomen.add(new ModelItem(15, "HELIOS III WOMENS", "As the third-generation re-design on the ever-popular Helios, the Women’s Helios III offers everything you need in an ultralight and perfectly cushioned trail shoe. We added additional tongue and ankle padding for more comfort on longer runs. A combination of rip-stop nylon and mono-burr inserts enable optimal breathability but also abrasion resistance. The low profile, thermo formed toe cap provides protection while the Frixion ® AT 2.0 rubber outsole is long lasting and remarkably versatile.", 1, 155, "Adidas", 155, new ImageIcon(getClass().getResource("/com/raven/images/wm15.png"))));
            itemListWomen.add(new ModelItem(16, "KAPTIVA WOMENS", "The Kaptiva will allow you to unleash your speed while training, racing or on long days in the mountains. The unique design allows them to provide the perfect balance of protection, cushion, stability and traction. Designed for those needing a precise, comfortable fit for running on technical terrain, they deliver superior grip on any surface.", 1, 99, "Adidas", 99, new ImageIcon(getClass().getResource("/com/raven/images/wm16.png"))));
            itemListWomen.add(new ModelItem(17, "KARACAL WOMENS", "Take your training to the next level with the Karacal Women's, a Mountain Running® shoe that will keep you smiling mile after mile. With its high volume fit and sublime cushion, the Karacal will deliver performance on the most technical of trails and on race day.", 1, 93, "Adidas", 93, new ImageIcon(getClass().getResource("/com/raven/images/wm17.png"))));
            itemListWomen.add(new ModelItem(18, "ULTRA RAPTOR II WOMENS", "The Ultra Raptor II Women's is an all-terrain Mountain Running® shoe perfect for long distance runs and ventures off the beaten path. A full-length rock guard and an ultra sticky rubber outsole offers maximum protection and stability, while the wicking AirMesh mesh upper keeps you cool, dry, and comfortable so you can continue to focus on the trail ahead of you.", 1, 149, "Adidas", 149, new ImageIcon(getClass().getResource("/com/raven/images/wm18.png"))));
            itemListWomen.add(new ModelItem(19, "ULTRA RAPTOR II WIDE WOMENS", "The Ultra Raptor II Women's is ideal for runners who are planning to (or unintentionally) take their adventure off road and encounter gnarly trails with choss, steep single-track or rock littered scree fields and want a good balance of protection, stability and traction. WIDE FIT. Normal fit is also AVAILABLE HERE.", 1, 149, "Adidas", 149, new ImageIcon(getClass().getResource("/com/raven/images/wm19.png"))));
            itemListWomen.add(new ModelItem(20, "ULTRA RAPTOR II GTX WOMENS", "The Ultra Raptor II GTX Women's is an all-terrain Mountain Running® shoe perfect for long distance runs and adventures off the beaten path - especially when you need protection from unpredictable weather conditions. The MEMlex midsole and ultra sticky rubber outsole provide maximum protection and traction, while the wicking AirMesh mesh upper in conjunction with the Gore-Tex® lining keeps you cool, dry, and comfortable so you can continue to focus on the trail ahead of you.", 1, 117, "Adidas", 117, new ImageIcon(getClass().getResource("/com/raven/images/wm20.png"))));
            itemListWomen.add(new ModelItem(21, "ULTRA RAPTOR II WIDE GTX WOMENS", "The Ultra Raptor II Women's is ideal for runners who are planning to (or unintentionally) take their adventure off road and encounter gnarly trails with choss, steep single-track or rock littered scree fields and want a good balance of protection, stability and traction. WIDE FIT. Normal fit is also AVAILABLE HERE.", 1, 117, "Adidas", 117, new ImageIcon(getClass().getResource("/com/raven/images/wm21.png"))));
            itemListWomen.add(new ModelItem(22, "WILDCAT WOMEN'S", "The Wildcat Women's is a highly breathable and cushioned Mountain Running® Shoe that excels at long trail outings where extra protection is needed.", 1, 145, "Adidas", 145, new ImageIcon(getClass().getResource("/com/raven/images/wm22.png"))));
            itemListWomen.add(new ModelItem(23, "WILDCAT 2.0 GTX WOMENS", "The Wildcat 2.0 GTX Women's is a highly stable, neutral trail runner that provides excellent cushioning and a secure fit. The waterproof Gore-Tex® lining keeps your feet dry in wet conditions, and the aggressive outsole and supple midsole combine to ensure comfort and stability on rough trails.", 1, 175, "Adidas", 175, new ImageIcon(getClass().getResource("/com/raven/images/wm23.png"))));
//itemListWomen.add(new ModelItem(1, "BUSHIDO III WIDE WOMENS", "This.", 160, "Adidas", new ImageIcon(getClass().getResource("/com/raven/images/wm1.png"))));
            

            
            home.setEvent(new EventItem() {
            @Override
            public void itemClick(Component com, ModelItem item) {
                if (itemSelected != null) {
                    mainPanel1.setImageOld(itemSelected.getImage());
                }
                if (itemSelected != item) {
                    if (!animator.isRunning()) {
                        itemSelected = item;
                        animatePoint = getLocationOf(com);
                        mainPanel1.setImage(item.getImage());
                        mainPanel1.setImageLocation(animatePoint);
                        mainPanel1.setImageSize(new Dimension(180, 120));
                        mainPanel1.repaint();
                        home.setSelected(com);
                        home.showItem(item);
                        animator.start();
                    }
                }
            }
        });
        for (ModelItem item : itemListWomen) {
            home.addItem(item);
        }
    }

    

    private Point getLocationOf(Component com) {
        Point p = home.getPanelItemLocation();
        int x = p.x;
        int y = p.y;
        int itemX = com.getX();
        int itemY = com.getY();
        int left = 10;
        int top = 35;
        return new Point(x + itemX + left, y + itemY + top);
    }
    private void filterItemsByPrice(String priceRange) {
        ArrayList<ModelItem> filteredItems = new ArrayList<>();

        for (ModelItem item : itemListWomen) {
            if (isWithinPriceRange(item, priceRange)) {
                filteredItems.add(item);
            }
        }

        home.getPanelItem().removeAll();

        for (ModelItem filteredItem : filteredItems) {
            home.addItem(filteredItem);
        }
    }

    private boolean isWithinPriceRange(ModelItem item, String priceRange) {
    double price = item.getPrice();

    String[] range = priceRange.split("-");
    int minPrice = Integer.parseInt(range[0].replace("$", "").trim());
    int maxPrice;

    if (range[1].contains("<")) {
        maxPrice = Integer.MAX_VALUE; 
    } else {
        
        maxPrice = Integer.parseInt(range[1].replace("$", "").trim());
    }
    return price >= minPrice && price <= maxPrice;
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel1 = new com.raven.swing.MainPanel();
        textFieldAnimation1 = new com.raven.swing.TextFieldAnimation();
        combobox1 = new com.raven.swing.Combobox();

        javax.swing.GroupLayout mainPanel1Layout = new javax.swing.GroupLayout(mainPanel1);
        mainPanel1.setLayout(mainPanel1Layout);
        mainPanel1Layout.setHorizontalGroup(
            mainPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 845, Short.MAX_VALUE)
        );
        mainPanel1Layout.setVerticalGroup(
            mainPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 619, Short.MAX_VALUE)
        );

        combobox1.setFont(new java.awt.Font("Segoe UI", 1, 14));
        combobox1.setLabeText("Search by price");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(textFieldAnimation1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textFieldAnimation1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addComponent(mainPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        combobox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"0$-100$", "100$-200$", "200$-500$", "500$-1000000$"}));
        combobox1.setSelectedIndex(-1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Combobox combobox1;
    private com.raven.swing.MainPanel mainPanel1;
    private com.raven.swing.TextFieldAnimation textFieldAnimation1;
    // End of variables declaration//GEN-END:variables
}
