package raven.table;

import ConnectMySQL.MySQL;
import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.raven.model.ModelItem;
import com.raven.swing.ScrollBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Testt extends javax.swing.JPanel {
    
    private int x = 1;
    private JCheckBox checkBox;
    private Boolean invoicingEnabled;
    private double totalWithTax;
    private ImageIcon qrImage;
    public Testt() throws SQLException {
        jTextArea1 = new JTextArea();
        initComponents();
        testData();
        setData();
        jScrollPane1.setVerticalScrollBar(new ScrollBar());
    }

    private void setData() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<ModelItem> items = MySQL.getConnection().getItems();

        for (ModelItem item : items) {
            model.addRow(new Object[]{
                item,
                item.getItemName(),
                item.getDescription(),
                item.getQty(),
                item.getPrice(),
                item.getBrandName(),
                item.getTotal(),
                false
            });
        }
    }

    private void testData() {
        table.getColumnModel().getColumn(0).setCellRenderer(new ModelItemCellRenderer());
        table.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (col == 7) {
                    updateTextArea();
                    sumAmount();
                }
            }
        });
        table.getColumnModel().getColumn(3).setCellEditor(new QtyCellEditor(new EventCellInputChange() {
            @Override
            public void inputChanged() {
                sumAmount();
    
            }
        }));
        table.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);
                return this;
            }
        });
        sumAmount();
        

    }
    private void sumAmount() {
    double total = 0.0;
    double taxRate = 0.10;
    for (int i = 0; i < table.getRowCount(); i++) {
        Boolean isChecked = (Boolean) table.getValueAt(i, 7); 
        if (Boolean.TRUE.equals(isChecked)) {
            ModelItem item = (ModelItem) table.getValueAt(i, 0);
            if (item != null) {
                total += item.getTotal();
            }
        }
    }
    double taxAmount = total * taxRate;
    totalWithTax = total + taxAmount;
    DecimalFormat df = new DecimalFormat("$ #,##0.00");
    DecimalFormat df1 = new DecimalFormat(" #,##0.00");
    lbTotal.setText(df.format(total));
    jTextField1.setText("10");
    jTextField2.setText(df1.format(total));
    jTextField3.setText(df1.format(totalWithTax));
        
}
   
   

   
  private void updateTextArea() {
    int x = 1;
    LocalDateTime currentTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
    String formattedTime = currentTime.format(formatter);
    StringBuilder textAreaContent = new StringBuilder(" ********************Baron Shop********************\n"
                                                      + " Time: " + formattedTime + "\n"
                                                      + " **************************************************\n\n");
    jTextArea1.setFont(new Font("Courier New", Font.PLAIN, 12));
    textAreaContent.append(String.format("%-5s%-20s%-10s%-5s%-10s\n", " No.", " Name", " ($)Price", " Qty", " ($)Total"));

    double grandTotal = 0.0; 
    for (int i = 0; i < table.getRowCount(); i++) {
        Boolean isChecked = (Boolean) table.getValueAt(i, 7);
        if (Boolean.TRUE.equals(isChecked)) {
            String itemName = (String) table.getValueAt(i, 1);
            double price = (double) table.getValueAt(i, 4);
            int qty = (int) table.getValueAt(i, 3);
            double total = price * qty; 
            Object totalObj = table.getValueAt(i, 6);
            if (totalObj instanceof Double) {
                total = (double) totalObj;
            }
            if (itemName.length() > 20) {
                itemName = itemName.substring(0, 15) + "...";
            }
            textAreaContent.append(String.format(" %-5d%-20s$%-10.2f%-5d$%-10.2f\n", x , itemName , price , qty , total));
            x++;
            grandTotal += total; 
        }
    }
    jTextArea1.setText(textAreaContent.toString());
    jTextField1.setText(String.format("%.2f", grandTotal)); 
} 







    private void buySelectedItems() {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    ImageIcon customIcon = new ImageIcon("C:\\Xaydungungdungmuabangiaytrendesktop - Copy\\src\\com\\raven\\icon\\tichxanh.png");
    boolean anyItemSelected = false;
   
    for (int i = 0; i < table.getRowCount(); i++) {
        Boolean buy = (Boolean) model.getValueAt(i, 7);
        if (buy != null && buy) {
            anyItemSelected = true;
            String itemName = (String) model.getValueAt(i, 1);
            String description = (String) model.getValueAt(i, 2);
            int qty = (int) model.getValueAt(i, 3);
            Object priceObj = model.getValueAt(i, 4);
            double price = 0.0;
            if (priceObj instanceof Double) {
                price = (Double) priceObj;
            } else if (priceObj instanceof String) {
                price = Double.parseDouble((String) priceObj);
            }
            String brandName = (String) model.getValueAt(i, 5);
            Object totalObj = model.getValueAt(i, 6);
            double total = price * qty;
            if (totalObj instanceof Double) {
                total = (Double) totalObj;
                
            }
            MySQL.getConnection().addItem1(itemName, description, qty, price, brandName, total);
        }
        
    }
    StringBuilder sb = new StringBuilder("\n\n **************************************************\n");
    String taxString = jTextField1.getText().replace(".", "").replace(",", ".");
    String subTotalString = jTextField2.getText().replace(".", "").replace(",", ".");
    String totalString = jTextField3.getText().replace(".", "").replace(",", ".");
    float tax = Float.parseFloat(taxString);
    float subTotal = Float.parseFloat(subTotalString);
    float total = Float.parseFloat(totalString);
    sb.append(String.format("%-15s%-6.2f%%\n\n", "\n Tax", tax));
    sb.append(String.format("%-15s$%-10.2f\n\n", " SubTotal", subTotal));
    sb.append(String.format("%-15s$%-10.2f\n", " Total", total));
    jTextArea1.setText(jTextArea1.getText() + sb.toString() + "\n\n **************************************************\n\n");
    if (!anyItemSelected) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một sản phẩm để mua!", "Thông báo", JOptionPane.WARNING_MESSAGE);
    } else {
        Object[] options = {"Ok", "In hóa đơn "};
        int result = JOptionPane.showOptionDialog(this,
                "Thanh toán thành công, cảm ơn quý khách!",
                "Thông báo",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                customIcon,
                options,
                options[0]);

        if (result == JOptionPane.NO_OPTION) {
            inHoaDon();
        }
    }
   
    
    
}

 


 public void reset() {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    int rowCount = model.getRowCount();
    for (int i = 0; i < rowCount; i++) {
        model.setValueAt(false, i, 7);
    }
    ((DefaultTableModel) table.getModel()).fireTableDataChanged();
    jTextArea1.setText("");
        jTextField1.setText("10");
        jTextField2.setText("0.0");
        jTextField3.setText("0.0");
        jTextField3.setText("$ 0.00");
        
}
    private void generateQRCode() {
    String accountNo = "0979487360";  
    String accountName = "LE DUY DAT";
    double exchangeRate = 25462.0; 
    double amountInVND = totalWithTax * exchangeRate;
    String amount = String.format("%.0f", amountInVND);

    if (amount.length() > 13) {
        JOptionPane.showMessageDialog(this, "Số tiền quá lớn để xử lý", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String addInfo = "THANH TOAN HOA DON";
    String template = "qr_only";
    String bank = "(970422) MBBank";
    String bankBin = bank.substring(1, bank.indexOf(')'));
    ApiRequest apiRequest = new ApiRequest(accountNo, accountName, Integer.parseInt(bankBin), Double.parseDouble(amount), addInfo, "text", template);
    String jsonRequest = new Gson().toJson(apiRequest);
    
    OkHttpClient client = new OkHttpClient();
    MediaType JSON = MediaType.get("application/json; charset=utf-8");
    RequestBody body = RequestBody.create(jsonRequest, JSON);
    Request request = new Request.Builder()
            .url("https://api.vietqr.io/v2/generate")
            .post(body)
            .build();

    try {
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String jsonResponse = response.body().string();
            System.out.println("API Response: " + jsonResponse); 
            ApiResponse apiResponse = new Gson().fromJson(jsonResponse, ApiResponse.class);
            
            if (apiResponse != null && apiResponse.getData() != null) {
                String qrDataURL = apiResponse.getData().getQrDataURL().replace("data:image/png;base64,", "");
                qrImage = new ImageIcon(Base64ToImage(qrDataURL));
                qrImage = new ImageIcon(qrImage.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH)); 
                jLabel6.setIcon(qrImage);
            } else {
                JOptionPane.showMessageDialog(this, "Phản hồi API không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                System.err.println("Phản hồi API không hợp lệ: " + jsonResponse); 
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không thể tạo mã QR", "Lỗi", JOptionPane.ERROR_MESSAGE);
            System.err.println("Yêu cầu API thất bại: " + response.message());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


private BufferedImage Base64ToImage(String base64String) {
    try {
        byte[] imageBytes = java.util.Base64.getDecoder().decode(base64String);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
        return ImageIO.read(bis);
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}


   
 private void inHoaDon() {
    String vanBanHoaDon = jTextArea1.getText();  
    ImageIcon qrImage = this.qrImage; 

    Printable hoaDonPrintable = new Printable() {
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if (pageIndex > 0) {
                return NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
             
            double pageWidth = pageFormat.getImageableWidth();

            g2d.setFont(new Font("Courier New", Font.PLAIN, 12));

            int y = 0;
            for (String line : vanBanHoaDon.split("\n")) {
                FontMetrics metrics = g2d.getFontMetrics();
                int x = (int) ((pageWidth - metrics.stringWidth(line)) / 2); 
                y += metrics.getHeight();
                g2d.drawString(line, x, y);
            }

            if (qrImage != null) {
                int qrWidth = qrImage.getIconWidth();
                int qrHeight = qrImage.getIconHeight();
                int qrX = (int) ((pageWidth - qrWidth) / 2);
                int qrY = y + g2d.getFontMetrics().getHeight();
                g2d.drawImage(qrImage.getImage(), qrX, qrY, null);
                
                y = qrY + qrHeight + g2d.getFontMetrics().getHeight(); 
            }

            String thankYouMessage = "\n\n **********THANKS YOU AND SEE YOU AGAIN!********** \n\n EMAIL ✍ ledat16072005@gmmail.com \n PHONE NUMBER ☏ 0979487360";
            for (String line : thankYouMessage.split("\n")) {
                FontMetrics metrics = g2d.getFontMetrics();
                g2d.setFont(new Font("Courier New", Font.ITALIC,12));
                int x = (int) ((pageWidth - metrics.stringWidth(line)) / 2);
                y += metrics.getHeight();
                g2d.drawString(line, x, y);
            }

            return PAGE_EXISTS;
        }
    };

    PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintable(hoaDonPrintable);

   

    boolean doPrint = job.printDialog();
    if (doPrint) {
        try {
            job.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
}




    

    class ApiRequest {
    private String accountNo;
    private String accountName;
    private int acqId;
    private double amount;
    private String addInfo;
    private String format;
    private String template;

    public ApiRequest(String accountNo, String accountName, int acqId, double amount, String addInfo, String format, String template) {
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.acqId = acqId;
        this.amount = amount;
        this.addInfo = addInfo;
        this.format = format;
        this.template = template;
    }
}

class ApiResponse {
    private String code;
    private String desc;
    private Data data;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public Data getData() {
        return data;
    }

    class Data {
        private String qrDataURL;

        public String getQrDataURL() {
            return qrDataURL;
        }
    }
}

class BankResponse {
    private String code;
    private String desc;
    private List<BankData> data;

    public List<BankData> getData() {
        return data;
    }
}

class BankData {
    private String bin;
    private String shortName;

    public String getCustomName() {
        return String.format("(%s) %s", bin, shortName);
    }
}
 




    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTotal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(245, 245, 245));

        lbTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTotal.setText("$ 0.00");

        table.setBorder(null);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Descroption", "Quantity", "Price", "Brand", "Total", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(50);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setPreferredWidth(0);
            table.getColumnModel().getColumn(0).setMaxWidth(0);
            table.getColumnModel().getColumn(2).setMinWidth(0);
            table.getColumnModel().getColumn(2).setPreferredWidth(0);
            table.getColumnModel().getColumn(2).setMaxWidth(0);
            table.getColumnModel().getColumn(3).setMinWidth(70);
            table.getColumnModel().getColumn(3).setPreferredWidth(70);
            table.getColumnModel().getColumn(3).setMaxWidth(70);
            table.getColumnModel().getColumn(4).setMinWidth(80);
            table.getColumnModel().getColumn(4).setPreferredWidth(80);
            table.getColumnModel().getColumn(4).setMaxWidth(80);
            table.getColumnModel().getColumn(5).setMinWidth(90);
            table.getColumnModel().getColumn(5).setPreferredWidth(90);
            table.getColumnModel().getColumn(5).setMaxWidth(90);
            table.getColumnModel().getColumn(6).setMinWidth(100);
            table.getColumnModel().getColumn(6).setPreferredWidth(100);
            table.getColumnModel().getColumn(6).setMaxWidth(100);
            table.getColumnModel().getColumn(7).setMinWidth(50);
            table.getColumnModel().getColumn(7).setPreferredWidth(50);
            table.getColumnModel().getColumn(7).setMaxWidth(50);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Total:");

        jButton2.setBackground(java.awt.Color.black);
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Buy");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(java.awt.Color.black);
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Delete");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel2.setText("|");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea1);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        // Code of sub-components and layout - not shown here

        // Code adding the component to the parent container - not shown here
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(66);
        jScrollPane2.getVerticalScrollBar().setBlockIncrement(64);

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("0.0");
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("0.0");
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("0.0");
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setText("Tax (%)");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setText("Sub Total ($)");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setText("Total ($)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText(" PAYMENT QR CODE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(16, 16, 16)))))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(21, Short.MAX_VALUE))))
        );

        //jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        // Code of sub-components and layout - not shown here

        // Code adding the component to the parent container - not shown here
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(66);
        jScrollPane1.getVerticalScrollBar().setBlockIncrement(64);
    }// </editor-fold>//GEN-END:initComponents



    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MySQL mySQL = MySQL.getConnection();

        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            ModelItem modelItem = (ModelItem) table.getValueAt(selectedRow, 0);
            int itemID = modelItem.getItemID();
            mySQL.deleteItem(itemID);
            try {
                setData(); 
            } catch (SQLException ex) {
                Logger.getLogger(Testt.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
        jButton3.setBackground(Color.RED);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        // TODO add your handling code here:
        jButton3.setBackground(Color.BLACK);
    }//GEN-LAST:event_jButton1MouseExited


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        generateQRCode();
        buySelectedItems();
        
       

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        // TODO add your handling code here:
        jButton2.setBackground(Color.GREEN);
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        // TODO add your handling code here:
        jButton2.setBackground(Color.BLACK);
    }//GEN-LAST:event_jButton2MouseExited

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextArea1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseClicked
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jTextArea1MouseClicked

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
