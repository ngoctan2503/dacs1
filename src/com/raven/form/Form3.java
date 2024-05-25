/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;
import com.raven.event.EventItem;
import com.raven.form.FormHome;
import com.raven.model.ModelItem;
import com.raven.swing.EventCallBack;
import com.raven.swing.EventTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.PropertySetter;
/**
 *
 * @author ledat
 */
public class Form3 extends javax.swing.JPanel {
    private FormHome home;
    private Animator animator;
    private Point animatePoint;
    private ModelItem itemSelected;
    public ArrayList<ModelItem> itemListKid;
    /**
     * Creates new form Form7
     */
    public Form3() {
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
                    String searchText = textFieldAnimation1.getText(); // Lấy nội dung từ TextField tìm kiếm
                    ArrayList<ModelItem> searchResults = new ArrayList<>(); // Danh sách kết quả tìm kiếm

                    // Duyệt qua danh sách các mục để tìm kiếm
                    for (ModelItem item : itemListKid) {
                        if (item.getItemName().toLowerCase().contains(searchText.toLowerCase())) {
                            // Nếu tên mục chứa chuỗi tìm kiếm, thêm mục vào danh sách kết quả
                            searchResults.add(item);
                        }
                    }

                    // Xóa tất cả các mục hiện tại trong panelItem của FormHome
                    home.getPanelItem().removeAll();


                    // Thêm các mục tìm kiếm được vào panelItem của FormHome
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
        itemListKid = new ArrayList<>();
        itemListKid.add(new ModelItem(1, "DUNK", "Recognising the Dunk's roots as the top-ranking university-team sneaker, the Be True To Your School Pack looks to the original ad campaign for inspiration. Colours represent top-flight universities, while crisp leather has the perfect amount of sheen to make 'em a hands-down win. So lace up and show off that varsity spirit. Are you game?", 1, 160, "Adidas", 160, new ImageIcon(getClass().getResource("/com/raven/images/img1.png"))));
        itemListKid.add(new ModelItem(2, "BLAZER", "True to Nike's DNA, built for today. The Blazer Phantom reimagines a classic silhouette in a sleek, low profile. An improved underfoot sensation with thicker sidewalls amplifies comfort without creating bulk.", 1, 200, "Adidas", 200, new ImageIcon(getClass().getResource("/com/raven/images/img2.png"))));
        itemListKid.add(new ModelItem(3, "AIR FORCE 1", "NMD City Stock 2", 1, 100, "Adidas", 100, new ImageIcon(getClass().getResource("/com/raven/images/img3.png"))));
        itemListKid.add(new ModelItem(4, "AIR JORDAN 1", "NMD City Stock 2", 1, 600, "Adidas", 600, new ImageIcon(getClass().getResource("/com/raven/images/img4.png"))));
        itemListKid.add(new ModelItem(5, "NIKE DUNK LOW RETRO", "Recognising the Dunk's roots as the top-ranking university-team sneaker, the Be True To Your School Pack looks to the original ad campaign for inspiration. Colours represent top-flight universities, while crisp leather has the perfect amount of sheen to make 'em a hands-down win. So lace up and show off that varsity spirit. Are you game?", 1, 120, "Adidas", 120, new ImageIcon(getClass().getResource("/com/raven/images/img5.png"))));
        itemListKid.add(new ModelItem(6, "NIKE DUNK HIGH RETRO", "Created for the hardwood but taken to the streets, the '80s basketball icon returns with perfectly sheened overlays and original university colours. With its classic hoops design, the Nike Dunk High Retro channels '80s vintage back onto the streets while its padded, high-top collar adds an old-school look rooted in comfort.", 1, 160, "Adidas", 160, new ImageIcon(getClass().getResource("/com/raven/images/img6.png"))));
        itemListKid.add(new ModelItem(7, "LA SPORTIVA", "NMD City Stock 1", 1, 1099, "Adidas", 1099, new ImageIcon(getClass().getResource("/com/raven/images/img7.png"))));
        itemListKid.add(new ModelItem(8, "LA SPORTIVA", "NMD City Stock 2", 1, 1199, "Adidas", 1199, new ImageIcon(getClass().getResource("/com/raven/images/img8.png"))));
        itemListKid.add(new ModelItem(9, "LA SPORTIVA X JANJI JACKAL II", "NMD City Stock 2", 1, 105, "Adidas", 105, new ImageIcon(getClass().getResource("/com/raven/images/img9.png"))));
        itemListKid.add(new ModelItem(10, "JACKAL II BOA", "NMD City Stock 2", 1, 185, "LA SPORTIVA", 185, new ImageIcon(getClass().getResource("/com/raven/images/img10.png"))));
        itemListKid.add(new ModelItem(11, "AKASHA II", "NMD City Stock 2", 1, 275, "LA SPORTIVA", 275, new ImageIcon(getClass().getResource("/com/raven/images/img11.png"))));
        itemListKid.add(new ModelItem(12, "MUTANT", "NMD City Stock 2", 1, 165, "LA SPORTIVA", 165, new ImageIcon(getClass().getResource("/com/raven/images/img12.png"))));
        itemListKid.add(new ModelItem(13, "CYKLON CROSS GTX", "NMD City Stock 2", 1, 245, "LA SPORTIVA", 245, new ImageIcon(getClass().getResource("/com/raven/images/img13.png"))));
        itemListKid.add(new ModelItem(14, "CYKLON", "NMD City Stock 2", 1, 235, "LA SPORTIVA", 235, new ImageIcon(getClass().getResource("/com/raven/images/img14.png"))));
        itemListKid.add(new ModelItem(15, "BLIZZARD GTX", "NMD City Stock 2", 1, 275, "Adidas", 275, new ImageIcon(getClass().getResource("/com/raven/images/img15.png"))));
        itemListKid.add(new ModelItem(16, "HELIOS III", "As the third-generation re-design on the ever-popular Helios, the Helios III offers everything you need in an ultralight and perfectly cushioned trail shoe. We added additional tongue and ankle padding for more comfort on longer runs. A combination of rip-stop nylon and mono-burr inserts enable optimal breathability but also abrasion resistance. The low profile, thermo formed toe cap provides protection while the Frixion ® AT 2.0 rubber outsole is long lasting and remarkably versatile.", 1, 275, "LA SPORTIVA", 275, new ImageIcon(getClass().getResource("/com/raven/images/img16.png"))));
        itemListKid.add(new ModelItem(17, "ULTRA RAPTOR II", "The Ultra Raptor II is an all-terrain Mountain Running® shoe perfect for long distance runs and ventures off the beaten path. A full-length rock guard and an ultra sticky rubber outsole offers maximum protection and stability, while the wicking AirMesh mesh upper keeps you cool, dry, and comfortable so you can continue to focus on the trail ahead of you.", 1, 249, "LA SPORTIVA", 249, new ImageIcon(getClass().getResource("/com/raven/images/img17.png"))));
        itemListKid.add(new ModelItem(18, "ULTRA RAPTOR II WIDE GTX", "The Ultra Raptor II Wide GTX is an all-terrain Mountain Running® shoe perfect for long distance runs and adventures off the beaten path - especially when you need protection from unpredictable weather conditions. The MEMlex midsole and ultra sticky rubber outsole provide maximum protection and traction, while the wicking AirMesh mesh upper in conjunction with the Gore-Tex® lining keeps you cool, dry, and comfortable so you can continue to focus on the trail ahead of you. WIDE FIT. Normal fit is also AVAILABLE HERE.", 1, 117, "LA SPORTIVA", 117, new ImageIcon(getClass().getResource("/com/raven/images/img18.png"))));
        itemListKid.add(new ModelItem(19, "WILDCAT 2.0 GTX", "The Wildcat 2.0 GTX is a highly stable, neutral trail runner that provides excellent cushioning and a secure fit. The waterproof Gore-Tex® lining keeps your feet dry in wet conditions, and the aggressive outsole and supple midsole combine to ensure comfort and stability on rough trails.", 1, 175, "LA SPORTIVA", 175, new ImageIcon(getClass().getResource("/com/raven/images/img19.png"))));
        itemListKid.add(new ModelItem(20, "BUSHIDO II JR", "The performance driven design and award-winning Bushido II is now available for kids with the Bushido II Jr model. The shoe is designed for children’s free time and family adventures, with a look very similar to the adult version and the same characteristics of stability, support, protection and superb traction.", 1, 85, "LA SPORTIVA", 85, new ImageIcon(getClass().getResource("/com/raven/images/img20.png"))));
        itemListKid.add(new ModelItem(21, "WILDCAT", "NMD City Stock 2", 1, 535, "LA SPORTIVA", 535, new ImageIcon(getClass().getResource("/com/raven/images/img21.png"))));
        itemListKid.add(new ModelItem(22, "NIKE AIR FORCE 1 '07'", "The radiance lives on in the Nike Air Force 1 '07, the basketball original that puts a fresh spin on what you know best: durably stitched overlays, clean finishes and the perfect amount of flash to make you shine.", 1, 535, "NIKE", 535, new ImageIcon(getClass().getResource("/com/raven/images/img22.png"))));
        itemListKid.add(new ModelItem(23, "NIKE AIR MAX PLUS 3", "Taking the best from past generations, this Air Max Plus 3 honours its heritage with subtle nods to its predecessors. The cushioning foam and Max Air units put tried-and-tested bounce and comfort in your step. Futuristic design lines and slight pops of colour bring these kicks into the present day with eye-catching style.", 1, 535, "NIKE", 535, new ImageIcon(getClass().getResource("/com/raven/images/img23.png"))));





















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
        for (ModelItem item : itemListKid) {
            home.addItem(item);
        }

    }
    private void filterItemsByPrice(String priceRange) {
        ArrayList<ModelItem> filteredItems = new ArrayList<>();

        for (ModelItem item : itemListKid) {
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








    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        mainPanel1 = new com.raven.swing.MainPanel();
        textFieldAnimation1 = new com.raven.swing.TextFieldAnimation();
        combobox1 = new com.raven.swing.Combobox();

        javax.swing.GroupLayout mainPanel1Layout = new javax.swing.GroupLayout(mainPanel1);
        mainPanel1.setLayout(mainPanel1Layout);
        mainPanel1Layout.setHorizontalGroup(
                mainPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 850, Short.MAX_VALUE)
        );
        mainPanel1Layout.setVerticalGroup(
                mainPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 622, Short.MAX_VALUE)
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
    }// </editor-fold>


    // Variables declaration - do not modify
    private com.raven.swing.Combobox combobox1;
    private com.raven.swing.MainPanel mainPanel1;
    private com.raven.swing.TextFieldAnimation textFieldAnimation1;
    // End of variables declaration
}
