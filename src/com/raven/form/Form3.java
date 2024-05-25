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
                    String searchText = textFieldAnimation1.getText(); 
                    ArrayList<ModelItem> searchResults = new ArrayList<>(); 

                    
                    for (ModelItem item : itemListKid) {
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
        itemListKid = new ArrayList<>();
        itemListKid.add(new ModelItem(1, "Jordan Spizike Low", "The Spizike takes elements of five classic Jordans, combines them and gives you one iconic sneaker. It's an homage to Spike Lee formally introducing Hollywood and hoops in a culture moment. You get a great-looking pair of kicks with some history. What more can you ask for? Ya dig?", 1, 125, "NIKE", 125, new ImageIcon(getClass().getResource("/com/raven/images/kid1.png"))));
        itemListKid.add(new ModelItem(2, "Jordan Max Aura 5", "Whenyou need a shoe that's ready 24/7, it's gotta be the Max Aura 5. Inspired by the AJ3, this pair of kicks puts a modern spin on the classic. They're made from durable leather and textiles that sit atop a heel of Nike Air cushioning so you can walk, run or skate all day and still have fresh-feeling soles.", 1, 100, "NIKE", 100, new ImageIcon(getClass().getResource("/com/raven/images/kid2.png"))));
        itemListKid.add(new ModelItem(3, "Nike Air Max 270 SE", "Known for its bouncy cushioning, the Nike Air Max 270 gets its name from an extra-large Max Air unit in the heel that gives you a 270-degree view of one of our favourite innovations. Plus, the sock-like fit of the stretchy inner sleeve helps your feet feel secure and comfortable as you play.", 1, 130, "NIKE", 130, new ImageIcon(getClass().getResource("/com/raven/images/kid3.png"))));
        itemListKid.add(new ModelItem(4, "FORTARUN 2.0 CLOUDFOAM", "With an ultra-light and smooth body, these running-style adidas kids shoes are a great springboard for your baby to move at full capacity. Cloudfoam cushions keep your baby comfortable until bedtime. Made from a range of recycled materials, shoe bodies contain a minimum of 50% recycled content. This product represents one of our many solutions towards ending plastic waste.", 1, 51, "Adidas", 51, new ImageIcon(getClass().getResource("/com/raven/images/kid4.png"))));
        itemListKid.add(new ModelItem(5, "RAPIDASPORT BOUNCE", "Children are usually very active. Your baby will want to feel comfortable running, jumping and having fun throughout the day. These adidas children's shoes are faithful to the running shoe style design with an elegant design and Bounce midsole for a soft and elastic feel. The straps hug the legs firmly so that your baby can be confident in every step. Made from a range of recycled materials and containing a minimum of 50% recycled content, this product represents one of our many solutions towards ending plastic waste.", 1, 55, "Adidas", 55, new ImageIcon(getClass().getResource("/com/raven/images/kid5.png"))));
        itemListKid.add(new ModelItem(6, "STAR WARS KIDS", "Dedicated to helping your little Jedi train, these adidas kids shoes are the perfect companion for an adventure journey. With Star Wars-inspired designs, from the iconic graphic pattern on the heel to the battle-ready sole, running and playing activities will feel like a great expedition. The easy-to-slip slip-on style means that your baby doesn't need to waste time with his shoelaces and can be ready to explore right away. Whether you're racing to save the galaxy or simply playing bridge on the playing field, the Force will awaken on these shoes. This product contains a minimum of 20% recycled material. By reusing materials that have already been created, we help reduce waste and dependence on finite resources and minimize the emissions of the products we create.", 1, 59, "Adidas", 59, new ImageIcon(getClass().getResource("/com/raven/images/kid6.png"))));
        itemListKid.add(new ModelItem(7, "MARVEL DURAMO SL", "Your kid comic book fan can run around and have fun like never before with these adidas Marvel Duramo shoes. Marvel's ultra-lightweight design features Spider-Man on one side preparing to swing, while the spider web pattern on the shoe stimulates the imagination of adventures. The stretchy sticker makes it easy for your child to put on and remove his shoes to quickly dress up like his favorite Marvel Superhero. This product contains a minimum of 20% recycled material. By reusing materials that have already been created, we help reduce waste and dependence on finite resources and minimize the emissions of the products we create.", 1, 55, "Adidas", 55, new ImageIcon(getClass().getResource("/com/raven/images/kid7.png"))));
        itemListKid.add(new ModelItem(8, "X_PLRPHASE", "Regardless of lunch or soccer, you need a versatile pair of sneakers. Wear these classic adidas teen shoes. 3 translucent stripes for modern style, with BOOST and Bounce cushions for great comfort every day. Made from a group of recycled materials, shoe bodies contain a minimum of 50% recycled content. This product represents one of our many solutions towards ending plastic waste.", 1, 71, "Adidas", 71, new ImageIcon(getClass().getResource("/com/raven/images/kid8.png"))));
        itemListKid.add(new ModelItem(9, "OZMILLEN", "Inspired by the iconic style of the 90s, this adidas OZMILLEN teen shoe features a unique yet versatile design. Eye-catching exterior details stand out on the mesh body of the shoe and provide an energetic feel. Adiplus cushions and rubber outsoles provide comfort throughout the day in all your activities.", 1, 86, "Adidas", 86, new ImageIcon(getClass().getResource("/com/raven/images/kid9.png"))));
        itemListKid.add(new ModelItem(10, "RACER IRON MAN MARVEL", "Equip the superhero in training with his favorite Avenger-worthy shoes – this Racer Iron Man adidas x Marvel shoe. The woven body of the shoe is attractive thanks to the colors of the Iron Man armor, complete with the superhero's signature on each heel. From darting off the slide to racing to see who's faster on the sidewalk, your baby's legs will always fit snugly and comfortably, just like Tony Stark in Iron Man armor. This product contains a minimum of 20% recycled material. By reusing materials that have already been created, we contribute to reducing waste and reducing dependence on finite resources, as well as reducing emissions from the products we produce.", 1, 59, "Adidas", 59, new ImageIcon(getClass().getResource("/com/raven/images/kid10.png"))));
        itemListKid.add(new ModelItem(11, "TENSAUR", "Your baby is learning new skills, always needs shoes that can accompany him. This classic adidas trainer model has a rubber outsole that feels smooth when running around but leaves no marks on wooden floors. Therefore, these shoes are suitable both at school as well as when practicing sports. Made from a range of recycled materials, shoe bodies contain a minimum of 50% recycled content. This product represents one of our many solutions towards ending plastic waste.", 1, 39, "Adidas", 39, new ImageIcon(getClass().getResource("/com/raven/images/kid11.png"))));
        itemListKid.add(new ModelItem(12, "COUNTRY XLG CF EL C", "NMD City Stock 2", 1, 63, "Adidas", 63, new ImageIcon(getClass().getResource("/com/raven/images/kid12.png"))));
        itemListKid.add(new ModelItem(13, "GRAND COURT X DISNEY", "Disney's most stylish mouse girl puts her signature stamp on these adidas Grand Court children's shoes. Vibrant summer accents make this low-neck design ideal on a sunny day, with stretchy shoelaces and top straps for easy insertion and removal. The iconic Disney graphic motif brings a magical mark anywhere. This product contains a minimum of 20% recycled material. By reusing materials that have already been created, we contribute to reducing waste and reducing dependence on finite resources, as well as reducing emissions from the products we produce.", 1, 51, "Adidas", 51, new ImageIcon(getClass().getResource("/com/raven/images/kid13.png"))));
        itemListKid.add(new ModelItem(14, "RIVALRY LOW", "Opposing styles have never been so beautiful. These adidas Rivalry low-neck teen shoes are a new version that combines sport and style. With a design that blends punk, skateboarding culture, and basketball style, you're sure to catch all eyes wearing these high-end shoes.", 1, 79, "Adidas", 79, new ImageIcon(getClass().getResource("/com/raven/images/kid14.png"))));
        itemListKid.add(new ModelItem(15, "RACER CAPTAIN AMERICA MARVEL", "Child heroes can also dress up for adventure with these adidas sneakers. Inspired by Marvel's Captain America, eye-catching details such as 3 Stripes, toe cap and logo on the heel of the shoe will unleash the super soldier inside the baby. In the middle of a break, the breathable mesh body and EVA injection molded midsole provide comfort and support for your baby's running and play. After school, the classic knitting design combined with jeans creates a bold style, ready to respond to bullies and fight evil immediately. This product contains a minimum of 20% recycled material. By reusing materials that have already been created, we contribute to reducing waste and reducing dependence on finite resources, as well as reducing emissions from the products we produce.", 1, 59, "Adidas", 59, new ImageIcon(getClass().getResource("/com/raven/images/kid15.png"))));
        itemListKid.add(new ModelItem(16, "FORTARUN X DISNEY", "The adventure beckons as she slips into these adidas x Disney Fortarun shoes. With a striking print of Minnie Mouse on the side of the shoe, these vivid sneakers will bring joy to every child. Get ready for an imaginative adventure right in your backyard or local park, the ultra-lightweight synthetic body and flexible outsole keep your baby's feet running. The straps make it easy for her to wear shoes, so that she feels independent when she and Minnie go out looking for new adventures. This product contains a minimum of 20% recycled material. By reusing materials that have already been created, we contribute to reducing waste and reducing dependence on finite resources, as well as reducing emissions from the products we produce.", 1, 59, "Adidas", 59, new ImageIcon(getClass().getResource("/com/raven/images/kid16.png"))));
        itemListKid.add(new ModelItem(17, "STAN SMITH", "Each generation has created its own version of Stan Smith. Now it's your turn. These teenage shoes have a neat look and edgy feel just like the original. What's the difference? It's the material. Recycled components are part of adidas' commitment towards reducing waste. It's time to put on your shoes and write a whole new story. This product is sewn with Primegreen technology fabric, belonging to the line of specialized recycled materials. The shoe body contains 50% recycled content. Do not use virgin polyester.", 1, 67, "Adidas", 67, new ImageIcon(getClass().getResource("/com/raven/images/kid17.png"))));
        itemListKid.add(new ModelItem(18, "Nike Air Max Dn", "Say hello to the next generation of Air technology. The Air Max Dn features our Dynamic Air unit system of dual-pressure tubes, creating a bouncy sensation with every step. This results in a futuristic design that's comfortable enough to wear all day. Go ahead—Feel The Unreal.", 1, 130, "NIKE", 130, new ImageIcon(getClass().getResource("/com/raven/images/kid18.png"))));
        itemListKid.add(new ModelItem(19, "Nike Air Max 270", "Boasting the first-ever Max Air unit created specifically for Nike Sportswear, the Nike Air Max 270 delivers an Air unit that absorbs and gives back energy with every springy step. Updated for modern comfort, it nods to the original 1991 Air Max 180 with its exaggerated tongue top and heritage tongue logo.", 1, 109, "NIKE", 109, new ImageIcon(getClass().getResource("/com/raven/images/kid19.png"))));
        itemListKid.add(new ModelItem(20, "Nike Air Max 280", "The Nike Air Max 280 combines the exaggerated tongue from the Air Max 180 and classic elements from the Air Max 93. It features Nike's biggest heel Air unit yet for a soft ride that feels as impossible as it looks.", 1, 120, "NIKE", 120, new ImageIcon(getClass().getResource("/com/raven/images/kid20.png"))));
        itemListKid.add(new ModelItem(21, "Nike Air Max SYSTM", "Cassette mixtapes, music videos and shopping centre arcades—the '80s had it all. We're celebrating that gnarly era with the Nike Air Max SYSTM. From the big and bold Air unit in the heel to design lines inspired by our favourite throwback Air Max shoes, these kicks are all about bringing back what's cool and introducing it to a new generation.", 1, 73, "NIKE", 73, new ImageIcon(getClass().getResource("/com/raven/images/kid21.png"))));
        itemListKid.add(new ModelItem(22, "Nike Air Max Plus", "Who says kids don't deserve big cushioning? Not us. Get a taste of our legendary Tuned Air technology with the Nike Air Max Plus. From the school playground to your back garden, these comfy kicks are ready to play with breathable mesh fabric up top and a durable rubber sole underfoot. Plus, wavy design lines and iconic plastic accents celebrate bold style.", 1, 140, "NIKE", 140, new ImageIcon(getClass().getResource("/com/raven/images/kid22.png"))));
        itemListKid.add(new ModelItem(23, "Air Max 1", "With its easygoing lines, heritage athletics look and, of course, visible Air cushioning, the Nike Air Max 1 is the perfect finish to any outfit. The rich mixture of materials adds depth while making it a durable and lightweight shoe for everyday wear.", 1, 100, "NIKE", 100, new ImageIcon(getClass().getResource("/com/raven/images/kid23.png"))));
        




















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
